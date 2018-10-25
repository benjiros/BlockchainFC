import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * This class handles the actual ether transfer (connects to geth via the web3j library)
 */
class EtherShifter
{
    // Path to ethereum base dir (This account will be debited)
    private final String LOCATION_SOURCE_ACCOUNT = "/Users/benjiros/Library/Ethereum/papyEstNul/keystore/UTC--2018-10-08T07-03-35.026817197Z--2a23ea990b784141dc3b2f7c09e17c944a143de1";

    // Password of the source account
    private final String SOURCE_ACCOUNT_PASSWORD = "";

    // Account to sent the ethers to
    private final String TARGET_ACCOUNT = "0x2a23ea990b784141dc3b2f7c09e17c944a143de1";

    // Amount of ether to send in transaction
    private final int AMOUNT = 42;

    public void transferEther() throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentialsWallet1 = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account
        Transfer.sendFunds(web3, credentialsWallet1, TARGET_ACCOUNT, BigDecimal.valueOf(AMOUNT), Convert.Unit.ETHER).sendAsync().get();
    }
}
