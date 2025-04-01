package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class DbgActTest extends EquallableElAdvUtil {
    @Test
    public void bpRes() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        save(b_,src_,"file2.txt","[({)}><]");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),55));
    }
    @Test
    public void bp0() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(59,59);
        toggleBp(b_);
        assertFalse(curRet(b_).is(file(curRet(b_)),59));
    }
    @Test
    public void bp1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),55));
    }
    @Test
    public void bp2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        toggleBp(b_);
        assertFalse(curRet(b_).is(file(curRet(b_)),55));
    }
    @Test
    public void bp3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiNoAna(b_,o_);
        assertTrue(b_.getCommonFrame().isVisible());
    }
    @Test
    public void bp4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBpEn(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),55));
    }
    @Test
    public void bp5() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiNoAna(b_,o_);
        assertTrue(b_.getCommonFrame().isVisible());
    }
    @Test
    public void bp6() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(false);
//        bpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertTrue(curRet(b_).is(file(curRet(b_)),55));
    }
    @Test
    public void bp7() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(false);
        bpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertFalse(curRet(b_).is(file(curRet(b_)),55));
    }
    @Test
    public void bp8() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getInstanceType().setSelected(false);
//        bpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertTrue(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isInstanceType());
        assertFalse(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isStaticType());
    }
    @Test
    public void bp9() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getInstanceType().setSelected(false);
        tpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertFalse(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isInstanceType());
        assertTrue(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isStaticType());
    }
    @Test
    public void bp10() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(false);
        b_.getFramePoints().getFrameTpFormContent().getInstanceType().setSelected(true);
        tpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getInstanceType().setSelected(false);
//        bpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertTrue(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isInstanceType());
        assertFalse(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isStaticType());
    }
    @Test
    public void bp11() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(false);
        b_.getFramePoints().getFrameTpFormContent().getInstanceType().setSelected(true);
        tpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getInstanceType().setSelected(false);
        tpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertFalse(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isInstanceType());
        assertTrue(curRet(b_).getPairType(file(curRet(b_)),13).getValue().isStaticType());
    }
    @Test
    public void bp12() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        bpForm(b_);
        assertFalse(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp13() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiNoAna(b_,o_);
        assertTrue(b_.getCommonFrame().isVisible());
    }
    @Test
    public void bp14() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        bpFormStdAddExc(b_);
        bpFormStdAddInc(b_);
        StackConstraintsForm.remove(new CustList<AbsCallContraints>(),null);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp15() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp16() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp17() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp18() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddInc(b_);
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp19() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp20() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp21() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp22() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp23() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormStdRemExc(b_,0);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp24() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        bpFormStdRemInc(b_,0);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp25() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp26() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp27() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getSingleCaret().setSelected(false);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp28() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getSingleCaret().setSelected(false);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp29() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(56,56);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormOk(b_);
        bpForm(b_);
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp30() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(56,56);
        toggleBp(b_);
        bpForm(b_);
        AbsTreeGui t_ = b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        bpFormOk(b_);
        bpForm(b_);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getMustNotBe().size());
    }
    @Test
    public void bp31() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(21,21);
        toggleWp(b_);
        wpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(false);
