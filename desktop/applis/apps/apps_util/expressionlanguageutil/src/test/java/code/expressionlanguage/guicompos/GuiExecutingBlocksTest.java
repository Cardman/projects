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
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class GuiExecutingBlocksTest extends EquallableElUtUtil {
    @Test
    public void message1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmOk0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmOk1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmFull0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmFull1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmYesNo0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmYesNo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmField0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmField1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmMessage0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctConfirmMessage1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void message11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmOk0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,four(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmOk0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,four(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmOk1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,five(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmOk1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,five(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmFull0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmFull0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmFull1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmFull1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmYesNo0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,five(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmYesNo0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,five(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmYesNo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message22() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmYesNo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmField0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message24() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmField0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message25() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmField1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message26() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmField1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message27() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmMessage0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message28() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmMessage0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message29() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctConfirmMessage1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message30() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctConfirmMessage1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message31() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmOk1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,five(img_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message32() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmOk1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,five(img_,d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message33() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmFull1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(img_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message34() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmFull1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(img_,d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message35() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmYesNo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(img_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message36() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmYesNo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,six(img_,d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message37() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmField1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(img_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message38() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmField1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(img_,d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message39() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmMessage1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(img_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void message40() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        stds_.getGuiExecutingBlocks().initEventClose((GuiContextEl) ctx_);
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct img_ = call(new FctImage(stds_.getGuiExecutingBlocks()), null, ctx_, null, three(new IntStruct(2), new IntStruct(3), BooleanStruct.of(false)), st_);
        call(new FctConfirmMessage1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,seven(img_,d_,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
}
