package cards.gui.animations;
import cards.gui.containers.ContainerTarot;
import cards.gui.labels.GraphicCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandTarotDog implements Runnable {

    private final ContainerTarot container;

    private final HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandTarotDog(ContainerTarot _container,
            HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    static void updateCardsInPanelTarotDog(ContainerTarot _s, AbsPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        TranslationsLg lg_ = _s.getOwner().getFrames().currentLg();
        for (GraphicCard<CardTarot> c: ContainerTarot.getGraphicCards(_s.getWindow(),lg_,_hand.getCards())) {
            _panel.add(c.getPaintableLabel());
        }
        _panel.validate();
    }

    @Override
    public void run() {
        updateCardsInPanelTarotDog(container, container.tapisTarot().getCenterDeck(), hand);
    }
}
