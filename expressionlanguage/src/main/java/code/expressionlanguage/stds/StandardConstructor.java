package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public final class StandardConstructor extends StandardNamedFunction implements GeneConstructor {

    private static final String DEFAULT_NAME = "";

    public StandardConstructor(StringList _parametersTypes, boolean _varargs, StandardType _type) {
        super(DEFAULT_NAME, _parametersTypes, DEFAULT_NAME, _varargs, _type);
    }

    public ConstructorId getId(String _className) {
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(_className, pTypes_, isVarargs());
    }

    public String getPrettyString(String _className) {
        return getId(_className).getSignature();
    }

    public ConstructorId getGenericId() {
        StandardType clBlock_ = getOwner();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }

    @Override
    public String getSignature() {
        return getId().getSignature();
    }

    @Override
    public String getDeclaringType() {
        return getOwner().getFullName();
    }

    @Override
    public ConstructorId getId() {
        StandardType clBlock_ = getOwner();
        String name_ = clBlock_.getFullName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }
}
