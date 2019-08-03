package cards.gui.animations;
import cards.gui.containers.ContainerSimu;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class WithdrawCards implements Runnable {

    private ContainerSimu container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public WithdrawCards(ContainerSimu _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.withdrawCards();
    }
}
