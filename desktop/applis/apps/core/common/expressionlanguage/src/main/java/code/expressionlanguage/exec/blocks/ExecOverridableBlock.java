package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class ExecOverridableBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private final MethodModifier methodModifier;

    private final ExecMethodKind kind;

    private final String idFull;
    public ExecOverridableBlock(String _i,AccessEnum _access, MethodModifier _modifier, ExecMethodKind _execKind, ExecExecNamedFunctionContent _content) {
        super(_access, _content);
        idFull = _i;
        methodModifier = _modifier;
        kind = _execKind;
    }

    @Override
    public String id() {
        return idFull;
    }

    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        CustList<BoolVal> rTypes_ = new CustList<BoolVal>();
        if (kind == ExecMethodKind.EXPLICIT_CAST || kind == ExecMethodKind.IMPLICIT_CAST
                ||kind == ExecMethodKind.TRUE_OPERATOR || kind == ExecMethodKind.FALSE_OPERATOR) {
            pTypes_.add(getImportedReturnType());
            rTypes_.add(BoolVal.FALSE);
        }
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
            rTypes_.add(getParametersRef(i));
        }
        return new MethodId(isRetRef(), MethodId.getKind(getModifier()), name_, pTypes_,rTypes_, isVarargs());

    }

    public MethodModifier getModifier() {
        return methodModifier;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

    public ExecMethodKind getKind() {
        return kind;
    }
}
