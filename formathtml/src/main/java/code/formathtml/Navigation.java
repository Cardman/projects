package code.formathtml;
import java.lang.reflect.Method;

import code.bean.Bean;
import code.bean.validator.Message;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.IndirectException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exceptions.BadParenthesesException;
import code.formathtml.exceptions.FormNotFoundException;
import code.formathtml.exceptions.InexistingValidatorException;
import code.formathtml.exceptions.NavCaseNotFoundException;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.formathtml.util.ValueChangeEvent;
import code.resources.ResourceFiles;
import code.serialize.ConstClasses;
import code.serialize.ConverterMethod;
import code.serialize.SerializeXmlObject;
import code.serialize.exceptions.InexistingValueForEnum;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.sml.Node;
import code.sml.NodeList;
import code.sml.Text;
import code.sml.exceptions.XmlParseException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.Listable;
import code.util.ints.WithMathFactory;

public final class Navigation {

    private static final String NUMBER_ANCHOR = "n-a";

    private static final String NUMBER_INPUT = "n-i";

    private static final String NUMBER_FORM = "n-f";

    private static final String END_TEMP = ">";

    private static final String BEG_TEMP = "<";

    private static final String END_PATH = ":";

    private static final char BEGIN_ARGS = '(';
    private static final char SEP_ARGS = ',';
    private static final char END_ARGS = ')';

    private static final String ATTRIBUTE_TITLE = "title";

    private static final String ATTRIBUTE_HREF = "href";

    private static final String TAG_A = "a";

    private static final String REF_TAG = "#";

    private static final String TAG_HEAD = "head";

    private static final String TAG_TITLE = "title";

    private static final String PAGE = "page";

    private static final String SESSION = "session";

    private static final String SELECTED = "selected";

    private static final String TAG_OPTION = "option";

    private static final String CHECKED = "checked";

    private static final String ATTRIBUTE_VALUE = "value";

    private static final String ATTRIBUTE_VALUE_MESSAGE = "valueMessage";

    private static final String ATTRIBUTE_FOR = "for";


    private static final String TAG_SPAN = "span";

    private static final String ON = "on";

    private static final String TAG_SELECT = "select";

    private static final String CHECKBOX = "checkbox";

    private static final String TEXT = "text";

    private static final String RANGE = "range";

    private static final String RADIO = "radio";

    private static final String NUMBER = "number";

    private static final String ATTRIBUTE_TYPE = "type";

    private static final String ATTRIBUTE_VALIDATOR = "validator";

    private static final String ATTRIBUTE_ID = "id";

    private static final String ATTRIBUTE_GROUP_ID = "groupId";

    private static final String TAG_INPUT = "input";

    private static final String TEXT_AREA = "textarea";

    private static final String ATTRIBUTE_COMMAND = "command";

    private static final String ATTRIBUTE_ACTION = "action";

    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";

    private static final String DOT = ".";

    private static final String CALL_METHOD = "$";

    private static final String RETURN_LINE = "\n";

    private static final String EMPTY_STRING = "";
    private static final String GET_LOC_VAR =";.";
    private static final String VALIDATE ="validate";
    private static final String ADD ="add";

    private static final Method ADD_METHOD = SerializeXmlObject.getMethod(Listable.class, ADD, Object.class);

    private Configuration session = new Configuration();

    private String currentBeanName;

    private String htmlText;

    private String referenceScroll;

    private String currentUrl;

    private String language;

    private StringMap<String> files = new StringMap<String>();

    private Object dataBase;

    private StringList tooltips = new StringList();

    private String title = EMPTY_STRING;

    private String resourcesFolder = EMPTY_STRING;

    public void setFiles(StringMap<String> _web, StringMap<String> _images) {
        files = new StringMap<String>();
        for (String f: _web.getKeys()) {
            files.put(f, _web.getVal(f));
        }
        for (String f: _images.getKeys()) {
            files.put(f, _images.getVal(f));
        }
    }

    public void loadConfiguration(String _conf) {
        boolean found_ = false;
        String fileName_ = EMPTY_STRING;
        for (EntryCust<String, String> e: files.entryList()) {
            if (e.getKey().equalsIgnoreCase(_conf)) {
                fileName_ = e.getKey();
                found_ = true;
                break;
            }
        }
        String content_;
        if (found_) {
            content_ = files.getVal(fileName_);
        } else {
            content_ = ResourceFiles.ressourceFichier(_conf);
        }
        session = (Configuration) SerializeXmlObject.newObjectFromXmlString(content_);
        session.init();
        if (session.getMathFactory() == null) {
            if (dataBase instanceof WithMathFactory) {
                session.setMathFactory(((WithMathFactory)dataBase).getMathFactory());
            }
        }
    }

