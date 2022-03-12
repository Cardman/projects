package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.fwd.Members;

public final class FwdRootBlockMembers {
    private final RootBlock rootBlock;
    private final Members members;

    public FwdRootBlockMembers(RootBlock _r, Members _m) {
        this.rootBlock = _r;
        this.members = _m;
    }

    public Members getMembers() {
        return members;
    }

    public RootBlock getRootBlock() {
        return rootBlock;
    }
}
