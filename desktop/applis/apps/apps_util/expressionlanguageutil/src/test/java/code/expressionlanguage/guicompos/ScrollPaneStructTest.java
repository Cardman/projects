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

public final class ScrollPaneStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new DfScrollPane(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, st_);
        call(new FctScrollPaneValidate(),null,ctx_,spl_,null,st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,null, st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(NullStruct.NULL_VALUE), st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct panel_ = call(new FctTabbedPane(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label_),st_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(label_), st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(label_), st_);
        assertSame(label_,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void view1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(NullStruct.NULL_VALUE), st_);
        call(new FctScrollPaneSetView(),null,ctx_,spl_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }

    @Test
    public void view2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(label_), st_);
        call(new FctScrollPaneSetView(),null,ctx_,spl_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void view3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(NullStruct.NULL_VALUE), st_);
        call(new FctScrollPaneSetView(),null,ctx_,spl_,one(label_),st_);
        assertSame(label_,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void view4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct label_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(label_), st_);
        call(new FctScrollPaneSetView(),null,ctx_,spl_,one(label2_),st_);
        assertSame(label2_,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void view5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct panel_ = call(new FctTabbedPane(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(0),new IntStruct(0)), st_);
        Struct label_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct label2_ = call(new FctImageLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct spl_ = call(new FctScrollPane1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,one(label_), st_);
        call(new FctTabbedPaneAdd(),null,ctx_,panel_,two(new StringStruct(""),label2_),st_);
        call(new FctScrollPaneSetView(),null,ctx_,spl_,one(label2_),st_);
        assertSame(label_,call(new FctScrollPaneGetView(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void horiz() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,null, st_);
        call(new FctScrollPaneHorizontalValue1(),null,ctx_,spl_,one(new IntStruct(5)),st_);
        assertEq(5,toLong(call(new FctScrollPaneHorizontalValue0(),null,ctx_,spl_,null,st_)));
    }
    @Test
    public void vert() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,null, st_);
        call(new FctScrollPaneVerticalValue1(),null,ctx_,spl_,one(new IntStruct(5)),st_);
        assertEq(5,toLong(call(new FctScrollPaneVerticalValue0(),null,ctx_,spl_,null,st_)));
    }

    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,null, st_);
        call(new FctCompoSetVisible(),null,ctx_,spl_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,spl_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct spl_ = call(new FctScrollPane0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null,null, st_);
        call(new FctCompoSetVisible(),null,ctx_,spl_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,spl_,null,st_));
    }
}
