package moves;
import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected java.lang.String describe() {
        return "использует Facade";
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        Status def_pok_status = def.getCondition();
        if (def_pok_status.equals(Status.BURN) | def_pok_status.equals(Status.PARALYZE) | def_pok_status.equals(Status.POISON)) {
            super.applyOppDamage(def, damage*2);
        }
        else {
            super.applyOppDamage(def, damage);
        }
    }
}
