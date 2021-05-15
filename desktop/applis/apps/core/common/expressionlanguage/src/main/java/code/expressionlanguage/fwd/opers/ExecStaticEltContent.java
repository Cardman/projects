package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecStaticEltContent {
    private final MethodAccessKind kind;
    private final ExecFormattedRootBlock formattedType;

    public ExecStaticEltContent(AnaTypeFct _pair, AnaCallFctContent _className, Forwards _fwd) {
        kind = kind(_pair);
        formattedType = build(_fwd,_className.getFormattedType());
    }

    public ExecStaticEltContent(ClassMethodIdMemberIdTypeFct _id, Forwards _fwd) {
        kind = kind(_id.getFunction());
        formattedType = build(_fwd, _id.getImplicit());
    }

    private static ExecFormattedRootBlock build(Forwards _fwd, AnaFormattedRootBlock _implicit) {
        if (_implicit == null) {
            return new ExecFormattedRootBlock(null, "");
        }
        if (_implicit.getRootBlock() == null) {
            return new ExecFormattedRootBlock(null, _implicit.getFormatted());
        }
        return FetchMemberUtil.fwdFormatType(_implicit, _fwd);
    }

    private static MethodAccessKind kind(AnaTypeFct _fct) {
        if (_fct == null) {
            return MethodAccessKind.STATIC;
        } else {
            return kind(_fct.getFunction());
        }
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
