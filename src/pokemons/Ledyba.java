package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Ledyba extends Pokemon {
    public Ledyba(String name, int level) {
        super(name, level);
        setStats(40, 20, 30, 40, 80, 55);
        setMove(new Dream_Eater(), new Dazzling_Gleam(), new Facade());
    }
}