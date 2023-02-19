package code.expressionlanguage.adv;

import code.mock.MockPlainButton;
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
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();
        assertEq(4,w_.getTabWidth());
    }
}
