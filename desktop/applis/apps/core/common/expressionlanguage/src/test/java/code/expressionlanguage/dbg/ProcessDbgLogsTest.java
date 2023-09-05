package code.expressionlanguage.dbg;

import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgLogsTest extends ProcessDbgCommon {
    @Test
    public void logs1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int m(){return 0;}}");
        CustList<String> logs_ = conditionalStdViewLogs("", "\"log\"", "src/file.txt", 49, "pkg.Ex", "m", files_, false, 0, false, true);
        assertEq(1,logs_.size());
        assertEq("log",logs_.get(0));
    }
    @Test
    public void logs2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int n(){return 0;}}");
        CustList<String> logs_ = conditionalStdViewLogs("", "", "src/file.txt", 49, "pkg.Ex", "n", files_, true, 0, false, true);
        assertEq(1,logs_.size());
        assertEq("src/file.txt:1,50:49\n" +
                "pkg.Ex.static n()",logs_.get(0));
    }
    @Test
    public void logs3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        CustList<String> logs_ = conditionalStdViewLogs("", "1/0", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertEq(1,logs_.size());
        assertEq(":1,2:1\n" +
                "pkg.Ex.static .1()", logs_.get(0));
    }
    @Test
    public void logs4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        CustList<String> logs_ = conditionalStdViewLogs("", "{static class Sec{public String $toString(){return \"log\";}}return new Sec();}", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertEq(1,logs_.size());
        assertEq("log",logs_.get(0));
    }
    @Test
    public void logs5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file.txt","public class pkg.Ex{public static int me(){return 0;}}");
        CustList<String> logs_ = conditionalStdViewLogs("", "{static class Sec{public String $toString(){return \"\"+(1/0);}}return new Sec();}", "src/file.txt", 50, "pkg.Ex", "me", files_, false, 0, false, true);
        assertEq(1,logs_.size());
        assertEq(":1,57:56\n" +
                "pkg.Ex..Sec+1.$toString()", logs_.get(0));
    }
    @Test
    public void logs6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file2.txt","public class pkg.Ex2{public static int m(){int j;iter(int i=0;15;1){j++;}return j;}}");
        CustList<String> logs_ = conditionalStdViewLogs("i%3==0", "i", "src/file2.txt", 68, "pkg.Ex2", "m", files_, false, 2, false, true);
        assertEq(2,logs_.size());
        assertEq("3",logs_.get(0));
        assertEq("9",logs_.get(1));
    }
    @Test
    public void logs7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file2.txt","public class pkg.Ex2{public static int m(){int j;iter(int i=0;15;1){j++;}return j;}}");
        CustList<String> logs_ = conditionalStdViewLogs("i%3==0", "i", "src/file2.txt", 68, "pkg.Ex2", "m", files_, false, 2, true, true);
        assertEq(1,logs_.size());
        assertEq("3",logs_.get(0));
    }
    @Test
    public void logs8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/file2.txt","public class pkg.Ex2{public static int m(){int j;iter(int i=0;15;1){j++;}return j;}}");
        CustList<String> logs_ = conditionalStdViewLogs("i%3==0", "i", "src/file2.txt", 68, "pkg.Ex2", "m", files_, false, 2, true, false);
        assertEq(0,logs_.size());
    }
}
