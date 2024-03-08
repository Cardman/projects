package cards.gui.animations;

import cards.gui.containers.ContainerSin;
import cards.gui.containers.ContainerSingleImpl;
import code.gui.MenuItemUtils;

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
        MenuItemUtils.setEnabledMenu(container.window().getPause(),true);
    }
}
