package cards.network.common.before;


public final class Ready extends PlayerActionBeforeGameCards {

    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean _ready) {
        ready = _ready;
    }
}
