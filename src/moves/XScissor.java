package moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class XScissor extends PhysicalMove {
    public XScissor() {
        super(Type.BUG, 80, 100);
    }

    @Override
    protected java.lang.String describe() {
        return "использует X-Scissor";
    }
}
