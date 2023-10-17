package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class SplitPaneStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT),NullStruct.NULL_VALUE,NullStruct.NULL_VALUE), st_);
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
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT),label_,NullStruct.NULL_VALUE), st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT),label_,label_), st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT),label2_,label_), st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }

    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void init6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(0),label_,label2_), st_);
        call(new FctSplitPaneValidate(),null,ctx_,spl_,null,st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void left1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetLeft(),null,ctx_,spl_,one(NullStruct.NULL_VALUE),st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void left2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetLeft(),null,ctx_,spl_,one(label2_),st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void left3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label3_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetLeft(),null,ctx_,spl_,one(label3_),st_);
        assertSame(label3_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void right1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetRight(),null,ctx_,spl_,one(NullStruct.NULL_VALUE),st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void right2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetRight(),null,ctx_,spl_,one(label_),st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label2_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void right3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label3_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetRight(),null,ctx_,spl_,one(label3_),st_);
        assertSame(label_,call(new FctSplitPaneGetLeft(),null,ctx_,spl_,null,st_));
        assertSame(label3_,call(new FctSplitPaneGetRight(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void continuous1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetContinuousLayout(),null,ctx_,spl_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctSplitPaneIsContinuousLayout(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void continuous2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetContinuousLayout(),null,ctx_,spl_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctSplitPaneIsContinuousLayout(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void oneTouch1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetOneTouchExpandable(),null,ctx_,spl_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctSplitPaneIsOneTouchExpandable(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void oneTouch2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetOneTouchExpandable(),null,ctx_,spl_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctSplitPaneIsOneTouchExpandable(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void dividerLocation() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetDividerLocation(),null,ctx_,spl_,one(new IntStruct(5)),st_);
        assertEq(5,toLong(call(new FctSplitPaneGetDividerLocation(),null,ctx_,spl_,null,st_)));
    }
    @Test
    public void dividerSize() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctSplitPaneSetDividerSize(),null,ctx_,spl_,one(new IntStruct(5)),st_);
        assertEq(5,toLong(call(new FctSplitPaneGetDividerSize(),null,ctx_,spl_,null,st_)));
    }

    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctCompoSetVisible(),null,ctx_,spl_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctSplitPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, three(new IntStruct(GuiConstants.HORIZONTAL_SPLIT), label_, label2_), st_);
        call(new FctCompoSetVisible(),null,ctx_,spl_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,spl_,null,st_));
    }
}
