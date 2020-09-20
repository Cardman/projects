package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public abstract class InitBlock extends MemberCallingsBlock {

    private int number;

    public InitBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
    }
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
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
