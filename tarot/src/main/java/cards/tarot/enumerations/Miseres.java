package cards.tarot.enumerations;

/**Miseres utilisees au tarot*/
public enum Miseres {
    TRUMP(10),POINT(10),CHARACTER(5),SUIT(30),LOW_CARDS(20);
    private final int points;
    Miseres(int _points){
        points = _points;
    }
    public int getPoints(){
        return points;
    }

}
