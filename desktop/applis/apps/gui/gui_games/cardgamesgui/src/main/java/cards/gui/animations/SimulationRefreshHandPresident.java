package cards.gui.animations;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.labels.GraphicCard;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import code.gui.AbsPanel;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandPresident implements Runnable {

    private final ContainerSimuPresident container;

    private final HandPresident hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandPresident(ContainerSimuPresident _container, HandPresident _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicCard<CardPresident> c: container.getGraphicCards(hand.getCards())) {
            panneau1_.add(c.getPaintableLabel());
        }
        panneau1_.validate();
    }
}
