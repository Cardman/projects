package cards.gui.animations;

import cards.gui.containers.ContainerSin;
import cards.gui.containers.ContainerSingleImpl;
import code.gui.MenuItemUtils;

/**Thread safe class*/
public final class ChangingPause implements Runnable {

    private final ContainerSin container;

    private final boolean enabled;

    public ChangingPause(ContainerSin _container, boolean _enabled) {
        container = _container;
        enabled = _enabled;
    }

    @Override
    public void run() {
        container.getAnimated().set(ContainerSingleImpl.PAUSE_ALIVE);
        MenuItemUtils.setEnabledMenu(container.window().getPause(),enabled);
    }
}
