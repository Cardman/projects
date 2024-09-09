package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.WhileCondition;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachWhileCondition extends ReachCondition implements ReachBreakableBlock {

    private final String label;
    public ReachWhileCondition(WhileCondition _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public boolean accessibleCondition() {
        Struct arg_ = getArgument();
        return ArgumentListCall.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        Struct arg_ = getArgument();
        boolean proc_ = ArgumentListCall.isTrueValue(arg_);
        abrWhileMutable(_anEl, proc_, this);
    }

    static void abrWhileMutable(AnalyzingEl _anEl, boolean _proc, ReachBracedBlock _reach) {
        boolean abr_ = !_anEl.isReachable(_reach) || _proc;
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_;
        breakables_ = _anEl.getReachBreakables();
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == _reach && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(_reach);
        }
    }
}
