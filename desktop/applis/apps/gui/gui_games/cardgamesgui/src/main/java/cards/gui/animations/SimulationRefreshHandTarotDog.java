package cards.gui.animations;
import cards.gui.containers.ContainerSimuTarot;
import cards.gui.containers.ContainerTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandTarotDog implements Runnable {

    private ContainerSimuTarot container;

    private HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandTarotDog(ContainerSimuTarot _container,
            HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    static void updateCardsInPanelTarotDog(ContainerSimuTarot _s, AbsPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        TranslationsLg lg_ = _s.getOwner().getFrames().currentLg();
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(_s.getWindow(),lg_,_hand.getCards())) {
            _panel.add(c.getPaintableLabel());
        }
        _panel.validate();
    }

    @Override
    public void run() {
        updateCardsInPanelTarotDog(container, container.tapisTarot().getCenterDeck(), hand);
    }
}
