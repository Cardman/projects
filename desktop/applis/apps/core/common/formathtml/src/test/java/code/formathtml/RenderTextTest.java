package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderTextTest extends CommonRender {
    @Test
    public void processDescTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"arg=2,arg2=4\"/>{arg},{arg2}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process0Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>[$int</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{escaped}</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'{1+1}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{escaped}2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{''escaped}'{1+1}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{'escaped}2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>''{1+1}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>'2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        Configuration conf_ = contextElFive(new StringMap<String>());
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(files_);
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(files_);
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(files_);
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$bool($true,1,0)}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>txt</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>txt,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>maniere,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>maniere,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:try><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/></c:try><c:finally><c:set className='pkg.BeanOne.Inner' value='i=&quot;man&quot;+&quot;iere&quot;'/></c:finally></body></html>";
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>maniere,1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>11,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>11,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>11,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>11,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>11,11</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>16,16</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>16,16</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>16,16</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>16,16</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>16,16</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,10</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1,5</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>10,10</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>5,5</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>5</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>5</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>0</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>true</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>true</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>true</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        Configuration conf_ = contextElFive();

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process51Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"StringBuilder\" value=\"arg\"/>{arg.append(0)}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
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
        StringMap<String> filesSec_ = new StringMap<String>();
        Configuration conf_ = contextElFive(filesSec_);

        setup(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{1+1</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>}1+1</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value='{(1})'/></body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{}<br/>'</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value=\"({)}&quot;&quot;\"/></body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$(Inexist)$null}</body></html>";
        Configuration conf_ = contextElFive();
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
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
        Configuration conf_ = contextElFive(files_);
        setup(folder_, relative_, conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
