package code.expressionlanguage.analyze.assign.blocks;

public abstract class AssLeaf extends AssBlock {
    protected AssLeaf(boolean _completeNormally,boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public AssBlock getFirstChild() {
        return null;
    }

}
