package tp5;

public class PotionMagique extends Nourriture {
	
	public PotionMagique(String nom, int frequence) {
		super(0, nom, tousLesTypesDePokemons, frequence);
	}
	
	@Override
	public PotionMagique genererMemeNourriture(boolean generer) {
		if (generer) {
			return new PotionMagique(this.nom, this.frequence);
		}
		else {
			return null;
		}
	}
	
	@Override
	public void estMangee(Pokemon pokemon) {
		super.estMangee(pokemon);
		pokemon.mettreANiveau();
	}
	
	@Override
	public String toString() {
		return("Potion magique : " + super.toString());
	}
}
