package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecOperatorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    public ExecOperatorBlock(boolean _retRef, String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, StringList _importedParametersTypes, CustList<Boolean> _parametersRef) {
        super(_access, new ExecExecNamedFunctionContent(_name, _importedParametersTypes, _parametersRef, _parametersNames, _retRef, _varargs));
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
