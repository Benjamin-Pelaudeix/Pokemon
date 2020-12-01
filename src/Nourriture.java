package tp11_et_12;

public class Nourriture extends Item implements Utilisable {

    private int apport;
    private String[] compatibilites;

    public static final String[] tousLesTypesDePokemons = new String[]{"Acier", "Combat", "Dragon", "Eau", "Electrique", "Fee", "Feu", "Glace", "Insecte", "Normal", "Plante", "Poison", "Psy", "Roche", "Sol", "Spectre", "Tenebres", "Vol"};

    public Nourriture(String nom, int frequence, int apport, String[] compatibilites) {
        super(nom, frequence, 1);
        this.apport = apport;
        this.compatibilites = compatibilites;
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new Nourriture(this.getNom(),this.getFrequence(), this.getApport(), this.getCompatibilites());
        }
        else {
            return null;
        }
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        if (joueur != null && indexPokemon >= 0 && indexPokemon <= joueur.getPokemons().length) {
            if (joueur.getPokemons()[indexPokemon] != null) {
                if (isCompatible(joueur.getPokemons()[indexPokemon]) && this.getUtilisationsRestantes() < 0) {
                    joueur.getPokemons()[indexPokemon].baisserAppetit(this.apport);
                    this.baisserUtilisationsRestantes(1);
                }
            }
        }
    }

    public boolean isCompatible(Pokemon pokemon) {
        boolean compatible = false;
        int i = 0;
        while (pokemon != null && i < this.compatibilites.length && !compatible) {
            if (this.compatibilites[i].equals(pokemon.getType())) {
                compatible = true;
            }
            i++;
        }
        return compatible;
    }

    public int getApport() {
        return apport;
    }

    public String[] getCompatibilites() {
        return compatibilites;
    }

    @Override
    public String toString() {
        return this.getNom() + " : " + this.getFrequence() + ", " + this.getUtilisationsRestantes() + "/" + this.getNombreUtilisations() + ", " + this.apport + ", {" + compatibilites + "}";
    }
}
