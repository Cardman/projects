package cards.network.common.before;
import cards.network.common.PlayerAction;


public abstract class PlayerActionBeforeGame extends PlayerAction {

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}
