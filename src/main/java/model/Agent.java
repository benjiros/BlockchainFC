package model;

import back.Variables;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import smartContracts.Mercato.Mercato;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Agent {


    public String name;
    public String addressShort;
    public String addressLong;
    private String password;

    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

    public Agent(String name, String addressShort, String addressLong,
                String password) {

        this.name = name;
        this.addressShort = addressShort;
        this.addressLong = addressLong;
        this.password = password;

    }

    public void proposeServiceToPlayer(Player player, BigInteger percentage )throws IOException, CipherException, TransactionException, InterruptedException, ExecutionException {

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(password, addressLong);
        Mercato mercato = Mercato.load(Variables.MERCATO_ADDRESS, web3, credentials, BigInteger.valueOf(0), DefaultGasProvider.GAS_LIMIT);

        try{
            mercato.proposeServiceToPlayer(player.addressShort, percentage).send();
            System.out.println(name + " send an agent offer to " + player.name);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public void getListPLayers(){}


}
