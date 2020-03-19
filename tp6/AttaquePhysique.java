package tp6;

import java.util.Random;

public class AttaquePhysique extends Attaque {

    Random random = new Random();

    public AttaquePhysique(String nom, int puissance, int precision, int nombreRepetitions) {
        super(nom, puissance, precision, nombreRepetitions);
    }

    @Override
    public void utiliserAttaque(Pokemon attaquant, Pokemon victime) {
        if (attaquant != null && victime != null) {
            if (repetitionsRestantes > 0) {
                int aleatoireAttaque = random.nextInt(attaquant.getAttaque());
                int aleatoireVictime = random.nextInt(victime.getDefense());
                if (attaquant.getAttaque() + aleatoireAttaque > victime.getDefense() + aleatoireVictime) {
                   precision = random.nextInt(100);
                   victime.blessure(random.nextInt(puissance));
                   repetitionsRestantes--;
                }
            }
        }
    }

    @Override
    public boolean isCompatible(Pokemon pokemon) {
        if (pokemon == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
