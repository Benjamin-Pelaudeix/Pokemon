package tp6;

public class AttaqueBulle extends AttaqueSpeciale {

    public AttaqueBulle(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
        super("Bulle", new String[]{"Eau"}, 40, 100, 30);
    }
}
