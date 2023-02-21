package code.expressionlanguage.adv;

import code.mock.MockPlainButton;
import org.junit.Test;

public final class OutputDialogLanguageTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogLanguage o_ = language(w_);
        o_.getChosenLanguage().selectItem(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq("fr",w_.getUsedLg());
    }

    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogLanguage o_ = language(w_);
        o_.getChosenLanguage().selectItem(1);
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();
        assertEq("en",w_.getUsedLg());
    }
}
