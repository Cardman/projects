package code.expressionlanguage.guicompos;

import code.gui.events.AbsWindowListenerClosing;

public final class DefaultClosingMainWindow implements AbsWindowListenerClosing {

    private final GuiContextEl context;

    public DefaultClosingMainWindow(GuiContextEl _context) {
        this.context = _context;
    }

    @Override
    public void windowClosing() {
        context.interrupt();
//        context.disposeAll(StackCall.newInstance(InitPhase.NOTHING,context));
    }
}
