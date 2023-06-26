package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecOperatorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private final String idFull;
    public ExecOperatorBlock(String _i,AccessEnum _access, ExecExecNamedFunctionContent _cont) {
        super(_access, _cont);
        idFull = _i;
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
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(isRetRef(), MethodAccessKind.STATIC, name_, pTypes_,getParametersRef(), isVarargs());
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

}
