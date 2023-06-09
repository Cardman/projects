package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
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

public final class RadioButtonStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio2(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(NullStruct.NULL_VALUE,BooleanStruct.of(true)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio2(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(new StringStruct(""),BooleanStruct.of(true)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio2(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(NullStruct.NULL_VALUE,BooleanStruct.of(false)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctRadio2(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(new StringStruct(""),BooleanStruct.of(false)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void text() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctRadioSetText(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctRadioSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        call(new FctRadioAddChange(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctRadioAddChange(),null,ctx_,r_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq("_",call(new FctRadioGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selected1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctRadioSetSelected(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctRadioIsSelected(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selected2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctRadio1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctRadioSetSelected(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctRadioIsSelected(),null,ctx_,ls_,null,st_));
    }
}
