package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import code.gui.MenuItemUtils;

/**Thread safe class*/
public final class ChangingPause implements Runnable {

    private ContainerGame container;

    private boolean enabled;

    public ChangingPause(ContainerGame _container, boolean _enabled) {
        container = _container;
        enabled = _enabled;
    }

    @Override
    public void run() {
        MenuItemUtils.setEnabledMenu(container.getPause(),enabled);
    }
}
