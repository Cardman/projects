package cards.gui.animations;

import cards.gui.containers.ContainerSin;
import cards.gui.containers.ContainerSingleImpl;

/**Thread safe class*/
public final class ChangingPause implements Runnable {

    private final ContainerSin container;

    public ChangingPause(ContainerSin _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setState(CardAnimState.PAUSE);
        container.getAnimated().set(ContainerSingleImpl.PAUSE_ALIVE);
        container.window().getPause().setEnabled(true);
    }
}
