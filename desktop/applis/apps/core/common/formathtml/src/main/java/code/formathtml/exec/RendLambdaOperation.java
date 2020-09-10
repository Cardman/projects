package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendLambdaOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

    private boolean intermediate;
    private boolean safeInstance;

    private ClassMethodId method;
    private String foundClass;
    private int ancestor;
    private boolean shiftArgument;
    private boolean polymorph;
    private boolean abstractMethod;
    private ConstructorId realId;
    private ClassField fieldId;
    private boolean staticField;
    private boolean finalField;
    private boolean affField;
    private boolean directCast;
    private boolean expCast;
    private String returnFieldType;
    private String fileName;
    private ExecAnnotableBlock annotableBlock;
    private ExecAnnotableParametersBlock functionBlock;
    private ExecNamedFunctionBlock function;
    private StandardMethod standardMethod;
    private ExecRootBlock declaring;

    public RendLambdaOperation(LambdaOperation _l,ContextEl _cont) {
        super(_l);
        standardMethod = _l.getStandardMethod();
        intermediate = _l.isIntermediate();
        safeInstance = _l.isSafeInstance();
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
        fileName = _l.getFileName();
        if (method == null && realId == null) {
            annotableBlock = ExecAbstractLambdaOperation.fetchField(_l,_cont);
        } else {
            functionBlock = ExecAbstractLambdaOperation.fetchFunction(_cont, _l.getRootNumber(), _l.getMemberNumber(), _l.getOperatorNumber());
            function = ExecAbstractLambdaOperation.fetchFunction(_cont, _l.getRootNumber(), _l.getMemberNumber(), _l.getOperatorNumber());
        }
        declaring = ExecAbstractLambdaOperation.fetchType(_cont,_l.getRootNumber());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf,_nodes);
    }

    Argument getCommonArgument(Argument _previous, Configuration _conf) {
        String name_ = getResultClass().getName();
        PageEl pageEl_ = _conf.getPageEl();
        ContextEl context_ = _conf.getContext();
        if (standardMethod != null) {
            return new Argument(ExecStdMethodLambdaOperation.newLambda(_previous, context_,foundClass,method,returnFieldType,
                    shiftArgument,safeInstance, name_, pageEl_, standardMethod));
        }
        if (method == null && realId == null) {
            return new Argument(ExecFieldLambdaOperation.newLambda(_previous, context_,foundClass,returnFieldType,fieldId,ancestor,
                    affField,staticField,finalField,shiftArgument,safeInstance, name_, pageEl_, fileName,declaring,annotableBlock));
        }
        if (method == null) {
            return new Argument(ExecConstructorLambdaOperation.newLambda(_previous, context_,foundClass,realId,returnFieldType,
                    shiftArgument,safeInstance, name_, pageEl_, fileName,functionBlock,declaring,function));
        }
        return new Argument(ExecMethodLambdaOperation.newLambda(_previous, context_,foundClass,method,returnFieldType,ancestor,
                directCast,polymorph,abstractMethod,expCast,shiftArgument,safeInstance, name_, pageEl_, fileName,functionBlock,function,declaring));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
