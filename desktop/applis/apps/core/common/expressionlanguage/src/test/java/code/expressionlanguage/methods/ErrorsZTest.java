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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Init</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Init</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
    }
}
