package tp10;

public class AttaqueFeinte extends AttaquePhysique {

    public AttaqueFeinte() {
        super("Feinte", 30, 100, 10);
    }

    @Override
    public AttaquePhysique genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
