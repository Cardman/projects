package cards.gui.animations;
import cards.gui.panels.Carpet;
import code.gui.AbsPanel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class WithdrawCards implements Runnable {

    private final AbsPanel container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public WithdrawCards(AbsPanel _container) {
        container = _container;
    }

    @Override
    public void run() {
        Carpet.retirerCartes(container);
    }
}
