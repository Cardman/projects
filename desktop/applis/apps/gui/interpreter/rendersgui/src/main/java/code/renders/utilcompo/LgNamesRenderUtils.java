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
        _kw.build(_util, _cust);
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
        _mess.build(_util, _cust);
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage, Initializer _init) {
        return new SingleContextEl(new CommonExecutionInfos(_tabWidth,_stack,this,new Classes(new ClassesCommon()),_coverage,new DefaultLockingClass(),_init));
    }
}
