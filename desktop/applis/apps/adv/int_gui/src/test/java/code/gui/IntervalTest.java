package code.gui;

import org.junit.Test;

public class IntervalTest extends EquallableIntGuiUtil {
    @Test
    public void inter() {
        Interval cur_ = new Interval(5, 6);
        assertEq(30, cur_.getMax()*cur_.getMin());
    }
    @Test
    public void ans() {
        TextAnswerValue cur_ = new TextAnswerValue(5, "_");
        assertEq(5, cur_.getAnswer());
        assertEq("_", cur_.getTypedText());
    }

}
