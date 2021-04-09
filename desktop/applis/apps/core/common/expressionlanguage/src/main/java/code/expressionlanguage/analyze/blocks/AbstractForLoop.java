package code.expressionlanguage.analyze.blocks;

public abstract class AbstractForLoop extends BracedBlock {
    private int conditionNb;
    protected AbstractForLoop(int _offset) {
        super(_offset);
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }
}
