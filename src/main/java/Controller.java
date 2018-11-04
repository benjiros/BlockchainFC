import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import smartContracts.Mercato.Mercato;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class Controller {

    // Path to ethereum base dir (This account will be debited)

    private String Directory = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/";

    private final String LOCATION_SOURCE_ACCOUNT = Directory + "UTC--2018-10-26T15-12-10.854285357Z--a35f975da43a9f032e262ccd51a41923290649fe";

    // Password of the source account
    private final String SOURCE_ACCOUNT_PASSWORD = "coinbase";

    // Account to sent the ethers to
    private final String TARGET_ACCOUNT = "0x616ac3f434f8aae021db944d350bc17ec1b1f59e"; //Account 2 -> arsenal



    public String MERCATO_ADDRESS = null;
    // Connect to local node
    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/


    public void initiate()  throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {
        // Connect to local node
        //Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account
        int AMOUNT_TO_OPEN_MERCATO = 1;
        Transfer.sendFunds(web3, credentials, TARGET_ACCOUNT, BigDecimal.valueOf(AMOUNT_TO_OPEN_MERCATO), Convert.Unit.ETHER).sendAsync().get();

        Mercato mercato;
        try {
       //     mercato = Mercato.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT).send();
            mercato = Mercato.deploy(web3, credentials,DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT).send();

            MERCATO_ADDRESS = mercato.getContractAddress();
            System.out.println("LE MERCATO EST OUVERT");
            System.out.println("mercato address : "+ MERCATO_ADDRESS);

        } catch (Exception e){
            System.err.println(e);
        }
    }

    public void transferProposal(String clubAccount, String clubPassword, String player, int duration) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(clubPassword,clubAccount);

        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        System.out.println("mercato address : "+ MERCATO_ADDRESS);

        try{
           // System.out.println(mercato.proposeTransferToPlayer(player,BigInteger.valueOf(duration), BigInteger.valueOf(1)).send());
            mercato.proposeTransferToPlayer(player,BigInteger.valueOf(duration), BigInteger.valueOf(1)).send();
            System.out.println("Transfer offer done");
        } catch (Exception e){

            System.err.println(e);
        }

    }

    public BigInteger getNumberProposalClub(String clubAccount, String clubPassword) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        Credentials credentials = WalletUtils.loadCredentials(clubPassword,clubAccount);

        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        System.out.println("mercato address : "+ MERCATO_ADDRESS);
        BigInteger nbOfferClub = null;
        try{

            nbOfferClub = mercato.getTransferProposalNumberClub().send();
            System.out.println("votre club a reçu : " + nbOfferClub + "offre(s)");

        } catch (Exception e){

            System.err.println(e);
        }
        return nbOfferClub;

    }


    public void registerPlayer(String playerAccount, String playerPassword) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {


        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(playerPassword, playerAccount);
        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);

        System.out.println("mercato address : "+ MERCATO_ADDRESS);
        mercato.registerPlayer();
    }

    public void registerPlayerToClub(String club, String playerAccount, String playerPassword, BigInteger duration , BigInteger price) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(playerPassword, playerAccount);
        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        System.out.println("mercato address : "+ MERCATO_ADDRESS);

        try{
            mercato.registerPlayer(club, duration , price);
            System.out.println("Player Registered");
        }
        catch (Exception e){
            System.err.println(e);
        }
    }


    public void employedOrNot (String playerAccount, String playerPassword) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(playerPassword, playerAccount);
        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        boolean employed;
        System.out.println("mercato address : "+ MERCATO_ADDRESS);

        try{
            employed = mercato.getPlayerEmployed().send();


            if(employed){
                System.out.println("You have a Club");
            }else{
                System.out.println("No club for you");
            }
        }

        catch (Exception e){
            System.err.println(e);
        }

    }


    public BigInteger getTransferPlayerProposalNumber(String playerAccount, String playerPassword) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {


        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(playerPassword, playerAccount);

        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        BigInteger nbOffers = null;
        try{
            nbOffers = mercato.getTransferProposalNumberPlayer().send();
            System.out.println("Vous avez reçu " + nbOffers + " offre(s)");
        } catch (Exception e){
            System.err.println(e);
        }
        return nbOffers;
    }




    public BigInteger getNumberOfPlayerInClub(String clubAccount, String clubPassword) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException{
        Credentials credentials = WalletUtils.loadCredentials( clubPassword, clubAccount);

        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        BigInteger nbPlayer = null;
        try{
            nbPlayer = mercato.getClubNumberOfPlayers().send();
            System.out.println("Votre club a " + nbPlayer + " joueur(s)");
        } catch (Exception e){
            System.err.println(e);
        }
        return nbPlayer;
    }

    public String getPlayerCurrentClub(String playerAccount, String playerPassword) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        Credentials credentials = WalletUtils.loadCredentials(playerPassword, playerAccount);

        Mercato mercato = Mercato.load(MERCATO_ADDRESS,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        String club = null;
        try{
            club = mercato.getPlayerCurrentClub().send();
            System.out.println("Votre club est : " + club );
        } catch (Exception e){
            System.err.println(e);
        }
        return club;
    }


/*
    public void transferEther() throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentialsWallet1 = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account

        Transfer.sendFunds(web3, credentialsWallet1, TARGET_ACCOUNT, BigDecimal.valueOf(AMOUNT), Convert.Unit.ETHER).sendAsync().get();
    }*/
}