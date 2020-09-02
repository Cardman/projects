package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class ErrorsZTest extends ProcessMethodCommon {

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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m42\">pkg.Ext</a> {\n" +
                " static int <a name=\"m64\">extField</a>;\n" +
                " static int <a name=\"m86\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m97\">l</a> = new <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m110\" title=\"The method field() from the type pkg.Int must be overriden in the concrete type pkg.Ext..Int*1.\" class=\"e\">{</a>\n" +
                "   public int <a name=\"m126\">field</a>=++<a title=\"pkg.Ext.extField\" href=\"#m64\">extField</a>;\n" +
                "  }</span>;\n" +
                "  return <a href=\"#m97\">l</a>.<a title=\"pkg.Int.field()\" href=\"#m25\">field</a>();\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <a name=\"m21\">field</a> = 15;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Ext</a> {\n" +
                " static int <a name=\"m63\">extField</a>;\n" +
                " static int <a name=\"m85\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">if</a> (new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m117\">{</a>\n" +
                "   public int <a name=\"m133\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Int.field\" href=\"#m21\">field</a> == 15)<a name=\"m159\">lab</a>{\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static int <a name=\"m28\">stfield</a>;\n" +
                " int <a name=\"m42\" title=\"A throw block or a return block is missing for the method field().\" class=\"e\">field</a>();\n" +
                " static {\n" +
                "  <a title=\"pkg.Int.stfield\" href=\"#m28\">stfield</a>++;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m85\">pkg.Ext</a> {\n" +
                " static int <a name=\"m107\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m118\">l</a> = new {} interfaces(<a title=\"pkg.Int\" href=\"#m6\">Int</a>) <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m150\" title=\"The type pkg.Int is not an interface.\" class=\"e\">{</a>\n" +
                "   public int <a name=\"m166\">field</a>=++<a title=\"pkg.Int.stfield\" href=\"#m28\">stfield</a>;\n" +
                "   public int <a name=\"m197\">field</a>(){\n" +
                "    return <a title=\"pkg.Ext..Int*1.field\" href=\"#m166\">field</a>;\n" +
                "   }\n" +
                "  }</span>;\n" +
                "  return <a href=\"#m118\">l</a>.<a title=\"pkg.Int.field()\" href=\"#m42\">field</a>();\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m41\">pkg.Ext</a> {\n" +
                " <a name=\"m52\" title=\"pkg.Ext-ONE.pkg.Ext-ONE(pkg.Int)\" href=\"#m200\">ONE</a>(<a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m113\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(1)<span class=\"t\"><a name=\"m66\">{</a>\n" +
                "  static int <a name=\"m81\">extField</a>;\n" +
                "  public int <a name=\"m104\">field</a>;\n" +
                "  <a name=\"m113\">public Int(</a>int <a name=\"m128\">p</a>){\n" +
                "   <a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a> = <a href=\"#m128\">p</a>;\n" +
                "  }\n" +
                "  public int <a name=\"m163\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a>;\n" +
                "  }\n" +
                " }</span>){\n" +
                "  <a name=\"m200\">ONE(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m208\">p</a>){\n" +
                "   <a title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#m246\">super</a>(<a href=\"#m208\">p</a>);\n" +
                "  }\n" +
                " };\n" +
                " <a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m238\">inner</a>;\n" +
                " <a name=\"m246\">Ext(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m254\">p</a>){\n" +
                "  <a title=\"pkg.Ext.inner\" href=\"#m238\">inner</a> = <a href=\"#m254\">p</a>;\n" +
                " }\n" +
                " static int <a name=\"m286\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.ONE\" href=\"#m52\">ONE</a>.<a title=\"pkg.Ext.inner\" href=\"#m238\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#m25\">field</a>();\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m41\">pkg.Ext</a> {\n" +
                " <a name=\"m52\" title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#m211\">ONE</a>(<a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m113\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(1)<span class=\"t\"><a name=\"m66\">{</a>\n" +
                "  static int <a name=\"m81\">extField</a>;\n" +
                "  public int <a name=\"m104\">field</a>;\n" +
                "  <a name=\"m113\">public Int(</a>int <a name=\"m128\">p</a>){\n" +
                "   <a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a> = <a href=\"#m128\">p</a>;\n" +
                "  }\n" +
                "  public int <a name=\"m163\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a>;\n" +
                "  }\n" +
                " }</span>);\n" +
                " <a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m203\">inner</a>;\n" +
                " <a name=\"m211\">Ext(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m219\">p</a>){\n" +
                "  <a title=\"pkg.Ext.inner\" href=\"#m203\">inner</a> = <a href=\"#m219\">p</a>;\n" +
                " }\n" +
                " static int <a name=\"m251\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.ONE\" href=\"#m52\">ONE</a>.<a title=\"pkg.Ext.inner\" href=\"#m203\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#m25\">field</a>();\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m42\">pkg.Ext</a> {\n" +
                " static <a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m64\">inner</a> = new <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m81\">{</a>\n" +
                "  static int <a name=\"m96\">extField</a>;\n" +
                "  public int <a name=\"m119\">field</a>=1;\n" +
                "  public int <a name=\"m141\">field</a>(){\n" +
                "   return <a title=\"pkg.Ext..Int*1.field\" href=\"#m119\">field</a>;\n" +
                "  }\n" +
                " }</span>;\n" +
                " static int <a name=\"m187\">m</a>(){\n" +
                "  return 0;\n" +
                "  <a title=\"The code is unreachable in the function static m()\" class=\"e\">return</a> <a title=\"pkg.Ext.inner\" href=\"#m64\">inner</a>.<a title=\"pkg.Int.field()\" href=\"#m25\">field</a>();\n" +
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
        xml_.append("   case 1:\n");
        xml_.append("   res+=',';\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <a name=\"m21\">field</a>=1;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <a name=\"m77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  int <a name=\"m93\">i</a> = 0;\n" +
                "  switch (new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m119\">{</a>}</span>.<a title=\"pkg.Int.field\" href=\"#m21\">field</a>){\n" +
                "   case 1:\n" +
                "   <a href=\"#m77\">res</a>+=<span class=\"s\">','</span>;\n" +
                "   <a href=\"#m93\">i</a>++;\n" +
                "  }\n" +
                "  return <a href=\"#m77\">res</a>;\n" +
                "  <a title=\"The code is unreachable in the function static m()\n" +
                "\n" +
                "The type int cannot be implicitly cast to $core.String\" class=\"e\">return</a> 0;\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <a name=\"m21\">field</a>=1;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <a name=\"m77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  for (int <a name=\"m98\">i</a>: <a title=\"pkg.Ext..Iterable*1.pkg.Ext..Iterable*1(int...)\" href=\"#m140\">new</a> Iterable&lt;int&gt;(1,2)<span class=\"t\"><a name=\"m123\">{</a>\n" +
                "   int[] <a name=\"m134\">f</a>;\n" +
                "   <a name=\"m140\">Iterable(</a>int... <a name=\"m156\">a</a>){\n" +
                "    <a title=\"pkg.Ext..Iterable*1.f\" href=\"#m134\">f</a> = <a href=\"#m156\">a</a>;\n" +
                "   }\n" +
                "   public Iterator&lt;int&gt; <a name=\"m200\">iterator</a>(){\n" +
                "    return <a title=\"pkg.Ext..Iterable*1..Iterator*1.pkg.Ext..Iterable*1..Iterator*1(int...)\" href=\"#m276\">new</a> Iterator&lt;int&gt;(<a title=\"pkg.Ext..Iterable*1.f\" href=\"#m134\">f</a>)<span class=\"t\"><a name=\"m243\">{</a>\n" +
                "     int[] <a name=\"m256\">g</a>;\n" +
                "     int <a name=\"m268\">j</a>;\n" +
                "     <a name=\"m276\">Iterator(</a>int... <a name=\"m292\">a</a>){\n" +
                "      <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#m256\">g</a> = <a href=\"#m292\">a</a>;\n" +
                "     }\n" +
                "     public boolean <a name=\"m336\">hasNext</a>(){\n" +
                "      return <a title=\"pkg.Ext..Iterable*1..Iterator*1.j\" href=\"#m268\">j</a> &lt; <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#m256\">g</a>.<b>length</b>;\n" +
                "     }\n" +
                "     public int <a name=\"m397\">next</a>(){\n" +
                "      return <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#m256\">g</a>[<a title=\"pkg.Ext..Iterable*1..Iterator*1.j\" href=\"#m268\">j</a>++];\n" +
                "     }\n" +
                "    }</span>;\n" +
                "   }\n" +
                "  }</span>) {\n" +
                "   <a href=\"#m77\">res</a>+=<a href=\"#m98\">i</a>+<span class=\"s\">\",\"</span>;\n" +
                "  }\n" +
                "  return <a href=\"#m77\">res</a>;\n" +
                "  <a title=\"The code is unreachable in the function static m()\n" +
                "\n" +
                "The type int cannot be implicitly cast to $core.String\" class=\"e\">return</a> 0;\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <a name=\"m21\">field</a>=1;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <a name=\"m77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  for (int <a name=\"m98\">i</a>, int <a name=\"m105\">y</a>: <a title=\"pkg.Ext..IterableTable*1.pkg.Ext..IterableTable*1([int,[int)\" href=\"#m194\">new</a> IterableTable&lt;int,int&gt;(new int[]{1,2},new int[]{3,4})<span class=\"t\"><a name=\"m165\">{</a>\n" +
                "   int[] <a name=\"m176\">e</a>;\n" +
                "   int[] <a name=\"m188\">f</a>;\n" +
                "   <a name=\"m194\">IterableTable(</a>int[] <a name=\"m214\">b</a>,int[] <a name=\"m222\">a</a>){\n" +
                "    <a title=\"pkg.Ext..IterableTable*1.e\" href=\"#m176\">e</a> = <a href=\"#m214\">b</a>;\n" +
                "    <a title=\"pkg.Ext..IterableTable*1.f\" href=\"#m188\">f</a> = <a href=\"#m222\">a</a>;\n" +
                "   }\n" +
                "   public IteratorTable&lt;int,int&gt; <a name=\"m286\">iteratorTable</a>(){\n" +
                "    return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.pkg.Ext..IterableTable*1..IteratorTable*1([int,[int)\" href=\"#m392\">new</a> IteratorTable&lt;int,int&gt;(<a title=\"pkg.Ext..IterableTable*1.e\" href=\"#m176\">e</a>,<a title=\"pkg.Ext..IterableTable*1.f\" href=\"#m188\">f</a>)<span class=\"t\"><a name=\"m345\">{</a>\n" +
                "     int[] <a name=\"m358\">g</a>;\n" +
                "     int[] <a name=\"m372\">h</a>;\n" +
                "     int <a name=\"m384\">j</a>;\n" +
                "     <a name=\"m392\">IteratorTable(</a>int[] <a name=\"m412\">b</a>,int[] <a name=\"m420\">a</a>){\n" +
                "      <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#m358\">g</a> = <a href=\"#m412\">b</a>;\n" +
                "      <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.h\" href=\"#m372\">h</a> = <a href=\"#m420\">a</a>;\n" +
                "     }\n" +
                "     public boolean <a name=\"m477\">hasNextPair</a>(){\n" +
                "      return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.j\" href=\"#m384\">j</a> &lt; <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#m358\">g</a>.<b>length</b>;\n" +
                "     }\n" +
                "     public Pair&lt;int,int&gt; <a name=\"m552\">nextPair</a>(){\n" +
                "      return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1([int,[int,int)\" href=\"#m658\">new</a> Pair&lt;int,int&gt;(<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#m358\">g</a>,<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.h\" href=\"#m372\">h</a>,<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.j\" href=\"#m384\">j</a>++)<span class=\"t\"><a name=\"m603\">{</a>\n" +
                "       int[] <a name=\"m618\">k</a>;\n" +
                "       int[] <a name=\"m634\">l</a>;\n" +
                "       int <a name=\"m648\">m</a>;\n" +
                "       <a name=\"m658\">Pair(</a>int[] <a name=\"m669\">b</a>,int[] <a name=\"m677\">a</a>, int <a name=\"m684\">z</a>){\n" +
                "        <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.k\" href=\"#m618\">k</a> = <a href=\"#m669\">b</a>;\n" +
                "        <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.l\" href=\"#m634\">l</a> = <a href=\"#m677\">a</a>;\n" +
                "        <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#m648\">m</a> = <a href=\"#m684\">z</a>;\n" +
                "       }\n" +
                "       public int <a name=\"m760\">getFirst</a>(){\n" +
                "        return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.k\" href=\"#m618\">k</a>[<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#m648\">m</a>];\n" +
                "       }\n" +
                "       public int <a name=\"m820\">getSecond</a>(){\n" +
                "        return <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.l\" href=\"#m634\">l</a>[<a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#m648\">m</a>];\n" +
                "       }\n" +
                "      }</span>;\n" +
                "     }\n" +
                "    }</span>;\n" +
                "   }\n" +
                "  }</span>) {\n" +
                "   <a href=\"#m77\">res</a>+=<a href=\"#m105\">y</a>+<span class=\"s\">\",\"</span>+<a href=\"#m98\">i</a>+<span class=\"s\">\";\"</span>;\n" +
                "  }\n" +
                "  return <a href=\"#m77\">res</a>;\n" +
                "  <a title=\"The code is unreachable in the function static m()\n" +
                "\n" +
                "The type int cannot be implicitly cast to $core.String\" class=\"e\">return</a> 0;\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Init</a> {\n" +
                " int <a name=\"m22\">field</a> = 2;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Until</a> {\n" +
                " int <a name=\"m58\">field</a> = 15;\n" +
                "}\n" +
                "class <a name=\"m78\">pkg.Step</a> {\n" +
                " int <a name=\"m94\">field</a> = 3;\n" +
                "}\n" +
                "class <a name=\"m113\">pkg.Ext</a> {\n" +
                " static int <a name=\"m135\">extField</a>;\n" +
                " static int <a name=\"m157\">m</a>(){\n" +
                "  int <a name=\"m168\">sum</a> = 0;\n" +
                "  iter (int <a name=\"m189\">v</a> = new <a title=\"pkg.Init\" href=\"#m6\">Init</a>()<span class=\"t\"><a name=\"m203\">{</a>\n" +
                "   public int <a name=\"m219\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Init.field\" href=\"#m22\">field</a>;new <a title=\"pkg.Until\" href=\"#m41\">Until</a>()<span class=\"t\"><a name=\"m250\">{</a>\n" +
                "   public int <a name=\"m266\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Until.field\" href=\"#m58\">field</a>;new <a title=\"pkg.Step\" href=\"#m78\">Step</a>()<span class=\"t\"><a name=\"m296\">{</a>\n" +
                "   public int <a name=\"m312\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Step.field\" href=\"#m94\">field</a>){\n" +
                "   <a href=\"#m168\">sum</a> += <a href=\"#m189\">v</a>;\n" +
                "  }\n" +
                "  return <a href=\"#m168\">sum</a>;\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Init</a> {\n" +
                " int <a name=\"m22\">field</a> = 2;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Until</a> {\n" +
                " int <a name=\"m58\">field</a> = 15;\n" +
                "}\n" +
                "class <a name=\"m78\">pkg.Step</a> {\n" +
                " int <a name=\"m94\">field</a> = 3;\n" +
                "}\n" +
                "class <a name=\"m113\">pkg.Ext</a> {\n" +
                " static int <a name=\"m135\">extField</a>;\n" +
                " static int <a name=\"m157\">m</a>(){\n" +
                "  int <a name=\"m168\">sum</a> = 0;\n" +
                "  for (int <a name=\"m188\">v</a> = new <a title=\"pkg.Init\" href=\"#m6\">Init</a>()<span class=\"t\"><a name=\"m202\">{</a>\n" +
                "   public int <a name=\"m218\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Init.field\" href=\"#m22\">field</a>;<a href=\"#m188\">v</a> &lt; new <a title=\"pkg.Until\" href=\"#m41\">Until</a>()<span class=\"t\"><a name=\"m253\">{</a>\n" +
                "   public int <a name=\"m269\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Until.field\" href=\"#m58\">field</a>;<a href=\"#m188\">v</a> += new <a title=\"pkg.Step\" href=\"#m78\">Step</a>()<span class=\"t\"><a name=\"m304\">{</a>\n" +
                "   public int <a name=\"m320\">subfield</a>;\n" +
                "  }</span>.<a title=\"pkg.Step.field\" href=\"#m94\">field</a>){\n" +
                "   <a href=\"#m168\">sum</a> += <a href=\"#m188\">v</a>;\n" +
                "  }\n" +
                "  return <a href=\"#m168\">sum</a>;\n" +
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
        xml_.append("   case new Int(){}.CST:\n");
        xml_.append("    res += 1;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <a name=\"m21\">CST</a> = 1;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <a name=\"m77\">res</a> = <span class=\"s\">\"\"</span>;\n" +
                "  switch(1){\n" +
                "   <a title=\"The case block with expression new Int(){}.CST is not constant.\" class=\"e\">case</a> new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m117\">{</a>}</span>.<a title=\"pkg.Int.CST\" href=\"#m21\">CST</a>:\n" +
                "    <a href=\"#m77\">res</a> += 1;\n" +
                "  }\n" +
                "  return <a href=\"#m77\">res</a>;\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static void <a name=\"m29\">m</a>(){\n" +
                "  new Enum&lt;<a title=\"pkg.Ext..MyEnum\" href=\"#m67\">MyEnum</a>&gt;()<span class=\"t\"><a name=\"m54\" title=\"The type pkg.Ext..Enum*1 cannot have explicitly the type $core.Enum as super type because $core.Enum is reserved.\" class=\"e\">{</a>}</span>;\n" +
                " }\n" +
                " enum <a name=\"m67\">MyEnum</a>{\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static void <a name=\"m29\">m</a>(){\n" +
                "  new $en()<span class=\"t\"><a name=\"m45\" title=\"The type pkg.Ext..$en*1 cannot have explicitly the type $core.$en as super type because $core.$en is reserved.\" class=\"e\">{</a>}</span>;\n" +
                " }\n" +
                " enum <a name=\"m58\">MyEnum</a>{\n" +
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
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static void <a name=\"m29\">m</a>(){\n" +
                "  <a title=\"The type $core.CharSequence is not resolved for instancing.\" class=\"e\">new</a> CharSequence()<span class=\"t\"><a name=\"m54\">{</a>int nonSeen;}</span>;\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"t\">(int <a name=\"m47\">a</a>:int)<a name=\"m53\" title=\"A throw block or a return block is missing for the method static .1(int).\" class=\"e\">-&gt;</a>{}</span>.<b>call</b>(3);\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"t\">(int <a name=\"m47\">a</a>,int <a title=\"The parameter function name a is duplicated.\" class=\"e\">a</a>:int)<a name=\"m59\">-&gt;</a>{return 0;}</span>.<b>call</b>(3,4);\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static void <a name=\"m29\">m</a>(){\n" +
                "  <span class=\"t\">(int <a name=\"m41\">a</a>:void)<a name=\"m48\">-&gt;</a>{<a title=\"The type cannot be the key word void.\" class=\"e\">return</a> 0;}</span>.<b>call</b>(3);\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static int <a name=\"m145\">extField</a>;\n" +
                " static int <a name=\"m167\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m89\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " <a name=\"m133\">ONE</a>;\n" +
                " static int <a name=\"m150\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m89\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " <a name=\"m133\">ONE</a>{};\n" +
                " static int <a name=\"m152\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m106\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m91\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " int <a name=\"m106\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m122\">pkg.Ext</a> {\n" +
                " <a name=\"m133\">ONE</a>{};\n" +
                " static int <a name=\"m152\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage671Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>\n" +
                " int <a name=\"m81\">field</a>()new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m97\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>;\n" +
                "}\n" +
                "enum <a name=\"m114\">pkg.Ext</a> {\n" +
                " <a name=\"m125\">ONE</a>{};\n" +
                " static int <a name=\"m144\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage672Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m106\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m91\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " int <a name=\"m106\">field</a>()new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>;\n" +
                "}\n" +
                "enum <a name=\"m139\">pkg.Ext</a> {\n" +
                " <a name=\"m150\">ONE</a>{};\n" +
                " static int <a name=\"m169\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage673Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<a title=\"pkg.Int\" href=\"#m6\">Int</a>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m148\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static int <a name=\"m170\">extField</a>;\n" +
                " static int <a name=\"m192\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage674Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<a title=\"pkg.Int\" href=\"#m6\">Int</a>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static int <a name=\"m170\">extField</a>;\n" +
                " static int <a name=\"m192\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage675Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m156\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static int <a name=\"m178\">extField</a>;\n" +
                " static int <a name=\"m200\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage676Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " <a name=\"m134\">Ext(</a>){};\n" +
                " static int <a name=\"m155\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage677Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " <a name=\"m134\">Ext(</a>@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m160\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m174\">p</a>){};\n" +
                " static int <a name=\"m192\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage678Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " <a name=\"m134\">Ext(</a>@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m160\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m191\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m205\">p</a>){};\n" +
                " static int <a name=\"m223\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage679Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " <a name=\"m134\">Ext(</a>@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m160\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m174\">p</a>, @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m199\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m213\">q</a>){};\n" +
                " static int <a name=\"m231\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage680Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static void <a name=\"m146\">l</a>(){};\n" +
                " static int <a name=\"m165\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage681Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static void <a name=\"m146\">l</a>(@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m184\">p</a>){};\n" +
                " static int <a name=\"m202\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage682Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static void <a name=\"m146\">l</a>(@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m201\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m215\">p</a>){};\n" +
                " static int <a name=\"m233\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage683Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " static void <a name=\"m146\">l</a>(@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m184\">p</a>, @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m209\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m223\">q</a>){};\n" +
                " static int <a name=\"m241\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage684Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " void <a name=\"m139\">this</a>(@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m166\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m180\">p</a>){};\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m209\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " int <a name=\"m224\">this</a>(@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m251\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m265\">p</a>){return 0;};\n" +
                " static int <a name=\"m292\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage685Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " @<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                " operator<a name=\"m142\">+</a> int(@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>) int <a name=\"m184\">p</a>){return 0;};\n" +
                " static int <a name=\"m211\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage686Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <a name=\"m34\">FIELD</a>=1;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m106\">{</a>}</span>.<a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a>)\n" +
                "class <a name=\"m122\">pkg.Ext</a> {\n" +
                " static int <a name=\"m144\" title=\"A throw block or a return block is missing for the method static m().\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage687Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return staticCall(<a title=\"pkg.Ext\" href=\"#m6\">Ext</a>).<a title=\"pkg.Ext.staticCall m()\" href=\"#m82\">m</a>();\n" +
                " }\n" +
                " staticCall int <a name=\"m82\">m</a>(){\n" +
                "  return <a title=\"pkg.Ext.staticCall m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m141\">m</a>(<span class=\"t\"><a name=\"m98\">a</a> <a name=\"m100\">-&gt;</a> <span class=\"t\"><a name=\"m103\">a</a> <a name=\"m105\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m103\">a</a> <a title=\"The operands types int;$core.Object for the operator * are unexpected.\" class=\"e\">*</a> <a title=\"There is no accessible field named ##a from the type pkg.Ext in this context.\" class=\"e\">##a</a></span></span>,2,3);\n" +
                " }\n" +
                " staticCall int <a name=\"m141\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m165\">fct</a>,int <a name=\"m173\">a</a>,int <a name=\"m179\">b</a>){\n" +
                "  return <a href=\"#m165\">fct</a>.<b>call</b>(<a href=\"#m173\">a</a>).<b>call</b>(<a href=\"#m179\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage688Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <a name=\"m39\">sum</a> = 0;\n" +
                "  int <a name=\"m54\">sum2</a> = 3;\n" +
                "  for (int <a name=\"m75\">i</a> = 1; <a href=\"#m75\">i</a> &lt;= 9; <a href=\"#m75\">i</a>+= 2){\n" +
                "   <a href=\"#m39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m187\">m</a>(<span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m54\">sum2</a> + <a href=\"#m115\">i</a> + <a href=\"#m110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable ##i is undefined in this context.\" class=\"e\">##i</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#m75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#m39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\"m187\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m211\">fct</a>,int <a name=\"m219\">a</a>,int <a name=\"m225\">b</a>){\n" +
                "  return <a href=\"#m211\">fct</a>.<b>call</b>(<a href=\"#m219\">a</a>).<b>call</b>(<a href=\"#m225\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage689Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <a name=\"m39\">sum</a> = 0;\n" +
                "  int <a name=\"m54\">sum2</a> = 3;\n" +
                "  for (int <a name=\"m75\">i</a> = 1; <a href=\"#m75\">i</a> &lt;= 9; <a href=\"#m75\">i</a>+= 2){\n" +
                "   <a href=\"#m39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m186\">m</a>(<span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m54\">sum2</a> + <a href=\"#m115\">i</a> + <a href=\"#m110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable #a is undefined in this context.\" class=\"e\">#a</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#m75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#m39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\"m186\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m210\">fct</a>,int <a name=\"m218\">a</a>,int <a name=\"m224\">b</a>){\n" +
                "  return <a href=\"#m210\">fct</a>.<b>call</b>(<a href=\"#m218\">a</a>).<b>call</b>(<a href=\"#m224\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage690Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <a name=\"m39\">sum</a> = 0;\n" +
                "  int <a name=\"m54\">sum2</a> = 3;\n" +
                "  for (int <a name=\"m75\">i</a> = 1; <a href=\"#m75\">i</a> &lt;= 9; <a href=\"#m75\">i</a>+= 2){\n" +
                "   <a href=\"#m39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m185\">m</a>(<span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m54\">sum2</a> + <a href=\"#m115\">i</a> + <a href=\"#m110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable # is undefined in this context.\" class=\"e\">#</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#m75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#m39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\"m185\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m209\">fct</a>,int <a name=\"m217\">a</a>,int <a name=\"m223\">b</a>){\n" +
                "  return <a href=\"#m209\">fct</a>.<b>call</b>(<a href=\"#m217\">a</a>).<b>call</b>(<a href=\"#m223\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage691Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <a name=\"m39\">sum</a> = 0;\n" +
                "  int <a name=\"m54\">sum2</a> = 3;\n" +
                "  for (int <a name=\"m75\">i</a> = 1; <a href=\"#m75\">i</a> &lt;= 9; <a href=\"#m75\">i</a>+= 2){\n" +
                "   <a href=\"#m39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m185\">m</a>(<span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m54\">sum2</a> + <a href=\"#m115\">i</a> + <a href=\"#m110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable , is undefined in this context.\" class=\"e\">,</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#m75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#m39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\"m185\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m209\">fct</a>,int <a name=\"m217\">a</a>,int <a name=\"m223\">b</a>){\n" +
                "  return <a href=\"#m209\">fct</a>.<b>call</b>(<a href=\"#m217\">a</a>).<b>call</b>(<a href=\"#m223\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage692Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <a name=\"m39\">sum</a> = 0;\n" +
                "  int <a name=\"m54\">sum2</a> = 3;\n" +
                "  for (int <a name=\"m75\">i</a> = 1; <a href=\"#m75\">i</a> &lt;= 9; <a href=\"#m75\">i</a>+= 2){\n" +
                "   <a href=\"#m39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m186\">m</a>(<span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m54\">sum2</a> + <a href=\"#m115\">i</a> + <a href=\"#m110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable ,a is undefined in this context.\" class=\"e\">,a</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#m75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#m39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\"m186\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m210\">fct</a>,int <a name=\"m218\">a</a>,int <a name=\"m224\">b</a>){\n" +
                "  return <a href=\"#m210\">fct</a>.<b>call</b>(<a href=\"#m218\">a</a>).<b>call</b>(<a href=\"#m224\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage693Test() {
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
        ContextEl cont_ = contextElErrorStdReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = getErrors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <a name=\"m39\">sum</a> = 0;\n" +
                "  int <a name=\"m54\">sum2</a> = 3;\n" +
                "  for (int <a name=\"m75\">i</a> = 1; <a href=\"#m75\">i</a> &lt;= 9; <a href=\"#m75\">i</a>+= 2){\n" +
                "   <a href=\"#m39\">sum</a> += <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m188\">m</a>(<span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\" title=\"The type $core.Number cannot be implicitly cast to int\" class=\"e\">-&gt;</a> <a href=\"#m54\">sum2</a> + <a href=\"#m115\">i</a> + <a href=\"#m110\">a</a> <a title=\"The operands types int;$core.Object for the operator + are unexpected.\" class=\"e\">+</a> ([<a title=\"The variable ,a,b is undefined in this context.\" class=\"e\">,a,b</a>]) <a title=\"The operands types $core.Number;int for the operator + are unexpected.\" class=\"e\">+</a> <a href=\"#m75\">#i</a></span></span>,2,7);\n" +
                "  }\n" +
                "  return <a href=\"#m39\">sum</a>;\n" +
                " }\n" +
                " static int <a name=\"m188\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m212\">fct</a>,int <a name=\"m220\">a</a>,int <a name=\"m226\">b</a>){\n" +
                "  return <a href=\"#m212\">fct</a>.<b>call</b>(<a href=\"#m220\">a</a>).<b>call</b>(<a href=\"#m226\">b</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
}
