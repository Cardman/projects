package code.stream.core;

import code.mock.MockTextFactory;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import org.junit.Test;

public class DefTextFactTest extends EquallableStreamCoreUtil {
    @Test
    public void t1() {
        StringMap<String> f = new StringMap<String>();
        f.put("abc","abc");
        assertEq("abc",new DefTextFact(new MockTextFactory(f)).contentsOfFile("abc",new DefaultUniformingString(),3));
    }
    @Test
    public void t2() {
        StringMap<String> f = new StringMap<String>();
        f.put("abc","abc");
        assertNull(new DefTextFact(new MockTextFactory(f)).contentsOfFile("",new DefaultUniformingString(),3));
    }
    @Test
    public void t3() {
        StringMap<String> f = new StringMap<String>();
        new DefTextFact(new MockTextFactory(f)).write("abc","abc",false);
        assertEq("abc",f.getVal("abc"));
    }
}
