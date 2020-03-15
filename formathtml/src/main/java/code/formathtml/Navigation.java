package code.formathtml;
import code.bean.Bean;
import code.bean.BeanInfo;
import code.bean.validator.Message;
import code.bean.validator.ValidatorInfo;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.formathtml.structs.StdStruct;
import code.sml.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class Navigation {

    private static final String NUMBER_INPUT = "n-i";

    private static final String NUMBER_FORM = "n-f";

    private static final char BEGIN_ARGS = '(';
    private static final char SEP_ARGS = ',';
    private static final char END_ARGS = ')';

    private static final String REF_TAG = "#";

    private static final String TAG_HEAD = "head";

    private static final String TAG_TITLE = "title";

    private static final String PAGE = "page";

    private static final String SESSION = "session";

    private static final String SELECTED = "selected";

    private static final String TAG_OPTION = "option";

    private static final String CHECKED = "checked";

    private static final String ATTRIBUTE_VALUE = "value";

    private static final String ATTRIBUTE_FOR = "for";


    private static final String TAG_SPAN = "span";

    private static final String ON = "on";

    private static final String TAG_SELECT = "select";

    private static final String CHECKBOX = "checkbox";

    private static final String TEXT = "text";

    private static final String RADIO = "radio";

    private static final String ATTRIBUTE_TYPE = "type";

    private static final String TAG_INPUT = "input";

    private static final String TEXT_AREA = "textarea";

    private static final String ATTRIBUTE_COMMAND = "command";

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

    private Object dataBase;
    private Struct dataBaseStruct = NullStruct.NULL_VALUE;

    private String title = EMPTY_STRING;

    private boolean error;

    public void loadConfiguration(String _cont, BeanLgNames _lgNames) {
        error = false;
        DocumentResult res_ = DocumentBuilder.parseSaxHtmlRowCol(_cont);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            error = true;
            return;
        }
        session = new Configuration();
        session.setDataBaseClassName(_lgNames.getAliasObject());
        session.setStandards(_lgNames);
        ReadConfiguration.load(session,doc_);
        if (session.getContext() == null) {
            error = true;
            return;
        }
        session.init();
    }
    public boolean isError() {
        return error;
    }
    public void setLanguage(String _language) {
        language = _language;
        session.setCurrentLanguage(language);
    }

    public void setDataBase(Object _dataBase) {
        dataBase = _dataBase;
        if (dataBase != null) {
            String className_ = session.getDataBaseClassName();
            setDataBaseStruct(StdStruct.newInstance(dataBase, className_));
        } else {
            setDataBaseStruct(NullStruct.NULL_VALUE);
        }
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

    public void initializeRendSession() {
        if (!session.isEmptyErrors()) {
            return;
        }
        BeanLgNames stds_ = session.getAdvStandards();
        stds_.initBeans(session,language,dataBaseStruct);
        if (session.getContext().hasException()) {
            return;
        }
        String currentUrl_ = session.getFirstUrl();
        session.setCurrentUrl(currentUrl_);
        String realFilePath_ = RendExtractFromResources.getRealFilePath(language, currentUrl_);
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = session.getRenders().getVal(realFilePath_);
        if (rendDocumentBlock_ == null) {
            BadElError badEl_ = new BadElError();
            badEl_.setFileName(session.getCurrentFileName());
            badEl_.setIndexFile(session.getCurrentLocationIndex());
            session.addError(badEl_);
            return;
        }
        htmlText = RendBlock.getRes(rendDocumentBlock_,session);
        if (htmlText.isEmpty()) {
            return;
        }
        //For title
        currentBeanName_ = session.getBeanName();
        currentBeanName = currentBeanName_;
        currentUrl = currentUrl_;
        setupText(htmlText);
    }

    public void setupRendClasses() {
        session.setupRendClasses(files);
        initInstancesPattern();
        setupRenders();
    }

    public void setupRenders() {
        BeanLgNames stds_ = session.getAdvStandards();
        stds_.preInitBeans(session);
        session.getAnalyzingDoc().setLanguages(languages);
        session.setCurrentLanguage(language);
        session.setupRenders(files);
    }

    public void initInstancesPattern() {
        String keyWordNew_ = session.getKeyWords().getKeyWordNew();
        for (EntryCust<String, BeanInfo> e: session.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            CustList<RendDynOperationNode> exps_ = RenderExpUtil.getAnalyzedOperations(
                    StringList.concat(keyWordNew_, " ", info_.getClassName(), "()"),
                    0, session, Calculation.staticCalculation(MethodAccessKind.STATIC));
            info_.setExps(exps_);
        }
        for (EntryCust<String,ValidatorInfo> e: session.getLateValidators().entryList()) {
            ValidatorInfo v_ = e.getValue();
            CustList<RendDynOperationNode> exps_ = RenderExpUtil.getAnalyzedOperations(
                    StringList.concat(keyWordNew_, " ", v_.getClassName(), "()"),
                    0, session, Calculation.staticCalculation(MethodAccessKind.STATIC));
            v_.setExps(exps_);
        }
    }

    public void rendRefresh() {
        for (Bean b: session.getBeans().values()) {
            b.setLanguage(language);
        }
        session.setCurrentLanguage(language);
        processRendAnchorRequest(currentUrl);
    }
    public void processRendAnchorRequest(String _anchorRef) {
        if (_anchorRef.contains(CALL_METHOD)) {
            session.clearPages();
            HtmlPage htmlPage_ = session.getHtmlPage();
            ImportingPage ip_ = new ImportingPage();
            ip_.setPrefix(session.getPrefix());
            session.addPage(ip_);
            int indexPoint_ = _anchorRef.indexOf(DOT);
            String action_ = _anchorRef
                    .substring(indexPoint_ + 1);
            String methodName_;
            String suffix_;
            boolean getArg_ = true;
            if (action_.indexOf(BEGIN_ARGS) == CustList.INDEX_NOT_FOUND_ELT) {
                methodName_ = action_;
                suffix_ = EMPTY_STRING;
                getArg_ = false;
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
            ip_.setOffset(_anchorRef.indexOf(CALL_METHOD) + 1);
            Struct bean_ = getBeanOrNull(beanName_);
            ip_.setOffset(indexPoint_+1);
            ip_.setGlobalArgumentStruct(bean_, session);
            session.getContext().setAnalyzing(new AnalyzedPageEl());
            session.getContext().setGlobalClass(ip_.getGlobalClass());
            Struct return_;
            if (htmlPage_.isForm()) {
                if (getArg_) {
                    return_ = RendRequestUtil.invokeMethodWithNumbersBis(
                            session, action_);
                } else {
                    return_ = RendRequestUtil.invokeMethodWithNumbersBis(
                            session, StringList.concat(action_,"()"));
                }
            } else {
                return_=RendRequestUtil.redirect(session,new Argument(bean_),(int)htmlPage_.getUrl());
            }
            if (session.getContext().hasException()) {
                return;
            }
            String urlDest_ = currentUrl;
            if (return_ != NullStruct.NULL_VALUE) {
                ip_.setOffset(_anchorRef.length());
                urlDest_ = getRendUrlDest(StringList.concat(beanName_, DOT, methodName_,suffix_), return_);
                if (session.getContext().hasException()) {
                    return;
                }
                if (urlDest_ == null) {
                    urlDest_ = currentUrl;
                }
            }
            processAfterInvoke(urlDest_,beanName_,bean_);
            return;
        }
        if (_anchorRef.isEmpty()) {
            return;
        }
        Struct bean_ = getBeanOrNull(currentBeanName);
        session.clearPages();
        ImportingPage ip_ = new ImportingPage();
        ip_.setPrefix(session.getPrefix());
        session.addPage(ip_);
        processAfterInvoke(_anchorRef,currentBeanName,bean_);
    }

    private Struct getBeanOrNull(String _currentBeanName) {
        Struct bean_ = getBean(_currentBeanName);
        if (bean_ == null) {
            bean_ = NullStruct.NULL_VALUE;
        }
        return bean_;
    }

    void processAfterInvoke(String _dest, String _beanName, Struct _bean){
        if (!_beanName.isEmpty()) {
            session.getAdvStandards().storeForms(_bean, session);
        }
        if (session.getContext().hasException()) {
            return;
        }
        processInitBeans(_dest,_beanName);
        if (session.getContext().hasException()) {
            return;
        }
        session.setCurrentUrl(_dest);
        String dest_ = StringList.getFirstToken(_dest, REF_TAG);
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = session.getRenders().getVal(dest_);
        if (rendDocumentBlock_ == null) {
            return;
        }
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(currentBeanName_);
        if (!_beanName.isEmpty()) {
            session.getAdvStandards().setStoredForms(bean_, session);
        }
        if (session.getContext().hasException()) {
            return;
        }
        String textToBeChanged_ = RendBlock.getRes(rendDocumentBlock_,session);
        if (textToBeChanged_.isEmpty()) {
            return;
        }
        currentBeanName = session.getBeanName();
        currentUrl = _dest;
        setupText(textToBeChanged_);
    }
    void processInitBeans(String _dest, String _beanName) {
        int s_ = session.getBuiltBeans().size();
        for (int i = 0; i < s_; i++) {
            String key_ = session.getBuiltBeans().getKey(i);
            boolean reinit_ = reinitRendBean(_dest, _beanName, key_);
            if (session.getContext().hasException()) {
                break;
            }
            if (!reinit_) {
                continue;
            }
            Struct bean_ = session.getBuiltBeans().getValue(i);
            BeanInfo info_ = session.getBeansInfos().getValue(i);
            bean_ = session.newBean(language, bean_,info_);
            if (session.getContext().hasException()) {
                break;
            }
            session.getBuiltBeans().setValue(i,bean_);
        }
    }

    private static boolean isPartOfArgument(char _char) {
        if (_char == SEP_ARGS) {
            return false;
        }
        if (_char == BEGIN_ARGS) {
            return false;
        }
        if (_char == END_ARGS) {
            return false;
        }
        return true;
    }

    public void processRendFormRequest() {
        session.clearPages();
        HtmlPage htmlPage_ = session.getHtmlPage();
        ImportingPage ip_ = new ImportingPage();
        ip_.setPrefix(session.getPrefix());
        session.addPage(ip_);
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        long lg_ = htmlPage_.getUrl();
        Document doc_ = session.getDocument();
        String actionCommand_;
        //retrieving form that is submitted
        Element formElement_ = DocumentBuilder.getFirstElementByAttribute(doc_, NUMBER_FORM, String.valueOf(lg_));
        if (formElement_ == null) {
            session.getContext().setException(NullStruct.NULL_VALUE);
            return;
        }
        htmlPage_.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop
        actionCommand_ = formElement_.getAttribute(StringList.concat(ip_.getPrefix(),ATTRIBUTE_COMMAND));

        StringMap<String> errors_;
        errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_;
        errorsArgs_ = new StringMap<StringList>();
        for (EntryCust<Long, NodeContainer> e: containersMap_.getVal(lg_).entryList()) {
            NodeContainer nCont_ = e.getValue();
            NodeInformations nInfos_ = nCont_.getNodeInformation();
            String valId_ = nInfos_.getValidator();
            String id_ = nInfos_.getId();
            Message messageTr_ = session.getAdvStandards().validate(session,nCont_,valId_);
            if (session.getContext().hasException()) {
                return;
            }
            if (messageTr_ != null) {
                errors_.put(id_, messageTr_.getMessage());
                errorsArgs_.put(id_, messageTr_.getArgs());
            }
        }
        //begin deleting previous errors
        ElementList spansForm_ = formElement_.getElementsByTagName(TAG_SPAN);
        int lengthSpansForom_ = spansForm_.getLength();
        for (int j = CustList.FIRST_INDEX; j < lengthSpansForom_; j++) {
            Element elt_ = spansForm_.item(j);
            if (!elt_.hasAttribute(StringList.concat(ip_.getPrefix(),ATTRIBUTE_FOR))) {
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
        updateRendBean(containers_);
        session.clearPages();
        if (session.getContext().hasException()) {
            return;
        }

        //invoke application
        processRendAnchorRequest(actionCommand_);
    }

    private void updateRendBean(LongTreeMap< NodeContainer> _containers) {
        for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
            NodeContainer nCont_ = e.getValue();
            if (!nCont_.isEnabled()) {
                continue;
            }
            Struct newObj_;
            ResultErrorStd res_ = session.getAdvStandards().convert(nCont_, session);
            if (session.getContext().hasException()) {
                return;
            }
            newObj_ = res_.getResult();
            Struct procObj_ = e.getValue().getStruct();
            session.getLastPage().setGlobalArgumentStruct(procObj_, session);
            RendRequestUtil.setRendObject(session, e.getValue(), newObj_);
            if (session.getContext().hasException()) {
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
            ElementList spans_ = _formElement.getElementsByTagName(TAG_SPAN);
            int lengthSpans_ = spans_.getLength();
            for (int j = CustList.FIRST_INDEX; j < lengthSpans_; j++) {
                Element elt_ = spans_.item(j);
                if (!StringList.quickEq(elt_.getAttribute(StringList.concat(session.getPrefix(),ATTRIBUTE_FOR)),i)) {
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
        ElementList inputs_ = _formElement.getElementsByTagName(TAG_INPUT);
        int lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(NUMBER_INPUT);
            if (idInput_.isEmpty()) {
                continue;
            }
            NodeContainer nCont_ = containers_.getVal(Numbers.parseLongZero(idInput_));
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),TEXT)) {
                elt_.setAttribute(ATTRIBUTE_VALUE, nCont_.getNodeInformation().getValue().first());
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),CHECKBOX)) {
                if (StringList.quickEq(nCont_.getNodeInformation().getValue().first(),ON)) {
                    elt_.setAttribute(CHECKED, CHECKED);
                } else {
                    elt_.removeAttribute(CHECKED);
                }
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
                String value_ = elt_.getAttribute(ATTRIBUTE_VALUE);
                if (StringList.quickEq(nCont_.getNodeInformation().getValue().first(), value_)) {
                    elt_.setAttribute(CHECKED, CHECKED);
                } else {
                    elt_.removeAttribute(CHECKED);
                }
                continue;
            }
            elt_.setAttribute(ATTRIBUTE_VALUE, nCont_.getNodeInformation().getValue().first());
        }
        inputs_ = _formElement.getElementsByTagName(TAG_SELECT);
        lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(NUMBER_INPUT);
            if (idInput_.isEmpty()) {
                continue;
            }
            NodeContainer nCont_ = containers_.getVal(Numbers.parseLongZero(idInput_));
            ElementList options_ = elt_.getElementsByTagName(TAG_OPTION);
            int optionsLen_ = options_.getLength();
            for (int j = CustList.FIRST_INDEX; j < optionsLen_; j++) {
                Element option_ = options_.item(j);
                if (StringList.contains(nCont_.getNodeInformation().getValue(), option_.getAttribute(ATTRIBUTE_VALUE))) {
                    option_.setAttribute(SELECTED, SELECTED);
                } else {
                    option_.removeAttribute(SELECTED);
                }
            }
        }
        inputs_ = _formElement.getElementsByTagName(TEXT_AREA);
        lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = inputs_.item(i);
            String idInput_ = elt_.getAttribute(NUMBER_INPUT);
            if (idInput_.isEmpty()) {
                continue;
            }
            NodeContainer nCont_ = containers_.getVal(Numbers.parseLongZero(idInput_));
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

    boolean reinitRendBean(String _dest, String _beanName, String _currentBean) {
        if (!StringList.quickEq(_currentBean,_beanName)) {
            return false;
        }
        Struct bean_ = getBeanOrNull(_currentBean);
        session.addPage(new ImportingPage());
        String scope_ = session.getAdvStandards().getScope(bean_,session);
        session.removeLastPage();
        if (session.getContext().hasException()) {
            return false;
        }
        if (StringList.quickEq(scope_,SESSION)) {
            return false;
        }
        if (StringList.quickEq(scope_,PAGE)) {
            if (StringList.quickEq(currentUrl,StringList.getFirstToken(_dest, REF_TAG))) {
                return false;
            }
        }
        return true;
    }
    void setupText(String _text) {
        Document doc_ = session.getDocument();
        ElementList nodes_;
        title = EMPTY_STRING;
        nodes_ = doc_.getElementsByTagName(TAG_HEAD);
        int size_ = nodes_.getLength();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            Element node_ = nodes_.item(i);
            ElementList subNodes_ = node_.getElementsByTagName(TAG_TITLE);
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

    private String getRendUrlDest(String _method, Struct _return) {
        StringMap<String> cases_ = session.getNavigation().getVal(_method);
        if (cases_ == null) {
            return null;
        }
        String case_ = session.getAdvStandards().processString(new Argument(_return),session);
        if (session.getContext().hasException()) {
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
