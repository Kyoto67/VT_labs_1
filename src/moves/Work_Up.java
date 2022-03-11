package moves;

import ru.ifmo.se.pokemon.*;

public class Work_Up extends StatusMove {
    public Work_Up() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.ATTACK, +1);
        pokemon.setMod(Stat.SPECIAL_ATTACK, +1);
    }

    @Override
    protected java.lang.String describe() {
        return "использует Work Up";
    }
}
