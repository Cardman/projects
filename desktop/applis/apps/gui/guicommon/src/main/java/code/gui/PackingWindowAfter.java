package code.gui;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter {

    private PackingWindowAfter() {
    }

    public static void pack(AbsGroupFrame _frame) {
        GuiBaseUtil.invokeLater(new PackThread(_frame.getCommonFrame()), _frame.getCommonFrame().getFrames());
    }

}
