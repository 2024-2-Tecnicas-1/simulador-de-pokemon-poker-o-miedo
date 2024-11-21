package simulador.objetos;

import simulador.pokemon.Pokemon;

public class Elixir {

    public void usar(Pokemon pokemon) {
        double aumentoAtaque = pokemon.getPataque() * 0.2;  // Aumenta el ataque en un 20%
        pokemon.setPataque(pokemon.getPataque() + aumentoAtaque);  // Incrementa el ataque del Pokémon
        System.out.println(pokemon.getNombre() + " ha usado un elixir. Ataque aumentado en " + aumentoAtaque + ".");
        System.out.println("Nuevo ataque de " + pokemon.getNombre() + ": " + pokemon.getPataque());
    }
}
