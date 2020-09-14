package code.formathtml;

import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderSubmitTest extends CommonRender {

    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:submit message=\"msg_example,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        Configuration conf_ = contextElFive(new StringMap<String>());
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><input value=\"desc &amp;lt;text&amp;gt;\" type=\"submit\"/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example,three\" param0=\"{typedString}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><input value=\"desc &amp;lt;TITLE2&amp;gt;\" type=\"submit\"/></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html><body><c:submit message=\"msg_example,three\" param0=\"{1/0}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);


        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }

    @Test
    public void process1FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example,five\" param0=\"{typedString}\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }

    @Test
    public void process2FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example_bis,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }

    @Test
    public void process3FailTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:submit message=\"msg_example,three\" param0=\"text\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public String typedString;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  typedString=\"TITLE2\";");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", StringList.concat(relative_, "2"));
        setFiles(files_,conf_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithOneBean(html_, conf_);
        assertTrue(!conf_.isEmptyErrors());
    }
}
