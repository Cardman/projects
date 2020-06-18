package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.methods.FileBlock;
import code.stream.StreamTextFile;
import code.util.EntryCust;

import java.io.File;

public final class CoveringCodeTask implements Runnable {
    private GuiContextEl contextEl;
    private ExecutingOptions executingOptions;

    public CoveringCodeTask(GuiContextEl contextEl, ExecutingOptions executingOptions) {
        this.contextEl = contextEl;
        this.executingOptions = executingOptions;
    }

    @Override
    public void run() {
        if (contextEl.isCovering()) {
            contextEl.getCustInit().joinHooks(contextEl);
            String exp_ = executingOptions.getCoverFolder();
            for (EntryCust<String,String> f:ExecFileBlock.export(contextEl).entryList()) {
                String full_ = exp_ + f.getKey();
                int end_ = full_.lastIndexOf('/');
                if (end_ > -1) {
                    String par_ = full_.substring(0, end_);
                    if (!par_.isEmpty()) {
                        new File(par_).mkdirs();
                    }
                }
                StreamTextFile.saveTextFile(full_,f.getValue());
            }
        }
    }
}
