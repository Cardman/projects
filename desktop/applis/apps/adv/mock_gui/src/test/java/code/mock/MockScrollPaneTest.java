package code.mock;

import org.junit.Test;

public final class MockScrollPaneTest extends EquallableMockGuiUtil {
    @Test
    public void s1() {
        MockScrollPane s_ = new MockScrollPane();
        assertEq(0,s_.getChildren().size());
    }
    @Test
    public void s2() {
        MockScrollPane s_ = new MockScrollPane(new MockMetaLabel());
        s_.setViewportView(new MockPlainLabel(""));
        assertEq(1,s_.getChildren().size());
    }
    @Test
    public void s3() {
        MockScrollPane s_ = new MockScrollPane(new MockPlainLabel(""));
        assertEq(1,s_.getChildren().size());
    }
    @Test
    public void s4() {
        MockScrollPane s_ = new MockScrollPane(new MockPlainLabel(""));
        s_.recalculateViewport();
        assertEq(1,s_.getChildren().size());
    }
    @Test
    public void s5() {
        MockScrollPane s_ = new MockScrollPane();
        s_.recalculateViewport();
        assertEq(0,s_.getChildren().size());
    }
    @Test
    public void s6() {
        MockScrollPane s_ = new MockScrollPane(new MockPlainLabel(""));
        s_.setNullViewportView();
        assertEq(0,s_.getChildren().size());
        s_.viewRect();
    }
    @Test
    public void s7() {
        MockScrollPane s_ = new MockScrollPane();
        s_.setVerticalValue(10);
        s_.setHorizontalValue(1);
        assertEq(1,s_.getHorizontalValue());
        assertEq(10,s_.getVerticalValue());
    }
}
