package cards.gui.animations;
import cards.gui.containers.ContainerSimuWithdraw;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class WithdrawCards implements Runnable {

    private ContainerSimuWithdraw container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public WithdrawCards(ContainerSimuWithdraw _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.withdrawCards();
    }
}
