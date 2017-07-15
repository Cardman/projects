package cards.gui.animations;
import cards.gui.containers.ContainerGame;

/**Thread safe class*/
public final class AddTextEvents extends Thread {

    private ContainerGame container;

    private String event;

    public AddTextEvents(ContainerGame _container, String _event) {
        container = _container;
        event = _event;
    }

    @Override
    public void run() {
        container.ajouterTexteDansZone(event);
    }
}
