package code.gui;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter {

    private PackingWindowAfter() {
    }

    public static void pack(GroupFrame _frame) {
        FrameUtil.invokeLater(new PackThread(_frame.getCommonFrame()), _frame.getFrames());
    }

}
