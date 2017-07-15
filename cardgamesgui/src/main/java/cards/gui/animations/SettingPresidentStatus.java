package cards.gui.animations;
import code.util.NumberMap;
import cards.gui.containers.ContainerPresident;
import cards.president.enumerations.Playing;

/**Thread safe class*/
public final class SettingPresidentStatus extends Thread {

    private ContainerPresident container;

    private NumberMap<Byte,Playing> status;

    private byte nextPlayer;

    public SettingPresidentStatus(ContainerPresident _container,
            NumberMap<Byte,Playing> _status, byte _nextPlayer) {
        container = _container;
        status = _status;
        nextPlayer = _nextPlayer;
    }

    @Override
    public void run() {
        container.tapisPresident().setStatus(status, nextPlayer);
    }
}
