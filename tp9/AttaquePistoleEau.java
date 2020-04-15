package tp9;

public class AttaquePistoleEau extends AttaqueSpeciale {

    public AttaquePistoleEau() {
        super("Pistolet a eau", new String[]{"Eau"}, 40, 100, 25);
    }

    @Override
    public AttaqueSpeciale genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
