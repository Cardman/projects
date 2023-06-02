package code.expressionlanguage.exec.blocks;

public final class ExecForIterativeLoopStrict extends ExecForIterativeLoop {

    public ExecForIterativeLoopStrict(String _label, String _importedClassIndexName, ExecVariableName _variableName,
                                      ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        super(_label, _importedClassIndexName, _variableName, _init, _exp, _step);
    }
}
