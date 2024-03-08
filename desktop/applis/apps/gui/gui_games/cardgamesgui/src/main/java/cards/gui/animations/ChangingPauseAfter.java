package cards.gui.animations;

import cards.gui.containers.ContainerSin;
import cards.gui.containers.ContainerSingleImpl;

public final class ChangingPauseAfter implements Runnable {

    private final ContainerSin container;
    private final CardAnimState state;

    public ChangingPauseAfter(ContainerSin _container, CardAnimState _s) {
        container = _container;
        state = _s;
    }

    @Override
    public void run() {
        container.setState(state);
        container.window().changeStreamsMenusEnabled(true);
        container.getAnimated().set(ContainerSingleImpl.PAUSE_STOPPED);
        container.window().getPause().setEnabled(true);
    }
}
