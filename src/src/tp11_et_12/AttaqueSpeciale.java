package tp11_et_12;

import java.util.Random;

public class AttaqueSpeciale extends Attaque {

    Random random = new Random();

    public AttaqueSpeciale(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
        super(nom, compatibilites, puissance, precision, nombreRepetitions);
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
        boolean compatible = false;
        for (int i = 0; i<this.compatibilites.length; i++) {
            if (this.compatibilites[i].equals(pokemon.getType())) {
                compatible = true;
            }
        }
        return compatible;
    }

    @Override
    public AttaqueSpeciale genererMemeAttaque(boolean generer) {
        if (generer) {
            return new AttaqueSpeciale(this.nom, this.compatibilites, this.puissance, this.precision, this.nombreRepetitions);
        } else {
            return null;
        }
    }
}
