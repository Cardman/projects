package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public abstract class ExecAbstractLambdaOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;
    private Argument previousArgument;
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
        previousArgument = _l.getPreviousArgument();
        foundClass = _l.getFoundClass();
        ancestor = _l.getAncestor();
        shiftArgument = _l.isShiftArgument();
        returnFieldType = _l.getReturnFieldType();
        fileName = _l.getFileName();
    }

    public static ExecInfoBlock fetchField(LambdaOperation _l, ContextEl _cont) {
        if (_cont.getAnalyzing().getAllMapMembers().getKeys().isValidIndex(_l.getRootNumber())) {
            if (_cont.getAnalyzing().getAllMapMembers().getValue(_l.getRootNumber()).getAllFields().getKeys().isValidIndex(_l.getMemberNumber())) {
                return _cont.getAnalyzing().getAllMapMembers().getValue(_l.getRootNumber()).getAllFields().getValue(_l.getMemberNumber());
            }
        }
        return null;
    }
    public static ExecNamedFunctionBlock fetchFunction(LambdaOperation _l, ContextEl _cont) {
        if (_cont.getAnalyzing().getAllMapMembers().getKeys().isValidIndex(_l.getRootNumber())) {
            if (_cont.getAnalyzing().getAllMapMembers().getValue(_l.getRootNumber()).getAllNamed().getKeys().isValidIndex(_l.getMemberNumber())) {
                return _cont.getAnalyzing().getAllMapMembers().getValue(_l.getRootNumber()).getAllNamed().getValue(_l.getMemberNumber());
            }
        } else if (_cont.getAnalyzing().getAllMapOperators().getKeys().isValidIndex(_l.getMemberNumber())) {
            return _cont.getAnalyzing().getAllMapOperators().getValue(_l.getMemberNumber());
        }
        return null;
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public Argument getPreviousArgument() {
        return previousArgument;
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
