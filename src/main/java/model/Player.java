package model;

import back.AskingMercato;
import back.Variables;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import smartContracts.Mercato.Mercato;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Player {

    public String name;
    public String addressShort;
    public String addressLong;
    private String password;
    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

    public Player(String name , String addressShort, String addressLong,
                  String password) {
        this.name = name;
        this.addressShort = addressShort;
        this.addressLong = addressLong;
        this.password = password;

    }

   /*
   public Club getCurrentClub(){
        return currentClubContract.club;
    }
*/

    public String getNameClub(){

        String clubName ="";
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(password, addressLong);
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try {
            Club club = Variables.listClubs.get(mercato.getPlayerCurrentClub().send());
            clubName = club.name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubName;
    }
    public String getAddressClub(){

        String clubAddress = "";
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(password, addressLong);
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try {
            Club club = Variables.listClubs.get(mercato.getPlayerCurrentClub().send());
            clubAddress = club.addressShort;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubAddress;
    }

    public void registerToClub(Club club, BigInteger duration , String price) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {


        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        BigInteger amount = Convert.toWei(price, Convert.Unit.ETHER).toBigInteger();
        try{
            mercato.registerPlayer(club.addressShort, duration , amount).send();
            System.out.println(this.name + " Registered to : " + club.name);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public Object [][] getListOfferPlayer () throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        Object [][] listOfferPlayer = new Object [0][0];
        try{
            int nbOffers = mercato.getTransferProposalNumberPlayer().send().intValue();
            System.out.println( "nb d'offre : " + nbOffers);
            Tuple3 offer ;
            listOfferPlayer = new Object [nbOffers][3];
            for (int i = 0; i < nbOffers; i++){
                offer = mercato.getTransferForPlayerAt(BigInteger.valueOf(i)).send();
                listOfferPlayer[i][0] =  offer.getValue1();
                listOfferPlayer[i][1] =  offer.getValue2();
                listOfferPlayer[i][2] =  offer.getValue3();

            }
        } catch (Exception e){
            System.err.println(e);
        }

        return listOfferPlayer;
    }


    public Object [][] getOffersAgent() throws IOException, CipherException {
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        Object [][] listAgent = new Object [0][0];

        try{
            int nbOffers = mercato.getAgentPlayerNumber().send().intValue();
            System.out.println( "nb d'offre : " + nbOffers);
            Tuple2 offer ;
            listAgent = new Object [nbOffers][3];
            for (int i = 0; i < nbOffers; i++){
                offer = mercato.getAgentPlayerAt(BigInteger.valueOf(i)).send();
                listAgent[i][0] =  offer.getValue1();
                listAgent[i][1] =  offer.getValue2();

            }
        } catch (Exception e){
            System.err.println(e);
        }

        return listAgent;
    }


    public void acceptOfferClub( Club club)throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
            mercato.acceptTransferProposalPlayer(club.addressShort).send();
            System.out.println(name + " accepted transfer offer from: " + club.name);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public void refuseOfferClub(Club club )throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
   //         mercato.refuseTransferProposalPlayer(/*     club.addressShort    */).send();
            System.out.println(name + " refused transfer offer from: " + club.name);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public void acceptOfferAgent( Agent agent) throws IOException, CipherException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
         //   mercato.acceptService().send();
            System.out.println(name + " accepted an agent offer from: " + agent.name);
        }
        catch (Exception e){
            System.err.println(e);
        }

    }

    public void refuseOfferAgent( Agent agent) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
          //  mercato.refuseService().send();
            System.out.println(name + " refused an agent offer from: " + agent.name);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }


}
