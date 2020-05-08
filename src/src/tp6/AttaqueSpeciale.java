package tp6;

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
                precision = random.nextInt(100);
                if (attaquant.getAttaqueSpeciale() + aleatoireAttaque > victime.getDefenseSpeciale() + aleatoireVictime && precision <= this.getPrecision()) {
                    victime.blessure(random.nextInt(puissance));
                }
                repetitionsRestantes--;
            }
        }
    }

    @Override
    public boolean isCompatible(Pokemon pokemon) {
        boolean compatible = false;
        int i = 0;
        while (i < this.compatibilites.length && !compatible) {
            if (this.compatibilites[i].equals(pokemon.getType())) {
                compatible = true;
            }
            i++;
        }
        return compatible;
    }
}
