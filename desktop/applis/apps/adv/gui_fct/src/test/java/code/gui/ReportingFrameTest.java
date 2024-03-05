package code.gui;

import org.junit.Test;

public final class ReportingFrameTest extends EquallableGuiFctUtil {
    @Test
    public void t0() {
        ReportingFrame r_ = ReportingFrame.newInstance(init());
        r_.display("_");
        assertEq("_",r_.getReport().getText());
    }
}
