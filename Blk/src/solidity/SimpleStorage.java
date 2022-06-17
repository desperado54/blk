package solidity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class SimpleStorage extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610588806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80632e64cec11461005c5780636057361d146100735780636f760f41146100885780638bab8dd51461009b5780639e7a13ad146100c6575b600080fd5b6000545b6040519081526020015b60405180910390f35b610086610081366004610245565b600055565b005b610086610096366004610301565b6100e7565b6100606100a9366004610346565b805160208183018101805160038252928201919093012091525481565b6100d96100d4366004610245565b61018d565b60405161006a9291906103b3565b6040805180820190915281815260208101838152600280546001810182556000829052835191027f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace8101918255915190917f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5acf01906101659082610476565b505050806003836040516101799190610536565b908152604051908190036020019020555050565b6002818154811061019d57600080fd5b600091825260209091206002909102018054600182018054919350906101c2906103ed565b80601f01602080910402602001604051908101604052809291908181526020018280546101ee906103ed565b801561023b5780601f106102105761010080835404028352916020019161023b565b820191906000526020600020905b81548152906001019060200180831161021e57829003601f168201915b5050505050905082565b60006020828403121561025757600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261028557600080fd5b813567ffffffffffffffff808211156102a0576102a061025e565b604051601f8301601f19908116603f011681019082821181831017156102c8576102c861025e565b816040528381528660208588010111156102e157600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806040838503121561031457600080fd5b823567ffffffffffffffff81111561032b57600080fd5b61033785828601610274565b95602094909401359450505050565b60006020828403121561035857600080fd5b813567ffffffffffffffff81111561036f57600080fd5b61037b84828501610274565b949350505050565b60005b8381101561039e578181015183820152602001610386565b838111156103ad576000848401525b50505050565b82815260406020820152600082518060408401526103d8816060850160208701610383565b601f01601f1916919091016060019392505050565b600181811c9082168061040157607f821691505b60208210810361042157634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561047157600081815260208120601f850160051c8101602086101561044e5750805b601f850160051c820191505b8181101561046d5782815560010161045a565b5050505b505050565b815167ffffffffffffffff8111156104905761049061025e565b6104a48161049e84546103ed565b84610427565b602080601f8311600181146104d957600084156104c15750858301515b600019600386901b1c1916600185901b17855561046d565b600085815260208120601f198616915b82811015610508578886015182559484019460019091019084016104e9565b50858210156105265787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60008251610548818460208701610383565b919091019291505056fea2646970667358221220529bf12ac499df19c2cf133326964eac45cdc0ae5c7b299ecaa42d782ecb465364736f6c634300080f0033";

    public static final String FUNC_ADDPERSON = "addPerson";

    public static final String FUNC_NAMETOFAVORITENUMBER = "nameToFavoriteNumber";

    public static final String FUNC_PEOPLE = "people";

    public static final String FUNC_RETRIEVE = "retrieve";

    public static final String FUNC_STORE = "store";

    @Deprecated
    protected SimpleStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleStorage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SimpleStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SimpleStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addPerson(String _name, BigInteger _favoriteNumber) {
        final Function function = new Function(
                FUNC_ADDPERSON, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_favoriteNumber)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> nameToFavoriteNumber(String param0) {
        final Function function = new Function(FUNC_NAMETOFAVORITENUMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, String>> people(BigInteger param0) {
        final Function function = new Function(FUNC_PEOPLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, String>>(function,
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> retrieve() {
        final Function function = new Function(FUNC_RETRIEVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> store(BigInteger _favoriteNumber) {
        final Function function = new Function(
                FUNC_STORE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_favoriteNumber)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static SimpleStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SimpleStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SimpleStorage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SimpleStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SimpleStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SimpleStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleStorage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleStorage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
