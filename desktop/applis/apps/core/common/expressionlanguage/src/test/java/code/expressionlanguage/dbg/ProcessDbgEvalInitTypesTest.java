package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalInitTypesTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext f_ = firstAna(files_);
        ResultContextLambda res_ = dynAna("{class Loc{static int i=1;static{i++;}}return Loc.i;}", "pkg.Ex", 55, f_);
        AbstractPageEl next_ = goToBp(f_, "pkg.Ex", "exmeth", null);
        assertEq(2,((NumberStruct)eval(res_,f_, next_)).intStruct());
        assertEq(2,((NumberStruct)eval(res_,f_, next_)).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext f_ = firstAna(files_);
        ResultContextLambda res_ = dynAna("{class Loc{static int i=1;static{i++;Moc.i=Moc.i;}}class Moc{static int i=1;static{i++;}}return Loc.i;}", "pkg.Ex", 55, f_);
        AbstractPageEl next_ = goToBp(f_, "pkg.Ex", "exmeth", null);
        assertEq(2,((NumberStruct)eval(res_,f_, next_)).intStruct());
        assertEq(2,((NumberStruct)eval(res_,f_, next_)).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}public enum pkg.ExEnum{ONE{},TWO{}}");
        ResultContext f_ = firstAna(files_);
        ResultContextLambda res_ = dynAna("{public enum LocEnum{THREE{},FOUR{}}return ExEnum.ONE.ordinal()+LocEnum.FOUR.ordinal();}", "pkg.Ex", 55, f_);
        AbstractPageEl next_ = goToBp(f_, "pkg.Ex", "exmeth", null);
        assertEq(1,((NumberStruct)eval(res_,f_, next_)).intStruct());
        assertEq(1,((NumberStruct)eval(res_,f_, next_)).intStruct());
    }
}
