package cards.belote.beans;
import code.util.Longs;

final class BeloteLineDeal {

    private int number;

    private Longs scores;

    int getNumber() {
        return number;
    }

    void setNumber(int _number) {
        number = _number;
    }

    Longs getScores() {
        return scores;
    }

    void setScores(Longs _scores) {
        scores = _scores;
    }

}
