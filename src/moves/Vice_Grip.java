package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Vice_Grip extends PhysicalMove {
    public Vice_Grip() {
        super(Type.NORMAL, 55, 100);
    }

    @Override
    protected java.lang.String describe() {
        return "использует Vice Grip";
    }
}
