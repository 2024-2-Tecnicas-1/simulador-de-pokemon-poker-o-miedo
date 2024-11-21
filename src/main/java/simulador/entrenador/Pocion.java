package simulador.objetos;

import simulador.pokemon.Pokemon;

public class Pocion {

    
    public void usar(Pokemon pokemon) {
        int vidaRestaurada = 20;  // Se restauran 20 puntos de vida
        pokemon.setVida(pokemon.getVida() + vidaRestaurada);  // Aumenta la vida del Pokémon
        System.out.println(pokemon.getNombre() + " ha usado una poción. Vida restaurada en " + vidaRestaurada + " puntos.");
        System.out.println("Vida actual de " + pokemon.getNombre() + ": " + pokemon.getVida());
    }
}
