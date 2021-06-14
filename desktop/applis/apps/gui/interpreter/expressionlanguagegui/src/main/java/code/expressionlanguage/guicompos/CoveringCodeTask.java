package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.EntryCust;

public final class CoveringCodeTask implements Runnable {
    private GuiContextEl contextEl;
    private ExecutingOptions executingOptions;
    private AbstractFileCoreStream list;
    private TechStreams tech;

    public CoveringCodeTask(GuiContextEl _contextEl, ExecutingOptions _executingOptions, AbstractFileCoreStream _list, TechStreams _tech) {
        this.contextEl = _contextEl;
        this.executingOptions = _executingOptions;
        list = _list;
        tech = _tech;
    }

    @Override
    public void run() {
        if (executingOptions.isCovering()) {
            contextEl.getCustInit().joinHooks(contextEl);
            String exp_ = executingOptions.getCoverFolder();
            for (EntryCust<String,String> f:ExecFileBlock.export(contextEl).entryList()) {
                String full_ = exp_ + f.getKey();
                StreamFolderFile.makeParent(full_,list);
                StreamTextFile.saveTextFile(full_,f.getValue(),tech);
            }
        }
    }
}
