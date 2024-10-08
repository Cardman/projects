package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderForEachLoopTest extends CommonRender {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";

    @Test
    public void process1Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><body><ul><c:for var=\"s\" list=\"$new java.lang.String[]{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='java.lang.String'><li>{s.length()}</li><c:continue/></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><body><ul><c:for var=\"s\" list=\"$new java.lang.String[]{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='$var'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"$new java.lang.Integer[]{$null}\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        Struct exc_ = getEx(html_, new StringMap<String>());
        assertNotNull(exc_);
    }
    @Test
    public void process4Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", getRes(html_, files_));
    }

    @Test
    public void process5Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;()\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html/>", getRes(html_, files_));
    }
    @Test
    public void process6Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><ul><c:for var=\"s\" list=\"array\" className='$int'><li>{s}</li></c:for></ul></body></html>";
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
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body><ul><li>1</li><li>2</li></ul></body></html>", getResOneBean(html_, filesSec_));
    }

    @Test
    public void process7Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"$new java.lang.Integer[]{}\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"$new java.lang.Integer[]{0}\" className='$int'><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'><li>{s}-{t}</li></c:for></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li>0-1</li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process9Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:if condition='$true'><li><ol><c:for var=\"t\" list=\"$new java.lang.Integer[]{1}\" className='$int'><li>{t}</li></c:for></ol></li></c:if></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li><ol><li>1</li></ol></li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process10Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:set className='$int[]' value='arr'/><c:for var=\"s\" list=\"arr\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        Struct exc_ = getEx(html_, new StringMap<String>());
        assertNotNull(exc_);
    }

    @Test
    public void process11Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomFailIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertNotNull(getEx(html_, files_));
    }
    @Test
    public void process12Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomFailIteratorBis());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertNotNull(getEx(html_, files_));
    }
    @Test
    public void process13Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomFailList());
        assertNotNull(getEx(html_, files_));
    }

    @Test
    public void process14Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;(0,1/0,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertNotNull(getEx(html_, files_));
    }
    @Test
    public void process15Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"(pkg.CustList&lt;?java.lang.Integer&gt;)$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", getRes(html_, files_));
    }
    @Test
    public void process16Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"(pkg.CustList&lt;!java.lang.Integer&gt;)$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='java.lang.Object'>{i}-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html>0-1-2-3-</html>", getRes(html_, files_));
    }
    @Test
    public void process17Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"(pkg.CustList&lt;?&gt;)$new pkg.CustList&lt;java.lang.Integer&gt;(0,1,2,3)\" className='java.lang.Object'>{i}-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html>0-1-2-3-</html>", getRes(html_, files_));
    }
    @Test
    public void process18Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><ul><c:for var=\"s\" list=\"array\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustList<$int> array = $new pkg.CustList<>(0,1,2,3);");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html><body><ul><li>0</li><li>1</li><li>2</li><li>3</li></ul></body></html>", getResOneBean(html_, filesSec_));
    }

    @Test
    public void process19Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><ul><c:for var=\"s\" list=\"array\" className='$var'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustList<$int> array = $new pkg.CustList<>(0,1,2,3);");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html><body><ul><li>0</li><li>1</li><li>2</li><li>3</li></ul></body></html>", getResOneBean(html_, filesSec_));
    }
    @Test
    public void process20Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><body><ul><c:for var=\"s\" list=\"{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='java.lang.String'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process21Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;&gt;(0,1,2,3)\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", getRes(html_, files_));
    }

    @Test
    public void process22Test() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:set className='$var' value='l=$(pkg.CustList&lt;$int&gt;)$null'/><c:for var=\"i\" list=\"l\" className='$int'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertNotNull(getEx(html_, files_));
    }

    @Test
    public void process23Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><body><ul><c:for var=\"s\" list=\"$new java.lang.String[]{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='java.lang.String' label='lab'><li>{s.length()}</li><c:break label='lab'/></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li>5</li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }
    private Struct getEx(String _html, StringMap<String> _files) {
        return getCommEx(_html, _files);
    }


    private String getResOneBean(String _html, StringMap<String> _filesSec) {
        return getCommOneBean(_html, _filesSec);
    }

    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html,_files);
    }
    @Test
    public void process1FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"s\" list=\"0\" className='java.lang.Integer'><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }

    @Test
    public void process2FailTest() {
        String html_ = "<html><c:for var=\"i\" list=\"$null\" className='$var'>{i}-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertTrue(hasErr(html_, files_));
    }

    @Test
    public void process3FailTest() {
        String html_ = "<html><c:for var=\"i\" list=\"$bool($true,$new pk.Cl1[]{$new pk.Cl1()},$new pk.Cl2[]{$new pk.Cl2()})\" className='$var'>{i}-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pk.Int1{}");
        xml_.append("$public $interface pk.Int2{}");
        xml_.append("$public $interface pk.Int3:Int1:Int2{}");
        xml_.append("$public $interface pk.Int4:Int1:Int2{}");
        xml_.append("$public $class pk.Cl1:Int3{}");
        xml_.append("$public $class pk.Cl2:Int4{}");
        files_.put("my_file",xml_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process4FailTest() {
        String html_ = "<html><c:for var=\"i\" list=\"$bool($true,$new pk.Cl1[]{$new pk.Cl1()},$new pk.Cl2[]{$new pk.Cl2()})\" className='$int'>{i}-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pk.Int1{}");
        xml_.append("$public $interface pk.Int2{}");
        xml_.append("$public $interface pk.Int3:Int1:Int2{}");
        xml_.append("$public $interface pk.Int4:Int1:Int2{}");
        xml_.append("$public $class pk.Cl1:Int3{}");
        xml_.append("$public $class pk.Cl2:Int4{}");
        files_.put("my_file",xml_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process5FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"#s\" list=\"0\" className='java.lang.Integer'>0</c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process6FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for var=\"#s\" list=\"0\" className='java.lang.Integer'><c:for var=\"#s\" list=\"0\" className='java.lang.Integer'>0</c:for></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process7FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:for className=\"$int\" init=\"s=0\" condition=\"\" step=\"i;++\"><c:for var=\"s\" list=\"0\" className='java.lang.Integer' indexClassName='java.lang.String'>0</c:for></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process8FailTest() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><c:for var=\"i\" list=\"$new pkg.CustList&lt;java.lang.Integer&gt;()\" className='java.lang.String'>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertTrue(hasErr(html_, files_));
    }

    @Test
    public void process9FailTest() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><ul><c:for var=\"s\" list=\"array\" className='java.lang.String'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustList<$int> array = $new pkg.CustList<>(0,1,2,3);");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        assertTrue(hasErrOneBean(html_, filesSec_));
    }

    @Test
    public void process10FailTest() {
        String html_ = "<>lt&60;gt&62;amp&38;quot&34;<html><body><ul><c:for var=\"s\" list=\"$new java.lang.String[]{&quot;FIRST&quot;,&quot;SECOND&quot;}\" className='java.lang.String' label=','><li>{s.length()}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        assertTrue(hasErr(html_,files_));
    }
    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }


    private static String getCustomFailList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public (U...elts){\n");
        xml_.append("  list=elts;\n");
        xml_.append("  length=elts.length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $int i = 1/0;\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }

    private static String getCustomFailIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index/=0;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomFailIteratorBis() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index/0<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }

    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public (U...elts){\n");
        xml_.append("  list=elts;\n");
        xml_.append("  length=elts.length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