    public void setLanguage(String _language) {
        language = _language;
    }

    public void setDataBase(Object _dataBase) {
        dataBase = _dataBase;
    }

    public StringList getTooltips() {
        return tooltips;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public String getHtmlTextFormatted() {
        return session.getDocument().exportHtml();
    }

    public String getReferenceScroll() {
        return referenceScroll;
    }

    public void initializeSession() {
        session.setupClasses(files);
        for (EntryCust<String, Bean> e: session.getBeans().entryList()) {
//            Bean bean_ = session.newBean(e.getValue());
//            bean_.setForms(new StringMap<Object>());
//            bean_.setDataBase(dataBase);
//            e.setValue(bean_);
            session.getBuiltBeans().put(e.getKey(), session.newBean(language, dataBase, e.getValue(), true));
        }
        String currentUrl_ = session.getFirstUrl();
        String text_ = ExtractFromResources.loadPage(session, files, currentUrl_, resourcesFolder);
        String currentBeanName_;
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(text_);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            throw new XmlParseException(ExtractFromResources.getRealFilePath(currentUrl_), text_);
        }
        Element root_ = doc_.getDocumentElement();
        session.setDocument(doc_);
        currentBeanName_ = root_.getAttribute(session.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
        htmlText = FormatHtml.processImports(text_, session, language, files, resourcesFolder);
        if (htmlText == null) {
            return;
        }
        //For title
        currentBeanName = currentBeanName_;
        currentUrl = currentUrl_;
        setupText(htmlText);
    }

    public void refresh() {
        for (Bean b: session.getBeans().values()) {
            b.setLanguage(language);
        }
        try {
            processAnchorRequest(currentUrl);
        } catch (Throwable _0) {
            session.setCurrentUrl(currentUrl);
            String currentUrl_ = StringList.getFirstToken(currentUrl,REF_TAG);
            String textToBeChanged_ = ExtractFromResources.loadPage(session, files, currentUrl_, resourcesFolder);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(textToBeChanged_);
            Document doc_ = res_.getDocument();
            if (doc_ == null) {
                throw new XmlParseException(ExtractFromResources.getRealFilePath(currentUrl_), textToBeChanged_);
            }
            session.setDocument(doc_);
            textToBeChanged_ = FormatHtml.processImports(
                    textToBeChanged_, session, language, files, resourcesFolder);
            if (textToBeChanged_ == null) {
                return;
            }
            setupText(textToBeChanged_);
        }
    }

    public void processAnchorRequest(String _anchorRef) {
        String textToBeChanged_;
        if (_anchorRef.contains(CALL_METHOD)) {
            session.clearPages();
            HtmlPage htmlPage_ = session.getHtmlPage();
            ImportingPage ip_ = new ImportingPage(true);
            ip_.setPrefix(session.getPrefix());
            ip_.setHtml(htmlText);
            session.addPage(ip_);
            FormatHtml.setEscapedChars(session, session.getDocument(), htmlText);
            Element node_;
            if (htmlPage_.isForm()) {
                Document doc_ = session.getDocument();
                node_ = DocumentBuilder.getFirstElementByAttribute(doc_, NUMBER_FORM, String.valueOf(htmlPage_.getUrl()));
            } else {
                Document doc_ = session.getDocument();
                node_ = DocumentBuilder.getFirstElementByAttribute(doc_, NUMBER_ANCHOR, String.valueOf(htmlPage_.getUrl()));
                if (node_.getAttribute(ATTRIBUTE_HREF).isEmpty()) {
                    htmlPage_.setUsedFieldUrl(ip_.getPrefix()+ATTRIBUTE_COMMAND);
                } else if (node_.getAttribute(ATTRIBUTE_HREF).endsWith(END_PATH)) {
                    htmlPage_.setUsedFieldUrl(ip_.getPrefix()+ATTRIBUTE_COMMAND);
                } else {
                    htmlPage_.setUsedFieldUrl(ATTRIBUTE_HREF);
                }
            }
            ip_.setProcessingNode(node_);
            ip_.setProcessingAttribute(htmlPage_.getUsedFieldUrl());
            ip_.setLookForAttrValue(true);
            ip_.setOffset(0);
            int indexPoint_ = _anchorRef.indexOf(DOT);
            String action_ = _anchorRef
                    .substring(indexPoint_ + 1);
            String methodName_;
            String suffix_;
            String strArgs_;
            boolean getArg_ = true;
            if (action_.indexOf(BEGIN_ARGS) == CustList.INDEX_NOT_FOUND_ELT) {
                methodName_ = action_;
                suffix_ = EMPTY_STRING;
                strArgs_ = EMPTY_STRING;
                getArg_ = false;
            } else {
                methodName_ = action_.substring(CustList.FIRST_INDEX, action_.indexOf(BEGIN_ARGS));
                suffix_ = action_.substring(action_.indexOf(BEGIN_ARGS));
                strArgs_ = suffix_.substring(CustList.SECOND_INDEX, suffix_.length() - 1);
                StringBuilder str_ = new StringBuilder();
                for (char c: suffix_.toCharArray()) {
                    if (isPartOfArgument(c)) {
                        continue;
                    }
                    str_.append(c);
                }
                suffix_ = str_.toString();
            }
            CustList<Argument> args_ = new CustList<Argument>();
            if (getArg_) {
                ip_.addToOffset(indexPoint_+1+action_.indexOf(BEGIN_ARGS));
                for (String l: StringList.splitChars(strArgs_, SEP_ARGS)) {
                    try {
                        args_.add(Argument.numberToArgument(l));
                    } catch (RuntimeException _0) {
                        throw new BadParenthesesException(session.joinPages());
                    }
                    ip_.addToOffset(l.length()+1);
                }
                ip_.setOffset(0);
            }
            String beanName_ = _anchorRef
                    .substring(_anchorRef.indexOf(CALL_METHOD) + 1, indexPoint_);
            ip_.setOffset(_anchorRef.indexOf(CALL_METHOD) + 1);
            Struct bean_ = getNotNullBean(beanName_);
            ip_.setOffset(indexPoint_+1);
            ip_.setGlobalArgumentStruct(bean_, session);
            Struct return_ = HtmlRequest.invokeMethodWithNumbers(
                    session, bean_, methodName_, Argument.toArgArray(args_));
            Struct forms_ = ExtractObject.getForms(session, bean_);
            String urlDest_ = currentUrl;
            if (!return_.isNull()) {
                ip_.setOffset(_anchorRef.length());
                urlDest_ = getUrlDest(beanName_ + DOT + methodName_+suffix_, return_);
                if (urlDest_ == null) {
                    urlDest_ = currentUrl;
                }
            }
            for (EntryCust<String, Struct> e: session.getBuiltBeans().entryList()) {
                if (!reinitBean(urlDest_, beanName_, e.getKey())) {
                    continue;
                }
                bean_ = e.getValue();
                bean_ = session.newBean(language, bean_);
                ExtractObject.setForms(session, bean_, forms_);
                e.setValue(bean_);
            }
            String currentUrl_ = urlDest_;
            session.setCurrentUrl(currentUrl_);
            String dest_ = StringList.getFirstToken(urlDest_, REF_TAG);
            textToBeChanged_ = ExtractFromResources.loadPage(session, files, dest_, resourcesFolder);
            String currentBeanName_;
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(textToBeChanged_);
            Document doc_ = res_.getDocument();
            if (doc_ == null) {
                throw new XmlParseException(ExtractFromResources.getRealFilePath(dest_), textToBeChanged_);
            }
            Element root_ = doc_.getDocumentElement();
            session.setDocument(doc_);
            currentBeanName_ = root_.getAttribute(session.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
            bean_ = getNotNullBean(currentBeanName_);
            ExtractObject.setForms(session, bean_, forms_);
            textToBeChanged_ = FormatHtml.processImports(
                    textToBeChanged_, session, language, files, resourcesFolder);
            if (textToBeChanged_ == null) {
                return;
            }
            currentBeanName = currentBeanName_;
            currentUrl = currentUrl_;
            setupText(textToBeChanged_);
            return;
        }
        if (_anchorRef.isEmpty()) {
            return;
        }
        Struct bean_ = getBean(currentBeanName);
        Struct forms_;
        try {
            session.addPage(new ImportingPage(false));
            forms_ = ExtractObject.getForms(session, bean_);
            session.removeLastPage();
        } catch (Throwable _0) {
            forms_ = NullStruct.NULL_VALUE;
        }
        for (EntryCust<String, Struct> e: session.getBuiltBeans().entryList()) {
            if (!reinitBean(_anchorRef, currentBeanName, e.getKey())) {
                continue;
            }
            bean_ = e.getValue();
            bean_ = session.newBean(language,bean_);
            session.addPage(new ImportingPage(false));
            ExtractObject.setForms(session, bean_, forms_);
            session.removeLastPage();
            e.setValue(bean_);
        }
        String currentUrl_ = _anchorRef;
        session.setCurrentUrl(currentUrl_);
        String dest_ = StringList.getFirstToken(_anchorRef, REF_TAG);
        textToBeChanged_ = ExtractFromResources.loadPage(session, files, dest_, resourcesFolder);
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(textToBeChanged_);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            throw new XmlParseException(ExtractFromResources.getRealFilePath(dest_), textToBeChanged_);
        }
        String currentBeanName_;
        Element root_ = doc_.getDocumentElement();
        session.setDocument(doc_);
        currentBeanName_ = root_.getAttribute(session.getPrefix()+FormatHtml.BEAN_ATTRIBUTE);
        bean_ = getBean(currentBeanName_);
        try {
            session.addPage(new ImportingPage(false));
            ExtractObject.setForms(session, bean_, forms_);
            session.removeLastPage();
        } catch (Throwable _0) {
        }
        textToBeChanged_ = FormatHtml.processImports(
                textToBeChanged_, session, language, files, resourcesFolder);
        if (textToBeChanged_ == null) {
            return;
        }
        currentBeanName = currentBeanName_;
        currentUrl = currentUrl_;
        setupText(textToBeChanged_);
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

    public void processFormRequest() {
        session.clearPages();
        HtmlPage htmlPage_ = session.getHtmlPage();
        ImportingPage ip_ = new ImportingPage(true);
        ip_.setPrefix(session.getPrefix());
        ip_.setHtml(htmlText);
        session.addPage(ip_);
        NumberMap<Long,NatTreeMap<Long,NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        long lg_ = htmlPage_.getUrl();
        Document doc_ = session.getDocument();
        String actionCommand_ = EMPTY_STRING;
        //retrieving form that is submitted
        Element formElement_ = DocumentBuilder.getFirstElementByAttribute(doc_, NUMBER_FORM, String.valueOf(lg_));
        if (formElement_ == null) {
            htmlPage_.setUsedFieldUrl(EMPTY_STRING);
            throw new FormNotFoundException(EMPTY_STRING);
        }
        htmlPage_.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop
        actionCommand_ = formElement_.getAttribute(ATTRIBUTE_ACTION);
        htmlPage_.setUsedFieldUrl(ATTRIBUTE_ACTION);
        if (actionCommand_.isEmpty()
                || actionCommand_.endsWith(END_PATH)) {
            actionCommand_ = formElement_.getAttribute(ip_.getPrefix()+ATTRIBUTE_COMMAND);
            htmlPage_.setUsedFieldUrl(ip_.getPrefix()+ATTRIBUTE_COMMAND);
        }

        StringMap<String> errors_;
        errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_;
        errorsArgs_ = new StringMap<StringList>();
        //TODO converters
        for (EntryCust<Long, NodeContainer> e: containersMap_.getVal(lg_).entryList()) {
            NodeInformations nInfos_ = e.getValue().getNodeInformation();
            String valId_ = nInfos_.getValidator();
            String id_ = nInfos_.getId();
            if (valId_.isEmpty()) {
                continue;
            }
            Element node_ = DocumentBuilder.getElementById(doc_, ATTRIBUTE_ID, ip_.getPrefix()+ATTRIBUTE_GROUP_ID, id_);
            ip_.setProcessingNode(node_);
            ip_.setProcessingAttribute(ip_.getPrefix()+ATTRIBUTE_VALIDATOR);
            ip_.setLookForAttrValue(true);
            ip_.setOffset(0);
            Struct validator_;
            try {
                validator_ = session.getBuiltValidators().getVal(valId_);
            } catch (Throwable _0) {
                throw new InvokeRedinedMethException(valId_+RETURN_LINE+session.joinPages());
            }
            if (validator_ == null) {
                throw new InexistingValidatorException(valId_+RETURN_LINE+session.joinPages());
            }
            StringList v_ = nInfos_.getValue();
            String className_ = nInfos_.getInputClass();
            Object obj_ = null;
            int indexTemp_;
            indexTemp_ = className_.indexOf(BEG_TEMP);
            boolean isList_ = false;
            String suffix_ = EMPTY_STRING;
            Class<?> tempClass_ = null;
            if (indexTemp_ != CustList.INDEX_NOT_FOUND_ELT) {
                String prefix_ = EMPTY_STRING;
                prefix_ = className_.substring(CustList.FIRST_INDEX, indexTemp_);
                suffix_ = className_.substring(indexTemp_);
                try {
                    tempClass_ = ConstClasses.classAliasForNameNotInit(prefix_);
                } catch (RuntimeClassNotFoundException _0) {
                    throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
                }
                if (Listable.class.isAssignableFrom(tempClass_)) {
                    isList_ = true;
                }
            } else {
                try {
                    tempClass_ = ConstClasses.classAliasForNameNotInit(className_);
                } catch (RuntimeClassNotFoundException _0) {
                    throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
                }
                if (Listable.class.isAssignableFrom(tempClass_)) {
                    suffix_ = Templates.getTypesByBases(className_, Listable.class.getName(), session.toContextEl()).first();
                    isList_ = true;
                }
            }
            if (isList_) {
                Object list_ = instance(tempClass_);
                String contentClass_ = suffix_;
                contentClass_ = StringList.removeStrings(contentClass_, BEG_TEMP, END_TEMP);
                for (String v:v_) {
                    try {
                        ConverterMethod.invokeMethod(ADD_METHOD, list_, retrieveObjectByClassName(v, contentClass_));
                    } catch (Throwable _0) {
                    }
                }
                obj_ = list_;
            } else {
                try {
                    obj_ = retrieveObjectByClassName(v_.first(), className_);
                } catch (Throwable _0) {
                }
            }
            LocalVariable lv_ = new LocalVariable();
            String valName_ = ip_.getNextTempVar();
            lv_ = new LocalVariable();
            lv_.setStruct(validator_);
            lv_.setClassName(validator_.getClassName(session.toContextEl()));
            ip_.getLocalVars().put(valName_, lv_);
            String navName_ = ip_.getNextTempVar();
            lv_ = new LocalVariable();
            lv_.setElement(this);
            lv_.setClassName(Object.class.getName());
            ip_.getLocalVars().put(navName_, lv_);
            String nodName_ = ip_.getNextTempVar();
            lv_ = new LocalVariable();
            lv_.setElement(node_);
            lv_.setClassName(Node.class.getName());
            ip_.getLocalVars().put(nodName_, lv_);
            String objName_ = ip_.getNextTempVar();
            lv_ = new LocalVariable();
            lv_.setElement(obj_);
            lv_.setClassName(Object.class.getName());
            ip_.getLocalVars().put(objName_, lv_);
            String expression_ = valName_ + GET_LOC_VAR+VALIDATE+BEGIN_ARGS;
            expression_ += navName_+GET_LOC_VAR + SEP_ARGS;
            expression_ += nodName_+GET_LOC_VAR + SEP_ARGS;
            expression_ += objName_+GET_LOC_VAR + END_ARGS;
            try {
                Argument message_ = ElUtil.processEl(expression_, 0, session.toContextEl());
                if (!message_.isNull()) {
                    Message messageTr_ = (Message) message_.getObject();
                    errors_.put(id_, messageTr_.format());
                    errorsArgs_.put(id_, messageTr_.getArgs());
                }
            } catch (IndirectException _0) {
                Struct ex_ = _0.getCustCause();
                throw new InvokeRedinedMethException(session.joinPages(), ex_);
            } catch (Throwable _0) {
                throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
            }
        }
        //begin deleting previous errors
        NodeList spansForm_ = formElement_.getElementsByTagName(TAG_SPAN);
        int lengthSpansForom_ = spansForm_.getLength();
        for (int j = CustList.FIRST_INDEX; j < lengthSpansForom_; j++) {
            Element elt_ = (Element) spansForm_.item(j);
            if (!elt_.hasAttribute(ip_.getPrefix()+ATTRIBUTE_FOR)) {
                continue;
            }
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int i = CustList.FIRST_INDEX; i < ch_; i++) {
                elt_.removeChild(children_.item(i));
            }
            Text text_ = doc_.createTextNode(FormatHtml.SPACE);
            elt_.appendChild(text_);
        }
        //end deleting previous errors
        if (!errors_.isEmpty()) {
            processFormErrors(doc_, formElement_, lg_, errors_, errorsArgs_);
            session.clearPages();
            return;
        }
        NatTreeMap<Long, NodeContainer> containers_ = containersMap_.getVal(lg_);
        //Setting values for bean
        updateBean(containers_);
        session.clearPages();

        //invoke application
        processAnchorRequest(actionCommand_);
    }

    /**
    @throws RuntimeClassNotFoundException
    @throws InexistingValueForEnum
    @throws NoSuchDeclaredMethodException*/
    private void updateBean(NatTreeMap<Long, NodeContainer> _containers) {
        Document doc_ = session.getDocument();
        for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
            NodeContainer nCont_ = e.getValue();
            if (!nCont_.isEnabled()) {
                continue;
            }
            Element input_ = DocumentBuilder.getFirstElementByAttribute(doc_, NUMBER_INPUT, Long.toString(e.getKey()));
            session.getLastPage().setProcessingNode(input_);
            session.getLastPage().setProcessingAttribute(EMPTY_STRING);
            Struct bean_ = getBean(nCont_.getBeanName());
            String simpleKey_ = nCont_.getNodeInformation().getName();
            Object obj_ = nCont_.getTypedField();
            Numbers<Long> indexes_ = new Numbers<Long>();
            for (String n: positiveNumbers(simpleKey_)) {
                indexes_.add(Long.parseLong(n));
            }
            String changingValue_ = EMPTY_STRING;
            String ch_ = nCont_.getNodeInformation().getChanging();
            if (!ch_.isEmpty()) {
                String method_ = ch_;
                try {
                    //checking for existence of method_ in the bean class
                    bean_.getClass().getMethod(method_,
                            ValueChangeEvent.class);
                    changingValue_ = method_;
                } catch (Throwable _0) {
                }
            }
            nCont_.getNodeInformation().setChanging(changingValue_);
            Object newObj_;
            StringList v_ = nCont_.getNodeInformation().getValue();
            String className_ = nCont_.getNodeInformation().getInputClass();
            try {
                if (obj_ == null) {
                    newObj_ = retrieveObjectByClassName(v_.first(), className_);
                } else {
                    Class<?> clObj_ = obj_.getClass();
                    if (Listable.class.isAssignableFrom(clObj_)){
                        Object list_ = instance(clObj_);
                        String contentClass_;
                        contentClass_ = Templates.getTypesByBases(clObj_.getName(), Listable.class.getName(), session.toContextEl()).first();
                        for (String v:v_) {
                            ConverterMethod.invokeMethod(ADD_METHOD, list_, retrieveObjectByClassName(v, contentClass_));
                        }
                        newObj_ = list_;
                    } else {
                        newObj_ = retrieveObjectByClassName(v_.first(), obj_.getClass().getName());
                    }
                }
            } catch (Throwable _0) {
                throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
            }
            Struct procObj_ = e.getValue().getStruct();
            session.getLastPage().setGlobalArgumentStruct(procObj_, session);
            HtmlRequest.setObject(session, e.getValue(), newObj_, indexes_);
        }
    }

