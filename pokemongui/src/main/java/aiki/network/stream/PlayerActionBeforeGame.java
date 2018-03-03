package aiki.network.stream;
import code.util.annot.RwXml;

@RwXml
public abstract class PlayerActionBeforeGame {

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}
