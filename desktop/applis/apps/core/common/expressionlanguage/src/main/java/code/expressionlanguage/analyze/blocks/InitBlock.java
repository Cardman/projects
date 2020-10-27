package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public abstract class InitBlock extends MemberCallingsBlock {

    private int number;

    public InitBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
    }
    public MethodId getId() {
        String name_ = Long.toString(getNumber());
        StringList pTypes_ = new StringList();
        return new MethodId(getStaticContext(), name_, pTypes_);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int _number) {
        this.number = _number;
    }
}
