package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import code.util.classestest.KeyExample;

@SuppressWarnings("static-method")
public class ReversibleMapTest {

/*    @Test
    public void put1Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<>();
        KeyExample k_ = new KeyExample(0, 0);
        KeyExample v_ = new KeyExample(1, 1);
        rev_.put("key_one","value_one");
        assertEq(1, rev_.size());
        assertEq("value_one", rev_.getVal("key_one"));
    }

    @Test
    public void put2Test() {
        ReversibleMap<String, String> rev_;
        rev_ = new ReversibleMap<>();
        KeyExample k_ = new KeyExample(0, 0);
        KeyExample v_ = new KeyExample(1, 1);
        rev_.put("key_one","value_one");
        rev_.put("key_one","value_two");
        assertEq(1, rev_.size());
        assertEq("value_two", rev_.getVal("key_one"));
    }

    @Test
    public void put3Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<>();
        rev_.put("key_one","value_one");
        rev_.put("key_two","value_one");
        assertEq(1, rev_.size());
        assertEq("value_one", rev_.getVal("key_two"));
    }

    @Test
    public void put4Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<>();
        rev_.put("key_one","value_one");
        rev_.put("key_two","value_two");
        assertEq(2, rev_.size());
        assertEq("value_one", rev_.getVal("key_one"));
        assertEq("value_two", rev_.getVal("key_two"));
    }

    @Test
    public void put5Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<>();
        rev_.put("key_one","value_one");
        rev_.put("key_two",null);
        assertEq(2, rev_.size());
        assertEq("value_one", rev_.getVal("key_one"));
        assertTrue(rev_.contains("key_two"));
        assertNull(rev_.getVal("key_two"));
    }

    @Test
    public void put6Test() {
        ReversibleMap<KeyExample, KeyExample> rev_;
        rev_ = new ReversibleMap<>();
        rev_.put("key_one",null);
        rev_.put("key_two",null);
        assertEq(1, rev_.size());
        assertTrue(rev_.contains("key_two"));
        assertNull(rev_.getVal("key_two"));
    }*/

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
