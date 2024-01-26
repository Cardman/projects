package cards.gui.animations;
import cards.gui.containers.ContainerSimuTarot;
import cards.tarot.HandTarot;
import code.sml.util.TranslationsLg;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationDiscardTarot implements Runnable {

    private ContainerSimuTarot container;

    private HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationDiscardTarot(ContainerSimuTarot _container, HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisTarot().setEcart(lg_,hand, container.getOwner().getCompoFactory());
    }
}
