package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecFieldLambdaOperation extends ExecAbstractLambdaOperation {


    private final ExecLambdaFieldContent lambdaFieldContent;

    public ExecFieldLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaFieldContent _lambdaFieldContent) {
        super(_opCont, _lamCont);
        lambdaFieldContent = _lambdaFieldContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        ExecFormattedRootBlock ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),lambdaFieldContent,previous_, ownerType_, clArg_,_conf));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, ExecLambdaFieldContent _field, Argument _previous, ExecFormattedRootBlock _ownerType,
                                   String _clArg,
                                   ContextEl _conf) {
        ClassField classField_ = _field.getClassField();
        if (classField_ == null) {
            return new LambdaFieldStruct(NullStruct.NULL_VALUE,_previous,_common,_field,_clArg,_ownerType.getFormatted());
        }
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType.getFormatted());
        String formCl_ = MetaInfoUtil.tryFormatType(idCl_, _ownerType.getFormatted(), _conf);
        FieldMetaInfo f_ = new FieldMetaInfo(_common,_field,classField_, formCl_);
        return new LambdaFieldStruct(f_,_previous,_common,_field,_clArg,_ownerType.getFormatted());
    }

}
