package cards.gui.animations;
import cards.belote.HandBelote;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.labels.GraphicBeloteCard;
import code.gui.Panel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandBelote implements Runnable {

    private ContainerGame container;

    private HandBelote hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandBelote(ContainerGame _container, HandBelote _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        String lg_ = container.getOwner().getLanguageKey();
        /*On place les cartes de l'utilisateur*/
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(container.getWindow().getImageFactory(),lg_,hand)) {
            panneau1_.add(c);
        }
        panneau1_.repaintChildren(container.getOwner().getImageFactory());
        panneau1_.validate();
    }
}
