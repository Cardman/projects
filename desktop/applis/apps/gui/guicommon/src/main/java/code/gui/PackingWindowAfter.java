package code.gui;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter implements Runnable {

    private Packable frame;

    private PackingWindowAfter() {
    }

    public static void pack(Packable _frame, boolean _createThread) {
        if (_createThread) {
            PackingWindowAfter p_;
            p_ = new PackingWindowAfter();
            p_.frame = _frame;
            CustComponent.newThread(p_).start();
        } else {
            packg(_frame);
        }
    }
    public static void packg(Packable _frame) {
        CustComponent.invokeLater(new PackThread(_frame));
    }
    public static void pack(GroupFrame _frame) {
        CustComponent.invokeLater(new PackThread(_frame));
    }

    @Override
    public void run() {
        pack();
    }

    private void pack() {
        CustComponent.invokeLater(new PackThread(frame));
    }
}
