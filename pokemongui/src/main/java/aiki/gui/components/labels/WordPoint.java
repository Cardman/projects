package aiki.gui.components.labels;
import code.util.PairNumber;

public final class WordPoint {

    private final String word;

    private final PairNumber<Integer,Integer> point;

    public WordPoint(String _word, PairNumber<Integer, Integer> _point) {
        word = _word;
        point = _point;
    }

    public String getWord() {
        return word;
    }

    public PairNumber<Integer, Integer> getPoint() {
        return point;
    }
}
