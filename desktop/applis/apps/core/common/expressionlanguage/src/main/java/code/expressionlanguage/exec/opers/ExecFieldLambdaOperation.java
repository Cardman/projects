package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecFieldLambdaOperation extends ExecAbstractLambdaOperation {


    private ClassField fieldId;
    private boolean staticField;
    private boolean finalField;
    private boolean affField;
    private ExecRootBlock rootBlock;
    private ExecAnnotableBlock infoBlock;

    public ExecFieldLambdaOperation(LambdaOperation _l, ContextEl _cont) {
        super(_l);
        fieldId = _l.getFieldId();
        staticField = _l.isStaticField();
        finalField = _l.isFinalField();
        affField = _l.isAffField();
        rootBlock = fetchType(_cont,_l.getRootNumber());
        infoBlock = fetchField(_l, _cont);
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
        arg_.setStruct(newLambda(_previous,_conf, getFoundClass(), getReturnFieldType(), fieldId, getAncestor(), affField, staticField, finalField));
        return arg_;
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, String returnFieldType, ClassField fieldId, int ancestor, boolean affField, boolean staticField, boolean finalField) {
        return newLambda(_previous, _conf, foundClass, returnFieldType, fieldId, ancestor, affField, staticField, finalField, isShiftArgument(), isSafeInstance(), getResultClass().getName(), _conf.getLastPage(), getFileName(),rootBlock,infoBlock);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, String returnFieldType, ClassField fieldId, int ancestor, boolean affField, boolean staticField, boolean finalField, boolean shiftArgument, boolean safeInstance, String name, PageEl lastPage, String fileName, ExecRootBlock rootBlock,ExecAnnotableBlock infoBlock) {
        String clArg_ = name;
        String ownerType_ = foundClass;
        ownerType_ = lastPage.formatVarType(ownerType_, _conf);
        clArg_ = lastPage.formatVarType(clArg_, _conf);
        String formatType_ = lastPage.formatVarType(returnFieldType, _conf);
        LambdaFieldStruct l_ = new LambdaFieldStruct(clArg_,ownerType_, fieldId, shiftArgument, ancestor, affField, formatType_);
        l_.setInstanceCall(_previous);
        l_.setStaticField(staticField);
        l_.setSafeInstance(safeInstance);
        if (fieldId != null) {
            String name_ = fieldId.getFieldName();
            String clName_ = fieldId.getClassName();
            FieldMetaInfo f_ = new FieldMetaInfo(clName_, name_, returnFieldType, staticField, finalField, AccessEnum.PUBLIC);
            f_.setFileName(fileName);
            f_.setAnnotableBlock(infoBlock);
            f_.setDeclaring(rootBlock);
            l_.setMetaInfo(f_);
        }
        return l_;
    }

}
