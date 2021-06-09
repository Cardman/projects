
package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ShownCache;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;


public final class MethodMetaInfo extends AbAnMeStruct implements AnnotatedParamStruct {

    private final PairMethodIds pairIds;

    private final Casts casts;
    private final boolean invokable;
    private final CallersInfo callers;
    private final Cache cache;

    public MethodMetaInfo() {
        super(new SingleRetType(""), AccessEnum.PRIVATE,"");
        invokable = false;
        pairIds = new PairMethodIds(new MethodId(MethodAccessKind.INSTANCE,"",new StringList()));
        ExecFormattedRootBlock formatted_ = ExecFormattedRootBlock.defValue();
        callers = buildCallerInfo(formatted_, null, MethodModifier.NORMAL, null);
        cache = null;
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(ContextEl _cont, String _declaringClass, MethodId _realId, String _returnType) {
        super(new DoubleRetType(_returnType,_declaringClass), AccessEnum.PUBLIC,"");
        String realInstClassName_ = StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCoreNames().getAliasObject());
        String idRealCl_ = StringExpUtil.getIdFromAllTypes(realInstClassName_);
        invokable = true;
        pairIds = new PairMethodIds(_realId);
        ExecFormattedRootBlock formatted_ = new ExecFormattedRootBlock((ExecRootBlock) null, idRealCl_);
        callers = buildCallerInfo(formatted_, null, MethodModifier.FINAL, null);
        cache = null;
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, MethodId _realId, String _returnType) {
        super(new SingleRetType(_returnType), AccessEnum.PUBLIC,_formatted.getRootBlock().getFile().getFileName());
        invokable = true;
        pairIds = new PairMethodIds(_realId);
        callers = buildCallerInfo(_formatted, null, MethodModifier.STATIC, null);
        cache = null;
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ExecAnnotationMethodBlock _annot) {
        super(new SingleRetType(_annot.getImportedReturnType()), AccessEnum.PUBLIC,_formatted.getRootBlock().getFile().getFileName());
        invokable = true;
        pairIds = new PairMethodIds(_annot.getId());
        callers = buildCallerInfo(_formatted, _annot, MethodModifier.ABSTRACT, null);
        cache = null;
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context, ExecOverridableBlock _over, boolean _expCast) {
        super(new SingleRetType(_over.getImportedReturnType()), _over.getAccess(),_formatted.getRootBlock().getFile().getFileName());
        MethodId id_ = _over.getId();
        invokable = true;
        pairIds = new PairMethodIds(id_,tryFormatId(_formatted, _context, id_));
        callers = buildCallerInfo(_formatted, _over, _over.getModifier(), null);
        cache = null;
        casts = new Casts(false,_expCast);
    }

