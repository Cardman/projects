package code.gui;

import code.gui.initialize.*;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class PackingWindowAfter {

    private PackingWindowAfter() {
    }

    public static void pack(AbsCommonFrame _comm, AbsCompoFactory _compo) {
        _compo.invokeNow(new PackThread(_comm));
    }

}
