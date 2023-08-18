package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import code.util.CustList;
import code.util.IdList;
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
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(b_.getCurrentResult()),55));
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
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(b_.getCurrentResult()),55));
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
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(b_.getCurrentResult()),55));
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
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getEnabledBp().setSelected(false);
        bpFormCancel(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(b_.getCurrentResult()),55));
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
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getEnabledBp().setSelected(false);
        bpFormOk(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(b_.getCurrentResult()),55));
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
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getStaticType().setSelected(true);
        b_.getFrameBpForm().getInstanceType().setSelected(false);
        bpFormCancel(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isInstanceType());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isStaticType());
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
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getStaticType().setSelected(true);
        b_.getFrameBpForm().getInstanceType().setSelected(false);
        bpFormOk(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isInstanceType());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isStaticType());
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
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getStaticType().setSelected(false);
        b_.getFrameBpForm().getInstanceType().setSelected(true);
        bpFormOk(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        bpForm(b_);
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getStaticType().setSelected(true);
        b_.getFrameBpForm().getInstanceType().setSelected(false);
        bpFormCancel(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isInstanceType());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isStaticType());
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
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getStaticType().setSelected(false);
        b_.getFrameBpForm().getInstanceType().setSelected(true);
        bpFormOk(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        bpForm(b_);
        assertTrue(b_.getFrameBpForm().getCommonFrame().isVisible());
        b_.getFrameBpForm().getStaticType().setSelected(true);
        b_.getFrameBpForm().getInstanceType().setSelected(false);
        bpFormOk(b_);
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isInstanceType());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(file(b_.getCurrentResult()),13).getValue().isStaticType());
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
        assertFalse(b_.getFrameBpForm().getCommonFrame().isVisible());
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
        GuiStackForm.remove(new CustList<AbsCallContraints>(),null);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(2,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddInc(b_);
        assertEq(2,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(2,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        assertEq(2,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormStdRemExc(b_,0);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        bpFormStdRemInc(b_,0);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        t_.select(t_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getSingleCaret().setSelected(false);
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddExc(b_);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getSingleCaret().setSelected(false);
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(56,56);
        bpFormStdAddInc(b_);
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddExc(b_);
        bpFormOk(b_);
        bpForm(b_);
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        AbsTreeGui t_ = b_.getFrameBpForm().getGuiStdStackForm().getBpFolderSystem();
        t_.select(null);
        t_.select(t_.getRoot());
        t_.select(t_.getRoot().getFirstChild());
        t_.select(t_.getRoot().getFirstChild().getFirstChild());
        b_.getFrameBpForm().getGuiStdStackForm().getReadOnlyFormTabEditor().getCenter().select(55,55);
        bpFormStdAddInc(b_);
        bpFormOk(b_);
        bpForm(b_);
        assertEq(1,b_.getFrameBpForm().getGuiStdStackForm().getMustBe().size());
        assertEq(0,b_.getFrameBpForm().getGuiStdStackForm().getMustNotBe().size());
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
        assertTrue(b_.getFrameMpForm().getCommonFrame().isVisible());
        b_.getFrameMpForm().getEnabledMp().setSelected(false);
        mpFormCancel(b_);
        assertFalse(b_.getFrameMpForm().getCommonFrame().isVisible());
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
        assertTrue(b_.getFrameMpForm().getCommonFrame().isVisible());
        b_.getFrameMpForm().getEnabledMp().setSelected(false);
        mpFormOk(b_);
        assertFalse(b_.getFrameMpForm().getCommonFrame().isVisible());
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
        assertFalse(b_.getFrameMpForm().getCommonFrame().isVisible());
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
        assertTrue(b_.getFrameWpForm().getCommonFrame().isVisible());
        b_.getFrameWpForm().getEnabledWp().setSelected(false);
        wpFormOk(b_);
        assertFalse(b_.getFrameWpForm().getCommonFrame().isVisible());
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
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().is(file(b_.getCurrentResult()),75));
        wpForm(b_);
        assertFalse(b_.getFrameWpForm().getCommonFrame().isVisible());
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
        assertTrue(b_.getFrameWpForm().getCommonFrame().isVisible());
        b_.getFrameWpForm().getEnabledWp().setSelected(false);
        wpFormOk(b_);
        assertFalse(b_.getFrameWpForm().getCommonFrame().isVisible());
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
        assertTrue(b_.getFrameWpForm().getCommonFrame().isVisible());
        b_.getFrameWpForm().getEnabledWp().setSelected(false);
        wpFormCancel(b_);
        assertFalse(b_.getFrameWpForm().getCommonFrame().isVisible());
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
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().hasNext());
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
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().hasNext());
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
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().hasNext());
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
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().next().getValue().isPropagated());
        editExc(b_,0);
        b_.getFramePoints().getFrameExcFormContent().getPropagated().setSelected(false);
        addExcOk(b_);
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().next().getValue().isPropagated());
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
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().next().getValue().isPropagated());
        editExc(b_,0);
        addExcRemove(b_);
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList().elts().iterator().hasNext());
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
        ((MockPlainButton)b_.getStopStack()).getActionListeners().get(0).action();
        launch(b_);
        assertTrue(b_.getStopDbg().get());
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).value().getClassName(b_.getCurrentResult().getContext()));
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex..Inner",root_.getChildren().get(0).str());
        assertEq("pkg.Ex..Inner",root_.getChildren().get(0).value().getClassName(b_.getCurrentResult().getContext()));
        assertEq(2,root_.getChildren().get(0).getChildren().size());
        assertEq("|",root_.getChildren().get(0).getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).getChildren().get(0).value().getClassName(b_.getCurrentResult().getContext()));
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild());
        trDetail_.select(trDetail_.getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,root_.getChildren().size());
        assertEq("pkg.Ex",root_.getChildren().get(0).str());
        assertEq("pkg.Ex",root_.getChildren().get(0).value().getClassName(b_.getCurrentResult().getContext()));
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        b_.getFrameBpForm().getStaticType().setSelected(true);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        b_.possibleSelect(-1);
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
        ((MockPlainButton)b_.getCallButtons().get(0)).getActionListeners().get(0).action();
        DbgRootStruct root_ = b_.getRoot();
        assertEq("",root_.str());
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
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
        IdList<AbstractMutableTreeNodeCore> chs_ = MutableTreeNodeCoreUtil.children(root_);
        assertEq(1,chs_.size());
        AbsTreeGui trDetail_ = b_.getTreeDetail();
        trDetail_.select(trDetail_.getRoot());
        trDetail_.select(trDetail_.getRoot().getFirstChild());
        assertEq(1,root_.getChildren().size());
        assertEq(2,((NumberStruct)root_.getChildren().get(0).value()).intStruct());
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
        ((MockPlainButton)b_.getPauseStack()).getActionListeners().get(0).action();
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
        assertEq("src/",t_.selectEvt().getUserObject());
        assertEq(4,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild()).getChildCount());
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
        assertEq("sub1/",t_.selectEvt().getUserObject());
        assertEq(3,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild()).getChildCount());
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
        assertEq("sub3/",t_.selectEvt().getUserObject());
        assertEq(2,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild()).getChildCount());
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
        assertEq("sub2/",t_.selectEvt().getUserObject());
        assertEq(3,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("sub4/",t_.selectEvt().getUserObject());
        assertEq(2,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild()).getChildCount());
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
        assertEq("file1.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file2.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file3.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file4.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file5.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file6.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file7.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild()).getChildCount());
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
        assertEq("file8.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file0.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild()).getChildCount());
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
        assertEq("file9.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file1.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file2.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file3.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file4.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file5.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file6.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNextSibling().getNextSibling()).getChildCount());
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
        assertEq("file7.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild()).getChildCount());
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
        assertEq("file8.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        assertEq("file0.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild()).getChildCount());
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
        assertEq("file9.txt",t_.selectEvt().getUserObject());
        assertEq(0,((AbstractMutableTreeNodeNav)t_.getRoot().getFirstChild().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getNextSibling()).getChildCount());
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
        ((MockPlainButton)_d.getSelectEnter()).getActionListeners().get(0).action();
    }

    private void next(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextAction()).getActionListeners().get(0).action();
    }

    private void nextInst(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextInstruction()).getActionListeners().get(0).action();
    }

    private void nextBlock(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextBlock()).getActionListeners().get(0).action();
    }

    private void nextGoUp(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextGoUp()).getActionListeners().get(0).action();
    }

    private void nextCursor(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextCursor()).getActionListeners().get(0).action();
    }

    private void nextGoInMethod(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextInMethod()).getActionListeners().get(0).action();
    }
    private void addRow(FormInputDebugLines _r) {
        ((MockPlainButton) _r.getAdd()).getActionListeners().get(0).action();
    }
    private void remRow(FormInputDebugLines _r) {
        ((MockPlainButton) _r.getRem()).getActionListeners().get(0).action();
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
        return _cont.getContext().getClasses().getDebugMapping().getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }
}
