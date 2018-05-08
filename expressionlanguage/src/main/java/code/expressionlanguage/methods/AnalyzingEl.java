package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassField;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.ObjectMap;

public final class AnalyzingEl {

    private IdMap<Block, Boolean> canCompleteNormally = new IdMap<Block, Boolean>();
    private IdMap<Block, Boolean> canCompleteNormallyGroup = new IdMap<Block, Boolean>();

    private IdMap<Block, Boolean> reachable = new IdMap<Block, Boolean>();
    private IdMap<BreakBlock, BreakableBlock> breakables = new IdMap<BreakBlock, BreakableBlock>();
    private IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>> breakablesAncestors = new IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>>();
    private IdMap<ContinueBlock, Loop> continuables = new IdMap<ContinueBlock, Loop>();
    private IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> continuablesAncestors = new IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>>();
    private IdMap<ReturnMehod, Eval> returnables = new IdMap<ReturnMehod, Eval>();
    private IdMap<ReturnMehod, MemberCallingsBlock> returnablesCallings = new IdMap<ReturnMehod, MemberCallingsBlock>();
    private IdMap<ReturnMehod, IdMap<Eval, IdList<BracedBlock>>> returnablesAncestors = new IdMap<ReturnMehod, IdMap<Eval, IdList<BracedBlock>>>();
    private IdMap<ReturnMehod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>> returnablesAncestorsCallings = new IdMap<ReturnMehod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>>();
    private CustList<BracedBlock> parents = new CustList<BracedBlock>();
    private CustList<BreakableBlock> parentsBreakables = new CustList<BreakableBlock>();
    private CustList<Loop> parentsContinuables = new CustList<Loop>();
    private CustList<Eval> parentsReturnables = new CustList<Eval>();
    private IdMap<ReturnMehod, ObjectMap<ClassField, Assignment>> assignments = new IdMap<ReturnMehod, ObjectMap<ClassField, Assignment>>();
    private ObjectMap<ClassField, Assignment> assignmentsCalling = new ObjectMap<ClassField, Assignment>();
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

    public IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>> getBreakablesAncestors() {
        return breakablesAncestors;
    }

    public IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> getContinuablesAncestors() {
        return continuablesAncestors;
    }

    public CustList<BracedBlock> getParents() {
        return parents;
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

    public IdMap<ReturnMehod, Eval> getReturnables() {
        return returnables;
    }

    public IdMap<ReturnMehod, MemberCallingsBlock> getReturnablesCallings() {
        return returnablesCallings;
    }

    public IdMap<ReturnMehod, IdMap<Eval, IdList<BracedBlock>>> getReturnablesAncestors() {
        return returnablesAncestors;
    }

    public IdMap<ReturnMehod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>> getReturnablesAncestorsCallings() {
        return returnablesAncestorsCallings;
    }

    public IdMap<ReturnMehod, ObjectMap<ClassField, Assignment>> getAssignments() {
        return assignments;
    }

    public ObjectMap<ClassField, Assignment> getAssignmentsCalling() {
        return assignmentsCalling;
    }

    public MemberCallingsBlock getRoot() {
        return root;
    }

    public void setRoot(MemberCallingsBlock _root) {
        root = _root;
    }
}
