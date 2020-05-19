package tp11_et_12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
		mappeAttaque.put("coupDeTete", new AttaqueCoupDeTete());
		mappeAttaque.put("croquer", new AttaqueCroquer());
		mappeAttaque.put("bulle", new AttaqueBulle());

		tp11_et_12.Nourriture tartiflette = new tp11_et_12.Nourriture("Tartiflette",35, 20,new String[]{"Dragon", "Feu", "Combat", "Normal", "Eau", "Electrique"});
		tp11_et_12.Nourriture ratatouille = new Nourriture("Ratatouille", 10, 50,new String[]{"Plante", "Eau", "Vol", "Feu", "Normal", "Electrique"});
		tp11_et_12.Gourmandise barreChocolat = new Gourmandise("Barre au chocolat",20, 10, new String[]{"Eau", "Feu", "Vol"},  7);
		tp11_et_12.PotionMagique mojito = new PotionMagique("Mojito", 2);

		final Random seedAlea = new Random();

		final HashMap<String, Integer> mappePokemon = new HashMap<String, Integer>();

		ArrayList<Pokemon> pokemonsUtilises = new ArrayList<>();

		try {
			FileReader source = new FileReader("src/src/tp11_et_12/pokedexComplet.txt");
			Scanner s = new Scanner(source);
			while (s.hasNextLine()) {
				int index = s.nextInt();
				String nom = s.next();
				mappePokemon.put(nom, index);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileReader source = new FileReader("src/src/tp11_et_12/InputFile.txt");
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
				int i = 0;
				while (i < attaques.length && !s.hasNext("END")) {
					attaques[i] = mappeAttaque.get(s.next());
					i++;
				}
				pokemonsUtilises.add(new Pokemon(mappePokemon.get(nom), nom, type, niveau, diurne, attaque, defense, attaqueSpeciale, defenseSpeciale, attaques));
				s.nextLine();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		Joueur ben = new Joueur("Pelaudeix", "Benjamin", 19, new Pokemon[]{pokemonsUtilises.get(0), pokemonsUtilises.get(1), pokemonsUtilises.get(2)}, new Item[15]);


		try {
			ben.getPokedex().charger("src/src/tp11_et_12/pokedexJoueur.txt");
		} catch (FileNotFoundException f) {
			f.printStackTrace();
			System.out.println("Fichier introuvable, mais il va être bientôt créer");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de charger le fichier : " + e.getMessage());
			System.exit(1);
		} catch (InputMismatchException i) {
			i.printStackTrace();
			System.out.println("Impossible de charger le fichier : " + i.getMessage());
			System.exit(1);
		}

		try	{
			ben.getPokedex().sauvegarder("src/src/tp11_et_12/pokedexJoueur.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Impossible de charger le fichier : " + e.getMessage());
			System.exit(1);
		}

		//COMBAT
		Scanner scanner = new Scanner(System.in);
		/*System.out.println("##COMBAT##\nPokemon 1:");
		int reponsePokemon1 = scanner.nextInt();
		System.out.println("Pokemon 2:");
		int reponsePokemon2 = scanner.nextInt();
		while (reponsePokemon1 < 0 || reponsePokemon1 > ben.getPokemons().length || reponsePokemon2 < 0 || reponsePokemon2 > ben.getPokemons().length || reponsePokemon1 == reponsePokemon2) {
			System.out.println("Saisies non valides... Veuillez recommencer !\nPokemon 1:");
			reponsePokemon1 = scanner.nextInt();
			System.out.println("Pokemon 2:");
			reponsePokemon2 = scanner.nextInt();
		}
		Bataille combat = new Bataille(ben.getPokemons()[reponsePokemon1], ben.getPokemons()[reponsePokemon2]);
		System.out.println(combat);
		combat.run();*/

		//ITEM
		//code qui genere aleatoirement de la nourriture
		double alea;
		int nombreDEssais = Integer.parseInt(args[0]);
		String reponse = "";
		String reponseNourriture = "";
		int reponsePokemonChoisi = -1;
		boolean generer;
		tp11_et_12.Nourriture[] diversesNourritures = new tp11_et_12.Nourriture[] {tartiflette, ratatouille, barreChocolat, mojito};
		Item[] nourritureGeneree = new Item[diversesNourritures.length];

		while (!reponse.equals("stop")) {
			alea = Math.random()*100;
			int aleaGenererPokemon = seedAlea.nextInt(pokemonsUtilises.size());
			if (alea < 100.0/pokemonsUtilises.get(aleaGenererPokemon).getNiveau()) {
				generer = true;
				pokemonsUtilises.get(aleaGenererPokemon).genererMemePokemon(generer);
				System.out.println("Un " + pokemonsUtilises.get(aleaGenererPokemon).getNom() + " de niveau " + pokemonsUtilises.get(aleaGenererPokemon).getNiveau() + " a été généré\nVoulez-vous engager un combat ? (oui/non)");
				reponse = scanner.next();
				if (reponse.equals("oui")) {
					System.out.println("Choisissez votre pokemon");
					reponsePokemonChoisi = scanner.nextInt();
					if (reponsePokemonChoisi < 0 || reponsePokemonChoisi > ben.getPokemons().length) {
						System.out.println("Vous n'avez pas de pokémon a cet index... Vous perdez votre tour !");
					}
					else {
						Bataille nouveauCombat = new Bataille(ben.getPokemons()[reponsePokemonChoisi], pokemonsUtilises.get(aleaGenererPokemon),scanner);
						System.out.println(nouveauCombat);
						nouveauCombat.run();
					}

				}
			}
			for (int j = 0; j<diversesNourritures.length; j++) {
				if (alea < diversesNourritures[j].getFrequence()) {
					generer = true;
					nourritureGeneree[j] = diversesNourritures[j].genererMemeItem(generer);
					System.out.println("Vous avez trouve un.e/du/de la " + nourritureGeneree[j]);
					System.out.println("Voulez-vous prendre cette nourriture ? (oui/non)");
					reponseNourriture = scanner.next();
					if (reponseNourriture.equals("oui")) {
						ben.ajouterItem(nourritureGeneree[j]);
					}
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
				int reponseNumeroNourriture = scanner.nextInt();
				ben.getPokemons()[reponsePokemon].utiliser(ben.getProvisions()[reponseNumeroNourriture]);
				System.out.println(ben.getPokemons()[reponsePokemon].getNom() + " a mange " + ben.getProvisions()[reponseNumeroNourriture]
						.nom);
			}
			System.out.println("Voulez-vous continuer ?");
			reponse = scanner.next();
		}
		System.out.println("Arret du jeu !");
		scanner.close();
	}



























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


		//instanciation tableau


		ben.ajouterItem(balle);

		ben.afficherSac();
		*/




	
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

