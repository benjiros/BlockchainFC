package back;

import model.Agent;
import model.Club;
import model.Player;
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
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Variables {

    public static  HashMap<String, Player>  listPlayers = new HashMap<>();
    public static  HashMap<String, Club>    listClubs   = new HashMap<>();
    public static  HashMap<String, Agent>   listAgents  = new HashMap<>();

    public static String MERCATO_ADDRESS = "0x54270a29270ec909c9f6d0aa989c8ddc1085c7fd";



    public Variables() throws InterruptedException, ExecutionException, TransactionException, CipherException, IOException {
    }

    private static String ClubA_longName = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-52-40.358632659Z--30c45fea0f173249978e90aa3d76add6222679bf";
    private static String ClubA = "0x30c45fea0f173249978e90aa3d76add6222679bf" ;
    private static String ClubA_Password = "liverpool" ;
    private static Club liverpool  = new Club("Liverpool", ClubA, ClubA_longName, ClubA_Password);

    private static String ClubB_longName = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-52-31.168522329Z--616ac3f434f8aae021db944d350bc17ec1b1f59e" ;
    private static String ClubB = "0x616ac3f434f8aae021db944d350bc17ec1b1f59e" ;
    private static String ClubB_Password = "arsenal" ;
    private static Club arsenal    = new Club("Arsenal", ClubB, ClubB_longName, ClubB_Password);

    private static String Player1_longName = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-51-03.916128461Z--970f192e8491c839ba957d7dcb286f1d3fb1ccc4";
    private static String Player1 = "0x970f192e8491c839ba957d7dcb286f1d3fb1ccc4" ;
    private static String Player1_Password = "" ;
    private static Player salah = new Player("Mohammed Salah", Player1, Player1_longName, Player1_Password);

    private static String Player2_longName = "/Users/benjiros/Library/Ethereum/keystore/UTC--2018-11-15T09-26-51.250146984Z--b7a2fe7f88b6764c2a4fdf8b26a27434e29199a8";
    private static String Player2 = "0xb7a2fe7f88b6764c2a4fdf8b26a27434e29199a8" ;
    private static String Player2_Password = "lacazette" ;
    private static Player lacazette = new Player("Alexandre Lacazette", Player2, Player2_longName, Player2_Password);


    private static String Agent_longName = "/Users/benjiros/Library/Ethereum/keystore/UTC--2018-11-14T08-37-26.478677725Z--1aa5f4fd00ef4d9392289f474d1b48c13023ab44";
    private static String Agent = "0x1aa5f4fd00ef4d9392289f474d1b48c13023ab44" ;
    private static String Agent_Password = "mino" ;
    private static Agent raiola = new Agent("Mino Raiola", Agent, Agent_longName, Agent_Password);



    public static void initiate(){

        try {
            initiateMercato();
        } catch (IOException | CipherException | TransactionException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        initiateClubs();
        try {
            initiatePlayers();
        } catch (InterruptedException | ExecutionException | CipherException | TransactionException | IOException e) {
            e.printStackTrace();
        }
        initiateAgents();
/*
        try {
            sendFundsToClub();
        } catch (IOException | CipherException | TransactionException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
*/
    }

    private static void initiatePlayers() throws InterruptedException, ExecutionException, TransactionException, CipherException, IOException {
        listPlayers.put(salah.addressShort  , salah);
        listPlayers.put(lacazette.addressShort  , lacazette);
        salah.registerToClub(liverpool, BigInteger.valueOf(1),"33");
        lacazette.registerToClub(arsenal, BigInteger.valueOf(1),"30");
    }

    private static void initiateClubs() {
        listClubs.put(liverpool.addressShort,liverpool);
        listClubs.put(arsenal.addressShort  ,arsenal);
    }

    private static void initiateAgents() {
        listAgents.put(raiola.addressShort, raiola);
        listAgents.put(raiola.addressShort, raiola);
    }

    private static void initiateMercato()  throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        String Directory = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/";
        String LOCATION_SOURCE_ACCOUNT = Directory + "UTC--2018-10-26T15-12-10.854285357Z--a35f975da43a9f032e262ccd51a41923290649fe";
        // Password of the source account
        String SOURCE_ACCOUNT_PASSWORD = "coinbase";
        // Account to sent the ethers to
        String TARGET_ACCOUNT = "0x637106b8d0646fbaa924e5608e2bd68a95df121f";
        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());
        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account
        int AMOUNT_TO_OPEN_MERCATO = 1;
        Transfer.sendFunds(web3, credentials, TARGET_ACCOUNT, BigDecimal.valueOf(AMOUNT_TO_OPEN_MERCATO), Convert.Unit.ETHER).sendAsync().get();

        Mercato mercato;
        try {
            //     mercato = Mercato.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT).send();
            mercato = Mercato.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT).send();

            MERCATO_ADDRESS = mercato.getContractAddress();
            System.out.println("LE MERCATO EST OUVERT");
            System.out.println("mercato address : "+ MERCATO_ADDRESS);

        } catch (Exception e){
            System.err.println(e);
        }
    }


    private static void sendFundsToClub() throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {
        String Directory = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/";
        String LOCATION_SOURCE_ACCOUNT = Directory + "UTC--2018-10-26T15-12-10.854285357Z--a35f975da43a9f032e262ccd51a41923290649fe";
        // Password of the source account
        String SOURCE_ACCOUNT_PASSWORD = "coinbase";
        // Account to sent the ethers to
        String TARGET_ACCOUNT = "0x637106b8d0646fbaa924e5608e2bd68a95df121f";
        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());
        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Transfer specified amount of ether to target account
        int AMOUNT_CLUBS = 800;

        Transfer.sendFunds(web3, credentials, liverpool.addressShort, BigDecimal.valueOf(AMOUNT_CLUBS), Convert.Unit.ETHER).sendAsync().get();
        Transfer.sendFunds(web3, credentials, arsenal.addressShort, BigDecimal.valueOf(AMOUNT_CLUBS), Convert.Unit.ETHER).sendAsync().get();

    }
}
