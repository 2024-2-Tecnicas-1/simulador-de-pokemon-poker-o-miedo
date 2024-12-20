package simulador.entrenador;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import simulador.pokemon.Pokemon;
import simulador.objetos.Pocion;
import simulador.objetos.Elixir;

public abstract class Entrenador {
    private String nombre;
    private List<Pokemon> Pokemones;
    private List<Object> objetos;  

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.Pokemones = new ArrayList<>();
        this.objetos = new ArrayList<>();
        objetos.add(new Pocion());
        objetos.add(new Elixir());
    }

    public void AgregarPokemon(Pokemon pokemon) {
        Pokemones.add(pokemon);
        System.out.println(pokemon.getNombre() + " se anadio al equipo de " + nombre);
    }

    public void RegistrarNuevoPokemon() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo Pokemon: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el tipo del Pokemon: ");
        String tipo = sc.nextLine();

        System.out.println("Ingrese la vida inicial del Pokemon: ");
        int vida = sc.nextInt();

        System.out.println("Ingrese el poder de ataque del Pokemon: ");
        double pataque = sc.nextDouble();

        
        Pokemon nuevoPokemon = Pokemon(nombre, vida, pataque, tipo, "Normal");

        
        AgregarPokemon(nuevoPokemon);

        System.out.println("Nuevo Pokemon " + nombre + " registrado y añadido al equipo.");
    }

    
    public void EntrenarPokemon(Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Seleccione lo que desea entrenar en " + pokemon.getNombre() + ":");
        System.out.println("1. Aumentar Vida");
        System.out.println("2. Aumentar Ataque");
        
        int opcion = sc.nextInt();
        
        if (opcion == 1) {
            int nuevaVida = (int) (pokemon.getVida() * 1.1);  
            pokemon.setVida(nuevaVida);
            System.out.println(pokemon.getNombre() + " ha aumentado su vida a " + nuevaVida);
        } else if (opcion == 2) {
            double nuevoPataque = pokemon.getPataque() * 1.1;  
            pokemon.setPataque(nuevoPataque);
            System.out.println(pokemon.getNombre() + " ha aumentado su ataque a " + nuevoPataque);
        } else {
            System.out.println("Opción no válida.");
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

    
    public void RealizarAtaque(Pokemon atacante, Pokemon defensor) {
        double dano = atacante.getPataque();
        double nuevaVida = defensor.getVida() - dano;
        defensor.setVida((int) Math.max(nuevaVida, 0));  
        System.out.println(atacante.getNombre() + " ataca a " + defensor.getNombre() + " causando " + dano + " de dano. Vida restante de " + defensor.getNombre() + ": " + defensor.getVida());
    }

    
    public void UsarObjeto(Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Elige un objeto para usar en " + pokemon.getNombre() + ":");
        System.out.println("1. Pocion (Recupera vida)");
        System.out.println("2. Elixir (Mejora ataque)");

        int opcion = sc.nextInt();

        if (opcion == 1) {
            Pocion pocion = new Pocion();
            pocion.usar(pokemon);
        } else if (opcion == 2) {
            Elixir elixir = new Elixir();
            elixir.usar(pokemon);
        } else {
            System.out.println("Opción no válida.");
        }
    }

    public void ParticiparBatalla(Entrenador oponente) {
        System.out.println(nombre + " va a pelear contra " + oponente.getNombre());

        
        Pokemon miPokemon = this.PrepararBatalla();
        Pokemon pokemonOponente = oponente.PrepararBatalla();

        if (miPokemon == null || pokemonOponente == null) {
            System.out.println("La batalla no pudo comenzar debido a la falta de Pokemon.");
            return;
        }

        
        RealizarAtaque(miPokemon, pokemonOponente);

        
        UsarObjeto(miPokemon);
        UsarObjeto(pokemonOponente);

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

    private Pokemon Pokemon(String nombre, int vida, double pataque, String tipo, String normal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
