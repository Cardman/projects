package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ConstructorMetaInfo extends AbAnMeStruct implements AnnotatedParamStruct {

    private final ConstructorId realId;
    private final ConstructorId fid;
    private final boolean invokable;
    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formatted;
    private final StandardType standardType;
    private final GeneType declType;

    public ConstructorMetaInfo(){
        super(new SingleRetType(""), AccessEnum.PRIVATE,"");
        invokable = false;
        realId = new ConstructorId("",new StringList(),false);
        fid = new ConstructorId("",new StringList(),false);
        ExecFormattedRootBlock formatted_ = ExecFormattedRootBlock.defValue();
        pair = new ExecTypeFunction(formatted_,null);
        formatted = formatted_;
        standardType = null;
        declType = null;
    }
    public ConstructorMetaInfo(ExecTypeFunction _pair, ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, ConstructorId _realId) {
        super(new SingleRetType(_common.getReturnFieldType()), AccessEnum.PUBLIC,_common.getFileName());
        ConstructorId fid_ = tryFormatId(_declaringClass, _realId);
        invokable = true;
        realId = _realId;
        fid = fid_;
        pair = _pair;
        formatted = _declaringClass;
        standardType = null;
        declType = _pair.getType();
    }
    public ConstructorMetaInfo(StandardType _standardType, ExecLambdaCommonContent _common, ExecFormattedRootBlock _declaringClass, ConstructorId _realId) {
        super(new SingleRetType(_common.getReturnFieldType()), AccessEnum.PUBLIC,_common.getFileName());
        standardType = _standardType;
        ConstructorId fid_ = tryFormatId(_declaringClass, _realId);
        invokable = true;
        realId = _realId;
        fid = fid_;
        pair = new ExecTypeFunction(_declaringClass,null);
        formatted = _declaringClass;
        declType = _standardType;
    }
    public ConstructorMetaInfo(ExecFormattedRootBlock _formatted, ExecConstructorBlock _ctor, ContextEl _context) {
        super(new SingleRetType(koRetType(_ctor, _context)), ac(_formatted, _ctor),_formatted.getRootBlock().getFile().getFileName());
        ExecRootBlock type_ = _formatted.getRootBlock();
        String formatted_ = _formatted.getFormatted();
        ConstructorId id_ = new ConstructorId(formatted_, new StringList(), false);
        ConstructorId fid_ = id_;
        if (_ctor != null) {
            id_ = _ctor.getGenericId(type_.getGenericString());
            fid_ = tryFormatId(_formatted, _context, id_);
        }
        invokable = true;
        realId = id_;
        fid = fid_;
        pair = new ExecTypeFunction(_formatted,_ctor);
        formatted  = _formatted;
        standardType = null;
        declType = type_;
    }
    public ConstructorMetaInfo(ContextEl _context, StandardType _std, StandardConstructor _ctor, String _declaringClass, ExecFormattedRootBlock _formatted) {
        super(new SingleRetType(koRetType(_context, _ctor)), AccessEnum.PUBLIC,"");
        ConstructorId id_ = new ConstructorId(_declaringClass, new StringList(), false);
        if (_ctor != null) {
            id_ = new ConstructorId(_declaringClass, _ctor.getImportedParametersTypes(), _ctor.isVarargs());
        }
        standardType = _std;
        invokable = true;
        realId = id_;
        fid = id_;
        pair = new ExecTypeFunction(_formatted,null);
        formatted = _formatted;
        declType = _std;
    }
    private static AccessEnum ac(ExecFormattedRootBlock _formatted, ExecConstructorBlock _ctor) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        AccessEnum acc_ = type_.getAccess();
        if (_ctor != null) {
            acc_ = _ctor.getAccess();
        }
        return acc_;
    }
    private static String koRetType(ExecConstructorBlock _c, ContextEl _context) {
        String c_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
        if (_c != null) {
            c_ = _c.getImportedReturnType();
        }
        return c_;
    }
    private static String koRetType(ContextEl _context, StandardConstructor _t) {
        String s_ = _context.getStandards().getContent().getCoreNames().getAliasVoid();
        if (_t != null) {
            s_ = _t.getImportedReturnType();
        }
        return s_;
    }
    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    @Override
    public ExecBlock getBl() {
        return pair.getFct();
    }

    public CustList<ExecAnnotContent> getAnnotationsOps(){
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getAnnotationsOps();
        }
        return new CustList<ExecAnnotContent>();
    }
    public CustList<CustList<ExecAnnotContent>> getAnnotationsOpsParams(){
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getAnnotationsOpsParams();
        }
        return new CustList<CustList<ExecAnnotContent>>();
    }

    public GeneType getDeclType() {
        return declType;
    }

    public ExecMemberCallingsBlock getCallee() {
        return pair.getFct();
    }

    public ExecRootBlock getPairType() {
        return pair.getType();
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    @Override
    public Identifiable fid() {
        return getFid();
    }

    @Override
    public Identifiable realId() {
        return getRealId();
    }

    public ConstructorId getRealId() {
        return realId;
    }
    public boolean isVararg() {
        return realId.isVararg();
    }


    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getAnonymousLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }
    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        ExecNamedFunctionBlock fct_ = pair.getFct();
        if (fct_ != null) {
            return fct_.getSwitchMethods();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    public ConstructorId getFid() {
        return fid;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasConstructor();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ConstructorMetaInfo)) {
            return false;
        }
        ConstructorMetaInfo info_ = (ConstructorMetaInfo) _other;
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
        return new StringStruct(StringUtil.concat(formatted.getFormatted(),";",realId.getSignature(_an)));
    }

    public boolean isInvokable() {
        return invokable;
    }
}
