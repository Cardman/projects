package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.gui.events.AbsWindowListenerClosing;

public final class DefaultClosingMainWindow extends AbsWindowListenerClosing {

    private final GuiExecutingBlocks executingBlocks;
    private final GuiContextEl context;

    public DefaultClosingMainWindow(GuiExecutingBlocks _executingBlocks, GuiContextEl _context) {
        this.executingBlocks = _executingBlocks;
        this.context = _context;
    }

    @Override
    public void windowClosing() {
        context.disposeAll(executingBlocks, StackCall.newInstance(InitPhase.NOTHING,context));
    }
}
