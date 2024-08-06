package code.formathtml.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.LgNamesContent;
import code.maths.litteralcom.MathExpUtil;
import code.sml.*;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendKeyWords {
    private static final String TAG_TEXTAREA="TagTextarea";
    private static final String TAG_SELECT="TagSelect";
    private static final String TAG_FORM="TagForm";
    private static final String TAG_FIELD="TagField";
    private static final String TAG_IMPORT="TagImport";
    private static final String TAG_A="TagAnchor";
    private static final String TAG_INPUT="TagInput";
    private static final String TAG_SUBMIT="TagSubmit";
    private static final String TAG_IMG="TagImg";
    private static final String TAG_SPAN="TagSpan";
    private static final String TAG_SET="TagSet";
    private static final String TAG_H6="TagHSix";
    private static final String TAG_DIV="TagDiv";
    private static final String TAG_BR="TagBr";
    private static final String TAG_H1="TagHOne";
    private static final String TAG_TH="TagTh";
    private static final String TAG_H5="TagHFive";
    private static final String TAG_OPTION="TagOption";
    private static final String TAG_MAP="TagMap";
    private static final String TAG_HEAD="TagHead";
    private static final String TAG_H2="TagHTwo";
    private static final String TAG_HR="TagHr";
    private static final String TAG_STYLE="TagStyle";
    private static final String TAG_LINK="TagLink";
    private static final String TAG_BODY="TagBody";
    private static final String TAG_OL="TagOl";
    private static final String TAG_H4="TagHFour";
    private static final String TAG_H3="TagHThree";
    private static final String TAG_P="TagPar";
    private static final String TAG_B="TagBold";
    private static final String TAG_PRE="TagPre";
    private static final String TAG_TABLE="TagTable";
    private static final String TAG_TD="TagTd";
    private static final String TAG_UL="TagUl";
    private static final String TAG_TR="TagTr";
    private static final String TAG_LI="TagLi";
    private static final String TAG_I="TagItalic";
    private static final String TAG_CONTINUE="TagContinue";
    private static final String TAG_DEFAULT="TagDefault";
    private static final String TAG_PACKAGE="TagPackage";
    private static final String TAG_FINALLY="TagFinally";
    private static final String TAG_CLASS="TagClass";
    private static final String TAG_IF="TagIf";
    private static final String TAG_FOR="TagFor";
    private static final String TAG_RETURN="TagReturn";
    private static final String TAG_ELSEIF="TagElseif";
    private static final String TAG_DO="TagDo";
    private static final String TAG_TRY="TagTry";
    private static final String TAG_WHILE="TagWhile";
    private static final String TAG_ELSE="TagElse";
    private static final String TAG_SWITCH="TagSwitch";
    private static final String TAG_CATCH="TagCatch";
    private static final String TAG_CASE="TagCase";
    private static final String TAG_BREAK="TagBreak";
    private static final String TAG_THROW="TagThrow";
    private static final String TAG_CAPTION="TagCaption";
    private static final String TAG_MESSAGE="TagMessage";

    private static final String ATTR_TYPE="AttrType";
    private static final String ATTR_MULTIPLE="AttrMultiple";
    private static final String ATTR_CLASSNAME="AttrClassName";
    private static final String ATTR_CONVERTVALUE="AttrConvertValue";
    private static final String ATTR_KEEPFIELDS="AttrKeepFields";
    private static final String ATTR_KEYCLASSNAME="AttrKeyClassName";
    private static final String ATTR_VALUEMESSAGE="AttrValueMessage";
    private static final String ATTR_CONVERTFIELDVALUE="AttrConvertFieldValue";
    private static final String ATTR_VARCLASSNAME="AttrVarClassName";
    private static final String ATTR_CONVERTFIELD="AttrConvertField";
    private static final String ATTR_ESCAPEDAMP="AttrEscapedAmp";
    private static final String ATTR_INDEXCLASSNAME="AttrIndexClassName";
    private static final String ATTR_CONDITION="AttrCondition";
    private static final String ATTR_LIST="AttrList";
    private static final String ATTR_VALUE="AttrValue";
    private static final String ATTR_MAP="AttrMap";
    private static final String ATTR_VAR="AttrVar";
    private static final String ATTR_FROM="AttrFrom";
    private static final String ATTR_STEP="AttrStep";
    private static final String ATTR_TO="AttrTo";
    private static final String ATTR_EQ="AttrEq";
    private static final String ATTR_KEY="AttrKey";
    private static final String ATTR_INIT="AttrInit";
    private static final String ATTR_TITLE="AttrTitle";
    private static final String ATTR_QUOTED="AttrQuoted";
    private static final String ATTR_ESCAPED="AttrEscaped";
    private static final String ATTR_NAME="AttrName";
    private static final String ATTR_DEFAULT="AttrDefault";
    private static final String ATTR_PREPARE="AttrPrepare";
    private static final String ATTR_PARAM="AttrParam";
    private static final String ATTR_CHECKED="AttrChecked";
    private static final String ATTR_ID="AttrId";
    private static final String ATTR_ALIAS="AttrAlias";
    private static final String ATTR_COMMAND="AttrCommand";
    private static final String ATTR_SGN="AttrSgn";
    private static final String ATTR_VARVALUE="AttrVarValue";
    private static final String ATTR_GROUPID="AttrGroupId";
    private static final String ATTR_VALIDATOR="AttrValidator";
    private static final String ATTR_FORM="AttrForm";
    private static final String ATTR_FOR="AttrFor";
    private static final String ATTR_PAGE="AttrPage";
    private static final String ATTR_N_I="AttrNi";
    private static final String ATTR_N_R="AttrNr";
    private static final String ATTR_ACTION="AttrAction";
    private static final String ATTR_N_F="AttrNf";
    private static final String ATTR_CONVERT="AttrConvert";
    private static final String ATTR_BEAN="AttrBean";
    private static final String ATTR_SELECTED="AttrSelected";
    private static final String ATTR_LABEL="AttrLabel";
    private static final String ATTR_MESSAGE="AttrMessage";
    private static final String ATTR_HREF="AttrHref";
    private static final String ATTR_N_A="AttrNa";
    private static final String ATTR_CLASS="AttrClass";
    private static final String ATTR_REL="AttrRel";
    private static final String ATTR_SRC="AttrSrc";
    private static final String ATTR_WIDTH="AttrWidth";
    private static final String ATTR_COLS="AttrCols";
    private static final String ATTR_DELAY="AttrDelay";
    private static final String ATTR_ROWS="AttrRows";
    private static final String VALUE_RANGE="ValueRange";
    private static final String VALUE_TEXT="ValueText";
    private static final String VALUE_RADIO="ValueRadio";
    private static final String VALUE_CHECKBOX="ValueCheckbox";
    private static final String VALUE_NUMBER="ValueNumber";
    private static final String VALUE_SUBMIT="ValueSubmit";
    private static final String VALUE_LICIRCLE="ValueLiCircle";
    private static final String VALUE_LIDISK="ValueLiDisk";
    private static final String VALUE_LINB="ValueLiNb";
    private static final String VALUE_LIMINLAT="ValueLiMinLat";
    private static final String VALUE_LISQUARE="ValueLiSquare";
    private static final String VALUE_LIRECT="ValueLiRect";
    private static final String VALUE_LIMAJLET="ValueLiMajLet";
    private static final String VALUE_LIMINLET="ValueLiMinLet";
    private static final String VALUE_STYLE="ValueStyle";
    private static final String VALUE_LIMAJLAT="ValueLiMajLat";
    private static final String STYLE_ATTR_BACKGROUND="StyleAttrBackground";
    private static final String STYLE_ATTR_BORDER="StyleAttrBorder";
    private static final String STYLE_ATTR_FONTFAM="StyleAttrFontFam";
    private static final String STYLE_ATTR_FONTSIZE="StyleAttrFontSize";
    private static final String STYLE_ATTR_COLOR="StyleAttrColor";
    private static final String STYLE_VALUE_CYAN="StyleValueCyan";
    private static final String STYLE_VALUE_BLUE="StyleValueBlue";
    private static final String STYLE_VALUE_YELLOW="StyleValueYellow";
    private static final String STYLE_VALUE_MAGENTA="StyleValueMagenta";
    private static final String STYLE_VALUE_GREEN="StyleValueGreen";
    private static final String STYLE_VALUE_GREY="StyleValueGrey";
    private static final String STYLE_VALUE_BLACK="StyleValueBlack";
    private static final String STYLE_VALUE_WHITE="StyleValueWhite";
    private static final String STYLE_VALUE_RED="StyleValueRed";
    private static final String STYLE_VALUE_RGB="StyleValueRgb";
    private static final String STYLE_UNIT_SOLID="StyleUnitSolid";
    private static final String STYLE_UNIT_EM="StyleUnitEm";
    private static final String STYLE_UNIT_PX="StyleUnitPx";

    private final RendKeyWordsTags rendKeyWordsTags = new RendKeyWordsTags();
    private final RendKeyWordsAttrs rendKeyWordsAttrs = new RendKeyWordsAttrs();
    private final RendKeyWordsValues rendKeyWordsValues = new RendKeyWordsValues();
    private final RendKeyWordsStyles rendKeyWordsStyles = new RendKeyWordsStyles();
//    private String keyWordFor = "for";
//    private String keyWordWhile = "while";
//    private String keyWordDo = "do";
//
//    private String keyWordIf = "if";
//    private String keyWordElse = "else";
//    private String keyWordElseif = "elseif";
//
//    private String keyWordTry = "try";
//    private String keyWordFinally = "finally";
//    private String keyWordCatch = "catch";
//
//    private String keyWordSwitch = "switch";
//    private String keyWordCase = "case";
//    private String keyWordDefault = "default";
//
//    private String keyWordReturn = "return";
//    private String keyWordThrow = "throw";
//    private String keyWordBreak = "break";
//    private String keyWordContinue = "continue";
//
//    private String keyWordSet="set";
//    private String keyWordImport="import";
//    private String keyWordPackage="package";
//    private String keyWordClass="class";
//    private String keyWordField="field";
//    private String keyWordForm="form";
//    private String keyWordSubmit="submit";
//    private String keyWordImg="img";
//    private String keyWordSelect="select";
//    private String keyWordMessage="message";
//    private String keyWordAnchor="a";
//    private String keyWordInput="input";
//    private String keyWordTextarea="textarea";
//    private String keyWordSpan="span";
//    private String keyWordLink="link";
//    private String keyWordStyle="style";
//    private String keyWordBody="body";
//    private String keyWordHead="head";
//    private String keyWordMap="map";
//    private String keyWordLi="li";
//    private String keyWordOl="ol";
//    private String keyWordUl="ul";
//    private String keyWordBold="b";
//    private String keyWordItalic="i";
//    private String keyWordPre="pre";
//    private String keyWordHOne="h1";
//    private String keyWordHTwo="h2";
//    private String keyWordHThree="h3";
//    private String keyWordHFour="h4";
//    private String keyWordHFive="h5";
//    private String keyWordHSix="h6";
//    private String keyWordBr="br";
//    private String keyWordHr="hr";
//    private String keyWordPar="p";
//    private String keyWordTable="table";
//    private String keyWordCaption="caption";
//    private String keyWordTd="td";
//    private String keyWordTh="th";
//    private String keyWordTr="tr";
//    private String keyWordDiv="div";
//    private String keyWordOption="option";

//    private String attrList="list";
//    private String attrMap="map";
//    private String attrVar="var";
//    private String attrKey="key";
//    private String attrValue="value";
//    private String attrFrom="from";
//    private String attrInit="init";
//    private String attrStep="step";
//    private String attrTo="to";
//    private String attrEq="eq";
//    private String attrCondition="condition";
//    private String attrClassName="className";
//    private String attrKeyClassName="keyClassName";
//    private String attrVarClassName="varClassName";
//    private String attrIndexClassName="indexClassName";
//    private String attrAlias="alias";
//    private String attrLabel="label";
//    private String attrBean="bean";
//
//    private String attrType="type";
//    private String attrChecked="checked";
//    private String attrSelected="selected";
//    private String attrMultiple="multiple";

//    private String valueText="text";
//    private String valueCheckbox="checkbox";
//    private String valueRadio="radio";
//    private String valueNumber="number";
//    private String valueRange="range";
//    private String valueSubmit="submit";

//    private String attrNf="n-f";
//    private String attrNa="n-a";
//    private String attrNi="n-i";
//    private String attrNr="n-r";
//
//    private String attrParam = "param";
//
//    private String attrName = "name";
//    private String attrHref = "href";
//    private String attrCommand = "command";
//    private String attrSgn = "sgn";
//    private String attrAction = "action";
//    private String attrTitle = "title";
//    private String attrFor = "for";
//    private String attrMessage = "message";
//    private String attrQuoted = "quoted";
//    private String attrEscaped = "escaped";
//    private String attrEscapedAmp = "escapedamp";
//    private String attrConvert = "convert";
//    private String attrConvertValue = "convertValue";
//    private String attrConvertField = "convertField";
//    private String attrConvertFieldValue = "convertFieldValue";
//    private String attrDefault = "default";
//    private String attrVarValue = "varValue";
//    private String attrPrepare = "prepare";
//    private String attrForm = "form";
//    private String attrValueMessage = "valueMessage";
//    private String attrId = "id";
//    private String attrGroupId = "groupId";
//    private String attrValidator = "validator";
//    private String attrPage = "page";
//    private String attrKeepFields = "keepfields";
//    private String attrSrc = "src";
//    private String attrRows = "rows";
//    private String attrCols = "cols";
//
//    private String attrClass="class";
//    private String attrWidth="width";
//    private String attrDelay="delay";
//    private String attrRel="rel";

//    private String valueStyle="stylesheet";

//    private String valueLiNb="1";
//    private String valueLiMinLet="a";
//    private String valueLiMajLet="A";
//    private String valueLiMinLat="i";
//    private String valueLiMajLat="I";
//    private String valueLiCircle="circle";
//    private String valueLiDisk="disc";
//    private String valueLiRect="rect";
//    private String valueLiSquare="square";


//    private String styleAttrFontFam="font-family";
//    private String styleAttrFontSize="font-size";
//    private String styleAttrColor="color";
//    private String styleAttrBackground="background";
//    private String styleAttrBorder="border";
//
//    private String styleValueRgb="rgb";
//    private String styleValueRed="red";
//    private String styleValueGreen="green";
//    private String styleValueBlue="blue";
//    private String styleValueYellow="yellow";
//    private String styleValueCyan="cyan";
//    private String styleValueMagenta="magenta";
//    private String styleValueBlack="black";
//    private String styleValueGrey="grey";
//    private String styleValueWhite="white";
//    private String styleUnitPx="px";
//    private String styleUnitEm="em";
//    private String styleUnitSolid="solid";
    public void otherStyleUnits(StringMap<String> _util, StringMap<String> _cust) {
        setStyleUnitSolid(LgNamesContent.get(_util, _cust, STYLE_UNIT_SOLID));
        setStyleUnitPx(LgNamesContent.get(_util, _cust, STYLE_UNIT_PX));
        setStyleUnitEm(LgNamesContent.get(_util, _cust, STYLE_UNIT_EM));
    }
    public void otherStyleValues(StringMap<String> _util, StringMap<String> _cust) {
        setStyleValueGreen(LgNamesContent.get(_util, _cust, STYLE_VALUE_GREEN));
        setStyleValueBlue(LgNamesContent.get(_util, _cust, STYLE_VALUE_BLUE));
        setStyleValueYellow(LgNamesContent.get(_util, _cust, STYLE_VALUE_YELLOW));
        setStyleValueMagenta(LgNamesContent.get(_util, _cust, STYLE_VALUE_MAGENTA));
        setStyleValueCyan(LgNamesContent.get(_util, _cust, STYLE_VALUE_CYAN));
        setStyleValueBlack(LgNamesContent.get(_util, _cust, STYLE_VALUE_BLACK));
        setStyleValueGrey(LgNamesContent.get(_util, _cust, STYLE_VALUE_GREY));
        setStyleValueWhite(LgNamesContent.get(_util, _cust, STYLE_VALUE_WHITE));
        setStyleValueRgb(LgNamesContent.get(_util, _cust, STYLE_VALUE_RGB));
        setStyleValueRed(LgNamesContent.get(_util, _cust, STYLE_VALUE_RED));
    }
    public void otherStyleAttrs(StringMap<String> _util, StringMap<String> _cust) {
        setStyleAttrColor(LgNamesContent.get(_util, _cust, STYLE_ATTR_COLOR));
        setStyleAttrBackground(LgNamesContent.get(_util, _cust, STYLE_ATTR_BACKGROUND));
        setStyleAttrBorder(LgNamesContent.get(_util, _cust, STYLE_ATTR_BORDER));
        setStyleAttrFontSize(LgNamesContent.get(_util, _cust, STYLE_ATTR_FONTSIZE));
        setStyleAttrFontFam(LgNamesContent.get(_util, _cust, STYLE_ATTR_FONTFAM));
    }
    public void otherValues(StringMap<String> _util, StringMap<String> _cust) {
        setValueNumber(LgNamesContent.get(_util, _cust, VALUE_NUMBER));
        setValueRange(LgNamesContent.get(_util, _cust, VALUE_RANGE));
        setValueRadio(LgNamesContent.get(_util, _cust, VALUE_RADIO));
        setValueCheckbox(LgNamesContent.get(_util, _cust, VALUE_CHECKBOX));
        setValueText(LgNamesContent.get(_util, _cust, VALUE_TEXT));
        setValueLiMinLet(LgNamesContent.get(_util, _cust, VALUE_LIMINLET));
        setValueLiDisk(LgNamesContent.get(_util, _cust, VALUE_LIDISK));
        setValueLiRect(LgNamesContent.get(_util, _cust, VALUE_LIRECT));
        setValueLiSquare(LgNamesContent.get(_util, _cust, VALUE_LISQUARE));
        setValueLiNb(LgNamesContent.get(_util, _cust, VALUE_LINB));
        setValueLiMajLat(LgNamesContent.get(_util, _cust, VALUE_LIMAJLAT));
        setValueStyle(LgNamesContent.get(_util, _cust, VALUE_STYLE));
        setValueLiCircle(LgNamesContent.get(_util, _cust, VALUE_LICIRCLE));
        setValueLiMajLet(LgNamesContent.get(_util, _cust, VALUE_LIMAJLET));
        setValueLiMinLat(LgNamesContent.get(_util, _cust, VALUE_LIMINLAT));
        setValueSubmit(LgNamesContent.get(_util, _cust, VALUE_SUBMIT));
    }
    public void otherAttrs(StringMap<String> _util, StringMap<String> _cust) {
        setAttrType(LgNamesContent.get(_util, _cust, ATTR_TYPE));
        setAttrClassName(LgNamesContent.get(_util, _cust, ATTR_CLASSNAME));
        setAttrMultiple(LgNamesContent.get(_util, _cust, ATTR_MULTIPLE));
        setAttrValidator(LgNamesContent.get(_util, _cust, ATTR_VALIDATOR));
        setAttrSelected(LgNamesContent.get(_util, _cust, ATTR_SELECTED));
        setAttrAction(LgNamesContent.get(_util, _cust, ATTR_ACTION));
        setAttrAlias(LgNamesContent.get(_util, _cust, ATTR_ALIAS));
        setAttrNf(LgNamesContent.get(_util, _cust, ATTR_N_F));
        setAttrNi(LgNamesContent.get(_util, _cust, ATTR_N_I));
        setAttrNr(LgNamesContent.get(_util, _cust, ATTR_N_R));
        setAttrChecked(LgNamesContent.get(_util, _cust, ATTR_CHECKED));
        setAttrLabel(LgNamesContent.get(_util, _cust, ATTR_LABEL));
        setAttrCondition(LgNamesContent.get(_util, _cust, ATTR_CONDITION));
        setAttrFor(LgNamesContent.get(_util, _cust, ATTR_FOR));
        setAttrTitle(LgNamesContent.get(_util, _cust, ATTR_TITLE));
        setAttrConvert(LgNamesContent.get(_util, _cust, ATTR_CONVERT));
        setAttrEscaped(LgNamesContent.get(_util, _cust, ATTR_ESCAPED));
        setAttrHref(LgNamesContent.get(_util, _cust, ATTR_HREF));
        setAttrCommand(LgNamesContent.get(_util, _cust, ATTR_COMMAND));
        setAttrSgn(LgNamesContent.get(_util, _cust, ATTR_SGN));
        setAttrMessage(LgNamesContent.get(_util, _cust, ATTR_MESSAGE));
        setAttrDefault(LgNamesContent.get(_util, _cust, ATTR_DEFAULT));
        setAttrPrepare(LgNamesContent.get(_util, _cust, ATTR_PREPARE));
        setAttrForm(LgNamesContent.get(_util, _cust, ATTR_FORM));
        setAttrId(LgNamesContent.get(_util, _cust, ATTR_ID));
        setAttrGroupId(LgNamesContent.get(_util, _cust, ATTR_GROUPID));
        setAttrVarValue(LgNamesContent.get(_util, _cust, ATTR_VARVALUE));
        setAttrBean(LgNamesContent.get(_util, _cust, ATTR_BEAN));
        setAttrNa(LgNamesContent.get(_util, _cust, ATTR_N_A));
        setAttrParam(LgNamesContent.get(_util, _cust, ATTR_PARAM));
        setAttrName(LgNamesContent.get(_util, _cust, ATTR_NAME));
        setAttrQuoted(LgNamesContent.get(_util, _cust, ATTR_QUOTED));
        setAttrRel(LgNamesContent.get(_util, _cust, ATTR_REL));
        setAttrSrc(LgNamesContent.get(_util, _cust, ATTR_SRC));
        setAttrDelay(LgNamesContent.get(_util, _cust, ATTR_DELAY));
        setAttrPage(LgNamesContent.get(_util, _cust, ATTR_PAGE));
        setAttrRows(LgNamesContent.get(_util, _cust, ATTR_ROWS));
        setAttrClass(LgNamesContent.get(_util, _cust, ATTR_CLASS));
        setAttrCols(LgNamesContent.get(_util, _cust, ATTR_COLS));
        setAttrWidth(LgNamesContent.get(_util, _cust, ATTR_WIDTH));
        setAttrList(LgNamesContent.get(_util, _cust, ATTR_LIST));
        setAttrMap(LgNamesContent.get(_util, _cust, ATTR_MAP));
        setAttrKey(LgNamesContent.get(_util, _cust, ATTR_KEY));
        setAttrTo(LgNamesContent.get(_util, _cust, ATTR_TO));
        setAttrEq(LgNamesContent.get(_util, _cust, ATTR_EQ));
        setAttrVar(LgNamesContent.get(_util, _cust, ATTR_VAR));
        setAttrStep(LgNamesContent.get(_util, _cust, ATTR_STEP));
        setAttrFrom(LgNamesContent.get(_util, _cust, ATTR_FROM));
        setAttrValue(LgNamesContent.get(_util, _cust, ATTR_VALUE));
        setAttrInit(LgNamesContent.get(_util, _cust, ATTR_INIT));
        setAttrKeepFields(LgNamesContent.get(_util, _cust, ATTR_KEEPFIELDS));
        setAttrValueMessage(LgNamesContent.get(_util, _cust, ATTR_VALUEMESSAGE));
        setAttrConvertField(LgNamesContent.get(_util, _cust, ATTR_CONVERTFIELD));
        setAttrKeyClassName(LgNamesContent.get(_util, _cust, ATTR_KEYCLASSNAME));
        setAttrVarClassName(LgNamesContent.get(_util, _cust, ATTR_VARCLASSNAME));
        setAttrIndexClassName(LgNamesContent.get(_util, _cust, ATTR_INDEXCLASSNAME));
        setAttrEscapedAmp(LgNamesContent.get(_util, _cust, ATTR_ESCAPEDAMP));
        setAttrConvertValue(LgNamesContent.get(_util, _cust, ATTR_CONVERTVALUE));
        setAttrConvertFieldValue(LgNamesContent.get(_util, _cust, ATTR_CONVERTFIELDVALUE));
    }

    public void otherTags(StringMap<String> _util, StringMap<String> _cust) {
        setKeyWordTextarea(LgNamesContent.get(_util, _cust, TAG_TEXTAREA));
        setKeyWordSelect(LgNamesContent.get(_util, _cust, TAG_SELECT));
        setKeyWordFinally(LgNamesContent.get(_util, _cust, TAG_FINALLY));
        setKeyWordContinue(LgNamesContent.get(_util, _cust, TAG_CONTINUE));
        setKeyWordPackage(LgNamesContent.get(_util, _cust, TAG_PACKAGE));
        setKeyWordDefault(LgNamesContent.get(_util, _cust, TAG_DEFAULT));
        setKeyWordReturn(LgNamesContent.get(_util, _cust, TAG_RETURN));
        setKeyWordFor(LgNamesContent.get(_util, _cust, TAG_FOR));
        setKeyWordIf(LgNamesContent.get(_util, _cust, TAG_IF));
        setKeyWordClass(LgNamesContent.get(_util, _cust, TAG_CLASS));
        setKeyWordElseif(LgNamesContent.get(_util, _cust, TAG_ELSEIF));
        setKeyWordThrow(LgNamesContent.get(_util, _cust, TAG_THROW));
        setKeyWordBreak(LgNamesContent.get(_util, _cust, TAG_BREAK));
        setKeyWordCatch(LgNamesContent.get(_util, _cust, TAG_CATCH));
        setKeyWordCase(LgNamesContent.get(_util, _cust, TAG_CASE));
        setKeyWordTry(LgNamesContent.get(_util, _cust, TAG_TRY));
        setKeyWordElse(LgNamesContent.get(_util, _cust, TAG_ELSE));
        setKeyWordSwitch(LgNamesContent.get(_util, _cust, TAG_SWITCH));
        setKeyWordWhile(LgNamesContent.get(_util, _cust, TAG_WHILE));
        setKeyWordDo(LgNamesContent.get(_util, _cust, TAG_DO));
        setKeyWordCaption(LgNamesContent.get(_util, _cust, TAG_CAPTION));
        setKeyWordMessage(LgNamesContent.get(_util, _cust, TAG_MESSAGE));
        setKeyWordAnchor(LgNamesContent.get(_util, _cust, TAG_A));
        setKeyWordForm(LgNamesContent.get(_util, _cust, TAG_FORM));
        setKeyWordSpan(LgNamesContent.get(_util, _cust, TAG_SPAN));
        setKeyWordImg(LgNamesContent.get(_util, _cust, TAG_IMG));
        setKeyWordInput(LgNamesContent.get(_util, _cust, TAG_INPUT));
        setKeyWordLink(LgNamesContent.get(_util, _cust, TAG_LINK));
        setKeyWordSet(LgNamesContent.get(_util, _cust, TAG_SET));
        setKeyWordSubmit(LgNamesContent.get(_util, _cust, TAG_SUBMIT));
        setKeyWordStyle(LgNamesContent.get(_util, _cust, TAG_STYLE));
        setKeyWordField(LgNamesContent.get(_util, _cust, TAG_FIELD));
        setKeyWordImport(LgNamesContent.get(_util, _cust, TAG_IMPORT));
        setKeyWordItalic(LgNamesContent.get(_util, _cust, TAG_I));
        setKeyWordHr(LgNamesContent.get(_util, _cust, TAG_HR));
        setKeyWordTd(LgNamesContent.get(_util, _cust, TAG_TD));
        setKeyWordBody(LgNamesContent.get(_util, _cust, TAG_BODY));
        setKeyWordOption(LgNamesContent.get(_util, _cust, TAG_OPTION));
        setKeyWordUl(LgNamesContent.get(_util, _cust, TAG_UL));
        setKeyWordHTwo(LgNamesContent.get(_util, _cust, TAG_H2));
        setKeyWordPre(LgNamesContent.get(_util, _cust, TAG_PRE));
        setKeyWordBr(LgNamesContent.get(_util, _cust, TAG_BR));
        setKeyWordHSix(LgNamesContent.get(_util, _cust, TAG_H6));
        setKeyWordMap(LgNamesContent.get(_util, _cust, TAG_MAP));
        setKeyWordTh(LgNamesContent.get(_util, _cust, TAG_TH));
        setKeyWordLi(LgNamesContent.get(_util, _cust, TAG_LI));
        setKeyWordHOne(LgNamesContent.get(_util, _cust, TAG_H1));
        setKeyWordOl(LgNamesContent.get(_util, _cust, TAG_OL));
        setKeyWordBold(LgNamesContent.get(_util, _cust, TAG_B));
        setKeyWordDiv(LgNamesContent.get(_util, _cust, TAG_DIV));
        setKeyWordTr(LgNamesContent.get(_util, _cust, TAG_TR));
        setKeyWordHFive(LgNamesContent.get(_util, _cust, TAG_H5));
        setKeyWordPar(LgNamesContent.get(_util, _cust, TAG_P));
        setKeyWordHead(LgNamesContent.get(_util, _cust, TAG_HEAD));
        setKeyWordHFour(LgNamesContent.get(_util, _cust, TAG_H4));
        setKeyWordTable(LgNamesContent.get(_util, _cust, TAG_TABLE));
        setKeyWordHThree(LgNamesContent.get(_util, _cust, TAG_H3));
    }
    public void validateTagContents(StringMap<String> _list, AnalyzedPageEl _page) {
        for (EntryCust<String,String> e: _list.entryList()) {
            validateTag(e, _page);
        }
    }
    public void validateDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                _page.addStdError(err_);
            }
        }
    }
    public void validateAttrContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
                _page.addStdError(err_);
                continue;
            }
            if (!StringUtil.quickEq(key_,ATTR_PARAM)
                    &&keyWordValue_.startsWith(rendKeyWordsAttrs.getAttrParam())) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                _page.addStdError(err_);
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!MathExpUtil.isDollarWordChar(c)&&c!='-') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    _page.addStdError(err_);
                    break;
                }
            }
        }
    }
    public void validateValueContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
                _page.addStdError(err_);
            }
        }
    }
    public void validateStyleValueContents(StringMap<String> _list, AnalyzedPageEl _page) {
        validateTagContents(_list, _page);
    }
    public void validateStyleUnitContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String keyWordValue_ = validateTag(e, _page);
            if (keyWordValue_.isEmpty()) {
                continue;
            }
            if (StringExpUtil.isDigit(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                _page.addStdError(err_);
            }
        }
    }

    private String validateTag(EntryCust<String, String> _e, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        String key_ = _e.getKey();
        String keyWordValue_ = _e.getValue();
        if (keyWordValue_.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
            _page.addStdError(err_);
            return "";
        }
        for (char c: keyWordValue_.toCharArray()) {
            if (!MathExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                _page.addStdError(err_);
                break;
            }
        }
        return keyWordValue_;
    }

    public StringMap<String> allTags() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(TAG_SELECT,rendKeyWordsTags.getKeyWordSelect());
        keyWords_.addEntry(TAG_TEXTAREA,rendKeyWordsTags.getKeyWordTextarea());
        keyWords_.addEntry(TAG_ELSE,rendKeyWordsTags.getKeyWordElse());
        keyWords_.addEntry(TAG_CASE,rendKeyWordsTags.getKeyWordCase());
        keyWords_.addEntry(TAG_SWITCH,rendKeyWordsTags.getKeyWordSwitch());
        keyWords_.addEntry(TAG_DO,rendKeyWordsTags.getKeyWordDo());
        keyWords_.addEntry(TAG_TRY,rendKeyWordsTags.getKeyWordTry());
        keyWords_.addEntry(TAG_WHILE,rendKeyWordsTags.getKeyWordWhile());
        keyWords_.addEntry(TAG_ELSEIF,rendKeyWordsTags.getKeyWordElseif());
        keyWords_.addEntry(TAG_BREAK,rendKeyWordsTags.getKeyWordBreak());
        keyWords_.addEntry(TAG_THROW,rendKeyWordsTags.getKeyWordThrow());
        keyWords_.addEntry(TAG_CATCH,rendKeyWordsTags.getKeyWordCatch());
        keyWords_.addEntry(TAG_CAPTION,rendKeyWordsTags.getKeyWordCaption());
        keyWords_.addEntry(TAG_MESSAGE,rendKeyWordsTags.getKeyWordMessage());
        keyWords_.addEntry(TAG_OL,rendKeyWordsTags.getKeyWordOl());
        keyWords_.addEntry(TAG_HEAD,rendKeyWordsTags.getKeyWordHead());
        keyWords_.addEntry(TAG_UL,rendKeyWordsTags.getKeyWordUl());
        keyWords_.addEntry(TAG_IMPORT,rendKeyWordsTags.getKeyWordImport());
        keyWords_.addEntry(TAG_B,rendKeyWordsTags.getKeyWordBold());
        keyWords_.addEntry(TAG_FIELD,rendKeyWordsTags.getKeyWordField());
        keyWords_.addEntry(TAG_SET,rendKeyWordsTags.getKeyWordSet());
        keyWords_.addEntry(TAG_FORM,rendKeyWordsTags.getKeyWordForm());
        keyWords_.addEntry(TAG_SPAN,rendKeyWordsTags.getKeyWordSpan());
        keyWords_.addEntry(TAG_STYLE,rendKeyWordsTags.getKeyWordStyle());
        keyWords_.addEntry(TAG_BODY,rendKeyWordsTags.getKeyWordBody());
        keyWords_.addEntry(TAG_MAP,rendKeyWordsTags.getKeyWordMap());
        keyWords_.addEntry(TAG_SUBMIT,rendKeyWordsTags.getKeyWordSubmit());
        keyWords_.addEntry(TAG_IMG,rendKeyWordsTags.getKeyWordImg());
        keyWords_.addEntry(TAG_INPUT,rendKeyWordsTags.getKeyWordInput());
        keyWords_.addEntry(TAG_A,rendKeyWordsTags.getKeyWordAnchor());
        keyWords_.addEntry(TAG_LINK,rendKeyWordsTags.getKeyWordLink());
        keyWords_.addEntry(TAG_LI,rendKeyWordsTags.getKeyWordLi());
        keyWords_.addEntry(TAG_H6,rendKeyWordsTags.getKeyWordHSix());
        keyWords_.addEntry(TAG_BR,rendKeyWordsTags.getKeyWordBr());
        keyWords_.addEntry(TAG_H2,rendKeyWordsTags.getKeyWordHTwo());
        keyWords_.addEntry(TAG_H4,rendKeyWordsTags.getKeyWordHFour());
        keyWords_.addEntry(TAG_TR,rendKeyWordsTags.getKeyWordTr());
        keyWords_.addEntry(TAG_TD,rendKeyWordsTags.getKeyWordTd());
        keyWords_.addEntry(TAG_H1,rendKeyWordsTags.getKeyWordHOne());
        keyWords_.addEntry(TAG_DIV,rendKeyWordsTags.getKeyWordDiv());
        keyWords_.addEntry(TAG_OPTION,rendKeyWordsTags.getKeyWordOption());
        keyWords_.addEntry(TAG_TH,rendKeyWordsTags.getKeyWordTh());
        keyWords_.addEntry(TAG_TABLE,rendKeyWordsTags.getKeyWordTable());
        keyWords_.addEntry(TAG_HR,rendKeyWordsTags.getKeyWordHr());
        keyWords_.addEntry(TAG_P,rendKeyWordsTags.getKeyWordPar());
        keyWords_.addEntry(TAG_I,rendKeyWordsTags.getKeyWordItalic());
        keyWords_.addEntry(TAG_H3,rendKeyWordsTags.getKeyWordHThree());
        keyWords_.addEntry(TAG_H5,rendKeyWordsTags.getKeyWordHFive());
        keyWords_.addEntry(TAG_PRE,rendKeyWordsTags.getKeyWordPre());
        keyWords_.addEntry(TAG_FINALLY,rendKeyWordsTags.getKeyWordFinally());
        keyWords_.addEntry(TAG_DEFAULT,rendKeyWordsTags.getKeyWordDefault());
        keyWords_.addEntry(TAG_PACKAGE,rendKeyWordsTags.getKeyWordPackage());
        keyWords_.addEntry(TAG_CONTINUE,rendKeyWordsTags.getKeyWordContinue());
        keyWords_.addEntry(TAG_CLASS,rendKeyWordsTags.getKeyWordClass());
        keyWords_.addEntry(TAG_FOR,rendKeyWordsTags.getKeyWordFor());
        keyWords_.addEntry(TAG_IF,rendKeyWordsTags.getKeyWordIf());
        keyWords_.addEntry(TAG_RETURN,rendKeyWordsTags.getKeyWordReturn());
        return keyWords_;
    }
    public StringMap<String> allAttrs() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(ATTR_CLASSNAME,rendKeyWordsAttrs.getAttrClassName());
        keyWords_.addEntry(ATTR_MULTIPLE,rendKeyWordsAttrs.getAttrMultiple());
        keyWords_.addEntry(ATTR_TYPE,rendKeyWordsAttrs.getAttrType());
        keyWords_.addEntry(ATTR_INDEXCLASSNAME,rendKeyWordsAttrs.getAttrIndexClassName());
        keyWords_.addEntry(ATTR_CONVERTFIELD,rendKeyWordsAttrs.getAttrConvertField());
        keyWords_.addEntry(ATTR_CONVERTFIELDVALUE,rendKeyWordsAttrs.getAttrConvertFieldValue());
        keyWords_.addEntry(ATTR_VALUEMESSAGE,rendKeyWordsAttrs.getAttrValueMessage());
        keyWords_.addEntry(ATTR_ESCAPEDAMP,rendKeyWordsAttrs.getAttrEscapedAmp());
        keyWords_.addEntry(ATTR_KEYCLASSNAME,rendKeyWordsAttrs.getAttrKeyClassName());
        keyWords_.addEntry(ATTR_VARCLASSNAME,rendKeyWordsAttrs.getAttrVarClassName());
        keyWords_.addEntry(ATTR_CONVERTVALUE,rendKeyWordsAttrs.getAttrConvertValue());
        keyWords_.addEntry(ATTR_KEEPFIELDS,rendKeyWordsAttrs.getAttrKeepFields());
        keyWords_.addEntry(ATTR_TITLE,rendKeyWordsAttrs.getAttrTitle());
        keyWords_.addEntry(ATTR_FOR,rendKeyWordsAttrs.getAttrFor());
        keyWords_.addEntry(ATTR_FROM,rendKeyWordsAttrs.getAttrFrom());
        keyWords_.addEntry(ATTR_SELECTED,rendKeyWordsAttrs.getAttrSelected());
        keyWords_.addEntry(ATTR_N_A,rendKeyWordsAttrs.getAttrNa());
        keyWords_.addEntry(ATTR_TO,rendKeyWordsAttrs.getAttrTo());
        keyWords_.addEntry(ATTR_STEP,rendKeyWordsAttrs.getAttrStep());
        keyWords_.addEntry(ATTR_INIT,rendKeyWordsAttrs.getAttrInit());
        keyWords_.addEntry(ATTR_VALUE,rendKeyWordsAttrs.getAttrValue());
        keyWords_.addEntry(ATTR_COMMAND,rendKeyWordsAttrs.getAttrCommand());
        keyWords_.addEntry(ATTR_SGN,rendKeyWordsAttrs.getAttrSgn());
        keyWords_.addEntry(ATTR_CONDITION,rendKeyWordsAttrs.getAttrCondition());
        keyWords_.addEntry(ATTR_HREF,rendKeyWordsAttrs.getAttrHref());
        keyWords_.addEntry(ATTR_MESSAGE,rendKeyWordsAttrs.getAttrMessage());
        keyWords_.addEntry(ATTR_CHECKED,rendKeyWordsAttrs.getAttrChecked());
        keyWords_.addEntry(ATTR_ACTION,rendKeyWordsAttrs.getAttrAction());
        keyWords_.addEntry(ATTR_EQ,rendKeyWordsAttrs.getAttrEq());
        keyWords_.addEntry(ATTR_KEY,rendKeyWordsAttrs.getAttrKey());
        keyWords_.addEntry(ATTR_LABEL,rendKeyWordsAttrs.getAttrLabel());
        keyWords_.addEntry(ATTR_BEAN,rendKeyWordsAttrs.getAttrBean());
        keyWords_.addEntry(ATTR_N_I,rendKeyWordsAttrs.getAttrNi());
        keyWords_.addEntry(ATTR_N_R,rendKeyWordsAttrs.getAttrNr());
        keyWords_.addEntry(ATTR_N_F,rendKeyWordsAttrs.getAttrNf());
        keyWords_.addEntry(ATTR_PARAM,rendKeyWordsAttrs.getAttrParam());
        keyWords_.addEntry(ATTR_ALIAS,rendKeyWordsAttrs.getAttrAlias());
        keyWords_.addEntry(ATTR_QUOTED,rendKeyWordsAttrs.getAttrQuoted());
        keyWords_.addEntry(ATTR_NAME,rendKeyWordsAttrs.getAttrName());
        keyWords_.addEntry(ATTR_MAP,rendKeyWordsAttrs.getAttrMap());
        keyWords_.addEntry(ATTR_VAR,rendKeyWordsAttrs.getAttrVar());
        keyWords_.addEntry(ATTR_LIST,rendKeyWordsAttrs.getAttrList());
        keyWords_.addEntry(ATTR_PREPARE,rendKeyWordsAttrs.getAttrPrepare());
        keyWords_.addEntry(ATTR_FORM,rendKeyWordsAttrs.getAttrForm());
        keyWords_.addEntry(ATTR_VARVALUE,rendKeyWordsAttrs.getAttrVarValue());
        keyWords_.addEntry(ATTR_DELAY,rendKeyWordsAttrs.getAttrDelay());
        keyWords_.addEntry(ATTR_ESCAPED,rendKeyWordsAttrs.getAttrEscaped());
        keyWords_.addEntry(ATTR_PAGE,rendKeyWordsAttrs.getAttrPage());
        keyWords_.addEntry(ATTR_WIDTH,rendKeyWordsAttrs.getAttrWidth());
        keyWords_.addEntry(ATTR_SRC,rendKeyWordsAttrs.getAttrSrc());
        keyWords_.addEntry(ATTR_VALIDATOR,rendKeyWordsAttrs.getAttrValidator());
        keyWords_.addEntry(ATTR_GROUPID,rendKeyWordsAttrs.getAttrGroupId());
        keyWords_.addEntry(ATTR_CONVERT,rendKeyWordsAttrs.getAttrConvert());
        keyWords_.addEntry(ATTR_ROWS,rendKeyWordsAttrs.getAttrRows());
        keyWords_.addEntry(ATTR_ID,rendKeyWordsAttrs.getAttrId());
        keyWords_.addEntry(ATTR_COLS,rendKeyWordsAttrs.getAttrCols());
        keyWords_.addEntry(ATTR_DEFAULT,rendKeyWordsAttrs.getAttrDefault());
        keyWords_.addEntry(ATTR_CLASS,rendKeyWordsAttrs.getAttrClass());
        keyWords_.addEntry(ATTR_REL,rendKeyWordsAttrs.getAttrRel());
        return keyWords_;
    }

    public StringMap<String> allValues() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(VALUE_RANGE,rendKeyWordsValues.getValueRange().trim());
        keyWords_.addEntry(VALUE_TEXT,rendKeyWordsValues.getValueText().trim());
        keyWords_.addEntry(VALUE_CHECKBOX,rendKeyWordsValues.getValueCheckbox().trim());
        keyWords_.addEntry(VALUE_NUMBER,rendKeyWordsValues.getValueNumber().trim());
        keyWords_.addEntry(VALUE_RADIO,rendKeyWordsValues.getValueRadio().trim());
        keyWords_.addEntry(VALUE_SUBMIT,rendKeyWordsValues.getValueSubmit().trim());
        keyWords_.addEntry(VALUE_STYLE,rendKeyWordsValues.getValueStyle().trim());
        keyWords_.addEntry(VALUE_LIDISK,rendKeyWordsValues.getValueLiDisk().trim());
        keyWords_.addEntry(VALUE_LIMINLET,rendKeyWordsValues.getValueLiMinLet().trim());
        keyWords_.addEntry(VALUE_LISQUARE,rendKeyWordsValues.getValueLiSquare().trim());
        keyWords_.addEntry(VALUE_LINB,rendKeyWordsValues.getValueLiNb().trim());
        keyWords_.addEntry(VALUE_LIMAJLET,rendKeyWordsValues.getValueLiMajLet().trim());
        keyWords_.addEntry(VALUE_LIMINLAT,rendKeyWordsValues.getValueLiMinLat().trim());
        keyWords_.addEntry(VALUE_LIMAJLAT,rendKeyWordsValues.getValueLiMajLat().trim());
        keyWords_.addEntry(VALUE_LICIRCLE,rendKeyWordsValues.getValueLiCircle().trim());
        keyWords_.addEntry(VALUE_LIRECT,rendKeyWordsValues.getValueLiRect().trim());
        return keyWords_;
    }
    public StringMap<String> allStyleAttrs(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_ATTR_BORDER,rendKeyWordsStyles.getStyleAttrBorder());
        keyWords_.addEntry(STYLE_ATTR_FONTFAM,rendKeyWordsStyles.getStyleAttrFontFam());
        keyWords_.addEntry(STYLE_ATTR_FONTSIZE,rendKeyWordsStyles.getStyleAttrFontSize());
        keyWords_.addEntry(STYLE_ATTR_COLOR,rendKeyWordsStyles.getStyleAttrColor());
        keyWords_.addEntry(STYLE_ATTR_BACKGROUND,rendKeyWordsStyles.getStyleAttrBackground());
        return keyWords_;
    }
    public StringMap<String> allStyleValues(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_VALUE_GREY,rendKeyWordsStyles.getStyleValueGrey().trim());
        keyWords_.addEntry(STYLE_VALUE_MAGENTA,rendKeyWordsStyles.getStyleValueMagenta().trim());
        keyWords_.addEntry(STYLE_VALUE_GREEN,rendKeyWordsStyles.getStyleValueGreen().trim());
        keyWords_.addEntry(STYLE_VALUE_CYAN,rendKeyWordsStyles.getStyleValueCyan().trim());
        keyWords_.addEntry(STYLE_VALUE_YELLOW,rendKeyWordsStyles.getStyleValueYellow().trim());
        keyWords_.addEntry(STYLE_VALUE_WHITE,rendKeyWordsStyles.getStyleValueWhite().trim());
        keyWords_.addEntry(STYLE_VALUE_BLUE,rendKeyWordsStyles.getStyleValueBlue().trim());
        keyWords_.addEntry(STYLE_VALUE_BLACK,rendKeyWordsStyles.getStyleValueBlack().trim());
        keyWords_.addEntry(STYLE_VALUE_RGB,rendKeyWordsStyles.getStyleValueRgb().trim());
        keyWords_.addEntry(STYLE_VALUE_RED,rendKeyWordsStyles.getStyleValueRed().trim());
        return keyWords_;
    }
    public StringMap<String> allStyleUnits(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_UNIT_SOLID,rendKeyWordsStyles.getStyleUnitSolid());
        keyWords_.addEntry(STYLE_UNIT_PX,rendKeyWordsStyles.getStyleUnitPx());
        keyWords_.addEntry(STYLE_UNIT_EM,rendKeyWordsStyles.getStyleUnitEm());
        return keyWords_;
    }
    public RendKeyWordsGroup group() {
        return new RendKeyWordsGroup(rendKeyWordsTags,rendKeyWordsAttrs,rendKeyWordsValues,rendKeyWordsStyles);
    }
    public String getKeyWordFor() {
        return rendKeyWordsTags.getKeyWordFor();
    }
    public void setKeyWordFor(String _keyWordFor) {
        rendKeyWordsTags.setKeyWordFor(_keyWordFor);
    }
    public String getKeyWordWhile() {
        return rendKeyWordsTags.getKeyWordWhile();
    }
    public void setKeyWordWhile(String _keyWordWhile) {
        rendKeyWordsTags.setKeyWordWhile(_keyWordWhile);
    }
    public String getKeyWordDo() {
        return rendKeyWordsTags.getKeyWordDo();
    }
    public void setKeyWordDo(String _keyWordDo) {
        rendKeyWordsTags.setKeyWordDo(_keyWordDo);
    }
    public String getKeyWordIf() {
        return rendKeyWordsTags.getKeyWordIf();
    }
    public void setKeyWordIf(String _keyWordIf) {
        rendKeyWordsTags.setKeyWordIf(_keyWordIf);
    }
    public String getKeyWordElse() {
        return rendKeyWordsTags.getKeyWordElse();
    }
    public void setKeyWordElse(String _keyWordElse) {
        rendKeyWordsTags.setKeyWordElse(_keyWordElse);
    }
    public String getKeyWordElseif() {
        return rendKeyWordsTags.getKeyWordElseif();
    }
    public void setKeyWordElseif(String _keyWordElseif) {
        rendKeyWordsTags.setKeyWordElseif(_keyWordElseif);
    }
    public String getKeyWordTry() {
        return rendKeyWordsTags.getKeyWordTry();
    }
    public void setKeyWordTry(String _keyWordTry) {
        rendKeyWordsTags.setKeyWordTry(_keyWordTry);
    }
    public String getKeyWordFinally() {
        return rendKeyWordsTags.getKeyWordFinally();
    }
    public void setKeyWordFinally(String _keyWordFinally) {
        rendKeyWordsTags.setKeyWordFinally(_keyWordFinally);
    }
    public String getKeyWordCatch() {
        return rendKeyWordsTags.getKeyWordCatch();
    }
    public void setKeyWordCatch(String _keyWordCatch) {
        rendKeyWordsTags.setKeyWordCatch(_keyWordCatch);
    }
    public String getKeyWordSwitch() {
        return rendKeyWordsTags.getKeyWordSwitch();
    }
    public void setKeyWordSwitch(String _keyWordSwitch) {
        rendKeyWordsTags.setKeyWordSwitch(_keyWordSwitch);
    }
    public String getKeyWordCase() {
        return rendKeyWordsTags.getKeyWordCase();
    }
    public void setKeyWordCase(String _keyWordCase) {
        rendKeyWordsTags.setKeyWordCase(_keyWordCase);
    }
    public String getKeyWordDefault() {
        return rendKeyWordsTags.getKeyWordDefault();
    }
    public void setKeyWordDefault(String _keyWordDefault) {
        rendKeyWordsTags.setKeyWordDefault(_keyWordDefault);
    }

    public String getKeyWordReturn() {
        return rendKeyWordsTags.getKeyWordReturn();
    }
    public void setKeyWordReturn(String _keyWordReturn) {
        rendKeyWordsTags.setKeyWordReturn(_keyWordReturn);
    }
    public String getKeyWordThrow() {
        return rendKeyWordsTags.getKeyWordThrow();
    }
    public void setKeyWordThrow(String _keyWordThrow) {
        rendKeyWordsTags.setKeyWordThrow(_keyWordThrow);
    }
    public String getKeyWordBreak() {
        return rendKeyWordsTags.getKeyWordBreak();
    }
    public void setKeyWordBreak(String _keyWordBreak) {
        rendKeyWordsTags.setKeyWordBreak(_keyWordBreak);
    }
    public String getKeyWordContinue() {
        return rendKeyWordsTags.getKeyWordContinue();
    }
    public void setKeyWordContinue(String _keyWordContinue) {
        rendKeyWordsTags.setKeyWordContinue(_keyWordContinue);
    }

    public String getKeyWordSet() {
        return rendKeyWordsTags.getKeyWordSet();
    }

    public void setKeyWordSet(String _keyWordSet) {
        this.rendKeyWordsTags.setKeyWordSet(_keyWordSet);
    }

    public String getKeyWordImport() {
        return rendKeyWordsTags.getKeyWordImport();
    }

    public void setKeyWordImport(String _keyWordImport) {
        this.rendKeyWordsTags.setKeyWordImport(_keyWordImport);
    }

    public String getKeyWordPackage() {
        return rendKeyWordsTags.getKeyWordPackage();
    }

    public void setKeyWordPackage(String _keyWordPackage) {
        this.rendKeyWordsTags.setKeyWordPackage(_keyWordPackage);
    }

    public String getKeyWordClass() {
        return rendKeyWordsTags.getKeyWordClass();
    }

    public void setKeyWordClass(String _keyWordClass) {
        this.rendKeyWordsTags.setKeyWordClass(_keyWordClass);
    }

    public String getKeyWordField() {
        return rendKeyWordsTags.getKeyWordField();
    }

    public void setKeyWordField(String _keyWordField) {
        this.rendKeyWordsTags.setKeyWordField(_keyWordField);
    }

    public String getKeyWordForm() {
        return rendKeyWordsTags.getKeyWordForm();
    }

    public void setKeyWordForm(String _keyWordForm) {
        this.rendKeyWordsTags.setKeyWordForm(_keyWordForm);
    }

    public String getKeyWordSubmit() {
        return rendKeyWordsTags.getKeyWordSubmit();
    }

    public void setKeyWordSubmit(String _keyWordSubmit) {
        this.rendKeyWordsTags.setKeyWordSubmit(_keyWordSubmit);
    }

    public String getKeyWordImg() {
        return rendKeyWordsTags.getKeyWordImg();
    }

    public void setKeyWordImg(String _keyWordImg) {
        this.rendKeyWordsTags.setKeyWordImg(_keyWordImg);
    }

    public String getKeyWordSelect() {
        return rendKeyWordsTags.getKeyWordSelect();
    }

    public void setKeyWordSelect(String _keyWordSelect) {
        this.rendKeyWordsTags.setKeyWordSelect(_keyWordSelect);
    }

    public String getKeyWordMessage() {
        return rendKeyWordsTags.getKeyWordMessage();
    }

    public void setKeyWordMessage(String _keyWordMessage) {
        this.rendKeyWordsTags.setKeyWordMessage(_keyWordMessage);
    }

    public String getKeyWordAnchor() {
        return rendKeyWordsTags.getKeyWordAnchor();
    }

    public void setKeyWordAnchor(String _keyWordAnchor) {
        this.rendKeyWordsTags.setKeyWordAnchor(_keyWordAnchor);
    }

    public String getKeyWordInput() {
        return rendKeyWordsTags.getKeyWordInput();
    }

    public void setKeyWordInput(String _keyWordInput) {
        this.rendKeyWordsTags.setKeyWordInput(_keyWordInput);
    }

    public String getKeyWordTextarea() {
        return rendKeyWordsTags.getKeyWordTextarea();
    }

    public void setKeyWordTextarea(String _keyWordTextarea) {
        this.rendKeyWordsTags.setKeyWordTextarea(_keyWordTextarea);
    }

    public String getKeyWordSpan() {
        return rendKeyWordsTags.getKeyWordSpan();
    }

    public void setKeyWordSpan(String _keyWordSpan) {
        this.rendKeyWordsTags.setKeyWordSpan(_keyWordSpan);
    }

    public String getKeyWordLink() {
        return rendKeyWordsTags.getKeyWordLink();
    }

    public void setKeyWordLink(String _keyWordLink) {
        this.rendKeyWordsTags.setKeyWordLink(_keyWordLink);
    }

    public String getKeyWordStyle() {
        return rendKeyWordsTags.getKeyWordStyle();
    }

    public void setKeyWordStyle(String _keyWordStyle) {
        this.rendKeyWordsTags.setKeyWordStyle(_keyWordStyle);
    }

    public String getKeyWordBody() {
        return rendKeyWordsTags.getKeyWordBody();
    }

    public void setKeyWordBody(String _keyWordBody) {
        this.rendKeyWordsTags.setKeyWordBody(_keyWordBody);
    }

    public String getKeyWordHead() {
        return rendKeyWordsTags.getKeyWordHead();
    }

    public void setKeyWordHead(String _keyWordHead) {
        this.rendKeyWordsTags.setKeyWordHead(_keyWordHead);
    }

