package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class ExecLambdaMethodContent {
    private final MethodId method;
    private final boolean polymorph;
    private final boolean directCast;
    private final boolean expCast;
    private final boolean clonedMethod;
    private final ExecTypeFunction pair;
    private final MethodModifier modifier;

    public ExecLambdaMethodContent(MethodId _method, AnaLambdaMethodContent _meth, ExecTypeFunction _pair) {
        method = _method;
        polymorph = _meth.isPolymorph();
        directCast = _meth.isDirectCast();
        expCast = _meth.isExpCast();
        clonedMethod = _meth.isClonedMethod();
        pair = _pair;
        modifier = modif(_method, _meth);
    }

    public ExecLambdaMethodContent(MethodId _method, ExecTypeFunction _pair) {
        method = _method;
        polymorph = false;
        directCast = false;
        expCast = false;
        clonedMethod = false;
        pair = _pair;
        modifier = modif(_method);
    }

    private static MethodModifier modif(MethodId _realId, AnaLambdaMethodContent _meth) {
        MethodModifier met_;
        if (_meth.isDirectCast()) {
            met_ = MethodModifier.STATIC;
        } else if (_meth.isClonedMethod()) {
            met_ = MethodModifier.NORMAL;
        } else if (_meth.isAbstractMethod()) {
            met_ = MethodModifier.ABSTRACT;
        } else {
            met_ = modif(_realId);
        }
        return met_;
    }

    private static MethodModifier modif(MethodId _realId) {
        MethodModifier met_;
        if (_realId.getKind() == MethodAccessKind.STATIC) {
            met_ = MethodModifier.STATIC;
        } else if (_realId.getKind() == MethodAccessKind.STATIC_CALL) {
            met_ = MethodModifier.STATIC_CALL;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        return met_;
    }

    public MethodModifier getModifier() {
        return modifier;
    }

    public MethodId getMethod() {
        return method;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isClonedMethod() {
        return clonedMethod;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }
}
