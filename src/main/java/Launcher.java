import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Demo program to tranfer a fix amount of ether from one account to another using geth via web3j access.
 * Geth must powered up and mining, first:
 geth --datadir="elephantchain" --etherbase '0x5edd062cc2ff7d260be8b93d0a0ac92be4e79da3'  --rpcapi personal,db,eth,net,web3 --rpc --nodiscover --mine --minerthreads=4 console
 * [*] runs the silent miner for an uninvolved third account.
 * [*] operates on the elephant chain
 */
public class Launcher {


    public static void main(String[] args) throws InterruptedException, TransactionException, CipherException, IOException, ExecutionException {

        String ClubA_longName = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-52-40.358632659Z--30c45fea0f173249978e90aa3d76add6222679bf";
        String ClubA = "0x30c45fea0f173249978e90aa3d76add6222679bf" ;
        String ClubA_Password = "liverpool" ;


        String ClubB_longName = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-52-31.168522329Z--616ac3f434f8aae021db944d350bc17ec1b1f59e" ;
        String ClubB = "616ac3f434f8aae021db944d350bc17ec1b1f59e" ;
        String ClubB_Password = "arsenal" ;

        String Player1_longName = "/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-51-03.916128461Z--970f192e8491c839ba957d7dcb286f1d3fb1ccc4";
        String Player1 = "0x970f192e8491c839ba957d7dcb286f1d3fb1ccc4" ;
        String Player1_Password = "" ;

        Controller controller = new Controller();

        controller.initiate();


    //    controller.registerPlayer("/Users/benjiros/Library/Ethereum/Blockchain FC/keystore/UTC--2018-10-24T14-51-03.916128461Z--970f192e8491c839ba957d7dcb286f1d3fb1ccc4","");
        BigInteger duration = BigInteger.valueOf(1);
        BigInteger price = BigInteger.valueOf(2);
        controller.registerPlayerToClub( ClubA, Player1_longName ,Player1_Password,  duration , price );



        controller.employedOrNot(Player1_longName,Player1_Password);


        controller.getNumberOfPlayerInClub(ClubA_longName, ClubA_Password);

        controller.transferProposal(ClubB_longName, ClubB_Password,Player1, 1);


        controller.getTransferPlayerProposalNumber(Player1_longName,Player1_Password);


    }
}