//        mpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp32() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(21,21);
        toggleWp(b_);
        wpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(false);
        mpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp33() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(21,21);
        wpForm(b_);
        assertFalse(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp34() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(39,39);
        toggleWp(b_);
        wpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(false);
        wpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp35() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(75,75);
        toggleWp(b_);
        assertFalse(curRet(b_).is(file(curRet(b_)),75));
        wpForm(b_);
        assertFalse(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp36() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(39,39);
        toggleWpEn(b_);
        wpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(false);
        wpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp37() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(39,39);
        toggleWp(b_);
        wpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(false);
//        wpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp38() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp39() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioFalse(b_);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp40() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Inex");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        assertFalse(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp41() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        b_.getFramePoints().getFrameExcFormContent().getPropagated().setSelected(true);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().next().getValue().isPropagated());
        editExc(b_,0);
        b_.getFramePoints().getFrameExcFormContent().getPropagated().setSelected(false);
        addExcOk(b_);
        assertFalse(curRet(b_).getContext().excList().elts().iterator().next().getValue().isPropagated());
    }
    @Test
    public void bp42() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        b_.getFramePoints().getFrameExcFormContent().getPropagated().setSelected(true);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().next().getValue().isPropagated());
        editExc(b_,0);
        addExcRemove(b_);
        assertFalse(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp43() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp44() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(26,26);
        toggleWp(b_);
        wpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(false);
        wpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp45() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_,"",null);
        assertFalse(curRet(b_).getContext().stdList().elts().iterator().hasNext());
        assertSame(b_.getFramePoints().getFrameStdFormContent().rootTree(),b_.getFramePoints().getFrameStdFormContent().node(null));
    }
    @Test
    public void bp46() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getAliasBoolean(),null);
        assertFalse(curRet(b_).getContext().stdList().elts().iterator().hasNext());
    }
    @Test
    public void bp47() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getAliasObject(),getConstructorId(true));
        assertFalse(curRet(b_).getContext().stdList().elts().iterator().hasNext());
    }
    @Test
    public void bp48() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getAliasObject(),getConstructorId(false));
        assertTrue(curRet(b_).getContext().stdList().elts().iterator().hasNext());
    }
    @Test
    public void bp49() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getAliasObject(),getConstructorId(false));
        editStd(b_,0);
        addStdRemove(b_);
        assertFalse(curRet(b_).getContext().stdList().elts().iterator().hasNext());
    }
    @Test
    public void bp50() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getAliasObject(),getConstructorId(false));
        editStd(b_,0);
        addStdOk(b_);
        assertTrue(curRet(b_).getContext().stdList().elts().iterator().hasNext());
    }
    @Test
    public void bp51() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp52() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        addWpOk(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp53() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp54() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        assertFalse(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp55() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        addWpOk(b_);
        assertFalse(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp56() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        assertFalse(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp57() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        editWatch(b_,0);
        addWatchRemove(b_);
        assertFalse(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp58() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        addWpOk(b_);
        editWatchAnnot(b_,0);
        addWatchRemove(b_);
        assertFalse(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp59() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        editWatch(b_,0);
        addWatchRemove(b_);
        assertFalse(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp60() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(41);
        addMpOk(b_);
        assertTrue(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp61() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("");
        addMpOk(b_);
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp62() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(41);
        addMpOk(b_);
        editMethod(b_,0);
        addMethodRemove(b_);
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp63() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(75);
        addBpOk(b_);
        assertTrue(curRet(b_).bpList().elts().iterator().hasNext());
    }
    @Test
    public void bp64() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("");
        addBpOk(b_);
        assertFalse(curRet(b_).bpList().elts().iterator().hasNext());
    }
    @Test
    public void bp65() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(75);
        addBpOk(b_);
        editBp(b_,0);
        addBpRemove(b_);
        assertFalse(curRet(b_).bpList().elts().iterator().hasNext());
    }
    @Test
    public void bp66() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        closeReadOnlyTab(b_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp67() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();}");
        guiAna(r_,b_,o_,src_);
        closeReadOnlyTab(b_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        addWpOk(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp68() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        tabEditor(b_).getCenter().select(63,63);
        toggleBpEn(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),63));
    }
    @Test
    public void bp69() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(false);
        addBpOk(b_);
        editBp(b_,0);
        tabEditor(b_).getCenter().select(63,63);
        toggleBpEn(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),63));
        assertTrue(b_.getFramePoints().getFrameBpFormContent().getEnabledBp().isSelected());
    }
    @Test
    public void bp70() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        addBpOk(b_);
        editBp(b_,0);
        tabEditor(b_).getCenter().select(63,63);
        toggleBpEn(b_);
        assertFalse(curRet(b_).is(file(curRet(b_)),63));
        assertFalse(b_.getFramePoints().getFrameBpFormContent().getEnabledBp().isSelected());
    }
    @Test
    public void bp71() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(52);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        addBpOk(b_);
        editBp(b_,0);
        tabEditor(b_).getCenter().select(63,63);
        toggleBpEn(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),63));
        assertTrue(b_.getFramePoints().getFrameBpFormContent().getEnabledBp().isSelected());
    }
    @Test
    public void bp72() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        tabEditor(b_).getCenter().select(21,21);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().metList().elts().iterator().hasNext());
    }
    @Test
    public void bp73() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(false);
        addMpOk(b_);
        editMethod(b_,0);
        tabEditor(b_).getCenter().select(21,21);
        toggleWpEn(b_);
        assertTrue(b_.getFramePoints().getFrameFormContent().getEnabledMp().isSelected());
    }
    @Test
    public void bp74() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(true);
        addMpOk(b_);
        editMethod(b_,0);
        tabEditor(b_).getCenter().select(21,21);
        toggleWpEn(b_);
        assertFalse(b_.getFramePoints().getFrameFormContent().getEnabledMp().isSelected());
    }
    @Test
    public void bp75() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(true);
        addMpOk(b_);
        editMethod(b_,0);
        tabEditor(b_).getCenter().select(58,58);
        toggleWpEn(b_);
        assertTrue(b_.getFramePoints().getFrameFormContent().getEnabledMp().isSelected());
    }
    @Test
    public void bp76() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v,w;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        tabEditor(b_).getCenter().select(39,39);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
    }
    @Test
    public void bp77() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v,w;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(false);
        addWpOk(b_);
        editWatch(b_,0);
        tabEditor(b_).getCenter().select(39,39);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertTrue(b_.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
    }
    @Test
    public void bp78() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v,w;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatch(b_,0);
        tabEditor(b_).getCenter().select(39,39);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(b_.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
    }
    @Test
    public void bp79() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v,w;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatch(b_,0);
        tabEditor(b_).getCenter().select(41,41);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertTrue(b_.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
    }
    @Test
    public void bp80() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();int w();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        tabEditor(b_).getCenter().select(30,30);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
    }
    @Test
    public void bp81() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();int w();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(false);
        addWpOk(b_);
        editWatchAnnot(b_,0);
        tabEditor(b_).getCenter().select(30,30);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertTrue(b_.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
    }
    @Test
    public void bp82() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();int w();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatchAnnot(b_,0);
        tabEditor(b_).getCenter().select(30,30);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertFalse(b_.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
    }
    @Test
    public void bp83() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();int w();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatchAnnot(b_,0);
        tabEditor(b_).getCenter().select(38,38);
        toggleWpEn(b_);
        assertTrue(curRet(b_).getContext().watchList().elts().iterator().hasNext());
        assertTrue(b_.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
    }
    @Test
    public void bp84() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        addBpOk(b_);
        editBp(b_,0);
        tabEditor(b_).getCenter().select(63,63);
        toggleBp(b_);
        assertFalse(curRet(b_).is(file(curRet(b_)),63));
        assertNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp85() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        addBpOk(b_);
        editBp(b_,0);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        assertTrue(curRet(b_).is(file(curRet(b_)),63));
        assertNotNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp86() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(true);
        addMpOk(b_);
        editMethod(b_,0);
        tabEditor(b_).getCenter().select(21,21);
        toggleWp(b_);
        assertNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp87() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(false);
        addMpOk(b_);
        editMethod(b_,0);
        tabEditor(b_).getCenter().select(58,58);
        toggleWp(b_);
        assertNotNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp88() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v,w;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatch(b_,0);
        tabEditor(b_).getCenter().select(39,39);
        toggleWp(b_);
        assertNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp89() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v,w;}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatch(b_,0);
        tabEditor(b_).getCenter().select(41,41);
        toggleWp(b_);
        assertNotNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp90() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();int w();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatchAnnot(b_,0);
        tabEditor(b_).getCenter().select(30,30);
        toggleWp(b_);
        assertNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp91() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int v();int w();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        b_.getFramePoints().getFrameWpFormContent().getEnabledWp().setSelected(true);
        addWpOk(b_);
        editWatchAnnot(b_,0);
        tabEditor(b_).getCenter().select(38,38);
        toggleBp(b_);
        assertNotNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp92() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthBp(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        addTpOk(b_);
        assertNull(compo(b_.getFramePoints().getView()));
    }
    @Test
    public void bp93() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthBp(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().get(0).setSelected(true);
        addBpOk(b_);
        assertTrue(curRet(b_).getPair(file(curRet(b_)),63).getValue().getResultStd().getOthers().elts().iterator().hasNext());
        editBp(b_,1);
        editOthBp(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().get(0).setSelected(false);
        addBpOk(b_);
        assertFalse(curRet(b_).getPair(file(curRet(b_)),63).getValue().getResultStd().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp94() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioFalse(b_);
        addExcOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthExc(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(3,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp95() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthExc(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(3,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp96() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        addMpOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthMethod(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(2,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp97() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {int v;public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        addWpOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthWatch(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(5,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp98() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getAliasObject(),getConstructorId(false));
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthStd(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(2,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp99() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthTp(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().get(0).setSelected(true);
        addBpOk(b_);
        assertTrue(curRet(b_).getPair(file(curRet(b_)),63).getValue().getResultStd().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp100() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(63);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(false);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        addBpOk(b_);
        assertTrue(curRet(b_).getPair(file(curRet(b_)),63).getValue().getResultStd().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp101() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioFalse(b_);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        addExcOk(b_);
        assertTrue(curRet(b_).getPairExc("pkg.Ex", ExcPointBlockKey.SAME_FAMILY).getValue().getResultThrown().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp102() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        addExcOk(b_);
        assertTrue(curRet(b_).getPairExc("pkg.Ex",ExcPointBlockKey.SAME).getValue().getResultThrown().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp103() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addMpOk(b_);
        assertNull(compo(b_.getFramePoints().getView()));
        assertTrue(curRet(b_).getPair(MemberCallingsBlock.clName(curRet(b_).getPageEl().getAnaClassBody("pkg.Ex").getOverridableBlocks().get(0))).getValue().getResultEntry().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp104() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {int v;public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addWp(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Ex");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getGuiReadStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getGuiReadStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getGuiReadStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getGuiReadStackForm().getDependantPointsForm().getChecksCurrent().get(3).setSelected(true);
        b_.getFramePoints().getFrameWpFormContent().getGuiReadStackForm().getDependantPointsForm().getChecksCurrent().get(4).setSelected(true);
        addWpOk(b_);
        assertTrue(curRet(b_).getPairWatch(true,curRet(b_).getPageEl().getAnaClassBody("pkg.Ex").getNumberAll(),"v").getValue().getResultRead().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp105() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addStd(b_);
        b_.getFramePoints().getFrameStdFormContent().getGuiEnterStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameStdFormContent().getGuiEnterStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        selectStd(b_, curRet(b_).getPageEl().getAliasObject(),getConstructorId(false));
        assertNull(compo(b_.getFramePoints().getView()));
        assertTrue(curRet(b_).getPair(getConstructorId(false).look(curRet(b_).getPageEl().getStandardsTypes().getVal(curRet(b_).getPageEl().getAliasObject())).get(0)).getValue().getResultEntry().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp106() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        AbsPanel bs_ = b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getButtons();
        AbsButton last_ = (AbsButton) bs_.getComponent(bs_.getComponentCount()-1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("_");
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(1);
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addMpOk(b_);
        assertNull(compo(b_.getFramePoints().getView()));
        assertTrue(curRet(b_).getPair(MemberCallingsBlock.clName(curRet(b_).getPageEl().getAnaClassBody("pkg.Ex").getOverridableBlocks().get(0))).getValue().getResultEntry().getPrefs().elts().iterator().hasNext());
    }
    @Test
    public void bp107() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {int v;public static int exmeth(){int v=1;return v;}}public annotation pkg.Annot {int v();}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addWpAnnot(b_);
        b_.getFramePoints().getFrameWpFormContent().getClassName().setText("pkg.Annot");
        b_.getFramePoints().getFrameWpFormContent().getFieldName().setText("v");
        b_.getFramePoints().getFrameWpFormContent().getTrueField().setSelected(false);
        addWpOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthWatchAnnot(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(5,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp108() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioFalse(b_);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp109() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public annotation pkg.Ex {int exmeth();}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,curRet(b_).getPageEl().getCharSeq().getAliasSubstring(),false,curRet(b_).getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        addStd(b_);
        selectStd(b_, curRet(b_).getPageEl().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,curRet(b_).getPageEl().getCharSeq().getAliasSubstring(),false,curRet(b_).getContext().getStandards().getPrimTypes().getAliasPrimInteger(),curRet(b_).getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        assertTrue(curRet(b_).getContext().stdList().elts().iterator().hasNext());
    }
    @Test
    public void bp110() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex2");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp111() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex<T> {public static int v;public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex<int>");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex<long>");
        selectExcRadioTrue(b_);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp112() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp113() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioFalse(b_);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp114() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Inex");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        assertFalse(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp115() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        b_.getFramePoints().getFrameArrFormContent().getLength().setSelected(true);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().next().getValue().isLength());
        editArr(b_,0);
        b_.getFramePoints().getFrameArrFormContent().getLength().setSelected(false);
        addArrOk(b_);
        assertFalse(curRet(b_).getContext().arrList().elts().iterator().next().getValue().isLength());
    }
    @Test
    public void bp116() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        b_.getFramePoints().getFrameArrFormContent().getLength().setSelected(true);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().next().getValue().isLength());
        editArr(b_,0);
        addArrRemove(b_);
        assertFalse(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp117() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp118() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioFalse(b_);
        addArrOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthArr(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(13,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp119() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthArr(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(13,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp120() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioFalse(b_);
        b_.getFramePoints().getFrameArrFormContent().getGuiLengthStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntGetStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntSetStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntCompoundGetStackForm().getDependantPointsForm().getChecksCurrent().get(3).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntCompoundSetStackForm().getDependantPointsForm().getChecksCurrent().get(4).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntCompoundSetErrStackForm().getDependantPointsForm().getChecksCurrent().get(5).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeGetStackForm().getDependantPointsForm().getChecksCurrent().get(6).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeSetStackForm().getDependantPointsForm().getChecksCurrent().get(7).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeCompoundGetStackForm().getDependantPointsForm().getChecksCurrent().get(8).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeCompoundSetStackForm().getDependantPointsForm().getChecksCurrent().get(9).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntGetSetStackForm().getDependantPointsForm().getChecksCurrent().get(10).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiInitArrayStackForm().getDependantPointsForm().getChecksCurrent().get(11).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiInitArrayStackForm().getDependantPointsForm().getChecksCurrent().get(12).setSelected(true);
        addArrOk(b_);
        assertTrue(curRet(b_).getPairArr("[pkg.Ex",ExcPointBlockKey.SAME_FAMILY).getValue().getResultLength().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp121() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        b_.getFramePoints().getFrameArrFormContent().getGuiLengthStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntGetStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntSetStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntCompoundGetStackForm().getDependantPointsForm().getChecksCurrent().get(3).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntCompoundSetStackForm().getDependantPointsForm().getChecksCurrent().get(4).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntCompoundSetErrStackForm().getDependantPointsForm().getChecksCurrent().get(5).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeGetStackForm().getDependantPointsForm().getChecksCurrent().get(6).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeSetStackForm().getDependantPointsForm().getChecksCurrent().get(7).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeCompoundGetStackForm().getDependantPointsForm().getChecksCurrent().get(8).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiRangeCompoundSetStackForm().getDependantPointsForm().getChecksCurrent().get(9).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiIntGetSetStackForm().getDependantPointsForm().getChecksCurrent().get(10).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiInitArrayStackForm().getDependantPointsForm().getChecksCurrent().get(11).setSelected(true);
        b_.getFramePoints().getFrameArrFormContent().getGuiInitArrayStackForm().getDependantPointsForm().getChecksCurrent().get(12).setSelected(true);
        addArrOk(b_);
        assertTrue(curRet(b_).getPairArr("[pkg.Ex",ExcPointBlockKey.SAME).getValue().getResultLength().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp122() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioFalse(b_);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp123() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex2");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp124() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex<T> {public static int v;public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex<int>");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex<long>");
        selectArrRadioTrue(b_);
        addArrOk(b_);
        assertTrue(curRet(b_).getContext().arrList().elts().iterator().hasNext());
    }
    @Test
    public void bp125() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp126() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioFalse(b_);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp127() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Inex");
        selectParRadioTrue(b_);
        addParOk(b_);
        assertFalse(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp128() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        b_.getFramePoints().getFrameParFormContent().getGet().setSelected(true);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().next().getValue().isGet());
        editPar(b_,0);
        b_.getFramePoints().getFrameParFormContent().getGet().setSelected(false);
        addParOk(b_);
        assertFalse(curRet(b_).getContext().parList().elts().iterator().next().getValue().isGet());
    }
    @Test
    public void bp129() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        b_.getFramePoints().getFrameParFormContent().getGet().setSelected(true);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().next().getValue().isGet());
        editPar(b_,0);
        addParRemove(b_);
        assertFalse(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp130() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(b_.getFramePoints().getCommonFrame().isVisible());
    }
    @Test
    public void bp131() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioFalse(b_);
        addParOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthPar(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp132() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        addParOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthPar(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp133() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioFalse(b_);
        b_.getFramePoints().getFrameParFormContent().getGuiGetStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        addParOk(b_);
        assertTrue(curRet(b_).getPairPar("pkg.Ex",ExcPointBlockKey.SAME_FAMILY).getValue().getResultGet().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp134() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        b_.getFramePoints().getFrameParFormContent().getGuiGetStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        addParOk(b_);
        assertTrue(curRet(b_).getPairPar("pkg.Ex",ExcPointBlockKey.SAME).getValue().getResultGet().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp135() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        addParOk(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioFalse(b_);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp136() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        selectParRadioTrue(b_);
        addParOk(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex2");
        selectParRadioTrue(b_);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp137() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex<T> {public static int v;public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex<int>");
        selectParRadioTrue(b_);
        addParOk(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex<long>");
        selectParRadioTrue(b_);
        addParOk(b_);
        assertTrue(curRet(b_).getContext().parList().elts().iterator().hasNext());
    }
    @Test
    public void bp138() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addOperNatCompo(b_);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSecond().setText("int");
        addOperNatCompoOk(b_);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp139() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("");
        addOperNatOk(b_);
        assertFalse(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp140() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addOperNatCompo(b_);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSecond().setText("int");
        addOperNatCompoOk(b_);
        editOperCompoNat(b_,0);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
        addOperNatCompoRemove(b_);
        assertFalse(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp141() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("-");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("");
        addOperNatOk(b_);
        editOperNat(b_,0);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
        addOperNatRemove(b_);
        assertFalse(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp142() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addOperNatCompo(b_);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSecond().setText("int");
        addOperNatCompoOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthOperCompoNat(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(2,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp143() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("-");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("");
        addOperNatOk(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        editOthOperNat(b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecks().size());
    }
    @Test
    public void bp144() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addOperNatCompo(b_);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSecond().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addOperNatCompoOk(b_);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp145() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addOperNatCompo(b_);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSecond().setText("int");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addOperNatCompoOk(b_);
        editOperCompoNat(b_,0);
        addOperNatCompoOk(b_);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp146() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp147() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp148() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddExcGl(b_);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(2,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp149() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddIncGl(b_);
        assertEq(2,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp150() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(2,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp151() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        assertEq(2,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp152() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        bpFormStdAddExcGl(b_);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp153() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        bpFormStdAddIncGl(b_);
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp154() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        bpFormStdRemExcGl(b_,0);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp155() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        bpFormStdRemIncGl(b_,0);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp156() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp157() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        bpFormStdAddIncGl(b_);
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp158() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getSingleCaret().setSelected(false);
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddExcGl(b_);
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp159() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getSingleCaret().setSelected(false);
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddIncGl(b_);
        assertEq(1,b_.getFramePoints().getStackConstraintsForm().getMustBe().size());
        assertEq(0,b_.getFramePoints().getStackConstraintsForm().getMustNotBe().size());
    }
    @Test
    public void bp160() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExcGl(b_);
        b_.getFramePoints().getValidStack().getActionListeners().get(0).action();
        assertTrue(curRet(b_).getBreakPointsBlock().getExclude().elts().iterator().hasNext());
        assertFalse(curRet(b_).getBreakPointsBlock().getInclude().elts().iterator().hasNext());
    }
    @Test
    public void bp161() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int exmeth(){return 1;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        AbsTreeGui t_ = b_.getFramePoints().getStackConstraintsForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFramePoints().getStackConstraintsForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddIncGl(b_);
        b_.getFramePoints().getValidStack().getActionListeners().get(0).action();
        assertTrue(curRet(b_).getBreakPointsBlock().getInclude().elts().iterator().hasNext());
        assertFalse(curRet(b_).getBreakPointsBlock().getExclude().elts().iterator().hasNext());
    }
    @Test
    public void bp162() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioInehrit(b_);
        addExcOk(b_);
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp163() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        selectExcRadioInehrit(b_);
        addExcOk(b_);
        editExc(b_,0);
        assertTrue(b_.getFramePoints().getFrameExcFormContent().getExactForm().getInherit().isSelected());
        assertTrue(curRet(b_).getContext().excList().elts().iterator().hasNext());
    }
    @Test
    public void bp164() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(false);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addTpOk(b_);
        assertTrue(curRet(b_).getPairType(file(curRet(b_)),13).getValue().getResultInstance().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp165() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(26);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        addTpOk(b_);
        assertFalse(curRet(b_).getContext().getNotNullTypePair(file(curRet(b_)),26).getValue().isEnabled());
    }
    @Test
    public void bp166() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}public class pkg.Ex2 {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(80,80);
        toggleBp(b_);
        openPoints(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(false);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameTpFormContent().getGuiInsStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addTpOk(b_);
        assertTrue(curRet(b_).getPairType(file(curRet(b_)),13).getValue().getResultInstance().getOthers().elts().iterator().hasNext());
    }
    @Test
    public void bp167() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int v;public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        addTpOk(b_);
        editTp(b_,0);
        addTpRemove(b_);
        assertFalse(curRet(b_).tpList().elts().iterator().hasNext());
    }
    @Test
    public void bp168() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addOperNatCompo(b_);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getFirst().setText("");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getSecond().setText("");
        b_.getFramePoints().getFrameOperNatCompoFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameOperNatCompoFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addOperNatCompoOk(b_);
        assertFalse(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp169() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(52,52);
        toggleBp(b_);
        openPoints(b_);
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("");
        b_.getFramePoints().getFrameOperNatFormContent().getGuiSimpleStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        addOperNatOk(b_);
        editOperNat(b_,0);
        addOperNatOk(b_);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
    }
    @Test
    public void bp170() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addMp(b_);
        b_.getFramePoints().getFrameFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameFormContent().getCaret().setValue(21);
        b_.getFramePoints().getFrameFormContent().getEnabledMp().setSelected(false);
        addMpOk(b_);
        editMethod(b_,0);
        b_.getFramePoints().getFrameFormContent().getExitFunction().setSelected(true);
        b_.getFramePoints().getFrameFormContent().getEnterFunction().setSelected(true);
        addMpOk(b_);
        assertTrue(curRet(b_).getContext().metList().elts().iterator().next().getValue().isExit());
        assertTrue(curRet(b_).getContext().metList().elts().iterator().next().getValue().isEntry());
    }
    @Test
    public void bp171() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        toggleBp(b_);
        assertFalse(curRet(b_).getContext().typeList().elts().iterator().hasNext());
    }
    @Test
    public void bp172() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        tabEditor(b_).getCenter().select(72,72);
        toggleBp(b_);
        assertTrue(curRet(b_).getContext().typeList().elts().iterator().hasNext());
    }
    @Test
    public void bp173() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        assertTrue(curRet(b_).getContext().typeList().elts().iterator().hasNext());
    }
    @Test
    public void bp174() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}public class pkg.Ex2 {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        assertTrue(curRet(b_).getContext().typeList().elts().iterator().hasNext());
    }
    @Test
    public void bp175() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(false);
        tpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertFalse(curRet(b_).is(file(curRet(b_)),13));
    }
    @Test
    public void bp176() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(false);
        addTpOk(b_);
        editTp(b_,0);
        tabEditor(b_).getCenter().select(13,13);
        toggleBpEn(b_);
        assertTrue(b_.getFramePoints().getFrameTpFormContent().getEnabledBp().isSelected());
    }
    @Test
    public void bp177() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}public class pkg.Ex2 {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        addTp(b_);
        b_.getFramePoints().getFrameTpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameTpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameTpFormContent().getEnabledBp().setSelected(false);
        addTpOk(b_);
        editTp(b_,0);
        tabEditor(b_).getCenter().select(80,80);
        toggleBpEn(b_);
        assertFalse(b_.getFramePoints().getFrameTpFormContent().getEnabledBp().isSelected());
    }
    @Test
    public void syntheFilter1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        StrictTypeFromFilter s_ = new StrictTypeFromFilter(curRet(b_));
        StringList f_ = s_.filter("", curRet(b_).getContext().getClasses().getClassesBodies().getKeys());
        assertEq(0,f_.size());
    }
    @Test
    public void syntheFilter2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){int v=1;return v;}}");
        guiAna(r_,b_,o_,src_);
        StrictTypeFromFilter s_ = new StrictTypeFromFilter(curRet(b_));
        StringList f_ = s_.filter("pkg.Ex", curRet(b_).getContext().getClasses().getClassesBodies().getKeys());
        assertEq(1,f_.size());
        AbsTextField t_ = b_.getFrames().getCompoFactory().newTextField();
        s_.act(t_,f_.get(0));
        assertEq("pkg.Ex",t_.getText());
    }
    @Test
    public void ref1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int field;public static int exmeth(){return field;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return Ex0.field;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(80,80);
        assertEq("src/file0.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(80,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
        refPartDbg(b_);
        assertEq("src/file0.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(40,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
    }
    @Test
    public void ref2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int field;public static int exmeth(){return field;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return Ex0.field;}}");
        guiAna(r_,b_,o_,src_);
        b_.getTabbedPane().selectIndex(1);
        b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().select(60,60);
        assertEq("src/file1.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(60,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
        refPartDbg(b_);
        assertEq("src/file0.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(40,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
    }
    @Test
    public void ref3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int field;public static int exmeth(){return field;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return Ex0.field_;}}");
        guiAna(r_,b_,o_,src_);
        b_.getTabbedPane().selectIndex(1);
        b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().select(60,60);
        assertEq("src/file1.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(60,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
        refPartDbg(b_);
        assertEq("src/file1.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(60,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
    }
    @Test
    public void ref4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int field;public static int exmeth(){return field;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return Integer.MAX_VALUE;}}");
        guiAna(r_,b_,o_,src_);
        b_.getTabbedPane().selectIndex(1);
        b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().select(64,64);
        assertEq("src/file1.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(64,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
        refPartDbg(b_);
        assertEq("src/file1.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(64,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
    }
    @Test
    public void ref5() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file0.txt","public class pkg.Ex0 {public static int field;public static int exmeth(){return field;}}");
        save(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(){return Ex0.field;}}");
        guiAna(r_,b_,o_,src_);
        b_.getTabbedPane().selectIndex(0);
        closeReadOnlyTab(b_);
        b_.getTabbedPane().selectIndex(0);
        b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().select(60,60);
        assertEq(1,b_.getTabs().size());
        assertEq(1,b_.getTabbedPane().getComponentCount());
        assertEq("src/file1.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(60,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
        refPartDbg(b_);
        assertEq(2,b_.getTabs().size());
        assertEq(2,b_.getTabbedPane().getComponentCount());
        assertEq("src/file0.txt",b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getFullPath());
        assertEq(40,b_.getTabs().get(b_.getTabbedPane().getSelectedIndex()).getCenter().getCaretPosition());
    }
    @Test
    public void m1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {Ex(){}public static int exmeth(){return 1;}public static int fmeth(String[] v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String... _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(int[] _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m5() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(int... _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m6() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int b;public staticCall int exmeth(String... _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m7() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void m8() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String... _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void m9() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.E");
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void m10() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(that String[] _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(false);
        param(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void m11() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(that String... _v){return 1;}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(true);
        param(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void m12() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int v;public static that int exmeth(String[] _v){return that(v);}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void m13() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int v;public static that int exmeth(String... _v){return that(v);}}");
        guiAna(r_,b_,o_,src_);
        vararg(b_).setSelected(true);
        retVal(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void m14() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int v;public static that int exmeth(String... _v){return that(v);}}");
        guiNoAna(b_,o_);
        vararg(b_).setSelected(true);
        retVal(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        assertTrue(methods(b_).isEmpty());
    }
    @Test
    public void arg1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        guiNoAna(b_,o_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        assertEq(1,f_.getCommentsRows().size());
        assertEq("Arg",f_.getCommentsRows().get(0).getValueArea().getText());
    }
    @Test
    public void arg2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        guiNoAna(b_,o_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertEq(1,f_.getCommentsRows().size());
        assertEq("Arg",f_.getCommentsRows().get(0).getValueArea().getText());
    }
    @Test
    public void arg3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        guiNoAna(b_,o_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        f_.getCommentsRows().get(0).getSelectForDelete().setSelected(true);
        remRow(f_);
        //validValues(f_);
        assertEq(0,f_.getCommentsRows().size());
    }
    @Test
    public void arg4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        guiNoAna(b_,o_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg1");
        addRow(f_);
        f_.getCommentsRows().get(1).getValueArea().setText("Arg2");
        //validValues(f_);
        f_.getCommentsRows().get(0).getSelectForDelete().setSelected(true);
        remRow(f_);
        //validValues(f_);
        assertEq(1,f_.getCommentsRows().size());
        assertEq(0,f_.getCommentsRows().get(0).getIndex());
        assertEq("Arg2",f_.getCommentsRows().get(0).getValueArea().getText());
    }
    @Test
    public void iStop() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(96,96);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        b_.getStopStack().getActionListeners().get(0).action();
        b_.next(StepDbgActionEnum.DEBUG, curRet(b_));
        assertTrue(curRet(b_).getContext().getInterrupt().get());
    }
    @Test
    public void i1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(96,96);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(2,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        trDetail_.select(null);
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getNextSibling());
        assertEq(2,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertSame(NullStruct.NULL_VALUE,root_.getChildren().get(0).value());
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq(2,toLong(root_.getChildren().get(0).getChildren().get(0).value()));
        assertEq("pkg.Ex|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertEq(4,toLong(root_.getChildren().get(0).getChildren().get(1).value()));
        assertEq("-1|v|[$core.String",root_.getChildren().get(1).str());
        assertEq(1,root_.getChildren().get(1).getChildren().size());
        assertEq("[0]",root_.getChildren().get(1).getChildren().get(0).str());
        assertEq("Arg",((StringStruct)root_.getChildren().get(1).getChildren().get(0).value()).getInstance());
    }
    @Test
    public void i2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {int a=2;Ex b=this;public void test(){a=a;}public static int exmeth(String[] v){new Ex().test();return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(58,58);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq(2,toLong(root_.getChildren().get(0).getChildren().get(0).value()));
        assertEq("pkg.Ex|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertSame(root_.getChildren().get(0).value(),root_.getChildren().get(0).getChildren().get(1).value());
        assertEq(0, root_.getChildren().get(0).getChildren().get(1).getChildren().size());
    }
    @Test
    public void i3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public class Inner{int b = 2;public void test(){b=b;}}Inner a = new Inner();public static int exmeth(String[] v){new Ex().a.test();return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(69,69);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex..Inner",root_.getChildren().get(0).str());
        assertEq("pkg.Ex..Inner",root_.getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("|",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq("pkg.Ex..Inner|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertEq(2,toLong(root_.getChildren().get(0).getChildren().get(1).value()));
        assertEq(1,root_.getChildren().get(0).getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).getChildren().get(0).str());
        assertSame(root_.getChildren().get(0).value(),root_.getChildren().get(0).getChildren().get(0).getChildren().get(0).value());
    }
    @Test
    public void i4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int s=0;iter(int i=0;v.length;1){s+=i;}return s;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(91,91);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(4,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getNextSibling());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getNextSibling().getNextSibling());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getNextSibling().getNextSibling().getNextSibling());
        assertEq(4,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertSame(NullStruct.NULL_VALUE,root_.getChildren().get(0).value());
        assertEq(0,root_.getChildren().get(0).getChildren().size());
        assertEq("-1|i|int([0])",root_.getChildren().get(1).str());
        assertEq(0,toLong(root_.getChildren().get(1).value()));
        assertEq(0,root_.getChildren().get(1).getChildren().size());
        assertEq("-1|s|int",root_.getChildren().get(2).str());
        assertEq(0,toLong(root_.getChildren().get(2).value()));
        assertEq(0,root_.getChildren().get(2).getChildren().size());
        assertEq("-1|v|[$core.String",root_.getChildren().get(3).str());
        assertEq(1,root_.getChildren().get(3).getChildren().size());
        assertEq("[0]",root_.getChildren().get(3).getChildren().get(0).str());
        assertEq("Arg",((StringStruct)root_.getChildren().get(3).getChildren().get(0).value()).getInstance());
    }
    @Test
    public void i5() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex:Super {int a=2;public void test(){a=a;}public static int exmeth(String[] v){new Ex().test();return v.length;}}public class pkg.Super{int b=4;}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(54,54);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq(2,toLong(root_.getChildren().get(0).getChildren().get(0).value()));
        assertEq("pkg.Super|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertEq(4,toLong(root_.getChildren().get(0).getChildren().get(1).value()));
    }
    @Test
    public void i6() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(96,96);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        next(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("|",root_.getChildren().get(0).str());
        assertEq(1,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i7() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static that int exmeth(String[] v){return that(a);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(100,100);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(true);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        next(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("int",root_.getChildren().get(0).str());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i8() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static that int exmeth(String[] v){return that(a);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(13,13);
        toggleBp(b_);
        bpForm(b_);
        b_.getFramePoints().getFrameTpFormContent().getStaticType().setSelected(true);
        tpFormOk(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(true);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        next(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("int",root_.getChildren().get(0).str());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i9() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static that int exmeth(String[] v){return that(a);}}");
        guiAna(r_,b_,o_,src_);
        launchNoWait(b_);
        b_.selectFocus(-1,-1);
        b_.focus(-1);
        b_.possibleSelectInstruction(-1,r_);
        b_.possibleSelectExpression(-1,r_);
        assertEq(src_.getVal("src/file.txt"),tabSelect(b_).getCenter().getText());
        assertTrue(b_.getSelectEnter().isEnabled());
    }
    @Test
    public void i10() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","");
        guiAna(r_,b_,o_,src_);
        assertTrue(classesFilter(b_).getList().isEmpty());
    }
    @Test
    public void i11() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = optBad(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        guiAna(r_,b_,o_,src_);
        assertTrue(classesFilter(b_).getList().isEmpty());
    }
    @Test
    public void i12() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = optBad2(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        guiAna(r_,b_,o_,src_);
        assertTrue(classesFilter(b_).getList().isEmpty());
    }
    @Test
    public void i13() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] victoire){return callee(victoire);}public static int callee(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(134,134);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        b_.getCallButtons().get(0).getActionListeners().get(0).action();
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(2,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild().getNextSibling());
        assertEq(2,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertSame(NullStruct.NULL_VALUE,root_.getChildren().get(0).value());
        assertEq(0,root_.getChildren().get(0).getChildren().size());
        assertEq("-1|victoire|[$core.String",root_.getChildren().get(1).str());
        assertEq(1,root_.getChildren().get(1).getChildren().size());
        assertEq("[0]",root_.getChildren().get(1).getChildren().get(0).str());
        assertEq("Arg",((StringStruct)root_.getChildren().get(1).getChildren().get(0).value()).getInstance());
    }
    @Test
    public void i14() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(62,62);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertTrue(b_.getNextInstruction().isEnabled());
        nextInst(b_);
        assertTrue(b_.getNextInstruction().isEnabled());
        nextInst(b_);
        assertTrue(b_.getNextInstruction().isEnabled());
        nextInst(b_);
        assertFalse(b_.getNextInstruction().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i15() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(62,62);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertTrue(b_.getNextGoUp().isEnabled());
        nextGoUp(b_);
        assertTrue(b_.getNextGoUp().isEnabled());
        nextGoUp(b_);
        assertFalse(b_.getNextGoUp().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i16() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(62,62);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertTrue(b_.getNextInMethod().isEnabled());
        nextGoInMethod(b_);
        assertTrue(b_.getNextInMethod().isEnabled());
        nextGoInMethod(b_);
        assertTrue(b_.getNextInMethod().isEnabled());
        nextGoInMethod(b_);
        assertFalse(b_.getNextInMethod().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i17() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(62,62);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertTrue(b_.getNextCursorInstruction().isEnabled());
        tabEditor(b_).getCenter().select(85,85);
        nextCursorInstruction(b_);
        assertTrue(b_.getNextCursorInstruction().isEnabled());
        nextGoInMethod(b_);
        assertFalse(b_.getNextCursorInstruction().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i18() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(95,95);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        b_.getMute().setSelected(true);
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoinNoWait(b_, trDetail_, null);
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        assertEq(1,root_.getChildren().size());
        assertEq("|",root_.getChildren().get(0).str());
        assertEq(1,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i19() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(62,62);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertTrue(b_.getNextBlock().isEnabled());
        nextBlock(b_);
        assertFalse(b_.getNextBlock().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i20() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(96,96);
        toggleBp(b_);
        bpForm(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getSuspend().setEnabled(false);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getLogs().setText("\"log\"");
        bpFormOk(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertEq("log\n",((AbsTextArea) compo(b_.getStatusDbgAreaScroll())).getText());
    }
    @Test
    public void i21() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(96,96);
        toggleBp(b_);
        bpForm(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getSuspend().setEnabled(true);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getWatches().setText("\"log\"");
        bpFormOk(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
    }
    @Test
    public void i22() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(96,96);
        toggleBp(b_);
        bpForm(b_);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getSuspend().setEnabled(true);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getWatches().setText("1/0");
        bpFormOk(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
    }
    @Test
    public void i23() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(62,62);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        assertTrue(b_.getNextCursorExpression().isEnabled());
        tabEditor(b_).getCenter().select(94,94);
        nextCursorExpression(b_);
        assertTrue(b_.getNextCursorExpression().isEnabled());
        tabEditor(b_).getCenter().select(96,96);
        nextCursorExpression(b_);
        assertTrue(b_.getNextCursorExpression().isEnabled());
        tabEditor(b_).getCenter().select(90,90);
        nextCursorExpression(b_);
        assertTrue(b_.getNextCursorExpression().isEnabled());
        nextGoInMethod(b_);
        assertFalse(b_.getNextCursorExpression().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        selectJoin(b_, trDetail_, trDetail_.getRoot());
        selectJoin(b_, trDetail_, trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void i24() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        Struct str_ = resNode(node_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i25() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\"+1/0;}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct info_ = b_.getRoot().getNode().simular(node_).info();
        resNode(info_, b_);
        assertEq("src/file.txt:1,158:157\n" +
                "pkg.Ex..Inner.$toString()\n",info_.logs().getText());
    }
    @Test
    public void i26() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct info_ = b_.getRoot().getNode().simular(node_).info();
        Struct str_ = resNode(info_, b_);
        assertNull(str_);
    }
    @Test
    public void i27() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=\"standard\";return 0;}public class Inner{}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(82,82);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct info_ = b_.getRoot().getNode().simular(node_).info();
        Struct str_ = resNode(info_, b_);
        assertEq("standard",((StringStruct)str_).getInstance());
    }
    @Test
    public void i28() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new int[]{1};return 0;}public class Inner{}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(84,84);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct info_ = b_.getRoot().getNode().simular(node_).info();
        Struct str_ = resNode(info_, b_);
        assertEq("[int",((ArrayStruct)str_).getClassName());
        assertEq(1,((ArrayStruct)str_).getLength());
        assertEq(1, NumParsers.convertToNumber(((ArrayStruct)str_).get(0)).intStruct());
    }
    @Test
    public void i29() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        b_.getCallButtonsRender().get(0).getActionListeners().get(0).action();
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        selectJoin(b_, trDetail_, node_);
        DbgAbsNodeStruct info_ = b_.getRoot().getNode().simular(node_).info();
        Struct str_ = resNode(info_, b_);
        b_.getCallButtonsRender().get(0).getActionListeners().get(0).action();
        assertEq("render",((StringStruct)str_).getInstance());
        info_.stopButton().getActionListeners().get(0).action();
    }
    @Test
    public void i30() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i31() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("to_str",((StringStruct)str_).getInstance());
    }
    @Test
    public void i32() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i33() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){Ex.Inner i=null;return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(81,81);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i34() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"to_str\";}}public class Inner2{}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner2");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("to_str",((StringStruct)str_).getInstance());
    }
    @Test
    public void i35() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"+1/0");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        resNode(i_, b_);
        assertEq(":1,11:10\n" +
                "pkg.Ex..Inner..1()\n",i_.logs().getText());
    }
    @Test
    public void i36() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"to_str\";}}public class Inner2{}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner2");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("pkg.Ex..Inner",str_.getClassName(curRet(b_).getContext()));
    }
    @Test
    public void i37() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        Struct str_ = resNode(node_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i38() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i39() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("pkg.Ex..Inner",str_.getClassName(curRet(b_).getContext()));
    }
    @Test
    public void i40() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}public class Inner2{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("pkg.Ex..Inner",str_.getClassName(curRet(b_).getContext()));
    }
    @Test
    public void i41() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner:Int1:Int2{public String $toString(){return \"render\";}}}public interface pkg.Int1{}public interface pkg.Int2{}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int1");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i42() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner:Int1:Int2{public String $toString(){return \"render\";}}}public interface pkg.Int1{}public interface pkg.Int2{}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int1");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render1\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        AbsPanel bs_ = b_.getFramePoints().getFrameRenderFormContent().getPrefs().getButtons();
        AbsButton last_ = (AbsButton) bs_.getComponent(bs_.getComponentCount()-1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex..Inner");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(1);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render2\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex..Inner");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(2);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render1",((StringStruct)str_).getInstance());
    }
    @Test
    public void i43() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner:Int1:Int2{public String $toString(){return \"render\";}}}public interface pkg.Int1{}public interface pkg.Int2{}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int1");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render1\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        AbsPanel bs_ = b_.getFramePoints().getFrameRenderFormContent().getPrefs().getButtons();
        AbsButton last_ = (AbsButton) bs_.getComponent(bs_.getComponentCount()-1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex..Inner");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(2);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render2\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex..Inner");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(1);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render2",((StringStruct)str_).getInstance());
    }
    @Test
    public void i44() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner:Int1:Int2{public String $toString(){return \"render\";}}}public interface pkg.Int1{}public interface pkg.Int2{}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int1");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render",((StringStruct)str_).getInstance());
    }
    @Test
    public void i45() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner:Int1:Int2{public String $toString(){return \"render\";}}}public interface pkg.Int1{}public interface pkg.Int2{}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int1");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render1\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(1);
        AbsPanel bs_ = b_.getFramePoints().getFrameRenderFormContent().getPrefs().getButtons();
        AbsButton last_ = (AbsButton) bs_.getComponent(bs_.getComponentCount()-1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(1);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render2\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex..Inner");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(2);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render1",((StringStruct)str_).getInstance());
    }
    @Test
    public void i46() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner:Int1:Int2{public String $toString(){return \"render\";}}}public interface pkg.Int1{}public interface pkg.Int2{}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int1");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render1\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(0);
        AbsPanel bs_ = b_.getFramePoints().getFrameRenderFormContent().getPrefs().getButtons();
        AbsButton last_ = (AbsButton) bs_.getComponent(bs_.getComponentCount()-1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex..Inner");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(2);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Int2");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render2\"");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getPref().setValue(1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getKey().valueString("pkg.Ex");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getGeneComponentModelEntryStringInteger().getValue().valueInt(1);
        b_.getFramePoints().getFrameRenderFormContent().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render2",((StringStruct)str_).getInstance());
    }
    @Test
    public void i47() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandText().setText("" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandFirst().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
    }
    @Test
    public void i48() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandText().setText("" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpand().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("pkg.Ex|a",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("pkg.Ex|b",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
    }
    @Test
    public void i49() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandText().setText("" +
                "        null" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(0,chsSec_.size());
    }
    @Test
    public void i50() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandText().setText("" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandFirst().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
    }
    @Test
    public void i51() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderExpandText().setText("" +
                "    new Pair<String,IterableTable<String,Object>>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getFirst(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getSecond(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpandRender().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertEq("3,4",((StringStruct)str_).getInstance());
    }
    @Test
    public void i52() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}public @class pkg.MyPair<S,T>:Pair<S,T>{public S first;public T second;public S getFirst(){return first;}public T getSecond(){return second;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderExpandText().setText("" +
                "    new MyPair<String,IterableTable<String,Object>>(first:(++a)+\",\"+(++b),second:" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a+=2;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b+=2;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        })\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpandRender().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(6,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(7,toLong(chsSec_.get(1).info().value()));
        assertEq("4,5",((StringStruct)str_).getInstance());
    }
    @Test
    public void i53() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderExpandText().setText("" +
                "    new Pair<String,IterableTable<String,Object>>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getFirst(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getSecond(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpandRender().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("pkg.Ex|a",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("pkg.Ex|b",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertNull(str_);
    }
    @Test
    public void i54() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderText().setText("" +
                "    new Pair<IterableTable<String,Object>,String>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getSecond(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getFirst(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledRenderExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertEq("3,4",((StringStruct)str_).getInstance());
    }
    @Test
    public void i55() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}public @class pkg.MyPair<S,T>:Pair<S,T>{public S first;public T second;public S getFirst(){return first;}public T getSecond(){return second;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderText().setText("" +
                "    new MyPair<IterableTable<String,Object>,String>(second:(++a)+\",\"+(++b),first:" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a+=2;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b+=2;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        })\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledRenderExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(6,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(7,toLong(chsSec_.get(1).info().value()));
        assertEq("4,5",((StringStruct)str_).getInstance());
    }
    @Test
    public void i56() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderText().setText("" +
                "    new Pair<IterableTable<String,Object>,String>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getSecond(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getFirst(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledRenderExpand().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("pkg.Ex|a",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("pkg.Ex|b",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertNull(str_);
    }
    @Test
    public void i57() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderExpandText().setText("" +
                "    new Pair<String,IterableTable<String,Object>>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getFirst(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getSecond(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpandRender().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertEq("3,4",((StringStruct)str_).getInstance());
    }
    @Test
    public void i58() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}public @class pkg.MyPair<S,T>:Pair<S,T>{public S first;public T second;public S getFirst(){return first;}public T getSecond(){return second;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderExpandText().setText("" +
                "    new MyPair<String,IterableTable<String,Object>>(first:(++a)+\",\"+(++b),second:" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a+=2;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b+=2;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        })\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpandRender().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(6,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(7,toLong(chsSec_.get(1).info().value()));
        assertEq("4,5",((StringStruct)str_).getInstance());
    }
    @Test
    public void i59() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getRenderExpandText().setText("" +
                "    new Pair<String,IterableTable<String,Object>>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getFirst(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getSecond(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExpandRender().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("pkg.Ex|a",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("pkg.Ex|b",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertNull(str_);
    }
    @Test
    public void i60() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderText().setText("" +
                "    new Pair<IterableTable<String,Object>,String>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getSecond(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getFirst(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledRenderExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertEq("3,4",((StringStruct)str_).getInstance());
    }
    @Test
    public void i61() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}public @class pkg.MyPair<S,T>:Pair<S,T>{public S first;public T second;public S getFirst(){return first;}public T getSecond(){return second;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderText().setText("" +
                "    new MyPair<IterableTable<String,Object>,String>(second:(++a)+\",\"+(++b),first:" +
                "        new IterableTable<String,Object>(this){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a+=2;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b+=2;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        })\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledRenderExpand().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("1:",chsSec_.get(0).info().str());
        assertEq(6,toLong(chsSec_.get(0).info().value()));
        assertEq("2:",chsSec_.get(1).info().str());
        assertEq(7,toLong(chsSec_.get(1).info().value()));
        assertEq("4,5",((StringStruct)str_).getInstance());
    }
    @Test
    public void i62() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public int a;public int b;public static int exmeth(String[] v){var i=new Ex();i.a=3;i.b=4;return 0;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(118,118);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex");
        checkInehrit(b_.getFramePoints().getFrameRenderFormContent().getExactForm());
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderText().setText("" +
                "    new Pair<IterableTable<String,Object>,String>(this){\n" +
                "      public Ex re;\n" +
                "      public(Ex e){\n" +
                "          this.re=e;\n" +
                "      }\n" +
                "      public String getSecond(){\n" +
                "          return re.a+\",\"+re.b;\n" +
                "      }\n" +
                "      public IterableTable<String,Object> getFirst(){\n" +
                "        return new IterableTable<String,Object>(re){\n" +
                "            public Ex e;\n" +
                "            public(Ex e){\n" +
                "                this.e=e;\n" +
                "            }\n" +
                "            public IteratorTable<String,Object> iteratorTable(){\n" +
                "                return new IteratorTable<String,Object>(e){\n" +
                "                    public Ex e;\n" +
                "                    public int i;\n" +
                "                    public(Ex e){\n" +
                "                        this.e=e;\n" +
                "                    }\n" +
                "                    public boolean hasNextPair(){\n" +
                "                        return i < 2;\n" +
                "                    }\n" +
                "                    public Pair<String,Object> nextPair(){\n" +
                "                        if (i == 0){\n" +
                "                            i++;\n" +
                "                            return new Pair<String,Object>(e){\n" +
                "                                Ex e;\n" +
                "                                public(Ex e){\n" +
                "                                    this.e=e;\n" +
                "                                }\n" +
                "                                public String getFirst(){\n" +
                "                                    return \"1:\";\n" +
                "                                }\n" +
                "                                public Object getSecond(){\n" +
                "                                    return e.a;\n" +
                "                                }\n" +
                "                            };\n" +
                "                        }\n" +
                "                        i++;\n" +
                "                        return new Pair<String,Object>(e){\n" +
                "                            Ex e;\n" +
                "                            public(Ex e){\n" +
                "                                this.e=e;\n" +
                "                            }\n" +
                "                            public String getFirst(){\n" +
                "                                return \"2:\";\n" +
                "                            }\n" +
                "                            public Object getSecond(){\n" +
                "                                return e.b;\n" +
                "                            }\n" +
                "                        };\n" +
                "                    }\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "      }\n" +
                "    }\n" +
                "    ");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledRenderExpand().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getExpandRenderChoice().setSelected(false);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chsSec_ = i_.getNode().children();
        assertEq(2,chsSec_.size());
        assertEq("pkg.Ex|a",chsSec_.get(0).info().str());
        assertEq(3,toLong(chsSec_.get(0).info().value()));
        assertEq("pkg.Ex|b",chsSec_.get(1).info().str());
        assertEq(4,toLong(chsSec_.get(1).info().value()));
        assertNull(str_);
    }
    @Test
    public void ir1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        addRendOk(b_);
        assertEq(1,b_.getRenderList().size());
    }
    @Test
    public void ir2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("ExInex");
        selectRenderTrue(b_);
        addRendOk(b_);
        assertEq(0,b_.getRenderList().size());
    }
    @Test
    public void ir3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        addRendOk(b_);
        editRend(b_, 0);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        addRendOk(b_);
        assertEq(1,b_.getRenderList().size());
        assertEq("\"render\"", b_.getRenderList().get(0).getRender().getResultStr());
    }
    @Test
    public void ir4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        addRendOk(b_);
        editRend(b_, 0);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"");
        addRendRemove(b_);
        assertEq(0,b_.getRenderList().size());
    }
    @Test
    public void ir5() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRendRemove(b_);
        assertEq(0,b_.getRenderList().size());
    }
    @Test
    public void ir6() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderTrue(b_);
        addRendOk(b_);
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderFalse(b_);
        addRendOk(b_);
        editRend(b_, 1);
        addRendRemove(b_);
        assertEq(1,b_.getRenderList().size());
    }
    @Test
    public void render() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public String $toString(){return \"render\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct info_ = b_.getRoot().getNode().simular(node_).info();
        new DbgRenderStrNodeTask(null, trDetail_,node_,info_,b_.getFrames()).run();
//        ((MockCompoFactory)b_.getCompoFactory()).invoke();
        assertFalse(node_.info().isEmpty());
    }
    @Test
    public void dyn1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=1;var j=2;return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(81,81);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        b_.getDynamicEval().setText("i+j");
        b_.getEvalNoPage().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) b_.getThreadFactory());
        DbgRootStruct root_ = b_.getRootStructStr();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getDynTrees().last();
        selectJoin(b_,trDetail_,trDetail_.getRoot());
        selectJoin(b_,trDetail_,trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(3,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void dyn2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=1;var j=2;return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(81,81);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        b_.getDynamicEval().setText("i+j");
        b_.getEvalPage().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) b_.getThreadFactory());
        DbgRootStruct root_ = b_.getRootStructStr();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getDynTrees().last();
        selectJoin(b_,trDetail_,trDetail_.getRoot());
        selectJoin(b_,trDetail_,trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(3,toLong(root_.getChildren().get(0).value()));
    }
    @Test
    public void dynCancel() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=1;var j=2;return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(81,81);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        b_.getDynamicEval().setText("i+j");
        b_.getEvalPage().getActionListeners().get(0).action();
        b_.getButtons().get(0).getActionListeners().get(0).action();
        assertEq(0,b_.getButtons().size());
//        assertEq(0,b_.getCancelDynWatch().getComponentCount());
    }
    @Test
    public void refreshRender1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public int v;public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"+(++v)");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        selectJoin(b_,trDetail_,trDetail_.getRoot());
        selectJoin(b_,trDetail_,trDetail_.getRoot().getFirstChild().getNextSibling());
        b_.getRefreshRender().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) b_.getThreadFactory());
        assertEq("render2",i_.repr());
    }
    @Test
    public void refreshRender2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=new Ex().new Inner();return 0;}public class Inner{public int v;public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(92,92);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(3,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        AbstractMutableTreeNodeCore<String> node_ = trDetail_.getRoot().getFirstChild().getNextSibling();
        DbgAbsNodeStruct i_ = b_.getRoot().getNode().simular(node_).info();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("pkg.Ex..Inner");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("\"render\"+(++v)");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(true);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        Struct str_ = resNode(i_, b_);
        assertEq("render1",((StringStruct)str_).getInstance());
        i_.repr(str_);
        b_.getRefreshRender().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) b_.getThreadFactory());
        assertEq("render1",i_.repr());
    }
    @Test
    public void refDyn() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){var i=1;var j=2;return 0;}public class Inner{public String $toString(){return \"to_str\";}}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(81,81);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        launch(b_);
        b_.getDynamicEval().setText("i+j");
        b_.getEvalPage().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) b_.getThreadFactory());
        DbgRootStruct root_ = b_.getRootStructStr();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getDynTrees().last();
        openPoints(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        addRend(b_);
        b_.getFramePoints().getFrameRenderFormContent().getClName().setText("");
        selectRenderFalse(b_);
        b_.getFramePoints().getFrameRenderFormContent().getRenderText().setText("");
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExc().setSelected(false);
        b_.getFramePoints().getFrameRenderFormContent().getEnabledExcGlobal().setSelected(true);
        addRendOk(b_);
        selectJoin(b_,trDetail_,trDetail_.getRoot());
        selectJoin(b_,trDetail_,trDetail_.getRoot().getFirstChild());
        b_.getButtonsDynRef().get(0).getActionListeners().get(0).action();
        tryAn((MockThreadFactory) b_.getThreadFactory());
        assertEq("3",root_.getChildren().get(0).repr());
    }
    @Test
    public void pause() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static int exmeth(String[] v){return v.length;}}");
        guiAna(r_,b_,o_,src_);
        tabEditor(b_).getCenter().select(95,95);
        toggleBp(b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(false);
        param(b_).setSelected(false);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exm");
        meths_.enterEvent();
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        //validValues(f_);
        assertFalse(methods(b_).isEmpty());
        b_.getPauseStack().setEnabled(true);
        b_.getPauseStack().getActionListeners().get(0).action();
        assertFalse(b_.getPauseStack().isEnabled());
    }
    @Test
    public void t1() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        assertEq(13,t_.getRoot().getChildCount());
    }
    @Test
    public void t2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        assertEq("src/",t_.selectEvt().info());
        assertEq(4, t_.getRoot().getFirstChild().getChildCount());
    }
    @Test
    public void t3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        assertEq("sub1/",t_.selectEvt().info());
        assertEq(3, t_.getRoot().getFirstChild().getFirstChild().getChildCount());
    }
    @Test
    public void t4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild());
        assertEq("sub3/",t_.selectEvt().info());
        assertEq(2, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getChildCount());
    }
    @Test
    public void t5() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq("sub2/",t_.selectEvt().info());
        assertEq(3, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t6() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild());
        assertEq("sub4/",t_.selectEvt().info());
        assertEq(2, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getChildCount());
    }
    @Test
    public void t7() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling());
        assertEq("file1.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t8() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNextSibling());
        assertEq("file2.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t9() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling());
        assertEq("file3.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t10() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getNextSibling());
        assertEq("file4.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t11() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling());
        assertEq("file5.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t12() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getNextSibling());
        assertEq("file6.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t13() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild());
        assertEq("file7.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getChildCount());
    }
    @Test
    public void t14() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNextSibling());
        assertEq("file8.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t15() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild());
        assertEq("file0.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getChildCount());
    }
    @Test
    public void t16() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling());
        assertEq("file9.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t17() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling());
        assertEq("file1.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t18() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNextSibling());
        assertEq("file2.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t19() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling());
        assertEq("file3.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t20() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getNextSibling());
        assertEq("file4.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t21() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling());
        assertEq("file5.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t22() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getNextSibling());
        assertEq("file6.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getChildCount());
    }
    @Test
    public void t23() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild());
        assertEq("file7.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getChildCount());
    }
    @Test
    public void t24() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNextSibling());
        assertEq("file8.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t25() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild());
        assertEq("file0.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getChildCount());
    }
    @Test
    public void t26() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        b_.closeCompos();
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling());
        assertEq("file9.txt",t_.selectEvt().info());
        assertEq(0, t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling().getChildCount());
    }
    @Test
    public void t27() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file1.txt","public class pkg.Ex1 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/file2.txt","public class pkg.Ex2 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file3.txt","public class pkg.Ex3 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/file4.txt","public class pkg.Ex4 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file5.txt","public class pkg.Ex5 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/file6.txt","public class pkg.Ex6 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file7.txt","public class pkg.Ex7 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub1/sub3/file8.txt","public class pkg.Ex8 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file0.txt","public class pkg.Ex0 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        saveFolder(b_,src_,"src/sub2/sub4/file9.txt","public class pkg.Ex9 {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiNoAna(b_,o_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        assertEq(0,t_.getRoot().getChildCount());
    }
    @Test
    public void closeTab() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        saveFolder(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(String[] v){int t = 8;int u = 3;return Math.mod(t,u);}}");
        guiAna(r_,b_,o_,src_);
        AbsTreeGui t_ = b_.getTree();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        assertEq(1,b_.getTabs().size());
        closeReadOnlyTab(b_);
        assertEq(0,b_.getTabs().size());
    }
    @Test
    public void window() {
        MockProgramInfos pr_ = genePr();
        WindowCdmEditor w_ = updated(pr_);
        w_.getFuture().attendre();
        w_.getFutureDbgInit().attendre();
        WindowExpressionEditor e_ = geneSecAlready(w_);
        save(pr_,"src/file.txt","public class pkg.Ex {static int v;public static that int exmeth(String[] _v){return that(v);}}");
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        AbsDebuggerGui b_ = e_.getSessionSingleMain();
        menuSingleMain(e_,b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    @Test
    public void noAna() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "");
        StringList ls_ = new StringList(w_.getSoftParams().getLines());
        ls_.add("keyWords=If=;");
        analyzeBad2(w_);
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void window3() {
        MockProgramInfos pr_ = genePr();
        WindowCdmEditor w_ = updated(pr_);
        StringList ls_ = new StringList(w_.getSoftParams().getLines());
        ls_.add("keyWords=If=;");
        w_.getSoftParams().setLines(ls_);
        w_.getFuture().attendre();
        WindowExpressionEditor e_ = geneSecAlready(w_);
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        AbsDebuggerGui b_ = buildExpAdvCore(w_);
        ManageOptions o_ = optBad(b_);
        ResultContext r_ = res(b_, o_);
        guiAna(r_,b_,o_,new StringMap<String>());
        b_.getResultContextNext().next(r_,r_);
        assertTrue(classesFilter(b_).getList().isEmpty());
    }
    @Test
    public void window1() {
        MockProgramInfos pr_ = advPr();
        WindowCdmEditor w_ = updated(pr_);
        w_.getFuture().attendre();
        ((LgNamesWithNewAliases)w_.getBaseResult().getForwards().getGenerator()).getExecContent().getExecutingOptions().setMainThread("//");
        WindowExpressionEditor e_ = geneSecAlready(w_);
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
        AbsDebuggerGui b_ = e_.getSessionExp();
        menuExp(e_,b_);
        assertEq(0,found(b_).size());
    }
//    @Test
//    public void window2() {
//        MockProgramInfos pr_ = advPr();
//        WindowCdmEditor w_ = updatedAdv(pr_);
//        StringList ls_ = new StringList(w_.getSoftParams().getLines());
//        ls_.add("keyWords=If=;");
//        w_.getSoftParams().setLines(ls_);
//        w_.getFuture().attendre();
//        WindowExpressionEditor e_ = geneSecAlready(w_);
//        e_.getTree().select(e_.getTree().getRoot());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
//        AbsDebuggerGui b_ = e_.getSessionExp();
//        menuExp(e_,b_);
//        assertEq(0,found(b_).size());
//    }
//    @Test
//    public void window3() {
//        MockProgramInfos pr_ = advPr();
//        WindowCdmEditor w_ = updatedAdv(pr_);
//        StringList ls_ = new StringList(w_.getSoftParams().getLines());
//        ls_.add("keyWords=If=;");
//        w_.getSoftParams().setLines(ls_);
//        w_.getFuture().attendre();
//        WindowExpressionEditor e_ = geneSecAlready(w_);
//        e_.getTree().select(e_.getTree().getRoot());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
//        AbsDebuggerGui b_ = buildExpAdvCore(w_);
//        ManageOptions o_ = optBad(b_);
//        ResultContext r_ = res(b_, o_);
//        guiAna(r_,b_,o_,new StringMap<String>());
//        b_.getResultContextNext().next(r_,r_);
//        assertTrue(classesFilter(b_).getList().isEmpty());
//    }
    @Test
    public void failSrcFile() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "");
        StringList ls_ = new StringList(w_.getSoftParams().getLines());
        ls_.add("keyWords=If=;");
        w_.getSoftParams().setLines(ls_);
        analyzeBad(w_);
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void failSrcFile2() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspace( "");
        StringList ls_ = new StringList(w_.getSoftParams().getLines());
        ls_.add("keyWords=If=;");
//        w_.getSoftParams().setLines(ls_);
        analyzeBad2(w_);
        refreshClasses(w_);
        assertEq(0,tabEditor(w_).getDico().size());
    }
    @Test
    public void noAna2() {
        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlready( "src//bad","public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
        StreamTextFile.saveTextFile("/project/sources/exp/0.txt","",w_.getFrames().getStreams());
        w_.getFolderExpressionMenu().getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.setLimitSymbol(1);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling());
        s_.getTabs().get(0).getCenter().select(0,0);
        currentElement(s_.getTabs().get(0));
        w_.setBaseResult(null);
        new RefreshLocationTask(w_.getPanel(),w_,new ResultRowSrcLocationList(AnalyzedPageEl.setInnerAnalyzing(),"",0,new CustList<SrcFileLocation>(),new CustList<RowSrcLocation>())).run();
        assertEq(0,s_.getSymbols().size());
    }
    protected static void analyzeBad(WindowCdmEditor _w) {
        _w.getFuture().attendre();
        AbsActionListener ev_ = _w.getAnalyzeMenu().getActionListeners().get(0);
        ev_.action();
        executeOneTask(_w.getService());
    }
    private static MockProgramInfos advPr() {
        MockProgramInfos pr_ = MockProgramInfos.inst("", "", new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("src/file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add(StringUtil.EN);
//        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());

        CdmParameterSoftModel c_ = new CdmParameterSoftModel();
        c_.setExecConf(current_);
        c_.getOpenedFiles().add("src/file.txt");
        c_.setFolderExpression("/project/sources");
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(c_),pr_.getStreams());


        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        pr_.setLanguages(new StringList(StringUtil.EN,StringUtil.FR));
        pr_.setLanguage(StringUtil.EN);
        update(pr_);
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        return pr_;
    }
    private CustList<SegmentFindPart> found(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getFound();
    }
    public static AbsDebuggerGui buildExpAdvCore(WindowCdmEditor _w) {
        AbstractProgramInfos pr_ = _w.getFrames();
        SampleMockResultContextNext m_ = new SampleMockResultContextNext(_w,_w.getFrames(),_w.getFactory());
        return new InitDebGuiImpl(new ExpMenuFrameInteract(pr_.getCompoFactory().newMenuItem()),m_, pr_,_w.getFactory());
    }
    protected static void analyzeBad2(WindowCdmEditor _w) {
        _w.getFuture().attendre();
        ((LgNamesWithNewAliases)_w.getBaseResult().getForwards().getGenerator()).getExecContent().getExecutingOptions().setMainThread("//");
        AbsActionListener ev_ = _w.getAnalyzeMenu().getActionListeners().get(0);
        ev_.action();
        executeOneTask(_w.getService());
    }
    private void launch(AbsDebuggerGui _d) {
        _d.getSelectEnter().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void launchNoWait(AbsDebuggerGui _d) {
        _d.getSelectEnter().getActionListeners().get(0).action();
    }
    private void next(AbsDebuggerGui _d) {
        _d.getNextAction().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void nextInst(AbsDebuggerGui _d) {
        _d.getNextInstruction().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void nextBlock(AbsDebuggerGui _d) {
        _d.getNextBlock().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void nextGoUp(AbsDebuggerGui _d) {
        _d.getNextGoUp().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void nextCursorInstruction(AbsDebuggerGui _d) {
        _d.getNextCursorInstruction().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void nextCursorExpression(AbsDebuggerGui _d) {
        _d.getNextCursorExpression().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }

    private void nextGoInMethod(AbsDebuggerGui _d) {
        _d.getNextInMethod().getActionListeners().get(0).action();
        tryAn((MockThreadFactory) _d.getThreadFactory());
    }
    private void addRow(FormInputDebugLines _r) {
        _r.getAdd().getActionListeners().get(0).action();
    }
    private void remRow(FormInputDebugLines _r) {
        _r.getRem().getActionListeners().get(0).action();
    }
//    private void validValues(FormInputDebugLines _r) {
//        ((MockPlainButton) _r.getVal()).getActionListeners().get(0).action();
//    }
    private CustList<ExecOverridableBlock> methods(AbsDebuggerGui _b) {
        String idCl_ = ((InitDebGuiImpl)_b).getClassesField().getText();
        ExecRootBlock type_ = ((InitDebGuiImpl)_b).selectedType(idCl_);
        return ((InitDebGuiImpl)_b).methods(type_);
    }
    private AutoCompleteDocument classesFilter(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getClassesFieldAutoComplete();
    }
    private AutoCompleteDocument methodFilter(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getClMethFieldAutoComplete();
    }
    private AbsCustCheckBox vararg(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getVararg();
    }
    private AbsCustCheckBox param(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getParamRef();
    }

    private AbsCustCheckBox retVal(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getRetRef();
    }

    private FormInputDebugLines formArgs(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getFormInputDebugLines();
    }
    private ExecFileBlock file(ResultContext _cont) {
        return file(_cont,"src/file.txt");
    }
    private ExecFileBlock file(ResultContext _cont, String _name) {
        return _cont.getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }

    private ResultContext curRet(AbsDebuggerGui _b) {
        return ((OpenFramePointsEvent)_b.getOpenPoints().getActionListeners().get(0)).getCurrentResult();
    }

    private void selectJoin(AbsDebuggerGui _b, AbsTreeGui _tree, AbstractMutableTreeNodeCore<String> _node) {
        _tree.select(_node);
        tryAn((MockThreadFactory) _b.getThreadFactory());
    }

    private void selectJoinNoWait(AbsDebuggerGui _b, AbsTreeGui _tree, AbstractMutableTreeNodeCore<String> _node) {
        _tree.select(_node);
    }

    private void selectArrRadioFalse(AbsDebuggerGui _b) {
        checkSameFamily(_b.getFramePoints().getFrameArrFormContent().getExactForm());
    }

    private void selectExcRadioFalse(AbsDebuggerGui _b) {
        checkSameFamily(_b.getFramePoints().getFrameExcFormContent().getExactForm());
    }

    private void selectParRadioFalse(AbsDebuggerGui _b) {
        checkSameFamily(_b.getFramePoints().getFrameParFormContent().getExactForm());
    }

    private void selectRenderFalse(AbsDebuggerGui _b) {
        checkSameFamily(_b.getFramePoints().getFrameRenderFormContent().getExactForm());
    }

    private void selectArrRadioTrue(AbsDebuggerGui _b) {
        checkSame(_b.getFramePoints().getFrameArrFormContent().getExactForm());
    }

    private void selectExcRadioTrue(AbsDebuggerGui _b) {
        checkSame(_b.getFramePoints().getFrameExcFormContent().getExactForm());
    }

    private void selectExcRadioInehrit(AbsDebuggerGui _b) {
        checkInehrit(_b.getFramePoints().getFrameExcFormContent().getExactForm());
    }
    private void selectParRadioTrue(AbsDebuggerGui _b) {
        checkSame(_b.getFramePoints().getFrameParFormContent().getExactForm());
    }

    private void selectRenderTrue(AbsDebuggerGui _b) {
        checkSame(_b.getFramePoints().getFrameRenderFormContent().getExactForm());
    }

    private void checkSame(ExactMatchingTypeForm _form) {
        checkSame(_form.getSame(), _form.getSameFamily(), _form.getInherit());
    }

    private void checkInehrit(ExactMatchingTypeForm _form) {
        _form.getSame().setSelected(false);
        _form.getSameFamily().setSelected(false);
        _form.getInherit().setSelected(true);
    }
    private void checkSame(AbsRadioButton _same, AbsRadioButton _sameFamily, AbsRadioButton _inherit) {
        _same.setSelected(true);
        _sameFamily.setSelected(false);
        _inherit.setSelected(false);
    }

    private void checkSameFamily(ExactMatchingTypeForm _form) {
        checkSameFamily(_form.getSame(), _form.getSameFamily(), _form.getInherit());
    }

    private void checkSameFamily(AbsRadioButton _same, AbsRadioButton _sameFamily, AbsRadioButton _inherit) {
        _same.setSelected(false);
        _sameFamily.setSelected(true);
        _inherit.setSelected(false);
    }

    private Struct resNode(AbstractMutableTreeNodeCore<String> _node, AbsDebuggerGui _b) {
        return resNode(_b.getRoot().getNode().simular(_node).info(), _b);
    }

    private Struct resNode(DbgAbsNodeStruct _i, AbsDebuggerGui _b) {
        return TreeNodeRenderUtil.result(RenderPointPair.stopExc(_b.getRenderList(), _i), _i, _b.getFrames());
    }


    private AbsCustComponent compo(AbsScrollPane _sc) {
        return ((MockScrollPane)_sc).getChild();
    }


}
