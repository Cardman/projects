package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

public final class RenderImportTest extends CommonRender {
    @Test
    public void process0Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne{");
        file_.append(" $public $int[] array={1,2};");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>3</li><li>4</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process0_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{3,4}'/><param value=''/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne{");
        file_.append(" $public $int[] array={1,2};");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>3</li><li>4</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertEq("<html><body><td>Content</td></body></html>", getResTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:if condition='$true'>Next</c:if>After</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertEq("<html><body><td>Content</td>NextAfter</body></html>", getResTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t}</c:for>After</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertEq("<html><body><td>Content</td>1After</body></html>", getResTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t}</c:for>{2/0}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertNotNull(getExTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t}</c:for><c:set className='$int' value='i=1/0'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertNotNull(getExTwoPages(folder_, relative_, html_, htmlTwo_));
    }

    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:switch value='1'><c:case value='1'>Next</c:case></c:switch>After</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertEq("<html><body><td>Content</td>NextAfter</body></html>", getResTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><pre> </pre></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertEq("<html><body><pre> </pre></body></html>", getResTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>3</li><li>4</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:import page=\"&quot;page2.html&quot;\"/></c:try><c:catch className='java.lang.Object' var='e'>Exc</c:catch></body></html>";
        String htmlTwo_ = "<html><body><td>Content</td><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'>{t}</c:for><c:set className='$int' value='i=1/0'/>Not displayed</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        assertEq("<html><body><td>Content</td>1Exc</body></html>", getResTwoPages(folder_, relative_, html_, htmlTwo_));
    }
    @Test
    public void process10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:import page=\"&quot;page3.html&quot;\"><c:package name='pkg'><c:class name='BeanThree'><c:field prepare='$intern.nb=3'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getResThreeBean(folder_,relative_,html_, htmlTwo_, htmlThree_,filesSec_));
    }

    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:import page=\"&quot;page3.html&quot;\"><c:package name='pkg'><c:class name='BeanThree'><c:field prepare='$intern.nb=3/0'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExThreeBeans(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }
    @Test
    public void process12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:import page=\"&quot;page3.html&quot;\"><c:package name='pkg'><c:class name='BeanThree'><c:field prepare='$intern.nb=3'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  nb=1/0;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExThreeBeans(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }

    @Test
    public void process13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\">\n<c:package name='pkg'>\n<c:class name='BeanTwo'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class>\n<c:class name='BeanOne'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>3</li><li>4</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\">\n<c:package name='pkg'>\n<c:class name='BeanTwo'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class>\n<c:class name='BeanOne'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body/></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\">\n<c:package name='pkg'>\n<c:class name='BeanTwo'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class>\n<c:class name='BeanOne'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'/>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body/></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page3.html&quot;\">\n<c:package name='pkg'>\n<c:class name='BeanTwo'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class>\n<c:class name='BeanOne'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'/>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body/></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"1/0\">\n<c:package name='pkg'>\n<c:class name='BeanTwo'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class>\n<c:class name='BeanOne'>\n<c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'/>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>3</li><li>4</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process19Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:form form=\"array\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=$new $int[]{5,6};");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>3</li><li>4</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process20Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:form form=\"array2\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=$new $int[]{5,6};");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process21Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{5,6}'/></c:class></c:package><c:form form=\"array2\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=array;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getResTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process22Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{5,6}'/></c:class></c:package><c:form form=\"array2\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BadMap:code.formathtml.nat.StringMapObject{");
        file_.append(" $public Object getVal(String s){");
        file_.append("  $return values()[1];");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new BadMap());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=array;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process23Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{5,6}'/></c:class></c:package><c:form form=\"array2\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=array;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public code.formathtml.nat.StringMapObject getForms(){");
        file_.append("  $int i = 1/0;");
        file_.append("  $return $super.getForms();");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process24Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{5,6}'/></c:class></c:package><c:form form=\"array2\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append(" $public code.formathtml.nat.StringMapObject getForms(){");
        file_.append("  $var res=$super.getForms();");
        file_.append("  $if (res.isEmpty()){");
        file_.append("   $return res;");
        file_.append("  }");
        file_.append("  $int i = 1/0;");
        file_.append("  $return res;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=array;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process25Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\" keepfields=\"y\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{5,6}'/></c:class></c:package><c:form form=\"array2\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append(" $public code.formathtml.nat.StringMapObject getForms(){");
        file_.append("  $var res=$super.getForms();");
        file_.append("  $int i = 1/0;");
        file_.append("  $return res;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=($int[])getForms().getVal(\"array\");");
        file_.append("  $if (arrayBis==$null){");
        file_.append("   arrayBis=array;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertNotNull(getExTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process26Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html><body><ul><c:for var=\"s\" list=\"$new $int[]{5,6}\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" {");
        file_.append("  setForms($new code.formathtml.nat.StringMapObject());");
        file_.append(" }");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append("  getForms().put(\"array\",$new $int[]{3,4});");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getResTwoPagesOne(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process27Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'>\n<body>\n<c:try>\n<c:import page=\"&quot;page2.html&quot;\"/>\n</c:try>\n<c:catch var='e' className='java.lang.Exception'>\n{java.lang.Exception.toString(e)}\n</c:catch>\n</body>\n</html>";
        String htmlTwo_ = "<html c:bean='bean_two'>\n<body>\n\t<c:import page=\"&quot;page3.html&quot;\">\n<c:package name='pkg'>\n<c:class name='BeanThree'>\n<c:field prepare='$intern.nb=3'/>\n</c:class>\n</c:package>\n</c:import>\n</body>\n</html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{\n");
        file_.append(" $public $int[] array;\n");
        file_.append(" $public $void beforeDisplaying(){\n");
        file_.append("  array={1,2};\n");
        file_.append(" }\n");
        file_.append("}\n");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{\n");
        file_.append(" $public $int[] array;\n");
        file_.append(" $public $int[] arrayBis;\n");
        file_.append(" $public $void beforeDisplaying(){\n");
        file_.append("  arrayBis=array;\n");
        file_.append(" }\n");
        file_.append("}\n");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{\n");
        file_.append(" $public $int nb;\n");
        file_.append(" $public $void beforeDisplaying(){\n");
        file_.append("  callee();\n");
        file_.append(" }\n");
        file_.append(" $public $void callee(){\n");
        file_.append("  nb=1/0;\n");
        file_.append(" }\n");
        file_.append("}\n");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html>\n<body>\n\n\n\t\ncode.expressionlanguage.exceptions.DivideZeroException\n\npage1.html:4,17:56\npkg.BeanOne.\npage2.html:3,21:49\npkg.BeanTwo.\nmy_file:17,3:371\npkg.BeanThree.beforeDisplaying()\nmy_file:20,7:415\npkg.BeanThree.callee()\n\n</body>\n</html>", getResThreeBean(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }


    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:class name='BeanThree'/><c:import page=\"&quot;page3.html&quot;\"><c:package name='pkg'><c:class name='BeanThree'><c:field prepare='$intern.nb=3'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrThree(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }
    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:field name='BeanThree'/><c:import page=\"&quot;page3.html&quot;\"><c:package name='pkg'><c:class name='BeanThree'><c:field prepare='$intern.nb=3'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrThree(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }
    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:package name='BeanThree'/><c:import page=\"&quot;page3.html&quot;\"><c:package name='pkg'><c:class name='BeanThree'><c:field prepare='$intern.nb=3'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrThree(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }
    @Test
    public void process4FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><c:import page=\"&quot;page3.html&quot;\"><c:package name='pk'><c:class name='BeanThree'><c:field prepare='$intern.nb=3'/></c:class></c:package></c:import></body></html>";
        String htmlThree_ = "<html c:bean='bean_three'><body>{nb}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanThree:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrThree(folder_, relative_, html_, htmlTwo_, htmlThree_, filesSec_));
    }
    @Test
    public void process5FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html><body><c:import page=\"&quot;page2.html&quot;\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern.array=$new $int[]{3,4}'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne{");
        file_.append(" $public $int[] array={1,2};");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process6FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html><body><c:form name='pkg'/></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne{");
        file_.append(" $public $int[] array={1,2};");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
    @Test
    public void process7FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";

        String html_ = "<html c:bean='bean_one'><body><c:import page=\"&quot;page2.html&quot;\"><c:package name='pkg'><c:class name='BeanTwo'><c:field prepare='$intern()'/></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean='bean_two'><body><ul><c:for var=\"s\" list=\"arrayBis\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  array={1,2};");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanTwo:code.bean.Bean{");
        file_.append(" $public $int[] array;");
        file_.append(" $public $int[] arrayBis;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  arrayBis=array;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertTrue(hasErrTwoPagesTwo(folder_, relative_, html_, htmlTwo_, filesSec_));
    }
}
