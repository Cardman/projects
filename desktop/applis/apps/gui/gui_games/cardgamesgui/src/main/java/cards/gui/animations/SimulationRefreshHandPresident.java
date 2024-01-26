package cards.gui.animations;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.HandPresident;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandPresident implements Runnable {

    private ContainerSimuPresident container;

    private HandPresident hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandPresident(ContainerSimuPresident _container, HandPresident _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        /*On place les cartes de l'utilisateur*/
        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(container.getWindow(),lg_,hand.getCards())) {
            panneau1_.add(c.getPaintableLabel());
        }
        panneau1_.validate();
    }
}
