package code.gui;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter implements Runnable {

    private Packable frame;

    private PackingWindowAfter() {
    }

    public static void packg(Packable _frame) {
        FrameUtil.invokeLater(new PackThread(_frame));
    }
    public static void pack(GroupFrame _frame) {
        FrameUtil.invokeLater(new PackThread(_frame));
    }

    @Override
    public void run() {
        pack();
    }

    private void pack() {
        FrameUtil.invokeLater(new PackThread(frame));
    }
}
