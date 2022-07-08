package code.gui.events;

import code.gui.EquallableGuiFctUtil;
import code.mock.MockCloseableDialog;
import org.junit.Test;

public final class ClosingDialogEventTest extends EquallableGuiFctUtil {
    @Test
    public void close1() {
        MockDialogSecSample d_ = new MockDialogSecSample(new MockProgramInfosSecSample("", "", new double[0], 0, new long[0], true, "/"));
        d_.setVisible(true);
        new ClosingDialogEvent(d_,new MockCloseableDialog(d_)).action();
        assertFalse(d_.isVisible());
    }
    @Test
    public void close2() {
        MockDialogSecSample d_ = new MockDialogSecSample(new MockProgramInfosSecSample("", "", new double[0], 0, new long[0], true, "/"));
        d_.setVisible(true);
        new ClosingDialogEvent(d_).action();
        assertFalse(d_.isVisible());
    }
    @Test
    public void close3() {
        MockDialogSecSample d_ = new MockDialogSecSample(new MockProgramInfosSecSample("", "", new double[0], 0, new long[0], true, "/"));
        d_.setVisible(true);
        new CrossClosingDialogListEvent(d_,new MockCloseableDialog(d_)).windowClosing();
        assertFalse(d_.isVisible());
    }
}
