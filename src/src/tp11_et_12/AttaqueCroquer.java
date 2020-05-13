package tp11_et_12;

public class AttaqueCroquer extends AttaquePhysique {

    public AttaqueCroquer() {
        super("Croquer", 80, 100, 15);
    }

    @Override
    public AttaquePhysique genererMemeAttaque(boolean generer) {
        return super.genererMemeAttaque(generer);
    }
}
