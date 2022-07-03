package code.stream.core;

import code.mock.*;
import code.threads.FileStruct;
import code.util.core.StringUtil;
import org.junit.Test;

public class DefBinFactTest extends EquallableStreamCoreUtil {
    @Test
    public void t1() {
        MockFileSet set_ = fileSet(0,new long[0],"/");
        set_.getFiles().put("/abc",new FileStruct(StringUtil.encode("abc"),0));
        assertEq(3,new DefBinFact(new MockBinFactory(set_)).loadFile("abc").length);
    }
    @Test
    public void t2() {
        assertNull(new DefBinFact(new MockBinFactory(fileSet(0,new long[0],"/"))).loadFile("abc"));
    }
    @Test
    public void t3() {
        MockFileSet set_ = fileSet(0,new long[0],"/");
        new DefBinFact(new MockBinFactory(set_)).writeFile("abc", StringUtil.encode("abc"));
        assertEq("abc",StringUtil.decode(set_.getFiles().getVal("abc").getContent()));
    }

    private static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
}
