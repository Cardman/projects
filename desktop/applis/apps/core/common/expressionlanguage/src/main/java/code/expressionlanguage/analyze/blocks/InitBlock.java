package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringList;

public abstract class InitBlock extends MemberCallingsBlock {

    private int number;

    public InitBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl) {
    }
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }
    public MethodId getId() {
        String name_ = Integer.toString(getNumber());
        StringList pTypes_ = new StringList();
        return new MethodId(getStaticContext(), name_, pTypes_);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
