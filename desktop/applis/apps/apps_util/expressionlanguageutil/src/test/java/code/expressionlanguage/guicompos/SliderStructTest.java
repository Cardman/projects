package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
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
import org.junit.Test;

public final class SliderStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
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
        call(new FctSlider1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(new IntStruct(GuiConstants.HORIZONTAL)),st_);
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
        call(new FctSlider2(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(new IntStruct(5),new IntStruct(95)),st_);
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
        call(new FctSlider3(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,three(new IntStruct(5),new IntStruct(95),new IntStruct(35)),st_);
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
        call(new FctSlider4(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,four(new IntStruct(GuiConstants.HORIZONTAL),new IntStruct(5),new IntStruct(95),new IntStruct(35)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void orient1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        assertEq(GuiConstants.HORIZONTAL,toLong(call(new FctSliderGetOrientation(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void orient2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.VERTICAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        assertEq(GuiConstants.VERTICAL,toLong(call(new FctSliderGetOrientation(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void orient3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctSliderSetOrientation(),null,ctx_,sl_,one(new IntStruct(GuiConstants.VERTICAL)),st_);
        assertEq(GuiConstants.VERTICAL,toLong(call(new FctSliderGetOrientation(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void orient4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.VERTICAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctSliderSetOrientation(),null,ctx_,sl_,one(new IntStruct(GuiConstants.HORIZONTAL)),st_);
        assertEq(GuiConstants.HORIZONTAL,toLong(call(new FctSliderGetOrientation(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void addAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctSliderAddChange(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((SliderStruct)t_).getSlider().getChangeListeners().size());
    }
    @Test
    public void addAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctSliderAddChange(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((SliderStruct)t_).getSlider().getChangeListeners().size());
    }
    @Test
    public void addAction3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stackLogger(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctSliderAddChange(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((SliderStruct)t_).getSlider().getChangeListeners().size());
    }
    @Test
    public void removeAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctSliderAddChange(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctSliderRemoveChange(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((SliderStruct)t_).getSlider().getChangeListeners().size());
    }
    @Test
    public void removeAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctSliderAddChange(),null,ctx_,t_,one(list_),st_);
        call(new FctSliderRemoveChange(),null,ctx_,t_,one(list_),st_);
        assertEq(0,((SliderStruct)t_).getSlider().getChangeListeners().size());
    }
    @Test
    public void removeAction3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stackLogger(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctSliderAddChange(),null,ctx_,t_,one(list_),st_);
        call(new FctSliderRemoveChange(),null,ctx_,t_,one(list_),st_);
        assertEq(0,((SliderStruct)t_).getSlider().getChangeListeners().size());
    }
    @Test
    public void actions() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctSlider0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct li_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctSliderAddChange(),null,ctx_,t_,one(li_),st_);
        ((SliderStruct)t_).getSlider().addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(pr_,""),0));
        ArrayStruct a_ = (ArrayStruct) call(new FctSliderGetChanges(), null, ctx_, t_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(li_,a_.get(0));
    }
    @Test
    public void min() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctSliderSetMin(""),null,ctx_,sl_,one(new IntStruct(3)),st_);
        assertEq(3,toLong(call(new FctSliderGetMin(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void max() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctSliderSetMax(""),null,ctx_,sl_,one(new IntStruct(98)),st_);
        assertEq(98,toLong(call(new FctSliderGetMax(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void value() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct sl_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctSliderSetValue(""),null,ctx_,sl_,one(new IntStruct(55)),st_);
        assertEq(55,toLong(call(new FctSliderGetValue(),null,ctx_,sl_,null,st_)));
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSlider4(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(new IntStruct(GuiConstants.HORIZONTAL), new IntStruct(5), new IntStruct(95), new IntStruct(35)), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selectDbg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,60);g.addChange((ChangeListener)(:void)->{g;});g.setValue(60);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,60);g.addChange((ChangeListener)(:void)->{g;});g.setValue(75);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(113,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(120,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void selectDbg3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,15);g.addChange((ChangeListener)(:void)->{g;});g.setValue(10);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,120);g.addChange((ChangeListener)(:void)->{g;});g.setValue(130);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 114);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,60);g.addChange((ChangeListener)(:void)->{g;});g.setMin(15);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,60);g.addChange((ChangeListener)(:void)->{g;});g.setMin(20);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(113,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(120,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
    }
    @Test
    public void selectDbg7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,60);g.addChange((ChangeListener)(:void)->{g;});g.setMax(120);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){Slider g = new(15,120,60);g.addChange((ChangeListener)(:void)->{g;});g.setMax(125);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        ctx_.toggleBreakPoint("src/sample.txt", 113);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(4,dbg_.getStack().nbPages());
        assertEq(113,dbg_.getStack().getCall(3).getTraceIndex());
        assertEq(120,dbg_.getStack().getCall(0).getTraceIndex());
        assertEq(0,dbgContinueNormalValue(dbg_.getStack(),ctx_.getContext()).getStack().nbPages());
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
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getGuiAliases().changeListener(_definedKw, _definedLgNames.getContent()));
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
        StandardClass input_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasInput(), fields_, constructors_, methods_, _definedLgNames.getGuiAliases().getAliasComponent(), StdClassModifier.ABSTRACT);
        input_.addSuperStdTypes(component_);
        input_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getGuiAliases().getAliasInput(), input_);
        _definedLgNames.getGuiAliases().slider(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), _definedLgNames.getGuiExecutingBlocks(), component_, input_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getGuiExecutingBlocks().changeListener(stds_.getGuiAliases(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
}
