package code.expressionlanguage;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.StringList;
import org.junit.Test;

public final class AddonClassesTest extends EquallableElUtUtil {
    @Test
    public void executorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        stds_.getExecContent().getCustAliases().setAliasExecutorService("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasExecutorService(),new ExecutorServiceStruct(pr_.getThreadFactory()).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void futureStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(pr_.getThreadFactory(),2);
        ArgumentListCall list_ = one(new MockRunnableStruct(""));
        stds_.getExecContent().getCustAliases().setAliasFuture("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasFuture(),call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void strMap() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        stds_.getExecContent().getCustAliases().setAliasTableStringObject("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasTableStringObject(),call(new FctTastr(new MockInterceptor()),null,null,null, null,null).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void key() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        stds_.getExecContent().getCustAliases().setAliasEntryStringObject("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasEntryStringObject(),entry_.getClassName(ctx_));
    }
    @Test
    public void scheduledExecutorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        stds_.getExecContent().getCustAliases().setAliasScheduledExecutorService("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasScheduledExecutorService(),new ScheduledExecutorServiceStruct(pr_.getThreadFactory()).getClassName(gene(stds_,opt_)));
    }
    @Test
    public void thread() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasThread("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasThread(),call(new FctThread(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void threadSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasThreadSet("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasThreadSet(),call(new FctThreadSet(new MockInterceptor()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void entryText() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasEntryText("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasEntryText(),call(new FctEntryText(),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void entryBinary() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getExecContent().getCustAliases().setAliasEntryBinary("");
        assertEq(stds_.getExecContent().getCustAliases().getAliasEntryBinary(),call(new FctEntryBinary(),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void color() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasColor("");
        assertEq(stds_.getGuiAliases().getAliasColor(),call(new FctColor0(),null,ctx_,null,one(new IntStruct(0)),st_).getClassName(ctx_));
    }
    @Test
    public void font() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasFont("");
        assertEq(stds_.getGuiAliases().getAliasFont(),call(new FctFont0(),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void image() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasImage("");
        assertEq(stds_.getGuiAliases().getAliasImage(),call(new FctImage(stds_.getGuiExecutingBlocks()),null,ctx_,null,three(new IntStruct(1),new IntStruct(1),BooleanStruct.of(false)),st_).getClassName(ctx_));
    }
    @Test
    public void compo() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        assertEq("_",call(new FctImageLabel0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),"_"),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void render() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasRender("");
        assertEq(stds_.getGuiAliases().getAliasRender(),call(new FctRender(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void dims() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasDimension("");
        assertEq(stds_.getGuiAliases().getAliasDimension(),call(new FctDimension1(),null,ctx_,null,two(new IntStruct(1),new IntStruct(1)),st_).getClassName(ctx_));
    }
    @Test
    public void treeNode() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasTreeNode("");
        assertEq(stds_.getGuiAliases().getAliasTreeNode(),call(new FctTreeNode0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void buttonGroup() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasButtonGroup("");
        assertEq(stds_.getGuiAliases().getAliasButtonGroup(),call(new FctButtonGroup(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenuItem("");
        assertEq(stds_.getGuiAliases().getAliasMenuItem(),call(new FctMenuItem0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenuItemCheck("");
        assertEq(stds_.getGuiAliases().getAliasMenuItemCheck(),call(new FctMenuItemCheck0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenu("");
        assertEq(stds_.getGuiAliases().getAliasMenu(),call(new FctMenu0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void menu4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasMenuBar("");
        assertEq(stds_.getGuiAliases().getAliasMenuBar(),call(new FctMenuBar(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void window1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasDialog("");
        assertEq(stds_.getGuiAliases().getAliasDialog(),call(new FctDialog(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void window2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasFrame("");
        assertEq(stds_.getGuiAliases().getAliasFrame(),call(new FctFrame(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
    @Test
    public void windowSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSampleCl(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        stds_.getGuiAliases().setAliasWindowSet("");
        assertEq(stds_.getGuiAliases().getAliasWindowSet(),call(new FctWindowSet(stds_.getExecContent().getCustAliases()),null,ctx_,null,null,st_).getClassName(ctx_));
    }
}
