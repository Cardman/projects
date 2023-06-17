package code.expressionlanguage.dbg;

import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalIndexerTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int[] arr={3};public int this(int i){return arr[i];}public void this(int i){arr[i]=value;}public static void exmeth(){new Ex()[0]=5;}}");
        Struct cont_ = valueDbg("2+value","pkg.Ex","exmeth",111,files_);
        assertEq(7,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int[] arr={3};public int this(int i){return arr[i];}public void this(int i){arr[i]=((int value:int)->value+#value).call(value+3);}public static void exmeth(){new Ex()[0]=5;}}");
        Struct cont_ = valueDbg("2+value","pkg.Ex","exmeth",129,files_);
        assertEq(10,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int[] arr={3};public int this(int i){return arr[i];}public void this(int i){arr[i]=((int value:int)->value+#value).call(value+3);}public static void exmeth(){new Ex()[0]=5;}}");
        Struct cont_ = valueDbg("2+#value","pkg.Ex","exmeth",129,files_);
        assertEq(7,((NumberStruct)cont_).intStruct());
    }
}
