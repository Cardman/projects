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
                "  $long <span style=\"background-color:green;\"><a name=\"m64\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m72\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 2 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m72\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:green;\">for (int <a name=\"m90\">j</a></span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">0</span>,<span style=\"background-color:green;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m109\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:green;\"> <a href=\"#m90\">j</a> </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m109\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">for (int <a name=\"m90\">j</a></span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">0</span>,<span style=\"background-color:green;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m109\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> <a href=\"#m90\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m109\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   break;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">for (int <a name=\"m90\">j</a></span>:<span style=\"background-color:green;\">{}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m106\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m90\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m106\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:green;\">while</span> (<span style=\"background-color:green;\">true</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m104\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m104\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m124\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m143\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m124\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m143\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  $Annotation[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m119\">arr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> $class(Ex)</span>.<span style=\"background-color:green;\">getAnnotations()</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m119\">arr</a>;.</span><span style=\"background-color:green;\">length </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> 1i</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">3i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><a href=\"#m119\">arr</a>;.</span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">2i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m119\">arr</a>;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class(MyAnnot)</span></span>){\n" +
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
                "  java.lang.Object <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m145\">arr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> $class(MyAnnot)</span>.<span style=\"background-color:green;\">getDeclaredMethods()</span></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0i</span>]</span></span>.<span style=\"background-color:green;\">getDefaultValue()</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><a href=\"#m145\">arr</a>;.</span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class(java.lang.Integer)</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">2i</span>:\n" +
                "  }\n" +
                "  java.lang.Integer <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m320\">a</a> </span>=<span style=\"background-color:green;\"> $(java.lang.Integer)<span style=\"background-color:green;\"><a href=\"#m145\">arr</a>;.</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m320\">a</a>;. </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> 1i</span></span>){\n" +
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
                " $public $static $int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m46\">st</a> </span>=<span style=\"background-color:green;\"> 0</span></span>:\n" +
                " $public $static $int <a name=\"m76\">exmeth</a>(){\n" +
                "  $long <span style=\"background-color:green;\"><a name=\"m94\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m94\">t</a>;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m94\">t</a>;.</span></span></span>:\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"false\">&lt;</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "   $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "  }\n" +
                "  $else{\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                "  $long <span style=\"background-color:green;\"><a name=\"m64\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">10</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span style=\"background-color:green;\">10</span>):\n" +
                "   $case(<span style=\"background-color:red;\">8</span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
                "  $long <span style=\"background-color:green;\"><a name=\"m64\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:green;\">8</span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
                "  $long <span style=\"background-color:green;\"><a name=\"m64\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">9</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:red;\">8</span>){\n" +
                "    <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:red;\">16</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
                "  $long <span style=\"background-color:green;\"><a name=\"m64\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">9</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:red;\">8</span>){\n" +
                "    <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:red;\">16</span></span>:\n" +
                "   }\n" +
                "   <span style=\"background-color:green;\">$default</span>{\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
                "  $long <span style=\"background-color:green;\"><a name=\"m64\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">8</span></span>:\n" +
                "  $switch(<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span style=\"background-color:red;\">10</span>):\n" +
                "   $case(<span style=\"background-color:green;\">8</span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">16</span></span>:\n" +
                "   }\n" +
                "   <span style=\"background-color:red;\">$default</span>{\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span>=<span style=\"background-color:green;\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">1i</span>+<span style=\"background-color:green;\">$($int)<span style=\"background-color:green;\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.$static call()\" href=\"#m99\">call</a>()</span>:\n" +
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex (pkg.Ex <a name=\"m26\">p</a>, pkg.Ex <a name=\"m36\">q</a>){\n" +
                " pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m48\">out</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m26\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m36\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$operator<a name=\"m131\">-</a> pkg.Ex (pkg.Ex <a name=\"m148\">p</a>, pkg.Ex <a name=\"m158\">q</a>){\n" +
                " pkg.Ex <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m170\">out</a> </span>=<span style=\"background-color:red;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m170\">out</a>;.</span><span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m148\">p</a>;.;</span><span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span>-<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m158\">q</a>;.;</span><span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:red;\"><a href=\"#m170\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m282\">field</a></span>:\n" +
                " $public $static $int <a name=\"m311\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m330\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m360\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m330\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m360\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m330\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span style=\"background-color:green;\"><a href=\"#m360\">two</a>;.</span></span>)</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span>:\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:green;\">1i</span>/<span style=\"background-color:green;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>(Object <a name=\"m158\">o</a>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">-<span style=\"background-color:green;\">1i</span></span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"false\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:green;\">2</span>:\n" +
                "  } <span style=\"background-color:red;\">$catch</span>(Object <a name=\"m159\">o</a>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:red;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:red;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                " <a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum()\" href=\"#m51\">ONE</a>;\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m43\">field</a></span>;\n" +
                " <a name=\"m51\">ExEnum(</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.field\" href=\"#m43\">field</a></span>=<span style=\"background-color:green;\">5</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m118\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m134\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m147\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m147\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m201\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m220\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m134\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m201\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m147\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m220\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m147\">sum</a></span>;\n" +
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
                " <a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m54\">ONE</a>(<span style=\"background-color:green;\">5</span>);\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m46\">field</a></span>;\n" +
                " <a name=\"m54\">ExEnum(</a>int <a name=\"m65\">param</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.field\" href=\"#m46\">field</a></span>=<span style=\"background-color:green;\"><a href=\"#m65\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m134\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m150\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m163\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m163\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m217\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m236\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m150\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m217\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m163\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m236\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m163\">sum</a></span>;\n" +
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
                " <a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m55\">FOUR</a>(<span style=\"background-color:green;\">5</span>);\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m47\">field</a></span>;\n" +
                " <a name=\"m55\">ExEnum(</a>int <a name=\"m66\">param</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.field\" href=\"#m47\">field</a></span>=<span style=\"background-color:green;\"><a href=\"#m66\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m135\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m151\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m164\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m164\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m218\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m237\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m151\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m218\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m164\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m237\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m164\">sum</a></span>;\n" +
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
                " <a name=\"m26\">ONE</a>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m74\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m90\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m103\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m103\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m157\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m176\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m90\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m157\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m103\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m176\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m103\">sum</a></span>;\n" +
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
                " <a name=\"m26\" title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR(int)\" href=\"#m37\">FOUR</a>(<span style=\"background-color:green;\">5</span>){\n" +
                "  <a name=\"m37\">(</a>int <a name=\"m42\">p</a>){\n" +
                "   <span style=\"background-color:green;\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m87\">super</a>(<span style=\"background-color:green;\"><a href=\"#m42\">p</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m79\">field</a></span>;\n" +
                " <a name=\"m87\">ExEnum(</a>int <a name=\"m98\">param</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.field\" href=\"#m79\">field</a></span>=<span style=\"background-color:green;\"><a href=\"#m98\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m167\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m183\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m196\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m196\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m250\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m269\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m183\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m250\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m196\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m269\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m196\">sum</a></span>;\n" +
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
                " <a name=\"m26\" title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR()\" href=\"#m34\">FOUR</a>{\n" +
                "  <a name=\"m34\">(</a>){\n" +
                "   <span style=\"background-color:green;\"><a title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR(int)\" href=\"#m56\">this</a>(<span style=\"background-color:green;\">5</span>)</span>;\n" +
                "  }\n" +
                "  <a name=\"m56\">(</a>int <a name=\"m61\">p</a>){\n" +
                "   <span style=\"background-color:green;\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m106\">super</a>(<span style=\"background-color:green;\"><a href=\"#m61\">p</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m98\">field</a></span>;\n" +
                " <a name=\"m106\">ExEnum(</a>int <a name=\"m117\">param</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.field\" href=\"#m98\">field</a></span>=<span style=\"background-color:green;\"><a href=\"#m117\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m186\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m202\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m215\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m215\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m269\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m288\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m202\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m269\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m215\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m288\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m215\">sum</a></span>;\n" +
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
                " <a name=\"m26\">FOUR</a>{\n" +
                "  public int <span style=\"background-color:green;\"><a name=\"m45\">field</a></span>;\n" +
                " };\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public static int <a name=\"m99\">exmeth</a>(){\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m115\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m128\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m128\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for (int <a name=\"m182\">j</a></span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">0</span>,<span style=\"background-color:red;\">1</span>}</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m201\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m115\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m182\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m128\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m201\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m128\">sum</a></span>;\n" +
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
        assertEq("<html><body><pre>operator<a name=\"m8\">+</a> pkg.Ex(pkg.Ex <a name=\"m24\">f</a>,pkg.Ex <a name=\"m33\">s</a>) {\n" +
                " return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m24\">f</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m33\">s</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> pkg.Ex(pkg.Ex <a name=\"m103\">f</a>) {\n" +
                " return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m103\">f</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> pkg.Ex(pkg.Ex <a name=\"m164\">f</a>) {\n" +
                " return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m164\">f</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>+<span style=\"background-color:green;\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m236\">field</a></span>;\n" +
                " <a name=\"m244\">public Ex(</a>int <a name=\"m258\">field</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">this</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>=<span style=\"background-color:green;\"> <a href=\"#m258\">field</a></span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">this</a>(<span style=\"background-color:red;\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m353\">one</a> </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> Ex(<span style=\"background-color:green;\">5</span>)</span></span>;\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m375\">two</a> </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> Ex(<span style=\"background-color:green;\">3</span>)</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m398\">three</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m353\">one</a> </span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"#m8\">+</a><span style=\"background-color:green;\"> <a href=\"#m375\">two</a></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m423\">four</a> </span>=<span style=\"background-color:green;\"> <a title=\"static +(pkg.Ex)\" href=\"#m87\">+</a><span style=\"background-color:green;\"> <a href=\"#m375\">two</a></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m443\">five</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m398\">three</a> </span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"#m8\">+</a>=<span style=\"background-color:green;\"> <a href=\"#m423\">four</a></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m471\">six</a> </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> Ex(<span style=\"background-color:green;\">6</span>)</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m494\">seven</a> </span>=<span style=\"background-color:yellow;\"> <a title=\"true\">!</a><span style=\"background-color:yellow;\">(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m471\">six</a> </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> <a href=\"#m471\">six</a></span></span>)</span></span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><a href=\"#m494\">seven</a></span>){\n" +
                "   <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m494\">seven</a> </span>=<span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m494\">seven</a></span></span></span>;\n" +
                "  } <span style=\"background-color:red;\">else if</span> (<span style=\"background-color:red;\"><a href=\"#m494\">seven</a></span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m494\">seven</a> </span>=<span style=\"background-color:red;\"> !<span style=\"background-color:red;\"><a href=\"#m494\">seven</a></span></span></span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m471\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"#m818\">[</a><span style=\"background-color:green;\">0</span><a title=\"pkg.Ex.[](int)\" href=\"#m818\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">=</a><span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m471\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">[</a><span style=\"background-color:green;\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">]</a> </span></span>=<span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m628\">eight</a> </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> Ex(<span style=\"background-color:green;\">8</span>)</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m628\">eight</a></span><a title=\"static ++(pkg.Ex)\" href=\"#m147\">+</a>+</span>;\n" +
                "  <span style=\"background-color:green;\"><a title=\"static ++(pkg.Ex)\" href=\"#m147\">+</a>+<span style=\"background-color:green;\"><a href=\"#m628\">eight</a></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m471\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"#m818\">[</a><span style=\"background-color:green;\">2</span><a title=\"pkg.Ex.[](int)\" href=\"#m818\">]</a></span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">+</a></span>;\n" +
                "  <span style=\"background-color:green;\">+<a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">+</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m471\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"#m818\">[</a><span style=\"background-color:green;\">3</span><a title=\"pkg.Ex.[](int)\" href=\"#m818\">]</a></span></span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m699\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m712\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m712\">toStr</a> </span><i>+</i>=<span style=\"background-color:green;\"> new ExTwo()</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m756\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m699\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m756\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>+<span style=\"background-color:green;\"><a title=\"pkg.Ex.static caller()\" href=\"#m915\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m818\">this</a>(int <a name=\"m827\">p</a>){\n" +
                "  return <span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m863\">this</a>(int <a name=\"m872\">p</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span>=<span style=\"background-color:green;\"> <b>value</b></span></span>;\n" +
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
        assertEq("<html><body><pre>operator<a name=\"m8\">+</a> pkg.Ex(pkg.Ex <a name=\"m24\">f</a>,pkg.Ex <a name=\"m33\">s</a>) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m24\">f</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m33\">s</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> pkg.Ex(pkg.Ex <a name=\"m103\">f</a>) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m103\">f</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> pkg.Ex(pkg.Ex <a name=\"m164\">f</a>) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m164\">f</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>+<span style=\"background-color:red;\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m236\">field</a></span>;\n" +
                " <a name=\"m244\">public Ex(</a>int <a name=\"m258\">field</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">this</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>=<span style=\"background-color:green;\"> <a href=\"#m258\">field</a></span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">this</a>(<span style=\"background-color:red;\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m354\">one</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m372\">two</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m390\">three</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a href=\"#m354\">one</a> </span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span style=\"background-color:yellow;\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m416\">four</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">&amp;</a>&amp;<span style=\"background-color:red;\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m442\">five</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">&amp;</a>&amp;<span style=\"background-color:red;\"> !<span style=\"background-color:red;\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m469\">eight</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a href=\"#m354\">one</a> </span><a title=\"true\">&amp;</a><a title=\"false\">&amp;</a><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m496\">six</a> </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> Ex(<span style=\"background-color:green;\">6</span>)</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m519\">seven</a> </span>=<span style=\"background-color:yellow;\"> <a title=\"true\">!</a><span style=\"background-color:yellow;\">(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m496\">six</a> </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> <a href=\"#m496\">six</a></span></span>)</span></span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m553\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m553\">i</a> </span>&lt;<span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m553\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\">!<span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m553\">i</a> </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>)</span></span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m519\">seven</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m496\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"#m785\">[</a><span style=\"background-color:green;\">0</span><a title=\"pkg.Ex.[](int)\" href=\"#m785\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m830\">=</a><span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m496\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=(int)\" href=\"#m830\">[</a><span style=\"background-color:green;\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"#m830\">]</a> </span></span>=<span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m658\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m671\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m671\">toStr</a> </span>+=<span style=\"background-color:green;\"> new ExTwo()</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m715\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m658\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m658\">s</a></span>+=<span style=\"background-color:green;\">1</span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m715\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>+<span style=\"background-color:green;\"><a title=\"pkg.Ex.static caller()\" href=\"#m882\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m785\">this</a>(int <a name=\"m794\">p</a>){\n" +
                "  return <span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m830\">this</a>(int <a name=\"m839\">p</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span>=<span style=\"background-color:green;\"> <b>value</b></span></span>;\n" +
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
        assertEq("<html><body><pre>operator<a name=\"m8\">+</a> pkg.Ex(pkg.Ex <a name=\"m24\">f</a>,pkg.Ex <a name=\"m33\">s</a>) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m24\">f</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m33\">s</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> pkg.Ex(pkg.Ex <a name=\"m103\">f</a>) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m103\">f</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> pkg.Ex(pkg.Ex <a name=\"m164\">f</a>) {\n" +
                " return <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> pkg.Ex(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m164\">f</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>+<span style=\"background-color:red;\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class pkg.Ex {\n" +
                " public int <span style=\"background-color:green;\"><a name=\"m236\">field</a></span>;\n" +
                " <a name=\"m244\">public Ex(</a>int <a name=\"m258\">field</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">this</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>=<span style=\"background-color:green;\"> <a href=\"#m258\">field</a></span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span style=\"background-color:red;\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">this</a>(<span style=\"background-color:red;\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m354\">one</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m372\">two</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m390\">three</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a href=\"#m354\">one</a> </span><a title=\"true\">|</a>|<span style=\"background-color:red;\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m416\">four</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">|</a><a title=\"true\">|</a><span style=\"background-color:yellow;\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m442\">five</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">|</a><a title=\"false\">|</a><span style=\"background-color:yellow;\"> <a title=\"false\">!</a><span style=\"background-color:yellow;\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m469\">eight</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"> <a href=\"#m354\">one</a> </span><a title=\"true\">|</a>|<span style=\"background-color:red;\"> !<span style=\"background-color:red;\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m496\">six</a> </span>=<span style=\"background-color:green;\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> Ex(<span style=\"background-color:green;\">6</span>)</span></span>;\n" +
                "  var <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m519\">seven</a> </span>=<span style=\"background-color:yellow;\"> <a title=\"true\">!</a><span style=\"background-color:yellow;\">(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m496\">six</a> </span><a title=\"false\">!=</a><span style=\"background-color:green;\"> <a href=\"#m496\">six</a></span></span>)</span></span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m553\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m553\">i</a> </span>&lt;<span style=\"background-color:green;\"> 4</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m553\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m553\">i</a> </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0 </span></span>&amp;&amp;<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m553\">i</a> </span>&gt;<span style=\"background-color:green;\"> 1</span></span></span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m519\">seven</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m496\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[](int)\" href=\"#m791\">[</a><span style=\"background-color:green;\">0</span><a title=\"pkg.Ex.[](int)\" href=\"#m791\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m836\">=</a><span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m496\">six</a></span><span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=(int)\" href=\"#m836\">[</a><span style=\"background-color:green;\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"#m836\">]</a> </span></span>=<span style=\"background-color:green;\"> 8</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m664\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m677\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m677\">toStr</a> </span>+=<span style=\"background-color:green;\"> new ExTwo()</span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m721\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m664\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m664\">s</a></span>+=<span style=\"background-color:green;\">1</span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m721\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>+<span style=\"background-color:green;\"><a title=\"pkg.Ex.static caller()\" href=\"#m888\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m791\">this</a>(int <a name=\"m800\">p</a>){\n" +
                "  return <span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m836\">this</a>(int <a name=\"m845\">p</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span>=<span style=\"background-color:green;\"> <b>value</b></span></span>;\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span>&lt;<span style=\"background-color:green;\"><span style=\"color:blue;\">\"\"</span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m74\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m74\">toStr</a> </span>+=<span style=\"background-color:green;\"> 1</span></span>;\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span>&lt;<span style=\"background-color:green;\"><span style=\"color:blue;\">\"\"</span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m74\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  Object <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m95\">right</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m74\">toStr</a> </span><i>+</i>=<span style=\"background-color:green;\"> <a href=\"#m95\">right</a></span></span>;\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span>&lt;<span style=\"background-color:green;\"><span style=\"color:blue;\">\"\"</span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m74\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  Object[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m97\">right</a> </span>=<span style=\"background-color:green;\"> null</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m74\">toStr</a> </span>+=<span style=\"background-color:green;\"> <a href=\"#m97\">right</a></span></span>;\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m93\">s</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span>&lt;<span style=\"background-color:green;\"><span style=\"color:blue;\">\"\"</span></span></span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m110\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  Annotation <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m135\">right</a> </span>=<span style=\"background-color:green;\"> null</span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m110\">toStr</a> </span>+=<span style=\"background-color:green;\"> <a href=\"#m135\">right</a></span></span>;\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">one</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><a href=\"#m57\">one</a></span>){\n" +
                "   return <span style=\"background-color:green;\">5</span>;\n" +
                "  }\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m104\">two</a> </span>=<span style=\"background-color:red;\"> true</span></span>;\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m122\">three</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m57\">one</a> </span>&amp;&amp;<span style=\"background-color:red;\"> <a href=\"#m104\">two</a></span></span></span>;\n" +
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
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">3i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">1i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">2i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\">$foreach(java.lang.Number <a name=\"m236\">e</a></span>:<span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m236\">e</a>;</span><span style=\"background-color:green;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m302\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">3i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">1i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">2i</span>)</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$foreach(java.lang.Number <a name=\"m236\">e</a></span>:<span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m236\">e</a>;</span><span style=\"background-color:green;\">intValue()</span></span></span>:\n" +
                "   $break:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m313\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$foreach(java.lang.Number <a name=\"m182\">e</a></span>:<span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m182\">e</a>;</span><span style=\"background-color:red;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m248\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>) {\n" +
                "   $return:\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">$foreach(java.lang.Number <a name=\"m217\">e</a></span>:<span style=\"background-color:red;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m217\">e</a>;</span><span style=\"background-color:red;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m283\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m66\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">3</span>,<span style=\"background-color:green;\">5</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">8</span>,<span style=\"background-color:green;\">1</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">2</span>,<span style=\"background-color:green;\">6</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\">$for(Number <a name=\"m207\">f</a>, Number <a name=\"m217\">s</a></span>: <span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m207\">f</a>;</span><span style=\"background-color:green;\">intValue()</span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m217\">s</a>;</span><span style=\"background-color:green;\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m293\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m66\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">3</span>,<span style=\"background-color:green;\">5</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">8</span>,<span style=\"background-color:green;\">1</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span style=\"background-color:green;\">2</span>,<span style=\"background-color:green;\">6</span>)</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$for(Number <a name=\"m207\">f</a>, Number <a name=\"m217\">s</a></span>: <span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m207\">f</a>;</span><span style=\"background-color:green;\">intValue()</span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m217\">s</a>;</span><span style=\"background-color:green;\">intValue()</span></span></span></span>:\n" +
                "   $break:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m304\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m66\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$for(Number <a name=\"m156\">f</a>, Number <a name=\"m166\">s</a></span>: <span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m156\">f</a>;</span><span style=\"background-color:red;\">intValue()</span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m166\">s</a>;</span><span style=\"background-color:red;\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m242\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
                " $public $static CustTable&lt;Number,Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m66\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> CustTable&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>) {\n" +
                "   $return:\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">$for(Number <a name=\"m191\">f</a>, Number <a name=\"m201\">s</a></span>: <span style=\"background-color:red;\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m191\">f</a>;</span><span style=\"background-color:red;\">intValue()</span></span>+<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m201\">s</a>;</span><span style=\"background-color:red;\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m277\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
                "  <span style=\"background-color:yellow;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m62\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m62\">i</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m62\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\">!<span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m62\">i</a> </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m131\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m144\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m144\">toStr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m144\">toStr</a> </span><i>+</i><span style=\"background-color:green;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m144\">toStr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> new ExTwo() </span><i>+</i><span style=\"background-color:green;\"> <a href=\"#m144\">toStr</a></span></span></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1</span></span>){\n" +
                "   return <span style=\"background-color:green;\">7</span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">for</span> (int <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m107\">i</a> </span>=<span style=\"background-color:red;\"> 0</span></span>; <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m107\">i</a> </span>&lt;<span style=\"background-color:red;\"> 2</span></span>; <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m107\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:red;\">if</span> (<span style=\"background-color:red;\">!<span style=\"background-color:red;\">(<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m107\">i</a> </span>%<span style=\"background-color:red;\"> 2 </span></span>==<span style=\"background-color:red;\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m176\">toStr</a> </span>=<span style=\"background-color:red;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m176\">toStr</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m176\">toStr</a> </span>+<span style=\"background-color:red;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m176\">toStr</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> new ExTwo() </span>+<span style=\"background-color:red;\"> <a href=\"#m176\">toStr</a></span></span></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1</span></span>){\n" +
                "   return <span style=\"background-color:green;\">7</span>;\n" +
                "  }\n" +
                "  for (;;){\n" +
                "    break;\n" +
                "  }\n" +
                "  var <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m129\">toStr</a> </span>=<span style=\"background-color:red;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m129\">toStr</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m129\">toStr</a> </span>+<span style=\"background-color:red;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m129\">toStr</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> new ExTwo() </span>+<span style=\"background-color:red;\"> <a href=\"#m129\">toStr</a></span></span></span>;\n" +
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
                "  $Annotation[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m119\">arr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> $class(Ex)</span>.<span style=\"background-color:green;\">getAnnotations()</span></span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m119\">arr</a>;.</span><span style=\"background-color:green;\">length </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> 1i</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">3i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$else $if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><a href=\"#m119\">arr</a>;.</span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span style=\"background-color:red;\">2i</span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:yellow;\">$else $if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$static($Class)</span>.<span style=\"background-color:green;\">getClass(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m119\">arr</a>;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span style=\"background-color:green;\"> $class(MyAnnot)</span></span>){\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">seven</a> </span>=<span style=\"background-color:green;\"> false</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m83\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a> </span>&lt;<span style=\"background-color:green;\"> 4</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a> </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">seven</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "   <span style=\"background-color:green;\">else if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a> </span>&gt;<span style=\"background-color:green;\"> 1</span></span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">seven</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m199\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
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
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">seven</a> </span>=<span style=\"background-color:green;\"> false</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m83\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a> </span>&lt;<span style=\"background-color:green;\"> 4</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m83\">i</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 4</span></span>){\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">seven</a> </span>=<span style=\"background-color:green;\"> true</span></span>;\n" +
                "   }\n" +
                "   <span style=\"background-color:red;\">else if</span> (<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m83\">i</a> </span>&gt;<span style=\"background-color:red;\"> 1</span></span>){\n" +
                "    <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m57\">seven</a> </span>=<span style=\"background-color:red;\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m194\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
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
                "  <span style=\"background-color:yellow;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m62\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>; <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m62\">i</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m62\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\">!<span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m62\">i</a> </span>%<span style=\"background-color:green;\"> 2 </span></span>==<span style=\"background-color:green;\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m131\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m144\">toStr</a> </span>=<span style=\"background-color:green;\"> <span style=\"color:blue;\">\"\"</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m144\">toStr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m144\">toStr</a> </span>+<span style=\"background-color:green;\"> new ExTwo()</span></span></span>;\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m144\">toStr</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> new ExTwo() </span>+<span style=\"background-color:green;\"> <a href=\"#m144\">toStr</a></span></span></span>;\n" +
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
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"#m25\">$new</a> Ex()</span>:\n" +
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
                "  $return <span style=\"background-color:green;\">$new StringBuilder(<span style=\"background-color:green;\"><span style=\"color:blue;\">\"hello\"</span></span>)</span>:\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">while</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1</span></span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m106\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m106\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">while</span> (<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m57\">s</a> </span>==<span style=\"background-color:red;\"> 1</span></span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m142\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:red;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m142\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:red;\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:green;\">1i</span>/<span style=\"background-color:green;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>(Object <a name=\"m158\">o</a>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">-<span style=\"background-color:green;\">1i</span></span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"false\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $return <span style=\"background-color:red;\"><span style=\"background-color:red;\">1i</span>/<span style=\"background-color:red;\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:green;\">2</span>:\n" +
                "  } <span style=\"background-color:red;\">$catch</span>(Object <a name=\"m159\">o</a>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:red;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:red;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $throw <span style=\"background-color:green;\">1i</span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>(Object <a name=\"m154\">o</a>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">1i</span></span>:\n" +
                "   $return <span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m94\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m94\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m94\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m94\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "  }<span style=\"background-color:green;\">while</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>&lt;<span style=\"background-color:green;\"> 7</span></span>);\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m94\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m94\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>&gt;=<span style=\"background-color:green;\"> 7</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }<span style=\"background-color:yellow;\">while</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 17</span></span>);\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m94\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 0 </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m94\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   <span style=\"background-color:green;\">if</span> (<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>&gt;=<span style=\"background-color:green;\"> 7</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "   continue;\n" +
                "  }<span style=\"background-color:yellow;\">while</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 17</span></span>);\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"#m25\">$new</a> Ex()</span>:\n" +
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
                " <a name=\"m25\">$public Ex(</a>$int <a name=\"m41\">p</a>){\n" +
                " }\n" +
                " $public $static Object <a name=\"m72\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex($int)\" href=\"#m25\">$new</a> Ex(<span style=\"background-color:green;\">0</span>)</span>:\n" +
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
                " $private pkg.CustList&lt;#T&gt; <span style=\"background-color:green;\"><a name=\"m75\">list</a></span>:\n" +
                " $private $int <span style=\"background-color:green;\"><a name=\"m96\">length</a></span>:\n" +
                " $private $int <span style=\"background-color:green;\"><a name=\"m119\">index</a></span>:\n" +
                " <a name=\"m127\">$public (</a>pkg.CustList&lt;#T&gt; <a name=\"m153\">i</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIter.list\" href=\"#m75\">list</a>;;;</span>=<span style=\"background-color:green;\"><a href=\"#m153\">i</a>;.;</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIter.length\" href=\"#m96\">length</a>;;;</span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIter.list\" href=\"#m75\">list</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.size()\" href=\"CustList.html#m519\">size</a>()</span></span></span>:\n" +
                " }\n" +
                " $public $normal #T <a name=\"m223\">next</a>(){\n" +
                "  #T <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m236\">out</a></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIter.list\" href=\"#m75\">list</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.get($int)\" href=\"CustList.html#m571\">get</a>(<span style=\"background-color:green;\"><a title=\"pkg.CustIter.index\" href=\"#m119\">index</a>;;;</span>)</span></span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIter.index\" href=\"#m119\">index</a>;;;</span>++</span>:\n" +
                "  $return <span style=\"background-color:green;\"><a href=\"#m236\">out</a>;.</span>:\n" +
                " }\n" +
                " $public $normal $boolean <a name=\"m322\">hasNext</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIter.index\" href=\"#m119\">index</a>;;;</span>&lt;<span style=\"background-color:green;\"><a title=\"pkg.CustIter.length\" href=\"#m96\">length</a>;;;</span></span>:\n" +
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
                " $private #U[] <span style=\"background-color:green;\"><a name=\"m63\">list</a></span>:\n" +
                " $private $int <span style=\"background-color:green;\"><a name=\"m84\">length</a></span>:\n" +
                " <a name=\"m93\">$public (</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span>=<span style=\"background-color:green;\">$new #U[<span style=\"background-color:green;\">0i</span>]</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m154\">add</a>(#U <a name=\"m161\">elt</a>){\n" +
                "  <span style=\"background-color:green;\"><a title=\"pkg.CustList.add($int,#U)\" href=\"#m218\">add</a>(<span style=\"background-color:green;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>,<span style=\"background-color:green;\"><a href=\"#m161\">elt</a>;.;</span>)</span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m218\">add</a>($int <a name=\"m227\">index</a>,#U <a name=\"m236\">elt</a>){\n" +
                "  #U[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m249\">newlist</a></span>=<span style=\"background-color:green;\">$new #U[<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>+<span style=\"background-color:green;\">1i</span></span>]</span></span>:\n" +
                "  <span style=\"background-color:green;\">$iter</span>($int <a name=\"m293\">i</a>=<span style=\"background-color:green;\">0i</span>:<span style=\"background-color:green;\"><a href=\"#m227\">index</a>;.;</span>:<span style=\"background-color:green;\">1i</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m249\">newlist</a>;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\"><a href=\"#m293\">i</a>;</span>]</span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\"><a href=\"#m293\">i</a>;</span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m249\">newlist</a>;.</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\"><a href=\"#m227\">index</a>;.;</span>]</span></span>=<span style=\"background-color:green;\"><a href=\"#m236\">elt</a>;.;</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$iter</span>($int <a name=\"m389\">i</a>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m227\">index</a>;.;</span>+<span style=\"background-color:green;\">1i</span></span>:<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>+<span style=\"background-color:green;\">1i</span></span>:<span style=\"background-color:green;\">1i</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m249\">newlist</a>;.</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><a href=\"#m389\">i</a>;</span>]</span></span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m389\">i</a>;</span>-<span style=\"background-color:red;\">1i</span></span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>++</span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span>=<span style=\"background-color:green;\"><a href=\"#m249\">newlist</a>;.</span></span>:\n" +
                " }\n" +
                " $public $normal $int <a name=\"m519\">size</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>:\n" +
                " }\n" +
                " $public $normal #U <a name=\"m571\">get</a>($int <a name=\"m580\">index</a>){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:green;\">[<span style=\"background-color:green;\"><a href=\"#m580\">index</a>;.;</span>]</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m643\">set</a>($int <a name=\"m652\">index</a>,#U <a name=\"m661\">elt</a>){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><a href=\"#m652\">index</a>;.;</span>]</span></span>=<span style=\"background-color:red;\"><a href=\"#m661\">elt</a>;.;</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m721\">remove</a>($int <a name=\"m733\">index</a>){\n" +
                "  <span style=\"background-color:red;\">$iter</span>($int <a name=\"m754\">i</a>=<span style=\"background-color:red;\"><a href=\"#m733\">index</a>;.;</span>:<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>-<span style=\"background-color:red;\">1i</span></span>:<span style=\"background-color:red;\">1i</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><a href=\"#m754\">i</a>;</span>]</span></span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m754\">i</a>;</span>+<span style=\"background-color:red;\">1i</span></span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span style=\"background-color:red;\">[<span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>-<span style=\"background-color:red;\">1i</span></span>]</span></span>=<span style=\"background-color:red;\">$null</span></span>:\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>--</span>:\n" +
                " }\n" +
                " $public $normal $iterator&lt;#U&gt; <a name=\"m898\">iterator</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustIter.pkg.CustIter(pkg.CustList&lt;#T&gt;)\" href=\"CustIter.html#m127\">$new</a> pkg.CustIter&lt;#U&gt;(<span style=\"background-color:green;\">$this</span>)</span>:\n" +
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
                " $private CustTable&lt;U,V&gt; <span style=\"background-color:green;\"><a name=\"m85\">list</a></span>:\n" +
                " $private $int <span style=\"background-color:green;\"><a name=\"m106\">length</a></span>:\n" +
                " $private $int <span style=\"background-color:green;\"><a name=\"m129\">index</a></span>:\n" +
                " <a name=\"m137\">$public CustIterTable(</a>CustTable&lt;U,V&gt; <a name=\"m174\">i</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.list\" href=\"#m85\">list</a>;;;</span>=<span style=\"background-color:green;\"><a href=\"#m174\">i</a>;.;</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.length\" href=\"#m106\">length</a>;;;</span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.list\" href=\"#m85\">list</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustTable.size()\" href=\"CustTable.html#m298\">size</a>()</span></span></span>:\n" +
                " }\n" +
                " $public CustPair&lt;U,V&gt; <a name=\"m247\">nextPair</a>(){\n" +
                "  CustPair&lt;U,V&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m275\">out</a></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.list\" href=\"#m85\">list</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustTable.get($int)\" href=\"CustTable.html#m355\">get</a>(<span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.index\" href=\"#m129\">index</a>;;;</span>)</span></span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.index\" href=\"#m129\">index</a>;;;</span>++</span>:\n" +
                "  $return <span style=\"background-color:green;\"><a href=\"#m275\">out</a>;.</span>:\n" +
                " }\n" +
                " $public $boolean <a name=\"m353\">hasNextPair</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.index\" href=\"#m129\">index</a>;;;</span>&lt;<span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.length\" href=\"#m106\">length</a>;;;</span></span>:\n" +
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
                " $private CustList&lt;CustPair&lt;U,V&gt;&gt; <span style=\"background-color:green;\"><a name=\"m90\">list</a></span>:\n" +
                " <a name=\"m97\">$public (</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> CustList&lt;CustPair&lt;U,V&gt;&gt;()</span></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m166\">add</a>(U <a name=\"m172\">f</a>,V <a name=\"m176\">s</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\"><a title=\"pkg.CustPair.pkg.CustPair(#U,#V)\" href=\"CustPair.html#m110\">$new</a> CustPair&lt;U,V&gt;(<span style=\"background-color:green;\"><a href=\"#m172\">f</a>;.;</span>,<span style=\"background-color:green;\"><a href=\"#m176\">s</a>;.;</span>)</span>)</span></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m241\">add</a>(CustPair&lt;U,V&gt; <a name=\"m259\">p</a>){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span style=\"background-color:red;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:red;\"><a href=\"#m259\">p</a>;.;</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m298\">size</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustList.size()\" href=\"CustList.html#m519\">size</a>()</span></span>:\n" +
                " }\n" +
                " $public CustPair&lt;U,V&gt; <a name=\"m355\">get</a>($int <a name=\"m364\">index</a>){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span style=\"background-color:green;\"><a title=\"pkg.CustList.get($int)\" href=\"CustList.html#m571\">get</a>(<span style=\"background-color:green;\"><a href=\"#m364\">index</a>;.;</span>)</span></span>:\n" +
                " }\n" +
                " $public $iteratorTable&lt;U,V&gt; <a name=\"m434\">iteratorTable</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustIterTable.pkg.CustIterTable(pkg.CustTable&lt;#U,#V&gt;)\" href=\"CustIterTable.html#m137\">$new</a> CustIterTable&lt;U,V&gt;(<span style=\"background-color:green;\">$this</span>)</span>:\n" +
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
                " $private U <span style=\"background-color:green;\"><a name=\"m58\">first</a></span>:\n" +
                " $private V <span style=\"background-color:green;\"><a name=\"m77\">second</a></span>:\n" +
                " <a name=\"m86\">$public CustPair(</a>){\n" +
                " }\n" +
                " <a name=\"m110\">$public CustPair(</a>U <a name=\"m129\">f</a>,V <a name=\"m133\">s</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustPair.first\" href=\"#m58\">first</a> </span>=<span style=\"background-color:green;\"> <a href=\"#m129\">f</a>;.;</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.CustPair.second\" href=\"#m77\">second</a> </span>=<span style=\"background-color:green;\"> <a href=\"#m133\">s</a>;.;</span></span>:\n" +
                " }\n" +
                " $public U <a name=\"m184\">getFirst</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustPair.first\" href=\"#m58\">first</a></span>:\n" +
                " }\n" +
                " $public V <a name=\"m227\">getSecond</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.CustPair.second\" href=\"#m77\">second</a></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m276\">setFirst</a>(U <a name=\"m287\">f</a>){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.CustPair.first\" href=\"#m58\">first</a> </span>=<span style=\"background-color:red;\"> <a href=\"#m287\">f</a>;.;</span></span>:\n" +
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">&lt;</a> $boolean (pkg.Ex <a name=\"m28\">p</a>, pkg.Ex <a name=\"m38\">q</a>){\n" +
                " $return <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m28\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span><a title=\"&quot;\">&lt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m38\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span>:\n" +
                "}\n" +
                "$operator<a name=\"m83\">&gt;</a> $boolean (pkg.Ex <a name=\"m102\">p</a>, pkg.Ex <a name=\"m112\">q</a>){\n" +
                " $return <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m102\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span><a title=\"&amp;\">&gt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m112\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m186\">field</a></span>:\n" +
                " $public $static $int <a name=\"m215\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m234\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m264\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m234\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m264\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m332\">rOne</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m234\">one</a>;. </span><a title=\"$static &lt;(pkg.Ex,pkg.Ex)\" href=\"#m9\">&lt;</a><span style=\"background-color:green;\"> <a href=\"#m264\">two</a>;.</span></span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m365\">rTwo</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m234\">one</a>;. </span><a title=\"$static &gt;(pkg.Ex,pkg.Ex)\" href=\"#m83\">&gt;</a><span style=\"background-color:green;\"> <a href=\"#m264\">two</a>;.</span></span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m398\">rThree</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m234\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span><a title=\"&quot;\">&lt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m264\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span></span>:\n" +
                "  $boolean <span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a name=\"m443\">rFour</a> </span>=<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m234\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span><a title=\"&amp;\">&gt;</a><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m264\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span></span>:\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:green;\">iter</span> (int <a name=\"m91\">j</a>=<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">2</span>;<span style=\"background-color:green;\">1</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m110\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:green;\"> <a href=\"#m91\">j</a> </span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m110\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">iter</span> (int <a name=\"m91\">j</a>=<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">2</span>;<span style=\"background-color:green;\">1</span>){\n" +
                "   int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m110\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> <a href=\"#m91\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m110\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span></span>;\n" +
                "   break;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">iter</span> (int <a name=\"m91\">j</a>=<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">0</span>;<span style=\"background-color:green;\">1</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m110\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m91\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m110\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m70\">sum</a></span>;\n" +
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m70\">sum</a> </span>=<span style=\"background-color:green;\"> 0</span></span>;\n" +
                "  <span style=\"background-color:yellow;\">if</span> (<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m70\">sum</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 0</span></span>){\n" +
                "   return <span style=\"background-color:green;\"><a href=\"#m57\">s</a></span>;\n" +
                "  }\n" +
                "  <span style=\"background-color:red;\">iter</span> (int <a name=\"m125\">j</a>=<span style=\"background-color:red;\">0</span>;<span style=\"background-color:red;\">0</span>;<span style=\"background-color:red;\">1</span>){\n" +
                "   int[] <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a name=\"m144\">t</a> </span>=<span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m57\">s</a> </span>==<span style=\"background-color:red;\"> <a href=\"#m125\">j</a> </span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m70\">sum</a> </span>+=<span style=\"background-color:red;\"><span style=\"background-color:red;\"> <a href=\"#m144\">t</a></span><span style=\"background-color:red;\">[<span style=\"background-color:red;\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:red;\"><a href=\"#m70\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static StringBuilder exmeth(){\n");
        xml_.append("  $return $new StringBuilder(\"hel\\\"lo\"):\n");
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
                "  $return <span style=\"background-color:green;\">$new StringBuilder(<span style=\"background-color:green;\"><span style=\"color:blue;\">\"hel\\\"lo\"</span></span>)</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static StringBuilder exmeth(){\n");
        xml_.append("  $return $new StringBuilder(`hel``lo`):\n");
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
                "  $return <span style=\"background-color:green;\">$new StringBuilder(<span style=\"background-color:green;\"><span style=\"color:blue;\">`hel``lo`</span></span>)</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $char exmeth(){\n");
        xml_.append("  $return '\\'':\n");
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
                " $public $static $char <a name=\"m47\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"color:blue;\">'\\''</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field+q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $return (one;.+two;.).$super.field:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field:\n");
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex (pkg.Ex <a name=\"m26\">p</a>, pkg.Ex <a name=\"m36\">q</a>){\n" +
                " pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m48\">out</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a> </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m26\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a></span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m36\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex:ExTwo {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m193\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m223\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span style=\"background-color:green;\">$super.<a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a></span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m343\">call</a>(){\n" +
                "  $return <span style=\"background-color:red;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class pkg.ExTwo {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m411\">field</a></span>:\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field+q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $return (one;.+two;.).$super.method():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $int method(){\n");
        xml_.append("  $return field:\n");
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex (pkg.Ex <a name=\"m26\">p</a>, pkg.Ex <a name=\"m36\">q</a>){\n" +
                " pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m48\">out</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a> </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m26\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a></span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m36\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex:ExTwo {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m193\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m223\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span style=\"background-color:green;\">$super.<a title=\"pkg.ExTwo.method()\" href=\"#m435\">method</a>()</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m346\">call</a>(){\n" +
                "  $return <span style=\"background-color:red;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class pkg.ExTwo {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m414\">field</a></span>:\n" +
                " $public $int <a name=\"m435\">method</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int one,two:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.one = 1:\n");
        xml_.append("  one;.two = 2:\n");
        xml_.append("  two;.one = 3:\n");
        xml_.append("  two;.two = 4:\n");
        xml_.append("  $return 5:\n");
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
                " $public $int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m38\">one</a></span>,<span style=\"background-color:green;\"><a name=\"m42\">two</a></span></span>:\n" +
                " $public $static $int <a name=\"m69\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m88\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m118\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m88\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m88\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m118\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span style=\"background-color:green;\"> 3</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m118\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span style=\"background-color:green;\"> 4</span></span>:\n" +
                "  $return <span style=\"background-color:green;\">5</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int one=7,two=8:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.one = 1:\n");
        xml_.append("  one;.two = 2:\n");
        xml_.append("  two;.one = 3:\n");
        xml_.append("  two;.two = 4:\n");
        xml_.append("  $return 5:\n");
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
                " $public $int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m38\">one</a></span>=<span style=\"background-color:green;\">7</span></span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m44\">two</a></span>=<span style=\"background-color:green;\">8</span></span></span>:\n" +
                " $public $static $int <a name=\"m73\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m92\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m122\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m92\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m92\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.two\" href=\"#m44\">two</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m122\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span style=\"background-color:green;\"> 3</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m122\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.two\" href=\"#m44\">two</a> </span></span>=<span style=\"background-color:green;\"> 4</span></span>:\n" +
                "  $return <span style=\"background-color:green;\">5</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE,TWO:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExEnum.ONE.$ordinal()+ExEnum.TWO.$ordinal():\n");
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
        assertEq("<html><body><pre>$public $enum pkg.ExEnum {\n" +
                " <a name=\"m28\">ONE</a>,<a name=\"m32\">TWO</a>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m85\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">ExEnum</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">ONE</a></span></span>.<span style=\"background-color:green;\">$ordinal()</span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">ExEnum</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExEnum.TWO\" href=\"#m32\">TWO</a></span></span>.<span style=\"background-color:green;\">$ordinal()</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $iterable<java.lang.Number> iter = inst:\n");
        xml_.append("  $var it = iter;.iterator():\n");
        xml_.append("  $while(it;.hasNext()){\n");
        xml_.append("   $var l = it;.next():\n");
        xml_.append("   res;;;+=l;.intValue():\n");
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
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">3i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">1i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">2i</span>)</span></span>:\n" +
                "  $iterable&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m238\">iter</a> </span>=<span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\"> ins</a>t</span></span>:\n" +
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m258\">it</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m238\">iter</a>;.</span><span style=\"background-color:green;\">iterator()</span></span></span>:\n" +
                "  <span style=\"background-color:green;\">$while</span>(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m258\">it</a>;.</span><span style=\"background-color:green;\">hasNext()</span></span>){\n" +
                "   $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m314\">l</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m258\">it</a>;.</span><span style=\"background-color:green;\">next()</span></span></span>:\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m314\">l</a>;.</span><span style=\"background-color:green;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m385\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $var iter = inst:\n");
        xml_.append("  $var it = iter;.iterator():\n");
        xml_.append("  $while(it;.hasNext()){\n");
        xml_.append("   $var l = it;.next():\n");
        xml_.append("   res;;;+=l;.intValue():\n");
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
                " $public $static pkg.CustList&lt;java.lang.Number&gt; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m72\">inst</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> pkg.CustList&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">3i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">1i</span>)</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span style=\"background-color:green;\">2i</span>)</span></span>:\n" +
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m215\">iter</a> </span>=<span style=\"background-color:green;\"><a title=\"pkg.Ex.inst\" href=\"#m72\"> ins</a>t</span></span>:\n" +
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m235\">it</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m215\">iter</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.CustList.iterator()\" href=\"CustList.html#m898\">iterator</a>()</span></span></span>:\n" +
                "  <span style=\"background-color:green;\">$while</span>(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m235\">it</a>;.</span><span style=\"background-color:green;\">hasNext()</span></span>){\n" +
                "   $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m291\">l</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m235\">it</a>;.</span><span style=\"background-color:green;\">next()</span></span></span>:\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m291\">l</a>;.</span><span style=\"background-color:green;\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m362\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static java.lang.Integer.MAX_VALUE;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return MAX_VALUE:\n");
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
        assertEq("<html><body><pre>$static java.lang.Integer.MAX_VALUE;\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m83\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\">MAX_VALUE</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $lambda(Ex,,field).call():\n");
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
                " $public $static $int <span style=\"background-color:green;\"><a name=\"m46\">field</a></span>:\n" +
                " $public $static $int <a name=\"m75\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m46\">$lambda</a>(Ex,,field)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExEnum exmeth(){\n");
        xml_.append("  $return $lambda(ExEnum,,ONE).call():\n");
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
        assertEq("<html><body><pre>$public $enum pkg.ExEnum {\n" +
                " <a name=\"m28\">ONE</a>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static ExEnum <a name=\"m83\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">$lambda</a>(ExEnum,,ONE)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE{}:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExEnum exmeth(){\n");
        xml_.append("  $return $lambda(ExEnum,,ONE).call():\n");
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
        assertEq("<html><body><pre>$public $enum pkg.ExEnum {\n" +
                " <a name=\"m28\">ONE</a>{}:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static ExEnum <a name=\"m85\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">$lambda</a>(ExEnum,,ONE)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $lambda(Integer,,MAX_VALUE).call():\n");
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
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">$lambda(Integer,,MAX_VALUE)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static StringBuilder exmeth(){\n");
        xml_.append("  $return $lambda(StringBuilder,$new).call():\n");
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
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">$lambda(StringBuilder,$new)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex exmeth(){\n");
        xml_.append("  $return $lambda(Ex,$new).call():\n");
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
                " $public $static Ex <a name=\"m44\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">$lambda(Ex,$new)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex exmeth(){\n");
        xml_.append("  $return $lambda(Ex,$new).call():\n");
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
                " $public $static Ex <a name=\"m62\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"#m25\">$lambda</a>(Ex,$new)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public Ex($int field){\n");
        xml_.append("  $this.field=field;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex exmeth(){\n");
        xml_.append("  $return $lambda(Ex,$new,$int).call(5):\n");
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
                " $public $int <span style=\"background-color:green;\"><a name=\"m38\">field</a></span>:\n" +
                " <a name=\"m46\">$public Ex(</a>$int <a name=\"m62\">field</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$this</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a></span></span>=<span style=\"background-color:green;\"><a href=\"#m62\">field</a>;.;</span></span>:\n" +
                " }\n" +
                " $public $static Ex <a name=\"m117\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.pkg.Ex($int)\" href=\"#m46\">$lambda</a>(Ex,$new,$int)</span>.<span style=\"background-color:green;\"><b>call</b>(<span style=\"background-color:green;\">5</span>)</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] exmeth(){\n");
        xml_.append("  $return $lambda(Ex[],$new,$int).call(5):\n");
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
                " $public $static Ex[] <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">$lambda(Ex[],$new,$int)</span>.<span style=\"background-color:green;\"><b>call</b>(<span style=\"background-color:green;\">5</span>)</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return \"\".$lambda(String,length).call():\n");
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
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"color:blue;\">\"\"</span></span>.<span style=\"background-color:green;\">$lambda(String,length)</span></span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex().$lambda(Ex,inst).call():\n");
        xml_.append(" }\n");
        xml_.append(" $public $int inst(){\n");
        xml_.append("  $return \"\".length():\n");
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
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$new Ex()</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.inst()\" href=\"#m118\">$lambda</a>(Ex,inst)</span></span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m118\">inst</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"color:blue;\">\"\"</span></span>.<span style=\"background-color:green;\">length()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex().$lambda(Ex,[],$int).call(8):\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int param){\n");
        xml_.append("  $return field:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int param){\n");
        xml_.append("  field = $value;.;:\n");
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
                " $public $int <span style=\"background-color:green;\"><a name=\"m38\">field</a></span>:\n" +
                " $public $static $int <a name=\"m67\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$new Ex()</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.[]($int)\" href=\"#m143\">$lambda</a>(Ex,[],$int)</span></span>.<span style=\"background-color:green;\"><b>call</b>(<span style=\"background-color:green;\">8</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m143\">$this</a>($int <a name=\"m154\">param</a>){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m197\">$this</a>($int <a name=\"m208\">param</a>){\n" +
                "  <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a> </span>=<span style=\"background-color:red;\"> <b>$value</b>;.;</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $new Ex().$lambda(Ex,[]=,$int).call(8,15):\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int param){\n");
        xml_.append("  $return field:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int param){\n");
        xml_.append("  field = $value;.;:\n");
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
                " $public $int <span style=\"background-color:green;\"><a name=\"m38\">field</a></span>:\n" +
                " $public $static Object <a name=\"m69\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\">$new Ex()</span>.<span style=\"background-color:green;\"><a title=\"pkg.Ex.[]=($int)\" href=\"#m203\">$lambda</a>(Ex,[]=,$int)</span></span>.<span style=\"background-color:green;\"><b>call</b>(<span style=\"background-color:green;\">8</span>,<span style=\"background-color:green;\">15</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m149\">$this</a>($int <a name=\"m160\">param</a>){\n" +
                "  $return <span style=\"background-color:red;\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m203\">$this</a>($int <a name=\"m214\">param</a>){\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a> </span>=<span style=\"background-color:green;\"> <b>$value</b>;.;</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b) {\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.field = a;.;field + b;.;field:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  Ex one = $new Ex():\n");
        xml_.append("  Ex two = $new Ex():\n");
        xml_.append("  one;.field = 15:\n");
        xml_.append("  two;.field = 14:\n");
        xml_.append("  $return $lambda($operator,+,Ex,Ex).call(one;.,two;.):\n");
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex(pkg.Ex <a name=\"m25\">a</a>,pkg.Ex <a name=\"m34\">b</a>) {\n" +
                " $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m45\">o</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m45\">o</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m25\">a</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m34\">b</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\"><a href=\"#m45\">o</a>;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m153\">field</a></span>:\n" +
                " $public $static Object <a name=\"m184\">exmeth</a>(){\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m199\">one</a> </span>=<span style=\"background-color:green;\"> $new Ex()</span></span>:\n" +
                "  Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m221\">two</a> </span>=<span style=\"background-color:green;\"> $new Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m199\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>=<span style=\"background-color:green;\"> 15</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m221\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>=<span style=\"background-color:green;\"> 14</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">$lambda</a>($operator,+,Ex,Ex)</span>.<span style=\"background-color:green;\"><b>call</b>(<span style=\"background-color:green;\"><a href=\"#m199\">one</a>;.</span>,<span style=\"background-color:green;\"><a href=\"#m221\">two</a>;.</span>)</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE{}:\n");
        xml_.append(" $public $int method(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $lambda(ExEnum,,ONE).call().method():\n");
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
        assertEq("<html><body><pre>$public $enum pkg.ExEnum {\n" +
                " <a name=\"m28\">ONE</a>{}:\n" +
                " $public $int <a name=\"m49\">method</a>(){\n" +
                "  $return <span style=\"background-color:green;\">5</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m123\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">$lambda</a>(ExEnum,,ONE)</span>.<span style=\"background-color:green;\"><b>call</b>()</span></span>.<span style=\"background-color:green;\"><a title=\"pkg.ExEnum.method()\" href=\"#m49\">method</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int one,two=one:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.one = 1:\n");
        xml_.append("  one;.two = 2:\n");
        xml_.append("  two;.one = 3:\n");
        xml_.append("  two;.two = 4:\n");
        xml_.append("  $return 5:\n");
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
                " $public $int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m38\">one</a></span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m42\">two</a></span>=<span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a></span></span></span>:\n" +
                " $public $static $int <a name=\"m73\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m92\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m122\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m92\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m92\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m122\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span style=\"background-color:green;\"> 3</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m122\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span style=\"background-color:green;\"> 4</span></span>:\n" +
                "  $return <span style=\"background-color:green;\">5</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field+q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $return (one;.+two;.).$superaccess(ExTwo)method():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $int method(){\n");
        xml_.append("  $return field:\n");
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex (pkg.Ex <a name=\"m26\">p</a>, pkg.Ex <a name=\"m36\">q</a>){\n" +
                " pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m48\">out</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m26\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m36\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex:ExTwo {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m193\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m223\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span style=\"background-color:green;\">$superaccess(ExTwo)<a title=\"pkg.ExTwo.method()\" href=\"#m447\">method</a>()</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m358\">call</a>(){\n" +
                "  $return <span style=\"background-color:red;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class pkg.ExTwo {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m426\">field</a></span>:\n" +
                " $public $int <a name=\"m447\">method</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field+q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $return (one;.+two;.).$classchoice(ExTwo)method():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $int method(){\n");
        xml_.append("  $return field:\n");
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
        assertEq("<html><body><pre>$operator<a name=\"m9\">+</a> pkg.Ex (pkg.Ex <a name=\"m26\">p</a>, pkg.Ex <a name=\"m36\">q</a>){\n" +
                " pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m48\">out</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                " <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m26\">p</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span>+<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m36\">q</a>;.;</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span></span></span>:\n" +
                " $return <span style=\"background-color:green;\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class pkg.Ex:ExTwo {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m193\">one</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  pkg.Ex <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m223\">two</a> </span>=<span style=\"background-color:green;\"> $new pkg.Ex()</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span><span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span style=\"background-color:green;\"> 2</span></span>:\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">(<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span style=\"background-color:green;\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span style=\"background-color:green;\">$classchoice(ExTwo)<a title=\"pkg.ExTwo.method()\" href=\"#m447\">method</a>()</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m358\">call</a>(){\n" +
                "  $return <span style=\"background-color:red;\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class pkg.ExTwo {\n" +
                " $public $int <span style=\"background-color:green;\"><a name=\"m426\">field</a></span>:\n" +
                " $public $int <a name=\"m447\">method</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $char exmeth(){\n");
        xml_.append("  $return '\\'':\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String exmeth2(){\n");
        xml_.append("  $return \"\"+'\\'':\n");
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
                " $public $static $char <a name=\"m47\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"color:blue;\">'\\''</span></span>:\n" +
                " }\n" +
                " $public $static String <a name=\"m100\">exmeth2</a>(){\n" +
                "  $return <span style=\"background-color:red;\"><span style=\"background-color:red;\"><span style=\"color:blue;\">\"\"</span></span>+<span style=\"background-color:red;\"><span style=\"color:blue;\">'\\''</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $var c = $true:\n");
        xml_.append("  b;. &= c;.:\n");
        xml_.append("  $return 0:\n");
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
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">b</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m81\">c</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  <span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"><a href=\"#m63\">b</a>;. </span><a title=\"true\">&amp;</a>=<span style=\"background-color:yellow;\"> <a href=\"#m81\">c</a>;.</span></span>:\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $var c = $false:\n");
        xml_.append("  b;. &= c;.:\n");
        xml_.append("  $return 0:\n");
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
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">b</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m81\">c</a> </span>=<span style=\"background-color:green;\"> $false</span></span>:\n" +
                "  <span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"><a href=\"#m63\">b</a>;. </span><a title=\"false\">&amp;</a>=<span style=\"background-color:yellow;\"> <a href=\"#m81\">c</a>;.</span></span>:\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $for ($boolean c:{$true,$false}){\n");
        xml_.append("   b;. &= c;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0:\n");
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
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">b</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  <span style=\"background-color:green;\">$for ($boolean <a name=\"m91\">c</a></span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">$true</span>,<span style=\"background-color:green;\">$false</span>}</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><a href=\"#m63\">b</a>;. </span>&amp;=<span style=\"background-color:green;\"> <a href=\"#m91\">c</a>;</span></span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $for ($boolean c:{}){\n");
        xml_.append("   b;. &= c;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0:\n");
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
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">b</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  <span style=\"background-color:yellow;\">$for ($boolean <a name=\"m91\">c</a></span>:<span style=\"background-color:green;\">{}</span>){\n" +
                "   <span style=\"background-color:red;\"><span style=\"background-color:red;\"><a href=\"#m63\">b</a>;. </span>&amp;=<span style=\"background-color:red;\"> <a href=\"#m91\">c</a>;</span></span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  b;. &= $true:\n");
        xml_.append("  b;. &= $false:\n");
        xml_.append("  $return 0:\n");
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
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">b</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  <span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"><a href=\"#m63\">b</a>;. </span>&amp;=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  <span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"><a href=\"#m63\">b</a>;. </span>&amp;=<span style=\"background-color:green;\"> $false</span></span>:\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $for ($boolean b:{$true,$false}){\n");
        xml_.append("   $for ($boolean c:{$false,$true}){\n");
        xml_.append("    $var loc = b;:\n");
        xml_.append("    loc;. &= c;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 0:\n");
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
                "  <span style=\"background-color:green;\">$for ($boolean <a name=\"m73\">b</a></span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">$true</span>,<span style=\"background-color:green;\">$false</span>}</span>){\n" +
                "   <span style=\"background-color:green;\">$for ($boolean <a name=\"m110\">c</a></span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">$false</span>,<span style=\"background-color:green;\">$true</span>}</span>){\n" +
                "    $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m138\">loc</a> </span>=<span style=\"background-color:green;\"> <a href=\"#m73\">b</a>;</span></span>:\n" +
                "    <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m138\">loc</a>;. </span>&amp;=<span style=\"background-color:green;\"> <a href=\"#m110\">c</a>;</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $for ($boolean c:{$true,$false,$true}){\n");
        xml_.append("   b;. &= c;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0:\n");
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
                "  $var <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">b</a> </span>=<span style=\"background-color:green;\"> $true</span></span>:\n" +
                "  <span style=\"background-color:green;\">$for ($boolean <a name=\"m91\">c</a></span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">$true</span>,<span style=\"background-color:green;\">$false</span>,<span style=\"background-color:green;\">$true</span>}</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m63\">b</a>;. </span>&amp;=<span style=\"background-color:green;\"> <a href=\"#m91\">c</a>;</span></span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1, u = 2;\n");
        xml_.append("  int[] t = s == 1 && u == 2?{4i}:{6i};\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a name=\"m64\">u</a> </span>=<span style=\"background-color:green;\"> 2</span></span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m79\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m64\">u</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 2</span></span></span>?<span style=\"background-color:green;\">{<span style=\"background-color:green;\">4i</span>}</span>:<span style=\"background-color:red;\">{<span style=\"background-color:red;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m79\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  for (int i = 0, j = 1; i < 2 && j < 4; i++,j++){\n");
        xml_.append("   s += i + j;\n");
        xml_.append("  }\n");
        xml_.append("  return s;\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m75\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a name=\"m82\">j</a> </span>=<span style=\"background-color:green;\"> 1</span></span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m75\">i</a> </span>&lt;<span style=\"background-color:green;\"> 2 </span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m82\">j</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 4</span></span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m75\">i</a></span>++</span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m82\">j</a></span>++</span></span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m75\">i</a> </span>+<span style=\"background-color:green;\"> <a href=\"#m82\">j</a></span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m57\">s</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  for (int i = 0, j = i; i < 2 && j < 4; i++,j++){\n");
        xml_.append("   s += i + j;\n");
        xml_.append("  }\n");
        xml_.append("  return s;\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m75\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a name=\"m82\">j</a> </span>=<span style=\"background-color:green;\"> <a href=\"#m75\">i</a></span></span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m75\">i</a> </span>&lt;<span style=\"background-color:green;\"> 2 </span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m82\">j</a> </span><a title=\"true\">&lt;</a><span style=\"background-color:green;\"> 4</span></span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m75\">i</a></span>++</span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m82\">j</a></span>++</span></span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span>+=<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a href=\"#m75\">i</a> </span>+<span style=\"background-color:green;\"> <a href=\"#m82\">j</a></span></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m57\">s</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1, u = s;\n");
        xml_.append("  int[] t = s == 1 && u == 2?{4i}:{6i};\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>,<span style=\"background-color:green;\"><span style=\"background-color:green;\"> <a name=\"m64\">u</a> </span>=<span style=\"background-color:green;\"> <a href=\"#m57\">s</a></span></span></span>;\n" +
                "  int[] <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m79\">t</a> </span>=<span style=\"background-color:green;\"><span style=\"background-color:yellow;\"><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span style=\"background-color:green;\"> 1 </span></span><a title=\"true\">&amp;</a><a title=\"false\">&amp;</a><span style=\"background-color:yellow;\"><span style=\"background-color:green;\"> <a href=\"#m64\">u</a> </span><a title=\"false\">==</a><span style=\"background-color:green;\"> 2</span></span></span>?<span style=\"background-color:red;\">{<span style=\"background-color:red;\">4i</span>}</span>:<span style=\"background-color:green;\">{<span style=\"background-color:green;\">6i</span>}</span></span></span>;\n" +
                "  return <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m79\">t</a></span><span style=\"background-color:green;\">[<span style=\"background-color:green;\">0</span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  for (int i = 0, j; i < 2; i++){\n");
        xml_.append("   s += i;\n");
        xml_.append("  }\n");
        xml_.append("  return s;\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\">for</span> (int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m75\">i</a> </span>=<span style=\"background-color:green;\"> 0</span></span>,<span style=\"background-color:green;\"> <a name=\"m82\">j</a></span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m75\">i</a> </span>&lt;<span style=\"background-color:green;\"> 2</span></span>; <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m75\">i</a></span>++</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span>+=<span style=\"background-color:green;\"> <a href=\"#m75\">i</a></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m57\">s</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  for (int i : {1,2}){\n");
        xml_.append("   s += ([i]);\n");
        xml_.append("  }\n");
        xml_.append("  return s;\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\">for (int <a name=\"m75\">i</a></span> : <span style=\"background-color:green;\">{<span style=\"background-color:green;\">1</span>,<span style=\"background-color:green;\">2</span>}</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span>+=<span style=\"background-color:green;\"> ([<a href=\"#m75\">i</a>])</span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m57\">s</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int s = 1:\n");
        xml_.append("  $for ($int i : {1,2}){\n");
        xml_.append("   s;. += i;;:\n");
        xml_.append("  }\n");
        xml_.append("  $return s;.:\n");
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
                "  $int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m63\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>:\n" +
                "  <span style=\"background-color:green;\">$for ($int <a name=\"m83\">i</a></span> : <span style=\"background-color:green;\">{<span style=\"background-color:green;\">1</span>,<span style=\"background-color:green;\">2</span>}</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m63\">s</a>;. </span>+=<span style=\"background-color:green;\"> <a href=\"#m83\">i</a>;;</span></span>:\n" +
                "  }\n" +
                "  $return <span style=\"background-color:green;\"><a href=\"#m63\">s</a>;.</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  for (int i : {1,2}){\n");
        xml_.append("   s += i;\n");
        xml_.append("  }\n");
        xml_.append("  return s;\n");
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
                "  int <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a name=\"m57\">s</a> </span>=<span style=\"background-color:green;\"> 1</span></span>;\n" +
                "  <span style=\"background-color:green;\">for (int <a name=\"m75\">i</a></span> : <span style=\"background-color:green;\">{<span style=\"background-color:green;\">1</span>,<span style=\"background-color:green;\">2</span>}</span>){\n" +
                "   <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m57\">s</a> </span>+=<span style=\"background-color:green;\"> <a href=\"#m75\">i</a></span></span>;\n" +
                "  }\n" +
                "  return <span style=\"background-color:green;\"><a href=\"#m57\">s</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage123Test() {
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
        xml_.append("  } $catch($int o){\n");
        xml_.append("   $return o;..:\n");
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
                "  $int <span style=\"background-color:green;\"><a name=\"m65\">t</a></span>:\n" +
                "  <span style=\"background-color:green;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span>=<span style=\"background-color:green;\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span style=\"background-color:yellow;\">$if</span>(<span style=\"background-color:yellow;\"><span style=\"background-color:green;\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span style=\"background-color:green;\">0</span></span>){\n" +
                "    $throw <span style=\"background-color:green;\">1i</span>:\n" +
                "   }\n" +
                "   $return <span style=\"background-color:red;\">2</span>:\n" +
                "  } <span style=\"background-color:green;\">$catch</span>($int <a name=\"m152\">o</a>){\n" +
                "   $return <span style=\"background-color:green;\"><a href=\"#m152\">o</a>;..</span>:\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage124Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg2/Ex", xml_.toString());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">ExTwo</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../pkg2/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage125Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/Ex", xml_.toString());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">ExTwo</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../Ex.html#m49\">exmeth</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage126Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/inner/Ex", xml_.toString());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">ExTwo</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"inner/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage127Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/com/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/com/pkg2/Ex", xml_.toString());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">ExTwo</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../pkg2/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage128Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ExTwo.exmeth():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/d1/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/d2/pkg2/Ex", xml_.toString());
        ContextEl cont_ = contextElCoverage();
        ContextFactory.validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span style=\"background-color:green;\"><span style=\"background-color:green;\">ExTwo</span>.<span style=\"background-color:green;\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../../d2/pkg2/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
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
