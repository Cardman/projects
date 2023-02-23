package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.mock.MockAbstractAction;
import code.util.StringList;
import org.junit.Test;

public final class AutoCompleteDocumentTest extends EquallableGuiFctUtil {
    @Test
    public void prop1() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("ONE","TWO"), pre_);
        f_.setText("ONE");
        assertEq(0,auto_.getList().size());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop2() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("ONE","TWO"), pre_);
        f_.setText("O");
        assertEq(1,auto_.getList().size());
        assertEq("ONE",auto_.getList().get(0));
        assertTrue(auto_.getPopup().isVisible());
    }
    @Test
    public void prop3() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        assertEq(2,auto_.getList().size());
        assertEq("TWO",auto_.getList().get(0));
        assertEq("THREE",auto_.getList().get(1));
        assertEq(0,auto_.getList().getSelectedIndex());
        assertTrue(auto_.getPopup().isVisible());
    }
    @Test
    public void prop4() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        down(auto_);
        assertEq(2,auto_.getList().size());
        assertEq("TWO",auto_.getList().get(0));
        assertEq("THREE",auto_.getList().get(1));
        assertEq(1,auto_.getList().getSelectedIndex());
        assertTrue(auto_.getPopup().isVisible());
    }
    @Test
    public void prop5() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        down(auto_);
        down(auto_);
        assertEq(2,auto_.getList().size());
        assertEq("TWO",auto_.getList().get(0));
        assertEq("THREE",auto_.getList().get(1));
        assertEq(1,auto_.getList().getSelectedIndex());
        assertTrue(auto_.getPopup().isVisible());
    }
    @Test
    public void prop6() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        down(auto_);
        up(auto_);
        assertEq(2,auto_.getList().size());
        assertEq("TWO",auto_.getList().get(0));
        assertEq("THREE",auto_.getList().get(1));
        assertEq(0,auto_.getList().getSelectedIndex());
        assertTrue(auto_.getPopup().isVisible());
    }
    @Test
    public void prop7() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        down(auto_);
        up(auto_);
        up(auto_);
        assertEq(2,auto_.getList().size());
        assertEq("TWO",auto_.getList().get(0));
        assertEq("THREE",auto_.getList().get(1));
        assertEq(0,auto_.getList().getSelectedIndex());
        assertTrue(auto_.getPopup().isVisible());
    }
    @Test
    public void prop8() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        down(auto_);
        assertEq(0,auto_.getList().size());
        assertEq(-1,auto_.getList().getSelectedIndex());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop9() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        up(auto_);
        assertEq(0,auto_.getList().size());
        assertEq(-1,auto_.getList().getSelectedIndex());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop10() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        enter(auto_);
        assertEq("TWO",f_.getText());
        assertEq(0,auto_.getList().size());
        assertEq(-1,auto_.getList().getSelectedIndex());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop11() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        down(auto_);
        enter(auto_);
        assertEq("THREE",f_.getText());
        assertEq(0,auto_.getList().size());
        assertEq(-1,auto_.getList().getSelectedIndex());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop12() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        enter(auto_);
        assertEq("",f_.getText());
        assertEq(0,auto_.getList().size());
        assertEq(-1,auto_.getList().getSelectedIndex());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop13() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("TWO","THREE"), pre_);
        f_.setText("T");
        escape(auto_);
        assertEq("T",f_.getText());
        assertEq(0,auto_.getList().size());
        assertEq(-1,auto_.getList().getSelectedIndex());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop14() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("ONE","TWO"), pre_);
        auto_.setMode(false);
        f_.setText("O");
        assertEq(0,auto_.getList().size());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop15() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("ONE","TWO"), pre_);
        auto_.setDictionary(new StringList("ONE","TWO"));
        auto_.setMode(false);
        auto_.changedUpdate();
        assertEq(0,auto_.getList().size());
        assertFalse(auto_.getPopup().isVisible());
    }
    @Test
    public void prop16() {
        MockProgramInfosSecSample pre_ = init();
        AbsTextField f_ = pre_.getCompoFactory().newTextField();
        AutoCompleteDocument auto_ = new AutoCompleteDocument(f_, new StringList("ONE","TWO"), pre_);
        f_.setText("P");
        f_.setText("");
        assertEq(0,auto_.getList().size());
        assertFalse(auto_.getPopup().isVisible());
    }
    private void down(AutoCompleteDocument _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getTextField(), GuiConstants.VK_DOWN,0)).action();
    }
    private void up(AutoCompleteDocument _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getTextField(), GuiConstants.VK_UP,0)).action();
    }
    private void enter(AutoCompleteDocument _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getTextField(), GuiConstants.VK_ENTER,0)).action();
    }
    private void escape(AutoCompleteDocument _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getTextField(), GuiConstants.VK_ESCAPE,0)).action();
    }
}
