package cards.gui.animations;
import cards.gui.containers.ContainerPresident;

/**Thread safe class*/
public final class SettingPresidentDeck implements Runnable {

    private ContainerPresident container;

    public SettingPresidentDeck(ContainerPresident _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.tapisPresident().setTalonPresident();
    }
}
