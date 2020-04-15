package tp9;

public class Gourmandise extends Nourriture {

    private int apportLoyaute;

    public Gourmandise(String nom, int frequence, int apport, String[] compatibilites, int apportLoyaute) {
        super(nom, frequence, apport, compatibilites);
        this.apportLoyaute = apportLoyaute;
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new Gourmandise(this.getNom(), this.getFrequence(), this.getApport(), this.getCompatibilites(), this.getApportLoyaute());
        }
        else {
            return null;
        }
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        super.utiliser(joueur, indexPokemon);
        if (joueur.getPokemons()[indexPokemon] != null) {
            joueur.getPokemons()[indexPokemon].monterLoyaute(apportLoyaute);
        }
    }

    public int getApportLoyaute() {
        return apportLoyaute;
    }

    @Override
    public String toString() {
        return this.getNom() + " : " + this.getFrequence() + ", " + this.getUtilisationsRestantes() + "/" + this.getNombreUtilisations() + ", " + this.apportLoyaute + ", {" + this.getCompatibilites() + "}";
    }
}
