package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public abstract class ExecInitBlock extends ExecMemberCallingsBlock implements ExecReturnableWithSignature {

    private final int offsetTrim;

    private int number;

    private final String idFull;
    ExecInitBlock(String _i,int _offsetTrim) {
        idFull = _i;
        offsetTrim = _offsetTrim;
    }

    @Override
    public String id() {
        return idFull;
    }

    public int getOffsetTrim() {
        return offsetTrim;
    }
    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana.getStandards().getDisplayedStrings());
    }
    public MethodId getId() {
        String name_ = Long.toString(getNumber());
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
