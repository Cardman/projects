package code.mock;

import org.junit.Test;

public final class MockMillisTest extends EquallableMockGuiUtil {
    @Test
    public void test1() {
        MockMillis mockMillis_ = new MockMillis(10,new long[0]);
        assertEq(10, mockMillis_.millis());
    }
    @Test
    public void test2() {
        long[] incrs_ = new long[1];
        incrs_[0] = 5;
        MockMillis mockMillis_ = new MockMillis(10, incrs_);
        assertEq(15, mockMillis_.millis());
    }
    @Test
    public void test3() {
        long[] incrs_ = new long[1];
        incrs_[0] = 5;
        MockMillis mockMillis_ = new MockMillis(10, incrs_);
        mockMillis_.millis();
        assertEq(20, mockMillis_.millis());
    }
    @Test
    public void test4() {
        long[] incrs_ = new long[2];
        incrs_[0] = 5;
        incrs_[1] = 2;
        MockMillis mockMillis_ = new MockMillis(10, incrs_);
        mockMillis_.millis();
        assertEq(17, mockMillis_.millis());
    }
    @Test
    public void test5() {
        long[] incrs_ = new long[2];
        incrs_[0] = 5;
        incrs_[1] = 2;
        MockMillis mockMillis_ = new MockMillis(10, incrs_);
        mockMillis_.millis();
        mockMillis_.millis();
        assertEq(22, mockMillis_.millis());
    }
}
