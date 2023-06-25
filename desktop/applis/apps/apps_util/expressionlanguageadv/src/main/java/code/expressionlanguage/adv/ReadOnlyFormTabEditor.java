package code.expressionlanguage.adv;

import code.expressionlanguage.options.Options;
import code.gui.initialize.AbstractProgramInfos;

public final class ReadOnlyFormTabEditor extends AbsReadOnlyTabEditor {
    private String fullPath = "";
    private String useFeed = "";
    public ReadOnlyFormTabEditor(AbsDebuggerGui _dbg, AbstractProgramInfos _frame, Options _options) {
        super(_dbg, _frame, _options);
    }

    @Override
    public String getUseFeed() {
        return useFeed;
    }

    public void setUseFeed(String _u) {
        this.useFeed = _u;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String _f) {
        this.fullPath = _f;
    }

}
