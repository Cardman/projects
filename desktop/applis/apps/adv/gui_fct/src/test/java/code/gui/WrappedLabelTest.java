package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import org.junit.Test;

public final class WrappedLabelTest extends EquallableGuiFctUtil {
    @Test
    public void lines1() {
        MockProgramInfosSecSample init_ = init();
        WrappedLabel w_ = new WrappedLabel(init_.getImageFactory(),"",init_.getCompoFactory());
        assertEq(1,w_.getLines().size());
    }
    @Test
    public void lines2() {
        MockProgramInfosSecSample init_ = init();
        WrappedLabel w_ = new WrappedLabel(init_.getImageFactory(),"a\nb\nc\n\nde\nf\nghi",init_.getCompoFactory());
        assertEq(7,w_.getLines().size());
    }
}
