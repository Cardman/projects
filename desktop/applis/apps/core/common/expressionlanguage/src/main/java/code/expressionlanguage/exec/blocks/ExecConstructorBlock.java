package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class ExecConstructorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private boolean implicitCallSuper;

    public ExecConstructorBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, StringList _importedParametersTypes, CustList<BoolVal> _parametersRef) {
        super(_access, new ExecExecNamedFunctionContent(_name, _importedParametersTypes, _parametersRef, _parametersNames, false, _varargs));
    }

    public ConstructorId getId() {
        return getGenericId("");
    }


    public ConstructorId getGenericId(String _name) {
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(_name, pTypes_,getParametersRef(), isVarargs());
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public void setImplicitCallSuper(boolean _implicitCallSuper) {
        this.implicitCallSuper = _implicitCallSuper;
    }
}
