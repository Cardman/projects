package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AnnotationMethodBlock;
import code.expressionlanguage.analyze.blocks.AnonymousFunctionBlock;
import code.expressionlanguage.analyze.blocks.MethodKind;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.GeneCustModifierMethod;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.exec.util.NameAndType;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ExecAnonymousFunctionBlock extends ExecNamedFunctionBlock implements GeneCustModifierMethod,ExecReturnableWithSignature {

    private final boolean staticMethod;
    private final boolean staticCallMethod;
    private ExecRootBlock parentType;
    private final CacheInfo cacheInfo = new CacheInfo();

    public ExecAnonymousFunctionBlock(AnonymousFunctionBlock _offset) {
        super(_offset);
        staticMethod = _offset.isStaticMethod();
        staticCallMethod = _offset.isStaticCallMethod();
        for (AnaNamedLocalVariable e: _offset.getCache().getLocalVariables()) {
            cacheInfo.getCacheLocalNames().add(new NameAndType(e.getName(),e.getLocalVariable().getClassName()));
        }
        for (AnaNamedLoopVariable e: _offset.getCache().getLoopVariables()) {
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

    public void buildImportedTypes(AnonymousFunctionBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
    }
    public MethodModifier getModifier() {
        if (staticCallMethod) {
            return MethodModifier.STATIC_CALL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
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
