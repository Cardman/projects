package cards.tarot.enumerations;

import code.util.IdList;

/**Miseres utilisees au tarot*/
public enum Miseres {
    TRUMP(1,10, "0"),POINT(2,10, "1"),CHARACTER(3,5, "2"),SUIT(4,30, "3"),LOW_CARDS(5,20, "4");
    private final int order;
    private final int points;
    private final String st;
    Miseres(int _o, int _points, String _s){
        order = _o;
        points = _points;
        st = _s;
    }

    public String getSt() {
        return st;
    }

    public int getOrder() {
        return order;
    }

    public int getPoints(){
        return points;
    }

    public static IdList<Miseres> all() {
        IdList<Miseres> all_ = new IdList<Miseres>();
        all_.add(TRUMP);
        all_.add(POINT);
        all_.add(CHARACTER);
        all_.add(SUIT);
        all_.add(LOW_CARDS);
        return all_;
    }

}
