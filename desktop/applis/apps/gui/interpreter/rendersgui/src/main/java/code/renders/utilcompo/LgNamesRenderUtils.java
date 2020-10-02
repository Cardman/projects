package code.renders.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.coverage.Coverage;
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
import code.util.StringList;
import code.util.StringMap;

public final class LgNamesRenderUtils extends BeanCustLgNames {

    private CustRenderAliases custRenderAliases = new CustRenderAliases();

    private AbstractResourcesReader reader = new DefaultResourcesReader();

    public CustRenderAliases getCustRenderAliases() {
        return custRenderAliases;
    }

    public LgNamesRenderUtils(AbstractGenerator _gene) {
        super(_gene, new DefaultInitializer());
    }

    @Override
    public void buildOther() {
        getBeanAliases().buildOther(getContent());
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
        _rendKw.setStyleUnitSolid(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_UNIT_SOLID));
        _rendKw.setStyleUnitPx(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_UNIT_PX));
        _rendKw.setStyleUnitEm(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_UNIT_EM));
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
        _rendKw.setStyleValueGreen(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_GREEN));
        _rendKw.setStyleValueBlue(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_BLUE));
        _rendKw.setStyleValueYellow(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_YELLOW));
        _rendKw.setStyleValueMagenta(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_MAGENTA));
        _rendKw.setStyleValueCyan(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_CYAN));
        _rendKw.setStyleValueBlack(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_BLACK));
        _rendKw.setStyleValueGrey(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_GREY));
        _rendKw.setStyleValueWhite(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_WHITE));
        _rendKw.setStyleValueRgb(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_RGB));
        _rendKw.setStyleValueRed(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_VALUE_RED));
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
        _rendKw.setStyleAttrColor(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_ATTR_COLOR));
        _rendKw.setStyleAttrBackground(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_ATTR_BACKGROUND));
        _rendKw.setStyleAttrBorder(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_ATTR_BORDER));
        _rendKw.setStyleAttrFontSize(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_ATTR_FONTSIZE));
        _rendKw.setStyleAttrFontFam(LgNamesContent.get(_util, _cust, RendKeyWords.STYLE_ATTR_FONTFAM));
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
        _rendKw.setValueNumber(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_NUMBER));
        _rendKw.setValueRange(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_RANGE));
        _rendKw.setValueRadio(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_RADIO));
        _rendKw.setValueCheckbox(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_CHECKBOX));
        _rendKw.setValueText(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_TEXT));
        _rendKw.setValueLiMinLet(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LIMINLET));
        _rendKw.setValueLiDisk(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LIDISK));
        _rendKw.setValueLiRect(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LIRECT));
        _rendKw.setValueLiSquare(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LISQUARE));
        _rendKw.setValueLiNb(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LINB));
        _rendKw.setValueLiMajLat(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LIMAJLAT));
        _rendKw.setValueStyle(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_STYLE));
        _rendKw.setValueLiCircle(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LICIRCLE));
        _rendKw.setValueLiMajLet(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LIMAJLET));
        _rendKw.setValueLiMinLat(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_LIMINLAT));
        _rendKw.setValueSubmit(LgNamesContent.get(_util, _cust, RendKeyWords.VALUE_SUBMIT));
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
        _rendKw.setAttrType(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_TYPE));
        _rendKw.setAttrClassName(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CLASSNAME));
        _rendKw.setAttrMultiple(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_MULTIPLE));
        _rendKw.setAttrValidator(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_VALIDATOR));
        _rendKw.setAttrSelected(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_SELECTED));
        _rendKw.setAttrAction(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_ACTION));
        _rendKw.setAttrAlias(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_ALIAS));
        _rendKw.setAttrNf(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_N_F));
        _rendKw.setAttrNi(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_N_I));
        _rendKw.setAttrChecked(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CHECKED));
        _rendKw.setAttrLabel(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_LABEL));
        _rendKw.setAttrCondition(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CONDITION));
        _rendKw.setAttrFor(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_FOR));
        _rendKw.setAttrTitle(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_TITLE));
        _rendKw.setAttrConvert(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CONVERT));
        _rendKw.setAttrEscaped(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_ESCAPED));
        _rendKw.setAttrHref(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_HREF));
        _rendKw.setAttrCommand(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_COMMAND));
        _rendKw.setAttrMessage(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_MESSAGE));
        _rendKw.setAttrDefault(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_DEFAULT));
        _rendKw.setAttrPrepare(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_PREPARE));
        _rendKw.setAttrForm(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_FORM));
        _rendKw.setAttrId(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_ID));
        _rendKw.setAttrGroupId(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_GROUPID));
        _rendKw.setAttrVarValue(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_VARVALUE));
        _rendKw.setAttrBean(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_BEAN));
        _rendKw.setAttrNa(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_N_A));
        _rendKw.setAttrParam(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_PARAM));
        _rendKw.setAttrName(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_NAME));
        _rendKw.setAttrQuoted(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_QUOTED));
        _rendKw.setAttrRel(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_REL));
        _rendKw.setAttrSrc(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_SRC));
        _rendKw.setAttrDelay(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_DELAY));
        _rendKw.setAttrPage(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_PAGE));
        _rendKw.setAttrRows(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_ROWS));
        _rendKw.setAttrClass(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CLASS));
        _rendKw.setAttrCols(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_COLS));
        _rendKw.setAttrWidth(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_WIDTH));
        _rendKw.setAttrList(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_LIST));
        _rendKw.setAttrMap(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_MAP));
        _rendKw.setAttrKey(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_KEY));
        _rendKw.setAttrTo(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_TO));
        _rendKw.setAttrEq(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_EQ));
        _rendKw.setAttrVar(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_VAR));
        _rendKw.setAttrStep(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_STEP));
        _rendKw.setAttrFrom(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_FROM));
        _rendKw.setAttrValue(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_VALUE));
        _rendKw.setAttrInit(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_INIT));
        _rendKw.setAttrKeepFields(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_KEEPFIELDS));
        _rendKw.setAttrValueMessage(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_VALUEMESSAGE));
        _rendKw.setAttrConvertField(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CONVERTFIELD));
        _rendKw.setAttrKeyClassName(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_KEYCLASSNAME));
        _rendKw.setAttrVarClassName(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_VARCLASSNAME));
        _rendKw.setAttrIndexClassName(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_INDEXCLASSNAME));
        _rendKw.setAttrEscapedAmp(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_ESCAPEDAMP));
        _rendKw.setAttrConvertValue(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CONVERTVALUE));
        _rendKw.setAttrConvertFieldValue(LgNamesContent.get(_util, _cust, RendKeyWords.ATTR_CONVERTFIELDVALUE));
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
        _rendKw.setKeyWordTextarea(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_TEXTAREA));
        _rendKw.setKeyWordSelect(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_SELECT));
        _rendKw.setKeyWordFinally(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_FINALLY));
        _rendKw.setKeyWordContinue(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_CONTINUE));
        _rendKw.setKeyWordPackage(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_PACKAGE));
        _rendKw.setKeyWordDefault(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_DEFAULT));
        _rendKw.setKeyWordReturn(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_RETURN));
        _rendKw.setKeyWordFor(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_FOR));
        _rendKw.setKeyWordIf(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_IF));
        _rendKw.setKeyWordClass(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_CLASS));
        _rendKw.setKeyWordElseif(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_ELSEIF));
        _rendKw.setKeyWordThrow(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_THROW));
        _rendKw.setKeyWordBreak(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_BREAK));
        _rendKw.setKeyWordCatch(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_CATCH));
        _rendKw.setKeyWordCase(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_CASE));
        _rendKw.setKeyWordTry(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_TRY));
        _rendKw.setKeyWordElse(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_ELSE));
        _rendKw.setKeyWordSwitch(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_SWITCH));
        _rendKw.setKeyWordWhile(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_WHILE));
        _rendKw.setKeyWordDo(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_DO));
        _rendKw.setKeyWordCaption(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_CAPTION));
        _rendKw.setKeyWordMessage(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_MESSAGE));
        _rendKw.setKeyWordAnchor(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_A));
        _rendKw.setKeyWordParam(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_PARAM));
        _rendKw.setKeyWordForm(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_FORM));
        _rendKw.setKeyWordSpan(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_SPAN));
        _rendKw.setKeyWordImg(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_IMG));
        _rendKw.setKeyWordInput(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_INPUT));
        _rendKw.setKeyWordLink(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_LINK));
        _rendKw.setKeyWordSet(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_SET));
        _rendKw.setKeyWordSubmit(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_SUBMIT));
        _rendKw.setKeyWordStyle(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_STYLE));
        _rendKw.setKeyWordField(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_FIELD));
        _rendKw.setKeyWordImport(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_IMPORT));
        _rendKw.setKeyWordItalic(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_I));
        _rendKw.setKeyWordHr(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_HR));
        _rendKw.setKeyWordTd(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_TD));
        _rendKw.setKeyWordBody(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_BODY));
        _rendKw.setKeyWordOption(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_OPTION));
        _rendKw.setKeyWordUl(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_UL));
        _rendKw.setKeyWordHTwo(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_H2));
        _rendKw.setKeyWordPre(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_PRE));
        _rendKw.setKeyWordBr(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_BR));
        _rendKw.setKeyWordHSix(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_H6));
        _rendKw.setKeyWordMap(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_MAP));
        _rendKw.setKeyWordTh(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_TH));
        _rendKw.setKeyWordLi(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_LI));
        _rendKw.setKeyWordHOne(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_H1));
        _rendKw.setKeyWordOl(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_OL));
        _rendKw.setKeyWordBold(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_B));
        _rendKw.setKeyWordDiv(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_DIV));
        _rendKw.setKeyWordTr(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_TR));
        _rendKw.setKeyWordHFive(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_H5));
        _rendKw.setKeyWordPar(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_P));
        _rendKw.setKeyWordHead(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_HEAD));
        _rendKw.setKeyWordHFour(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_H4));
        _rendKw.setKeyWordTable(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_TABLE));
        _rendKw.setKeyWordHThree(LgNamesContent.get(_util, _cust, RendKeyWords.TAG_H3));
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
        _kw.setKeyWordContinue(LgNamesContent.get(_util, _cust, KeyWords.CONTINUE));
        _kw.setKeyWordInstanceof(LgNamesContent.get(_util, _cust, KeyWords.INSTANCEOF));
        _kw.setKeyWordInterface(LgNamesContent.get(_util, _cust, KeyWords.INTERFACE));
        _kw.setKeyWordAbstract(LgNamesContent.get(_util, _cust, KeyWords.ABSTRACT));
        _kw.setKeyWordElseif(LgNamesContent.get(_util, _cust, KeyWords.ELSEIF));
        _kw.setKeyWordCast(LgNamesContent.get(_util, _cust, KeyWords.CAST));
        _kw.setKeyWordExplicit(LgNamesContent.get(_util, _cust, KeyWords.EXPLICIT));
        _kw.setKeyWordFor(LgNamesContent.get(_util, _cust, KeyWords.FOR));
        _kw.setKeyWordVar(LgNamesContent.get(_util, _cust, KeyWords.VAR));
        _kw.setKeyWordStatic(LgNamesContent.get(_util, _cust, KeyWords.STATIC));
        _kw.setKeyWordStaticCall(LgNamesContent.get(_util, _cust, KeyWords.STATIC_CALL));
        _kw.setKeyWordNull(LgNamesContent.get(_util, _cust, KeyWords.NULL));
        _kw.setKeyWordClass(LgNamesContent.get(_util, _cust, KeyWords.CLASS));
        _kw.setKeyWordFalse(LgNamesContent.get(_util, _cust, KeyWords.FALSE));
        _kw.setKeyWordFinal(LgNamesContent.get(_util, _cust, KeyWords.FINAL));
        _kw.setKeyWordBreak(LgNamesContent.get(_util, _cust, KeyWords.BREAK));
        _kw.setKeyWordIf(LgNamesContent.get(_util, _cust, KeyWords.IF));
        _kw.setKeyWordNew(LgNamesContent.get(_util, _cust, KeyWords.NEW));
        _kw.setKeyWordWhile(LgNamesContent.get(_util, _cust, KeyWords.WHILE));
        _kw.setKeyWordReturn(LgNamesContent.get(_util, _cust, KeyWords.RETURN));
        _kw.setKeyWordTrue(LgNamesContent.get(_util, _cust, KeyWords.TRUE));
        _kw.setKeyWordPublic(LgNamesContent.get(_util, _cust, KeyWords.PUBLIC));
        _kw.setKeyWordPrivate(LgNamesContent.get(_util, _cust, KeyWords.PRIVATE));
        _kw.setKeyWordAnnotation(LgNamesContent.get(_util, _cust, KeyWords.ANNOTATION));
        _kw.setKeyWordToString(LgNamesContent.get(_util, _cust, KeyWords.TO_STRING));
        _kw.setKeyWordNbSufBytePrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_BYTE_PRIM));
        _kw.setKeyWordNbSufByte(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_BYTE));
        _kw.setKeyWordNbSufShortPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_SHORT_PRIM));
        _kw.setKeyWordNbSufShort(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_SHORT));
        _kw.setKeyWordNbSufCharacterPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_CHARACTER_PRIM));
        _kw.setKeyWordNbSufCharacter(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_CHARACTER));
        _kw.setKeyWordNbSufIntegerPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_INTEGER_PRIM));
        _kw.setKeyWordNbSufInteger(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_INTEGER));
        _kw.setKeyWordNbSufLongPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_LONG_PRIM));
        _kw.setKeyWordNbSufLong(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_LONG));
        _kw.setKeyWordNbSufFloatPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_FLOAT_PRIM));
        _kw.setKeyWordNbSufFloat(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_FLOAT));
        _kw.setKeyWordNbSufDoublePrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_DOUBLE_PRIM));
        _kw.setKeyWordNbSufDouble(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_DOUBLE));
        _kw.setKeyWordIter(LgNamesContent.get(_util, _cust, KeyWords.ITER));
        _kw.setKeyWordValue(LgNamesContent.get(_util, _cust, KeyWords.VALUE));
        _kw.setKeyWordElse(LgNamesContent.get(_util, _cust, KeyWords.ELSE));
        _kw.setKeyWordCatch(LgNamesContent.get(_util, _cust, KeyWords.CATCH));
        _kw.setKeyWordThrow(LgNamesContent.get(_util, _cust, KeyWords.THROW));
        _kw.setKeyWordTry(LgNamesContent.get(_util, _cust, KeyWords.TRY));
        _kw.setKeyWordThis(LgNamesContent.get(_util, _cust, KeyWords.THIS));
        _kw.setKeyWordSuper(LgNamesContent.get(_util, _cust, KeyWords.SUPER));
        _kw.setKeyWordCase(LgNamesContent.get(_util, _cust, KeyWords.CASE));
        _kw.setKeyWordDo(LgNamesContent.get(_util, _cust, KeyWords.DO));
        _kw.setKeyWordEnum(LgNamesContent.get(_util, _cust, KeyWords.ENUM));
        _kw.setKeyWordSwitch(LgNamesContent.get(_util, _cust, KeyWords.SWITCH));
        _kw.setKeyWordIntern(LgNamesContent.get(_util, _cust, KeyWords.INTERN));
        _kw.setKeyWordNormal(LgNamesContent.get(_util, _cust, KeyWords.NORMAL));
        _kw.setKeyWordEscTab(LgNamesContent.get(_util, _cust, KeyWords.ESC_TAB));
        _kw.setKeyWordNbHex(LgNamesContent.get(_util, _cust, KeyWords.NB_HEX));
        _kw.setKeyWordNbHexEnd(LgNamesContent.get(_util, _cust, KeyWords.NB_HEX_END));
        _kw.setKeyWordNbBin(LgNamesContent.get(_util, _cust, KeyWords.NB_BIN));
        _kw.setKeyWordThat(LgNamesContent.get(_util, _cust, KeyWords.THAT));
        _kw.setKeyWordBool(LgNamesContent.get(_util, _cust, KeyWords.BOOL));
        _kw.setKeyWordValues(LgNamesContent.get(_util, _cust, KeyWords.VALUES));
        _kw.setKeyWordLambda(LgNamesContent.get(_util, _cust, KeyWords.LAMBDA));
        _kw.setKeyWordVararg(LgNamesContent.get(_util, _cust, KeyWords.VARARG));
        _kw.setKeyWordId(LgNamesContent.get(_util, _cust, KeyWords.ID));
        _kw.setKeyWordForeach(LgNamesContent.get(_util, _cust, KeyWords.FOREACH));
        _kw.setKeyWordNbExpBin(LgNamesContent.get(_util, _cust, KeyWords.NB_EXP_BIN));
        _kw.setKeyWordClasschoice(LgNamesContent.get(_util, _cust, KeyWords.CLASSCHOICE));
        _kw.setKeyWordFirstopt(LgNamesContent.get(_util, _cust, KeyWords.FIRSTOPT));
        _kw.setKeyWordPackage(LgNamesContent.get(_util, _cust, KeyWords.PACKAGE));
        _kw.setKeyWordFinally(LgNamesContent.get(_util, _cust, KeyWords.FINALLY));
        _kw.setKeyWordEscUnicode(LgNamesContent.get(_util, _cust, KeyWords.ESC_UNICODE));
        _kw.setKeyWordThisaccess(LgNamesContent.get(_util, _cust, KeyWords.THISACCESS));
        _kw.setKeyWordValueOf(LgNamesContent.get(_util, _cust, KeyWords.VALUE_OF));
        _kw.setKeyWordDefaultValue(LgNamesContent.get(_util, _cust, KeyWords.DEFAULT_VALUE));
        _kw.setKeyWordEscLine(LgNamesContent.get(_util, _cust, KeyWords.ESC_LINE));
        _kw.setKeyWordOperator(LgNamesContent.get(_util, _cust, KeyWords.OPERATOR));
        _kw.setKeyWordInterfaces(LgNamesContent.get(_util, _cust, KeyWords.INTERFACES));
        _kw.setKeyWordSuperaccess(LgNamesContent.get(_util, _cust, KeyWords.SUPERACCESS));
        _kw.setKeyWordEscBound(LgNamesContent.get(_util, _cust, KeyWords.ESC_BOUND));
        _kw.setKeyWordEscForm(LgNamesContent.get(_util, _cust, KeyWords.ESC_FORM));
        _kw.setKeyWordEscFeed(LgNamesContent.get(_util, _cust, KeyWords.ESC_FEED));
        _kw.setKeyWordNbExpDec(LgNamesContent.get(_util, _cust, KeyWords.NB_EXP_DEC));
        _kw.setKeyWordProtected(LgNamesContent.get(_util, _cust, KeyWords.PROTECTED));
        _kw.setKeyWordDefault(LgNamesContent.get(_util, _cust, KeyWords.DEFAULT));
        _kw.setKeyWordParent(LgNamesContent.get(_util, _cust, KeyWords.PARENT));
    }


    private void rendMessages(RendAnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messagesrender");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        rendMessages(_mess,util_,_cust);
    }
    private void rendMessages(RendAnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setBadInputName(LgNamesContent.get(_util, _cust, RendAnalysisMessages.BAD_INPUT_NAME));
        _mess.setStaticInputName(LgNamesContent.get(_util, _cust, RendAnalysisMessages.STATIC_INPUT_NAME));
        _mess.setUnexpectedChildTag(LgNamesContent.get(_util, _cust, RendAnalysisMessages.UNEXPECTED_CHILD_TAG));
        _mess.setEmptyAttr(LgNamesContent.get(_util, _cust, RendAnalysisMessages.EMPTY_ATTR));
        _mess.setUnexpectedExp(LgNamesContent.get(_util, _cust, RendAnalysisMessages.UNEXPECTED_EXP));
        _mess.setInexistantFile(LgNamesContent.get(_util, _cust, RendAnalysisMessages.INEXISTANT_FILE));
        _mess.setInexistantKey(LgNamesContent.get(_util, _cust, RendAnalysisMessages.INEXISTANT_KEY));
        _mess.setBadDocument(LgNamesContent.get(_util, _cust, RendAnalysisMessages.BAD_DOCUMENT));
    }
    private void messages(AnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messages");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        messages(_mess,util_,_cust);
    }
    private void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setDuplicateMergedMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_MERGED_METHOD));
        _mess.setDuplicateField(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_FIELD));
        _mess.setDuplicateVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_VAR_TYPE));
        _mess.setEmptyPkgRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PKG_REF_TYPE));
        _mess.setPrimitiveKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIMITIVE_KEY_WORD));
        _mess.setDefaultPkgRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.DEFAULT_PKG_REF_TYPE));
        _mess.setRefTypeKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.REF_TYPE_KEY_WORD));
        _mess.setDigitFirstPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_PRIMITIVE));
        _mess.setIllegalFirstChar(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_FIRST_CHAR));
        _mess.setEmptyPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PRIMITIVE));
        _mess.setNotWordCharPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_PRIMITIVE));
        _mess.setNotWordCharRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_REF_TYPE));
        _mess.setEmptyRefTypeIn(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_REF_TYPE_IN));
        _mess.setRefTypePrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.REF_TYPE_PRIMITIVE));
        _mess.setDigitFirstRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_REF_TYPE));
        _mess.setDuplicatePrimtive(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_PRIMTIVE));
        _mess.setDuplicateMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_METHOD));
        _mess.setDefaultPkgNoMatch(LgNamesContent.get(_util, _cust, AnalysisMessages.DEFAULT_PKG_NO_MATCH));
        _mess.setDuplicateRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_REF_TYPE));
        _mess.setDigitFirstMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_METHOD));
        _mess.setNotWordCharField(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_FIELD));
        _mess.setNotWordCharMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_METHOD));
        _mess.setVarTypeKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.VAR_TYPE_KEY_WORD));
        _mess.setVarTypePrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.VAR_TYPE_PRIMITIVE));
        _mess.setDigitFirstVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_VAR_TYPE));
        _mess.setDuplicateNumberWord(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_NUMBER_WORD));
        _mess.setMethodPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.METHOD_PRIMITIVE));
        _mess.setFieldPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.FIELD_PRIMITIVE));
        _mess.setDuplicateStringWord(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STRING_WORD));
        _mess.setDuplicateKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_KEY_WORD));
        _mess.setDigitFirstField(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_FIELD));
        _mess.setDuplicateStartingNb(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STARTING_NB));
        _mess.setDuplicateStartingUni(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STARTING_UNI));
        _mess.setDuplicateStarting(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STARTING));
        _mess.setNotWordCharVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_VAR_TYPE));
        _mess.setEmptyPreBin(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PRE_BIN));
        _mess.setEmptyVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_VAR_TYPE));
        _mess.setEmptyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_WORD));
        _mess.setEmptyField(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_FIELD));
        _mess.setEmptyNb(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_NB));
        _mess.setNotWordChar(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR));
        _mess.setEmptyPreHex(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PRE_HEX));
        _mess.setEmptyMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_METHOD));
        _mess.setDigitFirst(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST));
        _mess.setEmptyBinExp(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_BIN_EXP));
        _mess.setEmptyString(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_STRING));
        _mess.setIllegalChar(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CHAR));
        _mess.setMethodKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.METHOD_KEY_WORD));
        _mess.setEmptyRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_REF_TYPE));
        _mess.setFieldKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.FIELD_KEY_WORD));
        _mess.setAbstractMethodBody(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_BODY));
        _mess.setAbstractMethodConc(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_CONC));
        _mess.setAbstractMethodImpl(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_IMPL));
        _mess.setAbstractMethodRef(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_REF));
        _mess.setInaccessibleType(LgNamesContent.get(_util, _cust, AnalysisMessages.INACCESSIBLE_TYPE));
        _mess.setUnexpectedType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_TYPE));
        _mess.setUnexpectedRetType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_RET_TYPE));
        _mess.setMethodsAccesses(LgNamesContent.get(_util, _cust, AnalysisMessages.METHODS_ACCESSES));
        _mess.setEmptyPackage(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PACKAGE));
        _mess.setEmptyPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PART_CLASS_NAME));
        _mess.setBadPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PART_CLASS_NAME));
        _mess.setKeyWordPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_PART_CLASS_NAME));
        _mess.setPrimPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_PART_CLASS_NAME));
        _mess.setDigitPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_PART_CLASS_NAME));
        _mess.setBadPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PART_VAR_CLASS_NAME));
        _mess.setKeyWordPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_PART_VAR_CLASS_NAME));
        _mess.setPrimPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_PART_VAR_CLASS_NAME));
        _mess.setDigitPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_PART_VAR_CLASS_NAME));
        _mess.setDuplicatedPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_PART_VAR_CLASS_NAME));
        _mess.setCallCtorEnd(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_END));
        _mess.setCallCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR));
        _mess.setCallCtorBeforeBlock(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_BEFORE_BLOCK));
        _mess.setCallCtorFirstLine(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_FIRST_LINE));
        _mess.setCallCtorIntFromSuperInt(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_FROM_SUPER_INT));
        _mess.setCallCtorIntNotFromInt(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_NOT_FROM_INT));
        _mess.setCallCtorIntAfterSuperThis(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_AFTER_SUPER_THIS));
        _mess.setCallCtorIntInherits(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_INHERITS));
        _mess.setCallCtorSuperClassEnumSingleton(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON));
        _mess.setAnnotFieldNotUniq(LgNamesContent.get(_util, _cust, AnalysisMessages.ANNOT_FIELD_NOT_UNIQ));
        _mess.setAnnotFieldMust(LgNamesContent.get(_util, _cust, AnalysisMessages.ANNOT_FIELD_MUST));
        _mess.setDupSuppliedAnnotField(LgNamesContent.get(_util, _cust, AnalysisMessages.DUP_SUPPLIED_ANNOT_FIELD));
        _mess.setBadExpression(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_EXPRESSION));
        _mess.setBadFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_FIELD_NAME));
        _mess.setKeyWordFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_FIELD_NAME));
        _mess.setPrimFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_FIELD_NAME));
        _mess.setDigitFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIELD_NAME));
        _mess.setNotRetrievedFields(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_RETRIEVED_FIELDS));
        _mess.setBadNbFormat(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_NB_FORMAT));
        _mess.setBadCharFormat(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_CHAR_FORMAT));
        _mess.setBadImplicitCast(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_IMPLICIT_CAST));
        _mess.setNotPrimitiveWrapper(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_PRIMITIVE_WRAPPER));
        _mess.setVoidType(LgNamesContent.get(_util, _cust, AnalysisMessages.VOID_TYPE));
        _mess.setBadIndexInParser(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEX_IN_PARSER));
        _mess.setIllegalCharacter(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CHARACTER));
        _mess.setCallIntInherits(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_INHERITS));
        _mess.setCallIntNoNeed(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_NO_NEED));
        _mess.setCallIntNoNeedType(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_NO_NEED_TYPE));
        _mess.setCallIntNeedType(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_NEED_TYPE));
        _mess.setCallIntOnly(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_ONLY));
        _mess.setBadInheritsType(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE));
        _mess.setBadInheritsTypeInn(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE_INN));
        _mess.setBadInheritsTypeAsInn(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE_AS_INN));
        _mess.setBadInheritsTypeInt(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE_INT));
        _mess.setFinalType(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_TYPE));
        _mess.setDuplicateSuper(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_SUPER));
        _mess.setReservedType(LgNamesContent.get(_util, _cust, AnalysisMessages.RESERVED_TYPE));
        _mess.setSuperClass(LgNamesContent.get(_util, _cust, AnalysisMessages.SUPER_CLASS));
        _mess.setUnknownSuperType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNKNOWN_SUPER_TYPE));
        _mess.setCyclicInherits(LgNamesContent.get(_util, _cust, AnalysisMessages.CYCLIC_INHERITS));
        _mess.setAnnotationParam(LgNamesContent.get(_util, _cust, AnalysisMessages.ANNOTATION_PARAM));
        _mess.setCyclicMapping(LgNamesContent.get(_util, _cust, AnalysisMessages.CYCLIC_MAPPING));
        _mess.setAbsMapping(LgNamesContent.get(_util, _cust, AnalysisMessages.ABS_MAPPING));
        _mess.setFinalMapping(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_MAPPING));
        _mess.setMustCallIntCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_CALL_INT_CTOR));
        _mess.setMustNotCallIntCtorAfterThis(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_NOT_CALL_INT_CTOR_AFTER_THIS));
        _mess.setMustCallIntCtorNeed(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_CALL_INT_CTOR_NEED));
        _mess.setMustCallIntCtorNotNeed(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_CALL_INT_CTOR_NOT_NEED));
        _mess.setBadLabel(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_LABEL));
        _mess.setDuplicatedLabel(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_LABEL));
        _mess.setBadMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_METHOD_NAME));
        _mess.setKeyWordMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_METHOD_NAME));
        _mess.setPrimMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_METHOD_NAME));
        _mess.setDigitMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_METHOD_NAME));
        _mess.setBadOperatorName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_OPERATOR_NAME));
        _mess.setBadAccess(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_ACCESS));
        _mess.setBadReturnType(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_RETURN_TYPE));
        _mess.setBadParams(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAMS));
        _mess.setBadMethodModifier(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_METHOD_MODIFIER));
        _mess.setBadMethodVararg(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_METHOD_VARARG));
        _mess.setBadIndexerParams(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_PARAMS));
        _mess.setBadIndexerModifier(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_MODIFIER));
        _mess.setBadIndexerModifiers(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_MODIFIERS));
        _mess.setBadIndexerAccesses(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_ACCESSES));
        _mess.setBadIndexerPairGet(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_PAIR_GET));
        _mess.setBadIndexerPairSet(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_PAIR_SET));
        _mess.setDuplicateCustomMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_CUSTOM_METHOD));
        _mess.setReservedCustomMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.RESERVED_CUSTOM_METHOD));
        _mess.setDuplicateIndexer(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_INDEXER));
        _mess.setDuplicateOperator(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_OPERATOR));
        _mess.setFunctionalApplyNbDiff(LgNamesContent.get(_util, _cust, AnalysisMessages.FUNCTIONAL_APPLY_NB_DIFF));
        _mess.setFunctionalApplyOnly(LgNamesContent.get(_util, _cust, AnalysisMessages.FUNCTIONAL_APPLY_ONLY));
        _mess.setOperatorNbDiff(LgNamesContent.get(_util, _cust, AnalysisMessages.OPERATOR_NB_DIFF));
        _mess.setSplitComa(LgNamesContent.get(_util, _cust, AnalysisMessages.SPLIT_COMA));
        _mess.setSplitComaLow(LgNamesContent.get(_util, _cust, AnalysisMessages.SPLIT_COMA_LOW));
        _mess.setSplitDiff(LgNamesContent.get(_util, _cust, AnalysisMessages.SPLIT_DIFF));
        _mess.setBadDotted(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_DOTTED));
        _mess.setBadParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAM_NAME));
        _mess.setReservedParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.RESERVED_PARAM_NAME));
        _mess.setDuplicatedParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_PARAM_NAME));
        _mess.setBadReturnTypeInherit(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_RETURN_TYPE_INHERIT));
        _mess.setBadReturnTypeIndexer(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_RETURN_TYPE_INDEXER));
        _mess.setDuplicatedOverriding(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_OVERRIDING));
        _mess.setTwoFinal(LgNamesContent.get(_util, _cust, AnalysisMessages.TWO_FINAL));
        _mess.setFinalNotSubReturnType(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_NOT_SUB_RETURN_TYPE));
        _mess.setTwoReturnTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.TWO_RETURN_TYPES));
        _mess.setReturnTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.RETURN_TYPES));
        _mess.setBadVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_VARIABLE_NAME));
        _mess.setKeyWordVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_VARIABLE_NAME));
        _mess.setPrimVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_VARIABLE_NAME));
        _mess.setDigitVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_VARIABLE_NAME));
        _mess.setDuplicatedVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_VARIABLE_NAME));
        _mess.setCyclicCtorCall(LgNamesContent.get(_util, _cust, AnalysisMessages.CYCLIC_CTOR_CALL));
        _mess.setDeadCode(LgNamesContent.get(_util, _cust, AnalysisMessages.DEAD_CODE));
        _mess.setDuplicatedCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_CTOR));
        _mess.setDuplicatedGenericSuperTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_GENERIC_SUPER_TYPES));
        _mess.setDuplicatedInnerType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_INNER_TYPE));
        _mess.setDuplicatedType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE));
        _mess.setDuplicatedTypePrim(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE_PRIM));
        _mess.setDuplicatedTypeStd(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE_STD));
        _mess.setDuplicatedTypePkg(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE_PKG));
        _mess.setEmptyExpressionPart(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_EXPRESSION_PART));
        _mess.setDoWhileNotEmpty(LgNamesContent.get(_util, _cust, AnalysisMessages.DO_WHILE_NOT_EMPTY));
        _mess.setDuplicatedFinal(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_FINAL));
        _mess.setIllegalCtorEnum(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ENUM));
        _mess.setIllegalGenericSuperTypeBound(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_GENERIC_SUPER_TYPE_BOUND));
        _mess.setIllegalCtorAnnotation(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ANNOTATION));
        _mess.setIllegalCtorAbstract(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ABSTRACT));
        _mess.setIllegalCtorBound(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_BOUND));
        _mess.setIllegalCtorArray(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ARRAY));
        _mess.setIllegalCtorUnknown(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_UNKNOWN));
        _mess.setMissingAbrupt(LgNamesContent.get(_util, _cust, AnalysisMessages.MISSING_ABRUPT));
        _mess.setNotInitClass(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_INIT_CLASS));
        _mess.setNullValue(LgNamesContent.get(_util, _cust, AnalysisMessages.NULL_VALUE));
        _mess.setBadParameTypeForId(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAME_TYPE_FOR_ID));
        _mess.setNotResolvedOwner(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_RESOLVED_OWNER));
        _mess.setUndefinedAccessibleField(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_ACCESSIBLE_FIELD));
        _mess.setStaticAccess(LgNamesContent.get(_util, _cust, AnalysisMessages.STATIC_ACCESS));
        _mess.setStaticAccessPrev(LgNamesContent.get(_util, _cust, AnalysisMessages.STATIC_ACCESS_PREV));
        _mess.setUnassignedFinalField(LgNamesContent.get(_util, _cust, AnalysisMessages.UNASSIGNED_FINAL_FIELD));
        _mess.setUnassignedInferingType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNASSIGNED_INFERING_TYPE));
        _mess.setUndefinedCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_CTOR));
        _mess.setUndefinedMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_METHOD));
        _mess.setArrayCloneOnly(LgNamesContent.get(_util, _cust, AnalysisMessages.ARRAY_CLONE_ONLY));
        _mess.setUndefinedSuperCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_SUPER_CTOR));
        _mess.setUndefinedSuperCtorCall(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_SUPER_CTOR_CALL));
        _mess.setUndefinedVariable(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_VARIABLE));
        _mess.setUnexpectedAffect(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_AFFECT));
        _mess.setFinalField(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_FIELD));
        _mess.setBadOperatorRef(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_OPERATOR_REF));
        _mess.setUnexpectedCatchElseFinally(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CATCH_ELSE_FINALLY));
        _mess.setUnexpectedAbrupt(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_ABRUPT));
        _mess.setUnexpectedAbruptLab(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_ABRUPT_LAB));
        _mess.setUnexpectedCaseDef(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_DEF));
        _mess.setUnexpectedCaseVar(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_VAR));
        _mess.setUnexpectedCaseValue(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_VALUE));
        _mess.setUnexpectedCaseDup(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_DUP));
        _mess.setUnexpectedDefDup(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_DEF_DUP));
        _mess.setUnexpectedDoTry(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_DO_TRY));
        _mess.setUnexpectedSwitch(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_SWITCH));
        _mess.setUnexpectedMemberInst(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_MEMBER_INST));
        _mess.setUnexpectedBlockExp(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_BLOCK_EXP));
        _mess.setUnexpectedOperandTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_OPERAND_TYPES));
        _mess.setUnknownType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNKNOWN_TYPE));
        _mess.setEmptyType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_TYPE));
        _mess.setBadParamerizedType(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAMERIZED_TYPE));
        _mess.setUnexpectedTypeBound(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_TYPE_BOUND));
        _mess.setUnexpectedVararg(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_VARARG));
        _mess.setUnexpectedLeaf(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_LEAF));
        _mess.setEmptyPart(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PART));
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage, Initializer _init) {
        return new SingleContextEl(new CommonExecutionInfos(_tabWidth,_stack,this,new Classes(new ClassesCommon()),_coverage,new DefaultLockingClass(),_init));
    }
}
