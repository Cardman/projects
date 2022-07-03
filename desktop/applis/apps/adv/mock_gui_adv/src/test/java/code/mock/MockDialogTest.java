package code.mock;

import code.gui.AbsDialog;
import org.junit.Test;

public class MockDialogTest extends EquallableMockGuiAdvUtil{
    @Test
    public void ft() {
        MockProgramInfos pr_ = init();
        AbsDialog f_ = pr_.getMockFrameFactory().newDialog();
        AbsDialog g_ = pr_.getMockFrameFactory().newDialog(f_);
        g_.pack();
        assertTrue(((MockCustComponent)g_.getPane()).isAccessible());
        pr_.getMockFrameFactory().newOtherDialog();
    }
}
