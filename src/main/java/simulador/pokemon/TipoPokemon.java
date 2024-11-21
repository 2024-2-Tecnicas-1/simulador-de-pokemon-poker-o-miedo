package simulador.pokemon;

public enum TipoPokemon {
    FUEGO("Fuego"),
    AGUA("Agua"),
    PLANTAVENENO("Planta/Veneno"),
    ELECTRICO("Eléctrico"),
    PLANTAPSIQUICO("Planta/Psíquico"),
    TIERRAROCA("Tierra/Roca"),
    VOLADORVENENO("Volador/Veneno"),
    LUCHA("Lucha"),
    AGUAVENENO("Agua/Veneno"),
    NORMALVOLADOR("Normal/Volador");

    private final String nombre;

    TipoPokemon(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static double obtenerMultiplicadorDeDaño(TipoPokemon atacante, TipoPokemon defensor) {
        switch (atacante) {
            case FUEGO:
                if (defensor == PLANTAVENENO) return 2.0;
                if (defensor == AGUA) return 0.5;
                break;
            case AGUA:
                if (defensor == FUEGO) return 2.0;
                if (defensor == PLANTAVENENO) return 0.5;
                break;
            case PLANTAVENENO:
                if (defensor == AGUA) return 2.0;
                if (defensor == FUEGO) return 0.5;
                break;
            case ELECTRICO:
                if (defensor == AGUAVENENO) return 2.0;
                if (defensor == TIERRAROCA) return 0.0;
                break;
            case TIERRAROCA:
                if (defensor == ELECTRICO) return 2.0;
                if (defensor == AGUA) return 0.5;
                break;
            case VOLADORVENENO:
                if (defensor == LUCHA) return 2.0;
                if (defensor == TIERRAROCA) return 0.5;
                break;
            case LUCHA:
                if (defensor == NORMALVOLADOR) return 2.0;
                break;
        }
        return 1.0;
    }
}
