
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.util.CustList;
import code.util.core.StringUtil;


public final class FieldMetaInfo extends AbAnMeStruct {

    private final AccessEnum access;
    private final String name;

    private final boolean staticField;

    private final boolean finalField;
    private final boolean invokable;
    private final String fileName;
    private final ExecInfoBlock annotableBlock;
    private final ExecRootBlock declaring;
    private final ExecFormattedRootBlock formatted;
    public FieldMetaInfo() {
        super(new SingleRetType(""));
        invokable = false;
        name = "";
        access = AccessEnum.PRIVATE;
        staticField = false;
        finalField = false;
        fileName = "";
        annotableBlock = null;
        declaring = null;
        formatted = ExecFormattedRootBlock.defValue();
    }
    public FieldMetaInfo(ExecLambdaCommonContent _common, ExecLambdaFieldContent _field,
                         ClassField _id,
                         ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_common.getReturnFieldType()));
        invokable = true;
        name = StringUtil.nullToEmpty(_id.getFieldName());
        staticField = _field.isStaticField();
        finalField = _field.isFinalField();
        access = AccessEnum.PUBLIC;
        fileName = _common.getFileName();
        declaring = _field.getRootBlock();
        annotableBlock = _field.getInfoBlock();
        setOwner(_field.getRootBlock());
        formatted = _formatted;
    }
    public FieldMetaInfo(ExecInfoBlock _info,
                         String _name, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_info.getImportedClassName()));
        ExecRootBlock type_ = _formatted.getRootBlock();
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        staticField = _info.isStaticField();
        finalField = _info.isFinalField();
        access = _info.getAccess();
        declaring = type_;
        annotableBlock = _info;
        fileName = type_.getFile().getFileName();
        setOwner(type_);
        formatted = _formatted;
    }
    public FieldMetaInfo(String _name,
                         String _returnType,
                         ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_returnType));
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        staticField = true;
        finalField = true;
        access = AccessEnum.PUBLIC;
        fileName = "";
        annotableBlock = null;
        declaring = null;
        formatted = _formatted;
    }

    public CustList<CustList<ExecOperationNode>> getAnnotationsOps(){
        if (annotableBlock != null) {
            return annotableBlock.getAnnotationsOps();
        }
        return new CustList<CustList<ExecOperationNode>>();
    }

    public ExecInfoBlock getAnnotableBlock() {
        return annotableBlock;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public boolean isPublic() {
        return access == AccessEnum.PUBLIC;
    }
    
    public boolean isProtected() {
        return access == AccessEnum.PROTECTED;
    }
    
    public boolean isPrivate() {
        return access == AccessEnum.PRIVATE;
    }
    public boolean isPackage() {
        return access == AccessEnum.PACKAGE;
    }


    @Override
    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    public String getName() {
        return name;
    }
    public boolean isStaticField() {
        return staticField;
    }
    public boolean isFinalField() {
        return finalField;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (annotableBlock != null) {
            return (annotableBlock.getAnonymousLambda());
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (annotableBlock != null) {
            return annotableBlock.getSwitchMethods();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasField();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FieldMetaInfo)) {
            return false;
        }
        FieldMetaInfo f_ = (FieldMetaInfo) _other;
        if (!StringUtil.quickEq(formatted.getFormatted(), f_.formatted.getFormatted())) {
            return false;
        }
        return StringUtil.quickEq(name, f_.name);
    }

    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(formatted.getFormatted()));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(name));
        return r_;
    }
    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(formatted.getFormatted(),".",name));
    }

    public boolean isInvokable() {
        return invokable;
    }
}
