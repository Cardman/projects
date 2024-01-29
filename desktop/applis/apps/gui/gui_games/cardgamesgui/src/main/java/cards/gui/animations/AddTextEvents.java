package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import code.gui.AbsTextArea;

/**Thread safe class*/
public final class AddTextEvents implements Runnable {

    private String event;
    private AbsTextArea events;

    public AddTextEvents(ContainerGame _container, String _event) {
        event = _event;
        events = _container.getEvents();
    }

    @Override
    public void run() {
        events.append(event);
    }
}
