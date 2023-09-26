package code.expressionlanguage.exec.blocks;

public abstract class ExecCondition extends ExecBracedBlock implements WithEl {


    private final ExecOperationNodeListOff condition;

    ExecCondition(ExecOperationNodeListOff _ex) {
        condition = _ex;
    }

    public ExecOperationNodeListOff getCondition() {
        return condition;
    }

}
