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
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>TextFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>TextFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc</c:catch><c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try><c:catch>Exc</c:catch><c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Int</c:catch><c:finally>{1/0}</c:finally></c:try><c:catch className='java.lang.Object' var='ex2'>Exc</c:catch></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>IntExc</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:catch>Int</c:catch><c:finally><c:throw value='$null'/></c:finally></c:try><c:catch>Exc</c:catch></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>IntExc</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try><c:catch/><c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>TextFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:finally>Finally</c:finally></c:try><c:catch>Exc</c:catch></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>FinallyExc</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try><c:throw value='$null'/></c:try><c:catch><c:throw value='$null'/></c:catch><c:finally>Finally</c:finally></c:try><c:catch>Exc</c:catch></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>FinallyExc</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc{1/0}</c:catch><c:finally>Finally</c:finally></c:try><c:catch className='java.lang.Object' var='ex'>Sec</c:catch></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinallySec</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:try>{1/0}</c:try><c:catch className='java.lang.Object' var='ex'>Exc<c:return/></c:catch><c:finally>Finally</c:finally></c:try><c:catch className='java.lang.Object' var='ex'>Sec</c:catch></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n<c:catch className='java.lang.Object' var='ex'>Exc</c:catch>\n<c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>{1/0}</c:try>\n<c:catch>First</c:catch>\n<c:catch className='java.lang.Object' var='ex'>Exc</c:catch>\n<c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try><c:throw value='$null'/></c:try>\n<c:catch className='java.lang.Object' var='ex'>First</c:catch>\n<c:catch>Exc</c:catch>\n<c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>ExcFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:try>Text</c:try>\n<c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>TextFinally</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:finally>Finally</c:finally></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
