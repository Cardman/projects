package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class CoverageReportZTest extends ProcessMethodCommon {

    @Test
    public void coverage399Test() {
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
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m42\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m64\">extField</a></span>;\n" +
                " static int <a name=\"m86\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m97\">l</a> </span>=<span class=\"f\"> new <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m110\">{</a>\n" +
                "   public int <span class=\"f\"><span class=\"f\"><a name=\"m126\">field</a></span>=<span class=\"f\">++<span class=\"f\"><a title=\"pkg.Ext.extField\" href=\"#m64\">extField</a></span></span></span>;\n" +
                "   public int <a name=\"m158\">field</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m126\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m97\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage400Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" public int field=++extField;\n");
        xml_.append(" public int field(){\n");
        xml_.append("  return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static int <span class=\"g\"><a name=\"m28\">extField</a></span>;\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m50\">field</a></span>=<span class=\"f\">++<span class=\"f\"><a title=\"pkg.Int.extField\" href=\"#m28\">extField</a></span></span></span>;\n" +
                " public int <a name=\"m80\">field</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m50\">field</a></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m116\">pkg.Ext</a> {\n" +
                " static int <a name=\"m138\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m6\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m149\">l</a> </span>=<span class=\"f\"> new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m162\">{</a>\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m149\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m80\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage401Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new Int(1){\n");
        xml_.append("   public int field;\n");
        xml_.append("   Int(int p){\n");
        xml_.append("    field=p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m42\">pkg.Ext</a> {\n" +
                " static int <a name=\"m64\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m75\">l</a> </span>=<span class=\"f\"> <a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m115\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"f\">1</span>)<span class=\"t\"><a name=\"m89\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m105\">field</a></span>;\n" +
                "   <a name=\"m115\">Int(</a>int <a name=\"m123\">p</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m105\">field</a></span>=<span class=\"f\"><a href=\"#m123\">p</a></span></span>;\n" +
                "   }\n" +
                "   public int <a name=\"m159\">field</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m105\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m75\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage402Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field = 15;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  if (new Int(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }.field == 15)lab{\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m63\">extField</a></span>;\n" +
                " static int <a name=\"m85\">m</a>(){\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m105\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m121\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 15</span></span>)<a name=\"m147\">lab</a>{\n" +
                "   return <span class=\"f\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">2</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage403Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field = 15;\n");
        xml_.append(" static boolean true(Int i){\n");
        xml_.append("  return i.field == 15;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  if (new Int(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  }){\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                " static boolean <a name=\"m49\">true</a>(<a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m58\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">i</a></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 15</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m97\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m119\">extField</a></span>;\n" +
                " static int <a name=\"m141\">m</a>(){\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m161\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m177\">subfield</a></span>;\n" +
                "  }</span></span>)<a title=\"pkg.Int.static true(boolean,pkg.Int)\" href=\"#m49\">{</a>\n" +
                "   return <span class=\"f\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">2</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage404Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field = 15;\n");
        xml_.append(" static boolean true(Int i){\n");
        xml_.append("  return i.field == 15;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  if (new Int(){\n");
        xml_.append("   public int subfield;\n");
        xml_.append("  })lab{\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                " static boolean <a name=\"m49\">true</a>(<a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m58\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">i</a></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 15</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m97\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m119\">extField</a></span>;\n" +
                " static int <a name=\"m141\">m</a>(){\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m161\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m177\">subfield</a></span>;\n" +
                "  }</span></span>)<a name=\"m191\">lab</a><a title=\"pkg.Int.static true(boolean,pkg.Int)\" href=\"#m49\">{</a>\n" +
                "   return <span class=\"f\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">2</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage405Test() {
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
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Init</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m22\">field</a> </span>=<span class=\"f\"> 2</span></span>;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Until</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m58\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                "}\n" +
                "class <a name=\"m78\">pkg.Step</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m94\">field</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "}\n" +
                "class <a name=\"m113\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m135\">extField</a></span>;\n" +
                " static int <a name=\"m157\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m168\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">iter</span> (int <a name=\"m189\">v</a> = <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Init\" href=\"#m6\">Init</a>()<span class=\"t\"><a name=\"m203\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m219\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Init.field\" href=\"#m22\">field</a></span></span>;<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Until\" href=\"#m41\">Until</a>()<span class=\"t\"><a name=\"m250\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m266\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Until.field\" href=\"#m58\">field</a></span></span>;<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Step\" href=\"#m78\">Step</a>()<span class=\"t\"><a name=\"m296\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m312\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Step.field\" href=\"#m94\">field</a></span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m168\">sum</a> </span>+=<span class=\"f\"> <a href=\"#m189\">v</a></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m168\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage406Test() {
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
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Init</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m22\">field</a> </span>=<span class=\"f\"> 2</span></span>;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Until</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m58\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                "}\n" +
                "class <a name=\"m78\">pkg.Step</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m94\">field</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "}\n" +
                "class <a name=\"m113\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m135\">extField</a></span>;\n" +
                " static int <a name=\"m157\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m168\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">iter</span> (int <a name=\"m189\">v</a> = <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Init\" href=\"#m6\">Init</a>()<span class=\"t\"><a name=\"m203\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m219\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Init.field\" href=\"#m22\">field</a></span></span>;<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Until\" href=\"#m41\">Until</a>()<span class=\"t\"><a name=\"m250\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m266\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Until.field\" href=\"#m58\">field</a></span></span>;<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Step\" href=\"#m78\">Step</a>()<span class=\"t\"><a name=\"m296\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m312\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Step.field\" href=\"#m94\">field</a></span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m168\">sum</a> </span>+=<span class=\"f\"> <a href=\"#m189\">v</a></span></span>;\n" +
                "  }\n" +
                "  <span class=\"f\">iter</span> (int <a name=\"m363\">v</a> = <span class=\"f\"><span class=\"f\">new <a title=\"pkg.Init\" href=\"#m6\">Init</a>()<span class=\"t\"><a name=\"m377\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m393\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Init.field\" href=\"#m22\">field</a></span></span>;<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Until\" href=\"#m41\">Until</a>()<span class=\"t\"><a name=\"m424\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m440\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Until.field\" href=\"#m58\">field</a></span></span>;<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Step\" href=\"#m78\">Step</a>()<span class=\"t\"><a name=\"m470\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m486\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Step.field\" href=\"#m94\">field</a></span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m168\">sum</a> </span>+=<span class=\"f\"> <a href=\"#m363\">v</a></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m168\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage407Test() {
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
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Init</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m22\">field</a> </span>=<span class=\"f\"> 2</span></span>;\n" +
                "}\n" +
                "class <a name=\"m41\">pkg.Until</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m58\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                "}\n" +
                "class <a name=\"m78\">pkg.Step</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m94\">field</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "}\n" +
                "class <a name=\"m113\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m135\">extField</a></span>;\n" +
                " static int <a name=\"m157\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m168\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m188\">v</a> </span>=<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Init\" href=\"#m6\">Init</a>()<span class=\"t\"><a name=\"m202\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m218\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Init.field\" href=\"#m22\">field</a></span></span></span>;<span class=\"f\"><span class=\"f\"><a href=\"#m188\">v</a> </span>&lt;<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Until\" href=\"#m41\">Until</a>()<span class=\"t\"><a name=\"m253\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m269\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Until.field\" href=\"#m58\">field</a></span></span></span>;<span class=\"f\"><span class=\"f\"><a href=\"#m188\">v</a> </span>+=<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Step\" href=\"#m78\">Step</a>()<span class=\"t\"><a name=\"m304\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m320\">subfield</a></span>;\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Step.field\" href=\"#m94\">field</a></span></span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m168\">sum</a> </span>+=<span class=\"f\"> <a href=\"#m188\">v</a></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m168\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage408Test() {
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
        xml_.append("  return ONE.inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m41\">pkg.Ext</a> {\n" +
                " <span class=\"g\"><a name=\"m52\" title=\"pkg.Ext-ONE.pkg.Ext-ONE(pkg.Int)\" href=\"#m200\">ONE</a>(<span class=\"g\"><a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m113\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"g\">1</span>)<span class=\"t\"><a name=\"m66\">{</a>\n" +
                "  static int <span class=\"g\"><a name=\"m81\">extField</a></span>;\n" +
                "  public int <span class=\"g\"><a name=\"m104\">field</a></span>;\n" +
                "  <a name=\"m113\">public Int(</a>int <a name=\"m128\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a> </span>=<span class=\"g\"> <a href=\"#m128\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m163\">field</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span>)</span>{\n" +
                "  <a name=\"m200\">ONE(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m208\">p</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#m246\">super</a>(<span class=\"g\"><a href=\"#m208\">p</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"g\"><a name=\"m238\">inner</a></span>;\n" +
                " <a name=\"m246\">Ext(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m254\">p</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext.inner\" href=\"#m238\">inner</a> </span>=<span class=\"g\"> <a href=\"#m254\">p</a></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m286\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.ONE\" href=\"#m52\">ONE</a></span>.<span class=\"f\"><a title=\"pkg.Ext.inner\" href=\"#m238\">inner</a></span></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage409Test() {
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
        xml_.append("  return ONE.inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m41\">pkg.Ext</a> {\n" +
                " <span class=\"g\"><a name=\"m52\" title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#m211\">ONE</a>(<span class=\"g\"><a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m113\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"g\">1</span>)<span class=\"t\"><a name=\"m66\">{</a>\n" +
                "  static int <span class=\"g\"><a name=\"m81\">extField</a></span>;\n" +
                "  public int <span class=\"g\"><a name=\"m104\">field</a></span>;\n" +
                "  <a name=\"m113\">public Int(</a>int <a name=\"m128\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a> </span>=<span class=\"g\"> <a href=\"#m128\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m163\">field</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span>)</span>;\n" +
                " <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"g\"><a name=\"m203\">inner</a></span>;\n" +
                " <a name=\"m211\">Ext(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m219\">p</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext.inner\" href=\"#m203\">inner</a> </span>=<span class=\"g\"> <a href=\"#m219\">p</a></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m251\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.ONE\" href=\"#m52\">ONE</a></span>.<span class=\"f\"><a title=\"pkg.Ext.inner\" href=\"#m203\">inner</a></span></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage410Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" static int stfield;\n");
        xml_.append(" int field();\n");
        xml_.append(" static {\n");
        xml_.append("  stfield++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new interfaces(Int) Int(){\n");
        xml_.append("   public int field=++stfield;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " static int <span class=\"g\"><a name=\"m32\">stfield</a></span>;\n" +
                " int <a name=\"m46\">field</a>();\n" +
                " static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Int.stfield\" href=\"#m32\">stfield</a></span>++</span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m89\">pkg.Ext</a> {\n" +
                " static int <a name=\"m111\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m122\">l</a> </span>=<span class=\"f\"> new interfaces(<a title=\"pkg.Int\" href=\"#m10\">Int</a>) <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m151\">{</a>\n" +
                "   public int <span class=\"f\"><span class=\"f\"><a name=\"m167\">field</a></span>=<span class=\"f\">++<span class=\"f\"><a title=\"pkg.Int.stfield\" href=\"#m32\">stfield</a></span></span></span>;\n" +
                "   public int <a name=\"m198\">field</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m167\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m122\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m46\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage411Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
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
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " static int <span class=\"g\"><a name=\"m32\">stfield</a></span>;\n" +
                " int <a name=\"m46\">field</a>();\n" +
                " static {\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Int.stfield\" href=\"#m32\">stfield</a></span>++</span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m89\">pkg.Ext</a> {\n" +
                " static int <a name=\"m111\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m122\">l</a> </span>=<span class=\"f\"> new {} interfaces(<a title=\"pkg.Int\" href=\"#m10\">Int</a>) <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m154\">{</a>\n" +
                "   public int <span class=\"f\"><span class=\"f\"><a name=\"m170\">field</a></span>=<span class=\"f\">++<span class=\"f\"><a title=\"pkg.Int.stfield\" href=\"#m32\">stfield</a></span></span></span>;\n" +
                "   public int <a name=\"m201\">field</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m170\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m122\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m46\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage412Test() {
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
        xml_.append("  while (i < new Int(){}.field){\n");
        xml_.append("   res+=',';\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a></span>=<span class=\"f\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">while</span> (<span class=\"f\"><span class=\"f\"><a href=\"#m93\">i</a> </span>&lt;<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"s\">','</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m93\">i</a></span>++</span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage413Test() {
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
        xml_.append("  if (i < 0){\n");
        xml_.append("  } else if (i < new Int(){}.field){\n");
        xml_.append("   res+=',';\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a></span>=<span class=\"f\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m93\">i</a> </span><a title=\"false\">&lt;</a><span class=\"f\"> 0</span></span>){\n" +
                "  } <span class=\"p\">else if</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m93\">i</a> </span><a title=\"true\">&lt;</a><span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m140\">{</a>}</span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"s\">','</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m93\">i</a></span>++</span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage414Test() {
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
        xml_.append("  return inner.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m42\">pkg.Ext</a> {\n" +
                " static <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"g\"><span class=\"g\"><a name=\"m64\">inner</a> </span>=<span class=\"g\"> new <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m81\">{</a>\n" +
                "  static int <span class=\"g\"><a name=\"m96\">extField</a></span>;\n" +
                "  public int <span class=\"g\"><span class=\"g\"><a name=\"m119\">field</a></span>=<span class=\"g\">1</span></span>;\n" +
                "  public int <a name=\"m141\">field</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m119\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span></span>;\n" +
                " static int <a name=\"m187\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.inner\" href=\"#m64\">inner</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage415Test() {
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
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a></span>=<span class=\"f\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span> (<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m119\">{</a>}</span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span>){\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> 1:\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"s\">','</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m93\">i</a></span>++</span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage416Test() {
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
        xml_.append("  do {\n");
        xml_.append("   res+=',';\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  while (i < new Int(){}.field);\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a></span>=<span class=\"f\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  do {\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"s\">','</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m93\">i</a></span>++</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">while</span> (<span class=\"p\"><span class=\"f\"><a href=\"#m93\">i</a> </span><a title=\"false\">&lt;</a><span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m154\">{</a>}</span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span></span>);\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage417Test() {
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
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"n\"><span class=\"n\"><a name=\"m21\">field</a></span>=<span class=\"n\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m98\">i</a></span>: <span class=\"f\"><a title=\"pkg.Ext..Iterable*1.pkg.Ext..Iterable*1(int...)\" href=\"#m140\">new</a> Iterable&lt;int&gt;(<span class=\"f\">1</span>,<span class=\"f\">2</span>)<span class=\"t\"><a name=\"m123\">{</a>\n" +
                "   int[] <span class=\"f\"><a name=\"m134\">f</a></span>;\n" +
                "   <a name=\"m140\">Iterable(</a>int... <a name=\"m156\">a</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Iterable*1.f\" href=\"#m134\">f</a> </span>=<span class=\"f\"> <a href=\"#m156\">a</a></span></span>;\n" +
                "   }\n" +
                "   public Iterator&lt;int&gt; <a name=\"m200\">iterator</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..Iterable*1..Iterator*1.pkg.Ext..Iterable*1..Iterator*1(int...)\" href=\"#m276\">new</a> Iterator&lt;int&gt;(<span class=\"f\"><a title=\"pkg.Ext..Iterable*1.f\" href=\"#m134\">f</a></span>)<span class=\"t\"><a name=\"m243\">{</a>\n" +
                "     int[] <span class=\"f\"><a name=\"m256\">g</a></span>;\n" +
                "     int <span class=\"f\"><a name=\"m268\">j</a></span>;\n" +
                "     <a name=\"m276\">Iterator(</a>int... <a name=\"m292\">a</a>){\n" +
                "      <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#m256\">g</a> </span>=<span class=\"f\"> <a href=\"#m292\">a</a></span></span>;\n" +
                "     }\n" +
                "     public boolean <a name=\"m336\">hasNext</a>(){\n" +
                "      return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Iterable*1..Iterator*1.j\" href=\"#m268\">j</a> </span>&lt;<span class=\"f\"><span class=\"f\"> <a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#m256\">g</a></span>.<span class=\"f\"><b>length</b></span></span></span>;\n" +
                "     }\n" +
                "     public int <a name=\"m397\">next</a>(){\n" +
                "      return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Iterable*1..Iterator*1.g\" href=\"#m256\">g</a></span><span class=\"f\">[<span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Iterable*1..Iterator*1.j\" href=\"#m268\">j</a></span>++</span>]</span></span>;\n" +
                "     }\n" +
                "    }</span></span>;\n" +
                "   }\n" +
                "  }</span></span>) {\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"f\"><a href=\"#m98\">i</a></span>+<span class=\"f\"><span class=\"s\">\",\"</span></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage418Test() {
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
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"n\"><span class=\"n\"><a name=\"m21\">field</a></span>=<span class=\"n\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m98\">i</a>, int <a name=\"m105\">y</a></span>: <span class=\"f\"><a title=\"pkg.Ext..IterableTable*1.pkg.Ext..IterableTable*1([int,[int)\" href=\"#m194\">new</a> IterableTable&lt;int,int&gt;(<span class=\"f\">new int[]{<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span>,<span class=\"f\">new int[]{<span class=\"f\">3</span>,<span class=\"f\">4</span>}</span>)<span class=\"t\"><a name=\"m165\">{</a>\n" +
                "   int[] <span class=\"f\"><a name=\"m176\">e</a></span>;\n" +
                "   int[] <span class=\"f\"><a name=\"m188\">f</a></span>;\n" +
                "   <a name=\"m194\">IterableTable(</a>int[] <a name=\"m214\">b</a>,int[] <a name=\"m222\">a</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1.e\" href=\"#m176\">e</a> </span>=<span class=\"f\"> <a href=\"#m214\">b</a></span></span>;\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1.f\" href=\"#m188\">f</a> </span>=<span class=\"f\"> <a href=\"#m222\">a</a></span></span>;\n" +
                "   }\n" +
                "   public IteratorTable&lt;int,int&gt; <a name=\"m286\">iteratorTable</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.pkg.Ext..IterableTable*1..IteratorTable*1([int,[int)\" href=\"#m392\">new</a> IteratorTable&lt;int,int&gt;(<span class=\"f\"><a title=\"pkg.Ext..IterableTable*1.e\" href=\"#m176\">e</a></span>,<span class=\"f\"><a title=\"pkg.Ext..IterableTable*1.f\" href=\"#m188\">f</a></span>)<span class=\"t\"><a name=\"m345\">{</a>\n" +
                "     int[] <span class=\"f\"><a name=\"m358\">g</a></span>;\n" +
                "     int[] <span class=\"f\"><a name=\"m372\">h</a></span>;\n" +
                "     int <span class=\"f\"><a name=\"m384\">j</a></span>;\n" +
                "     <a name=\"m392\">IteratorTable(</a>int[] <a name=\"m412\">b</a>,int[] <a name=\"m420\">a</a>){\n" +
                "      <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#m358\">g</a> </span>=<span class=\"f\"> <a href=\"#m412\">b</a></span></span>;\n" +
                "      <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.h\" href=\"#m372\">h</a> </span>=<span class=\"f\"> <a href=\"#m420\">a</a></span></span>;\n" +
                "     }\n" +
                "     public boolean <a name=\"m477\">hasNextPair</a>(){\n" +
                "      return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.j\" href=\"#m384\">j</a> </span>&lt;<span class=\"f\"><span class=\"f\"> <a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#m358\">g</a></span>.<span class=\"f\"><b>length</b></span></span></span>;\n" +
                "     }\n" +
                "     public Pair&lt;int,int&gt; <a name=\"m552\">nextPair</a>(){\n" +
                "      return <span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1([int,[int,int)\" href=\"#m658\">new</a> Pair&lt;int,int&gt;(<span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.g\" href=\"#m358\">g</a></span>,<span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.h\" href=\"#m372\">h</a></span>,<span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1.j\" href=\"#m384\">j</a></span>++</span>)<span class=\"t\"><a name=\"m603\">{</a>\n" +
                "       int[] <span class=\"f\"><a name=\"m618\">k</a></span>;\n" +
                "       int[] <span class=\"f\"><a name=\"m634\">l</a></span>;\n" +
                "       int <span class=\"f\"><a name=\"m648\">m</a></span>;\n" +
                "       <a name=\"m658\">Pair(</a>int[] <a name=\"m669\">b</a>,int[] <a name=\"m677\">a</a>, int <a name=\"m684\">z</a>){\n" +
                "        <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.k\" href=\"#m618\">k</a> </span>=<span class=\"f\"> <a href=\"#m669\">b</a></span></span>;\n" +
                "        <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.l\" href=\"#m634\">l</a> </span>=<span class=\"f\"> <a href=\"#m677\">a</a></span></span>;\n" +
                "        <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#m648\">m</a> </span>=<span class=\"f\"> <a href=\"#m684\">z</a></span></span>;\n" +
                "       }\n" +
                "       public int <a name=\"m760\">getFirst</a>(){\n" +
                "        return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.k\" href=\"#m618\">k</a></span><span class=\"f\">[<span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#m648\">m</a></span>]</span></span>;\n" +
                "       }\n" +
                "       public int <a name=\"m820\">getSecond</a>(){\n" +
                "        return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.l\" href=\"#m634\">l</a></span><span class=\"f\">[<span class=\"f\"><a title=\"pkg.Ext..IterableTable*1..IteratorTable*1..Pair*1.m\" href=\"#m648\">m</a></span>]</span></span>;\n" +
                "       }\n" +
                "      }</span></span>;\n" +
                "     }\n" +
                "    }</span></span>;\n" +
                "   }\n" +
                "  }</span></span>) {\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m105\">y</a></span>+<span class=\"f\"><span class=\"s\">\",\"</span></span></span>+<span class=\"f\"><a href=\"#m98\">i</a></span></span>+<span class=\"f\"><span class=\"s\">\";\"</span></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage419Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" String field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  Int[] a = {new Int(1){\n");
        xml_.append("   public String field;\n");
        xml_.append("   Int(int p){\n");
        xml_.append("    field=\"one\\\"escape\"+p;\n");
        xml_.append("   }\n");
        xml_.append("   public String field(){\n");
        xml_.append("    return field+\"endone\";\n");
        xml_.append("   }\n");
        xml_.append("  },new Int(2,3){\n");
        xml_.append("   public String field;\n");
        xml_.append("   Int(int p, int q){\n");
        xml_.append("    field=\"two\\\"escape\"+p+';'+q;\n");
        xml_.append("   }\n");
        xml_.append("   public String field(){\n");
        xml_.append("    return field+\"endtwo\";\n");
        xml_.append("   }\n");
        xml_.append("  }};\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  int i = 0;\n");
        xml_.append("  while (i < 2){\n");
        xml_.append("   res+=a[i].field()+',';\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " String <a name=\"m28\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m45\">pkg.Ext</a> {\n" +
                " static String <a name=\"m70\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a>[] <span class=\"f\"><span class=\"f\"><a name=\"m83\">a</a> </span>=<span class=\"f\"> {<span class=\"f\"><a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m127\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"f\">1</span>)<span class=\"t\"><a name=\"m98\">{</a>\n" +
                "   public String <span class=\"f\"><a name=\"m117\">field</a></span>;\n" +
                "   <a name=\"m127\">Int(</a>int <a name=\"m135\">p</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m117\">field</a></span>=<span class=\"f\"><span class=\"f\"><span class=\"s\">\"one\\\"escape\"</span></span>+<span class=\"f\"><a href=\"#m135\">p</a></span></span></span>;\n" +
                "   }\n" +
                "   public String <a name=\"m188\">field</a>(){\n" +
                "    return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m117\">field</a></span>+<span class=\"f\"><span class=\"s\">\"endone\"</span></span></span>;\n" +
                "   }\n" +
                "  }</span></span>,<span class=\"f\"><a title=\"pkg.Ext..Int*2.pkg.Ext..Int*2(int,int)\" href=\"#m274\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"f\">2</span>,<span class=\"f\">3</span>)<span class=\"t\"><a name=\"m245\">{</a>\n" +
                "   public String <span class=\"f\"><a name=\"m264\">field</a></span>;\n" +
                "   <a name=\"m274\">Int(</a>int <a name=\"m282\">p</a>, int <a name=\"m289\">q</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*2.field\" href=\"#m264\">field</a></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"s\">\"two\\\"escape\"</span></span>+<span class=\"f\"><a href=\"#m282\">p</a></span></span>+<span class=\"f\"><span class=\"s\">';'</span></span></span>+<span class=\"f\"><a href=\"#m289\">q</a></span></span></span>;\n" +
                "   }\n" +
                "   public String <a name=\"m348\">field</a>(){\n" +
                "    return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*2.field\" href=\"#m264\">field</a></span>+<span class=\"f\"><span class=\"s\">\"endtwo\"</span></span></span>;\n" +
                "   }\n" +
                "  }</span></span>}</span></span>;\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m404\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m420\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">while</span> (<span class=\"f\"><span class=\"f\"><a href=\"#m420\">i</a> </span>&lt;<span class=\"f\"> 2</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m404\">res</a></span>+=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m83\">a</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m420\">i</a></span>]</span></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m28\">field</a>()</span></span>+<span class=\"f\"><span class=\"s\">','</span></span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m420\">i</a></span>++</span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m404\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage420Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int CST = 1;\n");
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
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">CST</a> </span>=<span class=\"g\"> 1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m51\">pkg.Ext</a> {\n" +
                " static String <a name=\"m76\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m90\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\">1</span>){\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m130\">{</a>}</span>.<a title=\"pkg.Int.CST\" href=\"#m34\">CST</a>:\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m90\">res</a> </span>+=<span class=\"f\"> 1</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m90\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage421Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static final int CST = 1;\n");
        xml_.append(" static String m(){\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  switch(2){\n");
        xml_.append("   case CST+CST:\n");
        xml_.append("    res += 1;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">CST</a> </span>=<span class=\"g\"> 1</span></span>;\n" +
                " static String <a name=\"m58\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m72\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\">2</span>){\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> <a title=\"pkg.Ext.CST\" href=\"#m34\">CST</a>+<a title=\"pkg.Ext.CST\" href=\"#m34\">CST</a>:\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m72\">res</a> </span>+=<span class=\"f\"> 1</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m72\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage422Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field = 1;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Int2 {\n");
        xml_.append(" Int f;\n");
        xml_.append(" Int2(Int i) {\n");
        xml_.append("  f = i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Int2(new Int(){}){\n");
        xml_.append("   Int2(Int p){super(p);}\n");
        xml_.append("  }.f.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m40\">pkg.Int2</a> {\n" +
                " <a title=\"pkg.Int\" href=\"#m6\">Int</a> <span class=\"f\"><a name=\"m56\">f</a></span>;\n" +
                " <a name=\"m60\">Int2(</a><a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m69\">i</a>) {\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Int2.f\" href=\"#m56\">f</a> </span>=<span class=\"f\"> <a href=\"#m69\">i</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m94\">pkg.Ext</a> {\n" +
                " static int <a name=\"m116\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int2*1.pkg.Ext..Int2*1(pkg.Int)\" href=\"#m156\">new</a> <a title=\"pkg.Int2\" href=\"#m40\">Int2</a>(<span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m148\">{</a>}</span></span>)<span class=\"t\"><a name=\"m151\">{</a>\n" +
                "   <a name=\"m156\">Int2(</a><a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m165\">p</a>){<span class=\"f\"><a title=\"pkg.Int2.pkg.Int2(pkg.Int)\" href=\"#m60\">super</a>(<span class=\"f\"><a href=\"#m165\">p</a></span>)</span>;}\n" +
                "  }</span></span>.<span class=\"f\"><a title=\"pkg.Int2.f\" href=\"#m56\">f</a></span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage423Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int a:int)->{return 2 * a;}.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"t\">(int <a name=\"m47\">a</a>:int)<a name=\"m53\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m47\">a</a></span></span>;}</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">3</span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage424Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m92\">m</a>(<span class=\"f\"><span class=\"t\">(int <a name=\"m49\">a</a>:int)<a name=\"m55\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m49\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m92\">m</a>(Fct&lt;int,int&gt; <a name=\"m107\">fct</a>,int <a name=\"m115\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m107\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m115\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage425Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(){\n");
        xml_.append("  return staticCall(ExtOther<T>).m((T a:T)->{return (T)(2 * (int)a);},(T)3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExtOther<S> {\n");
        xml_.append(" staticCall S m(Fct<S,S> fct,S a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a>&lt;<a name=\"m14\">T</a>&gt; {\n" +
                " static int <a name=\"m31\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m6\">Ext</a>&lt;int&gt;)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall m()\" href=\"#m88\">m</a>()</span></span>;\n" +
                " }\n" +
                " staticCall <a href=\"#m14\">T</a> <a name=\"m88\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExtOther\" href=\"#m181\">ExtOther</a>&lt;<a href=\"#m14\">T</a>&gt;)</span>.<span class=\"f\"><a title=\"pkg.ExtOther.staticCall m($core.Fct&lt;#S,#S&gt;,#S)\" href=\"#m213\">m</a>(<span class=\"f\"><span class=\"t\">(<a href=\"#m14\">T</a> <a name=\"m131\">a</a>:<a href=\"#m14\">T</a>)<a name=\"m135\">-&gt;</a>{return <span class=\"f\">(<a href=\"#m14\">T</a>)<span class=\"f\">(<span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> (int)<span class=\"f\"><a href=\"#m131\">a</a></span></span></span>)</span></span>;}</span></span>,<span class=\"f\">(<a href=\"#m14\">T</a>)<span class=\"f\">3</span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m181\">pkg.ExtOther</a>&lt;<a name=\"m194\">S</a>&gt; {\n" +
                " staticCall <a href=\"#m194\">S</a> <a name=\"m213\">m</a>(Fct&lt;<a href=\"#m194\">S</a>,<a href=\"#m194\">S</a>&gt; <a name=\"m224\">fct</a>,<a href=\"#m194\">S</a> <a name=\"m230\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m224\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m230\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage426Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Content content = new Content();\n");
        xml_.append("  m((Content a:void)->{a.incr();},content);\n");
        xml_.append("  return content.value;\n");
        xml_.append(" }\n");
        xml_.append(" static void m(Fct<Content,void> fct,Content a){\n");
        xml_.append("  fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Content {\n");
        xml_.append(" int value = 5;\n");
        xml_.append(" void incr(){\n");
        xml_.append("  value++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  <a title=\"pkg.Content\" href=\"#m214\">Content</a> <span class=\"f\"><span class=\"f\"><a name=\"m43\">content</a> </span>=<span class=\"f\"> new <a title=\"pkg.Content\" href=\"#m214\">Content</a>()</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;pkg.Content,void&gt;,pkg.Content)\" href=\"#m152\">m</a>(<span class=\"f\"><span class=\"t\">(<a title=\"pkg.Content\" href=\"#m214\">Content</a> <a name=\"m81\">a</a>:void)<a name=\"m88\">-&gt;</a>{<span class=\"f\"><span class=\"f\"><a href=\"#m81\">a</a></span>.<span class=\"f\"><a title=\"pkg.Content.incr()\" href=\"#m250\">incr</a>()</span></span>;}</span></span>,<span class=\"f\"><a href=\"#m43\">content</a></span>)</span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m43\">content</a></span>.<span class=\"f\"><a title=\"pkg.Content.value\" href=\"#m233\">value</a></span></span>;\n" +
                " }\n" +
                " static void <a name=\"m152\">m</a>(Fct&lt;<a title=\"pkg.Content\" href=\"#m214\">Content</a>,void&gt; <a name=\"m172\">fct</a>,<a title=\"pkg.Content\" href=\"#m214\">Content</a> <a name=\"m184\">a</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m172\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m184\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m214\">pkg.Content</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m233\">value</a> </span>=<span class=\"f\"> 5</span></span>;\n" +
                " void <a name=\"m250\">incr</a>(){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Content.value\" href=\"#m233\">value</a></span>++</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage427Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a:int)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m88\">m</a>(<span class=\"f\"><span class=\"t\">(<a name=\"m45\">a</a>:int)<a name=\"m51\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m45\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m88\">m</a>(Fct&lt;int,int&gt; <a name=\"m103\">fct</a>,int <a name=\"m111\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m103\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m111\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage428Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a:)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m85\">m</a>(<span class=\"f\"><span class=\"t\">(<a name=\"m45\">a</a>:)<a name=\"m48\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m45\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m85\">m</a>(Fct&lt;int,int&gt; <a name=\"m100\">fct</a>,int <a name=\"m108\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m100\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m108\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage429Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m88\">m</a>(<span class=\"f\"><span class=\"t\">(int <a name=\"m49\">a</a>)<a name=\"m51\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m49\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m88\">m</a>(Fct&lt;int,int&gt; <a name=\"m103\">fct</a>,int <a name=\"m111\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m103\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m111\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage430Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m84\">m</a>(<span class=\"f\"><span class=\"t\">(<a name=\"m45\">a</a>)<a name=\"m47\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m45\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m84\">m</a>(Fct&lt;int,int&gt; <a name=\"m99\">fct</a>,int <a name=\"m107\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m99\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m107\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage431Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(){\n");
        xml_.append("  return staticCall(ExtOther<T>).m((T a)->{return (T)(2 * (int)a);},(T)3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExtOther<S> {\n");
        xml_.append(" staticCall S m(Fct<S,S> fct,S a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a>&lt;<a name=\"m14\">T</a>&gt; {\n" +
                " static int <a name=\"m31\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m6\">Ext</a>&lt;int&gt;)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall m()\" href=\"#m88\">m</a>()</span></span>;\n" +
                " }\n" +
                " staticCall <a href=\"#m14\">T</a> <a name=\"m88\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExtOther\" href=\"#m179\">ExtOther</a>&lt;<a href=\"#m14\">T</a>&gt;)</span>.<span class=\"f\"><a title=\"pkg.ExtOther.staticCall m($core.Fct&lt;#S,#S&gt;,#S)\" href=\"#m211\">m</a>(<span class=\"f\"><span class=\"t\">(<a href=\"#m14\">T</a> <a name=\"m131\">a</a>)<a name=\"m133\">-&gt;</a>{return <span class=\"f\">(<a href=\"#m14\">T</a>)<span class=\"f\">(<span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> (int)<span class=\"f\"><a href=\"#m131\">a</a></span></span></span>)</span></span>;}</span></span>,<span class=\"f\">(<a href=\"#m14\">T</a>)<span class=\"f\">3</span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m179\">pkg.ExtOther</a>&lt;<a name=\"m192\">S</a>&gt; {\n" +
                " staticCall <a href=\"#m192\">S</a> <a name=\"m211\">m</a>(Fct&lt;<a href=\"#m192\">S</a>,<a href=\"#m192\">S</a>&gt; <a name=\"m222\">fct</a>,<a href=\"#m192\">S</a> <a name=\"m228\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m222\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m228\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage432Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int) -> {return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m94\">m</a>(<span class=\"f\"><span class=\"t\">(int <a name=\"m49\">a</a>:int) <a name=\"m56\">-&gt;</a> {return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m49\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m94\">m</a>(Fct&lt;int,int&gt; <a name=\"m109\">fct</a>,int <a name=\"m117\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m109\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m117\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage433Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m82\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m44\">a</a><a name=\"m45\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m44\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m82\">m</a>(Fct&lt;int,int&gt; <a name=\"m97\">fct</a>,int <a name=\"m105\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m97\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m105\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage434Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> {return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m84\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m44\">a</a> <a name=\"m46\">-&gt;</a> {return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m44\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m84\">m</a>(Fct&lt;int,int&gt; <a name=\"m99\">fct</a>,int <a name=\"m107\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m99\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m107\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage435Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a) -> 2 * a ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m77\">m</a>(<span class=\"f\"><span class=\"t\">(<a name=\"m45\">a</a>) <a name=\"m48\">-&gt;</a> <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m45\">a</a></span></span></span> </span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m77\">m</a>(Fct&lt;int,int&gt; <a name=\"m92\">fct</a>,int <a name=\"m100\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m92\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m100\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage436Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> 2 * a ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m75\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m44\">a</a> <a name=\"m46\">-&gt;</a> <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m44\">a</a></span></span></span> </span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m75\">m</a>(Fct&lt;int,int&gt; <a name=\"m90\">fct</a>,int <a name=\"m98\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m90\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m98\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage437Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> b -> a * b,2,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m81\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m44\">a</a> <a name=\"m46\">-&gt;</a> <span class=\"f\"><span class=\"t\"><a name=\"m49\">b</a> <a name=\"m51\">-&gt;</a> <span class=\"f\"><span class=\"f\"><a href=\"#m44\">a</a> </span>*<span class=\"f\"> <a href=\"#m49\">b</a></span></span></span></span></span></span>,<span class=\"f\">2</span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m81\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m105\">fct</a>,int <a name=\"m113\">a</a>,int <a name=\"m119\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m105\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m113\">a</a></span>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m119\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage438Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static int <span class=\"g\"><a name=\"m145\">extField</a></span>;\n" +
                " static int <a name=\"m167\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage439Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m89\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <span class=\"g\"><a name=\"m133\">ONE</a></span>;\n" +
                " static int <a name=\"m150\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage440Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m89\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <span class=\"g\"><a name=\"m133\">ONE</a></span>{};\n" +
                " static int <a name=\"m152\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage441Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m106\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m91\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " int <a name=\"m106\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m122\">pkg.Ext</a> {\n" +
                " <span class=\"g\"><a name=\"m133\">ONE</a></span>{};\n" +
                " static int <a name=\"m152\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage442Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a></span>\n" +
                " int <a name=\"m81\">field</a>()<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m97\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span>;\n" +
                "}\n" +
                "enum <a name=\"m114\">pkg.Ext</a> {\n" +
                " <span class=\"g\"><a name=\"m125\">ONE</a></span>{};\n" +
                " static int <a name=\"m144\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage443Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m106\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m91\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " int <a name=\"m106\">field</a>()<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m122\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span>;\n" +
                "}\n" +
                "enum <a name=\"m139\">pkg.Ext</a> {\n" +
                " <span class=\"g\"><a name=\"m150\">ONE</a></span>{};\n" +
                " static int <a name=\"m169\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage444Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\"><a title=\"pkg.Int\" href=\"#m6\">Int</a></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m148\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static int <span class=\"g\"><a name=\"m170\">extField</a></span>;\n" +
                " static int <a name=\"m192\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage445Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\"><a title=\"pkg.Int\" href=\"#m6\">Int</a></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static int <span class=\"g\"><a name=\"m170\">extField</a></span>;\n" +
                " static int <a name=\"m192\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage446Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m156\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static int <span class=\"g\"><a name=\"m178\">extField</a></span>;\n" +
                " static int <a name=\"m200\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage447Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <a name=\"m134\">Ext(</a>){};\n" +
                " static int <a name=\"m155\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage448Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <a name=\"m134\">Ext(</a><span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m160\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m174\">p</a>){};\n" +
                " static int <a name=\"m192\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage449Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <a name=\"m134\">Ext(</a><span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m160\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span><span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m191\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m205\">p</a>){};\n" +
                " static int <a name=\"m223\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage450Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " <a name=\"m134\">Ext(</a><span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m160\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m174\">p</a>, <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m199\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m213\">q</a>){};\n" +
                " static int <a name=\"m231\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage451Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static void <a name=\"m146\">l</a>(){};\n" +
                " static int <a name=\"m165\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage452Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static void <a name=\"m146\">l</a>(<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m184\">p</a>){};\n" +
                " static int <a name=\"m202\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage453Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static void <a name=\"m146\">l</a>(<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span><span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m201\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m215\">p</a>){};\n" +
                " static int <a name=\"m233\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage454Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " static void <a name=\"m146\">l</a>(<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m184\">p</a>, <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m209\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m223\">q</a>){};\n" +
                " static int <a name=\"m241\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage455Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " void <a name=\"m139\">this</a>(<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m166\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m180\">p</a>){};\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m209\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " int <a name=\"m224\">this</a>(<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m251\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m265\">p</a>){return <span class=\"n\">0</span>;};\n" +
                " static int <a name=\"m292\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage456Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m123\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                " operator<a name=\"m142\">+</a> int(<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m170\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span> int <a name=\"m184\">p</a>){return <span class=\"n\">0</span>;};\n" +
                " static int <a name=\"m211\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage457Test() {
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
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m106\">{</a>}</span></span>.<span class=\"n2\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span></span>)</span>\n" +
                "class <a name=\"m122\">pkg.Ext</a> {\n" +
                " static int <a name=\"m144\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage458Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" static final int FIELD=1;\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("@Annot(field=(:int)->{return Int.FIELD;}.call())\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\"><span class=\"t\">(:int)<a name=\"m103\">-&gt;</a>{return <span class=\"n\"><span class=\"n\"><a title=\"pkg.Int\" href=\"#m6\">Int</a></span>.<span class=\"n\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span>;}</span></span>.<span class=\"n2\"><b>call</b>()</span></span></span>)</span>\n" +
                "class <a name=\"m139\">pkg.Ext</a> {\n" +
                " static int <a name=\"m161\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage459Test() {
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
        xml_.append("@Annot(field=(:int)->{return Int.FIELD;}.call())\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">FIELD</a></span>=<span class=\"g\">1</span></span>;\n" +
                "}\n" +
                "annotation <a name=\"m56\">pkg.Annot</a> {\n" +
                " int <a name=\"m73\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m90\">pkg.Ext</a> {\n" +
                "<span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m56\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.field()\" href=\"#m73\">field</a>=<span class=\"n2\"><span class=\"n2\"><span class=\"t\">(:int)<a name=\"m119\">-&gt;</a>{return <span class=\"n\"><span class=\"n\"><a title=\"pkg.Int\" href=\"#m6\">Int</a></span>.<span class=\"n\"><a title=\"pkg.Int.FIELD\" href=\"#m34\">FIELD</a></span></span>;}</span></span>.<span class=\"n2\"><b>call</b>()</span></span></span>)</span>\n" +
                " static int <a name=\"m161\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage460Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  return m(a -> a -> a * #a,2,3);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m6\">Ext</a>)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall m()\" href=\"#m82\">m</a>()</span></span>;\n" +
                " }\n" +
                " staticCall int <a name=\"m82\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.staticCall m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m140\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m98\">a</a> <a name=\"m100\">-&gt;</a> <span class=\"f\"><span class=\"t\"><a name=\"m103\">a</a> <a name=\"m105\">-&gt;</a> <span class=\"f\"><span class=\"f\"><a href=\"#m103\">a</a> </span>*<span class=\"f\"> <a href=\"#m98\">#a</a></span></span></span></span></span></span>,<span class=\"f\">2</span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " staticCall int <a name=\"m140\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m164\">fct</a>,int <a name=\"m172\">a</a>,int <a name=\"m178\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m164\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m172\">a</a></span>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m178\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage461Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(a -> i -> sum2 + i + a + ([i]) + #i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m39\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m54\">sum2</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m75\">i</a> </span>=<span class=\"f\"> 1</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a> </span>&lt;=<span class=\"f\"> 9</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m75\">i</a></span>+=<span class=\"f\"> 2</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m39\">sum</a> </span>+=<span class=\"f\"> <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m185\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m110\">a</a> <a name=\"m112\">-&gt;</a> <span class=\"f\"><span class=\"t\"><a name=\"m115\">i</a> <a name=\"m117\">-&gt;</a> <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m54\">sum2</a> </span>+<span class=\"f\"> <a href=\"#m115\">i</a> </span></span>+<span class=\"f\"> <a href=\"#m110\">a</a> </span></span>+<span class=\"f\"> ([<a href=\"#m75\">i</a>]) </span></span>+<span class=\"f\"> <a href=\"#m75\">#i</a></span></span></span></span></span></span>,<span class=\"f\">2</span>,<span class=\"f\">7</span>)</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m39\">sum</a></span>;\n" +
                " }\n" +
                " static int <a name=\"m185\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m209\">fct</a>,int <a name=\"m217\">a</a>,int <a name=\"m223\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m209\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m217\">a</a></span>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m223\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage462Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  int a = 3;\n");
        xml_.append("  return m(a -> a * #a,2);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m6\">Ext</a>)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall m()\" href=\"#m82\">m</a>()</span></span>;\n" +
                " }\n" +
                " staticCall int <a name=\"m82\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">a</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.staticCall m($core.Fct&lt;int,int&gt;,int)\" href=\"#m146\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m111\">a</a> <a name=\"m113\">-&gt;</a> <span class=\"f\"><span class=\"f\"><a href=\"#m111\">a</a> </span>*<span class=\"f\"> <a href=\"#m93\">#a</a></span></span></span></span>,<span class=\"f\">2</span>)</span>;\n" +
                " }\n" +
                " staticCall int <a name=\"m146\">m</a>(Fct&lt;int,int&gt; <a name=\"m161\">fct</a>,int <a name=\"m169\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m161\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m169\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage463Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum3 = 0;\n");
        xml_.append("  for (int i = 2; i <= 10; i+= 2){\n");
        xml_.append("  sum3+=(:int) -> {int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += m(i -> i -> sum2 + #i + i + ([#i]) + ##i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;}.call();\n");
        xml_.append("  }\n");
        xml_.append("  return sum3;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m39\">sum3</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m60\">i</a> </span>=<span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m60\">i</a> </span>&lt;=<span class=\"f\"> 10</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m60\">i</a></span>+=<span class=\"f\"> 2</span></span>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m39\">sum3</a></span>+=<span class=\"f\"><span class=\"f\"><span class=\"t\">(:int) <a name=\"m99\">-&gt;</a> {int <span class=\"f\"><span class=\"f\"><a name=\"m107\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m122\">sum2</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m143\">i</a> </span>=<span class=\"f\"> 1</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m143\">i</a> </span>&lt;=<span class=\"f\"> 9</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m143\">i</a></span>+=<span class=\"f\"> 2</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m107\">sum</a> </span>+=<span class=\"f\"> <a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m284\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m178\">i</a> <a name=\"m180\">-&gt;</a> <span class=\"f\"><span class=\"t\"><a name=\"m183\">i</a> <a name=\"m185\">-&gt;</a> <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m122\">sum2</a> </span>+<span class=\"f\"> <a href=\"#m178\">#i</a> </span></span>+<span class=\"f\"> <a href=\"#m183\">i</a> </span></span>+<span class=\"f\"> ([<a href=\"#m60\">#i</a>]) </span></span>+<span class=\"f\"> <a href=\"#m143\">##i</a></span></span></span></span></span></span>,<span class=\"f\">2</span>,<span class=\"f\">7</span>)</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m107\">sum</a></span>;}</span></span>.<span class=\"f\"><b>call</b>()</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m39\">sum3</a></span>;\n" +
                " }\n" +
                " static int <a name=\"m284\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m308\">fct</a>,int <a name=\"m316\">a</a>,int <a name=\"m322\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m308\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m316\">a</a></span>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m322\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage464Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  int a = 4;\n");
        xml_.append("  return m(a -> a -> a * #a * ##a,2,3);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m6\">Ext</a>)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall m()\" href=\"#m82\">m</a>()</span></span>;\n" +
                " }\n" +
                " staticCall int <a name=\"m82\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">a</a> </span>=<span class=\"f\"> 4</span></span>;\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.staticCall m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m159\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m111\">a</a> <a name=\"m113\">-&gt;</a> <span class=\"f\"><span class=\"t\"><a name=\"m116\">a</a> <a name=\"m118\">-&gt;</a> <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m116\">a</a> </span>*<span class=\"f\"> <a href=\"#m111\">#a</a> </span></span>*<span class=\"f\"> <a href=\"#m93\">##a</a></span></span></span></span></span></span>,<span class=\"f\">2</span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " staticCall int <a name=\"m159\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m183\">fct</a>,int <a name=\"m191\">a</a>,int <a name=\"m197\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m183\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m191\">a</a></span>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m197\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage465Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int sum3 = 0;\n");
        xml_.append("  for (int i = 2; i <= 10; i+= 2){\n");
        xml_.append("  sum3+=(:int) -> {int sum = 0;\n");
        xml_.append("  int sum2 = 3;\n");
        xml_.append("  for (int i = 1; i <= 9; i+= 2){\n");
        xml_.append("   sum += ([#i])+m(i -> i -> sum2 + #i + i + ([#i]) + ##i,2,7);\n");
        xml_.append("  }\n");
        xml_.append("  return sum;}.call();\n");
        xml_.append("  }\n");
        xml_.append("  return sum3;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,Fct<int,int>> fct,int a,int b){\n");
        xml_.append("  return fct.call(a).call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m39\">sum3</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m60\">i</a> </span>=<span class=\"f\"> 2</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m60\">i</a> </span>&lt;=<span class=\"f\"> 10</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m60\">i</a></span>+=<span class=\"f\"> 2</span></span>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m39\">sum3</a></span>+=<span class=\"f\"><span class=\"f\"><span class=\"t\">(:int) <a name=\"m99\">-&gt;</a> {int <span class=\"f\"><span class=\"f\"><a name=\"m107\">sum</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m122\">sum2</a> </span>=<span class=\"f\"> 3</span></span>;\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m143\">i</a> </span>=<span class=\"f\"> 1</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m143\">i</a> </span>&lt;=<span class=\"f\"> 9</span></span>; <span class=\"f\"><span class=\"f\"><a href=\"#m143\">i</a></span>+=<span class=\"f\"> 2</span></span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m107\">sum</a> </span>+=<span class=\"f\"><span class=\"f\"> ([<a href=\"#m60\">#i</a>])</span>+<span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,$core.Fct&lt;int,int&gt;&gt;,int,int)\" href=\"#m291\">m</a>(<span class=\"f\"><span class=\"t\"><a name=\"m185\">i</a> <a name=\"m187\">-&gt;</a> <span class=\"f\"><span class=\"t\"><a name=\"m190\">i</a> <a name=\"m192\">-&gt;</a> <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m122\">sum2</a> </span>+<span class=\"f\"> <a href=\"#m185\">#i</a> </span></span>+<span class=\"f\"> <a href=\"#m190\">i</a> </span></span>+<span class=\"f\"> ([<a href=\"#m60\">#i</a>]) </span></span>+<span class=\"f\"> <a href=\"#m143\">##i</a></span></span></span></span></span></span>,<span class=\"f\">2</span>,<span class=\"f\">7</span>)</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m107\">sum</a></span>;}</span></span>.<span class=\"f\"><b>call</b>()</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m39\">sum3</a></span>;\n" +
                " }\n" +
                " static int <a name=\"m291\">m</a>(Fct&lt;int,Fct&lt;int,int&gt;&gt; <a name=\"m315\">fct</a>,int <a name=\"m323\">a</a>,int <a name=\"m329\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m315\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m323\">a</a></span>)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m329\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage466Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2,c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m(int,int,int)\" href=\"#m71\">m</a>(<span class=\"f\">2</span>,<span class=\"f\"><a href=\"#m89\">c</a>:<span class=\"f\">5</span></span>,<span class=\"f\"><a href=\"#m83\">b</a>:<span class=\"f\">3</span></span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m71\">m</a>(int <a name=\"m77\">a</a>,int <a name=\"m83\">b</a>,int <a name=\"m89\">c</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m77\">a</a></span>*<span class=\"f\"><a href=\"#m83\">b</a></span></span>+<span class=\"f\"><a href=\"#m89\">c</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage467Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2, c :5, b :3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m(int,int,int)\" href=\"#m75\">m</a>(<span class=\"f\">2</span>,<span class=\"f\"> <a href=\"#m93\">c</a> :<span class=\"f\">5</span></span>,<span class=\"f\"> <a href=\"#m87\">b</a> :<span class=\"f\">3</span></span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m75\">m</a>(int <a name=\"m81\">a</a>,int <a name=\"m87\">b</a>,int <a name=\"m93\">c</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m81\">a</a></span>*<span class=\"f\"><a href=\"#m87\">b</a></span></span>+<span class=\"f\"><a href=\"#m93\">c</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage468Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return ExtTwo.m(2,c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("class pkg.ExtTwo {\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/ExTwo", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExtTwo\" href=\"ExTwo.html#m6\">ExtTwo</a></span>.<span class=\"f\"><a title=\"pkg.ExtTwo.static m(int,int,int)\" href=\"ExTwo.html#m31\">m</a>(<span class=\"f\">2</span>,<span class=\"f\"><a href=\"ExTwo.html#m49\">c</a>:<span class=\"f\">5</span></span>,<span class=\"f\"><a href=\"ExTwo.html#m43\">b</a>:<span class=\"f\">3</span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.ExtTwo</a> {\n" +
                " static int <a name=\"m31\">m</a>(int <a name=\"m37\">a</a>,int <a name=\"m43\">b</a>,int <a name=\"m49\">c</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m37\">a</a></span>*<span class=\"f\"><a href=\"#m43\">b</a></span></span>+<span class=\"f\"><a href=\"#m49\">c</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.getValue(1));
    }
    @Test
    public void coverage469Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int field;\n");
        xml_.append(" Ext(int a,int b,int c,int d){\n");
        xml_.append("  field = a*b+c-d;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext(d:10,b:13,c:4,a:7){\n");
        xml_.append("   Ext(int a,int b,int c,int d){\n");
        xml_.append("    super(a,b,c,d);\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int <span class=\"f\"><a name=\"m21\">field</a></span>;\n" +
                " <a name=\"m29\">Ext(</a>int <a name=\"m37\">a</a>,int <a name=\"m43\">b</a>,int <a name=\"m49\">c</a>,int <a name=\"m55\">d</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m21\">field</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m37\">a</a></span>*<span class=\"f\"><a href=\"#m43\">b</a></span></span>+<span class=\"f\"><a href=\"#m49\">c</a></span></span>-<span class=\"f\"><a href=\"#m55\">d</a></span></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m93\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m104\">e</a> </span>=<span class=\"f\"> <a title=\"pkg.Ext..Ext*1.pkg.Ext..Ext*1(int,int,int,int)\" href=\"#m139\">new</a> <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>(<span class=\"f\"><a href=\"#m165\">d</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a href=\"#m153\">b</a>:<span class=\"f\">13</span></span>,<span class=\"f\"><a href=\"#m159\">c</a>:<span class=\"f\">4</span></span>,<span class=\"f\"><a href=\"#m147\">a</a>:<span class=\"f\">7</span></span>)<span class=\"t\"><a name=\"m134\">{</a>\n" +
                "   <a name=\"m139\">Ext(</a>int <a name=\"m147\">a</a>,int <a name=\"m153\">b</a>,int <a name=\"m159\">c</a>,int <a name=\"m165\">d</a>){\n" +
                "    <span class=\"f\"><a title=\"pkg.Ext.pkg.Ext(int,int,int,int)\" href=\"#m29\">super</a>(<span class=\"f\"><a href=\"#m147\">a</a></span>,<span class=\"f\"><a href=\"#m153\">b</a></span>,<span class=\"f\"><a href=\"#m159\">c</a></span>,<span class=\"f\"><a href=\"#m165\">d</a></span>)</span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m104\">e</a></span>.<span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m21\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage470Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static long m(){\n");
        xml_.append("  Generator g = (Generator)(Fct<long,long>)l -> l * 2;\n");
        xml_.append("  return g.get(a:10);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static long <a name=\"m29\">m</a>(){\n" +
                "  Generator <span class=\"f\"><span class=\"f\"><a name=\"m46\">g</a> </span>=<span class=\"f\"> (Generator)<span class=\"f\">(Fct&lt;long,long&gt;)<span class=\"f\"><span class=\"t\"><a name=\"m77\">l</a> <a name=\"m79\">-&gt;</a> <span class=\"f\"><span class=\"f\"><a href=\"#m77\">l</a> </span>*<span class=\"f\"> 2</span></span></span></span></span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m46\">g</a></span>.<span class=\"f\">get(<span class=\"f\">a:<span class=\"f\">10</span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage471Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[i:0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int[] <span class=\"f\"><span class=\"f\"><a name=\"m23\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " int <a name=\"m41\">this</a>(int <a name=\"m50\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m50\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " void <a name=\"m82\">this</a>(int <a name=\"m91\">i</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><span class=\"n\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"n\">[<span class=\"n\"><a href=\"#m91\">i</a></span>]</span></span>=<span class=\"n\"><b>value</b></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m128\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m139\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m139\">e</a></span><span class=\"f\"><a title=\"pkg.Ext.[](int)\" href=\"#m41\">[</a><span class=\"f\"><a href=\"#m50\">i</a>:<span class=\"f\">0</span></span><a title=\"pkg.Ext.[](int)\" href=\"#m41\">]</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage472Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  e[i:0]=15;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int[] <span class=\"f\"><span class=\"f\"><a name=\"m23\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " int <a name=\"m41\">this</a>(int <a name=\"m50\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"n\">[<span class=\"n\"><a href=\"#m50\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " void <a name=\"m82\">this</a>(int <a name=\"m91\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m91\">i</a></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m128\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m139\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m139\">e</a></span><span class=\"f\"><a title=\"pkg.Ext.[]=(int)\" href=\"#m82\">[</a><span class=\"f\"><a href=\"#m91\">i</a>:<span class=\"f\">0</span></span><a title=\"pkg.Ext.[]=(int)\" href=\"#m82\">]</a></span></span>=<span class=\"f\">15</span></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage473Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  e[i:0]++;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int[] <span class=\"f\"><span class=\"f\"><a name=\"m23\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " int <a name=\"m41\">this</a>(int <a name=\"m50\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m50\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " void <a name=\"m82\">this</a>(int <a name=\"m91\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m91\">i</a></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m128\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m139\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m139\">e</a></span><span class=\"f\"><a title=\"pkg.Ext.[](int)\" href=\"#m41\">[</a><span class=\"f\"><a href=\"#m50\">i</a><a href=\"#m91\">:</a><span class=\"f\">0</span></span><a title=\"pkg.Ext.[](int)\" href=\"#m41\">]</a></span></span>+<a title=\"pkg.Ext.[]=(int)\" href=\"#m82\">+</a></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage474Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  e[i:0]+=15;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int[] <span class=\"f\"><span class=\"f\"><a name=\"m23\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " int <a name=\"m41\">this</a>(int <a name=\"m50\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m50\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " void <a name=\"m82\">this</a>(int <a name=\"m91\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m91\">i</a></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m128\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m139\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m139\">e</a></span><span class=\"f\"><a title=\"pkg.Ext.[](int)\" href=\"#m41\">[</a><span class=\"f\"><a href=\"#m50\">i</a><a href=\"#m91\">:</a><span class=\"f\">0</span></span><a title=\"pkg.Ext.[](int)\" href=\"#m41\">]</a></span></span>+<a title=\"pkg.Ext.[]=(int)\" href=\"#m82\">=</a><span class=\"f\">15</span></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage475Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  e[i :0]++;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int[] <span class=\"f\"><span class=\"f\"><a name=\"m23\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " int <a name=\"m41\">this</a>(int <a name=\"m50\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m50\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " void <a name=\"m82\">this</a>(int <a name=\"m91\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m91\">i</a></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m128\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m139\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m139\">e</a></span><span class=\"f\"><a title=\"pkg.Ext.[](int)\" href=\"#m41\">[</a><span class=\"f\"><a href=\"#m50\">i</a> <a href=\"#m91\">:</a><span class=\"f\">0</span></span><a title=\"pkg.Ext.[](int)\" href=\"#m41\">]</a></span></span>+<a title=\"pkg.Ext.[]=(int)\" href=\"#m82\">+</a></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage476Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  e[i :0]+=15;\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " int[] <span class=\"f\"><span class=\"f\"><a name=\"m23\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " int <a name=\"m41\">this</a>(int <a name=\"m50\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m50\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " void <a name=\"m82\">this</a>(int <a name=\"m91\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m23\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m91\">i</a></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m128\">m</a>(){\n" +
                "  <a title=\"pkg.Ext\" href=\"#m6\">Ext</a> <span class=\"f\"><span class=\"f\"><a name=\"m139\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Ext\" href=\"#m6\">Ext</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m139\">e</a></span><span class=\"f\"><a title=\"pkg.Ext.[](int)\" href=\"#m41\">[</a><span class=\"f\"><a href=\"#m50\">i</a> <a href=\"#m91\">:</a><span class=\"f\">0</span></span><a title=\"pkg.Ext.[](int)\" href=\"#m41\">]</a></span></span>+<a title=\"pkg.Ext.[]=(int)\" href=\"#m82\">=</a><span class=\"f\">15</span></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage477Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int c = 5;\n");
        xml_.append("  return m(2,c:c,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m39\">c</a> </span>=<span class=\"f\"> 5</span></span>;\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m(int,int,int)\" href=\"#m84\">m</a>(<span class=\"f\">2</span>,<span class=\"f\"><a href=\"#m102\">c</a>:<span class=\"f\"><a href=\"#m39\">c</a></span></span>,<span class=\"f\"><a href=\"#m96\">b</a>:<span class=\"f\">3</span></span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m84\">m</a>(int <a name=\"m90\">a</a>,int <a name=\"m96\">b</a>,int <a name=\"m102\">c</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m90\">a</a></span>*<span class=\"f\"><a href=\"#m96\">b</a></span></span>+<span class=\"f\"><a href=\"#m102\">c</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage478Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" $public $static $int field;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredFields()[0].getAnnotations();\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnot</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m51\">pkg.Ex</a> {\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a></span>\n" +
                " $public $static $int <span class=\"g\"><a name=\"m92\">field</a></span>;\n" +
                " $public $static $int <a name=\"m121\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m149\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m51\">Ex</a>)</span>.<span class=\"f\">getDeclaredFields()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations()</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m149\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m149\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m149\">arr</a></span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage479Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" $int field()1;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(MyAnnot).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnot</a> {\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a></span>\n" +
                " $int <a name=\"m50\">field</a>()<span class=\"f2\">1</span>;\n" +
                "}\n" +
                "$public $class <a name=\"m77\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\"m108\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m136\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations()</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m136\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m136\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m136\">arr</a></span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage480Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int field()1;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\"m40\">field</a>()<span class=\"f2\">1</span>;\n" +
                "}\n" +
                "$public $class <a name=\"m67\">pkg.Ex</a> {\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a></span>\n" +
                " $public $static $int <a name=\"m108\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m136\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m67\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations()</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m136\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m136\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m136\">arr</a></span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage481Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(@MyAnnotOne@MyAnnotTwo $int a,@MyAnnotThree@MyAnnotFour $int b){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[][] arr = $class(Ex).getDeclaredMethods()[0].getAnnotationsParameters();\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0].length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[1].length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0][0]) != $class(MyAnnotOne)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0][1]) != $class(MyAnnotTwo)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1][0]) != $class(MyAnnotThree)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1][1]) != $class(MyAnnotFour)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " $public $static $void <a name=\"m206\">catching</a>(<span class=\"f2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span><span class=\"f2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span> $int <a name=\"m243\">a</a>,<span class=\"f2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span><span class=\"f2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span> $int <a name=\"m276\">b</a>){\n" +
                " }\n" +
                " $public $static $int <a name=\"m305\">catching</a>(){\n" +
                "  $Annotation[][] <span class=\"f\"><span class=\"f\"><a name=\"m335\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters()</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage482Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(@MyAnnotOne@MyAnnotTwo $int a,@MyAnnotThree@MyAnnotFour $int b){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[][] arr = $class(Ex).getDeclaredMethods()[0].getAnnotationsParameters($class(MyAnnotOne));\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[1].length != 0i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0][0]) != $class(MyAnnotOne)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " $public $static $void <a name=\"m206\">catching</a>(<span class=\"f2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span><span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span> $int <a name=\"m243\">a</a>,<span class=\"n2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span><span class=\"n2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span> $int <a name=\"m276\">b</a>){\n" +
                " }\n" +
                " $public $static $int <a name=\"m305\">catching</a>(){\n" +
                "  $Annotation[][] <span class=\"f\"><span class=\"f\"><a name=\"m335\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters(<span class=\"f\">$class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 0i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage483Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(@MyAnnotOne@MyAnnotTwo $int a,@MyAnnotThree@MyAnnotFour $int b){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[][] arr = $class(Ex).getDeclaredMethods()[0].getAnnotationsParameters($class(MyAnnotTwo));\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[1].length != 0i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0][0]) != $class(MyAnnotTwo)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " $public $static $void <a name=\"m206\">catching</a>(<span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span><span class=\"f2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span> $int <a name=\"m243\">a</a>,<span class=\"n2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span><span class=\"n2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span> $int <a name=\"m276\">b</a>){\n" +
                " }\n" +
                " $public $static $int <a name=\"m305\">catching</a>(){\n" +
                "  $Annotation[][] <span class=\"f\"><span class=\"f\"><a name=\"m335\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters(<span class=\"f\">$class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 0i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage484Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(@MyAnnotOne@MyAnnotTwo $int a,@MyAnnotThree@MyAnnotFour $int b){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[][] arr = $class(Ex).getDeclaredMethods()[0].getAnnotationsParameters($class(MyAnnotThree));\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0].length != 0){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[1].length != 1){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1][0]) != $class(MyAnnotThree)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " $public $static $void <a name=\"m206\">catching</a>(<span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span><span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span> $int <a name=\"m243\">a</a>,<span class=\"f2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span><span class=\"n2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span> $int <a name=\"m276\">b</a>){\n" +
                " }\n" +
                " $public $static $int <a name=\"m305\">catching</a>(){\n" +
                "  $Annotation[][] <span class=\"f\"><span class=\"f\"><a name=\"m335\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters(<span class=\"f\">$class(<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 0</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage485Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(@MyAnnotOne@MyAnnotTwo $int a,@MyAnnotThree@MyAnnotFour $int b){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[][] arr = $class(Ex).getDeclaredMethods()[0].getAnnotationsParameters($class(MyAnnotFour));\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0].length != 0){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[1].length != 1){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1][0]) != $class(MyAnnotFour)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " $public $static $void <a name=\"m206\">catching</a>(<span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span><span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span> $int <a name=\"m243\">a</a>,<span class=\"n2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span><span class=\"f2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span> $int <a name=\"m276\">b</a>){\n" +
                " }\n" +
                " $public $static $int <a name=\"m305\">catching</a>(){\n" +
                "  $Annotation[][] <span class=\"f\"><span class=\"f\"><a name=\"m335\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters(<span class=\"f\">$class(<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 0</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m335\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage486Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" @MyAnnotThree\n");
        xml_.append(" @MyAnnotFour\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations($class(MyAnnotOne));\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class(MyAnnotOne)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span>\n" +
                " $public $static $int <a name=\"m260\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m288\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations(<span class=\"f\">$class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m288\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage487Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" @MyAnnotThree\n");
        xml_.append(" @MyAnnotFour\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations($class(MyAnnotTwo));\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class(MyAnnotTwo)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span>\n" +
                " $public $static $int <a name=\"m260\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m288\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations(<span class=\"f\">$class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m288\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage488Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" @MyAnnotThree\n");
        xml_.append(" @MyAnnotFour\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations($class(MyAnnotThree));\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class(MyAnnotThree)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span>\n" +
                " $public $static $int <a name=\"m260\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m288\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations(<span class=\"f\">$class(<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m288\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage489Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotThree {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" @MyAnnotThree\n");
        xml_.append(" @MyAnnotFour\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations($class(MyAnnotFour));\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class(MyAnnotFour)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m98\">pkg.MyAnnotThree</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m139\">pkg.MyAnnotFour</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m174\">pkg.Ex</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotThree\" href=\"#m98\">MyAnnotThree</a></span>\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a></span>\n" +
                " $public $static $int <a name=\"m260\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m288\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m174\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations(<span class=\"f\">$class(<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m288\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m288\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotFour\" href=\"#m139\">MyAnnotFour</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage490Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations($class(MyAnnotOne));\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class(MyAnnotOne)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class(MyAnnotOne)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m93\">pkg.Ex</a> {\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " $public $static $int <a name=\"m176\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m204\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m93\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations(<span class=\"f\">$class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m204\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m204\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m204\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m204\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage491Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotOne {\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" @MyAnnotOne\n");
        xml_.append(" @MyAnnotTwo\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredMethods()[0].getAnnotations($class(MyAnnotTwo));\n");
        xml_.append("  $if (arr.length != 2i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0]) != $class(MyAnnotTwo)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[1]) != $class(MyAnnotTwo)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnotOne</a> {\n" +
                "}\n" +
                "$public $annotation <a name=\"m59\">pkg.MyAnnotTwo</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m93\">pkg.Ex</a> {\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " <span class=\"n2\">@<a title=\"pkg.MyAnnotOne\" href=\"#m20\">MyAnnotOne</a></span>\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a></span>\n" +
                " $public $static $int <a name=\"m176\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m204\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m93\">Ex</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations(<span class=\"f\">$class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span>)</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m204\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 2i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m204\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m204\">arr</a></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m204\">arr</a></span><span class=\"f\">[<span class=\"f\">1</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnotTwo\" href=\"#m59\">MyAnnotTwo</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage492Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  for (int i = 0; true ; i++){\n");
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
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <span class=\"f\">for</span> (int <span class=\"f\"><span class=\"f\"><a name=\"m62\">i</a> </span>=<span class=\"f\"> 0</span></span>; <span class=\"f\">true</span> ; <span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a></span>++</span>){\n" +
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
                "public class <a name=\"m248\">pkg.ExTwo</a> {\n" +
                " public String <a name=\"m275\">$toString</a>() {\n" +
                "  return <span class=\"f\">null</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage493Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  for (int i = 0; ; i++){\n");
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
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  for (int <span class=\"f\"><span class=\"f\"><a name=\"m62\">i</a> </span>=<span class=\"f\"> 0</span></span>; ; <span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a></span>++</span>){\n" +
                "   <span class=\"f\">if</span> (<span class=\"f\">!<span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m62\">i</a> </span>%<span class=\"f\"> 2 </span></span>==<span class=\"f\"> 0</span></span>)</span></span>){\n" +
                "    break;\n" +
                "   }\n" +
                "  }\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m126\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <b title=\"$core.String\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m139\">toStr</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m139\">toStr</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m139\">toStr</a> </span><i>+</i><span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m243\">ExTwo</a>()</span></span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m139\">toStr</a> </span>=<span class=\"f\"><span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m243\">ExTwo</a>() </span><i>+</i><span class=\"f\"> <a href=\"#m139\">toStr</a></span></span></span>;\n" +
                "  return <span class=\"f\">6</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m243\">pkg.ExTwo</a> {\n" +
                " public String <a name=\"m270\">$toString</a>() {\n" +
                "  return <span class=\"f\">null</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage494Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static StringBuilder exmeth(){\n");
        xml_.append("  $return $lambda(StringBuilder,$new,$id).call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static StringBuilder <a name=\"m55\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\">$lambda(StringBuilder,$new,$id)</span>.<span class=\"f\"><b>call</b>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage495Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $static().$lambda(ExField,,field).call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExField {\n");
        xml_.append(" $public $static String field = \"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\"m48\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\">$static()</span>.<span class=\"f\"><a title=\"pkg.ExField.field\" href=\"#m168\">$lambda</a>(<a title=\"pkg.ExField\" href=\"#m130\">ExField</a>,,field)</span></span>.<span class=\"f\"><b>call</b>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m130\">pkg.ExField</a> {\n" +
                " $public $static String <span class=\"g\"><span class=\"g\"><a name=\"m168\">field</a> </span>=<span class=\"g\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage496Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $static().$lambda(ExField,,field,String).call(\"value\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExField {\n");
        xml_.append(" $public $static String field = \"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\"m48\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\">$static()</span>.<span class=\"f\"><a title=\"pkg.ExField.field\" href=\"#m182\">$lambda</a>(<a title=\"pkg.ExField\" href=\"#m144\">ExField</a>,,field,String)</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><span class=\"s\">\"value\"</span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m144\">pkg.ExField</a> {\n" +
                " $public $static String <span class=\"g\"><span class=\"g\"><a name=\"m182\">field</a> </span>=<span class=\"g\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage497Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $static().$lambda(ExField,,field).call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExField {\n");
        xml_.append(" $public $static $final String field = \"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static String <a name=\"m48\">exmeth</a>(){\n" +
                "  $return <span class=\"f\"><span class=\"f\"><span class=\"f\">$static()</span>.<span class=\"f\"><a title=\"pkg.ExField.field\" href=\"#m175\">$lambda</a>(<a title=\"pkg.ExField\" href=\"#m130\">ExField</a>,,field)</span></span>.<span class=\"f\"><b>call</b>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m130\">pkg.ExField</a> {\n" +
                " $public $static $final String <span class=\"g\"><span class=\"g\"><a name=\"m175\">field</a> </span>=<span class=\"g\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage498Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true;\n");
        xml_.append("  $var c = $true;\n");
        xml_.append("  b &&&= c;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>;\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m81\">c</a> </span>=<span class=\"f\"> $true</span></span>;\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a> </span><a title=\"true\">&amp;</a>&amp;<a title=\"true\">&amp;</a>=<span class=\"p\"> <a href=\"#m81\">c</a></span></span>;\n" +
                "  $return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage499Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $false;\n");
        xml_.append("  $var c = $true;\n");
        xml_.append("  b &&&= c;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $false</span></span>;\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m82\">c</a> </span>=<span class=\"f\"> $true</span></span>;\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a> </span><a title=\"false\">&amp;</a>&amp;&amp;=<span class=\"n\"> <a href=\"#m82\">c</a></span></span>;\n" +
                "  $return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage500Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $false;\n");
        xml_.append("  $var c = $false;\n");
        xml_.append("  b |||= c;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $false</span></span>;\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m82\">c</a> </span>=<span class=\"f\"> $false</span></span>;\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a> </span><a title=\"false\">|</a>|<a title=\"false\">|</a>=<span class=\"p\"> <a href=\"#m82\">c</a></span></span>;\n" +
                "  $return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage501Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var b = $true;\n");
        xml_.append("  $var c = $false;\n");
        xml_.append("  b |||= c;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\"m46\">exmeth</a>(){\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m63\">b</a> </span>=<span class=\"f\"> $true</span></span>;\n" +
                "  <b title=\"$boolean\">$var</b> <span class=\"f\"><span class=\"f\"><a name=\"m81\">c</a> </span>=<span class=\"f\"> $false</span></span>;\n" +
                "  <span class=\"p\"><span class=\"p\"><a href=\"#m63\">b</a> </span><a title=\"true\">|</a>||=<span class=\"n\"> <a href=\"#m81\">c</a></span></span>;\n" +
                "  $return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage502Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  ExClass f = new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  return (e&&&=f)?\"Vrai\":\"Faux\";\n");
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
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m109\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m109\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"p\"><a href=\"#m67\">e</a></span><a title=\"true\n" +
                "\n" +
                "pkg.ExClass.static false(boolean,pkg.ExClass)\" href=\"#m314\">&amp;</a>&amp;<a title=\"pkg.ExClass.static &amp;&amp;(pkg.ExClass,pkg.ExClass)\" href=\"#m365\">&amp;</a>=<span class=\"f\"><a href=\"#m109\">f</a></span></span>)</span>?<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"n\"><span class=\"s\">\"Faux\"</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m192\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m218\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m250\">true</a>(<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m263\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m263\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m314\">false</a>(<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m328\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m328\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span><a title=\"false\">!=</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m365\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m385\">i</a>, <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m396\">j</a>){\n" +
                "  return <span class=\"p\"><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m385\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m396\">j</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m445\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m465\">i</a>, <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m476\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m465\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span>||<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m476\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m539\">$</a>(<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m549\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m549\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m600\">$</a>(boolean <a name=\"m610\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m624\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m624\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>=<span class=\"f\"><span class=\"p\"><a href=\"#m610\">i</a></span>?<span class=\"f\">1</span>:<span class=\"n\">2</span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m624\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage503Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  ExClass f = new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  return (e&&&=f)?\"Vrai\":\"Faux\";\n");
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
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>=<span class=\"f\">10</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m110\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m110\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"p\"><a href=\"#m67\">e</a></span><a title=\"false\n" +
                "\n" +
                "pkg.ExClass.static false(boolean,pkg.ExClass)\" href=\"#m315\">&amp;</a>&amp;<a title=\"pkg.ExClass.static &amp;&amp;(pkg.ExClass,pkg.ExClass)\" href=\"#m366\">&amp;</a>=<span class=\"n\"><a href=\"#m110\">f</a></span></span>)</span>?<span class=\"n\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"f\"><span class=\"s\">\"Faux\"</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m193\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m219\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m251\">true</a>(<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m264\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m264\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m315\">false</a>(<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m329\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m329\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span><a title=\"true\">!=</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m366\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m386\">i</a>, <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m397\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m386\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span>&amp;&amp;<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m397\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m446\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m466\">i</a>, <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m477\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m466\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span>||<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m477\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m540\">$</a>(<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m550\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m550\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span><a title=\"false\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m601\">$</a>(boolean <a name=\"m611\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <span class=\"n\"><span class=\"n\"><a name=\"m625\">e</a> </span>=<span class=\"n\"> new <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m625\">e</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>=<span class=\"n\"><span class=\"n\"><a href=\"#m611\">i</a></span>?<span class=\"n\">1</span>:<span class=\"n\">2</span></span></span>;\n" +
                "  return <span class=\"n\"><a href=\"#m625\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage504Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  ExClass f = new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  return (e|||=f)?\"Vrai\":\"Faux\";\n");
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
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m109\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m109\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"p\"><a href=\"#m67\">e</a></span><a title=\"true\n" +
                "\n" +
                "pkg.ExClass.static true(boolean,pkg.ExClass)\" href=\"#m250\">|</a>|<a title=\"pkg.ExClass.static ||(pkg.ExClass,pkg.ExClass)\" href=\"#m445\">|</a>=<span class=\"n\"><a href=\"#m109\">f</a></span></span>)</span>?<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"n\"><span class=\"s\">\"Faux\"</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m192\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m218\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m250\">true</a>(<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m263\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m263\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m314\">false</a>(<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m328\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m328\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m365\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m385\">i</a>, <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m396\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m385\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span>&amp;&amp;<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m396\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m445\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m465\">i</a>, <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m476\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m465\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span>||<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m476\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m539\">$</a>(<a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m549\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m549\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <a name=\"m600\">$</a>(boolean <a name=\"m610\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a> <span class=\"n\"><span class=\"n\"><a name=\"m624\">e</a> </span>=<span class=\"n\"> new <a title=\"pkg.ExClass\" href=\"#m192\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m624\">e</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m218\">field</a></span></span>=<span class=\"n\"><span class=\"n\"><a href=\"#m610\">i</a></span>?<span class=\"n\">1</span>:<span class=\"n\">2</span></span></span>;\n" +
                "  return <span class=\"n\"><a href=\"#m624\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage505Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  ExClass f = new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  return (e|||=f)?\"Vrai\":\"Faux\";\n");
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
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>=<span class=\"f\">10</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m110\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m110\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"p\"><a href=\"#m67\">e</a></span><a title=\"false\n" +
                "\n" +
                "pkg.ExClass.static true(boolean,pkg.ExClass)\" href=\"#m251\">|</a>|<a title=\"pkg.ExClass.static ||(pkg.ExClass,pkg.ExClass)\" href=\"#m446\">|</a>=<span class=\"f\"><a href=\"#m110\">f</a></span></span>)</span>?<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"n\"><span class=\"s\">\"Faux\"</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m193\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m219\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m251\">true</a>(<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m264\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m264\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span><a title=\"false\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m315\">false</a>(<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m329\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m329\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m366\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m386\">i</a>, <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m397\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m386\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span>&amp;&amp;<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m397\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m446\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m466\">i</a>, <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m477\">j</a>){\n" +
                "  return <span class=\"p\"><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m466\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span><a title=\"false\">==</a><span class=\"f\">1</span></span><a title=\"false\">|</a><a title=\"true\">|</a><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m477\">j</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m540\">$</a>(<a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m550\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m550\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <a name=\"m601\">$</a>(boolean <a name=\"m611\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m625\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m193\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m625\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m219\">field</a></span></span>=<span class=\"f\"><span class=\"p\"><a href=\"#m611\">i</a></span>?<span class=\"f\">1</span>:<span class=\"n\">2</span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m625\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage506Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  String out = \"\";\n");
        xml_.append("  for (ExClass e:{ new ExClass(10), new ExClass(1)}){\n");
        xml_.append("   ExClass g = e;\n");
        xml_.append("   ExClass f = new ExClass(1);\n");
        xml_.append("   f.field=1;\n");
        xml_.append("   out += (g&&&=f)?\"Vrai\":\"Faux\";\n");
        xml_.append("  }\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public ExClass(int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
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
        xml_.append("  ExClass e = new ExClass(i?1:2);\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m66\">out</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\">for (<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m91\">e</a></span>:<span class=\"f\">{<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\">10</span>)</span>,<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\">1</span>)</span>}</span>){\n" +
                "   <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m141\">g</a> </span>=<span class=\"f\"> <a href=\"#m91\">e</a></span></span>;\n" +
                "   <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m159\">f</a> </span>=<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\">1</span>)</span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m159\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m66\">out</a> </span>+=<span class=\"f\"><span class=\"f\"> (<span class=\"f\"><span class=\"f\"><a href=\"#m141\">g</a></span><a title=\"pkg.ExClass.static false(boolean,pkg.ExClass)\" href=\"#m425\">&amp;</a>&amp;<a title=\"pkg.ExClass.static &amp;&amp;(pkg.ExClass,pkg.ExClass)\" href=\"#m476\">&amp;</a>=<span class=\"f\"><a href=\"#m159\">f</a></span></span>)</span>?<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"f\"><span class=\"s\">\"Faux\"</span></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m66\">out</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m263\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m289\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " <a name=\"m299\">public ExClass(</a>int <a name=\"m318\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a> </span>=<span class=\"f\"> <a href=\"#m318\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m361\">true</a>(<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m374\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m374\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m425\">false</a>(<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m439\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m439\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>!=<span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m476\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m496\">i</a>, <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m507\">j</a>){\n" +
                "  return <span class=\"p\"><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m496\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m507\">j</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m556\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m576\">i</a>, <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m587\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m576\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"n\">1</span></span>||<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m587\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m650\">$</a>(<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m660\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m660\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m711\">$</a>(boolean <a name=\"m721\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m735\">e</a> </span>=<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\"><span class=\"p\"><a href=\"#m721\">i</a></span>?<span class=\"f\">1</span>:<span class=\"n\">2</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m735\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage507Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  String out = \"\";\n");
        xml_.append("  for (ExClass e:{ new ExClass(10), new ExClass(1)}){\n");
        xml_.append("   ExClass g = e;\n");
        xml_.append("   ExClass f = new ExClass(1);\n");
        xml_.append("   f.field=1;\n");
        xml_.append("   out += (g|||=f)?\"Vrai\":\"Faux\";\n");
        xml_.append("  }\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public ExClass(int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
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
        xml_.append("  ExClass e = new ExClass(i?1:2);\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m66\">out</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"f\">for (<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m91\">e</a></span>:<span class=\"f\">{<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\">10</span>)</span>,<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\">1</span>)</span>}</span>){\n" +
                "   <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m141\">g</a> </span>=<span class=\"f\"> <a href=\"#m91\">e</a></span></span>;\n" +
                "   <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m159\">f</a> </span>=<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\">1</span>)</span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m159\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m66\">out</a> </span>+=<span class=\"f\"><span class=\"f\"> (<span class=\"f\"><span class=\"f\"><a href=\"#m141\">g</a></span><a title=\"pkg.ExClass.static true(boolean,pkg.ExClass)\" href=\"#m361\">|</a>|<a title=\"pkg.ExClass.static ||(pkg.ExClass,pkg.ExClass)\" href=\"#m556\">|</a>=<span class=\"f\"><a href=\"#m159\">f</a></span></span>)</span>?<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"n\"><span class=\"s\">\"Faux\"</span></span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m66\">out</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m263\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m289\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " <a name=\"m299\">public ExClass(</a>int <a name=\"m318\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a> </span>=<span class=\"f\"> <a href=\"#m318\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m361\">true</a>(<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m374\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m374\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m425\">false</a>(<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m439\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m439\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m476\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m496\">i</a>, <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m507\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m496\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"n\">1</span></span>&amp;&amp;<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m507\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m556\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m576\">i</a>, <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m587\">j</a>){\n" +
                "  return <span class=\"p\"><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m576\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span><a title=\"false\">==</a><span class=\"f\">1</span></span><a title=\"false\">|</a><a title=\"true\">|</a><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m587\">j</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m650\">$</a>(<a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m660\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m660\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m289\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <a name=\"m711\">$</a>(boolean <a name=\"m721\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m735\">e</a> </span>=<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m299\">new</a> <a title=\"pkg.ExClass\" href=\"#m263\">ExClass</a>(<span class=\"f\"><span class=\"p\"><a href=\"#m721\">i</a></span>?<span class=\"f\">1</span>:<span class=\"n\">2</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m735\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage508Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  boolean v = true;\n");
        xml_.append("  return v &&&= false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">v</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  return <span class=\"p\"><span class=\"p\"><a href=\"#m65\">v</a> </span><a title=\"true\">&amp;</a>&amp;&amp;=<span class=\"f\"> false</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage509Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  boolean v = false;\n");
        xml_.append("  return v &&&= false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">v</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  return <span class=\"p\"><span class=\"p\"><a href=\"#m65\">v</a> </span><a title=\"false\">&amp;</a>&amp;&amp;=<span class=\"n\"> false</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage510Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  boolean v = true;\n");
        xml_.append("  return v |||= false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">v</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  return <span class=\"p\"><span class=\"p\"><a href=\"#m65\">v</a> </span><a title=\"true\">|</a>||=<span class=\"n\"> false</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage511Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static boolean exmeth(){\n");
        xml_.append("  boolean v = false;\n");
        xml_.append("  return v |||= false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">v</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  return <span class=\"p\"><span class=\"p\"><a href=\"#m65\">v</a> </span><a title=\"false\">|</a>||=<span class=\"f\"> false</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage512Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = false;\n");
        xml_.append("  Boolean j = false;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m86\">j</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"false\">?</a>??=<span class=\"n\"> <a href=\"#m86\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage513Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = null;\n");
        xml_.append("  Boolean j = false;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m85\">j</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>?<a title=\"false\">?</a>=<span class=\"p\"> <a href=\"#m85\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage514Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = false;\n");
        xml_.append("  Boolean j = null;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m86\">j</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"false\">?</a>??=<span class=\"n\"> <a href=\"#m86\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage515Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = null;\n");
        xml_.append("  Boolean j = null;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m85\">j</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>?<a title=\"null\">?</a>=<span class=\"p\"> <a href=\"#m85\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage516Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = null;\n");
        xml_.append("  Boolean j = true;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m85\">j</a> </span>=<span class=\"f\"> true</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>?<a title=\"true\">?</a>=<span class=\"p\"> <a href=\"#m85\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage517Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = null;\n");
        xml_.append("  Integer j = 1;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Integer <a name=\"m45\">exmeth</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m85\">j</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>?<a title=\"not null\">?</a>=<span class=\"p\"> <a href=\"#m85\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage518Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = 1;\n");
        xml_.append("  Integer j = null;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Integer <a name=\"m45\">exmeth</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m82\">j</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"not null\">?</a>??=<span class=\"n\"> <a href=\"#m82\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage519Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = 1;\n");
        xml_.append("  Integer j = 1;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Integer <a name=\"m45\">exmeth</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m82\">j</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"not null\">?</a>??=<span class=\"n\"> <a href=\"#m82\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage520Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = null;\n");
        xml_.append("  Integer j = null;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Integer <a name=\"m45\">exmeth</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m85\">j</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>?<a title=\"null\">?</a>=<span class=\"p\"> <a href=\"#m85\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage521Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth1(){\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = null;\n");
        xml_.append("  Integer j = null;\n");
        xml_.append("  return i ???= j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth1");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth1</a>(){\n" +
                "  return <span class=\"f\">1</span>;\n" +
                " }\n" +
                " public static Integer <a name=\"m90\">exmeth</a>(){\n" +
                "  Integer <span class=\"n\"><span class=\"n\"><a name=\"m110\">i</a> </span>=<span class=\"n\"> null</span></span>;\n" +
                "  Integer <span class=\"n\"><span class=\"n\"><a name=\"m130\">j</a> </span>=<span class=\"n\"> null</span></span>;\n" +
                "  return <span class=\"n\"><span class=\"n\"><a href=\"#m110\">i</a> </span>???=<span class=\"n\"> <a href=\"#m130\">j</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage522Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = false;\n");
        xml_.append("  return i ???= false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> false</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"false\">?</a>??=<span class=\"n\"> false</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage523Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Boolean exmeth(){\n");
        xml_.append("  Boolean i = null;\n");
        xml_.append("  return i ???= false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Boolean <a name=\"m45\">exmeth</a>(){\n" +
                "  Boolean <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>??=<span class=\"f\"> false</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage524Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = 1;\n");
        xml_.append("  return i ???= 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Integer <a name=\"m45\">exmeth</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"not null\">?</a>??=<span class=\"n\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage525Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static Integer exmeth(){\n");
        xml_.append("  Integer i = null;\n");
        xml_.append("  return i ???= 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static Integer <a name=\"m45\">exmeth</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m65\">i</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"p\"><a href=\"#m65\">i</a> </span><a title=\"null\">?</a>??=<span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage526Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth(that(t));\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><a name=\"m57\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m57\">t</a></span>=<span class=\"f\">0</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Ex.static exmeth(~int)\" href=\"#m121\">exmeth</a>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m57\">t</a></span>)</span>)</span>;\n" +
                "  return <span class=\"f\"><a href=\"#m57\">t</a></span>;\n" +
                " }\n" +
                " public static void <a name=\"m121\">exmeth</a>(that int <a name=\"m137\">t</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m137\">t</a></span>=<span class=\"f\">8</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage527Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  exmeth($id(Ex,~int),that(t));\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><a name=\"m57\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m57\">t</a></span>=<span class=\"f\">0</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Ex.static exmeth(~int)\" href=\"#m134\">exmeth</a>(<span class=\"f\">$id(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>,~int)</span>,<span class=\"f\">that(<span class=\"f\"><a href=\"#m57\">t</a></span>)</span>)</span>;\n" +
                "  return <span class=\"f\"><a href=\"#m57\">t</a></span>;\n" +
                " }\n" +
                " public static void <a name=\"m134\">exmeth</a>(that int <a name=\"m150\">t</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m150\">t</a></span>=<span class=\"f\">8</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage528Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Compo t;\n");
        xml_.append("  t=null;\n");
        xml_.append("  exmeth($id(Ex,~Compo),that(t));\n");
        xml_.append("  return t.t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that Compo t){\n");
        xml_.append("  Compo c = new Compo();\n");
        xml_.append("  c.t = 8;\n");
        xml_.append("  t=c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Compo {\n");
        xml_.append("  public int t;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Compo\" href=\"#m226\">Compo</a> <span class=\"f\"><a name=\"m59\">t</a></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m59\">t</a></span>=<span class=\"f\">null</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Ex.static exmeth(~pkg.Compo)\" href=\"#m143\">exmeth</a>(<span class=\"f\">$id(<a title=\"pkg.Ex\" href=\"#m13\">Ex</a>,~<a title=\"pkg.Compo\" href=\"#m226\">Compo</a>)</span>,<span class=\"f\">that(<span class=\"f\"><a href=\"#m59\">t</a></span>)</span>)</span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m59\">t</a></span>.<span class=\"f\"><a title=\"pkg.Compo.t\" href=\"#m251\">t</a></span></span>;\n" +
                " }\n" +
                " public static void <a name=\"m143\">exmeth</a>(that <a title=\"pkg.Compo\" href=\"#m226\">Compo</a> <a name=\"m161\">t</a>){\n" +
                "  <a title=\"pkg.Compo\" href=\"#m226\">Compo</a> <span class=\"f\"><span class=\"f\"><a name=\"m173\">c</a> </span>=<span class=\"f\"> new <a title=\"pkg.Compo\" href=\"#m226\">Compo</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m173\">c</a></span>.<span class=\"f\"><a title=\"pkg.Compo.t\" href=\"#m251\">t</a> </span></span>=<span class=\"f\"> 8</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m161\">t</a></span>=<span class=\"f\"><a href=\"#m173\">c</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m226\">pkg.Compo</a> {\n" +
                "  public int <span class=\"f\"><a name=\"m251\">t</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage529Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int i = 0;\n");
        xml_.append("  new Compo(that(i));\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Compo {\n");
        xml_.append(" public int f=15;\n");
        xml_.append(" public Compo(that int p){\n");
        xml_.append("  p = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Compo.pkg.Compo(~int)\" href=\"#m147\">new</a> <a title=\"pkg.Compo\" href=\"#m116\">Compo</a>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m57\">i</a></span>)</span>)</span>;\n" +
                "  return <span class=\"f\"><a href=\"#m57\">i</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m116\">pkg.Compo</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m140\">f</a></span>=<span class=\"f\">15</span></span>;\n" +
                " <a name=\"m147\">public Compo(</a>that int <a name=\"m169\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m169\">p</a> </span>=<span class=\"f\"> <a title=\"pkg.Compo.f\" href=\"#m140\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage530Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 1;\n");
        xml_.append("  that var u = that(t);\n");
        xml_.append("  u += 8;\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">t</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  that <b title=\"int\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m75\">u</a> </span>=<span class=\"f\"> that(<span class=\"f\"><a href=\"#m57\">t</a></span>)</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m75\">u</a> </span>+=<span class=\"f\"> 8</span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m57\">t</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage531Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Compo t = new Compo();\n");
        xml_.append("  t.f=8;\n");
        xml_.append("  Compo u = new Compo();\n");
        xml_.append("  u.f=3;\n");
        xml_.append("  that Compo v = that(t);\n");
        xml_.append("  v += u;\n");
        xml_.append("  return t.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Compo {\n");
        xml_.append(" public int f;\n");
        xml_.append(" operator+ Compo (Compo a, Compo b){\n");
        xml_.append("  Compo c = new Compo();\n");
        xml_.append("  c.f = a.f+b.f;\n");
        xml_.append("  return c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Compo\" href=\"#m187\">Compo</a> <span class=\"f\"><span class=\"f\"><a name=\"m59\">t</a> </span>=<span class=\"f\"> new <a title=\"pkg.Compo\" href=\"#m187\">Compo</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m59\">t</a></span>.<span class=\"f\"><a title=\"pkg.Compo.f\" href=\"#m211\">f</a></span></span>=<span class=\"f\">8</span></span>;\n" +
                "  <a title=\"pkg.Compo\" href=\"#m187\">Compo</a> <span class=\"f\"><span class=\"f\"><a name=\"m93\">u</a> </span>=<span class=\"f\"> new <a title=\"pkg.Compo\" href=\"#m187\">Compo</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m93\">u</a></span>.<span class=\"f\"><a title=\"pkg.Compo.f\" href=\"#m211\">f</a></span></span>=<span class=\"f\">3</span></span>;\n" +
                "  that <a title=\"pkg.Compo\" href=\"#m187\">Compo</a> <span class=\"f\"><span class=\"f\"><a name=\"m132\">v</a> </span>=<span class=\"f\"> that(<span class=\"f\"><a href=\"#m59\">t</a></span>)</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m132\">v</a> </span><a title=\"pkg.Compo.static +(pkg.Compo,pkg.Compo)\" href=\"#m223\">+</a>=<span class=\"f\"> <a href=\"#m93\">u</a></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m59\">t</a></span>.<span class=\"f\"><a title=\"pkg.Compo.f\" href=\"#m211\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m187\">pkg.Compo</a> {\n" +
                " public int <span class=\"f\"><a name=\"m211\">f</a></span>;\n" +
                " operator<a name=\"m223\">+</a> <a title=\"pkg.Compo\" href=\"#m187\">Compo</a> (<a title=\"pkg.Compo\" href=\"#m187\">Compo</a> <a name=\"m238\">a</a>, <a title=\"pkg.Compo\" href=\"#m187\">Compo</a> <a name=\"m247\">b</a>){\n" +
                "  <a title=\"pkg.Compo\" href=\"#m187\">Compo</a> <span class=\"f\"><span class=\"f\"><a name=\"m259\">c</a> </span>=<span class=\"f\"> new <a title=\"pkg.Compo\" href=\"#m187\">Compo</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m259\">c</a></span>.<span class=\"f\"><a title=\"pkg.Compo.f\" href=\"#m211\">f</a> </span></span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> <a href=\"#m238\">a</a></span>.<span class=\"f\"><a title=\"pkg.Compo.f\" href=\"#m211\">f</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m247\">b</a></span>.<span class=\"f\"><a title=\"pkg.Compo.f\" href=\"#m211\">f</a></span></span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m259\">c</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage532Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(field:10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m111\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m111\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.field\" href=\"#m126\">field</a>:<span class=\"f\">10</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.field\" href=\"#m126\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m111\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m126\">field</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage533Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(one:10,two:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int one;\n");
        xml_.append(" int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m120\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m120\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m135\">one</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m145\">two</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m135\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m145\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m120\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m135\">one</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m145\">two</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage534Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(two:10,one:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int one;\n");
        xml_.append(" int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m120\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m120\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m145\">two</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m135\">one</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m135\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m145\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m120\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m135\">one</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m145\">two</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage535Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(one:10,two:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m120\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m120\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m135\">one</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m139\">two</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m135\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m139\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m120\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m135\">one</a></span>,<span class=\"f\"><a name=\"m139\">two</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage536Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(one:10,two:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m120\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m120\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m135\">two</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m135\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m120\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m135\">two</a></span>,<span class=\"f\"><a name=\"m139\">one</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage537Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,Rec> l = $lambda(Rec,new,field);\n");
        xml_.append("  Rec r = l.call(10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,<a title=\"pkg.Rec\" href=\"#m147\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m67\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m147\">Rec</a>,new,<a title=\"pkg.Rec.field\" href=\"#m162\">field</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m147\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m101\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m67\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m101\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.field\" href=\"#m162\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m147\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m162\">field</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage538Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int one;\n");
        xml_.append(" int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m175\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m185\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m160\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m175\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m185\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m160\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m175\">one</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m185\">two</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage539Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m175\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m179\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m160\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m175\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m179\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m160\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m175\">one</a></span>,<span class=\"f\"><a name=\"m179\">two</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage540Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int two;\n");
        xml_.append(" int one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m185\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m175\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m160\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m185\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m175\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m160\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m175\">two</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m185\">one</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage541Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@class pkg.Rec {\n");
        xml_.append(" int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m160\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m179\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m175\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m160\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m179\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m175\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@class <a name=\"m160\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m175\">two</a></span>,<span class=\"f\"><a name=\"m179\">one</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage542Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 7;\n");
        xml_.append("  (that int u:void)->{u=8;}.call(that(t));\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(int t){\n");
        xml_.append("  return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">t</a> </span>=<span class=\"f\"> 7</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"t\">(that int <a name=\"m76\">u</a>:void)<a name=\"m83\">-&gt;</a>{<span class=\"f\"><span class=\"f\"><a href=\"#m76\">u</a></span>=<span class=\"f\">8</span></span>;}</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m57\">t</a></span>)</span>)</span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m57\">t</a></span>;\n" +
                " }\n" +
                " public static void <a name=\"m142\">exmeth</a>(that int <a name=\"m158\">t</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a href=\"#m158\">t</a></span>=<span class=\"n\">8</span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m191\">exmeth</a>(int <a name=\"m202\">t</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><a href=\"#m202\">t</a></span>+<span class=\"n\">8</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage543Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Inner v = new Inner(7);\n");
        xml_.append("  (that Inner u:void)->{u=new Inner(8);}.call(that(v));\n");
        xml_.append("  return v.t;\n");
        xml_.append(" }\n");
        xml_.append(" public static class Inner{\n");
        xml_.append("  int t;\n");
        xml_.append("  Inner(int p){\n");
        xml_.append("   t = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex..Inner\" href=\"#m171\">Inner</a> <span class=\"f\"><span class=\"f\"><a name=\"m59\">v</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m189\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m171\">Inner</a>(<span class=\"f\">7</span>)</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"t\">(that <a title=\"pkg.Ex..Inner\" href=\"#m171\">Inner</a> <a name=\"m91\">u</a>:void)<a name=\"m98\">-&gt;</a>{<span class=\"f\"><span class=\"f\"><a href=\"#m91\">u</a></span>=<span class=\"f\"><a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m189\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m171\">Inner</a>(<span class=\"f\">8</span>)</span></span>;}</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m59\">v</a></span>)</span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m59\">v</a></span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.t\" href=\"#m184\">t</a></span></span>;\n" +
                " }\n" +
                " public static class <a name=\"m171\">Inner</a>{\n" +
                "  int <span class=\"f\"><a name=\"m184\">t</a></span>;\n" +
                "  <a name=\"m189\">Inner(</a>int <a name=\"m199\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex..Inner.t\" href=\"#m184\">t</a> </span>=<span class=\"f\"> <a href=\"#m199\">p</a></span></span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage544Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 7;\n");
        xml_.append("  app((that u:void)->{u=8;},that(t));\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void app(Fct<~int,void> f,that int u){\n");
        xml_.append("  f.call(that(u));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">t</a> </span>=<span class=\"f\"> 7</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Ex.static app($core.Fct&lt;~int,void&gt;,~int)\" href=\"#m137\">app</a>(<span class=\"f\"><span class=\"t\">(that <a name=\"m76\">u</a>:void)<a name=\"m83\">-&gt;</a>{<span class=\"f\"><span class=\"f\"><a href=\"#m76\">u</a></span>=<span class=\"f\">8</span></span>;}</span></span>,<span class=\"f\">that(<span class=\"f\"><a href=\"#m57\">t</a></span>)</span>)</span>;\n" +
                "  return <span class=\"f\"><a href=\"#m57\">t</a></span>;\n" +
                " }\n" +
                " public static void <a name=\"m137\">app</a>(Fct&lt;~int,void&gt; <a name=\"m156\">f</a>,that int <a name=\"m167\">u</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m156\">f</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m167\">u</a></span>)</span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage545Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Inner v = new Inner(7);\n");
        xml_.append("  (that Inner u:that Inner)->{return that(u);}.call(that(v))=new Inner(8);\n");
        xml_.append("  return v.t;\n");
        xml_.append(" }\n");
        xml_.append(" public static class Inner{\n");
        xml_.append("  int t;\n");
        xml_.append("  Inner(int p){\n");
        xml_.append("   t = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex..Inner\" href=\"#m190\">Inner</a> <span class=\"f\"><span class=\"f\"><a name=\"m59\">v</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m208\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m190\">Inner</a>(<span class=\"f\">7</span>)</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"t\">(that <a title=\"pkg.Ex..Inner\" href=\"#m190\">Inner</a> <a name=\"m91\">u</a>:that <a title=\"pkg.Ex..Inner\" href=\"#m190\">Inner</a>)<a name=\"m104\">-&gt;</a>{return <span class=\"f\">that(<span class=\"f\"><a href=\"#m91\">u</a></span>)</span>;}</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m59\">v</a></span>)</span>)</span></span>=<span class=\"f\"><a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m208\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m190\">Inner</a>(<span class=\"f\">8</span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m59\">v</a></span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.t\" href=\"#m203\">t</a></span></span>;\n" +
                " }\n" +
                " public static class <a name=\"m190\">Inner</a>{\n" +
                "  int <span class=\"f\"><a name=\"m203\">t</a></span>;\n" +
                "  <a name=\"m208\">Inner(</a>int <a name=\"m218\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex..Inner.t\" href=\"#m203\">t</a> </span>=<span class=\"f\"> <a href=\"#m218\">p</a></span></span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage546Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static Inner v = new Inner(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (:that Inner)->{return that(v);}.call()=new Inner(8);\n");
        xml_.append("  return v.t;\n");
        xml_.append(" }\n");
        xml_.append(" public static class Inner{\n");
        xml_.append("  int t;\n");
        xml_.append("  Inner(int p){\n");
        xml_.append("   t = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " static <a title=\"pkg.Ex..Inner\" href=\"#m177\">Inner</a> <span class=\"g\"><span class=\"g\"><a name=\"m36\">v</a> </span>=<span class=\"g\"> <a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m195\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m177\">Inner</a>(<span class=\"g\">7</span>)</span></span>;\n" +
                " public static int <a name=\"m73\">exmeth</a>(){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"t\">(:that <a title=\"pkg.Ex..Inner\" href=\"#m177\">Inner</a>)<a name=\"m98\">-&gt;</a>{return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.v\" href=\"#m36\">v</a></span>)</span>;}</span></span>.<span class=\"f\"><b>call</b>()</span></span>=<span class=\"f\"><a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m195\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m177\">Inner</a>(<span class=\"f\">8</span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.v\" href=\"#m36\">v</a></span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.t\" href=\"#m190\">t</a></span></span>;\n" +
                " }\n" +
                " public static class <a name=\"m177\">Inner</a>{\n" +
                "  int <span class=\"g\"><a name=\"m190\">t</a></span>;\n" +
                "  <a name=\"m195\">Inner(</a>int <a name=\"m205\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex..Inner.t\" href=\"#m190\">t</a> </span>=<span class=\"g\"> <a href=\"#m205\">p</a></span></span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage547Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static Inner v = new Inner(7);\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  (:Inner)->{return v;}.call();\n");
        xml_.append("  return v.t;\n");
        xml_.append(" }\n");
        xml_.append(" public static class Inner{\n");
        xml_.append("  int t;\n");
        xml_.append("  Inner(int p){\n");
        xml_.append("   t = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " static <a title=\"pkg.Ex..Inner\" href=\"#m153\">Inner</a> <span class=\"g\"><span class=\"g\"><a name=\"m36\">v</a> </span>=<span class=\"g\"> <a title=\"pkg.Ex..Inner.pkg.Ex..Inner(int)\" href=\"#m171\">new</a> <a title=\"pkg.Ex..Inner\" href=\"#m153\">Inner</a>(<span class=\"g\">7</span>)</span></span>;\n" +
                " public static int <a name=\"m73\">exmeth</a>(){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"t\">(:<a title=\"pkg.Ex..Inner\" href=\"#m153\">Inner</a>)<a name=\"m93\">-&gt;</a>{return <span class=\"f\"><a title=\"pkg.Ex.v\" href=\"#m36\">v</a></span>;}</span></span>.<span class=\"f\"><b>call</b>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.v\" href=\"#m36\">v</a></span>.<span class=\"f\"><a title=\"pkg.Ex..Inner.t\" href=\"#m166\">t</a></span></span>;\n" +
                " }\n" +
                " public static class <a name=\"m153\">Inner</a>{\n" +
                "  int <span class=\"g\"><a name=\"m166\">t</a></span>;\n" +
                "  <a name=\"m171\">Inner(</a>int <a name=\"m181\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ex..Inner.t\" href=\"#m166\">t</a> </span>=<span class=\"g\"> <a href=\"#m181\">p</a></span></span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage548Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(field:10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m115\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m115\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.field\" href=\"#m130\">field</a>:<span class=\"f\">10</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.field\" href=\"#m130\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m115\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m130\">field</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage549Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(one:10,two:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int one;\n");
        xml_.append(" int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m124\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m124\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m149\">two</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m149\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m124\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m139\">one</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m149\">two</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage550Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(two:10,one:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int one;\n");
        xml_.append(" int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m124\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m124\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m149\">two</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m149\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m124\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m139\">one</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m149\">two</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage551Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(one:10,two:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m124\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m124\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m143\">two</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m139\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m143\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m124\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m139\">one</a></span>,<span class=\"f\"><a name=\"m143\">two</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage552Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Rec r = new Rec(one:10,two:12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  <a title=\"pkg.Rec\" href=\"#m124\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m58\">r</a> </span>=<span class=\"f\"> new <a title=\"pkg.Rec\" href=\"#m124\">Rec</a>(<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m143\">one</a>:<span class=\"f\">10</span></span>,<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m139\">two</a>:<span class=\"f\">12</span></span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m143\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m58\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m139\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m124\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m139\">two</a></span>,<span class=\"f\"><a name=\"m143\">one</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage553Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,Rec> l = $lambda(Rec,new,field);\n");
        xml_.append("  Rec r = l.call(10);\n");
        xml_.append("  return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,<a title=\"pkg.Rec\" href=\"#m151\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m67\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m151\">Rec</a>,new,<a title=\"pkg.Rec.field\" href=\"#m166\">field</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m151\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m101\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m67\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m101\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.field\" href=\"#m166\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m151\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m166\">field</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage554Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int one;\n");
        xml_.append(" int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m179\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m189\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m164\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m179\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m189\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m164\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m179\">one</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m189\">two</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage555Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m179\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m183\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m164\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m179\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m183\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m164\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m179\">one</a></span>,<span class=\"f\"><a name=\"m183\">two</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage556Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int two;\n");
        xml_.append(" int one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m189\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m179\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m164\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m189\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m179\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m164\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><a name=\"m179\">two</a></span>;\n" +
                " int <span class=\"f\"><a name=\"m189\">one</a></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage557Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth (){\n");
        xml_.append("  Fct<int,int,Rec> l = $lambda(Rec,new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@interface pkg.Rec {\n");
        xml_.append(" int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnly(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a> (){\n" +
                "  Fct&lt;int,int,<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m71\">l</a> </span>=<span class=\"f\"> $lambda(<a title=\"pkg.Rec\" href=\"#m164\">Rec</a>,new,<a title=\"pkg.Rec.one\" href=\"#m183\">one</a>,<a title=\"pkg.Rec.two\" href=\"#m179\">two</a>)</span></span>;\n" +
                "  <a title=\"pkg.Rec\" href=\"#m164\">Rec</a> <span class=\"f\"><span class=\"f\"><a name=\"m107\">r</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m71\">l</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">10</span>,<span class=\"f\">12</span>)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.one\" href=\"#m183\">one</a></span></span>-<span class=\"f\"><span class=\"f\"><a href=\"#m107\">r</a></span>.<span class=\"f\"><a title=\"pkg.Rec.two\" href=\"#m179\">two</a></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "@interface <a name=\"m164\">pkg.Rec</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m179\">two</a></span>,<span class=\"f\"><a name=\"m183\">one</a></span></span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage558Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(( a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static int <a name=\"m28\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ext.static m($core.Fct&lt;int,int&gt;,int)\" href=\"#m85\">m</a>(<span class=\"f\"><span class=\"t\">( <a name=\"m46\">a</a>)<a name=\"m48\">-&gt;</a>{return <span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m46\">a</a></span></span>;}</span></span>,<span class=\"f\">3</span>)</span>;\n" +
                " }\n" +
                " static int <a name=\"m85\">m</a>(Fct&lt;int,int&gt; <a name=\"m100\">fct</a>,int <a name=\"m108\">a</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m100\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m108\">a</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage559Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return m"+(char)225+"();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int m"+(char)225+"(){\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\"m15\">pkg.Ex</a> {\n" +
                " $public $static $int <a name=\"m46\">catching</a>(){\n" +
                "  $return <span class=\"f\"><a title=\"pkg.Ex.$static m&#225;()\" href=\"#m99\">m&#225;</a>()</span>;\n" +
                " }\n" +
                " $public $static $int <a name=\"m99\">m&#225;</a>(){\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage560Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExCaller<int>).method();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<S> {\n");
        xml_.append(" public staticCall S method(){\n");
        xml_.append("  ExClass<S> e = new ExClass<S>();\n");
        xml_.append("  e.field = (S)5;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExCaller\" href=\"#m117\">ExCaller</a>&lt;int&gt;)</span>.<span class=\"f\"><a title=\"pkg.ExCaller.staticCall method()\" href=\"#m156\">method</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m117\">pkg.ExCaller</a>&lt;<a name=\"m130\">S</a>&gt; {\n" +
                " public staticCall <a href=\"#m130\">S</a> <a name=\"m156\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m249\">ExClass</a>&lt;<a href=\"#m130\">S</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m179\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m249\">ExClass</a>&lt;<a href=\"#m130\">S</a>&gt;()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m179\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m276\">field</a> </span></span>=<span class=\"f\"> (<a href=\"#m130\">S</a>)<span class=\"f\">5</span></span></span>;\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExClass.static $(#T,pkg.ExClass&lt;#T&gt;)\" href=\"#m300\"> </a><a href=\"#m179\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m249\">pkg.ExClass</a>&lt;<a name=\"m261\">T</a>&gt; {\n" +
                " public <a href=\"#m261\">T</a> <span class=\"f\"><a name=\"m276\">field</a></span>;\n" +
                " public static <a href=\"#m261\">T</a> <a name=\"m300\">$</a>(<a title=\"pkg.ExClass\" href=\"#m249\">ExClass</a>&lt;<a href=\"#m261\">T</a>&gt; <a name=\"m313\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m313\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m276\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage561Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExCaller<int>).method();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<S> {\n");
        xml_.append(" public staticCall S method(){\n");
        xml_.append("  Fct<S> fct = (:S) -> new ExClass<S>((S)5);\n");
        xml_.append("  return fct.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public ExClass(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExCaller\" href=\"#m117\">ExCaller</a>&lt;int&gt;)</span>.<span class=\"f\"><a title=\"pkg.ExCaller.staticCall method()\" href=\"#m156\">method</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m117\">pkg.ExCaller</a>&lt;<a name=\"m130\">S</a>&gt; {\n" +
                " public staticCall <a href=\"#m130\">S</a> <a name=\"m156\">method</a>(){\n" +
                "  Fct&lt;<a href=\"#m130\">S</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m175\">fct</a> </span>=<span class=\"f\"><span class=\"t\"> (:<a href=\"#m130\">S</a>) <a name=\"m186\">-&gt;</a> <span class=\"f\"><a title=\"pkg.ExClass.static $(#T,pkg.ExClass&lt;#T&gt;)\" href=\"#m337\"> </a><a title=\"pkg.ExClass.pkg.ExClass(#T)\" href=\"#m285\">new</a> <a title=\"pkg.ExClass\" href=\"#m250\">ExClass</a>&lt;<a href=\"#m130\">S</a>&gt;(<span class=\"f\">(<a href=\"#m130\">S</a>)<span class=\"f\">5</span></span>)</span></span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m175\">fct</a></span>.<span class=\"f\"><b>call</b>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m250\">pkg.ExClass</a>&lt;<a name=\"m262\">T</a>&gt; {\n" +
                " public <a href=\"#m262\">T</a> <span class=\"f\"><a name=\"m277\">field</a></span>;\n" +
                " <a name=\"m285\">public ExClass(</a><a href=\"#m262\">T</a> <a name=\"m302\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m277\">field</a></span>=<span class=\"f\"><a href=\"#m302\">p</a></span></span>;\n" +
                " }\n" +
                " public static <a href=\"#m262\">T</a> <a name=\"m337\">$</a>(<a title=\"pkg.ExClass\" href=\"#m250\">ExClass</a>&lt;<a href=\"#m262\">T</a>&gt; <a name=\"m350\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m350\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m277\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage562Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  return e?\"Vrai\":\"Faux\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m173\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m205\">?</a><span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m147\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m173\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m205\">$</a>(<a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a> <a name=\"m215\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m215\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m173\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m266\">true</a>(<a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a> <a name=\"m279\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m279\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m173\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m330\">false</a>(<a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a> <a name=\"m344\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m344\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m173\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage563Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  return bool(e,\"Vrai\",\"Faux\"+1/0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m211\">bool</a>(<span class=\"f\"><a href=\"#m67\">e</a></span>,<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>,<span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m153\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m179\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m211\">$</a>(<a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <a name=\"m221\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m221\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m272\">true</a>(<a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <a name=\"m285\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m285\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m336\">false</a>(<a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <a name=\"m350\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m350\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage564Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  if (e){\n");
        xml_.append("   return \"Vrai\";\n");
        xml_.append("  }\n");
        xml_.append("  return \"Faux\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m170\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m170\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m196\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"f\"><a href=\"#m67\">e</a></span>)<a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m228\">{</a>\n" +
                "   return <span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m170\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m196\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m228\">$</a>(<a title=\"pkg.ExClass\" href=\"#m170\">ExClass</a> <a name=\"m238\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m238\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m196\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m289\">true</a>(<a title=\"pkg.ExClass\" href=\"#m170\">ExClass</a> <a name=\"m302\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m302\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m196\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m353\">false</a>(<a title=\"pkg.ExClass\" href=\"#m170\">ExClass</a> <a name=\"m367\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m367\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m196\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage565Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  boolean res = e;\n");
        xml_.append("  if (res){\n");
        xml_.append("   return \"Vrai\";\n");
        xml_.append("  }\n");
        xml_.append("  return \"Faux\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m191\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m191\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m217\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  boolean <span class=\"p\"><span class=\"f\"><a name=\"m109\">res</a> </span>=<span class=\"f\"><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m249\"> </a> <a href=\"#m67\">e</a></span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><a href=\"#m109\">res</a></span>){\n" +
                "   return <span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m191\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m217\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m249\">$</a>(<a title=\"pkg.ExClass\" href=\"#m191\">ExClass</a> <a name=\"m259\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m259\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m217\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m310\">true</a>(<a title=\"pkg.ExClass\" href=\"#m191\">ExClass</a> <a name=\"m323\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m323\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m217\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m374\">false</a>(<a title=\"pkg.ExClass\" href=\"#m191\">ExClass</a> <a name=\"m388\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m388\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m217\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage566Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  for (;e;){\n");
        xml_.append("   return \"Vrai\";\n");
        xml_.append("  }\n");
        xml_.append("  return \"Faux\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m173\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m173\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m199\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"p\">for</span> (;<span class=\"f\"><a href=\"#m67\">e</a></span>;)<a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m231\">{</a>\n" +
                "   return <span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m173\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m199\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m231\">$</a>(<a title=\"pkg.ExClass\" href=\"#m173\">ExClass</a> <a name=\"m241\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m241\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m199\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m292\">true</a>(<a title=\"pkg.ExClass\" href=\"#m173\">ExClass</a> <a name=\"m305\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m305\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m199\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m356\">false</a>(<a title=\"pkg.ExClass\" href=\"#m173\">ExClass</a> <a name=\"m370\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m370\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m199\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage567Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  int[] arr = {0,1,2};\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  return arr[e];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m62\">arr</a> </span>=<span class=\"f\"> {<span class=\"f\">0</span>,<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m87\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m62\">arr</a></span><span class=\"f\">[<span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m195\"> </a><a href=\"#m87\">e</a></span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m141\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m167\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m195\">$</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m205\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m205\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m253\">true</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m266\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m266\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m317\">false</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m331\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m331\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage568Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Integer nb = null;\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  nb ??= ex;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  Integer <span class=\"f\"><span class=\"f\"><a name=\"m64\">nb</a> </span>=<span class=\"f\"> null</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m149\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m85\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m149\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"p\"><a href=\"#m64\">nb</a> </span><a title=\"null\">?</a><a title=\"not null\">?</a>=<span class=\"p\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m203\"> </a> <a href=\"#m85\">ex</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m64\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m149\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m175\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m203\">$</a>(<a title=\"pkg.ExClass\" href=\"#m149\">ExClass</a> <a name=\"m213\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m213\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m175\">field</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m261\">true</a>(<a title=\"pkg.ExClass\" href=\"#m149\">ExClass</a> <a name=\"m274\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m274\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m175\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m325\">false</a>(<a title=\"pkg.ExClass\" href=\"#m149\">ExClass</a> <a name=\"m339\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m339\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m175\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage569Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  int nb = 0;\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  nb += ex;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append(" operator+ ExClass (int i, ExClass h){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = i + h.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m60\">nb</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m78\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m60\">nb</a> </span><a title=\"pkg.ExClass.static +(int,pkg.ExClass)\" href=\"#m239\">+</a>=<a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m195\"> </a><span class=\"f\"> <a href=\"#m78\">ex</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m60\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m141\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m167\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m195\">$</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m205\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m205\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m239\">+</a> <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> (int <a name=\"m254\">i</a>, <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m265\">h</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m279\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m279\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m254\">i</a> </span>+<span class=\"f\"><span class=\"f\"> <a href=\"#m265\">h</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m279\">e</a></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m361\">true</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m374\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m374\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m425\">false</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m439\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m439\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage570Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  int nb = 0;\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  nb += ex;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m60\">nb</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m78\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m60\">nb</a> </span>+=<span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m195\"> </a> <a href=\"#m78\">ex</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m60\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m141\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m167\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m195\">$</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m205\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m205\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m253\">true</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m266\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m266\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m317\">false</a>(<a title=\"pkg.ExClass\" href=\"#m141\">ExClass</a> <a name=\"m331\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m331\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m167\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage571Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  nb += ex;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m98\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m68\">nb</a> <a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m307\"> </a></span>+=<a title=\"pkg.ExClass.static $(pkg.ExClass,int)\" href=\"#m219\"> </a><span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m307\"> </a> <a href=\"#m98\">ex</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m68\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m161\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m187\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <a name=\"m219\">$</a>(int <a name=\"m225\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m239\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m239\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m187\">field</a> </span></span>=<span class=\"f\"> <a href=\"#m225\">i</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m239\">e</a></span>;\n" +
                " }\n" +
                " public static int <a name=\"m307\">$</a>(<a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <a name=\"m317\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m317\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m187\">field</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m365\">true</a>(<a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <a name=\"m378\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m378\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m187\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m429\">false</a>(<a title=\"pkg.ExClass\" href=\"#m161\">ExClass</a> <a name=\"m443\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m443\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m187\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage572Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  int nb = 0;\n");
        xml_.append("  ++nb;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m60\">nb</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">++<span class=\"f\"><a href=\"#m60\">nb</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m60\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage573Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  ++nb;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\">++<a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m273\"> </a><a title=\"pkg.ExClass.static $(pkg.ExClass,int)\" href=\"#m185\"> </a><span class=\"f\"><a href=\"#m68\">nb</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m68\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m127\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m153\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <a name=\"m185\">$</a>(int <a name=\"m191\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m205\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m205\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m153\">field</a> </span></span>=<span class=\"f\"> <a href=\"#m191\">i</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m205\">e</a></span>;\n" +
                " }\n" +
                " public static int <a name=\"m273\">$</a>(<a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <a name=\"m283\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m283\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m153\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage574Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  nb++;\n");
        xml_.append("  return nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m68\">nb</a></span><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m273\"> </a><a title=\"pkg.ExClass.static $(pkg.ExClass,int)\" href=\"#m185\"> </a>++</span>;\n" +
                "  return <span class=\"f\"><a href=\"#m68\">nb</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m127\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m153\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <a name=\"m185\">$</a>(int <a name=\"m191\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m205\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m205\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m153\">field</a> </span></span>=<span class=\"f\"> <a href=\"#m191\">i</a></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m205\">e</a></span>;\n" +
                " }\n" +
                " public static int <a name=\"m273\">$</a>(<a title=\"pkg.ExClass\" href=\"#m127\">ExClass</a> <a name=\"m283\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m283\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m153\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage575Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  int sum = nb + ex;\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m167\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m64\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m167\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m167\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m94\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m167\">ExClass</a>()</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m120\">sum</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m64\">nb</a> <a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m221\"> </a></span>+<span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m221\"> </a> <a href=\"#m94\">ex</a></span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m120\">sum</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m167\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m193\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m221\">$</a>(<a title=\"pkg.ExClass\" href=\"#m167\">ExClass</a> <a name=\"m231\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m231\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m193\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage576Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  ExTwo ex = new ExTwo();\n");
        xml_.append("  return nb + ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" operator+ ExClass (ExClass h, int i){\n");
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
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a> <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExTwo\" href=\"#m308\">ExTwo</a> <span class=\"f\"><span class=\"f\"><a name=\"m96\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m308\">ExTwo</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m68\">nb</a> </span><a title=\"pkg.ExClass.static +(pkg.ExClass,int)\" href=\"#m194\">+</a><span class=\"f\"><a title=\"pkg.ExTwo.static $(int,pkg.ExTwo)\" href=\"#m360\"> </a> <a href=\"#m96\">ex</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m150\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m176\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " operator<a name=\"m194\">+</a> <a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a> (<a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a> <a name=\"m213\">h</a>, int <a name=\"m220\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m234\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m150\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m234\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m176\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m220\">i</a> </span>+<span class=\"f\"><span class=\"f\"> <a href=\"#m213\">h</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m176\">field</a></span></span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m234\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m308\">pkg.ExTwo</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m332\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m360\">$</a>(<a title=\"pkg.ExTwo\" href=\"#m308\">ExTwo</a> <a name=\"m368\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m368\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m332\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage577Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static boolean method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  return nb <= ex;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static boolean <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m155\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m155\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m155\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m98\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m155\">ExClass</a>()</span></span>;\n" +
                "  return <span class=\"p\"><span class=\"f\"><a href=\"#m68\">nb</a> <a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m209\"> </a></span><a title=\"true\">&lt;=</a><span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m209\"> </a> <a href=\"#m98\">ex</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m155\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m181\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m209\">$</a>(<a title=\"pkg.ExClass\" href=\"#m155\">ExClass</a> <a name=\"m219\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m219\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m181\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage578Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
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
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a> <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExTwo\" href=\"#m310\">ExTwo</a> <span class=\"f\"><span class=\"f\"><a name=\"m96\">ex</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExTwo\" href=\"#m310\">ExTwo</a>()</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m68\">nb</a> </span><a title=\"pkg.ExClass.static &lt;=(pkg.ExClass,int)\" href=\"#m195\">&lt;=</a><span class=\"f\"><a title=\"pkg.ExTwo.static $(int,pkg.ExTwo)\" href=\"#m362\"> </a> <a href=\"#m96\">ex</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m151\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m177\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " operator<a name=\"m195\">&lt;=</a> <a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a> (<a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a> <a name=\"m215\">h</a>, int <a name=\"m222\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m236\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m151\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m236\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m177\">field</a> </span></span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m222\">i</a> </span>+<span class=\"f\"><span class=\"f\"> <a href=\"#m215\">h</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m177\">field</a></span></span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m236\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m310\">pkg.ExTwo</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m334\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m362\">$</a>(<a title=\"pkg.ExTwo\" href=\"#m310\">ExTwo</a> <a name=\"m370\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m370\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExTwo.field\" href=\"#m334\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage579Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  return -nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m116\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m64\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m116\">ExClass</a>()</span></span>;\n" +
                "  return <span class=\"f\">-<span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m170\"> </a><a href=\"#m64\">nb</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m116\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m142\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m170\">$</a>(<a title=\"pkg.ExClass\" href=\"#m116\">ExClass</a> <a name=\"m180\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m180\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m142\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage580Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  return ~nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static int <a name=\"m44\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m116\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m64\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m116\">ExClass</a>()</span></span>;\n" +
                "  return <span class=\"f\">~<span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m170\"> </a><a href=\"#m64\">nb</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m116\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m142\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m170\">$</a>(<a title=\"pkg.ExClass\" href=\"#m116\">ExClass</a> <a name=\"m180\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m180\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m142\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage581Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static boolean method(){\n");
        xml_.append("  ExClass nb = new ExClass();\n");
        xml_.append("  return !nb;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static boolean <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m120\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">nb</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m120\">ExClass</a>()</span></span>;\n" +
                "  return <span class=\"p\"><a title=\"true\">!</a><span class=\"f\"><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m178\"> </a><a href=\"#m68\">nb</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m120\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m146\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m178\">$</a>(<a title=\"pkg.ExClass\" href=\"#m120\">ExClass</a> <a name=\"m188\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m188\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m146\">field</a></span></span><a title=\"false\">==</a><span class=\"f\">0</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage582Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
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
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m109\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m109\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"p\"><a href=\"#m67\">e</a></span><a title=\"true\n" +
                "\n" +
                "pkg.ExClass.static false(boolean,pkg.ExClass)\" href=\"#m310\">&amp;</a><a title=\"pkg.ExClass.static &amp;&amp;(pkg.ExClass,pkg.ExClass)\" href=\"#m361\">&amp;</a><a title=\"pkg.ExClass.static $(pkg.ExClass,boolean)\" href=\"#m596\"> </a><span class=\"f\"><a href=\"#m109\">f</a></span></span><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m535\">?</a><span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>:<span class=\"n\"><span class=\"s\">\"Faux\"</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m188\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m214\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m246\">true</a>(<a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m259\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m259\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m310\">false</a>(<a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m324\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m324\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span><a title=\"false\">!=</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " operator<a name=\"m361\">&amp;&amp;</a> boolean (<a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m381\">i</a>, <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m392\">j</a>){\n" +
                "  return <span class=\"p\"><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m381\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m392\">j</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span></span>;\n" +
                " }\n" +
                " operator<a name=\"m441\">||</a> boolean (<a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m461\">i</a>, <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m472\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m461\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span>==<span class=\"n\">1</span></span>||<span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m472\">j</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span>==<span class=\"n\">1</span></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m535\">$</a>(<a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m545\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m545\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <a name=\"m596\">$</a>(boolean <a name=\"m606\">i</a>){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m620\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m188\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m620\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m214\">field</a></span></span>=<span class=\"f\"><span class=\"p\"><a href=\"#m606\">i</a></span>?<span class=\"f\">1</span>:<span class=\"n\">2</span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m620\">e</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage583Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static boolean method(){\n");
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
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static boolean <a name=\"m48\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m242\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m68\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m242\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m68\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m268\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#m353\">ExClass2</a> <span class=\"f\"><span class=\"f\"><a name=\"m111\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass2\" href=\"#m353\">ExClass2</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m111\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m380\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"p\"><a title=\"pkg.Apply.static and(boolean,boolean)\" href=\"#m182\">and</a>(<span class=\"f\"><a title=\"pkg.ExClass.static $(boolean,pkg.ExClass)\" href=\"#m300\"> </a><a href=\"#m68\">e</a></span>,<span class=\"f\"><a title=\"pkg.ExClass2.static $(boolean,pkg.ExClass2)\" href=\"#m412\"> </a><a href=\"#m111\">f</a></span>)</span>;\n" +
                " }\n" +
                " static boolean <a name=\"m182\">and</a>(boolean <a name=\"m194\">a</a>, boolean <a name=\"m205\">b</a>){\n" +
                "  return <span class=\"p\"><span class=\"p\"><a href=\"#m194\">a</a></span><a title=\"true\">&amp;</a><a title=\"true\">&amp;</a><span class=\"p\"><a href=\"#m205\">b</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m242\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m268\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m300\">$</a>(<a title=\"pkg.ExClass\" href=\"#m242\">ExClass</a> <a name=\"m310\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m310\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m268\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m353\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m380\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m412\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m353\">ExClass2</a> <a name=\"m423\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m423\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m380\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage584Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  ExClass2 f = new ExClass2();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  e[f]=10;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int[] field={0,1};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass2 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m157\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m65\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m157\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#m307\">ExClass2</a> <span class=\"f\"><span class=\"f\"><a name=\"m95\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass2\" href=\"#m307\">ExClass2</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m95\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m334\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m65\">e</a></span><span class=\"f\"><a title=\"pkg.ExClass.[]=(int)\" href=\"#m258\">[</a><span class=\"f\"><a title=\"pkg.ExClass2.static $(int,pkg.ExClass2)\" href=\"#m362\"> </a><a href=\"#m95\">f</a></span><a title=\"pkg.ExClass.[]=(int)\" href=\"#m258\">]</a></span></span>=<span class=\"f\">10</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m157\">pkg.ExClass</a> {\n" +
                " public int[] <span class=\"f\"><span class=\"f\"><a name=\"m185\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>}</span></span>;\n" +
                " public int <a name=\"m210\">this</a>(int <a name=\"m219\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m185\">field</a></span><span class=\"n\">[<span class=\"n\"><a href=\"#m219\">i</a></span>]</span></span>;\n" +
                " }\n" +
                " public void <a name=\"m258\">this</a>(int <a name=\"m267\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m185\">field</a></span><span class=\"f\">[<span class=\"f\"><a href=\"#m267\">i</a></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m307\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m334\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m362\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m307\">ExClass2</a> <a name=\"m373\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m373\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m334\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage585Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  ExClass2 f = new ExClass2();\n");
        xml_.append("  ExClass3 g = new ExClass3();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  g.field=1;\n");
        xml_.append("  e[f,g]=10;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int[] field={0,1,2};\n");
        xml_.append(" public int this(int i,int j){\n");
        xml_.append("  return field[i+j];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i,int j){\n");
        xml_.append("  field[i+j]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass2 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass3 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass3 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m203\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m65\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m203\">ExClass</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#m371\">ExClass2</a> <span class=\"f\"><span class=\"f\"><a name=\"m95\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass2\" href=\"#m371\">ExClass2</a>()</span></span>;\n" +
                "  <a title=\"pkg.ExClass3\" href=\"#m477\">ExClass3</a> <span class=\"f\"><span class=\"f\"><a name=\"m126\">g</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass3\" href=\"#m477\">ExClass3</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m95\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m398\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m126\">g</a></span>.<span class=\"f\"><a title=\"pkg.ExClass3.field\" href=\"#m504\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m65\">e</a></span><span class=\"f\"><a title=\"pkg.ExClass.[]=(int,int)\" href=\"#m314\">[</a><span class=\"f\"><a title=\"pkg.ExClass2.static $(int,pkg.ExClass2)\" href=\"#m426\"> </a><a href=\"#m95\">f</a></span>,<span class=\"f\"><a title=\"pkg.ExClass3.static $(int,pkg.ExClass3)\" href=\"#m532\"> </a><a href=\"#m126\">g</a></span><a title=\"pkg.ExClass.[]=(int,int)\" href=\"#m314\">]</a></span></span>=<span class=\"f\">10</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m203\">pkg.ExClass</a> {\n" +
                " public int[] <span class=\"f\"><span class=\"f\"><a name=\"m231\">field</a></span>=<span class=\"f\">{<span class=\"f\">0</span>,<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span></span>;\n" +
                " public int <a name=\"m258\">this</a>(int <a name=\"m267\">i</a>,int <a name=\"m273\">j</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m231\">field</a></span><span class=\"n\">[<span class=\"n\"><span class=\"n\"><a href=\"#m267\">i</a></span>+<span class=\"n\"><a href=\"#m273\">j</a></span></span>]</span></span>;\n" +
                " }\n" +
                " public void <a name=\"m314\">this</a>(int <a name=\"m323\">i</a>,int <a name=\"m329\">j</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m231\">field</a></span><span class=\"f\">[<span class=\"f\"><span class=\"f\"><a href=\"#m323\">i</a></span>+<span class=\"f\"><a href=\"#m329\">j</a></span></span>]</span></span>=<span class=\"f\"><b>value</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m371\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m398\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m426\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m371\">ExClass2</a> <a name=\"m437\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m437\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m398\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m477\">pkg.ExClass3</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m504\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m532\">$</a>(<a title=\"pkg.ExClass3\" href=\"#m477\">ExClass3</a> <a name=\"m543\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m543\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass3.field\" href=\"#m504\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage586Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  ExClass2 f = new ExClass2();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  ExClass e = new ExClass(field:f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public @class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass2 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#m202\">ExClass2</a> <span class=\"f\"><span class=\"f\"><a name=\"m66\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass2\" href=\"#m202\">ExClass2</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m66\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m229\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m154\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m109\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m154\">ExClass</a>(<span class=\"f\"><a title=\"pkg.ExClass2.static $(int,pkg.ExClass2)\" href=\"#m257\"> </a><a title=\"pkg.ExClass.field\" href=\"#m180\">field</a>:<span class=\"f\"><a href=\"#m66\">f</a></span></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public @class <a name=\"m154\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><a name=\"m180\">field</a></span>;\n" +
                "}\n" +
                "public class <a name=\"m202\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m229\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m257\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m202\">ExClass2</a> <a name=\"m268\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m268\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m229\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage587Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  int[] field={new ExClass2()};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass2 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  int[] <span class=\"f\"><span class=\"f\"><a name=\"m63\">field</a></span>=<span class=\"f\">{<span class=\"f\"><a title=\"pkg.ExClass2.static $(int,pkg.ExClass2)\" href=\"#m160\"> </a>new <a title=\"pkg.ExClass2\" href=\"#m105\">ExClass2</a>()</span>}</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m105\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m132\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m160\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m105\">ExClass2</a> <a name=\"m171\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m171\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m132\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage588Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  ExClass2 f = new ExClass2();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  ExClass e = new ExClass(f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public ExClass(int i){\n");
        xml_.append("  field=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass2 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#m233\">ExClass2</a> <span class=\"f\"><span class=\"f\"><a name=\"m66\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass2\" href=\"#m233\">ExClass2</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m66\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m260\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m109\">e</a> </span>=<span class=\"f\"> <a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m181\">new</a> <a title=\"pkg.ExClass\" href=\"#m147\">ExClass</a>(<span class=\"f\"><a title=\"pkg.ExClass2.static $(int,pkg.ExClass2)\" href=\"#m288\"> </a><a href=\"#m66\">f</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m147\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><a name=\"m173\">field</a></span>;\n" +
                " <a name=\"m181\">public ExClass(</a>int <a name=\"m200\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m173\">field</a></span>=<span class=\"f\"><a href=\"#m200\">i</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m233\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m260\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m288\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m233\">ExClass2</a> <a name=\"m299\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m299\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m260\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage589Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  ExClass2 f = new ExClass2();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  iter(int i = f; 1; 1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public ExClass(int i){\n");
        xml_.append("  field=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static int $(ExClass2 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass2\" href=\"#m228\">ExClass2</a> <span class=\"f\"><span class=\"f\"><a name=\"m66\">f</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass2\" href=\"#m228\">ExClass2</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m66\">f</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m255\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"p\">iter</span>(int <a name=\"m110\">i</a> = <span class=\"f\"><a title=\"pkg.ExClass2.static $(int,pkg.ExClass2)\" href=\"#m283\"> </a><a href=\"#m66\">f</a></span>; <span class=\"f\">1</span>; <span class=\"f\">1</span>);\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m142\">pkg.ExClass</a> {\n" +
                " public int <span class=\"n\"><a name=\"m168\">field</a></span>;\n" +
                " <a name=\"m176\">public ExClass(</a>int <a name=\"m195\">i</a>){\n" +
                "  <span class=\"n\"><span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m168\">field</a></span>=<span class=\"n\"><a href=\"#m195\">i</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m228\">pkg.ExClass2</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m255\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static int <a name=\"m283\">$</a>(<a title=\"pkg.ExClass2\" href=\"#m228\">ExClass2</a> <a name=\"m294\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m294\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass2.field\" href=\"#m255\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage590Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  iter(int i = new ExClass(1); 1; 1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public ExClass(int i){\n");
        xml_.append("  field=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <span class=\"p\">iter</span>(int <a name=\"m66\">i</a> = <span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m201\"> </a><a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m145\">new</a> <a title=\"pkg.ExClass\" href=\"#m111\">ExClass</a>(<span class=\"f\">1</span>)</span>; <span class=\"f\">1</span>; <span class=\"f\">1</span>);\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m111\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><a name=\"m137\">field</a></span>;\n" +
                " <a name=\"m145\">public ExClass(</a>int <a name=\"m164\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m137\">field</a></span>=<span class=\"f\"><a href=\"#m164\">i</a></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m201\">$</a>(<a title=\"pkg.ExClass\" href=\"#m111\">ExClass</a> <a name=\"m211\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m211\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m137\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage591Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  iter(int i = 1; new ExClass(1); 1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public ExClass(int i){\n");
        xml_.append("  field=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <span class=\"p\">iter</span>(int <a name=\"m66\">i</a> = <span class=\"f\">1</span>; <span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m201\"> </a><a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m145\">new</a> <a title=\"pkg.ExClass\" href=\"#m111\">ExClass</a>(<span class=\"f\">1</span>)</span>; <span class=\"f\">1</span>);\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m111\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><a name=\"m137\">field</a></span>;\n" +
                " <a name=\"m145\">public ExClass(</a>int <a name=\"m164\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m137\">field</a></span>=<span class=\"f\"><a href=\"#m164\">i</a></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m201\">$</a>(<a title=\"pkg.ExClass\" href=\"#m111\">ExClass</a> <a name=\"m211\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m211\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m137\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage592Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static void method(){\n");
        xml_.append("  iter(int i = 1; 1; new ExClass(1));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public ExClass(int i){\n");
        xml_.append("  field=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static void <a name=\"m45\">method</a>(){\n" +
                "  <span class=\"p\">iter</span>(int <a name=\"m66\">i</a> = <span class=\"f\">1</span>; <span class=\"f\">1</span>; <span class=\"f\"><a title=\"pkg.ExClass.static $(int,pkg.ExClass)\" href=\"#m201\"> </a><a title=\"pkg.ExClass.pkg.ExClass(int)\" href=\"#m145\">new</a> <a title=\"pkg.ExClass\" href=\"#m111\">ExClass</a>(<span class=\"f\">1</span>)</span>);\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m111\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><a name=\"m137\">field</a></span>;\n" +
                " <a name=\"m145\">public ExClass(</a>int <a name=\"m164\">i</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m137\">field</a></span>=<span class=\"f\"><a href=\"#m164\">i</a></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m201\">$</a>(<a title=\"pkg.ExClass\" href=\"#m111\">ExClass</a> <a name=\"m211\">i</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m211\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m137\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage593Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  return bool(e,\"Vrai\",\"Faux\"+1/0);\n");
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
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExClass.static true(boolean,pkg.ExClass)\" href=\"#m211\">bool</a>(<span class=\"p\"><a href=\"#m67\">e</a></span>,<span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>,<span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m153\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m179\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m211\">true</a>(<a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <a name=\"m224\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m224\">i</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m275\">false</a>(<a title=\"pkg.ExClass\" href=\"#m153\">ExClass</a> <a name=\"m289\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m289\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m179\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage594Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  if (e.field==1){\n");
        xml_.append("   return \"Vrai\";\n");
        xml_.append("  }\n");
        xml_.append("  return \"Faux\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m179\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m179\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m205\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m205\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>){\n" +
                "   return <span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m179\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m205\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m237\">$</a>(<a title=\"pkg.ExClass\" href=\"#m179\">ExClass</a> <a name=\"m247\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m247\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m205\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m298\">true</a>(<a title=\"pkg.ExClass\" href=\"#m179\">ExClass</a> <a name=\"m311\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m311\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m205\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m362\">false</a>(<a title=\"pkg.ExClass\" href=\"#m179\">ExClass</a> <a name=\"m376\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m376\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m205\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage595Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  for (;e.field==1;){\n");
        xml_.append("   return \"Vrai\";\n");
        xml_.append("  }\n");
        xml_.append("  return \"Faux\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field=2;\n");
        xml_.append(" public static boolean $(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Apply</a> {\n" +
                " public static String <a name=\"m47\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#m182\">ExClass</a> <span class=\"f\"><span class=\"f\"><a name=\"m67\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.ExClass\" href=\"#m182\">ExClass</a>()</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m208\">field</a></span></span>=<span class=\"f\">1</span></span>;\n" +
                "  <span class=\"p\">for</span> (;<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m67\">e</a></span>.<span class=\"f\"><a title=\"pkg.ExClass.field\" href=\"#m208\">field</a></span></span><a title=\"true\">==</a><span class=\"f\">1</span></span>;){\n" +
                "   return <span class=\"f\"><span class=\"s\">\"Vrai\"</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"s\">\"Faux\"</span></span>+<span class=\"n\"><span class=\"n\">1</span>/<span class=\"n\">0</span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m182\">pkg.ExClass</a> {\n" +
                " public int <span class=\"f\"><span class=\"f\"><a name=\"m208\">field</a></span>=<span class=\"f\">2</span></span>;\n" +
                " public static boolean <a name=\"m240\">$</a>(<a title=\"pkg.ExClass\" href=\"#m182\">ExClass</a> <a name=\"m250\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m250\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m208\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m301\">true</a>(<a title=\"pkg.ExClass\" href=\"#m182\">ExClass</a> <a name=\"m314\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m314\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m208\">field</a></span></span>==<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m365\">false</a>(<a title=\"pkg.ExClass\" href=\"#m182\">ExClass</a> <a name=\"m379\">i</a>){\n" +
                "  return <span class=\"n\"><span class=\"n\"><span class=\"n\"><a href=\"#m379\">i</a></span>.<span class=\"n\"><a title=\"pkg.ExClass.field\" href=\"#m208\">field</a></span></span>!=<span class=\"n\">1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage596Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  return \"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Apply", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertTrue(!filesExp_.isEmpty());
    }
    @Test
    public void coverage597Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" int field = 1;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Int2 {\n");
        xml_.append(" Int f;\n");
        xml_.append(" Int2(Int i) {\n");
        xml_.append("  f = i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return ((:Int2)->new Int2(new Int(){}){\n");
        xml_.append("   Int2(Int p){super(p);}\n");
        xml_.append("  }).call().f.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m40\">pkg.Int2</a> {\n" +
                " <a title=\"pkg.Int\" href=\"#m6\">Int</a> <span class=\"f\"><a name=\"m56\">f</a></span>;\n" +
                " <a name=\"m60\">Int2(</a><a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m69\">i</a>) {\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Int2.f\" href=\"#m56\">f</a> </span>=<span class=\"f\"> <a href=\"#m69\">i</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m94\">pkg.Ext</a> {\n" +
                " static int <a name=\"m116\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"t\">(:<a title=\"pkg.Int2\" href=\"#m40\">Int2</a>)<a name=\"m138\">-&gt;</a><span class=\"f\"><a title=\"pkg.Ext..Int2*1.pkg.Ext..Int2*1(pkg.Int)\" href=\"#m166\">new</a> <a title=\"pkg.Int2\" href=\"#m40\">Int2</a>(<span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m158\">{</a>}</span></span>)<span class=\"t\"><a name=\"m161\">{</a>\n" +
                "   <a name=\"m166\">Int2(</a><a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m175\">p</a>){<span class=\"f\"><a title=\"pkg.Int2.pkg.Int2(pkg.Int)\" href=\"#m60\">super</a>(<span class=\"f\"><a href=\"#m175\">p</a></span>)</span>;}\n" +
                "  }</span></span></span></span>)</span>.<span class=\"f\"><b>call</b>()</span></span>.<span class=\"f\"><a title=\"pkg.Int2.f\" href=\"#m56\">f</a></span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage598Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int t = 7;\n");
        xml_.append("  that int r = that(t);\n");
        xml_.append("  (int r:void)->{exmeth(that(#r),r);}.call(8);\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that int s,int u){\n");
        xml_.append("  s = u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m53\">t</a> </span>=<span class=\"f\"> 7</span></span>;\n" +
                "  that int <span class=\"f\"><span class=\"f\"><a name=\"m71\">r</a> </span>=<span class=\"f\"> that(<span class=\"f\"><a href=\"#m53\">t</a></span>)</span></span>;\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"t\">(int <a name=\"m91\">r</a>:void)<a name=\"m98\">-&gt;</a>{<span class=\"f\"><a title=\"pkg.Ext.static exmeth(~int,int)\" href=\"#m166\">exmeth</a>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m71\">#r</a></span>)</span>,<span class=\"f\"><a href=\"#m91\">r</a></span>)</span>;}</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">8</span>)</span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m53\">t</a></span>;\n" +
                " }\n" +
                " public static void <a name=\"m166\">exmeth</a>(that int <a name=\"m182\">s</a>,int <a name=\"m188\">u</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a href=\"#m182\">s</a> </span>=<span class=\"f\"> <a href=\"#m188\">u</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage599Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int t = 7;\n");
        xml_.append("  exmeth(that(t));\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append(" public static void exmeth(that int r){\n");
        xml_.append("  (int r:void)->{#r=8;}.call(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m53\">t</a> </span>=<span class=\"f\"> 7</span></span>;\n" +
                "  <span class=\"f\"><a title=\"pkg.Ext.static exmeth(~int)\" href=\"#m114\">exmeth</a>(<span class=\"f\">that(<span class=\"f\"><a href=\"#m53\">t</a></span>)</span>)</span>;\n" +
                "  return <span class=\"f\"><a href=\"#m53\">t</a></span>;\n" +
                " }\n" +
                " public static void <a name=\"m114\">exmeth</a>(that int <a name=\"m130\">r</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><span class=\"t\">(int <a name=\"m141\">r</a>:void)<a name=\"m148\">-&gt;</a>{<span class=\"f\"><span class=\"f\"><a href=\"#m130\">#r</a></span>=<span class=\"f\">8</span></span>;}</span></span>.<span class=\"f\"><b>call</b>(<span class=\"f\">0</span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage600Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Simple {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param1< T> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param2<T > {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param3< T > {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param4< T:Simple> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param5<T :Simple> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param6< T :Simple> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param7< T: Simple> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param8<T : Simple> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param9< T : Simple> {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m77\">pkg.Simple</a> {\n" +
                "}\n" +
                "public class <a name=\"m105\">pkg.Param1</a>&lt; <a name=\"m117\">T</a>&gt; {\n" +
                "}\n" +
                "public class <a name=\"m137\">pkg.Param2</a>&lt;<a name=\"m148\">T</a> &gt; {\n" +
                "}\n" +
                "public class <a name=\"m169\">pkg.Param3</a>&lt; <a name=\"m181\">T</a> &gt; {\n" +
                "}\n" +
                "public class <a name=\"m202\">pkg.Param4</a>&lt; <a name=\"m214\">T</a>:<a title=\"pkg.Simple\" href=\"#m77\">Simple</a>&gt; {\n" +
                "}\n" +
                "public class <a name=\"m241\">pkg.Param5</a>&lt;<a name=\"m252\">T</a> :<a title=\"pkg.Simple\" href=\"#m77\">Simple</a>&gt; {\n" +
                "}\n" +
                "public class <a name=\"m280\">pkg.Param6</a>&lt; <a name=\"m292\">T</a> :<a title=\"pkg.Simple\" href=\"#m77\">Simple</a>&gt; {\n" +
                "}\n" +
                "public class <a name=\"m320\">pkg.Param7</a>&lt; <a name=\"m332\">T</a>: <a title=\"pkg.Simple\" href=\"#m77\">Simple</a>&gt; {\n" +
                "}\n" +
                "public class <a name=\"m360\">pkg.Param8</a>&lt;<a name=\"m371\">T</a> : <a title=\"pkg.Simple\" href=\"#m77\">Simple</a>&gt; {\n" +
                "}\n" +
                "public class <a name=\"m400\">pkg.Param9</a>&lt; <a name=\"m412\">T</a> : <a title=\"pkg.Simple\" href=\"#m77\">Simple</a>&gt; {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage601Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int2 {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param1<T: Int1 & Int2 > {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param2<T : Int1 & Int2 > {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param3< T: Int1 & Int2 > {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param4< T : Int1 & Int2 > {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "public interface <a name=\"m81\">pkg.Int1</a> {\n" +
                "}\n" +
                "public interface <a name=\"m111\">pkg.Int2</a> {\n" +
                "}\n" +
                "public class <a name=\"m137\">pkg.Param1</a>&lt;<a name=\"m148\">T</a>: <a title=\"pkg.Int1\" href=\"#m81\">Int1</a> &amp; <a title=\"pkg.Int2\" href=\"#m111\">Int2</a> &gt; {\n" +
                "}\n" +
                "public class <a name=\"m182\">pkg.Param2</a>&lt;<a name=\"m193\">T</a> : <a title=\"pkg.Int1\" href=\"#m81\">Int1</a> &amp; <a title=\"pkg.Int2\" href=\"#m111\">Int2</a> &gt; {\n" +
                "}\n" +
                "public class <a name=\"m228\">pkg.Param3</a>&lt; <a name=\"m240\">T</a>: <a title=\"pkg.Int1\" href=\"#m81\">Int1</a> &amp; <a title=\"pkg.Int2\" href=\"#m111\">Int2</a> &gt; {\n" +
                "}\n" +
                "public class <a name=\"m274\">pkg.Param4</a>&lt; <a name=\"m286\">T</a> : <a title=\"pkg.Int1\" href=\"#m81\">Int1</a> &amp; <a title=\"pkg.Int2\" href=\"#m111\">Int2</a> &gt; {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage602Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int1 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int2 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int3 {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int4 {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Param< T : Int1 & Int2 , S : Int3 & Int4 > {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "public interface <a name=\"m81\">pkg.Int1</a> {\n" +
                "}\n" +
                "public interface <a name=\"m111\">pkg.Int2</a> {\n" +
                "}\n" +
                "public interface <a name=\"m141\">pkg.Int3</a> {\n" +
                "}\n" +
                "public interface <a name=\"m171\">pkg.Int4</a> {\n" +
                "}\n" +
                "public class <a name=\"m197\">pkg.Param</a>&lt; <a name=\"m208\">T</a> : <a title=\"pkg.Int1\" href=\"#m81\">Int1</a> &amp; <a title=\"pkg.Int2\" href=\"#m111\">Int2</a> , <a name=\"m226\">S</a> : <a title=\"pkg.Int3\" href=\"#m141\">Int3</a> &amp; <a title=\"pkg.Int4\" href=\"#m171\">Int4</a> &gt; {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage603Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(true);\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(boolean b){\n");
        xml_.append("  return that(b?that(p1):that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(boolean)\" href=\"#m152\">exmeth</a>(<span class=\"f\">true</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m152\">exmeth</a>(boolean <a name=\"m167\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><span class=\"p\"><a href=\"#m167\">b</a></span>?<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>:<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage604Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(false);\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(boolean b){\n");
        xml_.append("  return that(b?that(p1):that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(boolean)\" href=\"#m153\">exmeth</a>(<span class=\"f\">false</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m153\">exmeth</a>(boolean <a name=\"m168\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><span class=\"p\"><a href=\"#m168\">b</a></span>?<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>:<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage605Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(1));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(b?that(p1):that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m255\">new</a> <a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a>(<span class=\"f\">1</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><span class=\"p\"><a href=\"#m175\">b</a></span>?<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span><a title=\"pkg.ExBool.static true(boolean,pkg.ExBool)\" href=\"#m305\">:</a><span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m233\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m251\">f</a></span>;\n" +
                " <a name=\"m255\">ExBool(</a>int <a name=\"m266\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span>=<span class=\"f\"> <a href=\"#m266\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m305\">true</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m317\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m317\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage606Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(0));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(b?that(p1):that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m255\">new</a> <a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a>(<span class=\"f\">0</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><span class=\"p\"><a href=\"#m175\">b</a></span>?<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span><a title=\"pkg.ExBool.static true(boolean,pkg.ExBool)\" href=\"#m305\">:</a><span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m233\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m251\">f</a></span>;\n" +
                " <a name=\"m255\">ExBool(</a>int <a name=\"m266\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span>=<span class=\"f\"> <a href=\"#m266\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m305\">true</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m317\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m317\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span></span><a title=\"false\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage607Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(1));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(b?that(p1):that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean $(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m255\">new</a> <a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a>(<span class=\"f\">1</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><span class=\"f\"><a href=\"#m175\">b</a></span><a title=\"pkg.ExBool.static $(boolean,pkg.ExBool)\" href=\"#m305\">?</a><span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>:<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m233\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m251\">f</a></span>;\n" +
                " <a name=\"m255\">ExBool(</a>int <a name=\"m266\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span>=<span class=\"f\"> <a href=\"#m266\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m305\">$</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m314\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m314\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage608Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(0));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(b?that(p1):that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean $(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m255\">new</a> <a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a>(<span class=\"f\">0</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><span class=\"f\"><a href=\"#m175\">b</a></span><a title=\"pkg.ExBool.static $(boolean,pkg.ExBool)\" href=\"#m305\">?</a><span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>:<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m233\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m251\">f</a></span>;\n" +
                " <a name=\"m255\">ExBool(</a>int <a name=\"m266\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span>=<span class=\"f\"> <a href=\"#m266\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m305\">$</a>(<a title=\"pkg.ExBool\" href=\"#m233\">ExBool</a> <a name=\"m314\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m314\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m251\">f</a> </span></span><a title=\"false\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage609Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(true);\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(boolean b){\n");
        xml_.append("  return that(bool(b,that(p1),that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(boolean)\" href=\"#m152\">exmeth</a>(<span class=\"f\">true</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m152\">exmeth</a>(boolean <a name=\"m167\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\">bool(<span class=\"p\"><a href=\"#m167\">b</a></span>,<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>,<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span>)</span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage610Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(false);\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(boolean b){\n");
        xml_.append("  return that(bool(b,that(p1),that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(boolean)\" href=\"#m153\">exmeth</a>(<span class=\"f\">false</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m153\">exmeth</a>(boolean <a name=\"m168\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\">bool(<span class=\"p\"><a href=\"#m168\">b</a></span>,<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>,<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span>)</span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage611Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(1));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(bool(b,that(p1),that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m261\">new</a> <a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a>(<span class=\"f\">1</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.ExBool.static true(boolean,pkg.ExBool)\" href=\"#m311\">bool</a>(<span class=\"p\"><a href=\"#m175\">b</a></span>,<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>,<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span>)</span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m239\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m257\">f</a></span>;\n" +
                " <a name=\"m261\">ExBool(</a>int <a name=\"m272\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span>=<span class=\"f\"> <a href=\"#m272\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m311\">true</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m323\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m323\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage612Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(0));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(bool(b,that(p1),that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m261\">new</a> <a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a>(<span class=\"f\">0</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.ExBool.static true(boolean,pkg.ExBool)\" href=\"#m311\">bool</a>(<span class=\"p\"><a href=\"#m175\">b</a></span>,<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>,<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span>)</span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m239\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m257\">f</a></span>;\n" +
                " <a name=\"m261\">ExBool(</a>int <a name=\"m272\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span>=<span class=\"f\"> <a href=\"#m272\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m311\">true</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m323\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m323\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span></span><a title=\"false\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage613Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(1));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(bool(b,that(p1),that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean $(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m261\">new</a> <a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a>(<span class=\"f\">1</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.ExBool.static $(boolean,pkg.ExBool)\" href=\"#m311\">bool</a>(<span class=\"f\"><a href=\"#m175\">b</a></span>,<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>,<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span>)</span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m239\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m257\">f</a></span>;\n" +
                " <a name=\"m261\">ExBool(</a>int <a name=\"m272\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span>=<span class=\"f\"> <a href=\"#m272\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m311\">$</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m320\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m320\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage614Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int p1 = 15;\n");
        xml_.append(" public static int p2 = 18;\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return exmeth(new ExBool(0));\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmeth(ExBool b){\n");
        xml_.append("  return that(bool(b,that(p1),that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExBool {\n");
        xml_.append(" int f;\n");
        xml_.append(" ExBool(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean $(ExBool v){\n");
        xml_.append("  return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m41\">p1</a> </span>=<span class=\"g\"> 15</span></span>;\n" +
                " public static int <span class=\"g\"><span class=\"g\"><a name=\"m69\">p2</a> </span>=<span class=\"g\"> 18</span></span>;\n" +
                " public static int <a name=\"m97\">m</a>(){\n" +
                "  return <span class=\"f\"><a title=\"pkg.Ex.~static exmeth(pkg.ExBool)\" href=\"#m161\">exmeth</a>(<span class=\"f\"><a title=\"pkg.ExBool.pkg.ExBool(int)\" href=\"#m261\">new</a> <a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a>(<span class=\"f\">0</span>)</span>)</span>;\n" +
                " }\n" +
                " public static that int <a name=\"m161\">exmeth</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m175\">b</a>){\n" +
                "  return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.ExBool.static $(boolean,pkg.ExBool)\" href=\"#m311\">bool</a>(<span class=\"f\"><a href=\"#m175\">b</a></span>,<span class=\"n\">that(<span class=\"n\"><a title=\"pkg.Ex.p1\" href=\"#m41\">p1</a></span>)</span>,<span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ex.p2\" href=\"#m69\">p2</a></span>)</span>)</span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m239\">pkg.ExBool</a> {\n" +
                " int <span class=\"f\"><a name=\"m257\">f</a></span>;\n" +
                " <a name=\"m261\">ExBool(</a>int <a name=\"m272\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span>=<span class=\"f\"> <a href=\"#m272\">p</a></span></span>;\n" +
                " }\n" +
                " public static boolean <a name=\"m311\">$</a>(<a title=\"pkg.ExBool\" href=\"#m239\">ExBool</a> <a name=\"m320\">v</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m320\">v</a></span>.<span class=\"f\"><a title=\"pkg.ExBool.f\" href=\"#m257\">f</a> </span></span><a title=\"false\">==</a><span class=\"f\"> 1</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage615Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  Content[] arr = {null,null};\n");
        xml_.append("  for (that Content e : arr){\n");
        xml_.append("    e = new Content();\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">m</a>(){\n" +
                "  <a title=\"pkg.Content\" href=\"#m164\">Content</a>[] <span class=\"f\"><span class=\"f\"><a name=\"m58\">arr</a> </span>=<span class=\"f\"> {<span class=\"f\">null</span>,<span class=\"f\">null</span>}</span></span>;\n" +
                "  <span class=\"f\">for (that <a title=\"pkg.Content\" href=\"#m164\">Content</a> <a name=\"m97\">e</a></span> : <span class=\"f\"><a href=\"#m58\">arr</a></span>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m97\">e</a> </span>=<span class=\"f\"> new <a title=\"pkg.Content\" href=\"#m164\">Content</a>()</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m164\">pkg.Content</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage616Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  Content v = new Content();\n");
        xml_.append("  for (that Content e = that(v);;){\n");
        xml_.append("    break;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Content {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">m</a>(){\n" +
                "  <a title=\"pkg.Content\" href=\"#m156\">Content</a> <span class=\"f\"><span class=\"f\"><a name=\"m56\">v</a> </span>=<span class=\"f\"> new <a title=\"pkg.Content\" href=\"#m156\">Content</a>()</span></span>;\n" +
                "  for (that <a title=\"pkg.Content\" href=\"#m156\">Content</a> <span class=\"f\"><span class=\"f\"><a name=\"m95\">e</a> </span>=<span class=\"f\"> that(<span class=\"f\"><a href=\"#m56\">v</a></span>)</span></span>;;){\n" +
                "    break;\n" +
                "  }\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m156\">pkg.Content</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage617Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static String m(){\n");
        xml_.append("  return \"\"\"\n first \n second \n continuing \\\n after \n two words \n finish \"\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static String <a name=\"m44\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"s\">\"\"\"\n" +
                " first \n" +
                " second \n" +
                " continuing \\\n" +
                " after \n" +
                " two words \n" +
                " finish \"\"\"</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage618Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field()$intern($core.Int*1:field($core.Int*1)){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Comp {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Comp(int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">operator<a name=\"m8\">+</a> <a title=\"pkg.Int\" href=\"#m239\">pkg.Int</a>(<a title=\"pkg.Comp\" href=\"#m278\">pkg.Comp</a> <a name=\"m27\">a</a>, <a title=\"pkg.Comp\" href=\"#m278\">pkg.Comp</a> <a name=\"m39\">b</a>) {\n" +
                " return <span class=\"f\"><a title=\"$core.Int*1.$core.Int*1(int)\" href=\"#m97\">new</a> <a title=\"pkg.Int\" href=\"#m239\">pkg.Int</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m27\">a</a></span>.<span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m301\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m39\">b</a></span>.<span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m301\">field</a></span></span></span>)<span class=\"t\"><a name=\"m80\">{</a>\n" +
                "  int <span class=\"f\"><a name=\"m88\">field</a></span>;\n" +
                "  <a name=\"m97\">public Int(</a>int <a name=\"m112\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"$core.Int*1.field\" href=\"#m88\">field</a> </span>=<span class=\"f\"> <a href=\"#m112\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m147\">field</a>()$intern(<a title=\"$core.Int*1\" href=\"#m80\">$core.Int*1</a>:<a title=\"$core.Int*1.field()\" href=\"#m147\">field</a>(<a title=\"$core.Int*1\" href=\"#m80\">$core.Int*1</a>)){\n" +
                "   return <span class=\"f\"><a title=\"$core.Int*1.field\" href=\"#m88\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span>;\n" +
                "}\n" +
                "public interface <a name=\"m239\">pkg.Int</a> {\n" +
                " int <a name=\"m254\">field</a>();\n" +
                "}\n" +
                "public class <a name=\"m278\">pkg.Comp</a> {\n" +
                " public int <span class=\"f\"><a name=\"m301\">field</a></span>;\n" +
                " <a name=\"m309\">public Comp(</a>int <a name=\"m325\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m301\">field</a></span>=<span class=\"f\"><a href=\"#m325\">p</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m351\">pkg.Ext</a> {\n" +
                " static int <a name=\"m373\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a title=\"pkg.Comp.pkg.Comp(int)\" href=\"#m309\">new</a> <a title=\"pkg.Comp\" href=\"#m278\">Comp</a>(<span class=\"f\">2</span>)</span><a title=\"static +(pkg.Comp,pkg.Comp)\" href=\"#m8\">+</a><span class=\"f\"><a title=\"pkg.Comp.pkg.Comp(int)\" href=\"#m309\">new</a> <a title=\"pkg.Comp\" href=\"#m278\">Comp</a>(<span class=\"f\">4</span>)</span></span>)</span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m254\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage619Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(int f)$intern($core.Int*1:field($core.Int*1,int)){\n");
        xml_.append("   return field+f;\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field(int f);\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Comp {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Comp(int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">operator<a name=\"m8\">+</a> <a title=\"pkg.Int\" href=\"#m250\">pkg.Int</a>(<a title=\"pkg.Comp\" href=\"#m294\">pkg.Comp</a> <a name=\"m27\">a</a>, <a title=\"pkg.Comp\" href=\"#m294\">pkg.Comp</a> <a name=\"m39\">b</a>) {\n" +
                " return <span class=\"f\"><a title=\"$core.Int*1.$core.Int*1(int)\" href=\"#m97\">new</a> <a title=\"pkg.Int\" href=\"#m250\">pkg.Int</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m27\">a</a></span>.<span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m317\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m39\">b</a></span>.<span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m317\">field</a></span></span></span>)<span class=\"t\"><a name=\"m80\">{</a>\n" +
                "  int <span class=\"f\"><a name=\"m88\">field</a></span>;\n" +
                "  <a name=\"m97\">public Int(</a>int <a name=\"m112\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"$core.Int*1.field\" href=\"#m88\">field</a> </span>=<span class=\"f\"> <a href=\"#m112\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m147\">field</a>(int <a name=\"m157\">f</a>)$intern(<a title=\"$core.Int*1\" href=\"#m80\">$core.Int*1</a>:<a title=\"$core.Int*1.field(int)\" href=\"#m147\">field</a>(<a title=\"$core.Int*1\" href=\"#m80\">$core.Int*1</a>,int)){\n" +
                "   return <span class=\"f\"><span class=\"f\"><a title=\"$core.Int*1.field\" href=\"#m88\">field</a></span>+<span class=\"f\"><a href=\"#m157\">f</a></span></span>;\n" +
                "  }\n" +
                " }</span></span>;\n" +
                "}\n" +
                "public interface <a name=\"m250\">pkg.Int</a> {\n" +
                " int <a name=\"m265\">field</a>(int <a name=\"m275\">f</a>);\n" +
                "}\n" +
                "public class <a name=\"m294\">pkg.Comp</a> {\n" +
                " public int <span class=\"f\"><a name=\"m317\">field</a></span>;\n" +
                " <a name=\"m325\">public Comp(</a>int <a name=\"m341\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m317\">field</a></span>=<span class=\"f\"><a href=\"#m341\">p</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m367\">pkg.Ext</a> {\n" +
                " static int <a name=\"m389\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a title=\"pkg.Comp.pkg.Comp(int)\" href=\"#m325\">new</a> <a title=\"pkg.Comp\" href=\"#m294\">Comp</a>(<span class=\"f\">2</span>)</span><a title=\"static +(pkg.Comp,pkg.Comp)\" href=\"#m8\">+</a><span class=\"f\"><a title=\"pkg.Comp.pkg.Comp(int)\" href=\"#m325\">new</a> <a title=\"pkg.Comp\" href=\"#m294\">Comp</a>(<span class=\"f\">4</span>)</span></span>)</span>.<span class=\"f\"><a title=\"pkg.Int.field(int)\" href=\"#m265\">field</a>(<span class=\"f\">0</span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage620Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int[] info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new @Annot(info={2})@AnnotTwo(info2={2}) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "annotation <a name=\"m51\">pkg.AnnotTwo</a> {\n" +
                " int[] <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "interface <a name=\"m94\">pkg.Int</a> {\n" +
                " int <a name=\"m109\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m126\">pkg.Ext</a> {\n" +
                " static int <a name=\"m148\">m</a>(){\n" +
                "  <span class=\"f\">new <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m51\">AnnotTwo</a>(<span class=\"f2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m94\">Int</a>()<span class=\"t\"><a name=\"m201\">{</a>\n" +
                "   public int <a name=\"m217\">field</a>(){\n" +
                "    return <span class=\"n\">0</span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage621Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int[] info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new @Annot(info={2})@AnnotTwo(info2={2}) Int(5){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int p){\n");
        xml_.append("    field=p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "annotation <a name=\"m51\">pkg.AnnotTwo</a> {\n" +
                " int[] <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "interface <a name=\"m94\">pkg.Int</a> {\n" +
                " int <a name=\"m109\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m126\">pkg.Ext</a> {\n" +
                " static int <a name=\"m148\">m</a>(){\n" +
                "  <span class=\"f\"><a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m228\">new</a> <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m51\">AnnotTwo</a>(<span class=\"f2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m94\">Int</a>(<span class=\"f\">5</span>)<span class=\"t\"><a name=\"m202\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m218\">field</a></span>;\n" +
                "   <a name=\"m228\">public Int(</a>int <a name=\"m243\">p</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m218\">field</a></span>=<span class=\"f\"><a href=\"#m243\">p</a></span></span>;\n" +
                "   }\n" +
                "   public int <a name=\"m279\">field</a>(){\n" +
                "    return <span class=\"n\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m218\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage622Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int[] info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new @Annot(info={new Int(){public int field(){return 0;}}.field()})@AnnotTwo(info2={2}) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "annotation <a name=\"m51\">pkg.AnnotTwo</a> {\n" +
                " int[] <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "interface <a name=\"m94\">pkg.Int</a> {\n" +
                " int <a name=\"m109\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m126\">pkg.Ext</a> {\n" +
                " static int <a name=\"m148\">m</a>(){\n" +
                "  <span class=\"f\">new <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m94\">Int</a>()<span class=\"t\"><a name=\"m181\">{</a>public int <a name=\"m193\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m109\">field</a>()</span></span>}</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m51\">AnnotTwo</a>(<span class=\"f2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m94\">Int</a>()<span class=\"t\"><a name=\"m248\">{</a>\n" +
                "   public int <a name=\"m264\">field</a>(){\n" +
                "    return <span class=\"n\">0</span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage623Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int[] info2();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotThree {\n");
        xml_.append(" int[] info3();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotFour {\n");
        xml_.append(" int[] info4();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new @Annot(info={2})@AnnotTwo(info2={2}) Int(new @AnnotThree(info3={2})@AnnotFour(info4={2}) Int(){public int field(){return 0;}}.field()){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int p){\n");
        xml_.append("    field=p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "annotation <a name=\"m51\">pkg.AnnotTwo</a> {\n" +
                " int[] <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "annotation <a name=\"m95\">pkg.AnnotThree</a> {\n" +
                " int[] <a name=\"m119\">info3</a>();\n" +
                "}\n" +
                "annotation <a name=\"m141\">pkg.AnnotFour</a> {\n" +
                " int[] <a name=\"m164\">info4</a>();\n" +
                "}\n" +
                "interface <a name=\"m185\">pkg.Int</a> {\n" +
                " int <a name=\"m200\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m217\">pkg.Ext</a> {\n" +
                " static int <a name=\"m239\">m</a>(){\n" +
                "  <span class=\"f\"><a title=\"pkg.Ext..Int*2.pkg.Ext..Int*2(int)\" href=\"#m410\">new</a> <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"n2\">{<span class=\"n2\">2</span>}</span></span>)</span><span class=\"n2\">@<a title=\"pkg.AnnotTwo\" href=\"#m51\">AnnotTwo</a>(<span class=\"n2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"n2\">{<span class=\"n2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m185\">Int</a>(<span class=\"f\"><span class=\"f\">new <span class=\"f2\">@<a title=\"pkg.AnnotThree\" href=\"#m95\">AnnotThree</a>(<span class=\"f2\"><a title=\"pkg.AnnotThree.info3()\" href=\"#m119\">info3</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotFour\" href=\"#m141\">AnnotFour</a>(<span class=\"f2\"><a title=\"pkg.AnnotFour.info4()\" href=\"#m164\">info4</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m185\">Int</a>()<span class=\"t\"><a name=\"m344\">{</a>public int <a name=\"m356\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m200\">field</a>()</span></span>)<span class=\"t\"><a name=\"m384\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m400\">field</a></span>;\n" +
                "   <a name=\"m410\">public Int(</a>int <a name=\"m425\">p</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*2.field\" href=\"#m400\">field</a></span>=<span class=\"f\"><a href=\"#m425\">p</a></span></span>;\n" +
                "   }\n" +
                "   public int <a name=\"m461\">field</a>(){\n" +
                "    return <span class=\"n\"><a title=\"pkg.Ext..Int*2.field\" href=\"#m400\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage624Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new {} @Annot(info={2}) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "interface <a name=\"m50\">pkg.Int</a> {\n" +
                " int <a name=\"m65\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m82\">pkg.Ext</a> {\n" +
                " static int <a name=\"m104\">m</a>(){\n" +
                "  <span class=\"f\">new {} <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m50\">Int</a>()<span class=\"t\"><a name=\"m140\">{</a>\n" +
                "   public int <a name=\"m156\">field</a>(){\n" +
                "    return <span class=\"n\">0</span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage625Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" static int st;\n");
        xml_.append(" static{\n");
        xml_.append("  st++;\n");
        xml_.append(" }\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new {} @Annot(info={2}) interfaces(Int) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "interface <a name=\"m50\">pkg.Int</a> {\n" +
                " static int <span class=\"g\"><a name=\"m72\">st</a></span>;\n" +
                " static{\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Int.st\" href=\"#m72\">st</a></span>++</span>;\n" +
                " }\n" +
                " int <a name=\"m101\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m118\">pkg.Ext</a> {\n" +
                " static int <a name=\"m140\">m</a>(){\n" +
                "  <span class=\"f\">new {} <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> interfaces(<a title=\"pkg.Int\" href=\"#m50\">Int</a>) <a title=\"pkg.Int\" href=\"#m50\">Int</a>()<span class=\"t\"><a name=\"m192\">{</a>\n" +
                "   public int <a name=\"m208\">field</a>(){\n" +
                "    return <span class=\"n\">0</span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage626Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" static int st;\n");
        xml_.append(" static{\n");
        xml_.append("  st++;\n");
        xml_.append(" }\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int v = new {} @Annot(info={2}) interfaces(Int) (){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..$id*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "interface <a name=\"m50\">pkg.Int</a> {\n" +
                " static int <span class=\"g\"><a name=\"m72\">st</a></span>;\n" +
                " static{\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Int.st\" href=\"#m72\">st</a></span>++</span>;\n" +
                " }\n" +
                " int <a name=\"m101\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m118\">pkg.Ext</a> {\n" +
                " static int <a name=\"m140\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m50\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m151\">v</a> </span>=<span class=\"f\"> new {} <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> interfaces(<a title=\"pkg.Int\" href=\"#m50\">Int</a>) ()<span class=\"t\"><a name=\"m197\">{</a>\n" +
                "   public int <a name=\"m213\">field</a>(){\n" +
                "    return <span class=\"n\">0</span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..$id*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage627Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> v = new {} @Annot(info={2}) Int<>(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "interface <a name=\"m50\">pkg.Int</a>&lt;<a name=\"m58\">T</a>&gt; {\n" +
                " <a href=\"#m58\">T</a> <a name=\"m66\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m83\">pkg.Ext</a> {\n" +
                " static int <a name=\"m105\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m50\">Int</a>&lt;int&gt; <span class=\"f\"><span class=\"f\"><a name=\"m121\">v</a> </span>=<span class=\"f\"> new {} <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m50\">Int</a><a title=\"pkg.Int&lt;int&gt;\">&lt;&gt;</a>()<span class=\"t\"><a name=\"m156\">{</a>\n" +
                "   public int <a name=\"m172\">field</a>(){\n" +
                "    return <span class=\"n\">0</span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage628Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new @pkg.Annot(info={1}) (a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public $id(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Comp {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Comp(int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public annotation <a name=\"m18\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m37\">info</a>();\n" +
                "}\n" +
                "operator<a name=\"m55\">+</a> <a title=\"pkg.Int\" href=\"#m261\">pkg.Int</a>(<a title=\"pkg.Comp\" href=\"#m300\">pkg.Comp</a> <a name=\"m74\">a</a>, <a title=\"pkg.Comp\" href=\"#m300\">pkg.Comp</a> <a name=\"m86\">b</a>) {\n" +
                " return <span class=\"f\"><a title=\"$core.$id*1.$core.$id*1(int)\" href=\"#m158\">new</a> <span class=\"n2\">@<a title=\"pkg.Annot\" href=\"#m18\">pkg.Annot</a>(<span class=\"n2\"><a title=\"pkg.Annot.info()\" href=\"#m37\">info</a>=<span class=\"n2\">{<span class=\"n2\">1</span>}</span></span>)</span> (<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m74\">a</a></span>.<span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m323\">field</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m86\">b</a></span>.<span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m323\">field</a></span></span></span>)<span class=\"t\"><a name=\"m141\">{</a>\n" +
                "  int <span class=\"f\"><a name=\"m149\">field</a></span>;\n" +
                "  <a name=\"m158\">public $id(</a>int <a name=\"m173\">p</a>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a title=\"$core.$id*1.field\" href=\"#m149\">field</a> </span>=<span class=\"f\"> <a href=\"#m173\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m208\">field</a>(){\n" +
                "   return <span class=\"f\"><a title=\"$core.$id*1.field\" href=\"#m149\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span>;\n" +
                "}\n" +
                "public interface <a name=\"m261\">pkg.Int</a> {\n" +
                " int <a name=\"m276\">field</a>();\n" +
                "}\n" +
                "public class <a name=\"m300\">pkg.Comp</a> {\n" +
                " public int <span class=\"f\"><a name=\"m323\">field</a></span>;\n" +
                " <a name=\"m331\">public Comp(</a>int <a name=\"m347\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Comp.field\" href=\"#m323\">field</a></span>=<span class=\"f\"><a href=\"#m347\">p</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m373\">pkg.Ext</a> {\n" +
                " static int <a name=\"m395\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a title=\"pkg.Comp.pkg.Comp(int)\" href=\"#m331\">new</a> <a title=\"pkg.Comp\" href=\"#m300\">Comp</a>(<span class=\"f\">2</span>)</span><a title=\"static +(pkg.Comp,pkg.Comp)\" href=\"#m55\">+</a><span class=\"f\"><a title=\"pkg.Comp.pkg.Comp(int)\" href=\"#m331\">new</a> <a title=\"pkg.Comp\" href=\"#m300\">Comp</a>(<span class=\"f\">4</span>)</span></span>)</span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m276\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage629Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.AnnotOne {\n");
        xml_.append(" int info1();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int info2();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotThree {\n");
        xml_.append(" int info3();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotFour {\n");
        xml_.append(" int info4();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotFive {\n");
        xml_.append(" int info5();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotSix {\n");
        xml_.append(" int info6();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int v = m((@AnnotOne(info1=7)@AnnotTwo(info2=8) int a,@AnnotThree(info3=9)@AnnotFour(info4=10) int b:@AnnotFive(info5=11)@AnnotSix(info6=12) int)->{return 2 * a * b;},3,4);\n");
        xml_.append("  var met = class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  var arr = met.getAnnotations();\n");
        xml_.append("  var arrs = met.getAnnotationsParameters();\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int,int> fct,int a, int b){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.AnnotOne</a> {\n" +
                " int <a name=\"m31\">info1</a>();\n" +
                "}\n" +
                "annotation <a name=\"m53\">pkg.AnnotTwo</a> {\n" +
                " int <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "annotation <a name=\"m95\">pkg.AnnotThree</a> {\n" +
                " int <a name=\"m117\">info3</a>();\n" +
                "}\n" +
                "annotation <a name=\"m139\">pkg.AnnotFour</a> {\n" +
                " int <a name=\"m160\">info4</a>();\n" +
                "}\n" +
                "annotation <a name=\"m182\">pkg.AnnotFive</a> {\n" +
                " int <a name=\"m203\">info5</a>();\n" +
                "}\n" +
                "annotation <a name=\"m225\">pkg.AnnotSix</a> {\n" +
                " int <a name=\"m245\">info6</a>();\n" +
                "}\n" +
                "class <a name=\"m262\">pkg.Ext</a> {\n" +
                " static int <a name=\"m284\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m295\">v</a> </span>=<span class=\"f\"> <a title=\"pkg.Ext.static m($core.Fct&lt;int,int,int&gt;,int,int)\" href=\"#m650\">m</a>(<span class=\"f\"><span class=\"t\">(<span class=\"f2\">@<a title=\"pkg.AnnotOne\" href=\"#m11\">AnnotOne</a>(<span class=\"f2\"><a title=\"pkg.AnnotOne.info1()\" href=\"#m31\">info1</a>=<span class=\"f2\">7</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m53\">AnnotTwo</a>(<span class=\"f2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"f2\">8</span></span>)</span> int <a name=\"m343\">a</a>,<span class=\"f2\">@<a title=\"pkg.AnnotThree\" href=\"#m95\">AnnotThree</a>(<span class=\"f2\"><a title=\"pkg.AnnotThree.info3()\" href=\"#m117\">info3</a>=<span class=\"f2\">9</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotFour\" href=\"#m139\">AnnotFour</a>(<span class=\"f2\"><a title=\"pkg.AnnotFour.info4()\" href=\"#m160\">info4</a>=<span class=\"f2\">10</span></span>)</span> int <a name=\"m390\">b</a>:<span class=\"f2\">@<a title=\"pkg.AnnotFive\" href=\"#m182\">AnnotFive</a>(<span class=\"f2\"><a title=\"pkg.AnnotFive.info5()\" href=\"#m203\">info5</a>=<span class=\"f2\">11</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotSix\" href=\"#m225\">AnnotSix</a>(<span class=\"f2\"><a title=\"pkg.AnnotSix.info6()\" href=\"#m245\">info6</a>=<span class=\"f2\">12</span></span>)</span> int)<a name=\"m436\">-&gt;</a>{return <span class=\"f\"><span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m343\">a</a> </span></span>*<span class=\"f\"> <a href=\"#m390\">b</a></span></span>;}</span></span>,<span class=\"f\">3</span>,<span class=\"f\">4</span>)</span></span>;\n" +
                "  <b title=\"$core.Method\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m470\">met</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> class(<a title=\"pkg.Ext\" href=\"#m262\">Ext</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getDeclaredAnonymousLambda()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "  <b title=\"[$core.Annotation\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m550\">arr</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m470\">met</a></span>.<span class=\"f\">getAnnotations()</span></span></span>;\n" +
                "  <b title=\"[[$core.Annotation\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m584\">arrs</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m470\">met</a></span>.<span class=\"f\">getAnnotationsParameters()</span></span></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                " static int <a name=\"m650\">m</a>(Fct&lt;int,int,int&gt; <a name=\"m669\">fct</a>,int <a name=\"m677\">a</a>, int <a name=\"m684\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m669\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m677\">a</a></span>,<span class=\"f\"><a href=\"#m684\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage630Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.AnnotOne {\n");
        xml_.append(" int info1();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int info2();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotThree {\n");
        xml_.append(" int info3();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotFour {\n");
        xml_.append(" int info4();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotFive {\n");
        xml_.append(" int info5();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotSix {\n");
        xml_.append(" int info6();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int v = m((@AnnotOne(info1=7+new Int(){public int field(){return 0;}}.field())@AnnotTwo(info2=8+new Int(){public int field(){return 0;}}.field()) int a,@AnnotThree(info3=9+new Int(){public int field(){return 0;}}.field())@AnnotFour(info4=10+new Int(){public int field(){return 0;}}.field()) int b:@AnnotFive(info5=11+new Int(){public int field(){return 0;}}.field())@AnnotSix(info6=12+new Int(){public int field(){return 0;}}.field()) int)->{return 2 * a * b;},3,4);\n");
        xml_.append("  var met = class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  var arr = met.getAnnotations();\n");
        xml_.append("  var arrs = met.getAnnotationsParameters();\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int,int> fct,int a, int b){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.AnnotOne</a> {\n" +
                " int <a name=\"m31\">info1</a>();\n" +
                "}\n" +
                "annotation <a name=\"m53\">pkg.AnnotTwo</a> {\n" +
                " int <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "annotation <a name=\"m95\">pkg.AnnotThree</a> {\n" +
                " int <a name=\"m117\">info3</a>();\n" +
                "}\n" +
                "annotation <a name=\"m139\">pkg.AnnotFour</a> {\n" +
                " int <a name=\"m160\">info4</a>();\n" +
                "}\n" +
                "annotation <a name=\"m182\">pkg.AnnotFive</a> {\n" +
                " int <a name=\"m203\">info5</a>();\n" +
                "}\n" +
                "annotation <a name=\"m225\">pkg.AnnotSix</a> {\n" +
                " int <a name=\"m245\">info6</a>();\n" +
                "}\n" +
                "interface <a name=\"m266\">pkg.Int</a> {\n" +
                " int <a name=\"m281\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m298\">pkg.Ext</a> {\n" +
                " static int <a name=\"m320\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m331\">v</a> </span>=<span class=\"f\"> <a title=\"pkg.Ext.static m($core.Fct&lt;int,int,int&gt;,int,int)\" href=\"#m980\">m</a>(<span class=\"f\"><span class=\"t\">(<span class=\"f2\">@<a title=\"pkg.AnnotOne\" href=\"#m11\">AnnotOne</a>(<span class=\"f2\"><a title=\"pkg.AnnotOne.info1()\" href=\"#m31\">info1</a>=<span class=\"f2\"><span class=\"f2\">7</span>+<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m266\">Int</a>()<span class=\"t\"><a name=\"m365\">{</a>public int <a name=\"m377\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m281\">field</a>()</span></span></span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m53\">AnnotTwo</a>(<span class=\"f2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"f2\"><span class=\"f2\">8</span>+<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m266\">Int</a>()<span class=\"t\"><a name=\"m432\">{</a>public int <a name=\"m444\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m281\">field</a>()</span></span></span></span>)</span> int <a name=\"m477\">a</a>,<span class=\"f2\">@<a title=\"pkg.AnnotThree\" href=\"#m95\">AnnotThree</a>(<span class=\"f2\"><a title=\"pkg.AnnotThree.info3()\" href=\"#m117\">info3</a>=<span class=\"f2\"><span class=\"f2\">9</span>+<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m266\">Int</a>()<span class=\"t\"><a name=\"m508\">{</a>public int <a name=\"m520\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m281\">field</a>()</span></span></span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotFour\" href=\"#m139\">AnnotFour</a>(<span class=\"f2\"><a title=\"pkg.AnnotFour.info4()\" href=\"#m160\">info4</a>=<span class=\"f2\"><span class=\"f2\">10</span>+<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m266\">Int</a>()<span class=\"t\"><a name=\"m577\">{</a>public int <a name=\"m589\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m281\">field</a>()</span></span></span></span>)</span> int <a name=\"m622\">b</a>:<span class=\"f2\">@<a title=\"pkg.AnnotFive\" href=\"#m182\">AnnotFive</a>(<span class=\"f2\"><a title=\"pkg.AnnotFive.info5()\" href=\"#m203\">info5</a>=<span class=\"f2\"><span class=\"f2\">11</span>+<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m266\">Int</a>()<span class=\"t\"><a name=\"m653\">{</a>public int <a name=\"m665\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m281\">field</a>()</span></span></span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotSix\" href=\"#m225\">AnnotSix</a>(<span class=\"f2\"><a title=\"pkg.AnnotSix.info6()\" href=\"#m245\">info6</a>=<span class=\"f2\"><span class=\"f2\">12</span>+<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m266\">Int</a>()<span class=\"t\"><a name=\"m721\">{</a>public int <a name=\"m733\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m281\">field</a>()</span></span></span></span>)</span> int)<a name=\"m766\">-&gt;</a>{return <span class=\"f\"><span class=\"f\"><span class=\"f\">2 </span>*<span class=\"f\"> <a href=\"#m477\">a</a> </span></span>*<span class=\"f\"> <a href=\"#m622\">b</a></span></span>;}</span></span>,<span class=\"f\">3</span>,<span class=\"f\">4</span>)</span></span>;\n" +
                "  <b title=\"$core.Method\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m800\">met</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> class(<a title=\"pkg.Ext\" href=\"#m298\">Ext</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getDeclaredAnonymousLambda()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span></span>;\n" +
                "  <b title=\"[$core.Annotation\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m880\">arr</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m800\">met</a></span>.<span class=\"f\">getAnnotations()</span></span></span>;\n" +
                "  <b title=\"[[$core.Annotation\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m914\">arrs</a> </span>=<span class=\"f\"><span class=\"f\"> <a href=\"#m800\">met</a></span>.<span class=\"f\">getAnnotationsParameters()</span></span></span>;\n" +
                "  return <span class=\"f\">0</span>;\n" +
                " }\n" +
                " static int <a name=\"m980\">m</a>(Fct&lt;int,int,int&gt; <a name=\"m999\">fct</a>,int <a name=\"m1007\">a</a>, int <a name=\"m1014\">b</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m999\">fct</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a href=\"#m1007\">a</a></span>,<span class=\"f\"><a href=\"#m1014\">b</a></span>)</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage631Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int[] info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int[] info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new @Annot(info={})@AnnotTwo(info2={2}) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return new Int(){public int field(){return 0;}}.field();\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">annotation <a name=\"m11\">pkg.Annot</a> {\n" +
                " int[] <a name=\"m30\">info</a>();\n" +
                "}\n" +
                "annotation <a name=\"m51\">pkg.AnnotTwo</a> {\n" +
                " int[] <a name=\"m73\">info2</a>();\n" +
                "}\n" +
                "interface <a name=\"m94\">pkg.Int</a> {\n" +
                " int <a name=\"m109\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m126\">pkg.Ext</a> {\n" +
                " static int <a name=\"m148\">m</a>(){\n" +
                "  <span class=\"f\">new <span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m11\">Annot</a>(<span class=\"f2\"><a title=\"pkg.Annot.info()\" href=\"#m30\">info</a>=<span class=\"f2\">{}</span></span>)</span><span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m51\">AnnotTwo</a>(<span class=\"f2\"><a title=\"pkg.AnnotTwo.info2()\" href=\"#m73\">info2</a>=<span class=\"f2\">{<span class=\"f2\">2</span>}</span></span>)</span> <a title=\"pkg.Int\" href=\"#m94\">Int</a>()<span class=\"t\"><a name=\"m200\">{</a>\n" +
                "   public int <a name=\"m216\">field</a>(){\n" +
                "    return <span class=\"n\"><span class=\"n\">new <a title=\"pkg.Int\" href=\"#m94\">Int</a>()<span class=\"t\"><a name=\"m245\">{</a>public int <a name=\"m257\">field</a>(){return <span class=\"n\">0</span>;}}</span></span>.<span class=\"n\"><a title=\"pkg.Int.field()\" href=\"#m109\">field</a>()</span></span>;\n" +
                "   }\n" +
                "  }</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">Class</span>.<span class=\"f\">forName(<span class=\"f\"><span class=\"s\">\"pkg.Ext..Int*1\"</span></span>,<span class=\"f\">false</span>)</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage632Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m53\">a</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m67\">t</a> </span>=<span class=\"f\"> <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\"><a href=\"#m53\">a</a></span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> 10:\n" +
                "    return <span class=\"f\">5</span>;\n" +
                "   <span class=\"n\"><a title=\"0/1\">default</a></span>:\n" +
                "    return <span class=\"n\">1</span>;\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m67\">t</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage633Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int a = 9;\n");
        xml_.append("  int t = switch(a) {\n");
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m53\">a</a> </span>=<span class=\"f\"> 9</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m66\">t</a> </span>=<span class=\"f\"> <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\"><a href=\"#m53\">a</a></span>) <span class=\"t\">{\n" +
                "   <span class=\"n\"><a title=\"0/1\">case</a></span> 10:\n" +
                "    return <span class=\"n\">5</span>;\n" +
                "   <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "    return <span class=\"f\">1</span>;\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><a href=\"#m66\">t</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage634Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int t = 0;\n");
        xml_.append("  for (int a: {9,10}) {\n");
        xml_.append("   t += switch[int](a) {\n");
        xml_.append("    case 10:\n");
        xml_.append("     return 5;\n");
        xml_.append("    default:\n");
        xml_.append("     return 1;\n");
        xml_.append("   };\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m53\">t</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m71\">a</a></span>: <span class=\"f\">{<span class=\"f\">9</span>,<span class=\"f\">10</span>}</span>) {\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m53\">t</a> </span>+=<span class=\"f\"> <span class=\"f\"><a title=\"2/2\">switch</a></span>[int](<span class=\"f\"><a href=\"#m71\">a</a></span>) <span class=\"t\">{\n" +
                "    <span class=\"f\"><a title=\"1/1\">case</a></span> 10:\n" +
                "     return <span class=\"f\">5</span>;\n" +
                "    <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "     return <span class=\"f\">1</span>;\n" +
                "   }</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m53\">t</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage635Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int t = 0;\n");
        xml_.append("  for (int a: {}) {\n");
        xml_.append("   t += switch[int](a) {\n");
        xml_.append("    case 10:\n");
        xml_.append("     return 5;\n");
        xml_.append("    default:\n");
        xml_.append("     return 1;\n");
        xml_.append("   };\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m53\">t</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\">for (int <a name=\"m71\">a</a></span>: <span class=\"f\">{}</span>) {\n" +
                "   <span class=\"n\"><span class=\"n\"><a href=\"#m53\">t</a> </span>+=<span class=\"n\"> <span class=\"n\"><a title=\"0/2\">switch</a></span>[int](<span class=\"n\"><a href=\"#m71\">a</a></span>) <span class=\"t\">{\n" +
                "    <span class=\"n\"><a title=\"0/1\">case</a></span> 10:\n" +
                "     return <span class=\"n\">5</span>;\n" +
                "    <span class=\"n\"><a title=\"0/1\">default</a></span>:\n" +
                "     return <span class=\"n\">1</span>;\n" +
                "   }</span></span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m53\">t</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage636Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  (switch[Compo](10) {\n");
        xml_.append("   default:\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += new Compo(8));\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator+ Compo (Compo a, Compo b){\n");
        xml_.append("   return new Compo(a.f+b.f);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static <a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a> <span class=\"g\"><span class=\"g\"><a name=\"m44\">field</a> </span>=<span class=\"g\"> <a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m232\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a>(<span class=\"g\">7</span>)</span></span>;\n" +
                " public static int <a name=\"m85\">m</a>(){\n" +
                "  <span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"1/1\">switch</a></span>[<a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a>](<span class=\"f\">10</span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "    return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>)</span>;\n" +
                "  }</span> </span><a title=\"pkg.Ext..Compo.static +(pkg.Ext..Compo,pkg.Ext..Compo)\" href=\"#m277\">+</a>=<span class=\"f\"> <a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m232\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a>(<span class=\"f\">8</span>)</span></span>)</span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m227\">f</a></span></span>;\n" +
                " }\n" +
                " static class <a name=\"m206\">Compo</a> {\n" +
                "  public int <span class=\"g\"><a name=\"m227\">f</a></span>;\n" +
                "  <a name=\"m232\">public Compo(</a>int <a name=\"m249\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Compo.f\" href=\"#m227\">f</a> </span>=<span class=\"g\"> <a href=\"#m249\">p</a></span></span>;\n" +
                "  }\n" +
                "  operator<a name=\"m277\">+</a> <a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a> (<a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a> <a name=\"m292\">a</a>, <a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a> <a name=\"m301\">b</a>){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m232\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m206\">Compo</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m292\">a</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m227\">f</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m301\">b</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m227\">f</a></span></span></span>)</span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage637Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  (switch [ Compo](10) {\n");
        xml_.append("   default:\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } += new Compo(8));\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator+ Compo (Compo a, Compo b){\n");
        xml_.append("   return new Compo(a.f+b.f);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <span class=\"g\"><span class=\"g\"><a name=\"m44\">field</a> </span>=<span class=\"g\"> <a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"g\">7</span>)</span></span>;\n" +
                " public static int <a name=\"m85\">m</a>(){\n" +
                "  <span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"1/1\">switch</a></span> [ <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>](<span class=\"f\">10</span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "    return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>)</span>;\n" +
                "  }</span> </span><a title=\"pkg.Ext..Compo.static +(pkg.Ext..Compo,pkg.Ext..Compo)\" href=\"#m279\">+</a>=<span class=\"f\"> <a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"f\">8</span>)</span></span>)</span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span>;\n" +
                " }\n" +
                " static class <a name=\"m208\">Compo</a> {\n" +
                "  public int <span class=\"g\"><a name=\"m229\">f</a></span>;\n" +
                "  <a name=\"m234\">public Compo(</a>int <a name=\"m251\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a> </span>=<span class=\"g\"> <a href=\"#m251\">p</a></span></span>;\n" +
                "  }\n" +
                "  operator<a name=\"m279\">+</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> (<a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <a name=\"m294\">a</a>, <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <a name=\"m303\">b</a>){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m294\">a</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m303\">b</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span></span>)</span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage638Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  (switch [  Compo](10) {\n");
        xml_.append("   default:\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } +=new Compo(8));\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator+ Compo (Compo a, Compo b){\n");
        xml_.append("   return new Compo(a.f+b.f);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <span class=\"g\"><span class=\"g\"><a name=\"m44\">field</a> </span>=<span class=\"g\"> <a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"g\">7</span>)</span></span>;\n" +
                " public static int <a name=\"m85\">m</a>(){\n" +
                "  <span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"1/1\">switch</a></span> [  <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>](<span class=\"f\">10</span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "    return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>)</span>;\n" +
                "  }</span> </span><a title=\"pkg.Ext..Compo.static +(pkg.Ext..Compo,pkg.Ext..Compo)\" href=\"#m279\">+</a>=<span class=\"f\"><a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"f\">8</span>)</span></span>)</span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span>;\n" +
                " }\n" +
                " static class <a name=\"m208\">Compo</a> {\n" +
                "  public int <span class=\"g\"><a name=\"m229\">f</a></span>;\n" +
                "  <a name=\"m234\">public Compo(</a>int <a name=\"m251\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a> </span>=<span class=\"g\"> <a href=\"#m251\">p</a></span></span>;\n" +
                "  }\n" +
                "  operator<a name=\"m279\">+</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> (<a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <a name=\"m294\">a</a>, <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <a name=\"m303\">b</a>){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m294\">a</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m303\">b</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span></span>)</span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage639Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static Compo field = new Compo(7);\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  (switch  [ Compo](10) {\n");
        xml_.append("   default:\n");
        xml_.append("    return that(field);\n");
        xml_.append("  } +=new Compo(8));\n");
        xml_.append("  return field.f;\n");
        xml_.append(" }\n");
        xml_.append(" static class Compo {\n");
        xml_.append("  public int f;\n");
        xml_.append("  public Compo(int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append("  operator+ Compo (Compo a, Compo b){\n");
        xml_.append("   return new Compo(a.f+b.f);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <span class=\"g\"><span class=\"g\"><a name=\"m44\">field</a> </span>=<span class=\"g\"> <a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"g\">7</span>)</span></span>;\n" +
                " public static int <a name=\"m85\">m</a>(){\n" +
                "  <span class=\"f\">(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"1/1\">switch</a></span>  [ <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>](<span class=\"f\">10</span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "    return <span class=\"f\">that(<span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>)</span>;\n" +
                "  }</span> </span><a title=\"pkg.Ext..Compo.static +(pkg.Ext..Compo,pkg.Ext..Compo)\" href=\"#m279\">+</a>=<span class=\"f\"><a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"f\">8</span>)</span></span>)</span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.field\" href=\"#m44\">field</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span>;\n" +
                " }\n" +
                " static class <a name=\"m208\">Compo</a> {\n" +
                "  public int <span class=\"g\"><a name=\"m229\">f</a></span>;\n" +
                "  <a name=\"m234\">public Compo(</a>int <a name=\"m251\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a> </span>=<span class=\"g\"> <a href=\"#m251\">p</a></span></span>;\n" +
                "  }\n" +
                "  operator<a name=\"m279\">+</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> (<a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <a name=\"m294\">a</a>, <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a> <a name=\"m303\">b</a>){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Compo.pkg.Ext..Compo(int)\" href=\"#m234\">new</a> <a title=\"pkg.Ext..Compo\" href=\"#m208\">Compo</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m294\">a</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m303\">b</a></span>.<span class=\"f\"><a title=\"pkg.Ext..Compo.f\" href=\"#m229\">f</a></span></span></span>)</span>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage640Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot:@AnnotTwo](a) {\n");
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ext).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotations().length+\n");
        xml_.append("class(Ext).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotationsParameters().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public annotation <a name=\"m18\">pkg.Annot</a> {\n" +
                "}\n" +
                "public annotation <a name=\"m50\">pkg.AnnotTwo</a> {\n" +
                "}\n" +
                "public class <a name=\"m80\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m109\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m120\">a</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m134\">t</a> </span>=<span class=\"f\"> <span class=\"p\"><a title=\"1/2\">switch</a></span>[int:<span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m18\">Annot</a></span>:<span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m50\">AnnotTwo</a></span>](<span class=\"f\"><a href=\"#m120\">a</a></span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> 10:\n" +
                "    return <span class=\"f\">5</span>;\n" +
                "   <span class=\"n\"><a title=\"0/1\">default</a></span>:\n" +
                "    return <span class=\"n\">1</span>;\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">class(<a title=\"pkg.Ext\" href=\"#m80\">Ext</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getDeclaredSwitchMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>+<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">\n" +
                "class(<a title=\"pkg.Ext\" href=\"#m80\">Ext</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getDeclaredSwitchMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters()</span></span>.<span class=\"f\"><b>length</b></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage641Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot {\n");
        xml_.append(" int info1();\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.AnnotTwo {\n");
        xml_.append(" int info2();\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" normal int field(){return 0;}\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  int a = 10;\n");
        xml_.append("  int t = switch[int:@Annot(new Int(){}.field()):@AnnotTwo(new Int(){}.field())](a) {\n");
        xml_.append("   case 10:\n");
        xml_.append("    return 5;\n");
        xml_.append("   default:\n");
        xml_.append("    return 1;\n");
        xml_.append("  };\n");
        xml_.append("  return class(Ext).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotations().length+\n");
        xml_.append("class(Ext).getDeclaredMethods()[0].getDeclaredSwitchMethods()[0].getAnnotationsParameters().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public annotation <a name=\"m18\">pkg.Annot</a> {\n" +
                " int <a name=\"m35\">info1</a>();\n" +
                "}\n" +
                "public annotation <a name=\"m64\">pkg.AnnotTwo</a> {\n" +
                " int <a name=\"m84\">info2</a>();\n" +
                "}\n" +
                "public interface <a name=\"m112\">pkg.Int</a> {\n" +
                " normal int <a name=\"m134\">field</a>(){return <span class=\"f\">0</span>;}\n" +
                "}\n" +
                "public class <a name=\"m168\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m197\">m</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m208\">a</a> </span>=<span class=\"f\"> 10</span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m222\">t</a> </span>=<span class=\"f\"> <span class=\"p\"><a title=\"1/2\">switch</a></span>[int:<span class=\"f2\">@<a title=\"pkg.Annot\" href=\"#m18\">Annot</a>(<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m112\">Int</a>()<span class=\"t\"><a name=\"m253\">{</a>}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m134\">field</a>()</span></span>)</span>:<span class=\"f2\">@<a title=\"pkg.AnnotTwo\" href=\"#m64\">AnnotTwo</a>(<span class=\"f2\"><span class=\"f2\">new <a title=\"pkg.Int\" href=\"#m112\">Int</a>()<span class=\"t\"><a name=\"m284\">{</a>}</span></span>.<span class=\"f2\"><a title=\"pkg.Int.field()\" href=\"#m134\">field</a>()</span></span>)</span>](<span class=\"f\"><a href=\"#m208\">a</a></span>) <span class=\"t\">{\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> 10:\n" +
                "    return <span class=\"f\">5</span>;\n" +
                "   <span class=\"n\"><a title=\"0/1\">default</a></span>:\n" +
                "    return <span class=\"n\">1</span>;\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">class(<a title=\"pkg.Ext\" href=\"#m168\">Ext</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getDeclaredSwitchMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations()</span></span>.<span class=\"f\"><b>length</b></span></span>+<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\">\n" +
                "class(<a title=\"pkg.Ext\" href=\"#m168\">Ext</a>)</span>.<span class=\"f\">getDeclaredMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getDeclaredSwitchMethods()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotationsParameters()</span></span>.<span class=\"f\"><b>length</b></span></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage642Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return staticCall(ExParam<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExParam\" href=\"#m107\">ExParam</a><a title=\"pkg.ExParam&lt;int&gt;\">&lt;&gt;</a>)</span>.<span class=\"f\"><a title=\"pkg.ExParam.staticCall inst(#T)\" href=\"#m201\">inst</a>(<span class=\"f\">2</span>)</span></span>.<span class=\"f\"><a title=\"pkg.ExParam.f\" href=\"#m134\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m107\">pkg.ExParam</a>&lt;<a name=\"m119\">T</a>&gt; {\n" +
                " public <a href=\"#m119\">T</a> <span class=\"f\"><a name=\"m134\">f</a></span>;\n" +
                " <a name=\"m138\">public ExParam(</a><a href=\"#m119\">T</a> <a name=\"m155\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExParam.f\" href=\"#m134\">f</a> </span>=<span class=\"f\"> <a href=\"#m155\">p</a></span></span>;\n" +
                " }\n" +
                " public staticCall <a title=\"pkg.ExParam\" href=\"#m107\">ExParam</a>&lt;<a href=\"#m119\">T</a>&gt; <a name=\"m201\">inst</a>(<a href=\"#m119\">T</a> <a name=\"m208\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExParam.pkg.ExParam(#T)\" href=\"#m138\">new</a>(<span class=\"f\"><a href=\"#m208\">p</a></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage643Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return staticCall(ExParam <>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m42\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExParam\" href=\"#m107\">ExParam</a> <a title=\"pkg.ExParam&lt;int&gt;\">&lt;&gt;</a>)</span>.<span class=\"f\"><a title=\"pkg.ExParam.staticCall inst(#T)\" href=\"#m201\">inst</a>(<span class=\"f\">2</span>)</span></span>.<span class=\"f\"><a title=\"pkg.ExParam.f\" href=\"#m134\">f</a></span></span>;\n" +
                " }\n" +
                "}" +
                "public class <a name=\"m107\">pkg.ExParam</a>&lt;<a name=\"m119\">T</a>&gt; {\n" +
                " public <a href=\"#m119\">T</a> <span class=\"f\"><a name=\"m134\">f</a></span>;\n" +
                " <a name=\"m138\">public ExParam(</a><a href=\"#m119\">T</a> <a name=\"m155\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExParam.f\" href=\"#m134\">f</a> </span>=<span class=\"f\"> <a href=\"#m155\">p</a></span></span>;\n" +
                " }\n" +
                " public staticCall <a title=\"pkg.ExParam\" href=\"#m107\">ExParam</a>&lt;<a href=\"#m119\">T</a>&gt; <a name=\"m201\">inst</a>(<a href=\"#m119\">T</a> <a name=\"m208\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExParam.pkg.ExParam(#T)\" href=\"#m138\">new</a>(<span class=\"f\"><a href=\"#m208\">p</a></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage644Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class [staticCall pkg.ExParam.inst;] pkg.Ext {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return staticCall(<>).inst(2).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExParam<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public ExParam(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExParam<T> inst(T p){\n");
        xml_.append("  return new(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class [<span class=\"i\">staticCall pkg.ExParam.inst</span>;] <a name=\"m44\">pkg.Ext</a> {\n" +
                " public static int <a name=\"m73\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.ExParam&lt;int&gt;\">&lt;&gt;</a>)</span>.<span class=\"f\"><a title=\"pkg.ExParam.staticCall inst(#T)\" href=\"#m225\">inst</a>(<span class=\"f\">2</span>)</span></span>.<span class=\"f\"><a title=\"pkg.ExParam.f\" href=\"#m158\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m131\">pkg.ExParam</a>&lt;<a name=\"m143\">T</a>&gt; {\n" +
                " public <a href=\"#m143\">T</a> <span class=\"f\"><a name=\"m158\">f</a></span>;\n" +
                " <a name=\"m162\">public ExParam(</a><a href=\"#m143\">T</a> <a name=\"m179\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExParam.f\" href=\"#m158\">f</a> </span>=<span class=\"f\"> <a href=\"#m179\">p</a></span></span>;\n" +
                " }\n" +
                " public staticCall <a title=\"pkg.ExParam\" href=\"#m131\">ExParam</a>&lt;<a href=\"#m143\">T</a>&gt; <a name=\"m225\">inst</a>(<a href=\"#m143\">T</a> <a name=\"m232\">p</a>){\n" +
                "  return <span class=\"f\"><a title=\"pkg.ExParam.pkg.ExParam(#T)\" href=\"#m162\">new</a>(<span class=\"f\"><a href=\"#m232\">p</a></span>)</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage645Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.Ext<T> {\n");
        xml_.append(" public T f;\n");
        xml_.append(" public Ext(T p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  return staticCall(Ext<>).inn(14);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall T inn(T p){\n");
        xml_.append("  Fct<Ext<T>,T> f = staticCall(Ext<>).$lambda(exmethtwo);\n");
        xml_.append("  return f.call(new Ext<>(p));\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall T exmethtwo(Ext<T> e){\n");
        xml_.append("  return e.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ext</a>&lt;<a name=\"m21\">T</a>&gt; {\n" +
                " public <a href=\"#m21\">T</a> <span class=\"f\"><a name=\"m36\">f</a></span>;\n" +
                " <a name=\"m40\">public Ext(</a><a href=\"#m21\">T</a> <a name=\"m53\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.f\" href=\"#m36\">f</a> </span>=<span class=\"f\"> <a href=\"#m53\">p</a></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m88\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m13\">Ext</a><a title=\"pkg.Ext&lt;int&gt;\">&lt;&gt;</a>)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall inn(#T)\" href=\"#m153\">inn</a>(<span class=\"f\">14</span>)</span></span>;\n" +
                " }\n" +
                " public staticCall <a href=\"#m21\">T</a> <a name=\"m153\">inn</a>(<a href=\"#m21\">T</a> <a name=\"m159\">p</a>){\n" +
                "  Fct&lt;<a title=\"pkg.Ext\" href=\"#m13\">Ext</a>&lt;<a href=\"#m21\">T</a>&gt;,<a href=\"#m21\">T</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m179\">f</a> </span>=<span class=\"f\"><span class=\"f\"> staticCall(<a title=\"pkg.Ext\" href=\"#m13\">Ext</a><a title=\"pkg.Ext&lt;#T&gt;\">&lt;&gt;</a>)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall exmethtwo(pkg.Ext&lt;#T&gt;)\" href=\"#m276\">$lambda</a>(exmethtwo)</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m179\">f</a></span>.<span class=\"f\"><b>call</b>(<span class=\"f\"><a title=\"pkg.Ext.pkg.Ext(#T)\" href=\"#m40\">new</a> <a title=\"pkg.Ext\" href=\"#m13\">Ext</a><a title=\"pkg.Ext&lt;#T&gt;\">&lt;&gt;</a>(<span class=\"f\"><a href=\"#m159\">p</a></span>)</span>)</span></span>;\n" +
                " }\n" +
                " public staticCall <a href=\"#m21\">T</a> <a name=\"m276\">exmethtwo</a>(<a title=\"pkg.Ext\" href=\"#m13\">Ext</a>&lt;<a href=\"#m21\">T</a>&gt; <a name=\"m293\">e</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m293\">e</a></span>.<span class=\"f\"><a title=\"pkg.Ext.f\" href=\"#m36\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage646Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext<U> {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(2);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall U m(U p){\n");
        xml_.append("  Int<U> l = new Int<U>(p){\n");
        xml_.append("   public T field;\n");
        xml_.append("   public Int(T p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public T field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a>&lt;<a name=\"m18\">T</a>&gt; {\n" +
                " <a href=\"#m18\">T</a> <a name=\"m26\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m43\">pkg.Ext</a>&lt;<a name=\"m51\">U</a>&gt; {\n" +
                " static int <span class=\"g\"><a name=\"m68\">extField</a></span>;\n" +
                " static int <a name=\"m90\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">staticCall(<a title=\"pkg.Ext\" href=\"#m43\">Ext</a>&lt;int&gt;)</span>.<span class=\"f\"><a title=\"pkg.Ext.staticCall m(#U)\" href=\"#m148\">m</a>(<span class=\"f\">2</span>)</span></span>;\n" +
                " }\n" +
                " staticCall <a href=\"#m51\">U</a> <a name=\"m148\">m</a>(<a href=\"#m51\">U</a> <a name=\"m152\">p</a>){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a>&lt;<a href=\"#m51\">U</a>&gt; <span class=\"f\"><span class=\"f\"><a name=\"m165\">l</a> </span>=<span class=\"f\"> <a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(#T)\" href=\"#m206\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>&lt;<a href=\"#m51\">U</a>&gt;(<span class=\"f\"><a href=\"#m152\">p</a></span>)<span class=\"t\"><a name=\"m182\">{</a>\n" +
                "   public <a href=\"#m182\">T</a> <span class=\"f\"><a name=\"m196\">field</a></span>;\n" +
                "   <a name=\"m206\">public Int(</a><a href=\"#m182\">T</a> <a name=\"m219\">p</a>){\n" +
                "    <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m196\">field</a> </span>=<span class=\"f\"> <a href=\"#m219\">p</a></span></span>;\n" +
                "   }\n" +
                "   public <a href=\"#m182\">T</a> <a name=\"m255\">field</a>(){\n" +
                "    return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m196\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m165\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m26\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage647Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int field()1;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getDeclaredConstructors()[0].getAnnotations();\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = cov(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\"m20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\"m40\">field</a>()<span class=\"f2\">1</span>;\n" +
                "}\n" +
                "$public $class <a name=\"m67\">pkg.Ex</a> {\n" +
                " <span class=\"f2\">@<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a></span>\n" +
                " <a name=\"m87\">$public Ex(</a>){}\n" +
                " $public $static $int <a name=\"m124\">catching</a>(){\n" +
                "  $Annotation[] <span class=\"f\"><span class=\"f\"><a name=\"m152\">arr</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"> $class(<a title=\"pkg.Ex\" href=\"#m67\">Ex</a>)</span>.<span class=\"f\">getDeclaredConstructors()</span></span><span class=\"f\">[<span class=\"f\">0</span>]</span></span>.<span class=\"f\">getAnnotations()</span></span></span>;\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m152\">arr</a></span>.<span class=\"f\"><b>length</b> </span></span><a title=\"false\">!=</a><span class=\"f\"> 1i</span></span>){\n" +
                "   $return <span class=\"n\">3i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m152\">arr</a></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class($Annotation[])</span></span>){\n" +
                "   $return <span class=\"n\">2i</span>;\n" +
                "  }\n" +
                "  <span class=\"p\">$if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\">$static($Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><span class=\"f\"><a href=\"#m152\">arr</a></span><span class=\"f\">[<span class=\"f\">0i</span>]</span></span>) </span></span><a title=\"false\">!=</a><span class=\"f\"> $class(<a title=\"pkg.MyAnnot\" href=\"#m20\">MyAnnot</a>)</span></span>){\n" +
                "   $return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  $return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage648Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Ex(pkg.Ex a, pkg.Ex b) {\n");
        xml_.append(" return new pkg.Ex(a.f+b.f+switch[int](0){\n");
        xml_.append("  default:\n");
        xml_.append("   public class Loc : pkg.Int {public int field(){return 0;}}\n");
        xml_.append("   return new Loc(){public int field(){return 0;}}.field();\n");
        xml_.append(" });\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int f;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Ex e = new Ex(5);\n");
        xml_.append("  Ex g = new Ex(7);\n");
        xml_.append("  return (e+g).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">operator<a name=\"m8\">+</a> <a title=\"pkg.Ex\" href=\"#m278\">pkg.Ex</a>(<a title=\"pkg.Ex\" href=\"#m278\">pkg.Ex</a> <a name=\"m24\">a</a>, <a title=\"pkg.Ex\" href=\"#m278\">pkg.Ex</a> <a name=\"m34\">b</a>) {\n" +
                " return <span class=\"f\"><a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m303\">new</a> <a title=\"pkg.Ex\" href=\"#m278\">pkg.Ex</a>(<span class=\"f\"><span class=\"f\"><span class=\"f\"><span class=\"f\"><a href=\"#m24\">a</a></span>.<span class=\"f\"><a title=\"pkg.Ex.f\" href=\"#m299\">f</a></span></span>+<span class=\"f\"><span class=\"f\"><a href=\"#m34\">b</a></span>.<span class=\"f\"><a title=\"pkg.Ex.f\" href=\"#m299\">f</a></span></span></span>+<span class=\"f\"><span class=\"f\"><a title=\"1/1\">switch</a></span>[int](<span class=\"f\">0</span>)<span class=\"t\">{\n" +
                "  <span class=\"f\"><a title=\"1/1\">default</a></span>:\n" +
                "   public class <a name=\"m109\">Loc</a> : <a title=\"pkg.Int\" href=\"#m239\">pkg.Int</a> {public int <a name=\"m135\">field</a>(){return <span class=\"n\">0</span>;}}\n" +
                "   return <span class=\"f\"><span class=\"f\">new <a title=\"$core.Loc+1\" href=\"#m109\">Loc</a>()<span class=\"t\"><a name=\"m174\">{</a>public int <a name=\"m186\">field</a>(){return <span class=\"f\">0</span>;}}</span></span>.<span class=\"f\"><a title=\"$core.Loc+1.field()\" href=\"#m135\">field</a>()</span></span>;\n" +
                " }</span></span></span>)</span>;\n" +
                "}\n" +
                "public interface <a name=\"m239\">pkg.Int</a> {\n" +
                " int <a name=\"m254\">field</a>();\n" +
                "}\n" +
                "public class <a name=\"m278\">pkg.Ex</a> {\n" +
                " public int <span class=\"f\"><a name=\"m299\">f</a></span>;\n" +
                " <a name=\"m303\">public Ex(</a>int <a name=\"m317\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.Ex.f\" href=\"#m299\">f</a> </span>=<span class=\"f\"> <a href=\"#m317\">p</a></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m352\">exmeth</a>(){\n" +
                "  <a title=\"pkg.Ex\" href=\"#m278\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m367\">e</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m303\">new</a> <a title=\"pkg.Ex\" href=\"#m278\">Ex</a>(<span class=\"f\">5</span>)</span></span>;\n" +
                "  <a title=\"pkg.Ex\" href=\"#m278\">Ex</a> <span class=\"f\"><span class=\"f\"><a name=\"m387\">g</a> </span>=<span class=\"f\"> <a title=\"pkg.Ex.pkg.Ex(int)\" href=\"#m303\">new</a> <a title=\"pkg.Ex\" href=\"#m278\">Ex</a>(<span class=\"f\">7</span>)</span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\">(<span class=\"f\"><span class=\"f\"><a href=\"#m367\">e</a></span><a title=\"static +(pkg.Ex,pkg.Ex)\" href=\"#m8\">+</a><span class=\"f\"><a href=\"#m387\">g</a></span></span>)</span>.<span class=\"f\"><a title=\"pkg.Ex.f\" href=\"#m299\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void coverage649Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("pkgtwo.OuterTwo;\n");
        xml_.append("public class pkg.Outer<C>: OuterTwo<C> {\n");
        xml_.append(" public class Inner {\n");
        xml_.append(" }\n");
        xml_.append(" public class InnerTwo:OuterTwo<C>.InnerThree<C> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkgtwo.OuterTwo<B>:OuterThree<B> {\n");
        xml_.append(" public class InnerThree<F>:OuterThree<B>.InnerFive<F> {\n");
        xml_.append(" }\n");
        xml_.append(" public class InnerFour:InnerThree<B> {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkgtwo.OuterThree<A> {\n");
        xml_.append(" public class InnerFive<E> {\n");
        xml_.append("  public class InnerInner<G> {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkgtwo.ExFour {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  var v = new Outer<pkgtwo.ExFour>().new InnerThree<pkgtwo.ExFour>().new InnerInner<pkgtwo.ExFour>();\n");
        xml_.append("  if (static(Class).getClass(v).getName() != \"pkgtwo.OuterThree<pkgtwo.ExFour>..InnerFive<pkgtwo.ExFour>..InnerInner<pkgtwo.ExFour>\") {\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><span class=\"i\">pkgtwo.OuterTwo</span>;\n" +
                "public class <a name=\"m30\">pkg.Outer</a>&lt;<a name=\"m40\">C</a>&gt;: <a title=\"pkgtwo.OuterTwo\" href=\"#m152\">OuterTwo</a>&lt;<a href=\"#m40\">C</a>&gt; {\n" +
                " public class <a name=\"m72\">Inner</a> {\n" +
                " }\n" +
                " public class <a name=\"m97\">InnerTwo</a>:<a title=\"pkgtwo.OuterTwo\" href=\"#m152\">OuterTwo</a>&lt;<a href=\"#m40\">C</a>&gt;.<a title=\"pkgtwo.OuterTwo..InnerThree\" href=\"#m201\">InnerThree</a>&lt;<a href=\"#m40\">C</a>&gt; {\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m152\">pkgtwo.OuterTwo</a>&lt;<a name=\"m168\">B</a>&gt;:<a title=\"pkgtwo.OuterThree\" href=\"#m305\">OuterThree</a>&lt;<a href=\"#m168\">B</a>&gt; {\n" +
                " public class <a name=\"m201\">InnerThree</a>&lt;<a name=\"m212\">F</a>&gt;:<a title=\"pkgtwo.OuterThree\" href=\"#m305\">OuterThree</a>&lt;<a href=\"#m168\">B</a>&gt;.<a title=\"pkgtwo.OuterThree..InnerFive\" href=\"#m342\">InnerFive</a>&lt;<a href=\"#m212\">F</a>&gt; {\n" +
                " }\n" +
                " public class <a name=\"m261\">InnerFour</a>:<a title=\"pkgtwo.OuterTwo..InnerThree\" href=\"#m201\">InnerThree</a>&lt;<a href=\"#m168\">B</a>&gt; {\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m305\">pkgtwo.OuterThree</a>&lt;<a name=\"m323\">A</a>&gt; {\n" +
                " public class <a name=\"m342\">InnerFive</a>&lt;<a name=\"m352\">E</a>&gt; {\n" +
                "  public class <a name=\"m372\">InnerInner</a>&lt;<a name=\"m383\">G</a>&gt; {\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m410\">pkgtwo.ExFour</a> {\n" +
                "}\n" +
                "public class <a name=\"m441\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m469\">method</a>(){\n" +
                "  <b title=\"pkgtwo.OuterThree&lt;pkgtwo.ExFour&gt;..InnerFive&lt;pkgtwo.ExFour&gt;..InnerInner&lt;pkgtwo.ExFour&gt;\">var</b> <span class=\"f\"><span class=\"f\"><a name=\"m485\">v</a> </span>=<span class=\"f\"><span class=\"f\"><span class=\"f\"> new <a title=\"pkg.Outer\" href=\"#m30\">Outer</a>&lt;<a title=\"pkgtwo.ExFour\" href=\"#m410\">pkgtwo.ExFour</a>&gt;()</span>.<span class=\"f\">new <a title=\"pkgtwo.OuterTwo..InnerThree\" href=\"#m201\">InnerThree</a>&lt;<a title=\"pkgtwo.ExFour\" href=\"#m410\">pkgtwo.ExFour</a>&gt;()</span></span>.<span class=\"f\">new <a title=\"pkgtwo.OuterThree..InnerFive..InnerInner\" href=\"#m372\">InnerInner</a>&lt;<a title=\"pkgtwo.ExFour\" href=\"#m410\">pkgtwo.ExFour</a>&gt;()</span></span></span>;\n" +
                "  <span class=\"p\">if</span> (<span class=\"p\"><span class=\"f\"><span class=\"f\"><span class=\"f\">static(Class)</span>.<span class=\"f\">getClass(<span class=\"f\"><a href=\"#m485\">v</a></span>)</span></span>.<span class=\"f\">getName() </span></span><a title=\"false\">!=</a><span class=\"f\"> <span class=\"s\">\"pkgtwo.OuterThree&lt;pkgtwo.ExFour&gt;..InnerFive&lt;pkgtwo.ExFour&gt;..InnerInner&lt;pkgtwo.ExFour&gt;\"</span></span></span>) {\n" +
                "   return <span class=\"n\">1i</span>;\n" +
                "  }\n" +
                "  return <span class=\"f\">0i</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage650Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE (5);\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m55\">ONE</a> (<span class=\"g\">5</span>)</span>;\n" +
                " public int <span class=\"g\"><a name=\"m47\">field</a></span>;\n" +
                " <a name=\"m55\">ExEnum(</a>int <a name=\"m66\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m47\">field</a></span>=<span class=\"g\"><a href=\"#m66\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m107\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m135\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage651Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum<T> {\n");
        xml_.append(" ONE < Ex > (5);\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a>&lt;<a name=\"m23\">T</a>&gt; {\n" +
                " <span class=\"g\"><a name=\"m29\" title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m65\">ONE</a> &lt; <a title=\"pkg.Ex\" href=\"#m117\">Ex</a> &gt; (<span class=\"g\">5</span>)</span>;\n" +
                " public int <span class=\"g\"><a name=\"m57\">field</a></span>;\n" +
                " <a name=\"m65\">ExEnum(</a>int <a name=\"m76\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m57\">field</a></span>=<span class=\"g\"><a href=\"#m76\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m117\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m145\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m29\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage652Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  return new int[]{2,4,6,8}[0??????1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int[] <a name=\"m43\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new int[]{<span class=\"f\">2</span>,<span class=\"f\">4</span>,<span class=\"f\">6</span>,<span class=\"f\">8</span>}</span><span class=\"f\">[<span class=\"f\"><span class=\"f\">0</span>??????<span class=\"f\">1</span></span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage653Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  return new int[]{2,4,6,8}[0??? ???1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int[] <a name=\"m43\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new int[]{<span class=\"f\">2</span>,<span class=\"f\">4</span>,<span class=\"f\">6</span>,<span class=\"f\">8</span>}</span><span class=\"f\">[<span class=\"f\"><span class=\"f\">0</span>??? ???<span class=\"f\">1</span></span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage654Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int[] exmeth(){\n");
        xml_.append("  return new int[]{2,4,6,8}[new ExNum(0)??? ???new ExNum(1)];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExNum {\n");
        xml_.append(" public int f;\n");
        xml_.append(" ExNum(int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExNum p){\n");
        xml_.append("  return p.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int[] <a name=\"m43\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\">new int[]{<span class=\"f\">2</span>,<span class=\"f\">4</span>,<span class=\"f\">6</span>,<span class=\"f\">8</span>}</span><span class=\"f\">[<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExNum.pkg.ExNum(int)\" href=\"#m161\">new</a> <a title=\"pkg.ExNum\" href=\"#m133\">ExNum</a>(<span class=\"f\">0</span>)<a title=\"pkg.ExNum.static $(int,pkg.ExNum)\" href=\"#m206\"> </a></span>??? ???<span class=\"f\"><a title=\"pkg.ExNum.static $(int,pkg.ExNum)\" href=\"#m206\"> </a><a title=\"pkg.ExNum.pkg.ExNum(int)\" href=\"#m161\">new</a> <a title=\"pkg.ExNum\" href=\"#m133\">ExNum</a>(<span class=\"f\">1</span>)</span></span>]</span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m133\">pkg.ExNum</a> {\n" +
                " public int <span class=\"f\"><a name=\"m157\">f</a></span>;\n" +
                " <a name=\"m161\">ExNum(</a>int <a name=\"m171\">p</a>){\n" +
                "  <span class=\"f\"><span class=\"f\"><a title=\"pkg.ExNum.f\" href=\"#m157\">f</a> </span>=<span class=\"f\"> <a href=\"#m171\">p</a></span></span>;\n" +
                " }\n" +
                " public static int <a name=\"m206\">$</a>(<a title=\"pkg.ExNum\" href=\"#m133\">ExNum</a> <a name=\"m214\">p</a>){\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m214\">p</a></span>.<span class=\"f\"><a title=\"pkg.ExNum.f\" href=\"#m157\">f</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage655Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static long exmeth(){\n");
        xml_.append("  return *new Ex();\n");
        xml_.append(" }\n");
        xml_.append(" public long null(){\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static long <a name=\"m42\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><i>*</i><span class=\"f\">new <a title=\"pkg.Ex\" href=\"#m13\">Ex</a>()</span></span>;\n" +
                " }\n" +
                " public long <a name=\"m88\">null</a>(){\n" +
                "  return <span class=\"f\">2</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage656Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static long exmeth(){\n");
        xml_.append("  return *2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnReadOnlyImpl(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static long <a name=\"m42\">exmeth</a>(){\n" +
                "  return <span class=\"f\">*<span class=\"f\">2</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage657Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  for (int i : {1,2}){\n");
        xml_.append("   s += ( [ i ] );\n");
        xml_.append("  }\n");
        xml_.append("  return s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m57\">s</a> </span>=<span class=\"f\"> 1</span></span>;\n" +
                "  <span class=\"f\">for (int <a name=\"m75\">i</a></span> : <span class=\"f\">{<span class=\"f\">1</span>,<span class=\"f\">2</span>}</span>){\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m57\">s</a> </span>+=<span class=\"f\"> ( [ <a href=\"#m75\">i</a> ] )</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m57\">s</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage658Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE ( 5 , 6 );\n");
        xml_.append(" public int field;\n");
        xml_.append(" public int sec;\n");
        xml_.append(" ExEnum(int param, int sec){\n");
        xml_.append("  field=param;\n");
        xml_.append("  this.sec=sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int,int)\" href=\"#m78\">ONE</a> ( <span class=\"g\">5 </span>,<span class=\"g\"> 6</span> )</span>;\n" +
                " public int <span class=\"g\"><a name=\"m53\">field</a></span>;\n" +
                " public int <span class=\"g\"><a name=\"m72\">sec</a></span>;\n" +
                " <a name=\"m78\">ExEnum(</a>int <a name=\"m89\">param</a>, int <a name=\"m100\">sec</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m53\">field</a></span>=<span class=\"g\"><a href=\"#m89\">param</a></span></span>;\n" +
                "  <span class=\"g\"><span class=\"g\"><span class=\"g\">this</span>.<span class=\"g\"><a title=\"pkg.ExEnum.sec\" href=\"#m72\">sec</a></span></span>=<span class=\"g\"><a href=\"#m100\">sec</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m155\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m183\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage659Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE ( 5 , 6 ){\n");
        xml_.append("  ONE(int param, int sec){\n");
        xml_.append("   super(param,sec);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" public int field;\n");
        xml_.append(" public int sec;\n");
        xml_.append(" ExEnum(int param, int sec){\n");
        xml_.append("  field=param;\n");
        xml_.append("  this.sec=sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\" title=\"pkg.ExEnum-ONE.pkg.ExEnum-ONE(int,int)\" href=\"#m43\">ONE</a> ( <span class=\"g\">5 </span>,<span class=\"g\"> 6</span> )</span>{\n" +
                "  <a name=\"m43\">ONE(</a>int <a name=\"m51\">param</a>, int <a name=\"m62\">sec</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.ExEnum.pkg.ExEnum(int,int)\" href=\"#m134\">super</a>(<span class=\"g\"><a href=\"#m51\">param</a></span>,<span class=\"g\"><a href=\"#m62\">sec</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span class=\"g\"><a name=\"m109\">field</a></span>;\n" +
                " public int <span class=\"g\"><a name=\"m128\">sec</a></span>;\n" +
                " <a name=\"m134\">ExEnum(</a>int <a name=\"m145\">param</a>, int <a name=\"m156\">sec</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m109\">field</a></span>=<span class=\"g\"><a href=\"#m145\">param</a></span></span>;\n" +
                "  <span class=\"g\"><span class=\"g\"><span class=\"g\">this</span>.<span class=\"g\"><a title=\"pkg.ExEnum.sec\" href=\"#m128\">sec</a></span></span>=<span class=\"g\"><a href=\"#m156\">sec</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m211\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m239\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage660Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE ( 5 , 6) ;\n");
        xml_.append(" public int field;\n");
        xml_.append(" public int sec;\n");
        xml_.append(" ExEnum(int param, int sec){\n");
        xml_.append("  field=param;\n");
        xml_.append("  this.sec=sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\" title=\"pkg.ExEnum.pkg.ExEnum(int,int)\" href=\"#m78\">ONE</a> ( <span class=\"g\">5 </span>,<span class=\"g\"> 6</span>) </span>;\n" +
                " public int <span class=\"g\"><a name=\"m53\">field</a></span>;\n" +
                " public int <span class=\"g\"><a name=\"m72\">sec</a></span>;\n" +
                " <a name=\"m78\">ExEnum(</a>int <a name=\"m89\">param</a>, int <a name=\"m100\">sec</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m53\">field</a></span>=<span class=\"g\"><a href=\"#m89\">param</a></span></span>;\n" +
                "  <span class=\"g\"><span class=\"g\"><span class=\"g\">this</span>.<span class=\"g\"><a title=\"pkg.ExEnum.sec\" href=\"#m72\">sec</a></span></span>=<span class=\"g\"><a href=\"#m100\">sec</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m155\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m183\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage661Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE ( 5 , 6) {\n");
        xml_.append("  ONE(int param, int sec){\n");
        xml_.append("   super(param,sec);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" public int field;\n");
        xml_.append(" public int sec;\n");
        xml_.append(" ExEnum(int param, int sec){\n");
        xml_.append("  field=param;\n");
        xml_.append("  this.sec=sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\" title=\"pkg.ExEnum-ONE.pkg.ExEnum-ONE(int,int)\" href=\"#m43\">ONE</a> ( <span class=\"g\">5 </span>,<span class=\"g\"> 6</span>) </span>{\n" +
                "  <a name=\"m43\">ONE(</a>int <a name=\"m51\">param</a>, int <a name=\"m62\">sec</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.ExEnum.pkg.ExEnum(int,int)\" href=\"#m134\">super</a>(<span class=\"g\"><a href=\"#m51\">param</a></span>,<span class=\"g\"><a href=\"#m62\">sec</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span class=\"g\"><a name=\"m109\">field</a></span>;\n" +
                " public int <span class=\"g\"><a name=\"m128\">sec</a></span>;\n" +
                " <a name=\"m134\">ExEnum(</a>int <a name=\"m145\">param</a>, int <a name=\"m156\">sec</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m109\">field</a></span>=<span class=\"g\"><a href=\"#m145\">param</a></span></span>;\n" +
                "  <span class=\"g\"><span class=\"g\"><span class=\"g\">this</span>.<span class=\"g\"><a title=\"pkg.ExEnum.sec\" href=\"#m128\">sec</a></span></span>=<span class=\"g\"><a href=\"#m156\">sec</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m211\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m239\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage662Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum<T> {\n");
        xml_.append(" ONE < Ex > (5){\n");
        xml_.append("  ONE(int param){\n");
        xml_.append("   super(param);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append(" public int field;\n");
        xml_.append(" ExEnum(int param){\n");
        xml_.append("  field=param;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ExEnum.ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a>&lt;<a name=\"m23\">T</a>&gt; {\n" +
                " <span class=\"g\"><a name=\"m29\" title=\"pkg.ExEnum-ONE.pkg.ExEnum-ONE(int)\" href=\"#m47\">ONE</a> &lt; <a title=\"pkg.Ex\" href=\"#m160\">Ex</a> &gt; (<span class=\"g\">5</span>)</span>{\n" +
                "  <a name=\"m47\">ONE(</a>int <a name=\"m55\">param</a>){\n" +
                "   <span class=\"g\"><a title=\"pkg.ExEnum.pkg.ExEnum(int)\" href=\"#m108\">super</a>(<span class=\"g\"><a href=\"#m55\">param</a></span>)</span>;\n" +
                "  }\n" +
                " };\n" +
                " public int <span class=\"g\"><a name=\"m100\">field</a></span>;\n" +
                " <a name=\"m108\">ExEnum(</a>int <a name=\"m119\">param</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.ExEnum.field\" href=\"#m100\">field</a></span>=<span class=\"g\"><a href=\"#m119\">param</a></span></span>;\n" +
                " }\n" +
                "}\n" +
                "public class <a name=\"m160\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m188\">exmeth</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m29\">ONE</a></span></span>.<span class=\"f\">ordinal()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage663Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch(ExEnum.ONE){\n");
        xml_.append("   case TWO,THREE:\n");
        xml_.append("    return 2;\n");
        xml_.append("   case ONE:\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\">ONE</a></span>,<span class=\"g\"><a name=\"m30\">TWO</a></span>,<span class=\"g\"><a name=\"m34\">THREE</a></span>;\n" +
                "}\n" +
                "public class <a name=\"m56\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m84\">exmeth</a>(){\n" +
                "  <span class=\"p\"><a title=\"1/4\">switch</a></span>(<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a></span></span>){\n" +
                "   <span class=\"n\"><a title=\"0/2\">case</a></span> <a title=\"pkg.ExEnum.TWO\" href=\"#m30\">TWO</a>,<a title=\"pkg.ExEnum.THREE\" href=\"#m34\">THREE</a>:\n" +
                "    return <span class=\"n\">2</span>;\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> <a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a>:\n" +
                "    return <span class=\"f\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage664Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch(ExEnum.TWO){\n");
        xml_.append("   case TWO,THREE:\n");
        xml_.append("    return 2;\n");
        xml_.append("   case ONE:\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\">ONE</a></span>,<span class=\"g\"><a name=\"m30\">TWO</a></span>,<span class=\"g\"><a name=\"m34\">THREE</a></span>;\n" +
                "}\n" +
                "public class <a name=\"m56\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m84\">exmeth</a>(){\n" +
                "  <span class=\"p\"><a title=\"1/4\">switch</a></span>(<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.TWO\" href=\"#m30\">TWO</a></span></span>){\n" +
                "   <span class=\"p\"><a title=\"1/2\n0\">case</a></span> <a title=\"pkg.ExEnum.TWO\" href=\"#m30\">TWO</a>,<a title=\"pkg.ExEnum.THREE\" href=\"#m34\">THREE</a>:\n" +
                "    return <span class=\"f\">2</span>;\n" +
                "   <span class=\"n\"><a title=\"0/1\">case</a></span> <a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a>:\n" +
                "    return <span class=\"n\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage665Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE,TWO,THREE,FOUR;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int w = 0;\n");
        xml_.append("  for(var v:ExEnum.values()){\n");
        xml_.append("   switch(v){\n");
        xml_.append("    case TWO,THREE:\n");
        xml_.append("     w += 2;\n");
        xml_.append("    case ONE:\n");
        xml_.append("     w += 1;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return w;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\">ONE</a></span>,<span class=\"g\"><a name=\"m30\">TWO</a></span>,<span class=\"g\"><a name=\"m34\">THREE</a></span>,<span class=\"g\"><a name=\"m40\">FOUR</a></span>;\n" +
                "}\n" +
                "public class <a name=\"m61\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m89\">exmeth</a>(){\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m105\">w</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"f\">for(<b title=\"pkg.ExEnum\">var</b> <a name=\"m122\">v</a></span>:<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\">values()</span></span>){\n" +
                "   <span class=\"f\"><a title=\"4/4\">switch</a></span>(<span class=\"f\"><a href=\"#m122\">v</a></span>){\n" +
                "    <span class=\"f\"><a title=\"2/2\">case</a></span> <a title=\"pkg.ExEnum.TWO\" href=\"#m30\">TWO</a>,<a title=\"pkg.ExEnum.THREE\" href=\"#m34\">THREE</a>:\n" +
                "     <span class=\"f\"><span class=\"f\"><a href=\"#m105\">w</a> </span>+=<span class=\"f\"> 2</span></span>;\n" +
                "    <span class=\"f\"><a title=\"1/1\">case</a></span> <a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a>:\n" +
                "     <span class=\"f\"><span class=\"f\"><a href=\"#m105\">w</a> </span>+=<span class=\"f\"> 1</span></span>;\n" +
                "   }\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m105\">w</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage666Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.ExEnum {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch(ExEnum.THREE){\n");
        xml_.append("   case TWO,THREE:\n");
        xml_.append("    return 2;\n");
        xml_.append("   case ONE:\n");
        xml_.append("    return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public enum <a name=\"m12\">pkg.ExEnum</a> {\n" +
                " <span class=\"g\"><a name=\"m26\">ONE</a></span>,<span class=\"g\"><a name=\"m30\">TWO</a></span>,<span class=\"g\"><a name=\"m34\">THREE</a></span>;\n" +
                "}\n" +
                "public class <a name=\"m56\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m84\">exmeth</a>(){\n" +
                "  <span class=\"p\"><a title=\"1/4\">switch</a></span>(<span class=\"f\"><span class=\"f\"><a title=\"pkg.ExEnum\" href=\"#m12\">ExEnum</a></span>.<span class=\"f\"><a title=\"pkg.ExEnum.THREE\" href=\"#m34\">THREE</a></span></span>){\n" +
                "   <span class=\"p\"><a title=\"1/2\n1\">case</a></span> <a title=\"pkg.ExEnum.TWO\" href=\"#m30\">TWO</a>,<a title=\"pkg.ExEnum.THREE\" href=\"#m34\">THREE</a>:\n" +
                "    return <span class=\"f\">2</span>;\n" +
                "   <span class=\"n\"><a title=\"0/1\">case</a></span> <a title=\"pkg.ExEnum.ONE\" href=\"#m26\">ONE</a>:\n" +
                "    return <span class=\"n\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverage667Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  switch(1){\n");
        xml_.append("   case 1??2:\n");
        xml_.append("    return 2;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Ex</a> {\n" +
                " public static int <a name=\"m41\">exmeth</a>(){\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\">1</span>){\n" +
                "   <span class=\"f\"><a title=\"1/1\">case</a></span> 1??2:\n" +
                "    return <span class=\"f\">2</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">0</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void coverageComment17Test() {
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
        xml_.append("   public int field(){\n");
        xml_.append("    return\\*comment*\\field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        ContextEl cont_ = covEnCom(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "class <a name=\"m42\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m64\">extField</a></span>;\n" +
                " static int <a name=\"m86\">m</a>(){\n" +
                "  <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"f\"><span class=\"f\"><a name=\"m97\">l</a> </span>=<span class=\"f\"> new <a title=\"pkg.Int\" href=\"#m10\">Int</a>()<span class=\"t\"><a name=\"m110\">{</a>\n" +
                "   public int <span class=\"f\"><span class=\"f\"><a name=\"m126\">field</a></span>=<span class=\"f\">++<span class=\"f\"><a title=\"pkg.Ext.extField\" href=\"#m64\">extField</a></span></span></span>;\n" +
                "   public int <a name=\"m158\">field</a>(){\n" +
                "    return<span class=\"c\">\\*comment*\\</span><span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m126\">field</a></span>;\n" +
                "   }\n" +
                "  }</span></span></span>;\n" +
                "  return <span class=\"f\"><span class=\"f\"><a href=\"#m97\">l</a></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

}
