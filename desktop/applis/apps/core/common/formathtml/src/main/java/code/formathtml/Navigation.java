package code.formathtml;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.common.AdvFileEscapedCalc;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.structs.Message;
import code.formathtml.util.*;
import code.sml.*;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class Navigation {

    private Configuration session;
    private final NavigationCore core = new NavigationCore();

//    private String htmlText = "";

//    private String referenceScroll = "";


    private Struct dataBaseStruct = NullStruct.NULL_VALUE;

//    private String title = EMPTY_STRING;
//    private Document document;
//    private String currentUrl = "";
//    private String currentBeanName = "";

    public DualAnalyzedContext loadConfiguration(String _cont, String _lgCode, BeanCustLgNames _lgNames, AbstractFileBuilder _fileBuilder, DefaultConfigurationLoader _confLoad) {
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(_cont);
        Document doc_ = res_.getDocument();
        AdvFileEscapedCalc es_ = new AdvFileEscapedCalc(AnaRendBlock.getIndexesSpecChars(_cont, DocumentBuilder.possibleEncodes()));
        FileBlock file_ = new FileBlock(0, false, "", es_);
        file_.metrics(StringUtil.nullToEmpty(_cont));
        return loadConfiguration(_lgCode, _lgNames, _fileBuilder, _confLoad, doc_, file_);
    }

    public DualAnalyzedContext loadConfiguration(String _lgCode, BeanCustLgNames _lgNames, AbstractFileBuilder _fileBuilder, DefaultConfigurationLoader _confLoad, Document _doc, FileBlock _file) {
        if (_doc == null) {
            AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
            DualConfigurationContext context_ = new DualConfigurationContext();
            context_.setKo(true);
            return new DualAnalyzedContext(null,page_,_lgNames, context_, _file);
        }
        return innerLoad(_lgCode, _fileBuilder, _confLoad, _doc, _file);
    }

    public DualAnalyzedContext innerLoad(String _lgCode, AbstractFileBuilder _fileBuilder, DefaultConfigurationLoader _confLoad, Document _doc, FileBlock _file) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        session = new Configuration();
        DualAnalyzedContext ctx_ = _confLoad.load(session, _lgCode, _doc, _fileBuilder, page_, _file);
        if (!ctx_.getContext().isKo()) {
            session.init(ctx_.getContext());
        }
        return ctx_;
    }

    public void setLanguage(String _language) {
        core.setLanguage(_language);
        session.setCurrentLanguage(_language);
    }

    public String getHtmlText() {
        return core.getHtmlText();
    }

    public Document getDocument() {
        return core.getDocument();
    }

    public String getReferenceScroll() {
        return core.getReferenceScroll();
    }

    public Struct getDataBaseStruct() {
        return dataBaseStruct;
    }

    public StringMap<String> getFiles() {
        return core.getFiles();
    }

    public void feedErr(StringMap<Message> _map, StringMap<String> _errors, StringMap<StringList> _errorsArgs) {
        for (EntryCust<String, Message> e: _map.entryList()) {
            Message nCont_ = e.getValue();
            String id_ = e.getKey();
            _errors.put(id_, nCont_.getContent());
            _errorsArgs.put(id_, nCont_.getArgs());
        }
    }

    public void delPrevious(Document _doc, Element _formElement) {
        ElementList spansForm_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSpan());
        int lengthSpansForom_ = spansForm_.getLength();
        for (int j = IndexConstants.FIRST_INDEX; j < lengthSpansForom_; j++) {
            Element elt_ = spansForm_.item(j);
            if (!elt_.hasAttribute(StringUtil.concat(session.getPrefix(),session.getRendKeyWords().getAttrFor()))) {
                continue;
            }
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int i = IndexConstants.FIRST_INDEX; i < ch_; i++) {
                elt_.removeChild(children_.item(i));
            }
            Text text_ = _doc.createTextNode(RendBlock.SPACE);
            elt_.appendChild(text_);
        }
    }

    public void processRendFormErrors(Element _formElement, long _id,
                                      StringMap<String> _errors, StringMap<StringList> _errorsArgs, HtmlPageInt _page) {
        StringList idFormats_ = _page.getFormatIdMap().getVal(_id);
        for (String i : _errors.getKeys()) {
            int count_ = 0;
            ElementList spans_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSpan());
            int lengthSpans_ = spans_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < lengthSpans_; j++) {
                Element elt_ = spans_.item(j);
                if (!StringUtil.quickEq(elt_.getAttribute(StringUtil.concat(session.getPrefix(),session.getRendKeyWords().getAttrFor())),i)) {
                    count_++;
                    continue;
                }
                NodeList children_ = elt_.getChildNodes();
                int ch_ = children_.getLength();
                for (int k = IndexConstants.FIRST_INDEX; k < ch_; k++) {
                    elt_.removeChild(children_.item(k));
                }
                String error_ = _errors.getVal(i);
                String message_ = idFormats_.get(count_);
                if (!message_.isEmpty()) {
                    error_ = StringUtil.simpleStringsFormat(message_,_errorsArgs.getVal(i));
                }
                Text text_ = core.getDocument().createTextNode(error_);
                elt_.appendChild(text_);
                count_++;
            }
        }
        in(_formElement, _id, _page);
        se(_formElement, _id, _page);
        ta(_formElement, _id, _page);
        core.setupText(core.getDocument().export(),session.getRendKeyWords().getKeyWordHead(),session.getRendKeyWords().getAttrTitle());
    }

    private void in(Element _formElement, long _id, HtmlPageInt _page) {
        ElementList inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordInput());
        int lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeInformations nCont_ = getValue(_id, idInput_, _page);
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueText())) {
                elt_.setAttribute(session.getRendKeyWords().getAttrValue(), empt(nCont_.getValue()));
            } else if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueCheckbox())) {
                ch(elt_, nCont_);
            } else if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueRadio())) {
                ra(elt_, nCont_);
            } else {
                if (!StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()), session.getRendKeyWords().getValueSubmit())) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrValue(), empt(nCont_.getValue()));
                }
            }
        }
    }
    public static String empt(StringList _list) {
        if (_list.isEmpty()) {
            return "";
        }
        return _list.first();
    }

    private void ra(Element _elt, NodeInformations _nCont) {
        String value_ = _elt.getAttribute(session.getRendKeyWords().getAttrValue());
        if (StringUtil.contains(_nCont.getValue(),value_)) {
            _elt.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
        } else {
            _elt.removeAttribute(session.getRendKeyWords().getAttrChecked());
        }
    }

    private void ch(Element _elt, NodeInformations _nCont) {
        if (StringUtil.contains(_nCont.getValue(), SetupableAnalyzingDoc.ON)) {
            _elt.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
        } else {
            _elt.removeAttribute(session.getRendKeyWords().getAttrChecked());
        }
    }

    private void se(Element _formElement, long _id, HtmlPageInt _page) {
        ElementList inputs_;
        int lengthInputs_;
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSelect());
        lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeInformations nCont_ = getValue(_id, idInput_, _page);
            ElementList options_ = elt_.getElementsByTagName(session.getRendKeyWords().getKeyWordOption());
            int optionsLen_ = options_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < optionsLen_; j++) {
                Element option_ = options_.item(j);
                if (StringUtil.contains(nCont_.getValue(), option_.getAttribute(session.getRendKeyWords().getAttrValue()))) {
                    option_.setAttribute(session.getRendKeyWords().getAttrSelected(), session.getRendKeyWords().getAttrSelected());
                } else {
                    option_.removeAttribute(session.getRendKeyWords().getAttrSelected());
                }
            }
        }
    }

    private void ta(Element _formElement, long _id, HtmlPageInt _page) {
        ElementList inputs_;
        int lengthInputs_;
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordTextarea());
        lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeInformations nCont_ = getValue(_id, idInput_, _page);
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < ch_; j++) {
                elt_.removeChild(children_.item(j));
            }
            Text text_ = core.getDocument().createTextNode(nCont_.getValue().first());
            elt_.appendChild(text_);
        }
    }

    private static NodeInformations getValue(long _id, String _idInput, HtmlPageInt _htmlPage) {
        NodeInformations val_;
        if (_idInput.isEmpty()) {
            val_ = null;
        } else {
            val_ = _htmlPage.getContainer(_id,NumberUtil.parseLongZero(_idInput));
        }
        if (val_ == null) {
            return new NodeInformations();
        }
        return val_;
    }

    public boolean setupText(String _text, Document _document) {
        return core.setupText(_text,_document,session.getRendKeyWords().getKeyWordHead(),session.getRendKeyWords().getAttrTitle());
    }

    public Configuration getSession() {
        return session;
    }

    public void setSession(Configuration _session) {
        session = _session;
    }

    public NavigationCore getCore() {
        return core;
    }

    public void setFiles(StringMap<String> _files) {
        getCore().setFiles(_files);
    }

    public String getLanguage() {
        return core.getLanguage();
    }

    public String getTitle() {
        return core.getTitle();
    }

    public StringList getLanguages() {
        return core.getLanguages();
    }

    public void setLanguages(StringList _languages) {
        core.setLanguages(_languages);
    }

    public void setDataBaseStruct(Struct _dataBaseStruct) {
        dataBaseStruct = _dataBaseStruct;
    }

    public String getCurrentUrl() {
        return core.getCurrentUrl();
    }

    public void setCurrentUrl(String _v) {
        this.core.setCurrentUrl(_v);
    }

    public String getCurrentBeanName() {
        return core.getCurrentBeanName();
    }

    public void setCurrentBeanName(String _v) {
        this.core.setCurrentBeanName(_v);
    }
}
