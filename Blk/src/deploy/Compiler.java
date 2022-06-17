package deploy;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

public class Compiler {
	public static void main(String[] args) throws IOException {
        Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion);
	}
}
