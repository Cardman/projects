package code.formathtml;

import code.formathtml.structs.ValidatorInfo;
import code.util.StringMap;
import org.junit.Test;

public final class RenderReflectionTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{a[0]==a[1]}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>false</body></html>", getResTwoPagesTwo(folder_, relative_, html_,html2_, filesSec_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{a[0]==a[0]}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getResTwoPagesTwo(folder_, relative_, html_,html2_, filesSec_));
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{a[0]==1}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>false</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>page1.html</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{$Class.getClass(a[0])}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>code.bean.Document</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getDeclaringClass().getName()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>pkg.BeanOne</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getAnnotations().length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getDeclaredAnonymousLambda().length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getDeclaredAnonymousLambda().length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getDeclaredSwitchMethods().length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getDeclaredLocalTypes().length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/><c:set value='*a[0]'/>{a[0].getDeclaredAnonymousTypes().length}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getResOneBean(folder_, relative_, html_, files_, filesSec_));
    }

    @Test
    public void process13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{a[0].beanName()}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>bean_one</body></html>", getResTwoPagesTwo(folder_, relative_, html_,html2_, filesSec_));
    }

    @Test
    public void process14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{code.bean.Document.beanValue(a[0].beanName())==$this}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getResTwoPagesTwo(folder_, relative_, html_,html2_, filesSec_));
    }

    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{code.bean.Document.beanArr().length}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResTwoPagesTwo(folder_, relative_, html_,html2_, filesSec_));
    }

    @Test
    public void process16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{code.bean.Document.valArr().length}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class [code.bean.Message;] pkg.MyVal1:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class [code.bean.Message;] pkg.MyVal2:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getResTwoPagesTwo2(folder_, relative_, html_,html2_, filesSec_));
    }

    @Test
    public void process17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$var' value='a=code.bean.Document.all()'/>{code.bean.Document.valValue(code.bean.Document.valArr()[0])==code.bean.Document.valValue(code.bean.Document.valArr()[0])}</body></html>";
        String html2_ = "<html c:bean=\"bean_two\"><body>CONTENT</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html2_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append("}");
        file_.append("$public $class [code.bean.Message;] pkg.MyVal1:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class [code.bean.Message;] pkg.MyVal2:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $return $null;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getResTwoPagesTwo2(folder_, relative_, html_,html2_, filesSec_));
    }

    private String getResOneBean(String _folder, String _relative, String _html, StringMap<String> _files, StringMap<String> _filesSec, String... _types) {
        return getCommOneBean(_folder,_relative,_html,_files,_filesSec,_types);
    }
}
