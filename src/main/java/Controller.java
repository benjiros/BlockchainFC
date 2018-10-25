import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

public class Controller {

    // Path to ethereum base dir (This account will be debited)
    private final String LOCATION_SOURCE_ACCOUNT = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-52-40.358632659Z--30c45fea0f173249978e90aa3d76add6222679bf";

    // Password of the source account
    private final String SOURCE_ACCOUNT_PASSWORD = "liverpool";

    // Account to sent the ethers to
    private final String TARGET_ACCOUNT = "616ac3f434f8aae021db944d350bc17ec1b1f59e"; //Account 2 -> arsenal

    // Amount of ether to send in transaction
    private final int AMOUNT = 42;


    public void transferProposal() throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {
        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account
        int AMOUNT_TO_SEND_OFFER = 1;
        Transfer.sendFunds(web3, credentials, TARGET_ACCOUNT, BigDecimal.valueOf(AMOUNT_TO_SEND_OFFER), Convert.Unit.ETHER).sendAsync().get();

        .deploy(web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT).send();
    }


    public void transferEther() throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentialsWallet1 = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account

        Transfer.sendFunds(web3, credentialsWallet1, TARGET_ACCOUNT, BigDecimal.valueOf(AMOUNT), Convert.Unit.ETHER).sendAsync().get();
    }
}