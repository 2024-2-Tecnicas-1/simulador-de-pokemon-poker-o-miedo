package simulador.pokemon;

import simulador.entrenador.Entrenador;

public class Batalla {

    public void iniciarBatalla(Entrenador entrenador1, Entrenador entrenador2) {
        System.out.println("Que comience el visaje entre " + entrenador1.getNombre() + " y " + entrenador2.getNombre());
        
        Pokemon pokemon1 = entrenador1.prepararBatalla();
        Pokemon pokemon2 = entrenador2.prepararBatalla();

        if (pokemon1 == null || pokemon2 == null) {
            System.out.println("El visaje no puede continuar. Uno de los entrenadores esta de visajoso y no tiene un Pokemon valido.");
            return;
        }

        while (pokemon1.getVida() > 0 && pokemon2.getVida() > 0) {
            // Pokémon 1 ataca
            System.out.println("\nTurno de " + pokemon1.getNombre());
            realizarAtaque(pokemon1, pokemon2);
            if (pokemon2.getVida() <= 0) break;

            // Pokémon 2 ataca
            System.out.println("\nTurno de " + pokemon2.getNombre());
            realizarAtaque(pokemon2, pokemon1);
        }

        // Determinar el ganador
        if (pokemon1.getVida() > 0) {
            System.out.println("\n" + pokemon1.getNombre() + " de " + entrenador1.getNombre() + " el dio una tunda al otro");
        } else {
            System.out.println("\n" + pokemon2.getNombre() + " de " + entrenador2.getNombre() + " lo chuzo y lo dejo tieso");
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
