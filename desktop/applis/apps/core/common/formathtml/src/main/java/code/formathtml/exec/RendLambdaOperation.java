package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
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
    private ExecNamedFunctionBlock functionBlock;
    private ExecNamedFunctionBlock function;
    private StandardMethod standardMethod;
    private ExecRootBlock declaring;

    public RendLambdaOperation(LambdaOperation _l, AnalyzedPageEl _page) {
        super(_l);
        standardMethod = _l.getStandardMethod();
        intermediate = _l.getLambdaCommonContent().isIntermediate();
        safeInstance = _l.getLambdaCommonContent().isSafeInstance();
        method = _l.getMethod();
        foundClass = _l.getLambdaCommonContent().getFoundClass();
        ancestor = _l.getLambdaCommonContent().getAncestor();
        shiftArgument = _l.getLambdaCommonContent().isShiftArgument();
        polymorph = _l.getLambdaMethodContent().isPolymorph();
        abstractMethod = _l.getLambdaMethodContent().isAbstractMethod();
        realId = _l.getRealId();
        fieldId = _l.getFieldId();
        staticField = _l.getLambdaFieldContent().isStaticField();
        finalField = _l.getLambdaFieldContent().isFinalField();
        affField = _l.getLambdaFieldContent().isAffField();
        returnFieldType = _l.getLambdaCommonContent().getReturnFieldType();
        directCast = _l.getLambdaMethodContent().isDirectCast();
        expCast = _l.getLambdaMethodContent().isExpCast();
        fileName = _l.getLambdaCommonContent().getFileName();
        if (method == null && realId == null) {
            annotableBlock = ForwardInfos.fetchField(_page, _l.getLambdaMemberNumberContent().getRootNumber(), _l.getLambdaMemberNumberContent().getMemberNumber());
        } else {
            functionBlock = ForwardInfos.fetchFunction(_l.getLambdaMemberNumberContent().getRootNumber(), _l.getLambdaMemberNumberContent().getMemberNumber(), _l.getLambdaMemberNumberContent().getOperatorNumber(), _page);
            function = ForwardInfos.fetchFunction(_l.getLambdaMemberNumberContent().getRootNumber(), _l.getLambdaMemberNumberContent().getMemberNumber(), _l.getLambdaMemberNumberContent().getOperatorNumber(), _page);
        }
        declaring = ForwardInfos.fetchType(_l.getLambdaMemberNumberContent().getRootNumber(), _page);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf,_nodes);
    }

    Argument getCommonArgument(Argument _previous, Configuration _conf) {
        String name_ = getResultClass().getSingleNameOrEmpty();
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
