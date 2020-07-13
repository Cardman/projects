package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecLambdaOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private boolean intermediate;
    private Argument previousArgument;

    private ClassMethodId method;
    private String foundClass;
    private int ancestor;
    private boolean shiftArgument;
    private boolean polymorph;
    private boolean abstractMethod;
    private boolean directCast;
    private boolean safeInstance;
    private boolean expCast;
    private ConstructorId realId;
    private ClassField fieldId;
    private boolean staticField;
    private boolean finalField;
    private boolean affField;
    private String returnFieldType;

    public ExecLambdaOperation(LambdaOperation _l) {
        super(_l);
        safeInstance = _l.isSafeInstance();
        intermediate = _l.isIntermediate();
        previousArgument = _l.getPreviousArgument();
        method = _l.getMethod();
        foundClass = _l.getFoundClass();
        ancestor = _l.getAncestor();
        shiftArgument = _l.isShiftArgument();
        polymorph = _l.isPolymorph();
        abstractMethod = _l.isAbstractMethod();
        realId = _l.getRealId();
        fieldId = _l.getFieldId();
        staticField = _l.isStaticField();
        finalField = _l.isFinalField();
        affField = _l.isAffField();
        returnFieldType = _l.getReturnFieldType();
        directCast = _l.isDirectCast();
        expCast = _l.isExpCast();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        Argument arg_ = new Argument();
        arg_.setStruct(newLambda(_previous,_conf));
        return arg_;
    }
    private Struct newLambda(Argument _previous, ContextEl _conf) {
        String clArg_ = getResultClass().getName();
        String ownerType_ = foundClass;
        ownerType_ = _conf.getLastPage().formatVarType(ownerType_, _conf);
        clArg_ = _conf.getLastPage().formatVarType(clArg_, _conf);
        if (realId == null && method == null) {
            String formatType_ = _conf.getLastPage().formatVarType(returnFieldType, _conf);
            LambdaFieldStruct l_ = new LambdaFieldStruct(clArg_,ownerType_, fieldId, shiftArgument, ancestor,affField, formatType_);
            l_.setInstanceCall(_previous);
            l_.setStaticField(staticField);
            l_.setFinalField(finalField);
            l_.setSafeInstance(safeInstance);
            return l_;
        }
        if (method == null) {
            LambdaConstructorStruct l_ = new LambdaConstructorStruct(clArg_, ownerType_, realId, shiftArgument);
            l_.setInstanceCall(_previous);
            l_.setSafeInstance(safeInstance);
            return l_;
        }
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor,abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(directCast);
        l_.setExpCast(expCast);
        l_.setSafeInstance(safeInstance);
        return l_;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public Argument getPreviousArgument() {
        return previousArgument;
    }

}
