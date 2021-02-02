package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderTitledAnchorTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:a value=\"msg_example,three\" param0=\"TITLE\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><a title=\"desc &amp;lt;TITLE&amp;gt;\">Content</a></body></html>", getRes(folder_, relative_, html_, files_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:a value=\"msg_example,three\" param0=\"{1/0}\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(folder_, relative_, html_, files_, new StringMap<String>()));
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:a value=\"msg_example,five\">Content</c:a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(folder_, relative_, html_, files_, new StringMap<String>()));
    }


    private String getRes(String _folder, String _relative, String _html, StringMap<String> _f, StringMap<String> _files) {
        return getCommRes(_folder, _relative, _html, _f, _files);
    }


}
