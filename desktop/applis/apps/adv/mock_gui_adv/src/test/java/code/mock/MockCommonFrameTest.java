package code.mock;

import code.gui.AbsCommonFrame;
import org.junit.Test;

public class MockCommonFrameTest extends EquallableMockGuiAdvUtil{
    @Test
    public void ft() {
        MockProgramInfos pr_ = init();
        AbsCommonFrame f_ = pr_.getMockFrameFactory().newCommonFrame("",pr_,pr_.getImageFactory().newImageArgb(1,1));
        f_.pack();
        assertTrue(((MockCustComponent)f_.getPane()).isAccessible());
        pr_.getMockFrameFactory().newOtherFrame();
    }
}
