package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;

public final class ExecOperatorBlock extends ExecNamedFunctionBlock implements ExecReturnableWithSignature {

    private final String idFull;
    public ExecOperatorBlock(String _i,AccessEnum _access, ExecExecNamedFunctionContent _cont) {
        super(_access, _cont);
        idFull = _i;
    }

    @Override
    public String id() {
        return idFull;
    }

    public MethodId getId() {
        String name_ = getName();
        return new MethodId(isRetRef(), MethodAccessKind.STATIC, name_, ExecAnonymousFunctionBlock.types(getImportedParametersTypes()),getParametersRef(), isVarargs());
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }

}
