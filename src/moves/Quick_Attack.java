package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Quick_Attack extends PhysicalMove {
    public Quick_Attack() {
        super(Type.NORMAL, 40, 100, +1, 1);
    }

    @Override
    protected java.lang.String describe() {
        return "использует Quick Attack";
    }
}
