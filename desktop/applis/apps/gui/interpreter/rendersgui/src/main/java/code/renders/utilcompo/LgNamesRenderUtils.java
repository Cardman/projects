package code.renders.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class LgNamesRenderUtils extends BeanCustLgNames {

    private CustRenderAliases custRenderAliases = new CustRenderAliases();

    private AbstractResourcesReader reader = new DefaultResourcesReader();

    public CustRenderAliases getCustRenderAliases() {
        return custRenderAliases;
    }

    public LgNamesRenderUtils(AbstractGenerator _gene) {
        super(_gene);
    }

    @Override
    public void buildOther() {
        super.buildOther();
        custRenderAliases.buildOther(getContent());
    }
    @Override
    public Argument defaultInstance(ContextEl _cont, String _id) {
        return custRenderAliases.defaultInstance(_cont, _id);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return custRenderAliases.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        return custRenderAliases.getOtherResult(_cont, _instance, _method, _args);
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custRenderAliases.allTableTypeVarTypes().entryList()) {
            t_.addEntry(o.getKey(),o.getValue());
        }
        return t_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(custRenderAliases.allMergeTableTypeMethodNames(getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: custRenderAliases.allTableTypeFieldNames().entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> e: custRenderAliases.allTableTypeMethodNames(getContent()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String,String> o: custRenderAliases.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
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
            if (StringList.quickEq(fieldName_, "messages")) {
                messPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "rendmessages")) {
                rendMessPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "keywords")) {
                keyWordsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "aliases")) {
                aliasesPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "tags")) {
                tagsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "attrs")) {
                attrsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "values")) {
                valuesPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "styleAttrs")) {
                styleAttrsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "styleValues")) {
                styleValuesPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "styleUnits")) {
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
        if (StringList.quickEq(_lg, "en")) {
            messages(_mess,_lg,mess_);
            rendMessages(_rMess,_lg,rendMess_);
            keyWord(_kw,_lg,kw_);
            otherAlias(_lg,al_);
            otherTags(_rkw,_lg,tags_);
            otherAttrs(_rkw,_lg,attrs_);
            otherValues(_rkw,_lg,values_);
            otherStyleAttrs(_rkw,_lg,styleAttrs_);
            otherStyleValues(_rkw,_lg,styleValues_);
            otherStyleUnits(_rkw,_lg,styleUnits_);
        } else if (StringList.quickEq(_lg, "fr")) {
            messages(_mess,_lg,mess_);
            rendMessages(_rMess,_lg,rendMess_);
            keyWord(_kw,_lg,kw_);
            otherAlias(_lg,al_);
            otherTags(_rkw,_lg,tags_);
            otherAttrs(_rkw,_lg,attrs_);
            otherValues(_rkw,_lg,values_);
            otherStyleAttrs(_rkw,_lg,styleAttrs_);
            otherStyleValues(_rkw,_lg,styleValues_);
            otherStyleUnits(_rkw,_lg,styleUnits_);
        } else {
            messages(_mess,mess_,new StringMap<String>());
            rendMessages(_rMess,rendMess_,new StringMap<String>());
            keyWord(_kw,kw_,new StringMap<String>());
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
        if (_parts.length() > 0) {
            StringList infos_ = StringList.splitChars(_parts.toString(),',');
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringList.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(value_);
                _map.put(key_,value_);
            }
        }
    }

    private void otherStyleUnits(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"styleunits");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherStyleUnits(_rendKw,util_,_cust);
    }
    private void allStyleUnits(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleUnits(_rendKw,_util,_cust);
    }
    private void otherStyleUnits(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setStyleUnitSolid(get(_util,_cust,RendKeyWords.STYLE_UNIT_SOLID));
        _rendKw.setStyleUnitPx(get(_util,_cust,RendKeyWords.STYLE_UNIT_PX));
        _rendKw.setStyleUnitEm(get(_util,_cust,RendKeyWords.STYLE_UNIT_EM));
    }
    private void otherStyleValues(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"stylevalues");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherStyleValues(_rendKw,util_,_cust);
    }
    private void allStyleValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleValues(_rendKw,_util,_cust);
    }
    private void otherStyleValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setStyleValueGreen(get(_util,_cust,RendKeyWords.STYLE_VALUE_GREEN));
        _rendKw.setStyleValueBlue(get(_util,_cust,RendKeyWords.STYLE_VALUE_BLUE));
        _rendKw.setStyleValueYellow(get(_util,_cust,RendKeyWords.STYLE_VALUE_YELLOW));
        _rendKw.setStyleValueMagenta(get(_util,_cust,RendKeyWords.STYLE_VALUE_MAGENTA));
        _rendKw.setStyleValueCyan(get(_util,_cust,RendKeyWords.STYLE_VALUE_CYAN));
        _rendKw.setStyleValueBlack(get(_util,_cust,RendKeyWords.STYLE_VALUE_BLACK));
        _rendKw.setStyleValueGrey(get(_util,_cust,RendKeyWords.STYLE_VALUE_GREY));
        _rendKw.setStyleValueWhite(get(_util,_cust,RendKeyWords.STYLE_VALUE_WHITE));
        _rendKw.setStyleValueRgb(get(_util,_cust,RendKeyWords.STYLE_VALUE_RGB));
        _rendKw.setStyleValueRed(get(_util,_cust,RendKeyWords.STYLE_VALUE_RED));
    }
    private void otherStyleAttrs(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"styleattrs");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherStyleAttrs(_rendKw,util_,_cust);
    }
    private void allStyleAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleAttrs(_rendKw,_util,_cust);
    }
    private void otherStyleAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setStyleAttrColor(get(_util,_cust,RendKeyWords.STYLE_ATTR_COLOR));
        _rendKw.setStyleAttrBackground(get(_util,_cust,RendKeyWords.STYLE_ATTR_BACKGROUND));
        _rendKw.setStyleAttrBorder(get(_util,_cust,RendKeyWords.STYLE_ATTR_BORDER));
        _rendKw.setStyleAttrFontSize(get(_util,_cust,RendKeyWords.STYLE_ATTR_FONTSIZE));
        _rendKw.setStyleAttrFontFam(get(_util,_cust,RendKeyWords.STYLE_ATTR_FONTFAM));
    }
    private void otherValues(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"values");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherValues(_rendKw,util_,_cust);
    }
    private void allValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherValues(_rendKw,_util,_cust);
    }
    private void otherValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setValueNumber(get(_util,_cust,RendKeyWords.VALUE_NUMBER));
        _rendKw.setValueRange(get(_util,_cust,RendKeyWords.VALUE_RANGE));
        _rendKw.setValueRadio(get(_util,_cust,RendKeyWords.VALUE_RADIO));
        _rendKw.setValueCheckbox(get(_util,_cust,RendKeyWords.VALUE_CHECKBOX));
        _rendKw.setValueText(get(_util,_cust,RendKeyWords.VALUE_TEXT));
        _rendKw.setValueLiMinLet(get(_util,_cust,RendKeyWords.VALUE_LIMINLET));
        _rendKw.setValueLiDisk(get(_util,_cust,RendKeyWords.VALUE_LIDISK));
        _rendKw.setValueLiRect(get(_util,_cust,RendKeyWords.VALUE_LIRECT));
        _rendKw.setValueLiSquare(get(_util,_cust,RendKeyWords.VALUE_LISQUARE));
        _rendKw.setValueLiNb(get(_util,_cust,RendKeyWords.VALUE_LINB));
        _rendKw.setValueLiMajLat(get(_util,_cust,RendKeyWords.VALUE_LIMAJLAT));
        _rendKw.setValueStyle(get(_util,_cust,RendKeyWords.VALUE_STYLE));
        _rendKw.setValueLiCircle(get(_util,_cust,RendKeyWords.VALUE_LICIRCLE));
        _rendKw.setValueLiMajLet(get(_util,_cust,RendKeyWords.VALUE_LIMAJLET));
        _rendKw.setValueLiMinLat(get(_util,_cust,RendKeyWords.VALUE_LIMINLAT));
        _rendKw.setValueSubmit(get(_util,_cust,RendKeyWords.VALUE_SUBMIT));
    }
    private void otherAttrs(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"attrs");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherAttrs(_rendKw,util_,_cust);
    }
    private void allAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherAttrs(_rendKw,_util,_cust);
    }
    private void otherAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setAttrType(get(_util,_cust,RendKeyWords.ATTR_TYPE));
        _rendKw.setAttrClassName(get(_util,_cust,RendKeyWords.ATTR_CLASSNAME));
        _rendKw.setAttrMultiple(get(_util,_cust,RendKeyWords.ATTR_MULTIPLE));
        _rendKw.setAttrValidator(get(_util,_cust,RendKeyWords.ATTR_VALIDATOR));
        _rendKw.setAttrSelected(get(_util,_cust,RendKeyWords.ATTR_SELECTED));
        _rendKw.setAttrAction(get(_util,_cust,RendKeyWords.ATTR_ACTION));
        _rendKw.setAttrAlias(get(_util,_cust,RendKeyWords.ATTR_ALIAS));
        _rendKw.setAttrNf(get(_util,_cust,RendKeyWords.ATTR_N_F));
        _rendKw.setAttrNi(get(_util,_cust,RendKeyWords.ATTR_N_I));
        _rendKw.setAttrChecked(get(_util,_cust,RendKeyWords.ATTR_CHECKED));
        _rendKw.setAttrLabel(get(_util,_cust,RendKeyWords.ATTR_LABEL));
        _rendKw.setAttrCondition(get(_util,_cust,RendKeyWords.ATTR_CONDITION));
        _rendKw.setAttrFor(get(_util,_cust,RendKeyWords.ATTR_FOR));
        _rendKw.setAttrTitle(get(_util,_cust,RendKeyWords.ATTR_TITLE));
        _rendKw.setAttrConvert(get(_util,_cust,RendKeyWords.ATTR_CONVERT));
        _rendKw.setAttrEscaped(get(_util,_cust,RendKeyWords.ATTR_ESCAPED));
        _rendKw.setAttrHref(get(_util,_cust,RendKeyWords.ATTR_HREF));
        _rendKw.setAttrCommand(get(_util,_cust,RendKeyWords.ATTR_COMMAND));
        _rendKw.setAttrMessage(get(_util,_cust,RendKeyWords.ATTR_MESSAGE));
        _rendKw.setAttrDefault(get(_util,_cust,RendKeyWords.ATTR_DEFAULT));
        _rendKw.setAttrPrepare(get(_util,_cust,RendKeyWords.ATTR_PREPARE));
        _rendKw.setAttrForm(get(_util,_cust,RendKeyWords.ATTR_FORM));
        _rendKw.setAttrId(get(_util,_cust,RendKeyWords.ATTR_ID));
        _rendKw.setAttrGroupId(get(_util,_cust,RendKeyWords.ATTR_GROUPID));
        _rendKw.setAttrVarValue(get(_util,_cust,RendKeyWords.ATTR_VARVALUE));
        _rendKw.setAttrBean(get(_util,_cust,RendKeyWords.ATTR_BEAN));
        _rendKw.setAttrNa(get(_util,_cust,RendKeyWords.ATTR_N_A));
        _rendKw.setAttrParam(get(_util,_cust,RendKeyWords.ATTR_PARAM));
        _rendKw.setAttrName(get(_util,_cust,RendKeyWords.ATTR_NAME));
        _rendKw.setAttrQuoted(get(_util,_cust,RendKeyWords.ATTR_QUOTED));
        _rendKw.setAttrRel(get(_util,_cust,RendKeyWords.ATTR_REL));
        _rendKw.setAttrSrc(get(_util,_cust,RendKeyWords.ATTR_SRC));
        _rendKw.setAttrDelay(get(_util,_cust,RendKeyWords.ATTR_DELAY));
        _rendKw.setAttrPage(get(_util,_cust,RendKeyWords.ATTR_PAGE));
        _rendKw.setAttrRows(get(_util,_cust,RendKeyWords.ATTR_ROWS));
        _rendKw.setAttrClass(get(_util,_cust,RendKeyWords.ATTR_CLASS));
        _rendKw.setAttrCols(get(_util,_cust,RendKeyWords.ATTR_COLS));
        _rendKw.setAttrWidth(get(_util,_cust,RendKeyWords.ATTR_WIDTH));
        _rendKw.setAttrList(get(_util,_cust,RendKeyWords.ATTR_LIST));
        _rendKw.setAttrMap(get(_util,_cust,RendKeyWords.ATTR_MAP));
        _rendKw.setAttrKey(get(_util,_cust,RendKeyWords.ATTR_KEY));
        _rendKw.setAttrTo(get(_util,_cust,RendKeyWords.ATTR_TO));
        _rendKw.setAttrEq(get(_util,_cust,RendKeyWords.ATTR_EQ));
        _rendKw.setAttrVar(get(_util,_cust,RendKeyWords.ATTR_VAR));
        _rendKw.setAttrStep(get(_util,_cust,RendKeyWords.ATTR_STEP));
        _rendKw.setAttrFrom(get(_util,_cust,RendKeyWords.ATTR_FROM));
        _rendKw.setAttrValue(get(_util,_cust,RendKeyWords.ATTR_VALUE));
        _rendKw.setAttrInit(get(_util,_cust,RendKeyWords.ATTR_INIT));
        _rendKw.setAttrKeepFields(get(_util,_cust,RendKeyWords.ATTR_KEEPFIELDS));
        _rendKw.setAttrValueMessage(get(_util,_cust,RendKeyWords.ATTR_VALUEMESSAGE));
        _rendKw.setAttrConvertField(get(_util,_cust,RendKeyWords.ATTR_CONVERTFIELD));
        _rendKw.setAttrKeyClassName(get(_util,_cust,RendKeyWords.ATTR_KEYCLASSNAME));
        _rendKw.setAttrVarClassName(get(_util,_cust,RendKeyWords.ATTR_VARCLASSNAME));
        _rendKw.setAttrIndexClassName(get(_util,_cust,RendKeyWords.ATTR_INDEXCLASSNAME));
        _rendKw.setAttrEscapedAmp(get(_util,_cust,RendKeyWords.ATTR_ESCAPEDAMP));
        _rendKw.setAttrConvertValue(get(_util,_cust,RendKeyWords.ATTR_CONVERTVALUE));
        _rendKw.setAttrConvertFieldValue(get(_util,_cust,RendKeyWords.ATTR_CONVERTFIELDVALUE));
    }
    private void otherTags(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"tags");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherTags(_rendKw,util_,_cust);
    }
    private void allTags(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherTags(_rendKw,_util,_cust);
    }
    private void otherTags(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setKeyWordTextarea(get(_util,_cust,RendKeyWords.TAG_TEXTAREA));
        _rendKw.setKeyWordSelect(get(_util,_cust,RendKeyWords.TAG_SELECT));
        _rendKw.setKeyWordFinally(get(_util,_cust,RendKeyWords.TAG_FINALLY));
        _rendKw.setKeyWordContinue(get(_util,_cust,RendKeyWords.TAG_CONTINUE));
        _rendKw.setKeyWordPackage(get(_util,_cust,RendKeyWords.TAG_PACKAGE));
        _rendKw.setKeyWordDefault(get(_util,_cust,RendKeyWords.TAG_DEFAULT));
        _rendKw.setKeyWordReturn(get(_util,_cust,RendKeyWords.TAG_RETURN));
        _rendKw.setKeyWordFor(get(_util,_cust,RendKeyWords.TAG_FOR));
        _rendKw.setKeyWordIf(get(_util,_cust,RendKeyWords.TAG_IF));
        _rendKw.setKeyWordClass(get(_util,_cust,RendKeyWords.TAG_CLASS));
        _rendKw.setKeyWordElseif(get(_util,_cust,RendKeyWords.TAG_ELSEIF));
        _rendKw.setKeyWordThrow(get(_util,_cust,RendKeyWords.TAG_THROW));
        _rendKw.setKeyWordBreak(get(_util,_cust,RendKeyWords.TAG_BREAK));
        _rendKw.setKeyWordCatch(get(_util,_cust,RendKeyWords.TAG_CATCH));
        _rendKw.setKeyWordCase(get(_util,_cust,RendKeyWords.TAG_CASE));
        _rendKw.setKeyWordTry(get(_util,_cust,RendKeyWords.TAG_TRY));
        _rendKw.setKeyWordElse(get(_util,_cust,RendKeyWords.TAG_ELSE));
        _rendKw.setKeyWordSwitch(get(_util,_cust,RendKeyWords.TAG_SWITCH));
        _rendKw.setKeyWordWhile(get(_util,_cust,RendKeyWords.TAG_WHILE));
        _rendKw.setKeyWordDo(get(_util,_cust,RendKeyWords.TAG_DO));
        _rendKw.setKeyWordCaption(get(_util,_cust,RendKeyWords.TAG_CAPTION));
        _rendKw.setKeyWordMessage(get(_util,_cust,RendKeyWords.TAG_MESSAGE));
        _rendKw.setKeyWordAnchor(get(_util,_cust,RendKeyWords.TAG_A));
        _rendKw.setKeyWordParam(get(_util,_cust,RendKeyWords.TAG_PARAM));
        _rendKw.setKeyWordForm(get(_util,_cust,RendKeyWords.TAG_FORM));
        _rendKw.setKeyWordSpan(get(_util,_cust,RendKeyWords.TAG_SPAN));
        _rendKw.setKeyWordImg(get(_util,_cust,RendKeyWords.TAG_IMG));
        _rendKw.setKeyWordInput(get(_util,_cust,RendKeyWords.TAG_INPUT));
        _rendKw.setKeyWordLink(get(_util,_cust,RendKeyWords.TAG_LINK));
        _rendKw.setKeyWordSet(get(_util,_cust,RendKeyWords.TAG_SET));
        _rendKw.setKeyWordSubmit(get(_util,_cust,RendKeyWords.TAG_SUBMIT));
        _rendKw.setKeyWordStyle(get(_util,_cust,RendKeyWords.TAG_STYLE));
        _rendKw.setKeyWordField(get(_util,_cust,RendKeyWords.TAG_FIELD));
        _rendKw.setKeyWordImport(get(_util,_cust,RendKeyWords.TAG_IMPORT));
        _rendKw.setKeyWordItalic(get(_util,_cust,RendKeyWords.TAG_I));
        _rendKw.setKeyWordHr(get(_util,_cust,RendKeyWords.TAG_HR));
        _rendKw.setKeyWordTd(get(_util,_cust,RendKeyWords.TAG_TD));
        _rendKw.setKeyWordBody(get(_util,_cust,RendKeyWords.TAG_BODY));
        _rendKw.setKeyWordOption(get(_util,_cust,RendKeyWords.TAG_OPTION));
        _rendKw.setKeyWordUl(get(_util,_cust,RendKeyWords.TAG_UL));
        _rendKw.setKeyWordHTwo(get(_util,_cust,RendKeyWords.TAG_H2));
        _rendKw.setKeyWordPre(get(_util,_cust,RendKeyWords.TAG_PRE));
        _rendKw.setKeyWordBr(get(_util,_cust,RendKeyWords.TAG_BR));
        _rendKw.setKeyWordHSix(get(_util,_cust,RendKeyWords.TAG_H6));
        _rendKw.setKeyWordMap(get(_util,_cust,RendKeyWords.TAG_MAP));
        _rendKw.setKeyWordTh(get(_util,_cust,RendKeyWords.TAG_TH));
        _rendKw.setKeyWordLi(get(_util,_cust,RendKeyWords.TAG_LI));
        _rendKw.setKeyWordHOne(get(_util,_cust,RendKeyWords.TAG_H1));
        _rendKw.setKeyWordOl(get(_util,_cust,RendKeyWords.TAG_OL));
        _rendKw.setKeyWordBold(get(_util,_cust,RendKeyWords.TAG_B));
        _rendKw.setKeyWordDiv(get(_util,_cust,RendKeyWords.TAG_DIV));
        _rendKw.setKeyWordTr(get(_util,_cust,RendKeyWords.TAG_TR));
        _rendKw.setKeyWordHFive(get(_util,_cust,RendKeyWords.TAG_H5));
        _rendKw.setKeyWordPar(get(_util,_cust,RendKeyWords.TAG_P));
        _rendKw.setKeyWordHead(get(_util,_cust,RendKeyWords.TAG_HEAD));
        _rendKw.setKeyWordHFour(get(_util,_cust,RendKeyWords.TAG_H4));
        _rendKw.setKeyWordTable(get(_util,_cust,RendKeyWords.TAG_TABLE));
        _rendKw.setKeyWordHThree(get(_util,_cust,RendKeyWords.TAG_H3));
    }
    private void otherAlias(String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"types");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        custRenderAliases.build(getContent(),getBeanAliases(),util_,_cust);
        custRenderAliases.build(util_,_cust);
    }
    private void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        custRenderAliases.build(getContent(),getBeanAliases(),_util,_cust);
        custRenderAliases.build(_util,_cust);
    }
    private void keyWord(KeyWords _kw,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"keywords");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        keyWord(_kw,util_,_cust);
    }
    private void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
        _kw.setKeyWordContinue(get(_util,_cust, KeyWords.CONTINUE));
        _kw.setKeyWordInstanceof(get(_util,_cust, KeyWords.INSTANCEOF));
        _kw.setKeyWordInterface(get(_util,_cust, KeyWords.INTERFACE));
        _kw.setKeyWordAbstract(get(_util,_cust, KeyWords.ABSTRACT));
        _kw.setKeyWordElseif(get(_util,_cust, KeyWords.ELSEIF));
        _kw.setKeyWordCast(get(_util,_cust, KeyWords.CAST));
        _kw.setKeyWordExplicit(get(_util,_cust, KeyWords.EXPLICIT));
        _kw.setKeyWordFor(get(_util,_cust, KeyWords.FOR));
        _kw.setKeyWordVar(get(_util,_cust, KeyWords.VAR));
        _kw.setKeyWordStatic(get(_util,_cust, KeyWords.STATIC));
        _kw.setKeyWordStaticCall(get(_util,_cust, KeyWords.STATIC_CALL));
        _kw.setKeyWordNull(get(_util,_cust, KeyWords.NULL));
        _kw.setKeyWordClass(get(_util,_cust, KeyWords.CLASS));
        _kw.setKeyWordFalse(get(_util,_cust, KeyWords.FALSE));
        _kw.setKeyWordFinal(get(_util,_cust, KeyWords.FINAL));
        _kw.setKeyWordBreak(get(_util,_cust, KeyWords.BREAK));
        _kw.setKeyWordIf(get(_util,_cust, KeyWords.IF));
        _kw.setKeyWordNew(get(_util,_cust, KeyWords.NEW));
        _kw.setKeyWordWhile(get(_util,_cust, KeyWords.WHILE));
        _kw.setKeyWordReturn(get(_util,_cust, KeyWords.RETURN));
        _kw.setKeyWordTrue(get(_util,_cust, KeyWords.TRUE));
        _kw.setKeyWordPublic(get(_util,_cust, KeyWords.PUBLIC));
        _kw.setKeyWordPrivate(get(_util,_cust, KeyWords.PRIVATE));
        _kw.setKeyWordAnnotation(get(_util,_cust, KeyWords.ANNOTATION));
        _kw.setKeyWordToString(get(_util,_cust, KeyWords.TO_STRING));
        _kw.setKeyWordNbSufBytePrim(get(_util,_cust, KeyWords.NB_SUF_BYTE_PRIM));
        _kw.setKeyWordNbSufByte(get(_util,_cust, KeyWords.NB_SUF_BYTE));
        _kw.setKeyWordNbSufShortPrim(get(_util,_cust, KeyWords.NB_SUF_SHORT_PRIM));
        _kw.setKeyWordNbSufShort(get(_util,_cust, KeyWords.NB_SUF_SHORT));
        _kw.setKeyWordNbSufCharacterPrim(get(_util,_cust, KeyWords.NB_SUF_CHARACTER_PRIM));
        _kw.setKeyWordNbSufCharacter(get(_util,_cust, KeyWords.NB_SUF_CHARACTER));
        _kw.setKeyWordNbSufIntegerPrim(get(_util,_cust, KeyWords.NB_SUF_INTEGER_PRIM));
        _kw.setKeyWordNbSufInteger(get(_util,_cust, KeyWords.NB_SUF_INTEGER));
        _kw.setKeyWordNbSufLongPrim(get(_util,_cust, KeyWords.NB_SUF_LONG_PRIM));
        _kw.setKeyWordNbSufLong(get(_util,_cust, KeyWords.NB_SUF_LONG));
        _kw.setKeyWordNbSufFloatPrim(get(_util,_cust, KeyWords.NB_SUF_FLOAT_PRIM));
        _kw.setKeyWordNbSufFloat(get(_util,_cust, KeyWords.NB_SUF_FLOAT));
        _kw.setKeyWordNbSufDoublePrim(get(_util,_cust, KeyWords.NB_SUF_DOUBLE_PRIM));
        _kw.setKeyWordNbSufDouble(get(_util,_cust, KeyWords.NB_SUF_DOUBLE));
        _kw.setKeyWordIter(get(_util,_cust, KeyWords.ITER));
        _kw.setKeyWordValue(get(_util,_cust, KeyWords.VALUE));
        _kw.setKeyWordElse(get(_util,_cust, KeyWords.ELSE));
        _kw.setKeyWordCatch(get(_util,_cust, KeyWords.CATCH));
        _kw.setKeyWordThrow(get(_util,_cust, KeyWords.THROW));
        _kw.setKeyWordTry(get(_util,_cust, KeyWords.TRY));
        _kw.setKeyWordThis(get(_util,_cust, KeyWords.THIS));
        _kw.setKeyWordSuper(get(_util,_cust, KeyWords.SUPER));
        _kw.setKeyWordCase(get(_util,_cust, KeyWords.CASE));
        _kw.setKeyWordDo(get(_util,_cust, KeyWords.DO));
        _kw.setKeyWordEnum(get(_util,_cust, KeyWords.ENUM));
        _kw.setKeyWordSwitch(get(_util,_cust, KeyWords.SWITCH));
        _kw.setKeyWordIntern(get(_util,_cust, KeyWords.INTERN));
        _kw.setKeyWordNormal(get(_util,_cust, KeyWords.NORMAL));
        _kw.setKeyWordEscTab(get(_util,_cust, KeyWords.ESC_TAB));
        _kw.setKeyWordNbHex(get(_util,_cust, KeyWords.NB_HEX));
        _kw.setKeyWordNbHexEnd(get(_util,_cust, KeyWords.NB_HEX_END));
        _kw.setKeyWordNbBin(get(_util,_cust, KeyWords.NB_BIN));
        _kw.setKeyWordThat(get(_util,_cust, KeyWords.THAT));
        _kw.setKeyWordBool(get(_util,_cust, KeyWords.BOOL));
        _kw.setKeyWordValues(get(_util,_cust, KeyWords.VALUES));
        _kw.setKeyWordLambda(get(_util,_cust, KeyWords.LAMBDA));
        _kw.setKeyWordVararg(get(_util,_cust, KeyWords.VARARG));
        _kw.setKeyWordId(get(_util,_cust, KeyWords.ID));
        _kw.setKeyWordForeach(get(_util,_cust, KeyWords.FOREACH));
        _kw.setKeyWordNbExpBin(get(_util,_cust, KeyWords.NB_EXP_BIN));
        _kw.setKeyWordClasschoice(get(_util,_cust, KeyWords.CLASSCHOICE));
        _kw.setKeyWordFirstopt(get(_util,_cust, KeyWords.FIRSTOPT));
        _kw.setKeyWordPackage(get(_util,_cust, KeyWords.PACKAGE));
        _kw.setKeyWordFinally(get(_util,_cust, KeyWords.FINALLY));
        _kw.setKeyWordEscUnicode(get(_util,_cust, KeyWords.ESC_UNICODE));
        _kw.setKeyWordThisaccess(get(_util,_cust, KeyWords.THISACCESS));
        _kw.setKeyWordValueOf(get(_util,_cust, KeyWords.VALUE_OF));
        _kw.setKeyWordDefaultValue(get(_util,_cust, KeyWords.DEFAULT_VALUE));
        _kw.setKeyWordEscLine(get(_util,_cust, KeyWords.ESC_LINE));
        _kw.setKeyWordOperator(get(_util,_cust, KeyWords.OPERATOR));
        _kw.setKeyWordInterfaces(get(_util,_cust, KeyWords.INTERFACES));
        _kw.setKeyWordSuperaccess(get(_util,_cust, KeyWords.SUPERACCESS));
        _kw.setKeyWordEscBound(get(_util,_cust, KeyWords.ESC_BOUND));
        _kw.setKeyWordEscForm(get(_util,_cust, KeyWords.ESC_FORM));
        _kw.setKeyWordEscFeed(get(_util,_cust, KeyWords.ESC_FEED));
        _kw.setKeyWordNbExpDec(get(_util,_cust, KeyWords.NB_EXP_DEC));
        _kw.setKeyWordProtected(get(_util,_cust, KeyWords.PROTECTED));
        _kw.setKeyWordDefault(get(_util,_cust, KeyWords.DEFAULT));
        _kw.setKeyWordParent(get(_util,_cust, KeyWords.PARENT));
    }


    private void rendMessages(RendAnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messagesrender");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        rendMessages(_mess,util_,_cust);
    }
    private void rendMessages(RendAnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setBadInputName(get(_util,_cust,RendAnalysisMessages.BAD_INPUT_NAME));
        _mess.setStaticInputName(get(_util,_cust,RendAnalysisMessages.STATIC_INPUT_NAME));
        _mess.setUnexpectedChildTag(get(_util,_cust,RendAnalysisMessages.UNEXPECTED_CHILD_TAG));
        _mess.setEmptyAttr(get(_util,_cust,RendAnalysisMessages.EMPTY_ATTR));
        _mess.setUnexpectedExp(get(_util,_cust,RendAnalysisMessages.UNEXPECTED_EXP));
        _mess.setInexistantFile(get(_util,_cust,RendAnalysisMessages.INEXISTANT_FILE));
        _mess.setInexistantKey(get(_util,_cust,RendAnalysisMessages.INEXISTANT_KEY));
        _mess.setBadDocument(get(_util,_cust,RendAnalysisMessages.BAD_DOCUMENT));
    }
    private void messages(AnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messages");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        messages(_mess,util_,_cust);
    }
    private void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setDuplicateMergedMethod(get(_util,_cust,AnalysisMessages.DUPLICATE_MERGED_METHOD));
        _mess.setDuplicateField(get(_util,_cust,AnalysisMessages.DUPLICATE_FIELD));
        _mess.setDuplicateVarType(get(_util,_cust,AnalysisMessages.DUPLICATE_VAR_TYPE));
        _mess.setEmptyPkgRefType(get(_util,_cust,AnalysisMessages.EMPTY_PKG_REF_TYPE));
        _mess.setPrimitiveKeyWord(get(_util,_cust,AnalysisMessages.PRIMITIVE_KEY_WORD));
        _mess.setDefaultPkgRefType(get(_util,_cust,AnalysisMessages.DEFAULT_PKG_REF_TYPE));
        _mess.setRefTypeKeyWord(get(_util,_cust,AnalysisMessages.REF_TYPE_KEY_WORD));
        _mess.setDigitFirstPrimitive(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_PRIMITIVE));
        _mess.setIllegalFirstChar(get(_util,_cust,AnalysisMessages.ILLEGAL_FIRST_CHAR));
        _mess.setEmptyPrimitive(get(_util,_cust,AnalysisMessages.EMPTY_PRIMITIVE));
        _mess.setNotWordCharPrimitive(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_PRIMITIVE));
        _mess.setNotWordCharRefType(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_REF_TYPE));
        _mess.setEmptyRefTypeIn(get(_util,_cust,AnalysisMessages.EMPTY_REF_TYPE_IN));
        _mess.setRefTypePrimitive(get(_util,_cust,AnalysisMessages.REF_TYPE_PRIMITIVE));
        _mess.setDigitFirstRefType(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_REF_TYPE));
        _mess.setDuplicatePrimtive(get(_util,_cust,AnalysisMessages.DUPLICATE_PRIMTIVE));
        _mess.setDuplicateMethod(get(_util,_cust,AnalysisMessages.DUPLICATE_METHOD));
        _mess.setDefaultPkgNoMatch(get(_util,_cust,AnalysisMessages.DEFAULT_PKG_NO_MATCH));
        _mess.setDuplicateRefType(get(_util,_cust,AnalysisMessages.DUPLICATE_REF_TYPE));
        _mess.setDigitFirstMethod(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_METHOD));
        _mess.setNotWordCharField(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_FIELD));
        _mess.setNotWordCharMethod(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_METHOD));
        _mess.setVarTypeKeyWord(get(_util,_cust,AnalysisMessages.VAR_TYPE_KEY_WORD));
        _mess.setVarTypePrimitive(get(_util,_cust,AnalysisMessages.VAR_TYPE_PRIMITIVE));
        _mess.setDigitFirstVarType(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_VAR_TYPE));
        _mess.setDuplicateNumberWord(get(_util,_cust,AnalysisMessages.DUPLICATE_NUMBER_WORD));
        _mess.setMethodPrimitive(get(_util,_cust,AnalysisMessages.METHOD_PRIMITIVE));
        _mess.setFieldPrimitive(get(_util,_cust,AnalysisMessages.FIELD_PRIMITIVE));
        _mess.setDuplicateStringWord(get(_util,_cust,AnalysisMessages.DUPLICATE_STRING_WORD));
        _mess.setDuplicateKeyWord(get(_util,_cust,AnalysisMessages.DUPLICATE_KEY_WORD));
        _mess.setDigitFirstField(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_FIELD));
        _mess.setDuplicateStartingNb(get(_util,_cust,AnalysisMessages.DUPLICATE_STARTING_NB));
        _mess.setDuplicateStartingUni(get(_util,_cust,AnalysisMessages.DUPLICATE_STARTING_UNI));
        _mess.setDuplicateStarting(get(_util,_cust,AnalysisMessages.DUPLICATE_STARTING));
        _mess.setNotWordCharVarType(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_VAR_TYPE));
        _mess.setEmptyPreBin(get(_util,_cust,AnalysisMessages.EMPTY_PRE_BIN));
        _mess.setEmptyVarType(get(_util,_cust,AnalysisMessages.EMPTY_VAR_TYPE));
        _mess.setEmptyWord(get(_util,_cust,AnalysisMessages.EMPTY_WORD));
        _mess.setEmptyField(get(_util,_cust,AnalysisMessages.EMPTY_FIELD));
        _mess.setEmptyNb(get(_util,_cust,AnalysisMessages.EMPTY_NB));
        _mess.setNotWordChar(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR));
        _mess.setEmptyPreHex(get(_util,_cust,AnalysisMessages.EMPTY_PRE_HEX));
        _mess.setEmptyMethod(get(_util,_cust,AnalysisMessages.EMPTY_METHOD));
        _mess.setDigitFirst(get(_util,_cust,AnalysisMessages.DIGIT_FIRST));
        _mess.setEmptyBinExp(get(_util,_cust,AnalysisMessages.EMPTY_BIN_EXP));
        _mess.setEmptyString(get(_util,_cust,AnalysisMessages.EMPTY_STRING));
        _mess.setIllegalChar(get(_util,_cust,AnalysisMessages.ILLEGAL_CHAR));
        _mess.setMethodKeyWord(get(_util,_cust,AnalysisMessages.METHOD_KEY_WORD));
        _mess.setEmptyRefType(get(_util,_cust,AnalysisMessages.EMPTY_REF_TYPE));
        _mess.setFieldKeyWord(get(_util,_cust,AnalysisMessages.FIELD_KEY_WORD));
        _mess.setAbstractMethodBody(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_BODY));
        _mess.setAbstractMethodConc(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_CONC));
        _mess.setAbstractMethodImpl(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_IMPL));
        _mess.setAbstractMethodRef(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_REF));
        _mess.setInaccessibleType(get(_util,_cust,AnalysisMessages.INACCESSIBLE_TYPE));
        _mess.setUnexpectedType(get(_util,_cust,AnalysisMessages.UNEXPECTED_TYPE));
        _mess.setUnexpectedRetType(get(_util,_cust,AnalysisMessages.UNEXPECTED_RET_TYPE));
        _mess.setMethodsAccesses(get(_util,_cust,AnalysisMessages.METHODS_ACCESSES));
        _mess.setEmptyPackage(get(_util,_cust,AnalysisMessages.EMPTY_PACKAGE));
        _mess.setEmptyPartClassName(get(_util,_cust,AnalysisMessages.EMPTY_PART_CLASS_NAME));
        _mess.setBadPartClassName(get(_util,_cust,AnalysisMessages.BAD_PART_CLASS_NAME));
        _mess.setKeyWordPartClassName(get(_util,_cust,AnalysisMessages.KEY_WORD_PART_CLASS_NAME));
        _mess.setPrimPartClassName(get(_util,_cust,AnalysisMessages.PRIM_PART_CLASS_NAME));
        _mess.setDigitPartClassName(get(_util,_cust,AnalysisMessages.DIGIT_PART_CLASS_NAME));
        _mess.setBadPartVarClassName(get(_util,_cust,AnalysisMessages.BAD_PART_VAR_CLASS_NAME));
        _mess.setKeyWordPartVarClassName(get(_util,_cust,AnalysisMessages.KEY_WORD_PART_VAR_CLASS_NAME));
        _mess.setPrimPartVarClassName(get(_util,_cust,AnalysisMessages.PRIM_PART_VAR_CLASS_NAME));
        _mess.setDigitPartVarClassName(get(_util,_cust,AnalysisMessages.DIGIT_PART_VAR_CLASS_NAME));
        _mess.setDuplicatedPartVarClassName(get(_util,_cust,AnalysisMessages.DUPLICATED_PART_VAR_CLASS_NAME));
        _mess.setCallCtorEnd(get(_util,_cust,AnalysisMessages.CALL_CTOR_END));
        _mess.setCallCtor(get(_util,_cust,AnalysisMessages.CALL_CTOR));
        _mess.setCallCtorBeforeBlock(get(_util,_cust,AnalysisMessages.CALL_CTOR_BEFORE_BLOCK));
        _mess.setCallCtorFirstLine(get(_util,_cust,AnalysisMessages.CALL_CTOR_FIRST_LINE));
        _mess.setCallCtorIntFromSuperInt(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_FROM_SUPER_INT));
        _mess.setCallCtorIntNotFromInt(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_NOT_FROM_INT));
        _mess.setCallCtorIntAfterSuperThis(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_AFTER_SUPER_THIS));
        _mess.setCallCtorIntInherits(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_INHERITS));
        _mess.setCallCtorSuperClassEnumSingleton(get(_util,_cust,AnalysisMessages.CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON));
        _mess.setAnnotFieldNotUniq(get(_util,_cust,AnalysisMessages.ANNOT_FIELD_NOT_UNIQ));
        _mess.setAnnotFieldMust(get(_util,_cust,AnalysisMessages.ANNOT_FIELD_MUST));
        _mess.setDupSuppliedAnnotField(get(_util,_cust,AnalysisMessages.DUP_SUPPLIED_ANNOT_FIELD));
        _mess.setBadExpression(get(_util,_cust,AnalysisMessages.BAD_EXPRESSION));
        _mess.setBadFieldName(get(_util,_cust,AnalysisMessages.BAD_FIELD_NAME));
        _mess.setKeyWordFieldName(get(_util,_cust,AnalysisMessages.KEY_WORD_FIELD_NAME));
        _mess.setPrimFieldName(get(_util,_cust,AnalysisMessages.PRIM_FIELD_NAME));
        _mess.setDigitFieldName(get(_util,_cust,AnalysisMessages.DIGIT_FIELD_NAME));
        _mess.setNotRetrievedFields(get(_util,_cust,AnalysisMessages.NOT_RETRIEVED_FIELDS));
        _mess.setBadNbFormat(get(_util,_cust,AnalysisMessages.BAD_NB_FORMAT));
        _mess.setBadCharFormat(get(_util,_cust,AnalysisMessages.BAD_CHAR_FORMAT));
        _mess.setBadImplicitCast(get(_util,_cust,AnalysisMessages.BAD_IMPLICIT_CAST));
        _mess.setNotPrimitiveWrapper(get(_util,_cust,AnalysisMessages.NOT_PRIMITIVE_WRAPPER));
        _mess.setVoidType(get(_util,_cust,AnalysisMessages.VOID_TYPE));
        _mess.setBadIndexInParser(get(_util,_cust,AnalysisMessages.BAD_INDEX_IN_PARSER));
        _mess.setIllegalCharacter(get(_util,_cust,AnalysisMessages.ILLEGAL_CHARACTER));
        _mess.setCallIntInherits(get(_util,_cust,AnalysisMessages.CALL_INT_INHERITS));
        _mess.setCallIntNoNeed(get(_util,_cust,AnalysisMessages.CALL_INT_NO_NEED));
        _mess.setCallIntNoNeedType(get(_util,_cust,AnalysisMessages.CALL_INT_NO_NEED_TYPE));
        _mess.setCallIntNeedType(get(_util,_cust,AnalysisMessages.CALL_INT_NEED_TYPE));
        _mess.setCallIntOnly(get(_util,_cust,AnalysisMessages.CALL_INT_ONLY));
        _mess.setBadInheritsType(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE));
        _mess.setBadInheritsTypeInn(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE_INN));
        _mess.setBadInheritsTypeAsInn(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE_AS_INN));
        _mess.setBadInheritsTypeInt(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE_INT));
        _mess.setFinalType(get(_util,_cust,AnalysisMessages.FINAL_TYPE));
        _mess.setDuplicateSuper(get(_util,_cust,AnalysisMessages.DUPLICATE_SUPER));
        _mess.setReservedType(get(_util,_cust,AnalysisMessages.RESERVED_TYPE));
        _mess.setSuperClass(get(_util,_cust,AnalysisMessages.SUPER_CLASS));
        _mess.setUnknownSuperType(get(_util,_cust,AnalysisMessages.UNKNOWN_SUPER_TYPE));
        _mess.setCyclicInherits(get(_util,_cust,AnalysisMessages.CYCLIC_INHERITS));
        _mess.setAnnotationParam(get(_util,_cust,AnalysisMessages.ANNOTATION_PARAM));
        _mess.setCyclicMapping(get(_util,_cust,AnalysisMessages.CYCLIC_MAPPING));
        _mess.setAbsMapping(get(_util,_cust,AnalysisMessages.ABS_MAPPING));
        _mess.setFinalMapping(get(_util,_cust,AnalysisMessages.FINAL_MAPPING));
        _mess.setMustCallIntCtor(get(_util,_cust,AnalysisMessages.MUST_CALL_INT_CTOR));
        _mess.setMustNotCallIntCtorAfterThis(get(_util,_cust,AnalysisMessages.MUST_NOT_CALL_INT_CTOR_AFTER_THIS));
        _mess.setMustCallIntCtorNeed(get(_util,_cust,AnalysisMessages.MUST_CALL_INT_CTOR_NEED));
        _mess.setMustCallIntCtorNotNeed(get(_util,_cust,AnalysisMessages.MUST_CALL_INT_CTOR_NOT_NEED));
        _mess.setBadLabel(get(_util,_cust,AnalysisMessages.BAD_LABEL));
        _mess.setDuplicatedLabel(get(_util,_cust,AnalysisMessages.DUPLICATED_LABEL));
        _mess.setBadMethodName(get(_util,_cust,AnalysisMessages.BAD_METHOD_NAME));
        _mess.setKeyWordMethodName(get(_util,_cust,AnalysisMessages.KEY_WORD_METHOD_NAME));
        _mess.setPrimMethodName(get(_util,_cust,AnalysisMessages.PRIM_METHOD_NAME));
        _mess.setDigitMethodName(get(_util,_cust,AnalysisMessages.DIGIT_METHOD_NAME));
        _mess.setBadOperatorName(get(_util,_cust,AnalysisMessages.BAD_OPERATOR_NAME));
        _mess.setBadAccess(get(_util,_cust,AnalysisMessages.BAD_ACCESS));
        _mess.setBadReturnType(get(_util,_cust,AnalysisMessages.BAD_RETURN_TYPE));
        _mess.setBadParams(get(_util,_cust,AnalysisMessages.BAD_PARAMS));
        _mess.setBadMethodModifier(get(_util,_cust,AnalysisMessages.BAD_METHOD_MODIFIER));
        _mess.setBadMethodVararg(get(_util,_cust,AnalysisMessages.BAD_METHOD_VARARG));
        _mess.setBadIndexerParams(get(_util,_cust,AnalysisMessages.BAD_INDEXER_PARAMS));
        _mess.setBadIndexerModifier(get(_util,_cust,AnalysisMessages.BAD_INDEXER_MODIFIER));
        _mess.setBadIndexerModifiers(get(_util,_cust,AnalysisMessages.BAD_INDEXER_MODIFIERS));
        _mess.setBadIndexerAccesses(get(_util,_cust,AnalysisMessages.BAD_INDEXER_ACCESSES));
        _mess.setBadIndexerPairGet(get(_util,_cust,AnalysisMessages.BAD_INDEXER_PAIR_GET));
        _mess.setBadIndexerPairSet(get(_util,_cust,AnalysisMessages.BAD_INDEXER_PAIR_SET));
        _mess.setDuplicateCustomMethod(get(_util,_cust,AnalysisMessages.DUPLICATE_CUSTOM_METHOD));
        _mess.setReservedCustomMethod(get(_util,_cust,AnalysisMessages.RESERVED_CUSTOM_METHOD));
        _mess.setDuplicateIndexer(get(_util,_cust,AnalysisMessages.DUPLICATE_INDEXER));
        _mess.setDuplicateOperator(get(_util,_cust,AnalysisMessages.DUPLICATE_OPERATOR));
        _mess.setFunctionalApplyNbDiff(get(_util,_cust,AnalysisMessages.FUNCTIONAL_APPLY_NB_DIFF));
        _mess.setFunctionalApplyOnly(get(_util,_cust,AnalysisMessages.FUNCTIONAL_APPLY_ONLY));
        _mess.setOperatorNbDiff(get(_util,_cust,AnalysisMessages.OPERATOR_NB_DIFF));
        _mess.setSplitComa(get(_util,_cust,AnalysisMessages.SPLIT_COMA));
        _mess.setSplitComaLow(get(_util,_cust,AnalysisMessages.SPLIT_COMA_LOW));
        _mess.setSplitDiff(get(_util,_cust,AnalysisMessages.SPLIT_DIFF));
        _mess.setBadDotted(get(_util,_cust,AnalysisMessages.BAD_DOTTED));
        _mess.setBadParamName(get(_util,_cust,AnalysisMessages.BAD_PARAM_NAME));
        _mess.setReservedParamName(get(_util,_cust,AnalysisMessages.RESERVED_PARAM_NAME));
        _mess.setDuplicatedParamName(get(_util,_cust,AnalysisMessages.DUPLICATED_PARAM_NAME));
        _mess.setBadReturnTypeInherit(get(_util,_cust,AnalysisMessages.BAD_RETURN_TYPE_INHERIT));
        _mess.setBadReturnTypeIndexer(get(_util,_cust,AnalysisMessages.BAD_RETURN_TYPE_INDEXER));
        _mess.setDuplicatedOverriding(get(_util,_cust,AnalysisMessages.DUPLICATED_OVERRIDING));
        _mess.setTwoFinal(get(_util,_cust,AnalysisMessages.TWO_FINAL));
        _mess.setFinalNotSubReturnType(get(_util,_cust,AnalysisMessages.FINAL_NOT_SUB_RETURN_TYPE));
        _mess.setTwoReturnTypes(get(_util,_cust,AnalysisMessages.TWO_RETURN_TYPES));
        _mess.setReturnTypes(get(_util,_cust,AnalysisMessages.RETURN_TYPES));
        _mess.setBadVariableName(get(_util,_cust,AnalysisMessages.BAD_VARIABLE_NAME));
        _mess.setKeyWordVariableName(get(_util,_cust,AnalysisMessages.KEY_WORD_VARIABLE_NAME));
        _mess.setPrimVariableName(get(_util,_cust,AnalysisMessages.PRIM_VARIABLE_NAME));
        _mess.setDigitVariableName(get(_util,_cust,AnalysisMessages.DIGIT_VARIABLE_NAME));
        _mess.setDuplicatedVariableName(get(_util,_cust,AnalysisMessages.DUPLICATED_VARIABLE_NAME));
        _mess.setCyclicCtorCall(get(_util,_cust,AnalysisMessages.CYCLIC_CTOR_CALL));
        _mess.setDeadCode(get(_util,_cust,AnalysisMessages.DEAD_CODE));
        _mess.setDuplicatedCtor(get(_util,_cust,AnalysisMessages.DUPLICATED_CTOR));
        _mess.setDuplicatedGenericSuperTypes(get(_util,_cust,AnalysisMessages.DUPLICATED_GENERIC_SUPER_TYPES));
        _mess.setDuplicatedInnerType(get(_util,_cust,AnalysisMessages.DUPLICATED_INNER_TYPE));
        _mess.setDuplicatedType(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE));
        _mess.setDuplicatedTypePrim(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE_PRIM));
        _mess.setDuplicatedTypeStd(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE_STD));
        _mess.setDuplicatedTypePkg(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE_PKG));
        _mess.setEmptyExpressionPart(get(_util,_cust,AnalysisMessages.EMPTY_EXPRESSION_PART));
        _mess.setDoWhileNotEmpty(get(_util,_cust,AnalysisMessages.DO_WHILE_NOT_EMPTY));
        _mess.setDuplicatedFinal(get(_util,_cust,AnalysisMessages.DUPLICATED_FINAL));
        _mess.setIllegalCtorEnum(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ENUM));
        _mess.setIllegalGenericSuperTypeBound(get(_util,_cust,AnalysisMessages.ILLEGAL_GENERIC_SUPER_TYPE_BOUND));
        _mess.setIllegalCtorAnnotation(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ANNOTATION));
        _mess.setIllegalCtorAbstract(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ABSTRACT));
        _mess.setIllegalCtorBound(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_BOUND));
        _mess.setIllegalCtorArray(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ARRAY));
        _mess.setIllegalCtorUnknown(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_UNKNOWN));
        _mess.setMissingAbrupt(get(_util,_cust,AnalysisMessages.MISSING_ABRUPT));
        _mess.setNotInitClass(get(_util,_cust,AnalysisMessages.NOT_INIT_CLASS));
        _mess.setNullValue(get(_util,_cust,AnalysisMessages.NULL_VALUE));
        _mess.setBadParameTypeForId(get(_util,_cust,AnalysisMessages.BAD_PARAME_TYPE_FOR_ID));
        _mess.setNotResolvedOwner(get(_util,_cust,AnalysisMessages.NOT_RESOLVED_OWNER));
        _mess.setUndefinedAccessibleField(get(_util,_cust,AnalysisMessages.UNDEFINED_ACCESSIBLE_FIELD));
        _mess.setStaticAccess(get(_util,_cust,AnalysisMessages.STATIC_ACCESS));
        _mess.setStaticAccessPrev(get(_util,_cust,AnalysisMessages.STATIC_ACCESS_PREV));
        _mess.setUnassignedFinalField(get(_util,_cust,AnalysisMessages.UNASSIGNED_FINAL_FIELD));
        _mess.setUnassignedInferingType(get(_util,_cust,AnalysisMessages.UNASSIGNED_INFERING_TYPE));
        _mess.setUndefinedCtor(get(_util,_cust,AnalysisMessages.UNDEFINED_CTOR));
        _mess.setUndefinedMethod(get(_util,_cust,AnalysisMessages.UNDEFINED_METHOD));
        _mess.setArrayCloneOnly(get(_util,_cust,AnalysisMessages.ARRAY_CLONE_ONLY));
        _mess.setUndefinedSuperCtor(get(_util,_cust,AnalysisMessages.UNDEFINED_SUPER_CTOR));
        _mess.setUndefinedSuperCtorCall(get(_util,_cust,AnalysisMessages.UNDEFINED_SUPER_CTOR_CALL));
        _mess.setUndefinedVariable(get(_util,_cust,AnalysisMessages.UNDEFINED_VARIABLE));
        _mess.setUnexpectedAffect(get(_util,_cust,AnalysisMessages.UNEXPECTED_AFFECT));
        _mess.setFinalField(get(_util,_cust,AnalysisMessages.FINAL_FIELD));
        _mess.setBadOperatorRef(get(_util,_cust,AnalysisMessages.BAD_OPERATOR_REF));
        _mess.setUnexpectedCatchElseFinally(get(_util,_cust,AnalysisMessages.UNEXPECTED_CATCH_ELSE_FINALLY));
        _mess.setUnexpectedAbrupt(get(_util,_cust,AnalysisMessages.UNEXPECTED_ABRUPT));
        _mess.setUnexpectedAbruptLab(get(_util,_cust,AnalysisMessages.UNEXPECTED_ABRUPT_LAB));
        _mess.setUnexpectedCaseDef(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_DEF));
        _mess.setUnexpectedCaseVar(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_VAR));
        _mess.setUnexpectedCaseValue(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_VALUE));
        _mess.setUnexpectedCaseDup(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_DUP));
        _mess.setUnexpectedDefDup(get(_util,_cust,AnalysisMessages.UNEXPECTED_DEF_DUP));
        _mess.setUnexpectedDoTry(get(_util,_cust,AnalysisMessages.UNEXPECTED_DO_TRY));
        _mess.setUnexpectedSwitch(get(_util,_cust,AnalysisMessages.UNEXPECTED_SWITCH));
        _mess.setUnexpectedMemberInst(get(_util,_cust,AnalysisMessages.UNEXPECTED_MEMBER_INST));
        _mess.setUnexpectedBlockExp(get(_util,_cust,AnalysisMessages.UNEXPECTED_BLOCK_EXP));
        _mess.setUnexpectedOperandTypes(get(_util,_cust,AnalysisMessages.UNEXPECTED_OPERAND_TYPES));
        _mess.setUnknownType(get(_util,_cust,AnalysisMessages.UNKNOWN_TYPE));
        _mess.setEmptyType(get(_util,_cust,AnalysisMessages.EMPTY_TYPE));
        _mess.setBadParamerizedType(get(_util,_cust,AnalysisMessages.BAD_PARAMERIZED_TYPE));
        _mess.setUnexpectedTypeBound(get(_util,_cust,AnalysisMessages.UNEXPECTED_TYPE_BOUND));
        _mess.setUnexpectedVararg(get(_util,_cust,AnalysisMessages.UNEXPECTED_VARARG));
        _mess.setUnexpectedLeaf(get(_util,_cust,AnalysisMessages.UNEXPECTED_LEAF));
        _mess.setEmptyPart(get(_util,_cust,AnalysisMessages.EMPTY_PART));
    }
    private static String get(StringMap<String> _util, StringMap<String> _cust, String _key) {
        String val_ = _cust.getVal(_key);
        if (val_ == null) {
            String def_ = _util.getVal(_key);
            if (def_ == null) {
                return "";
            }
            return def_;
        }
        return val_;
    }
}
