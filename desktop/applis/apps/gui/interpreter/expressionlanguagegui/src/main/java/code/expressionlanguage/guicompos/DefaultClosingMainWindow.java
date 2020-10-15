package code.expressionlanguage.guicompos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class DefaultClosingMainWindow extends WindowAdapter {

    private final GuiExecutingBlocks executingBlocks;
    private final GuiContextEl context;

    public DefaultClosingMainWindow(GuiExecutingBlocks _executingBlocks, GuiContextEl _context) {
        this.executingBlocks = _executingBlocks;
        this.context = _context;
    }

    @Override
    public void windowClosing(WindowEvent _e) {
        context.disposeAll(executingBlocks);
    }
}
