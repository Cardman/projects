package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                " static boolean <a name=\"m49\">true</a>(<a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m58\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">i</a></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 15</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m97\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m119\">extField</a></span>;\n" +
                " static int <a name=\"m141\">m</a>(){\n" +
                "  <span class=\"p\">if</span> (<span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m161\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m177\">subfield</a></span>;\n" +
                "  }</span></span>)<a title=\"pkg.Int.static true(boolean,pkg.Int)\" href=\"#m49\">{</a>\n" +
                "   return <span class=\"f\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">2</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a> </span>=<span class=\"f\"> 15</span></span>;\n" +
                " static boolean <a name=\"m49\">true</a>(<a title=\"pkg.Int\" href=\"#m6\">Int</a> <a name=\"m58\">i</a>){\n" +
                "  return <span class=\"p\"><span class=\"f\"><span class=\"f\"><a href=\"#m58\">i</a></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a> </span></span><a title=\"true\">==</a><span class=\"f\"> 15</span></span>;\n" +
                " }\n" +
                "}\n" +
                "class <a name=\"m97\">pkg.Ext</a> {\n" +
                " static int <span class=\"g\"><a name=\"m119\">extField</a></span>;\n" +
                " static int <a name=\"m141\">m</a>(){\n" +
                "  <span class=\"p\">if</span> (<span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m161\">{</a>\n" +
                "   public int <span class=\"f\"><a name=\"m177\">subfield</a></span>;\n" +
                "  }</span></span>)<a name=\"m191\">lab</a><a title=\"pkg.Int.static true(boolean,pkg.Int)\" href=\"#m49\">{</a>\n" +
                "   return <span class=\"f\">1</span>;\n" +
                "  }\n" +
                "  return <span class=\"n\">2</span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Init</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Init</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Init</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m41\">pkg.Ext</a> {\n" +
                " <a name=\"m52\" title=\"pkg.Ext-ONE.pkg.Ext-ONE(pkg.Int)\" href=\"#m200\">ONE</a>(<span class=\"g\"><a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m113\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"g\">1</span>)<span class=\"t\"><a name=\"m66\">{</a>\n" +
                "  static int <span class=\"g\"><a name=\"m81\">extField</a></span>;\n" +
                "  public int <span class=\"g\"><a name=\"m104\">field</a></span>;\n" +
                "  <a name=\"m113\">public Int(</a>int <a name=\"m128\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a> </span>=<span class=\"g\"> <a href=\"#m128\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m163\">field</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span>){\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
                " int <a name=\"m25\">field</a>();\n" +
                "}\n" +
                "enum <a name=\"m41\">pkg.Ext</a> {\n" +
                " <a name=\"m52\" title=\"pkg.Ext.pkg.Ext(pkg.Int)\" href=\"#m211\">ONE</a>(<span class=\"g\"><a title=\"pkg.Ext..Int*1.pkg.Ext..Int*1(int)\" href=\"#m113\">new</a> <a title=\"pkg.Int\" href=\"#m10\">Int</a>(<span class=\"g\">1</span>)<span class=\"t\"><a name=\"m66\">{</a>\n" +
                "  static int <span class=\"g\"><a name=\"m81\">extField</a></span>;\n" +
                "  public int <span class=\"g\"><a name=\"m104\">field</a></span>;\n" +
                "  <a name=\"m113\">public Int(</a>int <a name=\"m128\">p</a>){\n" +
                "   <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a> </span>=<span class=\"g\"> <a href=\"#m128\">p</a></span></span>;\n" +
                "  }\n" +
                "  public int <a name=\"m163\">field</a>(){\n" +
                "   return <span class=\"f\"><a title=\"pkg.Ext..Int*1.field\" href=\"#m104\">field</a></span>;\n" +
                "  }\n" +
                " }</span></span>);\n" +
                " <a title=\"pkg.Int\" href=\"#m10\">Int</a> <span class=\"g\"><a name=\"m203\">inner</a></span>;\n" +
                " <a name=\"m211\">Ext(</a><a title=\"pkg.Int\" href=\"#m10\">Int</a> <a name=\"m219\">p</a>){\n" +
                "  <span class=\"g\"><span class=\"g\"><a title=\"pkg.Ext.inner\" href=\"#m203\">inner</a> </span>=<span class=\"g\"> <a href=\"#m219\">p</a></span></span>;\n" +
                " }\n" +
                " static int <a name=\"m251\">m</a>(){\n" +
                "  return <span class=\"f\"><span class=\"f\"><span class=\"f\"><a title=\"pkg.Ext.ONE\" href=\"#m52\">ONE</a></span>.<span class=\"f\"><a title=\"pkg.Ext.inner\" href=\"#m203\">inner</a></span></span>.<span class=\"f\"><a title=\"pkg.Int.field()\" href=\"#m25\">field</a>()</span></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
                " int <span class=\"f\"><span class=\"f\"><a name=\"m21\">field</a></span>=<span class=\"f\">1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m38\">pkg.Ext</a> {\n" +
                " static String <a name=\"m63\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m77\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  int <span class=\"f\"><span class=\"f\"><a name=\"m93\">i</a> </span>=<span class=\"f\"> 0</span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span> (<span class=\"f\"><span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m119\">{</a>}</span></span>.<span class=\"f\"><a title=\"pkg.Int.field\" href=\"#m21\">field</a></span></span>){\n" +
                "   case <span class=\"f\">1</span>:\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m77\">res</a></span>+=<span class=\"f\"><span class=\"s\">','</span></span></span>;\n" +
                "   <span class=\"f\"><span class=\"f\"><a href=\"#m93\">i</a></span>++</span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m77\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Int</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">CST</a> </span>=<span class=\"g\"> 1</span></span>;\n" +
                "}\n" +
                "class <a name=\"m51\">pkg.Ext</a> {\n" +
                " static String <a name=\"m76\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m90\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\">1</span>){\n" +
                "   case <span class=\"f\">new <a title=\"pkg.Int\" href=\"#m6\">Int</a>()<span class=\"t\"><a name=\"m130\">{</a>}</span>.<a title=\"pkg.Int.CST\" href=\"#m34\">CST</a></span>:\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m90\">res</a> </span>+=<span class=\"f\"> 1</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m90\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>class <a name=\"m6\">pkg.Ext</a> {\n" +
                " static final int <span class=\"g\"><span class=\"g\"><a name=\"m34\">CST</a> </span>=<span class=\"g\"> 1</span></span>;\n" +
                " static String <a name=\"m58\">m</a>(){\n" +
                "  String <span class=\"f\"><span class=\"f\"><a name=\"m72\">res</a> </span>=<span class=\"f\"> <span class=\"s\">\"\"</span></span></span>;\n" +
                "  <span class=\"p\"><a title=\"1/2\">switch</a></span>(<span class=\"f\">2</span>){\n" +
                "   case <span class=\"f\"><a title=\"pkg.Ext.CST\" href=\"#m34\">CST</a>+<a title=\"pkg.Ext.CST\" href=\"#m34\">CST</a></span>:\n" +
                "    <span class=\"f\"><span class=\"f\"><a href=\"#m72\">res</a> </span>+=<span class=\"f\"> 1</span></span>;\n" +
                "  }\n" +
                "  return <span class=\"f\"><a href=\"#m72\">res</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
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
        ContextEl cont_ = contextElCoverageDefaultEnComment();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckValid(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        calculateNormal("pkg.Ext", id_, args_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.export(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>interface <a name=\"m10\">pkg.Int</a> {\n" +
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
                "</pre></body></html>", filesExp_.firstValue());
    }

}
