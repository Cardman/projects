package code.formathtml;


import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.structs.Struct;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderWhileTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&lt;=2'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&gt;=2'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&lt;2'><c:set className='$var' value='j=0'/><c:while condition='j;.&lt;2'>{i;.}-{j;.}<br/><c:set value='j;.++'/></c:while><br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>0-0<br/>0-1<br/><br/>1-0<br/>1-1<br/><br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&gt;=2/0'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i;.&gt;=2/(i;.-1)'>{i;.}<br/><c:set value='i;.++'/></c:while></body></html>";
        Configuration conf_ = contextElThird();
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:bean='bean_one'><body><c:while condition='nb&lt;=2'>{nb}<br/><c:set value='nb++'/></c:while></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb:");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setMessagesFolder(folder_);
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        addBeanInfo(conf_,"bean_one",bean_);
        conf_.clearPages();
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:bean='bean_one'><body>Loop:<c:while condition='nb&lt;=2'>{nb}<br/><c:set value='nb++'/></c:while></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb:");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElThird(filesSec_);
        conf_.setMessagesFolder(folder_);
        addImportingPage(conf_);
        Struct bean_ = RenderExpUtil.processEl("$new pkg.BeanOne()", 0, conf_).getStruct();
        addBeanInfo(conf_,"bean_one",bean_);
        conf_.clearPages();
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        Document doc_ = DocumentBuilder.parseSax(html_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        conf_.getContext().setAnalyzing(new AnalyzedPageEl());
        conf_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(conf_);
        assertTrue(conf_.getClasses().isEmptyErrors());
        assertEq("<html><body>Loop:0<br/>1<br/>2<br/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
}
