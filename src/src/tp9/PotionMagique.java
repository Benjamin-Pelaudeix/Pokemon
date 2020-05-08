package tp9;

public class PotionMagique extends Nourriture {

    public PotionMagique(String nom, int frequence) {
        super(nom, frequence,0, tousLesTypesDePokemons);
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new PotionMagique(this.getNom(), this.getFrequence());
        }
        else {
            return null;
        }
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        super.utiliser(joueur, indexPokemon);
        if (joueur.getPokemons()[indexPokemon] != null) {
            joueur.getPokemons()[indexPokemon].mettreANiveau();
            this.baisserUtilisationsRestantes(1);
        }
    }
}
