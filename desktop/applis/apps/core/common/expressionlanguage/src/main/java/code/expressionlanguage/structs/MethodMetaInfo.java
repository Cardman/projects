
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ShownCache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;


public final class MethodMetaInfo extends AbAnMeStruct implements AnnotatedParamStruct {

    private final MethodId realId;
    private final MethodId fid;

    private final AccessEnum access;
    private final MethodModifier modifier;

    private final String fileName;
    private final boolean directCast;
    private final boolean expCast;
    private final boolean invokable;
    private final ExecMemberCallingsBlock callee;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formatted;
    private final StandardMethod stdCallee;
    private final Cache cache;

    public MethodMetaInfo() {
        super(new SingleRetType(""));
        invokable = false;
        realId = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        fid = new MethodId(MethodAccessKind.INSTANCE,"",new StringList());
        access = AccessEnum.PRIVATE;
        modifier = MethodModifier.NORMAL;
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = ExecFormattedRootBlock.defValue();
        fileName = "";
        cache = null;
        stdCallee = null;
        callee = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ContextEl _cont, String _declaringClass, MethodId _realId, String _returnType) {
        super(new DoubleRetType(_returnType,_declaringClass));
        LgNames lgNames_ = _cont.getStandards();
        String realInstClassName_ = StringExpUtil.getPrettyArrayType(lgNames_.getContent().getCoreNames().getAliasObject());
        String idRealCl_ = StringExpUtil.getIdFromAllTypes(realInstClassName_);
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = _realId;
        modifier = MethodModifier.FINAL;
        fid = _realId;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,idRealCl_);
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        cache = null;
        fileName = "";
        stdCallee = null;
        callee = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, MethodId _realId, String _returnType) {
        super(new SingleRetType(_returnType));
        ExecRootBlock type_ = _formatted.getRootBlock();
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = _realId;
        modifier = MethodModifier.STATIC;
        fid = _realId;
        pair = new ExecTypeFunction(_formatted,null);
        callee = null;
        fileName = type_.getFile().getFileName();
        formatted = _formatted;
        setOwner(type_);
        stdCallee = null;
        directCast = false;
        cache = null;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ExecAnnotationMethodBlock _annot) {
        super(new SingleRetType(_annot.getImportedReturnType()));
        ExecRootBlock type_ = _formatted.getRootBlock();
        MethodId id_ = _annot.getId();
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = id_;
        modifier = MethodModifier.ABSTRACT;
        fid = id_;
        pair = new ExecTypeFunction(_formatted,_annot);
        callee = _annot;
        fileName = type_.getFile().getFileName();
        formatted = _formatted;
        stdCallee = null;
        cache = null;
        setOwner(type_);
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context, ExecOverridableBlock _over, boolean _expCast) {
        super(new SingleRetType(_over.getImportedReturnType()));
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        expCast = _expCast;
        MethodId id_ = _over.getId();
        MethodId fid_ = tryFormatId(formatted_, _context, id_);
        invokable = true;
        access = _over.getAccess();
        realId = id_;
        modifier = _over.getModifier();
        fid = fid_;
        pair = new ExecTypeFunction(_formatted,_over);
        cache = null;
        callee = _over;
        fileName = type_.getFile().getFileName();
        setOwner(type_);
        formatted = _formatted;
        stdCallee = null;
        directCast = false;
    }
    public MethodMetaInfo(ContextEl _cont, ExecAnonymousFunctionBlock _f, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_f.getImportedReturnType()));
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        pair = new ExecTypeFunction(type_,_f);
        LgNames standards_ = _cont.getStandards();
        MethodId id_ = _f.getId();
        invokable = true;
        access = _f.getAccess();
        stdCallee = null;
        realId = id_;
        formatted = _formatted;
        modifier = _f.getModifier();
        fid =  tryFormatId(formatted_, _cont, realId);
        callee = _f;
        fileName = _f.getFile().getFileName();
        cache = new ShownCache(_f, standards_.getContent().getCoreNames().getAliasObject());
        setOwner(type_);
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(StandardMethod _std, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_std.getImportedReturnType()));
        invokable = true;
        access = AccessEnum.PUBLIC;
        stdCallee = _std;
        realId = _std.getId();
        modifier = _std.getModifier();
        fid = realId;
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = _formatted;
        fileName = "";
        callee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecOperatorBlock _oper) {
        super(new SingleRetType(_oper.getImportedReturnType()));
        realId = _oper.getId();
        fid = realId;
        invokable = true;
        access = _oper.getAccess();
        modifier = MethodModifier.STATIC;
        fileName = _oper.getFile().getFileName();
        pair = new ExecTypeFunction((ExecRootBlock)null,_oper);
        formatted = ExecFormattedRootBlock.defValue();
        callee = _oper;
        stdCallee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(Cache _cache, ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, ExecFormattedRootBlock _declaringClass, MethodId _realId, ExecTypeFunction _pair) {
        super(new SingleRetType(_common.getReturnFieldType()));
        cache = _cache;
        boolean abstractMethod_ = false;
        boolean expCast_ = false;
        if (_meth != null) {
            abstractMethod_ = _meth.isAbstractMethod();
            expCast_ = _meth.isExpCast();
        }
        MethodModifier met_;
        if (abstractMethod_) {
            met_ = MethodModifier.ABSTRACT;
        } else if (_realId.getKind() == MethodAccessKind.STATIC) {
            met_ = MethodModifier.STATIC;
        } else if (_realId.getKind() == MethodAccessKind.STATIC_CALL) {
            met_ = MethodModifier.STATIC_CALL;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        invokable = true;
        access = AccessEnum.PUBLIC;
        expCast = expCast_;
        realId = _realId;
        modifier = met_;
        fid = tryFormatId(_declaringClass, _realId);
        pair = _pair;
        callee = _pair.getFct();
        fileName = _common.getFileName();
        setOwner(_pair.getType());
        formatted = _declaringClass;
        stdCallee = null;
        directCast = false;
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, ExecTypeFunction _pair) {
        super(new SingleRetType(_common.getReturnFieldType()));
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = _realId;
        modifier = MethodModifier.STATIC;
        fid = tryFormatId(_declaringClass, _realId);
        pair = _pair;
        callee = _pair.getFct();
        fileName = _common.getFileName();
        setOwner(_pair.getType());
        formatted = _declaringClass;
        stdCallee = null;
        cache = null;
        directCast = false;
        expCast = false;
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, MethodModifier _modifier, boolean _directCast) {
        super(new SingleRetType(_common.getReturnFieldType()));
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = _realId;
        modifier = _modifier;
        fid = tryFormatId(_declaringClass, _realId);
        directCast = _directCast;
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = _declaringClass;
        callee = null;
        fileName = _common.getFileName();
        stdCallee = null;
        cache = null;
        expCast = false;
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, MethodModifier _modifier, StandardMethod _stdCallee) {
        super(new SingleRetType(_common.getReturnFieldType()));
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = _realId;
        modifier = _modifier;
        fid = tryFormatId(_declaringClass, _realId);
        pair = new ExecTypeFunction((ExecRootBlock)null,null);
        formatted = _declaringClass;
        callee = null;
        stdCallee = _stdCallee;
        fileName = _common.getFileName();
        cache = null;
        directCast = false;
        expCast = false;
    }

    public MethodMetaInfo(ContextEl _cont, ExecAbstractSwitchMethod _f, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_f.getRetType()));
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        MethodId id_ = _f.getId();
        LgNames standards_ = _cont.getStandards();
        invokable = true;
        access = AccessEnum.PUBLIC;
        realId = id_;
        directCast = false;
        modifier = _f.getModifier();
        fileName = _f.getFile().getFileName();
        cache = new ShownCache(_f, standards_.getContent().getCoreNames().getAliasObject());
        stdCallee = null;
        fid = tryFormatId(formatted_, _cont, realId);
        pair = new ExecTypeFunction(type_,null);
        callee = _f;
        setOwner(type_);
        formatted = _formatted;
        expCast = false;
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context, ExecInitBlock _meth) {
        super(new SingleRetType(_context.getStandards().getContent().getCoreNames().getAliasVoid()));
        ExecRootBlock type_ = _formatted.getRootBlock();
        MethodId id_ = _meth.getId();
        MethodModifier mod_;
        if (_meth instanceof ExecInstanceBlock) {
            mod_ = MethodModifier.FINAL;
        } else {
            mod_ = MethodModifier.STATIC;
        }
        invokable = false;
        access = AccessEnum.PRIVATE;
        realId = id_;
        modifier = mod_;
        fid = id_;
        pair = new ExecTypeFunction(_formatted,null);
        setOwner(type_);
        formatted = _formatted;
        fileName = type_.getFile().getFileName();
        stdCallee = null;
        callee = _meth;
        cache = null;
        directCast = false;
        expCast = false;
    }

    public CustList<CustList<ExecOperationNode>> getAnnotationsOps(){
        if (callee instanceof ExecAnnotableParamBlock) {
            return ((ExecAnnotableParamBlock)callee).getAnnotationsOps();
        }
        return new CustList<CustList<ExecOperationNode>>();
    }
    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams(){
        if (callee instanceof ExecAnnotableParamBlock) {
            return ((ExecAnnotableParamBlock)callee).getAnnotationsOpsParams();
        }
        return new CustList<CustList<CustList<ExecOperationNode>>>();
    }

    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    public ExecMemberCallingsBlock getCallee() {
        return callee;
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

    public StandardMethod getStdCallee() {
        return stdCallee;
    }

    @Override
    public String getFileName() {
        return fileName;
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
        if (!StringUtil.quickEq(formatted.getFormatted(), info_.formatted.getFormatted())) {
            return false;
        }
        return realId.eq(info_.realId);
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(formatted.getFormatted());
    }
    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(formatted.getFormatted(),".", getSignature(_an)));
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

}
