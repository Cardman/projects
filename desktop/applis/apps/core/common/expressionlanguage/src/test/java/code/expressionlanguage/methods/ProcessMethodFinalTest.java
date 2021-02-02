package code.expressionlanguage.methods;

import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodFinalTest extends ProcessMethodCommon {

    @Test
    public void calculateAndOr1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(){\n");
        xml_.append("  $boolean b1;\n");
        xml_.append("  $boolean c1;\n");
        xml_.append("  $boolean d1=b1=$false||c1=$false;\n");
        xml_.append("  $boolean f1=b1=$true||c1=$true;\n");
        xml_.append("  $boolean e1=b1;\n");
        xml_.append("  $boolean b;\n");
        xml_.append("  $boolean c;\n");
        xml_.append("  $boolean d=b=$true&&c=$true;\n");
        xml_.append("  $boolean f=b=$false&&c=$false;\n");
        xml_.append("  $boolean e=b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateAndOr2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void catching(){\n");
        xml_.append("  $boolean b;\n");
        xml_.append("  $boolean c;\n");
        xml_.append("  $boolean d=b=$true&&c=$true;\n");
        xml_.append("  $boolean f=b=$false&&c=$false;\n");
        xml_.append("  $boolean e=b;\n");
        xml_.append("  $boolean b1;\n");
        xml_.append("  $boolean c1;\n");
        xml_.append("  $boolean d1=b1=$false||c1=$false;\n");
        xml_.append("  $boolean f1=b1=$true||c1=$true;\n");
        xml_.append("  $boolean e1=b1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentInexistFieldTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  annotfield=8;\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentAnnot2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $final $int annotfield;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getAnnotations();\n");
        xml_.append("  $if (arr.length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentAnnot3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $final $int annotfield;\n");
        xml_.append(" {\n");
        xml_.append("  annotfield = 1i;\n");
        xml_.append("  annotfield = 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $if ($static(MyAnnot).annotfield != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getAnnotations();\n");
        xml_.append("  $if (arr.length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $final $int l;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for($int i=4i;i>0i;i--){\n");
        xml_.append("   $if(i==2i){\n");
        xml_.append("    l=3i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+l;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for($final $int i=4i;i>0i;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor_4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $final $int i;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for(;t>0i;i=5){\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $final $int i;\n");
        xml_.append("  $for(;t>0i;i=0i){\n");
        xml_.append("   t+=0i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for($final $int i;t>0i;i=0i){\n");
        xml_.append("   t+=0i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $for($int i=4i;i>0i;i--,u=1i){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $for($int i=4i;(u=1i)>0i;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for($final $int i;t==0;){\n");
        xml_.append("   $for(i=4i;i>0i;){\n");
        xml_.append("    t+=i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for($int j=0;t==0;){\n");
        xml_.append("   $for($final $int i;t==0;){\n");
        xml_.append("    j=t;\n");
        xml_.append("    $int v = 0;\n");
        xml_.append("    $for(i=4i;i>0i;){\n");
        xml_.append("     t+=i;\n");
        xml_.append("    }\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentFor17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $for($final $int i;t>0i;){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument10600Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf;\n");
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument10601Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new pkg.ExTwo().getinst()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int myf;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $int getinst(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument10602Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new pkg.ExTwo().getinst()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int myf;\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $try{\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $int getinst(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument1061Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.ExTwo).getstatic()+8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int myf;\n");
        xml_.append(" $public $static $int mys = 1;\n");
        xml_.append(" $static{\n");
        xml_.append("  $try{\n");
        xml_.append("   $if (mys == 1){\n");
        xml_.append("    $return;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getstatic(){\n");
        xml_.append("  $return myf;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentSwitchFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $long t;\n");
        xml_.append("  $long i;\n");
        xml_.append("  i=9;\n");
        xml_.append("  $switch(i)label{\n");
        xml_.append("   $default:\n");
        xml_.append("    t=12;\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8):\n");
        xml_.append("    t=16;\n");
        xml_.append("    $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgumentCore1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int other;\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" {\n");
        xml_.append("  $int loc=0;\n");
        xml_.append("  $int i=0;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   $int v=0;\n");
        xml_.append("   other=1;\n");
        xml_.append("   $if(i>=5){\n");
        xml_.append("    inst = loc;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   loc += i;\n");
        xml_.append("   i++;\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i.0){\n");
        xml_.append("   ance=([i])\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=2i*([i])\n");
        xml_.append(" }\n");
        xml_.append(" $public (pkg.ExThree i){\n");
        xml_.append("  $int j=$this.ance+1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i.0){\n");
        xml_.append("   ance=([i])\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i.0){\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=([i])\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentIf1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t<0) label {\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   u=8i;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentIf2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t<0) label {\n");
        xml_.append("   u=8i;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentIf3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int u = 5i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t<0) label {\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   u=8i;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgumentIf4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int u = 5i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t<0) label {\n");
        xml_.append("   u=8i;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgumentSimple8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $final $int v = $bool(t==8,u=10,11)+1;\n");
        xml_.append("  $return u+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgumentSimple9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $boolean u;\n");
        xml_.append("  $final $boolean v = $bool(t==8,u=$false,$true);\n");
        xml_.append("  $return u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgumentSimple10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $final $int v = $bool(t==8,10,u=11)+1;\n");
        xml_.append("  $return u+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgumentSimple11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $boolean u;\n");
        xml_.append("  $final $boolean v = $bool(t==8,$false,u=$true);\n");
        xml_.append("  $return u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgumentSimple12FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $boolean u;\n");
        xml_.append("  $final $boolean v = $bool(t==8,u=$true,$false);\n");
        xml_.append("  $return u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgumentSimple13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $boolean u;\n");
        xml_.append("  $final $boolean v = $bool(t==8,$true,u=$false);\n");
        xml_.append("  $return u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $while(i<10i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i<10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<10i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i<10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<10i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument31FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $iter($int i=0;5;5){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   p=6i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument32FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $for($int i:{}){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   p=6i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument33FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int i,$int v:it){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   p=6i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument34FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $final $int p;\n");
        xml_.append(" $static{\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int i,$int v:it){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   p=6i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i<10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("  } $elseif(i<10i){\n");
        xml_.append("    p=10i;\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<10i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i<10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("  } $elseif(i<10i){\n");
        xml_.append("    p=10i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<10i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $if(i>10i){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  } $elseif(i>1i){\n");
        xml_.append("    p=10i;\n");
        xml_.append("    $return p;\n");
        xml_.append("  }\n");
        xml_.append("  $while(i<10i){\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=7i;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument43FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $final $int o;\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $int j=0i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $while($true)label{\n");
        xml_.append("   j=0i;\n");
        xml_.append("   $while($true)labeltwo{\n");
        xml_.append("    t+=1i;\n");
        xml_.append("    $if(j==2){\n");
        xml_.append("     o=10i;\n");
        xml_.append("     $break labeltwo;\n");
        xml_.append("    }\n");
        xml_.append("    t+=10i;\n");
        xml_.append("    $if(i==2){\n");
        xml_.append("     $break label;\n");
        xml_.append("    }\n");
        xml_.append("    t+=100i;\n");
        xml_.append("    j++;");
        xml_.append("   }\n");
        xml_.append("   i++;");
        xml_.append("  }\n");
        xml_.append("  $return o+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  } $while($true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int i;\n");
        xml_.append("  $final $int p;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=0i;\n");
        xml_.append("  $do{\n");
        xml_.append("   t+=1i;\n");
        xml_.append("   $if(i==2){\n");
        xml_.append("    p=6i;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   p=7i;\n");
        xml_.append("   t+=10i;\n");
        xml_.append("   i+=1i;\n");
        xml_.append("  } $while($true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int inst=m(),ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int inst=ance,ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int inst=ance,ance,bis;\n");
        xml_.append(" $public $static $final $int inst2=bis;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
