package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderTextTest extends CommonRender {
    @Test
    public void processDescTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"arg=2,arg2=4\"/>{arg},{arg2}</body></html>";
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void processEmptyTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html/>";
        assertEq("<html/>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process0Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}}</body></html>";
        assertEq("<html><body>[$int</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'</body></html>";
        assertEq("<html><body>{escaped}</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'{1+1}</body></html>";
        assertEq("<html><body>{escaped}2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{''escaped}'{1+1}</body></html>";
        assertEq("<html><body>{'escaped}2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>''{1+1}</body></html>";
        assertEq("<html><body>'2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body></body></html>", getRes2(folder_, relative_, html_, files_));
    }

    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]/0+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertNotNull(getEx(folder_, relative_, html_, files_));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$bool($true,1,0)}</body></html>";
        assertEq("<html><body>1</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }

    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='String' value='i=$new pkg.BeanOne.Inner()'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>txt</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='String' value='i=$new pkg.BeanOne.Inner()'/>{i},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>txt,1</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_,"pkg.BeanOne..Inner"));
    }
    @Test
    public void process13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;maniere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>maniere,1</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_,"pkg.BeanOne..Inner"));
    }
    @Test
    public void process14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>maniere,1</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_,"pkg.BeanOne..Inner"));
    }
    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:try><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/></c:try><c:finally><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/></c:finally>{1/0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v/=0;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_,"pkg.BeanOne..Inner"));
    }
    @Test
    public void process16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $int j = 1/0;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;'/><c:set value='i+=&quot;iere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $operator+ String(Inner t, Inner v){");
        file_.append("   $return t.textField+v.textField;");
        file_.append("  }");
        file_.append("  $public $static String $(Inner t){");
        file_.append("   $return t.textField;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>maniere,1</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_,"pkg.BeanOne..Inner"));
    }
    @Test
    public void process18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;'/><c:set value='i+=&quot;iere&quot;'/>{i.textField},{pkg.BeanOne.v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int v;");
        file_.append(" $public $static $int count;");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static Inner $(String v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.textField=v+1/(count-2);");
        file_.append("   count++;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $operator+ String(Inner t, Inner v){");
        file_.append("   $return t.textField+v.textField;");
        file_.append("  }");
        file_.append("  $public $static String $(Inner t){");
        file_.append("   $return t.textField;");
        file_.append("  }");
        file_.append("  $static {");
        file_.append("   v++;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process19Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{(i++).count},{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process20Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{(++i).count},{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>11,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process21Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{(pkg.BeanOne.Inner.st++).count},{pkg.BeanOne.Inner.st.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static Inner st = $new Inner();");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process22Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{(++pkg.BeanOne.Inner.st).count},{pkg.BeanOne.Inner.st.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static Inner st = $new Inner();");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>11,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process23Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='pkg.BeanOne.Inner' init='i=$new pkg.BeanOne.Inner()' condition='' step=''>{(i++).count},{i.count}<c:break/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process24Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='pkg.BeanOne.Inner' init='i=$new pkg.BeanOne.Inner()' condition='' step=''>{(++i).count},{i.count}<c:break/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>11,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process25Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner[]' value='i={$new pkg.BeanOne.Inner()}'/>{(i[0]++).count},{i[0].count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process26Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner[]' value='i={$new pkg.BeanOne.Inner()}'/>{(++i[0]).count},{i[0].count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>11,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process27Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Cont' value='i=$new pkg.BeanOne.Cont()'/>{(i[0]++).count},{i[0].count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Cont{");
        file_.append("  $public Inner[] count={$new Inner()};");
        file_.append("  $public Inner $this($int v){");
        file_.append("   $return count[v];");
        file_.append("  }");
        file_.append("  $public $void $this($int t){");
        file_.append("   count[t]=$value;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process28Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Cont' value='i=$new pkg.BeanOne.Cont()'/>{(++i[0]).count},{i[0].count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Cont{");
        file_.append("  $public Inner[] count={$new Inner()};");
        file_.append("  $public Inner $this($int v){");
        file_.append("   $return count[v];");
        file_.append("  }");
        file_.append("  $public $void $this($int t){");
        file_.append("   count[t]=$value;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>11,11</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process29Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{(i++).count},{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count/0;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process30Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{(++i).count},{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v/0;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process31Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{(i+=6).count},{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>16,16</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process32Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{(pkg.BeanOne.Inner.st+=6).count},{pkg.BeanOne.Inner.st.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static Inner st = $new Inner();");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>16,16</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process33Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='pkg.BeanOne.Inner' init='i=$new pkg.BeanOne.Inner()' condition='' step=''>{(i+=6).count},{i.count}<c:break/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>16,16</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process34Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner[]' value='i={$new pkg.BeanOne.Inner()}'/>{(i[0]+=6).count},{i[0].count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>16,16</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process35Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Cont' value='i=$new pkg.BeanOne.Cont()'/>{(i[0]+=6).count},{i[0].count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Cont{");
        file_.append("  $public Inner[] count={$new Inner()};");
        file_.append("  $public Inner $this($int v){");
        file_.append("   $return count[v];");
        file_.append("  }");
        file_.append("  $public $void $this($int t){");
        file_.append("   count[t]=$value;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>16,16</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process36Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{(i+=6).count},{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner $($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count=v/0;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append("  $public $static $int $(Inner t){");
        file_.append("   $return t.count;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process37Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;j).count},{(i||j).count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,10</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process38Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;j).count},{(i||j).count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>1,5</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process39Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;j)?10:0},{(i||j)?10:0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10,10</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process40Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;j)?10:5},{(i||j)?10:5}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>5,5</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process41Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;j)?10:0},{(i||j)?10:0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count/0!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process42Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;j)?10:5},{(i||j)?10:5}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   o.count/=0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process43Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(5),j=$new pkg.BeanOne.Inner()'/>{(i&amp;&amp;=j)?10:5}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public Inner(){");
        file_.append("  }");
        file_.append("  $public Inner($int v){");
        file_.append("   count=v;");
        file_.append("  }");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>5</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process44Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(10),j=$new pkg.BeanOne.Inner()'/>{(i||=j)?5:10}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public Inner(){");
        file_.append("  }");
        file_.append("  $public Inner($int v){");
        file_.append("   count=v;");
        file_.append("  }");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>5</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process45Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{i?1:0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>1</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process46Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{i?1:0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process47Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{$staticCall(pkg.BeanOne.Inner).$true(i)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process48Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{$staticCall(pkg.BeanOne.Inner).$classchoice(pkg.BeanOne.Inner)$true(i)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process49Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner()'/>{$staticCall(pkg.BeanOne.Inner).$superaccess(pkg.BeanOne.Inner)$true(i)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| Inner(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:0;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process50Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body>{$class(java.lang.$math).getDeclaredMethods(&quot;mod&quot;,$true,$false,$class($int),$class($int))[0i].invoke($null,4i,3i)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        assertEq("<html><body>1</body></html>", getRes(folder_, relative_, html_, files_, new StringMap<String>()));
    }

    @Test
    public void process51Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"StringBuilder\" value=\"arg\"/>{arg.append(0)}</body></html>";
        assertNotNull(getEx(folder_, relative_, html_, new StringMap<String>()));
    }

    @Test
    public void process52Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{($(pkg.MyAnnotation)$class(pkg.BeanOne).getAnnotations()[0]).infoInt()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $annotation pkg.MyAnnotation{");
        file_.append(" $int infoInt()1;");
        file_.append("}");
        file_.append("@MyAnnotation\n");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>1</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process53Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='a=5,b=3'/>{pkg.BeanOne.exmeth(j:a,k:b)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($int j,$int k){\n");
        file_.append("  $return 1+j+k;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>9</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process54Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='a=5,b=3'/>{pkg.BeanOne.exmeth(k:b,j:a)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($int j,$int k){\n");
        file_.append("  $return 1+j+k;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>9</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process55Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$lambda(pkg.BeanOne.Inner,$new,$int).call(4)'/>{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public Inner($int v){");
        file_.append("   count=v;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process56Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$static().$lambda(pkg.BeanOne.Inner,method,$int).call(4)'/>{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner method($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count = v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process57Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int[]' value='i=$new $int[]{4}'/>{i.$lambda($int[],[],$int).call(0)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner method($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count = v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process58Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int[]' value='i=$lambda($int[],$new,$int).call(4)'/>{i.length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner method($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count = v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process59Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int[]' value='i=$lambda($int[],clone).call($new $int[]{4})'/>{i[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner method($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count = v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process60Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Integer' value='i=$lambda(Integer,$,Integer).call(4)'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner method($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count = v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process61Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Inner' value='i=$lambda($operator,+,Inner,Inner).call($new Inner(),$new Inner())'/>{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$operator+ pkg.BeanOne.Inner(pkg.BeanOne.Inner a, pkg.BeanOne.Inner b){");
        file_.append(" pkg.BeanOne.Inner c = $new pkg.BeanOne.Inner();");
        file_.append(" c.count = a.count + b.count;");
        file_.append(" $return c;");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=10;");
        file_.append("  $public $static Inner method($int v){");
        file_.append("   Inner i = $new Inner();");
        file_.append("   i.count = v;");
        file_.append("   $return i;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>20</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process62Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Inner' value='i=$static().$lambda(BeanOne.Inner,valueOf,String).call(&quot;ONE&quot;)'/>{i.$name()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $enum Inner{");
        file_.append("  ONE;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>ONE</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process63Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanOne.Inner' value='i=$new pkg.BeanOne.Inner(10),j=$new pkg.BeanOne.Inner()'/>{(i|||=j)?5:10}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count=1;");
        file_.append("  $public Inner(){");
        file_.append("  }");
        file_.append("  $public Inner($int v){");
        file_.append("   count=v;");
        file_.append("  }");
        file_.append("  $public $static $boolean $true(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static $boolean $false(Inner v){");
        file_.append("   $return v.count!=10;");
        file_.append("  }");
        file_.append("  $operator&& $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10&&b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $operator|| $boolean(Inner a, Inner b){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=a.count==10||b.count==10?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append("  $public $static $boolean $(Inner v){");
        file_.append("   $return v.count==10;");
        file_.append("  }");
        file_.append("  $public $static Inner $($boolean v){");
        file_.append("   Inner o = $new Inner();");
        file_.append("   o.count=v?10:5;");
        file_.append("   $return o;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>5</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process64Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth($that(count))'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process65Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth(t:$that(count))'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process66Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth($that(count))'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int count=1;");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process67Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth($that($this.count))'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process68Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth($that(pkg.BeanOne.count))'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int count=1;");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process69Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth($that(count[0]))'/>{count[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] count={1};");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process70Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1,j=4'/><c:set value='exmeth(a:$that(i),b:$that(j))'/>{i}-{j}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $static $void exmeth($that $int a,$that $int b){\n");
        file_.append("  a*=8;\n");
        file_.append("  b+=3;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8-7</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process71Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1,j=4'/><c:set value='exmeth(b:$that(i),a:$that(j))'/>{i}-{j}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $static $void exmeth($that $int a,$that $int b){\n");
        file_.append("  a*=8;\n");
        file_.append("  b+=3;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4-32</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process72Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1'/><c:set href='' className='$int' value='j=$that(i)'/><c:set value='j+=3'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process73Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1'/><c:set href='' className='$int' value='j=$that(i)'/><c:set value='j=3'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process74Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1'/><c:set href='' className='$int' value='j=$that(i)'/><c:set value='j++'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process75Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1'/><c:set href='' className='$int' value='j=$that(i)'/><c:set value='++j'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process76Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='i=&quot;1&quot;'/><c:set href='' className='$var' value='j=$that(i)'/><c:set value='j+=3'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>13</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process77Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=1'/><c:set href='' className='$int' value='j=$that(i)'/><c:set href='' className='$int' value='k=$that(j)'/><c:set value='k+=3'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process78Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='i=$new Inner(1)'/><c:set className='$var' value='j=$new Inner(3)'/><c:set href='' className='$var' value='k=$that(i)'/><c:set value='k+=j'/>{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count;");
        file_.append("  $public Inner($int p){");
        file_.append("   count = p;");
        file_.append("  }");
        file_.append("  $operator+ Inner(Inner v,Inner w){");
        file_.append("   $return $new Inner(v.count+w.count);");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process79Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='i=$new Inner(3)'/><c:set href='' className='$var' value='k=$that(i)'/><c:set value='k++'/>{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count;");
        file_.append("  $public Inner($int p){");
        file_.append("   count = p;");
        file_.append("  }");
        file_.append("  $operator++ Inner(Inner v){");
        file_.append("   $return $new Inner(v.count+1);");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process80Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='i=$new Inner(3)'/><c:set href='' className='$var' value='k=$that(i)'/><c:set value='++k'/>{i.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count;");
        file_.append("  $public Inner($int p){");
        file_.append("   count = p;");
        file_.append("  }");
        file_.append("  $operator++ Inner(Inner v){");
        file_.append("   $return $new Inner(v.count+1);");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process81Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set href='' className='$var' value='i=$that(exmeth())'/><c:set value='i+=3'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process82Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth()+=3'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process83Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth()=3'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process84Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='(exmeth())=3'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process85Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth()+=&quot;3&quot;'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String count=\"1\";");
        file_.append(" $public $that String exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>13</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process86Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='++exmeth()'/>{count.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static Inner count=$new Inner(3);");
        file_.append(" $public $static $that Inner exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count;");
        file_.append("  $public Inner($int p){");
        file_.append("   count = p;");
        file_.append("  }");
        file_.append("  $operator++ Inner(Inner v){");
        file_.append("   $return $new Inner(v.count+1);");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process87Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth()++'/>{count.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static Inner count=$new Inner(3);");
        file_.append(" $public $static $that Inner exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count;");
        file_.append("  $public Inner($int p){");
        file_.append("   count = p;");
        file_.append("  }");
        file_.append("  $operator++ Inner(Inner v){");
        file_.append("   $return $new Inner(v.count+1);");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process88Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='j=$new Inner(5)'/><c:set value='exmeth()+=j'/>{count.count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static Inner count=$new Inner(3);");
        file_.append(" $public $static $that Inner exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public $int count;");
        file_.append("  $public Inner($int p){");
        file_.append("   count = p;");
        file_.append("  }");
        file_.append("  $operator+ Inner(Inner v,Inner w){");
        file_.append("   $return $new Inner(v.count+w.count);");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process89Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='exmeth()++'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=3;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process90Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='++exmeth()'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=3;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process91Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{exmeth()++}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=3;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process92Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{++exmeth()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=3;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process93Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{$new pkg.Rec(count:10).count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public @$class pkg.Rec{");
        file_.append(" $public $int count;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process94Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{$lambda(pkg.Rec,$new,count).call(10).count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public @$class pkg.Rec{");
        file_.append(" $public $int count;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>10</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process95Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set href='' className='$var' value='i=$that($this.exmeth())'/><c:set value='i+=3'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $that $int exmeth(){\n");
        file_.append("  $return $that(count);\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process96Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set value='$lambda(pkg.BeanOne,exmeth,~$int).call($that(count))'/>{count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int count=1;");
        file_.append(" $public $static $void exmeth($that $int t){\n");
        file_.append("  t=8;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>8</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process97Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{exmeth($that($true?$that(i):$that(j)))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($that $int t){\n");
        file_.append("  $return t;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process98Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{exmeth($that($false?$that(i):$that(j)))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($that $int t){\n");
        file_.append("  $return t;\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process99Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{exmeth($that($new ExBool(1)?$that(i):$that(j)))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($that $int t){\n");
        file_.append("  $return t;\n");
        file_.append(" }\n");
        file_.append(" $public $static $class ExBool {\n");
        file_.append("  $int f;\n");
        file_.append("  ExBool($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $public $static $boolean $true(ExBool v){\n");
        file_.append("   $return v.f == 1;\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process100Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{exmeth($that($new ExBool(0)?$that(i):$that(j)))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($that $int t){\n");
        file_.append("  $return t;\n");
        file_.append(" }\n");
        file_.append(" $public $static $class ExBool {\n");
        file_.append("  $int f;\n");
        file_.append("  ExBool($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $public $static $boolean $true(ExBool v){\n");
        file_.append("   $return v.f == 1;\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process101Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{exmeth($that($new ExBool(1)?$that(i):$that(j)))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($that $int t){\n");
        file_.append("  $return t;\n");
        file_.append(" }\n");
        file_.append(" $public $static $class ExBool {\n");
        file_.append("  $int f;\n");
        file_.append("  ExBool($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $public $static $boolean $(ExBool v){\n");
        file_.append("   $return v.f == 1;\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process102Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{exmeth($that($new ExBool(0)?$that(i):$that(j)))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $int exmeth($that $int t){\n");
        file_.append("  $return t;\n");
        file_.append(" }\n");
        file_.append(" $public $static $class ExBool {\n");
        file_.append("  $int f;\n");
        file_.append("  ExBool($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $public $static $boolean $(ExBool v){\n");
        file_.append("   $return v.f == 1;\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process103Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/><c:set value='($true?$that(i):$that(j))=4'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process104Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/><c:set value='($false?$that(i):$that(j))=4'/>{j}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process105Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=4'/><c:set value='($true?$that(i):$that(j))++'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process106Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/><c:set value='($false?$that(i):$that(j))++'/>{j}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process107Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=4'/><c:set value='++($true?$that(i):$that(j))'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process108Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/><c:set value='++($false?$that(i):$that(j))'/>{j}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process109Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=4'/>{($true?$that(i):$that(j))++}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process110Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{($false?$that(i):$that(j))++}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process111Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=4'/>{++($true?$that(i):$that(j))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process112Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{++($false?$that(i):$that(j))}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process113Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/><c:set value='($true?$that(i):$that(j))+=4'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>6</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process114Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/><c:set value='($false?$that(i):$that(j))+=4'/>{j}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>7</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process115Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{($true?$that(i):$that(j))+=4}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>6</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process116Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int' value='i=2,j=3'/>{($false?$that(i):$that(j))+=4}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>7</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process117Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/><c:set value='($true?$that(i):$that(j))+=$new Compo(4)'/>{i.f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator+ Compo (Compo a, Compo b){\n");
        file_.append("   $return $new Compo(a.f+b.f);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>6</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process118Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/><c:set value='($false?$that(i):$that(j))+=$new Compo(4)'/>{j.f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator+ Compo (Compo a, Compo b){\n");
        file_.append("   $return $new Compo(a.f+b.f);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>7</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process119Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/>{(($true?$that(i):$that(j))+=$new Compo(4)).f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator+ Compo (Compo a, Compo b){\n");
        file_.append("   $return $new Compo(a.f+b.f);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>6</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process120Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/>{(($false?$that(i):$that(j))+=$new Compo(4)).f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator+ Compo (Compo a, Compo b){\n");
        file_.append("   $return $new Compo(a.f+b.f);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>7</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process121Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(4)'/><c:set value='($true?$that(i):$that(j))++'/>{i.f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process122Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/><c:set value='($false?$that(i):$that(j))++'/>{j.f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process123Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(4)'/>{(($true?$that(i):$that(j))++).f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process124Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/>{(($false?$that(i):$that(j))++).f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process125Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(4)'/><c:set value='++($true?$that(i):$that(j))'/>{i.f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process126Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/><c:set value='++($false?$that(i):$that(j))'/>{j.f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process127Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(4)'/>{(++($true?$that(i):$that(j))).f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process128Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='Compo' value='i=$new Compo(2),j=$new Compo(3)'/>{(++($false?$that(i):$that(j))).f}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $static $class Compo {\n");
        file_.append("  $public $int f;\n");
        file_.append("  $public Compo($int p){\n");
        file_.append("   f = p;\n");
        file_.append("  }\n");
        file_.append("  $operator++ Compo (Compo a){\n");
        file_.append("   $return $new Compo(a.f+1);\n");
        file_.append("  }\n");
        file_.append(" }\n");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>4</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }
    @Test
    public void process129Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"java.lang.$Fct&lt;$int,$int,$int&gt;\" value=\"l=$static().$lambda(java.lang.$math,max,$int,$int)\"/>{l.call(a:{2,1})}</body></html>";
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process130Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"java.lang.$Fct&lt;$int,$int,$int&gt;\" value=\"l=$static().$lambda(java.lang.$math,max,$int,$int)\"/>{l.call($id(java.lang.$Fct,java.lang.Object...),2,1)}</body></html>";
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process131Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$staticCall(pkg.Ex&lt;$int&gt;).cat($new pkg.Ex&lt;&gt;(2,4))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+a.inst[0]+\",\"+a.inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process132Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='pkg.Ex&lt;$int&gt;' value='v=$new(2,4)'/>{$staticCall(pkg.Ex&lt;$int&gt;).cat(v)}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+a.inst[0]+\",\"+a.inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process133Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='pkg.Ex&lt;$int&gt;[]' value='v=$new pkg.Ex&lt;&gt;[]{$new pkg.Ex&lt;&gt;(2,4)}'/>{$staticCall(pkg.Ex&lt;$int&gt;).cat(v[0])}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+a.inst[0]+\",\"+a.inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process134Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='pkg.Ex&lt;$int&gt;[]' value='v=$new pkg.Ex&lt;&gt;[1]'/><c:set value='v[0]=$new pkg.Ex&lt;&gt;(2,4)'/>{$staticCall(pkg.Ex&lt;$int&gt;).cat(v[0])}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+a.inst[0]+\",\"+a.inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process135Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='pkg.Ex&lt;$int&gt;[]' value='v=$new[1]'/><c:set value='v[0]=$new(2,4)'/>{$staticCall(pkg.Ex&lt;$int&gt;).cat(v[0])}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+a.inst[0]+\",\"+a.inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process136Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$staticCall(pkg.Ex&lt;$int&gt;).cat($new { } pkg.Ex&lt;&gt;(2,4))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+a.inst[0]+\",\"+a.inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process137Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$staticCall(pkg.Ex&lt;$int[]&gt;).cat($new pkg.Ex&lt;$int[]&gt;($new $int[]{2},$new $int[]{4}))}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public Ex(T... p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall String cat(Ex<T> a){\n");
        xml_.append("  $return \"\"+(($int[])a.inst[0])[0]+\",\"+(($int[])a.inst[1])[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2,4</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process138Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='pkg.ExClass' value='e=$new pkg.ExClass()'/><c:set value='e.field=2'/>{$staticCall(pkg.ExClass).$true($id(pkg.ExClass,pkg.ExClass),e)?\"Vrai\":\"Faux\"}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append("  $return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append("  $return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>Faux</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process139Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{pkg.ExClass.values().length}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExClass {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process140Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{($new $int[]{2,4,6,8})[1???3].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process141Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{($new $int[]{2,4,6,8})[(1???)].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>3</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process142Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int[]\" value=\"v=$new $int[]{2,4,6,8}\"/>{v[1???3].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process143Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"v=1\"/>{($new $int[]{2,4,6,8})[v???3].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process144Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int[]\" value=\"v=$null\"/>{v[1???].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertNotNull(getEx(folder_, relative_, html_, files_));
    }
    @Test
    public void process145Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"v=1\"/>{($new $int[]{2,4,6,8})[v???].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>3</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process146Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"v=1\"/>{($new $int[]{2,4,6,8})[v???3???1].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process147Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"v=1\"/>{($new $int[]{2,4,6,8})[v??????1].length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>3</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process148Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$long\" value=\"v=*$null\"/>0</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertEq("<html><body>0</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process149Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{*$new pkg.ExClass()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $long $null(){$return 2;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>2</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process150Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"pkg.ExCont&lt;$int&gt;\" value=\"inst=$new(0,2)\"/><c:set href='' className=\"$int\" value=\"v=$that(inst[0])\"/><c:set value=\"v=3\"/>{inst[0]}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" T[] field = $new T[1];\n");
        xml_.append(" $public ExCont($int i, T j){\n");
        xml_.append("  field[i]=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  field[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>3</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    @Test
    public void process151Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"pkg.ExCont&lt;java.lang.String&gt;\" value=\"inst=$new(0,&quot;2&quot;)\"/><c:set value=\"inst[0]+=3\"/>{inst[0]}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" T[] field = $new T[1];\n");
        xml_.append(" $public ExCont($int i, T j){\n");
        xml_.append("  field[i]=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  field[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>23</body></html>", getRes2(folder_, relative_, html_, files_));
    }
    private Struct getExOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec, String... _types) {
        return getCommExOneBean(_folder,_relative,_html,_files,_filesSec,_types);
    }

    private String getRes(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec) {
        return getCommRes(_folder,_relative,_html,_files,_filesSec);
    }


    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec, String... _types) {
        return getCommOneBean(_folder,_relative,_html,_files,_filesSec,_types);
    }
    private Struct getEx(String _folder, String _relative, String _html, StringMap<String> _files) {
        return getCommEx(_folder, _relative, _html, _files);
    }

    @Test
    public void process0FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body>{(i&&j).count},{(i||j).count}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        assertTrue(hasErrOneBean(folder_, relative_, html_, files_));
    }

    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{1+1</body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>}1+1</body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value='{(1})'/></body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{</body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{}<br/>'</body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value=\"({)}&quot;&quot;\"/></body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$(Inexist)$null}</body></html>";
        assertTrue(hasErr(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process8FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(folder_, relative_, html_, files_));
    }
    @Test
    public void process9FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><l>{$new}</l><l>{$new {r}</l><l>{$new {}</l><l>{$new $int{}}</l><l>{$new</l></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertTrue(hasErr(folder_, relative_, html_, files_));
    }

    private boolean hasErrOneBean(String _folder, String _relative, String _html, StringMap<String> _files) {
        StringMap<String> filesSec_ = new StringMap<String>();
        return hasCommErrOneBean(_folder,_relative,_html,_files,filesSec_);
    }

}
