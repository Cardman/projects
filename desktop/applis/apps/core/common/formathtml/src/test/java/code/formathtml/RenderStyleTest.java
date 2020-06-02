package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderStyleTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>.classTest{color:blue;}CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>{0}CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String html_ = "<html><head><style>CONTENT</style><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><style>CONTENT.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String html_ = "<html><head><style/><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><style>.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><link rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String html_ = "<html><link href=\"main.css\" rel=\"stylesheet\"/><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process7Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><link href=\"main.css\"/></head><style>CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main1.css", ".classTest{color:blue;}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;+1/0};'}'\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        Configuration conf_ = contextElFive();
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getAnalyzingDoc().setFiles(files_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,conf_);
        assertNotNull(getException(conf_));
    }

}
