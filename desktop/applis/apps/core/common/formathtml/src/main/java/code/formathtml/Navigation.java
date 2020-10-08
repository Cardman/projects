package code.formathtml;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.structs.ValidatorInfo;
import code.expressionlanguage.Argument;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.*;
import code.sml.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class Navigation {

    private static final char BEGIN_ARGS = '(';
    private static final char SEP_ARGS = ',';
    private static final char END_ARGS = ')';

    private static final String REF_TAG = "#";

    private static final String DOT = ".";

    private static final String CALL_METHOD = "$";

    private static final String EMPTY_STRING = "";

    private Configuration session;

    private String currentBeanName;

    private String htmlText;

    private String referenceScroll;

    private String currentUrl;

    private String language = "";
    private StringList languages = new StringList();

    private StringMap<String> files = new StringMap<String>();

    private Struct dataBaseStruct = NullStruct.NULL_VALUE;

    private String title = EMPTY_STRING;

    public Navigation(){
        //instance
    }
    public DualAnalyzedContext loadConfiguration(String _cont, String _lgCode, BeanLgNames _lgNames, RendAnalysisMessages _rend, AbstractFileBuilder _fileBuilder, AbstractConfigurationLoader _confLoad) {
        DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(_cont);
        Document doc_ = res_.getDocument();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        if (doc_ == null) {
            return new DualAnalyzedContext(page_,_lgNames,null);
        }
        session = new Configuration();
        DualConfigurationContext ctx_ = _confLoad.load(session,_lgCode, doc_, _fileBuilder, page_);
        if (ctx_.getContext() == null) {
            return new DualAnalyzedContext(page_,_lgNames,null);
        }
        session.init(ctx_);
        return new DualAnalyzedContext(page_,_lgNames,ctx_);
    }

    public void setLanguage(String _language) {
        language = _language;
        session.setCurrentLanguage(language);
    }

    public String getHtmlText() {
        return htmlText;
    }

    public Document getDocument() {
        return session.getDocument();
    }

    public String getReferenceScroll() {
        return referenceScroll;
    }

    public void initializeRendSession(ContextEl _context, BeanLgNames _stds) {
        RendDocumentBlock rendDocumentBlock_ = session.getRendDocumentBlock();
        initializeRendSessionDoc(_context, rendDocumentBlock_, _stds);
    }
    public void initializeRendSessionDoc(ContextEl _ctx, RendDocumentBlock _doc, BeanLgNames _stds) {
        if (_doc == null) {
            return;
        }
        _stds.initBeans(session,language,dataBaseStruct, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        String currentUrl_ = session.getFirstUrl();
        session.setCurrentUrl(currentUrl_);
        htmlText = RendBlock.getRes(_doc,session, _stds, _ctx);
        if (htmlText.isEmpty()) {
            return;
        }
        //For title
        currentBeanName = session.getBeanName();
        currentUrl = currentUrl_;
        setupText(htmlText);
    }

    public StringMap<AnaRendDocumentBlock> analyzedRenders(AnalyzedPageEl _page, BeanLgNames _stds, RendAnalysisMessages _rend, AnalyzingDoc _analyzingDoc, DualConfigurationContext _dual) {
        _stds.preInitBeans(session);
        _analyzingDoc.setRendAnalysisMessages(_rend);
        _analyzingDoc.setLanguages(languages);
        session.setCurrentLanguage(language);
        return session.analyzedRenders(files, _analyzingDoc, _page, _stds, _dual);
    }

    public StringMap<String> getFiles() {
        return files;
    }

    public void initInstancesPattern(AnalyzedPageEl _page, AnalyzingDoc _anaDoc) {
        String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
        for (EntryCust<String, BeanInfo> e: session.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            OperationNode root_ = RenderAnalysis.getRootAnalyzedOperations(StringList.concat(keyWordNew_, " ", info_.getClassName(), "()"), 0, _anaDoc, _page);
            info_.setResolvedClassName(info_.getClassName());
            _anaDoc.getBeansInfos().addEntry(root_,info_);
        }
        for (EntryCust<String,ValidatorInfo> e: session.getLateValidators().entryList()) {
            ValidatorInfo v_ = e.getValue();
            OperationNode root_ = RenderAnalysis.getRootAnalyzedOperations(StringList.concat(keyWordNew_, " ", v_.getClassName(), "()"), 0, _anaDoc, _page);
            _anaDoc.getLateValidators().addEntry(root_,v_);
        }
    }

    public void processRendAnchorRequest(String _anchorRef, BeanLgNames _advStandards, ContextEl _ctx) {
        if (_anchorRef.contains(CALL_METHOD)) {
            session.clearPages();
            HtmlPage htmlPage_ = session.getHtmlPage();
            ImportingPage ip_ = new ImportingPage();
            session.addPage(ip_);
            int indexPoint_ = _anchorRef.indexOf(DOT);
            String action_ = _anchorRef
                    .substring(indexPoint_ + 1);
            String methodName_;
            String suffix_;
            if (action_.indexOf(BEGIN_ARGS) == CustList.INDEX_NOT_FOUND_ELT) {
                methodName_ = action_;
                suffix_ = EMPTY_STRING;
            } else {
                methodName_ = action_.substring(CustList.FIRST_INDEX, action_.indexOf(BEGIN_ARGS));
                suffix_ = action_.substring(action_.indexOf(BEGIN_ARGS));
                StringBuilder str_ = new StringBuilder();
                for (char c: suffix_.toCharArray()) {
                    if (isPartOfArgument(c)) {
                        continue;
                    }
                    str_.append(c);
                }
                suffix_ = str_.toString();
            }
            String beanName_ = _anchorRef
                    .substring(_anchorRef.indexOf(CALL_METHOD) + 1, indexPoint_);
            Struct bean_ = getBeanOrNull(beanName_);
            ip_.setOffset(indexPoint_+1);
            ip_.setGlobalArgumentStruct(bean_);
            Struct return_;
            if (htmlPage_.isForm()) {
                return_ = RendRequestUtil.redirectForm(session,new Argument(bean_),(int)htmlPage_.getUrl(), _advStandards, _ctx);
            } else {
                return_=RendRequestUtil.redirect(session,new Argument(bean_),(int)htmlPage_.getUrl(), _advStandards, _ctx);
            }
            if (_ctx.callsOrException()) {
                return;
            }
            String urlDest_ = currentUrl;
            if (return_ != NullStruct.NULL_VALUE) {
                ip_.setOffset(_anchorRef.length());
                urlDest_ = getRendUrlDest(StringList.concat(beanName_, DOT, methodName_,suffix_), return_, _advStandards, _ctx);
                if (_ctx.callsOrException()) {
                    return;
                }
                if (urlDest_ == null) {
                    urlDest_ = currentUrl;
                }
            }
            session.clearPages();
            processAfterInvoke(urlDest_,beanName_,bean_, _advStandards, _ctx);
            return;
        }
        if (_anchorRef.isEmpty()) {
            return;
        }
        Struct bean_ = getBeanOrNull(currentBeanName);
        session.clearPages();
        processAfterInvoke(_anchorRef,currentBeanName,bean_, _advStandards, _ctx);
    }

    private Struct getBeanOrNull(String _currentBeanName) {
        Struct bean_ = getBean(_currentBeanName);
        if (bean_ == null) {
            bean_ = NullStruct.NULL_VALUE;
        }
        return bean_;
    }

    private void processAfterInvoke(String _dest, String _beanName, Struct _bean, BeanLgNames _advStandards, ContextEl _context){
        String textToBeChanged_ = _advStandards.processAfterInvoke(session,_dest,_beanName,_bean,currentUrl,language, _context);
        if (textToBeChanged_.isEmpty()) {
            return;
        }
        currentBeanName = session.getBeanName();
        currentUrl = _dest;
        setupText(textToBeChanged_);
    }

    private static boolean isPartOfArgument(char _char) {
        if (_char == SEP_ARGS) {
            return false;
        }
        if (_char == BEGIN_ARGS) {
            return false;
        }
        return _char != END_ARGS;
    }

    public void processRendFormRequest(BeanLgNames _advStandards, ContextEl _ctx) {
        session.clearPages();
        HtmlPage htmlPage_ = session.getHtmlPage();
        ImportingPage ip_ = new ImportingPage();
        session.addPage(ip_);
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        long lg_ = htmlPage_.getUrl();
        Document doc_ = session.getDocument();
        String actionCommand_;
        //retrieving form that is submitted
        Element formElement_ = DocumentBuilder.getFirstElementByAttribute(doc_, session.getRendKeyWords().getAttrNf(), String.valueOf(lg_));
        if (formElement_ == null) {
            _ctx.setCallingState(NullStruct.NULL_VALUE);
            return;
        }
        htmlPage_.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop
        actionCommand_ = formElement_.getAttribute(StringList.concat(session.getPrefix(),session.getRendKeyWords().getAttrCommand()));

        StringMap<String> errors_;
        errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_;
        errorsArgs_ = new StringMap<StringList>();
        for (EntryCust<Long, NodeContainer> e: containersMap_.getVal(lg_).entryList()) {
            NodeContainer nCont_ = e.getValue();
            NodeInformations nInfos_ = nCont_.getNodeInformation();
            String valId_ = nInfos_.getValidator();
            String id_ = nInfos_.getId();
            Message messageTr_ = _advStandards.validate(session,nCont_,valId_, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            if (messageTr_ != null) {
                errors_.put(id_, messageTr_.getMessage());
                errorsArgs_.put(id_, messageTr_.getArgs());
            }
        }
        //begin deleting previous errors
        ElementList spansForm_ = formElement_.getElementsByTagName(session.getRendKeyWords().getKeyWordSpan());
        int lengthSpansForom_ = spansForm_.getLength();
        for (int j = CustList.FIRST_INDEX; j < lengthSpansForom_; j++) {
            Element elt_ = spansForm_.item(j);
            if (!elt_.hasAttribute(StringList.concat(session.getPrefix(),session.getRendKeyWords().getAttrFor()))) {
                continue;
            }
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int i = CustList.FIRST_INDEX; i < ch_; i++) {
                elt_.removeChild((MutableNode) children_.item(i));
            }
            Text text_ = doc_.createTextNode(RendBlock.SPACE);
            elt_.appendChild(text_);
        }
        //end deleting previous errors
        if (!errors_.isEmpty()) {
            processRendFormErrors(doc_, formElement_, lg_, errors_, errorsArgs_);
            session.clearPages();
            return;
        }
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(lg_);
        //Setting values for bean
        updateRendBean(containers_, _advStandards, _ctx);
        session.clearPages();
        if (_ctx.callsOrException()) {
            return;
        }

        //invoke application
        processRendAnchorRequest(actionCommand_, _advStandards, _ctx);
    }

    private void updateRendBean(LongTreeMap<NodeContainer> _containers, BeanLgNames _advStandards, ContextEl _ctx) {
        for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
            NodeContainer nCont_ = e.getValue();
            if (!nCont_.isEnabled()) {
                continue;
            }
            Struct newObj_;
            ResultErrorStd res_ = _advStandards.convert(nCont_, session, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            newObj_ = res_.getResult();
            Struct procObj_ = e.getValue().getUpdated();
            session.getLastPage().setGlobalArgumentStruct(procObj_);
            RendRequestUtil.setRendObject(session, e.getValue(), newObj_, _advStandards, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
        }
    }

    private void processRendFormErrors(Document _doc, Element _formElement, long _id,
                                   StringMap<String> _errors, StringMap<StringList> _errorsArgs) {
        HtmlPage htmlPage_ = session.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        StringList idFormats_ = htmlPage_.getFormatIdMap().getVal(_id);
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(_id);
        for (String i : _errors.getKeys()) {
            int count_ = 0;
            ElementList spans_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSpan());
            int lengthSpans_ = spans_.getLength();
            for (int j = CustList.FIRST_INDEX; j < lengthSpans_; j++) {
                Element elt_ = spans_.item(j);
                if (!StringList.quickEq(elt_.getAttribute(StringList.concat(session.getPrefix(),session.getRendKeyWords().getAttrFor())),i)) {
                    count_++;
                    continue;
                }
                NodeList children_ = elt_.getChildNodes();
                int ch_ = children_.getLength();
                for (int k = CustList.FIRST_INDEX; k < ch_; k++) {
                    elt_.removeChild((MutableNode) children_.item(k));
                }
                String error_ = _errors.getVal(i);
                String message_ = idFormats_.get(count_);
                if (!message_.isEmpty()) {
                    error_ = StringList.simpleStringsFormat(message_,_errorsArgs.getVal(i));
                }
                Text text_ = _doc.createTextNode(error_);
                elt_.appendChild(text_);
                count_++;
            }
        }
        ElementList inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordInput());
        int lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeContainer nCont_ = getValue(containers_, idInput_);
            if (StringList.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueText())) {
                elt_.setAttribute(session.getRendKeyWords().getAttrValue(), nCont_.getNodeInformation().getValue().first());
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueCheckbox())) {
                if (StringList.quickEq(nCont_.getNodeInformation().getValue().first(),BeanLgNames.ON)) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
                } else {
                    elt_.removeAttribute(session.getRendKeyWords().getAttrChecked());
                }
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueRadio())) {
                String value_ = elt_.getAttribute(session.getRendKeyWords().getAttrValue());
                if (StringList.quickEq(nCont_.getNodeInformation().getValue().first(), value_)) {
                    elt_.setAttribute(session.getRendKeyWords().getAttrChecked(), session.getRendKeyWords().getAttrChecked());
                } else {
                    elt_.removeAttribute(session.getRendKeyWords().getAttrChecked());
                }
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(session.getRendKeyWords().getAttrType()),session.getRendKeyWords().getValueSubmit())) {
                continue;
            }
            elt_.setAttribute(session.getRendKeyWords().getAttrValue(), nCont_.getNodeInformation().getValue().first());
        }
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordSelect());
        lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeContainer nCont_ = getValue(containers_, idInput_);
            ElementList options_ = elt_.getElementsByTagName(session.getRendKeyWords().getKeyWordOption());
            int optionsLen_ = options_.getLength();
            for (int j = CustList.FIRST_INDEX; j < optionsLen_; j++) {
                Element option_ = options_.item(j);
                if (StringList.contains(nCont_.getNodeInformation().getValue(), option_.getAttribute(session.getRendKeyWords().getAttrValue()))) {
                    option_.setAttribute(session.getRendKeyWords().getAttrSelected(), session.getRendKeyWords().getAttrSelected());
                } else {
                    option_.removeAttribute(session.getRendKeyWords().getAttrSelected());
                }
            }
        }
        inputs_ = _formElement.getElementsByTagName(session.getRendKeyWords().getKeyWordTextarea());
        lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(session.getRendKeyWords().getAttrNi());
            NodeContainer nCont_ = getValue(containers_, idInput_);
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int j = CustList.FIRST_INDEX; j < ch_; j++) {
                elt_.removeChild((MutableNode) children_.item(j));
            }
            Text text_ = _doc.createTextNode(nCont_.getNodeInformation().getValue().first());
            elt_.appendChild(text_);
        }
        setupText(_doc.export());
    }

    private static NodeContainer getValue(LongTreeMap<NodeContainer> containers_, String idInput_) {
        NodeContainer val_;
        if (idInput_.isEmpty()) {
            val_ = null;
        } else {
            val_ = containers_.getVal(Numbers.parseLongZero(idInput_));
        }
        if (val_ == null) {
            val_ = new NodeContainer();
        }
        return val_;
    }

    private void setupText(String _text) {
        Document doc_ = session.getDocument();
        ElementList nodes_;
        title = EMPTY_STRING;
        nodes_ = doc_.getElementsByTagName(session.getRendKeyWords().getKeyWordHead());
        int size_ = nodes_.getLength();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            Element node_ = nodes_.item(i);
            ElementList subNodes_ = node_.getElementsByTagName(session.getRendKeyWords().getAttrTitle());
            int subListSize_ = subNodes_.getLength();
            for (int j = CustList.FIRST_INDEX; j < subListSize_; j++) {
                Element subNode_ = subNodes_.item(j);
                title = subNode_.getTextContent().trim();
            }
        }
        htmlText = _text;
        StringList tokens_ = StringList.splitStrings(currentUrl, REF_TAG);
        if (tokens_.size() > CustList.ONE_ELEMENT) {
            referenceScroll = tokens_.get(CustList.SECOND_INDEX);
            currentUrl = tokens_.first();
        } else {
            referenceScroll = EMPTY_STRING;
        }
    }

    private String getRendUrlDest(String _method, Struct _return, BeanLgNames _advStandards, ContextEl _context) {
        StringMap<String> cases_ = session.getNavigation().getVal(_method);
        if (cases_ == null) {
            return null;
        }
        String case_ = _advStandards.processString(new Argument(_return), _context);
        if (_context.callsOrException()) {
            return null;
        }
        return cases_.getVal(case_);
    }

    private Struct getBean(String _beanName) {
        return session.getBuiltBeans().getVal(_beanName);
    }

    public HtmlPage getHtmlPage() {
        return session.getHtmlPage();
    }

    public Configuration getSession() {
        return session;
    }

    public void setSession(Configuration _session) {
        session = _session;
    }

    public String getCurrentBeanName() {
        return currentBeanName;
    }

    public String getCurrentUrl() {
        return currentUrl;
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
