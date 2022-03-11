package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Double_Hit extends PhysicalMove {
    public Double_Hit() {
        super(Type.NORMAL, 35, 90, 0, 2);
    }

    @Override
    protected String describe() {
        return "использует Double Hit";
    }
}
