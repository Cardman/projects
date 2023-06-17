package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalIterLoopTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustList<int> inst=new();int res;inst.add(3);inst.add(1);inst.add(2);for(int e:inst){res+=e;}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        Struct cont_ = valueDbg("2*e","pkg.Ex","exmeth",138,files_);
        assertEq(6,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustList<int> inst=new();int res;inst.add(3);inst.add(4);inst.add(2);for(int e:inst){if(([e])==1){res+=e;}}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        Struct cont_ = valueDbg("e+([e])","pkg.Ex","exmeth",146,files_);
        assertEq(5,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustList<int> inst=new();int res;inst.add(3);inst.add(1);inst.add(2);for(int e:inst){res+=e;}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ReportedMessages cont_ = valueDbgKo("e","pkg.Ex","exmeth", 127,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
}
