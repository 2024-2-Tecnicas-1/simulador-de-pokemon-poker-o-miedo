package simulador;

import simulador.entrenador.Entrenador;
import simulador.pokemon.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista de entrenadores
        List<Entrenador> entrenadores = new ArrayList<>();

        // Bienvenida
        System.out.println("¡Bienvenido al Simulador de Pokémon!");

        boolean salir = false;

        while (!salir) {
            // Menú principal
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear Entrenador");
            System.out.println("2. Agregar Pokémon a un Entrenador");
            System.out.println("3. Mostrar Entrenadores y sus Pokémon");
            System.out.println("4. Iniciar Batalla");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Crear un nuevo entrenador
                    System.out.print("Introduce el nombre del entrenador: ");
                    String nombreEntrenador = scanner.nextLine();
                    Entrenador nuevoEntrenador = new Entrenador(nombreEntrenador) {};
                    entrenadores.add(nuevoEntrenador);
                    System.out.println("Entrenador " + nombreEntrenador + " creado.");
                    break;

                case 2:
                    // Agregar un Pokémon a un entrenador existente
                    if (entrenadores.isEmpty()) {
                        System.out.println("Primero debes crear al menos un entrenador.");
                        break;
                    }

                    System.out.println("Selecciona un entrenador:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadores.get(i).getNombre());
                    }

                    int seleccionEntrenador = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (seleccionEntrenador < 1 || seleccionEntrenador > entrenadores.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    Entrenador entrenadorSeleccionado = entrenadores.get(seleccionEntrenador - 1);

                    System.out.print("Introduce el nombre del Pokémon: ");
                    String nombrePokemon = scanner.nextLine();
                    System.out.print("Introduce la vida del Pokémon: ");
                    int vidaPokemon = scanner.nextInt();
                    System.out.print("Introduce el ataque del Pokémon: ");
                    double ataquePokemon = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar buffer

                    System.out.println("Selecciona el tipo del Pokémon:");
                    TipoPokemon[] tipos = TipoPokemon.values();
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println((i + 1) + ". " + tipos[i].getNombre());
                    }

                    int seleccionTipo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (seleccionTipo < 1 || seleccionTipo > tipos.length) {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    TipoPokemon tipoPokemon = tipos[seleccionTipo - 1];

                    Pokemon nuevoPokemon = new Pokemon(nombrePokemon, vidaPokemon, ataquePokemon, tipoPokemon, Estado.normal) {};
                    entrenadorSeleccionado.Agregarpokemon(nuevoPokemon);

                    System.out.println("¡Pokémon " + nombrePokemon + " agregado a " + entrenadorSeleccionado.getNombre() + "!");
                    break;

                case 3:
                    // Mostrar entrenadores y sus Pokémon
                    if (entrenadores.isEmpty()) {
                        System.out.println("No hay entrenadores creados.");
                    } else {
                        for (Entrenador entrenador : entrenadores) {
                            entrenador.mostrarpokemones();
                        }
                    }
                    break;

                case 4:
                    // Iniciar batalla entre entrenadores
                    if (entrenadores.size() < 2) {
                        System.out.println("Se necesitan al menos dos entrenadores para una batalla.");
                        break;
                    }

                    System.out.println("Selecciona el primer entrenador:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadores.get(i).getNombre());
                    }

                    int indiceEntrenador1 = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    System.out.println("Selecciona el segundo entrenador:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadores.get(i).getNombre());
                    }

                    int indiceEntrenador2 = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (indiceEntrenador1 == indiceEntrenador2 || 
                        indiceEntrenador1 < 1 || indiceEntrenador1 > entrenadores.size() || 
                        indiceEntrenador2 < 1 || indiceEntrenador2 > entrenadores.size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    Entrenador entrenador1 = entrenadores.get(indiceEntrenador1 - 1);
                    Entrenador entrenador2 = entrenadores.get(indiceEntrenador2 - 1);

                    Batalla batalla = new Batalla();
                    batalla.iniciarBatalla(entrenador1, entrenador2);
                    break;

                case 5:
                    // Salir del programa
                    System.out.println("¡Gracias por jugar! Hasta la próxima.");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }
}
