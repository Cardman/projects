
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.util.CustList;
import code.util.core.StringUtil;


public final class FieldMetaInfo extends AbsAnnotatedStruct implements AnnotatedMemberStruct {

    private final AccessEnum access;
    private final String formDeclaringClass;
    private final String name;

    private final String type;

    private final boolean staticField;

    private final boolean finalField;
    private final boolean invokable;
    private final String fileName;
    private final ExecInfoBlock annotableBlock;
    private final ExecRootBlock declaring;
    private final ExecFormattedRootBlock formatted;
    public FieldMetaInfo() {
        invokable = false;
        name = "";
        type = "";
        access = AccessEnum.PRIVATE;
        staticField = false;
        finalField = false;
        formDeclaringClass = "";
        fileName = "";
        annotableBlock = null;
        declaring = null;
        formatted = new ExecFormattedRootBlock(null,"");
    }
    public FieldMetaInfo(ExecLambdaCommonContent _common, ExecLambdaFieldContent _field,
                         ClassField _id,
                         String _formDeclaringClass, ExecFormattedRootBlock _formatted) {
        invokable = true;
        name = StringUtil.nullToEmpty(_id.getFieldName());
        type = StringUtil.nullToEmpty(_common.getReturnFieldType());
        staticField = _field.isStaticField();
        finalField = _field.isFinalField();
        access = AccessEnum.PUBLIC;
        formDeclaringClass = StringUtil.nullToEmpty(_formDeclaringClass);
        fileName = _common.getFileName();
        declaring = _field.getRootBlock();
        annotableBlock = _field.getInfoBlock();
        setOwner(_field.getRootBlock());
        formatted = _formatted;
    }
    public FieldMetaInfo(ContextEl _context, ExecRootBlock _type, ExecInfoBlock _info, String _declaringClass,
                         String _name, ExecFormattedRootBlock _formatted) {
        String idType_ = _type.getFullName();
        String formCl_ = MetaInfoUtil.tryFormatType(idType_, _declaringClass, _context);
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        type = StringUtil.nullToEmpty(_info.getImportedClassName());
        staticField = _info.isStaticField();
        finalField = _info.isFinalField();
        access = _info.getAccess();
        formDeclaringClass = StringUtil.nullToEmpty(formCl_);
        declaring = _type;
        annotableBlock = _info;
        fileName = _type.getFile().getFileName();
        setOwner(_type);
        formatted = _formatted;
    }
    public FieldMetaInfo(String _name,
                         String _returnType,
                         String _formDeclaringClass, ExecFormattedRootBlock _formatted) {
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        type = StringUtil.nullToEmpty(_returnType);
        staticField = true;
        finalField = true;
        access = AccessEnum.PUBLIC;
        formDeclaringClass = StringUtil.nullToEmpty(_formDeclaringClass);
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
    
    public boolean isPackage() {
        return access == AccessEnum.PACKAGE;
    }

    public boolean isPrivate() {
        return access == AccessEnum.PRIVATE;
    }
    public String getDeclaringClass() {
        return formatted.getFormatted();
    }

    public String getFormDeclaringClass() {
        return formDeclaringClass;
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
    public String getType() {
        return type;
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
