package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.BlocksFlags;
import code.expressionlanguage.analyze.BlocksLabels;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.util.*;

public final class AnalyzingEl {
    private IdMap<ExecBlock,Block> mappingMembers = new IdMap<ExecBlock,Block>();
    private IdMap<BracedBlock,ExecBracedBlock> mappingBracedMembers = new IdMap<BracedBlock,ExecBracedBlock>();
    private BlocksFlags canCompleteNormally = new BlocksFlags();
    private BlocksFlags canCompleteNormallyGroup = new BlocksFlags();

    private BlocksFlags reachable = new BlocksFlags();
    private BlocksFlags finals = new BlocksFlags();
    private BlocksLabels labelsMapping = new BlocksLabels();
    private IdMap<BreakBlock, BreakableBlock> breakables = new IdMap<BreakBlock, BreakableBlock>();
    private IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>> breakablesAncestors = new IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>>();
    private IdMap<ContinueBlock, Loop> continuables = new IdMap<ContinueBlock, Loop>();
    private IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> continuablesAncestors = new IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>>();
    private IdMap<ReturnMethod, Eval> returnables = new IdMap<ReturnMethod, Eval>();
    private IdMap<ReturnMethod, MemberCallingsBlock> returnablesCallings = new IdMap<ReturnMethod, MemberCallingsBlock>();
    private IdMap<ReturnMethod, IdMap<Eval, IdList<BracedBlock>>> returnablesAncestors = new IdMap<ReturnMethod, IdMap<Eval, IdList<BracedBlock>>>();
    private IdMap<ReturnMethod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>> returnablesAncestorsCallings = new IdMap<ReturnMethod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>>();
    private CustList<BracedBlock> parents = new CustList<BracedBlock>();
    private StringList labels = new StringList();
    private CustList<BreakableBlock> parentsBreakables = new CustList<BreakableBlock>();
    private CustList<Loop> parentsContinuables = new CustList<Loop>();
    private CustList<Eval> parentsReturnables = new CustList<Eval>();
    private Mapping mapping;
    private MemberCallingsBlock root;

    public AnalyzingEl(Mapping _mapping) {
        mapping = _mapping;
    }

    public boolean isReachable(Block _reach) {
        return reachable.getVal(_reach);
    }

    public boolean canCompleteNormally(Block _reach) {
        return canCompleteNormally.getVal(_reach);
    }

    public boolean canCompleteNormallyGroup(Block _reach) {
        return canCompleteNormallyGroup.getVal(_reach);
    }

    public BlocksFlags getCanCompleteNormally() {
        return canCompleteNormally;
    }

    public BlocksFlags getCanCompleteNormallyGroup() {
        return canCompleteNormallyGroup;
    }

    public void reach(Block _reach) {
        reachable.put(_reach, true);
        canCompleteNormally.put(_reach, true);
        canCompleteNormallyGroup.put(_reach, true);
    }

    public void putLabel(Block _reach) {
        labelsMapping.put(_reach,"");
    }

    public void putFinal(Block _reach) {
        finals.put(_reach,false);
    }

    public void putLabel(Block _reach, String _label) {
        labelsMapping.put(_reach,_label);
    }

    public void putFinal(Block _reach, boolean _label) {
        finals.put(_reach,_label);
    }

    public BlocksLabels getLabelsMapping() {
        return labelsMapping;
    }

    public BlocksFlags getFinals() {
        return finals;
    }

    public void completeAbruptGroup(Block _reach) {
        completeAbrupt(_reach);
        canCompleteNormallyGroup.put(_reach, false);
    }
    public void completeAbrupt(Block _reach) {
        canCompleteNormally.put(_reach, false);
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
    public boolean isCorrectMapping(ContextEl _an) {
        return AnaTemplates.isCorrectOrNumbers(mapping, _an);
    }

    public IdMap<BreakBlock, BreakableBlock> getBreakables() {
        return breakables;
    }

    public IdMap<ContinueBlock, Loop> getContinuables() {
        return continuables;
    }

    public IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>> getBreakablesAncestors() {
        return breakablesAncestors;
    }

    public IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> getContinuablesAncestors() {
        return continuablesAncestors;
    }

    public CustList<BracedBlock> getParents() {
        return parents;
    }

    public StringList getLabels() {
        return labels;
    }

    public CustList<BreakableBlock> getParentsBreakables() {
        return parentsBreakables;
    }

    public CustList<Loop> getParentsContinuables() {
        return parentsContinuables;
    }

    public CustList<Eval> getParentsReturnables() {
        return parentsReturnables;
    }

    public IdMap<ReturnMethod, Eval> getReturnables() {
        return returnables;
    }

    public IdMap<ReturnMethod, MemberCallingsBlock> getReturnablesCallings() {
        return returnablesCallings;
    }

    public IdMap<ReturnMethod, IdMap<Eval, IdList<BracedBlock>>> getReturnablesAncestors() {
        return returnablesAncestors;
    }

    public IdMap<ReturnMethod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>> getReturnablesAncestorsCallings() {
        return returnablesAncestorsCallings;
    }

    public MemberCallingsBlock getRoot() {
        return root;
    }

    public void setRoot(MemberCallingsBlock _root) {
        root = _root;
    }

    public IdMap<ExecBlock, Block> getMappingMembers() {
        return mappingMembers;
    }

    public IdMap<BracedBlock, ExecBracedBlock> getMappingBracedMembers() {
        return mappingBracedMembers;
    }
}
