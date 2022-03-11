package moves;

import ru.ifmo.se.pokemon.*;

public class Dream_Eater extends SpecialMove {
    double last_damage;
    public Dream_Eater() {
        super(Type.PSYCHIC, 100, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        last_damage = damage;
        Status def_poke_stat = def.getCondition();
        if (def_poke_stat.equals(Status.SLEEP)) {
            super.applyOppDamage(def, damage);
        } else {
            super.applyOppDamage(def, 0);
        }
        System.out.println(def.getStat(Stat.HP));
    }

    @Override
    protected void applySelfDamage(Pokemon p, double damage) {
        double damage1=this.last_damage;
        super.applySelfDamage(p, damage1/(-2));
    }

    @Override
    protected String describe() {
            return "использует Dream Eater";
    }
}
