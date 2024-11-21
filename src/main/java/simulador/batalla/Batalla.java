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
        
            System.out.println("\nTurno de " + pokemon1.getNombre());
            mostrarMenuBatalla(entrenador1, pokemon1, pokemon2);

            
            if (pokemon2.getVida() <= 0) break;

           
            System.out.println("\nTurno de " + pokemon2.getNombre());
            mostrarMenuBatalla(entrenador2, pokemon2, pokemon1);

            
            if (pokemon1.getVida() <= 0) break;
        }

       
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
                Pokemon nuevoPokemon = entrenador.PrepararBatalla();
                if (nuevoPokemon != null) {
                    pokemonAtacante = nuevoPokemon;
                }
                break;
            case 3:
                
                System.out.println("Aún no se implementan objetos.");
                break;
            default:
                System.out.println("Opcion no valida, intenta de nuevo.");
                mostrarMenuBatalla(entrenador, pokemonAtacante, pokemonDefensor);
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
