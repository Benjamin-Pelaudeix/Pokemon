package tp11_et_12;

public class AttaqueCoupDeTete extends AttaquePhysique {

    public AttaqueCoupDeTete() {
        super("Coup de tete", 70, 100, 15);
    }

    @Override
    public AttaquePhysique genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
