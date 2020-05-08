package tp5;

public class Joueur {
	
	private String nom;
	private String prenom;
	private int age;
	private Pokemon[] pokemons = new Pokemon[5];
	private Nourriture[] provisions = new Nourriture[10];
	
	public Joueur(String nom, String prenom, int age, Pokemon[] pokemons, Nourriture[] provisions) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.pokemons = pokemons;
		for (int i = 0; i < pokemons.length; i++) {
			if (null!=this.pokemons[i]) {
				this.pokemons[i].setMonJoueur(this);
			}
			
		}
		this.provisions = provisions;
		for (int i = 0; i < provisions.length; i++) {
			this.provisions[i] = null;
		}
	}
	
	public Joueur(String nom, String prenom, int age) {
		this(nom, prenom, age, new Pokemon[5], new Nourriture[10]);
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public Pokemon[] getPokemons() {
		return this.pokemons;
	}
	
	public Nourriture[] getProvisions() {
		return this.provisions;
	}
	
	private int trouverProvision(Nourriture nourriture) {
		int position = -1;
		for (int i = 0; i < provisions.length; i++) {
			if (provisions[i] == nourriture){
				return i;
			}
		}
		return position;
	}

	public void ajouterProvision(Nourriture nourriture) {
		if (this.trouverProvision(null) != -1) {
			provisions[trouverProvision(null)] = nourriture;
		}
		else {
			System.out.println("Vous n'avez plus de place dans votre sac de provision...");
		}
	}

	public void afficherProvision() {
		for (int i = 0; i < this.provisions.length; i++) {
			if (this.provisions[i] != null) {
				System.out.println(i + " : " + this.provisions[i].nom);
			}
		}
	}
	
	public void nourrirPokemon(Pokemon pokemon, Nourriture nourriture) {
		if (pokemon == null || nourriture == null || pokemon.getMonJoueur() != this) {
			System.out.println("Vous ne pouvez pas nourrir ce pokemon...");
		}
		else {
			pokemon.manger(nourriture);
			System.out.println(pokemon + " mange " + nourriture);
			provisions[trouverProvision(nourriture)] = null;
			System.out.println(nourriture + " a ete retiree de votre sac");
		}
	}
	
	private int trouverPokemon(Pokemon pokemon) {
		boolean pokemonTrouve = false;
		int i = 0;
		
		while (i<this.pokemons.length && !pokemonTrouve) {
			if(this.pokemons[i]==pokemon) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	//Question 3- On peut utiliser la methode trouverPokemon pour trouver une place disponible dans notre tableau de Pok�mon en specifiant en entrer la reference memoire "null".
	
	public void capturer(Pokemon pokemon) {
		if ((pokemon.getMonJoueur() == null) && trouverPokemon(null) != -1) {
			pokemon.setMonJoueur(this); //mise � jour des informations du pokemon
			pokemon.setAppetit(10);
			pokemon.setLoyaute(0);
			pokemons[trouverPokemon(null)] = pokemon;
		} 
		else {
			System.out.println("Vous ne pouvez pas posseder un autre pok�mon... Veuillez en liberer un");
		}
	}
	
	public void liberer(Pokemon pokemon) {
		pokemons[trouverPokemon(pokemon)].setMonJoueur(null);
		pokemons[trouverPokemon(pokemon)].setNomDonne(null);
		pokemons[trouverPokemon(pokemon)].setAppetit(10);
		pokemons[trouverPokemon(pokemon)].setLoyaute(0);
		pokemons[trouverPokemon(pokemon)] = null;
	}
	
	public void nommer(Pokemon pokemon, String nomDonne) {
		if (pokemon != null) {
			int positionPokemon = this.trouverPokemon(pokemon);
			if (positionPokemon != -1) {
				if (pokemon.getNomDonne() == null) {
					pokemon.monterLoyaute(10);
				}
				else {
					pokemon.baisserLoyaute(10);
				}
				pokemon.setNomDonne(nomDonne);
			}
			else {
				System.out.println("Vous ne pouvez pas nommer ce pokemon car vous n'etes pas son maitre !");
			}
		}
	}
	
	public void caresserPokemon (Pokemon pokemon) {
		if (pokemon.getMonJoueur() != this) {
			System.out.println("Ce pokemon ne t'appartient pas... Tu ne peux pas le caresser !");
		}
		else
		{
			if (pokemon.getLoyaute() + 1 < 100) {
				System.out.println("Mmmm, ca sent bon. Et sous mon oreille gauche ?");
			}
			else {
				System.out.println("Oui, moi aussi je t'aime !");
			}
		}
	}
	
	public String toString() {
		return this.prenom;
	}
}
