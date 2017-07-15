package code.gui;
import javax.swing.SwingUtilities;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter extends Thread {

    private Packable frame;

    private PackingWindowAfter() {
    }

    public static void pack(Packable _frame, boolean _createThread) {
        if (_createThread) {
            PackingWindowAfter p_;
            p_ = new PackingWindowAfter();
            p_.frame = _frame;
            p_.start();
        } else {
            pack(_frame);
        }
    }
    public static void pack(Packable _frame) {
        PackingWindowAfter p_;
        p_ = new PackingWindowAfter();
        p_.frame = _frame;
        p_.pack();
    }

    @Override
    public void run() {
        pack();
    }

    private void pack() {
        SwingUtilities.invokeLater(new PackThread(frame));
    }
}
