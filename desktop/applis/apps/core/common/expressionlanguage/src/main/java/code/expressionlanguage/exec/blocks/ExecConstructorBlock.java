package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.ConstructorBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public final class ExecConstructorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private boolean implicitCallSuper;

    public ExecConstructorBlock(ConstructorBlock _offset) {
        super(_offset);
    }

    public ConstructorId getId() {
        ExecRootBlock clBlock_ = (ExecRootBlock) getParent();
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


    public String getDeclaringType() {
        ExecRootBlock clBlock_ = (ExecRootBlock) getParent();
        return clBlock_.getFullName();
    }
    public ConstructorId getGenericId() {
        ExecRootBlock clBlock_ = (ExecRootBlock) getParent();
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
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public void buildImportedTypes(ConstructorBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
    }

    public void setImplicitCallSuper(boolean implicitCallSuper) {
        this.implicitCallSuper = implicitCallSuper;
    }
}
