package pokemons;

import moves.Focus_Blast;
import ru.ifmo.se.pokemon.Type;

public class Ledian extends Ledyba {
    public Ledian(String name, int level) {
        super(name,level);
        setType(Type.BUG, Type.FLYING);
        setStats(55, 35, 50, 55, 110, 85);
        addMove(new Focus_Blast());
    }
}