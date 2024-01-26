package cards.gui.animations;
import cards.belote.HandBelote;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerSimuBelote;
import cards.gui.labels.GraphicBeloteCard;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandBelote implements Runnable {

    private ContainerSimuBelote container;

    private HandBelote hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandBelote(ContainerSimuBelote _container, HandBelote _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        /*On place les cartes de l'utilisateur*/
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(container.getWindow(),lg_,hand.getCards())) {
            panneau1_.add(c.getPaintableLabel());
        }
        panneau1_.validate();
    }
}
