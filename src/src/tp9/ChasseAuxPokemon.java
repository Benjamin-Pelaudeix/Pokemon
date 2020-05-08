package tp9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChasseAuxPokemon {

	
	public static void main(String[] args) throws FileNotFoundException {

		//initialisation
		final HashMap<String, Attaque> mappeAttaque = new HashMap<String, Attaque>();
		mappeAttaque.put("tackle", new AttaqueTackle());
		mappeAttaque.put("morsure",new AttaqueMorsure());
		mappeAttaque.put("pistoleEau", new AttaquePistoleEau());
		mappeAttaque.put("enfer", new AttaqueEnfer());
		mappeAttaque.put("feinte",new AttaqueFeinte());
		mappeAttaque.put("tornadeFeuiles", new AttaqueTornadeFeuilles());
		mappeAttaque.put("coupTete", new AttaqueCoupDeTete());
		mappeAttaque.put("croquer", new AttaqueCroquer());
		mappeAttaque.put("bulle", new AttaqueBulle());

		ArrayList<Pokemon> pokemonsUtilises = new ArrayList<>();

		try {
			FileReader source = new FileReader("tp9/jeuPokemon.txt");
			Scanner s = new Scanner(source);
			while (s.hasNextLine()) {
				String nom = s.next();
				String type = s.next();
				int niveau = s.nextInt();
				boolean diurne = s.nextBoolean();
				int attaque = s.nextInt();
				int defense = s.nextInt();
				int attaqueSpeciale = s.nextInt();
				int defenseSpeciale = s.nextInt();
				Attaque[] attaques = new Attaque[4];
				for (int i = 0; i < 4; i++) {
					if (i < 4) {
						attaques[i] = mappeAttaque.get(s.next());
					}
				}
				pokemonsUtilises.add(new Pokemon(nom, type, niveau, diurne, attaque, defense, attaqueSpeciale, defenseSpeciale, attaques));
				s.nextLine();
			}
			s.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		System.out.println(pokemonsUtilises.get(0));
































		/*
		//instantiation
		Pokemon piplup = new Pokemon("Piplup", "Eau", 5, true, 51, 53, 61, 56, new Attaque[]{new AttaqueMorsure(), new AttaquePistoleEau(), null, null}); //erreur dans l'instanciation car nous avons changé les constructeurs de cette classe
		Pokemon rowlet = new Pokemon("Rowlet", "Plante", 10, false, 55, 55, 50, 50, new Attaque[]{new AttaqueMorsure(), new AttaqueTornadeFeuilles(), null, null});
		Pokemon totodile = new Pokemon("Totodile", "Eau", 8, true, 65, 64, 44, 48, new Attaque[]{new AttaqueMorsure(), new AttaquePistoleEau(), null, null});
		Nourriture tartiflette = new Nourriture("Tartiflette", 35, 20, new String[]{"Dragon", "Feu", "Combat", "Normal", "Eau", "Electrique"});
		Nourriture ratatouille = new Nourriture("Ratatouille", 10, 50, new String[]{"Plante", "Eau", "Vol", "Feu", "Normal", "Electrique"});
		Gourmandise barreChocolat = new Gourmandise("Barre au chocolat", 20, 10, new String[]{"Eau", "Feu", "Vol"}, 7);
		PotionMagique mojito = new PotionMagique("Mojito", 2);
		Jouet balle = new Jouet("Balle",20,10,10,5);
		Outil marteau = new Outil("Le Petit Marteau des Merveilles", 10,2);
		Joueur ben = new Joueur("Pelaudeix", "Benjamin", 19, new Pokemon[]{piplup,rowlet,totodile}, new Item[15]);

		//instanciation tableau
		Nourriture[] diversesNourritures = new Nourriture[] {tartiflette, ratatouille, barreChocolat, mojito};

		ben.ajouterItem(balle);

		ben.afficherSac();
		*/



		/*
		//code qui genere aleatoirement de la nourriture
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
					System.out.println("Vous avez trouve un.e/du/de la " + nourritureGeneree[j]);
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
				ben.afficherProvision();
			}
			if (reponse.equals("4")) {
				System.out.println("Quel pokemon voulez-vous nourrir ?");
				int reponsePokemon = scanner.nextInt();
				System.out.println("Que voulez-vous lui donner ?");
				int reponseNourriture = scanner.nextInt();
				ben.getPokemons()[reponsePokemon].manger(ben.getProvisions()[reponseNourriture]);
				System.out.println(ben.getPokemons()[reponsePokemon].getNom() + " a mange " + ben.getProvisions()[reponseNourriture]
						.nom);
			}
			System.out.println("Voulez-vous continuer ?");
			reponse = scanner.next();
		}
		System.out.println("Arret du jeu !");
		scanner.close();*/
		
		
	}
	
	//TP5
	//Q1- La signature du constructeur pour un objet de type Scanner est : public Scanner(InputStream source)
	//Pour une entree de type fichier : public Scanner(File source)
	//Q3- L'erreur de compilation retournee est : "Scanner cannot be resolved to a type".
	//Q5- Exercice sur l'utilisation du scanner
		//System.out.println("Etes-vous etudiant de premiere annee a l'IUT du Limousin ?");
		//String reponse = scanner.next();
		//System.out.println(reponse);
		//scanner.close();
	//Q5b- La methode next() de la classe Scanner recherche et renvoie le "token" suivant du scanner.
	//Q5e- Lors d'une reponse a plusieurs mots, seul le premier mot est affiche avec la commande System.out.println.
}

