package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.blocks.AnonymousTypeBlock;
import code.expressionlanguage.analyze.opers.util.MemberId;

public final class AnaInstancingAnonContent {

    private final AnonymousTypeBlock block;
    private MemberId memberId = new MemberId();

    public AnaInstancingAnonContent(AnonymousTypeBlock _block) {
        block = _block;
    }
    public AnonymousTypeBlock getBlock() {
        return block;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

}
