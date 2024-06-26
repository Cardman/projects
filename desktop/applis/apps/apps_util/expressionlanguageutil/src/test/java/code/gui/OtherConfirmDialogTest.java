package code.gui;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.expressionlanguage.utilcompo.MathAdvAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.sml.util.TranslationsFile;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class OtherConfirmDialogTest extends EquallableElUtUtil {
    @Test
    public void yesNo1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogYesNo(pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.YES_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNo2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogYesNo(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(2);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.NO_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNo3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogYesNo(pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.NO_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNo4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogYesNo(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.YES_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialog(pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.YES_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialog(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(2);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.NO_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialog(pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(2);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.CANCEL_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialog(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.YES_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialog(pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.NO_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialog(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","yes","no","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(3);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(OtherConfirmDialog.CANCEL_OPTION,conf_.getAnswer());
    }
    @Test
    public void ok1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogOk(pr_.getLightFrameFactory().newOtherFrame(),"message","title","ok");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(GuiConstants.OK_OPTION,conf_.getAnswer());
    }
    @Test
    public void ok2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogOk(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","ok");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(GuiConstants.OK_OPTION,conf_.getAnswer());
    }
    @Test
    public void ok3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMiniDialogOk(pr_.getLightFrameFactory().newOtherFrame(),"message","title","ok");
        ((MockWindow)conf_.getDialog()).getWindowListenersDef().get(0).windowClosing();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(GuiConstants.OK_OPTION,conf_.getAnswer());
    }
    @Test
    public void showTextField1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showTextField(pr_.getLightFrameFactory().newOtherFrame(),"message","title","en","ok","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(1);
        field_.setText("_");
        MockPanel buttons_ = (MockPanel) pane_.getComponent(2);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq("_",conf_.textValue().getTypedText());
        assertEq(GuiConstants.YES_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showTextField2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showTextField(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","en","ok","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(2);
        field_.setText("_");
        MockPanel buttons_ = (MockPanel) pane_.getComponent(3);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq("",conf_.textValue().getTypedText());
        assertEq(GuiConstants.NO_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showTextField3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showTextField(pr_.getLightFrameFactory().newOtherFrame(),"message","title","en","ok","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(1);
        field_.setText("_");
        MockPanel buttons_ = (MockPanel) pane_.getComponent(2);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq("",conf_.textValue().getTypedText());
        assertEq(GuiConstants.NO_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showTextField4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showTextField(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","en","ok","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(2);
        field_.setText("_");
        MockPanel buttons_ = (MockPanel) pane_.getComponent(3);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
        assertEq("_",conf_.textValue().getTypedText());
        assertEq(GuiConstants.YES_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showTextField5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showTextField(pr_.getLightFrameFactory().newOtherFrame(),"message","title","en","ok","cancel");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(1);
        field_.setText("_");
        ((MockWindow)conf_.getDialog()).getWindowListenersDef().get(0).windowClosing();
        assertFalse(conf_.getDialog().isVisible());
        assertEq(GuiConstants.NO_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showMessage1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMessage(pr_.getLightFrameFactory().newOtherFrame(),"message","title","ok");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
    }
    @Test
    public void showMessage2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMessage(pr_.getImageFactory().newImageArgb(1,1),pr_.getLightFrameFactory().newOtherFrame(),"message","title","ok");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
    }
    @Test
    public void showMessage3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMessage(pr_.getLightFrameFactory().newOtherDialog(),"message","title","ok");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
    }
    @Test
    public void showMessage4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        OtherConfirmDialog conf_ = new OtherConfirmDialog(pr_);
        conf_.showMessage(null,"message","title","ok");
        AbsPanel pane_ = conf_.getDialog().getContentPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getDialog().isVisible());
    }
    @Test
    public void print() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        MathAdvAliases.en(en_);
        StringMap<String> mapp_ = TranslationsFile.extractMap(en_);
        StringMap<String> keys_ = TranslationsFile.extractKeys(en_);
        stds_.getExecContent().getCustAliases().getMathAdvAliases().build(mapp_,new StringMap<String>(), keys_);
        stds_.getCoreNames().setObjType(new StandardClass("_._",new CustList<CstFieldInfo>(),new CustList<StandardConstructor>(),new CustList<StandardMethod>(),"", MethodModifier.NORMAL));
        stds_.getContent().getCharSeq().setAliasString("");
        Options opt_ = new Options();
        ContextEl c_ = new AdvContextGenerator(new ConcreteBoolean()).geneWith(getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE, InitPhase.READ_ONLY_OTHERS);
        assertFalse(StringUtil.nullToEmpty(stds_.getStrAlias().getAliasStringSegment()+"_").isEmpty());
        assertFalse(StringUtil.nullToEmpty(stds_.getAliasLgInt()+"_").isEmpty());
        assertFalse(StringUtil.nullToEmpty(stds_.getAliasRate()+"_").isEmpty());
        ((InterruptibleContextEl)c_).stopJoinSleep();
        assertTrue(c_.callsOrException(st_));
    }
}