//    public String getKeyWordMap() {
//        return rendKeyWordsTags.getKeyWordMap();
//    }

    public void setKeyWordMap(String _keyWordMap) {
        this.rendKeyWordsTags.setKeyWordMap(_keyWordMap);
    }

//    public String getKeyWordLi() {
//        return rendKeyWordsTags.getKeyWordLi();
//    }

    public void setKeyWordLi(String _keyWordLi) {
        this.rendKeyWordsTags.setKeyWordLi(_keyWordLi);
    }

//    public String getKeyWordOl() {
//        return rendKeyWordsTags.getKeyWordOl();
//    }

    public void setKeyWordOl(String _keyWordOl) {
        this.rendKeyWordsTags.setKeyWordOl(_keyWordOl);
    }

//    public String getKeyWordUl() {
//        return rendKeyWordsTags.getKeyWordUl();
//    }

    public void setKeyWordUl(String _keyWordUl) {
        this.rendKeyWordsTags.setKeyWordUl(_keyWordUl);
    }

//    public String getKeyWordBold() {
//        return rendKeyWordsTags.getKeyWordBold();
//    }

    public void setKeyWordBold(String _keyWordBold) {
        this.rendKeyWordsTags.setKeyWordBold(_keyWordBold);
    }

//    public String getKeyWordItalic() {
//        return rendKeyWordsTags.getKeyWordItalic();
//    }

    public void setKeyWordItalic(String _keyWordItalic) {
        this.rendKeyWordsTags.setKeyWordItalic(_keyWordItalic);
    }

