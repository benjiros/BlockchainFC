package back;

import back.AskingMercato;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class InterfaceConsole {

    AskingMercato mercato = new AskingMercato();
    Scanner sc = new Scanner(System.in);
    public void start() throws InterruptedException, TransactionException, CipherException, IOException, ExecutionException {
        mercato.initiate();


        String actor = "";
        while (!actor.equals("0")) {
            System.out.println("Quel votre adresse complète sur Blockchain FC ?");
            String id  = sc.nextLine();

            System.out.println("Quel votre mot de passe correspondant ?");
            String mdp = sc.nextLine();

            System.out.println("Quel type d'acteur êtes vous?");
            System.out.println("1 - Joueur");
            System.out.println("2 - Agent");
            System.out.println("3 - Club");
            System.out.println("0 - Quitter");
            System.out.println("(Tapez le chiffre correspondant)");
            actor = sc.nextLine();
            switch (actor) {
                case "1":
                    System.out.println("Vous êtes un joueur");
                    playerSelection(id,mdp);
                    break;
                case "2":
                    System.out.println("vous êtes un agent");
                    break;
                case "3":
                    System.out.println("vous êtes un club");
                    clubSelection(id,mdp);
                    break;
                default:
                    System.out.println();
                    System.out.println("Erreur de commande");
                    System.out.println();
                    break;
            }
        }

    }
    public void playerSelection(String id, String mdp) throws InterruptedException, TransactionException, CipherException, IOException, ExecutionException {

        System.out.println("1 - Liste des offres de club parvenues");
        System.out.println("2 - S'enregistrer à un club (si vous n'avez pas de club)");
        System.out.println("3 - Liste des offres d'agent");
        System.out.println("(Tapez le chiffre correspondant)");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                mercato.getListOfferPlayer(id,mdp);
                break;
            case "2":
                System.out.println("Quel est l'adresse du club?");
                String club = sc.nextLine();
                System.out.println("Quel est la durée du contrat?");
                BigInteger duration = BigInteger.valueOf(sc.nextInt());
                System.out.println("Quel est le prix d'achat?");
                BigInteger price = BigInteger.valueOf(sc.nextInt());
                mercato.registerPlayerToClub(club, id, mdp,  duration , price );
                break;
            case "3":

                break;
            default:
                System.out.println();
                System.out.println("Erreur de commande");
                System.out.println();
                break;
        }
    }


    public void clubSelection(String id, String mdp) throws InterruptedException, TransactionException, CipherException, IOException, ExecutionException {

        System.out.println("1 - Faire une offre");
        System.out.println("2 - Nombre de joueurs dans le club");
        System.out.println("3 - Liste des offres reçues");
        System.out.println("4 - Voir la première offre");
        System.out.println("(Tapez le chiffre correspondant)");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Quel est l'adresse du joueur?");
                String player = sc.nextLine();
                System.out.println("Quel est la durée du contrat?");
                BigInteger duration = BigInteger.valueOf(sc.nextInt());
                System.out.println("Quel est le montant d'achat?");
                BigInteger price = BigInteger.valueOf(sc.nextInt());
                mercato.transferProposal(id, mdp,player, duration,price);
                break;
            case "2":
                mercato.getNumberOfPlayerInClub(id,mdp);
                break;
            case "3":

                break;
            case "4":

                break;
            default:
                System.out.println();
                System.out.println("Erreur de commande");
                System.out.println();
                break;
        }


    }
}
