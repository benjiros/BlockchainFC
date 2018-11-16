package model;

import back.Variables;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import smartContracts.Mercato.Mercato;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Club {

    public String name;
    public String addressShort;
    public String addressLong;
    private String password;
    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

    public Club(String name, String addressShort, String addressLong,
                  String password) {

        this.name = name;
        this.addressShort = addressShort;
        this.addressLong = addressLong;
        this.password = password;

    }

    public void transferProposal( Player player, BigInteger duration, String price) throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        BigDecimal amount = new BigDecimal(price);

        BigInteger amountWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();
        try{
            mercato.proposeTransferToPlayer(player.addressShort, duration, amountWei).send();
            System.out.println(this.name + " did a transfer offer for " + player.name);
        } catch (Exception e){

            System.err.println(e);
        }
    }

    public void swapProposal(Player myPlayer, Player wantedPlayer, BigInteger duration1,  BigInteger duration2 ) throws IOException, CipherException {
        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);
        try{
            mercato.proposeSwap(myPlayer.addressShort, wantedPlayer.addressShort, duration1, duration2).send();
            System.out.println(this.name + " did a swap offer to exchange " + myPlayer.name + " with " + wantedPlayer.name);
        } catch (Exception e){
            System.err.println(e);
        }
    }

    public BigDecimal getBalance() throws ExecutionException, InterruptedException {

        EthGetBalance ethgGetBalance = web3.ethGetBalance(this.addressShort, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigDecimal balance = Convert.fromWei(ethgGetBalance.getBalance().toString(),  Convert.Unit.ETHER);

        return balance;
    }


    public Object [][] getPlayerOfClub () throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        Object [][] listPlayerClub = new Object [0][0];
        try{
            int nbPlayers = mercato.getClubNumberOfPlayers().send().intValue();
            System.out.println( "nb de joueurs dans club: " + nbPlayers);
            Tuple3 player ;
            listPlayerClub = new Object [nbPlayers][4];
            for (int i = 0; i < nbPlayers; i++){
                player = mercato.getClubPlayerAt(BigInteger.valueOf(i)).send();
                listPlayerClub[i][0] =  player.getValue1();
                listPlayerClub[i][1] =  Variables.listPlayers.get(player.getValue1()).name;
                listPlayerClub[i][2] =  player.getValue2();
                listPlayerClub[i][3] =  Convert.fromWei(player.getValue3().toString(),  Convert.Unit.ETHER);
            }
        } catch (Exception e){
            System.err.println(e);
        }
        return listPlayerClub;
    }


    public Object [][] getPlayersOnMarket () {

        Object [] [] players = new Object[Variables.listPlayers.size()][2];

        int j=0;
        for(String key : Variables.listPlayers.keySet()){
            if (Variables.listPlayers.get(key).getAddressClub() != addressShort){
                System.out.println(Variables.listPlayers.get(key).name);
                players[j][0] = Variables.listPlayers.get(key).name;
                players[j][1] = Variables.listPlayers.get(key).getNameClub();
                j++;
            }
        }

        Object [][] playersOnMarket = new Object[j][players[0].length];
        for (int i = 0; i < playersOnMarket.length; i++)
            playersOnMarket[i] = Arrays.copyOf(players[i], players[i].length);

        return playersOnMarket;
    }

    public  Object [][] listOfferReceived () throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        Object [][] listOfferPlayer = new Object [0][0];

        try{
            int nbOffers = mercato.getTransferProposalNumberClub().send().intValue();
            System.out.println( "nb de joueurs : " + nbOffers);
            Tuple6 <String, String, BigInteger, BigInteger, Boolean, Boolean> offer ;
            listOfferPlayer = new Object [nbOffers][8];
            for (int i = 0; i < nbOffers; i++){
                offer = mercato.getOfferReceivedByClubAt(BigInteger.valueOf(i)).send();

                listOfferPlayer[i][0] =  offer.getValue1();
                listOfferPlayer[i][1] =  offer.getValue2();
                listOfferPlayer[i][2] =  Variables.listPlayers.get(offer.getValue1()).name;
                listOfferPlayer[i][3] =  Variables.listClubs.get(offer.getValue2()).name;
                listOfferPlayer[i][4] =  offer.getValue3();
                listOfferPlayer[i][5] =  Convert.fromWei(offer.getValue4().toString(),  Convert.Unit.ETHER);
                listOfferPlayer[i][6] =  offer.getValue5();
                listOfferPlayer[i][7] =  offer.getValue6();


/*
                System.out.println( "Offer for (player address) : "  + offer.getValue1() +
                                    " From (club address) : "  + offer.getValue2() +
                                    " duration :  "      + offer.getValue3() +
                                    " price :  "         + offer.getValue4() +
                                    " player accepted : "         + offer.getValue5() +
                                    " club accepted :  "         + offer.getValue6()
                                    );
*/
            }
        } catch (Exception e){
            System.err.println(e);
        }
        return listOfferPlayer;
    }

    public  Object [][] listSwapReceived () throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        Object [][] listSwapReceived = new Object [0][0];

        try{
            int nbOffers = mercato.getSwapProposalNumberForClub().send().intValue();
            System.out.println( "nb de joueurs : " + nbOffers);
            Tuple5 offer ;
            listSwapReceived = new Object [nbOffers][7];
            for (int i = 0; i < nbOffers; i++){
                offer = mercato.getCurrentSwapProposalForClubAt(BigInteger.valueOf(i)).send();

                listSwapReceived[i][0] =  offer.getValue1();
                listSwapReceived[i][1] =  offer.getValue2();
                listSwapReceived[i][2] =  Variables.listPlayers.get(offer.getValue1()).name;
                listSwapReceived[i][3] =  Variables.listPlayers.get(offer.getValue2()).name;
                listSwapReceived[i][4] =  Variables.listClubs.get(offer.getValue3()).name;
                listSwapReceived[i][5] =  offer.getValue4();
                listSwapReceived[i][6] =  offer.getValue5();

                System.out.println("SWAP : ");
                System.out.println(listSwapReceived[i][0]);
                System.out.println(listSwapReceived[i][1]);
                System.out.println(listSwapReceived[i][2]);
                System.out.println(listSwapReceived[i][3]);
                System.out.println(listSwapReceived[i][4]);
                System.out.println(listSwapReceived[i][5]);
                System.out.println(listSwapReceived[i][6]);

            }
        } catch (Exception e){
            System.err.println(e);
        }
        return listSwapReceived;
    }



    public void acceptTransferProposal( Player player, Club clubOffering)throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
            // System.out.println(mercato.proposeTransferToPlayer(player,BigInteger.valueOf(duration), BigInteger.valueOf(1)).send());
            mercato.acceptTransferProposalClub(player.addressShort, clubOffering.addressShort).send();
            System.out.println(this.name + " accepted the transfer of " + player.name + " to " + clubOffering.name);
        } catch (Exception e){

            System.err.println(e);
        }

    }

    public void refuseTransferProposal(Player player, Club clubOffering)throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
            mercato.refuseTransferProposalClub(/*     player.addressShort, clubOffering.addressShort  */).send();
            System.out.println(this.name + " refused the transfer of " + player.name + " to " + clubOffering.name);
        } catch (Exception e){

            System.err.println(e);
        }
    }

    public void acceptSwapProposal( Player myPlayer, Player player2)throws IOException, CipherException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
            mercato.acceptSwapProposal(myPlayer.addressShort, player2.addressShort).send();
            System.out.println(this.name + " accepted the swap of " + myPlayer.name + " with " + player2.name);
        } catch (Exception e){

            System.err.println(e);
        }
    }

    public void refuseSwapProposal(Player player, Player player2)throws IOException, CipherException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
            //mercato.refuse(/*     player.addressShort, clubOffering.addressShort  */).send();
            System.out.println(this.name + " refused the swap of " + player.name + " with " + player2.name);
        } catch (Exception e){

            System.err.println(e);
        }
    }


    public Object [][] finalizableOffers() throws Exception {
        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        Tuple4 <String, BigInteger, BigInteger, BigInteger> finalizable ;

        int i = 0;

        int length = mercato.getTransfertProposalReadyAt(BigInteger.valueOf(i)).send().getValue4().intValue();
        Object [] [] listFinalizable = new Object[length][4];

        for (i = 0; i < length; i++) {
            finalizable = mercato.getTransfertProposalReadyAt(BigInteger.valueOf(i)).send();
            listFinalizable[i][0] = finalizable.getValue1();
            listFinalizable[i][1] = Variables.listPlayers.get(finalizable.getValue1()).name;
            listFinalizable[i][2] = finalizable.getValue2();
            listFinalizable[i][3] = Convert.fromWei(finalizable.getValue3().toString(),  Convert.Unit.ETHER);

        }

        return listFinalizable;
    }

    public void finalizeTransfer(Player player, String amount)throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS,web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);


        BigInteger weiValue = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();

        try{
            mercato.finalizeTransfer(player.addressShort, weiValue).send();
            System.out.println(this.name + " finalized the transfer of " + player.name );
        } catch (Exception e){

            System.err.println(e);
        }
    }



/*
    public BigInteger getNumberOfPlayerInClub() throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException{

        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);

        Mercato mercato = Mercato.load(mercatoAddress,web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        BigInteger nbPlayer = null;
        try{
            nbPlayer = mercato.getClubNumberOfPlayers().send();
            System.out.println("Votre club a " + nbPlayer + " joueur(s)");
        } catch (Exception e){
            System.err.println(e);
        }
        return nbPlayer;
    }
    */

}
