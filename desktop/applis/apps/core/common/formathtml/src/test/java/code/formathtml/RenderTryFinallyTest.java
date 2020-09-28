package code.formathtml;



import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderTryFinallyTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:catch className='java.lang.Object' var='ex'/><c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>TextFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>TextFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc</c:catch><c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>ExcFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try><c:catch>Exc</c:catch><c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>ExcFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Int</c:catch><c:finally>{1/0}</c:finally></c:try><c:catch className='java.lang.Object' var='ex2'>Exc</c:catch></body></html>";
        assertEq("<html><body>IntExc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:catch>Int</c:catch><c:finally><c:throw value='$null'/></c:finally></c:try><c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>IntExc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:catch/><c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>TextFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:finally>Finally</c:finally></c:try><c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>FinallyExc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:catch><c:throw value='$null'/></c:catch><c:finally>Finally</c:finally></c:try><c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>FinallyExc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc{1/0}</c:catch><c:finally>Finally</c:finally></c:try><c:catch className='java.lang.Object' var='ex'>Sec</c:catch></body></html>";
        assertEq("<html><body>ExcFinallySec</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc<c:return/></c:catch><c:finally>Finally</c:finally></c:try><c:catch className='java.lang.Object' var='ex'>Sec</c:catch></body></html>";
        assertEq("<html><body>ExcFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n<c:catch className='java.lang.Object' var='ex'>Exc</c:catch>\n<c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>ExcFinally</body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n<c:catch>First</c:catch>\n<c:catch className='java.lang.Object' var='ex'>Exc</c:catch>\n<c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>ExcFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try>\n<c:catch className='java.lang.Object' var='ex'>First</c:catch>\n<c:catch>Exc</c:catch>\n<c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>ExcFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try>\n<c:finally>Finally</c:finally></body></html>";
        assertEq("<html><body>TextFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process16Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try label='lab'>Text</c:try><c:catch className='java.lang.Object' var='ex'/><c:finally>Finally<c:break label='lab'/></c:finally></body></html>";
        assertEq("<html><body>TextFinally</body></html>", getRes(html_, new StringMap<String>()));
    }
    private String getRes(String html_, StringMap<String> _files) {
        return getCommRes(html_,_files);
    }

    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:finally>Finally</c:finally></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:finally>Finally</c:finally></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:catch className='java.lang.Object' var='ex'/><c:finally>Finally<c:break label='lab'/></c:finally></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try label=','>NOT EMPTY</c:try></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String html_, StringMap<String> _files) {
        return hasCommErr(html_, _files);
    }
}
