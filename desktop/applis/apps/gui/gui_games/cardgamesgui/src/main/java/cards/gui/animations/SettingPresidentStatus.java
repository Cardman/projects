package cards.gui.animations;
import cards.gui.containers.ContainerPresident;
import cards.president.enumerations.Playing;
import code.sml.util.TranslationsLg;
import code.util.*;

/**Thread safe class*/
public final class SettingPresidentStatus implements Runnable {

    private final ContainerPresident container;

    private final IntMap<Playing> status;

    private final int nextPlayer;

    public SettingPresidentStatus(ContainerPresident _container,
            IntMap<Playing> _status, int _nextPlayer) {
        container = _container;
        status = _status;
        nextPlayer = _nextPlayer;
    }

    @Override
    public void run() {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisPresident().setStatus(container.getOwner().getImageFactory(), lg_,status, nextPlayer);
        container.pack();
    }
}
