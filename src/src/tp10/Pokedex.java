package tp10;

import java.io.FileReader;
import java.io.IOException;
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

    public void charger(String chemin) {
        try {
            FileReader source = new FileReader(chemin);
            Scanner s = new Scanner(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
