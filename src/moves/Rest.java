package moves;
import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC,0,0);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon){
        Effect e = new Effect().turns(2).condition(Status.SLEEP);
        pokemon.addEffect(e);
        pokemon.setMod(Stat.HP, (int) pokemon.getStat(Stat.HP));
    }

    @Override
    protected java.lang.String describe() {
        return "использует Rest";
    }
}
