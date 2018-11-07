package smartContracts.Mercato;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
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
 * <p>Generated with web3j version 3.6.0.
 */
public class Mercato extends Contract {
    private static final String BINARY = "608060405260008054600160a060020a03191633179055611c17806100256000396000f3006080604052600436106101325763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663094490d981146101375780630c3025cd1461015e578063181b92cd14610184578063204bc12f146101995780632a355fa8146101ae5780633b0266d9146101c35780633d48b1e51461020257806341c0e1b5146102315780634a30b41f146102465780635103281a1461025b5780635c07a4b0146102895780637c8b99961461029e5780637db6b867146102b357806387c99823146102d45780639c8497f1146102fd5780639ffa9d0814610324578063a614489414610345578063b1b42f4714610392578063c97f504b146103ca578063e0caf616146103f1578063e3c13f1014610431578063f2135c5014610449578063fe13d78d14610461575b600080fd5b34801561014357600080fd5b5061014c610492565b60408051918252519081900360200190f35b34801561016a57600080fd5b50610182600160a060020a03600435166024356104a6565b005b34801561019057600080fd5b5061014c610529565b3480156101a557600080fd5b5061014c610540565b3480156101ba57600080fd5b50610182610553565b3480156101cf57600080fd5b506101d861067a565b60408051600160a060020a0394851681529290931660208301528183015290519081900360600190f35b610216600160a060020a036004351661079d565b60408051921515835290151560208301528051918290030190f35b34801561023d57600080fd5b50610182610a14565b34801561025257600080fd5b5061014c610a37565b34801561026757600080fd5b50610270610a4a565b6040805163ffffffff9092168252519081900360200190f35b34801561029557600080fd5b50610182610aed565b3480156102aa57600080fd5b50610182610b06565b3480156102bf57600080fd5b50610216600160a060020a0360043516610b27565b3480156102e057600080fd5b506102e9610d0f565b604080519115158252519081900360200190f35b34801561030957600080fd5b506102e9600160a060020a0360043581169060243516610d25565b34801561033057600080fd5b50610216600160a060020a0360043516610f07565b34801561035157600080fd5b5061036c600160a060020a0360043516602435604435610fbd565b60408051600160a060020a03938416815291909216602082015281519081900390910190f35b34801561039e57600080fd5b506103a76110b3565b60408051600160a060020a03909316835260208301919091528051918290030190f35b3480156103d657600080fd5b506101d8600160a060020a03600435166024356044356110fe565b3480156103fd57600080fd5b506104096004356114c9565b60408051600160a060020a039094168452602084019290925282820152519081900360600190f35b34801561043d57600080fd5b506103a7600435611522565b34801561045557600080fd5b5061040960043561156e565b34801561046d57600080fd5b506104766115c7565b60408051600160a060020a039092168252519081900360200190f35b336000908152600260205260409020545b90565b6104ae6119c3565b5060408051606081018252338152600160a060020a0393841660208083018281528385019586526000928352600a825293822080546001818101835591845291909220925160039091029092018054928616600160a060020a0319938416178155925190830180549190951691161790925551600290910155565b3360009081526009602052604090208054905b5090565b3360009081526007602052604090205490565b33600090815260076020526040902080548190600019810190811061057457fe5b906000526020600020906005020181600081548110151561059157fe5b6000918252602090912082546005909202018054600160a060020a03928316600160a060020a03199182161782556001808501549083018054918516918316919091179055600280850180549184018054929095169190921617808455815474ff00000000000000000000000000000000000000001990911660a060020a9182900460ff908116151590920217808555915475ff0000000000000000000000000000000000000000001990921660a860020a928390049091161515909102179091556003808301549082015560049182015491015580546106768260001983016119e3565b5050565b6000806000806106886119c3565b336000908152600a60205260408120805490919081106106a457fe5b60009182526020808320604080516060810182523380825260039586029093018054600160a060020a03908116838701908152600280840154858701908152878b526001808a52878c2087518154908716600160a060020a031991821617825585518284018054918916918316919091179055835191850191909155865486168d52838b52888d2080548084018255908e528b8e20895191909d02909c0180549c87169c82169c909c178c559351908b0180549190951693169290921790925551960195909555918552600a909252908320919450925061078491611a14565b8051602082015160409092015190969195509350915050565b600160a060020a0381166000908152600960205260408120819081805b825463ffffffff831610156108325782543390849063ffffffff85169081106107df57fe5b6000918252602090912060016005909202010154600160a060020a0316141561082757828263ffffffff1681548110151561081657fe5b906000526020600020906005020190505b6001909101906107ba565b600160a060020a03861660009081526003602052604090205460ff161561095057600281015460a860020a900460ff1680156108795750600281015460a060020a900460ff165b1561092e57600481015434101561088f57600080fd5b6040805160e0810182528254600160a060020a03908116825260018401548116602083015260028401549081169282019290925260ff60a060020a830481161515606083015260a860020a90920490911615156080820152600382015460a0820152600482015460c0820152610904906115e3565b506002810154815461092291600160a060020a0390811691166116e1565b60018094509450610a0c565b600281015460ff60a860020a82048116965060a060020a909104169350610a0c565b600281015460a060020a900460ff1615610a0457600481015434101561097557600080fd5b6040805160e0810182528254600160a060020a03908116825260018401548116602083015260028401549081169282019290925260ff60a060020a830481161515606083015260a860020a90920490911615156080820152600382015460a0820152600482015460c08201526109ea906115e3565b506002810154610a0490600160a060020a031660006116e1565b600180945094505b505050915091565b600054600160a060020a0316331415610a3557600054600160a060020a0316ff5b565b3360009081526006602052604090205490565b336000908152600a60205260408120805481906000198101908110610a6b57fe5b9060005260206000209060030201816000815481101515610a8857fe5b6000918252602090912082546003909202018054600160a060020a03928316600160a060020a03199182161782556001808501549083018054919094169116179091556002918201549101558054610ae4826000198301611a38565b50600091505090565b336000908152600360205260409020805460ff19169055565b33600090815260096020526040902080548190600019810190811061057457fe5b3360009081526009602052604081208190818080805b845463ffffffff85161015610bfb5787600160a060020a0316858563ffffffff16815481101515610b6a57fe5b6000918252602090912060016005909202010154600160a060020a03161415610bf057848463ffffffff16815481101515610ba157fe5b906000526020600020906005020192506001858563ffffffff16815481101515610bc757fe5b906000526020600020906005020160020160146101000a81548160ff0219169083151502179055505b600190930192610b3d565b50508054600160a060020a03166000908152600760205260408120905b815463ffffffff82161015610ceb5781543390839063ffffffff8416908110610c3d57fe5b6000918252602090912060026005909202010154600160a060020a0316148015610c9f575087600160a060020a0316828263ffffffff16815481101515610c8057fe5b6000918252602090912060016005909202010154600160a060020a0316145b15610ce3576001828263ffffffff16815481101515610cba57fe5b906000526020600020906005020160020160146101000a81548160ff0219169083151502179055505b600101610c18565b50506002015460ff60a860020a820481169760a060020a9092041695509350505050565b3360009081526003602052604090205460ff1690565b336000908152600760205260408120818080805b845463ffffffff85161015610e3b5787600160a060020a0316858563ffffffff16815481101515610d6657fe5b6000918252602090912060026005909202010154600160a060020a0316148015610dc8575086600160a060020a0316858563ffffffff16815481101515610da957fe5b6000918252602090912060016005909202010154600160a060020a0316145b15610e3057848463ffffffff16815481101515610de157fe5b906000526020600020906005020192506001858563ffffffff16815481101515610e0757fe5b906000526020600020906005020160020160156101000a81548160ff0219169083151502179055505b600190930192610d39565b5050600160a060020a0386166000908152600960205260408120905b815463ffffffff82161015610eee5786600160a060020a0316828263ffffffff16815481101515610e8457fe5b6000918252602090912060016005909202010154600160a060020a03161415610ee6576001828263ffffffff16815481101515610ebd57fe5b906000526020600020906005020160020160156101000a81548160ff0219169083151502179055505b600101610e57565b50506002015460a860020a900460ff1695945050505050565b336000908152600760205260408120819081805b825463ffffffff83161015610f9b5785600160a060020a0316838363ffffffff16815481101515610f4857fe5b6000918252602090912060026005909202010154600160a060020a03161415610f9057828263ffffffff16815481101515610f7f57fe5b906000526020600020906005020190505b600190910190610f1b565b6002015460ff60a860020a820481169760a060020a9092041695509350505050565b600080610fc8611a64565b5050336000818152600360208181526040808420805460ff191660019081179091558151608081018352600160a060020a038b81168083528286018981528386019c8d52606084019b8c528989526005875285892084518154908516600160a060020a0319918216178255825182880180549187169183169190911790558e516002808401919091558e51928b0192909255928a526006885295892080548087018255908a5296909820925160049096029092018054958216958316959095178555955191840180549290961691161790935595519186019190915592519390920192909255929050565b336000908152600a60205260408120805482918291829081106110d257fe5b600091825260209091206003909102018054600290910154600160a060020a0390911694909350915050565b600080600061110b611a8b565b600160a060020a03871660009081526003602052604081205481908190819060ff161561118f57600160a060020a03808c166000818152600560209081526040808320815160e08101835281549096168652339286019290925284019290925260608301819052608083015260a082018c905260c082018b905290955093506111d5565b6040805160e0810182526000808252336020830152600160a060020a038e169282019290925260608101829052608081019190915260a081018b905260c081018a905294505b505050600160a060020a038816600090815260096020526040812090805b825463ffffffff82161015611254578460200151600160a060020a0316838263ffffffff1681548110151561122457fe5b6000918252602090912060016005909202010154600160a060020a0316141561124c57600191505b6001016111f3565b8115156114a657600960008c600160a060020a0316600160a060020a03168152602001908152602001600020859080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a815481600160a060020a030219169083600160a060020a0316021790555060208201518160010160006101000a815481600160a060020a030219169083600160a060020a0316021790555060408201518160020160006101000a815481600160a060020a030219169083600160a060020a0316021790555060608201518160020160146101000a81548160ff02191690831515021790555060808201518160020160156101000a81548160ff02191690831515021790555060a0820151816003015560c08201518160040155505050600360008c600160a060020a0316600160a060020a0316815260200190815260200160002060009054906101000a900460ff16156114a6578354600160a060020a039081166000908152600760209081526040808320805460018181018355918552938390208a516005909502018054948616600160a060020a0319958616178155928a0151908301805491861691851691909117905588015160028201805460608b015160808c0151151560a860020a0275ff0000000000000000000000000000000000000000001991151560a060020a0274ff0000000000000000000000000000000000000000199590981692909616919091179290921694909417169190911790915560a0860151600382015560c08601516004909101555b846040015185602001518660a00151975097509750505050505093509350939050565b336000908152600960205260408120805482918291829190869081106114eb57fe5b6000918252602090912060059091020160018101546003820154600490920154600160a060020a0390911697919650945092505050565b3360009081526002602052604081208054829182918590811061154157fe5b600091825260209091206003909102018054600290910154600160a060020a039091169590945092505050565b3360009081526006602052604081208054829182918291908690811061159057fe5b6000918252602090912060049091020160018101546002820154600390920154600160a060020a0390911697919650945092505050565b33600090815260056020526040902054600160a060020a031690565b6115eb611a64565b6115f3611a64565b50506040805160808101825260208084018051600160a060020a0390811684528585018051821684860190815260a088015186880190815260c0890151606088019081529251841660009081526005875288812088518154908716600160a060020a0319918216178255845160018084018054928a16928416929092179091558451600280850191909155875160039485015598518816845260068a529a83208054808d018255908452989092208951600490990201805498871698831698909817885592519887018054999095169816979097179092559451918301919091559251920191909155919050565b600160a060020a03821660009081526009602052604081208190819081906117099082611ac7565b600160a060020a0385166000908152600760205260408120945092505b835463ffffffff8416101561189c5785600160a060020a0316848463ffffffff1681548110151561175357fe5b6000918252602090912060026005909202010154600160a060020a031614156118915783548490600019810190811061178857fe5b9060005260206000209060050201848463ffffffff168154811015156117aa57fe5b6000918252602090912082546005909202018054600160a060020a03928316600160a060020a03199182161782556001808501549083018054918516918316919091179055600280850180549184018054929095169190921617808455815474ff00000000000000000000000000000000000000001990911660a060020a9182900460ff908116151590920217808555915475ff0000000000000000000000000000000000000000001990921660a860020a9283900490911615159091021790915560038083015490820155600491820154910155835461188f8560001983016119e3565b505b600190920191611726565b600160a060020a038516156119bb575050600160a060020a0383166000908152600660205260408120905b815463ffffffff821610156119bb5785600160a060020a0316828263ffffffff168154811015156118f457fe5b6000918252602090912060016004909202010154600160a060020a031614156119b35781548290600019810190811061192957fe5b9060005260206000209060040201828263ffffffff1681548110151561194b57fe5b6000918252602090912082546004909202018054600160a060020a03928316600160a060020a03199182161782556001808501549083018054919094169116179091556002808301549082015560039182015491015581546119b1836000198301611ae8565b505b6001016118c7565b505050505050565b604080516060810182526000808252602082018190529181019190915290565b815481835581811115611a0f57600502816005028360005260206000209182019101611a0f9190611b14565b505050565b5080546000825560030290600052602060002090810190611a359190611b74565b50565b815481835581811115611a0f57600302816003028360005260206000209182019101611a0f9190611b74565b60408051608081018252600080825260208201819052918101829052606081019190915290565b6040805160e081018252600080825260208201819052918101829052606081018290526080810182905260a0810182905260c081019190915290565b5080546000825560050290600052602060002090810190611a359190611b14565b815481835581811115611a0f57600402816004028360005260206000209182019101611a0f9190611bac565b6104a391905b8082111561053c578054600160a060020a03199081168255600182018054909116905560028101805475ffffffffffffffffffffffffffffffffffffffffffff191690556000600382018190556004820155600501611b1a565b6104a391905b8082111561053c578054600160a060020a03199081168255600182018054909116905560006002820155600301611b7a565b6104a391905b8082111561053c578054600160a060020a0319908116825560018201805490911690556000600282018190556003820155600401611bb25600a165627a7a723058200b177b6e25d6171812acd2362ed5dbe05d664564f61d8808b21c4eac8b8b365d0029";

