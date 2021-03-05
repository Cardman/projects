package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;

public final class ExecStaticEltContent {
    private final MethodAccessKind kind;
    private final String className;

    public ExecStaticEltContent(AnaTypeFct _pair, String _className) {
        if (_pair == null) {
            kind =MethodAccessKind.STATIC;
        } else {
            kind = kind(_pair.getFunction());
        }
        className = _className;
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

    public String getClassName() {
        return className;
    }

    public MethodAccessKind getKind() {
        return kind;
    }
}
