package code.expressionlanguage.methods;

import code.expressionlanguage.common.MessagesCdmBase;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringMap;
import org.junit.Test;

public final class Errors1Test extends ProcessMethodCommon {

    @Test
    public void report648Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field=++extField;\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\""+ExportCst.PREF_REF+"10\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"25\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"42\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"64\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"86\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"97\">l</a> = new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\" title=\"The method field() from the type pkg.Int must be overriden in the concrete type pkg.Ext..Int*1.\" class=\"e\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"126\">field</a>=++<a title=\"pkg.Ext.extField\" href=\"#"+ExportCst.PREF_REF+"64\">extField</a>;\n" +
                "  }</span>;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"97\">l</a>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report649Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field = 15;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append("  if (new Int(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field == 15)lab{\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"21\">field</a> = 15;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"41\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"63\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"85\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">if</a> (new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"117\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"133\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Int.field\" href=\"#"+ExportCst.PREF_REF+"21\">field</a> == 15)<a name=\""+ExportCst.PREF_REF+"159\">lab</a>{\n" +
                "   <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> 1;\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> 2;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report650Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static int stfield;\n");
        xml_.append(" int field();\n");
        xml_.append(" static {\n");
        xml_.append("  stfield++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new {} interfaces(Int) Int(){\n");
        xml_.append("   public int field=++stfield;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">stfield</a>;\n" +
                " int <a name=\""+ExportCst.PREF_REF+"42\" title=\"A throw block or a return block is missing for the method field().\" class=\"e\">field</a>();\n" +
                " static {\n" +
                "  <a title=\"pkg.Int.stfield\" href=\"#"+ExportCst.PREF_REF+"28\">stfield</a>++;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"85\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"107\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a> <a name=\""+ExportCst.PREF_REF+"118\">l</a> = new {} interfaces(<a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>) <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"150\" title=\"The type pkg.Int is not an interface.\" class=\"e\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"166\">field</a>=++<a title=\"pkg.Int.stfield\" href=\"#"+ExportCst.PREF_REF+"28\">stfield</a>;\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"197\">field</a>(){\n" +
                "    return <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"166\">field</a>;\n" +
                "   }\n" +
                "  }</span>;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"118\">l</a>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"42\">field</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report651Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE(new Int(1){\n");
        xml_.append("  static int extField;\n");
        xml_.append("  public int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }){\n");
        xml_.append("  ONE(Int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" Int inner;\n");
        xml_.append(" Ext(Int p){\n");
        xml_.append("  inner = p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append("  return ONE.inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\""+ExportCst.PREF_REF+"10\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"41\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"52\" title=\"pkg.Ext-ONE.pkg.Ext-ONE(pkg.Int)\" href=\"#"+ExportCst.PREF_REF+"200\">ONE</a>(<a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#"+ExportCst.PREF_REF+"113\">new</a> <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>(1)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"66\">{</a>\n" +
                "  static int <a name=\""+ExportCst.PREF_REF+"81\">extField</a>;\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"104\">field</a>;\n" +
                "  <a name=\""+ExportCst.PREF_REF+"113\">public Int(</a>int <a name=\""+ExportCst.PREF_REF+"128\">p</a>){\n" +
                "   <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"104\">field</a> = <a href=\"#"+ExportCst.PREF_REF+"128\">p</a>;\n" +
                "  }\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"163\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"104\">field</a>;\n" +
                "  }\n" +
                " }</span>){\n" +
                "  <a name=\""+ExportCst.PREF_REF+"200\">ONE(</a><a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"208\">p</a>){\n" +
                "   <a title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#"+ExportCst.PREF_REF+"246\">super</a>(<a href=\"#"+ExportCst.PREF_REF+"208\">p</a>);\n" +
                "  }\n" +
                " };\n" +
                " <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"238\">inner</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"246\">Ext(</a><a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"254\">p</a>){\n" +
                "  <a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"238\">inner</a> = <a href=\"#"+ExportCst.PREF_REF+"254\">p</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"286\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.ONE\" href=\"#"+ExportCst.PREF_REF+"52\">ONE</a>.<a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"238\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report652Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE(new Int(1){\n");
        xml_.append("  static int extField;\n");
        xml_.append("  public int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" });\n");
        xml_.append(" Int inner;\n");
        xml_.append(" Ext(Int p){\n");
        xml_.append("  inner = p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append("  return ONE.inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\""+ExportCst.PREF_REF+"10\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"41\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"52\" title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#"+ExportCst.PREF_REF+"211\">ONE</a>(<a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#"+ExportCst.PREF_REF+"113\">new</a> <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>(1)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"66\">{</a>\n" +
                "  static int <a name=\""+ExportCst.PREF_REF+"81\">extField</a>;\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"104\">field</a>;\n" +
                "  <a name=\""+ExportCst.PREF_REF+"113\">public Int(</a>int <a name=\""+ExportCst.PREF_REF+"128\">p</a>){\n" +
                "   <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"104\">field</a> = <a href=\"#"+ExportCst.PREF_REF+"128\">p</a>;\n" +
                "  }\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"163\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"104\">field</a>;\n" +
                "  }\n" +
                " }</span>);\n" +
                " <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"203\">inner</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"211\">Ext(</a><a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"219\">p</a>){\n" +
                "  <a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"203\">inner</a> = <a href=\"#"+ExportCst.PREF_REF+"219\">p</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"251\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.ONE\" href=\"#"+ExportCst.PREF_REF+"52\">ONE</a>.<a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"203\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report653Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Int inner = new Int(){\n");
        xml_.append("  static int extField;\n");
        xml_.append("  public int field=1;\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append("  return inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\""+ExportCst.PREF_REF+"10\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"25\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"42\">pkg.Ext</a> {\n" +
                " static <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"64\">inner</a> = new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"81\">{</a>\n" +
                "  static int <a name=\""+ExportCst.PREF_REF+"96\">extField</a>;\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"119\">field</a>=1;\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"141\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"119\">field</a>;\n" +
                "  }\n" +
                " }</span>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"187\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"64\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report654Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field=1;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  int i = 0;\n");
        xml_.append("  switch (new Int(){}.field){\n");
        xml_.append("   case 1;\n");
        xml_.append("   res+=',';\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"21\">field</a>=1;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"38\">pkg.Ext</a> {\n" +
                " static String <a name=\""+ExportCst.PREF_REF+"63\">m</a>(){\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"93\">i</a> = 0;\n" +
                "  switch (new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"119\">{</a>}</span>.<a title=\"pkg.Int.field\" href=\"#"+ExportCst.PREF_REF+"21\">field</a>){\n" +
                "   case 1;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>+=<span class=\"s\">','</span>;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"93\">i</a>++;\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>;\n" +
                "  <a title=\"The type int cannot be implicitly cast to $core.String\n" +
                "\n" +
                "The code is unreachable in the function static m()\" class=\"e\">return</a> 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report655Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field=1;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  for (int i: new Iterable<int>(1,2){\n");
        xml_.append("   int[] f;\n");
        xml_.append("   Iterable(int... a){\n");
        xml_.append("    f = a;\n");
        xml_.append("   }\n");
        xml_.append("   public Iterator<int> iterator(){\n");
        xml_.append("    return new Iterator<int>(f){\n");
        xml_.append("     int[] g;\n");
        xml_.append("     int j;\n");
        xml_.append("     Iterator(int... a){\n");
        xml_.append("      g = a;\n");
        xml_.append("     }\n");
        xml_.append("     public boolean hasNext(){\n");
        xml_.append("      return j < g.length;\n");
        xml_.append("     }\n");
        xml_.append("     public int next(){\n");
        xml_.append("      return g[j++];\n");
        xml_.append("     }\n");
        xml_.append("    };\n");
        xml_.append("   }\n");
        xml_.append("  }) {\n");
        xml_.append("   res+=i+\",\";\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"21\">field</a>=1;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"38\">pkg.Ext</a> {\n" +
                " static String <a name=\""+ExportCst.PREF_REF+"63\">m</a>(){\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"98\">i</a>: <a title=\"pkg.Ext..Iterable*1.pkg.Ext..Iterable*1(int...)\" href=\"#"+ExportCst.PREF_REF+"140\">new</a> Iterable"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(1,2)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>\n" +
                "   int[] <a name=\""+ExportCst.PREF_REF+"134\">f</a>;\n" +
                "   <a name=\""+ExportCst.PREF_REF+"140\">Iterable(</a>int... <a name=\""+ExportCst.PREF_REF+"156\">a</a>){\n" +
                "    <a title=\"pkg.Ext..Iterable*1.f\" href=\"#"+ExportCst.PREF_REF+"134\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"156\">a</a>;\n" +
                "   }\n" +
                "   public Iterator"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"200\">iterator</a>(){\n" +
                "    return <a title=\"pkg.Ext..Iterable*1..Iterator*1.pkg.Ext..Iterable*1..Iterator*1(int...)\" href=\"#"+ExportCst.PREF_REF+"276\">new</a> Iterator"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(<a title=\"pkg.Ext..Iterable*1.f\" href=\"#"+ExportCst.PREF_REF+"134\">f</a>)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"243\">{</a>\n" +
                "     int[] <a name=\""+ExportCst.PREF_REF+"256\">g</a>;\n" +
                "     int <a name=\""+ExportCst.PREF_REF+"268\">j</a>;\n" +
                "     <a name=\""+ExportCst.PREF_REF+"276\">Iterator(</a>int... <a name=\""+ExportCst.PREF_REF+"292\">a</a>){\n" +
                "      <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#"+ExportCst.PREF_REF+"256\">g</a> = <a href=\"#"+ExportCst.PREF_REF+"292\">a</a>;\n" +
                "     }\n" +
                "     public boolean <a name=\""+ExportCst.PREF_REF+"336\">hasNext</a>(){\n" +
                "      return <a title=\"pkg.Ext..Iterable*1..Iterator*1.j\" href=\"#"+ExportCst.PREF_REF+"268\">j</a> "+MessagesCdmBase.LT+" <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#"+ExportCst.PREF_REF+"256\">g</a>.<b>length</b>;\n" +
                "     }\n" +
                "     public int <a name=\""+ExportCst.PREF_REF+"397\">next</a>(){\n" +
                "      return <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#"+ExportCst.PREF_REF+"256\">g</a>[<a title=\"pkg.Ext..Iterable*1..Iterator*1.j\" href=\"#"+ExportCst.PREF_REF+"268\">j</a>++];\n" +
                "     }\n" +
                "    }</span>;\n" +
                "   }\n" +
                "  }</span>) {\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>+=<a href=\"#"+ExportCst.PREF_REF+"98\">i</a>+<span class=\"s\">\",\"</span>;\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>;\n" +
                "  <a title=\"The type int cannot be implicitly cast to $core.String\n" +
                "\n" +
                "The code is unreachable in the function static m()\" class=\"e\">return</a> 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report656Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field=1;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  for (int i, int y: new IterableTable<int,int>(new int[]{1,2},new int[]{3,4}){\n");
        xml_.append("   int[] e;\n");
        xml_.append("   int[] f;\n");
        xml_.append("   IterableTable(int[] b,int[] a){\n");
        xml_.append("    e = b;\n");
        xml_.append("    f = a;\n");
        xml_.append("   }\n");
        xml_.append("   public IteratorTable<int,int> iteratorTable(){\n");
        xml_.append("    return new IteratorTable<int,int>(e,f){\n");
        xml_.append("     int[] g;\n");
        xml_.append("     int[] h;\n");
        xml_.append("     int j;\n");
        xml_.append("     IteratorTable(int[] b,int[] a){\n");
        xml_.append("      g = b;\n");
        xml_.append("      h = a;\n");
        xml_.append("     }\n");
        xml_.append("     public boolean hasNextPair(){\n");
        xml_.append("      return j < g.length;\n");
        xml_.append("     }\n");
        xml_.append("     public Pair<int,int> nextPair(){\n");
        xml_.append("      return new Pair<int,int>(g,h,j++){\n");
        xml_.append("       int[] k;\n");
        xml_.append("       int[] l;\n");
        xml_.append("       int m;\n");
        xml_.append("       Pair(int[] b,int[] a, int z){\n");
        xml_.append("        k = b;\n");
        xml_.append("        l = a;\n");
        xml_.append("        m = z;\n");
        xml_.append("       }\n");
        xml_.append("       public int getFirst(){\n");
        xml_.append("        return k[m];\n");
        xml_.append("       }\n");
        xml_.append("       public int getSecond(){\n");
        xml_.append("        return l[m];\n");
        xml_.append("       }\n");
        xml_.append("      };\n");
        xml_.append("     }\n");
        xml_.append("    };\n");
        xml_.append("   }\n");
        xml_.append("  }) {\n");
        xml_.append("   res+=y+\",\"+i+\";\";\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"21\">field</a>=1;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"38\">pkg.Ext</a> {\n" +
                " static String <a name=\""+ExportCst.PREF_REF+"63\">m</a>(){\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"98\">i</a>, int <a name=\""+ExportCst.PREF_REF+"105\">y</a>: <a title=\"pkg.Ext..IterableTable*1.pkg.Ext..IterableTable*1([int,[int)\" href=\"#"+ExportCst.PREF_REF+"194\">new</a> IterableTable"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+"(new int[]{1,2},new int[]{3,4})<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"165\">{</a>\n" +
                "   int[] <a name=\""+ExportCst.PREF_REF+"176\">e</a>;\n" +
                "   int[] <a name=\""+ExportCst.PREF_REF+"188\">f</a>;\n" +
                "   <a name=\""+ExportCst.PREF_REF+"194\">IterableTable(</a>int[] <a name=\""+ExportCst.PREF_REF+"214\">b</a>,int[] <a name=\""+ExportCst.PREF_REF+"222\">a</a>){\n" +
                "    <a title=\"pkg.Ext..IterableTable*1.e\" href=\"#"+ExportCst.PREF_REF+"176\">e</a> = <a href=\"#"+ExportCst.PREF_REF+"214\">b</a>;\n" +
                "    <a title=\"pkg.Ext..IterableTable*1.f\" href=\"#"+ExportCst.PREF_REF+"188\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"222\">a</a>;\n" +
                "   }\n" +
                "   public IteratorTable"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"286\">iteratorTable</a>(){\n" +
                "    return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.pkg.Ext..IterableTable*1..IteratorTable*1([int,[int)\" href=\"#"+ExportCst.PREF_REF+"392\">new</a> IteratorTable"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+"(<a title=\"pkg.Ext..IterableTable*1.e\" href=\"#"+ExportCst.PREF_REF+"176\">e</a>,<a title=\"pkg.Ext..IterableTable*1.f\" href=\"#"+ExportCst.PREF_REF+"188\">f</a>)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"345\">{</a>\n" +
                "     int[] <a name=\""+ExportCst.PREF_REF+"358\">g</a>;\n" +
                "     int[] <a name=\""+ExportCst.PREF_REF+"372\">h</a>;\n" +
                "     int <a name=\""+ExportCst.PREF_REF+"384\">j</a>;\n" +
                "     <a name=\""+ExportCst.PREF_REF+"392\">IteratorTable(</a>int[] <a name=\""+ExportCst.PREF_REF+"412\">b</a>,int[] <a name=\""+ExportCst.PREF_REF+"420\">a</a>){\n" +
                "      <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#"+ExportCst.PREF_REF+"358\">g</a> = <a href=\"#"+ExportCst.PREF_REF+"412\">b</a>;\n" +
                "      <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.h\" href=\"#"+ExportCst.PREF_REF+"372\">h</a> = <a href=\"#"+ExportCst.PREF_REF+"420\">a</a>;\n" +
                "     }\n" +
                "     public boolean <a name=\""+ExportCst.PREF_REF+"477\">hasNextPair</a>(){\n" +
                "      return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.j\" href=\"#"+ExportCst.PREF_REF+"384\">j</a> "+MessagesCdmBase.LT+" <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#"+ExportCst.PREF_REF+"358\">g</a>.<b>length</b>;\n" +
                "     }\n" +
                "     public Pair"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"552\">nextPair</a>(){\n" +
                "      return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1([int,[int,int)\" href=\"#"+ExportCst.PREF_REF+"658\">new</a> Pair"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+"(<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#"+ExportCst.PREF_REF+"358\">g</a>,<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.h\" href=\"#"+ExportCst.PREF_REF+"372\">h</a>,<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.j\" href=\"#"+ExportCst.PREF_REF+"384\">j</a>++)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"603\">{</a>\n" +
                "       int[] <a name=\""+ExportCst.PREF_REF+"618\">k</a>;\n" +
                "       int[] <a name=\""+ExportCst.PREF_REF+"634\">l</a>;\n" +
                "       int <a name=\""+ExportCst.PREF_REF+"648\">m</a>;\n" +
                "       <a name=\""+ExportCst.PREF_REF+"658\">Pair(</a>int[] <a name=\""+ExportCst.PREF_REF+"669\">b</a>,int[] <a name=\""+ExportCst.PREF_REF+"677\">a</a>, int <a name=\""+ExportCst.PREF_REF+"684\">z</a>){\n" +
                "        <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.k\" href=\"#"+ExportCst.PREF_REF+"618\">k</a> = <a href=\"#"+ExportCst.PREF_REF+"669\">b</a>;\n" +
                "        <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.l\" href=\"#"+ExportCst.PREF_REF+"634\">l</a> = <a href=\"#"+ExportCst.PREF_REF+"677\">a</a>;\n" +
                "        <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#"+ExportCst.PREF_REF+"648\">m</a> = <a href=\"#"+ExportCst.PREF_REF+"684\">z</a>;\n" +
                "       }\n" +
                "       public int <a name=\""+ExportCst.PREF_REF+"760\">getFirst</a>(){\n" +
                "        return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.k\" href=\"#"+ExportCst.PREF_REF+"618\">k</a>[<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#"+ExportCst.PREF_REF+"648\">m</a>];\n" +
                "       }\n" +
                "       public int <a name=\""+ExportCst.PREF_REF+"820\">getSecond</a>(){\n" +
                "        return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.l\" href=\"#"+ExportCst.PREF_REF+"634\">l</a>[<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#"+ExportCst.PREF_REF+"648\">m</a>];\n" +
                "       }\n" +
                "      }</span>;\n" +
                "     }\n" +
                "    }</span>;\n" +
                "   }\n" +
                "  }</span>) {\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>+=<a href=\"#"+ExportCst.PREF_REF+"105\">y</a>+<span class=\"s\">\",\"</span>+<a href=\"#"+ExportCst.PREF_REF+"98\">i</a>+<span class=\"s\">\";\"</span>;\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>;\n" +
                "  <a title=\"The type int cannot be implicitly cast to $core.String\n" +
                "\n" +
                "The code is unreachable in the function static m()\" class=\"e\">return</a> 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report657Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Init {\n");
        xml_.append(" int field = 2;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Until {\n");
        xml_.append(" int field = 15;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Step {\n");
        xml_.append(" int field = 3;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int v = new Init(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field;new Until(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field;new Step(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field){\n");
        xml_.append("   sum += v;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Init</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"22\">field</a> = 2;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"41\">pkg.Until</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"58\">field</a> = 15;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"78\">pkg.Step</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"94\">field</a> = 3;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"113\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"135\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"157\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"168\">sum</a> = 0;\n" +
                "  iter (int <a name=\""+ExportCst.PREF_REF+"189\">v</a> = new <a title=\"pkg.Init\" href=\"#"+ExportCst.PREF_REF+"6\">Init</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"203\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"219\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Init.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;new <a title=\"pkg.Until\" href=\"#"+ExportCst.PREF_REF+"41\">Until</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"250\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"266\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Until.field\" href=\"#"+ExportCst.PREF_REF+"58\">field</a>;new <a title=\"pkg.Step\" href=\"#"+ExportCst.PREF_REF+"78\">Step</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"296\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"312\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Step.field\" href=\"#"+ExportCst.PREF_REF+"94\">field</a>){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"168\">sum</a> += <a href=\"#"+ExportCst.PREF_REF+"189\">v</a>;\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"168\">sum</a>;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report658Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Init {\n");
        xml_.append(" int field = 2;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Until {\n");
        xml_.append(" int field = 15;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Step {\n");
        xml_.append(" int field = 3;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int v = new Init(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field;v < new Until(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field;v += new Step(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field){\n");
        xml_.append("   sum += v;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Init</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"22\">field</a> = 2;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"41\">pkg.Until</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"58\">field</a> = 15;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"78\">pkg.Step</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"94\">field</a> = 3;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"113\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"135\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"157\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"168\">sum</a> = 0;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"188\">v</a> = new <a title=\"pkg.Init\" href=\"#"+ExportCst.PREF_REF+"6\">Init</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"202\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"218\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Init.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;<a href=\"#"+ExportCst.PREF_REF+"188\">v</a> "+MessagesCdmBase.LT+" new <a title=\"pkg.Until\" href=\"#"+ExportCst.PREF_REF+"41\">Until</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"253\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"269\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Until.field\" href=\"#"+ExportCst.PREF_REF+"58\">field</a>;<a href=\"#"+ExportCst.PREF_REF+"188\">v</a> += new <a title=\"pkg.Step\" href=\"#"+ExportCst.PREF_REF+"78\">Step</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"304\">{</a>\n" +
                "   public int <a name=\""+ExportCst.PREF_REF+"320\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Step.field\" href=\"#"+ExportCst.PREF_REF+"94\">field</a>){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"168\">sum</a> += <a href=\"#"+ExportCst.PREF_REF+"188\">v</a>;\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"168\">sum</a>;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report659Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int CST = 1;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  switch(1){\n");
        xml_.append("   case new Int(){}.CST;\n");
        xml_.append("    res += 1;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"21\">CST</a> = 1;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"38\">pkg.Ext</a> {\n" +
                " static String <a name=\""+ExportCst.PREF_REF+"63\">m</a>(){\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  switch(1){\n" +
                "   <a title=\"The case block with expression new Int(){}.CST is not constant.\" class=\"e\">case</a> new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"117\">{</a>}</span>.<a title=\"pkg.Int.CST\" href=\"#"+ExportCst.PREF_REF+"21\">CST</a>;\n" +
                "    <a href=\"#"+ExportCst.PREF_REF+"77\">res</a> += 1;\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"77\">res</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report660Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  new Enum<MyEnum>(){};\n");
        xml_.append(" }\n");
        xml_.append(" enum MyEnum{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  new Enum"+MessagesCdmBase.LT+"<a title=\"pkg.Ext..MyEnum\" href=\"#"+ExportCst.PREF_REF+"67\">MyEnum</a>"+MessagesCdmBase.GT+"()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"54\" title=\"The type pkg.Ext..Enum*1 cannot have explicitly the type $core.Enum as super type because $core.Enum is reserved.\" class=\"e\">{</a>}</span>;\n" +
                " }\n" +
                " enum <a name=\""+ExportCst.PREF_REF+"67\">MyEnum</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report661Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  new $en(){};\n");
        xml_.append(" }\n");
        xml_.append(" enum MyEnum{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  new $en()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"45\" title=\"The type pkg.Ext..$en*1 cannot have explicitly the type $core.$en as super type because $core.$en is reserved.\" class=\"e\">{</a>}</span>;\n" +
                " }\n" +
                " enum <a name=\""+ExportCst.PREF_REF+"58\">MyEnum</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report662Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.MySub() {\n");
        xml_.append(" $int i\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$operator+ pkg.MySub() {\n" +
                " $int i<a title=\"Bad index by parsing.\" class=\"e\">\n" +
                "</a></span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report663Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  new CharSequence(){int nonSeen;};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  <a title=\"The type $core.CharSequence is not resolved for instancing.\" class=\"e\">new</a> CharSequence()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"54\">{</a>int <a name=\""+ExportCst.PREF_REF+"59\">nonSeen</a>;}</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report664Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int a:int)->{}.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  return <span class=\"t\">(int <a name=\""+ExportCst.PREF_REF+"47\">a</a>:int)<a name=\""+ExportCst.PREF_REF+"53\" title=\"A throw block or a return block is missing for the method static .1(int).\" class=\"e\">-"+MessagesCdmBase.GT+"</a>{}</span>.<b>call</b>(3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report665Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int a,int a:int)->{return 0;}.call(3,4);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  return <span class=\"t\">(int <a name=\""+ExportCst.PREF_REF+"47\">a</a>,int <a name=\""+ExportCst.PREF_REF+"53\" title=\"The parameter function name a is duplicated.\" class=\"e\">a</a>:int)<a name=\""+ExportCst.PREF_REF+"59\">-"+MessagesCdmBase.GT+"</a>{return 0;}</span>.<b>call</b>(3,4);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report666Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  (int a:void)->{return 0;}.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  <span class=\"t\">(int <a name=\""+ExportCst.PREF_REF+"41\">a</a>:void)<a name=\""+ExportCst.PREF_REF+"48\">-"+MessagesCdmBase.GT+"</a>{<a title=\"The type cannot be the key word void.\" class=\"e\">return</a> 0;}</span>.<b>call</b>(3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report667Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"145\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"167\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report668Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" ONE;\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"89\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"122\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"133\">ONE</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"150\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report669Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" ONE{};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"89\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"122\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"133\">ONE</a>{};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"152\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report670Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE{};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"106\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"91\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " int <a name=\""+ExportCst.PREF_REF+"106\">field</a>();\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"122\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"133\">ONE</a>{};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"152\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report671Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" @Annot\n");
        xml_.append(" int field()new Int(){}.FIELD;\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE{};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>\n" +
                " int <a name=\""+ExportCst.PREF_REF+"81\">field</a>()new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"97\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>;\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"114\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"125\">ONE</a>{};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"144\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report672Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" int field()new Int(){}.FIELD;\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE{};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"106\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"91\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " int <a name=\""+ExportCst.PREF_REF+"106\">field</a>()new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"122\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>;\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"139\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"150\">ONE</a>{};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"169\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report673Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=Int.FIELD)\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=<a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"148\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"170\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"192\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report674Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" @Annot(field=Int.FIELD)\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=<a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"170\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"192\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report675Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"156\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"178\">extField</a>;\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"200\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report676Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" Ext(){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"134\">Ext(</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"155\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report677Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" Ext(@Annot(field=new Int(){}.FIELD) int p){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"134\">Ext(</a>@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"160\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"174\">p</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"192\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report678Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" Ext(@Annot(field=new Int(){}.FIELD)@Annot(field=new Int(){}.FIELD) int p){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"134\">Ext(</a>@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"160\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"191\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"205\">p</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"223\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report679Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" Ext(@Annot(field=new Int(){}.FIELD) int p, @Annot(field=new Int(){}.FIELD) int q){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"134\">Ext(</a>@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"160\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"174\">p</a>, @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"199\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"213\">q</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"231\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report680Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static void l(){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"146\">l</a>(){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"165\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report681Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static void l(@Annot(field=new Int(){}.FIELD) int p){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"146\">l</a>(@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"184\">p</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"202\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report682Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static void l(@Annot(field=new Int(){}.FIELD)@Annot(field=new Int(){}.FIELD) int p){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"146\">l</a>(@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"201\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"215\">p</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"233\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report683Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" static void l(@Annot(field=new Int(){}.FIELD) int p, @Annot(field=new Int(){}.FIELD) int q){};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"146\">l</a>(@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"184\">p</a>, @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"209\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"223\">q</a>){};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"241\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report684Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" void this(@Annot(field=new Int(){}.FIELD) int p){};\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" int this(@Annot(field=new Int(){}.FIELD) int p){return 0;};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " void <a name=\""+ExportCst.PREF_REF+"139\">this</a>(@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"166\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"180\">p</a>){};\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"209\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " int <a name=\""+ExportCst.PREF_REF+"224\">this</a>(@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"251\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"265\">p</a>){return 0;};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"292\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report685Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" @Annot(field=new Int(){}.FIELD)\n");
        xml_.append(" operator+ int(@Annot(field=new Int(){}.FIELD) int p){return 0;};\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                " operator<a name=\""+ExportCst.PREF_REF+"142\">+</a> int(@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>) int <a name=\""+ExportCst.PREF_REF+"184\">p</a>){return 0;};\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"211\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report686Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("@Annot(field=new Int(){}.FIELD)\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Int</a> {\n" +
                " static final int <a name=\""+ExportCst.PREF_REF+"34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\""+ExportCst.PREF_REF+"56\">pkg.Annot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"73\">field</a>();\n" +
                "}\n" +
                "@<a title=\"pkg.Annot\" href=\"#"+ExportCst.PREF_REF+"56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#"+ExportCst.PREF_REF+"73\">field</a>=new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"6\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"106\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#"+ExportCst.PREF_REF+"34\">FIELD</a>)\n" +
                "class <a name=\""+ExportCst.PREF_REF+"122\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"144\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report687Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  return m(a -> a -> a * ##a,2,3);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>).<a title=\"pkg.Ext.staticCall m()\" href=\"#"+ExportCst.PREF_REF+"82\">m</a>();\n" +
                " }\n" +
                " staticCall int <a name=\""+ExportCst.PREF_REF+"82\">m</a>(){\n" +
                "  return <a title=\"pkg.Ext.staticCall m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"141\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"98\">a</a> <a name=\""+ExportCst.PREF_REF+"100\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"103\">a</a> <a name=\""+ExportCst.PREF_REF+"105\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"103\">a</a> <a title=\"The operands types int;$core.Object for the operator * are unexpected.\" class=\"e\">*</a> <a title=\"There is no accessible field named ##a from the type pkg.Ext in this context.\" class=\"e\">##a</a></span></span>,2,3);\n" +
                " }\n" +
                " staticCall int <a name=\""+ExportCst.PREF_REF+"141\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"165\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"173\">a</a>,int <a name=\""+ExportCst.PREF_REF+"179\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"165\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"173\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"179\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report688Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([##i]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"54\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"75\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"187\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\">a</a> <a name=\""+ExportCst.PREF_REF+"112\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"115\">i</a> <a name=\""+ExportCst.PREF_REF+"117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"54\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"115\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable ##i is undefined in this context.\" class=\"e\">##i</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#"+ExportCst.PREF_REF+"75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"187\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"211\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"219\">a</a>,int <a name=\""+ExportCst.PREF_REF+"225\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"211\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"219\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"225\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report689Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([#a]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"54\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"75\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"186\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\">a</a> <a name=\""+ExportCst.PREF_REF+"112\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"115\">i</a> <a name=\""+ExportCst.PREF_REF+"117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"54\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"115\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable #a is undefined in this context.\" class=\"e\">#a</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#"+ExportCst.PREF_REF+"75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"186\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"210\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"218\">a</a>,int <a name=\""+ExportCst.PREF_REF+"224\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"210\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"218\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"224\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report690Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([#]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"54\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"75\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"185\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\">a</a> <a name=\""+ExportCst.PREF_REF+"112\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"115\">i</a> <a name=\""+ExportCst.PREF_REF+"117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"54\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"115\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable # is undefined in this context.\" class=\"e\">#</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#"+ExportCst.PREF_REF+"75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"185\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"209\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"217\">a</a>,int <a name=\""+ExportCst.PREF_REF+"223\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"209\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"217\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"223\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report691Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([,]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"54\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"75\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"185\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\">a</a> <a name=\""+ExportCst.PREF_REF+"112\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"115\">i</a> <a name=\""+ExportCst.PREF_REF+"117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"54\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"115\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable , is undefined in this context.\" class=\"e\">,</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#"+ExportCst.PREF_REF+"75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"185\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"209\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"217\">a</a>,int <a name=\""+ExportCst.PREF_REF+"223\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"209\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"217\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"223\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report692Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([,a]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"54\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"75\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"186\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\">a</a> <a name=\""+ExportCst.PREF_REF+"112\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"115\">i</a> <a name=\""+ExportCst.PREF_REF+"117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"54\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"115\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable ,a is undefined in this context.\" class=\"e\">,a</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#"+ExportCst.PREF_REF+"75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"186\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"210\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"218\">a</a>,int <a name=\""+ExportCst.PREF_REF+"224\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"210\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"218\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"224\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report693Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([,a,b]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"54\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"75\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"75\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"188\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"110\">a</a> <a name=\""+ExportCst.PREF_REF+"112\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"115\">i</a> <a name=\""+ExportCst.PREF_REF+"117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a> <a href=\"#"+ExportCst.PREF_REF+"54\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"115\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable ,a,b is undefined in this context.\" class=\"e\">,a,b</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#"+ExportCst.PREF_REF+"75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"188\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"212\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"220\">a</a>,int <a name=\""+ExportCst.PREF_REF+"226\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"212\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"220\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"226\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report694Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  final int i = 1;\n");
        xml_.append("  i = 2;\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"45\">i</a> = 1;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"45\">i</a> <a title=\"The field i is already assigned.\" class=\"e\">=</a> 2;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"45\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report695Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  final int i = 1;\n");
        xml_.append("  i = \"2\";\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"45\">i</a> = 1;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"45\">i</a> <a title=\"The type $core.String cannot be implicitly cast to int\" class=\"e\">=</a> <span class=\"s\">\"2\"</span>;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"45\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report696Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  final int i = 1;\n");
        xml_.append("  i++;\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"45\">i</a> = 1;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"45\">i</a><a title=\"The field i is already assigned.\" class=\"e\">++</a>;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"45\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report697Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  final int i = 1;\n");
        xml_.append("  i+=1;\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"45\">i</a> = 1;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"45\">i</a>+<a title=\"The field i is already assigned.\" class=\"e\">=</a>1;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"45\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report698Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  for (final int i = 1;i<2;){\n");
        xml_.append("   i = 2;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  for (final int <a name=\""+ExportCst.PREF_REF+"50\">i</a> = 1;<a href=\"#"+ExportCst.PREF_REF+"50\">i</a>"+MessagesCdmBase.LT+"2;){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"50\">i</a> <a title=\"The field i is already assigned.\" class=\"e\">=</a> 2;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report699Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  for (final int i = 1;i<2;){\n");
        xml_.append("   i = \"2\";\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  for (final int <a name=\""+ExportCst.PREF_REF+"50\">i</a> = 1;<a href=\"#"+ExportCst.PREF_REF+"50\">i</a>"+MessagesCdmBase.LT+"2;){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"50\">i</a> <a title=\"The type $core.String cannot be implicitly cast to int\" class=\"e\">=</a> <span class=\"s\">\"2\"</span>;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report700Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  for (final int i = 1;i<2;){\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  for (final int <a name=\""+ExportCst.PREF_REF+"50\">i</a> = 1;<a href=\"#"+ExportCst.PREF_REF+"50\">i</a>"+MessagesCdmBase.LT+"2;){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"50\">i</a><a title=\"The field i is already assigned.\" class=\"e\">++</a>;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report701Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  for (final int i = i = 1;i<2;){\n");
        xml_.append("   i += 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  for (final int <a name=\""+ExportCst.PREF_REF+"50\">i</a> = <a href=\"#"+ExportCst.PREF_REF+"50\">i</a> = 1;<a href=\"#"+ExportCst.PREF_REF+"50\">i</a>"+MessagesCdmBase.LT+"2;){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"50\">i</a> +<a title=\"The field i is already assigned.\" class=\"e\">=</a> 1;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report702Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  final int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> {final int b = 0;\n");
        xml_.append("   b = 1;\n");
        xml_.append("   return sum2 + i + a;},2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"60\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"81\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"218\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"116\">a</a> <a name=\""+ExportCst.PREF_REF+"118\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"121\">i</a> <a name=\""+ExportCst.PREF_REF+"123\">-"+MessagesCdmBase.GT+"</a> {final int <a name=\""+ExportCst.PREF_REF+"137\">b</a> = 0;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"137\">b</a> <a title=\"The field b is already assigned.\" class=\"e\">=</a> 1;\n" +
                "   return <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"121\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"116\">a</a>;}</span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"218\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"242\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"250\">a</a>,int <a name=\""+ExportCst.PREF_REF+"256\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"242\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"250\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"256\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report703Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  final int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> {final int b = 0;\n");
        xml_.append("   sum2 = 1;\n");
        xml_.append("   return sum2 + i + a;},2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"60\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"81\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"221\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"116\">a</a> <a name=\""+ExportCst.PREF_REF+"118\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"121\">i</a> <a name=\""+ExportCst.PREF_REF+"123\">-"+MessagesCdmBase.GT+"</a> {final int <a name=\""+ExportCst.PREF_REF+"137\">b</a> = 0;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> <a title=\"The field sum2 is already assigned.\" class=\"e\">=</a> 1;\n" +
                "   return <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"121\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"116\">a</a>;}</span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"221\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"245\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"253\">a</a>,int <a name=\""+ExportCst.PREF_REF+"259\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"245\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"253\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"259\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report704Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  final int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> {final int b = 0;\n");
        xml_.append("   sum2 = \"1\";\n");
        xml_.append("   return sum2 + i + a;},2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"60\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"81\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"223\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"116\">a</a> <a name=\""+ExportCst.PREF_REF+"118\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"121\">i</a> <a name=\""+ExportCst.PREF_REF+"123\">-"+MessagesCdmBase.GT+"</a> {final int <a name=\""+ExportCst.PREF_REF+"137\">b</a> = 0;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> <a title=\"The type $core.String cannot be implicitly cast to int\" class=\"e\">=</a> <span class=\"s\">\"1\"</span>;\n" +
                "   return <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"121\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"116\">a</a>;}</span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"223\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"247\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"255\">a</a>,int <a name=\""+ExportCst.PREF_REF+"261\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"247\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"255\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"261\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report705Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  final int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> {final int b = 0;\n");
        xml_.append("   sum2++;\n");
        xml_.append("   return sum2 + i + a;},2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"60\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"81\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"219\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"116\">a</a> <a name=\""+ExportCst.PREF_REF+"118\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"121\">i</a> <a name=\""+ExportCst.PREF_REF+"123\">-"+MessagesCdmBase.GT+"</a> {final int <a name=\""+ExportCst.PREF_REF+"137\">b</a> = 0;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a><a title=\"The field sum2 is already assigned.\" class=\"e\">++</a>;\n" +
                "   return <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"121\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"116\">a</a>;}</span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"219\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"243\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"251\">a</a>,int <a name=\""+ExportCst.PREF_REF+"257\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"243\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"251\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"257\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report706Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  final int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> {final int b = 0;\n");
        xml_.append("   sum2+=1;\n");
        xml_.append("   return sum2 + i + a;},2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">sum</a> = 0;\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"60\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"81\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"81\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"220\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"116\">a</a> <a name=\""+ExportCst.PREF_REF+"118\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"121\">i</a> <a name=\""+ExportCst.PREF_REF+"123\">-"+MessagesCdmBase.GT+"</a> {final int <a name=\""+ExportCst.PREF_REF+"137\">b</a> = 0;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a>+<a title=\"The field sum2 is already assigned.\" class=\"e\">=</a>1;\n" +
                "   return <a href=\"#"+ExportCst.PREF_REF+"60\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"121\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"116\">a</a>;}</span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"220\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"244\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"252\">a</a>,int <a name=\""+ExportCst.PREF_REF+"258\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"244\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"252\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"258\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report707Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(int j){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  final int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> {final int b = 0;\n");
        xml_.append("   j = 1;\n");
        xml_.append("   return sum2 + i + a;},2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(int <a name=\""+ExportCst.PREF_REF+"34\">j</a>){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"44\">sum</a> = 0;\n" +
                "  final int <a name=\""+ExportCst.PREF_REF+"65\">sum2</a> = 3;\n" +
                "  for (int <a name=\""+ExportCst.PREF_REF+"86\">i</a> = 1; <a href=\"#"+ExportCst.PREF_REF+"86\">i</a> "+MessagesCdmBase.LT+"= 9; <a href=\"#"+ExportCst.PREF_REF+"86\">i</a>+= 2){\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"44\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,$core.Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"223\">m</a>(<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"121\">a</a> <a name=\""+ExportCst.PREF_REF+"123\">-"+MessagesCdmBase.GT+"</a> <span class=\"t\"><a name=\""+ExportCst.PREF_REF+"126\">i</a> <a name=\""+ExportCst.PREF_REF+"128\">-"+MessagesCdmBase.GT+"</a> {final int <a name=\""+ExportCst.PREF_REF+"142\">b</a> = 0;\n" +
                "   <a href=\"#"+ExportCst.PREF_REF+"34\">j</a> <a title=\"The field j is already assigned.\" class=\"e\">=</a> 1;\n" +
                "   return <a href=\"#"+ExportCst.PREF_REF+"65\">sum2</a> + <a href=\"#"+ExportCst.PREF_REF+"126\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"121\">a</a>;}</span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"223\">m</a>(Fct"+MessagesCdmBase.LT+"int,Fct"+MessagesCdmBase.LT+"int,int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"247\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"255\">a</a>,int <a name=\""+ExportCst.PREF_REF+"261\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"247\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"255\">a</a>).<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"261\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report708Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return m(2,c:5,d:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return <a title=\"The function static m(int,int,int) is undefined.\" class=\"e\">m</a>(2,c:5,d:3);\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"74\">m</a>(int <a name=\""+ExportCst.PREF_REF+"80\">a</a>,int <a name=\""+ExportCst.PREF_REF+"86\">b</a>,int <a name=\""+ExportCst.PREF_REF+"92\">c</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"80\">a</a>*<a href=\"#"+ExportCst.PREF_REF+"86\">b</a>+<a href=\"#"+ExportCst.PREF_REF+"92\">c</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report709Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return m(2,c:5,10,d:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c,int d){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return m(2,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:5,10,d:3);\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"77\">m</a>(int <a name=\""+ExportCst.PREF_REF+"83\">a</a>,int <a name=\""+ExportCst.PREF_REF+"89\">b</a>,int <a name=\""+ExportCst.PREF_REF+"95\">c</a>,int <a name=\""+ExportCst.PREF_REF+"101\">d</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"83\">a</a>*<a href=\"#"+ExportCst.PREF_REF+"89\">b</a>+<a href=\"#"+ExportCst.PREF_REF+"95\">c</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report710Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return m(2,c:5,c:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return m(2,c:5,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:3);\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"74\">m</a>(int <a name=\""+ExportCst.PREF_REF+"80\">a</a>,int <a name=\""+ExportCst.PREF_REF+"86\">b</a>,int <a name=\""+ExportCst.PREF_REF+"92\">c</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"80\">a</a>*<a href=\"#"+ExportCst.PREF_REF+"86\">b</a>+<a href=\"#"+ExportCst.PREF_REF+"92\">c</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report711Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return (a:3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return (<a title=\"The parameter function name a is duplicated.\" class=\"e\">a</a>:3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report712Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  new Ext(2,c:5,c:3);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int a,int b,int c){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>(2,c:5,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:3);\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"60\">Ext(</a>int <a name=\""+ExportCst.PREF_REF+"68\">a</a>,int <a name=\""+ExportCst.PREF_REF+"74\">b</a>,int <a name=\""+ExportCst.PREF_REF+"80\">c</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report713Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  new Ext(2,c:5,c:3){\n");
        xml_.append("   Ext(int a,int b, int c){\n");
        xml_.append("    super(a,b,c);\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int a,int b,int c){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>(2,c:5,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:3)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"54\">{</a>\n" +
                "   <a name=\""+ExportCst.PREF_REF+"59\">Ext(</a>int <a name=\""+ExportCst.PREF_REF+"67\">a</a>,int <a name=\""+ExportCst.PREF_REF+"73\">b</a>, int <a name=\""+ExportCst.PREF_REF+"80\">c</a>){\n" +
                "    <a title=\"pkg.Ext.pkg.Ext(int,int,int)\" href=\"#"+ExportCst.PREF_REF+"116\">super</a>(<a href=\"#"+ExportCst.PREF_REF+"67\">a</a>,<a href=\"#"+ExportCst.PREF_REF+"73\">b</a>,<a href=\"#"+ExportCst.PREF_REF+"80\">c</a>);\n" +
                "   }\n" +
                "  }</span>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"116\">Ext(</a>int <a name=\""+ExportCst.PREF_REF+"124\">a</a>,int <a name=\""+ExportCst.PREF_REF+"130\">b</a>,int <a name=\""+ExportCst.PREF_REF+"136\">c</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report714Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  new Ext(){\n");
        xml_.append("   Ext(){\n");
        xml_.append("    super(2,c:5,c:3);\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int a,int b,int c){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  <a title=\"pkg.Ext..Ext*1.pkg.Ext..Ext*1()\" href=\"#"+ExportCst.PREF_REF+"50\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"45\">{</a>\n" +
                "   <a name=\""+ExportCst.PREF_REF+"50\">Ext(</a>){\n" +
                "    super(2,c:5,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:3);\n" +
                "   }\n" +
                "  }</span>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"93\">Ext(</a>int <a name=\""+ExportCst.PREF_REF+"101\">a</a>,int <a name=\""+ExportCst.PREF_REF+"107\">b</a>,int <a name=\""+ExportCst.PREF_REF+"113\">c</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report715Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return classchoice(Ext)m(2,c:5,c:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return classchoice(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>)m(2,c:5,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:3);\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"90\">m</a>(int <a name=\""+ExportCst.PREF_REF+"96\">a</a>,int <a name=\""+ExportCst.PREF_REF+"102\">b</a>,int <a name=\""+ExportCst.PREF_REF+"108\">c</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"96\">a</a>*<a href=\"#"+ExportCst.PREF_REF+"102\">b</a>+<a href=\"#"+ExportCst.PREF_REF+"108\">c</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report716Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return superaccess(Ext)m(2,c:5,c:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return superaccess(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>)m(2,c:5,<a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a>:3);\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"90\">m</a>(int <a name=\""+ExportCst.PREF_REF+"96\">a</a>,int <a name=\""+ExportCst.PREF_REF+"102\">b</a>,int <a name=\""+ExportCst.PREF_REF+"108\">c</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"96\">a</a>*<a href=\"#"+ExportCst.PREF_REF+"102\">b</a>+<a href=\"#"+ExportCst.PREF_REF+"108\">c</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report717Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext()[0,b:1,b:2];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int a,int b){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" void this(int a,int b){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>()[0,b:1,<a title=\"The parameter function name b is duplicated.\" class=\"e\">b</a>:2];\n" +
                " }\n" +
                " int <a name=\""+ExportCst.PREF_REF+"75\">this</a>(int <a name=\""+ExportCst.PREF_REF+"84\">a</a>,int <a name=\""+ExportCst.PREF_REF+"90\">b</a>){\n" +
                "  return 0;\n" +
                " }\n" +
                " void <a name=\""+ExportCst.PREF_REF+"115\">this</a>(int <a name=\""+ExportCst.PREF_REF+"124\">a</a>,int <a name=\""+ExportCst.PREF_REF+"130\">b</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report718Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return operator(+,Ext)(0,b:1,b:2);\n");
        xml_.append(" }\n");
        xml_.append(" operator+ int(int a,int b){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return operator(+,<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>)(0,b:1,<a title=\"The parameter function name b is duplicated.\" class=\"e\">b</a>:2);\n" +
                " }\n" +
                " operator<a name=\""+ExportCst.PREF_REF+"85\">+</a> int(int <a name=\""+ExportCst.PREF_REF+"95\">a</a>,int <a name=\""+ExportCst.PREF_REF+"101\">b</a>){\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report719Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return (a:3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return (<a title=\"The parameter function name a is duplicated.\" class=\"e\">a</a>:3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report720Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext(2,c:5,d:3);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int a,int b,int c){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext(int,int,int) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>(2,c:5,d:3);\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"69\">Ext(</a>int <a name=\""+ExportCst.PREF_REF+"77\">a</a>,int <a name=\""+ExportCst.PREF_REF+"83\">b</a>,int <a name=\""+ExportCst.PREF_REF+"89\">c</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report721Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext(a:2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext(int) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>(a:2);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report722Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(b:new Ext<>(11));\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+").<a title=\"The function staticCall m($core.Object) is undefined.\" class=\"e\">m</a>(b:<a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11));\n" +
                " }\n" +
                " staticCall <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"141\">m</a>(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"150\">a</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"150\">a</a>.<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report723Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext<int>(b:new Ext<>(11));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"55\">Ext(</a><a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"66\">a</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"66\">a</a>.<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"105\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"($core.Object) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(b:<a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report724Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(b:new Ext<>[]{new Ext<>(11)});\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+").<a title=\"The function staticCall m([$core.Object) is undefined.\" class=\"e\">m</a>(b:new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"[] is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"[]<a title=\"The type $core.Object is unexpected.\" class=\"e\">{</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11)});\n" +
                " }\n" +
                " staticCall <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"154\">m</a>(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"[] <a name=\""+ExportCst.PREF_REF+"165\">a</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"165\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report725Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext<int>(b:new Ext<>[]{new Ext<>(11)});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"55\">Ext(</a><a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"[] <a name=\""+ExportCst.PREF_REF+"68\">a</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"68\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"110\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"([$core.Object) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(b:new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"[] is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"[]<a title=\"The type $core.Object is unexpected.\" class=\"e\">{</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11)});\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report726Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(c:a -> 2 * a,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Fct<T,T> a,T b){\n");
        xml_.append("  return a.call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+").<a title=\"The function staticCall m($core.Fct"+MessagesCdmBase.LT+"$core.Object,$core.Object"+MessagesCdmBase.GT+",int) is undefined.\" class=\"e\">m</a>(c:<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"108\">a</a> <a name=\""+ExportCst.PREF_REF+"110\">-"+MessagesCdmBase.GT+"</a> 2 <a title=\"The operands types int;$core.Object for the operator * are unexpected.\" class=\"e\">*</a> <a href=\"#"+ExportCst.PREF_REF+"108\">a</a></span>,b:3);\n" +
                " }\n" +
                " staticCall <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"142\">m</a>(Fct"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>,<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"153\">a</a>,<a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"157\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"153\">a</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"157\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report727Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<T,T> a,T b){\n");
        xml_.append("  field=a.call(b);\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext<int>(c:a -> 2 * a,b:3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"55\">Ext(</a>Fct"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>,<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"68\">a</a>,<a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"72\">b</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"68\">a</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"72\">b</a>);\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"113\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"($core.Fct"+MessagesCdmBase.LT+"$core.Object,$core.Object"+MessagesCdmBase.GT+",int) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(c:<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"142\">a</a> <a name=\""+ExportCst.PREF_REF+"144\">-"+MessagesCdmBase.GT+"</a> 2 <a title=\"The operands types int;$core.Object for the operator * are unexpected.\" class=\"e\">*</a> <a href=\"#"+ExportCst.PREF_REF+"142\">a</a></span>,b:3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report728Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(new Ext<>[1][1],0,0,null);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Object m(Object[][] a,int b, int c, Object d){\n");
        xml_.append("  return a[b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+").<a title=\"pkg.Ext.staticCall m([[$core.Object,int,int,$core.Object)\" href=\"#"+ExportCst.PREF_REF+"155\">m</a>(new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"[1][1],0,0,null);\n" +
                " }\n" +
                " staticCall Object <a name=\""+ExportCst.PREF_REF+"155\">m</a>(Object[][] <a name=\""+ExportCst.PREF_REF+"168\">a</a>,int <a name=\""+ExportCst.PREF_REF+"174\">b</a>, int <a name=\""+ExportCst.PREF_REF+"181\">c</a>, Object <a name=\""+ExportCst.PREF_REF+"191\">d</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"168\">a</a>[<a href=\"#"+ExportCst.PREF_REF+"174\">b</a>][<a href=\"#"+ExportCst.PREF_REF+"181\">c</a>]=<a href=\"#"+ExportCst.PREF_REF+"191\">d</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report729Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Object[][] a,int b, int c, Object d){\n");
        xml_.append("  field=(a[b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext<int>(new Ext<>[1][1],0,0,null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"55\">Ext(</a>Object[][] <a name=\""+ExportCst.PREF_REF+"70\">a</a>,int <a name=\""+ExportCst.PREF_REF+"76\">b</a>, int <a name=\""+ExportCst.PREF_REF+"83\">c</a>, Object <a name=\""+ExportCst.PREF_REF+"93\">d</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a><a title=\"The type $core.Object cannot be implicitly cast to #T\" class=\"e\">=</a>(<a href=\"#"+ExportCst.PREF_REF+"70\">a</a>[<a href=\"#"+ExportCst.PREF_REF+"76\">b</a>][<a href=\"#"+ExportCst.PREF_REF+"83\">c</a>]=<a href=\"#"+ExportCst.PREF_REF+"93\">d</a>).<a title=\"There is no accessible field named field from the type $core.Object in this context.\" class=\"e\">field</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"142\">m</a>(){\n" +
                "  return <a title=\"pkg.Ext.pkg.Ext([[$core.Object,int,int,$core.Object)\" href=\"#"+ExportCst.PREF_REF+"55\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"[1][1],0,0,null);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report730Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(e:new Ext<>[1][1],b:0,c:0,d:new Ext<>(11));\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+").<a title=\"The function staticCall m([[$core.Object,int,int,$core.Object) is undefined.\" class=\"e\">m</a>(e:new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"[1][1],b:0,c:0,d:<a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11));\n" +
                " }\n" +
                " staticCall <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"172\">m</a>(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"[][] <a name=\""+ExportCst.PREF_REF+"185\">a</a>,int <a name=\""+ExportCst.PREF_REF+"191\">b</a>, int <a name=\""+ExportCst.PREF_REF+"198\">c</a>, <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"208\">d</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"185\">a</a>[<a href=\"#"+ExportCst.PREF_REF+"191\">b</a>][<a href=\"#"+ExportCst.PREF_REF+"198\">c</a>]=<a href=\"#"+ExportCst.PREF_REF+"208\">d</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report731Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext<int>(e:new Ext<>[1][1],b:0,c:0,d:new Ext<>(11));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"55\">Ext(</a><a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"[][] <a name=\""+ExportCst.PREF_REF+"70\">a</a>,int <a name=\""+ExportCst.PREF_REF+"76\">b</a>, int <a name=\""+ExportCst.PREF_REF+"83\">c</a>, <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"93\">d</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=(<a href=\"#"+ExportCst.PREF_REF+"70\">a</a>[<a href=\"#"+ExportCst.PREF_REF+"76\">b</a>][<a href=\"#"+ExportCst.PREF_REF+"83\">c</a>]=<a href=\"#"+ExportCst.PREF_REF+"93\">d</a>).<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"142\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"([[$core.Object,int,int,$core.Object) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(e:new <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"[1][1],b:0,c:0,d:<a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report732Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,c:{new Ext<>(11)});\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>... a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T>... a){\n");
        xml_.append("  return (T)((int)a[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>[] a, int... b){\n");
        xml_.append("  return (T)((int)a[0].field+b[0]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+").<a title=\"The function staticCall m(int,[$core.Object) is undefined.\" class=\"e\">m</a>(15,c:<a title=\"The type  is unexpected.\" class=\"e\">{</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11)});\n" +
                " }\n" +
                " staticCall <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"146\">m</a>(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"... <a name=\""+ExportCst.PREF_REF+"158\">a</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"158\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                " staticCall <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"200\">m</a>(int <a name=\""+ExportCst.PREF_REF+"206\">b</a>,<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"... <a name=\""+ExportCst.PREF_REF+"218\">a</a>){\n" +
                "  return (<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>)((int)<a href=\"#"+ExportCst.PREF_REF+"218\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>+<a href=\"#"+ExportCst.PREF_REF+"206\">b</a>);\n" +
                " }\n" +
                " staticCall <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"272\">m</a>(<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"[] <a name=\""+ExportCst.PREF_REF+"283\">a</a>, int... <a name=\""+ExportCst.PREF_REF+"293\">b</a>){\n" +
                "  return (<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>)((int)<a href=\"#"+ExportCst.PREF_REF+"283\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>+<a href=\"#"+ExportCst.PREF_REF+"293\">b</a>[0]);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report733Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>... a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T>... a){\n");
        xml_.append("  field=(T)((int)a[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a, int... b){\n");
        xml_.append("  field=(T)((int)a[0].field+b[0]);\n");
        xml_.append(" }\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return new Ext<int>(15,c:{new Ext<>(11)});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"30\">Ext(</a><a href=\"#"+ExportCst.PREF_REF+"14\">T</a> <a name=\""+ExportCst.PREF_REF+"36\">p</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"36\">p</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"55\">Ext(</a><a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"... <a name=\""+ExportCst.PREF_REF+"69\">a</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"69\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>;\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"97\">Ext(</a>int <a name=\""+ExportCst.PREF_REF+"105\">b</a>,<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"... <a name=\""+ExportCst.PREF_REF+"117\">a</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=(<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>)((int)<a href=\"#"+ExportCst.PREF_REF+"117\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>+<a href=\"#"+ExportCst.PREF_REF+"105\">b</a>);\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"157\">Ext(</a><a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+"[] <a name=\""+ExportCst.PREF_REF+"170\">a</a>, int... <a name=\""+ExportCst.PREF_REF+"180\">b</a>){\n" +
                "  <a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>=(<a href=\"#"+ExportCst.PREF_REF+"14\">T</a>)((int)<a href=\"#"+ExportCst.PREF_REF+"170\">a</a>[0].<a title=\"pkg.Ext.field\" href=\"#"+ExportCst.PREF_REF+"22\">field</a>+<a href=\"#"+ExportCst.PREF_REF+"180\">b</a>[0]);\n" +
                " }\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"237\">m</a>(){\n" +
                "  return <a title=\"The constructor pkg.Ext"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(int,[$core.Object) is undefined.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+"(15,c:<a title=\"The type  is unexpected.\" class=\"e\">{</a><a title=\"The type Ext"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not resolved for instancing.\" class=\"e\">new</a> <a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"(11)});\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report734Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  ExAbsFinal v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $final $class pkg.ExAbsFinal {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"pkg.ExAbsFinal\" href=\"#"+ExportCst.PREF_REF+"145\">ExAbsFinal</a> <a name=\""+ExportCst.PREF_REF+"65\">v</a> = $null;\n" +
                "  <a title=\"The type pkg.ExAbsFinal is unexpected.\" class=\"e\">$switch</a> (<a href=\"#"+ExportCst.PREF_REF+"65\">v</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "$public $abstract $final $class <a name=\""+ExportCst.PREF_REF+"145\">pkg.ExAbsFinal</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report735Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" ONE((1+2]){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The expression part is empty.\" class=\"e\">ONE</a>((1+2]){}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report736Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" ONE((1+2])\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The expression part is empty.\" class=\"e\">ONE</a>((1+2])\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report737Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i&&&=(MySub)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to $int\" class=\"e\">"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report738Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i???=(MySub)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to $int\" class=\"e\">???</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report739Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass e = $new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  ExClass f = $new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  e|||=f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $operator&& $boolean (ExClass i, ExClass j){\n");
        xml_.append(" }\n");
        xml_.append(" $operator|| $boolean (ExClass i, ExClass j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExClass $($boolean i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"71\">e</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"205\">field</a>=10;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"115\">f</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"115\">f</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"205\">field</a>=1;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a><a title=\"pkg.ExClass.$static $true($boolean,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"240\">|</a><a title=\"pkg.ExClass.$static ||(pkg.ExClass,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"368\">||</a>=<a href=\"#"+ExportCst.PREF_REF+"115\">f</a>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"178\">pkg.ExClass</a> {\n" +
                " $public <a title=\"The type int is unknown.\" class=\"e\">int</a> <a name=\""+ExportCst.PREF_REF+"205\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"240\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"254\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"287\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"302\">i</a>){\n" +
                " }\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"319\" title=\"A $throw block or a $return block is missing for the method $static "+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"(pkg.ExClass,pkg.ExClass).\" class=\"e\">"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"</a> $boolean (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"340\">i</a>, <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"351\">j</a>){\n" +
                " }\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"368\" title=\"A $throw block or a $return block is missing for the method $static ||(pkg.ExClass,pkg.ExClass).\" class=\"e\">||</a> $boolean (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"389\">i</a>, <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"400\">j</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"433\" title=\"A $throw block or a $return block is missing for the method $static $($boolean,pkg.ExClass).\" class=\"e\">$</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"443\">i</a>){\n" +
                " }\n" +
                " $public $static <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"178\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"475\" title=\"A $throw block or a $return block is missing for the method $static $(pkg.ExClass,$boolean).\" class=\"e\">$</a>($boolean <a name=\""+ExportCst.PREF_REF+"486\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report740Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int a = 0;\n");
        xml_.append("  that Object #v = that(a);\n");
        xml_.append("  return a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">a</a> = 0;\n" +
                "  that Object <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a> <a title=\"The type int cannot be implicitly cast to \" class=\"e\">=</a> that(<a href=\"#"+ExportCst.PREF_REF+"39\">a</a>);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"39\">a</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report741Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  int[] a = new int[0];\n");
        xml_.append("  a.clone() = new int[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"42\">a</a> = new int[0];\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"42\">a</a>.<b>clone</b>() <a title=\"The assignment operator = is unexpected.\" class=\"e\">=</a> new int[0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report742Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  int[] a = new int[0];\n");
        xml_.append("  a.clone() += new int[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"42\">a</a> = new int[0];\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"42\">a</a>.<b>clone</b>() <a title=\"The assignment operator += is unexpected.\" class=\"e\">+=</a> new int[0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report743Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  int[] a = new int[0];\n");
        xml_.append("  a.clone() ++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"42\">a</a> = new int[0];\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"42\">a</a>.<b>clone</b>() <a title=\"The assignment operator ++ is unexpected.\" class=\"e\">++</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report744Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  int[] a = new int[0];\n");
        xml_.append("  ++ a.clone();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"42\">a</a> = new int[0];\n" +
                "  <a title=\"The assignment operator ++ is unexpected.\" class=\"e\">++</a> <a href=\"#"+ExportCst.PREF_REF+"42\">a</a>.<b>clone</b>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report745Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  int a = 0;\n");
        xml_.append("  that(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"40\">a</a> = 0;\n" +
                "  <a title=\"The key word that is unexpected here.\" class=\"e\">that</a>(<a href=\"#"+ExportCst.PREF_REF+"40\">a</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report746Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@class pkg.Ext {\n");
        xml_.append(" (){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@class <a name=\""+ExportCst.PREF_REF+"7\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"18\">(</a>)<a title=\"The instance type pkg.Ext must contain only instance types and instance initilizing blocks.\" class=\"e\">{</a>\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report747Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(f:10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"107\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"107\">Rec</a>(<a title=\"The parameter function name f is duplicated.\" class=\"e\">f</a>:10);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"122\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"107\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"122\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report748Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth (){\n");
        xml_.append("  $lambda(Rec,new,f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a> (){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"87\">Rec</a>,new,f);\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"87\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"102\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report749Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(field:10,field:10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"120\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"120\">Rec</a>(<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"135\">field</a>:10,<a title=\"The parameter function name field is duplicated.\n" +
                "\n" +
                "pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"135\" class=\"e\">field</a>:10);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"135\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"120\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"135\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report750Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(field:\"10\");\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"113\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"113\">Rec</a>(<a title=\"The parameter function name field is duplicated.\n" +
                "\n" +
                "pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"128\" class=\"e\">field</a>:<span class=\"s\">\"10\"</span>);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"128\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"113\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"128\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report751Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth (){\n");
        xml_.append("  $lambda(Rec,new,field,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a> (){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"97\">Rec</a>,new,<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"112\">field</a>,<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"112\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"97\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"112\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report752Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth (){\n");
        xml_.append("  $lambda(Rec<?>,new,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec<T> {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a> (){\n" +
                "  <a title=\"The argument ? of the type pkg.Rec"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" is bound. It cannot be used in constructor call.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"94\">Rec</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+",new,field);\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"94\">pkg.Rec</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"102\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"112\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report753Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  int[] a = new int[0];\n");
        xml_.append("  that int[] b = that(a.clone());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"42\">a</a> = new int[0];\n" +
                "  that int[] <a name=\""+ExportCst.PREF_REF+"71\">b</a> <a title=\"The type $core.Object cannot be implicitly cast to [int\" class=\"e\">=</a> <a title=\"The key word that is unexpected here.\" class=\"e\">that</a>(<a href=\"#"+ExportCst.PREF_REF+"42\">a</a>.<b>clone</b>());\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report754Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object t = ();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">t</a> = <a title=\"The number of required operands 1 is different from the number of supplied arguments 0.\" class=\"e\">(</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report755Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Param<~$int> t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Param<T> {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"pkg.Param\" href=\"#"+ExportCst.PREF_REF+"68\">Param</a>"+MessagesCdmBase.LT+"<a title=\"The type Param"+MessagesCdmBase.LT+"~$int"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">~</a>$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"45\">t</a>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"68\">pkg.Param</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"78\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report756Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object t=$(Param<~~$int>)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Param<T> {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">t</a>=$(<a title=\"pkg.Param\" href=\"#"+ExportCst.PREF_REF+"84\">Param</a>"+MessagesCdmBase.LT+"~<a title=\"The type Param"+MessagesCdmBase.LT+"~~$int"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">~</a>$int"+MessagesCdmBase.GT+")$null;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"84\">pkg.Param</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"94\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report757Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object t=$($Fct<~>)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Param<T> {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">t</a>=$($Fct<a title=\"The type $Fct"+MessagesCdmBase.LT+"~"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>~"+MessagesCdmBase.GT+")$null;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"78\">pkg.Param</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"88\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report758Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $lambda(Ex,exmeth,~$int).call(t);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">exmeth</a>(){\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"63\">t</a> = 7;\n" +
                "  <a title=\"pkg.Ex.$static exmeth(~$int)\" href=\"#"+ExportCst.PREF_REF+"145\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"15\">Ex</a>,exmeth,~$int).<b>call</b><a title=\"The type $int cannot be implicitly cast to ~$int\" class=\"e\">(</a><a href=\"#"+ExportCst.PREF_REF+"63\">t</a>);\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"63\">t</a>;\n" +
                " }\n" +
                " $public $static $void <a name=\""+ExportCst.PREF_REF+"145\">exmeth</a>($that $int <a name=\""+ExportCst.PREF_REF+"163\">t</a>){\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"163\">t</a>=8;\n" +
                " }\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"199\">exmeth</a>($int <a name=\""+ExportCst.PREF_REF+"211\">t</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"211\">t</a>+8;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report759Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Object t = 7;\n");
        xml_.append("  $lambda(Ex,exmeth,~$int).call($that(t));\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">exmeth</a>(){\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"65\">t</a> = 7;\n" +
                "  <a title=\"pkg.Ex.$static exmeth(~$int)\" href=\"#"+ExportCst.PREF_REF+"154\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"15\">Ex</a>,exmeth,~$int).<b>call</b><a title=\"The type java.lang.Object cannot be implicitly cast to ~$int\" class=\"e\">(</a>$that(<a href=\"#"+ExportCst.PREF_REF+"65\">t</a>));\n" +
                "  $return 0;\n" +
                " }\n" +
                " $public $static $void <a name=\""+ExportCst.PREF_REF+"154\">exmeth</a>($that $int <a name=\""+ExportCst.PREF_REF+"172\">t</a>){\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"172\">t</a>=8;\n" +
                " }\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"208\">exmeth</a>($int <a name=\""+ExportCst.PREF_REF+"220\">t</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"220\">t</a>+8;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report760Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Object t = 7;\n");
        xml_.append("  $lambda(Ex,exmeth,$int).call($that(t));\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">exmeth</a>(){\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"65\">t</a> = 7;\n" +
                "  <a title=\"pkg.Ex.$static exmeth($int)\" href=\"#"+ExportCst.PREF_REF+"207\">$lambda</a>(<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"15\">Ex</a>,exmeth,$int).<b>call</b><a title=\"The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">(</a>$that(<a href=\"#"+ExportCst.PREF_REF+"65\">t</a>));\n" +
                "  $return 0;\n" +
                " }\n" +
                " $public $static $void <a name=\""+ExportCst.PREF_REF+"153\">exmeth</a>($that $int <a name=\""+ExportCst.PREF_REF+"171\">t</a>){\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"171\">t</a>=8;\n" +
                " }\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"207\">exmeth</a>($int <a name=\""+ExportCst.PREF_REF+"219\">t</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"219\">t</a>+8;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report761Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("  $Fct<$int> t=$($Fct<~$int>)$null;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"37\">t</a><a title=\"The type java.lang.$Fct"+MessagesCdmBase.LT+"~$int"+MessagesCdmBase.GT+" cannot be implicitly cast to java.lang.$Fct"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+"\" class=\"e\">=</a>$($Fct"+MessagesCdmBase.LT+"~$int"+MessagesCdmBase.GT+")$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report762Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("  $Fct<~$int> t=$($Fct<$int>)$null;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                "  $Fct"+MessagesCdmBase.LT+"~$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"38\">t</a><a title=\"The type java.lang.$Fct"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+" cannot be implicitly cast to java.lang.$Fct"+MessagesCdmBase.LT+"~$int"+MessagesCdmBase.GT+"\" class=\"e\">=</a>$($Fct"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+")$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report763Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("  {\n");
        xml_.append("   $Fct<~~$int> t;\n");
        xml_.append("  }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                "  {\n" +
                "   <a title=\"There is no accessible field named $Fct from the type pkg.Ex in this context.\" class=\"e\">$Fct</a>"+MessagesCdmBase.LT+"<a title=\"The operands types java.lang.Number for the operator ~ are unexpected.\" class=\"e\">~</a><a title=\"The operands types java.lang.Object for the operator ~ are unexpected.\" class=\"e\">~</a><a title=\"There is no accessible field named $int from the type pkg.Ex in this context.\" class=\"e\">$int</a><a title=\"The number of required operands 2 is different from the number of supplied arguments 3 for the operator "+MessagesCdmBase.LT+"\" class=\"e\">"+MessagesCdmBase.GT+"</a> <a title=\"There is no accessible field named t from the type pkg.Ex in this context.\" class=\"e\">t</a>;\n" +
                "  }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report764Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@interface pkg.Ext {\n");
        xml_.append(" (){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@interface <a name=\""+ExportCst.PREF_REF+"11\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"22\">(</a>)<a title=\"The instance type pkg.Ext must contain only instance types and instance initilizing blocks.\" class=\"e\">{</a>\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report765Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(f:10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"111\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"111\">Rec</a>(<a title=\"The parameter function name f is duplicated.\" class=\"e\">f</a>:10);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"126\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"111\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"126\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report766Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth (){\n");
        xml_.append("  $lambda(Rec,new,f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a> (){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"91\">Rec</a>,new,f);\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"91\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"106\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report767Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(field:10,field:10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"124\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"124\">Rec</a>(<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"139\">field</a>:10,<a title=\"The parameter function name field is duplicated.\n" +
                "\n" +
                "pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"139\" class=\"e\">field</a>:10);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"139\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"124\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"139\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report768Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(field:\"10\");\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"117\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"117\">Rec</a>(<a title=\"The parameter function name field is duplicated.\n" +
                "\n" +
                "pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"132\" class=\"e\">field</a>:<span class=\"s\">\"10\"</span>);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"132\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"117\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"132\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report769Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth (){\n");
        xml_.append("  $lambda(Rec,new,field,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a> (){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"101\">Rec</a>,new,<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"116\">field</a>,<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"116\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"101\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"116\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report770Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth (){\n");
        xml_.append("  $lambda(Rec<?>,new,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec<T> {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"42\">exmeth</a> (){\n" +
                "  <a title=\"The argument ? of the type pkg.Rec"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" is bound. It cannot be used in constructor call.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"98\">Rec</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+",new,field);\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"98\">pkg.Rec</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"106\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"116\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report771Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public void exmeth (){\n");
        xml_.append("  ++this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public void <a name=\""+ExportCst.PREF_REF+"35\">exmeth</a> (){\n" +
                "  <a title=\"The assignment operator ++ is unexpected.\" class=\"e\">++</a>this;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report772Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  ExClass2 f = new ExClass2();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  return and(e,f);\n");
        xml_.append(" }\n");
        xml_.append(" static boolean and(boolean a, boolean b){\n");
        xml_.append("  return a&&b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass2 i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Apply</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"44\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"238\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"64\">e</a> = new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"238\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"64\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"264\">field</a>=1;\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#"+ExportCst.PREF_REF+"349\">ExClass2</a> <a name=\""+ExportCst.PREF_REF+"107\">f</a> = new <a title=\"pkg.ExClass2\" href=\"#"+ExportCst.PREF_REF+"349\">ExClass2</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"107\">f</a>.<a title=\"pkg.ExClass2.field\" href=\"#"+ExportCst.PREF_REF+"376\">field</a>=1;\n" +
                "  <a title=\"The type boolean cannot be implicitly cast to int\" class=\"e\">return</a> <a title=\"pkg.Apply.static and(boolean,boolean)\" href=\"#"+ExportCst.PREF_REF+"178\">and</a>(<a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"296\"> </a><a href=\"#"+ExportCst.PREF_REF+"64\">e</a>,<a title=\"pkg.ExClass2.static $(boolean,pkg.ExClass2)\" href=\"#"+ExportCst.PREF_REF+"408\"> </a><a href=\"#"+ExportCst.PREF_REF+"107\">f</a>);\n" +
                " }\n" +
                " static boolean <a name=\""+ExportCst.PREF_REF+"178\">and</a>(boolean <a name=\""+ExportCst.PREF_REF+"190\">a</a>, boolean <a name=\""+ExportCst.PREF_REF+"201\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"190\">a</a>"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"<a href=\"#"+ExportCst.PREF_REF+"201\">b</a>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"238\">pkg.ExClass</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"264\">field</a>=2;\n" +
                " public static boolean <a name=\""+ExportCst.PREF_REF+"296\">$</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"238\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"306\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"306\">i</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"264\">field</a>==1;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"349\">pkg.ExClass2</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"376\">field</a>=2;\n" +
                " public static boolean <a name=\""+ExportCst.PREF_REF+"408\">$</a>(<a title=\"pkg.ExClass2\" href=\"#"+ExportCst.PREF_REF+"349\">ExClass2</a> <a name=\""+ExportCst.PREF_REF+"419\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"419\">i</a>.<a title=\"pkg.ExClass2.field\" href=\"#"+ExportCst.PREF_REF+"376\">field</a>==1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report773Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  ExClass f = new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  return e&&f?\"Vrai\":\"Faux\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append(" operator&& boolean (ExClass i, ExClass j){\n");
        xml_.append("  return i.field==1&&j.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" operator|| boolean (ExClass i, ExClass j){\n");
        xml_.append("  return i.field==1||j.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(boolean i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i?1:2;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Apply</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"44\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"64\">e</a> = new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"64\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>=1;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"106\">f</a> = new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"106\">f</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>=1;\n" +
                "  <a title=\"The type $core.String cannot be implicitly cast to int\" class=\"e\">return</a> <a href=\"#"+ExportCst.PREF_REF+"64\">e</a><a title=\"pkg.ExClass.static false(boolean,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"307\">"+MessagesCdmBase.AMP+"</a><a title=\"pkg.ExClass.static "+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"(pkg.ExClass,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"358\">"+MessagesCdmBase.AMP+"</a><a title=\"pkg.ExClass.static $(pkg.ExClass,boolean)\" href=\"#"+ExportCst.PREF_REF+"593\"> </a><a href=\"#"+ExportCst.PREF_REF+"106\">f</a><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"532\">?</a><span class=\"s\">\"Vrai\"</span>:<span class=\"s\">\"Faux\"</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"185\">pkg.ExClass</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"211\">field</a>=2;\n" +
                " public static boolean <a name=\""+ExportCst.PREF_REF+"243\">true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"256\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"256\">i</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>==1;\n" +
                " }\n" +
                " public static boolean <a name=\""+ExportCst.PREF_REF+"307\">false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"321\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"321\">i</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>!=1;\n" +
                " }\n" +
                " operator<a name=\""+ExportCst.PREF_REF+"358\">"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"</a> boolean (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"378\">i</a>, <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"389\">j</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"378\">i</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>==1"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"<a href=\"#"+ExportCst.PREF_REF+"389\">j</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>==1;\n" +
                " }\n" +
                " operator<a name=\""+ExportCst.PREF_REF+"438\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"458\">i</a>, <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"469\">j</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"458\">i</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>==1||<a href=\"#"+ExportCst.PREF_REF+"469\">j</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>==1;\n" +
                " }\n" +
                " public static boolean <a name=\""+ExportCst.PREF_REF+"532\">$</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"542\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"542\">i</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>==1;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"593\">$</a>(boolean <a name=\""+ExportCst.PREF_REF+"603\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"617\">e</a> = new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"185\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"617\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"211\">field</a>=<a href=\"#"+ExportCst.PREF_REF+"603\">i</a>?1:2;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"617\">e</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report774Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  ExTwo ex = new ExTwo();\n");
        xml_.append("  return nb <= ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" operator<= ExClass (ExClass h, int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = i + h.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExTwo i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Apply</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"44\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"147\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"64\">nb</a> = new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"147\">ExClass</a>();\n" +
                "  <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"306\">ExTwo</a> <a name=\""+ExportCst.PREF_REF+"92\">ex</a> = new <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"306\">ExTwo</a>();\n" +
                "  <a title=\"The type pkg.ExClass cannot be implicitly cast to int\" class=\"e\">return</a> <a href=\"#"+ExportCst.PREF_REF+"64\">nb</a> <a title=\"pkg.ExClass.static "+MessagesCdmBase.LT+"=(pkg.ExClass,int)\" href=\"#"+ExportCst.PREF_REF+"191\">"+MessagesCdmBase.LT+"=</a><a title=\"pkg.ExTwo.static $(int,pkg.ExTwo)\" href=\"#"+ExportCst.PREF_REF+"358\"> </a> <a href=\"#"+ExportCst.PREF_REF+"92\">ex</a>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"147\">pkg.ExClass</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"173\">field</a>=2;\n" +
                " operator<a name=\""+ExportCst.PREF_REF+"191\">"+MessagesCdmBase.LT+"=</a> <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"147\">ExClass</a> (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"147\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"211\">h</a>, int <a name=\""+ExportCst.PREF_REF+"218\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"147\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"232\">e</a> = new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"147\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"232\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"173\">field</a> = <a href=\"#"+ExportCst.PREF_REF+"218\">i</a> + <a href=\"#"+ExportCst.PREF_REF+"211\">h</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"173\">field</a>;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"232\">e</a>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"306\">pkg.ExTwo</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"330\">field</a>=2;\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"358\">$</a>(<a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"306\">ExTwo</a> <a name=\""+ExportCst.PREF_REF+"366\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"366\">i</a>.<a title=\"pkg.ExTwo.field\" href=\"#"+ExportCst.PREF_REF+"330\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report775Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExTwo ex = new ExTwo();\n");
        xml_.append("  return ex <= ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExTwo i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Apply</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"44\">method</a>(){\n" +
                "  <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"117\">ExTwo</a> <a name=\""+ExportCst.PREF_REF+"62\">ex</a> = new <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"117\">ExTwo</a>();\n" +
                "  <a title=\"The type boolean cannot be implicitly cast to int\" class=\"e\">return</a> <a href=\"#"+ExportCst.PREF_REF+"62\">ex</a> <a title=\"pkg.ExTwo.static $(int,pkg.ExTwo)\" href=\"#"+ExportCst.PREF_REF+"169\"> </a>"+MessagesCdmBase.LT+"=<a title=\"pkg.ExTwo.static $(int,pkg.ExTwo)\" href=\"#"+ExportCst.PREF_REF+"169\"> </a> <a href=\"#"+ExportCst.PREF_REF+"62\">ex</a>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"117\">pkg.ExTwo</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"141\">field</a>=2;\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"169\">$</a>(<a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"117\">ExTwo</a> <a name=\""+ExportCst.PREF_REF+"177\">i</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"177\">i</a>.<a title=\"pkg.ExTwo.field\" href=\"#"+ExportCst.PREF_REF+"141\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report776Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  ExTwo ex = new ExTwo();\n");
        xml_.append("  ex += ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public int field=2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Apply</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"45\">method</a>(){\n" +
                "  <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"111\">ExTwo</a> <a name=\""+ExportCst.PREF_REF+"63\">ex</a> = new <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"111\">ExTwo</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"63\">ex</a> <a title=\"The type pkg.ExTwo cannot be implicitly cast to pkg.ExTwo\" class=\"e\">+</a>= <a href=\"#"+ExportCst.PREF_REF+"63\">ex</a>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"111\">pkg.ExTwo</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"135\">field</a>=2;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report777Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  int a = 0;\n");
        xml_.append("  a += ;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Apply</a> {\n" +
                " public static void <a name=\""+ExportCst.PREF_REF+"45\">method</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"61\">a</a> = 0;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"61\">a</a> <a title=\"The expression part is empty.\" class=\"e\">+=</a> ;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report778Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", "");
        StringMap<String> filesExp_ = ctxErrStdReadOnlyImpl2(files_);
        assertTrue(!filesExp_.isEmpty());
    }
    @Test
    public void report779Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec( f :10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"113\">Rec</a> <a name=\""+ExportCst.PREF_REF+"58\">r</a> = new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"113\">Rec</a>( <a title=\"The parameter function name f is duplicated.\" class=\"e\">f</a> :10);\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"58\">r</a>.<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"128\">field</a>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\""+ExportCst.PREF_REF+"113\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"128\">field</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report780Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  return m(2, c :5, c :3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  return m(2, c :5, <a title=\"The parameter function name c is duplicated.\" class=\"e\">c</a> :3);\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"78\">m</a>(int <a name=\""+ExportCst.PREF_REF+"84\">a</a>,int <a name=\""+ExportCst.PREF_REF+"90\">b</a>,int <a name=\""+ExportCst.PREF_REF+"96\">c</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"84\">a</a>*<a href=\"#"+ExportCst.PREF_REF+"90\">b</a>+<a href=\"#"+ExportCst.PREF_REF+"96\">c</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report781Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,$int,$);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,$int,<a title=\"The operator symbol $ is not valid.\" class=\"e\">$</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report782Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,$);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"The operator symbol $ is not valid.\" class=\"e\">$</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report783Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,$);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"The type $ is unknown.\" class=\"e\">$</a><a title=\"The operator symbol $ is not valid.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report784Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,$int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,$int<a title=\"The operator symbol $int is not valid.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report785Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,$,$);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"The type $ is unknown.\" class=\"e\">$</a>,<a title=\"The operator symbol $ is not valid.\" class=\"e\">$</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report786Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,$int, $);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,$int, <a title=\"The operator symbol $ is not valid.\" class=\"e\">$</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report787Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator, +,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator, <a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a><a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report788Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub, +,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>, <a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a><a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report789Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator, +,$id,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator, <a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a>,$id<a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report790Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub, +,$id,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>, <a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a>,$id<a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report791Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator, +,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator, <a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report792Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub, +,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>, <a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report793Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator, +,$id,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator, <a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,$id,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report794Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub, +,$id,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>, <a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,$id,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report795Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator, ++,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator, <a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">++</a><a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report796Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object t = $(Inex) 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">t</a> = $(<a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>) 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report797Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($int b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($int <a name=\""+ExportCst.PREF_REF+"126\">b</a>){\n" +
                "  $return $that(<a href=\"#"+ExportCst.PREF_REF+"126\">b</a><a title=\"The type $int is unexpected.\" class=\"e\">?</a>$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>):$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report798Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?p1:$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"130\">b</a>){\n" +
                "  $return $that(<a href=\"#"+ExportCst.PREF_REF+"130\">b</a>?<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a><a title=\"The key word $that is unexpected here.\" class=\"e\">:</a>$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report799Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):p2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"130\">b</a>){\n" +
                "  $return $that(<a href=\"#"+ExportCst.PREF_REF+"130\">b</a>?$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>)<a title=\"The key word $that is unexpected here.\" class=\"e\">:</a><a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report800Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String p1 = \"15\";\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that Object exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">p1</a> = <span class=\"s\">\"15\"</span>;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"81\">p2</a> = 18;\n" +
                " $public $static $that Object <a name=\""+ExportCst.PREF_REF+"120\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"136\">b</a>){\n" +
                "  <a title=\"The type java.lang.String cannot be implicitly cast to java.lang.Object\" class=\"e\">$return</a> $that(<a href=\"#"+ExportCst.PREF_REF+"136\">b</a>?$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"48\">p1</a>)<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a>$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"81\">p2</a>));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report801Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($int b){\n");
        xml_.append("  $return $that($bool(b,$that(p1),$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($int <a name=\""+ExportCst.PREF_REF+"126\">b</a>){\n" +
                "  $return $that($bool<a title=\"The type $int is unexpected.\" class=\"e\">(</a><a href=\"#"+ExportCst.PREF_REF+"126\">b</a>,$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>),$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report802Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that($bool(b,p1,$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"130\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"130\">b</a>,<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>,$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report803Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that($bool(b,$that(p1),p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"130\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"130\">b</a>,$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>),<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report804Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String p1 = \"15\";\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that Object exmeth($boolean b){\n");
        xml_.append("  $return $that($bool(b,$that(p1),$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">p1</a> = <span class=\"s\">\"15\"</span>;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"81\">p2</a> = 18;\n" +
                " $public $static $that Object <a name=\""+ExportCst.PREF_REF+"120\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"136\">b</a>){\n" +
                "  <a title=\"The type java.lang.String cannot be implicitly cast to java.lang.Object\" class=\"e\">$return</a> $that(<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"136\">b</a>,$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"48\">p1</a>),$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"81\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report805Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($int b){\n");
        xml_.append("  $return $that($bool(b,p1,$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($int <a name=\""+ExportCst.PREF_REF+"126\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\" class=\"e\">$bool</a><a title=\"The type $int is unexpected.\" class=\"e\">(</a><a href=\"#"+ExportCst.PREF_REF+"126\">b</a>,<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>,$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report806Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that($bool(b,p1,$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $true(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"192\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"128\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\n" +
                "\n" +
                "pkg.ExBool.$static $true($boolean,pkg.ExBool)\" href=\"#"+ExportCst.PREF_REF+"269\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"128\">b</a>,<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>,$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"192\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"211\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"215\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"227\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"211\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"227\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"269\">$true</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"192\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"282\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"282\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"211\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report807Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that($bool(b,p1,$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"192\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"128\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\n" +
                "\n" +
                "pkg.ExBool.$static $($boolean,pkg.ExBool)\" href=\"#"+ExportCst.PREF_REF+"269\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"128\">b</a>,<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>,$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"192\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"211\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"215\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"227\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"211\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"227\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"269\">$</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"192\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"278\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"278\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"211\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report808Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that($bool(b,p1,$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"192\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"128\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"128\">b</a>,<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>,$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"192\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"211\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"215\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"227\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"211\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"227\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"269\">$</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"192\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"278\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"278\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"211\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report809Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):p2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $true(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"185\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"128\">b</a>){\n" +
                "  $return $that(<a href=\"#"+ExportCst.PREF_REF+"128\">b</a>?$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>)<a title=\"The key word $that is unexpected here.\n" +
                "\n" +
                "pkg.ExBool.$static $true($boolean,pkg.ExBool)\" href=\"#"+ExportCst.PREF_REF+"262\" class=\"e\">:</a><a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"185\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"204\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"208\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"220\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"204\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"220\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"262\">$true</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"185\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"275\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"275\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"204\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report810Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):p2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"185\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"128\">b</a>){\n" +
                "  $return $that(<a href=\"#"+ExportCst.PREF_REF+"128\">b</a><a title=\"pkg.ExBool.$static $($boolean,pkg.ExBool)\" href=\"#"+ExportCst.PREF_REF+"262\">?</a>$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>)<a title=\"The key word $that is unexpected here.\" class=\"e\">:</a><a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"185\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"204\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"208\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"220\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"204\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"220\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"262\">$</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"185\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"271\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"271\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"204\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report811Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):p2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"185\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"128\">b</a>){\n" +
                "  $return $that(<a href=\"#"+ExportCst.PREF_REF+"128\">b</a>?$that(<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>)<a title=\"The key word $that is unexpected here.\" class=\"e\">:</a><a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"185\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"204\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"208\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"220\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"204\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"220\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"262\">$</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"185\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"271\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"271\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"204\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report812Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that($bool(b,p1,$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"46\">p1</a> = 15;\n" +
                " $public $static $int <a name=\""+ExportCst.PREF_REF+"77\">p2</a> = 18;\n" +
                " $public $static $that $int <a name=\""+ExportCst.PREF_REF+"114\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"130\">b</a>){\n" +
                "  $return $that(<a title=\"The key word $that is unexpected here.\" class=\"e\">$bool</a>(<a href=\"#"+ExportCst.PREF_REF+"130\">b</a>,<a title=\"pkg.Ex.p1\" href=\"#"+ExportCst.PREF_REF+"46\">p1</a>,$that(<a title=\"pkg.Ex.p2\" href=\"#"+ExportCst.PREF_REF+"77\">p2</a>)));\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"194\">pkg.ExBool</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"213\">f</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"217\">ExBool(</a>$int <a name=\""+ExportCst.PREF_REF+"229\">p</a>){\n" +
                "  <a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"213\">f</a> = <a href=\"#"+ExportCst.PREF_REF+"229\">p</a>;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"271\">$</a>(<a title=\"pkg.ExBool\" href=\"#"+ExportCst.PREF_REF+"194\">ExBool</a> <a name=\""+ExportCst.PREF_REF+"280\">v</a>){\n" +
                "  $return <a href=\"#"+ExportCst.PREF_REF+"280\">v</a>.<a title=\"pkg.ExBool.f\" href=\"#"+ExportCst.PREF_REF+"213\">f</a> == 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report813Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth($boolean b){\n");
        xml_.append("  $return \"\"\"`\n\"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"64\">b</a>){\n" +
                "  $return <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"`\n" +
                ""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\" class=\"e\">\"\"\"`\n" +
                "\"\"\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report814Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth($boolean b){\n");
        xml_.append("  $return \"\"\"\n\\u000g\"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"64\">b</a>){\n" +
                "  $return <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\n" +
                "\\u000g"+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\" class=\"e\">\"\"\"\n" +
                "\\u000g\"\"\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report815Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth($boolean b){\n");
        xml_.append("  $return \"\"\"\n\\u000G\"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"64\">b</a>){\n" +
                "  $return <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\n" +
                "\\u000G"+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\" class=\"e\">\"\"\"\n" +
                "\\u000G\"\"\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report816Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth($boolean b){\n");
        xml_.append("  $return \"\"\"\n\\u000!\"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"64\">b</a>){\n" +
                "  $return <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\n" +
                "\\u000!"+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\" class=\"e\">\"\"\"\n" +
                "\\u000!\"\"\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report817Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth($boolean b){\n");
        xml_.append("  $return \"\"\"\n\\u000~\"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"64\">b</a>){\n" +
                "  $return <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\n" +
                "\\u000~"+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\" class=\"e\">\"\"\"\n" +
                "\\u000~\"\"\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report818Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth($boolean b){\n");
        xml_.append("  $return \"\"\"\n\\a\"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"48\">exmeth</a>($boolean <a name=\""+ExportCst.PREF_REF+"64\">b</a>){\n" +
                "  $return <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\n" +
                "\\a"+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+""+MessagesCdmBase.QUOT+"\" class=\"e\">\"\"\"\n" +
                "\\a\"\"\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report819Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$interface pkg.Int {\n");
        xml_.append(" $int field();\n");
        xml_.append("}\n");
        xml_.append("$class pkg.Ext {\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $new @Annot(info={2})@AnnotTwo(info2={2}) Int(){\n");
        xml_.append("   $public $int field(){\n");
        xml_.append("    $return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$interface <a name=\""+ExportCst.PREF_REF+"11\">pkg.Int</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"27\">field</a>();\n" +
                "}\n" +
                "$class <a name=\""+ExportCst.PREF_REF+"45\">pkg.Ext</a> {\n" +
                " $static $int <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  $new <a title=\"After @ the type Annot is not an annotation.\" class=\"e\">@</a><a title=\"The type Annot is unknown.\" class=\"e\">Annot</a>(info=<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>2})<a title=\"After @ the type AnnotTwo is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotTwo is unknown.\" class=\"e\">AnnotTwo</a>(info2=<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>2}) <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"11\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"123\">{</a>\n" +
                "   $public $int <a name=\""+ExportCst.PREF_REF+"141\">field</a>(){\n" +
                "    $return 0;\n" +
                "   }\n" +
                "  }</span>;\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report820Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$interface pkg.Int {\n");
        xml_.append(" $int field();\n");
        xml_.append("}\n");
        xml_.append("$class pkg.Ext {\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $new @Annot(info={$new Int(){}.field()})@AnnotTwo(info2={2}) Int(){\n");
        xml_.append("   $public $int field(){\n");
        xml_.append("    $return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnlyImpl(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$interface <a name=\""+ExportCst.PREF_REF+"11\">pkg.Int</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"27\">field</a>();\n" +
                "}\n" +
                "$class <a name=\""+ExportCst.PREF_REF+"45\">pkg.Ext</a> {\n" +
                " $static $int <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                "  $new <a title=\"After @ the type Annot is not an annotation.\" class=\"e\">@</a><a title=\"The type Annot is unknown.\" class=\"e\">Annot</a>(info=<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>$new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"11\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"104\" title=\"The method field() from the type pkg.Int must be overriden in the concrete type pkg.Ext..Int*1..Int*1.\" class=\"e\">{</a>}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"27\">field</a>()})<a title=\"After @ the type AnnotTwo is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotTwo is unknown.\" class=\"e\">AnnotTwo</a>(info2=<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>2}) <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"11\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"142\">{</a>\n" +
                "   $public $int <a name=\""+ExportCst.PREF_REF+"160\">field</a>(){\n" +
                "    $return 0;\n" +
                "   }\n" +
                "  }</span>;\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report821Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int v = m((@AnnotOne(info1=7)@AnnotTwo(info2=8) int a,@AnnotThree(info3=9)@AnnotFour(info4=10) int b:@AnnotFive(info5=11)@AnnotSix(info6=12) int)->{return 2 * a * b;},3,4);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int,int> fct,int a, int b){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">v</a> = <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,int,int"+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"235\">m</a>(<span class=\"t\">(<a title=\"After @ the type AnnotOne is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotOne is unknown.\" class=\"e\">AnnotOne</a>(info1=7)<a title=\"After @ the type AnnotTwo is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotTwo is unknown.\" class=\"e\">AnnotTwo</a>(info2=8) int <a name=\""+ExportCst.PREF_REF+"87\">a</a>,<a title=\"After @ the type AnnotThree is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotThree is unknown.\" class=\"e\">AnnotThree</a>(info3=9)<a title=\"After @ the type AnnotFour is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotFour is unknown.\" class=\"e\">AnnotFour</a>(info4=10) int <a name=\""+ExportCst.PREF_REF+"134\">b</a>:<a title=\"After @ the type AnnotFive is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotFive is unknown.\" class=\"e\">AnnotFive</a>(info5=11)<a title=\"After @ the type AnnotSix is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotSix is unknown.\" class=\"e\">AnnotSix</a>(info6=12) int)<a name=\""+ExportCst.PREF_REF+"180\">-"+MessagesCdmBase.GT+"</a>{return 2 * <a href=\"#"+ExportCst.PREF_REF+"87\">a</a> * <a href=\"#"+ExportCst.PREF_REF+"134\">b</a>;}</span>,3,4);\n" +
                "  return 0;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"235\">m</a>(Fct"+MessagesCdmBase.LT+"int,int,int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"254\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"262\">a</a>, int <a name=\""+ExportCst.PREF_REF+"269\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"254\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"262\">a</a>,<a href=\"#"+ExportCst.PREF_REF+"269\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report822Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int v = m((@AnnotOne(info1=7+new Int(){public int field(){return 0;}}.field())@AnnotTwo(info2=8+new Int(){public int field(){return 0;}}.field()) int a,@AnnotThree(info3=9+new Int(){public int field(){return 0;}}.field())@AnnotFour(info4=10+new Int(){public int field(){return 0;}}.field()) int b:@AnnotFive(info5=11+new Int(){public int field(){return 0;}}.field())@AnnotSix(info6=12+new Int(){public int field(){return 0;}}.field()) int)->{return 2 * a * b;},3,4);\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int,int> fct,int a, int b){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\""+ExportCst.PREF_REF+"10\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"25\">field</a>();\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"42\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"64\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"75\">v</a> = <a title=\"pkg.Ext.static m($core.Fct"+MessagesCdmBase.LT+"int,int,int"+MessagesCdmBase.GT+",int,int)\" href=\"#"+ExportCst.PREF_REF+"565\">m</a>(<span class=\"t\">(<a title=\"After @ the type AnnotOne is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotOne is unknown.\" class=\"e\">AnnotOne</a>(info1=7+new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"109\">{</a>public int <a name=\""+ExportCst.PREF_REF+"121\">field</a>(){return 0;}}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>())<a title=\"After @ the type AnnotTwo is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotTwo is unknown.\" class=\"e\">AnnotTwo</a>(info2=8+new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"176\">{</a>public int <a name=\""+ExportCst.PREF_REF+"188\">field</a>(){return 0;}}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>()) int <a name=\""+ExportCst.PREF_REF+"221\">a</a>,<a title=\"After @ the type AnnotThree is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotThree is unknown.\" class=\"e\">AnnotThree</a>(info3=9+new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"252\">{</a>public int <a name=\""+ExportCst.PREF_REF+"264\">field</a>(){return 0;}}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>())<a title=\"After @ the type AnnotFour is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotFour is unknown.\" class=\"e\">AnnotFour</a>(info4=10+new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"321\">{</a>public int <a name=\""+ExportCst.PREF_REF+"333\">field</a>(){return 0;}}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>()) int <a name=\""+ExportCst.PREF_REF+"366\">b</a>:<a title=\"After @ the type AnnotFive is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotFive is unknown.\" class=\"e\">AnnotFive</a>(info5=11+new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"397\">{</a>public int <a name=\""+ExportCst.PREF_REF+"409\">field</a>(){return 0;}}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>())<a title=\"After @ the type AnnotSix is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotSix is unknown.\" class=\"e\">AnnotSix</a>(info6=12+new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"465\">{</a>public int <a name=\""+ExportCst.PREF_REF+"477\">field</a>(){return 0;}}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>()) int)<a name=\""+ExportCst.PREF_REF+"510\">-"+MessagesCdmBase.GT+"</a>{return 2 * <a href=\"#"+ExportCst.PREF_REF+"221\">a</a> * <a href=\"#"+ExportCst.PREF_REF+"366\">b</a>;}</span>,3,4);\n" +
                "  return 0;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"565\">m</a>(Fct"+MessagesCdmBase.LT+"int,int,int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"584\">fct</a>,int <a name=\""+ExportCst.PREF_REF+"592\">a</a>, int <a name=\""+ExportCst.PREF_REF+"599\">b</a>){\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"584\">fct</a>.<b>call</b>(<a href=\"#"+ExportCst.PREF_REF+"592\">a</a>,<a href=\"#"+ExportCst.PREF_REF+"599\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report823Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">a</a> = 10;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"53\">t</a> = switch(<a href=\"#"+ExportCst.PREF_REF+"39\">a</a>) <span class=\"t\">{\n" +
                "   case 10;\n" +
                "    return 5;\n" +
                "   default;\n" +
                "    return 1;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report824Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">a</a> = 10;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"53\">t</a> = <a title=\"A throw block or a return block is missing for the method static .1(int).\" class=\"e\">switch</a>(<a href=\"#"+ExportCst.PREF_REF+"39\">a</a>) <span class=\"t\">{\n" +
                "   case 10;\n" +
                "    return 5;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report825Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    break;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">a</a> = 10;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"53\">t</a> = switch(<a href=\"#"+ExportCst.PREF_REF+"39\">a</a>) <span class=\"t\">{\n" +
                "   case 10;\n" +
                "    <a title=\"The break block must be inner of the blocks switch|for|foreach|do|iter|while.\" class=\"e\">break</a>;\n" +
                "   default;\n" +
                "    return 1;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report826Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int t = switch(0) {\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">t</a> = <a title=\"A throw block or a return block is missing for the method static .1(int).\" class=\"e\">switch</a>(0) <span class=\"t\">{\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report827Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int t = switch(0) {\n");
        xml_.append("   default;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">t</a> = <a title=\"A throw block or a return block is missing for the method static .1(int).\" class=\"e\">switch</a>(0) <span class=\"t\">{\n" +
                "   default;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report828Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int t = switch((Object)null) {\n");
        xml_.append("   default d;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">t</a> = <a title=\"A throw block or a return block is missing for the method static .1($core.Object).\" class=\"e\">switch</a>((Object)null) <span class=\"t\">{\n" +
                "   default <a title=\"$core.Object\" name=\""+ExportCst.PREF_REF+"77\">d</a>;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report829Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int t = switch(0) {\n");
        xml_.append("   int i;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"39\">t</a> = <a title=\"A throw block or a return block is missing for the method static .1(int).\" class=\"e\">switch</a>(0) <span class=\"t\">{\n" +
                "   int <a name=\""+ExportCst.PREF_REF+"62\">i</a><a title=\"The switch block must contain only one of the blocks case|default.\" class=\"e\">;</a>\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report830Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  (switch(0) {\n");
        xml_.append("   default;\n");
        xml_.append("    return 0;\n");
        xml_.append("  })=1;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  (switch(0) <span class=\"t\">{\n" +
                "   default;\n" +
                "    <a title=\"The type int cannot be implicitly cast to $core.Object\" class=\"e\">return</a> 0;\n" +
                "  }</span>)=1;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report831Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  (switch[String](0) {\n");
        xml_.append("   default;\n");
        xml_.append("    return that(0);\n");
        xml_.append("  })=\"1\";\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"28\">m</a>(){\n" +
                "  (switch[String](0) <span class=\"t\">{\n" +
                "   default;\n" +
                "    <a title=\"The type $core.Object cannot be implicitly cast to $core.String\" class=\"e\">return</a> <a title=\"The key word that is unexpected here.\" class=\"e\">that</a>(0);\n" +
                "  }</span>)=<span class=\"s\">\"1\"</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report832Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot:@AnnotTwo](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ext</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"42\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"53\">a</a> = 10;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"67\">t</a> = switch[int:<a title=\"After @ the type Annot is not an annotation.\" class=\"e\">@</a><a title=\"The type Annot is unknown.\" class=\"e\">Annot</a>:<a title=\"After @ the type AnnotTwo is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotTwo is unknown.\" class=\"e\">AnnotTwo</a>](<a href=\"#"+ExportCst.PREF_REF+"53\">a</a>) <span class=\"t\">{\n" +
                "   case 10;\n" +
                "    return 5;\n" +
                "   default;\n" +
                "    return 1;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report833Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" normal int field(){return 0;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot(new Int(){}.field()):@AnnotTwo(new Int(){}.field())](a) {\n");
        xml_.append("   case 10;\n");
        xml_.append("    return 5;\n");
        xml_.append("   default;\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public interface <a name=\""+ExportCst.PREF_REF+"17\">pkg.Int</a> {\n" +
                " normal int <a name=\""+ExportCst.PREF_REF+"39\">field</a>(){return 0;}\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"73\">pkg.Ext</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"102\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"113\">a</a> = 10;\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"127\">t</a> = switch[int:<a title=\"After @ the type Annot is not an annotation.\" class=\"e\">@</a><a title=\"The type Annot is unknown.\" class=\"e\">Annot</a>(new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"17\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"158\">{</a>}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"39\">field</a>()):<a title=\"After @ the type AnnotTwo is not an annotation.\" class=\"e\">@</a><a title=\"The type AnnotTwo is unknown.\" class=\"e\">AnnotTwo</a>(new <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"17\">Int</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"189\">{</a>}</span>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"39\">field</a>())](<a href=\"#"+ExportCst.PREF_REF+"113\">a</a>) <span class=\"t\">{\n" +
                "   case 10;\n" +
                "    return 5;\n" +
                "   default;\n" +
                "    return 1;\n" +
                "  }</span>;\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report834Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $classe pkg.Ex{}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public <a name=\""+ExportCst.PREF_REF+"8\" title=\"The part $classe pkg in a type is not valid. It must be a word.\" class=\"e\">$classe pkg.Ex</a><a title=\"Bad index by parsing.\" class=\"e\">{</a>}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report835Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$classe pkg.Ex{}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.ExTwo</a>{}\n" +
                "<a name=\""+ExportCst.PREF_REF+"27\" title=\"The part $classe pkg in a type is not valid. It must be a word.\" class=\"e\">$classe pkg.Ex</a><a title=\"Bad index by parsing.\" class=\"e\">{</a>}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report836Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces pkg.Ex{}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public <a title=\"Bad index by parsing.\" class=\"e\">$class</a> <a name=\""+ExportCst.PREF_REF+"15\" title=\"The part $interfaces pkg in a type is not valid. It must be a word.\" class=\"e\">$interfaces pkg.Ex</a>{}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report837Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ext {\n");
        xml_.append(" $public $static Object m(){\n");
        xml_.append("  $return $staticCall(ExInext<>).inst(2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ext</a> {\n" +
                " $public $static Object <a name=\""+ExportCst.PREF_REF+"49\">m</a>(){\n" +
                "  $return $staticCall(<a title=\"The type ExInext is unknown.\" class=\"e\">ExInext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+").<a title=\"The function $staticCall inst($int) is undefined.\" class=\"e\">inst</a>(2);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report838Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ext {\n");
        xml_.append(" $public $static Object m(){\n");
        xml_.append("  $return $staticCall(ExInext<>).inst(2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExInext<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ext</a> {\n" +
                " $public $static Object <a name=\""+ExportCst.PREF_REF+"49\">m</a>(){\n" +
                "  $return $staticCall(<a title=\"pkg.ExInext\" href=\"#"+ExportCst.PREF_REF+"116\">ExInext</a>"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+").<a title=\"The function $staticCall inst($int) is undefined.\" class=\"e\">inst</a>(2);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"116\">pkg.ExInext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"128\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report839Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyCont.MyClass<String>.Inner v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCont{\n");
        xml_.append(" $public $static $class MyClass<T>{\n");
        xml_.append("  $public $static $class Inner{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyCont\" href=\"#"+ExportCst.PREF_REF+"81\">pkg.MyCont</a>.<a title=\"pkg.MyCont..MyClass\" href=\"#"+ExportCst.PREF_REF+"117\">MyClass</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+"<a title=\"The type pkg.MyCont..MyClass"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+"..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCont..MyClass..Inner\" href=\"#"+ExportCst.PREF_REF+"154\">Inner</a> <a name=\""+ExportCst.PREF_REF+"61\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"81\">pkg.MyCont</a>{\n" +
                " $public $static $class <a name=\""+ExportCst.PREF_REF+"117\">MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"125\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "  $public $static $class <a name=\""+ExportCst.PREF_REF+"154\">Inner</a>{\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report840Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE (new Int(1){\n");
        xml_.append("  static int extField;\n");
        xml_.append("  public int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }){\n");
        xml_.append("  ONE(Int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" Int inner;\n");
        xml_.append(" Ext(Int p){\n");
        xml_.append("  inner = p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append("  return ONE.inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\""+ExportCst.PREF_REF+"10\">pkg.Int</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\""+ExportCst.PREF_REF+"41\">pkg.Ext</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"52\" title=\"pkg.Ext-ONE.pkg.Ext-ONE(pkg.Int)\" href=\"#"+ExportCst.PREF_REF+"201\">ONE</a> (<a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#"+ExportCst.PREF_REF+"114\">new</a> <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a>(1)<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"67\">{</a>\n" +
                "  static int <a name=\""+ExportCst.PREF_REF+"82\">extField</a>;\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"105\">field</a>;\n" +
                "  <a name=\""+ExportCst.PREF_REF+"114\">public Int(</a>int <a name=\""+ExportCst.PREF_REF+"129\">p</a>){\n" +
                "   <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"105\">field</a> = <a href=\"#"+ExportCst.PREF_REF+"129\">p</a>;\n" +
                "  }\n" +
                "  public int <a name=\""+ExportCst.PREF_REF+"164\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#"+ExportCst.PREF_REF+"105\">field</a>;\n" +
                "  }\n" +
                " }</span>){\n" +
                "  <a name=\""+ExportCst.PREF_REF+"201\">ONE(</a><a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"209\">p</a>){\n" +
                "   <a title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#"+ExportCst.PREF_REF+"247\">super</a>(<a href=\"#"+ExportCst.PREF_REF+"209\">p</a>);\n" +
                "  }\n" +
                " };\n" +
                " <a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"239\">inner</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"247\">Ext(</a><a title=\"pkg.Int\" href=\"#"+ExportCst.PREF_REF+"10\">Int</a> <a name=\""+ExportCst.PREF_REF+"255\">p</a>){\n" +
                "  <a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"239\">inner</a> = <a href=\"#"+ExportCst.PREF_REF+"255\">p</a>;\n" +
                " }\n" +
                " static int <a name=\""+ExportCst.PREF_REF+"287\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.ONE\" href=\"#"+ExportCst.PREF_REF+"52\">ONE</a>.<a title=\"pkg.Ext.inner\" href=\"#"+ExportCst.PREF_REF+"239\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#"+ExportCst.PREF_REF+"25\">field</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report841Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int t = 0;\n");
        xml_.append("  return t[1???2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int <a name=\""+ExportCst.PREF_REF+"42\">t</a> = 0;\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"42\">t</a><a title=\"The type int is unexpected.\" class=\"e\">[</a>1???2];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report842Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[\"1\"???\"2\"];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[<span class=\"s\">\"1\"</span><a title=\"The operands types $core.String;$core.String for the operator ??? are unexpected.\" class=\"e\">?</a>?<a title=\"The operands types $core.String;$core.String for the operator ??? are unexpected.\" class=\"e\">?</a><span class=\"s\">\"2\"</span>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report843Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[\"1\"???];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[<span class=\"s\">\"1\"</span><a title=\"The operands types $core.String for the operator ??? are unexpected.\" class=\"e\">?</a>??];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report844Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Range m(){\n");
        xml_.append("  return ???;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Range <a name=\""+ExportCst.PREF_REF+"30\">m</a>(){\n" +
                "  return <a title=\"The expression part is empty.\" class=\"e\">???</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report845Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[???];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[<a title=\"The expression part is empty.\" class=\"e\">???</a>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report846Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[1???2???3???4];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[1???2???3<a title=\"The number of required operands 3 is different from the number of supplied arguments 4 for the operator ???\" class=\"e\">???</a>4];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report847Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[\"1\"???\"2\"???1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[<span class=\"s\">\"1\"</span><a title=\"The operands types $core.String;$core.String for the operator ??? are unexpected.\" class=\"e\">?</a>?<a title=\"The operands types $core.String;$core.String for the operator ??? are unexpected.\" class=\"e\">?</a><span class=\"s\">\"2\"</span>???1];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report848Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[\"1\"???\"2\"???\"3\"];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[<span class=\"s\">\"1\"</span><a title=\"The operands types $core.String;$core.String for the operator ??? are unexpected.\" class=\"e\">?</a>?<a title=\"The operands types $core.String;$core.String for the operator ??? are unexpected.\" class=\"e\">?</a><span class=\"s\">\"2\"</span>??<a title=\"The operands types $core.String for the operator ??? are unexpected.\" class=\"e\">?</a><span class=\"s\">\"3\"</span>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report849Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[1???2???\"3\"];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  int[] <a name=\""+ExportCst.PREF_REF+"44\">t</a> = {};\n" +
                "  return <a href=\"#"+ExportCst.PREF_REF+"44\">t</a>[1???2??<a title=\"The operands types $core.String for the operator ??? are unexpected.\" class=\"e\">?</a><span class=\"s\">\"3\"</span>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report850Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public long null(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public long <a name=\""+ExportCst.PREF_REF+"35\" title=\"A throw block or a return block is missing for the method null().\" class=\"e\">null</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report851Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object a =( $(MySub));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">a</a> =( <a title=\"The expression part is empty.\" class=\"e\">$</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report852Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object t = $null;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\" string\";\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">t</a> = $null;\n" +
                "  $switch(<a href=\"#"+ExportCst.PREF_REF+"39\">t</a>){\n" +
                "   $case $int <a name=\""+ExportCst.PREF_REF+"78\">v</a>;\n" +
                "    <a href=\"#"+ExportCst.PREF_REF+"39\">t</a>=<a href=\"#"+ExportCst.PREF_REF+"78\">v</a>+<span class=\"s\">\" int\"</span>;\n" +
                "   <a title=\"The code is unreachable in the function 0()\" class=\"e\">$case</a> 8;\n" +
                "    <a href=\"#"+ExportCst.PREF_REF+"39\">t</a>=<span class=\"s\">\" string\"</span><a title=\"The code is unreachable in the function 0()\" class=\"e\">;</a>\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report853Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  Object t = $null;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\" int\";\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\" string\";\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"39\">t</a> = $null;\n" +
                "  $switch(<a href=\"#"+ExportCst.PREF_REF+"39\">t</a>){\n" +
                "   $case 8;\n" +
                "    <a href=\"#"+ExportCst.PREF_REF+"39\">t</a>=<span class=\"s\">\" int\"</span>;\n" +
                "   <a title=\"The $case block with value 8 is duplicated in the parent $switch block.\" class=\"e\">$case</a> 8;\n" +
                "    <a href=\"#"+ExportCst.PREF_REF+"39\">t</a>=<span class=\"s\">\" string\"</span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report854Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  Inex<Ext<int>> v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"14\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"32\">m</a>(){\n" +
                "  <a title=\"The type Inex"+MessagesCdmBase.LT+"Ext"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">Inex</a>"+MessagesCdmBase.LT+"<a title=\"pkg.Ext\" href=\"#"+ExportCst.PREF_REF+"6\">Ext</a>"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"54\">v</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report855Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static void m(){\n");
        xml_.append("  Inex.i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static void <a name=\""+ExportCst.PREF_REF+"29\">m</a>(){\n" +
                "  <a title=\"There is no accessible field named Inex from the type pkg.Ext in this context.\" class=\"e\">Inex</a>.<a title=\"There is no accessible field named i from the type $core.Object in this context.\" class=\"e\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report856Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Param<ParamCt<int>> v;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Param<T> {\n");
        xml_.append("}\n");
        xml_.append("class pkg.Simple {\n");
        xml_.append("}\n");
        xml_.append("class pkg.ParamCt<U:Simple> {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " <a title=\"pkg.Param\" href=\"#"+ExportCst.PREF_REF+"48\">Param</a>"+MessagesCdmBase.LT+"<a title=\"pkg.ParamCt\" href=\"#"+ExportCst.PREF_REF+"92\">ParamCt</a><a title=\"The type pkg.ParamCt"+MessagesCdmBase.LT+"int"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.LT+"</a>int"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"37\">v</a>;\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"48\">pkg.Param</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"58\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"71\">pkg.Simple</a> {\n" +
                "}\n" +
                "class <a name=\""+ExportCst.PREF_REF+"92\">pkg.ParamCt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"104\">U</a>:<a title=\"pkg.Simple\" href=\"#"+ExportCst.PREF_REF+"71\">Simple</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report857Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" [] v;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " <a title=\"The type [] is unknown.\" class=\"e\">[]</a> <a name=\""+ExportCst.PREF_REF+"20\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report858Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch((Object)1){\n");
        xml_.append("   case int t: new ExTwo(){}.f + 2;\n");
        xml_.append("    return t;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public int f = 1;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  switch((Object)1){\n" +
                "   <a title=\"The type int is unexpected.\" class=\"e\">case</a> int <a name=\""+ExportCst.PREF_REF+"84\">t</a>: new <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"156\">ExTwo</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"98\">{</a>}</span>.<a title=\"pkg.ExTwo.f\" href=\"#"+ExportCst.PREF_REF+"180\">f</a> + 2;\n" +
                "    return <a href=\"#"+ExportCst.PREF_REF+"84\">t</a>;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"156\">pkg.ExTwo</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"180\">f</a> = 1;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report859Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch((Object)1){\n");
        xml_.append("   case int t;\n");
        xml_.append("    return t;\n");
        xml_.append("   case int t: t == 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  switch((Object)1){\n" +
                "   case int <a name=\""+ExportCst.PREF_REF+"84\">t</a>;\n" +
                "    return <a href=\"#"+ExportCst.PREF_REF+"84\">t</a>;\n" +
                "   <a title=\"The code is unreachable in the function static exmeth()\" class=\"e\">case</a> int <a name=\""+ExportCst.PREF_REF+"113\">t</a>: <a href=\"#"+ExportCst.PREF_REF+"113\">t</a> == 1;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report860Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch((int)1){\n");
        xml_.append("   case int t: t == 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  switch((int)1){\n" +
                "   <a title=\"This case block must be constant.\" class=\"e\">case</a> int <a name=\""+ExportCst.PREF_REF+"81\">t</a>: <a href=\"#"+ExportCst.PREF_REF+"81\">t</a> == 1;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report861Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch(ExEnum.ONE){\n");
        xml_.append("   case int t: t == 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  switch(<a title=\"pkg.ExEnum\" href=\"#"+ExportCst.PREF_REF+"129\">ExEnum</a>.<a title=\"pkg.ExEnum.ONE\" href=\"#"+ExportCst.PREF_REF+"143\">ONE</a>){\n" +
                "   <a title=\"This case block must be constant.\" class=\"e\">case</a> int <a name=\""+ExportCst.PREF_REF+"85\">t</a>: <a href=\"#"+ExportCst.PREF_REF+"85\">t</a> == 1;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "public enum <a name=\""+ExportCst.PREF_REF+"129\">pkg.ExEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"143\">ONE</a>\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report862Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE{}\n");
        xml_.append(" TWO{};\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\""+ExportCst.PREF_REF+"12\">pkg.Ex</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"22\">ONE</a>{}\n" +
                " <a name=\""+ExportCst.PREF_REF+"29\" title=\"Bad index by parsing.\" class=\"e\">TWO</a>{};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report863Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE{}\n");
        xml_.append(" TWO;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\""+ExportCst.PREF_REF+"12\">pkg.Ex</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"22\">ONE</a>{}\n" +
                " <a name=\""+ExportCst.PREF_REF+"29\" title=\"Bad index by parsing.\" class=\"e\">TWO</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report864Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  new Rec(field:10, RecSuper2. field2:12);\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec:RecSuper {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper2 {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"106\">Rec</a>(<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"130\">field</a>:10, <a title=\"pkg.RecSuper2\" href=\"#"+ExportCst.PREF_REF+"189\">RecSuper2</a>. <a title=\"The parameter function name RecSuper2. field2 is duplicated.\" class=\"e\">field2</a>:12);\n" +
                "  return null;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"106\" title=\"Initializing the interface pkg.RecSuper from the type {1} is needed.\" class=\"e\">pkg.Rec</a>:<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"149\">RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"130\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"149\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"169\">field2</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"189\">pkg.RecSuper2</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"210\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report865Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  new Rec(field:10, RecSuper . field3:12);\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec:RecSuper {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper2 {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"106\">Rec</a>(<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"130\">field</a>:10, <a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"149\">RecSuper</a> . <a title=\"The parameter function name RecSuper . field3 is duplicated.\" class=\"e\">field3</a>:12);\n" +
                "  return null;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"106\" title=\"Initializing the interface pkg.RecSuper from the type {1} is needed.\" class=\"e\">pkg.Rec</a>:<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"149\">RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"130\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"149\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"169\">field2</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"189\">pkg.RecSuper2</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"210\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report866Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  new Rec(field:10, RecSuper3. field2:12);\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec:RecSuper {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper2 {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  new <a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"106\">Rec</a>(<a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"130\">field</a>:10, <a title=\"The type RecSuper3 is unknown.\" class=\"e\">RecSuper3</a>. <a title=\"The parameter function name RecSuper3. field2 is duplicated.\" class=\"e\">field2</a>:12);\n" +
                "  return null;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"106\" title=\"Initializing the interface pkg.RecSuper from the type {1} is needed.\" class=\"e\">pkg.Rec</a>:<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"149\">RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"130\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"149\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"169\">field2</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"189\">pkg.RecSuper2</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"210\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report867Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  $lambda(Rec,new, field, RecSuper2. field2);\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec:RecSuper {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper2 {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"109\">Rec</a>,new, <a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"133\">field</a>, <a title=\"pkg.RecSuper2\" href=\"#"+ExportCst.PREF_REF+"192\">RecSuper2</a>. field2);\n" +
                "  return null;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"109\" title=\"Initializing the interface pkg.RecSuper from the type {1} is needed.\" class=\"e\">pkg.Rec</a>:<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"152\">RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"133\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"152\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"172\">field2</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"192\">pkg.RecSuper2</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"213\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report868Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  $lambda(Rec,new, field, RecSuper. field3);\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec:RecSuper {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper2 {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"108\">Rec</a>,new, <a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"132\">field</a>, <a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"151\">RecSuper</a>. field3);\n" +
                "  return null;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"108\" title=\"Initializing the interface pkg.RecSuper from the type {1} is needed.\" class=\"e\">pkg.Rec</a>:<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"151\">RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"132\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"151\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"171\">field2</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"191\">pkg.RecSuper2</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"212\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report869Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  $lambda(Rec,new, field, RecSuper3. field2);\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec:RecSuper {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper2 {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\""+ExportCst.PREF_REF+"6\">pkg.Ext</a> {\n" +
                " static Object <a name=\""+ExportCst.PREF_REF+"31\">m</a>(){\n" +
                "  <a title=\"The type pkg.Rec cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.Rec\" href=\"#"+ExportCst.PREF_REF+"109\">Rec</a>,new, <a title=\"pkg.Rec.field\" href=\"#"+ExportCst.PREF_REF+"133\">field</a>, <a title=\"The type RecSuper3 is unknown.\" class=\"e\">RecSuper3</a>. field2);\n" +
                "  return null;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\""+ExportCst.PREF_REF+"109\" title=\"Initializing the interface pkg.RecSuper from the type {1} is needed.\" class=\"e\">pkg.Rec</a>:<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"152\">RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"133\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"152\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"172\">field2</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"192\">pkg.RecSuper2</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"213\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report870Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@class interfaces(RecSuper) pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@class interfaces(<a title=\"pkg.RecSuper\" href=\"#"+ExportCst.PREF_REF+"62\">RecSuper</a>) <a name=\""+ExportCst.PREF_REF+"28\" title=\"The type pkg.RecSuper is not an interface.\" class=\"e\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"43\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"62\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"82\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report871Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@class interfaces(RecSuper2) pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@class interfaces(<a title=\"The type RecSuper2 is unknown.\" class=\"e\">RecSuper2</a>) <a name=\""+ExportCst.PREF_REF+"29\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"44\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"63\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"83\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report872Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@class interfaces(,) pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@class interfaces(<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">)</a> <a name=\""+ExportCst.PREF_REF+"21\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"36\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"55\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"75\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report873Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@class interfaces( , ) pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        xml_.append("interface pkg.RecSuper {\n");
        xml_.append(" int field2;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@class interfaces(<a title=\"There must be a type.\" class=\"e\"> </a>,<a title=\"There must be a type.\" class=\"e\"> </a>) <a name=\""+ExportCst.PREF_REF+"23\">pkg.Rec</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"38\">field</a>;\n" +
                "}\n" +
                "interface <a name=\""+ExportCst.PREF_REF+"57\">pkg.RecSuper</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"77\">field2</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report874Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("static pkg.ExTwo.*;\n");
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int infoIntOne()2i;\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotTwo {\n");
        xml_.append(" int infoIntTwo()4i;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append("\n");
        xml_.append(" public int this(int p){\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int p, @MyAnnot(infoIntOne=((int x:int)->y).call(1))@MyAnnotTwo(infoIntTwo=3i) int value){\n");
        xml_.append("  (switch(p){default;return 0;});\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Annotation[] arr = class(Ex).getDeclaredMethods()[1i].getDeclaredSwitchMethods()[0i].getAnnotationsSupp();\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><span class=\"i\">static pkg.ExTwo.*</span>;\n" +
                "public annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"57\">infoIntOne</a>()2i;\n" +
                "}\n" +
                "public annotation <a name=\""+ExportCst.PREF_REF+"93\">pkg.MyAnnotTwo</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"115\">infoIntTwo</a>()4i;\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"146\">pkg.Ex</a> {\n" +
                "\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"168\">this</a>(int <a name=\""+ExportCst.PREF_REF+"177\">p</a>){\n" +
                "  return 0i;\n" +
                " }\n" +
                " public void <a name=\""+ExportCst.PREF_REF+"210\">this</a>(int <a name=\""+ExportCst.PREF_REF+"219\">p</a>, @<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>(<a title=\"pkg.MyAnnot.infoIntOne()\" href=\"#"+ExportCst.PREF_REF+"57\">infoIntOne</a>=(<span class=\"t\">(int <a name=\""+ExportCst.PREF_REF+"248\">x</a>:int)<a name=\""+ExportCst.PREF_REF+"254\" title=\"The type $core.Object cannot be implicitly cast to int\" class=\"e\">-"+MessagesCdmBase.GT+"</a><a title=\"There is no accessible field named y from the type pkg.Ex in this context.\" class=\"e\">y</a></span>).<b>call</b>(1))@<a title=\"pkg.MyAnnotTwo\" href=\"#"+ExportCst.PREF_REF+"93\">MyAnnotTwo</a>(<a title=\"pkg.MyAnnotTwo.infoIntTwo()\" href=\"#"+ExportCst.PREF_REF+"115\">infoIntTwo</a>=3i) int value){\n" +
                "  (switch(<a href=\"#"+ExportCst.PREF_REF+"219\">p</a>)<span class=\"t\">{default;return 0;}</span>);\n" +
                " }\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"362\">exmeth</a>(){\n" +
                "  Annotation[] <a name=\""+ExportCst.PREF_REF+"387\">arr</a> = class(<a title=\"pkg.Ex\" href=\"#"+ExportCst.PREF_REF+"146\">Ex</a>).getDeclaredMethods()[1i].getDeclaredSwitchMethods()[0i].getAnnotationsSupp();\n" +
                "  return 0i;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report875Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch((Object)1){\n");
        xml_.append("   case int:?: new ExTwo(){}.f + 2;\n");
        xml_.append("    return 0;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public int f = 1;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.Ex</a> {\n" +
                " public static int <a name=\""+ExportCst.PREF_REF+"41\">exmeth</a>(){\n" +
                "  switch((Object)1){\n" +
                "   <a title=\"The type int is unexpected.\" class=\"e\">case</a> int:?: new <a title=\"pkg.ExTwo\" href=\"#"+ExportCst.PREF_REF+"156\">ExTwo</a>()<span class=\"t\"><a name=\""+ExportCst.PREF_REF+"98\">{</a>}</span>.<a title=\"pkg.ExTwo.f\" href=\"#"+ExportCst.PREF_REF+"180\">f</a> + 2;\n" +
                "    return 0;\n" +
                "  }\n" +
                "  return 0;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\""+ExportCst.PREF_REF+"156\">pkg.ExTwo</a> {\n" +
                " public int <a name=\""+ExportCst.PREF_REF+"180\">f</a> = 1;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void reportSpecTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" static Object m(){\n");
        xml_.append("  int[] t = {};\n");
        xml_.append("  return t[1???2???3???4];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertTrue(filesExp_.firstValue().contains("class=\"e\""));
    }
}
