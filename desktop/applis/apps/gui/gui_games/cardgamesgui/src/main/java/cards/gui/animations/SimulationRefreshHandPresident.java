package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.HandPresident;
import code.gui.AbsPanel;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandPresident implements Runnable {

    private ContainerGame container;

    private HandPresident hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandPresident(ContainerGame _container, HandPresident _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        String lg_ = container.getOwner().getLanguageKey();
        /*On place les cartes de l'utilisateur*/
        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(container.getWindow(),lg_,hand.getCards())) {
            panneau1_.add(c);
        }
        panneau1_.repaintChildren(container.getOwner().getImageFactory());
        panneau1_.validate();
    }
}
