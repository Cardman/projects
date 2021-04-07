
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;


public final class MethodMetaInfo extends WithoutParentStruct implements AnnotatedParamStruct {

    private static final String EMPTY_STRING = "";
    private final String declaringClass;
    private final String className;
    private final String formDeclaringClass;

    private final MethodId realId;
    private final MethodId fid;

    private final AccessEnum access;
    private final MethodModifier modifier;

    private final String returnType;
    private String fileName = EMPTY_STRING;
    private boolean directCast;
    private boolean expCast;
    private final boolean invokable;
    private ExecMemberCallingsBlock callee;
    private ExecTypeFunction pair;
    private StandardMethod stdCallee;
    private Cache cache;

    public MethodMetaInfo() {
        declaringClass = "";
        invokable = false;
        className = "";
        formDeclaringClass = "";
        realId = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        fid = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        access = AccessEnum.PRIVATE;
        modifier = MethodModifier.NORMAL;
        returnType = "";
        pair = new ExecTypeFunction(null,null);
    }
    public MethodMetaInfo(String _declaringClass,AccessEnum _access, String _className, MethodId _realId, MethodModifier _modifier, String _returnType,
                          MethodId _fid, String _formDeclaringClass) {
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        invokable = true;
        access = _access;
        className = StringUtil.nullToEmpty(_className);
        realId = _realId;
        modifier = _modifier;
        returnType = StringUtil.nullToEmpty(_returnType);
        fid = _fid;
        formDeclaringClass = StringUtil.nullToEmpty(_formDeclaringClass);
        pair = new ExecTypeFunction(null,null);
    }
    public MethodMetaInfo(String _declaringClass, String _className, MethodId _realId, MethodModifier _modifier, String _returnType,
                          MethodId _fid, String _formDeclaringClass) {
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        invokable = true;
        access = AccessEnum.PUBLIC;
        className = StringUtil.nullToEmpty(_className);
        realId = _realId;
        modifier = _modifier;
        returnType = StringUtil.nullToEmpty(_returnType);
        fid = _fid;
        formDeclaringClass = StringUtil.nullToEmpty(_formDeclaringClass);
        pair = new ExecTypeFunction(null,null);
    }
    public MethodMetaInfo(String _declaringClass, String _className, MethodId _realId, MethodModifier _modifier, String _returnType,
                          String _formDeclaringClass) {
        declaringClass = StringUtil.nullToEmpty(_declaringClass);
        invokable = false;
        access = AccessEnum.PRIVATE;
        className = StringUtil.nullToEmpty(_className);
        realId = _realId;
        modifier = _modifier;
        returnType = StringUtil.nullToEmpty(_returnType);
        fid = _realId;
        formDeclaringClass = StringUtil.nullToEmpty(_formDeclaringClass);
        pair = new ExecTypeFunction(null,null);
    }

    public String getDeclaringClass() {
        return declaringClass;
    }

    public ExecAnnotableBlock getAnnotableBlock() {
        if (callee instanceof ExecAnnotableParamBlock) {
            return (ExecAnnotableParamBlock)callee;
        }
        return null;
    }

    public ExecMemberCallingsBlock getCallee() {
        return callee;
    }

    public void setCallee(ExecMemberCallingsBlock _callee) {
        this.callee = _callee;
    }

    public ExecRootBlock getPairType() {
        return pair.getType();
    }

    public ExecNamedFunctionBlock getPairFct() {
        return pair.getFct();
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public void pair(ExecRootBlock _type, ExecNamedFunctionBlock _fct) {
        pair = new ExecTypeFunction(_type, _fct);
    }

    public void setPair(ExecTypeFunction _pair) {
        pair = _pair;
    }

    public StandardMethod getStdCallee() {
        return stdCallee;
    }

    public void setStdCallee(StandardMethod _stdCallee) {
        this.stdCallee = _stdCallee;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = StringUtil.nullToEmpty(_fileName);
    }

    public void setExpCast(boolean _expCast) {
        this.expCast = _expCast;
    }

    public void setDirectCast(boolean _directCast) {
        directCast = _directCast;
    }

    public String getClassName() {
        return className;
    }
    public String getFormDeclaringClass() {
        return formDeclaringClass;
    }
    public String getName() {
        return realId.getName();
    }
    public MethodId getFid() {
        return fid;
    }
    public StringList getParameterNames() {
        return new StringList(realId.getParametersTypes());
    }
    public boolean isVararg() {
        return realId.isVararg();
    }
    public MethodId getRealId() {
        return realId;
    }
    public MethodAccessKind getKind() {
        return realId.getKind();
    }
    public boolean isStatic() {
        return modifier == MethodModifier.STATIC;
    }
    public boolean isStaticCall() {
        return modifier == MethodModifier.STATIC_CALL;
    }
    public boolean isInstanceMethod() {
        return !isWideStatic();
    }
    public boolean isWideStatic() {
        return modifier == MethodModifier.STATIC || modifier == MethodModifier.STATIC_CALL;
    }
    public boolean isAbstract() {
        return modifier == MethodModifier.ABSTRACT;
    }
    
    public boolean isFinal() {
        return modifier == MethodModifier.FINAL;
    }
    
    public boolean isNormal() {
        return modifier == MethodModifier.NORMAL;
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

    public String getReturnType() {
        return returnType;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (callee != null) {
            return callee.getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (callee != null) {
            return callee.getSwitchMethods();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasMethod();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof MethodMetaInfo)) {
            return false;
        }
        MethodMetaInfo info_ = (MethodMetaInfo) _other;
        if (!StringUtil.quickEq(className, info_.className)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(className,".", getSignature(_an)));
    }

    public String getSignature(ContextEl _an) {
        return realId.getSignature(_an);
    }

    public boolean isInvokable() {
        return invokable;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache _cache) {
        this.cache = _cache;
    }
}
