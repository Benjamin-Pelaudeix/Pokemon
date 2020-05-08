package tp7_et_8;

public class Jouet extends Item implements Utilisable, Modifiable {

    int apportAppetit;
    int apportLoyaute;

    public Jouet(String nom, int frequence, int nombreUtilisations, int apportAppetit, int apportLoyaute) {
        super(nom, frequence, nombreUtilisations);
        this.apportAppetit = apportAppetit;
        this.apportLoyaute = apportLoyaute;
    }

    @Override
    public Item genererMemeItem(boolean generer) {
        if (generer) {
            return new Jouet(this.getNom(),this.getFrequence(), this.getNombreUtilisations(), this.getApportLoyaute(), this.getApportLoyaute());
        }
        else {
            return null;
        }
    }

    @Override
    public void modifier() {
        this.nom += " magique";
        this.apportAppetit += 10;
        this.apportLoyaute += 5;
        this.utilisationsRestantes = this.nombreUtilisations;
        System.out.println("Le jouet a ete modifie");
    }

    @Override
    public void utiliser(Joueur joueur, int indexPokemon) {
        if (joueur != null && (indexPokemon >= 0 || indexPokemon < joueur.getPokemons().length)) {
            if (joueur.getPokemons()[indexPokemon] != null && this.getUtilisationsRestantes() > 0) {
                joueur.getPokemons()[indexPokemon].monterAppetit(this.apportAppetit);
                joueur.getPokemons()[indexPokemon].monterLoyaute(this.apportLoyaute);
                this.baisserUtilisationsRestantes(1);
            }
        }
    }

    public int getApportAppetit() {
        return apportAppetit;
    }

    public int getApportLoyaute() {
        return apportLoyaute;
    }

    @Override
    public String toString() {
        return this.getNom() + " : " + this.getFrequence() + ", " + this.getUtilisationsRestantes() + "/" + this.getNombreUtilisations() + ", " + this.apportAppetit + this.apportLoyaute;
    }
}
