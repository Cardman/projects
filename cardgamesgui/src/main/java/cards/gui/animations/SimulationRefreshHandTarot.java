package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.HandTarot;
import code.gui.Panel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandTarot implements Runnable {

    private ContainerGame container;

    private HandTarot hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandTarot(ContainerGame _container, HandTarot _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        String lg_ = container.getOwner().getLanguageKey();
        /*On place les cartes de l'utilisateur*/
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_,hand)) {
            panneau1_.add(c);
        }
        panneau1_.repaintChildren();
        panneau1_.validate();
    }
}
