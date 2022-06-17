package deploy;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;

import solidity.SimpleStorage;

public class App {
    private final static String PRIVATE_KEY = "917ac209999dd6134e1d9d7dc7f18decbdb0095bd1a281007c1386cd14ce28a2";

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    
    private final static String RECEIPIENT_ADDRESS = "0x1E08439b10B764Fc98E25e940a23B3F7390e2FE4";

    private final static String CONTRACT_ADDRESS="0xc568Ba36c2892b4EF6157e64f221c516BDE81a72";

    public static void main(String[] args) throws Exception {
		// We start by creating a new web3j instance to connect to remote nodes on the
		// network.
		// Note: if using web3j Android, use Web3jFactory.build(...
		Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545")); // FIXME:

		System.err.println(
				"Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// We then need to load our Ethereum wallet file
		// FIXME: Generate a new wallet file using the web3j command line tools
		// https://docs.web3j.io/command_line.html
		Credentials credentials = Credentials
				.create(PRIVATE_KEY);
//        Credentials credentials =
//                WalletUtils.loadCredentials(
//                        "<password>",
//                        "/path/to/<walletfile>");
		System.err.println("Credentials loaded");

		// FIXME: Request some Ether for the Rinkeby test network at
		// https://www.rinkeby.io/#faucet
//        System.err.println("Sending 1 Wei ("
//                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");

		EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
				.send();
		BigInteger balance = ethGetBalance.getBalance();

		System.err.println("sender balance: " + balance);

      TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                RECEIPIENT_ADDRESS,  // you can put any address here
                new BigDecimal(1000000), Convert.Unit.GWEI)  // 1 wei = 10^-18 Ether
                .send();
		System.err.println("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
				+ transferReceipt.getTransactionHash());
		
		TransactionManager tm = new RawTransactionManager(web3j, credentials);

		EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
		System.err.println("gas price: " + ethGasPrice.getGasPrice().longValue());
		System.err.println("block gas limit=" + web3j.ethGetBlockByHash("0x3af042a5ed3cecc21146badc63f913177d1bfafd5f844a378e32d5d9001c742b", true).getId());

        ContractGasProvider contractGasProvider = new ContractGasProvider() {

			@Override
			public BigInteger getGasPrice(String contractFunc) {
				// TODO Auto-generated method stub
				return ethGasPrice.getGasPrice();
			}

			@Override
			public BigInteger getGasPrice() {
				// TODO Auto-generated method stub
				return ethGasPrice.getGasPrice();
			}

			@Override
			public BigInteger getGasLimit(String contractFunc) {
				// TODO Auto-generated method stub
				return BigInteger.valueOf(2000000L);
			}

			@Override
			public BigInteger getGasLimit() {
				// TODO Auto-generated method stub
				return BigInteger.valueOf(2000000L);
			}
        	
        };
        
        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials
        );
        
        Transfer transfer = new Transfer(web3j, transactionManager);

        TransactionReceipt transactionReceipt = transfer.sendFunds(
                "0x1E08439b10B764Fc98E25e940a23B3F7390e2FE4",
                BigDecimal.ONE,
                Convert.Unit.ETHER,
                GAS_PRICE,
                GAS_LIMIT
        ).send();

        System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
        
//        SimpleStorage contract = SimpleStorage.deploy(
//                web3j,
//                credentials,
//                contractGasProvider
//                ).send();
//        
//        System.err.println("contract deployed: " + contract.getContractAddress());
        
	    SimpleStorage contract = SimpleStorage.load("0xc568Ba36c2892b4EF6157e64f221c516BDE81a72", web3j, credentials, contractGasProvider);
	    
        //web3j.ethEstimateGas(t);
        
        System.err.println("Value stored in remote smart contract: " + contract.store(BigInteger.valueOf(113L)).send());

        // Lets modify the value in our smart contract
        BigInteger num = contract.retrieve().send();
        
        System.err.println("stored value = " + num);
    }

}
