package tp6;

public class AttaqueSpeciale extends Attaque {

    public AttaqueSpeciale(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
        super(nom, compatibilites, puissance, precision, nombreRepetitions);
    }

    @Override
    public void utiliserAttaque(Pokemon attaquant, Pokemon victime) {

    }

    @Override
    public boolean isCompatible(Pokemon pokemon) {
        return false;
    }
}
