package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Illumise extends Pokemon {
    public Illumise(String name, int level) {
        super(name,level);
    setStats(65, 47, 75, 73, 85, 85);
    setType(Type.BUG);
    setMove(new Vice_Grip(), new XScissor(), new Quick_Attack(), new Double_Hit());
    }
}