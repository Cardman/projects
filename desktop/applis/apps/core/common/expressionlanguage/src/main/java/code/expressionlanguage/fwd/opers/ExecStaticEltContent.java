package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecStaticEltContent {
    private final MethodAccessKind kind;
    private final ExecFormattedRootBlock formattedType;

    public ExecStaticEltContent(AnaCallFctContent _className, Forwards _fwd) {
        kind = kind(_className.getFunction().getFunction());
        formattedType = build(_fwd,_className.getFormattedType());
    }

    public ExecStaticEltContent(ClassMethodIdMemberIdTypeFct _id, Forwards _fwd) {
        kind = kind(_id.getFunction().getFunction());
        formattedType = build(_fwd, _id.getImplicit());
    }

    static ExecFormattedRootBlock build(Forwards _fwd, AnaFormattedRootBlock _implicit) {
        if (_implicit.getRootBlock() == null) {
            return new ExecFormattedRootBlock((ExecRootBlock)null, _implicit.getFormatted());
        }
        return FetchMemberUtil.fwdFormatType(_implicit, _fwd);
    }

    private static MethodAccessKind kind(NamedFunctionBlock _fct) {
        MethodAccessKind kind_;
        if (AbsBk.isOverBlock(_fct)) {
            kind_ =MethodId.getKind(((NamedCalledFunctionBlock)_fct).getModifier());
        } else {
            kind_ =MethodAccessKind.STATIC;
        }
        return kind_;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