    public static final String FUNC_GETAGENTPLAYERNUMBER = "getAgentPlayerNumber";

    public static final String FUNC_PROPOSESERVICETOPLAYER = "proposeServiceToPlayer";

    public static final String FUNC_GETTRANSFERPROPOSALNUMBERPLAYER = "getTransferProposalNumberPlayer";

    public static final String FUNC_GETTRANSFERPROPOSALNUMBERCLUB = "getTransferProposalNumberClub";

    public static final String FUNC_REFUSETRANSFERPROPOSALCLUB = "refuseTransferProposalClub";

    public static final String FUNC_ACCEPTSERVICE = "acceptService";

    public static final String FUNC_FINALIZETRANSFER = "finalizeTransfer";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_GETCLUBNUMBEROFPLAYERS = "getClubNumberOfPlayers";

    public static final String FUNC_REFUSESERVICE = "refuseService";

    public static final String FUNC_REGISTERPLAYER = "registerPlayer";

    public static final String FUNC_REFUSETRANSFERPROPOSALPLAYER = "refuseTransferProposalPlayer";

    public static final String FUNC_ACCEPTTRANSFERPROPOSALPLAYER = "acceptTransferProposalPlayer";

    public static final String FUNC_GETPLAYEREMPLOYED = "getPlayerEmployed";

