package code.expressionlanguage.adv;

import code.mock.MockProgramInfos;
import org.junit.Test;

public final class RedoUndoActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_);
        assertEq(1, w_.getTabs().get(0).getTexts().size());
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_);
        assertEq(2, w_.getTabs().get(0).getTexts().size());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_);
        assertEq("halo",tabEditor(w_).getCenter().getText());
        undo(w_);
        assertEq("hello",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_);
        undo(w_);
        redo(w_);
        assertEq("halo",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action5() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_);
        undo(w_);
        redo(w_);
        clearEdit(w_);
        assertEq(0,w_.getTabs().get(0).getTexts().size());
    }
}
