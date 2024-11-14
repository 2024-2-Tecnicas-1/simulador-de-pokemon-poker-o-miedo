package simulador.entrenador;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import simulador.pokemon.Pokemon;

public abstract class Entrenador {
    //atributos del personaje
    public String nombre;
    public List <Pokemon> Pokemones; 
    
    //constructor 
    public Entrenador(String nombre){
        this.nombre=nombre;
        this.Pokemones= new ArrayList<>();
    }
    //el metodo para capturar un pokemon
    public void Agregarpokemon(Pokemon Pokemon){
        Pokemones.add (Pokemon);
        System.out.println(Pokemon.getNombre()+"se añadio al equipo"+nombre);
        
    }
    //el metodo para el entrenamiento de los pokemones
    public void Entrenarpokemon (Pokemon Pokemon){
        if (Pokemones.contains(Pokemon)){
            int nuevaVida=(int) (Pokemon.getVida()*1.1);
            Pokemon.setVida(nuevaVida);
            System.out.println(Pokemon.getVida() +" a entrenado mucho, ahora su vida a incrementado a "+ nuevaVida);
        }else 
            System.out.println(Pokemon.getNombre() +" no se encuentra en el equipo "+ nombre);
        
        if (Pokemones.contains(Pokemon)){
            double nuevoPataque=Pokemon.getPataque()*1.1;
            Pokemon.setPataque(nuevoPataque);
            System.out.println(Pokemon.getNombre()+" a entrenado mucho, ahora su poder es de "+nuevoPataque);
        }else {
            System.out.println(Pokemon.getNombre() +" no se encuentra en el equipo "+ nombre);
        }
    }
    //el metodo para que muestre lo pokemones actuales o accesibles del equipo
    public void mostrarpokemones(){
        System.out.println("Pokemones de "+nombre+" :");
        for (Pokemon Pokemon : Pokemones){
                System.out.println("- " +Pokemon.getNombre()+" (Vida: "+Pokemon.getVida() +" Ataque "+ Pokemon.getPataque() +" )");
        }
    }
    //el metodo para preparar la batalla
    public Pokemon prepararBatalla() {
    if (Pokemones.isEmpty()) {   
        System.out.println(nombre + " no tiene Pokémon para la batalla");
        return null;
    }
    
    System.out.println(nombre + " selecciona un Pokémon");
    mostrarpokemones();
    
    Scanner sc = new Scanner(System.in); 
    int seleccion = sc.nextInt();
    
    if (seleccion < 1 || seleccion > Pokemones.size()) {
        System.out.println("Selección inválida");
        return null;
    }
    Pokemon pokemonSeleccionado = Pokemones.get(seleccion - 1);
    System.out.println(pokemonSeleccionado.getNombre() + " seleccionado para la batalla");
    return pokemonSeleccionado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPokemones(List<Pokemon> Pokemones) {
        this.Pokemones = Pokemones;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getPokemones() {
        return Pokemones;
    }
    
    //metodo para iniciar la batalla
    public void participarbatalla(Entrenador oponente){
        System.out.println(nombre +" va a pelear contra "+ oponente.getNombre());
    }
}
