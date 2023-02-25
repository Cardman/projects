package code.gui;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter {

    private PackingWindowAfter() {
    }

    public static void pack(AbsCommonFrame _comm) {
        _comm.getFrames().getCompoFactory().invokeNow(new PackThread(_comm));
    }

}
