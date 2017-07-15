package cards.gui.animations;
import javax.swing.JPanel;

import cards.belote.HandBelote;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.labels.GraphicBeloteCard;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandBelote extends Thread {

    private ContainerGame container;

    private HandBelote hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandBelote(ContainerGame _container, HandBelote _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        JPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(hand)) {
            panneau1_.add(c);
        }
        panneau1_.repaint();
        panneau1_.revalidate();
    }
}
