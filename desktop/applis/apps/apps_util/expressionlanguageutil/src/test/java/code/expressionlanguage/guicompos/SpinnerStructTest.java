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

public final class SpinnerStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerAddChange(),null,ctx_,sl_,one(NullStruct.NULL_VALUE),st_);
        call(new FctSpinnerAddChange(),null,ctx_,sl_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void min() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerSetMin(),null,ctx_,sl_,one(new IntStruct(3)),st_);
        assertEq(3,toLong(call(new FctSpinnerGetMin(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void max() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerSetMax(),null,ctx_,sl_,one(new IntStruct(98)),st_);
        assertEq(98,toLong(call(new FctSpinnerGetMax(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void value() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerSetValue(),null,ctx_,sl_,one(new IntStruct(55)),st_);
        assertEq(55,toLong(call(new FctSpinnerGetValue(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void step() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerSetStep(),null,ctx_,sl_,one(new IntStruct(4)),st_);
        assertEq(4,toLong(call(new FctSpinnerGetStep(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void range() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerSetRange(),null,ctx_,sl_,two(new IntStruct(3),new IntStruct(98)),st_);
        assertEq(3,toLong(call(new FctSpinnerGetMin(),null,ctx_,sl_,null,st_)));
        assertEq(98,toLong(call(new FctSpinnerGetMax(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void rangeValue() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctSpinnerSetRangeValue(),null,ctx_,sl_,three(new IntStruct(55),new IntStruct(3),new IntStruct(98)),st_);
        assertEq(3,toLong(call(new FctSpinnerGetMin(),null,ctx_,sl_,null,st_)));
        assertEq(55,toLong(call(new FctSpinnerGetValue(),null,ctx_,sl_,null,st_)));
        assertEq(98,toLong(call(new FctSpinnerGetMax(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctCompoToolTip1(),null,ctx_,sl_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,sl_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctCompoToolTip1(),null,ctx_,sl_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,sl_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctCompoSetVisible(),null,ctx_,sl_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,sl_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctCompoSetVisible(),null,ctx_,sl_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,sl_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctInputSetEnabled(),null,ctx_,sl_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEnabled(),null,ctx_,sl_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSpinner(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(71),new IntStruct(5),new IntStruct(95),new IntStruct(3)), st_);
        call(new FctInputSetEnabled(),null,ctx_,sl_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,sl_,null,st_));
    }
}
