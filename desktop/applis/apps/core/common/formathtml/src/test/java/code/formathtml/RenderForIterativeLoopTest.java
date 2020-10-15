package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderForIterativeLoopTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/>1 - 1<br/>2 - 2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/>2 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"-1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/>1 - 1<br/>2 - 2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"-2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/>2 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/>1 - 1<br/>0 - 2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/>0 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"-1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/>1 - 1<br/>0 - 2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" eq=\"true\" step=\"-2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/>0 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/>1 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"-1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/>1 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"-2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/>1 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" step=\"2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" step=\"-1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/>1 - 1<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"2\" to=\"0\" step=\"-2\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>2 - 0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"0\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k} - {([k])} - <c:for className=\"$int\" var=\"l\" from=\"0\" to=\"2\" step=\"1\">{l} - {([l])} -<br/></c:for>+<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0 - 0 - 0 -<br/>1 - 1 -<br/>+<br/>1 - 1 - 0 - 0 -<br/>1 - 1 -<br/>+<br/></body></html>", getRes(html_, new StringMap<String>()));
    }

    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html,_files);
    }

    @Test
    public void process19Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0/0\" to=\"2\" step=\"0\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process20Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2/0\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process21Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1/0\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process22Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:set className='java.lang.Integer' value='v'/><c:for className=\"java.lang.Integer\" var=\"k\" from=\"v\" to=\"2\" step=\"0\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process23Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:set className='java.lang.Integer' value='v'/><c:for className=\"java.lang.Integer\" var=\"k\" from=\"0\" to=\"v\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process24Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:set className='java.lang.Integer' value='v'/><c:for className=\"java.lang.Integer\" var=\"k\" from=\"0\" to=\"2\" step=\"v\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    private Struct getEx(String _html, StringMap<String> _files) {

        return getCommEx(_html, _files);
    }
    @Test
    public void process25Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"$new pkg.Ex(0)\" to=\"$new pkg.Ex(2)\" step=\"$new pkg.Ex(1)\">{k} - {([k])} - <c:for className=\"$int\" var=\"l\" from=\"0\" to=\"2\" step=\"1\">{l} - {([l])} -<br/></c:for>+<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{");
        xml_.append(" $private $int v;");
        xml_.append(" $public Ex($int p){");
        xml_.append("  v=p;");
        xml_.append(" }");
        xml_.append(" $public $static $int $(Ex e){");
        xml_.append("  $return e.v;");
        xml_.append(" }");
        xml_.append("}");
        filesSec_.put("pkg/Ex", xml_.toString());
        assertEq("<html><body>0 - 0 - 0 - 0 -<br/>1 - 1 -<br/>+<br/>1 - 1 - 0 - 0 -<br/>1 - 1 -<br/>+<br/></body></html>", getRes(html_, filesSec_));
    }
    @Test
    public void process26Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"1\" label='lab'>{k} - {([k])}<br/><c:break label='lab'/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>0 - 0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"#k\" from=\"&quot;0&quot;\" to=\"&quot;2&quot;\" eq=\"true\" step=\"&quot;1&quot;\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1\"><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k} - {([k])}<br/></c:for></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$var\" init=\"k=0\" condition=\"k;&lt;4\" step=\"k;++\"><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k} - {([k])}<br/></c:for></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"java.lang.String\" var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process5FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for indexClassName=\"java.lang.String\" className=\"$int\" var=\"k\" from=\"0\" to=\"2\" step=\"1\">{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process6FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:for className=\"$int\" var=\"k\" from=\"0\" to=\"2\" eq=\"true\" step=\"1\" label=','>{k} - {([k])}<br/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }
}
