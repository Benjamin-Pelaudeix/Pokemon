package tp5;
import java.util.Scanner;

public class ChasseAuxPokemon {

	
	public static void main(String[] args) {
		
		//instantiation
		Pokemon piplup = new Pokemon("Piplup", "Eau", 5, true);
		Pokemon rowlet = new Pokemon("Rowlet", "Plante", 10, false);
		Pokemon totodile = new Pokemon("Totodile", "Eau", 8, true);
		Nourriture tartiflette = new Nourriture(35, "Tartiflette", new String[]{"Dragon", "Feu", "Combat", "Normal", "Eau", "Electrique"}, 20);
		Nourriture ratatouille = new Nourriture(10, "Ratatouille", new String[]{"Plante", "Eau", "Vol", "Feu", "Normal", "Electrique"}, 50);
		Gourmandise barreChocolat = new Gourmandise(20, "Barre au chocolat", new String[]{"Eau", "Feu", "Vol"}, 10, 7);
		PotionMagique mojito = new PotionMagique("Mojito", 2);
		Joueur ben = new Joueur("Pelaudeix", "Benjamin", 19, new Pokemon[]{piplup, rowlet, totodile}, new Nourriture[]{null, null, null, null, null});
		Scanner scanner = new Scanner(System.in);
		
		//instanciation tableau
		Nourriture[] diversesNourritures = new Nourriture[] {tartiflette, ratatouille, barreChocolat, mojito};
		
		//code qui génère aléatoirement de la nourriture
		double alea;
		int nombreDEssais = Integer.valueOf(args[0]);
		String reponse = "";
		boolean generer;
		Nourriture[] nourritureGeneree = new Nourriture[diversesNourritures.length];
		
		while (!reponse.equals("stop")) {
			alea = Math.random()*100;
			for (int j = 0; j<diversesNourritures.length; j++) {
				if (alea < diversesNourritures[j].getFrequence()) {
					generer = true;
					nourritureGeneree[j] = diversesNourritures[j].genererMemeNourriture(generer);
					System.out.println("Vous avez trouvé un.e/du/de la " + nourritureGeneree[j]);
					System.out.println("Voulez-vous prendre cette nourriture ? (oui/non)");
					String reponseNourriture = scanner.next();
					if (reponseNourriture.equals("oui")) {
						ben.ajouterProvision(nourritureGeneree[j]); 
					}
				}
				else {
					generer = false;
				}
			}
			if (reponse.equals("1")) {
				for (int i = 0; i < ben.getPokemons().length; i++) {
					System.out.println("Index " + i + " : " + ben.getPokemons()[i].getNom());
				}
			}
			if (reponse.equals("2")) {
				System.out.println("Quel pokemon voulez-vous caresser ?");
				int reponseCaresse = scanner.nextInt();
				while (reponseCaresse < 0 || reponseCaresse > ben.getPokemons().length) {
					System.out.println("Attention ! Vous ne pouvez choisir que les pokemons entre les index 0 et " + ben.getPokemons().length + "...");
					reponseCaresse = scanner.nextInt();
				}
				System.out.println("Vous caressez " + ben.getPokemons()[reponseCaresse].getNom());
				ben.caresserPokemon(ben.getPokemons()[reponseCaresse]);
			}
			if (reponse.equals("3")) {
				for (int i = 0; i < ben.getProvisions().length; i++) {
					System.out.println("Index " + i + " : " + ben.getProvisions()[i].nom);
				}
			}
			if (reponse.equals("4")) {
				System.out.println("Quel pokemon voulez-vous nourrir ?");
				int reponsePokemon = scanner.nextInt();
				System.out.println("Que voulez-vous lui donner ?");
				int reponseNourriture = scanner.nextInt();
				ben.getPokemons()[reponsePokemon].manger(ben.getProvisions()[reponseNourriture]);
				System.out.println(ben.getPokemons()[reponsePokemon].getNom() + " a mangé " + ben.getProvisions()[reponseNourriture]
						.nom);
			}
			System.out.println("Voulez-vous continuer ?");
			reponse = scanner.next();
		}
		System.out.println("Arrêt du jeu !");
		scanner.close();
		
		
	}
	
	//TP5
	//Q1- La signature du constructeur pour un objet de type Scanner est : public Scanner(InputStream source)
	//Pour une entrée de type fichier : public Scanner(File source)
	//Q3- L'erreur de compilation retournée est : "Scanner cannot be resolved to a type".
	//Q5- Exercice sur l'utilisation du scanner
		//System.out.println("Êtes-vous étudiant de première année à l'IUT du Limousin ?");
		//String reponse = scanner.next();
		//System.out.println(reponse);
		//scanner.close();
	//Q5b- La méthode next() de la classe Scanner recherche et renvoie le "token" suivant du scanner.
	//Q5e- Lors d'une réponse à plusieurs mots, seul le premier mot est affiché avec la commande System.out.println.
}

