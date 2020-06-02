package aiki.gui.components.labels;
import code.images.IntPoint;

public final class WordPoint {

    private final String word;

    private final IntPoint point;

    public WordPoint(String _word, IntPoint _point) {
        word = _word;
        point = _point;
    }

    public String getWord() {
        return word;
    }

    public IntPoint getPoint() {
        return point;
    }
}