//    public String getKeyWordPre() {
//        return rendKeyWordsTags.getKeyWordPre();
//    }

    public void setKeyWordPre(String _keyWordPre) {
        this.rendKeyWordsTags.setKeyWordPre(_keyWordPre);
    }

//    public String getKeyWordHOne() {
//        return rendKeyWordsTags.getKeyWordHOne();
//    }

    public void setKeyWordHOne(String _keyWordHOne) {
        this.rendKeyWordsTags.setKeyWordHOne(_keyWordHOne);
    }

//    public String getKeyWordHTwo() {
//        return rendKeyWordsTags.getKeyWordHTwo();
//    }

    public void setKeyWordHTwo(String _keyWordHTwo) {
        this.rendKeyWordsTags.setKeyWordHTwo(_keyWordHTwo);
    }

//    public String getKeyWordHThree() {
//        return rendKeyWordsTags.getKeyWordHThree();
//    }

    public void setKeyWordHThree(String _keyWordHThree) {
        this.rendKeyWordsTags.setKeyWordHThree(_keyWordHThree);
    }

//    public String getKeyWordHFour() {
//        return rendKeyWordsTags.getKeyWordHFour();
//    }

    public void setKeyWordHFour(String _keyWordHFour) {
        this.rendKeyWordsTags.setKeyWordHFour(_keyWordHFour);
    }

