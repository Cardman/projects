package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.expressionlanguage.analyze.blocks.ReturnableWithSignature;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneCustMethod;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class ExecOperatorBlock extends ExecNamedFunctionBlock implements GeneCustMethod, ExecAccessingImportingBlock,ReturnableWithSignature {

    private StringList imports;

    public ExecOperatorBlock(OperatorBlock _offset) {
        super(_offset);
        imports = _offset.getImports();
    }

    public void buildImportedTypes(OperatorBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
    }
    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodAccessKind.STATIC, name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isTypeHidden(ExecRootBlock _class, ContextEl _analyzable) {
        return _class.getAccess() != AccessEnum.PUBLIC;
    }

    public MethodModifier getModifier() {
        return MethodModifier.STATIC;
    }

    public String getDeclaringType() {
        return EMPTY_STRING;
    }

    @Override
    public StringList getFileImports() {
        return getFile().getImports();
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    @Override
    public StringList getImports() {
        return imports;
    }
}
