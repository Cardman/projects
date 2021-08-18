package aiki.network.stream;


public final class ReadyAiki extends PlayerActionBeforeGameAiki {

    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean _ready) {
        ready = _ready;
    }
}
