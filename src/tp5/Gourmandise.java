package src.tp5;

public class Gourmandise extends Nourriture {
	
	private int apportLoyaute;
	
	public Gourmandise(int apport, String nom, String[] compatibilites, int frequence, int apportLoyaute) {
		super(apport, nom, compatibilites, frequence);
		this.apportLoyaute = apportLoyaute;
	}
	
	@Override
	public Gourmandise genererMemeNourriture(boolean generer) {
		if (generer) {
			return new Gourmandise(this.apport, this.nom, this.compatibilites.clone(), this.frequence, this.apportLoyaute);
		}
		else {
			return null;
		}
	}
	
	@Override
	public void estMangee(Pokemon pokemon) {
		super.estMangee(pokemon);
		if (pokemon.getMonJoueur() != null) {
			pokemon.monterLoyaute(apportLoyaute);
		}
	}
	
	@Override
	public String toString() {
		return("Gourmandise : " + this.apportLoyaute + " , " + super.toString());
	}
	
	public int getApportLoyaute() {
		return this.apportLoyaute;
	}
}