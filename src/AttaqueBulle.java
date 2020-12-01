package tp11_et_12;

public class AttaqueBulle extends AttaqueSpeciale {

    public AttaqueBulle() {
        super("Bulle", new String[]{"Eau"}, 40, 100, 30);
    }

    @Override
    public AttaqueSpeciale genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
