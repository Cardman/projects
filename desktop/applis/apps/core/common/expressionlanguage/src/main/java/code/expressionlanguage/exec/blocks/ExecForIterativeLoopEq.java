package code.expressionlanguage.exec.blocks;

public final class ExecForIterativeLoopEq extends ExecForIterativeLoop {

    public ExecForIterativeLoopEq(String _label, String _importedClassIndexName, ExecVariableName _variableName,
                                  ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        super(_label, _importedClassIndexName, _variableName, _init, _exp, _step);
    }
}
