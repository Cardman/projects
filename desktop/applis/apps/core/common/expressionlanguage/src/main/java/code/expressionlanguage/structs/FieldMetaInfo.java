
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.util.CustList;
import code.util.core.StringUtil;


public final class FieldMetaInfo extends AbAnMeStruct {

    private final String name;

    private final boolean staticField;

    private final boolean finalField;
    private final boolean invokable;
    private final ExecInfoBlock annotableBlock;
    private final ExecFormattedRootBlock formatted;
    public FieldMetaInfo() {
        super(new SingleRetType(""), AccessEnum.PRIVATE,"");
        invokable = false;
        name = "";
        staticField = false;
        finalField = false;
        annotableBlock = null;
        formatted = ExecFormattedRootBlock.defValue();
    }
    public FieldMetaInfo(ExecLambdaCommonContent _common, ExecLambdaFieldContent _field,
                         ClassField _id,
                         ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_common.getReturnFieldType()), AccessEnum.PUBLIC,_common.getFileName());
        invokable = true;
        name = StringUtil.nullToEmpty(_id.getFieldName());
        staticField = _field.isStaticField();
        finalField = _field.isFinalField();
        annotableBlock = _field.getInfoBlock();
        formatted = _formatted;
    }
    public FieldMetaInfo(ExecFieldBlock _info,
                         String _name, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_info.getImportedClassName()), _info.getAccess(),_formatted.getRootBlock().getFile().getFileName());
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        staticField = _info.isStaticField();
        finalField = _info.isFinalField();
        annotableBlock = _info;
        formatted = _formatted;
    }
    public FieldMetaInfo(ExecInnerTypeOrElement _info,
                         String _name, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_info.getImportedClassName()), AccessEnum.PUBLIC,_formatted.getRootBlock().getFile().getFileName());
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        staticField = true;
        finalField = true;
        annotableBlock = _info;
        formatted = _formatted;
    }
    public FieldMetaInfo(String _name,
                         String _returnType,
                         ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_returnType), AccessEnum.PUBLIC,"");
        invokable = true;
        name = StringUtil.nullToEmpty(_name);
        staticField = true;
        finalField = true;
        annotableBlock = null;
        formatted = _formatted;
    }

    @Override
    public ExecBlock getBl() {
        return (ExecBlock) annotableBlock;
    }

    public ExecInfoBlock getAnnotableBlock() {
        return annotableBlock;
    }

    public ExecRootBlock getDeclaring() {
        return formatted.getRootBlock();
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
            return (annotableBlock.getElementContent().getContainer().getAnonymousLambda());
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (annotableBlock != null) {
            return annotableBlock.getElementContent().getContainer().getSwitchMethods();
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
