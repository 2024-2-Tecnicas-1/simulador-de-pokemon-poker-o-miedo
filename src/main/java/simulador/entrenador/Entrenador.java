package simulador.entrenador;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import simulador.pokemon.Pokemon;

public abstract class Entrenador {
    private String nombre;
    private List<Pokemon> Pokemones;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.Pokemones = new ArrayList<>();
    }

    public void AgregarPokemon(Pokemon pokemon) {
        Pokemones.add(pokemon);
        System.out.println(pokemon.getNombre() + " se anadio al equipo de " + nombre);
    }

    public void EntrenarPokemon(Pokemon pokemon) {
        if (Pokemones.contains(pokemon)) {
            int nuevaVida = (int) (pokemon.getVida() * 1.1);
            double nuevoPataque = pokemon.getPataque() * 1.1;
            pokemon.setVida(nuevaVida);
            pokemon.setPataque(nuevoPataque);
            System.out.println(pokemon.getNombre() + " ha entrenado mucho: Vida aumentada a " + nuevaVida + ", Ataque aumentado a " + nuevoPataque);
        } else {
            System.out.println(pokemon.getNombre() + " no se encuentra en el equipo de " + nombre);
        }
    }

    public void MostrarPokemones() {
        System.out.println("Pokemon de " + nombre + ":");
        for (Pokemon pokemon : Pokemones) {
            System.out.println("- " + pokemon.getNombre() + " (Vida: " + pokemon.getVida() + " Ataque: " + pokemon.getPataque() + ")");
        }
    }

    public Pokemon PrepararBatalla() {
        if (Pokemones.isEmpty()) {
            System.out.println(nombre + " no tiene Pokemon para la batalla");
            return null;
        }

        System.out.println(nombre + " selecciona un Pokemon");
        MostrarPokemones();

        Scanner sc = new Scanner(System.in);
        int seleccion = -1;

        while (seleccion < 1 || seleccion > Pokemones.size()) {
            try {
                seleccion = Integer.parseInt(sc.nextLine());
                if (seleccion < 1 || seleccion > Pokemones.size()) {
                    System.out.println("Seleccion invalida, por favor ingresa un numero valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un numero valido.");
            }
        }

        Pokemon pokemonSeleccionado = Pokemones.get(seleccion - 1);
        System.out.println(pokemonSeleccionado.getNombre() + " seleccionado para la batalla");
        return pokemonSeleccionado;
    }

    // Metodo para realizar el ataque y reducir vida
    public void RealizarAtaque(Pokemon atacante, Pokemon defensor) {
        double dano = atacante.getPataque();  // El dano es igual al poder de ataque del atacante
        double nuevaVida = defensor.getVida() - dano;  // Reducimos la vida del defensor por el dano causado
        defensor.setVida((int) Math.max(nuevaVida, 0));  // Aseguramos que la vida no sea negativa
        System.out.println(atacante.getNombre() + " ataca a " + defensor.getNombre() + " causando " + dano + " de dano. Vida restante de " + defensor.getNombre() + ": " + defensor.getVida());
    }

    public void ParticiparBatalla(Entrenador oponente) {
        System.out.println(nombre + " va a pelear contra " + oponente.getNombre());

        // Seleccionamos los Pokemon para la batalla
        Pokemon miPokemon = this.PrepararBatalla();
        Pokemon pokemonOponente = oponente.PrepararBatalla();

        if (miPokemon == null || pokemonOponente == null) {
            System.out.println("La batalla no pudo comenzar debido a la falta de Pokemon.");
            return;
        }

        // Ejemplo simple: un solo ataque entre los Pokemon
        RealizarAtaque(miPokemon, pokemonOponente);
        
        // Verificamos si el Pokemon oponente ha perdido toda su vida
        if (pokemonOponente.getVida() <= 0) {
            System.out.println(pokemonOponente.getNombre() + " ha sido derrotado.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getPokemones() {
        return Pokemones;
    }
}
