package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneCustModifierMethod;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.exec.util.NameAndType;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class ExecAnonymousFunctionBlock extends ExecNamedFunctionBlock implements GeneCustModifierMethod,ExecReturnableWithSignature {

    private final MethodModifier methodModifier;
    private ExecRootBlock parentType;
    private final CacheInfo cacheInfo = new CacheInfo();

    public ExecAnonymousFunctionBlock(OffsetsBlock _offset, String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, MethodModifier _modifier, AnaCache _cache) {
        super(_offset, _name, _varargs, _access, _parametersNames);
        methodModifier = _modifier;
        for (AnaNamedLocalVariable e: _cache.getLocalVariables()) {
            cacheInfo.getCacheLocalNames().add(new NameAndType(e.getName(),e.getLocalVariable().getClassName()));
        }
        for (AnaNamedLoopVariable e: _cache.getLoopVariables()) {
            cacheInfo.getCacheLoopNames().add(new NameAndType(e.getName(),e.getLocalVariable().getIndexClassName()));
        }
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
        return new MethodId(MethodId.getKind(getModifier()), name_, pTypes_, isVarargs());
    }

    public void buildImportedTypes(String _importedReturnType, StringList _importedParametersTypes) {
        setImportedReturnType(_importedReturnType);
        getImportedParametersTypes().addAllElts(_importedParametersTypes);
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

    public void setParentType(ExecRootBlock parentType) {
        this.parentType = parentType;
    }

    public CacheInfo getCacheInfo() {
        return cacheInfo;
    }
}
