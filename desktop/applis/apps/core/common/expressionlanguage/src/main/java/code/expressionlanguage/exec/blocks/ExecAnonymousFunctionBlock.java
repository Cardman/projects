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

    private final String idFull;
    private final MethodModifier methodModifier;
    private final ExecAnonFctContent anonFctContent;

    public ExecAnonymousFunctionBlock(String _i,AccessEnum _access, MethodModifier _modifier, ExecAnonFctContent _anonFctContent, ExecExecNamedFunctionContent _content) {
        super(_access, _content);
        idFull = _i;
        methodModifier = _modifier;
        anonFctContent = _anonFctContent;
    }

    @Override
    public String id() {
        return idFull;
    }

    public MethodId getId() {
        String name_ = getName();
        StringList pTypes_ = types(getImportedParametersTypes());
        return new MethodId(isRetRef(), MethodId.getKind(getModifier()), name_, pTypes_,getParametersRef(), isVarargs());
    }

    static StringList types(StringList _ls) {
        int len_ = _ls.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = _ls.get(i);
            pTypes_.add(n_);
        }
        return pTypes_;
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
