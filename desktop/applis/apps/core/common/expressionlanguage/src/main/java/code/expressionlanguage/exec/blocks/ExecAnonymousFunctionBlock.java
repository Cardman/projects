package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;
import code.util.BooleanList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecAnonymousFunctionBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature,WithCache {

    private final MethodModifier methodModifier;
    private ExecRootBlock parentType;
    private ExecOperatorBlock operator;
    private final ExecAnonFctContent anonFctContent;

    public ExecAnonymousFunctionBlock(boolean _retRef,String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, MethodModifier _modifier, int _offsetTrim, ExecAnonFctContent _anonFctContent, StringList _importedParametersTypes, BooleanList _parametersRef) {
        super(_retRef, _name, _varargs, _access, _parametersNames, _offsetTrim, _importedParametersTypes, _parametersRef);
        methodModifier = _modifier;
        anonFctContent = _anonFctContent;
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
        return new MethodId(isRetRef(), MethodId.getKind(getModifier()), name_, pTypes_,getParametersRef(), isVarargs());
    }

    public MethodModifier getModifier() {
        return methodModifier;
    }
    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public ExecRootBlock getParentType() {
        return parentType;
    }

    public void setParentType(ExecRootBlock _parentType) {
        this.parentType = _parentType;
    }

    public ExecOperatorBlock getOperator() {
        return operator;
    }

    public void setOperator(ExecOperatorBlock _operator) {
        this.operator = _operator;
    }

    public CacheInfo getCacheInfo() {
        return anonFctContent.getCacheInfo();
    }
}
