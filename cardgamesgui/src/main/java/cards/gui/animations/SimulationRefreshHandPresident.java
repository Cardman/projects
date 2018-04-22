package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.HandPresident;
import code.gui.Panel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SimulationRefreshHandPresident extends Thread {

    private ContainerGame container;

    private HandPresident hand;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SimulationRefreshHandPresident(ContainerGame _container, HandPresident _hand) {
        container = _container;
        hand = _hand;
    }

    @Override
    public void run() {
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(hand)) {
            panneau1_.add(c);
        }
        panneau1_.repaint();
        panneau1_.revalidate();
    }
}
