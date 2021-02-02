package code.formathtml;



import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderDoWhileTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&lt;=2'/></body></html>";
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&gt;=2'/></body></html>";
        assertEq("<html><body>0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&gt;=2/0'/></body></html>";
        assertNotNull(getEx(html_, new StringMap<String>()));
    }


    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do>\n<c:while condition='i&lt;=2'/></body></html>";
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do><c:set className='$var' value='j=0'/><c:do>{i}-{j}<br/><c:set value='j++'/></c:do><c:while condition='j&lt;2'/>+<c:set value='i++'/></c:do><c:while condition='i&lt;2'/></body></html>";
        assertEq("<html><body>0-0<br/>0-1<br/>+1-0<br/>1-1<br/>+</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do label='lab'>{i}<br/><c:set value='i++'/><c:break label='lab'/></c:do><c:while condition='i&lt;=2'/></body></html>";
        assertEq("<html><body>0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    private Struct getEx(String _html, StringMap<String> _files) {
        return getCommEx(_html,_files);
    }

    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html,_files);
    }

    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do>\n</body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do>\n<br/></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><br/></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do label=','>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&lt;=2'/></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }
}
