package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import code.util.core.StringUtil;
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
        call(new FctTreeGetSelected1(""),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        call(new FctTreeGetSelected1(""),null,ctx_,t_,one(n_),st_);
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
    @Test
    public void selectDbg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)(TreeNode e:void)->{e.get();});g.selected(n);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt",164);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(177,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(164,dbg_.getStack().getCall(3).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)new MyImpl().$lambda(MyImpl,impl,TreeNode));g.selected(n);}}public class pkg.MyImpl{public void impl(TreeNode t){t.get();}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt",257);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(190,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(257,dbg_.getStack().getCall(3).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)(TreeNode e:void)->{e.get();});g.selected(n);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleWatchPoint("src/sample.txt",144);
        MethodPointBlockPair p_ = ctx_.getContext().metList().elts().iterator().next();
        p_.getValue().setEntry(true);
        p_.getValue().setExit(false);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(3,dbg_.getStack().nbPages());
        assertEq(177,dbg_.getStack().getCall(0).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)(TreeNode e:void)->{e.get();});g.selected(n);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleWatchPoint("src/sample.txt",144);
        MethodPointBlockPair p_ = ctx_.getContext().metList().elts().iterator().next();
        p_.getValue().setEntry(false);
        p_.getValue().setExit(true);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(177,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(164,dbg_.getStack().getCall(3).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)new MyImpl().$lambda(MyImpl,impl,TreeNode));g.selected(n);}}public class pkg.MyImpl{public void impl(TreeNode t){t.get();}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleWatchPoint("src/sample.txt",228);
        MethodPointBlockPair p_ = ctx_.getContext().metList().elts().iterator().next();
        p_.getValue().setEntry(true);
        p_.getValue().setExit(false);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(3,dbg_.getStack().nbPages());
        assertEq(190,dbg_.getStack().getCall(0).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)new MyImpl().$lambda(MyImpl,impl,TreeNode));g.selected(n);}}public class pkg.MyImpl{public void impl(TreeNode t){t.get();}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleWatchPoint("src/sample.txt",228);
        MethodPointBlockPair p_ = ctx_.getContext().metList().elts().iterator().next();
        p_.getValue().setEntry(false);
        p_.getValue().setExit(true);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(190,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(257,dbg_.getStack().getCall(3).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)(TreeNode e:void)->{e==null;});g.selected(null);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt",164);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(177,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(164,dbg_.getStack().getCall(3).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)(TreeNode e:void)->{e.get();});g.selected(null);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleWatchPoint("src/sample.txt",144);
        MethodPointBlockPair p_ = ctx_.getContext().metList().elts().iterator().next();
        p_.getValue().setEntry(true);
        p_.getValue().setExit(false);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(3,dbg_.getStack().nbPages());
        assertEq(177,dbg_.getStack().getCall(0).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    @Test
    public void selectDbg9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){TreeNode n=new(\"\");TreeNode s=new(\"\");s.add(n);Tree g = new(n);g.addTreeListener((TreeListener)(TreeNode e:void)->{e.get();});g.selected(null);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleWatchPoint("src/sample.txt",144);
        MethodPointBlockPair p_ = ctx_.getContext().metList().elts().iterator().next();
        p_.getValue().setEntry(false);
        p_.getValue().setExit(true);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(177,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(166,dbg_.getStack().getCall(3).getTraceIndex());
        StackCallReturnValue n_ = dbgContinueNormalValue(dbg_.getStack(), ctx_.getContext());
        assertEq(0,n_.getStack().nbPages());
    }
    private StackCallReturnValue launchDbg(ResultContext _ctx) {
        ExecRootBlock ex_ = _ctx.getContext().getClasses().getClassBody("pkg.Sample");
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ExecClassesUtil.tryInitStaticlyTypes(_ctx.getContext(), _ctx.getForwards().getOptions(), null, new CustomFoundMethod(form_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), new Parameters()), StepDbgActionEnum.DEBUG, false);
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, false);
    }
    private ResultContext ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(), StringUtil.EN);
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getGuiAliases().treeListener(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass component_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasComponent(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        component_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getGuiAliases().getAliasComponent(), component_);
        _definedLgNames.getGuiAliases().tree(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), _definedLgNames.getGuiExecutingBlocks(), component_);
        _definedLgNames.getGuiAliases().treeNode(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), _definedLgNames.getGuiExecutingBlocks());

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getGuiExecutingBlocks().treeListener(stds_.getGuiAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),StringUtil.EN);
        return stds_;
    }
}
