package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.mock.MockProgramInfos;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class DbgActTest extends EquallableElAdvUtil {
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
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getInstanceType().setSelected(false);
//        bpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertTrue(curRet(b_).getPair(file(curRet(b_)),13).getValue().isInstanceType());
        assertFalse(curRet(b_).getPair(file(curRet(b_)),13).getValue().isStaticType());
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
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getInstanceType().setSelected(false);
        bpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertFalse(curRet(b_).getPair(file(curRet(b_)),13).getValue().isInstanceType());
        assertTrue(curRet(b_).getPair(file(curRet(b_)),13).getValue().isStaticType());
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
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(false);
        b_.getFramePoints().getFrameBpFormContent().getInstanceType().setSelected(true);
        bpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getInstanceType().setSelected(false);
//        bpFormCancel(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertTrue(curRet(b_).getPair(file(curRet(b_)),13).getValue().isInstanceType());
        assertFalse(curRet(b_).getPair(file(curRet(b_)),13).getValue().isStaticType());
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
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(false);
        b_.getFramePoints().getFrameBpFormContent().getInstanceType().setSelected(true);
        bpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        bpForm(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getInstanceType().setSelected(false);
        bpFormOk(b_);
        assertTrue(b_.getFramePoints().getCommonFrame().isVisible());
        assertFalse(curRet(b_).getPair(file(curRet(b_)),13).getValue().isInstanceType());
        assertTrue(curRet(b_).getPair(file(curRet(b_)),13).getValue().isStaticType());
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
        toggleBp(b_);
        bpForm(b_);
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
        toggleBp(b_);
        bpForm(b_);
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
        bpForm(b_);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(false);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
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
        toggleBp(b_);
        bpForm(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        toggleBpEn(b_);
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
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        assertEq(1,b_.getFramePoints().getView().getChildren().size());
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
        toggleBp(b_);
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        toggleBp(b_);
        assertEq(1,b_.getFramePoints().getView().getChildren().size());
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
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        assertEq(1,b_.getFramePoints().getView().getChildren().size());
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
        toggleBp(b_);
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        assertEq(1,b_.getFramePoints().getView().getChildren().size());
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
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthBp(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        addBpOk(b_);
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(false);
        addExcOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthExc(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(3,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
        addExcOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthExc(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(3,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthMethod(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthWatch(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(5,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthStd(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        editOthBp(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
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
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(false);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        addExcOk(b_);
        assertTrue(curRet(b_).getPairExc("pkg.Ex",false).getValue().getResultThrown().getOthers().elts().iterator().hasNext());
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        b_.getFramePoints().getFrameExcFormContent().getGuiThrownStackForm().getDependantPointsForm().getChecksCurrent().get(2).setSelected(true);
        addExcOk(b_);
        assertTrue(curRet(b_).getPairExc("pkg.Ex",true).getValue().getResultThrown().getOthers().elts().iterator().hasNext());
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
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        AbsPlainButton last_ = (AbsPlainButton) bs_.getComponent(bs_.getComponentCount()-1);
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getAdd().getActionListeners().get(0).action();
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getGeneKey().value("_");
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getGeneKey().value("pkg.Ex");
        last_.getActionListeners().get(0).action();
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getGeneValue().value(1);
        b_.getFramePoints().getFrameFormContent().getGuiEnterStackForm().getPrefs().getValidAddEdit().getActionListeners().get(0).action();
        addMpOk(b_);
        assertEq(0,b_.getFramePoints().getView().getChildren().size());
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
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthWatchAnnot(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(5,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
        addExcOk(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex");
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(false);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
        addExcOk(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex2");
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
        addExcOk(b_);
        addExc(b_);
        b_.getFramePoints().getFrameExcFormContent().getClName().setText("pkg.Ex<long>");
        b_.getFramePoints().getFrameExcFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(false);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(false);
        addArrOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthArr(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
        addArrOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthArr(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(false);
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
        addArrOk(b_);
        assertTrue(curRet(b_).getPairArr("[pkg.Ex",false).getValue().getResultLength().getOthers().elts().iterator().hasNext());
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        addArrOk(b_);
        assertTrue(curRet(b_).getPairArr("[pkg.Ex",true).getValue().getResultLength().getOthers().elts().iterator().hasNext());
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
        addArrOk(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex");
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(false);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
        addArrOk(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex2");
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
        addArrOk(b_);
        addArr(b_);
        b_.getFramePoints().getFrameArrFormContent().getClName().setText("[pkg.Ex<long>");
        b_.getFramePoints().getFrameArrFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(false);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(false);
        addParOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthPar(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
        addParOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthPar(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(false);
        b_.getFramePoints().getFrameParFormContent().getGuiGetStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        addParOk(b_);
        assertTrue(curRet(b_).getPairPar("pkg.Ex",false).getValue().getResultGet().getOthers().elts().iterator().hasNext());
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
        b_.getFramePoints().getFrameParFormContent().getGuiGetStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        addParOk(b_);
        assertTrue(curRet(b_).getPairPar("pkg.Ex",true).getValue().getResultGet().getOthers().elts().iterator().hasNext());
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
        addParOk(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex");
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(false);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
        addParOk(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex2");
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
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
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
        addParOk(b_);
        addPar(b_);
        b_.getFramePoints().getFrameParFormContent().getClName().setText("pkg.Ex<long>");
        b_.getFramePoints().getFrameParFormContent().getExact().setSelected(true);
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
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("int");
        addOperNatOk(b_);
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
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("int");
        addOperNatOk(b_);
        editOperNat(b_,0);
        assertTrue(curRet(b_).getContext().operNatList().elts().iterator().hasNext());
        addOperNatRemove(b_);
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
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("int");
        addOperNatOk(b_);
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthOperNat(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(2,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        addBp(b_);
        b_.getFramePoints().getFrameBpFormContent().getFileName().setText("src/file.txt");
        b_.getFramePoints().getFrameBpFormContent().getCaret().setValue(13);
        b_.getFramePoints().getFrameBpFormContent().getEnabledBp().setSelected(true);
        editOthOperNat(b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm(),0);
        assertEq(1,b_.getFramePoints().getFrameBpFormContent().getGuiStdStackForm().getDependantPointsForm().getChecks().size());
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
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameOperNatFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addOperNatOk(b_);
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
        addOperNat(b_);
        b_.getFramePoints().getFrameOperNatFormContent().getSymbol().setText("%");
        b_.getFramePoints().getFrameOperNatFormContent().getFirst().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getSecond().setText("int");
        b_.getFramePoints().getFrameOperNatFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(0).setSelected(true);
        b_.getFramePoints().getFrameOperNatFormContent().getGuiCompoundStackForm().getDependantPointsForm().getChecksCurrent().get(1).setSelected(true);
        addOperNatOk(b_);
        editOperNat(b_,0);
        addOperNatOk(b_);
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getNextSibling());
        assertEq(2,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertSame(NullStruct.NULL_VALUE,root_.getChildren().get(0).value());
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).getChildren().get(0).value()).intStruct());
        assertEq("pkg.Ex|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertEq(4,((NumberStruct)root_.getChildren().get(0).getChildren().get(1).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).getChildren().get(0).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex..Inner",root_.getChildren().get(0).str());
        assertEq("pkg.Ex..Inner",root_.getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("|",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq("pkg.Ex..Inner|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).getChildren().get(1).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getNextSibling());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getNextSibling().getNextSibling());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getNextSibling().getNextSibling().getNextSibling());
        assertEq(4,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertSame(NullStruct.NULL_VALUE,root_.getChildren().get(0).value());
        assertEq(0,root_.getChildren().get(0).getChildren().size());
        assertEq("-1|i|int([0])",root_.getChildren().get(1).str());
        assertEq(0,((NumberStruct)root_.getChildren().get(1).value()).intStruct());
        assertEq(0,root_.getChildren().get(1).getChildren().size());
        assertEq("-1|s|int",root_.getChildren().get(2).str());
        assertEq(0,((NumberStruct)root_.getChildren().get(2).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).value().getClassName(curRet(b_).getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("pkg.Ex|a",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).getChildren().get(0).value()).intStruct());
        assertEq("pkg.Super|b",root_.getChildren().get(0).getChildren().get(1).str());
        assertEq(4,((NumberStruct)root_.getChildren().get(0).getChildren().get(1).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("|",root_.getChildren().get(0).str());
        assertEq(1,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("int",root_.getChildren().get(0).str());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        b_.getFramePoints().getFrameBpFormContent().getStaticType().setSelected(true);
        bpFormOk(b_);
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("int",root_.getChildren().get(0).str());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
    }
    @Test
    public void i9() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {static int a=2;static int b=4;public static that int exmeth(String[] v){return that(a);}}");
        guiAna(r_,b_,o_,src_);
        launch(b_);
        b_.selectFocus(-1,-1);
        b_.focus(-1);
        b_.possibleSelect(-1,r_);
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getNextSibling());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        assertTrue(b_.getNextCursor().isEnabled());
        tabEditor(b_).getCenter().select(85,85);
        nextCursor(b_);
        assertTrue(b_.getNextCursor().isEnabled());
        nextGoInMethod(b_);
        assertFalse(b_.getNextCursor().isEnabled());
        DbgRootStruct root_ = b_.getRoot();
        IdList<AbstractMutableTreeNodeCore<DbgAbsNodeStruct>> chs_ = root_.getNode().children();
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        trDetail_.select(null);
        trDetail_.select(trDetail_.getRoot());
        assertEq(1,root_.getChildren().size());
        assertEq("|",root_.getChildren().get(0).str());
        assertEq(1,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        assertEq("log\n",((AbsTextArea)b_.getStatusDbgAreaScroll().getChildren().get(0)).getText());
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
    private void launch(AbsDebuggerGui _d) {
        _d.getSelectEnter().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
    }

    private void next(AbsDebuggerGui _d) {
        _d.getNextAction().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
    }

    private void nextInst(AbsDebuggerGui _d) {
        _d.getNextInstruction().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
    }

    private void nextBlock(AbsDebuggerGui _d) {
        _d.getNextBlock().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
    }

    private void nextGoUp(AbsDebuggerGui _d) {
        _d.getNextGoUp().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
    }

    private void nextCursor(AbsDebuggerGui _d) {
        _d.getNextCursor().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
    }

    private void nextGoInMethod(AbsDebuggerGui _d) {
        _d.getNextInMethod().getActionListeners().get(0).action();
        _d.getCurrentThreadActions().join();
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

}
