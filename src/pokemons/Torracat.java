package pokemons;

import moves.Bulldoze;

public class Torracat extends Litten {
    public Torracat(String name, int level) {
        super(name, level);
        setStats(65, 85, 50, 80, 50, 90);
        addMove(new Bulldoze());
    }


}