//    public String getKeyWordHFive() {
//        return rendKeyWordsTags.getKeyWordHFive();
//    }

    public void setKeyWordHFive(String _keyWordHFive) {
        this.rendKeyWordsTags.setKeyWordHFive(_keyWordHFive);
    }

//    public String getKeyWordHSix() {
//        return rendKeyWordsTags.getKeyWordHSix();
//    }

    public void setKeyWordHSix(String _keyWordHSix) {
        this.rendKeyWordsTags.setKeyWordHSix(_keyWordHSix);
    }

//    public String getKeyWordBr() {
//        return rendKeyWordsTags.getKeyWordBr();
//    }

    public void setKeyWordBr(String _keyWordBr) {
        this.rendKeyWordsTags.setKeyWordBr(_keyWordBr);
    }

//    public String getKeyWordHr() {
//        return rendKeyWordsTags.getKeyWordHr();
//    }

    public void setKeyWordHr(String _keyWordHr) {
        this.rendKeyWordsTags.setKeyWordHr(_keyWordHr);
    }

//    public String getKeyWordPar() {
//        return rendKeyWordsTags.getKeyWordPar();
//    }

    public void setKeyWordPar(String _keyWordPar) {
        this.rendKeyWordsTags.setKeyWordPar(_keyWordPar);
    }