    private Object retrieveObjectByClassName(String _value, String _className) {
        //case where it is better to test on class of the value
        Class<?> class_;
        try {
            class_ = ConstClasses.classAliasForObjectNameNotInit(_className);
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(new StdStruct(_0));
        }
        if (class_.isEnum()) {
            //Enum
            for (Object o : class_.getEnumConstants()) {
                if (StringList.quickEq(ConverterMethod.getName(o),_value)) {
                    return o;
                }
            }
            throw new InexistingValueForEnum(_value,class_.getName());
        }
        //Boolean
        if (class_ == Boolean.class || class_ == boolean.class) {
            return StringList.quickEq(_value,ON);
        }
        //Number
        if (Number.class.isAssignableFrom(class_) || class_.isPrimitive()) {
            return ExtractObject.instanceByString(session, _className,_value);
        }
        Method method_ = ConverterMethod.getFromStringMethod(class_);
        if (method_ != null) {
            Object instance_;
            try {
                instance_ = method_.invoke(null, _value);
            } catch (Throwable _0) {
                instance_ = null;
            }
            if (instance_ == null) {
                throw new InvokeRedinedMethException(session.joinPages());
            }
            return instance_;
        }
        return _value;
    }

    private static Object instance(Class<?> _class) {
        try {
            return ConverterMethod.newInstance(_class.getDeclaredConstructor());
        } catch (Throwable _0) {
            throw new ErrorCausingException(new StdStruct(_0));
        }
    }

