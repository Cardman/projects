package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecFieldLambdaOperation extends ExecAbstractLambdaOperation {


    private ExecLambdaFieldContent lambdaFieldContent;

    public ExecFieldLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaFieldContent _lambdaFieldContent) {
        super(_opCont, _lamCont);
        lambdaFieldContent = _lambdaFieldContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), lambdaFieldContent.getClassField(), getAncestor(), lambdaFieldContent.isAffField(), lambdaFieldContent.isStaticField(), lambdaFieldContent.isFinalField()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String _foundClass, String _returnFieldType, ClassField _fieldId, int _ancestor,
                             boolean _affField, boolean _staticField, boolean _finalField) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        String formatType_ = _conf.formatVarType(_returnFieldType);
        return newLambda(_previous, ownerType_, _returnFieldType, _fieldId, _ancestor, _affField, _staticField, _finalField, isShiftArgument(), isSafeInstance(), clArg_, getFileName(), lambdaFieldContent.getRootBlock(), lambdaFieldContent.getInfoBlock(), formatType_);
    }

    public static Struct newLambda(Argument _previous, String _ownerType, String _returnFieldType, ClassField _fieldId, int _ancestor,
                                   boolean _affField, boolean _staticField, boolean _finalField, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName,
                                   ExecRootBlock _rootBlock, ExecAnnotableBlock _infoBlock, String _formatType) {
        LambdaFieldStruct l_ = new LambdaFieldStruct(_clArg,_ownerType, _fieldId, _shiftArgument, _ancestor, _affField, _formatType);
        l_.setInstanceCall(_previous);
        l_.setStaticField(_staticField);
        l_.setSafeInstance(_safeInstance);
        if (_fieldId != null) {
            String name_ = _fieldId.getFieldName();
            String clName_ = _fieldId.getClassName();
            FieldMetaInfo f_ = new FieldMetaInfo(clName_, name_, _returnFieldType, _staticField, _finalField, AccessEnum.PUBLIC);
            f_.setFileName(_fileName);
            f_.setAnnotableBlock(_infoBlock);
            f_.setDeclaring(_rootBlock);
            l_.setMetaInfo(f_);
        }
        return l_;
    }

}
