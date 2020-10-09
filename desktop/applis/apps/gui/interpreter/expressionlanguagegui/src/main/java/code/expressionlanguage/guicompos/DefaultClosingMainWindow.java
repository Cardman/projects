package code.expressionlanguage.guicompos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class DefaultClosingMainWindow extends WindowAdapter {

    private final GuiExecutingBlocks executingBlocks;
    private final GuiContextEl context;

    public DefaultClosingMainWindow(GuiExecutingBlocks executingBlocks, GuiContextEl context) {
        this.executingBlocks = executingBlocks;
        this.context = context;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        context.disposeAll(executingBlocks);
    }
}
