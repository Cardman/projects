package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.ContextFactory;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class CoverageReportTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "src/pkg/CustIter";
    private static final String CUST_LIST_PATH = "src/pkg/CustList";
    private static final String CUST_ITER_TABLE_PATH = "src/pkg/CustIterTable";
    private static final String CUST_PAIR_PATH = "src/pkg/CustPair";
    private static final String CUST_TABLE_PATH = "src/pkg/CustTable";
    @Test
    public void coverage1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int[] t = s == 1 ?{4i}:{6i};\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\">t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int[] t = s == 2 ?{4i}:{6i};\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 2 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\">t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:green;\">for (int j</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">0</span>,<span style=\"background-color:green;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> s </span>==<span style=\"background-color:green;\"> j </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   break;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">for (int j</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">0</span>,<span style=\"background-color:green;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   break;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">for (int j</span>:<span style=\"background-color:green;\">{}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  while (true){\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   return sum;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:green;\">while</span> (<span style=\"background-color:green;\">true</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:green;\">sum</span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getAnnotations():\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($Annotation[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $annotation pkg.MyAnnot {\n" +
                "}\n" +
                "@MyAnnot\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m91\">catching</a>(){\n" +
                "  $Annotation[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">arr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> $class(Ex)</span>.<span style=\"background-color:green;\">getAnnotations()</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">arr;.</span><span style=\"background-color:green;\">length </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> 1i</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">3i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\">arr;.</span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">2i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><span style=\"background-color:green;\">arr;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class(MyAnnot)</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">1i</span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int infoInt()1i:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(2i)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Object arr = $class(MyAnnot).getDeclaredMethods()[0i].getDefaultValue():\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  java.lang.Integer a = $(java.lang.Integer)arr;.:\n");
        xml_.append("  $if (a;. != 1i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $annotation pkg.MyAnnot {\n" +
                " $int infoInt()1i:\n" +
                "}\n" +
                "@MyAnnot(2i)\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m114\">catching</a>(){\n" +
                "  java.lang.Object <span style=\"background-color:green;\"><span style=\"background-color:green;\">arr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> $class(MyAnnot)</span>.<span style=\"background-color:green;\">getDeclaredMethods()</span></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0i</span>]</span></span>.<span style=\"background-color:green;\">getDefaultValue()</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\">arr;.</span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class(java.lang.Integer)</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">2i</span>:\n" +
                "  }\n" +
                "  java.lang.Integer <span style=\"background-color:green;\"><span style=\"background-color:green;\">a </span>=<span style=\"background-color:green;\"> $(java.lang.Integer)<span style=\"background-color:green;\">arr;.</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">a;. </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> 1i</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">1i</span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int st = 0:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <span style=\"background-color:green;\"><span style=\"background-color:green;\">st </span>=<span style=\"background-color:green;\"> 0</span></span>:\n" +
                " $public $static $int <a name=\"m76\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $if(t;.<0){\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"false\">&lt;</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "   $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "  }\n" +
                "  $else{\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">t;.</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $if(t;.>=0){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\">t;.</span>:\n" +
                "  }\n" +
                "  $else{\n" +
                "   $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">10</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\">t;.</span>){\n" +
                "   $case(<span style=\"background-color:green;\">10</span>):\n" +
                "   $case(<span style=\"background-color:red;\">8</span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\">t;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:green;\">8</span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">9</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\">t;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:red;\">8</span>){\n" +
                "    <span style=\"background-color:red;\"><span style=\"background-color:red;\">t;.</span>=<span style=\"background-color:red;\">16</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">9</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\">t;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:red;\">8</span>){\n" +
                "    <span style=\"background-color:red;\"><span style=\"background-color:red;\">t;.</span>=<span style=\"background-color:red;\">16</span></span>:\n" +
                "   }\n" +
                "   <span style=\"background-color:green;\">$default</span>{\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\">t;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:green;\">8</span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">16</span></span>:\n" +
                "   }\n" +
                "   <span style=\"background-color:red;\">$default</span>{\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\">t;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return call():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.$static call()\" href=\"pkg/Ex.html#m99\">call</a>()</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m99\">call</a>(){\n" +
                "  $return <span style=\"background-color:green;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">1i</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m95\">call</a>(){\n" +
                "  $return <span style=\"background-color:red;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field+q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$operator- pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field-q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $return (one;.+two;.).field:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex (pkg.Ex p, pkg.Ex q){\n" +
                " pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">out </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">out;.</span><span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> p;.;</span><span style=\"background-color:green;\">field</span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\">q;.;</span><span style=\"background-color:green;\">field</span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\">out;.</span>:\n" +
                "}\n" +
                "$operator<a name=\"m131\">-</a> pkg.Ex (pkg.Ex p, pkg.Ex q){\n" +
                " pkg.Ex <span style=\"background-color:red;\"><span style=\"background-color:red;\">out </span>=<span style=\"background-color:red;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">out;.</span><span style=\"background-color:red;\">field </span></span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> p;.;</span><span style=\"background-color:red;\">field</span></span>-<span style=\"background-color:red;\"><span style=\"background-color:red;\">q;.;</span><span style=\"background-color:red;\">field</span></span></span></span>:\n" +
                " $return <span style=\"background-color:red;\">out;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $int <span style=\"background-color:green;\">field</span>:\n" +
                " $public $static $int <a name=\"m311\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">one </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">two </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">one;.</span><span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">two;.</span><span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\">one;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"pkg/Ex.html#m9\">+</a><span style=\"background-color:green;\">two;.</span></span>)</span>.<span style=\"background-color:green;\">field</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m473\">call</a>(){\n" +
                "  $return <span style=\"background-color:red;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $return 1i/0i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:green;\">1i</span>/<span style=\"background-color:green;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>(Object o){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\">t;.</span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=-1i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $return 1i/0i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">-<span style=\"background-color:red;\">1i</span></span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"false\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:green;\">2</span>:\n" +
                "  } <span style=\"background-color:red;\">$catch</span>(Object o){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">t;.</span>=<span style=\"background-color:red;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:red;\">t;.</span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE;\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(){\n");
        xml_.append("  field=5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " <a title=\"pkg.ExEnum.pkg.ExEnum()\" href=\"pkg/Ex.html#m51\">ONE</a>;\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m51\">ExEnum(</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field</span>=<span style=\"background-color:green;\">5</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m118\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE(5);\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " <a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"pkg/Ex.html#m54\">ONE</a>(<span style=\"background-color:green;\">5</span>);\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m54\">ExEnum(</a>int param){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field</span>=<span style=\"background-color:green;\">param</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m134\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" FOUR(5);\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " <a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"pkg/Ex.html#m55\">FOUR</a>(<span style=\"background-color:green;\">5</span>);\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m55\">ExEnum(</a>int param){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field</span>=<span style=\"background-color:green;\">param</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m135\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " ONE;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m74\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" FOUR(5){\n");
        xml_.append("  (int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " <a title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR(int)\" href=\"pkg/Ex.html#m37\">FOUR</a>(<span style=\"background-color:green;\">5</span>){\n" +
                "  <a name=\"m37\">(</a>int p){\n" +
                "   <span style=\"background-color:green;\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"pkg/Ex.html#m87\">super</a>(<span style=\"background-color:green;\">p</span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m87\">ExEnum(</a>int param){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field</span>=<span style=\"background-color:green;\">param</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m167\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" FOUR{\n");
        xml_.append("  (){\n");
        xml_.append("   this(5);\n");
        xml_.append("  }\n");
        xml_.append("  (int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " <a title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR()\" href=\"pkg/Ex.html#m34\">FOUR</a>{\n" +
                "  <a name=\"m34\">(</a>){\n" +
                "   <span style=\"background-color:green;\"><a title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR(int)\" href=\"pkg/Ex.html#m56\">this</a>(<span style=\"background-color:green;\">5</span>)</span>;\n" +
                "  }\n" +
                "  <a name=\"m56\">(</a>int p){\n" +
                "   <span style=\"background-color:green;\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"pkg/Ex.html#m106\">super</a>(<span style=\"background-color:green;\">p</span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m106\">ExEnum(</a>int param){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field</span>=<span style=\"background-color:green;\">param</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m186\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" FOUR{\n");
        xml_.append("  public int field;\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public enum pkg.ExEnum {\n" +
                " FOUR{\n" +
                "  public int <span style=\"background-color:green;\">field</span>;\n" +
                " };\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m99\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int j</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex f,pkg.Ex s) {\n");
        xml_.append(" return new pkg.Ex(f.field + s.field);\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex f) {\n");
        xml_.append(" return new pkg.Ex(f.field);\n");
        xml_.append("}\n");
        xml_.append("operator++ pkg.Ex(pkg.Ex f) {\n");
        xml_.append(" return new pkg.Ex(f.field+1);\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Ex(int field){\n");
        xml_.append("  this.field = field;\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(){\n");
        xml_.append("  this(0);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex one = new Ex(5);\n");
        xml_.append("  Ex two = new Ex(3);\n");
        xml_.append("  var three = one + two;\n");
        xml_.append("  var four = + two;\n");
        xml_.append("  var five = three += four;\n");
        xml_.append("  var six = new Ex(6);\n");
        xml_.append("  var seven = !(six != six);\n");
        xml_.append("  if (seven){\n");
        xml_.append("   seven = !seven;\n");
        xml_.append("  } else if (seven){\n");
        xml_.append("   seven = !seven;\n");
        xml_.append("  }\n");
        xml_.append("  six[0] += 8;\n");
        xml_.append("  six[1] = 8;\n");
        xml_.append("  Ex eight = new Ex(8);\n");
        xml_.append("  eight++;\n");
        xml_.append("  ++eight;\n");
        xml_.append("  six[2]++;\n");
        xml_.append("  ++six[3];\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr += new ExTwo();\n");
        xml_.append("  int[] t = s == 1 ?{4i}:{6i};\n");
        xml_.append("  return t[0]+caller();\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int p){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int p){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append(" public static int caller(){\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>operator<a name=\"m8\">+</a> pkg.Ex(pkg.Ex f,pkg.Ex s) {\n" +
                " return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">f</span>.<span style=\"background-color:green;\">field </span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"> s</span>.<span style=\"background-color:green;\">field</span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> pkg.Ex(pkg.Ex f) {\n" +
                " return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:green;\"><span style=\"background-color:green;\">f</span>.<span style=\"background-color:green;\">field</span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> pkg.Ex(pkg.Ex f) {\n" +
                " return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">f</span>.<span style=\"background-color:green;\">field</span></span>+<span style=\"background-color:green;\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m244\">public Ex(</a>int field){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">this</span>.<span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> field</span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">this</a>(<span style=\"background-color:red;\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">one </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> Ex(<span style=\"background-color:green;\">5</span>)</span></span>;\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">two </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> Ex(<span style=\"background-color:green;\">3</span>)</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">three </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> one </span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"pkg/Ex.html#m8\">+</a><span style=\"background-color:green;\"> two</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">four </span>=<span style=\"background-color:green;\"> <a title=\"static +(pkg.Ex)\" href=\"pkg/Ex.html#m87\">+</a><span style=\"background-color:green;\"> two</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">five </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> three </span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"pkg/Ex.html#m8\">+</a>=<span style=\"background-color:green;\"> four</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">six </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> Ex(<span style=\"background-color:green;\">6</span>)</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:yellow;\"> <a title=\"true\">!</a><span style=\"background-color:yellow;\">(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">six </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> six</span></span>)</span></span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\">seven</span>){\n" +
                "   <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">seven</span></span></span>;\n" +
                "  } <span style=\"background-color:red;\">else if</span> (<span style=\"background-color:red;\">seven</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">seven </span>=<span style=\"background-color:red;\"> !<span style=\"background-color:red;\">seven</span></span></span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m818\">[</a><span style=\"background-color:green;\">0</span><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m818\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m863\">=</a><span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m863\">[</a><span style=\"background-color:green;\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m863\">]</a> </span></span>=<span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">eight </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> Ex(<span style=\"background-color:green;\">8</span>)</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">eight</span><a title=\"static ++(pkg.Ex)\" href=\"pkg/Ex.html#m147\">+</a>+</span>;\n" +
                "  <span style=\"background-color:green;\"><a title=\"static ++(pkg.Ex)\" href=\"pkg/Ex.html#m147\">+</a>+<span style=\"background-color:green;\">eight</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m818\">[</a><span style=\"background-color:green;\">2</span><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m818\">]</a></span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m863\">+</a></span>;\n" +
                "  <span style=\"background-color:green;\">+<a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m863\">+</a><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m818\">[</a><span style=\"background-color:green;\">3</span><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m818\">]</a></span></span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span><i>+</i>=<span style=\"background-color:green;\"> new ExTwo()</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>+<span style=\"background-color:green;\"><a title=\"pkg.Ex.static caller()\" href=\"pkg/Ex.html#m915\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m818\">th</a>is(int p){\n" +
                "  return <span style=\"background-color:green;\">field</span>;\n" +
                " }\n" +
                " public void <a name=\"m863\">thi</a>s(int p){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field </span>=<span style=\"background-color:green;\"> value</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m915\">caller</a>(){\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                " public String <a name=\"m982\">$toString</a>(){\n" +
                "  return <span style=\"background-color:green;\">null</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex f,pkg.Ex s) {\n");
        xml_.append(" return new pkg.Ex(f.field + s.field);\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex f) {\n");
        xml_.append(" return new pkg.Ex(f.field);\n");
        xml_.append("}\n");
        xml_.append("operator++ pkg.Ex(pkg.Ex f) {\n");
        xml_.append(" return new pkg.Ex(f.field+1);\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Ex(int field){\n");
        xml_.append("  this.field = field;\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(){\n");
        xml_.append("  this(0);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var one = true;\n");
        xml_.append("  var two = true;\n");
        xml_.append("  var three = one && two;\n");
        xml_.append("  var four = !one && two;\n");
        xml_.append("  var five = !one && !two;\n");
        xml_.append("  var eight = one && !two;\n");
        xml_.append("  var six = new Ex(6);\n");
        xml_.append("  var seven = !(six != six);\n");
        xml_.append("  for (int i = 0; i < 2; i++){\n");
        xml_.append("   if (!(i % 2 == 0)){\n");
        xml_.append("    seven = true;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  six[0] += 8;\n");
        xml_.append("  six[1] = 8;\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr += new ExTwo();\n");
        xml_.append("  int[] t = s == 1 ?{4i}:{6i};\n");
        xml_.append("  s+=1;\n");
        xml_.append("  return t[0]+caller();\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int p){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int p){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append(" public static int caller(){\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>operator<a name=\"m8\">+</a> pkg.Ex(pkg.Ex f,pkg.Ex s) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">f</span>.<span style=\"background-color:red;\">field </span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\"> s</span>.<span style=\"background-color:red;\">field</span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> pkg.Ex(pkg.Ex f) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\">f</span>.<span style=\"background-color:red;\">field</span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> pkg.Ex(pkg.Ex f) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">f</span>.<span style=\"background-color:red;\">field</span></span>+<span style=\"background-color:red;\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m244\">public Ex(</a>int field){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">this</span>.<span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> field</span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">this</a>(<span style=\"background-color:red;\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">one </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">two </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">three </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> one </span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span style=\"background-color:yellow;\"> two</span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">four </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">one </span></span><a title=\"false\">&amp;</a>&amp;<span style=\"background-color:red;\"> two</span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">five </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">one </span></span><a title=\"false\">&amp;</a>&amp;<span style=\"background-color:red;\"> !<span style=\"background-color:red;\">two</span></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">eight </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> one </span><a title=\"true\">&amp;</a><a title=\"false\">&amp;</a><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">two</span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">six </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> Ex(<span style=\"background-color:green;\">6</span>)</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:yellow;\"> <a title=\"true\">!</a><span style=\"background-color:yellow;\">(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">six </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> six</span></span>)</span></span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>&lt;<span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\">!<span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>)</span></span>){\n" +
                "    <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m785\">[</a><span style=\"background-color:green;\">0</span><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m785\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m830\">=</a><span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m830\">[</a><span style=\"background-color:green;\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m830\">]</a> </span></span>=<span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>+=<span style=\"background-color:green;\"> new ExTwo()</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">s</span>+=<span style=\"background-color:green;\">1</span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>+<span style=\"background-color:green;\"><a title=\"pkg.Ex.static caller()\" href=\"pkg/Ex.html#m882\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m785\">th</a>is(int p){\n" +
                "  return <span style=\"background-color:green;\">field</span>;\n" +
                " }\n" +
                " public void <a name=\"m830\">thi</a>s(int p){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field </span>=<span style=\"background-color:green;\"> value</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m882\">caller</a>(){\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex f,pkg.Ex s) {\n");
        xml_.append(" return new pkg.Ex(f.field + s.field);\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Ex(pkg.Ex f) {\n");
        xml_.append(" return new pkg.Ex(f.field);\n");
        xml_.append("}\n");
        xml_.append("operator++ pkg.Ex(pkg.Ex f) {\n");
        xml_.append(" return new pkg.Ex(f.field+1);\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Ex(int field){\n");
        xml_.append("  this.field = field;\n");
        xml_.append(" }\n");
        xml_.append(" public Ex(){\n");
        xml_.append("  this(0);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var one = true;\n");
        xml_.append("  var two = true;\n");
        xml_.append("  var three = one || two;\n");
        xml_.append("  var four = !one || two;\n");
        xml_.append("  var five = !one || !two;\n");
        xml_.append("  var eight = one || !two;\n");
        xml_.append("  var six = new Ex(6);\n");
        xml_.append("  var seven = !(six != six);\n");
        xml_.append("  for (int i = 0; i < 4; i++){\n");
        xml_.append("   if (i % 2 == 0 && i > 1){\n");
        xml_.append("    seven = true;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  six[0] += 8;\n");
        xml_.append("  six[1] = 8;\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr += new ExTwo();\n");
        xml_.append("  int[] t = s == 1 ?{4i}:{6i};\n");
        xml_.append("  s+=1;\n");
        xml_.append("  return t[0]+caller();\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int p){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int p){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append(" public static int caller(){\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>operator<a name=\"m8\">+</a> pkg.Ex(pkg.Ex f,pkg.Ex s) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">f</span>.<span style=\"background-color:red;\">field </span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\"> s</span>.<span style=\"background-color:red;\">field</span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> pkg.Ex(pkg.Ex f) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\">f</span>.<span style=\"background-color:red;\">field</span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> pkg.Ex(pkg.Ex f) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">f</span>.<span style=\"background-color:red;\">field</span></span>+<span style=\"background-color:red;\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public int <span style=\"background-color:green;\">field</span>;\n" +
                " <a name=\"m244\">public Ex(</a>int field){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">this</span>.<span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> field</span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">this</a>(<span style=\"background-color:red;\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">one </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">two </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">three </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> one </span><a title=\"true\">|</a>|<span style=\"background-color:red;\"> two</span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">four </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">one </span></span><a title=\"false\">|</a><a title=\"true\">|</a><span style=\"background-color:yellow;\"> two</span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">five </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">one </span></span><a title=\"false\">|</a><a title=\"false\">|</a><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\">two</span></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">eight </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> one </span><a title=\"true\">|</a>|<span style=\"background-color:red;\"> !<span style=\"background-color:red;\">two</span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">six </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"pkg/Ex.html#m244\">new</a> Ex(<span style=\"background-color:green;\">6</span>)</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:yellow;\"> <a title=\"true\">!</a><span style=\"background-color:yellow;\">(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">six </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> six</span></span>)</span></span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>&lt;<span style=\"background-color:green;\"> 4</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0 </span></span>&amp;&amp;<span style=\"background-color:green;\"><span style=\"background-color:green;\"> i </span>&gt;<span style=\"background-color:green;\"> 1</span></span></span>){\n" +
                "    <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m791\">[</a><span style=\"background-color:green;\">0</span><a title=\"pkg.Ex.[](int)\" href=\"pkg/Ex.html#m791\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m836\">=</a><span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">six</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m836\">[</a><span style=\"background-color:green;\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"pkg/Ex.html#m836\">]</a> </span></span>=<span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>+=<span style=\"background-color:green;\"> new ExTwo()</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">s</span>+=<span style=\"background-color:green;\">1</span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>+<span style=\"background-color:green;\"><a title=\"pkg.Ex.static caller()\" href=\"pkg/Ex.html#m888\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m791\">th</a>is(int p){\n" +
                "  return <span style=\"background-color:green;\">field</span>;\n" +
                " }\n" +
                " public void <a name=\"m836\">thi</a>s(int p){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">field </span>=<span style=\"background-color:green;\"> value</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m888\">caller</a>(){\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var s = \"\"<\"\";\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr += 1;\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"><span style=\"background-color:red;\"> \"\"</span>&lt;<span style=\"background-color:red;\">\"\"</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>+=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var s = \"\"<\"\";\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  Object right = 1;\n");
        xml_.append("  toStr += right;\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"><span style=\"background-color:red;\"> \"\"</span>&lt;<span style=\"background-color:red;\">\"\"</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  Object <span style=\"background-color:green;\"><span style=\"background-color:green;\">right </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span><i>+</i>=<span style=\"background-color:green;\"> right</span></span>;\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var s = \"\"<\"\";\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  Object[] right = null;\n");
        xml_.append("  toStr += right;\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"><span style=\"background-color:red;\"> \"\"</span>&lt;<span style=\"background-color:red;\">\"\"</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  Object[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">right </span>=<span style=\"background-color:green;\"> null</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>+=<span style=\"background-color:green;\"> right</span></span>;\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annotation {}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var s = \"\"<\"\";\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  Annotation right = null;\n");
        xml_.append("  toStr += right;\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public annotation pkg.Annotation {}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m77\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"><span style=\"background-color:red;\"> \"\"</span>&lt;<span style=\"background-color:red;\">\"\"</span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  Annotation <span style=\"background-color:green;\"><span style=\"background-color:green;\">right </span>=<span style=\"background-color:green;\"> null</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>+=<span style=\"background-color:green;\"> right</span></span>;\n" +
                "  return <span style=\"background-color:green;\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var one = true;\n");
        xml_.append("  if (one){\n");
        xml_.append("   return 5;\n");
        xml_.append("  }\n");
        xml_.append("  var two = true;\n");
        xml_.append("  var three = one && two;\n");
        xml_.append("  return 6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">one </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\">one</span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\">two </span>=<span style=\"background-color:red;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\">three </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> one </span>&amp;&amp;<span style=\"background-color:red;\"> two</span></span></span>;\n" +
                "  return <span style=\"background-color:red;\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"pkg/CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\">3i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\">1i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\">2i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\">$foreach(java.lang.Number e</span>:<span style=\"background-color:green;\">inst;;;</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">res;;;</span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\">e;</span><span style=\"background-color:green;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m302\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("   $break:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"pkg/CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\">3i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\">1i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\">2i</span>)</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$foreach(java.lang.Number e</span>:<span style=\"background-color:green;\">inst;;;</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">res;;;</span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\">e;</span><span style=\"background-color:green;\">intValue()</span></span></span>:\n" +
                "   $break:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m313\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"pkg/CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$foreach(java.lang.Number e</span>:<span style=\"background-color:green;\">inst;;;</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">res;;;</span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\">e;</span><span style=\"background-color:red;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m248\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  $if (res == 0) {\n");
        xml_.append("   $return:\n");
        xml_.append("  }\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"pkg/CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">res </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>) {\n" +
                "   $return:\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">$foreach(java.lang.Number e</span>:<span style=\"background-color:red;\">inst;;;</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">res;;;</span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\">e;</span><span style=\"background-color:red;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m283\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"pkg/CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"pkg/CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">3</span>,<span style=\"background-color:green;\">5</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"pkg/CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">8</span>,<span style=\"background-color:green;\">1</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"pkg/CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">2</span>,<span style=\"background-color:green;\">6</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\">$for(Number f, Number s</span>: <span style=\"background-color:green;\">inst</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">res </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> f;</span><span style=\"background-color:green;\">intValue()</span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\">s;</span><span style=\"background-color:green;\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m293\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("   $break:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"pkg/CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"pkg/CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">3</span>,<span style=\"background-color:green;\">5</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"pkg/CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">8</span>,<span style=\"background-color:green;\">1</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"pkg/CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">2</span>,<span style=\"background-color:green;\">6</span>)</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$for(Number f, Number s</span>: <span style=\"background-color:green;\">inst</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">res </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> f;</span><span style=\"background-color:green;\">intValue()</span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\">s;</span><span style=\"background-color:green;\">intValue()</span></span></span></span>:\n" +
                "   $break:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m304\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"pkg/CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$for(Number f, Number s</span>: <span style=\"background-color:green;\">inst</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">res </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> f;</span><span style=\"background-color:red;\">intValue()</span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\">s;</span><span style=\"background-color:red;\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m242\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  $if (res == 0) {\n");
        xml_.append("   $return:\n");
        xml_.append("  }\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">inst</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"pkg/CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\">res</span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">res </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>) {\n" +
                "   $return:\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">$for(Number f, Number s</span>: <span style=\"background-color:red;\">inst</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">res </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> f;</span><span style=\"background-color:red;\">intValue()</span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\">s;</span><span style=\"background-color:red;\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m277\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">res</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  for (int i = 0; i < 2; i++){\n");
        xml_.append("   if (!(i % 2 == 0)){\n");
        xml_.append("    break;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr = toStr + new ExTwo();\n");
        xml_.append("  toStr = new ExTwo() + toStr;\n");
        xml_.append("  return 6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public String $toString() {\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <span style=\"background-color:yellow;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">i </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\">!<span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> toStr </span><i>+</i><span style=\"background-color:green;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> new ExTwo() </span><i>+</i><span style=\"background-color:green;\"> toStr</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                " public String <a name=\"m275\">$toString</a>() {\n" +
                "  return <span style=\"background-color:green;\">null</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  if (s == 1){\n");
        xml_.append("   return 7;\n");
        xml_.append("  }\n");
        xml_.append("  for (int i = 0; i < 2; i++){\n");
        xml_.append("   if (!(i % 2 == 0)){\n");
        xml_.append("    break;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr = toStr + new ExTwo();\n");
        xml_.append("  toStr = new ExTwo() + toStr;\n");
        xml_.append("  return 6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1</span></span>){\n" +
                "   return <span style=\"background-color:green;\">7</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for</span> (int <span style=\"background-color:red;\"><span style=\"background-color:red;\">i </span>=<span style=\"background-color:red;\"> 0</span></span>; <span style=\"background-color:red;\"><span style=\"background-color:red;\">i </span>&lt;<span style=\"background-color:red;\"> 2</span></span>; <span style=\"background-color:red;\"><span style=\"background-color:red;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:red;\">if</span> (<span style=\"background-color:red;\">!<span style=\"background-color:red;\">(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">i </span>%<span style=\"background-color:red;\"> 2 </span></span>==<span style=\"background-color:red;\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\">toStr </span>=<span style=\"background-color:red;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">toStr </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> toStr </span>+<span style=\"background-color:red;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">toStr </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> new ExTwo() </span>+<span style=\"background-color:red;\"> toStr</span></span></span>;\n" +
                "  return <span style=\"background-color:red;\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  if (s == 1){\n");
        xml_.append("   return 7;\n");
        xml_.append("  }\n");
        xml_.append("  for (;;){\n");
        xml_.append("    break;\n");
        xml_.append("  }\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr = toStr + new ExTwo();\n");
        xml_.append("  toStr = new ExTwo() + toStr;\n");
        xml_.append("  return 6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1</span></span>){\n" +
                "   return <span style=\"background-color:green;\">7</span>;\n" +
                "  }\n" +
                "  for (;;){\n" +
                "    break;\n" +
                "  }\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\">toStr </span>=<span style=\"background-color:red;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">toStr </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> toStr </span>+<span style=\"background-color:red;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">toStr </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> new ExTwo() </span>+<span style=\"background-color:red;\"> toStr</span></span></span>;\n" +
                "  return <span style=\"background-color:red;\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getAnnotations():\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $else $if ($static($Class).getClass(arr;.) != $class($Annotation[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $else $if ($static($Class).getClass(arr;.[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $annotation pkg.MyAnnot {\n" +
                "}\n" +
                "@MyAnnot\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m91\">catching</a>(){\n" +
                "  $Annotation[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">arr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> $class(Ex)</span>.<span style=\"background-color:green;\">getAnnotations()</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">arr;.</span><span style=\"background-color:green;\">length </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> 1i</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">3i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$else $if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\">arr;.</span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">2i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$else $if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><span style=\"background-color:green;\">arr;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class(MyAnnot)</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">1i</span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var seven = false;\n");
        xml_.append("  for (int i = 0; i < 4; i++){\n");
        xml_.append("   if (i % 2 == 0){\n");
        xml_.append("    seven = true;\n");
        xml_.append("   }\n");
        xml_.append("   else if (i > 1){\n");
        xml_.append("    seven = true;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  return 8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> false</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>&lt;<span style=\"background-color:green;\"> 4</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>){\n" +
                "    <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "   <span style=\"background-color:green;\">else if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>&gt;<span style=\"background-color:green;\"> 1</span></span>){\n" +
                "    <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  return <span style=\"background-color:green;\">8</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var seven = false;\n");
        xml_.append("  for (int i = 0; i < 4; i++){\n");
        xml_.append("   if (i < 4){\n");
        xml_.append("    seven = true;\n");
        xml_.append("   }\n");
        xml_.append("   else if (i > 1){\n");
        xml_.append("    seven = true;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  return 8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> false</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>&lt;<span style=\"background-color:green;\"> 4</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">i </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 4</span></span>){\n" +
                "    <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">seven </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "   <span style=\"background-color:red;\">else if</span> (<span style=\"background-color:red;\"><span style=\"background-color:red;\">i </span>&gt;<span style=\"background-color:red;\"> 1</span></span>){\n" +
                "    <span style=\"background-color:red;\"><span style=\"background-color:red;\">seven </span>=<span style=\"background-color:red;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  return <span style=\"background-color:green;\">8</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  for (int i = 0; i < 2; i++){\n");
        xml_.append("   if (!(i % 2 == 0)){\n");
        xml_.append("    break;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  var toStr = \"\";\n");
        xml_.append("  toStr = toStr + new ExTwo();\n");
        xml_.append("  toStr = new ExTwo() + toStr;\n");
        xml_.append("  return 6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <span style=\"background-color:yellow;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">i </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\">i</span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\">!<span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">i </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"> \"\"</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> toStr </span>+<span style=\"background-color:green;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">toStr </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> new ExTwo() </span>+<span style=\"background-color:green;\"> toStr</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.ExTwo {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $new Ex():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " <a name=\"m25\">$public Ex(</a>){\n" +
                " }\n" +
                " $public $static Object <a name=\"m66\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"pkg/Ex.html#m25\">$new</a> Ex()</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static StringBuilder exmeth(){\n");
        xml_.append("  $return $new StringBuilder(\"hello\"):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static StringBuilder <a name=\"m55\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">$new StringBuilder(<span style=\"background-color:green;\">\"hello\"</span>)</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  while (s == 1){\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   return sum;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">while</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">s </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1</span></span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:green;\">sum</span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return sum;\n");
        xml_.append("  }\n");
        xml_.append("  while (s == 1){\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   return sum;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">sum</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">while</span> (<span style=\"background-color:red;\"><span style=\"background-color:red;\">s </span>==<span style=\"background-color:red;\"> 1</span></span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:red;\">sum</span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $return 1i/0i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  } $finally {\n");
        xml_.append("   $return 3:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:green;\">1i</span>/<span style=\"background-color:green;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>(Object o){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\">t;.</span>:\n" +
                "  } $finally {\n" +
                "   $return <span style=\"background-color:green;\">3</span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=-1i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $return 1i/0i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  } $finally {\n");
        xml_.append("   $return 3:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">-<span style=\"background-color:red;\">1i</span></span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"false\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:green;\">2</span>:\n" +
                "  } <span style=\"background-color:red;\">$catch</span>(Object o){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">t;.</span>=<span style=\"background-color:red;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:red;\">t;.</span>:\n" +
                "  } $finally {\n" +
                "   $return <span style=\"background-color:green;\">3</span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $throw 1i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span style=\"background-color:green;\">t</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">t;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $throw <span style=\"background-color:green;\">1i</span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>(Object o){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">t;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\">t;.</span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  do{\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   return sum;\n");
        xml_.append("  }while (true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:green;\">sum</span>;\n" +
                "  }<span style=\"background-color:red;\">while</span> (<span style=\"background-color:red;\">true</span>);\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  do{\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }while (sum < 7);\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "  }<span style=\"background-color:green;\">while</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>&lt;<span style=\"background-color:green;\"> 7</span></span>);\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  do{\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   if (sum >= 7){\n");
        xml_.append("    break;\n");
        xml_.append("   }\n");
        xml_.append("  }while (sum < 17);\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>&gt;=<span style=\"background-color:green;\"> 7</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }<span style=\"background-color:yellow;\">while</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 17</span></span>);\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  do{\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   if (sum >= 7){\n");
        xml_.append("    break;\n");
        xml_.append("   }\n");
        xml_.append("   continue;\n");
        xml_.append("  }while (sum < 17);\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>&gt;=<span style=\"background-color:green;\"> 7</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "   continue;\n" +
                "  }<span style=\"background-color:yellow;\">while</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 17</span></span>);\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $new Ex():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageOtherIni();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " <a name=\"m25\">$public Ex(</a>){\n" +
                " }\n" +
                " $public $static Object <a name=\"m66\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"pkg/Ex.html#m25\">$new</a> Ex()</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $new Ex(0):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageOtherIni();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " <a name=\"m25\">$public Ex(</a>$int p){\n" +
                " }\n" +
                " $public $static Object <a name=\"m72\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex($int)\" href=\"pkg/Ex.html#m25\">$new</a> Ex(<span style=\"background-color:green;\">0</span>)</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.CustIter&lt;#T&gt; :$iterator&lt;#T&gt;{\n" +
                " $private pkg.CustList&lt;#T&gt; <span style=\"background-color:green;\">list</span>:\n" +
                " $private $int <span style=\"background-color:green;\">length</span>:\n" +
                " $private $int <span style=\"background-color:green;\">index</span>:\n" +
                " <a name=\"m127\">$public (</a>pkg.CustList&lt;#T&gt; i){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span>=<span style=\"background-color:green;\">i;.;</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">length;;;</span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.size()\" href=\"pkg/CustList.html#m519\">size</a>()</span></span></span>:\n" +
                " }\n" +
                " $public $normal #T <a name=\"m223\">next</a>(){\n" +
                "  #T <span style=\"background-color:green;\"><span style=\"background-color:green;\">out</span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.get($int)\" href=\"pkg/CustList.html#m571\">get</a>(<span style=\"background-color:green;\">index;;;</span>)</span></span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">index;;;</span>++</span>:\n" +
                "  $return <span style=\"background-color:green;\">out;.</span>:\n" +
                " }\n" +
                " $public $normal $boolean <a name=\"m322\">hasNext</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">index;;;</span>&lt;<span style=\"background-color:green;\">length;;;</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.getValue(1));
    }
    @Test
    public void coverage69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.CustList&lt;#U&gt; :$iterable&lt;#U&gt;{\n" +
                " $private #U[] <span style=\"background-color:green;\">list</span>:\n" +
                " $private $int <span style=\"background-color:green;\">length</span>:\n" +
                " <a name=\"m93\">$public (</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span>=<span style=\"background-color:green;\">$new #U[<span style=\"background-color:green;\">0i</span>]</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m154\">add</a>(#U elt){\n" +
                "  <span style=\"background-color:green;\"><a title=\"pkg.CustList.add($int,#U)\" href=\"pkg/CustList.html#m218\">add</a>(<span style=\"background-color:green;\">length;;;</span>,<span style=\"background-color:green;\">elt;.;</span>)</span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m218\">add</a>($int index,#U elt){\n" +
                "  #U[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">newlist</span>=<span style=\"background-color:green;\">$new #U[<span style=\"background-color:green;\"><span style=\"background-color:green;\">length;;;</span>+<span style=\"background-color:green;\">1i</span></span>]</span></span>:\n" +
                "  <span style=\"background-color:green;\">$iter</span>($int i=<span style=\"background-color:green;\">0i</span>:<span style=\"background-color:green;\">index;.;</span>:<span style=\"background-color:green;\">1i</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">newlist;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">i;</span>]</span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">i;</span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">newlist;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">index;.;</span>]</span></span>=<span style=\"background-color:green;\">elt;.;</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$iter</span>($int i=<span style=\"background-color:green;\"><span style=\"background-color:green;\">index;.;</span>+<span style=\"background-color:green;\">1i</span></span>:<span style=\"background-color:green;\"><span style=\"background-color:green;\">length;;;</span>+<span style=\"background-color:green;\">1i</span></span>:<span style=\"background-color:green;\">1i</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">newlist;.</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">i;</span>]</span></span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\">list;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><span style=\"background-color:red;\">i;</span>-<span style=\"background-color:red;\">1i</span></span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">length;;;</span>++</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span>=<span style=\"background-color:green;\">newlist;.</span></span>:\n" +
                " }\n" +
                " $public $normal $int <a name=\"m519\">size</a>(){\n" +
                "  $return <span style=\"background-color:green;\">length;;;</span>:\n" +
                " }\n" +
                " $public $normal #U <a name=\"m571\">get</a>($int index){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">index;.;</span>]</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m643\">set</a>($int index,#U elt){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">list;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">index;.;</span>]</span></span>=<span style=\"background-color:red;\">elt;.;</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m721\">remove</a>($int index){\n" +
                "  <span style=\"background-color:red;\">$iter</span>($int i=<span style=\"background-color:red;\">index;.;</span>:<span style=\"background-color:red;\"><span style=\"background-color:red;\">length;;;</span>-<span style=\"background-color:red;\">1i</span></span>:<span style=\"background-color:red;\">1i</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">list;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">i;</span>]</span></span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\">list;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><span style=\"background-color:red;\">i;</span>+<span style=\"background-color:red;\">1i</span></span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\">list;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><span style=\"background-color:red;\">length;;;</span>-<span style=\"background-color:red;\">1i</span></span>]</span></span>=<span style=\"background-color:red;\">$null</span></span>:\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">length;;;</span>--</span>:\n" +
                " }\n" +
                " $public $normal $iterator&lt;#U&gt; <a name=\"m898\">iterator</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustIter.pkg.CustIter(pkg.CustList&lt;#T&gt;)\" href=\"pkg/CustIter.html#m127\">$new</a> pkg.CustIter&lt;#U&gt;(<span style=\"background-color:green;\">$this</span>)</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.getValue(2));
    }
    @Test
    public void coverage70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.CustIterTable&lt;U,V&gt; :$iteratorTable&lt;U,V&gt;{\n" +
                " $private CustTable&lt;U,V&gt; <span style=\"background-color:green;\">list</span>:\n" +
                " $private $int <span style=\"background-color:green;\">length</span>:\n" +
                " $private $int <span style=\"background-color:green;\">index</span>:\n" +
                " <a name=\"m137\">$public CustIterTable(</a>CustTable&lt;U,V&gt; i){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span>=<span style=\"background-color:green;\">i;.;</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">length;;;</span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustTable.size()\" href=\"pkg/CustTable.html#m298\">size</a>()</span></span></span>:\n" +
                " }\n" +
                " $public CustPair&lt;U,V&gt; <a name=\"m247\">nextPair</a>(){\n" +
                "  CustPair&lt;U,V&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\">out</span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\">list;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustTable.get($int)\" href=\"pkg/CustTable.html#m355\">get</a>(<span style=\"background-color:green;\">index;;;</span>)</span></span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">index;;;</span>++</span>:\n" +
                "  $return <span style=\"background-color:green;\">out;.</span>:\n" +
                " }\n" +
                " $public $boolean <a name=\"m353\">hasNextPair</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">index;;;</span>&lt;<span style=\"background-color:green;\">length;;;</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.getValue(3));
    }
    @Test
    public void coverage71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.CustTable&lt;U,V&gt; :$iterableTable&lt;U,V&gt;{\n" +
                " $private CustList&lt;CustPair&lt;U,V&gt;&gt; <span style=\"background-color:green;\">list</span>:\n" +
                " <a name=\"m97\">$public (</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">list</span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"pkg/CustList.html#m93\">$new</a> CustList&lt;CustPair&lt;U,V&gt;&gt;()</span></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m166\">add</a>(U f,V s){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">list</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:green;\"><a title=\"pkg.CustPair.pkg.CustPair(#U,#V)\" href=\"pkg/CustPair.html#m110\">$new</a> CustPair&lt;U,V&gt;(<span style=\"background-color:green;\">f;.;</span>,<span style=\"background-color:green;\">s;.;</span>)</span>)</span></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m241\">add</a>(CustPair&lt;U,V&gt; p){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">list</span>.<span style=\"background-color:red;\"><a title=\"pkg.CustList.add(#U)\" href=\"pkg/CustList.html#m154\">add</a>(<span style=\"background-color:red;\">p;.;</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m298\">size</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">list</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustList.size()\" href=\"pkg/CustList.html#m519\">size</a>()</span></span>:\n" +
                " }\n" +
                " $public CustPair&lt;U,V&gt; <a name=\"m355\">get</a>($int index){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">list</span>.<span style=\"background-color:green;\"><a title=\"pkg.CustList.get($int)\" href=\"pkg/CustList.html#m571\">get</a>(<span style=\"background-color:green;\">index;.;</span>)</span></span>:\n" +
                " }\n" +
                " $public $iteratorTable&lt;U,V&gt; <a name=\"m434\">iteratorTable</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.pkg.CustIterTable(pkg.CustTable&lt;#U,#V&gt;)\" href=\"pkg/CustIterTable.html#m137\">$new</a> CustIterTable&lt;U,V&gt;(<span style=\"background-color:green;\">$this</span>)</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.getValue(4));
    }
    @Test
    public void coverage72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.CustPair&lt;U,V&gt; :$pair&lt;U,V&gt;{\n" +
                " $private U <span style=\"background-color:green;\">first</span>:\n" +
                " $private V <span style=\"background-color:green;\">second</span>:\n" +
                " <a name=\"m86\">$public CustPair(</a>){\n" +
                " }\n" +
                " <a name=\"m110\">$public CustPair(</a>U f,V s){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">first </span>=<span style=\"background-color:green;\"> f;.;</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\">second </span>=<span style=\"background-color:green;\"> s;.;</span></span>:\n" +
                " }\n" +
                " $public U <a name=\"m184\">getFirst</a>(){\n" +
                "  $return <span style=\"background-color:green;\">first</span>:\n" +
                " }\n" +
                " $public V <a name=\"m227\">getSecond</a>(){\n" +
                "  $return <span style=\"background-color:green;\">second</span>:\n" +
                " }\n" +
                " $public $void <a name=\"m276\">setFirst</a>(U f){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\">first </span>=<span style=\"background-color:red;\"> f;.;</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.getValue(5));
    }
    @Test
    public void coverage73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator< $boolean (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" $return p;.;field<q;.;field:\n");
        xml_.append("}\n");
        xml_.append("$operator> $boolean (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" $return p;.;field>q;.;field:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $boolean rOne = one;. < two;.:\n");
        xml_.append("  $boolean rTwo = one;. > two;.:\n");
        xml_.append("  $boolean rThree = one;.field < two;.field:\n");
        xml_.append("  $boolean rFour = one;.field > two;.field:\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageDisplay();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$operator<a name=\"m9\">&lt;</a> $boolean (pkg.Ex p, pkg.Ex q){\n" +
                " $return <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">p;.;</span><span style=\"background-color:green;\">field</span></span><a title=\"&quot;\">&lt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\">q;.;</span><span style=\"background-color:green;\">field</span></span></span>:\n" +
                "}\n" +
                "$operator<a name=\"m83\">&gt;</a> $boolean (pkg.Ex p, pkg.Ex q){\n" +
                " $return <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">p;.;</span><span style=\"background-color:green;\">field</span></span><a title=\"&amp;\">&gt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\">q;.;</span><span style=\"background-color:green;\">field</span></span></span>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $int <span style=\"background-color:green;\">field</span>:\n" +
                " $public $static $int <a name=\"m215\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">one </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\">two </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">one;.</span><span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">two;.</span><span style=\"background-color:green;\">field </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">rOne </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> one;. </span><a title=\"$static &lt;(pkg.Ex,pkg.Ex)\" href=\"pkg/Ex.html#m9\">&lt;</a><span style=\"background-color:green;\"> two;.</span></span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">rTwo </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> one;. </span><a title=\"$static &gt;(pkg.Ex,pkg.Ex)\" href=\"pkg/Ex.html#m83\">&gt;</a><span style=\"background-color:green;\"> two;.</span></span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">rThree </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> one;.</span><span style=\"background-color:green;\">field </span></span><a title=\"&quot;\">&lt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"> two;.</span><span style=\"background-color:green;\">field</span></span></span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\">rFour </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> one;.</span><span style=\"background-color:green;\">field </span></span><a title=\"&amp;\">&gt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"> two;.</span><span style=\"background-color:green;\">field</span></span></span></span>:\n" +
                "  $return <span style=\"background-color:green;\">5</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int j=0;2;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:green;\">iter</span> (int j=<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">2</span>;<span style=\"background-color:green;\">1</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> s </span>==<span style=\"background-color:green;\"> j </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int j=0;2;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   break;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">iter</span> (int j=<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">2</span>;<span style=\"background-color:green;\">1</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\">t </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> s </span><a title=\"false\">==</a><span style=\"background-color:green;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> t</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   break;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int j=0;0;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">iter</span> (int j=<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">1</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return s;\n");
        xml_.append("  }\n");
        xml_.append("  iter (int j=0;0;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>public class pkg.Ex {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">s </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\">sum </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\">sum </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">s</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">iter</span> (int j=<span style=\"background-color:red;\">0</span>;<span style=\"background-color:red;\">0</span>;<span style=\"background-color:red;\">1</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\">t </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> s </span>==<span style=\"background-color:red;\"> j </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\">sum </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> t</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\">sum</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    private static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustPair<U,V> :$pair<U,V>{\n");
        xml_.append(" $private U first:\n");
        xml_.append(" $private V second:\n");
        xml_.append(" $public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair(U f,V s){\n");
        xml_.append("  first = f;.;:\n");
        xml_.append("  second = s;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public U getFirst(){\n");
        xml_.append("  $return first:\n");
        xml_.append(" }\n");
        xml_.append(" $public V getSecond(){\n");
        xml_.append("  $return second:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void setFirst(U f){\n");
        xml_.append("  first = f;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustTable<U,V> :$iterableTable<U,V>{\n");
        xml_.append(" $private CustList<CustPair<U,V>> list:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new CustList<CustPair<U,V>>():\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(U f,V s){\n");
        xml_.append("  list.add($new CustPair<U,V>(f;.;,s;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $int size(){\n");
        xml_.append("  $return list.size():\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> get($int index){\n");
        xml_.append("  $return list.get(index;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $iteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  $return $new CustIterTable<U,V>($this):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIterTable<U,V> :$iteratorTable<U,V>{\n");
        xml_.append(" $private CustTable<U,V> list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $private $int index:\n");
        xml_.append(" $public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list;;;=i;.;:\n");
        xml_.append("  length;;;=list;;;size():\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list;;;get(index;;;):\n");
        xml_.append("  index;;;++:\n");
        xml_.append("  $return out;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $boolean hasNextPair(){\n");
        xml_.append("  $return index;;;<length;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<#U> :$iterable<#U>{\n");
        xml_.append(" $private #U[] list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list;;;=$new #U[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(#U elt){\n");
        xml_.append("  add(length;;;,elt;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,#U elt){\n");
        xml_.append("  #U[] newlist=$new #U[length;;;+1i]:\n");
        xml_.append("  $iter($int i=0i:index;.;:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;]:\n");
        xml_.append("  }\n");
        xml_.append("  newlist;.[index;.;]=elt;.;:\n");
        xml_.append("  $iter($int i=index;.;+1i:length;;;+1i:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;-1i]:\n");
        xml_.append("  }\n");
        xml_.append("  length;;;++:\n");
        xml_.append("  list;;;=newlist;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #U get($int index){\n");
        xml_.append("  $return list;;;[index;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,#U elt){\n");
        xml_.append("  list;;;[index;.;]=elt;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;.;:length;;;-1i:1i){\n");
        xml_.append("   list;;;[i;]=list;;;[i;+1i]:\n");
        xml_.append("  }\n");
        xml_.append("  list;;;[length;;;-1i]=$null:\n");
        xml_.append("  length;;;--:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<#U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<#U>($this):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<#T> :$iterator<#T>{\n");
        xml_.append(" $private pkg.CustList<#T> list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $private $int index:\n");
        xml_.append(" $public (pkg.CustList<#T> i){\n");
        xml_.append("  list;;;=i;.;:\n");
        xml_.append("  length;;;=list;;;size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T next(){\n");
        xml_.append("  #T out=list;;;get(index;;;):\n");
        xml_.append("  index;;;++:\n");
        xml_.append("  $return out;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index;;;<length;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
