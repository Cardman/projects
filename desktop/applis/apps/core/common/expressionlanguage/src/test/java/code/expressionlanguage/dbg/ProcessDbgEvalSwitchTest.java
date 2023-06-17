package code.expressionlanguage.dbg;

import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalSwitchTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t=8;int u=3;return switch(t+u){case var v?:v==11; return 1;default w; return 1;};}}");
        Struct cont_ = valueDbg("(t-u)*v","pkg.Ex","exmeth",95,files_);
        assertEq(55,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t=8;int u=3;return switch(t+u){case 11?:t+u==11; return 1;default w; return 1;};}}");
        Struct cont_ = valueDbg("t-u","pkg.Ex","exmeth",92,files_);
        assertEq(5,((NumberStruct)cont_).intStruct());
    }
}
