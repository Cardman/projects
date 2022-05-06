package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.common.NumberInfos;

public final class NumberInfosOutput {

    private NumberInfos infos;

    private int previousIndex;
    private int nextIndex;

    public NumberInfos getInfos() {
        return infos;
    }

    public void setInfos(NumberInfos _infos) {
        infos = _infos;
    }

    public int getPreviousIndex() {
        return previousIndex;
    }

    public void setPreviousIndex(int _pre) {
        this.previousIndex = _pre;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

}
