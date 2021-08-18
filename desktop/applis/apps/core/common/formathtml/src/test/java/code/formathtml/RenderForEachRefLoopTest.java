package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderForEachRefLoopTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:set className='$int[]' value='arr={0,0,0,0}'/><c:set className='$int' value='v=1'/><c:for href='' var=\"s\" list=\"arr\" className='$int'><c:set value='s=v++'/></c:for><c:for var=\"s\" list=\"arr\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul><li>1</li><li>2</li><li>3</li><li>4</li></ul></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:set className='$int[]' value='arr={}'/><c:set className='$int' value='v=1'/><c:for href='' var=\"s\" list=\"arr\" className='$int'><c:set value='s=v++'/></c:for><c:for var=\"s\" list=\"arr\" className='$int'><li>{s}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body><ul/></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><ul><c:set className='$int[]' value='arr=$null'/><c:set className='$int' value='v=1'/><c:for href='' var=\"s\" list=\"arr\" className='$int'><c:set value='s=v++'/></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertNotNull(getEx(html_,new StringMap<String>()));
    }

    @Test
    public void process4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:set className='$int' value='v=0'/><c:set className='$int' value='s=0'/><c:for href='' className=\"$int\" init=\"e = $that(v)\" condition=\"e&lt;4\" step=\"e++\"><c:set value='s+=e'/></c:for>{s}-{v}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertEq("<html><body>6-4</body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void processFailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html><body><c:set className='$int[]' value='arr={0,0,0,0}'/><c:set className='$int' value='v=0'/><c:set className='$int' value='s=0'/><c:for href='' className=\"java.lang.Object\" init=\"e = $that(v)\" condition=\"e&lt;4\" step=\"e++\"><c:set value='s+=e'/></c:for>{s}-{vv}<c:for href='' var=\"s\" list=\"$new $iterable&lt;$int&gt;()\" className='$int'/><c:for href='' var=\"s\" list=\"arr\" className='java.lang.Object'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        assertTrue(hasErr(html_, new StringMap<String>()));
    }

    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }
    private Struct getEx(String _html, StringMap<String> _files) {
        return getCommEx(_html, _files);
    }
    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html,_files);
    }
}
