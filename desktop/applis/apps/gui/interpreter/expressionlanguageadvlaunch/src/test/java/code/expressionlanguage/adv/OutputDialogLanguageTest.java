package code.expressionlanguage.adv;

import code.gui.GuiBaseUtil;
import code.mock.MockPlainButton;
import code.mock.MockWindow;
import org.junit.Test;

public final class OutputDialogLanguageTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogLanguage o_ = language(w_);
        o_.getChosenLanguage().select(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("fr",w_.getUsedLg());
    }

    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogLanguage o_ = language(w_);
        o_.getChosenLanguage().select(1);
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq("en",w_.getUsedLg());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogLanguage o_ = language(w_);
        o_.getChosenLanguage().select(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("fr",w_.getUsedLg());
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        OutputDialogLanguage o2_ = language(w_);
        assertEq("fr", GuiBaseUtil.getSelectedItem(o2_.getChosenLanguage()));
    }
}