//    public String getKeyWordTable() {
//        return rendKeyWordsTags.getKeyWordTable();
//    }

    public void setKeyWordTable(String _keyWordTable) {
        this.rendKeyWordsTags.setKeyWordTable(_keyWordTable);
    }

//    public String getKeyWordCaption() {
//        return rendKeyWordsTags.getKeyWordCaption();
//    }

    public void setKeyWordCaption(String _keyWordCaption) {
        this.rendKeyWordsTags.setKeyWordCaption(_keyWordCaption);
    }

//    public String getKeyWordTd() {
//        return rendKeyWordsTags.getKeyWordTd();
//    }

    public void setKeyWordTd(String _keyWordTd) {
        this.rendKeyWordsTags.setKeyWordTd(_keyWordTd);
    }

//    public String getKeyWordTh() {
//        return rendKeyWordsTags.getKeyWordTh();
//    }

    public void setKeyWordTh(String _keyWordTh) {
        this.rendKeyWordsTags.setKeyWordTh(_keyWordTh);
    }

//    public String getKeyWordTr() {
//        return rendKeyWordsTags.getKeyWordTr();
//    }

    public void setKeyWordTr(String _keyWordTr) {
        this.rendKeyWordsTags.setKeyWordTr(_keyWordTr);
    }

//    public String getKeyWordDiv() {
//        return rendKeyWordsTags.getKeyWordDiv();
//    }

    public void setKeyWordDiv(String _keyWordDiv) {
        this.rendKeyWordsTags.setKeyWordDiv(_keyWordDiv);
    }

    public String getKeyWordOption() {
        return rendKeyWordsTags.getKeyWordOption();
    }

    public void setKeyWordOption(String _keyWordOptions) {
        this.rendKeyWordsTags.setKeyWordOption(_keyWordOptions);
    }

    public String getAttrList() {
        return rendKeyWordsAttrs.getAttrList();
    }

    public void setAttrList(String _attrList) {
        this.rendKeyWordsAttrs.setAttrList(_attrList);
    }

    public String getAttrMap() {
        return rendKeyWordsAttrs.getAttrMap();
    }

    public void setAttrMap(String _attrMap) {
        this.rendKeyWordsAttrs.setAttrMap(_attrMap);
    }

    public String getAttrVar() {
        return rendKeyWordsAttrs.getAttrVar();
    }

    public void setAttrVar(String _attrVar) {
        this.rendKeyWordsAttrs.setAttrVar(_attrVar);
    }

    public String getAttrKey() {
        return rendKeyWordsAttrs.getAttrKey();
    }

    public void setAttrKey(String _attrKey) {
        this.rendKeyWordsAttrs.setAttrKey(_attrKey);
    }

    public String getAttrValue() {
        return rendKeyWordsAttrs.getAttrValue();
    }

    public void setAttrValue(String _attrValue) {
        this.rendKeyWordsAttrs.setAttrValue(_attrValue);
    }

    public String getAttrFrom() {
        return rendKeyWordsAttrs.getAttrFrom();
    }

    public void setAttrFrom(String _attrFrom) {
        this.rendKeyWordsAttrs.setAttrFrom(_attrFrom);
    }

    public String getAttrInit() {
        return rendKeyWordsAttrs.getAttrInit();
    }

    public void setAttrInit(String _attrInit) {
        this.rendKeyWordsAttrs.setAttrInit(_attrInit);
    }

    public String getAttrStep() {
        return rendKeyWordsAttrs.getAttrStep();
    }

    public void setAttrStep(String _attrStep) {
        this.rendKeyWordsAttrs.setAttrStep(_attrStep);
    }

    public String getAttrTo() {
        return rendKeyWordsAttrs.getAttrTo();
    }

    public void setAttrTo(String _attrTo) {
        this.rendKeyWordsAttrs.setAttrTo(_attrTo);
    }

    public String getAttrEq() {
        return rendKeyWordsAttrs.getAttrEq();
    }

    public void setAttrEq(String _attrEq) {
        this.rendKeyWordsAttrs.setAttrEq(_attrEq);
    }

    public String getAttrCondition() {
        return rendKeyWordsAttrs.getAttrCondition();
    }

    public void setAttrCondition(String _attrCondition) {
        this.rendKeyWordsAttrs.setAttrCondition(_attrCondition);
    }

    public String getAttrClassName() {
        return rendKeyWordsAttrs.getAttrClassName();
    }

    public void setAttrClassName(String _attrClassName) {
        this.rendKeyWordsAttrs.setAttrClassName(_attrClassName);
    }

    public String getAttrKeyClassName() {
        return rendKeyWordsAttrs.getAttrKeyClassName();
    }

    public void setAttrKeyClassName(String _attrKeyClassName) {
        this.rendKeyWordsAttrs.setAttrKeyClassName(_attrKeyClassName);
    }

    public String getAttrVarClassName() {
        return rendKeyWordsAttrs.getAttrVarClassName();
    }

    public void setAttrVarClassName(String _attrVarClassName) {
        this.rendKeyWordsAttrs.setAttrVarClassName(_attrVarClassName);
    }

    public String getAttrIndexClassName() {
        return rendKeyWordsAttrs.getAttrIndexClassName();
    }

    public void setAttrIndexClassName(String _attrIndexClassName) {
        this.rendKeyWordsAttrs.setAttrIndexClassName(_attrIndexClassName);
    }

    public String getAttrAlias() {
        return rendKeyWordsAttrs.getAttrAlias();
    }

    public void setAttrAlias(String _attrAlias) {
        this.rendKeyWordsAttrs.setAttrAlias(_attrAlias);
    }

    public String getAttrLabel() {
        return rendKeyWordsAttrs.getAttrLabel();
    }

    public void setAttrLabel(String _attrLabel) {
        this.rendKeyWordsAttrs.setAttrLabel(_attrLabel);
    }

    public String getAttrBean() {
        return rendKeyWordsAttrs.getAttrBean();
    }

    public void setAttrBean(String _attrBean) {
        this.rendKeyWordsAttrs.setAttrBean(_attrBean);
    }

    public String getAttrType() {
        return rendKeyWordsAttrs.getAttrType();
    }

    public void setAttrType(String _attrType) {
        this.rendKeyWordsAttrs.setAttrType(_attrType);
    }

    public String getAttrChecked() {
        return rendKeyWordsAttrs.getAttrChecked();
    }

    public void setAttrChecked(String _attrChecked) {
        this.rendKeyWordsAttrs.setAttrChecked(_attrChecked);
    }

    public String getAttrSelected() {
        return rendKeyWordsAttrs.getAttrSelected();
    }

    public void setAttrSelected(String _attrSelected) {
        this.rendKeyWordsAttrs.setAttrSelected(_attrSelected);
    }

    public String getAttrMultiple() {
        return rendKeyWordsAttrs.getAttrMultiple();
    }

    public void setAttrMultiple(String _attrMultiple) {
        this.rendKeyWordsAttrs.setAttrMultiple(_attrMultiple);
    }

    public String getValueText() {
        return rendKeyWordsValues.getValueText();
    }

    public void setValueText(String _valueText) {
        this.rendKeyWordsValues.setValueText(_valueText);
    }

    public String getValueCheckbox() {
        return rendKeyWordsValues.getValueCheckbox();
    }

    public void setValueCheckbox(String _valueCheckbox) {
        this.rendKeyWordsValues.setValueCheckbox(_valueCheckbox);
    }

    public String getValueRadio() {
        return rendKeyWordsValues.getValueRadio();
    }

    public void setValueRadio(String _valueRadio) {
        this.rendKeyWordsValues.setValueRadio(_valueRadio);
    }

