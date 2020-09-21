package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.StringStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;

public final class ProcessIndexerBisTest extends ProcessMethodCommon {
    @Test
    public void calculate46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  $return e[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $static $int count;\n");
        xml_.append(" $public $static String calls = \"\";\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  calls +=++count+\" get at \"+p+\";\";\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  calls +=++count+\" set at \"+p+\",\"+$value+\";\";\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
        assertEq("1 set at 0,5;2 get at 0;3 set at 0,6;4 get at 0;", ((StringStruct) getStaticField(cont_, new ClassField("pkg.Ex", "calls"))).getInstance());
    }
    @Test
    public void calculate47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  $return e[0]+=10;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $static $int count;\n");
        xml_.append(" $public $static String calls = \"\";\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  calls +=++count+\" get at \"+p+\";\";\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  calls +=++count+\" set at \"+p+\",\"+$value+\";\";\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
        assertEq("1 set at 0,5;2 get at 0;3 set at 0,15;", ((StringStruct) getStaticField(cont_, new ClassField("pkg.Ex", "calls"))).getInstance());
    }
    @Test
    public void calculate101Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[0,1] = 5;\n");
        xml_.append("  return e[0,1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[p[0]+q];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p[0]+q] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate102Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$id(Ex,int...)] = 5;\n");
        xml_.append("  return e[$id(Ex,int...)];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate1022Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$id(Ex,int,int...),9] = 5;\n");
        xml_.append("  return e[$id(Ex,int,int...),9];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0]+q+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value+q+p.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }
    @Test
    public void calculate1023Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$id(Ex,int...),9] = 5;\n");
        xml_.append("  return e[$id(Ex,int...),9];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0]+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value+p[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }
    @Test
    public void calculate1024Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$id(Ex,int...),new int[]{9}] = 5;\n");
        xml_.append("  return e[$id(Ex,int...),new int[]{9}];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0]+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value+p[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }
    @Test
    public void calculate1025Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$id(Ex,int,int...),9,8] = 5;\n");
        xml_.append("  return e[$id(Ex,int,int...),9,8];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0]+q+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value+q+p[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(39, getNumber(ret_));
    }
    @Test
    public void calculate1026Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$id(Ex,int,int...),9,new int[]{8}] = 5;\n");
        xml_.append("  return e[$id(Ex,int,int...),9,new int[]{8}];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0]+q+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int q,int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value+q+p[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(39, getNumber(ret_));
    }
    @Test
    public void calculate103Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[$vararg(int)] = 5;\n");
        xml_.append("  return e[$vararg(int)];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int... p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[0] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new ExSub();\n");
        xml_.append("  e.thisaccess(Ex)[0] = 5;\n");
        xml_.append("  return e.thisaccess(Ex)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:Ex {\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected int this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateFailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new ExSub();\n");
        xml_.append("  e.$thisaccess(Ex)[0] = 5;\n");
        xml_.append("  return e.$thisaccess(Ex)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:Ex {\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected int this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculate1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculateFail1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public Object inst=1?2(3:]4;\n");
        xml_.append(" public Object inst2=0x;\n");
        xml_.append(" public Object inst3=0b1;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculateFail2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public classe pkg.Ex {\n");
        xml_.append(" public Objet inst3=0y;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "fr"));
    }

    @Test
    public void calculateFail3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public classe pkg.Ex {\n");
        xml_.append(" public Objet inst3=0y;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("fr", files_));
    }

    @Test
    public void calculate2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new ExSub();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  return e[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:Ex {\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return super[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  super[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected abstract int this(int p);\n");
        xml_.append(" protected abstract void this(int p);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculate3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  e[m()] = 5;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static void m(){}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return super[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  super[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculate4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  m()[0] = 5;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static void m(){}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return super[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  super[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculate5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new ExSub();\n");
        xml_.append("  e.thisaccess(String)[0] = 5;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:Ex {\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected int this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
    @Test
    public void calculate6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int test(){\n");
        xml_.append("  Ex e = new ExSub();\n");
        xml_.append("  e.superaccess(String)[0] = 5;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:Ex {\n");
        xml_.append(" protected int this(int value)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[value]*2;\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" protected int this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" protected void this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_, "en"));
    }
}
