package cards.gui.animations;
import cards.gui.containers.ContainerSimuTarot;
import cards.tarot.HandTarot;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandTarotDog extends Thread {

    private ContainerSimuTarot container;

    private HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandTarotDog(ContainerSimuTarot _container,
            HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        GoSimulateTarot.updateCardsInPanelTarotDog(container, container.tapisTarot().getCenterDeck(), hand);
    }
}
