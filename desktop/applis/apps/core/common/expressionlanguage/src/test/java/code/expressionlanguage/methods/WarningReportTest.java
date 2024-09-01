package code.expressionlanguage.methods;

import code.expressionlanguage.linkage.ExportCst;
import code.util.StringMap;
import org.junit.Test;

public final class WarningReportTest extends ProcessMethodCommon {
    @Test
    public void report0Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return true?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  return true<a title=\"A part of code is unreachable in this ternary operation.\" class=\"w\">?</a>1:0;\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static that int exmeth(){\n");
        xml_.append("  return that(true?that(v):that(v));\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">v</a>;\n" +
                " public static that int <a name=\""+ExportCst.PREF_REF+"68\">exmeth</a>(){\n" +
                "  return that(true<a title=\"A part of code is unreachable in this ternary operation.\" class=\"w\">?</a>that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>):that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>));\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return bool(true,1,0);\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  return bool<a title=\"A part of code is unreachable in this ternary operation.\" class=\"w\">(</a>true,1,0);\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static that int exmeth(){\n");
        xml_.append("  return that(bool(true,that(v),that(v)));\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">v</a>;\n" +
                " public static that int <a name=\""+ExportCst.PREF_REF+"68\">exmeth</a>(){\n" +
                "  return that(bool<a title=\"A part of code is unreachable in this ternary operation.\" class=\"w\">(</a>true,that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>),that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>)));\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var b = true;\n");
        xml_.append("  return b?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <a name=\""+ExportCst.PREF_REF+"57\">b</a> = true;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"57\">b</a>?1:0;\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static that int exmeth(){\n");
        xml_.append("  var b = true;\n");
        xml_.append("  return that(b?that(v):that(v));\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">v</a>;\n" +
                " public static that int <a name=\""+ExportCst.PREF_REF+"68\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <a name=\""+ExportCst.PREF_REF+"84\">b</a> = true;\n" +
                "  return that(<a href=\"#"+ExportCst.PREF_REF+"84\">b</a>?that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>):that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>));\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  var b = true;\n");
        xml_.append("  return bool(b,1,0);\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <a name=\""+ExportCst.PREF_REF+"57\">b</a> = true;\n" +
                "  return bool(<a href=\"#"+ExportCst.PREF_REF+"57\">b</a>,1,0);\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static that int exmeth(){\n");
        xml_.append("  var b = true;\n");
        xml_.append("  return that(bool(b,that(v),that(v)));\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">v</a>;\n" +
                " public static that int <a name=\""+ExportCst.PREF_REF+"68\">exmeth</a>(){\n" +
                "  <b title=\"boolean\">var</b> <a name=\""+ExportCst.PREF_REF+"84\">b</a> = true;\n" +
                "  return that(bool(<a href=\"#"+ExportCst.PREF_REF+"84\">b</a>,that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>),that(<a title=\"pkg.Ex.v\" href=\"#"+ExportCst.PREF_REF+"41\">v</a>)));\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(int u){\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a>(int <a title=\"The parameter u is unused.\" name=\""+ExportCst.PREF_REF+"53\" class=\"w\">u</a>){\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(int u){\n");
        xml_.append("  return u;\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(int <a name=\""+ExportCst.PREF_REF+"52\">u</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"52\">u</a>;\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(int int,int u){\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a>(int <a name=\""+ExportCst.PREF_REF+"53\" title=\"The parameter function name int is not valid. It must not a primitive type.\" class=\"e\">int</a>,int <a title=\"The parameter u is unused.\" name=\""+ExportCst.PREF_REF+"61\" class=\"w\">u</a>){\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public @class pkg.Ex {\n");
        xml_.append(" int f;\n");
        xml_.append(" operator+ Ex(Ex a,Ex b){\n");
        xml_.append("  return new(f:a.f+b.f);\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public @class <a name=\""+ExportCst.PREF_REF+"14\">pkg.Ex</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"28\">f</a>;\n" +
                " operator<a name=\""+ExportCst.PREF_REF+"40\">+</a> <a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"14\">Ex</a>(<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"14\">Ex</a> <a name=\""+ExportCst.PREF_REF+"48\">a</a>,<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"14\">Ex</a> <a name=\""+ExportCst.PREF_REF+"53\">b</a>){\n" +
                "  return new(<a title=\"pkg.Ex.f\" href=\"#"+ExportCst.PREF_REF+"28\">f</a>:<a href=\"#"+ExportCst.PREF_REF+"48\">a</a>.<a title=\"pkg.Ex.f\" href=\"#"+ExportCst.PREF_REF+"28\">f</a>+<a href=\"#"+ExportCst.PREF_REF+"53\">b</a>.<a title=\"pkg.Ex.f\" href=\"#"+ExportCst.PREF_REF+"28\">f</a>);\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void caller(){\n");
        xml_.append("  Fct<int,void> elt = staticCall(Ex).$lambda(exmeth);\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(int u){\n");
        xml_.append(" }\n");
        xml_.append("}");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxWarnStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">caller</a>(){\n" +
                "  Fct&lt;int,void&gt; <a name=\""+ExportCst.PREF_REF+"68\">elt</a> = staticCall(<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"13\">Ex</a>).<a title=\"pkg.Ex.static exmeth(int)\" href=\"#"+ExportCst.PREF_REF+"129\">$lambda</a>(exmeth);\n" +
                " }\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"129\">exmeth</a>(int <a name=\""+ExportCst.PREF_REF+"140\">u</a>){\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
}
