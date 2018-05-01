package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.Templates;
import code.util.CustList;
import code.util.IdMap;

public final class AnalyzingEl {

    private IdMap<Block, Boolean> canCompleteNormally = new IdMap<Block, Boolean>();
    private IdMap<Block, Boolean> canCompleteNormallyGroup = new IdMap<Block, Boolean>();

    private IdMap<Block, Boolean> reachable = new IdMap<Block, Boolean>();
    private IdMap<BreakBlock, BreakableBlock> breakables = new IdMap<BreakBlock, BreakableBlock>();
    private IdMap<ContinueBlock, Loop> continuables = new IdMap<ContinueBlock, Loop>();
    private CustList<BracedBlock> parents = new CustList<BracedBlock>();
    private Mapping mapping;
    private MemberCallingsBlock root;

    public AnalyzingEl(Mapping _mapping) {
        mapping = _mapping;
    }

    public Boolean isReachable(Block _reach) {
        return reachable.getVal(_reach);
    }

    public Boolean canCompleteNormally(Block _reach) {
        return canCompleteNormally.getVal(_reach);
    }

    public Boolean canCompleteNormallyGroup(Block _reach) {
        return canCompleteNormallyGroup.getVal(_reach);
    }

    public void reach(Block _reach) {
        reachable.put(_reach, true);
        canCompleteNormally.put(_reach, true);
        canCompleteNormallyGroup.put(_reach, true);
    }

    public void completeAbrupt(Block _reach) {
        canCompleteNormally.put(_reach, false);
    }

    public void completeAbruptGroup(Block _reach) {
        canCompleteNormallyGroup.put(_reach, false);
    }
    public void unreach(Block _reach) {
        reachable.put(_reach, false);
        canCompleteNormally.put(_reach, false);
        canCompleteNormallyGroup.put(_reach, false);
    }
    public void setParamMapping(String _param) {
        mapping.setParam(_param);
    }
    public void setArgMapping(String _arg) {
        mapping.setArg(_arg);
    }
    public boolean isCorrectMapping(Analyzable _an) {
        return Templates.isCorrect(mapping, _an);
    }

    public IdMap<BreakBlock, BreakableBlock> getBreakables() {
        return breakables;
    }

    public IdMap<ContinueBlock, Loop> getContinuables() {
        return continuables;
    }

    public CustList<BracedBlock> getParents() {
        return parents;
    }

    public MemberCallingsBlock getRoot() {
        return root;
    }

    public void setRoot(MemberCallingsBlock _root) {
        root = _root;
    }
}
