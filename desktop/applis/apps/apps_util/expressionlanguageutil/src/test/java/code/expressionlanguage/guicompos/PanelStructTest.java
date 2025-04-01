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

public final class PanelStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertSame(MockLayout.LINE,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelPageBox(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertSame(MockLayout.PAGE,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelAbsolute(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertSame(MockLayout.ABSOLUTE,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelFlow(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertSame(MockLayout.LINE,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        assertSame(MockLayout.GRID,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        assertSame(MockLayout.BORDER,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init60() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new DfPanelBorder(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, two(new IntStruct(1),new IntStruct(1)), st_);
        assertSame(MockLayout.BORDER,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new DfPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, st_);
        assertSame(MockLayout.LINE,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctPanelPageBox(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctPanelAbsolute(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctPanelFlow(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctPanelGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new DfPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, st_);
        assertTrue(st_.isFailInit());
    }

    @Test
    public void init15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(0)), st_);
        assertSame(MockLayout.GRID,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(1)), st_);
        assertSame(MockLayout.GRID,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        assertSame(MockLayout.GRID,((MockPanel)((PanelStruct)panel_).getPanel()).getLayout());
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctPanelGetIndexCompo(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertSame(label_,call(new FctPanelGetIndexCompo(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelAddCompo1(),null,ctx_,panel_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctPanelGetIndexCompo(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo1(),null,ctx_,panel_,two(label_,new IntStruct(0)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertSame(label_,call(new FctPanelGetIndexCompo(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo1(),null,ctx_,panel_,two(label_,new IntStruct(0)),st_);
        call(new FctPanelAddCompo1(),null,ctx_,panel_,two(label_,new IntStruct(0)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        call(new FctPanelBorder(),null,ctx_,panel_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct("")),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.AFTER_LAST_LINE)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.AFTER_LINE_ENDS)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.SOUTH)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.EAST)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.CENTER)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.WEST)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.NORTH)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.BEFORE_LINE_BEGINS)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(1),new IntStruct(1)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE)),st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(label_,new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE)),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct panelParent_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct labelParent_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panelParent_,one(panel_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,labelParent_,one(label_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,labelParent_,null,st_)));
        assertSame(label_,call(new FctPanelGetIndexCompo(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertSame(panel_,call(new FctCompoGetParentCompo(),null,ctx_,label_,null,st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count22() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct panelParent_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelAddCompo0(),null,ctx_,panelParent_,one(panel_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(panelParent_),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(panel_),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count24() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelAddCompo1(),null,ctx_,panel_,two(panel_,new IntStruct(0)),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count25() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanelBorderInst(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelBorder(),null,ctx_,panel_,two(panel_,new IntStruct(0)),st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void removeAll() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        call(new FctPanelRemoveAll(),null,ctx_,panel_,null,st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctPanelRemove0(),null,ctx_,panel_,one(new IntStruct(-1)),st_));
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertSame(label_,call(new FctPanelRemove0(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertEq(-2,toLong(call(new FctPanelRemove1(),null,ctx_,panel_,one(NullStruct.NULL_VALUE),st_)));
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label3_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label1_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label2_),st_);
        assertEq(-1,toLong(call(new FctPanelRemove1(),null,ctx_,panel_,one(label3_),st_)));
        assertEq(2,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label1_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label2_),st_);
        assertEq(0,toLong(call(new FctPanelRemove1(),null,ctx_,panel_,one(label1_),st_)));
        assertEq(1,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
        assertSame(label2_,call(new FctPanelGetIndexCompo(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void validate() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelValidate(),null,ctx_,panel_,null,st_);
        assertEq(0,toLong(call(new FctPanelCount(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void parent1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelValidate(),null,ctx_,panel_,null,st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoGetParentCompo(),null,ctx_,panel_,null,st_));
    }
    @Test
    public void parent2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctPanelValidate(),null,ctx_,panel_,null,st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label_),st_);
        assertSame(panel_,call(new FctCompoGetParentCompo(),null,ctx_,label_,null,st_));
    }

    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctCompoSetVisible(),null,ctx_,panel_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,panel_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctCompoSetVisible(),null,ctx_,panel_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,panel_,null,st_));
    }
}
