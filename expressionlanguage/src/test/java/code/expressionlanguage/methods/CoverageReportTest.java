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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m64\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">8</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m72\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1 </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m72\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m72\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 2 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m72\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m90\">j</a></span>:<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m109\">t</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m57\">s</a> </span>==<span class=\"f\"> <a href=\"#m90\">j</a> </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m109\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">for (int <a name=\"m90\">j</a></span>:<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m109\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> <a href=\"#m90\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m109\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   break;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">for (int <a name=\"m90\">j</a></span>:<span class=\"f\">{}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m106\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m57\">s</a> </span>==<span class=\"n\"> <a href=\"#m90\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m70\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m106\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">while</span> (<span class=\"f\">true</span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m104\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m104\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m70\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m124\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m143\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m57\">s</a> </span>==<span class=\"n\"> <a href=\"#m124\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m70\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m143\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>\n" +
                "$public $class <a name=\"m60\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m91\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m119\">arr</a> </span>=<span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m60\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span></span>:\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m119\">arr</a>;.</span><span class=\"f\">length </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>:\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m119\">arr</a>;.</span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>:\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m119\">arr</a>;.</span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">infoInt</a>()1i:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(2i)\n" +
                "$public $class <a name=\"m83\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m114\">catching</a>(){\n" +
                "  java.lang.Object <span class=\"f\"><span class=\"f\"><a name=\"m145\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>.<span class=\"f\">getDefaultValue()</span></span></span>:\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m145\">arr</a>;.</span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(java.lang.Integer)</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>:\n" +
                "  }\n" +
                "  java.lang.Integer <span class=\"f\"><span class=\"f\"><a name=\"m320\">a</a> </span>=<span class=\"f\"> $(java.lang.Integer)<span class=\"f\"><a href=\"#m145\">arr</a>;.</span></span></span>:\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m320\">a</a>;. </span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <span class=\"g\"><span class=\"g\"><a name=\"m46\">st</a> </span>=<span class=\"g\"> 0</span></span>:\n" +
                " $public $static $int <a name=\"m76\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m94\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m94\">t</a>;.</span>=<span class=\"f\">8</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m94\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">0i</span></span>:\n" +
                "  <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"false\">&lt;</a><span class=\"f\">0</span></span>){\n" +
                "   $return <span class=\"n\"><span class=\"n\">1i</span>/<span class=\"n\">0i</span></span>:\n" +
                "  }\n" +
                "  $else{\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">1i</span></span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">0i</span></span>:\n" +
                "  <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">1i</span></span>:\n" +
                "   $return <span class=\"f\"><a href=\"#m65\">t</a>;.</span>:\n" +
                "  }\n" +
                "  $else{\n" +
                "   $return <span class=\"n\"><span class=\"n\">1i</span>/<span class=\"n\">0i</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m64\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">10</span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span class=\"f\">10</span>):\n" +
                "   $case(<span class=\"n\">8</span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m64\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">8</span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span class=\"n\">10</span>):\n" +
                "   $case(<span class=\"f\">8</span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m64\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">9</span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span class=\"n\">10</span>):\n" +
                "   $case(<span class=\"n\">8</span>){\n" +
                "    <span class=\"n\"><span class=\"n\"><a href=\"#m64\">t</a>;.</span>=<span class=\"n\">16</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m64\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">9</span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span class=\"n\">10</span>):\n" +
                "   $case(<span class=\"n\">8</span>){\n" +
                "    <span class=\"n\"><span class=\"n\"><a href=\"#m64\">t</a>;.</span>=<span class=\"n\">16</span></span>:\n" +
                "   }\n" +
                "   <span class=\"f\">$default</span>{\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $long <span class=\"f\"><a name=\"m64\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">8</span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m64\">t</a>;.</span>){\n" +
                "   $case(<span class=\"n\">10</span>):\n" +
                "   $case(<span class=\"f\">8</span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">16</span></span>:\n" +
                "   }\n" +
                "   <span class=\"n\">$default</span>{\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m64\">t</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m64\">t</a>;.</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.$static call()\" href=\"#m99\">call</a>()</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m99\">call</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m95\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$operator<a name=\"m131\">-</a> <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <a name=\"m148\">p</a>, <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <a name=\"m158\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <span class=\"n\"><span class=\"n\"><a name=\"m170\">out</a> </span>=<span class=\"n\"> $new <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m170\">out</a>;.</span><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m148\">p</a>;.;</span><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span>-<span class=\"n\"><span class=\"n\"><a href=\"#m158\">q</a>;.;</span><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"n\"><a href=\"#m170\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m259\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m282\">field</a></span>:\n" +
                " $public $static $int <a name=\"m311\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m330\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m360\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m259\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m330\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m360\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m330\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m360\">two</a>;.</span></span>)</span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m282\">field</a></span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m473\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "    $return <span class=\"n\"><span class=\"n\">1i</span>/<span class=\"n\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span class=\"n\">2</span>:\n" +
                "  } <span class=\"f\">$catch</span>(Object <a name=\"m158\">o</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">1i</span></span>:\n" +
                "   $return <span class=\"f\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">-<span class=\"f\">1i</span></span></span>:\n" +
                "  $try{\n" +
                "   <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"false\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "    $return <span class=\"n\"><span class=\"n\">1i</span>/<span class=\"n\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span class=\"f\">2</span>:\n" +
                "  } <span class=\"n\">$catch</span>(Object <a name=\"m159\">o</a>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m65\">t</a>;.</span>=<span class=\"n\">1i</span></span>:\n" +
                "   $return <span class=\"n\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum()\" href=\"#m51\">ONE</a>;\n" +
                " public int <span class=\"g\"><a name=\"m43\">field</a></span>;\n" +
                " <a name=\"m51\">ExEnum(</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m43\">field</a></span>=<span class=\"g\">5</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m90\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m118\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m134\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m147\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m147\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m201\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m220\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m134\">s</a> </span>==<span class=\"n\"> <a href=\"#m201\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m147\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m220\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m147\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m54\">ONE</a>(<span class=\"g\">5</span>);\n" +
                " public int <span class=\"g\"><a name=\"m46\">field</a></span>;\n" +
                " <a name=\"m54\">ExEnum(</a>int <a name=\"m65\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m46\">field</a></span>=<span class=\"g\"><a href=\"#m65\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m106\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m134\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m150\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m163\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m163\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m217\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m236\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m150\">s</a> </span>==<span class=\"n\"> <a href=\"#m217\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m163\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m236\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m163\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m55\">FOUR</a>(<span class=\"g\">5</span>);\n" +
                " public int <span class=\"g\"><a name=\"m47\">field</a></span>;\n" +
                " <a name=\"m55\">ExEnum(</a>int <a name=\"m66\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m47\">field</a></span>=<span class=\"g\"><a href=\"#m66\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m107\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m135\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m151\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m164\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m164\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m218\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m237\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m151\">s</a> </span>==<span class=\"n\"> <a href=\"#m218\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m164\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m237\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m164\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\">ONE</a>;\n" +
                "}\n" +
                "public class <a name=\"m46\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m74\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m90\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m103\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m103\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m157\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m176\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m90\">s</a> </span>==<span class=\"n\"> <a href=\"#m157\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m103\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m176\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m103\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\" title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR(int)\" href=\"#m37\">FOUR</a>(<span class=\"g\">5</span>){\n" +
                "  <a name=\"m37\">(</a>int <a name=\"m42\">p</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m87\">super</a>(<span class=\"g\"><a href=\"#m42\">p</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span class=\"g\"><a name=\"m79\">field</a></span>;\n" +
                " <a name=\"m87\">ExEnum(</a>int <a name=\"m98\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m79\">field</a></span>=<span class=\"g\"><a href=\"#m98\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m139\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m167\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m183\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m196\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m196\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m250\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m269\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m183\">s</a> </span>==<span class=\"n\"> <a href=\"#m250\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m196\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m269\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m196\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\" title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR()\" href=\"#m34\">FOUR</a>{\n" +
                "  <a name=\"m34\">(</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.ExEnum-FOUR.pkg.ExEnum-FOUR(int)\" href=\"#m56\">this</a>(<span class=\"g\">5</span>)</span>;\n" +
                "  }\n" +
                "  <a name=\"m56\">(</a>int <a name=\"m61\">p</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m106\">super</a>(<span class=\"g\"><a href=\"#m61\">p</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span class=\"g\"><a name=\"m98\">field</a></span>;\n" +
                " <a name=\"m106\">ExEnum(</a>int <a name=\"m117\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m98\">field</a></span>=<span class=\"g\"><a href=\"#m117\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m158\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m186\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m202\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m215\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m215\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m269\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m288\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m202\">s</a> </span>==<span class=\"n\"> <a href=\"#m269\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m215\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m288\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m215\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                " <a name=\"m26\">FOUR</a>{\n" +
                "  public int <span class=\"g\"><a name=\"m45\">field</a></span>;\n" +
                " };\n" +
                "}\n" +
                "public class <a name=\"m71\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m99\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m115\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m128\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m128\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for (int <a name=\"m182\">j</a></span>:<span class=\"n\">{<span class=\"n\">0</span>,<span class=\"n\">1</span>}</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m201\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m115\">s</a> </span>==<span class=\"n\"> <a href=\"#m182\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m128\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m201\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m128\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>operator<a name=\"m8\">+</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m24\">f</a>,<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m33\">s</a>) {\n" +
                " return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m24\">f</a></span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>+<span class=\"f\"><span class=\"f\"> <a href=\"#m33\">s</a></span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m103\">f</a>) {\n" +
                " return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"f\"><span class=\"f\"><a href=\"#m103\">f</a></span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m164\">f</a>) {\n" +
                " return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m164\">f</a></span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>+<span class=\"f\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class <a name=\"m215\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m236\">field</a></span>;\n" +
                " <a name=\"m244\">public Ex(</a>int <a name=\"m258\">field</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\">this</span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>=<span class=\"f\"> <a href=\"#m258\">field</a></span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">this</a>(<span class=\"n\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m215\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m353\">one</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">Ex</a>(<span class=\"f\">5</span>)</span></span>;\n" +
                "  <a title=\"pkg.Ex\" href=\"#m215\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m375\">two</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">Ex</a>(<span class=\"f\">3</span>)</span></span>;\n" +
                "  <b title=\"pkg.Ex\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m398\">three</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m353\">one</a> </span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"#m8\">+</a><span class=\"f\"> <a href=\"#m375\">two</a></span></span></span>;\n" +
                "  <b title=\"pkg.Ex\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m423\">four</a> </span>=<span class=\"f\"> <a title=\"static +(pkg.Ex)\" href=\"#m87\">+</a><span class=\"f\"> <a href=\"#m375\">two</a></span></span></span>;\n" +
                "  <b title=\"pkg.Ex\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m443\">five</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m398\">three</a> </span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"#m8\">+</a>=<span class=\"f\"> <a href=\"#m423\">four</a></span></span></span>;\n" +
                "  <b title=\"pkg.Ex\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m471\">six</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">Ex</a>(<span class=\"f\">6</span>)</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m494\">seven</a> </span>=<span class=\"p\"> <a title=\"true\">!</a><span class=\"p\">(<span class=\"p\"><span class=\"f\"><a href=\"#m471\">six</a> </span><a title=\"false\">!=</a><span class=\"f\"> <a href=\"#m471\">six</a></span></span>)</span></span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><a href=\"#m494\">seven</a></span>){\n" +
                "   <span class=\"p\"><span class=\"f\"><a href=\"#m494\">seven</a> </span>=<span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m494\">seven</a></span></span></span>;\n" +
                "  } <span class=\"n\">else if</span> (<span class=\"n\"><a href=\"#m494\">seven</a></span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m494\">seven</a> </span>=<span class=\"n\"> !<span class=\"n\"><a href=\"#m494\">seven</a></span></span></span>;\n" +
                "  }\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m471\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m818\">[</a><span class=\"f\">0</span><a title=\"pkg.Ex.[](int)\" href=\"#m818\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">=</a><span class=\"f\"> 8</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m471\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">[</a><span class=\"f\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">]</a> </span></span>=<span class=\"f\"> 8</span></span>;\n" +
                "  <a title=\"pkg.Ex\" href=\"#m215\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m628\">eight</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">Ex</a>(<span class=\"f\">8</span>)</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m628\">eight</a></span><a title=\"static ++(pkg.Ex)\" href=\"#m147\">+</a>+</span>;\n" +
                "  <span class=\"f\"><a title=\"static ++(pkg.Ex)\" href=\"#m147\">+</a>+<span class=\"f\"><a href=\"#m628\">eight</a></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m471\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m818\">[</a><span class=\"f\">2</span><a title=\"pkg.Ex.[](int)\" href=\"#m818\">]</a></span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">+</a></span>;\n" +
                "  <span class=\"f\">+<a title=\"pkg.Ex.[]=(int)\" href=\"#m863\">+</a><span class=\"f\"><span class=\"f\"><a href=\"#m471\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m818\">[</a><span class=\"f\">3</span><a title=\"pkg.Ex.[](int)\" href=\"#m818\">]</a></span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m699\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m712\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m712\">toStr</a> </span><i>+</i>=<span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m955\">ExTwo</a>()</span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m756\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m699\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1 </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m756\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>+<span class=\"f\"><a title=\"pkg.Ex.static caller()\" href=\"#m915\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m818\">this</a>(int <a name=\"m827\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m863\">this</a>(int <a name=\"m872\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span>=<span class=\"f\"> <b>value</b></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m915\">caller</a>(){\n" +
                "  return <span class=\"f\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m955\">pkg.ExTwo </a>{\n" +
                " public String <a name=\"m982\">$toString</a>(){\n" +
                "  return <span class=\"f\">null</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>operator<a name=\"m8\">+</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m24\">f</a>,<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m33\">s</a>) {\n" +
                " return <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m24\">f</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>+<span class=\"n\"><span class=\"n\"> <a href=\"#m33\">s</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m103\">f</a>) {\n" +
                " return <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"n\"><span class=\"n\"><a href=\"#m103\">f</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m164\">f</a>) {\n" +
                " return <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m164\">f</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>+<span class=\"n\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class <a name=\"m215\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m236\">field</a></span>;\n" +
                " <a name=\"m244\">public Ex(</a>int <a name=\"m258\">field</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\">this</span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>=<span class=\"f\"> <a href=\"#m258\">field</a></span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">this</a>(<span class=\"n\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m354\">one</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m372\">two</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m390\">three</a> </span>=<span class=\"p\"><span class=\"p\"> <a href=\"#m354\">one</a> </span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m416\">four</a> </span>=<span class=\"p\"><span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">&amp;</a>&amp;<span class=\"n\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m442\">five</a> </span>=<span class=\"p\"><span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">&amp;</a>&amp;<span class=\"n\"> !<span class=\"n\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m469\">eight</a> </span>=<span class=\"p\"><span class=\"p\"> <a href=\"#m354\">one</a> </span><a title=\"true\">&amp;</a><a title=\"false\">&amp;</a><span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  <b title=\"pkg.Ex\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m496\">six</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">Ex</a>(<span class=\"f\">6</span>)</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m519\">seven</a> </span>=<span class=\"p\"> <a title=\"true\">!</a><span class=\"p\">(<span class=\"p\"><span class=\"f\"><a href=\"#m496\">six</a> </span><a title=\"false\">!=</a><span class=\"f\"> <a href=\"#m496\">six</a></span></span>)</span></span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m553\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m553\">i</a> </span>&lt;<span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m553\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\">!<span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m553\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0</span></span>)</span></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m519\">seven</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m496\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m785\">[</a><span class=\"f\">0</span><a title=\"pkg.Ex.[](int)\" href=\"#m785\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m830\">=</a><span class=\"f\"> 8</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m496\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[]=(int)\" href=\"#m830\">[</a><span class=\"f\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"#m830\">]</a> </span></span>=<span class=\"f\"> 8</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m658\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m671\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m671\">toStr</a> </span>+=<span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m922\">ExTwo</a>()</span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m715\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m658\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1 </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m658\">s</a></span>+=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m715\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>+<span class=\"f\"><a title=\"pkg.Ex.static caller()\" href=\"#m882\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m785\">this</a>(int <a name=\"m794\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m830\">this</a>(int <a name=\"m839\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span>=<span class=\"f\"> <b>value</b></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m882\">caller</a>(){\n" +
                "  return <span class=\"f\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m922\">pkg.ExTwo </a>{\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>operator<a name=\"m8\">+</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m24\">f</a>,<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m33\">s</a>) {\n" +
                " return <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m24\">f</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>+<span class=\"n\"><span class=\"n\"> <a href=\"#m33\">s</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m87\">+</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m103\">f</a>) {\n" +
                " return <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"n\"><span class=\"n\"><a href=\"#m103\">f</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>)</span>;\n" +
                "}\n" +
                "operator<a name=\"m147\">++</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a> <a name=\"m164\">f</a>) {\n" +
                " return <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">pkg.Ex</a>(<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m164\">f</a></span>.<span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span></span>+<span class=\"n\">1</span></span>)</span>;\n" +
                "}\n" +
                "public class <a name=\"m215\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m236\">field</a></span>;\n" +
                " <a name=\"m244\">public Ex(</a>int <a name=\"m258\">field</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\">this</span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span></span>=<span class=\"f\"> <a href=\"#m258\">field</a></span></span>;\n" +
                " }\n" +
                " <a name=\"m292\">public Ex(</a>){\n" +
                "  <span class=\"n\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">this</a>(<span class=\"n\">0</span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m338\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m354\">one</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m372\">two</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m390\">three</a> </span>=<span class=\"p\"><span class=\"p\"> <a href=\"#m354\">one</a> </span><a title=\"true\">|</a>|<span class=\"n\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m416\">four</a> </span>=<span class=\"p\"><span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">|</a><a title=\"true\">|</a><span class=\"p\"> <a href=\"#m372\">two</a></span></span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m442\">five</a> </span>=<span class=\"p\"><span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m354\">one</a> </span></span><a title=\"false\">|</a><a title=\"false\">|</a><span class=\"p\"> <a title=\"false\">!</a><span class=\"p\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m469\">eight</a> </span>=<span class=\"p\"><span class=\"p\"> <a href=\"#m354\">one</a> </span><a title=\"true\">|</a>|<span class=\"n\"> !<span class=\"n\"><a href=\"#m372\">two</a></span></span></span></span>;\n" +
                "  <b title=\"pkg.Ex\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m496\">six</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m244\">new</a> <a title=\"pkg.Ex\" href=\"#m215\">Ex</a>(<span class=\"f\">6</span>)</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"p\"><span class=\"f\"><a name=\"m519\">seven</a> </span>=<span class=\"p\"> <a title=\"true\">!</a><span class=\"p\">(<span class=\"p\"><span class=\"f\"><a href=\"#m496\">six</a> </span><a title=\"false\">!=</a><span class=\"f\"> <a href=\"#m496\">six</a></span></span>)</span></span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m553\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m553\">i</a> </span>&lt;<span class=\"f\"> 4</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m553\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m553\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0 </span></span>&amp;&amp;<span class=\"f\"><span class=\"f\"> <a href=\"#m553\">i</a> </span>&gt;<span class=\"f\"> 1</span></span></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m519\">seven</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m496\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m791\">[</a><span class=\"f\">0</span><a title=\"pkg.Ex.[](int)\" href=\"#m791\">]</a> </span></span>+<a title=\"pkg.Ex.[]=(int)\" href=\"#m836\">=</a><span class=\"f\"> 8</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m496\">six</a></span><span class=\"f\"><a title=\"pkg.Ex.[]=(int)\" href=\"#m836\">[</a><span class=\"f\">1</span><a title=\"pkg.Ex.[]=(int)\" href=\"#m836\">]</a> </span></span>=<span class=\"f\"> 8</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m664\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m677\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m677\">toStr</a> </span>+=<span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m928\">ExTwo</a>()</span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m721\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m664\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1 </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m664\">s</a></span>+=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m721\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>+<span class=\"f\"><a title=\"pkg.Ex.static caller()\" href=\"#m888\">caller</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m791\">this</a>(int <a name=\"m800\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m836\">this</a>(int <a name=\"m845\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m236\">field</a> </span>=<span class=\"f\"> <b>value</b></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m888\">caller</a>(){\n" +
                "  return <span class=\"f\">1</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m928\">pkg.ExTwo </a>{\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"><span class=\"f\"> <span class=\"s\">\"\"</span></span>&lt;<span class=\"f\"><span class=\"s\">\"\"</span></span></span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m74\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m74\">toStr</a> </span>+=<span class=\"f\"> 1</span></span>;\n" +
                "  return <span class=\"f\">1</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"><span class=\"f\"> <span class=\"s\">\"\"</span></span>&lt;<span class=\"f\"><span class=\"s\">\"\"</span></span></span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m74\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  Object <span class=\"f\"><span class=\"f\"><a name=\"m95\">right</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m74\">toStr</a> </span><i>+</i>=<span class=\"f\"> <a href=\"#m95\">right</a></span></span>;\n" +
                "  return <span class=\"f\">1</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"><span class=\"f\"> <span class=\"s\">\"\"</span></span>&lt;<span class=\"f\"><span class=\"s\">\"\"</span></span></span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m74\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  Object[] <span class=\"f\"><span class=\"f\"><a name=\"m97\">right</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m74\">toStr</a> </span>+=<span class=\"f\"> <a href=\"#m97\">right</a></span></span>;\n" +
                "  return <span class=\"f\">1</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public annotation <a name=\"m18\">pkg.Annotation </a>{}\n" +
                "public class <a name=\"m49\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m77\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m93\">s</a> </span>=<span class=\"f\"><span class=\"f\"> <span class=\"s\">\"\"</span></span>&lt;<span class=\"f\"><span class=\"s\">\"\"</span></span></span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m110\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <a title=\"pkg.Annotation\" href=\"#m18\">Annotation</a> <span class=\"f\"><span class=\"f\"><a name=\"m135\">right</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m110\">toStr</a> </span>+=<span class=\"f\"> <a href=\"#m135\">right</a></span></span>;\n" +
                "  return <span class=\"f\">1</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">one</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><a href=\"#m57\">one</a></span>){\n" +
                "   return <span class=\"f\">5</span>;\n" +
                "  }\n" +
                "  <b title=\"boolean\">var</b> <span class=\"n\"><span class=\"n\"><a name=\"m104\">two</a> </span>=<span class=\"n\"> true</span></span>;\n" +
                "  <b title=\"boolean\">var</b> <span class=\"n\"><span class=\"n\"><a name=\"m122\">three</a> </span>=<span class=\"n\"><span class=\"n\"> <a href=\"#m57\">one</a> </span>&amp;&amp;<span class=\"n\"> <a href=\"#m104\">two</a></span></span></span>;\n" +
                "  return <span class=\"n\">6</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">3i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">1i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">2i</span>)</span></span>:\n" +
                "  <span class=\"f\">$foreach(java.lang.Number <a name=\"m236\">e</a></span>:<span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"g\"><span class=\"g\"><a href=\"#m236\">e</a>;</span><span class=\"g\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m302\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">3i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">1i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">2i</span>)</span></span>:\n" +
                "  <span class=\"p\">$foreach(java.lang.Number <a name=\"m236\">e</a></span>:<span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"g\"><span class=\"g\"><a href=\"#m236\">e</a>;</span><span class=\"g\">intValue()</span></span></span>:\n" +
                "   $break:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m313\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"p\">$foreach(java.lang.Number <a name=\"m182\">e</a></span>:<span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"n\"><span class=\"n\"><a href=\"#m182\">e</a>;</span><span class=\"n\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m248\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"p\">$if</span> (<span class=\"q\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a> </span><a title=\"true\">==</a><span class=\"g\"> 0</span></span>) {\n" +
                "   $return:\n" +
                "  }\n" +
                "  <span class=\"n\">$foreach(java.lang.Number <a name=\"m217\">e</a></span>:<span class=\"n\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"n\"><span class=\"n\"><a href=\"#m217\">e</a>;</span><span class=\"n\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m283\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m66\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">3</span>,<span class=\"g\">5</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">8</span>,<span class=\"g\">1</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">2</span>,<span class=\"g\">6</span>)</span></span>:\n" +
                "  <span class=\"f\">$for(Number <a name=\"m207\">f</a>, Number <a name=\"m217\">s</a></span>: <span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span class=\"g\"><span class=\"g\"><span class=\"g\"> <a href=\"#m207\">f</a>;</span><span class=\"g\">intValue()</span></span>+<span class=\"g\"><span class=\"g\"><a href=\"#m217\">s</a>;</span><span class=\"g\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m293\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m66\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">3</span>,<span class=\"g\">5</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">8</span>,<span class=\"g\">1</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">2</span>,<span class=\"g\">6</span>)</span></span>:\n" +
                "  <span class=\"p\">$for(Number <a name=\"m207\">f</a>, Number <a name=\"m217\">s</a></span>: <span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span class=\"g\"><span class=\"g\"><span class=\"g\"> <a href=\"#m207\">f</a>;</span><span class=\"g\">intValue()</span></span>+<span class=\"g\"><span class=\"g\"><a href=\"#m217\">s</a>;</span><span class=\"g\">intValue()</span></span></span></span>:\n" +
                "   $break:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m304\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m66\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"p\">$for(Number <a name=\"m156\">f</a>, Number <a name=\"m166\">s</a></span>: <span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m156\">f</a>;</span><span class=\"n\">intValue()</span></span>+<span class=\"n\"><span class=\"n\"><a href=\"#m166\">s</a>;</span><span class=\"n\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m242\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m66\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"p\">$if</span> (<span class=\"q\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span><a title=\"true\">==</a><span class=\"g\"> 0</span></span>) {\n" +
                "   $return:\n" +
                "  }\n" +
                "  <span class=\"n\">$for(Number <a name=\"m191\">f</a>, Number <a name=\"m201\">s</a></span>: <span class=\"n\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m191\">f</a>;</span><span class=\"n\">intValue()</span></span>+<span class=\"n\"><span class=\"n\"><a href=\"#m201\">s</a>;</span><span class=\"n\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m277\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <span class=\"p\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m62\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"p\"><span class=\"f\"><a href=\"#m62\">i</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\">!<span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m131\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m144\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m144\">toStr</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m144\">toStr</a> </span><i>+</i><span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m248\">ExTwo</a>()</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m144\">toStr</a> </span>=<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m248\">ExTwo</a>() </span><i>+</i><span class=\"f\"> <a href=\"#m144\">toStr</a></span></span></span>;\n" +
                "  return <span class=\"f\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m248\">pkg.ExTwo </a>{\n" +
                " public String <a name=\"m275\">$toString</a>() {\n" +
                "  return <span class=\"f\">null</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>){\n" +
                "   return <span class=\"f\">7</span>;\n" +
                "  }\n" +
                "  <span class=\"n\">for</span> (int <span class=\"n\"><span class=\"n\"><a name=\"m107\">i</a> </span>=<span class=\"n\"> 0</span></span>; <span class=\"n\"><span class=\"n\"><a href=\"#m107\">i</a> </span>&lt;<span class=\"n\"> 2</span></span>; <span class=\"n\"><span class=\"n\"><a href=\"#m107\">i</a></span>++</span>){\n" +
                "   <span class=\"n\">if</span> (<span class=\"n\">!<span class=\"n\">(<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m107\">i</a> </span>%<span class=\"n\"> 2 </span></span>==<span class=\"n\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"n\"><span class=\"n\"><a name=\"m176\">toStr</a> </span>=<span class=\"n\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"n\"><span class=\"n\"><a href=\"#m176\">toStr</a> </span>=<span class=\"n\"><span class=\"n\"> <a href=\"#m176\">toStr</a> </span>+<span class=\"n\"> new <a title=\"pkg.ExTwo\" href=\"#m280\">ExTwo</a>()</span></span></span>;\n" +
                "  <span class=\"n\"><span class=\"n\"><a href=\"#m176\">toStr</a> </span>=<span class=\"n\"><span class=\"n\"> new <a title=\"pkg.ExTwo\" href=\"#m280\">ExTwo</a>() </span>+<span class=\"n\"> <a href=\"#m176\">toStr</a></span></span></span>;\n" +
                "  return <span class=\"n\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m280\">pkg.ExTwo </a>{\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>){\n" +
                "   return <span class=\"f\">7</span>;\n" +
                "  }\n" +
                "  for (;;){\n" +
                "    break;\n" +
                "  }\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"n\"><span class=\"n\"><a name=\"m129\">toStr</a> </span>=<span class=\"n\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"n\"><span class=\"n\"><a href=\"#m129\">toStr</a> </span>=<span class=\"n\"><span class=\"n\"> <a href=\"#m129\">toStr</a> </span>+<span class=\"n\"> new <a title=\"pkg.ExTwo\" href=\"#m233\">ExTwo</a>()</span></span></span>;\n" +
                "  <span class=\"n\"><span class=\"n\"><a href=\"#m129\">toStr</a> </span>=<span class=\"n\"><span class=\"n\"> new <a title=\"pkg.ExTwo\" href=\"#m233\">ExTwo</a>() </span>+<span class=\"n\"> <a href=\"#m129\">toStr</a></span></span></span>;\n" +
                "  return <span class=\"n\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m233\">pkg.ExTwo </a>{\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>\n" +
                "$public $class <a name=\"m60\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m91\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m119\">arr</a> </span>=<span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m60\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span></span>:\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m119\">arr</a>;.</span><span class=\"f\">length </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>:\n" +
                "  }\n" +
                "  <span class=\"p\">$else $if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m119\">arr</a>;.</span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>:\n" +
                "  }\n" +
                "  <span class=\"p\">$else $if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m119\">arr</a>;.</span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">seven</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m83\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m83\">i</a> </span>&lt;<span class=\"f\"> 4</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m83\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m83\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0</span></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m57\">seven</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "   }\n" +
                "   <span class=\"f\">else if</span> (<span class=\"f\"><span class=\"f\"><a href=\"#m83\">i</a> </span>&gt;<span class=\"f\"> 1</span></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m57\">seven</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m199\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  return <span class=\"f\">8</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">seven</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m83\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m83\">i</a> </span>&lt;<span class=\"f\"> 4</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m83\">i</a></span>++</span>){\n" +
                "   <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m83\">i</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 4</span></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m57\">seven</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "   }\n" +
                "   <span class=\"n\">else if</span> (<span class=\"n\"><span class=\"n\"><a href=\"#m83\">i</a> </span>&gt;<span class=\"n\"> 1</span></span>){\n" +
                "    <span class=\"n\"><span class=\"n\"><a href=\"#m57\">seven</a> </span>=<span class=\"n\"> true</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m194\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  return <span class=\"f\">8</span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <span class=\"p\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m62\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"p\"><span class=\"f\"><a href=\"#m62\">i</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\">!<span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m131\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m144\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m144\">toStr</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m144\">toStr</a> </span>+<span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m248\">ExTwo</a>()</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m144\">toStr</a> </span>=<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m248\">ExTwo</a>() </span>+<span class=\"f\"> <a href=\"#m144\">toStr</a></span></span></span>;\n" +
                "  return <span class=\"f\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m248\">pkg.ExTwo </a>{\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " <a name=\"m25\">$public Ex(</a>){\n" +
                " }\n" +
                " $public $static Object <a name=\"m66\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"#m25\">$new</a> <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>()</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static StringBuilder <a name=\"m55\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">$new StringBuilder(<span class=\"f\"><span class=\"s\">\"hello\"</span></span>)</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">while</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m106\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m106\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m70\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }\n" +
                "  <span class=\"n\">while</span> (<span class=\"n\"><span class=\"n\"><a href=\"#m57\">s</a> </span>==<span class=\"n\"> 1</span></span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m142\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m57\">s</a> </span>==<span class=\"n\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m70\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m142\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "   return <span class=\"n\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "    $return <span class=\"n\"><span class=\"n\">1i</span>/<span class=\"n\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span class=\"n\">2</span>:\n" +
                "  } <span class=\"f\">$catch</span>(Object <a name=\"m158\">o</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">1i</span></span>:\n" +
                "   $return <span class=\"f\"><a href=\"#m65\">t</a>;.</span>:\n" +
                "  } $finally {\n" +
                "   $return <span class=\"f\">3</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">-<span class=\"f\">1i</span></span></span>:\n" +
                "  $try{\n" +
                "   <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"false\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "    $return <span class=\"n\"><span class=\"n\">1i</span>/<span class=\"n\">0i</span></span>:\n" +
                "   }\n" +
                "   $return <span class=\"f\">2</span>:\n" +
                "  } <span class=\"n\">$catch</span>(Object <a name=\"m159\">o</a>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m65\">t</a>;.</span>=<span class=\"n\">1i</span></span>:\n" +
                "   $return <span class=\"n\"><a href=\"#m65\">t</a>;.</span>:\n" +
                "  } $finally {\n" +
                "   $return <span class=\"f\">3</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "    $throw <span class=\"f\">1i</span>:\n" +
                "   }\n" +
                "   $return <span class=\"n\">2</span>:\n" +
                "  } <span class=\"f\">$catch</span>(Object <a name=\"m154\">o</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">1i</span></span>:\n" +
                "   $return <span class=\"f\"><a href=\"#m65\">t</a>;.</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m94\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m94\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
                "  }<span class=\"n\">while</span> (<span class=\"n\">true</span>);\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m94\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m94\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "  }<span class=\"f\">while</span> (<span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>&lt;<span class=\"f\"> 7</span></span>);\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m94\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m94\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>&gt;=<span class=\"f\"> 7</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }<span class=\"p\">while</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m70\">sum</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 17</span></span>);\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  do{\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m94\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> 0 </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m94\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>&gt;=<span class=\"f\"> 7</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "   continue;\n" +
                "  }<span class=\"p\">while</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m70\">sum</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 17</span></span>);\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " <a name=\"m25\">$public Ex(</a>){\n" +
                " }\n" +
                " $public $static Object <a name=\"m66\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"#m25\">$new</a> <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>()</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " <a name=\"m25\">$public Ex(</a>$int <a name=\"m41\">p</a>){\n" +
                " }\n" +
                " $public $static Object <a name=\"m72\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex($int)\" href=\"#m25\">$new</a> <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>(<span class=\"f\">0</span>)</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.CustIter</a>&lt;<a name=\"m28\">#T</a>&gt; :$iterator&lt;<a href=\"#m28\">#T</a>&gt;{\n" +
                " $private <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;<a href=\"#m28\">#T</a>&gt; <span class=\"g\"><a name=\"m75\">list</a></span>:\n" +
                " $private $int <span class=\"g\"><a name=\"m96\">length</a></span>:\n" +
                " $private $int <span class=\"g\"><a name=\"m119\">index</a></span>:\n" +
                " <a name=\"m127\">$public (</a><a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;<a href=\"#m28\">#T</a>&gt; <a name=\"m153\">i</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIter.list\" href=\"#m75\">list</a>;;;</span>=<span class=\"g\"><a href=\"#m153\">i</a>;.;</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIter.length\" href=\"#m96\">length</a>;;;</span>=<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIter.list\" href=\"#m75\">list</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.size()\" href=\"CustList.html#m519\">size</a>()</span></span></span>:\n" +
                " }\n" +
                " $public $normal <a href=\"#m28\">#T</a> <a name=\"m223\">next</a>(){\n" +
                "  <a href=\"#m28\">#T</a> <span class=\"g\"><span class=\"g\"><a name=\"m236\">out</a></span>=<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIter.list\" href=\"#m75\">list</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.get($int)\" href=\"CustList.html#m571\">get</a>(<span class=\"g\"><a title=\"pkg.CustIter.index\" href=\"#m119\">index</a>;;;</span>)</span></span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIter.index\" href=\"#m119\">index</a>;;;</span>++</span>:\n" +
                "  $return <span class=\"g\"><a href=\"#m236\">out</a>;.</span>:\n" +
                " }\n" +
                " $public $normal $boolean <a name=\"m322\">hasNext</a>(){\n" +
                "  $return <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIter.index\" href=\"#m119\">index</a>;;;</span>&lt;<span class=\"g\"><a title=\"pkg.CustIter.length\" href=\"#m96\">length</a>;;;</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.CustList</a>&lt;<a name=\"m28\">#U</a>&gt; :$iterable&lt;<a href=\"#m28\">#U</a>&gt;{\n" +
                " $private <a href=\"#m28\">#U</a>[] <span class=\"g\"><a name=\"m63\">list</a></span>:\n" +
                " $private $int <span class=\"g\"><a name=\"m84\">length</a></span>:\n" +
                " <a name=\"m93\">$public (</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span>=<span class=\"g\">$new <a href=\"#m28\">#U</a>[<span class=\"g\">0i</span>]</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m154\">add</a>(<a href=\"#m28\">#U</a> <a name=\"m161\">elt</a>){\n" +
                "  <span class=\"g\"><a title=\"pkg.CustList.add($int,#U)\" href=\"#m218\">add</a>(<span class=\"g\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>,<span class=\"g\"><a href=\"#m161\">elt</a>;.;</span>)</span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m218\">add</a>($int <a name=\"m227\">index</a>,<a href=\"#m28\">#U</a> <a name=\"m236\">elt</a>){\n" +
                "  <a href=\"#m28\">#U</a>[] <span class=\"g\"><span class=\"g\"><a name=\"m249\">newlist</a></span>=<span class=\"g\">$new <a href=\"#m28\">#U</a>[<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>+<span class=\"g\">1i</span></span>]</span></span>:\n" +
                "  <span class=\"f\">$iter</span>($int <a name=\"m293\">i</a>=<span class=\"g\">0i</span>:<span class=\"g\"><a href=\"#m227\">index</a>;.;</span>:<span class=\"g\">1i</span>){\n" +
                "   <span class=\"g\"><span class=\"g\"><span class=\"g\"><a href=\"#m249\">newlist</a>;.</span><span class=\"g\">[<span class=\"g\"><a href=\"#m293\">i</a>;</span>]</span></span>=<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"g\">[<span class=\"g\"><a href=\"#m293\">i</a>;</span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span class=\"g\"><span class=\"g\"><span class=\"g\"><a href=\"#m249\">newlist</a>;.</span><span class=\"g\">[<span class=\"g\"><a href=\"#m227\">index</a>;.;</span>]</span></span>=<span class=\"g\"><a href=\"#m236\">elt</a>;.;</span></span>:\n" +
                "  <span class=\"p\">$iter</span>($int <a name=\"m389\">i</a>=<span class=\"g\"><span class=\"g\"><a href=\"#m227\">index</a>;.;</span>+<span class=\"g\">1i</span></span>:<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>+<span class=\"g\">1i</span></span>:<span class=\"g\">1i</span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m249\">newlist</a>;.</span><span class=\"n\">[<span class=\"n\"><a href=\"#m389\">i</a>;</span>]</span></span>=<span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"n\">[<span class=\"n\"><span class=\"n\"><a href=\"#m389\">i</a>;</span>-<span class=\"n\">1i</span></span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>++</span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span>=<span class=\"g\"><a href=\"#m249\">newlist</a>;.</span></span>:\n" +
                " }\n" +
                " $public $normal $int <a name=\"m519\">size</a>(){\n" +
                "  $return <span class=\"g\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>:\n" +
                " }\n" +
                " $public $normal <a href=\"#m28\">#U</a> <a name=\"m571\">get</a>($int <a name=\"m580\">index</a>){\n" +
                "  $return <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"g\">[<span class=\"g\"><a href=\"#m580\">index</a>;.;</span>]</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m643\">set</a>($int <a name=\"m652\">index</a>,<a href=\"#m28\">#U</a> <a name=\"m661\">elt</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"n\">[<span class=\"n\"><a href=\"#m652\">index</a>;.;</span>]</span></span>=<span class=\"n\"><a href=\"#m661\">elt</a>;.;</span></span>:\n" +
                " }\n" +
                " $public $normal $void <a name=\"m721\">remove</a>($int <a name=\"m733\">index</a>){\n" +
                "  <span class=\"n\">$iter</span>($int <a name=\"m754\">i</a>=<span class=\"n\"><a href=\"#m733\">index</a>;.;</span>:<span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>-<span class=\"n\">1i</span></span>:<span class=\"n\">1i</span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"n\">[<span class=\"n\"><a href=\"#m754\">i</a>;</span>]</span></span>=<span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"n\">[<span class=\"n\"><span class=\"n\"><a href=\"#m754\">i</a>;</span>+<span class=\"n\">1i</span></span>]</span></span></span>:\n" +
                "  }\n" +
                "  <span class=\"n\"><span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.list\" href=\"#m63\">list</a>;;;</span><span class=\"n\">[<span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>-<span class=\"n\">1i</span></span>]</span></span>=<span class=\"n\">$null</span></span>:\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.CustList.length\" href=\"#m84\">length</a>;;;</span>--</span>:\n" +
                " }\n" +
                " $public $normal $iterator&lt;<a href=\"#m28\">#U</a>&gt; <a name=\"m898\">iterator</a>(){\n" +
                "  $return <span class=\"g\"><a title=\"pkg.CustIter.pkg.CustIter(pkg.CustList&lt;#T&gt;)\" href=\"CustIter.html#m127\">$new</a> <a title=\"pkg.CustIter\" href=\"CustIter.html#m15\">pkg.CustIter</a>&lt;<a href=\"#m28\">#U</a>&gt;(<span class=\"g\">$this</span>)</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.CustIterTable</a>&lt;<a name=\"m33\">U</a>,<a name=\"m35\">V</a>&gt; :$iteratorTable&lt;<a href=\"#m33\">U</a>,<a href=\"#m35\">V</a>&gt;{\n" +
                " $private <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;<a href=\"#m33\">U</a>,<a href=\"#m35\">V</a>&gt; <span class=\"g\"><a name=\"m85\">list</a></span>:\n" +
                " $private $int <span class=\"g\"><a name=\"m106\">length</a></span>:\n" +
                " $private $int <span class=\"g\"><a name=\"m129\">index</a></span>:\n" +
                " <a name=\"m137\">$public CustIterTable(</a><a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;<a href=\"#m33\">U</a>,<a href=\"#m35\">V</a>&gt; <a name=\"m174\">i</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIterTable.list\" href=\"#m85\">list</a>;;;</span>=<span class=\"g\"><a href=\"#m174\">i</a>;.;</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIterTable.length\" href=\"#m106\">length</a>;;;</span>=<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIterTable.list\" href=\"#m85\">list</a>;;;</span><span class=\"g\"><a title=\"pkg.CustTable.size()\" href=\"CustTable.html#m298\">size</a>()</span></span></span>:\n" +
                " }\n" +
                " $public <a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m33\">U</a>,<a href=\"#m35\">V</a>&gt; <a name=\"m247\">nextPair</a>(){\n" +
                "  <a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m33\">U</a>,<a href=\"#m35\">V</a>&gt; <span class=\"g\"><span class=\"g\"><a name=\"m275\">out</a></span>=<span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIterTable.list\" href=\"#m85\">list</a>;;;</span><span class=\"g\"><a title=\"pkg.CustTable.get($int)\" href=\"CustTable.html#m355\">get</a>(<span class=\"g\"><a title=\"pkg.CustIterTable.index\" href=\"#m129\">index</a>;;;</span>)</span></span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIterTable.index\" href=\"#m129\">index</a>;;;</span>++</span>:\n" +
                "  $return <span class=\"g\"><a href=\"#m275\">out</a>;.</span>:\n" +
                " }\n" +
                " $public $boolean <a name=\"m353\">hasNextPair</a>(){\n" +
                "  $return <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustIterTable.index\" href=\"#m129\">index</a>;;;</span>&lt;<span class=\"g\"><a title=\"pkg.CustIterTable.length\" href=\"#m106\">length</a>;;;</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.CustTable</a>&lt;<a name=\"m29\">U</a>,<a name=\"m31\">V</a>&gt; :$iterableTable&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt;{\n" +
                " $private <a title=\"pkg.CustList\" href=\"CustList.html#m15\">CustList</a>&lt;<a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt;&gt; <span class=\"g\"><a name=\"m90\">list</a></span>:\n" +
                " <a name=\"m97\">$public (</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">CustList</a>&lt;<a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt;&gt;()</span></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m166\">add</a>(<a href=\"#m29\">U</a> <a name=\"m172\">f</a>,<a href=\"#m31\">V</a> <a name=\"m176\">s</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\"><a title=\"pkg.CustPair.pkg.CustPair(#U,#V)\" href=\"CustPair.html#m110\">$new</a> <a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt;(<span class=\"g\"><a href=\"#m172\">f</a>;.;</span>,<span class=\"g\"><a href=\"#m176\">s</a>;.;</span>)</span>)</span></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m241\">add</a>(<a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt; <a name=\"m259\">p</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span class=\"n\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"n\"><a href=\"#m259\">p</a>;.;</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m298\">size</a>(){\n" +
                "  $return <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span class=\"g\"><a title=\"pkg.CustList.size()\" href=\"CustList.html#m519\">size</a>()</span></span>:\n" +
                " }\n" +
                " $public <a title=\"pkg.CustPair\" href=\"CustPair.html#m15\">CustPair</a>&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt; <a name=\"m355\">get</a>($int <a name=\"m364\">index</a>){\n" +
                "  $return <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustTable.list\" href=\"#m90\">list</a></span>.<span class=\"g\"><a title=\"pkg.CustList.get($int)\" href=\"CustList.html#m571\">get</a>(<span class=\"g\"><a href=\"#m364\">index</a>;.;</span>)</span></span>:\n" +
                " }\n" +
                " $public $iteratorTable&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt; <a name=\"m434\">iteratorTable</a>(){\n" +
                "  $return <span class=\"g\"><a title=\"pkg.CustIterTable.pkg.CustIterTable(pkg.CustTable&lt;#U,#V&gt;)\" href=\"CustIterTable.html#m137\">$new</a> <a title=\"pkg.CustIterTable\" href=\"CustIterTable.html#m15\">CustIterTable</a>&lt;<a href=\"#m29\">U</a>,<a href=\"#m31\">V</a>&gt;(<span class=\"g\">$this</span>)</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.CustPair</a>&lt;<a name=\"m28\">U</a>,<a name=\"m30\">V</a>&gt; :$pair&lt;<a href=\"#m28\">U</a>,<a href=\"#m30\">V</a>&gt;{\n" +
                " $private <a href=\"#m28\">U</a> <span class=\"g\"><a name=\"m58\">first</a></span>:\n" +
                " $private <a href=\"#m30\">V</a> <span class=\"g\"><a name=\"m77\">second</a></span>:\n" +
                " <a name=\"m86\">$public CustPair(</a>){\n" +
                " }\n" +
                " <a name=\"m110\">$public CustPair(</a><a href=\"#m28\">U</a> <a name=\"m129\">f</a>,<a href=\"#m30\">V</a> <a name=\"m133\">s</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustPair.first\" href=\"#m58\">first</a> </span>=<span class=\"g\"> <a href=\"#m129\">f</a>;.;</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.CustPair.second\" href=\"#m77\">second</a> </span>=<span class=\"g\"> <a href=\"#m133\">s</a>;.;</span></span>:\n" +
                " }\n" +
                " $public <a href=\"#m28\">U</a> <a name=\"m184\">getFirst</a>(){\n" +
                "  $return <span class=\"g\"><a title=\"pkg.CustPair.first\" href=\"#m58\">first</a></span>:\n" +
                " }\n" +
                " $public <a href=\"#m30\">V</a> <a name=\"m227\">getSecond</a>(){\n" +
                "  $return <span class=\"g\"><a title=\"pkg.CustPair.second\" href=\"#m77\">second</a></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m276\">setFirst</a>(<a href=\"#m28\">U</a> <a name=\"m287\">f</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.CustPair.first\" href=\"#m58\">first</a> </span>=<span class=\"n\"> <a href=\"#m287\">f</a>;.;</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">&lt;</a> $boolean (<a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a> <a name=\"m28\">p</a>, <a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a> <a name=\"m38\">q</a>){\n" +
                " $return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m28\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span><a title=\"&quot;\">&lt;</a><span class=\"f\"><span class=\"f\"><a href=\"#m38\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span>:\n" +
                "}\n" +
                "$operator<a name=\"m83\">&gt;</a> $boolean (<a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a> <a name=\"m102\">p</a>, <a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a> <a name=\"m112\">q</a>){\n" +
                " $return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m102\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span><a title=\"&amp;\">&gt;</a><span class=\"f\"><span class=\"f\"><a href=\"#m112\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span>:\n" +
                "}\n" +
                "$public $class <a name=\"m163\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m186\">field</a></span>:\n" +
                " $public $static $int <a name=\"m215\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m234\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m264\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m163\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m234\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m264\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $boolean <span class=\"p\"><span class=\"f\"><a name=\"m332\">rOne</a> </span>=<span class=\"p\"><span class=\"f\"> <a href=\"#m234\">one</a>;. </span><a title=\"$static &lt;(pkg.Ex,pkg.Ex)\" href=\"#m9\">&lt;</a><span class=\"f\"> <a href=\"#m264\">two</a>;.</span></span></span>:\n" +
                "  $boolean <span class=\"p\"><span class=\"f\"><a name=\"m365\">rTwo</a> </span>=<span class=\"p\"><span class=\"f\"> <a href=\"#m234\">one</a>;. </span><a title=\"$static &gt;(pkg.Ex,pkg.Ex)\" href=\"#m83\">&gt;</a><span class=\"f\"> <a href=\"#m264\">two</a>;.</span></span></span>:\n" +
                "  $boolean <span class=\"p\"><span class=\"f\"><a name=\"m398\">rThree</a> </span>=<span class=\"p\"><span class=\"f\"><span class=\"f\"> <a href=\"#m234\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span><a title=\"&quot;\">&lt;</a><span class=\"f\"><span class=\"f\"> <a href=\"#m264\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span></span>:\n" +
                "  $boolean <span class=\"p\"><span class=\"f\"><a name=\"m443\">rFour</a> </span>=<span class=\"p\"><span class=\"f\"><span class=\"f\"> <a href=\"#m234\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a> </span></span><a title=\"&amp;\">&gt;</a><span class=\"f\"><span class=\"f\"> <a href=\"#m264\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m186\">field</a></span></span></span></span>:\n" +
                "  $return <span class=\"f\">5</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">iter</span> (int <a name=\"m91\">j</a>=<span class=\"f\">0</span>;<span class=\"f\">2</span>;<span class=\"f\">1</span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m110\">t</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m57\">s</a> </span>==<span class=\"f\"> <a href=\"#m91\">j</a> </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m110\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">iter</span> (int <a name=\"m91\">j</a>=<span class=\"f\">0</span>;<span class=\"f\">2</span>;<span class=\"f\">1</span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m110\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> <a href=\"#m91\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m110\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   break;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">iter</span> (int <a name=\"m91\">j</a>=<span class=\"f\">0</span>;<span class=\"f\">0</span>;<span class=\"f\">1</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m110\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m57\">s</a> </span>==<span class=\"n\"> <a href=\"#m91\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m70\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m110\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m70\">sum</a> </span><a title=\"true\">==</a><span class=\"f\"> 0</span></span>){\n" +
                "   return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
                "  }\n" +
                "  <span class=\"n\">iter</span> (int <a name=\"m125\">j</a>=<span class=\"n\">0</span>;<span class=\"n\">0</span>;<span class=\"n\">1</span>){\n" +
                "   int[] <span class=\"n\"><span class=\"n\"><a name=\"m144\">t</a> </span>=<span class=\"n\"><span class=\"n\"><span class=\"n\"> <a href=\"#m57\">s</a> </span>==<span class=\"n\"> <a href=\"#m125\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m70\">sum</a> </span>+=<span class=\"n\"><span class=\"n\"> <a href=\"#m144\">t</a></span><span class=\"n\">[<span class=\"n\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><a href=\"#m70\">sum</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static StringBuilder <a name=\"m55\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">$new StringBuilder(<span class=\"f\"><span class=\"s\">\"hel\\\"lo\"</span></span>)</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static StringBuilder <a name=\"m55\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">$new StringBuilder(<span class=\"f\"><span class=\"s\">`hel``lo`</span></span>)</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $char <a name=\"m47\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"s\">'\\''</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m137\">pkg.Ex</a>:<a title=\"pkg.ExTwo\" href=\"#m385\">ExTwo</a> {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m193\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m223\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m223\">two</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span class=\"f\">$super.<a title=\"pkg.ExTwo.field\" href=\"#m411\">field</a></span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m343\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m385\">pkg.ExTwo </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m411\">field</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m137\">pkg.Ex</a>:<a title=\"pkg.ExTwo\" href=\"#m388\">ExTwo</a> {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m193\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m223\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m223\">two</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span class=\"f\">$super.<a title=\"pkg.ExTwo.method()\" href=\"#m435\">method</a>()</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m346\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m388\">pkg.ExTwo </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m414\">field</a></span>:\n" +
                " $public $int <a name=\"m435\">method</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m414\">field</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><span class=\"f\"><a name=\"m38\">one</a></span>,<span class=\"f\"><a name=\"m42\">two</a></span></span>:\n" +
                " $public $static $int <a name=\"m69\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m88\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m118\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m88\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m88\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m118\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span class=\"f\"> 3</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m118\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span class=\"f\"> 4</span></span>:\n" +
                "  $return <span class=\"f\">5</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><span class=\"f\"><span class=\"f\"><a name=\"m38\">one</a></span>=<span class=\"f\">7</span></span>,<span class=\"f\"><span class=\"f\"><a name=\"m44\">two</a></span>=<span class=\"f\">8</span></span></span>:\n" +
                " $public $static $int <a name=\"m73\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m92\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m122\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m92\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m92\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.two\" href=\"#m44\">two</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m122\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span class=\"f\"> 3</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m122\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.two\" href=\"#m44\">two</a> </span></span>=<span class=\"f\"> 4</span></span>:\n" +
                "  $return <span class=\"f\">5</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                " <a name=\"m28\">ONE</a>,<a name=\"m32\">TWO</a>:\n" +
                "}\n" +
                "$public $class <a name=\"m54\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m85\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">ONE</a></span></span>.<span class=\"f\">$ordinal()</span></span>+<span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.TWO\" href=\"#m32\">TWO</a></span></span>.<span class=\"f\">$ordinal()</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">3i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">1i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">2i</span>)</span></span>:\n" +
                "  $iterable&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m238\">iter</a> </span>=<span class=\"g\"> <a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a></span></span>:\n" +
                "  <b title=\"java.lang.$iterator&lt;java.lang.Number&gt;\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m258\">it</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m238\">iter</a>;.</span><span class=\"g\">iterator()</span></span></span>:\n" +
                "  <span class=\"f\">$while</span>(<span class=\"g\"><span class=\"g\"><a href=\"#m258\">it</a>;.</span><span class=\"g\">hasNext()</span></span>){\n" +
                "   <b title=\"java.lang.Number\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m314\">l</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m258\">it</a>;.</span><span class=\"g\">next()</span></span></span>:\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"g\"><span class=\"g\"><a href=\"#m314\">l</a>;.</span><span class=\"g\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m385\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">3i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">1i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">2i</span>)</span></span>:\n" +
                "  <b title=\"pkg.CustList&lt;java.lang.Number&gt;\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m215\">iter</a> </span>=<span class=\"g\"> <a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a></span></span>:\n" +
                "  <b title=\"java.lang.$iterator&lt;java.lang.Number&gt;\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m235\">it</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m215\">iter</a>;.</span><span class=\"g\"><a title=\"pkg.CustList.iterator()\" href=\"CustList.html#m898\">iterator</a>()</span></span></span>:\n" +
                "  <span class=\"f\">$while</span>(<span class=\"g\"><span class=\"g\"><a href=\"#m235\">it</a>;.</span><span class=\"g\">hasNext()</span></span>){\n" +
                "   <b title=\"java.lang.Number\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m291\">l</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m235\">it</a>;.</span><span class=\"g\">next()</span></span></span>:\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"g\"><span class=\"g\"><a href=\"#m291\">l</a>;.</span><span class=\"g\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m362\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$static java.lang.Integer.MAX_VALUE;\n" +
                "$public $class <a name=\"m52\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m83\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">MAX_VALUE</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <span class=\"g\"><a name=\"m46\">field</a></span>:\n" +
                " $public $static $int <a name=\"m75\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m46\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,,field)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                " <a name=\"m28\">ONE</a>:\n" +
                "}\n" +
                "$public $class <a name=\"m50\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a> <a name=\"m83\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">$lambda</a>(<a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a>,,ONE)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                " <a name=\"m28\">ONE</a>{}:\n" +
                "}\n" +
                "$public $class <a name=\"m52\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a> <a name=\"m85\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">$lambda</a>(<a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a>,,ONE)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\">$lambda(Integer,,MAX_VALUE)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static StringBuilder <a name=\"m55\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\">$lambda(StringBuilder,$new)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.Ex\" href=\"#m15\">Ex</a> <a name=\"m44\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\">$lambda(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,$new)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " <a name=\"m25\">$public Ex(</a>){\n" +
                " }\n" +
                " $public $static <a title=\"pkg.Ex\" href=\"#m15\">Ex</a> <a name=\"m62\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.pkg.Ex()\" href=\"#m25\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,$new)</span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m38\">field</a></span>:\n" +
                " <a name=\"m46\">$public Ex(</a>$int <a name=\"m62\">field</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\">$this</span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a></span></span>=<span class=\"f\"><a href=\"#m62\">field</a>;.;</span></span>:\n" +
                " }\n" +
                " $public $static <a title=\"pkg.Ex\" href=\"#m15\">Ex</a> <a name=\"m117\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.pkg.Ex($int)\" href=\"#m46\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,$new,$int)</span>.<span class=\"f\"><b>call</b>(<span class=\"f\">5</span>)</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>[] <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\">$lambda(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>[],$new,$int)</span>.<span class=\"f\"><b>call</b>(<span class=\"f\">5</span>)</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">$lambda(String,length)</span></span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\">$new <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.inst()\" href=\"#m118\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,inst)</span></span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m118\">inst</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">length()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m38\">field</a></span>:\n" +
                " $public $static $int <a name=\"m67\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\">$new <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.[]($int)\" href=\"#m143\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,[],$int)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">8</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m143\">$this</a>($int <a name=\"m154\">param</a>){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m197\">$this</a>($int <a name=\"m208\">param</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a> </span>=<span class=\"n\"> <b>$value</b>;.;</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m38\">field</a></span>:\n" +
                " $public $static Object <a name=\"m69\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\">$new <a title=\"pkg.Ex\" href=\"#m15\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.[]=($int)\" href=\"#m203\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>,[]=,$int)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">8</span>,<span class=\"f\">15</span>)</span></span>:\n" +
                " }\n" +
                " $public $int <a name=\"m149\">$this</a>($int <a name=\"m160\">param</a>){\n" +
                "  $return <span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a></span>:\n" +
                " }\n" +
                " $public $void <a name=\"m203\">$this</a>($int <a name=\"m214\">param</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m38\">field</a> </span>=<span class=\"f\"> <b>$value</b>;.;</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m130\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m130\">pkg.Ex</a> <a name=\"m25\">a</a>,<a title=\"pkg.Ex\" href=\"#m130\">pkg.Ex</a> <a name=\"m34\">b</a>) {\n" +
                " <b title=\"pkg.Ex\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m45\">o</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m130\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m45\">o</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m25\">a</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>+<span class=\"f\"><span class=\"f\"> <a href=\"#m34\">b</a>;.;</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m45\">o</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m130\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m153\">field</a></span>:\n" +
                " $public $static Object <a name=\"m184\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m130\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m199\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m130\">Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m130\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m221\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m130\">Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m199\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>=<span class=\"f\"> 15</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m221\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m153\">field</a> </span></span>=<span class=\"f\"> 14</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">$lambda</a>($operator,+,<a title=\"pkg.Ex\" href=\"#m130\">Ex</a>,<a title=\"pkg.Ex\" href=\"#m130\">Ex</a>)</span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m199\">one</a>;.</span>,<span class=\"f\"><a href=\"#m221\">two</a>;.</span>)</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                " <a name=\"m28\">ONE</a>{}:\n" +
                " $public $int <a name=\"m49\">method</a>(){\n" +
                "  $return <span class=\"f\">5</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m92\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m123\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m28\">$lambda</a>(<a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a>,,ONE)</span>.<span class=\"f\"><b>call</b>()</span></span>.<span class=\"f\"><a title=\"pkg.ExEnum.method()\" href=\"#m49\">method</a>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $int <span class=\"f\"><span class=\"f\"><a name=\"m38\">one</a></span>,<span class=\"f\"><span class=\"f\"><a name=\"m42\">two</a></span>=<span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a></span></span></span>:\n" +
                " $public $static $int <a name=\"m73\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m92\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m122\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m15\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m92\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m92\">one</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m122\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.one\" href=\"#m38\">one</a> </span></span>=<span class=\"f\"> 3</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m122\">two</a>;.</span><span class=\"f\"><a title=\"pkg.Ex.two\" href=\"#m42\">two</a> </span></span>=<span class=\"f\"> 4</span></span>:\n" +
                "  $return <span class=\"f\">5</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m137\">pkg.Ex</a>:<a title=\"pkg.ExTwo\" href=\"#m400\">ExTwo</a> {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m193\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m223\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m223\">two</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span class=\"f\">$superaccess(<a title=\"pkg.ExTwo\" href=\"#m400\">ExTwo</a>)<a title=\"pkg.ExTwo.method()\" href=\"#m447\">method</a>()</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m358\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m400\">pkg.ExTwo </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m426\">field</a></span>:\n" +
                " $public $int <a name=\"m447\">method</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m137\">pkg.Ex</a>:<a title=\"pkg.ExTwo\" href=\"#m400\">ExTwo</a> {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m193\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m223\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m223\">two</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span class=\"f\">$classchoice(<a title=\"pkg.ExTwo\" href=\"#m400\">ExTwo</a>)<a title=\"pkg.ExTwo.method()\" href=\"#m447\">method</a>()</span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m358\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m400\">pkg.ExTwo </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m426\">field</a></span>:\n" +
                " $public $int <a name=\"m447\">method</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m426\">field</a></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $char <a name=\"m47\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"s\">'\\''</span></span>:\n" +
                " }\n" +
                " $public $static String <a name=\"m100\">exmeth2</a>(){\n" +
                "  $return <span class=\"n\"><span class=\"n\"><span class=\"s\">\"\"</span></span>+<span class=\"n\"><span class=\"s\">'\\''</span></span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m81\">c</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span><a title=\"true\">&amp;</a>=<span class=\"p\"> <a href=\"#m81\">c</a>;.</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m81\">c</a> </span>=<span class=\"f\"> $false</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span><a title=\"false\">&amp;</a>=<span class=\"p\"> <a href=\"#m81\">c</a>;.</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"f\">$for ($boolean <a name=\"m91\">c</a></span>:<span class=\"f\">{<span class=\"f\">$true</span>,<span class=\"f\">$false</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span>&amp;=<span class=\"f\"> <a href=\"#m91\">c</a>;</span></span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"p\">$for ($boolean <a name=\"m91\">c</a></span>:<span class=\"f\">{}</span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m63\">b</a>;. </span>&amp;=<span class=\"n\"> <a href=\"#m91\">c</a>;</span></span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span>&amp;=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span>&amp;=<span class=\"f\"> $false</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <span class=\"f\">$for ($boolean <a name=\"m73\">b</a></span>:<span class=\"f\">{<span class=\"f\">$true</span>,<span class=\"f\">$false</span>}</span>){\n" +
                "   <span class=\"f\">$for ($boolean <a name=\"m110\">c</a></span>:<span class=\"f\">{<span class=\"f\">$false</span>,<span class=\"f\">$true</span>}</span>){\n" +
                "    <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m138\">loc</a> </span>=<span class=\"f\"> <a href=\"#m73\">b</a>;</span></span>:\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m138\">loc</a>;. </span>&amp;=<span class=\"f\"> <a href=\"#m110\">c</a>;</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"f\">$for ($boolean <a name=\"m91\">c</a></span>:<span class=\"f\">{<span class=\"f\">$true</span>,<span class=\"f\">$false</span>,<span class=\"f\">$true</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m63\">b</a>;. </span>&amp;=<span class=\"f\"> <a href=\"#m91\">c</a>;</span></span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\">0</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>,<span class=\"f\"><span class=\"f\"> <a name=\"m64\">u</a> </span>=<span class=\"f\"> 2</span></span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m79\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1 </span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><span class=\"f\"> <a href=\"#m64\">u</a> </span><a title=\"true\">==</a><span class=\"f\"> 2</span></span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"n\">{<span class=\"n\">6i</span>}</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m79\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><span class=\"f\"><a name=\"m75\">i</a> </span>=<span class=\"f\"> 0</span></span>,<span class=\"f\"><span class=\"f\"> <a name=\"m82\">j</a> </span>=<span class=\"f\"> 1</span></span></span>; <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a> </span>&lt;<span class=\"f\"> 2 </span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><span class=\"f\"> <a href=\"#m82\">j</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 4</span></span></span>; <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a></span>++</span>,<span class=\"f\"><span class=\"f\"><a href=\"#m82\">j</a></span>++</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m57\">s</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m75\">i</a> </span>+<span class=\"f\"> <a href=\"#m82\">j</a></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><span class=\"f\"><a name=\"m75\">i</a> </span>=<span class=\"f\"> 0</span></span>,<span class=\"f\"><span class=\"f\"> <a name=\"m82\">j</a> </span>=<span class=\"f\"> <a href=\"#m75\">i</a></span></span></span>; <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a> </span>&lt;<span class=\"f\"> 2 </span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><span class=\"f\"> <a href=\"#m82\">j</a> </span><a title=\"true\">&lt;</a><span class=\"f\"> 4</span></span></span>; <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a></span>++</span>,<span class=\"f\"><span class=\"f\"><a href=\"#m82\">j</a></span>++</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m57\">s</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m75\">i</a> </span>+<span class=\"f\"> <a href=\"#m82\">j</a></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>,<span class=\"f\"><span class=\"f\"> <a name=\"m64\">u</a> </span>=<span class=\"f\"> <a href=\"#m57\">s</a></span></span></span>;\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m79\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"true\">==</a><span class=\"f\"> 1 </span></span><a title=\"true\">&amp;</a><a title=\"false\">&amp;</a><span class=\"p\"><span class=\"f\"> <a href=\"#m64\">u</a> </span><a title=\"false\">==</a><span class=\"f\"> 2</span></span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m79\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><span class=\"f\"><a name=\"m75\">i</a> </span>=<span class=\"f\"> 0</span></span>,<span class=\"f\"> <a name=\"m82\">j</a></span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a> </span>&lt;<span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a></span>++</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m57\">s</a> </span>+=<span class=\"f\"> <a href=\"#m75\">i</a></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m75\">i</a></span> : <span class=\"f\">{<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m57\">s</a> </span>+=<span class=\"f\"> ([<a href=\"#m75\">i</a>])</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $int <span class=\"f\"><span class=\"f\"><a name=\"m63\">s</a> </span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\">$for ($int <a name=\"m83\">i</a></span> : <span class=\"f\">{<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m63\">s</a>;. </span>+=<span class=\"f\"> <a href=\"#m83\">i</a>;;</span></span>:\n" +
                "  }\n" +
                "  $return <span class=\"f\"><a href=\"#m63\">s</a>;.</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m75\">i</a></span> : <span class=\"f\">{<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m57\">s</a> </span>+=<span class=\"f\"> <a href=\"#m75\">i</a></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $int <span class=\"f\"><a name=\"m65\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span>=<span class=\"f\">0i</span></span>:\n" +
                "  $try{\n" +
                "   <span class=\"p\">$if</span>(<span class=\"p\"><span class=\"f\"><a href=\"#m65\">t</a>;.</span><a title=\"true\">&gt;=</a><span class=\"f\">0</span></span>){\n" +
                "    $throw <span class=\"f\">1i</span>:\n" +
                "   }\n" +
                "   $return <span class=\"n\">2</span>:\n" +
                "  } <span class=\"f\">$catch</span>($int <a name=\"m152\">o</a>){\n" +
                "   $return <span class=\"f\"><a href=\"#m152\">o</a>;..</span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExTwo\" href=\"../pkg2/Ex.html#m15\">ExTwo</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../pkg2/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExTwo\" href=\"../Ex.html#m15\">ExTwo</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../Ex.html#m49\">exmeth</a>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExTwo\" href=\"inner/Ex.html#m15\">ExTwo</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"inner/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExTwo\" href=\"../pkg2/Ex.html#m15\">ExTwo</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../pkg2/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExTwo\" href=\"../../d2/pkg2/Ex.html#m15\">ExTwo</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.$static exmeth()\" href=\"../../d2/pkg2/Ex.html#m49\">exmeth</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  for (int i:{10,9,8}){\n");
        xml_.append("   switch(i){\n");
        xml_.append("    case 10:\n");
        xml_.append("    case 8:\n");
        xml_.append("     t+=12;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  long <span class=\"f\"><a name=\"m58\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>=<span class=\"f\">10</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m80\">i</a></span>:<span class=\"f\">{<span class=\"f\">10</span>,<span class=\"f\">9</span>,<span class=\"f\">8</span>}</span>){\n" +
                "   <span class=\"f\"><a title=\"3/3\">switch</a></span>(<span class=\"f\"><a href=\"#m80\">i</a></span>){\n" +
                "    case <span class=\"f\">10</span>:\n" +
                "    case <span class=\"f\">8</span>:\n" +
                "     <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>+=<span class=\"f\">12</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m58\">t</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  for (int i:{10,9,8}){\n");
        xml_.append("   switch(i){\n");
        xml_.append("    case 10:\n");
        xml_.append("    case 8:\n");
        xml_.append("     t+=12;\n");
        xml_.append("     break;\n");
        xml_.append("    default:\n");
        xml_.append("     t+=14;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  long <span class=\"f\"><a name=\"m58\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>=<span class=\"f\">10</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m80\">i</a></span>:<span class=\"f\">{<span class=\"f\">10</span>,<span class=\"f\">9</span>,<span class=\"f\">8</span>}</span>){\n" +
                "   <span class=\"f\"><a title=\"3/3\">switch</a></span>(<span class=\"f\"><a href=\"#m80\">i</a></span>){\n" +
                "    case <span class=\"f\">10</span>:\n" +
                "    case <span class=\"f\">8</span>:\n" +
                "     <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>+=<span class=\"f\">12</span></span>;\n" +
                "     break;\n" +
                "    <span class=\"f\">default</span>:\n" +
                "     <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>+=<span class=\"f\">14</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m58\">t</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  for (int i:{}){\n");
        xml_.append("   switch(i){\n");
        xml_.append("    case 10:\n");
        xml_.append("    case 8:\n");
        xml_.append("     t+=12;\n");
        xml_.append("     break;\n");
        xml_.append("    default:\n");
        xml_.append("     t+=14;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  long <span class=\"f\"><a name=\"m58\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>=<span class=\"f\">10</span></span>;\n" +
                "  <span class=\"p\">for (int <a name=\"m80\">i</a></span>:<span class=\"f\">{}</span>){\n" +
                "   <span class=\"n\"><a title=\"0/3\">switch</a></span>(<span class=\"n\"><a href=\"#m80\">i</a></span>){\n" +
                "    case <span class=\"n\">10</span>:\n" +
                "    case <span class=\"n\">8</span>:\n" +
                "     <span class=\"n\"><span class=\"n\"><a href=\"#m58\">t</a></span>+=<span class=\"n\">12</span></span>;\n" +
                "     break;\n" +
                "    <span class=\"n\">default</span>:\n" +
                "     <span class=\"n\"><span class=\"n\"><a href=\"#m58\">t</a></span>+=<span class=\"n\">14</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m58\">t</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage132Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  for (int i:{10,8}){\n");
        xml_.append("   switch(i){\n");
        xml_.append("    case 10:\n");
        xml_.append("    case 8:\n");
        xml_.append("     t+=12;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  long <span class=\"f\"><a name=\"m58\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>=<span class=\"f\">10</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m80\">i</a></span>:<span class=\"f\">{<span class=\"f\">10</span>,<span class=\"f\">8</span>}</span>){\n" +
                "   <span class=\"p\"><a title=\"2/3\">switch</a></span>(<span class=\"f\"><a href=\"#m80\">i</a></span>){\n" +
                "    case <span class=\"f\">10</span>:\n" +
                "    case <span class=\"f\">8</span>:\n" +
                "     <span class=\"f\"><span class=\"f\"><a href=\"#m58\">t</a></span>+=<span class=\"f\">12</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m58\">t</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage133Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $iterable<java.lang.Number> iter = Ex.inst:\n");
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">3i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">1i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">2i</span>)</span></span>:\n" +
                "  $iterable&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m238\">iter</a> </span>=<span class=\"g\"><span class=\"g\"> <a title=\"pkg.Ex\" href=\"#m15\">Ex</a></span>.<span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a></span></span></span>:\n" +
                "  <b title=\"java.lang.$iterator&lt;java.lang.Number&gt;\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m261\">it</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m238\">iter</a>;.</span><span class=\"g\">iterator()</span></span></span>:\n" +
                "  <span class=\"f\">$while</span>(<span class=\"g\"><span class=\"g\"><a href=\"#m261\">it</a>;.</span><span class=\"g\">hasNext()</span></span>){\n" +
                "   <b title=\"java.lang.Number\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m317\">l</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m261\">it</a>;.</span><span class=\"g\">next()</span></span></span>:\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"g\"><span class=\"g\"><a href=\"#m317\">l</a>;.</span><span class=\"g\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m388\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $iterable<java.lang.Number> iter = Ex. inst:\n");
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m72\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustList.pkg.CustList()\" href=\"CustList.html#m93\">$new</a> <a title=\"pkg.CustList\" href=\"CustList.html#m15\">pkg.CustList</a>&lt;java.lang.Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m138\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">3i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">1i</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a>;;;</span><span class=\"g\"><a title=\"pkg.CustList.add(#U)\" href=\"CustList.html#m154\">add</a>(<span class=\"g\">2i</span>)</span></span>:\n" +
                "  $iterable&lt;java.lang.Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m238\">iter</a> </span>=<span class=\"g\"><span class=\"g\"> <a title=\"pkg.Ex\" href=\"#m15\">Ex</a></span>.<span class=\"g\"> <a title=\"pkg.Ex.inst\" href=\"#m72\">inst</a></span></span></span>:\n" +
                "  <b title=\"java.lang.$iterator&lt;java.lang.Number&gt;\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m262\">it</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m238\">iter</a>;.</span><span class=\"g\">iterator()</span></span></span>:\n" +
                "  <span class=\"f\">$while</span>(<span class=\"g\"><span class=\"g\"><a href=\"#m262\">it</a>;.</span><span class=\"g\">hasNext()</span></span>){\n" +
                "   <b title=\"java.lang.Number\">$var</b> <span class=\"g\"><span class=\"g\"><a name=\"m318\">l</a> </span>=<span class=\"g\"><span class=\"g\"> <a href=\"#m262\">it</a>;.</span><span class=\"g\">next()</span></span></span>:\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a>;;;</span>+=<span class=\"g\"><span class=\"g\"><a href=\"#m318\">l</a>;.</span><span class=\"g\">intValue()</span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m389\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m138\">res</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (var j:new int[]{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for (<b title=\"int\">var</b> <a name=\"m90\">j</a></span>:<span class=\"f\">new int[]{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span>){\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m118\">t</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m57\">s</a> </span>==<span class=\"f\"> <a href=\"#m90\">j</a> </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m118\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage136Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for($var f, $var s: inst){\n");
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt; <span class=\"g\"><span class=\"g\"><a name=\"m66\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;Number,Number&gt;()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m126\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">3</span>,<span class=\"g\">5</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">8</span>,<span class=\"g\">1</span>)</span></span>:\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>.<span class=\"g\"><a title=\"pkg.CustTable.add(#U,#V)\" href=\"CustTable.html#m166\">add</a>(<span class=\"g\">2</span>,<span class=\"g\">6</span>)</span></span>:\n" +
                "  <span class=\"f\">$for(<b title=\"java.lang.Number\">$var</b> <a name=\"m205\">f</a>, <b title=\"java.lang.Number\">$var</b> <a name=\"m213\">s</a></span>: <span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m66\">inst</a></span>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a> </span>+=<span class=\"g\"><span class=\"g\"><span class=\"g\"> <a href=\"#m205\">f</a>;</span><span class=\"g\">intValue()</span></span>+<span class=\"g\"><span class=\"g\"><a href=\"#m213\">s</a>;</span><span class=\"g\">intValue()</span></span></span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m289\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m126\">res</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var seven = 8;\n");
        xml_.append("  for (var i = 0; i < 2; i++){\n");
        xml_.append("   if (!(i % 2 == 0)){\n");
        xml_.append("    seven += 15;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return seven;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <b title=\"int\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m57\">seven</a> </span>=<span class=\"f\"> 8</span></span>;\n" +
                "  <span class=\"f\">for</span> (<b title=\"int\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m79\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m79\">i</a> </span>&lt;<span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m79\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\">!<span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m79\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0</span></span>)</span></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m57\">seven</a> </span>+=<span class=\"f\"> 15</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">seven</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE,TWO\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int o = 10:\n");
        xml_.append("  ExEnum t:\n");
        xml_.append("  t;.=ExEnum.ONE:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(ONE):\n");
        xml_.append("   $case(TWO){\n");
        xml_.append("    o;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)o;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                "<a name=\"m27\">ONE</a>,<a name=\"m31\">TWO</a>\n" +
                "}\n" +
                "$public $class <a name=\"m52\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m83\">exmeth</a>(){\n" +
                "  $int <span class=\"f\"><span class=\"f\"><a name=\"m100\">o</a> </span>=<span class=\"f\"> 10</span></span>:\n" +
                "  <a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a> <span class=\"f\"><a name=\"m117\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m117\">t</a>;.</span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m27\">ONE</a></span></span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m117\">t</a>;.</span>){\n" +
                "   $case(<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m27\">ONE</a></span>):\n" +
                "   $case(<span class=\"n\"><a title=\"pkg.ExEnum.TWO\" href=\"#m31\">TWO</a></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m100\">o</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m100\">o</a>;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append("ONE,TWO\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int o = 10;\n");
        xml_.append("  ExEnum t;\n");
        xml_.append("  t=ExEnum.ONE;\n");
        xml_.append("  switch(t){\n");
        xml_.append("   case(ONE):\n");
        xml_.append("   case(TWO):\n");
        xml_.append("    o=12;\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                "<a name=\"m25\">ONE</a>,<a name=\"m29\">TWO</a>\n" +
                "}\n" +
                "public class <a name=\"m48\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m76\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m92\">o</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  <a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a> <span class=\"f\"><a name=\"m109\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m109\">t</a></span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m25\">ONE</a></span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/3\">switch</a></span>(<span class=\"f\"><a href=\"#m109\">t</a></span>){\n" +
                "   case(<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m25\">ONE</a></span>):\n" +
                "   case(<span class=\"n\"><a title=\"pkg.ExEnum.TWO\" href=\"#m29\">TWO</a></span>):\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m92\">o</a></span>=<span class=\"f\">12</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m92\">o</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE,TWO\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int o = 10:\n");
        xml_.append("  ExEnum t:\n");
        xml_.append("  t;.=ExEnum.ONE:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(ONE):\n");
        xml_.append("   $case($null){\n");
        xml_.append("    o;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)o;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                "<a name=\"m27\">ONE</a>,<a name=\"m31\">TWO</a>\n" +
                "}\n" +
                "$public $class <a name=\"m52\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m83\">exmeth</a>(){\n" +
                "  $int <span class=\"f\"><span class=\"f\"><a name=\"m100\">o</a> </span>=<span class=\"f\"> 10</span></span>:\n" +
                "  <a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a> <span class=\"f\"><a name=\"m117\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m117\">t</a>;.</span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m27\">ONE</a></span></span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m117\">t</a>;.</span>){\n" +
                "   $case(<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m27\">ONE</a></span>):\n" +
                "   $case(<span class=\"n\">$null</span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m100\">o</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m100\">o</a>;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append("ONE,TWO\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int o = 10;\n");
        xml_.append("  ExEnum t;\n");
        xml_.append("  t=ExEnum.ONE;\n");
        xml_.append("  switch(t){\n");
        xml_.append("   case(ONE):\n");
        xml_.append("   case(null):\n");
        xml_.append("    o=12;\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                "<a name=\"m25\">ONE</a>,<a name=\"m29\">TWO</a>\n" +
                "}\n" +
                "public class <a name=\"m48\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m76\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m92\">o</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  <a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a> <span class=\"f\"><a name=\"m109\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m109\">t</a></span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m25\">ONE</a></span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/3\">switch</a></span>(<span class=\"f\"><a href=\"#m109\">t</a></span>){\n" +
                "   case(<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m25\">ONE</a></span>):\n" +
                "   case(<span class=\"n\">null</span>):\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m92\">o</a></span>=<span class=\"f\">12</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m92\">o</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExEnum {\n");
        xml_.append(" $public $static $final $int ONE = 10:\n");
        xml_.append(" $public $static $final $int TWO = 8:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int o = 10:\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=ExEnum.ONE:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(ExEnum.ONE):\n");
        xml_.append("   $case(ExEnum.TWO){\n");
        xml_.append("    o;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)o;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.ExEnum </a>{\n" +
                " $public $static $final $int <span class=\"g\"><span class=\"g\"><a name=\"m57\">ONE</a> </span>=<span class=\"g\"> 10</span></span>:\n" +
                " $public $static $final $int <span class=\"g\"><span class=\"g\"><a name=\"m96\">TWO</a> </span>=<span class=\"g\"> 8</span></span>:\n" +
                "}\n" +
                "$public $class <a name=\"m122\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m153\">exmeth</a>(){\n" +
                "  $int <span class=\"f\"><span class=\"f\"><a name=\"m170\">o</a> </span>=<span class=\"f\"> 10</span></span>:\n" +
                "  $int <span class=\"f\"><a name=\"m185\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m185\">t</a>;.</span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m15\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m57\">ONE</a></span></span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m185\">t</a>;.</span>){\n" +
                "   $case(<span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m15\">ExEnum</a>.<a title=\"pkg.ExEnum.ONE\" href=\"#m57\">ONE</a></span>):\n" +
                "   $case(<span class=\"n\"><a title=\"pkg.ExEnum\" href=\"#m15\">ExEnum</a>.<a title=\"pkg.ExEnum.TWO\" href=\"#m96\">TWO</a></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m170\">o</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m170\">o</a>;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExEnum {\n");
        xml_.append(" public static final int ONE = 10;\n");
        xml_.append(" public static final int TWO = 8;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int o = 10;\n");
        xml_.append("  int t;\n");
        xml_.append("  t=ExEnum.ONE;\n");
        xml_.append("  switch(t){\n");
        xml_.append("   case(ExEnum.ONE):\n");
        xml_.append("   case(ExEnum.TWO):\n");
        xml_.append("    o=12;\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.ExEnum </a>{\n" +
                " public static final int <span class=\"g\"><span class=\"g\"><a name=\"m51\">ONE</a> </span>=<span class=\"g\"> 10</span></span>;\n" +
                " public static final int <span class=\"g\"><span class=\"g\"><a name=\"m86\">TWO</a> </span>=<span class=\"g\"> 8</span></span>;\n" +
                "}\n" +
                "public class <a name=\"m110\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m138\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m154\">o</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  int <span class=\"f\"><a name=\"m168\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m168\">t</a></span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m13\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m51\">ONE</a></span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/3\">switch</a></span>(<span class=\"f\"><a href=\"#m168\">t</a></span>){\n" +
                "   case(<span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m13\">ExEnum</a>.<a title=\"pkg.ExEnum.ONE\" href=\"#m51\">ONE</a></span>):\n" +
                "   case(<span class=\"n\"><a title=\"pkg.ExEnum\" href=\"#m13\">ExEnum</a>.<a title=\"pkg.ExEnum.TWO\" href=\"#m86\">TWO</a></span>):\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m154\">o</a></span>=<span class=\"f\">12</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m154\">o</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage144Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE,TWO:\n");
        xml_.append(" $public $static $final $int THREE = 9:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int o = 10:\n");
        xml_.append("  ExEnum t:\n");
        xml_.append("  t;.=ExEnum.ONE:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(ONE):\n");
        xml_.append("   $case(TWO){\n");
        xml_.append("    o;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)o;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.ExEnum </a>{\n" +
                "<a name=\"m27\">ONE</a>,<a name=\"m31\">TWO</a>:\n" +
                " $public $static $final $int <span class=\"g\"><span class=\"g\"><a name=\"m65\">THREE</a> </span>=<span class=\"g\"> 9</span></span>:\n" +
                "}\n" +
                "$public $class <a name=\"m93\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m124\">exmeth</a>(){\n" +
                "  $int <span class=\"f\"><span class=\"f\"><a name=\"m141\">o</a> </span>=<span class=\"f\"> 10</span></span>:\n" +
                "  <a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a> <span class=\"f\"><a name=\"m158\">t</a></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m158\">t</a>;.</span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m14\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m27\">ONE</a></span></span></span>:\n" +
                "  <span class=\"p\"><a title=\"1/3\">$switch</a></span>(<span class=\"f\"><a href=\"#m158\">t</a>;.</span>){\n" +
                "   $case(<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m27\">ONE</a></span>):\n" +
                "   $case(<span class=\"n\"><a title=\"pkg.ExEnum.TWO\" href=\"#m31\">TWO</a></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m141\">o</a>;.</span>=<span class=\"f\">12</span></span>:\n" +
                "   }\n" +
                "  }\n" +
                "  $return <span class=\"f\"><span class=\"f\">1i</span>+<span class=\"f\">$($int)<span class=\"f\"><a href=\"#m141\">o</a>;.</span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage145Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append("ONE,TWO;\n");
        xml_.append(" public static final int THREE = 9;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int o = 10;\n");
        xml_.append("  ExEnum t;\n");
        xml_.append("  t=ExEnum.ONE;\n");
        xml_.append("  switch(t){\n");
        xml_.append("   case(ONE):\n");
        xml_.append("   case(TWO):\n");
        xml_.append("    o=12;\n");
        xml_.append("  }\n");
        xml_.append("  return 1+(int)o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum </a>{\n" +
                "<a name=\"m25\">ONE</a>,<a name=\"m29\">TWO</a>;\n" +
                " public static final int <span class=\"g\"><span class=\"g\"><a name=\"m59\">THREE</a> </span>=<span class=\"g\"> 9</span></span>;\n" +
                "}\n" +
                "public class <a name=\"m85\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m113\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m129\">o</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  <a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a> <span class=\"f\"><a name=\"m146\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m146\">t</a></span>=<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m25\">ONE</a></span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/3\">switch</a></span>(<span class=\"f\"><a href=\"#m146\">t</a></span>){\n" +
                "   case(<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m25\">ONE</a></span>):\n" +
                "   case(<span class=\"n\"><a title=\"pkg.ExEnum.TWO\" href=\"#m29\">TWO</a></span>):\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m129\">o</a></span>=<span class=\"f\">12</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><span class=\"f\">1</span>+<span class=\"f\">(int)<span class=\"f\"><a href=\"#m129\">o</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage146Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}) lab {\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   break lab;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">for (int <a name=\"m90\">j</a></span>:<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span>) <a name=\"m99\">lab</a> {\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m114\">t</a> </span>=<span class=\"f\"><span class=\"p\"><span class=\"f\"> <a href=\"#m57\">s</a> </span><a title=\"false\">==</a><span class=\"f\"> <a href=\"#m90\">j</a> </span></span>?<span class=\"n\">{<span class=\"n\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m114\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   break <a href=\"#99\">lab</a>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage147Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}) lab {\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   continue lab;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m70\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m90\">j</a></span>:<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span>) <a name=\"m99\">lab</a> {\n" +
                "   int[] <span class=\"f\"><span class=\"f\"><a name=\"m114\">t</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m57\">s</a> </span>==<span class=\"f\"> <a href=\"#m90\">j</a> </span></span>?<span class=\"f\">{<span class=\"f\">4i</span>}</span>:<span class=\"f\">{<span class=\"f\">6i</span>}</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m70\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m114\">t</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "   continue <a href=\"#99\">lab</a>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m70\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage148Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex exmeth(){\n");
        xml_.append("  $return (Ex)$null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.Ex\" href=\"#m15\">Ex</a> <a name=\"m44\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>)<span class=\"f\">$null</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage149Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Ex exmeth(){\n");
        xml_.append("  return (Ex)null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m40\">exmeth</a>(){\n" +
                "  return <span class=\"f\">(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)<span class=\"f\">null</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage150Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex exmeth(){\n");
        xml_.append("  $return $(Ex)$null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.Ex\" href=\"#m15\">Ex</a> <a name=\"m44\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">$(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>)<span class=\"f\">$null</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Ex exmeth(){\n");
        xml_.append("  return $(Ex)null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m40\">exmeth</a>(){\n" +
                "  return <span class=\"f\">$(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)<span class=\"f\">null</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage152Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int i=0:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(Ex).i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <span class=\"g\"><span class=\"g\"><a name=\"m46\">i</a></span>=<span class=\"g\">0</span></span>:\n" +
                " $public $static $int <a name=\"m73\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\">$static(<a title=\"pkg.Ex\" href=\"#m15\">Ex</a>)</span>.<span class=\"f\"><a title=\"pkg.Ex.i\" href=\"#m46\">i</a></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage153Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int i=0;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return static(Ex).i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">i</a></span>=<span class=\"g\">0</span></span>;\n" +
                " public static int <a name=\"m65\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">static(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span>.<span class=\"f\"><a title=\"pkg.Ex.i\" href=\"#m41\">i</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage154Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  return null instanceof Ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  return <span class=\"p\"><span class=\"f\">null </span>instanceof <a title=\"pkg.Ex\" href=\"#m13\">Ex</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage155Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<#T> {\n");
        xml_.append(" public static class ExTwo{}\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  return null instanceof Ex<ExTwo>;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefaultSingle();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">#T</a>&gt; {\n" +
                " public static class <a name=\"m47\">ExTwo</a>{}\n" +
                " public static boolean <a name=\"m78\">exmeth</a>(){\n" +
                "  return <span class=\"p\"><span class=\"f\">null </span>instanceof <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;<a title=\"pkg.Ex..ExTwo\" href=\"#m47\">ExTwo</a>&gt;</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage156Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class Inner<U> {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public(int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  public int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int call(T p){\n");
        xml_.append("  Fct<int,Ex<T>.Inner<T>> c = this.$lambda(Inner<T>,new,int);\n");
        xml_.append("  Ex<T>.Inner<T> instance = c.call(14);\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" public int call2(T p){\n");
        xml_.append("  return call(p);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex<String> instance = new Ex<String>();\n");
        xml_.append("  return instance.call2(\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefaultSingle();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public class <a name=\"m39\">Inner</a>&lt;<a name=\"m45\">U</a>&gt; {\n" +
                "  public int <span class=\"f\"><a name=\"m63\">field</a></span>;\n" +
                "  <a name=\"m72\">public(</a>int <a name=\"m83\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex..Inner.field\" href=\"#m63\">field</a></span>=<span class=\"f\"><a href=\"#m83\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m116\">get</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ex..Inner.field\" href=\"#m63\">field</a></span>;\n" +
                "  }\n" +
                " }\n" +
                " public int <a name=\"m159\">call</a>(<a href=\"#m20\">T</a> <a name=\"m166\">p</a>){\n" +
                "  Fct&lt;int,<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;<a href=\"#m20\">T</a>&gt;.<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;<a href=\"#m20\">T</a>&gt;&gt; <span class=\"f\"><span class=\"f\"><a name=\"m196\">c</a> </span>=<span class=\"f\"><span class=\"f\"> this</span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m72\">$lambda</a>(<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;<a href=\"#m20\">T</a>&gt;,new,int)</span></span></span>;\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;<a href=\"#m20\">T</a>&gt;.<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;<a href=\"#m20\">T</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m249\">instance</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m196\">c</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">14</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m249\">instance</a></span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.get()\" href=\"#m116\">get</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m312\">call2</a>(<a href=\"#m20\">T</a> <a name=\"m320\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.call(#T)\" href=\"#m159\">call</a>(<span class=\"f\"><a href=\"#m320\">p</a></span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m364\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;String&gt; <span class=\"f\"><span class=\"f\"><a name=\"m387\">instance</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;String&gt;()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m387\">instance</a></span>.<span class=\"f\"><a title=\"pkg.Ex.call2(#T)\" href=\"#m312\">call2</a>(<span class=\"f\"><span class=\"s\">\"\"</span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage157Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class Inner<U> {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public(U p){\n");
        xml_.append("   field=(int)p;\n");
        xml_.append("  }\n");
        xml_.append("  public int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public int call(T p){\n");
        xml_.append("  Fct<T,Ex<T>.Inner<T>> c = this.$lambda(Inner<T>,new,T);\n");
        xml_.append("  Ex<T>.Inner<T> instance = c.call((T)14);\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" public int call2(T p){\n");
        xml_.append("  return call(p);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex<int> instance = new Ex<int>();\n");
        xml_.append("  return instance.call2(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefaultSingle();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public class <a name=\"m39\">Inner</a>&lt;<a name=\"m45\">U</a>&gt; {\n" +
                "  public int <span class=\"f\"><a name=\"m63\">field</a></span>;\n" +
                "  <a name=\"m72\">public(</a><a href=\"#m45\">U</a> <a name=\"m81\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex..Inner.field\" href=\"#m63\">field</a></span>=<span class=\"f\">(int)<span class=\"f\"><a href=\"#m81\">p</a></span></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m119\">get</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ex..Inner.field\" href=\"#m63\">field</a></span>;\n" +
                "  }\n" +
                " }\n" +
                " public int <a name=\"m162\">call</a>(<a href=\"#m20\">T</a> <a name=\"m169\">p</a>){\n" +
                "  Fct&lt;<a href=\"#m20\">T</a>,<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;<a href=\"#m20\">T</a>&gt;.<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;<a href=\"#m20\">T</a>&gt;&gt; <span class=\"f\"><span class=\"f\"><a name=\"m197\">c</a> </span>=<span class=\"f\"><span class=\"f\"> this</span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.pkg.Ex..Inner(#U)\" href=\"#m72\">$lambda</a>(<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;<a href=\"#m20\">T</a>&gt;,new,<a href=\"#m20\">T</a>)</span></span></span>;\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;<a href=\"#m20\">T</a>&gt;.<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;<a href=\"#m20\">T</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m248\">instance</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m197\">c</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">(<a href=\"#m20\">T</a>)<span class=\"f\">14</span></span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m248\">instance</a></span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.get()\" href=\"#m119\">get</a>()</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m314\">call2</a>(<a href=\"#m20\">T</a> <a name=\"m322\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.call(#T)\" href=\"#m162\">call</a>(<span class=\"f\"><a href=\"#m322\">p</a></span>)</span>;\n" +
                " }\n" +
                " public static int <a name=\"m366\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt; <span class=\"f\"><span class=\"f\"><a name=\"m386\">instance</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m386\">instance</a></span>.<span class=\"f\"><a title=\"pkg.Ex.call2(#T)\" href=\"#m314\">call2</a>(<span class=\"f\">0</span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage158Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().$lambda(Ex,inst,$id,Ex).call(null);\n");
        xml_.append(" }\n");
        xml_.append(" public int inst(Ex p){\n");
        xml_.append("  return \"\".length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.inst(pkg.Ex)\" href=\"#m120\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>,inst,$id,<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">null</span>)</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m120\">inst</a>(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m128\">p</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">length()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage159Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Ex exmeth(){\n");
        xml_.append("  return defaultValue(Ex);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m40\">exmeth</a>(){\n" +
                "  return <span class=\"f\">defaultValue(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage160Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().inst($id(Ex,Ex),null);\n");
        xml_.append(" }\n");
        xml_.append(" public int inst(Ex p){\n");
        xml_.append("  return \"\".length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.inst(pkg.Ex)\" href=\"#m107\">inst</a>(<span class=\"f\">$id(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>,<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span>,<span class=\"f\">null</span>)</span></span>;\n" +
                " }\n" +
                " public int <a name=\"m107\">inst</a>(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m115\">p</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">length()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage161Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new ExTwo<Integer>().inst($id(ExTwo,#T),null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public int inst(T p){\n");
        xml_.append("  return \"\".length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex2", xml_.toString());
        ContextEl cont_ = contextElCoverageEnDefault();
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.ExTwo\" href=\"Ex2.html#m13\">ExTwo</a>&lt;Integer&gt;()</span>.<span class=\"f\"><a title=\"pkg.ExTwo.inst(#T)\" href=\"Ex2.html#m40\">inst</a>(<span class=\"f\">$id(<a title=\"pkg.ExTwo\" href=\"Ex2.html#m13\">ExTwo</a>,<a href=\"Ex2.html#m23\">#T</a>)</span>,<span class=\"f\">null</span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage162Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().inst($id(Ex,static,Ex),null);\n");
        xml_.append(" }\n");
        xml_.append(" public static int inst(Ex p){\n");
        xml_.append("  return \"\".length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.static inst(pkg.Ex)\" href=\"#m121\">inst</a>(<span class=\"f\">$id(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>,static,<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span>,<span class=\"f\">null</span>)</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m121\">inst</a>(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m129\">p</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">length()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage163Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static Ex<int> exmeth(){\n");
        xml_.append("  return new Ex<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt; <a name=\"m48\">exmeth</a>(){\n" +
                "  return <span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a><a title=\"pkg.Ex&lt;int&gt;\">&lt;&gt;</a>()</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage164Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class Inner<S> {\n");
        xml_.append(" }\n");
        xml_.append(" public Inner<int> exmeth(){\n");
        xml_.append("  return new Inner<>();\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex<int>.Inner<int> exmeth(){\n");
        xml_.append("  return new Ex<int>().exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefaultSingle();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public class <a name=\"m39\">Inner</a>&lt;<a name=\"m45\">S</a>&gt; {\n" +
                " }\n" +
                " public <a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;int&gt; <a name=\"m72\">exmeth</a>(){\n" +
                "  return <span class=\"f\">new <a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a><a title=\"pkg.Ex&lt;#T&gt;..Inner&lt;int&gt;\">&lt;&gt;</a>()</span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;.<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;int&gt; <a name=\"m143\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;()</span>.<span class=\"f\"><a title=\"pkg.Ex.exmeth()\" href=\"#m72\">exmeth</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage165Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public class Inner<S> {\n");
        xml_.append(" }\n");
        xml_.append(" public static Ex<int>.Inner<int> exmeth(){\n");
        xml_.append("  return new Ex<int>().new Inner<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefaultSingle();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public class <a name=\"m39\">Inner</a>&lt;<a name=\"m45\">S</a>&gt; {\n" +
                " }\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;.<a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a>&lt;int&gt; <a name=\"m87\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;()</span>.<span class=\"f\">new <a title=\"pkg.Ex..Inner\" href=\"#m39\">Inner</a><a title=\"pkg.Ex&lt;int&gt;..Inner&lt;int&gt;\">&lt;&gt;</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage166Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static Ex<int>[] exmeth(){\n");
        xml_.append("  return new Ex<>[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;[] <a name=\"m50\">exmeth</a>(){\n" +
                "  return <span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a><a title=\"pkg.Ex&lt;int&gt;\">&lt;&gt;</a>[<span class=\"f\">0</span>]</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage167Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex<T> {\n");
        xml_.append(" public static Ex<?> exmeth(){\n");
        xml_.append("  return new Ex<int>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefaultSingle();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex</a>&lt;<a name=\"m20\">T</a>&gt; {\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;?&gt; <a name=\"m46\">exmeth</a>(){\n" +
                "  return <span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>&lt;int&gt;()</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage168Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Ex[] exmeth(){\n");
        xml_.append("  return new Ex[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>[] <a name=\"m42\">exmeth</a>(){\n" +
                "  return <span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>[]{}</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage169Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Ex[] exmeth(){\n");
        xml_.append("  return {};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>[] <a name=\"m42\">exmeth</a>(){\n" +
                "  return <span class=\"f\">{}</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage170Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().inst($vararg(Ex),$firstopt(null));\n");
        xml_.append(" }\n");
        xml_.append(" public static int inst(Ex... p){\n");
        xml_.append("  return \"\".length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.static inst(pkg.Ex...)\" href=\"#m126\">inst</a>(<span class=\"f\">$vararg(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span>,<span class=\"f\">$firstopt(<span class=\"f\">null</span>)</span>)</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m126\">inst</a>(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>... <a name=\"m137\">p</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">length()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage171Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().inst($vararg(Ex));\n");
        xml_.append(" }\n");
        xml_.append(" public static int inst(Ex... p){\n");
        xml_.append("  return \"\".length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span>.<span class=\"f\"><a title=\"pkg.Ex.static inst(pkg.Ex...)\" href=\"#m110\">inst</a>(<span class=\"f\">$vararg(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span>)</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m110\">inst</a>(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>... <a name=\"m121\">p</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"s\">\"\"</span></span>.<span class=\"f\">length()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage172Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExSuper {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public int this(int param){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int param){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex:ExSuper {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  return e.super[8];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.ExSuper </a>{\n" +
                " public int <span class=\"f\"><a name=\"m39\">field</a></span>;\n" +
                " public int <a name=\"m58\">this</a>(int <a name=\"m67\">param</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExSuper.field\" href=\"#m39\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m107\">this</a>(int <a name=\"m116\">param</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.ExSuper.field\" href=\"#m39\">field</a> </span>=<span class=\"n\"> <b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m159\">pkg.Ex</a>:<a title=\"pkg.ExSuper\" href=\"#m13\">ExSuper</a> {\n" +
                " public static int <a name=\"m195\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m159\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m210\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m159\">Ex</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m210\">e</a></span>.<span class=\"f\">super</span></span><span class=\"f\"><a title=\"pkg.ExSuper.[](int)\" href=\"#m58\">[</a><span class=\"f\">8</span><a title=\"pkg.ExSuper.[](int)\" href=\"#m58\">]</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage173Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  return e.that[8];\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int param){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int param){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m34\">field</a></span>;\n" +
                " public static int <a name=\"m60\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m75\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">e</a></span>.<span class=\"f\">that</span></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m124\">[</a><span class=\"f\">8</span><a title=\"pkg.Ex.[](int)\" href=\"#m124\">]</a></span></span>;\n" +
                " }\n" +
                " public int <a name=\"m124\">this</a>(int <a name=\"m133\">param</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m173\">this</a>(int <a name=\"m182\">param</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a> </span>=<span class=\"n\"> <b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage174Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  return e.classchoice(Ex)[8];\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int param){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int param){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m34\">field</a></span>;\n" +
                " public static int <a name=\"m60\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m75\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">e</a></span>.<span class=\"f\">classchoice(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m135\">[</a><span class=\"f\">8</span><a title=\"pkg.Ex.[](int)\" href=\"#m135\">]</a></span></span>;\n" +
                " }\n" +
                " public int <a name=\"m135\">this</a>(int <a name=\"m144\">param</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m184\">this</a>(int <a name=\"m193\">param</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a> </span>=<span class=\"n\"> <b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage175Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  return e.thisaccess(Ex)[8];\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int param){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int param){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m34\">field</a></span>;\n" +
                " public static int <a name=\"m60\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m75\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">e</a></span>.<span class=\"f\">thisaccess(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m134\">[</a><span class=\"f\">8</span><a title=\"pkg.Ex.[](int)\" href=\"#m134\">]</a></span></span>;\n" +
                " }\n" +
                " public int <a name=\"m134\">this</a>(int <a name=\"m143\">param</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m183\">this</a>(int <a name=\"m192\">param</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a> </span>=<span class=\"n\"> <b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage176Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex();\n");
        xml_.append("  return e.superaccess(Ex)[8];\n");
        xml_.append(" }\n");
        xml_.append(" public int this(int param){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int param){\n");
        xml_.append("  field = value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><a name=\"m34\">field</a></span>;\n" +
                " public static int <a name=\"m60\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m75\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m75\">e</a></span>.<span class=\"f\">superaccess(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>)</span></span><span class=\"f\"><a title=\"pkg.Ex.[](int)\" href=\"#m135\">[</a><span class=\"f\">8</span><a title=\"pkg.Ex.[](int)\" href=\"#m135\">]</a></span></span>;\n" +
                " }\n" +
                " public int <a name=\"m135\">this</a>(int <a name=\"m144\">param</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a></span>;\n" +
                " }\n" +
                " public void <a name=\"m184\">this</a>(int <a name=\"m193\">param</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a> </span>=<span class=\"n\"> <b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage177Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum<T,S> {\n");
        xml_.append(" ONE<Ex,ExTwo>,\n");
        xml_.append(" TWO<ExTwo,Ex>{}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 7;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public enum <a name=\"m12\">pkg.ExEnum</a>&lt;<a name=\"m23\">T</a>,<a name=\"m25\">S</a>&gt; {\n" +
                " <a name=\"m31\">ONE</a>&lt;<a title=\"pkg.Ex\" href=\"#m104\">Ex</a>,<a title=\"pkg.ExTwo\" href=\"#m78\">ExTwo</a>&gt;,\n" +
                " <a name=\"m47\">TWO</a>&lt;<a title=\"pkg.ExTwo\" href=\"#m78\">ExTwo</a>,<a title=\"pkg.Ex\" href=\"#m104\">Ex</a>&gt;{}\n" +
                "}\n" +
                "public class <a name=\"m78\">pkg.ExTwo </a>{}\n" +
                "public class <a name=\"m104\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m132\">exmeth</a>(){\n" +
                "  return <span class=\"f\">7</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage178Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public static int count;\n");
        xml_.append(" public int field = ++count;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (ExTwo e: {new ExTwo(),new ExTwo(),new ExTwo(),new ExTwo()}){\n");
        xml_.append("   sum += e.field;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.ExTwo </a>{\n" +
                " public static int <span class=\"g\"><a name=\"m44\">count</a></span>;\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m63\">field</a> </span>=<span class=\"f\"> ++<span class=\"f\"><a title=\"pkg.ExTwo.count\" href=\"#m44\">count</a></span></span></span>;\n" +
                "}\n" +
                "public class <a name=\"m95\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m123\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m139\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for (<a title=\"pkg.ExTwo\" href=\"#m13\">ExTwo</a> <a name=\"m161\">e</a></span>: <span class=\"f\">{<span class=\"f\">new <a title=\"pkg.ExTwo\" href=\"#m13\">ExTwo</a>()</span>,<span class=\"f\">new <a title=\"pkg.ExTwo\" href=\"#m13\">ExTwo</a>()</span>,<span class=\"f\">new <a title=\"pkg.ExTwo\" href=\"#m13\">ExTwo</a>()</span>,<span class=\"f\">new <a title=\"pkg.ExTwo\" href=\"#m13\">ExTwo</a>()</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m139\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> <a href=\"#m161\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m63\">field</a></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m139\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage179Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<ExTwo,ExTwo> inst=$new CustTable<>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  $for(ExTwo f, ExTwo s: inst){\n");
        xml_.append("   res += 2:\n");
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.ExTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m44\">pkg.Ex </a>{\n" +
                " $public $static <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a>&lt;<a title=\"pkg.ExTwo\" href=\"#m15\">ExTwo</a>,<a title=\"pkg.ExTwo\" href=\"#m15\">ExTwo</a>&gt; <span class=\"g\"><span class=\"g\"><a name=\"m93\">inst</a></span>=<span class=\"g\"><a title=\"pkg.CustTable.pkg.CustTable()\" href=\"CustTable.html#m97\">$new</a> <a title=\"pkg.CustTable\" href=\"CustTable.html#m15\">CustTable</a><a title=\"pkg.CustTable&lt;pkg.ExTwo,pkg.ExTwo&gt;\">&lt;&gt;</a>()</span></span>:\n" +
                " $public $static $int <span class=\"g\"><a name=\"m140\">res</a></span>:\n" +
                " $static {\n" +
                "  <span class=\"p\">$for(<a title=\"pkg.ExTwo\" href=\"#m15\">ExTwo</a> <a name=\"m169\">f</a>, <a title=\"pkg.ExTwo\" href=\"#m15\">ExTwo</a> <a name=\"m178\">s</a></span>: <span class=\"g\"><a title=\"pkg.Ex.inst\" href=\"#m93\">inst</a></span>){\n" +
                "   <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.res\" href=\"#m140\">res</a> </span>+=<span class=\"n\"> 2</span></span>:\n" +
                "  }\n" +
                " }\n" +
                " $public $static $int <a name=\"m230\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.res\" href=\"#m140\">res</a></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage180Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field = 15;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  try {\n");
        xml_.append("   throw new Ex();\n");
        xml_.append("  }\n");
        xml_.append("  catch (Ex e){\n");
        xml_.append("   return e.field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m34\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                " public static int <a name=\"m65\">exmeth</a>(){\n" +
                "  try {\n" +
                "   throw <span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span>;\n" +
                "  }\n" +
                "  <span class=\"f\">catch</span> (<a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m118\">e</a>){\n" +
                "   return <span class=\"f\"><span class=\"f\"><a href=\"#m118\">e</a></span>.<span class=\"f\"><a title=\"pkg.Ex.field\" href=\"#m34\">field</a></span></span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage181Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field = 15;\n");
        xml_.append(" public static Ex exmeth(){\n");
        xml_.append("  for (Ex e = new Ex();;) {\n");
        xml_.append("   return e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public class <a name=\"m13\">pkg.Ex </a>{\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m34\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                " public static <a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <a name=\"m64\">exmeth</a>(){\n" +
                "  for (<a title=\"pkg.Ex\" href=\"#m13\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m84\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span></span>;;) {\n" +
                "   return <span class=\"f\"><a href=\"#m84\">e</a></span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage182Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot\n");
        xml_.append("@MyAnnotTwo\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>\n" +
                "@<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">MyAnnotTwo</a>\n" +
                "$public $class <a name=\"m111\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m142\">catching</a>(){\n" +
                "  $return <span class=\"f\">0i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage183Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching(0i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(@MyAnnot@MyAnnotTwo $int p){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m90\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m121\">catching</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.$static catching($int)\" href=\"#m182\">catching</a>(<span class=\"f\">0i</span>)</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m182\">catching</a>(@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>@<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">MyAnnotTwo</a> $int <a name=\"m216\">p</a>){\n" +
                "  $return <span class=\"f\">0i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage184Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching(0i,1i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(@MyAnnot $int p,@MyAnnotTwo $int q){\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m90\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m121\">catching</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.$static catching($int,$int)\" href=\"#m185\">catching</a>(<span class=\"f\">0i</span>,<span class=\"f\">1i</span>)</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m185\">catching</a>(@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> $int <a name=\"m208\">p</a>,@<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">MyAnnotTwo</a> $int <a name=\"m227\">q</a>){\n" +
                "  $return <span class=\"f\">0i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage185Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex(@MyAnnot $int p,@MyAnnotTwo $int q){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m90\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m121\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                " <a name=\"m150\">$public Ex(</a>@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> $int <a name=\"m175\">p</a>,@<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">MyAnnotTwo</a> $int <a name=\"m194\">q</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage186Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$operator+ pkg.Ex(@pkg.MyAnnot pkg.Ex p,@pkg.MyAnnotTwo pkg.Ex q){\n");
        xml_.append(" $return $new pkg.Ex():\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m90\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m121\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "$operator<a name=\"m160\">+</a> <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a>(@<a title=\"pkg.MyAnnot\" href=\"#m20\">pkg.MyAnnot</a> <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a> <a name=\"m189\">p</a>,@<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">pkg.MyAnnotTwo</a> <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a> <a name=\"m214\">q</a>){\n" +
                " $return <span class=\"n\">$new <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a>()</span>:\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage187Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.method():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>\n" +
                "$public $class <a name=\"m77\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m108\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m130\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m77\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a href=\"#m130\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">method</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage188Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public enum pkg.ExEnum<T,S> {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" ONE<Ex,ExTwo>,\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" TWO<ExTwo,Ex>{}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 7;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public annotation <a name=\"m18\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "public annotation <a name=\"m52\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "public enum <a name=\"m83\">pkg.ExEnum</a>&lt;<a name=\"m94\">T</a>,<a name=\"m96\">S</a>&gt; {\n" +
                " @<a title=\"pkg.MyAnnot\" href=\"#m18\">MyAnnot</a>\n" +
                " <a name=\"m112\">ONE</a>&lt;<a title=\"pkg.Ex\" href=\"#m198\">Ex</a>,<a title=\"pkg.ExTwo\" href=\"#m172\">ExTwo</a>&gt;,\n" +
                " @<a title=\"pkg.MyAnnotTwo\" href=\"#m52\">MyAnnotTwo</a>\n" +
                " <a name=\"m141\">TWO</a>&lt;<a title=\"pkg.ExTwo\" href=\"#m172\">ExTwo</a>,<a title=\"pkg.Ex\" href=\"#m198\">Ex</a>&gt;{}\n" +
                "}\n" +
                "public class <a name=\"m172\">pkg.ExTwo </a>{}\n" +
                "public class <a name=\"m198\">pkg.Ex </a>{\n" +
                " public static int <a name=\"m226\">exmeth</a>(){\n" +
                "  return <span class=\"f\">7</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage189Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" public static int field;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 7;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public annotation <a name=\"m18\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "public annotation <a name=\"m52\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "public class <a name=\"m84\">pkg.Ex </a>{\n" +
                " @<a title=\"pkg.MyAnnot\" href=\"#m18\">MyAnnot</a>\n" +
                " @<a title=\"pkg.MyAnnotTwo\" href=\"#m52\">MyAnnotTwo</a>\n" +
                " public static int <span class=\"g\"><a name=\"m135\">field</a></span>;\n" +
                " public static int <a name=\"m161\">exmeth</a>(){\n" +
                "  return <span class=\"f\">7</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage190Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 7;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public annotation <a name=\"m18\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "public annotation <a name=\"m52\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "public class <a name=\"m84\">pkg.Ex </a>{\n" +
                " @<a title=\"pkg.MyAnnot\" href=\"#m18\">MyAnnot</a>\n" +
                " @<a title=\"pkg.MyAnnotTwo\" href=\"#m52\">MyAnnotTwo</a>\n" +
                " public static int <a name=\"m135\">exmeth</a>(){\n" +
                "  return <span class=\"f\">7</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage191Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return 7;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>public annotation <a name=\"m18\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "public annotation <a name=\"m52\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "public class <a name=\"m84\">pkg.Ex </a>{\n" +
                " @<a title=\"pkg.MyAnnot\" href=\"#m18\">MyAnnot</a>\n" +
                " @<a title=\"pkg.MyAnnotTwo\" href=\"#m52\">MyAnnotTwo</a>\n" +
                " <a name=\"m117\">public Ex(</a>){\n" +
                " }\n" +
                " public static int <a name=\"m152\">exmeth</a>(){\n" +
                "  return <span class=\"f\">7</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage192Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@pkg.MyAnnot\n");
        xml_.append("@pkg.MyAnnotTwo\n");
        xml_.append("$operator+ pkg.Ex(pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" $return $new pkg.Ex():\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m90\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m121\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">pkg.MyAnnot</a>\n" +
                "@<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">pkg.MyAnnotTwo</a>\n" +
                "$operator<a name=\"m189\">+</a> <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a> <a name=\"m205\">p</a>, <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a> <a name=\"m215\">q</a>){\n" +
                " $return <span class=\"n\">$new <a title=\"pkg.Ex\" href=\"#m90\">pkg.Ex</a>()</span>:\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage193Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" $int method():\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m56\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $annotation <a name=\"m95\">pkg.MyAnnotThree </a>{\n" +
                " @<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>\n" +
                " @<a title=\"pkg.MyAnnotTwo\" href=\"#m56\">MyAnnotTwo</a>\n" +
                " $int <a name=\"m143\">method</a>():\n" +
                "}\n" +
                "$public $class <a name=\"m170\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m201\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage194Test() {
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
        xml_.append("  $return (one;.+two;.).$classchoice(ExTwo)field:\n");
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m137\">pkg.Ex</a>:<a title=\"pkg.ExTwo\" href=\"#m397\">ExTwo</a> {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m193\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m223\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m223\">two</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span class=\"f\">$classchoice(<a title=\"pkg.ExTwo\" href=\"#m397\">ExTwo</a>)<a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a></span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m355\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m397\">pkg.ExTwo </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m423\">field</a></span>:\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage195Test() {
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
        xml_.append("  $return (one;.+two;.).$superaccess(ExTwo)field:\n");
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
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> (<a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m26\">p</a>, <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <a name=\"m36\">q</a>){\n" +
                " <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m48\">out</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                " <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m48\">out</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m26\">p</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m36\">q</a>;.;</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a></span></span></span></span>:\n" +
                " $return <span class=\"f\"><a href=\"#m48\">out</a>;.</span>:\n" +
                "}\n" +
                "$public $class <a name=\"m137\">pkg.Ex</a>:<a title=\"pkg.ExTwo\" href=\"#m397\">ExTwo</a> {\n" +
                " $public $static $int <a name=\"m174\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m193\">one</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m223\">two</a> </span>=<span class=\"f\"> $new <a title=\"pkg.Ex\" href=\"#m137\">pkg.Ex</a>()</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a> </span></span>=<span class=\"f\"> 1</span></span>:\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m223\">two</a>;.</span><span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a> </span></span>=<span class=\"f\"> 2</span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m193\">one</a>;.</span><a title=\"$static +(pkg.Ex,pkg.Ex)\" href=\"#m9\">+</a><span class=\"f\"><a href=\"#m223\">two</a>;.</span></span>)</span>.<span class=\"f\">$superaccess(<a title=\"pkg.ExTwo\" href=\"#m397\">ExTwo</a>)<a title=\"pkg.ExTwo.field\" href=\"#m423\">field</a></span></span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m355\">call</a>(){\n" +
                "  $return <span class=\"n\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m397\">pkg.ExTwo </a>{\n" +
                " $public $int <span class=\"f\"><a name=\"m423\">field</a></span>:\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage196Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" MyAnnotTwo method() @MyAnnotTwo:\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " <a title=\"pkg.MyAnnotTwo\" href=\"#m90\">MyAnnotTwo</a> <a name=\"m46\">method</a>() @<a title=\"pkg.MyAnnotTwo\" href=\"#m90\">MyAnnotTwo</a>:\n" +
                "}\n" +
                "$public $annotation <a name=\"m90\">pkg.MyAnnotTwo </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m124\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m155\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage197Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.IntOne {}\n");
        xml_.append("$public $interface pkg.IntTwo {}\n");
        xml_.append("$public $interface pkg.IntThree {}\n");
        xml_.append("$public $interface pkg.IntFour {}\n");
        xml_.append("$public $class pkg.ExParam<T:IntOne&IntTwo,U:IntThree&IntFour> {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.IntOne </a>{}\n" +
                "$public $interface <a name=\"m52\">pkg.IntTwo </a>{}\n" +
                "$public $interface <a name=\"m85\">pkg.IntThree </a>{}\n" +
                "$public $interface <a name=\"m120\">pkg.IntFour </a>{}\n" +
                "$public $class <a name=\"m150\">pkg.ExParam</a>&lt;<a name=\"m162\">T</a>:<a title=\"pkg.IntOne\" href=\"#m19\">IntOne</a>&amp;<a title=\"pkg.IntTwo\" href=\"#m52\">IntTwo</a>,<a name=\"m178\">U</a>:<a title=\"pkg.IntThree\" href=\"#m85\">IntThree</a>&amp;<a title=\"pkg.IntFour\" href=\"#m120\">IntFour</a>&gt; {}\n" +
                "$public $class <a name=\"m216\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m247\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage198Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.IntOne {}\n");
        xml_.append("$public $interface pkg.IntTwo {}\n");
        xml_.append("$public $interface pkg.IntThree {}\n");
        xml_.append("$public $interface pkg.IntFour {}\n");
        xml_.append("$public $class pkg.ExParam<TV:IntOne&IntTwo,UW:IntThree&IntFour> {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.IntOne </a>{}\n" +
                "$public $interface <a name=\"m52\">pkg.IntTwo </a>{}\n" +
                "$public $interface <a name=\"m85\">pkg.IntThree </a>{}\n" +
                "$public $interface <a name=\"m120\">pkg.IntFour </a>{}\n" +
                "$public $class <a name=\"m150\">pkg.ExParam</a>&lt;<a name=\"m162\">TV</a>:<a title=\"pkg.IntOne\" href=\"#m19\">IntOne</a>&amp;<a title=\"pkg.IntTwo\" href=\"#m52\">IntTwo</a>,<a name=\"m179\">UW</a>:<a title=\"pkg.IntThree\" href=\"#m85\">IntThree</a>&amp;<a title=\"pkg.IntFour\" href=\"#m120\">IntFour</a>&gt; {}\n" +
                "$public $class <a name=\"m218\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m249\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage199Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.IntOne {}\n");
        xml_.append("$public $interface pkg.IntTwo {}\n");
        xml_.append("$public $interface pkg.IntThree {}\n");
        xml_.append("$public $interface pkg.IntFour {}\n");
        xml_.append("$public $class pkg.ExParam<TVX:IntOne&IntTwo,UWY:IntThree&IntFour> {}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.IntOne </a>{}\n" +
                "$public $interface <a name=\"m52\">pkg.IntTwo </a>{}\n" +
                "$public $interface <a name=\"m85\">pkg.IntThree </a>{}\n" +
                "$public $interface <a name=\"m120\">pkg.IntFour </a>{}\n" +
                "$public $class <a name=\"m150\">pkg.ExParam</a>&lt;<a name=\"m162\">TVX</a>:<a title=\"pkg.IntOne\" href=\"#m19\">IntOne</a>&amp;<a title=\"pkg.IntTwo\" href=\"#m52\">IntTwo</a>,<a name=\"m180\">UWY</a>:<a title=\"pkg.IntThree\" href=\"#m85\">IntThree</a>&amp;<a title=\"pkg.IntFour\" href=\"#m120\">IntFour</a>&gt; {}\n" +
                "$public $class <a name=\"m220\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m251\">catching</a>(){\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage200Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(method=2)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.method():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(<a title=\"pkg.MyAnnot.method\" href=\"#m40\">method</a>=2)\n" +
                "$public $class <a name=\"m87\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m118\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m140\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m87\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a href=\"#m140\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">method</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage201Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot( method=2)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.method():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>( <a title=\"pkg.MyAnnot.method\" href=\"#m40\">method</a>=2)\n" +
                "$public $class <a name=\"m88\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m119\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m141\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m88\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a href=\"#m141\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">method</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage202Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append(" $int method2()3:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(method=2,method2=4)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.method():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                " $int <a name=\"m57\">method2</a>()3:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(<a title=\"pkg.MyAnnot.method\" href=\"#m40\">method</a>=2,<a title=\"pkg.MyAnnot.method2\" href=\"#m57\">method2</a>=4)\n" +
                "$public $class <a name=\"m115\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m146\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m168\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m115\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a href=\"#m168\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">method</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage203Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(2)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.method():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(2)\n" +
                "$public $class <a name=\"m80\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m111\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m133\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m80\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a href=\"#m133\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">method</a>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage204Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method = 5:\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(method=2)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.method()+MyAnnot.method:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <span class=\"g\"><span class=\"g\"><a name=\"m40\">method</a> </span>=<span class=\"g\"> 5</span></span>:\n" +
                " $int <a name=\"m58\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(<a title=\"pkg.MyAnnot.method\" href=\"#m58\">method</a>=2)\n" +
                "$public $class <a name=\"m105\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m136\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m158\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m105\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m158\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m58\">method</a>()</span></span>+<span class=\"f\"><span class=\"f\"><a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a></span>.<span class=\"f\"><a title=\"pkg.MyAnnot.method\" href=\"#m40\">method</a></span></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage205Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(method=2)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return m;.$lambda(MyAnnot,method).call():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(<a title=\"pkg.MyAnnot.method\" href=\"#m40\">method</a>=2)\n" +
                "$public $class <a name=\"m87\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m118\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m140\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m87\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m140\">m</a>;.</span><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">$lambda</a>(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>,method)</span></span>.<span class=\"f\"><b>call</b>()</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage206Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int method()1:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(method=2)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return $lambda(MyAnnot,method).call(m;.):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">method</a>()1:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(<a title=\"pkg.MyAnnot.method\" href=\"#m40\">method</a>=2)\n" +
                "$public $class <a name=\"m87\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m118\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m140\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m87\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m40\">$lambda</a>(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>,method)</span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m140\">m</a>;.</span>)</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage207Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int FIELD = 2:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return FIELD+(FIELD+FIELD)+FIELD:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $final $int <span class=\"g\"><span class=\"g\"><a name=\"m53\">FIELD</a> </span>=<span class=\"g\"> 2</span></span>:\n" +
                " $public $static $int <a name=\"m86\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span>+<span class=\"f\">(<span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span>+<span class=\"f\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span></span>)</span></span>+<span class=\"f\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage208Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int FIELD = 2:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 2:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth2(){\n");
        xml_.append("  $return FIELD+(FIELD+FIELD)+FIELD:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $final $int <span class=\"g\"><span class=\"g\"><a name=\"m53\">FIELD</a> </span>=<span class=\"g\"> 2</span></span>:\n" +
                " $public $static $int <a name=\"m86\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">2</span>:\n" +
                " }\n" +
                " $public $static $int <a name=\"m134\">exmeth2</a>(){\n" +
                "  $return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span>+<span class=\"n\">(<span class=\"n\"><span class=\"n\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span>+<span class=\"n\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span></span>)</span></span>+<span class=\"n\"><a title=\"pkg.Ex.FIELD\" href=\"#m53\">FIELD</a></span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage209Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" String method()\"1\":\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(method=\"2\")\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  MyAnnot m = (MyAnnot)$class(Ex).getAnnotations()[0]:\n");
        xml_.append("  $return $lambda(MyAnnot,method).call(m;.):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " String <a name=\"m42\">method</a>()<span class=\"s\">\"1\"</span>:\n" +
                "}\n" +
                "@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>(<a title=\"pkg.MyAnnot.method\" href=\"#m42\">method</a>=<span class=\"s\">\"2\"</span>)\n" +
                "$public $class <a name=\"m93\">pkg.Ex </a>{\n" +
                " $public $static String <a name=\"m126\">catching</a>(){\n" +
                "  <a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a> <span class=\"f\"><span class=\"f\"><a name=\"m148\">m</a> </span>=<span class=\"f\"> (<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)<span class=\"f\"><span class=\"f\"><span class=\"f\">$class(<a title=\"pkg.Ex\" href=\"#m93\">Ex</a>)</span>.<span class=\"f\">getAnnotations()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span></span>:\n" +
                "  $return <span class=\"f\"><span class=\"f\"><a title=\"pkg.MyAnnot.method()\" href=\"#m42\">$lambda</a>(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>,method)</span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m148\">m</a>;.</span>)</span></span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage210Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $var c = $true:\n");
        xml_.append("  b;. &&= c;.:\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageReadOnly();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m81\">c</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span><a title=\"true\">&amp;&amp;</a>=<span class=\"p\"> <a href=\"#m81\">c</a>;.</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage211Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $false:\n");
        xml_.append("  $var c = $true:\n");
        xml_.append("  b;. &&= c;.:\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageReadOnly();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $false</span></span>:\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m82\">c</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span>&amp;&amp;=<span class=\"n\"> <a href=\"#m82\">c</a>;.</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage212Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $false:\n");
        xml_.append("  $var c = $false:\n");
        xml_.append("  b;. ||= c;.:\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageReadOnly();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $false</span></span>:\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m82\">c</a> </span>=<span class=\"f\"> $false</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span><a title=\"false\">||</a>=<span class=\"p\"> <a href=\"#m82\">c</a>;.</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage213Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true:\n");
        xml_.append("  $var c = $false:\n");
        xml_.append("  b;. ||= c;.:\n");
        xml_.append("  $return 0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverageReadOnly();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>:\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m81\">c</a> </span>=<span class=\"f\"> $false</span></span>:\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a>;. </span>||=<span class=\"n\"> <a href=\"#m81\">c</a>;.</span></span>:\n" +
                "  $return <span class=\"f\">0</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  //comment\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <span class=\"c\">//comment</span>\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  /*comment*/\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <span class=\"c\">/*comment*/</span>\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("//comment\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"c\">//comment</span>\n" +
                "$public $class <a name=\"m25\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m56\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("/*comment*/\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"c\">/*comment*/</span>\n" +
                "$public $class <a name=\"m27\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m58\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("//comment\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "<span class=\"c\">//comment</span>\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("/*comment*/\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "<span class=\"c\">/*comment*/</span>\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("//");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "<span class=\"c\">//</span>" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("/*comment");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = FileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  $return <span class=\"f\">1i</span>:\n" +
                " }\n" +
                "}\n" +
                "<span class=\"c\">/*comment</span>" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object catching(){\n");
        xml_.append("  $return $(#T)0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("src/pkg/Ex", xml_.toString());
        validate(cont_.getKeyWords(),cont_.getStandards(),files_,cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
