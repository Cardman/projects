package code.expressionlanguage.analyze.blocks;

public abstract class SwitchPartBlock extends BracedBlock implements
        BuildableElMethod {

    private SwitchBlock switchParent;

    private SwitchMethodBlock switchMethod;

    private int conditionNb;

    protected SwitchPartBlock(int _offset) {
        super(_offset);
    }

    public SwitchBlock getSwitchParent() {
        return switchParent;
    }

    public void setSwitchParent(SwitchBlock _switchParent) {
        switchParent = _switchParent;
    }

    public SwitchMethodBlock getSwitchMethod() {
        return switchMethod;
    }

    public void setSwitchMethod(SwitchMethodBlock _switchMethod) {
        this.switchMethod = _switchMethod;
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }
}
