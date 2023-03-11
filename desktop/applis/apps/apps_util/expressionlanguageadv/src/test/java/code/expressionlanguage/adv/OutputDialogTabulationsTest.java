package code.expressionlanguage.adv;

import code.mock.MockPlainButton;
import code.mock.MockWindow;
import org.junit.Test;

public final class OutputDialogTabulationsTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogTab o_ = tabulations(w_);
        o_.getTabulation().setValue(8);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq(8,w_.getTabWidth());
    }

    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogTab o_ = tabulations(w_);
        o_.getTabulation().setValue(8);
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq(4,w_.getTabWidth());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        OutputDialogTab o_ = tabulations(w_);
        o_.getTabulation().setValue(8);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertEq(8,w_.getTabWidth());
        ((MockWindow)o_.getFrame()).getWindowListenersDef().get(0).windowClosing();
        assertEq(8,w_.getTabWidth());
        OutputDialogTab o2_ = tabulations(w_);
        assertEq(8,o2_.getTabulation().getValue());
    }
}
