package code.expressionlanguage.exec.blocks;

public final class ExecForIterativeLoopStrict extends ExecForIterativeLoop {

    public ExecForIterativeLoopStrict(String _label, String _importedClassName, String _importedClassIndexName, String _variableName,
                                      ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        super(_label, _importedClassName, _importedClassIndexName, _variableName, _init, _exp, _step);
    }
}
