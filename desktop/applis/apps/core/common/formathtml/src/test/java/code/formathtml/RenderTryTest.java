package code.formathtml;



import code.util.StringMap;
import org.junit.Test;

public final class RenderTryTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:catch className='java.lang.Object' var='ex'/></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:catch/></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try><c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>{1/0}</c:catch></c:try><c:catch className='java.lang.Object' var='ex2'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:catch><c:throw value='$null'/></c:catch></c:try><c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='code.util.exceptions.NullObjectException' var='ex'>Npe</c:catch><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try><c:catch className='code.util.exceptions.NullObjectException' var='ex'>Npe</c:catch><c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch>Ne</c:catch><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch>Ne</c:catch></c:try><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='1/0'/></c:try><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc<c:return/></c:catch></c:try><c:catch className='java.lang.Object' var='ex'>Sec</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try>\n<c:catch className='java.lang.Object' var='ex'/></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try>\n<c:catch/></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n<c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process16Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try>\n<c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process17Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n<c:catch>First</c:catch>\n<c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process18Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try>\n<c:catch className='java.lang.Object' var='ex'>First</c:catch>\n<c:catch>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process19Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>{$Class.getClass(ex).getName()}</c:catch></body></html>";
        assertEq("<html><body>code.expressionlanguage.exceptions.DivideZeroException</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process20Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try label='lab'>{1/0}</c:try><c:catch/><c:catch className='java.lang.Object' var='ex'>Exc<c:break label='lab'/></c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process21Test() {
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex' condition='ex == $null'>Exc2</c:catch><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process22Test() {
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex' condition='ex != $null'>Exc</c:catch><c:catch className='java.lang.Object' var='ex'>Exc2</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process23Test() {
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex' condition='ex == 1/0'>Exc2</c:catch><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process24Test() {
        String html_ = "<html><body><c:try><c:throw value='2'/></c:try><c:catch value='1'>Exc2</c:catch><c:catch value='2'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process25Test() {
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex' condition='ex == $null'>Exc2</c:catch><c:finally><c:return/></c:finally></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process26Test() {
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex' condition='ex == 1/0' href='$throw'>Exc2</c:catch><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></c:try><c:catch className='java.lang.Object' var='ex'>Exc3</c:catch></body></html>";
        assertEq("<html><body>Exc3</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process27Test() {
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className=':' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process28Test() {
        String html_ = "<html><body><c:set className=\"$int\" value=\"arg=2,arg2=4\"/><c:try>{1/0}</c:try><c:catch className='java.lang.Object' condition='arg == 1' href='$throw'>Exc2</c:catch><c:catch className='java.lang.Object' var='ex'>Exc</c:catch></body></html>";
        assertEq("<html><body>Exc</body></html>", getRes(html_, new StringMap<String>()));
    }
    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html,_files);
    }

    @Test
    public void process0FailTest() {
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className=':' var='ex'>Exc</c:catch><c:catch>Exc</c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:catch className='java.lang.Object' var='ex'>First</c:catch>\n<c:catch>Exc</c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:catch className='java.lang.Object' var='ex'>First</c:catch>\n<c:catch>Exc</c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n</body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:catch>Exc</c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:catch>Exc</c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:catch className='java.lang.Object' var='#ex'/></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process8FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>1</c:try><c:catch className='java.lang.Object' var='ex'><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>{1/0}</c:catch></c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process9FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:catch className='java.lang.Object' var='ex'>Exc<c:break label='lab'/></c:catch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }
}
