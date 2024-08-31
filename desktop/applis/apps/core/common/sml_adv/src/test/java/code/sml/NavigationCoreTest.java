package code.sml;

import code.formathtml.util.FormInputCoords;
import code.formathtml.util.IndexButtons;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class NavigationCoreTest extends EquallableSmlAdvUtil {
    @Test
    public void setupText1() {
        NavigationCore n_ = new NavigationCore();
        n_.setCurrentUrl("url");
        setupText(n_,"<_/>");
        assertEq("<_/>",n_.getHtmlText());
        assertEq("<_/>",n_.getDocument().export());
        assertEq("",n_.getTitle());
        assertEq("",n_.getReferenceScroll());
        assertEq("url",n_.getCurrentUrl());
    }
    @Test
    public void setupText2() {
        NavigationCore n_ = new NavigationCore();
        n_.setCurrentUrl("url");
        setupText(n_,"<head><title>TITLE</title></head>");
        assertEq("TITLE",n_.getTitle());
        assertEq("",n_.getReferenceScroll());
        assertEq("url",n_.getCurrentUrl());
    }
    @Test
    public void setupText3() {
        NavigationCore n_ = new NavigationCore();
        n_.setCurrentUrl("url#local");
        setupText(n_,"<head><title>TITLE</title></head>");
        assertEq("TITLE",n_.getTitle());
        assertEq("local",n_.getReferenceScroll());
        assertEq("url",n_.getCurrentUrl());
    }
    @Test
    public void setupText4() {
        NavigationCore n_ = new NavigationCore();
        n_.setLanguage("");
        n_.setFiles(new StringMap<String>());
        n_.setCurrentBeanName("");
        n_.setLanguages(new StringList());
        setupTextEmpty(n_);
        assertEq("",n_.getTitle());
        assertEq("",n_.getLanguage());
        assertEq("",n_.getCurrentBeanName());
        assertEq(0,n_.getFiles().size());
        assertEq(0,n_.getLanguages().size());
    }
    @Test
    public void getMessages1() {
        StringMap<String> res_ = NavigationCore.getMessages("\tf=g\na=b\n\nc=d\n\te");
        assertEq(2, res_.size());
        assertEq("b", res_.getVal("a"));
        assertEq("de", res_.getVal("c"));
    }
    @Test
    public void appendText1() {
        Document d_ = doc("<_>-</_>");
        extracted("H", d_);
        assertEq("<_>-H</_>", d_.export());
    }
    @Test
    public void appendText2() {
        Document d_ = doc("<_/>");
        extracted("H", d_);
        assertEq("<_>H</_>", d_.export());
    }
    @Test
    public void appendChild1() {
        Document d_ = doc("<_ a='-'>-</_>");
        Element e_ = extractedCh(d_);
        assertEq("<_ a=\"-\"/>", e_.export());
    }
    @Test
    public void simpleAppendChild1() {
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        simpleAppendChild(doc_,new RendReadWrite());
        assertEq("<__/>", doc_.export());
    }
    @Test
    public void simpleAppendChild2() {
        Document d_ = doc("<_ a='-'>-</_>");
        RendReadWrite r_ = new RendReadWrite();
        r_.setWrite(d_.getDocumentElement());
        simpleAppendChild(d_, r_);
        assertEq("<_ a=\"-\">-<__/></_>", d_.export());
    }
    @Test
    public void getParentNode1() {
        assertNull(NavigationCore.getParentNode(new RendReadWrite()));
    }
    @Test
    public void getParentNode2() {
        Document d_ = doc("<_ a='-'><__><___/></__></_>");
        RendReadWrite r_ = new RendReadWrite();
        r_.setWrite((Element)d_.getDocumentElement().getFirstChild().getFirstChild());
        assertEq("<__><___/></__>",NavigationCore.getParentNode(r_).export());
    }
    @Test
    public void prImg1() {
        Document d_ = doc("<_ src='cont.txt'/>");
        NavigationCore.prImg(new ConfigurationCore(),MessagesRendKeyWordsAttrs.init(),d_.getDocumentElement(),"");
        assertEq("<_ src=\"cont.txt\"/>",d_.export());
    }
    @Test
    public void prImg2() {
        Document d_ = doc("<_ src='cont.txt'/>");
        ConfigurationCore c_ = new ConfigurationCore();
        c_.getFiles().addEntry("repl.txt","inner");
        NavigationCore.prImg(c_,MessagesRendKeyWordsAttrs.init(),d_.getDocumentElement(),"repl.txt");
        assertEq("<_ src=\"inner\"/>",d_.export());
    }
    @Test
    public void procLink1() {
        Document d_ = doc("<head><other/></head>");
        procLink(d_,new RendKeyWordsGroup());
        assertEq("<head><other/><style>content</style></head>",d_.export());
    }
    @Test
    public void procLink2() {
        Document d_ = doc("<head><style/></head>");
        procLink(d_,new RendKeyWordsGroup());
        assertEq("<head><style>content</style></head>",d_.export());
    }
//    @Test
//    public void adjustMap() {
//        StringMap<StringMap<String>> messages_ = new StringMap<StringMap<String>>();
//        StringMap<String> g_ = new StringMap<String>();
//        g_.addEntry("","<_/>");
//        messages_.addEntry("", g_);
//        NavigationCore.adjustMap(messages_);
//        assertEq("<_/>",g_.getVal(""));
//    }
    @Test
    public void oneElt1() {
        assertEq("",NavigationCore.oneElt(new StringList()));
    }
    @Test
    public void oneElt2() {
        assertEq("",NavigationCore.oneElt(new StringList("")));
    }
    @Test
    public void getId1() {
        assertEq("",NavigationCore.getId(new ConfigurationCore(),doc("<_/>").getDocumentElement(),new RendKeyWordsGroup()));
    }
    @Test
    public void getId2() {
        assertEq("_",NavigationCore.getId(new ConfigurationCore(),doc("<_ id='_'/>").getDocumentElement(),new RendKeyWordsGroup()));
    }
    @Test
    public void procDefValue1() {
        Element w_ = doc("<_ value='_'/>").getDocumentElement();
        NavigationCore.procDefValue(w_,"",new RendKeyWordsGroup());
        assertEq("<_ value=\"_\"/>",w_.export());
    }
    @Test
    public void procDefValue2() {
        Element w_ = doc("<_ value='_'/>").getDocumentElement();
        NavigationCore.procDefValue(w_,"_",new RendKeyWordsGroup());
        assertEq("<_ value=\"_\" checked=\"checked\"/>",w_.export());
    }
    @Test
    public void getInputClass1() {
        assertEq("",NavigationCore.getInputClass(new ConfigurationCore(),doc("<_/>").getDocumentElement(),new FieldUpdates(),new RendKeyWordsGroup()));
    }
    @Test
    public void getInputClass2() {
        ConfigurationCore c_ = new ConfigurationCore();
        c_.setPrefix("x:");
        assertEq("_",NavigationCore.getInputClass(c_,doc("<_ x:className='_'/>").getDocumentElement(),new FieldUpdates(),new RendKeyWordsGroup()));
    }
    @Test
    public void parse1() {
        assertEq(0,SetupableAnalyzingDoc.parseInt("",0));
    }
    @Test
    public void parse2() {
        assertEq(1,SetupableAnalyzingDoc.parseInt("1",0));
    }
    @Test
    public void buttons() {
        IndexButtons but_ = new IndexButtons();
        FormInputCoords f_ = new FormInputCoords();
        f_.setForm(0);
        f_.setInput(0);
        assertEq(0,but_.addOrIncr(f_));
        assertEq(0,f_.getForm());
        assertEq(0,f_.getInput());
        assertEq(1,but_.addOrIncr(f_));
        assertEq(0,f_.getForm());
        assertEq(0,f_.getInput());
        FormInputCoords n_ = new FormInputCoords();
        n_.setForm(0);
        n_.setInput(1);
        assertEq(0,but_.addOrIncr(n_));
        assertEq(0,n_.getForm());
        assertEq(1,n_.getInput());
        assertEq(1,but_.addOrIncr(n_));
        assertEq(0,n_.getForm());
        assertEq(1,n_.getInput());
        FormInputCoords m_ = new FormInputCoords();
        m_.setForm(1);
        m_.setInput(0);
        assertEq(0,but_.addOrIncr(m_));
        assertEq(1,m_.getForm());
        assertEq(0,m_.getInput());
        assertEq(1,but_.addOrIncr(m_));
        assertEq(1,m_.getForm());
        assertEq(0,m_.getInput());
    }

    private static void procLink(Document _doc, RendKeyWordsGroup _cont) {
        ElementList heads_ = _doc.getElementsByTagName(_cont.getKeyWordsTags().getKeyWordHead());
        Element head_ = heads_.item(IndexConstants.FIRST_INDEX);
        NavigationCore.prHeader(_cont.getKeyWordsTags(),"content",_doc,head_);
    }

    private static void extracted(String _app, Document _doc) {
        NavigationCore.appendText(_app, _doc, _doc.getDocumentElement());
    }

    private static void simpleAppendChild(Document _doc, RendReadWrite _res) {
        NavigationCore.simpleAppendChild(_doc, _res, _doc.createElement("__"));
    }


    private static Element extractedCh(Document _doc) {
        return NavigationCore.appendChild(_doc, _doc.getDocumentElement());
    }

    private static void setupText(NavigationCore _al,String _txt) {
        Document document_ = doc(_txt);
        _al.setupText(_txt,document_,"head","title");
    }

    private static Document doc(String _txt) {
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(_txt);
        return res_.getDocument();
    }

    private static void setupTextEmpty(NavigationCore _al) {
        _al.setupText("",null,"head","title");
    }
}
