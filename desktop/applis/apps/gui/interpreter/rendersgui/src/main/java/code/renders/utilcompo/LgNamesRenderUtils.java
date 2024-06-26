package code.renders.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.LgNamesUtilsContent;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.scripts.messages.gui.MessCdmRenderGr;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class LgNamesRenderUtils extends BeanCustLgNames implements LgNamesWithNewAliases {
    public static final String RESOURCES_RENDERS_ALIASES = "resources_renders/aliases";
    private final LgNamesUtilsContent execContent;
    private final StringMap<String> properties = MessCdmRenderGr.ms();
    public LgNamesRenderUtils(FileInfos _infos,AbstractInterceptor _inter) {
        super(_infos.getGenerator());
        execContent = new LgNamesUtilsContent(_infos, _inter);
    }

    @Override
    public StringViewReplaceAliases getStrAlias() {
        return getExecContent().getCustAliases().getStringViewReplaceAliases();
    }

    @Override
    public LgNamesUtilsContent getExecContent() {
        return execContent;
    }

    @Override
    public void build() {
        buildBase();
        buildOtherBean();
    }

    @Override
    public void buildOtherBean() {
        getBeanAliases().buildOther(getContent(), getRendExecutingBlocks());
        execContent.getCustAliases().buildOther(getContent(), execContent.getExecutingBlocks());
    }
    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _functional, _named, _contextEl);
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
            feed(fieldName_, "messages", messPart_, c);
            feed(fieldName_, "rendmessages", rendMessPart_, c);
            feed(fieldName_, "keywords", keyWordsPart_, c);
            feed(fieldName_, "aliases", aliasesPart_, c);
            feed(fieldName_, "tags", tagsPart_, c);
            feed(fieldName_, "attrs", attrsPart_, c);
            feed(fieldName_, "values", valuesPart_, c);
            feed(fieldName_, "styleAttrs", styleAttrsPart_, c);
            feed(fieldName_, "styleValues", styleValuesPart_, c);
            feed(fieldName_, "styleUnits", styleUnitsPart_, c);
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
            execContent.getCustAliases().messages(_mess, mess_);
            rendMessages(_rMess,_lg,rendMess_);
            execContent.getCustAliases().keyWord(_kw, kw_);
            otherAlias(_lg,al_);
            otherTags(_rkw,_lg,tags_);
            otherAttrs(_rkw,_lg,attrs_);
            otherValues(_rkw,_lg,values_);
            otherStyleAttrs(_rkw,_lg,styleAttrs_);
            otherStyleValues(_rkw,_lg,styleValues_);
            otherStyleUnits(_rkw,_lg,styleUnits_);
        } else {
            execContent.getCustAliases().messages(_mess,mess_,new StringMap<String>());
            rendMessages(_rMess,rendMess_,new StringMap<String>());
            execContent.getCustAliases().keyWord(_kw,kw_,new StringMap<String>());
            allAlias(al_, new StringMap<String>());
            allTags(_rkw,tags_, new StringMap<String>());
            allAttrs(_rkw,attrs_, new StringMap<String>());
            allValues(_rkw,values_,new StringMap<String>());
            allStyleAttrs(_rkw,styleAttrs_,new StringMap<String>());
            allStyleValues(_rkw,styleValues_,new StringMap<String>());
            allStyleUnits(_rkw,styleUnits_,new StringMap<String>());
        }
    }

    private void feed(String _field, String _cst, StringBuilder _part, Element _c) {
        if (StringUtil.quickEq(_field, _cst)) {
            _part.append(_c.getAttribute("value"));
        }
    }

    private static void buildMap(StringBuilder _parts, StringMap<String> _map) {
        ParseLinesArgUtil.buildMap(_parts, _map);
    }

    private void otherStyleUnits(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"styleunits");
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"stylevalues");
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"styleattrs");
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"values");
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"attrs");
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"tags");
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"types");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        getBeanAliases().build(util_, _cust);
        execContent.getCustAliases().otherAlias(getContent(), _cust);
    }
    private void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        getBeanAliases().build(_util, _cust);
        execContent.getCustAliases().otherAlias(getContent(),_cust);
    }


    private void rendMessages(RendAnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_RENDERS_ALIASES,_lang,"messagesrender");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        rendMessages(_mess,util_,_cust);
    }
    private void rendMessages(RendAnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.rendMessages(_util, _cust);
    }

    @Override
    public String getAliasLgInt() {
        return getExecContent().getCustAliases().getMathAdvAliases().getAliasLgInt();
    }

    @Override
    public String getAliasRate() {
        return getExecContent().getCustAliases().getMathAdvAliases().getAliasRate();
    }

    @Override
    public ContextEl newContext(AbstractAtomicBoolean _at, CommonExecutionInfos _common, StringList _args) {
        return new RunnableContextEl(_at,null,_common,_args);
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return commonExecutionInfos(execContent.getCustAliases().getInterceptor().newInterceptorStdCaller(execContent.getCustAliases().getAliasConcurrentError()), _opt, _options, new CustInitializer(execContent.getInfos().getThreadFactory().newAtomicLong(),execContent.getCustAliases().getInterceptor()));
    }

    @Override
    public AbstractInterceptorStdCaller interceptor() {
        return execContent.getCustAliases().getInterceptor().newInterceptorStdCaller(execContent.getCustAliases().getAliasConcurrentError());
    }
    @Override
    public StringList args() {
        return new StringList();
    }

    @Override
    public AbstractFileBuilder newFileBuilder() {
        return new CustBeanFileBuilder(getContent(), getBeanAliases(), execContent.getCustAliases());
    }

    @Override
    public CustList<AbsAliasFileBuilder> newFileBuilders() {
        CustList<AbsAliasFileBuilder> bs_ = new CustList<AbsAliasFileBuilder>();
        bs_.add(new DefAliasFileBuilder());
        bs_.add(getBeanAliases());
        bs_.add(execContent.getCustAliases());
        bs_.add(execContent.getCustAliases().getStringViewReplaceAliases());
        return bs_;
    }

    @Override
    public StringMap<String> mappingMessages() {
        return execContent.getCustAliases().extractMessagesKeys();
    }

    @Override
    public StringMap<String> mappingKeywords() {
        return execContent.getCustAliases().extractKeywordsKeys();
    }

    @Override
    public StringMap<String> mappingAliases() {
        return execContent.getCustAliases().extractAliasesKeys();
    }
}
