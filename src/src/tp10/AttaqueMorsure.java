package tp10;

public class AttaqueMorsure extends AttaquePhysique {

    public AttaqueMorsure() {
        super("Morsure", 60, 30, 25);
    }

    @Override
    public AttaquePhysique genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
