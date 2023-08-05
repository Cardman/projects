package code.expressionlanguage.exec;

import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class BreakPointMiddleInfo {
    private AbstractPageEl exiting;
    private int previousNbPages;
    private int previousNbBlocks;

    public AbstractPageEl getExiting() {
        return exiting;
    }

    public void setExiting(AbstractPageEl _e) {
        this.exiting = _e;
    }

    public int getPreviousNbPages() {
        return previousNbPages;
    }

    public void setPreviousNbPages(int _p) {
        this.previousNbPages = _p;
    }

    public int getPreviousNbBlocks() {
        return previousNbBlocks;
    }

    public void setPreviousNbBlocks(int _p) {
        this.previousNbBlocks = _p;
    }
}
