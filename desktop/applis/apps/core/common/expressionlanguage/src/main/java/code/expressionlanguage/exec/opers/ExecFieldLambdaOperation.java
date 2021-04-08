package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
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
        String returnFieldType_ = getReturnFieldType();
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        String formatType_ = _stack.formatVarType(returnFieldType_);
        String idCl_ = StringExpUtil.getIdFromAllTypes(ownerType_);
        String formCl_ = MetaInfoUtil.tryFormatType(idCl_, ownerType_, _conf);
        Argument res_ = new Argument(newLambda(previous_, ownerType_, returnFieldType_, lambdaFieldContent.getClassField(), getAncestor(), lambdaFieldContent.isAffField(), lambdaFieldContent.isStaticField(), lambdaFieldContent.isFinalField(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), lambdaFieldContent.getRootBlock(), lambdaFieldContent.getInfoBlock(), formatType_, formCl_));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(Argument _previous, String _ownerType, String _returnFieldType, ClassField _fieldId, int _ancestor,
                                   boolean _affField, boolean _staticField, boolean _finalField, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName,
                                   ExecRootBlock _rootBlock, ExecInfoBlock _infoBlock, String _formatType, String _formCl) {
        LambdaFieldStruct l_ = new LambdaFieldStruct(_clArg,_ownerType, _fieldId, _shiftArgument, _ancestor, _affField, _formatType);
        l_.setInstanceCall(_previous);
        l_.setStaticField(_staticField);
        l_.setSafeInstance(_safeInstance);
        if (_fieldId != null) {
            String name_ = _fieldId.getFieldName();
            String clName_ = _fieldId.getClassName();
            FieldMetaInfo f_ = new FieldMetaInfo(clName_, name_, _returnFieldType, _staticField, _finalField, AccessEnum.PUBLIC, _formCl);
            f_.setFileName(_fileName);
            f_.pair(_rootBlock,_infoBlock);
            l_.setMetaInfo(f_);
        }
        return l_;
    }

}
