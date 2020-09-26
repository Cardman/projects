package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public final class ExecConstructorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private boolean implicitCallSuper;

    public ExecConstructorBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, int _offsetTrim) {
        super(_name, _varargs, _access, _parametersNames, _offsetTrim);
    }

    public ConstructorId getId() {
        return getGenericId("");
    }


    public ConstructorId getGenericId(String _name) {
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(_name, pTypes_, isVarargs());
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public void buildImportedTypes(String _importedReturnType, StringList _importedParametersTypes) {
        setImportedReturnType(_importedReturnType);
        getImportedParametersTypes().addAllElts(_importedParametersTypes);
    }

    public void setImplicitCallSuper(boolean implicitCallSuper) {
        this.implicitCallSuper = implicitCallSuper;
    }
}
