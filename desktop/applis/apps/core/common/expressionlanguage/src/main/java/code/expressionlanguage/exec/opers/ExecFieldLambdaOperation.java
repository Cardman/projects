package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.LambdaFieldStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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
        ExecFormattedRootBlock ownerType_ = formatVarType(_stack);
        String clArg_ = formatVarTypeRes(_stack);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),lambdaFieldContent,previous_, ownerType_, clArg_));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, ExecLambdaFieldContent _field, Argument _previous, ExecFormattedRootBlock _ownerType,
                                   String _clArg) {
        String formCl_ = _ownerType.getFormatted();
        ClassField classField_ = _field.getClassField();
        if (classField_ == null) {
            return new LambdaFieldStruct(NullStruct.NULL_VALUE,_previous,_common,_field,_clArg,formCl_);
        }
        FieldMetaInfo f_ = new FieldMetaInfo(_common,_field,classField_, _ownerType);
        return new LambdaFieldStruct(f_,_previous,_common,_field,_clArg,formCl_);
    }

}
