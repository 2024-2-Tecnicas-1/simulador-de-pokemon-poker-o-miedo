package simulador.pokemon;

public class TipoPokemon {
    
    public static final TipoPokemon FUEGO = new TipoPokemon("Fuego");
    public static final TipoPokemon AGUA = new TipoPokemon("Agua");
    public static final TipoPokemon PLANTAVENENO = new TipoPokemon("Planta/Veneno");
    public static final TipoPokemon ELECTRICO = new TipoPokemon("Eléctrico");
    public static final TipoPokemon PLANTAPSIQUICO = new TipoPokemon("Planta/Psíquico");
    public static final TipoPokemon TIERRAROCA = new TipoPokemon("Tierra/Roca");
    public static final TipoPokemon VOLADORVENENO = new TipoPokemon("Volador/Veneno");
    public static final TipoPokemon LUCHA = new TipoPokemon("Lucha");
    public static final TipoPokemon AGUAVENENO = new TipoPokemon("Agua/Veneno");
    public static final TipoPokemon NORMALVOLADOR = new TipoPokemon("Normal/Volador");

    private String nombre;

    private TipoPokemon(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static double obtenerMultiplicadorDeDaño(TipoPokemon atacante, TipoPokemon defensor) {
        if (atacante == FUEGO) {
            if (defensor == PLANTAVENENO) return 2.0; // Fuego > Planta/Veneno
            if (defensor == AGUA) return 0.5;          // Fuego < Agua
        } else if (atacante == AGUA) {
            if (defensor == FUEGO) return 2.0;         // Agua > Fuego
            if (defensor == PLANTAVENENO) return 0.5;  // Agua < Planta/Veneno
        } else if (atacante == PLANTAVENENO) {
            if (defensor == AGUA) return 2.0;          // Planta/Veneno > Agua
            if (defensor == FUEGO) return 0.5;         // Planta/Veneno < Fuego
        } else if (atacante == ELECTRICO) {
            if (defensor == AGUAVENENO) return 2.0;    // Eléctrico > Agua/Veneno
            if (defensor == TIERRAROCA) return 0.0;    // Eléctrico no afecta a Tierra/Roca
        } else if (atacante == TIERRAROCA) {
            if (defensor == ELECTRICO) return 2.0;     // Tierra/Roca > Eléctrico
            if (defensor == AGUA) return 0.5;          // Tierra/Roca < Agua
        } else if (atacante == VOLADORVENENO) {
            if (defensor == LUCHA) return 2.0;         // Volador/Veneno > Lucha
            if (defensor == TIERRAROCA) return 0.5;    // Volador/Veneno < Tierra/Roca
        } else if (atacante == LUCHA) {
            if (defensor == NORMALVOLADOR) return 2.0; // Lucha > Normal/Volador
        }

        return 1.0; 
    }
}