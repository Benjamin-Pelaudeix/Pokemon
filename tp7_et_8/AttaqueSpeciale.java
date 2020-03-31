package tp7_et_8;

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
                int aleatoireAttaque = random.nextInt(attaquant.getAttaqueSpeciale());
                int aleatoireVictime = random.nextInt(victime.getDefenseSpeciale());
                if (attaquant.getAttaqueSpeciale() + aleatoireAttaque > victime.getDefenseSpeciale() + aleatoireVictime) {
                    precision = random.nextInt(100);
                    victime.blessure(random.nextInt(puissance));
                }
                repetitionsRestantes--;
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
}
