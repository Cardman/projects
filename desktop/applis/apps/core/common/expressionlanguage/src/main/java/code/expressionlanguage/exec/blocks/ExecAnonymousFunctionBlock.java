package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecAnonymousFunctionBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature,WithCache {

    private final MethodModifier methodModifier;
    private final ExecAnonFctContent anonFctContent;

    public ExecAnonymousFunctionBlock(AccessEnum _access, MethodModifier _modifier, ExecAnonFctContent _anonFctContent, ExecExecNamedFunctionContent _content) {
        super(_access, _content);
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
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

    public CacheInfo getCacheInfo() {
        return anonFctContent.getCacheInfo();
    }
}
