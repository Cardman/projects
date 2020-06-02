package code.formathtml;



import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderDoWhileTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&lt;=2'/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    protected static RendDocumentBlock build(String html_, Configuration conf_) {
        return buildRendWithoutBean(html_,conf_);
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&gt;=2'/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>0<br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><c:while condition='i&gt;=2/0'/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do>\n<c:while condition='i&lt;=2'/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do><c:set className='$var' value='j=0'/><c:do>{i}-{j}<br/><c:set value='j++'/></c:do><c:while condition='j&lt;2'/>+<c:set value='i++'/></c:do><c:while condition='i&lt;2'/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>0-0<br/>0-1<br/>+1-0<br/>1-1<br/>+</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do>\n</body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do>\n<br/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:do>{i}<br/><c:set value='i++'/></c:do><br/></body></html>";
        Configuration conf_ = contextElFive();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        RendDocumentBlock rendDocumentBlock_ = build(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
