package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecConstructorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private boolean implicitCallSuper;

    public ExecConstructorBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _offsetTrim, StringList _importedParametersTypes, CustList<Boolean> _parametersRef) {
        super(false, _name, _varargs, _access, _parametersNames, _offsetTrim, _importedParametersTypes, _parametersRef);
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
        return getId().getSignature(_ana);
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public void setImplicitCallSuper(boolean _implicitCallSuper) {
        this.implicitCallSuper = _implicitCallSuper;
    }
}
