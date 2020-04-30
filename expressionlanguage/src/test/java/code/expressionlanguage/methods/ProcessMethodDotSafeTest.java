package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodDotSafeTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return c?.content?.length()+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return (c?.content=null)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public Integer content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  Integer e = null;\n");
        xml_.append("  Integer d = null,f=c?.content??e?.intValue();\n");
        xml_.append("  return d??5+f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public Integer content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = new Content();\n");
        xml_.append("  return c?.content?.length()+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = new Content();\n");
        xml_.append("  return c?.content?.length()+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content=\"hello\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(6, ret_.getNumber());
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] c = null;\n");
        xml_.append("  return c?[0]+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[][] c = null;\n");
        xml_.append("  return c?[0]?[0]+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return ((c?.content)=null)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public Integer content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return ((c?.content)+=null)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("1", ret_.getString());
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return ((c?.content)++)+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public Integer content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return (c?.content)+++1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public Integer content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Content c = null;\n");
        xml_.append("  return c?.content+++1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public Integer content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Content c = new Content();\n");
        xml_.append("  return c?.content=\"world\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content=\"hello\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("world", ret_.getString());
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Content c = new Content();\n");
        xml_.append("  c?.content=\"world\";\n");
        xml_.append("  return c.content;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content=\"hello\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("world", ret_.getString());
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Content c = new Content();\n");
        xml_.append("  return c?.content+=\"world\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content=\"hello\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("helloworld", ret_.getString());
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Content c = new Content();\n");
        xml_.append("  c?.content+=\"world\";\n");
        xml_.append("  return c.content;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content=\"hello\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("helloworld", ret_.getString());
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] c = new int[1];\n");
        xml_.append("  return c?[0]+++1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] c = new int[1];\n");
        xml_.append("  return ++c?[0]+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] c = new int[1];\n");
        xml_.append("  c?[0]++;\n");
        xml_.append("  return c[0]+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int[] c = new int[1];\n");
        xml_.append("  ++c?[0];\n");
        xml_.append("  return c[0]+1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Container c = new Container();\n");
        xml_.append("  return c?.container.content=\"hello\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Container{\n");
        xml_.append(" public Content container = new Content();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("hello", ret_.getString());
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Container c = new Container();\n");
        xml_.append("  return c?.container?.content=\"hello\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Container{\n");
        xml_.append(" public Content container = new Content();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("hello", ret_.getString());
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Container c = new Container();\n");
        xml_.append("  c?.container?.content=\"hello\";\n");
        xml_.append("  var out = c?.container.content;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Container{\n");
        xml_.append(" public Content container = new Content();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("hello", ret_.getString());
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String exmeth(){\n");
        xml_.append("  Container c = new Container();\n");
        xml_.append("  c?.container?.content=\"hello\";\n");
        xml_.append("  var out = c?.container?.content;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Container{\n");
        xml_.append(" public Content container = new Content();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content{\n");
        xml_.append(" public String content;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEnElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq("hello", ret_.getString());
    }
}
