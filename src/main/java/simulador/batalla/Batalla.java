package simulador.pokemon;

import simulador.entrenador.Entrenador;
import java.util.Scanner;

public class Batalla {

    public void iniciarBatalla(Entrenador entrenador1, Entrenador entrenador2) {
        System.out.println("Que comience la batalla entre " + entrenador1.getNombre() + " y " + entrenador2.getNombre() + "");
        
        Pokemon pokemon1 = entrenador1.PrepararBatalla();
        Pokemon pokemon2 = entrenador2.PrepararBatalla();

        if (pokemon1 == null || pokemon2 == null) {
            System.out.println("La batalla no puede continuar. Uno de los entrenadores no tiene un Pokemon valido.");
            return;
        }

        while (pokemon1.getVida() > 0 && pokemon2.getVida() > 0) {
            // Turno del primer entrenador
            System.out.println("\nTurno de " + pokemon1.getNombre());
            mostrarMenuBatalla(entrenador1, pokemon1, pokemon2);

            // Si pokemon2 ya está vencido, terminar la batalla
            if (pokemon2.getVida() <= 0) break;

            // Turno del segundo entrenador
            System.out.println("\nTurno de " + pokemon2.getNombre());
            mostrarMenuBatalla(entrenador2, pokemon2, pokemon1);

            // Si pokemon1 ya está vencido, terminar la batalla
            if (pokemon1.getVida() <= 0) break;
        }

        // Se determina el ganador
        if (pokemon1.getVida() > 0) {
            System.out.println("\n" + pokemon1.getNombre() + " de " + entrenador1.getNombre() + " gano la batalla.");
        } else {
            System.out.println("\n" + pokemon2.getNombre() + " de " + entrenador2.getNombre() + " gano la batalla.");
        }
    }

    private void mostrarMenuBatalla(Entrenador entrenador, Pokemon pokemonAtacante, Pokemon pokemonDefensor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nElige una accion para " + pokemonAtacante.getNombre() + ":");
        System.out.println("1. Atacar");
        System.out.println("2. Cambiar Pokemon");
        System.out.println("3. Usar objeto (a implementar)");
        System.out.print("Tu elección: ");
        
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                realizarAtaque(pokemonAtacante, pokemonDefensor);
                break;
            case 2:
                Pokemon nuevoPokemon = entrenador.PrepararBatalla(); // Cambiar Pokémon
                if (nuevoPokemon != null) {
                    pokemonAtacante = nuevoPokemon; // Se cambia el Pokémon actual
                }
                break;
            case 3:
                // En este caso, solo se ha mencionado un posible uso de objetos,
                // si se desea implementar este paso, podría ir aquí.
                System.out.println("Aún no se implementan objetos.");
                break;
            default:
                System.out.println("Opcion no valida, intenta de nuevo.");
                mostrarMenuBatalla(entrenador, pokemonAtacante, pokemonDefensor); // Volver a mostrar el menú
                break;
        }
    }

    private void realizarAtaque(Pokemon atacante, Pokemon defensor) {
        double multiplicador = TipoPokemon.obtenerMultiplicadorDeDaño(atacante.getTipos(), defensor.getTipos());
        int daño = (int) (atacante.getPataque() * multiplicador);

        defensor.recibirDaño(daño);

        System.out.println(atacante.getNombre() + " ataca a " + defensor.getNombre() +
                " causando " + daño + " de daño (Multiplicador: " + multiplicador + ").");
    }
}