    public static final String FUNC_ACCEPTTRANSFERPROPOSALCLUB = "acceptTransferProposalClub";

    public static final String FUNC_ISOFFERACCEPTED = "isOfferAccepted";

    public static final String FUNC_GETSERVICEPROPOSAL = "getServiceProposal";

    public static final String FUNC_PROPOSETRANSFERTOPLAYER = "proposeTransferToPlayer";

    public static final String FUNC_GETTRANSFERFORPLAYERAT = "getTransferForPlayerAt";

    public static final String FUNC_GETAGENTPLAYERAT = "getAgentPlayerAt";

    public static final String FUNC_GETCLUBPLAYERAT = "getClubPlayerAt";

    public static final String FUNC_GETPLAYERCURRENTCLUB = "getPlayerCurrentClub";

    @Deprecated
    protected Mercato(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
/*
    protected Mercato(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }
*/
    @Deprecated
    protected Mercato(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Mercato(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getAgentPlayerNumber() {
        final Function function = new Function(FUNC_GETAGENTPLAYERNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> proposeServiceToPlayer(String player, BigInteger percentage) {
        final Function function = new Function(
                FUNC_PROPOSESERVICETOPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(player), 
                new org.web3j.abi.datatypes.generated.Uint256(percentage)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getTransferProposalNumberPlayer() {
        final Function function = new Function(FUNC_GETTRANSFERPROPOSALNUMBERPLAYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getTransferProposalNumberClub() {
        final Function function = new Function(FUNC_GETTRANSFERPROPOSALNUMBERCLUB, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> refuseTransferProposalClub() {
        final Function function = new Function(
                FUNC_REFUSETRANSFERPROPOSALCLUB, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> acceptService() {
        final Function function = new Function(
                FUNC_ACCEPTSERVICE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> finalizeTransfer(String player, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_FINALIZETRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(player)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getClubNumberOfPlayers() {
        final Function function = new Function(FUNC_GETCLUBNUMBEROFPLAYERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> refuseService() {
        final Function function = new Function(
                FUNC_REFUSESERVICE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> registerPlayer() {
        final Function function = new Function(
                FUNC_REGISTERPLAYER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> refuseTransferProposalPlayer() {
        final Function function = new Function(
                FUNC_REFUSETRANSFERPROPOSALPLAYER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> acceptTransferProposalPlayer(String club) {
        final Function function = new Function(
                FUNC_ACCEPTTRANSFERPROPOSALPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(club)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> getPlayerEmployed() {
        final Function function = new Function(FUNC_GETPLAYEREMPLOYED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> acceptTransferProposalClub(String player, String club) {
        final Function function = new Function(
                FUNC_ACCEPTTRANSFERPROPOSALCLUB, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(player), 
                new org.web3j.abi.datatypes.Address(club)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> isOfferAccepted(String player) {
        final Function function = new Function(
                FUNC_ISOFFERACCEPTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(player)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> registerPlayer(String club, BigInteger duration, BigInteger price) {
        final Function function = new Function(
                FUNC_REGISTERPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(club), 
                new org.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getServiceProposal() {
        final Function function = new Function(FUNC_GETSERVICEPROPOSAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> proposeTransferToPlayer(String player, BigInteger duration, BigInteger price) {
        final Function function = new Function(
                FUNC_PROPOSETRANSFERTOPLAYER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(player), 
                new org.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> getTransferForPlayerAt(BigInteger i) {
        final Function function = new Function(FUNC_GETTRANSFERFORPLAYERAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple2<String, BigInteger>> getAgentPlayerAt(BigInteger i) {
        final Function function = new Function(FUNC_GETAGENTPLAYERAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> getClubPlayerAt(BigInteger i) {
        final Function function = new Function(FUNC_GETCLUBPLAYERAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<String> getPlayerCurrentClub() {
        final Function function = new Function(FUNC_GETPLAYERCURRENTCLUB, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
/*
    public static RemoteCall<Mercato> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Mercato.class, web3j, credentials, contractGasProvider, BINARY, "");
    }
*/
    @Deprecated
    public static RemoteCall<Mercato> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Mercato.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }
/*
    public static RemoteCall<Mercato> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Mercato.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }
*/
    @Deprecated
    public static RemoteCall<Mercato> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Mercato.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static Mercato load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mercato(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Mercato load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Mercato(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
/*
    public static Mercato load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Mercato(contractAddress, web3j, credentials, contractGasProvider);
    }
*/
    public static Mercato load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Mercato(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
