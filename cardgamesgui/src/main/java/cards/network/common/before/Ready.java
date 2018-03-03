package cards.network.common.before;
import code.util.annot.RwXml;

@RwXml
public final class Ready extends PlayerActionBeforeGame {

    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean _ready) {
        ready = _ready;
    }
}
