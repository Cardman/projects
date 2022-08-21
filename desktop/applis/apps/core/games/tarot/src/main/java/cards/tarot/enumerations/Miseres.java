package cards.tarot.enumerations;

/**Miseres utilisees au tarot*/
public enum Miseres {
    TRUMP(1,10),POINT(2,10),CHARACTER(3,5),SUIT(4,30),LOW_CARDS(5,20);
    private final int order;
    private final int points;
    Miseres(int _o,int _points){
        order = _o;
        points = _points;
    }

    public int getOrder() {
        return order;
    }

    public int getPoints(){
        return points;
    }

}
