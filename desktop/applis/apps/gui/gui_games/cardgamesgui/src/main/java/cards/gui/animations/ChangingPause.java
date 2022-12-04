package cards.gui.animations;
import cards.gui.containers.ContainerSingle;
import code.gui.MenuItemUtils;

/**Thread safe class*/
public final class ChangingPause implements Runnable {

    private ContainerSingle container;

    private boolean enabled;

    public ChangingPause(ContainerSingle _container, boolean _enabled) {
        container = _container;
        enabled = _enabled;
    }

    @Override
    public void run() {
        MenuItemUtils.setEnabledMenu(container.window().getPause(),enabled);
    }
}
