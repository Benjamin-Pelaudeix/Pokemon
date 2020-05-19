package tp11_et_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pokemon {

	private int numeroPokedex;
	private String nom;
	private String type;
	private int niveau;
	private boolean diurne;
	private String nomDonne;
	private Joueur monJoueur;
	private int appetit;
	private int loyaute;
	private int attaque;
	private int defense;
	private int attaqueSpeciale;
	private int defenseSpeciale;
	private int hp;
	private List<Attaque> attaques = new ArrayList<Attaque>(4);

	public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur, int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, Attaque[] attaques) {
		this.numeroPokedex = numeroPokedex;
		this.nom = nom;
		this.type = type;
		this.niveau = niveau;
		this.diurne = diurne;
		this.nomDonne = nomDonne;
		this.monJoueur = monJoueur;
		this.appetit = 50;
		this.loyaute = 0;
		this.attaque = attaque;
		this.defense = defense;
		this.attaqueSpeciale = attaqueSpeciale;
		this.defenseSpeciale = defenseSpeciale;
		this.hp = 30;
		for (int i = 0; i < attaques.length; i++) {
				this.ajouterAttaque(attaques[i]);
		}
		if (attaques.length > 4) {
			System.err.println("Liste trop longue ! Seules les 4 premières attaques ont été prises en compte.");
		}
	}

	public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, Attaque[] attaques) {
		this(numeroPokedex, nom, type, niveau, diurne, null, null, attaque, defense, attaqueSpeciale, defenseSpeciale, attaques);
	}

	public Pokemon(int numeroPokedex, String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur, int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, List<Attaque> attaques) {
	}

	public void direBonjour(String periode) {
		if (periode.equals("jour")) {
			if (this.diurne) {
				System.out.println(nom + " dit : 'Bonjour !'");
			}
			else {
				System.out.println(nom + " dort : 'Zzzzzz !'");
			}
		}
		if (periode.equals("nuit")) {
			if (this.diurne) {
				System.out.println(nom + " dort : 'Zzzzzz !'");
			}
			else {
				System.out.println(nom + " dit : 'Bonjour !'");
			}
		}
	}

	public void sePresenter() {
		System.out.println("Voici un pokemon " + this.nom + " de niveau " + this.niveau);
		if (this.monJoueur != null) {
			System.out.println("Il appartient a " + this.monJoueur);
			if (this.nomDonne != null) {
				System.out.println("Il s'appelle " + this.nomDonne);
			}
		}
	}

	public void baisserAppetit(int difference) {
		if (this.appetit - difference > 0) {
			this.appetit -= difference;
		}
		else {
			this.appetit = 0;
		}
	}

	public void monterAppetit(int difference) {
		if (this.appetit + difference < 100) {
			this.appetit += difference;
		}
		else {
			this.appetit = 100;
		}
	}

	public void baisserLoyaute(int difference) {
		if (this.loyaute - difference > 0) {
			this.loyaute -= difference;
		}
		else {
			this.loyaute = 0;
		}
	}

	public void monterLoyaute(int difference) {
		if (this.loyaute + difference < 100) {
			this.loyaute += difference;
		}
		else {
			this.appetit = 100;
		}
	}

	public void utiliser(Utilisable item) {
		if (item != null) {
			if (this.getMonJoueur() != null) {
				int index = this.getMonJoueur().trouverPokemon(this);
				if (index != -1) {
					this.utiliser(item);
				}
			}
			else {
				System.out.println("Ce pokemon n'a pas de maitre.. Il ne peut pas recevoir des objets utilisables.");
			}
		}
	}

	private int trouverAttaque(Attaque attaque) {
		return this.attaques.indexOf(attaque);
	}

	public void ajouterAttaque(Attaque attaque) {
		if (attaque != null && attaque.isCompatible(this)) {
			if (this.attaques.size() <= 4) {
				attaques.add(attaque);
			}
			else {
				System.err.println("Votre pokemon n'est pas compatible ou il n'a plus de place dans ses attaques....");
			}
		}
	}

	public void ajouterAttaque(Attaque attaque, int i) {
		if (i >= 0 && i < 4) {
			this.attaques.add(i, attaque);
		}
		else {
			System.err.println("Vous êtes hors de l'intervalle 0-" + this.attaques.size());
		}
	}

	public void rechargerAttaques() {
		for (int i =0; i < attaques.size(); i++) {
			if (this.attaques.get(i) != null) {
				this.attaques.get(i).resetNombreRepetitions();
			}
		}
	}

	public void blessure(int dommage) {
		this.hp -= dommage;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	public boolean etreEvanoui() {
		if (this.hp == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void utiliserAttaque(int index, Pokemon victime) {
		if (!victime.etreEvanoui() && index >= 0 && index < attaques.size()) {
			if (attaques.get(index) != null) {
				attaques.get(index).utiliserAttaque(this, victime);
				System.out.println(this.nom + " utilise " + attaques.get(index).getNom());
			}
		}
	}

	public void afficherEtatAttaques() {
		for (int i = 0; i < attaques.size(); i++) {
			if (this.attaques.get(i) != null) {
				System.out.println(i + " : " + this.attaques.get(i).getNom() + " , " + this.attaques.get(i).getRepetitionsRestantes() + "/" + this.attaques.get(i).getNombreRepetitions());
			}
		}
	}

	public Pokemon genererMemePokemon(boolean generer) {
		if (generer) {
			return new Pokemon(this.numeroPokedex, this.nom, this.type, this.niveau, this.diurne, null, null, this.attaque, this.defense, this.attaqueSpeciale, this.defenseSpeciale, this.attaques);
		}
		else {
			return null;
		}
	}

	public void mettreANiveau() {
		this.niveau += 1;
	}

	public int getNumeroPokedex() {	return numeroPokedex; }

	public String getNom() {
		return this.nom;
	}

	public String getType() {
		return this.type;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public boolean etreDiurne() {
		return this.diurne;
	}

	public String getNomDonne() {
		return this.nomDonne;
	}

	public Joueur getMonJoueur() {
		return this.monJoueur;
	}
	
	public int getAppetit() {
		return this.appetit;
	}
	
	public int getLoyaute() {
		return this.loyaute;
	}

	public int getAttaque() { return this.attaque; }

	public int getDefense() { return this.defense; }

	public int getAttaqueSpeciale() { return this.attaqueSpeciale;	}

	public int getDefenseSpeciale() { return this.defenseSpeciale;	}

	public int getHp() { return this.hp; }

	public List<Attaque> getAttaques() { return this.attaques; }

	public void setNomDonne(String nomDonne) { this.nomDonne = nomDonne; }
	
	public void setMonJoueur(Joueur monJoueur) { this.monJoueur = monJoueur; }
	
	public void setAppetit(int appetit) { this.appetit = appetit; }
	
	public void setLoyaute(int loyaute) { this.loyaute = loyaute;	}

	@Override
	public String toString() {
		return "Pokemon{" +
				numeroPokedex + " : " +
				"nom='" + nom + '\'' +
				", type='" + type + '\'' +
				", niveau=" + niveau +
				", diurne=" + diurne +
				", nomDonne='" + nomDonne + '\'' +
				", monJoueur=" + monJoueur +
				", appetit=" + appetit +
				", loyaute=" + loyaute +
				", attaque=" + attaque +
				", defense=" + defense +
				", attaqueSpeciale=" + attaqueSpeciale +
				", defenseSpeciale=" + defenseSpeciale +
				", hp=" + hp +
				", attaques=" + Arrays.toString(new List[]{attaques}) +
				'}';
	}
}
