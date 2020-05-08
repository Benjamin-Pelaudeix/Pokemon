package tp10;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Pokedex {

    Set<Integer> setPokemons;

    public Pokedex() {
        this.setPokemons = new TreeSet<>();
    }

    public void rencontrer(Pokemon pokemon) {
        if (pokemon != null) {
            int numeroPokemonRencontre = pokemon.getNumeroPokedex();
            setPokemons.add(numeroPokemonRencontre);
        }
    }

    public void rencontrer(Pokemon[] pokemons) {
        for (Pokemon pokemon : pokemons) {
           rencontrer(pokemon);
        }
    }

    public void charger(String chemin) throws IOException, FileNotFoundException {
        try(FileReader source = new FileReader(chemin)) {
            Scanner s = new Scanner(source);
            while (s.hasNextLine()) {
                int numero = s.nextInt();
                this.setPokemons.add(numero);
                s.nextLine();
            }
            s.close();
        }
    }

    public void sauvegarder(String chemin) throws IOException {
        try(FileWriter scribe = new FileWriter(chemin)) {
            PrintWriter afficheur = new PrintWriter(scribe);
            for (int numero : setPokemons) {
                afficheur.println(numero);
            }
            scribe.close();
            afficheur.close();
        }
    }
}
