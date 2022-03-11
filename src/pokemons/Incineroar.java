package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;


public class Incineroar extends Torracat {
    public Incineroar(String name, int level) {
        super(name,level);
        setStats(
                95, 115, 90,
                80, 90, 90);
        addType(Type.DARK);
        setMove(new Work_Up());
    }
}
