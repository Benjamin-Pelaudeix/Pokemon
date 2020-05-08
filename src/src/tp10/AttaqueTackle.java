package tp10;

public class AttaqueTackle extends AttaquePhysique {
    public AttaqueTackle() {
        super("Tackle", 40, 100, 35);
    }

    @Override
    public AttaquePhysique genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
