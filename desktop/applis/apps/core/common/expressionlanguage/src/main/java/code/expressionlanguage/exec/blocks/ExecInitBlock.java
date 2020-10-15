package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public abstract class ExecInitBlock extends ExecMemberCallingsBlock implements ExecReturnableWithSignature {

    private int number;
    ExecInitBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }
    public MethodId getId() {
        String name_ = Integer.toString(getNumber());
        StringList pTypes_ = new StringList();
        return new MethodId(getStaticContext(), name_, pTypes_);
    }

    protected abstract MethodAccessKind getStaticContext();

    public int getNumber() {
        return number;
    }

    public void setNumber(int _number) {
        this.number = _number;
    }
}
