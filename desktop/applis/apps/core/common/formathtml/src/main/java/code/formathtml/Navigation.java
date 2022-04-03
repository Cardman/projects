package code.formathtml;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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

    private static final String REF_TAG = "#";

    private static final String EMPTY_STRING = "";

    private Configuration session;

    private String htmlText = "";

    private String referenceScroll = "";

    private String language = "";
    private StringList languages = new StringList();

    private StringMap<String> files = new StringMap<String>();

    private Struct dataBaseStruct = NullStruct.NULL_VALUE;

    private String title = EMPTY_STRING;
    private Document document;

    public DualAnalyzedContext loadConfiguration(String _cont, String _lgCode, BeanCustLgNames _lgNames, AbstractFileBuilder _fileBuilder, DefaultConfigurationLoader _confLoad) {
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(_cont);
        Document doc_ = res_.getDocument();
        return loadConfiguration(_lgCode, _lgNames, _fileBuilder, _confLoad, doc_);
    }

    public DualAnalyzedContext loadConfiguration(String _lgCode, BeanCustLgNames _lgNames, AbstractFileBuilder _fileBuilder, DefaultConfigurationLoader _confLoad, Document _doc) {
        if (_doc == null) {
            AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
            DualConfigurationContext context_ = new DualConfigurationContext();
            context_.setKo(true);
            return new DualAnalyzedContext(null,page_,_lgNames, context_);
        }
        return innerLoad(_lgCode, _lgNames, _fileBuilder, _confLoad, _doc);
    }

    public DualAnalyzedContext innerLoad(String _lgCode, BeanCustLgNames _lgNames, AbstractFileBuilder _fileBuilder, DefaultConfigurationLoader _confLoad, Document _doc) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        session = new Configuration();
        DualAnalyzedContext ctx_ = _confLoad.load(session, _lgCode, _doc, _fileBuilder, page_,_lgNames);
        if (!ctx_.getContext().isKo()) {
            session.init(ctx_.getContext());
        }
        return ctx_;
    }

    public void setLanguage(String _language) {
        language = _language;
        session.setCurrentLanguage(language);
    }

    public String getHtmlText() {
        return htmlText;
    }

    public Document getDocument() {
        return document;
    }

    public String getReferenceScroll() {
        return referenceScroll;
    }

    public Struct getDataBaseStruct() {
        return dataBaseStruct;
    }

    public StringMap<String> getFiles() {
        return files;
    }

    public void feedErr(StringMap<Message> map_, StringMap<String> errors_, StringMap<StringList> errorsArgs_) {
        for (EntryCust<String, Message> e: map_.entryList()) {
            Message nCont_ = e.getValue();
            String id_ = e.getKey();
            errors_.put(id_, nCont_.getContent());
            errorsArgs_.put(id_, nCont_.getArgs());
        }
    }

    public void delPrevious(Document doc_, Element formElement_) {
        ElementList spansForm_ = formElement_.getElementsByTagName(session.getRendKeyWords().getKeyWordSpan());
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
            Text text_ = doc_.createTextNode(RendBlock.SPACE);
            elt_.appendChild(text_);
        }
    }

    public void processRendFormErrors(BeanLgNames _advStandards, Element _formElement, long _id,
                                      StringMap<String> _errors, StringMap<StringList> _errorsArgs) {
        StringList idFormats_ = _advStandards.getPage().getFormatIdMap().getVal(_id);
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
                Text text_ = document.createTextNode(error_);
                elt_.appendChild(text_);
                count_++;
            }
        }
        ElementList inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordInput());
        int lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeInformations nCont_ = getValue(_id, idInput_, _advStandards.getPage());
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueText())) {
                elt_.setAttribute(session.getRendKeyWords().getAttrValue(), nCont_.getValue().first());
                continue;
            }
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueCheckbox())) {
                if (StringUtil.quickEq(nCont_.getValue().first(),BeanLgNames.ON)) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
                } else {
                    elt_.removeAttribute(session.getRendKeyWords().getAttrChecked());
                }
                continue;
            }
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueRadio())) {
                String value_ = elt_.getAttribute(session.getRendKeyWords().getAttrValue());
                if (StringUtil.quickEq(nCont_.getValue().first(), value_)) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
                } else {
                    elt_.removeAttribute(session.getRendKeyWords().getAttrChecked());
                }
                continue;
            }
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueSubmit())) {
                continue;
            }
            elt_.setAttribute(session.getRendKeyWords().getAttrValue(), nCont_.getValue().first());
        }
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSelect());
        lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeInformations nCont_ = getValue(_id, idInput_, _advStandards.getPage());
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
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordTextarea());
        lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeInformations nCont_ = getValue(_id, idInput_, _advStandards.getPage());
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < ch_; j++) {
                elt_.removeChild(children_.item(j));
            }
            Text text_ = document.createTextNode(nCont_.getValue().first());
            elt_.appendChild(text_);
        }
        setupText(document.export(),_advStandards);
    }

    private static NodeInformations getValue(long _id, String _idInput, HtmlPage _htmlPage) {
        NodeContainer val_;
        if (_idInput.isEmpty()) {
            val_ = null;
        } else {
            val_ = _htmlPage.getContainer(_id,NumberUtil.parseLongZero(_idInput));
        }
        if (val_ == null) {
            return new NodeInformations();
        }
        return val_.getNodeInformation();
    }

    public boolean setupText(String _text, BeanLgNames _advStandards, Document _document) {
        if (_text.isEmpty()) {
            return false;
        }
        document = _document;
        setupText(_text, _advStandards);
        return true;
    }
    public void setupText(String _text, BeanLgNames _advStandards) {
        ElementList nodes_;
        title = EMPTY_STRING;
        nodes_ = document.getElementsByTagName(session.getRendKeyWords().getKeyWordHead());
        int size_ = nodes_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
            Element node_ = nodes_.item(i);
            ElementList subNodes_ = node_.getElementsByTagName(session.getRendKeyWords().getAttrTitle());
            int subListSize_ = subNodes_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < subListSize_; j++) {
                Element subNode_ = subNodes_.item(j);
                title = subNode_.getTextContent().trim();
            }
        }
        htmlText = _text;
        StringList tokens_ = StringUtil.splitStrings(_advStandards.getCurrentUrl(), REF_TAG);
        if (tokens_.size() > IndexConstants.ONE_ELEMENT) {
            referenceScroll = tokens_.get(IndexConstants.SECOND_INDEX);
            _advStandards.setCurrentUrl(tokens_.first());
        } else {
            referenceScroll = EMPTY_STRING;
        }
    }

    public Configuration getSession() {
        return session;
    }

    public void setSession(Configuration _session) {
        session = _session;
    }

    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public void setDataBaseStruct(Struct _dataBaseStruct) {
        dataBaseStruct = _dataBaseStruct;
    }
}
