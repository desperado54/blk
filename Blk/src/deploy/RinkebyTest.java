package deploy;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import solidity.SimpleStorage;

public class RinkebyTest {

	public static void main(String[] args) throws Exception {
		// We start by creating a new web3j instance to connect to remote nodes on the
		// network.
		// Note: if using web3j Android, use Web3jFactory.build(...
		Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/4672999f09c142098b712cdfdf6deb13")); // FIXME:
																														// Enter
																														// your
																														// Infura
																														// token
																														// here;
		System.err.println(
				"Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// We then need to load our Ethereum wallet file
		// FIXME: Generate a new wallet file using the web3j command line tools
		// https://docs.web3j.io/command_line.html
		Credentials credentials = Credentials
				.create("e0a2afdfea4ede6ea5ce1ac3f6807fa9205234c09056de89bb9901d51a1070df");
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

//      TransactionReceipt transferReceipt = Transfer.sendFunds(
//                web3j, credentials,
//                "0x2BAf968Bcbf12a5116cE1D4901C00BCfb2379507",  // you can put any address here
//                new BigDecimal(10000000), Convert.Unit.GWEI)  // 1 wei = 10^-18 Ether
//                .send();
//		System.err.println("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
//				+ transferReceipt.getTransactionHash());
		
		TransactionManager tm = new RawTransactionManager(web3j, credentials);

        ContractGasProvider contractGasProvider = new DefaultGasProvider();
//        SimpleStorage contract = SimpleStorage.deploy(
//                web3j,
//                credentials,
//                contractGasProvider
//                ).send();
//        System.err.println("contract deployed: " + contract.getContractAddress());
        
	    String contractAddress = "0x4Ca50fBCE84Ce04be9E18A9A246e13f838c87190"; // The deployed contract address, taken
	    SimpleStorage contract = SimpleStorage.load(contractAddress, web3j, credentials, contractGasProvider);
        
        System.err.println("Value stored in remote smart contract: " + contract.store(BigInteger.valueOf(123L)).send());
        contract.addPerson("nidaye", BigInteger.valueOf(1000));
        
        // Lets modify the value in our smart contract
        BigInteger num = contract.retrieve().send();
        
        System.err.println("stored value = " + num);
        System.err.println("people = " + contract.people(BigInteger.valueOf(0)));

	}
}
