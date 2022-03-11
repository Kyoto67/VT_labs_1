package moves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Dazzling_Gleam extends SpecialMove {
    public Dazzling_Gleam() {
        super(Type.FAIRY, 80, 100);
    }

    @Override
    protected String describe () {
        return "использует Dazzling Gleam";
    }
}
