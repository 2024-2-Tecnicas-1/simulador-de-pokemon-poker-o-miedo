package simulador.pokemon;

import java.util.List;

public abstract class Pokemon {

    private String nombre;
    private int vida;
    private double Pataque;
    private TipoPokemon tipos;  
    private Estado estado;

    // Constructor
    public Pokemon(String nombre, int vida, double Pataque,TipoPokemon tipos,Estado estado) {
        this.nombre = nombre;
        this.vida = vida;
        this.Pataque = Pataque;
        this.tipos = tipos;
        this.estado = estado;
    }

    // el metodo que se utiliza para mostrar la info del pokemon
    public void Info() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Vida: " + vida);
        System.out.println("Ataque: " + Pataque);
        System.out.println("Tipos de Pokémon: " + tipos);
        System.out.println("Estado del Pokémon: " + estado);
    }

    // Método para el ataque
    public void atacar(Pokemon oponente) {
        int daño = (int) (oponente.getPataque()-oponente.getVida());
        oponente.recibirDaño(daño);
        System.out.println(this.nombre + " ataca a " + oponente.nombre + " causando " + daño + " de daño.");
    }

    // Método para el daño
    public void recibirDaño(int daño) {
        this.vida -= daño;
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(this.nombre + " anda valiendo miercoles .");
        }
    }

    // Método para el entrenamiento
    public void entrenar() {
        this.vida += 10; // Mejora de la vida
        this.Pataque += 5; // Mejora de los puntos de ataque
        System.out.println(this.nombre + " ha sido entrenado. Vida: " + this.vida + ", Puntos de Ataque: " + this.Pataque);
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public double getPataque() {
        return Pataque;
    }

    public List<TipoPokemon> getTipos() {
        return (List<TipoPokemon>) tipos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setPataque(double Pataque) {
        this.Pataque = Pataque;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


}
