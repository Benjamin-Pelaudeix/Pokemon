package tp11_et_12;

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
                int aleatoireAttaque = random.nextInt(attaquant.getAttaque() + 1);
                int aleatoireVictime = random.nextInt(victime.getDefense() + 1);
                int precision = random.nextInt(101);
                if (attaquant.getAttaque() + aleatoireAttaque > victime.getDefense() + aleatoireVictime) {
                   victime.blessure(random.nextInt(puissance));
                   this.baisserNombreRepetitions();
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

    @Override
    public AttaquePhysique genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaquePhysique(this.nom,this.puissance,this.precision,this.nombreRepetitions);
        }
        else {
            return null;
        }
    }
}
