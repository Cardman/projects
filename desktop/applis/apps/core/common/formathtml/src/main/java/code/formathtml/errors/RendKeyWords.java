package code.formathtml.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.stds.ErrorCat;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.LgNamesContent;
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
    private static final String TAG_PARAM="TagParam";
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
    private static final String ATTR_VARVALUE="AttrVarValue";
    private static final String ATTR_GROUPID="AttrGroupId";
    private static final String ATTR_VALIDATOR="AttrValidator";
    private static final String ATTR_FORM="AttrForm";
    private static final String ATTR_FOR="AttrFor";
    private static final String ATTR_PAGE="AttrPage";
    private static final String ATTR_N_I="AttrNi";
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

    private String keyWordFor = "for";
    private String keyWordWhile = "while";
    private String keyWordDo = "do";

    private String keyWordIf = "if";
    private String keyWordElse = "else";
    private String keyWordElseif = "elseif";

    private String keyWordTry = "try";
    private String keyWordFinally = "finally";
    private String keyWordCatch = "catch";

    private String keyWordSwitch = "switch";
    private String keyWordCase = "case";
    private String keyWordDefault = "default";

    private String keyWordReturn = "return";
    private String keyWordThrow = "throw";
    private String keyWordBreak = "break";
    private String keyWordContinue = "continue";

    private String keyWordSet="set";
    private String keyWordImport="import";
    private String keyWordPackage="package";
    private String keyWordClass="class";
    private String keyWordField="field";
    private String keyWordForm="form";
    private String keyWordSubmit="submit";
    private String keyWordImg="img";
    private String keyWordSelect="select";
    private String keyWordMessage="message";
    private String keyWordAnchor="a";
    private String keyWordParam="param";
    private String keyWordInput="input";
    private String keyWordTextarea="textarea";
    private String keyWordSpan="span";
    private String keyWordLink="link";
    private String keyWordStyle="style";
    private String keyWordBody="body";
    private String keyWordHead="head";
    private String keyWordMap="map";
    private String keyWordLi="li";
    private String keyWordOl="ol";
    private String keyWordUl="ul";
    private String keyWordBold="b";
    private String keyWordItalic="i";
    private String keyWordPre="pre";
    private String keyWordHOne="h1";
    private String keyWordHTwo="h2";
    private String keyWordHThree="h3";
    private String keyWordHFour="h4";
    private String keyWordHFive="h5";
    private String keyWordHSix="h6";
    private String keyWordBr="br";
    private String keyWordHr="hr";
    private String keyWordPar="p";
    private String keyWordTable="table";
    private String keyWordCaption="caption";
    private String keyWordTd="td";
    private String keyWordTh="th";
    private String keyWordTr="tr";
    private String keyWordDiv="div";
    private String keyWordOption="option";

    private String attrList="list";
    private String attrMap="map";
    private String attrVar="var";
    private String attrKey="key";
    private String attrValue="value";
    private String attrFrom="from";
    private String attrInit="init";
    private String attrStep="step";
    private String attrTo="to";
    private String attrEq="eq";
    private String attrCondition="condition";
    private String attrClassName="className";
    private String attrKeyClassName="keyClassName";
    private String attrVarClassName="varClassName";
    private String attrIndexClassName="indexClassName";
    private String attrAlias="alias";
    private String attrLabel="label";
    private String attrBean="bean";

    private String attrType="type";
    private String attrChecked="checked";
    private String attrSelected="selected";
    private String attrMultiple="multiple";

    private String valueText="text";
    private String valueCheckbox="checkbox";
    private String valueRadio="radio";
    private String valueNumber="number";
    private String valueRange="range";
    private String valueSubmit="submit";

    private String attrNf="n-f";
    private String attrNa="n-a";
    private String attrNi="n-i";

    private String attrParam = "param";

    private String attrName = "name";
    private String attrHref = "href";
    private String attrCommand = "command";
    private String attrAction = "action";
    private String attrTitle = "title";
    private String attrFor = "for";
    private String attrMessage = "message";
    private String attrQuoted = "quoted";
    private String attrEscaped = "escaped";
    private String attrEscapedAmp = "escapedamp";
    private String attrConvert = "convert";
    private String attrConvertValue = "convertValue";
    private String attrConvertField = "convertField";
    private String attrConvertFieldValue = "convertFieldValue";
    private String attrDefault = "default";
    private String attrVarValue = "varValue";
    private String attrPrepare = "prepare";
    private String attrForm = "form";
    private String attrValueMessage = "valueMessage";
    private String attrId = "id";
    private String attrGroupId = "groupId";
    private String attrValidator = "validator";
    private String attrPage = "page";
    private String attrKeepFields = "keepfields";
    private String attrSrc = "src";
    private String attrRows = "rows";
    private String attrCols = "cols";

    private String attrClass="class";
    private String attrWidth="width";
    private String attrDelay="delay";
    private String attrRel="rel";

    private String valueStyle="stylesheet";

    private String valueLiNb="1";
    private String valueLiMinLet="a";
    private String valueLiMajLet="A";
    private String valueLiMinLat="i";
    private String valueLiMajLat="I";
    private String valueLiCircle="circle";
    private String valueLiDisk="disc";
    private String valueLiRect="rect";
    private String valueLiSquare="square";


    private String styleAttrFontFam="font-family";
    private String styleAttrFontSize="font-size";
    private String styleAttrColor="color";
    private String styleAttrBackground="background";
    private String styleAttrBorder="border";

    private String styleValueRgb="rgb";
    private String styleValueRed="red";
    private String styleValueGreen="green";
    private String styleValueBlue="blue";
    private String styleValueYellow="yellow";
    private String styleValueCyan="cyan";
    private String styleValueMagenta="magenta";
    private String styleValueBlack="black";
    private String styleValueGrey="grey";
    private String styleValueWhite="white";
    private String styleUnitPx="px";
    private String styleUnitEm="em";
    private String styleUnitSolid="solid";
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
        setAttrChecked(LgNamesContent.get(_util, _cust, ATTR_CHECKED));
        setAttrLabel(LgNamesContent.get(_util, _cust, ATTR_LABEL));
        setAttrCondition(LgNamesContent.get(_util, _cust, ATTR_CONDITION));
        setAttrFor(LgNamesContent.get(_util, _cust, ATTR_FOR));
        setAttrTitle(LgNamesContent.get(_util, _cust, ATTR_TITLE));
        setAttrConvert(LgNamesContent.get(_util, _cust, ATTR_CONVERT));
        setAttrEscaped(LgNamesContent.get(_util, _cust, ATTR_ESCAPED));
        setAttrHref(LgNamesContent.get(_util, _cust, ATTR_HREF));
        setAttrCommand(LgNamesContent.get(_util, _cust, ATTR_COMMAND));
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
        setKeyWordParam(LgNamesContent.get(_util, _cust, TAG_PARAM));
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
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _page.addStdError(err_);
                    break;
                }
            }
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
                err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
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
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _page.addStdError(err_);
                continue;
            }
            if (!StringUtil.quickEq(key_,ATTR_PARAM)
                    &&keyWordValue_.startsWith(attrParam)) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
                _page.addStdError(err_);
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)&&c!='-') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
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
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
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
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _page.addStdError(err_);
            }
        }
    }
    public StringMap<String> allTags() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(TAG_SELECT,keyWordSelect);
        keyWords_.addEntry(TAG_TEXTAREA,keyWordTextarea);
        keyWords_.addEntry(TAG_ELSE,keyWordElse);
        keyWords_.addEntry(TAG_CASE,keyWordCase);
        keyWords_.addEntry(TAG_SWITCH,keyWordSwitch);
        keyWords_.addEntry(TAG_DO,keyWordDo);
        keyWords_.addEntry(TAG_TRY,keyWordTry);
        keyWords_.addEntry(TAG_WHILE,keyWordWhile);
        keyWords_.addEntry(TAG_ELSEIF,keyWordElseif);
        keyWords_.addEntry(TAG_BREAK,keyWordBreak);
        keyWords_.addEntry(TAG_THROW,keyWordThrow);
        keyWords_.addEntry(TAG_CATCH,keyWordCatch);
        keyWords_.addEntry(TAG_CAPTION,keyWordCaption);
        keyWords_.addEntry(TAG_MESSAGE,keyWordMessage);
        keyWords_.addEntry(TAG_OL,keyWordOl);
        keyWords_.addEntry(TAG_HEAD,keyWordHead);
        keyWords_.addEntry(TAG_UL,keyWordUl);
        keyWords_.addEntry(TAG_IMPORT,keyWordImport);
        keyWords_.addEntry(TAG_B,keyWordBold);
        keyWords_.addEntry(TAG_FIELD,keyWordField);
        keyWords_.addEntry(TAG_SET,keyWordSet);
        keyWords_.addEntry(TAG_FORM,keyWordForm);
        keyWords_.addEntry(TAG_SPAN,keyWordSpan);
        keyWords_.addEntry(TAG_STYLE,keyWordStyle);
        keyWords_.addEntry(TAG_BODY,keyWordBody);
        keyWords_.addEntry(TAG_MAP,keyWordMap);
        keyWords_.addEntry(TAG_SUBMIT,keyWordSubmit);
        keyWords_.addEntry(TAG_IMG,keyWordImg);
        keyWords_.addEntry(TAG_PARAM,keyWordParam);
        keyWords_.addEntry(TAG_INPUT,keyWordInput);
        keyWords_.addEntry(TAG_A,keyWordAnchor);
        keyWords_.addEntry(TAG_LINK,keyWordLink);
        keyWords_.addEntry(TAG_LI,keyWordLi);
        keyWords_.addEntry(TAG_H6,keyWordHSix);
        keyWords_.addEntry(TAG_BR,keyWordBr);
        keyWords_.addEntry(TAG_H2,keyWordHTwo);
        keyWords_.addEntry(TAG_H4,keyWordHFour);
        keyWords_.addEntry(TAG_TR,keyWordTr);
        keyWords_.addEntry(TAG_TD,keyWordTd);
        keyWords_.addEntry(TAG_H1,keyWordHOne);
        keyWords_.addEntry(TAG_DIV,keyWordDiv);
        keyWords_.addEntry(TAG_OPTION,keyWordOption);
        keyWords_.addEntry(TAG_TH,keyWordTh);
        keyWords_.addEntry(TAG_TABLE,keyWordTable);
        keyWords_.addEntry(TAG_HR,keyWordHr);
        keyWords_.addEntry(TAG_P,keyWordPar);
        keyWords_.addEntry(TAG_I,keyWordItalic);
        keyWords_.addEntry(TAG_H3,keyWordHThree);
        keyWords_.addEntry(TAG_H5,keyWordHFive);
        keyWords_.addEntry(TAG_PRE,keyWordPre);
        keyWords_.addEntry(TAG_FINALLY,keyWordFinally);
        keyWords_.addEntry(TAG_DEFAULT,keyWordDefault);
        keyWords_.addEntry(TAG_PACKAGE,keyWordPackage);
        keyWords_.addEntry(TAG_CONTINUE,keyWordContinue);
        keyWords_.addEntry(TAG_CLASS,keyWordClass);
        keyWords_.addEntry(TAG_FOR,keyWordFor);
        keyWords_.addEntry(TAG_IF,keyWordIf);
        keyWords_.addEntry(TAG_RETURN,keyWordReturn);
        return keyWords_;
    }
    public StringMap<String> allAttrs() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(ATTR_CLASSNAME,attrClassName);
        keyWords_.addEntry(ATTR_MULTIPLE,attrMultiple);
        keyWords_.addEntry(ATTR_TYPE,attrType);
        keyWords_.addEntry(ATTR_INDEXCLASSNAME,attrIndexClassName);
        keyWords_.addEntry(ATTR_CONVERTFIELD,attrConvertField);
        keyWords_.addEntry(ATTR_CONVERTFIELDVALUE,attrConvertFieldValue);
        keyWords_.addEntry(ATTR_VALUEMESSAGE,attrValueMessage);
        keyWords_.addEntry(ATTR_ESCAPEDAMP,attrEscapedAmp);
        keyWords_.addEntry(ATTR_KEYCLASSNAME,attrKeyClassName);
        keyWords_.addEntry(ATTR_VARCLASSNAME,attrVarClassName);
        keyWords_.addEntry(ATTR_CONVERTVALUE,attrConvertValue);
        keyWords_.addEntry(ATTR_KEEPFIELDS,attrKeepFields);
        keyWords_.addEntry(ATTR_TITLE,attrTitle);
        keyWords_.addEntry(ATTR_FOR,attrFor);
        keyWords_.addEntry(ATTR_FROM,attrFrom);
        keyWords_.addEntry(ATTR_SELECTED,attrSelected);
        keyWords_.addEntry(ATTR_N_A,attrNa);
        keyWords_.addEntry(ATTR_TO,attrTo);
        keyWords_.addEntry(ATTR_STEP,attrStep);
        keyWords_.addEntry(ATTR_INIT,attrInit);
        keyWords_.addEntry(ATTR_VALUE,attrValue);
        keyWords_.addEntry(ATTR_COMMAND,attrCommand);
        keyWords_.addEntry(ATTR_CONDITION,attrCondition);
        keyWords_.addEntry(ATTR_HREF,attrHref);
        keyWords_.addEntry(ATTR_MESSAGE,attrMessage);
        keyWords_.addEntry(ATTR_CHECKED,attrChecked);
        keyWords_.addEntry(ATTR_ACTION,attrAction);
        keyWords_.addEntry(ATTR_EQ,attrEq);
        keyWords_.addEntry(ATTR_KEY,attrKey);
        keyWords_.addEntry(ATTR_LABEL,attrLabel);
        keyWords_.addEntry(ATTR_BEAN,attrBean);
        keyWords_.addEntry(ATTR_N_I,attrNi);
        keyWords_.addEntry(ATTR_N_F,attrNf);
        keyWords_.addEntry(ATTR_PARAM,attrParam);
        keyWords_.addEntry(ATTR_ALIAS,attrAlias);
        keyWords_.addEntry(ATTR_QUOTED,attrQuoted);
        keyWords_.addEntry(ATTR_NAME,attrName);
        keyWords_.addEntry(ATTR_MAP,attrMap);
        keyWords_.addEntry(ATTR_VAR,attrVar);
        keyWords_.addEntry(ATTR_LIST,attrList);
        keyWords_.addEntry(ATTR_PREPARE,attrPrepare);
        keyWords_.addEntry(ATTR_FORM,attrForm);
        keyWords_.addEntry(ATTR_VARVALUE,attrVarValue);
        keyWords_.addEntry(ATTR_DELAY,attrDelay);
        keyWords_.addEntry(ATTR_ESCAPED,attrEscaped);
        keyWords_.addEntry(ATTR_PAGE,attrPage);
        keyWords_.addEntry(ATTR_WIDTH,attrWidth);
        keyWords_.addEntry(ATTR_SRC,attrSrc);
        keyWords_.addEntry(ATTR_VALIDATOR,attrValidator);
        keyWords_.addEntry(ATTR_GROUPID,attrGroupId);
        keyWords_.addEntry(ATTR_CONVERT,attrConvert);
        keyWords_.addEntry(ATTR_ROWS,attrRows);
        keyWords_.addEntry(ATTR_ID,attrId);
        keyWords_.addEntry(ATTR_COLS,attrCols);
        keyWords_.addEntry(ATTR_DEFAULT,attrDefault);
        keyWords_.addEntry(ATTR_CLASS,attrClass);
        keyWords_.addEntry(ATTR_REL,attrRel);
        return keyWords_;
    }

    public StringMap<String> allValues() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(VALUE_RANGE,valueRange.trim());
        keyWords_.addEntry(VALUE_TEXT,valueText.trim());
        keyWords_.addEntry(VALUE_CHECKBOX,valueCheckbox.trim());
        keyWords_.addEntry(VALUE_NUMBER,valueNumber.trim());
        keyWords_.addEntry(VALUE_RADIO,valueRadio.trim());
        keyWords_.addEntry(VALUE_SUBMIT,valueSubmit.trim());
        keyWords_.addEntry(VALUE_STYLE,valueStyle.trim());
        keyWords_.addEntry(VALUE_LIDISK,valueLiDisk.trim());
        keyWords_.addEntry(VALUE_LIMINLET,valueLiMinLet.trim());
        keyWords_.addEntry(VALUE_LISQUARE,valueLiSquare.trim());
        keyWords_.addEntry(VALUE_LINB,valueLiNb.trim());
        keyWords_.addEntry(VALUE_LIMAJLET,valueLiMajLet.trim());
        keyWords_.addEntry(VALUE_LIMINLAT,valueLiMinLat.trim());
        keyWords_.addEntry(VALUE_LIMAJLAT,valueLiMajLat.trim());
        keyWords_.addEntry(VALUE_LICIRCLE,valueLiCircle.trim());
        keyWords_.addEntry(VALUE_LIRECT,valueLiRect.trim());
        return keyWords_;
    }
    public StringMap<String> allStyleAttrs(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_ATTR_BORDER,styleAttrBorder);
        keyWords_.addEntry(STYLE_ATTR_FONTFAM,styleAttrFontFam);
        keyWords_.addEntry(STYLE_ATTR_FONTSIZE,styleAttrFontSize);
        keyWords_.addEntry(STYLE_ATTR_COLOR,styleAttrColor);
        keyWords_.addEntry(STYLE_ATTR_BACKGROUND,styleAttrBackground);
        return keyWords_;
    }
    public StringMap<String> allStyleValues(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_VALUE_GREY,styleValueGrey.trim());
        keyWords_.addEntry(STYLE_VALUE_MAGENTA,styleValueMagenta.trim());
        keyWords_.addEntry(STYLE_VALUE_GREEN,styleValueGreen.trim());
        keyWords_.addEntry(STYLE_VALUE_CYAN,styleValueCyan.trim());
        keyWords_.addEntry(STYLE_VALUE_YELLOW,styleValueYellow.trim());
        keyWords_.addEntry(STYLE_VALUE_WHITE,styleValueWhite.trim());
        keyWords_.addEntry(STYLE_VALUE_BLUE,styleValueBlue.trim());
        keyWords_.addEntry(STYLE_VALUE_BLACK,styleValueBlack.trim());
        keyWords_.addEntry(STYLE_VALUE_RGB,styleValueRgb.trim());
        keyWords_.addEntry(STYLE_VALUE_RED,styleValueRed.trim());
        return keyWords_;
    }
    public StringMap<String> allStyleUnits(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_UNIT_SOLID,styleUnitSolid);
        keyWords_.addEntry(STYLE_UNIT_PX,styleUnitPx);
        keyWords_.addEntry(STYLE_UNIT_EM,styleUnitEm);
        return keyWords_;
    }
    public String getKeyWordFor() {
        return keyWordFor;
    }
    public void setKeyWordFor(String _keyWordFor) {
        keyWordFor = _keyWordFor;
    }
    public String getKeyWordWhile() {
        return keyWordWhile;
    }
    public void setKeyWordWhile(String _keyWordWhile) {
        keyWordWhile = _keyWordWhile;
    }
    public String getKeyWordDo() {
        return keyWordDo;
    }
    public void setKeyWordDo(String _keyWordDo) {
        keyWordDo = _keyWordDo;
    }
    public String getKeyWordIf() {
        return keyWordIf;
    }
    public void setKeyWordIf(String _keyWordIf) {
        keyWordIf = _keyWordIf;
    }
    public String getKeyWordElse() {
        return keyWordElse;
    }
    public void setKeyWordElse(String _keyWordElse) {
        keyWordElse = _keyWordElse;
    }
    public String getKeyWordElseif() {
        return keyWordElseif;
    }
    public void setKeyWordElseif(String _keyWordElseif) {
        keyWordElseif = _keyWordElseif;
    }
    public String getKeyWordTry() {
        return keyWordTry;
    }
    public void setKeyWordTry(String _keyWordTry) {
        keyWordTry = _keyWordTry;
    }
    public String getKeyWordFinally() {
        return keyWordFinally;
    }
    public void setKeyWordFinally(String _keyWordFinally) {
        keyWordFinally = _keyWordFinally;
    }
    public String getKeyWordCatch() {
        return keyWordCatch;
    }
    public void setKeyWordCatch(String _keyWordCatch) {
        keyWordCatch = _keyWordCatch;
    }
    public String getKeyWordSwitch() {
        return keyWordSwitch;
    }
    public void setKeyWordSwitch(String _keyWordSwitch) {
        keyWordSwitch = _keyWordSwitch;
    }
    public String getKeyWordCase() {
        return keyWordCase;
    }
    public void setKeyWordCase(String _keyWordCase) {
        keyWordCase = _keyWordCase;
    }
    public String getKeyWordDefault() {
        return keyWordDefault;
    }
    public void setKeyWordDefault(String _keyWordDefault) {
        keyWordDefault = _keyWordDefault;
    }

    public String getKeyWordReturn() {
        return keyWordReturn;
    }
    public void setKeyWordReturn(String _keyWordReturn) {
        keyWordReturn = _keyWordReturn;
    }
    public String getKeyWordThrow() {
        return keyWordThrow;
    }
    public void setKeyWordThrow(String _keyWordThrow) {
        keyWordThrow = _keyWordThrow;
    }
    public String getKeyWordBreak() {
        return keyWordBreak;
    }
    public void setKeyWordBreak(String _keyWordBreak) {
        keyWordBreak = _keyWordBreak;
    }
    public String getKeyWordContinue() {
        return keyWordContinue;
    }
    public void setKeyWordContinue(String _keyWordContinue) {
        keyWordContinue = _keyWordContinue;
    }

    public String getKeyWordSet() {
        return keyWordSet;
    }

    public void setKeyWordSet(String _keyWordSet) {
        this.keyWordSet = _keyWordSet;
    }

    public String getKeyWordImport() {
        return keyWordImport;
    }

    public void setKeyWordImport(String _keyWordImport) {
        this.keyWordImport = _keyWordImport;
    }

    public String getKeyWordPackage() {
        return keyWordPackage;
    }

    public void setKeyWordPackage(String _keyWordPackage) {
        this.keyWordPackage = _keyWordPackage;
    }

    public String getKeyWordClass() {
        return keyWordClass;
    }

    public void setKeyWordClass(String _keyWordClass) {
        this.keyWordClass = _keyWordClass;
    }

    public String getKeyWordField() {
        return keyWordField;
    }

    public void setKeyWordField(String _keyWordField) {
        this.keyWordField = _keyWordField;
    }

    public String getKeyWordForm() {
        return keyWordForm;
    }

    public void setKeyWordForm(String _keyWordForm) {
        this.keyWordForm = _keyWordForm;
    }

    public String getKeyWordSubmit() {
        return keyWordSubmit;
    }

    public void setKeyWordSubmit(String _keyWordSubmit) {
        this.keyWordSubmit = _keyWordSubmit;
    }

    public String getKeyWordImg() {
        return keyWordImg;
    }

    public void setKeyWordImg(String _keyWordImg) {
        this.keyWordImg = _keyWordImg;
    }

    public String getKeyWordSelect() {
        return keyWordSelect;
    }

    public void setKeyWordSelect(String _keyWordSelect) {
        this.keyWordSelect = _keyWordSelect;
    }

    public String getKeyWordMessage() {
        return keyWordMessage;
    }

    public void setKeyWordMessage(String _keyWordMessage) {
        this.keyWordMessage = _keyWordMessage;
    }

    public String getKeyWordAnchor() {
        return keyWordAnchor;
    }

    public void setKeyWordAnchor(String _keyWordAnchor) {
        this.keyWordAnchor = _keyWordAnchor;
    }

    public String getKeyWordParam() {
        return keyWordParam;
    }

    public void setKeyWordParam(String _keyWordParam) {
        this.keyWordParam = _keyWordParam;
    }

    public String getKeyWordInput() {
        return keyWordInput;
    }

    public void setKeyWordInput(String _keyWordInput) {
        this.keyWordInput = _keyWordInput;
    }

    public String getKeyWordTextarea() {
        return keyWordTextarea;
    }

    public void setKeyWordTextarea(String _keyWordTextarea) {
        this.keyWordTextarea = _keyWordTextarea;
    }

    public String getKeyWordSpan() {
        return keyWordSpan;
    }

    public void setKeyWordSpan(String _keyWordSpan) {
        this.keyWordSpan = _keyWordSpan;
    }

    public String getKeyWordLink() {
        return keyWordLink;
    }

    public void setKeyWordLink(String _keyWordLink) {
        this.keyWordLink = _keyWordLink;
    }

    public String getKeyWordStyle() {
        return keyWordStyle;
    }

    public void setKeyWordStyle(String _keyWordStyle) {
        this.keyWordStyle = _keyWordStyle;
    }

    public String getKeyWordBody() {
        return keyWordBody;
    }

    public void setKeyWordBody(String _keyWordBody) {
        this.keyWordBody = _keyWordBody;
    }

    public String getKeyWordHead() {
        return keyWordHead;
    }

    public void setKeyWordHead(String _keyWordHead) {
        this.keyWordHead = _keyWordHead;
    }

    public String getKeyWordMap() {
        return keyWordMap;
    }

    public void setKeyWordMap(String _keyWordMap) {
        this.keyWordMap = _keyWordMap;
    }

    public String getKeyWordLi() {
        return keyWordLi;
    }

    public void setKeyWordLi(String _keyWordLi) {
        this.keyWordLi = _keyWordLi;
    }

    public String getKeyWordOl() {
        return keyWordOl;
    }

    public void setKeyWordOl(String _keyWordOl) {
        this.keyWordOl = _keyWordOl;
    }

    public String getKeyWordUl() {
        return keyWordUl;
    }

    public void setKeyWordUl(String _keyWordUl) {
        this.keyWordUl = _keyWordUl;
    }

    public String getKeyWordBold() {
        return keyWordBold;
    }

    public void setKeyWordBold(String _keyWordBold) {
        this.keyWordBold = _keyWordBold;
    }

    public String getKeyWordItalic() {
        return keyWordItalic;
    }

    public void setKeyWordItalic(String _keyWordItalic) {
        this.keyWordItalic = _keyWordItalic;
    }

    public String getKeyWordPre() {
        return keyWordPre;
    }

    public void setKeyWordPre(String _keyWordPre) {
        this.keyWordPre = _keyWordPre;
    }

    public String getKeyWordHOne() {
        return keyWordHOne;
    }

    public void setKeyWordHOne(String _keyWordHOne) {
        this.keyWordHOne = _keyWordHOne;
    }

    public String getKeyWordHTwo() {
        return keyWordHTwo;
    }

    public void setKeyWordHTwo(String _keyWordHTwo) {
        this.keyWordHTwo = _keyWordHTwo;
    }

    public String getKeyWordHThree() {
        return keyWordHThree;
    }

    public void setKeyWordHThree(String _keyWordHThree) {
        this.keyWordHThree = _keyWordHThree;
    }

    public String getKeyWordHFour() {
        return keyWordHFour;
    }

    public void setKeyWordHFour(String _keyWordHFour) {
        this.keyWordHFour = _keyWordHFour;
    }

    public String getKeyWordHFive() {
        return keyWordHFive;
    }

    public void setKeyWordHFive(String _keyWordHFive) {
        this.keyWordHFive = _keyWordHFive;
    }

    public String getKeyWordHSix() {
        return keyWordHSix;
    }

    public void setKeyWordHSix(String _keyWordHSix) {
        this.keyWordHSix = _keyWordHSix;
    }

    public String getKeyWordBr() {
        return keyWordBr;
    }

    public void setKeyWordBr(String _keyWordBr) {
        this.keyWordBr = _keyWordBr;
    }

    public String getKeyWordHr() {
        return keyWordHr;
    }

    public void setKeyWordHr(String _keyWordHr) {
        this.keyWordHr = _keyWordHr;
    }

    public String getKeyWordPar() {
        return keyWordPar;
    }

    public void setKeyWordPar(String _keyWordPar) {
        this.keyWordPar = _keyWordPar;
    }

    public String getKeyWordTable() {
        return keyWordTable;
    }

    public void setKeyWordTable(String _keyWordTable) {
        this.keyWordTable = _keyWordTable;
    }

    public String getKeyWordCaption() {
        return keyWordCaption;
    }

    public void setKeyWordCaption(String _keyWordCaption) {
        this.keyWordCaption = _keyWordCaption;
    }

    public String getKeyWordTd() {
        return keyWordTd;
    }

    public void setKeyWordTd(String _keyWordTd) {
        this.keyWordTd = _keyWordTd;
    }

    public String getKeyWordTh() {
        return keyWordTh;
    }

    public void setKeyWordTh(String _keyWordTh) {
        this.keyWordTh = _keyWordTh;
    }

    public String getKeyWordTr() {
        return keyWordTr;
    }

    public void setKeyWordTr(String _keyWordTr) {
        this.keyWordTr = _keyWordTr;
    }

    public String getKeyWordDiv() {
        return keyWordDiv;
    }

    public void setKeyWordDiv(String _keyWordDiv) {
        this.keyWordDiv = _keyWordDiv;
    }

    public String getKeyWordOption() {
        return keyWordOption;
    }

    public void setKeyWordOption(String _keyWordOptions) {
        this.keyWordOption = _keyWordOptions;
    }

    public String getAttrList() {
        return attrList;
    }

    public void setAttrList(String _attrList) {
        this.attrList = _attrList;
    }

    public String getAttrMap() {
        return attrMap;
    }

    public void setAttrMap(String _attrMap) {
        this.attrMap = _attrMap;
    }

    public String getAttrVar() {
        return attrVar;
    }

    public void setAttrVar(String _attrVar) {
        this.attrVar = _attrVar;
    }

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String _attrKey) {
        this.attrKey = _attrKey;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String _attrValue) {
        this.attrValue = _attrValue;
    }

    public String getAttrFrom() {
        return attrFrom;
    }

    public void setAttrFrom(String _attrFrom) {
        this.attrFrom = _attrFrom;
    }

    public String getAttrInit() {
        return attrInit;
    }

    public void setAttrInit(String _attrInit) {
        this.attrInit = _attrInit;
    }

    public String getAttrStep() {
        return attrStep;
    }

    public void setAttrStep(String _attrStep) {
        this.attrStep = _attrStep;
    }

    public String getAttrTo() {
        return attrTo;
    }

    public void setAttrTo(String _attrTo) {
        this.attrTo = _attrTo;
    }

    public String getAttrEq() {
        return attrEq;
    }

    public void setAttrEq(String _attrEq) {
        this.attrEq = _attrEq;
    }

    public String getAttrCondition() {
        return attrCondition;
    }

    public void setAttrCondition(String _attrCondition) {
        this.attrCondition = _attrCondition;
    }

    public String getAttrClassName() {
        return attrClassName;
    }

    public void setAttrClassName(String _attrClassName) {
        this.attrClassName = _attrClassName;
    }

    public String getAttrKeyClassName() {
        return attrKeyClassName;
    }

    public void setAttrKeyClassName(String _attrKeyClassName) {
        this.attrKeyClassName = _attrKeyClassName;
    }

    public String getAttrVarClassName() {
        return attrVarClassName;
    }

    public void setAttrVarClassName(String _attrVarClassName) {
        this.attrVarClassName = _attrVarClassName;
    }

    public String getAttrIndexClassName() {
        return attrIndexClassName;
    }

    public void setAttrIndexClassName(String _attrIndexClassName) {
        this.attrIndexClassName = _attrIndexClassName;
    }

    public String getAttrAlias() {
        return attrAlias;
    }

    public void setAttrAlias(String _attrAlias) {
        this.attrAlias = _attrAlias;
    }

    public String getAttrLabel() {
        return attrLabel;
    }

    public void setAttrLabel(String _attrLabel) {
        this.attrLabel = _attrLabel;
    }

    public String getAttrBean() {
        return attrBean;
    }

    public void setAttrBean(String _attrBean) {
        this.attrBean = _attrBean;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String _attrType) {
        this.attrType = _attrType;
    }

    public String getAttrChecked() {
        return attrChecked;
    }

    public void setAttrChecked(String _attrChecked) {
        this.attrChecked = _attrChecked;
    }

    public String getAttrSelected() {
        return attrSelected;
    }

    public void setAttrSelected(String _attrSelected) {
        this.attrSelected = _attrSelected;
    }

    public String getAttrMultiple() {
        return attrMultiple;
    }

    public void setAttrMultiple(String _attrMultiple) {
        this.attrMultiple = _attrMultiple;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String _valueText) {
        this.valueText = _valueText;
    }

    public String getValueCheckbox() {
        return valueCheckbox;
    }

    public void setValueCheckbox(String _valueCheckbox) {
        this.valueCheckbox = _valueCheckbox;
    }

    public String getValueRadio() {
        return valueRadio;
    }

    public void setValueRadio(String _valueRadio) {
        this.valueRadio = _valueRadio;
    }

    public String getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(String _valueNumber) {
        this.valueNumber = _valueNumber;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String _valueRange) {
        this.valueRange = _valueRange;
    }

    public String getValueSubmit() {
        return valueSubmit;
    }

    public void setValueSubmit(String _valueSubmit) {
        this.valueSubmit = _valueSubmit;
    }

    public String getAttrNf() {
        return attrNf;
    }

    public void setAttrNf(String _attrNf) {
        this.attrNf = _attrNf;
    }

    public String getAttrNa() {
        return attrNa;
    }

    public void setAttrNa(String _attrNa) {
        this.attrNa = _attrNa;
    }

    public String getAttrNi() {
        return attrNi;
    }

    public void setAttrNi(String _attrNi) {
        this.attrNi = _attrNi;
    }

    public String getAttrParam() {
        return attrParam;
    }

    public void setAttrParam(String _attrParam) {
        this.attrParam = _attrParam;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String _attrName) {
        this.attrName = _attrName;
    }

    public String getAttrHref() {
        return attrHref;
    }

    public void setAttrHref(String _attrHref) {
        this.attrHref = _attrHref;
    }

    public String getAttrCommand() {
        return attrCommand;
    }

    public void setAttrCommand(String _attrCommand) {
        this.attrCommand = _attrCommand;
    }

    public String getAttrAction() {
        return attrAction;
    }

    public void setAttrAction(String _attrAction) {
        this.attrAction = _attrAction;
    }

    public String getAttrTitle() {
        return attrTitle;
    }

    public void setAttrTitle(String _attrTitle) {
        this.attrTitle = _attrTitle;
    }

    public String getAttrFor() {
        return attrFor;
    }

    public void setAttrFor(String _attrFor) {
        this.attrFor = _attrFor;
    }

    public String getAttrMessage() {
        return attrMessage;
    }

    public void setAttrMessage(String _attrMessage) {
        this.attrMessage = _attrMessage;
    }

    public String getAttrQuoted() {
        return attrQuoted;
    }

    public void setAttrQuoted(String _attrQuoted) {
        this.attrQuoted = _attrQuoted;
    }

    public String getAttrEscaped() {
        return attrEscaped;
    }

    public void setAttrEscaped(String _attrEscaped) {
        this.attrEscaped = _attrEscaped;
    }

    public String getAttrEscapedAmp() {
        return attrEscapedAmp;
    }

    public void setAttrEscapedAmp(String _attrEscapedAmp) {
        this.attrEscapedAmp = _attrEscapedAmp;
    }

    public String getAttrConvert() {
        return attrConvert;
    }

    public void setAttrConvert(String _attrConvert) {
        this.attrConvert = _attrConvert;
    }

    public String getAttrConvertValue() {
        return attrConvertValue;
    }

    public void setAttrConvertValue(String _attrConvertValue) {
        this.attrConvertValue = _attrConvertValue;
    }

    public String getAttrConvertField() {
        return attrConvertField;
    }

    public void setAttrConvertField(String _attrConvertField) {
        this.attrConvertField = _attrConvertField;
    }

    public String getAttrConvertFieldValue() {
        return attrConvertFieldValue;
    }

    public void setAttrConvertFieldValue(String _attrConvertFieldValue) {
        this.attrConvertFieldValue = _attrConvertFieldValue;
    }

    public String getAttrDefault() {
        return attrDefault;
    }

    public void setAttrDefault(String _attrDefault) {
        this.attrDefault = _attrDefault;
    }

    public String getAttrVarValue() {
        return attrVarValue;
    }

    public void setAttrVarValue(String _attrVarValue) {
        this.attrVarValue = _attrVarValue;
    }

    public String getAttrPrepare() {
        return attrPrepare;
    }

    public void setAttrPrepare(String _attrPrepare) {
        this.attrPrepare = _attrPrepare;
    }

    public String getAttrForm() {
        return attrForm;
    }

    public void setAttrForm(String _attrForm) {
        this.attrForm = _attrForm;
    }

    public String getAttrValueMessage() {
        return attrValueMessage;
    }

    public void setAttrValueMessage(String _attrValueMessage) {
        this.attrValueMessage = _attrValueMessage;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String _attrId) {
        this.attrId = _attrId;
    }

    public String getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(String _attrGroupId) {
        this.attrGroupId = _attrGroupId;
    }

    public String getAttrValidator() {
        return attrValidator;
    }

    public void setAttrValidator(String _attrValidator) {
        this.attrValidator = _attrValidator;
    }

    public String getAttrPage() {
        return attrPage;
    }

    public void setAttrPage(String _attrPage) {
        this.attrPage = _attrPage;
    }

    public String getAttrKeepFields() {
        return attrKeepFields;
    }

    public void setAttrKeepFields(String _attrKeepFields) {
        this.attrKeepFields = _attrKeepFields;
    }

    public String getAttrSrc() {
        return attrSrc;
    }

    public void setAttrSrc(String _attrSrc) {
        this.attrSrc = _attrSrc;
    }

    public String getAttrRows() {
        return attrRows;
    }

    public void setAttrRows(String _attrRows) {
        this.attrRows = _attrRows;
    }

    public String getAttrCols() {
        return attrCols;
    }

    public void setAttrCols(String _attrCols) {
        this.attrCols = _attrCols;
    }

    public String getAttrClass() {
        return attrClass;
    }

    public void setAttrClass(String _attrClass) {
        this.attrClass = _attrClass;
    }

    public String getAttrWidth() {
        return attrWidth;
    }

    public void setAttrWidth(String _attrWidth) {
        this.attrWidth = _attrWidth;
    }

    public String getAttrDelay() {
        return attrDelay;
    }

    public void setAttrDelay(String _attrDelay) {
        this.attrDelay = _attrDelay;
    }

    public String getAttrRel() {
        return attrRel;
    }

    public void setAttrRel(String _attrRel) {
        this.attrRel = _attrRel;
    }

    public String getValueStyle() {
        return valueStyle;
    }

    public void setValueStyle(String _valueStyle) {
        this.valueStyle = _valueStyle;
    }

    public String getValueLiNb() {
        return valueLiNb;
    }

    public void setValueLiNb(String _valueLiNb) {
        this.valueLiNb = _valueLiNb;
    }

    public String getValueLiMinLet() {
        return valueLiMinLet;
    }

    public void setValueLiMinLet(String _valueLiMinLet) {
        this.valueLiMinLet = _valueLiMinLet;
    }

    public String getValueLiMajLet() {
        return valueLiMajLet;
    }

    public void setValueLiMajLet(String _valueLiMajLet) {
        this.valueLiMajLet = _valueLiMajLet;
    }

    public String getValueLiMinLat() {
        return valueLiMinLat;
    }

    public void setValueLiMinLat(String _valueLiMinLat) {
        this.valueLiMinLat = _valueLiMinLat;
    }

    public String getValueLiMajLat() {
        return valueLiMajLat;
    }

    public void setValueLiMajLat(String _valueLiMajLat) {
        this.valueLiMajLat = _valueLiMajLat;
    }

    public String getValueLiCircle() {
        return valueLiCircle;
    }

    public void setValueLiCircle(String _valueLiCircle) {
        this.valueLiCircle = _valueLiCircle;
    }

    public String getValueLiDisk() {
        return valueLiDisk;
    }

    public void setValueLiDisk(String _valueLiDisk) {
        this.valueLiDisk = _valueLiDisk;
    }

    public String getValueLiRect() {
        return valueLiRect;
    }

    public void setValueLiRect(String _valueLiRect) {
        this.valueLiRect = _valueLiRect;
    }

    public String getValueLiSquare() {
        return valueLiSquare;
    }

    public void setValueLiSquare(String _valueLiSquare) {
        this.valueLiSquare = _valueLiSquare;
    }

    public String getStyleAttrFontFam() {
        return styleAttrFontFam;
    }

    public void setStyleAttrFontFam(String _styleAttrFontFam) {
        this.styleAttrFontFam = _styleAttrFontFam;
    }

    public String getStyleAttrFontSize() {
        return styleAttrFontSize;
    }

    public void setStyleAttrFontSize(String _styleAttrFontSize) {
        this.styleAttrFontSize = _styleAttrFontSize;
    }

    public String getStyleAttrColor() {
        return styleAttrColor;
    }

    public void setStyleAttrColor(String _styleAttrColor) {
        this.styleAttrColor = _styleAttrColor;
    }

    public String getStyleAttrBackground() {
        return styleAttrBackground;
    }

    public void setStyleAttrBackground(String _styleAttrBackground) {
        this.styleAttrBackground = _styleAttrBackground;
    }

    public String getStyleAttrBorder() {
        return styleAttrBorder;
    }

    public void setStyleAttrBorder(String _styleAttrBorder) {
        this.styleAttrBorder = _styleAttrBorder;
    }

    public String getStyleValueRgb() {
        return styleValueRgb;
    }

    public void setStyleValueRgb(String _styleValueRgb) {
        this.styleValueRgb = _styleValueRgb;
    }

    public String getStyleValueRed() {
        return styleValueRed;
    }

    public void setStyleValueRed(String _styleValueRed) {
        this.styleValueRed = _styleValueRed;
    }

    public String getStyleValueGreen() {
        return styleValueGreen;
    }

    public void setStyleValueGreen(String _styleValueGreen) {
        this.styleValueGreen = _styleValueGreen;
    }

    public String getStyleValueBlue() {
        return styleValueBlue;
    }

    public void setStyleValueBlue(String _styleValueBlue) {
        this.styleValueBlue = _styleValueBlue;
    }

    public String getStyleValueYellow() {
        return styleValueYellow;
    }

    public void setStyleValueYellow(String _styleValueYellow) {
        this.styleValueYellow = _styleValueYellow;
    }

    public String getStyleValueCyan() {
        return styleValueCyan;
    }

    public void setStyleValueCyan(String _styleValueCyan) {
        this.styleValueCyan = _styleValueCyan;
    }

    public String getStyleValueMagenta() {
        return styleValueMagenta;
    }

    public void setStyleValueMagenta(String _styleValueMagenta) {
        this.styleValueMagenta = _styleValueMagenta;
    }

    public String getStyleValueBlack() {
        return styleValueBlack;
    }

    public void setStyleValueBlack(String _styleValueBlack) {
        this.styleValueBlack = _styleValueBlack;
    }

    public String getStyleValueGrey() {
        return styleValueGrey;
    }

    public void setStyleValueGrey(String _styleValueGrey) {
        this.styleValueGrey = _styleValueGrey;
    }

    public String getStyleValueWhite() {
        return styleValueWhite;
    }

    public void setStyleValueWhite(String _styleValueWhite) {
        this.styleValueWhite = _styleValueWhite;
    }

    public String getStyleUnitPx() {
        return styleUnitPx;
    }

    public void setStyleUnitPx(String _styleUnitPx) {
        this.styleUnitPx = _styleUnitPx;
    }

    public String getStyleUnitEm() {
        return styleUnitEm;
    }

    public void setStyleUnitEm(String _styleUnitEm) {
        this.styleUnitEm = _styleUnitEm;
    }

    public String getStyleUnitSolid() {
        return styleUnitSolid;
    }

    public void setStyleUnitSolid(String _styleUnitSolid) {
        this.styleUnitSolid = _styleUnitSolid;
    }
}
