import pokemons.*;
import ru.ifmo.se.pokemon.*;

public class var2028 {
    public static void main(String [] args) {
        Battle b = new Battle();
        Incineroar p1 = new Incineroar("", 3);
        Illumise p2 = new Illumise("", 1);
        Ledian p3 = new Ledian("", 2);
        Ledyba p4 = new Ledyba("", 1);
        Litten p5 = new Litten("", 1);
        Torracat p6 = new Torracat("", 2);
        b.addAlly(p1);
        b.addAlly(p3);
        b.addAlly(p5);
        b.addFoe(p2);
        b.addFoe(p4);
        b.addFoe(p6);
        b.go();
    }
}
