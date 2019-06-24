package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.LambdaOperation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.LambdaFieldStruct;
import code.expressionlanguage.structs.LambdaMethodStruct;
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
    private ConstructorId realId;
    private ClassField fieldId;
    private boolean affField;
    private String returnFieldType;

    public ExecLambdaOperation(LambdaOperation _l) {
        super(_l);
        intermediate = _l.isIntermediate();
        setPreviousArgument(_l.getPreviousArgument());
        method = _l.getMethod();
        foundClass = _l.getFoundClass();
        ancestor = _l.getAncestor();
        shiftArgument = _l.isShiftArgument();
        polymorph = _l.isPolymorph();
        abstractMethod = _l.isAbstractMethod();
        realId = _l.getRealId();
        fieldId = _l.getFieldId();
        affField = _l.isAffField();
        returnFieldType = _l.getReturnFieldType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ExecutableCode _conf) {
        Argument arg_ = new Argument();
        String clArg_ = getResultClass().getName();
        String ownerType_ = foundClass;
        ownerType_ = _conf.getOperationPageEl().formatVarType(ownerType_, _conf);
        clArg_ = _conf.getOperationPageEl().formatVarType(clArg_, _conf);
        if (realId == null && method == null) {
            String formatType_ = _conf.getOperationPageEl().formatVarType(returnFieldType, _conf);
            LambdaFieldStruct l_ = new LambdaFieldStruct(clArg_, fieldId, shiftArgument, ancestor,affField, formatType_);
            l_.setInstanceCall(_previous);
            arg_.setStruct(l_);
            return arg_;
        }
        if (method == null) {
            LambdaConstructorStruct l_ = new LambdaConstructorStruct(clArg_, ownerType_, realId, shiftArgument);
            l_.setInstanceCall(_previous);
            arg_.setStruct(l_);
            return arg_;
        }
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor,abstractMethod);
        l_.setInstanceCall(_previous);
        arg_.setStruct(l_);
        return arg_;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }
}