//    public String getValueNumber() {
//        return rendKeyWordsValues.getValueNumber();
//    }

    public void setValueNumber(String _valueNumber) {
        this.rendKeyWordsValues.setValueNumber(_valueNumber);
    }

//    public String getValueRange() {
//        return rendKeyWordsValues.getValueRange();
//    }

    public void setValueRange(String _valueRange) {
        this.rendKeyWordsValues.setValueRange(_valueRange);
    }

    public String getValueSubmit() {
        return rendKeyWordsValues.getValueSubmit();
    }

    public void setValueSubmit(String _valueSubmit) {
        this.rendKeyWordsValues.setValueSubmit(_valueSubmit);
    }

    public String getAttrNf() {
        return rendKeyWordsAttrs.getAttrNf();
    }

    public void setAttrNf(String _attrNf) {
        this.rendKeyWordsAttrs.setAttrNf(_attrNf);
    }

    public String getAttrNa() {
        return rendKeyWordsAttrs.getAttrNa();
    }

    public void setAttrNa(String _attrNa) {
        this.rendKeyWordsAttrs.setAttrNa(_attrNa);
    }

    public String getAttrNi() {
        return rendKeyWordsAttrs.getAttrNi();
    }

    public void setAttrNi(String _attrNi) {
        this.rendKeyWordsAttrs.setAttrNi(_attrNi);
    }

    public String getAttrNr() {
        return rendKeyWordsAttrs.getAttrNr();
    }

    public void setAttrNr(String _attrNr) {
        this.rendKeyWordsAttrs.setAttrNr(_attrNr);
    }

    public String getAttrParam() {
        return rendKeyWordsAttrs.getAttrParam();
    }

    public void setAttrParam(String _attrParam) {
        this.rendKeyWordsAttrs.setAttrParam(_attrParam);
    }

    public String getAttrName() {
        return rendKeyWordsAttrs.getAttrName();
    }

    public void setAttrName(String _attrName) {
        this.rendKeyWordsAttrs.setAttrName(_attrName);
    }

    public String getAttrHref() {
        return rendKeyWordsAttrs.getAttrHref();
    }

    public void setAttrHref(String _attrHref) {
        this.rendKeyWordsAttrs.setAttrHref(_attrHref);
    }

    public String getAttrCommand() {
        return rendKeyWordsAttrs.getAttrCommand();
    }

    public void setAttrCommand(String _attrCommand) {
        this.rendKeyWordsAttrs.setAttrCommand(_attrCommand);
    }

    public String getAttrSgn() {
        return rendKeyWordsAttrs.getAttrSgn();
    }

    public void setAttrSgn(String _attrSgn) {
        this.rendKeyWordsAttrs.setAttrSgn(_attrSgn);
    }

    public String getAttrAction() {
        return rendKeyWordsAttrs.getAttrAction();
    }

    public void setAttrAction(String _attrAction) {
        this.rendKeyWordsAttrs.setAttrAction(_attrAction);
    }

    public String getAttrTitle() {
        return rendKeyWordsAttrs.getAttrTitle();
    }

    public void setAttrTitle(String _attrTitle) {
        this.rendKeyWordsAttrs.setAttrTitle(_attrTitle);
    }

    public String getAttrFor() {
        return rendKeyWordsAttrs.getAttrFor();
    }

    public void setAttrFor(String _attrFor) {
        this.rendKeyWordsAttrs.setAttrFor(_attrFor);
    }

    public String getAttrMessage() {
        return rendKeyWordsAttrs.getAttrMessage();
    }

    public void setAttrMessage(String _attrMessage) {
        this.rendKeyWordsAttrs.setAttrMessage(_attrMessage);
    }

    public String getAttrQuoted() {
        return rendKeyWordsAttrs.getAttrQuoted();
    }

    public void setAttrQuoted(String _attrQuoted) {
        this.rendKeyWordsAttrs.setAttrQuoted(_attrQuoted);
    }

    public String getAttrEscaped() {
        return rendKeyWordsAttrs.getAttrEscaped();
    }

    public void setAttrEscaped(String _attrEscaped) {
        this.rendKeyWordsAttrs.setAttrEscaped(_attrEscaped);
    }

    public String getAttrEscapedAmp() {
        return rendKeyWordsAttrs.getAttrEscapedAmp();
    }

    public void setAttrEscapedAmp(String _attrEscapedAmp) {
        this.rendKeyWordsAttrs.setAttrEscapedAmp(_attrEscapedAmp);
    }

    public String getAttrConvert() {
        return rendKeyWordsAttrs.getAttrConvert();
    }

    public void setAttrConvert(String _attrConvert) {
        this.rendKeyWordsAttrs.setAttrConvert(_attrConvert);
    }

    public String getAttrConvertValue() {
        return rendKeyWordsAttrs.getAttrConvertValue();
    }

    public void setAttrConvertValue(String _attrConvertValue) {
        this.rendKeyWordsAttrs.setAttrConvertValue(_attrConvertValue);
    }

    public String getAttrConvertField() {
        return rendKeyWordsAttrs.getAttrConvertField();
    }

    public void setAttrConvertField(String _attrConvertField) {
        this.rendKeyWordsAttrs.setAttrConvertField(_attrConvertField);
    }

    public String getAttrConvertFieldValue() {
        return rendKeyWordsAttrs.getAttrConvertFieldValue();
    }

    public void setAttrConvertFieldValue(String _attrConvertFieldValue) {
        this.rendKeyWordsAttrs.setAttrConvertFieldValue(_attrConvertFieldValue);
    }

    public String getAttrDefault() {
        return rendKeyWordsAttrs.getAttrDefault();
    }

    public void setAttrDefault(String _attrDefault) {
        this.rendKeyWordsAttrs.setAttrDefault(_attrDefault);
    }

    public String getAttrVarValue() {
        return rendKeyWordsAttrs.getAttrVarValue();
    }

    public void setAttrVarValue(String _attrVarValue) {
        this.rendKeyWordsAttrs.setAttrVarValue(_attrVarValue);
    }

    public String getAttrPrepare() {
        return rendKeyWordsAttrs.getAttrPrepare();
    }

    public void setAttrPrepare(String _attrPrepare) {
        this.rendKeyWordsAttrs.setAttrPrepare(_attrPrepare);
    }

    public String getAttrForm() {
        return rendKeyWordsAttrs.getAttrForm();
    }

    public void setAttrForm(String _attrForm) {
        this.rendKeyWordsAttrs.setAttrForm(_attrForm);
    }

    public String getAttrValueMessage() {
        return rendKeyWordsAttrs.getAttrValueMessage();
    }

    public void setAttrValueMessage(String _attrValueMessage) {
        this.rendKeyWordsAttrs.setAttrValueMessage(_attrValueMessage);
    }

    public String getAttrId() {
        return rendKeyWordsAttrs.getAttrId();
    }

    public void setAttrId(String _attrId) {
        this.rendKeyWordsAttrs.setAttrId(_attrId);
    }

    public String getAttrGroupId() {
        return rendKeyWordsAttrs.getAttrGroupId();
    }

    public void setAttrGroupId(String _attrGroupId) {
        this.rendKeyWordsAttrs.setAttrGroupId(_attrGroupId);
    }

    public String getAttrValidator() {
        return rendKeyWordsAttrs.getAttrValidator();
    }

    public void setAttrValidator(String _attrValidator) {
        this.rendKeyWordsAttrs.setAttrValidator(_attrValidator);
    }

    public String getAttrPage() {
        return rendKeyWordsAttrs.getAttrPage();
    }

    public void setAttrPage(String _attrPage) {
        this.rendKeyWordsAttrs.setAttrPage(_attrPage);
    }

    public String getAttrKeepFields() {
        return rendKeyWordsAttrs.getAttrKeepFields();
    }

    public void setAttrKeepFields(String _attrKeepFields) {
        this.rendKeyWordsAttrs.setAttrKeepFields(_attrKeepFields);
    }

    public String getAttrSrc() {
        return rendKeyWordsAttrs.getAttrSrc();
    }

    public void setAttrSrc(String _attrSrc) {
        this.rendKeyWordsAttrs.setAttrSrc(_attrSrc);
    }

