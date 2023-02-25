package code.expressionlanguage.adv;

import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.mock.MockAbstractAction;
import code.mock.MockPlainButton;
import org.junit.Test;

public final class NavRowColActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        assertTrue(act(w_, 1, 1));

        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertTrue(act(w_, 2, 1));

        assertEq(6,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(6,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        assertTrue(act(w_, 2, 5));

        assertEq(7,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(7,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        assertTrue(act(w_, 1, 5));

        assertEq(4,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(4,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action5() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertFalse(enabled(w_, 3, 1));
    }
    @Test
    public void action6() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        assertTrue(act(w_, 1, 6));

        assertEq(5,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(5,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action7() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertTrue(act(w_, 2, 5));

        assertEq(10,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(10,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action8() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertTrue(act(w_, 2, 6));
        assertEq(11,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(11,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action9() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertTrue(act(w_, 1, 1));

        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action10() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertFalse(enabled(w_, 1, 7));
    }
    @Test
    public void action11() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        assertTrue(act(w_, 2, 6));

        assertEq(8,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(8,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action12() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nw\torld");
        assertTrue(act(w_, 2, 5));

        assertEq(8,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(8,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action13() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        assertTrue(act(w_, 2, 9));

        assertEq(11,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(11,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action14() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        assertTrue(act(w_, 2, 10));

        assertEq(12,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(12,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action15() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        assertFalse(enabled(w_, 2, 7));
    }
    @Test
    public void action16() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        assertFalse(enabled(w_, 1, 7));
    }
    @Test
    public void action17() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        assertFalse(enabled(w_, 2, 11));
    }
    private boolean act(WindowCdmEditor _w, int _row, int _col) {
        TabEditor tab_ = prepare(_w, _row, _col);
        assertTrue(tab_.enabled(_row, _col));
        ((MockPlainButton)tab_.getVal()).getActionListeners().get(0).action();
        return true;
    }

    private TabEditor prepare(WindowCdmEditor _w, int _row, int _col) {
        TabEditor tab_ = tabEditor(_w);
        find(tab_);
        tab_.getRow().setValue(_row);
        tab_.getCol().setValue(_col);
        return tab_;
    }

    private void find(TabEditor _tab) {
        MockAbstractAction ac_ = (MockAbstractAction) GuiBaseUtil.getAction(_tab.getCenter(), GuiConstants.VK_F, GuiConstants.CTRL_DOWN_MASK);
        ac_.action();
    }

    private boolean enabled(WindowCdmEditor _w, int _row, int _col) {
        TabEditor tab_ = prepare(_w, _row, _col);
        return tab_.enabled(_row, _col);
    }
}
