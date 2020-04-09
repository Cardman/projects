package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderTextTest extends CommonRender {
    @Test
    public void process0Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>[$int</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{escaped}</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{escaped}'{1+1}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{escaped}2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>'{''escaped}'{1+1}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>{'escaped}2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>''{1+1}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>'2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new $int[]{0,1}.length}</body></html>";
        Configuration conf_ = contextElThird(new StringMap<String>());
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>2,4</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]/0+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$bool($true,1,0)}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body>1</body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{1+1</body></html>";
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
        String html_ = "<html><body>}1+1</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value='{(1})'/></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>1+1{}<br/>'</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set value=\"({)}&quot;&quot;\"/></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$(Inexist)$null}</body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
    @Test
    public void process8FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>{$new pkg.Ex()}</body></html>";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4}:\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = contextElThird(files_);
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
