package simulador;

import simulador.entrenador.Entrenador;
import simulador.pokemon.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        List<Entrenador> entrenadores = new ArrayList<>();

        // Bienvenida
        System.out.println("Bienvenido al Simulador de Pokemon");

        boolean salir = false;

        while (!salir) {
          
            System.out.println("\n Menu Principal ");
            System.out.println("1. Crear Entrenador");
            System.out.println("2. Agregar Pokemon a un Entrenador");
            System.out.println("3. registrar pokemon");
            System.out.println("4. Mostrar Entrenadores y sus Pokemon");
            System.out.println("5. Iniciar Batalla");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    
                    System.out.print("Introduce el nombre del entrenador: ");
                    String nombreEntrenador = scanner.nextLine();
                    Entrenador nuevoEntrenador = new Entrenador(nombreEntrenador) {
                    }; // Instancia abstracta con clase anónima
                    entrenadores.add(nuevoEntrenador);
                    System.out.println("Entrenador " + nombreEntrenador + " creado.");
                    break;

                case 2:
                    
                    if (entrenadores.isEmpty()) {
                        System.out.println("No hay entrenadores creados.");
                        break;
                    }
                    System.out.println("Selecciona un entrenador:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadores.get(i).getNombre());
                    }
                    int indiceEntrenador = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpiar buffer

                    if (indiceEntrenador < 0 || indiceEntrenador >= entrenadores.size()) {
                        System.out.println("Entrenador no valido.");
                        break;
                    }

                    Entrenador entrenadorSeleccionado = entrenadores.get(indiceEntrenador);

                    // Menú para seleccionar qué Pokémon agregar
                    System.out.println("\nSelecciona un Pokemon para agregar:");
                    System.out.println("1. Bellsprout");
                    System.out.println("2. Farfetch'd");
                    System.out.println("3. Growlithe");
                    System.out.println("4. Hitmonlee");
                    System.out.println("5. Onix");
                    System.out.println("6. Psyduck");
                    System.out.println("7. Tentacool");
                    System.out.println("8. Voltorb");
                    System.out.println("9. Zubat");
                    System.out.println("10. Exeggcute");

                    int opcionPokemon = scanner.nextInt();
                    scanner.nextLine(); 

                    Pokemon pokemonSeleccionado = null;
                    switch (opcionPokemon) {
                        case 1:
                            pokemonSeleccionado = new Bellsprout();
                            break;
                        case 2:
                            pokemonSeleccionado = new Farfetchd();
                            break;
                        case 3:
                            pokemonSeleccionado = new Growlithe();
                            break;
                        case 4:
                            pokemonSeleccionado = new Hitmonlee();
                            break;
                        case 5:
                            pokemonSeleccionado = new Onix();
                            break;
                        case 6:
                            pokemonSeleccionado = new Psyduck();
                            break;
                        case 7:
                            pokemonSeleccionado = new Tentacool();
                            break;
                        case 8:
                            pokemonSeleccionado = new Voltorb();
                            break;
                        case 9:
                            pokemonSeleccionado = new Zubat();
                            break;
                        case 10:
                            pokemonSeleccionado = new Exeggcute();
                            break;
                        default:
                            System.out.println("Selección invalida.");
                            break;
                    }

                    if (pokemonSeleccionado != null) {
                        entrenadorSeleccionado.AgregarPokemon(pokemonSeleccionado);
                    }
                    break;

                case 3:
                    
                    if (entrenadores.isEmpty()) {
                        System.out.println("No hay entrenadores creados.");
                    } else {
                        for (Entrenador entrenador : entrenadores) {
                            entrenador.MostrarPokemones();
                        }
                    }
                    break;

                case 4:
                    
                    if (entrenadores.size() < 2) {
                        System.out.println("Se necesitan al menos dos entrenadores para una batalla.");
                        break;
                    }

                    System.out.println("Selecciona el primer entrenador:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadores.get(i).getNombre());
                    }

                    int indiceEntrenador1 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Selecciona el segundo entrenador:");
                    for (int i = 0; i < entrenadores.size(); i++) {
                        System.out.println((i + 1) + ". " + entrenadores.get(i).getNombre());
                    }

                    int indiceEntrenador2 = scanner.nextInt();
                    scanner.nextLine();

                    if (indiceEntrenador1 == indiceEntrenador2
                            || indiceEntrenador1 < 1 || indiceEntrenador1 > entrenadores.size()
                            || indiceEntrenador2 < 1 || indiceEntrenador2 > entrenadores.size()) {
                        System.out.println("Seleccion de entrenadores invalida.");
                    } else {
                        Entrenador entrenador1 = entrenadores.get(indiceEntrenador1 - 1);
                        Entrenador entrenador2 = entrenadores.get(indiceEntrenador2 - 1);

                       
                        Batalla batalla = new Batalla();
                        batalla.iniciarBatalla(entrenador1, entrenador2);
                    }
                    break;

                case 5:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción invalida. Intenta de nuevo.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Hasta luego");
    }
}
