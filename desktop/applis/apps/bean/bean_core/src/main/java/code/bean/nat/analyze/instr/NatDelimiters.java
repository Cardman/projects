package code.bean.nat.analyze.instr;

import code.util.CustList;
import code.util.Ints;

public final class NatDelimiters {

    private int indexEnd;
    private final Ints allowedOperatorsIndexes = new Ints();
    private final Ints delLoopVars = new Ints();
    private final CustList<NatVariableInfo> variables = new CustList<NatVariableInfo>();

    public Ints getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public Ints getDelLoopVars() {
        return delLoopVars;
    }

    public int getIndexEnd() {
        return indexEnd;
    }
    public void setIndexEnd(int _indexEnd) {
        indexEnd = _indexEnd;
    }

    public CustList<NatVariableInfo> getVariables() {
        return variables;
    }

}
