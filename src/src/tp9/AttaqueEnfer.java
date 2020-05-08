package tp9;

public class AttaqueEnfer extends AttaqueSpeciale {

    public AttaqueEnfer() {
        super("Enfer", new String[]{"Feu"}, 100, 50, 5);
    }

    @Override
    public AttaqueSpeciale genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
