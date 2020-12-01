package tp11_et_12;

import java.util.Scanner;

public class Bataille {

    private final Pokemon pokemon1;
    private final Pokemon pokemon2;
    private final Scanner lecteurBataille;
    private boolean batailleFinie;

    public Bataille(Pokemon pokemon1, Pokemon pokemon2, Scanner scanner) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.lecteurBataille = scanner;
        this.batailleFinie = false;
    }

    public void run() {
        while (!batailleFinie) {
            this.choisirAction(pokemon1, pokemon2);
            if (pokemon2.etreEvanoui()) {
                batailleFinie = true;
                System.out.println("### Combat terminé ! " + pokemon2.getNom() + " (ennemi) est KO... ###");
                pokemon1.rechargerAttaques();
                pokemon2.rechargerAttaques();
            }
            else {
                this.choisirAction(pokemon2, pokemon1);
                if (pokemon1.etreEvanoui()) {
                    batailleFinie = true;
                    System.out.println("### Combat terminé ! " + pokemon1.getNom() + " est KO... ###");
                    pokemon1.rechargerAttaques();
                    pokemon2.rechargerAttaques();
                }
            }
        }
    }

    private void choisirAction(Pokemon pokemonActif, Pokemon pokemonPassif) {
        System.out.println(pokemonActif.getNom() + " (HP : " + pokemonActif.getHp() + ") choisit une attaque...");
        if (pokemonActif.getMonJoueur() != null) {
            pokemonActif.afficherEtatAttaques();
            int reponseAttaquePokemon1 = lecteurBataille.nextInt();
            pokemonActif.utiliserAttaque(reponseAttaquePokemon1, pokemonPassif);
        }
        else {
            int aleatoire = ((int) (Math.random() * pokemonActif.getAttaques().size()));
            pokemonActif.utiliserAttaque(aleatoire, pokemonPassif);
        }

        if (batailleFinie && pokemonPassif.getMonJoueur() == null) {
            System.out.println("Le pokémon vaincu n'a pas de maître. Souhaitez-vous le capturer ? (oui/non)");
            String reponseCapture = lecteurBataille.next();
            if (reponseCapture.equals("oui")) {
                if (pokemonActif.getMonJoueur().trouverPokemon(null) != -1) {
                    pokemonActif.getMonJoueur().capturer(pokemonPassif);
                }
                else {
                    System.out.println("Vous n'avez pas assez de place pour un pokémon supplémentaire. Voulez-vous en libérer un ? (oui/non)");
                    String reponseLiberer = lecteurBataille.next();
                    if (reponseLiberer.equals("oui")) {
                        System.out.println("Quel pokémon voulez-vous libérer ?");
                        int reponsePokemonLibere = lecteurBataille.nextInt();
                        pokemonActif.getMonJoueur().liberer(pokemonActif.getMonJoueur().getPokemons()[reponsePokemonLibere]);
                        System.out.println(pokemonActif.getMonJoueur().getPokemons()[reponsePokemonLibere].getNom() + " est désormais libre");
                        pokemonActif.getMonJoueur().capturer(pokemonPassif);
                        System.out.println(pokemonPassif.getNom() + " a été capturé avec succès");
                    } else {
                        System.out.println("Vous ne libérez aucun pokémon");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Bataille : " + pokemon1.getNom() + " vs " + pokemon2.getNom();
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public Scanner getLecteurBataille() {
        return lecteurBataille;
    }

    public boolean isBatailleFinie() {
        return batailleFinie;
    }
}
