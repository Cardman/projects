package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.events.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class TabbedPaneStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new DfTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        assertEq(0,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        assertEq(1,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
        assertSame(label_,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void count4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        assertEq(1,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void removeAll() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        call(new FctTabbedPaneRemoveAll(),null,ctx_,panel_,null,st_);
        assertEq(0,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTabbedPaneRemove0(),null,ctx_,panel_,one(new IntStruct(-1)),st_));
        assertEq(1,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        assertSame(label_,call(new FctTabbedPaneRemove0(),null,ctx_,panel_,one(new IntStruct(0)),st_));
        assertEq(0,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        assertEq(-2,toLong(call(new FctTabbedPaneRemove1(),null,ctx_,panel_,one(NullStruct.NULL_VALUE),st_)));
        assertEq(1,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label3_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label2_),st_);
        assertEq(-1,toLong(call(new FctTabbedPaneRemove1(),null,ctx_,panel_,one(label3_),st_)));
        assertEq(2,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
    }
    @Test
    public void remove5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label2_),st_);
        assertEq(0,toLong(call(new FctTabbedPaneRemove1(),null,ctx_,panel_,one(label1_),st_)));
        assertEq(1,toLong(call(new FctTabbedPaneNb(),null,ctx_,panel_,null,st_)));
        assertSame(label2_,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void index1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        assertEq(-1,toLong(call(new FctTabbedPaneIndex(),null,ctx_,panel_,one(label2_),st_)));
    }
    @Test
    public void index2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        assertEq(0,toLong(call(new FctTabbedPaneIndex(),null,ctx_,panel_,one(label1_),st_)));
    }
    @Test
    public void index3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        assertEq(-1,toLong(call(new FctTabbedPaneIndex(),null,ctx_,panel_,one(NullStruct.NULL_VALUE),st_)));
    }
    @Test
    public void tab1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneSet(),null,ctx_,panel_,two(new IntStruct(0),label2_),st_);
        assertSame(label2_,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void tab2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneSet(),null,ctx_,panel_,two(new IntStruct(0),label1_),st_);
        assertSame(label1_,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void tab3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneSet(),null,ctx_,panel_,two(new IntStruct(-1),label1_),st_);
        assertSame(label1_,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void tab4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneSet(),null,ctx_,panel_,two(new IntStruct(-1),NullStruct.NULL_VALUE),st_);
        assertSame(label1_,call(new FctTabbedPaneGet(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void title() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneSetTitle(),null,ctx_,panel_,two(new IntStruct(0),NullStruct.NULL_VALUE),st_);
        call(new FctTabbedPaneSetTitle(),null,ctx_,panel_,two(new IntStruct(0),new StringStruct("_")),st_);
        assertEq("_",call(new FctTabbedPaneGetTitle(),null,ctx_,panel_,one(new IntStruct(0)),st_));
    }
    @Test
    public void selected() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label1_),st_);
        call(new FctTabbedPaneSelIndex1(),null,ctx_,panel_,one(new IntStruct(0)),st_);
        assertEq(0,toLong(call(new FctTabbedPaneSelIndex0(),null,ctx_,panel_,null,st_)));
    }

    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctCompoSetVisible(),null,ctx_,panel_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,panel_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctCompoSetVisible(),null,ctx_,panel_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,panel_,null,st_));
    }
}