    public MethodMetaInfo(ContextEl _cont, ExecAnonymousFunctionBlock _f, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_f.getImportedReturnType()), _f.getAccess(),_f.getFile().getFileName());
        callers = buildCallerInfo(_formatted, _f, _f.getModifier(), null);
        MethodId id_ = _f.getId();
        invokable = true;
        pairIds = new PairMethodIds(id_,tryFormatId(_formatted, _cont, id_));
        cache = new ShownCache(_f, _cont.getStandards().getContent().getCoreNames().getAliasObject());
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(StandardMethod _std, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_std.getImportedReturnType()), AccessEnum.PUBLIC,"");
        invokable = true;
        callers = buildCallerInfo(_formatted, null, _std.getModifier(), _std);
        pairIds = new PairMethodIds(_std.getId());
        cache = null;
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(ExecOperatorBlock _oper) {
        super(new SingleRetType(_oper.getImportedReturnType()), _oper.getAccess(),_oper.getFile().getFileName());
        pairIds = new PairMethodIds(_oper.getId());
        invokable = true;
        ExecFormattedRootBlock formatted_ = ExecFormattedRootBlock.defValue();
        callers = buildCallerInfo(formatted_, _oper, MethodModifier.STATIC, null);
        cache = null;
        casts = new Casts(false,false);
    }
    public MethodMetaInfo(CallersInfo _caller, Cache _cache, ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth) {
        super(new SingleRetType(_common.getReturnFieldType()), AccessEnum.PUBLIC, _common.getFileName());
        cache = _cache;
        invokable = true;
        pairIds = new PairMethodIds(_meth.getMethod(),tryFormatId(_caller.getFormatted(), _meth.getMethod()));
        callers = _caller;
        casts = new Casts(false,_meth.isExpCast());
    }

    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, ExecLambdaMethodContent _meth) {
        super(new SingleRetType(_common.getReturnFieldType()), AccessEnum.PUBLIC,_common.getFileName());
        invokable = true;
        pairIds = new PairMethodIds(_meth.getMethod(),tryFormatId(_declaringClass, _meth.getMethod()));
        callers = buildCallerInfo(_declaringClass, null, _meth.getModifier(), null);
        cache = null;
        casts = new Casts(_meth.isDirectCast(),false);
    }
    public MethodMetaInfo(ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, MethodId _realId, StandardMethod _stdCallee) {
        super(new SingleRetType(_common.getReturnFieldType()), AccessEnum.PUBLIC,_common.getFileName());
        invokable = true;
        pairIds = new PairMethodIds(_realId,tryFormatId(_declaringClass, _realId));
        callers = buildCallerInfo(_declaringClass, null, _stdCallee.getModifier(), _stdCallee);
        cache = null;
        casts = new Casts(false,false);
    }

    public MethodMetaInfo(ContextEl _cont, ExecAbstractSwitchMethod _f, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(_f.getRetType()), AccessEnum.PUBLIC,_f.getFile().getFileName());
        MethodId id_ = _f.getId();
        invokable = true;
        cache = new ShownCache(_f, _cont.getStandards().getContent().getCoreNames().getAliasObject());
        pairIds = new PairMethodIds(id_,tryFormatId(_formatted, _cont, id_));
        callers = buildCallerInfo(_formatted, _f, _f.getModifier());
        casts = new Casts(false,false);
    }

    public MethodMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context, ExecInitBlock _meth) {
        super(new SingleRetType(_context.getStandards().getContent().getCoreNames().getAliasVoid()), AccessEnum.PRIVATE,_formatted.getRootBlock().getFile().getFileName());
        invokable = false;
        pairIds = new PairMethodIds(_meth.getId());
        callers = buildCallerInfo(_formatted, _meth, modif(_meth));
        cache = null;
        casts = new Casts(false,false);
    }

    private static CallersInfo buildCallerInfo(ExecFormattedRootBlock _formatted, ExecMemberCallingsBlock _meth, MethodModifier _modif) {
        return new CallersInfo(_modif, _meth, new ExecTypeFunction(_formatted, null), _formatted, null);
    }

    private static CallersInfo buildCallerInfo(ExecFormattedRootBlock _formatted, ExecNamedFunctionBlock _over, MethodModifier _modifier, StandardMethod _std) {
        return new CallersInfo(_modifier, _over, new ExecTypeFunction(_formatted, _over), _formatted, _std);
    }

    private static MethodModifier modif(ExecInitBlock _meth) {
        MethodModifier mod_;
        if (_meth instanceof ExecInstanceBlock) {
            mod_ = MethodModifier.FINAL;
        } else {
            mod_ = MethodModifier.STATIC;
        }
        return mod_;
    }

    public CustList<ExecAnnotContent> getAnnotationsOps(){
        if (callers.getCallee() instanceof ExecAnnotableParamBlock) {
            return ((ExecAnnotableParamBlock) callers.getCallee()).getAnnotationsOps();
        }
        return new CustList<ExecAnnotContent>();
    }
    public CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams(){
        if (callers.getCallee() instanceof ExecAnnotableParamBlock) {
            return ((ExecAnnotableParamBlock) callers.getCallee()).getAnnotationsOpsParams();
        }
        return new CustList<CustList<ExecAnnotContent>>();
    }

    public ExecFormattedRootBlock getFormatted() {
        return callers.getFormatted();
    }

    public ExecMemberCallingsBlock getCallee() {
        return callers.getCallee();
    }

    public ExecRootBlock getPairType() {
        return callers.getPair().getType();
    }

    public ExecNamedFunctionBlock getPairFct() {
        return callers.getPair().getFct();
    }

    public ExecTypeFunction getPair() {
        return callers.getPair();
    }

    public StandardMethod getStdCallee() {
        return callers.getStdCallee();
    }

    public String getName() {
        return pairIds.getRealId().getName();
    }
    public MethodId getFid() {
        return pairIds.getFid();
    }
    public StringList getParameterNames() {
        return new StringList(pairIds.getRealId().getParametersTypes());
    }
    public boolean isVararg() {
        return pairIds.getRealId().isVararg();
    }
    public MethodId getRealId() {
        return pairIds.getRealId();
    }
    public MethodAccessKind getKind() {
        return pairIds.getRealId().getKind();
    }
    public boolean isStatic() {
        return callers.getModifier() == MethodModifier.STATIC;
    }
    public boolean isStaticCall() {
        return callers.getModifier() == MethodModifier.STATIC_CALL;
    }
    public boolean isInstanceMethod() {
        return !isWideStatic();
    }
    public boolean isWideStatic() {
        return callers.getModifier() == MethodModifier.STATIC || callers.getModifier() == MethodModifier.STATIC_CALL;
    }
    public boolean isAbstract() {
        return callers.getModifier() == MethodModifier.ABSTRACT;
    }

    public boolean isFinal() {
        return callers.getModifier() == MethodModifier.FINAL;
    }

    public boolean isNormal() {
        return callers.getModifier() == MethodModifier.NORMAL;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (callers.getCallee() != null) {
            return callers.getCallee().getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (callers.getCallee() != null) {
            return callers.getCallee().getSwitchMethods();
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
        if (!StringUtil.quickEq(callers.getFormatted().getFormatted(), info_.callers.getFormatted().getFormatted())) {
            return false;
        }
        return pairIds.getRealId().eq(info_.pairIds.getRealId());
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(callers.getFormatted().getFormatted());
    }
    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(StringUtil.concat(callers.getFormatted().getFormatted(),".", getSignature(_an)));
    }

    public String getSignature(ContextEl _an) {
        return pairIds.getRealId().getSignature(_an);
    }

    public boolean isInvokable() {
        return invokable;
    }

    public boolean isDirectCast() {
        return casts.isDirectCast();
    }

    public boolean isExpCast() {
        return casts.isExpCast();
    }

    public Cache getCache() {
        return cache;
    }

}
