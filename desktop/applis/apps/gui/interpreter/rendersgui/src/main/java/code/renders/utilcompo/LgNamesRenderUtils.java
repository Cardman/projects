package code.renders.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.scripts.messages.gui.MessCdmRenderGr;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class LgNamesRenderUtils extends BeanCustLgNames implements LgNamesWithNewAliases {

    private final CustAliases custAliases = new CustAliases();
    private final FileInfos infos;

    private ExecutingOptions executingOptions;
    private final ExecutingBlocks executingBlocks = new ExecutingBlocks();
    private final StringMap<String> properties = MessCdmRenderGr.ms();
    public LgNamesRenderUtils(FileInfos _infos,AbstractInterceptor _inter) {
        super(_infos.getGenerator());
        custAliases.setInfos(_infos);
        custAliases.setInterceptor(_inter);
        infos = _infos;
    }

    public FileInfos getInfos() {
        return infos;
    }

    @Override
    public AbstractInterceptor getInterceptor() {
        return custAliases.getInterceptor();
    }

    @Override
    public ExecutingBlocks getExecutingBlocks() {
        return executingBlocks;
    }

    @Override
    public CustAliases getCustAliases() {
        return custAliases;
    }

    public void forwardAndClear(Classes _classes) {
        executingBlocks.forwardAndClear(getContent(),custAliases,_classes);
    }
    @Override
    public void buildOther() {
        getBeanAliases().buildOther(getContent());
        custAliases.buildOther(getContent(), getExecutingBlocks());
    }
    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }

    public AbstractFunctionalInstance newFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl){
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _named, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _named, _contextEl);
    }

    @Override
    public void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess) {
        StringBuilder messPart_ = new StringBuilder();
        StringBuilder rendMessPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder tagsPart_ = new StringBuilder();
        StringBuilder attrsPart_ = new StringBuilder();
        StringBuilder valuesPart_ = new StringBuilder();
        StringBuilder styleAttrsPart_ = new StringBuilder();
        StringBuilder styleValuesPart_ = new StringBuilder();
        StringBuilder styleUnitsPart_ = new StringBuilder();
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringUtil.quickEq(fieldName_, "messages")) {
                messPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "rendmessages")) {
                rendMessPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "keywords")) {
                keyWordsPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "aliases")) {
                aliasesPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "tags")) {
                tagsPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "attrs")) {
                attrsPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "values")) {
                valuesPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "styleAttrs")) {
                styleAttrsPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "styleValues")) {
                styleValuesPart_.append(c.getAttribute("value"));
            }
            if (StringUtil.quickEq(fieldName_, "styleUnits")) {
                styleUnitsPart_.append(c.getAttribute("value"));
            }
        }
        StringMap<String> mess_ = new StringMap<String>();
        StringMap<String> rendMess_ = new StringMap<String>();
        StringMap<String> kw_ = new StringMap<String>();
        StringMap<String> al_ = new StringMap<String>();
        StringMap<String> tags_ = new StringMap<String>();
        StringMap<String> attrs_ = new StringMap<String>();
        StringMap<String> values_ = new StringMap<String>();
        StringMap<String> styleAttrs_ = new StringMap<String>();
        StringMap<String> styleValues_ = new StringMap<String>();
        StringMap<String> styleUnits_ = new StringMap<String>();
        buildMap(messPart_, mess_);
        buildMap(rendMessPart_, rendMess_);
        buildMap(keyWordsPart_, kw_);
        buildMap(aliasesPart_, al_);
        buildMap(tagsPart_, tags_);
        buildMap(attrsPart_, attrs_);
        buildMap(valuesPart_, values_);
        buildMap(styleAttrsPart_, styleAttrs_);
        buildMap(styleUnitsPart_, styleUnits_);
        buildMap(styleValuesPart_, styleValues_);
        if (!_lg.isEmpty()) {
            custAliases.messages(_mess,_lg,mess_);
            rendMessages(_rMess,_lg,rendMess_);
            custAliases.keyWord(_kw,_lg,kw_);
            otherAlias(_lg,al_);
            otherTags(_rkw,_lg,tags_);
            otherAttrs(_rkw,_lg,attrs_);
            otherValues(_rkw,_lg,values_);
            otherStyleAttrs(_rkw,_lg,styleAttrs_);
            otherStyleValues(_rkw,_lg,styleValues_);
            otherStyleUnits(_rkw,_lg,styleUnits_);
        } else {
            custAliases.messages(_mess,mess_,new StringMap<String>());
            rendMessages(_rMess,rendMess_,new StringMap<String>());
            custAliases.keyWord(_kw,kw_,new StringMap<String>());
            allAlias(al_, new StringMap<String>());
            allTags(_rkw,tags_, new StringMap<String>());
            allAttrs(_rkw,attrs_, new StringMap<String>());
            allValues(_rkw,values_,new StringMap<String>());
            allStyleAttrs(_rkw,styleAttrs_,new StringMap<String>());
            allStyleValues(_rkw,styleValues_,new StringMap<String>());
            allStyleUnits(_rkw,styleUnits_,new StringMap<String>());
        }
    }

    private static void buildMap(StringBuilder _parts, StringMap<String> _map) {
        ParseLinesArgUtil.buildMap(_parts, _map);
    }

    private void otherStyleUnits(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"styleunits");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherStyleUnits(_rendKw,util_,_cust);
    }
    private void allStyleUnits(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleUnits(_rendKw,_util,_cust);
    }
    private void otherStyleUnits(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.otherStyleUnits(_util, _cust);
    }
    private void otherStyleValues(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"stylevalues");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherStyleValues(_rendKw,util_,_cust);
    }
    private void allStyleValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleValues(_rendKw,_util,_cust);
    }
    private void otherStyleValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.otherStyleValues(_util, _cust);
    }
    private void otherStyleAttrs(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"styleattrs");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherStyleAttrs(_rendKw,util_,_cust);
    }
    private void allStyleAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleAttrs(_rendKw,_util,_cust);
    }
    private void otherStyleAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.otherStyleAttrs(_util, _cust);
    }
    private void otherValues(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"values");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherValues(_rendKw,util_,_cust);
    }
    private void allValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherValues(_rendKw,_util,_cust);
    }
    private void otherValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.otherValues(_util, _cust);
    }
    private void otherAttrs(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"attrs");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherAttrs(_rendKw,util_,_cust);
    }
    private void allAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherAttrs(_rendKw,_util,_cust);
    }
    private void otherAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.otherAttrs(_util, _cust);
    }
    private void otherTags(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"tags");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherTags(_rendKw,util_,_cust);
    }
    private void allTags(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherTags(_rendKw,_util,_cust);
    }
    private void otherTags(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.otherTags(_util, _cust);
    }
    private void otherAlias(String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"types");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        getBeanAliases().build(util_, _cust);
        custAliases.otherAlias(getContent(),_lang,_cust);
    }
    private void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        getContent().build(_util, _cust);
        getBeanAliases().build(_util, _cust);
        custAliases.build(_util,_cust);
    }


    private void rendMessages(RendAnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_renders/aliases",_lang,"messagesrender");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        rendMessages(_mess,util_,_cust);
    }
    private void rendMessages(RendAnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.rendMessages(_util, _cust);
    }

    public ExecutingOptions getExecutingOptions() {
        return executingOptions;
    }

    public void setExecutingOptions(ExecutingOptions _executingOptions) {
        this.executingOptions = _executingOptions;
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new RunnableContextEl(InitPhase.READ_ONLY_OTHERS, new CommonExecutionInfos(getCustAliases().getInterceptor().newInterceptorStdCaller(getCustAliases().getAliasConcurrentError()),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new CustInitializer(infos.getThreadFactory().newAtomicLong(),getCustAliases().getInterceptor())));
    }
}
