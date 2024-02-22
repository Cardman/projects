package cards.gui.containers;

public final class DirectCardsCallEvents implements IntCardsCallEvents {

    @Override
    public void call(Runnable _call) {
        _call.run();
    }
}
