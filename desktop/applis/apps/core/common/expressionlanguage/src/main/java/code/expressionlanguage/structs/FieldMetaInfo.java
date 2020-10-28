
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.CustList;
import code.util.core.StringUtil;


public final class FieldMetaInfo extends WithoutParentStruct implements AnnotatedMemberStruct {

    private static final String EMPTY_STRING = "";
    private final AccessEnum access;
    private final String declaringClass;
    private final String formDeclaringClass;
    private final String name;

    private final String type;

    private final boolean staticField;

    private final boolean finalField;
    private final boolean invokable;
    private String fileName = EMPTY_STRING;
    private ExecAnnotableBlock annotableBlock;
    private ExecRootBlock declaring;
    public FieldMetaInfo() {
        invokable = false;
        declaringClass = "";
        name = "";
        type = "";
        access = AccessEnum.PRIVATE;
        staticField = false;
        finalField = false;
        formDeclaringClass = "";
    }
    public FieldMetaInfo(String _declaringClass,
                         String _name,
                         String _returnType, boolean _static,
                         boolean _finalField,
                         AccessEnum _access, String _formDeclaringClass) {
        invokable = true;
        declaringClass = _declaringClass;
        name = _name;
        type = _returnType;
        staticField = _static;
        finalField = _finalField;
        access = _access;
        formDeclaringClass = _formDeclaringClass;
    }

    public ExecAnnotableBlock getAnnotableBlock() {
        return annotableBlock;
    }

    public void setAnnotableBlock(ExecAnnotableBlock _annotableBlock) {
        this.annotableBlock = _annotableBlock;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }

    public void setDeclaring(ExecRootBlock _declaring) {
        this.declaring = _declaring;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
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
        return declaringClass;
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
        if (annotableBlock instanceof ExecInfoBlock) {
            return (((ExecInfoBlock)annotableBlock).getAnonymousLambda());
        }
        return new CustList<ExecAnonymousFunctionBlock>();
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
        if (!StringUtil.quickEq(declaringClass, f_.declaringClass)) {
            return false;
        }
        return StringUtil.quickEq(name, f_.name);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(declaringClass,".",name));
    }

    public boolean isInvokable() {
        return invokable;
    }
}