//    public String getAttrRows() {
//        return rendKeyWordsAttrs.getAttrRows();
//    }

    public void setAttrRows(String _attrRows) {
        this.rendKeyWordsAttrs.setAttrRows(_attrRows);
    }

//    public String getAttrCols() {
//        return rendKeyWordsAttrs.getAttrCols();
//    }

    public void setAttrCols(String _attrCols) {
        this.rendKeyWordsAttrs.setAttrCols(_attrCols);
    }

//    public String getAttrClass() {
//        return rendKeyWordsAttrs.getAttrClass();
//    }

    public void setAttrClass(String _attrClass) {
        this.rendKeyWordsAttrs.setAttrClass(_attrClass);
    }

//    public String getAttrWidth() {
//        return rendKeyWordsAttrs.getAttrWidth();
//    }

    public void setAttrWidth(String _attrWidth) {
        this.rendKeyWordsAttrs.setAttrWidth(_attrWidth);
    }

//    public String getAttrDelay() {
//        return rendKeyWordsAttrs.getAttrDelay();
//    }

    public void setAttrDelay(String _attrDelay) {
        this.rendKeyWordsAttrs.setAttrDelay(_attrDelay);
    }

    public String getAttrRel() {
        return rendKeyWordsAttrs.getAttrRel();
    }

    public void setAttrRel(String _attrRel) {
        this.rendKeyWordsAttrs.setAttrRel(_attrRel);
    }

    public String getValueStyle() {
        return rendKeyWordsValues.getValueStyle();
    }

    public void setValueStyle(String _valueStyle) {
        this.rendKeyWordsValues.setValueStyle(_valueStyle);
    }

