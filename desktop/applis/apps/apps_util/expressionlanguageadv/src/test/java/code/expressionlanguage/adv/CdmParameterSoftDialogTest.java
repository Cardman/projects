package code.expressionlanguage.adv;

import code.mock.MockPlainButton;
import org.junit.Test;

public final class CdmParameterSoftDialogTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        CdmParameterSoftDialog o_ = softParams(w_);
        o_.getCheck().setSelected(true);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(w_.getSoftParams().isDirectMatchKeyValue());
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        CdmParameterSoftDialog o_ = softParams(w_);
        o_.getCheck().setSelected(false);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(w_.getSoftParams().isDirectMatchKeyValue());
    }
}
