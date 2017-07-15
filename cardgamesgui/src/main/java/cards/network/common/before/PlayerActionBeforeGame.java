package cards.network.common.before;
import code.util.annot.RwXml;
import cards.network.common.PlayerAction;

@RwXml
public class PlayerActionBeforeGame extends PlayerAction {

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}
