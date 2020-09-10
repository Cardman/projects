package code.expressionlanguage.exec.opers;

import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.opers.LambdaOperation;

public abstract class ExecAbstractLambdaOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;
    private boolean safeInstance;
    private String returnFieldType;
    private String fileName;
    private boolean shiftArgument;
    private int ancestor;
    private String foundClass;

    public ExecAbstractLambdaOperation(LambdaOperation _l) {
        super(_l);
        safeInstance = _l.isSafeInstance();
        intermediate = _l.isIntermediate();
        foundClass = _l.getFoundClass();
        ancestor = _l.getAncestor();
        shiftArgument = _l.isShiftArgument();
        returnFieldType = _l.getReturnFieldType();
        fileName = _l.getFileName();
    }

    public ExecAbstractLambdaOperation(AnonymousLambdaOperation _l) {
        super(_l);
        foundClass = _l.getFoundClass();
        returnFieldType = _l.getReturnFieldType();
        fileName = _l.getFileName();
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public String getFileName() {
        return fileName;
    }

    public int getAncestor() {
        return ancestor;
    }

    public String getFoundClass() {
        return foundClass;
    }
}
