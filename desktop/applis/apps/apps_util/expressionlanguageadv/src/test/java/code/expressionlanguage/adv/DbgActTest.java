package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.mock.MockPlainButton;
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
        guiNoAna(b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBp(b_);
        assertEq(55,tabEditor(b_).getCenter().getCaretPosition());
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
        guiNoAna(b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        toggleBpEn(b_);
        assertEq(55,tabEditor(b_).getCenter().getCaretPosition());
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
        assertTrue(b_.getBpForm().isVisible());
        b_.getEnabledBp().setSelected(false);
        bpFormCancel(b_);
        assertFalse(b_.getBpForm().isVisible());
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
        assertTrue(b_.getBpForm().isVisible());
        b_.getEnabledBp().setSelected(false);
        bpFormOk(b_);
        assertFalse(b_.getBpForm().isVisible());
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
        assertTrue(b_.getBpForm().isVisible());
        b_.getStaticType().setSelected(true);
        b_.getInstanceType().setSelected(false);
        bpFormCancel(b_);
        assertFalse(b_.getBpForm().isVisible());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isInstanceType());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isStaticType());
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
        assertTrue(b_.getBpForm().isVisible());
        b_.getStaticType().setSelected(true);
        b_.getInstanceType().setSelected(false);
        bpFormOk(b_);
        assertFalse(b_.getBpForm().isVisible());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isInstanceType());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isStaticType());
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
        assertTrue(b_.getBpForm().isVisible());
        b_.getStaticType().setSelected(false);
        b_.getInstanceType().setSelected(true);
        bpFormOk(b_);
        assertFalse(b_.getBpForm().isVisible());
        bpForm(b_);
        assertTrue(b_.getBpForm().isVisible());
        b_.getStaticType().setSelected(true);
        b_.getInstanceType().setSelected(false);
        bpFormCancel(b_);
        assertFalse(b_.getBpForm().isVisible());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isInstanceType());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isStaticType());
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
        assertTrue(b_.getBpForm().isVisible());
        b_.getStaticType().setSelected(false);
        b_.getInstanceType().setSelected(true);
        bpFormOk(b_);
        assertFalse(b_.getBpForm().isVisible());
        bpForm(b_);
        assertTrue(b_.getBpForm().isVisible());
        b_.getStaticType().setSelected(true);
        b_.getInstanceType().setSelected(false);
        bpFormOk(b_);
        assertFalse(b_.getBpForm().isVisible());
        assertFalse(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isInstanceType());
        assertTrue(b_.getCurrentResult().getContext().getClasses().getDebugMapping().getBreakPointsBlock().get(file(b_.getCurrentResult()),13).isStaticType());
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
        assertFalse(b_.getBpForm().isVisible());
    }
    @Test
    public void bp13() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        save(b_,src_,"src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        guiNoAna(b_,o_,src_);
        tabEditor(b_).getCenter().select(55,55);
        bpForm(b_);
        assertEq(55,tabEditor(b_).getCenter().getCaretPosition());
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
        guiNoAna(b_,o_,src_);
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
        StringMap<String> src_ = new StringMap<String>();
        guiNoAna(b_,o_,src_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        assertEq(0,f_.getOutput().size());
    }
    @Test
    public void arg2() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        guiNoAna(b_,o_,src_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        validValues(f_);
        assertEq(1,f_.getOutput().size());
        assertEq("Arg",f_.getOutput().get(0).getInstance());
    }
    @Test
    public void arg3() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        guiNoAna(b_,o_,src_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg");
        validValues(f_);
        f_.getCommentsRows().get(0).getSelectForDelete().setSelected(true);
        remRow(f_);
        validValues(f_);
        assertEq(0,f_.getOutput().size());
    }
    @Test
    public void arg4() {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        StringMap<String> src_ = new StringMap<String>();
        guiNoAna(b_,o_,src_);
        FormInputDebugLines f_ = formArgs(b_);
        addRow(f_);
        f_.getCommentsRows().get(0).getValueArea().setText("Arg1");
        addRow(f_);
        f_.getCommentsRows().get(1).getValueArea().setText("Arg2");
        validValues(f_);
        f_.getCommentsRows().get(0).getSelectForDelete().setSelected(true);
        remRow(f_);
        validValues(f_);
        assertEq(1,f_.getOutput().size());
        assertEq(0,f_.getCommentsRows().get(0).getIndex());
        assertEq("Arg2",f_.getOutput().get(0).getInstance());
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
        validValues(f_);
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
        validValues(f_);
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
        validValues(f_);
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
        validValues(f_);
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
        validValues(f_);
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
        validValues(f_);
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
        validValues(f_);
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
        b_.getStaticType().setSelected(true);
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
        validValues(f_);
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
    private void launch(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getSelectEnter()).getActionListeners().get(0).action();
    }

    private void next(AbsDebuggerGui _d) {
        ((MockPlainButton)_d.getNextAction()).getActionListeners().get(0).action();
    }
    private void addRow(FormInputDebugLines _r) {
        ((MockPlainButton) _r.getAdd()).getActionListeners().get(0).action();
    }
    private void remRow(FormInputDebugLines _r) {
        ((MockPlainButton) _r.getRem()).getActionListeners().get(0).action();
    }
    private void validValues(FormInputDebugLines _r) {
        ((MockPlainButton) _r.getVal()).getActionListeners().get(0).action();
    }
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
