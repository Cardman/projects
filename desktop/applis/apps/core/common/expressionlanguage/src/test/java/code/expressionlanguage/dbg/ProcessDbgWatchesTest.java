package code.expressionlanguage.dbg;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.BreakPointOutputInfo;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgWatchesTest extends ProcessDbgCommon {
    @Test
    public void logs1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int m(){return 0;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("", "\"log\"", "src/file.txt", 49, "pkg.Ex", "m", files_, false, 0, false, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertEq("log",getString(logs_.getWatchResults().getWatchedObject()));
    }
    @Test
    public void logs2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int n(){return 0;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("", "", "src/file.txt", 49, "pkg.Ex", "n", files_, true, 0, false, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertNull(logs_.getWatchResults().getWatchedObject());
    }
    @Test
    public void logs3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("", "1/0", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertNull(logs_.getWatchResults().getWatchedObject());
        ArrayStruct stack_ = (ArrayStruct) logs_.getWatchResults().getWatchedTrace();
        assertEq(1,stack_.getLength());
        assertEq(":1,2:1\n" +
                "pkg.Ex.static .1()", ((StackTraceElementStruct)stack_.get(0)).getDisplayedString(null).getInstance());
    }
    @Test
    public void logs4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("", "{static class Sec{public String $toString(){return \"log\";}}return new Sec();}", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertEq("pkg.Ex..Sec+1",logs_.getWatchResults().getWatchedObject().getClassName(logs_.getWatchResults().getSubContext()));
    }
    @Test
    public void logs5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("", "{static class Sec{public String $toString(){return \"\"+(1/0);}}return new Sec();}", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertEq("pkg.Ex..Sec+1",logs_.getWatchResults().getWatchedObject().getClassName(logs_.getWatchResults().getSubContext()));
    }
    @Test
    public void logs6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file2.txt","public class pkg.Ex2{public static int m(){int j;iter(int i=0;15;1){j++;}return j;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("i%3==0", "i", "src/file2.txt", 68, "pkg.Ex2", "m", files_, false, 2, false, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertEq(3,getNumber(logs_.getWatchResults().getWatchedObject()));
    }
    @Test
    public void logs7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file2.txt","public class pkg.Ex2{public static int m(){int j;iter(int i=0;15;1){j++;}return j;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("i%3==0", "i", "src/file2.txt", 68, "pkg.Ex2", "m", files_, false, 2, true, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertEq(3,getNumber(logs_.getWatchResults().getWatchedObject()));
    }
    @Test
    public void logs8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file2.txt","public class pkg.Ex2{public static int m(){int j;iter(int i=0;15;1){j++;}return j;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("i%3==0", "i", "src/file2.txt", 68, "pkg.Ex2", "m", files_, false, 2, true, false);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertNull(logs_.getWatchResults().getWatchedObject());
    }
    @Test
    public void logs9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        BreakPointOutputInfo logs_ = conditionalStdViewLogsWacthes("", "{static class Sec{public int v=2;}return new Sec();}", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertNull(logs_.getWatchResults().getWatchedTrace());
        assertEq("pkg.Ex..Sec+1",logs_.getWatchResults().getWatchedObject().getClassName(logs_.getWatchResults().getSubContext()));
        assertEq(2, getNumber(ExecFieldTemplates.getInstanceField(logs_.getWatchResults().getWatchedObject(),logs_.getWatchResults().getSubContext(), StackCall.newInstance(InitPhase.NOTHING,logs_.getWatchResults().getSubContext()),new ClassField("pkg.Ex..Sec+1","v"))));
    }
}
