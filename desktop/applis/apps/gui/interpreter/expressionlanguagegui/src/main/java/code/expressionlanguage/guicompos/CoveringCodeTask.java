package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.util.EntryCust;

public final class CoveringCodeTask implements Runnable {
    private GuiContextEl contextEl;
    private ExecutingOptions executingOptions;

    public CoveringCodeTask(GuiContextEl _contextEl, ExecutingOptions _executingOptions) {
        this.contextEl = _contextEl;
        this.executingOptions = _executingOptions;
    }

    @Override
    public void run() {
        if (executingOptions.isCovering()) {
            contextEl.getCustInit().joinHooks(contextEl);
            FileInfos infos_ = ((LgNamesGui)contextEl.getStandards()).getInfos();
            for (EntryCust<String,String> f:ExecFileBlock.export(contextEl).entryList()) {
                infos_.getReporter().coverFile(executingOptions,f.getKey(),f.getValue());
            }
        }
    }
}
