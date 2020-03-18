package src.tp5;

public class Pokemon {
	
	private String nom;
	private String type;
	private int niveau;
	private boolean diurne;
	private String nomDonne;
	private Joueur monJoueur;
	private int appetit;
	private int loyaute;
	
	public Pokemon(String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur, int appetit, int loyaute) {
		this.nom = nom;
		this.type = type;
		this.niveau = niveau;
		this.diurne = diurne;
		this.nomDonne = nomDonne;
		this.monJoueur = monJoueur;
		this.appetit = appetit;
		this.loyaute = loyaute;
	}
	
	public Pokemon(String nom, String type, int niveau, boolean diurne) {
		this(nom, type, niveau, diurne, null, null, 50, 0);
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
	
	public void setNomDonne(String nomDonne) {
		this.nomDonne = nomDonne;
	}
	
	public void setMonJoueur(Joueur monJoueur) {
		this.monJoueur = monJoueur;
	}
	
	public void setAppetit(int appetit) {
		this.appetit = appetit;
	}
	
	public void setLoyaute(int loyaute) {
		this.loyaute = loyaute;
	}
	
	public String toString() {
		return("[ Nom : " + this.nom + "; Type : " + this.type + "; Niveau : " +this.niveau + "; Diurne : " + this.diurne + " ; Appetit : " + this.appetit + " ; Loyaute : " + this.loyaute + "; Nom Donne : " + this.nomDonne + "; Mon Joueur : " + this.monJoueur + " ]");
	}
}
