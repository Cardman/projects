package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.LambdaOperation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendLambdaOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

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
    private boolean directCast;
    private boolean expCast;
    private String returnFieldType;

    public RendLambdaOperation(LambdaOperation _l) {
        super(_l);
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
        affField = _l.isAffField();
        returnFieldType = _l.getReturnFieldType();
        directCast = _l.isDirectCast();
        expCast = _l.isExpCast();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf,_nodes);
    }

    Argument getCommonArgument(Argument _previous, Configuration _conf) {
        Argument arg_ = new Argument();
        arg_.setStruct(newLambda(_previous,_conf));
        return arg_;
    }
    private Struct newLambda(Argument _previous, Configuration _conf) {
        String clArg_ = getResultClass().getName();
        String ownerType_ = foundClass;
        ownerType_ = _conf.getPageEl().formatVarType(ownerType_, _conf.getContext());
        clArg_ = _conf.getPageEl().formatVarType(clArg_, _conf.getContext());
        if (realId == null && method == null) {
            String formatType_ = _conf.getPageEl().formatVarType(returnFieldType, _conf.getContext());
            LambdaFieldStruct l_ = new LambdaFieldStruct(clArg_, fieldId, shiftArgument, ancestor,affField, formatType_);
            l_.setInstanceCall(_previous);
            return l_;
        }
        if (method == null) {
            LambdaConstructorStruct l_ = new LambdaConstructorStruct(clArg_, ownerType_, realId, shiftArgument);
            l_.setInstanceCall(_previous);
            return l_;
        }
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor,abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(directCast);
        l_.setExpCast(expCast);
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
