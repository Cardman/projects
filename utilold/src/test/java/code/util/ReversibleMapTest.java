package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import code.util.classestest.KeyExample;

@SuppressWarnings("static-method")
public class ReversibleMapTest {

    @Test
    public void getKey1Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<KeyExample, KeyExample>();
        KeyExample k_ = new KeyExample(0, 0);
        KeyExample v_ = new KeyExample(1, 1);
        rev_.put(k_,v_);
        assertEq(k_, rev_.getKey(v_));
    }

    @Test
    public void getKey2Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<KeyExample, KeyExample>();
        KeyExample k_ = new KeyExample(0, 0);
        KeyExample v_ = new KeyExample(1, 1);
        rev_.put(k_,v_);
        assertNull(rev_.getKey(k_));
    }

    @Test
    public void getKey3Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<KeyExample, KeyExample>();
        KeyExample k_ = new KeyExample(0, 0);
        rev_.put(k_,null);
        assertEq(k_, rev_.getKey(null));
    }

    @Test
    public void getKey4Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<KeyExample, KeyExample>();
        KeyExample k_ = new KeyExample(0, 0);
        KeyExample v_ = new KeyExample(1, 1);
        rev_.put(k_,v_);
        assertNull(rev_.getKey(null));
    }

    @Test
    public void reverse1Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<KeyExample, KeyExample>();
        KeyExample k_ = new KeyExample(0, 0);
        KeyExample v_ = new KeyExample(1, 1);
        rev_.put(k_,v_);
        ReversibleMap<KeyExample, KeyExample> res_ = rev_.reverse();
        assertEq(1, res_.size());
        assertEq(k_, res_.getVal(v_));
    }
}
