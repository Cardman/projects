package code.expressionlanguage.analyze.assign.blocks;


import code.util.StringList;

public final class AssSimDeclareVariable extends AssLeaf {
    private final StringList assignedVariables = new StringList();
    public AssSimDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }


    public StringList getAssignedVariables() {
        return assignedVariables;
    }
}
