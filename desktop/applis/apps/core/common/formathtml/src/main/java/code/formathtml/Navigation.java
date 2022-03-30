package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.structs.Message;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;
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
    private HtmlPage htmlPage;

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

    public void initializeRendSession(ContextEl _context, BeanLgNames _stds, RendStackCall _rendStackCall) {
        initializeRendSessionDoc(_context, _stds, _rendStackCall);
    }
    public void initializeRendSessionDoc(ContextEl _ctx, BeanLgNames _stds, RendStackCall _rendStackCall) {
        String textToBeChanged_ = _stds.initializeRendSessionDoc(_ctx, language, session, dataBaseStruct, _rendStackCall);
        if (textToBeChanged_.isEmpty()) {
            return;
        }
        setupText(textToBeChanged_,_stds, _rendStackCall);
    }

    public StringMap<String> getFiles() {
        return files;
    }

    public void processRendAnchorRequest(BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        Document doc_ = getDocument();
        processRendAnchorRequest(_advStandards, _ctx, _rendStack, doc_);
    }

    public void processRendAnchorRequest(BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack, Document doc_) {
        Element ancElement_ = DocumentBuilder.getFirstElementByAttribute(doc_, getSession().getRendKeyWords().getAttrNa(), Long.toString(htmlPage.getUrl()));
        processRendAnchorRequest(_advStandards, _ctx, _rendStack,ancElement_);
    }

    public void processRendAnchorRequest(BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack, Element ancElement_) {
        String res_ = _advStandards.processRendAnchorRequest(ancElement_, language, session, htmlPage, _ctx, _rendStack);
        if (!res_.isEmpty()) {
            setupText(res_,_advStandards,_rendStack);
        }
    }

    public void processRendFormRequest(BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.clearPages();
        _rendStackCall.setDocument(document);
        HtmlPage htmlPage_ = htmlPage;
        ImportingPage ip_ = new ImportingPage();
        _rendStackCall.addPage(ip_);
        long lg_ = htmlPage_.getUrl();
        Document doc_ = document;
        //retrieving form that is submitted
        Element formElement_ = DocumentBuilder.getFirstElementByAttribute(doc_, session.getRendKeyWords().getAttrNf(), Long.toString(lg_));
        if (formElement_ == null) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(NullStruct.NULL_VALUE));
            return;
        }
        htmlPage_.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop

        StringMap<Message> map_ = _advStandards.validateAll(htmlPage_, session, _ctx, _rendStackCall);
        if (map_ == null) {
            return;
        }
        StringMap<String> errors_;
        errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_;
        errorsArgs_ = new StringMap<StringList>();
        for (EntryCust<String, Message> e: map_.entryList()) {
            Message nCont_ = e.getValue();
            String id_ = e.getKey();
            errors_.put(id_, nCont_.getContent());
            errorsArgs_.put(id_, nCont_.getArgs());
        }
        //begin deleting previous errors
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
        //end deleting previous errors
        if (!errors_.isEmpty()) {
            processRendFormErrors(doc_,_advStandards, formElement_, lg_, errors_, errorsArgs_, _rendStackCall);
            _rendStackCall.clearPages();
            return;
        }
        //Setting values for bean
        boolean res_ = _advStandards.updateRendBean(htmlPage_, _ctx, _rendStackCall);
        _rendStackCall.clearPages();
        if (!res_) {
            return;
        }

        //invoke application
        processRendAnchorRequest(_advStandards, _ctx, _rendStackCall,formElement_);
    }

    private void processRendFormErrors(Document _doc, BeanLgNames _advStandards, Element _formElement, long _id,
                                       StringMap<String> _errors, StringMap<StringList> _errorsArgs, RendStackCall _rendStackCall) {
        HtmlPage htmlPage_ = htmlPage;
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        StringList idFormats_ = htmlPage_.getFormatIdMap().getVal(_id);
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(_id);
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
                Text text_ = _doc.createTextNode(error_);
                elt_.appendChild(text_);
                count_++;
            }
        }
        ElementList inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordInput());
        int lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeContainer nCont_ = getValue(containers_, idInput_);
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueText())) {
                elt_.setAttribute(session.getRendKeyWords().getAttrValue(), nCont_.getNodeInformation().getValue().first());
                continue;
            }
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueCheckbox())) {
                if (StringUtil.quickEq(nCont_.getNodeInformation().getValue().first(),BeanLgNames.ON)) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
                } else {
                    elt_.removeAttribute(session.getRendKeyWords().getAttrChecked());
                }
                continue;
            }
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueRadio())) {
                String value_ = elt_.getAttribute(session.getRendKeyWords().getAttrValue());
                if (StringUtil.quickEq(nCont_.getNodeInformation().getValue().first(), value_)) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
                } else {
                    elt_.removeAttribute(session.getRendKeyWords().getAttrChecked());
                }
                continue;
            }
            if (StringUtil.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueSubmit())) {
                continue;
            }
            elt_.setAttribute(session.getRendKeyWords().getAttrValue(), nCont_.getNodeInformation().getValue().first());
        }
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSelect());
        lengthInputs_ = inputs_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeContainer nCont_ = getValue(containers_, idInput_);
            ElementList options_ = elt_.getElementsByTagName(session.getRendKeyWords().getKeyWordOption());
            int optionsLen_ = options_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < optionsLen_; j++) {
                Element option_ = options_.item(j);
                if (StringUtil.contains(nCont_.getNodeInformation().getValue(), option_.getAttribute(session.getRendKeyWords().getAttrValue()))) {
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
            NodeContainer nCont_ = getValue(containers_, idInput_);
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int j = IndexConstants.FIRST_INDEX; j < ch_; j++) {
                elt_.removeChild(children_.item(j));
            }
            Text text_ = _doc.createTextNode(nCont_.getNodeInformation().getValue().first());
            elt_.appendChild(text_);
        }
        setupText(_doc.export(),_advStandards, _rendStackCall);
    }

    private static NodeContainer getValue(LongTreeMap<NodeContainer> _containers, String _idInput) {
        NodeContainer val_;
        if (_idInput.isEmpty()) {
            val_ = null;
        } else {
            val_ = _containers.getVal(NumberUtil.parseLongZero(_idInput));
        }
        if (val_ == null) {
            val_ = new NodeContainer();
        }
        return val_;
    }

    private void setupText(String _text, BeanLgNames _advStandards, RendStackCall _rendStackCall) {
        Document doc_ = _rendStackCall.getDocument();
        document = doc_;
        htmlPage = _rendStackCall.getHtmlPage();
        ElementList nodes_;
        title = EMPTY_STRING;
        nodes_ = doc_.getElementsByTagName(session.getRendKeyWords().getKeyWordHead());
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

    public HtmlPage getHtmlPage() {
        return htmlPage;
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
