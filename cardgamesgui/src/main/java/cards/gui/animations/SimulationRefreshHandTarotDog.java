package cards.gui.animations;
import cards.gui.containers.ContainerTarot;
import cards.tarot.HandTarot;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandTarotDog extends Thread {

    private ContainerTarot container;

    private HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandTarotDog(ContainerTarot _container,
            HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        GoSimulateTarot.updateCardsInPanelTarotDog(container.tapisTarot().getCenterDeck(), hand);
    }
}
