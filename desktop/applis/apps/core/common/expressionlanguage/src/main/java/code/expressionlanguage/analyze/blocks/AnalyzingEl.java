package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.BlocksFlags;
import code.expressionlanguage.analyze.BlocksLabels;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.reach.blocks.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.util.*;

public final class AnalyzingEl {
    private final BlocksFlags canCompleteNormally = new BlocksFlags();
    private final BlocksFlags canCompleteNormallyGroup = new BlocksFlags();

    private final BlocksFlags reachable = new BlocksFlags();
    private final BlocksLabels labelsMapping = new BlocksLabels();
    private final IdMap<ReachBreakBlock, ReachBreakableBlock> reachBreakables = new IdMap<ReachBreakBlock, ReachBreakableBlock>();
    private final IdMap<ReachContinueBlock, ReachBreakableBlock> reachContinuables = new IdMap<ReachContinueBlock, ReachBreakableBlock>();
    private final StringList labels = new StringList();
    private final Mapping mapping;
    private boolean variableIssue;

    public AnalyzingEl(Mapping _mapping) {
        mapping = _mapping;
    }

    public boolean isReachable(ReachBlock _reach) {
        return isReachable(getOrNull(_reach));
    }

    public boolean canCompleteNormally(ReachBlock _reach) {
        return canCompleteNormally(getOrNull(_reach));
    }

    public boolean canCompleteNormallyGroup(ReachBlock _reach) {
        return canCompleteNormallyGroup(getOrNull(_reach));
    }
    private static AbsBk getOrNull(ReachBlock _reach) {
        if (_reach == null) {
            return null;
        }
        return _reach.getInfo();
    }
    public boolean isReachable(AbsBk _reach) {
        return reachable.getVal(_reach);
    }

    public boolean canCompleteNormally(AbsBk _reach) {
        return canCompleteNormally.getVal(_reach);
    }

    public boolean canCompleteNormallyGroup(AbsBk _reach) {
        return canCompleteNormallyGroup.getVal(_reach);
    }

    public BlocksFlags getCanCompleteNormally() {
        return canCompleteNormally;
    }

    public BlocksFlags getCanCompleteNormallyGroup() {
        return canCompleteNormallyGroup;
    }

    public void reach(ReachBlock _reach) {
        reach(_reach.getInfo());
    }

    public void putLabel(ReachBlock _reach) {
        putLabel(_reach.getInfo());
    }

    public void putLabel(ReachBlock _reach, String _label) {
        putLabel(_reach.getInfo(),_label);
    }

    public void reach(AbsBk _reach) {
        reachable.put(_reach, true);
        canCompleteNormally.put(_reach, true);
        canCompleteNormallyGroup.put(_reach, true);
    }

    public void putLabel(AbsBk _reach) {
        labelsMapping.put(_reach,"");
    }

    public void putLabel(AbsBk _reach, String _label) {
        labelsMapping.put(_reach,_label);
    }

    public BlocksLabels getLabelsMapping() {
        return labelsMapping;
    }

    public void completeAbruptGroup(ReachBlock _reach) {
        completeAbruptGroup(_reach.getInfo());
    }
    public void completeAbrupt(ReachBlock _reach) {
        completeAbrupt(_reach.getInfo());
    }

    public void unreach(ReachBlock _reach) {
        unreach(_reach.getInfo());
    }

    public void completeAbruptGroup(AbsBk _reach) {
        completeAbrupt(_reach);
        canCompleteNormallyGroup.put(_reach, false);
    }
    public void completeAbrupt(AbsBk _reach) {
        canCompleteNormally.put(_reach, false);
    }

    public void unreach(AbsBk _reach) {
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
    public boolean isCorrectMapping(AnalyzedPageEl _page) {
        return AnaInherits.isCorrectOrNumbers(mapping, _page);
    }

    public StringList getLabels() {
        return labels;
    }

    public IdMap<ReachBreakBlock, ReachBreakableBlock> getReachBreakables() {
        return reachBreakables;
    }

    public IdMap<ReachContinueBlock, ReachBreakableBlock> getReachContinuables() {
        return reachContinuables;
    }

    public boolean isVariableIssue() {
        return variableIssue;
    }

    public void setVariableIssue(boolean _variableIssue) {
        this.variableIssue = _variableIssue;
    }

}
