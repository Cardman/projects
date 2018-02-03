package cards.president.beans;
import code.util.Numbers;

final class LineDeal {

    private int number;

    private Numbers<Long> scores;

    int getNumber() {
        return number;
    }

    void setNumber(int _number) {
        number = _number;
    }

    Numbers<Long> getScores() {
        return scores;
    }

    void setScores(Numbers<Long> _scores) {
        scores = _scores;
    }

}
