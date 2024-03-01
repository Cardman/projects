package cards.gui.animations;

import cards.gui.containers.ContainerTarot;
import cards.gui.labels.GraphicCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandTarot implements Runnable {

    private final ContainerTarot container;

    private final HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandTarot(ContainerTarot _container, HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        /*On place les cartes de l'utilisateur*/
        for (GraphicCard<CardTarot> c: ContainerTarot.getGraphicCards(container.getWindow(),lg_,hand.getCards())) {
            panneau1_.add(c.getPaintableLabel());
        }
        panneau1_.validate();
    }
}
