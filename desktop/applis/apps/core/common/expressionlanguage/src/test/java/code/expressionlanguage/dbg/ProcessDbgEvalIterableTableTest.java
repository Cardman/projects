package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalIterableTableTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustTable<int,int> inst=new();int res;inst.add(3,5);inst.add(8,1);inst.add(2,6);for(int y,int u:inst){res+=y+u;}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Struct cont_ = valueDbg("(y-u)*(y+u)","pkg.Ex","exmeth",155,files_);
        assertEq(-16,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustTable<int,int> inst=new();int res;inst.add(3,5);inst.add(8,4);inst.add(2,6);for(int y,int u:inst){if(([y])==1){res+=y+u;}}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Struct cont_ = valueDbg("(y+u)*(([y])+([u]))","pkg.Ex","exmeth",163,files_);
        assertEq(24,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustTable<int,int> inst=new();int res;inst.add(3,5);inst.add(8,1);inst.add(2,6);for(int y,int u:inst){res+=y+u;}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ReportedMessages cont_ = valueDbgKo("u","pkg.Ex","exmeth", 144,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){CustTable<int,int> inst=new();int res;inst.add(3,5);inst.add(8,1);inst.add(2,6);for(int y,int u:inst){res+=y+u;}return res;}}");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ReportedMessages cont_ = valueDbgKo("y","pkg.Ex","exmeth", 144,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
}