    private static StringList positiveNumbers(String _string) {
        StringList tokens_ = StringList.getWordsSeparators(_string);
        StringList newList_ = new StringList();
        for (String t: tokens_) {
            if (StringList.isPositiveNumber(t)) {
                newList_.add(t);
            }
        }
        return newList_;
    }

    private void processFormErrors(Document _doc, Element _formElement, long _id,
            StringMap<String> _errors, StringMap<StringList> _errorsArgs) {
        HtmlPage htmlPage_ = session.getHtmlPage();
        NumberMap<Long,NatTreeMap<Long,NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        NatTreeMap<Long, NodeContainer> containers_ = containersMap_.getVal(_id);
        for (String i : _errors.getKeys()) {
            NodeList spans_ = _formElement.getElementsByTagName(TAG_SPAN);
            int lengthSpans_ = spans_.getLength();
            for (int j = CustList.FIRST_INDEX; j < lengthSpans_; j++) {
                Element elt_ = (Element) spans_.item(j);
                if (!StringList.quickEq(elt_.getAttribute(session.getPrefix()+ATTRIBUTE_FOR),i)) {
                    continue;
                }
                int len_ = elt_.getChildNodes().getLength();
                if (len_ > CustList.ONE_ELEMENT) {
                    continue;
                }
                if (len_ == CustList.ONE_ELEMENT) {
                    elt_.removeChild(elt_.getChildNodes().item(0));
                }
                String error_ = _errors.getVal(i);
                String valueMessage_ = elt_.getAttribute(StringList.concat(session.getPrefix(),ATTRIBUTE_VALUE_MESSAGE));
                if (!valueMessage_.isEmpty()) {
                    error_ = HtmlRequest.formatErrorMessage(session, valueMessage_, elt_.hasAttribute(StringList.concat(session.getPrefix(),ATTRIBUTE_ESCAPED_EAMP)), language, files, resourcesFolder, _errorsArgs.getVal(i).toArray());
                }
                Text text_ = _doc.createTextNode(error_);
                elt_.appendChild(text_);
            }
        }
        NodeList inputs_ = _doc.getElementsByTagName(TAG_INPUT);
        int lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = (Element) inputs_.item(i);
            String idInput_ = elt_.getAttribute(NUMBER_INPUT);
            if (idInput_.isEmpty()) {
                continue;
            }
            NodeContainer nCont_ = containers_.getVal(Long.parseLong(idInput_));
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),TEXT)) {
                elt_.setAttribute(ATTRIBUTE_VALUE, nCont_.getNodeInformation().getValue().first());
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),NUMBER)) {
                elt_.setAttribute(ATTRIBUTE_VALUE, nCont_.getNodeInformation().getValue().first());
                continue;
            }
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),RANGE)) {
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
                if (value_ == null) {
                    value_ = EMPTY_STRING;
                }
                if (StringList.quickEq(nCont_.getNodeInformation().getValue().first(), value_)) {
                    elt_.setAttribute(CHECKED, CHECKED);
                } else {
                    elt_.removeAttribute(CHECKED);
                }
            }
        }
        inputs_ = _doc.getElementsByTagName(TAG_SELECT);
        lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = (Element) inputs_.item(i);
            String idInput_ = elt_.getAttribute(NUMBER_INPUT);
            if (idInput_.isEmpty()) {
                continue;
            }
            NodeContainer nCont_ = containers_.getVal(Long.parseLong(idInput_));
            NodeList options_ = elt_.getElementsByTagName(TAG_OPTION);
            int optionsLen_ = options_.getLength();
            for (int j = CustList.FIRST_INDEX; j < optionsLen_; j++) {
                Element option_ = (Element) options_.item(j);
                if (nCont_.getNodeInformation().getValue().containsStr(option_.getAttribute(ATTRIBUTE_VALUE))) {
                    option_.setAttribute(SELECTED, SELECTED);
                } else {
                    option_.removeAttribute(SELECTED);
                }
            }
        }
        inputs_ = _doc.getElementsByTagName(TEXT_AREA);
        lengthInputs_ = inputs_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthInputs_; i++) {
            Element elt_ = (Element) inputs_.item(i);
            String idInput_ = elt_.getAttribute(NUMBER_INPUT);
            if (idInput_.isEmpty()) {
                continue;
            }
            NodeContainer nCont_ = containers_.getVal(Long.parseLong(idInput_));
            NodeList children_ = elt_.getChildNodes();
            int ch_ = children_.getLength();
            for (int j = CustList.FIRST_INDEX; j < ch_; j++) {
                elt_.removeChild(children_.item(j));
            }
            Text text_ = _doc.createTextNode(nCont_.getNodeInformation().getValue().first());
            elt_.appendChild(text_);
        }
        setupText(DocumentBuilder.toXml(_doc));
    }

    boolean reinitBean(String _dest, String _beanName, String _currentBean) {
        if (!StringList.quickEq(_currentBean,_beanName)) {
            return false;
        }
        Struct bean_ = getNotNullBean(_currentBean);
        session.addPage(new ImportingPage(false));
        String scope_ = ExtractObject.getScope(session, bean_);
        session.removeLastPage();
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
        String textToDisplay_ = _text;
        tooltips.clear();
        Document doc_ = session.getDocument();
        NodeList nodes_ = doc_.getElementsByTagName(TAG_A);
        int size_ = nodes_.getLength();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            Element node_ = (Element) nodes_.item(i);
            if (node_.getAttribute(ATTRIBUTE_HREF).isEmpty()) {
                continue;
            }
            String title_ = node_.getAttribute(ATTRIBUTE_TITLE);
            if (title_.isEmpty()) {
                continue;
            }
            tooltips.add(title_);
        }
        title = EMPTY_STRING;
        nodes_ = doc_.getElementsByTagName(TAG_HEAD);
        size_ = nodes_.getLength();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            Element node_ = (Element) nodes_.item(i);
            NodeList subNodes_ = node_.getElementsByTagName(TAG_TITLE);
            int subListSize_ = subNodes_.getLength();
            for (int j = CustList.FIRST_INDEX; j < subListSize_; j++) {
                Element subNode_ = (Element) subNodes_.item(j);
                title = subNode_.getTextContent().trim();
            }
        }
        htmlText = textToDisplay_;
        StringList tokens_ = StringList.splitStrings(currentUrl, REF_TAG);
        if (tokens_.size() > CustList.ONE_ELEMENT) {
            referenceScroll = tokens_.get(CustList.SECOND_INDEX);
        } else {
            referenceScroll = EMPTY_STRING;
        }
    }

    private String getUrlDest(String _method, Struct _return) {
        StringMap<String> cases_;
        try {
            cases_ = session.getNavigation().getVal(_method);
        } catch (Error _0) {
            throw new NavCaseNotFoundException(_method+RETURN_LINE+session.joinPages());
        } catch (RuntimeException _0) {
            throw new NavCaseNotFoundException(_method+RETURN_LINE+session.joinPages());
        }
        if (cases_ == null) {
            throw new NavCaseNotFoundException(_method+RETURN_LINE+session.joinPages());
        }
        try {
            return cases_.getVal(ExtractObject.toString(session, _return));
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
        }
    }
    private Struct getBean(String _beanName) {
        try {
            return session.getBuiltBeans().getVal(_beanName);
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
        }
    }
    private Struct getNotNullBean(String _beanName) {
        try {
            Struct b_ = session.getBuiltBeans().getVal(_beanName);
            b_.getClass();
            return b_;
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(session.joinPages(), new StdStruct(_0));
        }
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

    public void setCurrentBeanName(String _currentBeanName) {
        currentBeanName = _currentBeanName;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String _currentUrl) {
        currentUrl = _currentUrl;
    }

    public StringMap<String> getFiles() {
        return files;
    }

    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public String getLanguage() {
        return language;
    }

    public Object getDataBase() {
        return dataBase;
    }

    public void setHtmlText(String _htmlText) {
        htmlText = _htmlText;
    }

    public void setTooltips(StringList _tooltips) {
        tooltips = _tooltips;
    }

    public String getTitle() {
        return title;
    }

    public String getResourcesFolder() {
        return resourcesFolder;
    }

    public void setResourcesFolder(String _resourcesFolder) {
        resourcesFolder = _resourcesFolder;
    }
}
