package cards.gui.animations;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.panels.Carpet;
import code.gui.AbsPanel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class WithdrawCards implements Runnable {

    private final ContainerSingleImpl container;
    private final AbsPanel panel;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public WithdrawCards(ContainerSingleImpl _csi, AbsPanel _p) {
        container = _csi;
        panel = _p;
    }

    @Override
    public void run() {
        Carpet.retirerCartes(panel);
        container.pack();
    }
}
