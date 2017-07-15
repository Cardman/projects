package cards.gui.animations;
import cards.gui.containers.ContainerGame;

/**Thread safe class*/
public final class ChangingPause extends Thread {

    private ContainerGame container;

    private boolean enabled;

    public ChangingPause(ContainerGame _container, boolean _enabled) {
        container = _container;
        enabled = _enabled;
    }

    @Override
    public void run() {
        container.getPause().setEnabled(enabled);
    }
}
