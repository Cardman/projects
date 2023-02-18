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

public final class PreparedLabelStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new FctImageLabel0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        call(new DfImageLabel(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctImageLabel0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new DfImageLabel(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctImageLabel1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(call(new FctImage(stds_.getGuiExecutingBlocks()),null,ctx_,null,three(new IntStruct(0),new IntStruct(0),BooleanStruct.of(true)),st_)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void image1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctImageLabel(stds_.getGuiExecutingBlocks()),null,ctx_,i_,one(call(new FctImage(stds_.getGuiExecutingBlocks()),null,ctx_,null,three(new IntStruct(0),new IntStruct(0),BooleanStruct.of(true)),st_)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void image2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctImageLabel(stds_.getGuiExecutingBlocks()),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void height() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoGetHeight(),null,ctx_,i_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void width() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoGetWidth(),null,ctx_,i_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoToolTip1(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,i_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoToolTip1(),null,ctx_,i_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,i_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoSetVisible(),null,ctx_,i_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,i_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoSetVisible(),null,ctx_,i_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,i_,null,st_));
    }
    @Test
    public void focusable1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoFocusable1(),null,ctx_,i_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoFocusable0(),null,ctx_,i_,null,st_));
    }
    @Test
    public void focusable2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoFocusable1(),null,ctx_,i_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoFocusable0(),null,ctx_,i_,null,st_));
    }
    @Test
    public void autoscrolls1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoSetAutoscrolls(),null,ctx_,i_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsAutoscrolls(),null,ctx_,i_,null,st_));
    }
    @Test
    public void autoscrolls2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoSetAutoscrolls(),null,ctx_,i_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsAutoscrolls(),null,ctx_,i_,null,st_));
    }
    @Test
    public void next1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoGetNextCompo(),null,ctx_,i_,null,st_));
    }
    @Test
    public void next2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label1_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label2_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoGetNextCompo(),null,ctx_,label2_,null,st_));
    }
    @Test
    public void next3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctPanel(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label1_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label1_),st_);
        call(new FctPanelAddCompo0(),null,ctx_,panel_,one(label2_),st_);
        assertSame(label2_,call(new FctCompoGetNextCompo(),null,ctx_,label1_,null,st_));
    }
    @Test
    public void location() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoLoc(),null,ctx_,i_,two(new IntStruct(2),new IntStruct(3)),st_);
        assertEq(2,toLong(call(new FctCompoGetFirstPos(),null,ctx_,i_,null,st_)));
        assertEq(3,toLong(call(new FctCompoGetSecondPos(),null,ctx_,i_,null,st_)));
    }
    @Test
    public void modif() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoBorLine0(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoBorLine0(),null,ctx_,i_,one(new ColorStruct(0)),st_);
        call(new FctCompoBorLine1(),null,ctx_,i_,two(NullStruct.NULL_VALUE,new IntStruct(1)),st_);
        call(new FctCompoBorLine1(),null,ctx_,i_,two(new ColorStruct(0),new IntStruct(1)),st_);
        call(new FctCompoBorTitle(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoBorTitle(),null,ctx_,i_,one(new StringStruct("")),st_);
        call(new FctCompoOpaque1(),null,ctx_,i_,one(BooleanStruct.of(true)),st_);
        call(new FctCompoOpaque0(),null,ctx_,i_,null,st_);
        call(new FctCompoOpaque1(),null,ctx_,i_,one(BooleanStruct.of(false)),st_);
        call(new FctCompoOpaque0(),null,ctx_,i_,null,st_);
        call(new FctCompoBack1(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoBack1(),null,ctx_,i_,one(new ColorStruct(0)),st_);
        call(new FctCompoBack0(),null,ctx_,i_,null,st_);
        call(new FctCompoFore1(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoFore1(),null,ctx_,i_,one(new ColorStruct(0)),st_);
        call(new FctCompoFore0(),null,ctx_,i_,null,st_);
        call(new FctCompoSetFont(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoGetFont(),null,ctx_,i_,null,st_);
        call(new FctCompoSetFont(),null,ctx_,i_,one(new FontStruct()),st_);
        call(new FctCompoGetFont(),null,ctx_,i_,null,st_);
        call(new FctCompoTop(),null,ctx_,i_,null,st_);
        call(new FctCompoLeft(),null,ctx_,i_,null,st_);
        call(new FctCompoRight(),null,ctx_,i_,null,st_);
        call(new FctCompoBottom(),null,ctx_,i_,null,st_);
        call(new FctCompoCentHoriz(),null,ctx_,i_,null,st_);
        call(new FctCompoCentVert(),null,ctx_,i_,null,st_);
        call(new FctCompoSetSize(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoSetSize(),null,ctx_,i_,one(new DimensionStruct(1,1)),st_);
        call(new FctCompoSetPreferredSize(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoGetPreferredSize(),null,ctx_,i_,null,st_);
        call(new FctCompoSetPreferredSize(),null,ctx_,i_,one(new DimensionStruct(1,1)),st_);
        call(new FctCompoGetPreferredSize(),null,ctx_,i_,null,st_);
        call(new FctCompoSetPaint(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        call(new FctCompoGetPaint(),null,ctx_,i_,null,st_);
        call(new FctCompoRequestFocus(),null,ctx_,i_,null,st_);
        call(new FctCompoBorLower(),null,ctx_,i_,null,st_);
        call(new FctCompoBorRaise(),null,ctx_,i_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void addMouse1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddMouseListener(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((CustComponentStruct)i_).getComponent().getMouseListeners().size());
    }
    @Test
    public void addMouse2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddMouseListener(),null,ctx_,i_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((CustComponentStruct)i_).getComponent().getMouseListeners().size());
    }
    @Test
    public void removeMouse1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddMouseListener(),null,ctx_,i_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctCompoRemoveMouseListener(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((CustComponentStruct)i_).getComponent().getMouseListeners().size());
    }
    @Test
    public void removeMouse2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoAddMouseListener(),null,ctx_,i_,one(list_),st_);
        call(new FctCompoRemoveMouseListener(),null,ctx_,i_,one(list_),st_);
        assertEq(0,((CustComponentStruct)i_).getComponent().getMouseListeners().size());
    }
    @Test
    public void mice1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoAddMouseListener(),null,ctx_,i_,one(list_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctCompoGetMouseListeners(), null, ctx_, i_, null, st_);
        assertEq(2,a_.getLength());
        assertSame(list_,a_.get(0));
        assertSame(list_,a_.get(1));
    }
    @Test
    public void mice2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        ((CustComponentStruct)i_).getComponent().addMouseListener((AbsMouseListener) null);
        ((CustComponentStruct)i_).getComponent().addMouseMotionListener(null);
        ArrayStruct a_ = (ArrayStruct) call(new FctCompoGetMouseListeners(), null, ctx_, i_, null, st_);
        assertEq(0,a_.getLength());
    }
    @Test
    public void addKey1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddKeyListener(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((CustComponentStruct)i_).getComponent().getKeyListeners().size());
    }
    @Test
    public void addKey2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddKeyListener(),null,ctx_,i_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((CustComponentStruct)i_).getComponent().getKeyListeners().size());
    }
    @Test
    public void removeKey1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddKeyListener(),null,ctx_,i_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctCompoRemoveKeyListener(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((CustComponentStruct)i_).getComponent().getKeyListeners().size());
    }
    @Test
    public void removeKey2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoAddKeyListener(),null,ctx_,i_,one(list_),st_);
        call(new FctCompoRemoveKeyListener(),null,ctx_,i_,one(list_),st_);
        assertEq(0,((CustComponentStruct)i_).getComponent().getKeyListeners().size());
    }
    @Test
    public void keyes1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoAddKeyListener(),null,ctx_,i_,one(list_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctCompoGetKeyListeners(), null, ctx_, i_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(list_,a_.get(0));
    }
    @Test
    public void keyes2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        ((CustComponentStruct)i_).getComponent().addKeyListener(null);
        ArrayStruct a_ = (ArrayStruct) call(new FctCompoGetKeyListeners(), null, ctx_, i_, null, st_);
        assertEq(0,a_.getLength());
    }
    @Test
    public void addWheel1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddWheelListener(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((CustComponentStruct)i_).getComponent().getKeyListeners().size());
    }
    @Test
    public void addWheel2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddWheelListener(),null,ctx_,i_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((CustComponentStruct)i_).getComponent().getMouseWheelListeners().size());
    }
    @Test
    public void removeWheel1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        call(new FctCompoAddWheelListener(),null,ctx_,i_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctCompoRemoveWheelListener(),null,ctx_,i_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((CustComponentStruct)i_).getComponent().getMouseWheelListeners().size());
    }
    @Test
    public void removeWheel2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoAddWheelListener(),null,ctx_,i_,one(list_),st_);
        call(new FctCompoRemoveWheelListener(),null,ctx_,i_,one(list_),st_);
        assertEq(0,((CustComponentStruct)i_).getComponent().getMouseWheelListeners().size());
    }
    @Test
    public void will1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoAddWheelListener(),null,ctx_,i_,one(list_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctCompoGetWheelListeners(), null, ctx_, i_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(list_,a_.get(0));
    }
    @Test
    public void will2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct i_ = call(new FctImageLabel1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(0), new IntStruct(0), BooleanStruct.of(true)), st_)), st_);
        ((CustComponentStruct)i_).getComponent().addMouseWheelListener(null);
        ArrayStruct a_ = (ArrayStruct) call(new FctCompoGetWheelListeners(), null, ctx_, i_, null, st_);
        assertEq(0,a_.getLength());
    }
}
