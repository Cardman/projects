package aiki.network.stream;
import code.util.annot.RwXml;

@RwXml
public class Ready extends PlayerActionBeforeGame {

    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean _ready) {
        ready = _ready;
    }
}
