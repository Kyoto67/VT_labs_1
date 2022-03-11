package pokemons;

import moves.Facade;
import moves.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Litten extends Pokemon {
    public Litten(String name, int level) {
        super(name,level);
        setStats(45, 65, 40, 60, 40, 70);
        setType((Type.FIRE));
        setMove(new Rest(), new Facade());
    }
}