//    public String getValueLiNb() {
//        return rendKeyWordsValues.getValueLiNb();
//    }

    public void setValueLiNb(String _valueLiNb) {
        this.rendKeyWordsValues.setValueLiNb(_valueLiNb);
    }

//    public String getValueLiMinLet() {
//        return rendKeyWordsValues.getValueLiMinLet();
//    }

    public void setValueLiMinLet(String _valueLiMinLet) {
        this.rendKeyWordsValues.setValueLiMinLet(_valueLiMinLet);
    }

//    public String getValueLiMajLet() {
//        return rendKeyWordsValues.getValueLiMajLet();
//    }

    public void setValueLiMajLet(String _valueLiMajLet) {
        this.rendKeyWordsValues.setValueLiMajLet(_valueLiMajLet);
    }

//    public String getValueLiMinLat() {
//        return rendKeyWordsValues.getValueLiMinLat();
//    }

    public void setValueLiMinLat(String _valueLiMinLat) {
        this.rendKeyWordsValues.setValueLiMinLat(_valueLiMinLat);
    }

//    public String getValueLiMajLat() {
//        return rendKeyWordsValues.getValueLiMajLat();
//    }

    public void setValueLiMajLat(String _valueLiMajLat) {
        this.rendKeyWordsValues.setValueLiMajLat(_valueLiMajLat);
    }

//    public String getValueLiCircle() {
//        return rendKeyWordsValues.getValueLiCircle();
//    }

    public void setValueLiCircle(String _valueLiCircle) {
        this.rendKeyWordsValues.setValueLiCircle(_valueLiCircle);
    }

//    public String getValueLiDisk() {
//        return rendKeyWordsValues.getValueLiDisk();
//    }

    public void setValueLiDisk(String _valueLiDisk) {
        this.rendKeyWordsValues.setValueLiDisk(_valueLiDisk);
    }

//    public String getValueLiRect() {
//        return rendKeyWordsValues.getValueLiRect();
//    }

    public void setValueLiRect(String _valueLiRect) {
        this.rendKeyWordsValues.setValueLiRect(_valueLiRect);
    }

//    public String getValueLiSquare() {
//        return rendKeyWordsValues.getValueLiSquare();
//    }

    public void setValueLiSquare(String _valueLiSquare) {
        this.rendKeyWordsValues.setValueLiSquare(_valueLiSquare);
    }

//    public String getStyleAttrFontFam() {
//        return rendKeyWordsStyles.getStyleAttrFontFam();
//    }

    public void setStyleAttrFontFam(String _styleAttrFontFam) {
        this.rendKeyWordsStyles.setStyleAttrFontFam(_styleAttrFontFam);
    }

//    public String getStyleAttrFontSize() {
//        return rendKeyWordsStyles.getStyleAttrFontSize();
//    }

    public void setStyleAttrFontSize(String _styleAttrFontSize) {
        this.rendKeyWordsStyles.setStyleAttrFontSize(_styleAttrFontSize);
    }

//    public String getStyleAttrColor() {
//        return rendKeyWordsStyles.getStyleAttrColor();
//    }

    public void setStyleAttrColor(String _styleAttrColor) {
        this.rendKeyWordsStyles.setStyleAttrColor(_styleAttrColor);
    }

//    public String getStyleAttrBackground() {
//        return rendKeyWordsStyles.getStyleAttrBackground();
//    }

    public void setStyleAttrBackground(String _styleAttrBackground) {
        this.rendKeyWordsStyles.setStyleAttrBackground(_styleAttrBackground);
    }

//    public String getStyleAttrBorder() {
//        return rendKeyWordsStyles.getStyleAttrBorder();
//    }

    public void setStyleAttrBorder(String _styleAttrBorder) {
        this.rendKeyWordsStyles.setStyleAttrBorder(_styleAttrBorder);
    }

//    public String getStyleValueRgb() {
//        return rendKeyWordsStyles.getStyleValueRgb();
//    }

    public void setStyleValueRgb(String _styleValueRgb) {
        this.rendKeyWordsStyles.setStyleValueRgb(_styleValueRgb);
    }

//    public String getStyleValueRed() {
//        return rendKeyWordsStyles.getStyleValueRed();
//    }

    public void setStyleValueRed(String _styleValueRed) {
        this.rendKeyWordsStyles.setStyleValueRed(_styleValueRed);
    }

//    public String getStyleValueGreen() {
//        return rendKeyWordsStyles.getStyleValueGreen();
//    }

    public void setStyleValueGreen(String _styleValueGreen) {
        this.rendKeyWordsStyles.setStyleValueGreen(_styleValueGreen);
    }

//    public String getStyleValueBlue() {
//        return rendKeyWordsStyles.getStyleValueBlue();
//    }

    public void setStyleValueBlue(String _styleValueBlue) {
        this.rendKeyWordsStyles.setStyleValueBlue(_styleValueBlue);
    }

//    public String getStyleValueYellow() {
//        return rendKeyWordsStyles.getStyleValueYellow();
//    }

    public void setStyleValueYellow(String _styleValueYellow) {
        this.rendKeyWordsStyles.setStyleValueYellow(_styleValueYellow);
    }

//    public String getStyleValueCyan() {
//        return rendKeyWordsStyles.getStyleValueCyan();
//    }

    public void setStyleValueCyan(String _styleValueCyan) {
        this.rendKeyWordsStyles.setStyleValueCyan(_styleValueCyan);
    }

//    public String getStyleValueMagenta() {
//        return rendKeyWordsStyles.getStyleValueMagenta();
//    }

    public void setStyleValueMagenta(String _styleValueMagenta) {
        this.rendKeyWordsStyles.setStyleValueMagenta(_styleValueMagenta);
    }

//    public String getStyleValueBlack() {
//        return rendKeyWordsStyles.getStyleValueBlack();
//    }

    public void setStyleValueBlack(String _styleValueBlack) {
        this.rendKeyWordsStyles.setStyleValueBlack(_styleValueBlack);
    }

//    public String getStyleValueGrey() {
//        return rendKeyWordsStyles.getStyleValueGrey();
//    }

    public void setStyleValueGrey(String _styleValueGrey) {
        this.rendKeyWordsStyles.setStyleValueGrey(_styleValueGrey);
    }

//    public String getStyleValueWhite() {
//        return rendKeyWordsStyles.getStyleValueWhite();
//    }

    public void setStyleValueWhite(String _styleValueWhite) {
        this.rendKeyWordsStyles.setStyleValueWhite(_styleValueWhite);
    }

//    public String getStyleUnitPx() {
//        return rendKeyWordsStyles.getStyleUnitPx();
//    }

    public void setStyleUnitPx(String _styleUnitPx) {
        this.rendKeyWordsStyles.setStyleUnitPx(_styleUnitPx);
    }

//    public String getStyleUnitEm() {
//        return rendKeyWordsStyles.getStyleUnitEm();
//    }

    public void setStyleUnitEm(String _styleUnitEm) {
        this.rendKeyWordsStyles.setStyleUnitEm(_styleUnitEm);
    }

//    public String getStyleUnitSolid() {
//        return rendKeyWordsStyles.getStyleUnitSolid();
//    }

    public void setStyleUnitSolid(String _styleUnitSolid) {
        this.rendKeyWordsStyles.setStyleUnitSolid(_styleUnitSolid);
    }
}
