package tp11_et_12;

public class Joueur {

	private final String nom;
	private final String prenom;
	private final int age;
	private Pokemon[] pokemons = new Pokemon[5];
	private Nourriture[] provisions = new Nourriture[10];
	private final Item[] sac;
	private final Pokedex pokedex;

	public Joueur(String nom, String prenom, int age, Pokemon[] pokemons, Item[] sac) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.pokemons = pokemons;
		for (int i = 0; i < pokemons.length; i++) {
			if (null!=this.pokemons[i]) {
				this.pokemons[i].setMonJoueur(this);
			}

		}
		this.provisions = new Nourriture[10];
		this.sac = new Item[15];
		this.pokedex = new Pokedex();
		this.pokedex.rencontrer(pokemons);
	}

	public Joueur(String nom, String prenom, int age) {
		this(nom, prenom, age, new Pokemon[5], new Item[15]);
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
		int iterateur = 0;
		while (iterateur < this.provisions.length) {
			if (this.provisions[iterateur] == nourriture) {
				return iterateur;
			}
		}
		return -1;
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
			pokemon.utiliser(nourriture);
			System.out.println(pokemon + " mange " + nourriture);
			provisions[trouverProvision(nourriture)] = null;
			System.out.println(nourriture + " a ete retiree de votre sac");
		}
	}

	public int trouverPokemon(Pokemon pokemon) {
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
			pokedex.rencontrer(pokemon);
		}
		else {
			System.out.println("Vous ne pouvez pas posseder un autre pok�mon... Veuillez en liberer un");
		}
	}

	public void liberer(Pokemon pokemon) {
		if (pokemon.getMonJoueur() != this) {
			System.out.println("Vous ne pouvez pas libérer un Pokemon qui ne vous appartient pas..");
			this.pokedex.rencontrer(pokemon);
		} else {
			pokemons[trouverPokemon(pokemon)].setMonJoueur(null);
			pokemons[trouverPokemon(pokemon)].setNomDonne(null);
			pokemons[trouverPokemon(pokemon)].setAppetit(10);
			pokemons[trouverPokemon(pokemon)].setLoyaute(0);
			pokemons[trouverPokemon(pokemon)] = null;
		}

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
				this.pokedex.rencontrer(pokemon);
			}
		}
	}

	public void caresserPokemon (Pokemon pokemon) {
		if (pokemon.getMonJoueur() != this) {
			System.out.println("Ce pokemon ne t'appartient pas... Tu ne peux pas le caresser !");
			this.pokedex.rencontrer(pokemon);
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

	public void donnerItem (int indexPokemon, int indexItem) {
		if ((indexPokemon > 0 && indexPokemon < this.getPokemons().length) && (indexItem > 0 && indexItem < this.getSac().length)) {
			if (this.getPokemons()[indexPokemon] != null && this.getSac()[indexItem] != null) {
				if (this.sac[indexItem] instanceof Utilisable) {
					if (this.sac[indexItem].getUtilisationsRestantes() > 0) {
						Utilisable item = (Utilisable)this.sac[indexItem];
						this.getPokemons()[indexPokemon].utiliser(item);
					}
					else {
						this.sac[indexItem] = null;
					}
				}
			}
		}
	}

	public void modifierItem (int indexChangeur, int indexAModifier) {
		if ((indexChangeur > 0 && indexChangeur < this.getSac().length) && (indexAModifier > 0 && indexAModifier < this.getSac().length)) {
			if (this.sac[indexChangeur] instanceof ChangerItems && this.sac[indexAModifier] instanceof Modifiable) {
				if (this.sac[indexChangeur].getUtilisationsRestantes() > 0) {
					Modifiable item = (Modifiable)this.sac[indexAModifier];
					item.modifier();
				}
				else {
					this.sac[indexChangeur] = null;
				}
			}
		}
	}

	public int trouverItem (Item item) {
		int iterateur = 0;
		while (iterateur < this.sac.length) {
			if (this.sac[iterateur] == item) {
				return iterateur;
			}
			iterateur++;
		}
		return -1;
	}

	public void ajouterItem (Item item) {
		if (trouverItem(null) != -1) {
			this.sac[trouverItem(null)] = item;
		}
	}

	public void lacherItem (Item item) {
		if (trouverItem(item) != -1) {
			this.sac[trouverItem(item)] = null;
		}
	}

	public void afficherSac() {
		String affichageSac = "Contenu du sac : ";
		for (int i = 0; i < this.sac.length; i++) {
			if (this.sac[i] != null) {
				affichageSac += this.sac[i] + ", ";
			}
		}
		System.out.println(affichageSac);
	}

	public String toString() {
		return this.prenom;
	}

	public Item[] getSac() {
		return sac;
	}

	public Pokedex getPokedex() { return pokedex; }
}
