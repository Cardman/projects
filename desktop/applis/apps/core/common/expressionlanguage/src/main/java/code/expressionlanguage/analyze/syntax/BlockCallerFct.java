package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;

public final class BlockCallerFct {
    private final AbsBk block;
    private final MemberCallingsBlock caller;

    public BlockCallerFct(AbsBk _b, MemberCallingsBlock _c) {
        this.block = _b;
        this.caller = _c;
    }

    public AbsBk getBlock() {
        return block;
    }

    public MemberCallingsBlock getCaller() {
        return caller;
    }
}
