package tp6;

import java.util.Arrays;

public class Pokemon {
	
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
	private Attaque[] attaques = new Attaque[4];

	public Pokemon(String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur, int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, Attaque[] attaques) {
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
			if (attaques[i] != null) {
				this.ajouterAttaque(attaques[i]);
			}
		}
	}

	public Pokemon(String nom, String type, int niveau, boolean diurne, int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, Attaque[] attaques) {
		this(nom, type, niveau, diurne, null, null, attaque, defense, attaqueSpeciale, defenseSpeciale, attaques);
	}

	public void direBonjour(String periode) {
		if (periode.equals("jour")) {
			if (this.diurne == true) {
				System.out.println(nom + " dit : 'Bonjour !'");
			}
			else {
				System.out.println(nom + " dort : 'Zzzzzz !'");
			}
		}
		if (periode.equals("nuit")) {
			if (this.diurne == true) {
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
			System.out.println("Il appartient � " + this.monJoueur);
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
	
	public void manger(Nourriture nourriture) {
		if (nourriture != null && nourriture.isCompatible(this)) {
			nourriture.estMangee(this);
		}
		else {
			if (nourriture == null) {
				System.out.println("Il n'y a pas de nourriture...");
			}
			if (!nourriture.isCompatible(this)) {
				System.out.println("La nourriture n'est pas compatible avec ce Pokemon...");
			}
		}
	}

	private int trouverAttaque(Attaque attaque) {
		int position = -1;
		for (int i = 0; i < attaques.length; i++) {
			if (attaques[i] == attaque){
				return i;
			}
		}
		return position;
	}

	public void ajouterAttaque(Attaque attaque) {
		int positionLibre = trouverAttaque(null);
		if (attaque.isCompatible(this) && positionLibre != -1) {
			attaques[positionLibre] = attaque;
		}
		else {
			System.out.println("Votre pokemon n'est pas compatible ou il n'a plus de place dans ses attaques....");
		}
	}

	public void ajouterAttaque(Attaque attaque, int i) {
		if (i >= 0 && i < attaques.length) {
			this.attaques[i] = attaque;
		}
		else {
			System.out.println("Vous êtes hors de l'intervalle 0-" + this.attaques.length);
		}
	}

	public void rechargerAttaques() {
		for (int i =0; i < attaques.length; i++) {
			if (this.attaques[i] != null) {
				this.attaques[i].resetNombreRepetitions();
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
		if (!victime.etreEvanoui() && index >= 0 && index < attaques.length) {
			if (attaques[index] != null) {
				attaques[index].utiliserAttaque(this, victime);
				System.out.println(this.nom + " utilise " + attaques[index].getNom());
			}
		}
	}

	public void afficherEtatAttaques() {
		for (int i = 0; i < attaques.length; i++) {
			if (this.attaques[i] != null) {
				System.out.println(i + " : " + this.attaques[i].getNom() + " , " + this.attaques[i].getRepetitionsRestantes() + "/" + this.attaques[i].getNombreRepetitions() + "\n");
			}
		}
	}
	
	public void mettreANiveau() {
		this.niveau += 1;
	}
	
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

	public int getHp() { return this.hp;	}

	public Attaque[] getAttaques() { return this.attaques;	}

	public void setNomDonne(String nomDonne) { this.nomDonne = nomDonne; }
	
	public void setMonJoueur(Joueur monJoueur) { this.monJoueur = monJoueur; }
	
	public void setAppetit(int appetit) { this.appetit = appetit; }
	
	public void setLoyaute(int loyaute) { this.loyaute = loyaute;	}

	@Override
	public String toString() {
		return "Pokemon{" +
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
				", attaques=" + Arrays.toString(attaques) +
				'}';
	}
}
