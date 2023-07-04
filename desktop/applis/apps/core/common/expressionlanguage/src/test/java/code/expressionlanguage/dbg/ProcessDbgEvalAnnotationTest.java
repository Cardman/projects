package code.expressionlanguage.dbg;

import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalAnnotationTest extends ProcessDbgCommon {

    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}public class pkg.Ex {@MyAnnot public static void catching(){class(Ex).getDeclaredMethods()[0].getAnnotations();}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",53,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}@MyAnnot public class pkg.Ex {public static void catching(){class(Ex).getAnnotations();}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",32,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}public class pkg.Ex {public static void catching(){class(Ex).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotations();(switch[int:@MyAnnot:@MyAnnot](0){default;return 0;});}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",176,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}public class pkg.Ex {public static void catching(){class(Ex).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].getAnnotations();(@MyAnnot int a:@MyAnnot int)->{return a;};}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",182,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}operator+ pkg.Ex(@pkg.MyAnnot pkg.Ex a){return a;}public class pkg.Ex {public static void catching(){Class.getOperators()[0].getAnnotationsParameters();}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",49,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}@pkg.MyAnnot operator+ pkg.Ex(pkg.Ex a){return a;}public class pkg.Ex {public static void catching(){Class.getOperators()[0].getAnnotations();}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",32,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {int m()1;}public class pkg.Ex {@MyAnnot public static void catching(){class(Ex).getDeclaredMethods()[0].getAnnotations();}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",38,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public annotation pkg.MyAnnot {}public class pkg.Ex {@MyAnnot public int field;public static void catching(){class(Ex).getDeclaredFields()[0].getAnnotations();}}");
        Struct cont_ = valueDbg("2","pkg.Ex","catching",53,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }
}
