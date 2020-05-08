package tp5;

public class Nourriture {
	
	protected int apport;
	protected String nom;
	protected String[] compatibilites = new String[18]; //on instancie le tableau a 18 car il existe 18 types de pokemons differents
	protected int frequence;
	
	public static final String[] tousLesTypesDePokemons = new String[]{"Acier", "Combat", "Dragon", "Eau", "Electrique", "Fee", "Feu", "Glace", "Insecte", "Normal", "Plante", "Poison", "Psy", "Roche", "Sol", "Spectre", "Tenebres", "Vol"}; 
	
	public Nourriture(int apport, String nom, String[] compatibilites, int frequence) {
		this.apport = apport;
		this.nom = nom;
		this.compatibilites = compatibilites;
		this.frequence = frequence;
	}
	
	public boolean isCompatible(Pokemon pokemon) {
		boolean compatible = false;
		for (int i = 0; i<this.compatibilites.length; i++) {
			if (this.compatibilites[i].equals(pokemon.getType())) {
				compatible = true;
			}
		}
		return compatible;
	}
	
	public Nourriture genererMemeNourriture(boolean generer) {
		if (generer) {
			return new Nourriture(this.apport, this.nom, this.compatibilites.clone(), this.frequence);
		}
		else {
			return null;
		}
	}
	
	public void estMangee(Pokemon pokemon) {
		if (pokemon != null) {
			pokemon.baisserAppetit(apport);
		}
	}
	
	public String toString() {
		String compatibilites = "";
		for (int i=0;i<=this.compatibilites.length-1;i++) {
            compatibilites += this.compatibilites[i] +", ";
        }
		return (this.nom + " , " + this.apport +  " , " + this.frequence + "/100 , " + "{" + compatibilites + "}");
	}
	
	public int getApport() {
		return this.apport;
	}
	
	public String[] getCompatibilites() {
		return this.compatibilites;
	}
	
	public int getFrequence() {
		return this.frequence;
	}
	public String getNom() {
		return this.nom;
	}
}
