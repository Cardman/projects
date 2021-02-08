package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodAnonymousTypesTest extends ProcessMethodCommon {

    @Test
    public void calculate1() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int[] arr = new int[2];\n");
        xml_.append("  for (int i = 0; i < 2; i++){\n");
        xml_.append("   Int l = new Int(){\n");
        xml_.append("    public int field=++extField;\n");
        xml_.append("    public int field(){\n");
        xml_.append("     return field;\n");
        xml_.append("    }\n");
        xml_.append("   };\n");
        xml_.append("   arr[i] = l.field();\n");
        xml_.append("  }\n");
        xml_.append("  if (arr[0] != 1){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return arr[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate3() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new Int(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> l = new Int<int>(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> l = new Int<>(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> l = new(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public $id(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> l = new(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public $id(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> l = new($vararg(int),$firstopt(2)){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public $id(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Loc{\n");
        xml_.append("   public int field = 10;\n");
        xml_.append("  }\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field=new Loc().field;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculate11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE(new Int(){\n");
        xml_.append("  static int extField;\n");
        xml_.append("  public int field=1;\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("enum pkg.Ext {\n");
        xml_.append(" ONE(new Int(){\n");
        xml_.append("  static int extField;\n");
        xml_.append("  public int field=1;\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate13() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate14() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate15() {
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
        xml_.append("  }.field == 15){\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate16() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate17() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Loc{\n");
        xml_.append("   public int field = 10;\n");
        xml_.append("  }\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field=2;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("   class LocTwo{\n");
        xml_.append("    public int field = 10;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Loc{\n");
        xml_.append("   public int field = 10;\n");
        xml_.append("  }\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    class LocTwo{\n");
        xml_.append("     public int field = 2;\n");
        xml_.append("    }\n");
        xml_.append("    return new LocTwo().field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Loc{\n");
        xml_.append("   public int field = 10;\n");
        xml_.append("  }\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return new LocTwo().field;\n");
        xml_.append("   }\n");
        xml_.append("   class LocTwo{\n");
        xml_.append("    public int field = 2;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Loc{\n");
        xml_.append("   public int field = 10;\n");
        xml_.append("  }\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    class LocTwo{\n");
        xml_.append("     public int field = 2;\n");
        xml_.append("     public int field(){\n");
        xml_.append("      return field;\n");
        xml_.append("     }\n");
        xml_.append("    }\n");
        xml_.append("    return new LocTwo().field();\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate22() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("one\"escape1endone,two\"escape2;3endtwo,", getString(ret_));
    }

    @Test
    public void calculate23() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("1,2,", getString(ret_));
    }

    @Test
    public void calculate24() {
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
        xml_.append("   res+=i+\",\"+y+\";\";\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("1,3;2,4;", getString(ret_));
    }
    @Test
    public void calculate25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Anon {\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  Anon a = new Anon(){};\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Anon*1\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("pkg.Ext..Anon*1", getString(ret_));
    }

    @Test
    public void calculate26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Int {\n");
        xml_.append(" public int[] field={10};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int l = new Int(){\n");
        xml_.append("  }[0];\n");
        xml_.append("  return l;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculate27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field()$intern(Ext.Int*1:field(Ext.Int*1));\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field()$intern(Ext.Int*1:field2(Ext.Int*1));\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field=++extField;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("   public int field2(){\n");
        xml_.append("    return field+2;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculate29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static String m(){\n");
        xml_.append("  Int<int> l = new(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public $id(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..$id*1\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("pkg.Ext..$id*1", getString(ret_));
    }
    @Test
    public void calculate30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static String m(){\n");
        xml_.append("  Int<int> l = new (2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public $id(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..$id*1\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("pkg.Ext..$id*1", getString(ret_));
    }
    @Test
    public void calculate31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static String m(){\n");
        xml_.append("  Int<int> l = new {} (2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public $id(int p){\n");
        xml_.append("    field = p;\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..$id*1\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("pkg.Ext..$id*1", getString(ret_));
    }

    @Test
    public void calculate32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field+new pkg.Int(){public int field(){return 0;}}.field();\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  public class Intern:pkg.Int{public int field(){return 0;}}\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field+new Intern().field();\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   public class Intern:pkg.Int{public int field(){return 0;}}\n");
        xml_.append("   return field+new Intern().field();\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot{\n");
        xml_.append(" int val();\n");
        xml_.append("}\n");
        xml_.append("@pkg.Annot(new pkg.Int(){public int field(){return 1;}}.field())\n");
        xml_.append("operator+ pkg.Comp(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Comp(a.field+b.field);\n");
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
        xml_.append("  return ((Annot)Class.getOperators()[0].getAnnotations()[0]).val();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return (pkg.Int)(:int)-> a.field+b.field;\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot{\n");
        xml_.append(" int val();\n");
        xml_.append("}\n");
        xml_.append("@pkg.Annot(((:int)-> 1).call())\n");
        xml_.append("operator+ pkg.Comp(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Comp(a.field+b.field);\n");
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
        xml_.append("  return ((Annot)Class.getOperators()[0].getAnnotations()[0]).val();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return (pkg.Int)(:int)-> a.field+b.field;\n");
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
        xml_.append("  return Class.getOperators()[0].getDeclaredAnonymousLambda().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public annotation pkg.Annot{\n");
        xml_.append(" int val();\n");
        xml_.append("}\n");
        xml_.append("@pkg.Annot(((:int)-> 2).call())\n");
        xml_.append("operator+ pkg.Comp(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Comp(a.field+b.field);\n");
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
        xml_.append("  return Class.getOperators()[0].getDeclaredAnonymousLambda().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return new Loc(a.field+b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+[pkg.*;] Int(Comp a, Comp b) {\n");
        xml_.append(" public class Loc: Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return new Loc(a.field+b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("operator- pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field-b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
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
        xml_.append("  if ((new Comp(2)-new Comp(4)).field() != -2){\n");
        xml_.append("   throw null;\n");
        xml_.append("  }\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator- pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field-b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new pkg.Int(a.field+b.field){\n");
        xml_.append("  int field;\n");
        xml_.append("  public Int(int p){\n");
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
        xml_.append("  if ((new Comp(2)-new Comp(4)).field() != -2){\n");
        xml_.append("   throw null;\n");
        xml_.append("  }\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return new Loc(a.field+b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("operator- pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return new Loc(a.field-b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
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
        xml_.append("  if ((new Comp(2)-new Comp(4)).field() != -2){\n");
        xml_.append("   throw null;\n");
        xml_.append("  }\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator- pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return new Loc(a.field-b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return new Loc(a.field+b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
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
        xml_.append("  if ((new Comp(2)-new Comp(4)).field() != -2){\n");
        xml_.append("   throw null;\n");
        xml_.append("  }\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc1: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc1(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public class Loc: Loc1{\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return (Loc1)new Loc(a.field+b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("operator- pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc2: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc2(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public class Loc: Loc2{\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return (Loc2)new Loc(a.field-b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
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
        xml_.append("  if ((new Comp(2)-new Comp(4)).field() != -2){\n");
        xml_.append("   throw null;\n");
        xml_.append("  }\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator- pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc2: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc2(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public class Loc: Loc2{\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return (Loc2)new Loc(a.field-b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" public class Loc1: pkg.Int{\n");
        xml_.append("  int field;\n");
        xml_.append("  public Loc1(int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append("  public int field(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public class Loc: Loc1{\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" return (Loc1)new Loc(a.field+b.field){\n");
        xml_.append("  public Loc(int p){\n");
        xml_.append("   super(p);\n");
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
        xml_.append("  if ((new Comp(2)-new Comp(4)).field() != -2){\n");
        xml_.append("   throw null;\n");
        xml_.append("  }\n");
        xml_.append("  return (new Comp(2)+new Comp(4)).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate49() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new@Annot(info=2) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new@Annot(info=2) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return ((Annot)Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations()[0]).info();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new@Annot(info=2)@AnnotTwo(info2=2) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int info();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new{}@Annot(info=2)@AnnotTwo(info2=2) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate54() {
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
        xml_.append("  new{}@Annot(info={2})@AnnotTwo(info2={2}) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate55() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ pkg.Int(pkg.Comp a, pkg.Comp b) {\n");
        xml_.append(" return new (a.field+b.field){\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate57() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate58() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("annotation pkg.Annot {\n");
        xml_.append(" int info1();\n");
        xml_.append("}\n");
        xml_.append("annotation pkg.AnnotTwo {\n");
        xml_.append(" int info2();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  new@Annot(info1=3)@AnnotTwo(info2=4) Int(){\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return 0;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  var arr = Class.forName(\"pkg.Ext..Int*1\",false).getAnnotations();\n");
        xml_.append("  if (arr.length != 2){\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  if (((Annot)arr[0]).info1() != 3){\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  if (((AnnotTwo)arr[1]).info2() != 4){\n");
        xml_.append("   return 1;\n");
        xml_.append("  }\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculate59() {
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext<U> {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return Class.forName(\"pkg.Ext..Int*1<int>\",false).getDeclaredMethods().length;\n");
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
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T,W:V,V:Int2<V>> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExSimple:Int2<ExSimple> {\n");
        xml_.append(" int sample(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int2<A:Int2<A>> {\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext<U,B:ExSimple> {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int,ExSimple>).m(2,new ExSimple());\n");
        xml_.append(" }\n");
        xml_.append(" staticCall U m(U p,B q){\n");
        xml_.append("  Int<U,B,ExSimple> l = new Int<U,B,ExSimple>(p,q){\n");
        xml_.append("   public T field;\n");
        xml_.append("   public W field2;\n");
        xml_.append("   public Int(T p,W q){\n");
        xml_.append("    field = p;\n");
        xml_.append("    field2 = q;\n");
        xml_.append("   }\n");
        xml_.append("   public T field(){\n");
        xml_.append("    return (T)((int)field+field2.sample());\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void fail1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<int> l = new Int<int>(\"2\"){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_,"en"));
    }
    @Test
    public void fail2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<!int> l = new Int<!int>(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_,"en"));
    }
    @Test
    public void fail3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<?int> l = new Int<?int>(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_,"en"));
    }
    @Test
    public void fail4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("interface pkg.Int2 {\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Int<?int> l = this.new Int<?int>(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  Int2 m = new Int2.(){\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_,"en"));
    }
    @Test
    public void fail5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Loc{\n");
        xml_.append("   public int field = 10;\n");
        xml_.append("  }\n");
        xml_.append("  Int l = new Int(){\n");
        xml_.append("   public int field=0;\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("   class Loc{\n");
        xml_.append("    public int field = 10;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_,"en"));
    }
    @Test
    public void fail6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<T> {\n");
        xml_.append(" T field();\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext<U> {\n");
        xml_.append(" static int extField;\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  Int<?U> l = new Int<?U>(2){\n");
        xml_.append("   public int field;\n");
        xml_.append("   public Int(int... p){\n");
        xml_.append("    field = p[0];\n");
        xml_.append("   }\n");
        xml_.append("   public int field(){\n");
        xml_.append("    return field;\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  new @Annot()Unknown();\n");
        xml_.append("  return l.field();\n");
        xml_.append("  return l.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLg(files_,"en"));
    }
}
