package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class TreeStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeGetSelected1(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        call(new FctTreeGetSelected1(),null,ctx_,t_,one(n_),st_);
        call(new FctTreeReload(),null,ctx_,t_,null,st_);
        assertTrue(n_.sameReference(call(new FctTreeGetSelected0(),null,ctx_,t_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void addAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((TreeStruct)t_).getActionsTree().size());
    }
    @Test
    public void addAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((TreeStruct)t_).getActionsTree().size());
    }
    @Test
    public void addAction3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stackLogger(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((TreeStruct)t_).getActionsTree().size());
    }
    @Test
    public void removeAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctTreeRemoveTreeListener(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((TreeStruct)t_).getActionsTree().size());
    }
    @Test
    public void removeAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(list_),st_);
        call(new FctTreeRemoveTreeListener(),null,ctx_,t_,one(list_),st_);
        assertEq(0,((TreeStruct)t_).getActionsTree().size());
    }
    @Test
    public void removeAction3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stackLogger(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(list_),st_);
        call(new FctTreeRemoveTreeListener(),null,ctx_,t_,one(list_),st_);
        assertEq(0,((TreeStruct)t_).getActionsTree().size());
    }
    @Test
    public void actions() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        Struct li_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctTreeAddTreeListener(),null,ctx_,t_,one(li_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctTreeGetTreeListeners(), null, ctx_, t_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(li_,a_.get(0));
    }
    @Test
    public void rootVisible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeSetRootVisible(),null,ctx_,t_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctTreeIsRootVisible(),null,ctx_,t_,null,st_));
    }
    @Test
    public void rootVisible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctTreeSetRootVisible(),null,ctx_,t_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctTreeIsRootVisible(),null,ctx_,t_,null,st_));
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctCompoToolTip1(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,t_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct t_ = call(new FctTree(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(n_), st_);
        call(new FctCompoToolTip1(),null,ctx_,t_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,t_,null,st_));
    }
}
