package code.formathtml.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.LgNamesContent;
import code.images.BaseSixtyFourUtil;
import code.maths.litteralcom.MathExpUtil;
import code.sml.*;
import code.sml.util.MessagesTranslations;
import code.sml.util.TranslationsFile;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class RendKeyWords {
    public static final String TAGS_FILE="tags";
    public static final String ATTRS_FILE="attrs";
    public static final String VALUES_FILE="values";
    public static final String STYLE_VALUES_FILE="st_values";
    public static final String STYLE_UNITS_FILE="st_units";
    public static final String STYLE_ATTRS_FILE="st_attrs";
    public static final String STYLE_DEF_FILE="st_defs";
    public static final String TAG_FOR="0";
    public static final String TAG_WHILE="1";
    public static final String TAG_DO="2";
    public static final String TAG_IF="3";
    public static final String TAG_ELSE="4";
    public static final String TAG_ELSEIF="5";
    public static final String TAG_TRY="6";
    public static final String TAG_FINALLY="7";
    public static final String TAG_CATCH="8";
    public static final String TAG_SWITCH="9";
    public static final String TAG_CASE="10";
    public static final String TAG_DEFAULT="11";
    public static final String TAG_RETURN="12";
    public static final String TAG_THROW="13";
    public static final String TAG_BREAK="14";
    public static final String TAG_CONTINUE="15";
    public static final String TAG_SET="16";
    public static final String TAG_IMPORT="17";
    public static final String TAG_PACKAGE="18";
    public static final String TAG_CLASS="19";
    public static final String TAG_FIELD="20";
    public static final String TAG_FORM="21";
    public static final String TAG_SUBMIT="22";
    public static final String TAG_IMG="23";
    public static final String TAG_SELECT="24";
    public static final String TAG_MESSAGE="25";
    public static final String TAG_A="26";
    public static final String TAG_PARAM="27";
    public static final String TAG_INPUT="28";
    public static final String TAG_TEXTAREA="29";
    public static final String TAG_SPAN="30";
    public static final String TAG_LINK="31";
    public static final String TAG_STYLE="32";
    public static final String TAG_BODY="33";
    public static final String TAG_HEAD="34";
    public static final String TAG_MAP="35";
    public static final String TAG_LI="36";
    public static final String TAG_OL="37";
    public static final String TAG_UL="38";
    public static final String TAG_B="39";
    public static final String TAG_I="40";
    public static final String TAG_PRE="41";
    public static final String TAG_H1="42";
    public static final String TAG_H2="43";
    public static final String TAG_H3="44";
    public static final String TAG_H4="45";
    public static final String TAG_H5="46";
    public static final String TAG_H6="47";
    public static final String TAG_BR="48";
    public static final String TAG_HR="49";
    public static final String TAG_P="50";
    public static final String TAG_TABLE="51";
    public static final String TAG_CAPTION="52";
    public static final String TAG_TD="53";
    public static final String TAG_TH="54";
    public static final String TAG_TR="55";
    public static final String TAG_DIV="56";
    public static final String TAG_OPTION="57";

    public static final String ATTR_TYPE="0";
    public static final String ATTR_MULTIPLE="1";
    public static final String ATTR_CLASSNAME="2";
    public static final String ATTR_CONVERTFIELD="3";
    public static final String ATTR_VALUEMESSAGE="4";
    public static final String ATTR_ESCAPEDAMP="5";
    public static final String ATTR_CONVERTVALUE="6";
    public static final String ATTR_CONVERTFIELDVALUE="7";
    public static final String ATTR_VARCLASSNAME="8";
    public static final String ATTR_KEEPFIELDS="9";
    public static final String ATTR_KEYCLASSNAME="10";
    public static final String ATTR_INDEXCLASSNAME="11";
    public static final String ATTR_KEY="12";
    public static final String ATTR_VALUE="13";
    public static final String ATTR_EQ="14";
    public static final String ATTR_INIT="15";
    public static final String ATTR_LIST="16";
    public static final String ATTR_CONDITION="17";
    public static final String ATTR_TO="18";
    public static final String ATTR_MAP="19";
    public static final String ATTR_BEAN="20";
    public static final String ATTR_FROM="21";
    public static final String ATTR_CHECKED="22";
    public static final String ATTR_SELECTED="23";
    public static final String ATTR_VAR="24";
    public static final String ATTR_LABEL="25";
    public static final String ATTR_N_F="26";
    public static final String ATTR_N_A="27";
    public static final String ATTR_ALIAS="28";
    public static final String ATTR_N_I="29";
    public static final String ATTR_N_R="30";
    public static final String ATTR_STEP="31";
    public static final String ATTR_HREF="32";
    public static final String ATTR_PREPARE="33";
    public static final String ATTR_FOR="34";
    public static final String ATTR_QUOTED="35";
    public static final String ATTR_ID="36";
    public static final String ATTR_CLASS="37";
    public static final String ATTR_ACTION="38";
    public static final String ATTR_PARAM="39";
    public static final String ATTR_MESSAGE="40";
    public static final String ATTR_COLS="41";
    public static final String ATTR_FORM="42";
    public static final String ATTR_VARVALUE="43";
    public static final String ATTR_ROWS="44";
    public static final String ATTR_COMMAND="45";
    public static final String ATTR_SGN="46";
    public static final String ATTR_DEFAULT="47";
    public static final String ATTR_SRC="48";
    public static final String ATTR_ESCAPED="49";
    public static final String ATTR_GROUPID="50";
    public static final String ATTR_TITLE="51";
    public static final String ATTR_VALIDATOR="52";
    public static final String ATTR_PAGE="53";
    public static final String ATTR_WIDTH="54";
    public static final String ATTR_DELAY="55";
    public static final String ATTR_REL="56";
    public static final String ATTR_NAME="57";
    public static final String ATTR_CONVERT="58";

    public static final String VALUE_RANGE="0";
    public static final String VALUE_TEXT="1";
    public static final String VALUE_CHECKBOX="2";
    public static final String VALUE_NUMBER="3";
    public static final String VALUE_RADIO="4";
    public static final String VALUE_SUBMIT="5";
    public static final String VALUE_LIMAJLAT="6";
    public static final String VALUE_LIRECT="7";
    public static final String VALUE_STYLE="8";
    public static final String VALUE_LIMINLET="9";
    public static final String VALUE_LINB="10";
    public static final String VALUE_LIMAJLET="11";
    public static final String VALUE_LIDISK="12";
    public static final String VALUE_LIMINLAT="13";
    public static final String VALUE_LICIRCLE="14";
    public static final String VALUE_LISQUARE="15";

    public static final String STYLE_ATTR_BACKGROUND="0";
    public static final String STYLE_ATTR_COLOR="1";
    public static final String STYLE_ATTR_BORDER="2";
    public static final String STYLE_ATTR_FONTFAM="3";
    public static final String STYLE_ATTR_FONTSIZE="4";

    public static final String STYLE_VALUE_BLUE="0";
    public static final String STYLE_VALUE_GREY="1";
    public static final String STYLE_VALUE_GREEN="2";
    public static final String STYLE_VALUE_BLACK="3";
    public static final String STYLE_VALUE_MAGENTA="4";
    public static final String STYLE_VALUE_YELLOW="5";
    public static final String STYLE_VALUE_WHITE="6";
    public static final String STYLE_VALUE_CYAN="7";
    public static final String STYLE_VALUE_RED="8";
    public static final String STYLE_VALUE_RGB="9";

    public static final String STYLE_UNIT_SOLID="0";
    public static final String STYLE_UNIT_EM="1";
    public static final String STYLE_UNIT_PX="2";

    public static final String DEF_MIN_LETTER="0";
    public static final String DEF_MAJ_LETTER="1";
    public static final String DEF_MIN_LATIN="2";
    public static final String DEF_MAJ_LATIN="3";
    public static final String DEF_BASE_SIXTY_FOUR="4";

    private final RendKeyWordsTags rendKeyWordsTags = MessagesRendKeyWordsTags.init();
    private final RendKeyWordsAttrs rendKeyWordsAttrs = MessagesRendKeyWordsAttrs.init();
    private final RendKeyWordsValues rendKeyWordsValues = MessagesRendKeyWordsValues.init();
    private final RendKeyWordsStyles rendKeyWordsStyles = MessagesRendKeyWordsStyles.init();
    private final RendKeyWordsDefs rendKeyWordsDefs = MessagesRendKeyWordsDefs.init();
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
    public static TranslationsFile messDef() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(DEF_MIN_LETTER,"DefMinLetter=abcdefghijklmnopqrstuvwxyz");
        fr_.add(DEF_MAJ_LETTER,"DefMajLetter=ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        fr_.add(DEF_MIN_LATIN,"DefMinLatin=ivxlcdmq");
        fr_.add(DEF_MAJ_LATIN,"DefMajLatin=IVXLCDMQ");
        fr_.add(DEF_BASE_SIXTY_FOUR,"DefBaseSixtyFour="+ MessagesTranslations.BASE);
        return fr_;
    }
    public void otherStyleDefs(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setDefMinLetter(LgNamesContent.get(_util, _cust, _mapping.getVal(DEF_MIN_LETTER)));
        setDefMajLetter(LgNamesContent.get(_util, _cust, _mapping.getVal(DEF_MAJ_LETTER)));
        setDefMinLatin(LgNamesContent.get(_util, _cust, _mapping.getVal(DEF_MIN_LATIN)));
        setDefMajLatin(LgNamesContent.get(_util, _cust, _mapping.getVal(DEF_MAJ_LATIN)));
        setDefBaseSixtyFour(LgNamesContent.get(_util, _cust, _mapping.getVal(DEF_BASE_SIXTY_FOUR)));
    }
    public static StringMap<String> mappingDefs() {
        StringMap<String> mappingStyleAttrs_ = new StringMap<String>();
        mappingStyleAttrs_.addEntry(DEF_MIN_LETTER,"DefMinLetter");
        mappingStyleAttrs_.addEntry(DEF_MAJ_LETTER,"DefMajLetter");
        mappingStyleAttrs_.addEntry(DEF_MIN_LATIN,"DefMinLatin");
        mappingStyleAttrs_.addEntry(DEF_MAJ_LATIN,"DefMajLatin");
        mappingStyleAttrs_.addEntry(DEF_BASE_SIXTY_FOUR,"DefBaseSixtyFour");
        return mappingStyleAttrs_;
    }

    public void patchDefs(StringMap<String> _mapping) {
        String adjMinLetter_ = DisplayedStrings.build(rendKeyWordsDefs.getDefMinLetter(),new LowerCharMapping()).toString();
        if (ko(adjMinLetter_, 1)) {
            rendKeyWordsDefs.setDefMinLetter(_mapping.getVal(DEF_MIN_LETTER));
        } else {
            rendKeyWordsDefs.setDefMinLetter(adjMinLetter_);
        }
        String adjMajLetter_ = DisplayedStrings.build(rendKeyWordsDefs.getDefMajLetter(),new UpperCharMapping()).toString();
        if (ko(adjMajLetter_, 1)) {
            rendKeyWordsDefs.setDefMajLetter(_mapping.getVal(DEF_MAJ_LETTER));
        } else {
            rendKeyWordsDefs.setDefMajLetter(adjMajLetter_);
        }
        String adjMinLatin_ = eightFirstChars(DisplayedStrings.build(rendKeyWordsDefs.getDefMinLatin(),new LowerCharMapping()).toString());
        if (ko(adjMinLatin_, 8)) {
            rendKeyWordsDefs.setDefMinLatin(_mapping.getVal(DEF_MIN_LATIN));
        } else {
            rendKeyWordsDefs.setDefMinLatin(adjMinLatin_);
        }
        String adjMajLatin_ = eightFirstChars(DisplayedStrings.build(rendKeyWordsDefs.getDefMajLatin(),new UpperCharMapping()).toString());
        if (ko(adjMajLatin_, 8)) {
            rendKeyWordsDefs.setDefMajLatin(_mapping.getVal(DEF_MAJ_LATIN));
        } else {
            rendKeyWordsDefs.setDefMajLatin(adjMajLatin_);
        }
        rendKeyWordsDefs.setDefBaseSixtyFour(BaseSixtyFourUtil.checkBase(rendKeyWordsDefs.getDefBaseSixtyFour(),_mapping.getVal(DEF_BASE_SIXTY_FOUR)));
    }
    private static String eightFirstChars(String _str) {
        return _str.substring(0, NumberUtil.min(8,_str.length()));
    }

    private boolean ko(String _a, int _min) {
        return _a.length() < _min;
    }

    public static TranslationsFile enStyleUnits() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(STYLE_UNIT_SOLID,"StyleUnitSolid=solid");
        en_.add(STYLE_UNIT_EM,"StyleUnitEm=em");
        en_.add(STYLE_UNIT_PX,"StyleUnitPx=px");
        return en_;
    }
    public static TranslationsFile frStyleUnits() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(STYLE_UNIT_SOLID,"StyleUnitSolid=solid");
        fr_.add(STYLE_UNIT_EM,"StyleUnitEm=em");
        fr_.add(STYLE_UNIT_PX,"StyleUnitPx=px");
        return fr_;
    }
    public void otherStyleUnits(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setStyleUnitSolid(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_UNIT_SOLID)));
        setStyleUnitPx(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_UNIT_PX)));
        setStyleUnitEm(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_UNIT_EM)));
    }
    public static TranslationsFile enStyleValues() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(STYLE_VALUE_BLUE,"StyleValueBlue=blue");
        en_.add(STYLE_VALUE_GREY,"StyleValueGrey=grey");
        en_.add(STYLE_VALUE_GREEN,"StyleValueGreen=green");
        en_.add(STYLE_VALUE_BLACK,"StyleValueBlack=black");
        en_.add(STYLE_VALUE_MAGENTA,"StyleValueMagenta=magenta");
        en_.add(STYLE_VALUE_YELLOW,"StyleValueYellow=yellow");
        en_.add(STYLE_VALUE_WHITE,"StyleValueWhite=white");
        en_.add(STYLE_VALUE_CYAN,"StyleValueCyan=cyan");
        en_.add(STYLE_VALUE_RED,"StyleValueRed=red");
        en_.add(STYLE_VALUE_RGB,"StyleValueRgb=rgb");
        return en_;
    }
    public static TranslationsFile frStyleValues() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(STYLE_VALUE_BLUE,"StyleValueBlue=bleu");
        fr_.add(STYLE_VALUE_GREY,"StyleValueGrey=gris");
        fr_.add(STYLE_VALUE_GREEN,"StyleValueGreen=vert");
        fr_.add(STYLE_VALUE_BLACK,"StyleValueBlack=noir");
        fr_.add(STYLE_VALUE_MAGENTA,"StyleValueMagenta=magenta");
        fr_.add(STYLE_VALUE_YELLOW,"StyleValueYellow=jaune");
        fr_.add(STYLE_VALUE_WHITE,"StyleValueWhite=blanc");
        fr_.add(STYLE_VALUE_CYAN,"StyleValueCyan=cyan");
        fr_.add(STYLE_VALUE_RED,"StyleValueRed=rouge");
        fr_.add(STYLE_VALUE_RGB,"StyleValueRgb=rvb");
        return fr_;
    }
    public void otherStyleValues(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setStyleValueGreen(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_GREEN)));
        setStyleValueBlue(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_BLUE)));
        setStyleValueYellow(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_YELLOW)));
        setStyleValueMagenta(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_MAGENTA)));
        setStyleValueCyan(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_CYAN)));
        setStyleValueBlack(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_BLACK)));
        setStyleValueGrey(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_GREY)));
        setStyleValueWhite(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_WHITE)));
        setStyleValueRgb(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_RGB)));
        setStyleValueRed(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_VALUE_RED)));
    }
    public static TranslationsFile enStyleAttrs() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(STYLE_ATTR_BACKGROUND,"StyleAttrBackground=background");
        en_.add(STYLE_ATTR_COLOR,"StyleAttrColor=color");
        en_.add(STYLE_ATTR_BORDER,"StyleAttrBorder=border");
        en_.add(STYLE_ATTR_FONTFAM,"StyleAttrFontFam=font-family");
        en_.add(STYLE_ATTR_FONTSIZE,"StyleAttrFontSize=font-size");
        return en_;
    }
    public static TranslationsFile frStyleAttrs() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(STYLE_ATTR_BACKGROUND,"StyleAttrBackground=dernierplan");
        fr_.add(STYLE_ATTR_COLOR,"StyleAttrColor=couleur");
        fr_.add(STYLE_ATTR_BORDER,"StyleAttrBorder=bordure");
        fr_.add(STYLE_ATTR_FONTFAM,"StyleAttrFontFam=police-famille");
        fr_.add(STYLE_ATTR_FONTSIZE,"StyleAttrFontSize=police-taille");
        return fr_;
    }
    public void otherStyleAttrs(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setStyleAttrColor(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_ATTR_COLOR)));
        setStyleAttrBackground(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_ATTR_BACKGROUND)));
        setStyleAttrBorder(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_ATTR_BORDER)));
        setStyleAttrFontSize(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_ATTR_FONTSIZE)));
        setStyleAttrFontFam(LgNamesContent.get(_util, _cust, _mapping.getVal(STYLE_ATTR_FONTFAM)));
    }
    public static TranslationsFile enValues() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(VALUE_RANGE,"ValueRange=range");
        en_.add(VALUE_TEXT,"ValueText=text");
        en_.add(VALUE_CHECKBOX,"ValueCheckbox=checkbox");
        en_.add(VALUE_NUMBER,"ValueNumber=number");
        en_.add(VALUE_RADIO,"ValueRadio=radio");
        en_.add(VALUE_SUBMIT,"ValueSubmit=submit");
        en_.add(VALUE_LIMAJLAT,"ValueLiMajLat=I");
        en_.add(VALUE_LIRECT,"ValueLiRect=rect");
        en_.add(VALUE_STYLE,"ValueStyle=stylesheet");
        en_.add(VALUE_LIMINLET,"ValueLiMinLet=a");
        en_.add(VALUE_LINB,"ValueLiNb=1");
        en_.add(VALUE_LIMAJLET,"ValueLiMajLet=A");
        en_.add(VALUE_LIDISK,"ValueLiDisk=disc");
        en_.add(VALUE_LIMINLAT,"ValueLiMinLat=i");
        en_.add(VALUE_LICIRCLE,"ValueLiCircle=circle");
        en_.add(VALUE_LISQUARE,"ValueLiSquare=square");
        return en_;
    }
    public static TranslationsFile frValues() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(VALUE_RANGE,"ValueRange=rang");
        fr_.add(VALUE_TEXT,"ValueText=texte");
        fr_.add(VALUE_CHECKBOX,"ValueCheckbox=coche");
        fr_.add(VALUE_NUMBER,"ValueNumber=nombre");
        fr_.add(VALUE_RADIO,"ValueRadio=radio");
        fr_.add(VALUE_SUBMIT,"ValueSubmit=soumission");
        fr_.add(VALUE_LIMAJLAT,"ValueLiMajLat=I");
        fr_.add(VALUE_LIRECT,"ValueLiRect=rect");
        fr_.add(VALUE_STYLE,"ValueStyle=feuillestyle");
        fr_.add(VALUE_LIMINLET,"ValueLiMinLet=a");
        fr_.add(VALUE_LINB,"ValueLiNb=1");
        fr_.add(VALUE_LIMAJLET,"ValueLiMajLet=A");
        fr_.add(VALUE_LIDISK,"ValueLiDisk=disq");
        fr_.add(VALUE_LIMINLAT,"ValueLiMinLat=i");
        fr_.add(VALUE_LICIRCLE,"ValueLiCircle=cercle");
        fr_.add(VALUE_LISQUARE,"ValueLiSquare=carre");
        return fr_;
    }
    public void otherValues(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setValueNumber(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_NUMBER)));
        setValueRange(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_RANGE)));
        setValueRadio(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_RADIO)));
        setValueCheckbox(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_CHECKBOX)));
        setValueText(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_TEXT)));
        setValueLiMinLet(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LIMINLET)));
        setValueLiDisk(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LIDISK)));
        setValueLiRect(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LIRECT)));
        setValueLiSquare(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LISQUARE)));
        setValueLiNb(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LINB)));
        setValueLiMajLat(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LIMAJLAT)));
        setValueStyle(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_STYLE)));
        setValueLiCircle(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LICIRCLE)));
        setValueLiMajLet(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LIMAJLET)));
        setValueLiMinLat(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_LIMINLAT)));
        setValueSubmit(LgNamesContent.get(_util, _cust,_mapping.getVal(VALUE_SUBMIT)));
    }
    public static TranslationsFile enAttrs() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(ATTR_TYPE,"AttrType=type");
        en_.add(ATTR_MULTIPLE,"AttrMultiple=multiple");
        en_.add(ATTR_CLASSNAME,"AttrClassName=className");
        en_.add(ATTR_CONVERTFIELD,"AttrConvertField=convertField");
        en_.add(ATTR_VALUEMESSAGE,"AttrValueMessage=valueMessage");
        en_.add(ATTR_ESCAPEDAMP,"AttrEscapedAmp=escapedamp");
        en_.add(ATTR_CONVERTVALUE,"AttrConvertValue=convertValue");
        en_.add(ATTR_CONVERTFIELDVALUE,"AttrConvertFieldValue=convertFieldValue");
        en_.add(ATTR_VARCLASSNAME,"AttrVarClassName=varClassName");
        en_.add(ATTR_KEEPFIELDS,"AttrKeepFields=keepfields");
        en_.add(ATTR_KEYCLASSNAME,"AttrKeyClassName=keyClassName");
        en_.add(ATTR_INDEXCLASSNAME,"AttrIndexClassName=indexClassName");
        en_.add(ATTR_KEY,"AttrKey=key");
        en_.add(ATTR_VALUE,"AttrValue=value");
        en_.add(ATTR_EQ,"AttrEq=eq");
        en_.add(ATTR_INIT,"AttrInit=init");
        en_.add(ATTR_LIST,"AttrList=list");
        en_.add(ATTR_CONDITION,"AttrCondition=condition");
        en_.add(ATTR_TO,"AttrTo=to");
        en_.add(ATTR_MAP,"AttrMap=map");
        en_.add(ATTR_BEAN,"AttrBean=bean");
        en_.add(ATTR_FROM,"AttrFrom=from");
        en_.add(ATTR_CHECKED,"AttrChecked=checked");
        en_.add(ATTR_SELECTED,"AttrSelected=selected");
        en_.add(ATTR_VAR,"AttrVar=var");
        en_.add(ATTR_LABEL,"AttrLabel=label");
        en_.add(ATTR_N_F,"AttrNf=n-f");
        en_.add(ATTR_N_A,"AttrNa=n-a");
        en_.add(ATTR_ALIAS,"AttrAlias=alias");
        en_.add(ATTR_N_I,"AttrNi=n-i");
        en_.add(ATTR_N_R,"AttrNr=n-r");
        en_.add(ATTR_STEP,"AttrStep=step");
        en_.add(ATTR_HREF,"AttrHref=href");
        en_.add(ATTR_PREPARE,"AttrPrepare=prepare");
        en_.add(ATTR_FOR,"AttrFor=for");
        en_.add(ATTR_QUOTED,"AttrQuoted=quoted");
        en_.add(ATTR_ID,"AttrId=id");
        en_.add(ATTR_CLASS,"AttrClass=class");
        en_.add(ATTR_ACTION,"AttrAction=action");
        en_.add(ATTR_PARAM,"AttrParam=param");
        en_.add(ATTR_MESSAGE,"AttrMessage=message");
        en_.add(ATTR_COLS,"AttrCols=cols");
        en_.add(ATTR_FORM,"AttrForm=form");
        en_.add(ATTR_VARVALUE,"AttrVarValue=varValue");
        en_.add(ATTR_ROWS,"AttrRows=rows");
        en_.add(ATTR_COMMAND,"AttrCommand=command");
        en_.add(ATTR_SGN,"AttrSgn=sgn");
        en_.add(ATTR_DEFAULT,"AttrDefault=default");
        en_.add(ATTR_SRC,"AttrSrc=src");
        en_.add(ATTR_ESCAPED,"AttrEscaped=escaped");
        en_.add(ATTR_GROUPID,"AttrGroupId=groupId");
        en_.add(ATTR_TITLE,"AttrTitle=title");
        en_.add(ATTR_VALIDATOR,"AttrValidator=validator");
        en_.add(ATTR_PAGE,"AttrPage=page");
        en_.add(ATTR_WIDTH,"AttrWidth=width");
        en_.add(ATTR_DELAY,"AttrDelay=delay");
        en_.add(ATTR_REL,"AttrRel=rel");
        en_.add(ATTR_NAME,"AttrName=name");
        en_.add(ATTR_CONVERT,"AttrConvert=convert");
        return en_;
    }
    public static TranslationsFile frAttrs() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(ATTR_TYPE,"AttrType=type");
        fr_.add(ATTR_MULTIPLE,"AttrMultiple=multiple");
        fr_.add(ATTR_CLASSNAME,"AttrClassName=nomClasse");
        fr_.add(ATTR_CONVERTFIELD,"AttrConvertField=convertChamp");
        fr_.add(ATTR_VALUEMESSAGE,"AttrValueMessage=valeurMessage");
        fr_.add(ATTR_ESCAPEDAMP,"AttrEscapedAmp=echapAmp");
        fr_.add(ATTR_CONVERTVALUE,"AttrConvertValue=convertValeur");
        fr_.add(ATTR_CONVERTFIELDVALUE,"AttrConvertFieldValue=convertChampValeur");
        fr_.add(ATTR_VARCLASSNAME,"AttrVarClassName=varNomClasse");
        fr_.add(ATTR_KEEPFIELDS,"AttrKeepFields=consChamps");
        fr_.add(ATTR_KEYCLASSNAME,"AttrKeyClassName=cleNomClasse");
        fr_.add(ATTR_INDEXCLASSNAME,"AttrIndexClassName=indiceNomClasse");
        fr_.add(ATTR_KEY,"AttrKey=cle");
        fr_.add(ATTR_VALUE,"AttrValue=valeur");
        fr_.add(ATTR_EQ,"AttrEq=eg");
        fr_.add(ATTR_INIT,"AttrInit=init");
        fr_.add(ATTR_LIST,"AttrList=list");
        fr_.add(ATTR_CONDITION,"AttrCondition=condition");
        fr_.add(ATTR_TO,"AttrTo=vers");
        fr_.add(ATTR_MAP,"AttrMap=table");
        fr_.add(ATTR_BEAN,"AttrBean=haricot");
        fr_.add(ATTR_FROM,"AttrFrom=depuis");
        fr_.add(ATTR_CHECKED,"AttrChecked=coche");
        fr_.add(ATTR_SELECTED,"AttrSelected=selection");
        fr_.add(ATTR_VAR,"AttrVar=var");
        fr_.add(ATTR_LABEL,"AttrLabel=etiq");
        fr_.add(ATTR_N_F,"AttrNf=n-f");
        fr_.add(ATTR_N_A,"AttrNa=n-a");
        fr_.add(ATTR_ALIAS,"AttrAlias=alias");
        fr_.add(ATTR_N_I,"AttrNi=n-i");
        fr_.add(ATTR_N_R,"AttrNr=n-r");
        fr_.add(ATTR_STEP,"AttrStep=pas");
        fr_.add(ATTR_HREF,"AttrHref=href");
        fr_.add(ATTR_PREPARE,"AttrPrepare=prepare");
        fr_.add(ATTR_FOR,"AttrFor=pour");
        fr_.add(ATTR_QUOTED,"AttrQuoted=cite");
        fr_.add(ATTR_ID,"AttrId=id");
        fr_.add(ATTR_CLASS,"AttrClass=classe");
        fr_.add(ATTR_ACTION,"AttrAction=action");
        fr_.add(ATTR_PARAM,"AttrParam=param");
        fr_.add(ATTR_MESSAGE,"AttrMessage=message");
        fr_.add(ATTR_COLS,"AttrCols=cols");
        fr_.add(ATTR_FORM,"AttrForm=form");
        fr_.add(ATTR_VARVALUE,"AttrVarValue=varValeur");
        fr_.add(ATTR_ROWS,"AttrRows=lignes");
        fr_.add(ATTR_COMMAND,"AttrCommand=command");
        fr_.add(ATTR_SGN,"AttrSgn=sgn");
        fr_.add(ATTR_DEFAULT,"AttrDefault=autrement");
        fr_.add(ATTR_SRC,"AttrSrc=src");
        fr_.add(ATTR_ESCAPED,"AttrEscaped=echap");
        fr_.add(ATTR_GROUPID,"AttrGroupId=groupId");
        fr_.add(ATTR_TITLE,"AttrTitle=titre");
        fr_.add(ATTR_VALIDATOR,"AttrValidator=validateur");
        fr_.add(ATTR_PAGE,"AttrPage=page");
        fr_.add(ATTR_WIDTH,"AttrWidth=largeur");
        fr_.add(ATTR_DELAY,"AttrDelay=delai");
        fr_.add(ATTR_REL,"AttrRel=rel");
        fr_.add(ATTR_NAME,"AttrName=nom");
        fr_.add(ATTR_CONVERT,"AttrConvert=convert");
        return fr_;
    }
    public void otherAttrs(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAttrType(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_TYPE)));
        setAttrClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CLASSNAME)));
        setAttrMultiple(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_MULTIPLE)));
        setAttrValidator(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_VALIDATOR)));
        setAttrSelected(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_SELECTED)));
        setAttrAction(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_ACTION)));
        setAttrAlias(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_ALIAS)));
        setAttrNf(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_N_F)));
        setAttrNi(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_N_I)));
        setAttrNr(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_N_R)));
        setAttrChecked(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CHECKED)));
        setAttrLabel(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_LABEL)));
        setAttrCondition(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CONDITION)));
        setAttrFor(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_FOR)));
        setAttrTitle(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_TITLE)));
        setAttrConvert(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CONVERT)));
        setAttrEscaped(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_ESCAPED)));
        setAttrHref(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_HREF)));
        setAttrCommand(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_COMMAND)));
        setAttrSgn(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_SGN)));
        setAttrMessage(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_MESSAGE)));
        setAttrDefault(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_DEFAULT)));
        setAttrPrepare(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_PREPARE)));
        setAttrForm(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_FORM)));
        setAttrId(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_ID)));
        setAttrGroupId(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_GROUPID)));
        setAttrVarValue(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_VARVALUE)));
        setAttrBean(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_BEAN)));
        setAttrNa(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_N_A)));
        setAttrParam(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_PARAM)));
        setAttrName(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_NAME)));
        setAttrQuoted(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_QUOTED)));
        setAttrRel(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_REL)));
        setAttrSrc(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_SRC)));
        setAttrDelay(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_DELAY)));
        setAttrPage(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_PAGE)));
        setAttrRows(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_ROWS)));
        setAttrClass(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CLASS)));
        setAttrCols(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_COLS)));
        setAttrWidth(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_WIDTH)));
        setAttrList(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_LIST)));
        setAttrMap(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_MAP)));
        setAttrKey(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_KEY)));
        setAttrTo(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_TO)));
        setAttrEq(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_EQ)));
        setAttrVar(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_VAR)));
        setAttrStep(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_STEP)));
        setAttrFrom(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_FROM)));
        setAttrValue(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_VALUE)));
        setAttrInit(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_INIT)));
        setAttrKeepFields(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_KEEPFIELDS)));
        setAttrValueMessage(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_VALUEMESSAGE)));
        setAttrConvertField(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CONVERTFIELD)));
        setAttrKeyClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_KEYCLASSNAME)));
        setAttrVarClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_VARCLASSNAME)));
        setAttrIndexClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_INDEXCLASSNAME)));
        setAttrEscapedAmp(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_ESCAPEDAMP)));
        setAttrConvertValue(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CONVERTVALUE)));
        setAttrConvertFieldValue(LgNamesContent.get(_util, _cust, _mapping.getVal(ATTR_CONVERTFIELDVALUE)));
    }
    public static TranslationsFile enTags() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TAG_FOR,"TagFor=for");
        en_.add(TAG_WHILE,"TagWhile=while");
        en_.add(TAG_DO,"TagDo=do");
        en_.add(TAG_IF,"TagIf=if");
        en_.add(TAG_ELSE,"TagElse=else");
        en_.add(TAG_ELSEIF,"TagElseif=elseif");
        en_.add(TAG_TRY,"TagTry=try");
        en_.add(TAG_FINALLY,"TagFinally=finally");
        en_.add(TAG_CATCH,"TagCatch=catch");
        en_.add(TAG_SWITCH,"TagSwitch=switch");
        en_.add(TAG_CASE,"TagCase=case");
        en_.add(TAG_DEFAULT,"TagDefault=default");
        en_.add(TAG_RETURN,"TagReturn=return");
        en_.add(TAG_THROW,"TagThrow=throw");
        en_.add(TAG_BREAK,"TagBreak=break");
        en_.add(TAG_CONTINUE,"TagContinue=continue");
        en_.add(TAG_SET,"TagSet=set");
        en_.add(TAG_IMPORT,"TagImport=import");
        en_.add(TAG_PACKAGE,"TagPackage=package");
        en_.add(TAG_CLASS,"TagClass=class");
        en_.add(TAG_FIELD,"TagField=field");
        en_.add(TAG_FORM,"TagForm=form");
        en_.add(TAG_SUBMIT,"TagSubmit=submit");
        en_.add(TAG_IMG,"TagImg=img");
        en_.add(TAG_SELECT,"TagSelect=select");
        en_.add(TAG_MESSAGE,"TagMessage=message");
        en_.add(TAG_A,"TagAnchor=a");
        en_.add(TAG_PARAM,"TagParam=param");
        en_.add(TAG_INPUT,"TagInput=input");
        en_.add(TAG_TEXTAREA,"TagTextarea=textarea");
        en_.add(TAG_SPAN,"TagSpan=span");
        en_.add(TAG_LINK,"TagLink=link");
        en_.add(TAG_STYLE,"TagStyle=style");
        en_.add(TAG_BODY,"TagBody=body");
        en_.add(TAG_HEAD,"TagHead=head");
        en_.add(TAG_MAP,"TagMap=map");
        en_.add(TAG_LI,"TagLi=li");
        en_.add(TAG_OL,"TagOl=ol");
        en_.add(TAG_UL,"TagUl=ul");
        en_.add(TAG_B,"TagBold=b");
        en_.add(TAG_I,"TagItalic=i");
        en_.add(TAG_PRE,"TagPre=pre");
        en_.add(TAG_H1,"TagHOne=h1");
        en_.add(TAG_H2,"TagHTwo=h2");
        en_.add(TAG_H3,"TagHThree=h3");
        en_.add(TAG_H4,"TagHFour=h4");
        en_.add(TAG_H5,"TagHFive=h5");
        en_.add(TAG_H6,"TagHSix=h6");
        en_.add(TAG_BR,"TagBr=br");
        en_.add(TAG_HR,"TagHr=hr");
        en_.add(TAG_P,"TagPar=p");
        en_.add(TAG_TABLE,"TagTable=table");
        en_.add(TAG_CAPTION,"TagCaption=caption");
        en_.add(TAG_TD,"TagTd=td");
        en_.add(TAG_TH,"TagTh=th");
        en_.add(TAG_TR,"TagTr=tr");
        en_.add(TAG_DIV,"TagDiv=div");
        en_.add(TAG_OPTION,"TagOption=option");
        return en_;
    }
    public static TranslationsFile frTags() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(TAG_FOR,"TagFor=pour");
        fr_.add(TAG_WHILE,"TagWhile=tantque");
        fr_.add(TAG_DO,"TagDo=faire");
        fr_.add(TAG_IF,"TagIf=si");
        fr_.add(TAG_ELSE,"TagElse=sinon");
        fr_.add(TAG_ELSEIF,"TagElseif=sinonsi");
        fr_.add(TAG_TRY,"TagTry=essai");
        fr_.add(TAG_FINALLY,"TagFinally=finallement");
        fr_.add(TAG_CATCH,"TagCatch=capture");
        fr_.add(TAG_SWITCH,"TagSwitch=selon");
        fr_.add(TAG_CASE,"TagCase=cas");
        fr_.add(TAG_DEFAULT,"TagDefault=autrement");
        fr_.add(TAG_RETURN,"TagReturn=retour");
        fr_.add(TAG_THROW,"TagThrow=lancer");
        fr_.add(TAG_BREAK,"TagBreak=sortir");
        fr_.add(TAG_CONTINUE,"TagContinue=iterer");
        fr_.add(TAG_SET,"TagSet=maj");
        fr_.add(TAG_IMPORT,"TagImport=import");
        fr_.add(TAG_PACKAGE,"TagPackage=paquetage");
        fr_.add(TAG_CLASS,"TagClass=classe");
        fr_.add(TAG_FIELD,"TagField=champ");
        fr_.add(TAG_FORM,"TagForm=form");
        fr_.add(TAG_SUBMIT,"TagSubmit=soumission");
        fr_.add(TAG_IMG,"TagImg=img");
        fr_.add(TAG_SELECT,"TagSelect=select");
        fr_.add(TAG_MESSAGE,"TagMessage=message");
        fr_.add(TAG_A,"TagAnchor=a");
        fr_.add(TAG_PARAM,"TagParam=param");
        fr_.add(TAG_INPUT,"TagInput=entree");
        fr_.add(TAG_TEXTAREA,"TagTextarea=zonetexte");
        fr_.add(TAG_SPAN,"TagSpan=noeudligne");
        fr_.add(TAG_LINK,"TagLink=lien");
        fr_.add(TAG_STYLE,"TagStyle=style");
        fr_.add(TAG_BODY,"TagBody=corps");
        fr_.add(TAG_HEAD,"TagHead=tete");
        fr_.add(TAG_MAP,"TagMap=map");
        fr_.add(TAG_LI,"TagLi=le");
        fr_.add(TAG_OL,"TagOl=lo");
        fr_.add(TAG_UL,"TagUl=lp");
        fr_.add(TAG_B,"TagBold=g");
        fr_.add(TAG_I,"TagItalic=i");
        fr_.add(TAG_PRE,"TagPre=pre");
        fr_.add(TAG_H1,"TagHOne=h1");
        fr_.add(TAG_H2,"TagHTwo=h2");
        fr_.add(TAG_H3,"TagHThree=h3");
        fr_.add(TAG_H4,"TagHFour=h4");
        fr_.add(TAG_H5,"TagHFive=h5");
        fr_.add(TAG_H6,"TagHSix=h6");
        fr_.add(TAG_BR,"TagBr=saut");
        fr_.add(TAG_HR,"TagHr=sautligne");
        fr_.add(TAG_P,"TagPar=p");
        fr_.add(TAG_TABLE,"TagTable=table");
        fr_.add(TAG_CAPTION,"TagCaption=entete");
        fr_.add(TAG_TD,"TagTd=td");
        fr_.add(TAG_TH,"TagTh=th");
        fr_.add(TAG_TR,"TagTr=tr");
        fr_.add(TAG_DIV,"TagDiv=div");
        fr_.add(TAG_OPTION,"TagOption=option");
        return fr_;
    }

    public void otherTags(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setKeyWordTextarea(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_TEXTAREA)));
        setKeyWordSelect(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_SELECT)));
        setKeyWordFinally(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_FINALLY)));
        setKeyWordContinue(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_CONTINUE)));
        setKeyWordPackage(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_PACKAGE)));
        setKeyWordDefault(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_DEFAULT)));
        setKeyWordReturn(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_RETURN)));
        setKeyWordFor(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_FOR)));
        setKeyWordIf(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_IF)));
        setKeyWordClass(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_CLASS)));
        setKeyWordElseif(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_ELSEIF)));
        setKeyWordThrow(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_THROW)));
        setKeyWordBreak(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_BREAK)));
        setKeyWordCatch(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_CATCH)));
        setKeyWordCase(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_CASE)));
        setKeyWordTry(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_TRY)));
        setKeyWordElse(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_ELSE)));
        setKeyWordSwitch(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_SWITCH)));
        setKeyWordWhile(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_WHILE)));
        setKeyWordDo(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_DO)));
        setKeyWordCaption(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_CAPTION)));
        setKeyWordMessage(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_MESSAGE)));
        setKeyWordAnchor(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_A)));
        setKeyWordForm(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_FORM)));
        setKeyWordSpan(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_SPAN)));
        setKeyWordImg(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_IMG)));
        setKeyWordInput(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_INPUT)));
        setKeyWordLink(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_LINK)));
        setKeyWordSet(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_SET)));
        setKeyWordSubmit(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_SUBMIT)));
        setKeyWordStyle(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_STYLE)));
        setKeyWordField(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_FIELD)));
        setKeyWordImport(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_IMPORT)));
        setKeyWordItalic(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_I)));
        setKeyWordHr(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_HR)));
        setKeyWordTd(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_TD)));
        setKeyWordBody(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_BODY)));
        setKeyWordOption(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_OPTION)));
        setKeyWordUl(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_UL)));
        setKeyWordHTwo(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_H2)));
        setKeyWordPre(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_PRE)));
        setKeyWordBr(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_BR)));
        setKeyWordHSix(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_H6)));
        setKeyWordMap(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_MAP)));
        setKeyWordTh(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_TH)));
        setKeyWordLi(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_LI)));
        setKeyWordHOne(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_H1)));
        setKeyWordOl(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_OL)));
        setKeyWordBold(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_B)));
        setKeyWordDiv(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_DIV)));
        setKeyWordTr(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_TR)));
        setKeyWordHFive(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_H5)));
        setKeyWordPar(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_P)));
        setKeyWordHead(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_HEAD)));
        setKeyWordHFour(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_H4)));
        setKeyWordTable(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_TABLE)));
        setKeyWordHThree(LgNamesContent.get(_util, _cust, _mapping.getVal(TAG_H3)));
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
    public void validateAttrContents(StringMap<String> _list, AnalyzedPageEl _page, StringMap<String> _mappingAttrs) {
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
            if (!StringUtil.quickEq(key_,_mappingAttrs.getVal(ATTR_PARAM))
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

    public StringMap<String> allTags(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(TAG_SELECT),rendKeyWordsTags.getKeyWordSelect());
        keyWords_.addEntry(_mapping.getVal(TAG_TEXTAREA),rendKeyWordsTags.getKeyWordTextarea());
        keyWords_.addEntry(_mapping.getVal(TAG_ELSE),rendKeyWordsTags.getKeyWordElse());
        keyWords_.addEntry(_mapping.getVal(TAG_CASE),rendKeyWordsTags.getKeyWordCase());
        keyWords_.addEntry(_mapping.getVal(TAG_SWITCH),rendKeyWordsTags.getKeyWordSwitch());
        keyWords_.addEntry(_mapping.getVal(TAG_DO),rendKeyWordsTags.getKeyWordDo());
        keyWords_.addEntry(_mapping.getVal(TAG_TRY),rendKeyWordsTags.getKeyWordTry());
        keyWords_.addEntry(_mapping.getVal(TAG_WHILE),rendKeyWordsTags.getKeyWordWhile());
        keyWords_.addEntry(_mapping.getVal(TAG_ELSEIF),rendKeyWordsTags.getKeyWordElseif());
        keyWords_.addEntry(_mapping.getVal(TAG_BREAK),rendKeyWordsTags.getKeyWordBreak());
        keyWords_.addEntry(_mapping.getVal(TAG_THROW),rendKeyWordsTags.getKeyWordThrow());
        keyWords_.addEntry(_mapping.getVal(TAG_CATCH),rendKeyWordsTags.getKeyWordCatch());
        keyWords_.addEntry(_mapping.getVal(TAG_CAPTION),rendKeyWordsTags.getKeyWordCaption());
        keyWords_.addEntry(_mapping.getVal(TAG_MESSAGE),rendKeyWordsTags.getKeyWordMessage());
        keyWords_.addEntry(_mapping.getVal(TAG_OL),rendKeyWordsTags.getKeyWordOl());
        keyWords_.addEntry(_mapping.getVal(TAG_HEAD),rendKeyWordsTags.getKeyWordHead());
        keyWords_.addEntry(_mapping.getVal(TAG_UL),rendKeyWordsTags.getKeyWordUl());
        keyWords_.addEntry(_mapping.getVal(TAG_IMPORT),rendKeyWordsTags.getKeyWordImport());
        keyWords_.addEntry(_mapping.getVal(TAG_B),rendKeyWordsTags.getKeyWordBold());
        keyWords_.addEntry(_mapping.getVal(TAG_FIELD),rendKeyWordsTags.getKeyWordField());
        keyWords_.addEntry(_mapping.getVal(TAG_SET),rendKeyWordsTags.getKeyWordSet());
        keyWords_.addEntry(_mapping.getVal(TAG_FORM),rendKeyWordsTags.getKeyWordForm());
        keyWords_.addEntry(_mapping.getVal(TAG_SPAN),rendKeyWordsTags.getKeyWordSpan());
        keyWords_.addEntry(_mapping.getVal(TAG_STYLE),rendKeyWordsTags.getKeyWordStyle());
        keyWords_.addEntry(_mapping.getVal(TAG_BODY),rendKeyWordsTags.getKeyWordBody());
        keyWords_.addEntry(_mapping.getVal(TAG_MAP),rendKeyWordsTags.getKeyWordMap());
        keyWords_.addEntry(_mapping.getVal(TAG_SUBMIT),rendKeyWordsTags.getKeyWordSubmit());
        keyWords_.addEntry(_mapping.getVal(TAG_IMG),rendKeyWordsTags.getKeyWordImg());
        keyWords_.addEntry(_mapping.getVal(TAG_INPUT),rendKeyWordsTags.getKeyWordInput());
        keyWords_.addEntry(_mapping.getVal(TAG_A),rendKeyWordsTags.getKeyWordAnchor());
        keyWords_.addEntry(_mapping.getVal(TAG_LINK),rendKeyWordsTags.getKeyWordLink());
        keyWords_.addEntry(_mapping.getVal(TAG_LI),rendKeyWordsTags.getKeyWordLi());
        keyWords_.addEntry(_mapping.getVal(TAG_H6),rendKeyWordsTags.getKeyWordHSix());
        keyWords_.addEntry(_mapping.getVal(TAG_BR),rendKeyWordsTags.getKeyWordBr());
        keyWords_.addEntry(_mapping.getVal(TAG_H2),rendKeyWordsTags.getKeyWordHTwo());
        keyWords_.addEntry(_mapping.getVal(TAG_H4),rendKeyWordsTags.getKeyWordHFour());
        keyWords_.addEntry(_mapping.getVal(TAG_TR),rendKeyWordsTags.getKeyWordTr());
        keyWords_.addEntry(_mapping.getVal(TAG_TD),rendKeyWordsTags.getKeyWordTd());
        keyWords_.addEntry(_mapping.getVal(TAG_H1),rendKeyWordsTags.getKeyWordHOne());
        keyWords_.addEntry(_mapping.getVal(TAG_DIV),rendKeyWordsTags.getKeyWordDiv());
        keyWords_.addEntry(_mapping.getVal(TAG_OPTION),rendKeyWordsTags.getKeyWordOption());
        keyWords_.addEntry(_mapping.getVal(TAG_TH),rendKeyWordsTags.getKeyWordTh());
        keyWords_.addEntry(_mapping.getVal(TAG_TABLE),rendKeyWordsTags.getKeyWordTable());
        keyWords_.addEntry(_mapping.getVal(TAG_HR),rendKeyWordsTags.getKeyWordHr());
        keyWords_.addEntry(_mapping.getVal(TAG_P),rendKeyWordsTags.getKeyWordPar());
        keyWords_.addEntry(_mapping.getVal(TAG_I),rendKeyWordsTags.getKeyWordItalic());
        keyWords_.addEntry(_mapping.getVal(TAG_H3),rendKeyWordsTags.getKeyWordHThree());
        keyWords_.addEntry(_mapping.getVal(TAG_H5),rendKeyWordsTags.getKeyWordHFive());
        keyWords_.addEntry(_mapping.getVal(TAG_PRE),rendKeyWordsTags.getKeyWordPre());
        keyWords_.addEntry(_mapping.getVal(TAG_FINALLY),rendKeyWordsTags.getKeyWordFinally());
        keyWords_.addEntry(_mapping.getVal(TAG_DEFAULT),rendKeyWordsTags.getKeyWordDefault());
        keyWords_.addEntry(_mapping.getVal(TAG_PACKAGE),rendKeyWordsTags.getKeyWordPackage());
        keyWords_.addEntry(_mapping.getVal(TAG_CONTINUE),rendKeyWordsTags.getKeyWordContinue());
        keyWords_.addEntry(_mapping.getVal(TAG_CLASS),rendKeyWordsTags.getKeyWordClass());
        keyWords_.addEntry(_mapping.getVal(TAG_FOR),rendKeyWordsTags.getKeyWordFor());
        keyWords_.addEntry(_mapping.getVal(TAG_IF),rendKeyWordsTags.getKeyWordIf());
        keyWords_.addEntry(_mapping.getVal(TAG_RETURN),rendKeyWordsTags.getKeyWordReturn());
        return keyWords_;
    }
    public static StringMap<String> mappingTags() {
        StringMap<String> mappingTags_ = new StringMap<String>();
        mappingTags_.addEntry(TAG_FOR,"TagFor");
        mappingTags_.addEntry(TAG_WHILE,"TagWhile");
        mappingTags_.addEntry(TAG_DO,"TagDo");
        mappingTags_.addEntry(TAG_IF,"TagIf");
        mappingTags_.addEntry(TAG_ELSE,"TagElse");
        mappingTags_.addEntry(TAG_ELSEIF,"TagElseif");
        mappingTags_.addEntry(TAG_TRY,"TagTry");
        mappingTags_.addEntry(TAG_FINALLY,"TagFinally");
        mappingTags_.addEntry(TAG_CATCH,"TagCatch");
        mappingTags_.addEntry(TAG_SWITCH,"TagSwitch");
        mappingTags_.addEntry(TAG_CASE,"TagCase");
        mappingTags_.addEntry(TAG_DEFAULT,"TagDefault");
        mappingTags_.addEntry(TAG_RETURN,"TagReturn");
        mappingTags_.addEntry(TAG_THROW,"TagThrow");
        mappingTags_.addEntry(TAG_BREAK,"TagBreak");
        mappingTags_.addEntry(TAG_CONTINUE,"TagContinue");
        mappingTags_.addEntry(TAG_SET,"TagSet");
        mappingTags_.addEntry(TAG_IMPORT,"TagImport");
        mappingTags_.addEntry(TAG_PACKAGE,"TagPackage");
        mappingTags_.addEntry(TAG_CLASS,"TagClass");
        mappingTags_.addEntry(TAG_FIELD,"TagField");
        mappingTags_.addEntry(TAG_FORM,"TagForm");
        mappingTags_.addEntry(TAG_SUBMIT,"TagSubmit");
        mappingTags_.addEntry(TAG_IMG,"TagImg");
        mappingTags_.addEntry(TAG_SELECT,"TagSelect");
        mappingTags_.addEntry(TAG_MESSAGE,"TagMessage");
        mappingTags_.addEntry(TAG_A,"TagAnchor");
        mappingTags_.addEntry(TAG_INPUT,"TagInput");
        mappingTags_.addEntry(TAG_TEXTAREA,"TagTextarea");
        mappingTags_.addEntry(TAG_SPAN,"TagSpan");
        mappingTags_.addEntry(TAG_LINK,"TagLink");
        mappingTags_.addEntry(TAG_STYLE,"TagStyle");
        mappingTags_.addEntry(TAG_BODY,"TagBody");
        mappingTags_.addEntry(TAG_HEAD,"TagHead");
        mappingTags_.addEntry(TAG_MAP,"TagMap");
        mappingTags_.addEntry(TAG_LI,"TagLi");
        mappingTags_.addEntry(TAG_OL,"TagOl");
        mappingTags_.addEntry(TAG_UL,"TagUl");
        mappingTags_.addEntry(TAG_B,"TagBold");
        mappingTags_.addEntry(TAG_I,"TagItalic");
        mappingTags_.addEntry(TAG_PRE,"TagPre");
        mappingTags_.addEntry(TAG_H1,"TagHOne");
        mappingTags_.addEntry(TAG_H2,"TagHTwo");
        mappingTags_.addEntry(TAG_H3,"TagHThree");
        mappingTags_.addEntry(TAG_H4,"TagHFour");
        mappingTags_.addEntry(TAG_H5,"TagHFive");
        mappingTags_.addEntry(TAG_H6,"TagHSix");
        mappingTags_.addEntry(TAG_BR,"TagBr");
        mappingTags_.addEntry(TAG_HR,"TagHr");
        mappingTags_.addEntry(TAG_P,"TagPar");
        mappingTags_.addEntry(TAG_TABLE,"TagTable");
        mappingTags_.addEntry(TAG_CAPTION,"TagCaption");
        mappingTags_.addEntry(TAG_TD,"TagTd");
        mappingTags_.addEntry(TAG_TH,"TagTh");
        mappingTags_.addEntry(TAG_TR,"TagTr");
        mappingTags_.addEntry(TAG_DIV,"TagDiv");
        mappingTags_.addEntry(TAG_OPTION,"TagOption");
        mappingTags_.addEntry(TAG_PARAM,"TagParam");
        return mappingTags_;
    }
    public StringMap<String> allAttrs(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(ATTR_CLASSNAME),rendKeyWordsAttrs.getAttrClassName());
        keyWords_.addEntry(_mapping.getVal(ATTR_MULTIPLE),rendKeyWordsAttrs.getAttrMultiple());
        keyWords_.addEntry(_mapping.getVal(ATTR_TYPE),rendKeyWordsAttrs.getAttrType());
        keyWords_.addEntry(_mapping.getVal(ATTR_INDEXCLASSNAME),rendKeyWordsAttrs.getAttrIndexClassName());
        keyWords_.addEntry(_mapping.getVal(ATTR_CONVERTFIELD),rendKeyWordsAttrs.getAttrConvertField());
        keyWords_.addEntry(_mapping.getVal(ATTR_CONVERTFIELDVALUE),rendKeyWordsAttrs.getAttrConvertFieldValue());
        keyWords_.addEntry(_mapping.getVal(ATTR_VALUEMESSAGE),rendKeyWordsAttrs.getAttrValueMessage());
        keyWords_.addEntry(_mapping.getVal(ATTR_ESCAPEDAMP),rendKeyWordsAttrs.getAttrEscapedAmp());
        keyWords_.addEntry(_mapping.getVal(ATTR_KEYCLASSNAME),rendKeyWordsAttrs.getAttrKeyClassName());
        keyWords_.addEntry(_mapping.getVal(ATTR_VARCLASSNAME),rendKeyWordsAttrs.getAttrVarClassName());
        keyWords_.addEntry(_mapping.getVal(ATTR_CONVERTVALUE),rendKeyWordsAttrs.getAttrConvertValue());
        keyWords_.addEntry(_mapping.getVal(ATTR_KEEPFIELDS),rendKeyWordsAttrs.getAttrKeepFields());
        keyWords_.addEntry(_mapping.getVal(ATTR_TITLE),rendKeyWordsAttrs.getAttrTitle());
        keyWords_.addEntry(_mapping.getVal(ATTR_FOR),rendKeyWordsAttrs.getAttrFor());
        keyWords_.addEntry(_mapping.getVal(ATTR_FROM),rendKeyWordsAttrs.getAttrFrom());
        keyWords_.addEntry(_mapping.getVal(ATTR_SELECTED),rendKeyWordsAttrs.getAttrSelected());
        keyWords_.addEntry(_mapping.getVal(ATTR_N_A),rendKeyWordsAttrs.getAttrNa());
        keyWords_.addEntry(_mapping.getVal(ATTR_TO),rendKeyWordsAttrs.getAttrTo());
        keyWords_.addEntry(_mapping.getVal(ATTR_STEP),rendKeyWordsAttrs.getAttrStep());
        keyWords_.addEntry(_mapping.getVal(ATTR_INIT),rendKeyWordsAttrs.getAttrInit());
        keyWords_.addEntry(_mapping.getVal(ATTR_VALUE),rendKeyWordsAttrs.getAttrValue());
        keyWords_.addEntry(_mapping.getVal(ATTR_COMMAND),rendKeyWordsAttrs.getAttrCommand());
        keyWords_.addEntry(_mapping.getVal(ATTR_SGN),rendKeyWordsAttrs.getAttrSgn());
        keyWords_.addEntry(_mapping.getVal(ATTR_CONDITION),rendKeyWordsAttrs.getAttrCondition());
        keyWords_.addEntry(_mapping.getVal(ATTR_HREF),rendKeyWordsAttrs.getAttrHref());
        keyWords_.addEntry(_mapping.getVal(ATTR_MESSAGE),rendKeyWordsAttrs.getAttrMessage());
        keyWords_.addEntry(_mapping.getVal(ATTR_CHECKED),rendKeyWordsAttrs.getAttrChecked());
        keyWords_.addEntry(_mapping.getVal(ATTR_ACTION),rendKeyWordsAttrs.getAttrAction());
        keyWords_.addEntry(_mapping.getVal(ATTR_EQ),rendKeyWordsAttrs.getAttrEq());
        keyWords_.addEntry(_mapping.getVal(ATTR_KEY),rendKeyWordsAttrs.getAttrKey());
        keyWords_.addEntry(_mapping.getVal(ATTR_LABEL),rendKeyWordsAttrs.getAttrLabel());
        keyWords_.addEntry(_mapping.getVal(ATTR_BEAN),rendKeyWordsAttrs.getAttrBean());
        keyWords_.addEntry(_mapping.getVal(ATTR_N_I),rendKeyWordsAttrs.getAttrNi());
        keyWords_.addEntry(_mapping.getVal(ATTR_N_R),rendKeyWordsAttrs.getAttrNr());
        keyWords_.addEntry(_mapping.getVal(ATTR_N_F),rendKeyWordsAttrs.getAttrNf());
        keyWords_.addEntry(_mapping.getVal(ATTR_PARAM),rendKeyWordsAttrs.getAttrParam());
        keyWords_.addEntry(_mapping.getVal(ATTR_ALIAS),rendKeyWordsAttrs.getAttrAlias());
        keyWords_.addEntry(_mapping.getVal(ATTR_QUOTED),rendKeyWordsAttrs.getAttrQuoted());
        keyWords_.addEntry(_mapping.getVal(ATTR_NAME),rendKeyWordsAttrs.getAttrName());
        keyWords_.addEntry(_mapping.getVal(ATTR_MAP),rendKeyWordsAttrs.getAttrMap());
        keyWords_.addEntry(_mapping.getVal(ATTR_VAR),rendKeyWordsAttrs.getAttrVar());
        keyWords_.addEntry(_mapping.getVal(ATTR_LIST),rendKeyWordsAttrs.getAttrList());
        keyWords_.addEntry(_mapping.getVal(ATTR_PREPARE),rendKeyWordsAttrs.getAttrPrepare());
        keyWords_.addEntry(_mapping.getVal(ATTR_FORM),rendKeyWordsAttrs.getAttrForm());
        keyWords_.addEntry(_mapping.getVal(ATTR_VARVALUE),rendKeyWordsAttrs.getAttrVarValue());
        keyWords_.addEntry(_mapping.getVal(ATTR_DELAY),rendKeyWordsAttrs.getAttrDelay());
        keyWords_.addEntry(_mapping.getVal(ATTR_ESCAPED),rendKeyWordsAttrs.getAttrEscaped());
        keyWords_.addEntry(_mapping.getVal(ATTR_PAGE),rendKeyWordsAttrs.getAttrPage());
        keyWords_.addEntry(_mapping.getVal(ATTR_WIDTH),rendKeyWordsAttrs.getAttrWidth());
        keyWords_.addEntry(_mapping.getVal(ATTR_SRC),rendKeyWordsAttrs.getAttrSrc());
        keyWords_.addEntry(_mapping.getVal(ATTR_VALIDATOR),rendKeyWordsAttrs.getAttrValidator());
        keyWords_.addEntry(_mapping.getVal(ATTR_GROUPID),rendKeyWordsAttrs.getAttrGroupId());
        keyWords_.addEntry(_mapping.getVal(ATTR_CONVERT),rendKeyWordsAttrs.getAttrConvert());
        keyWords_.addEntry(_mapping.getVal(ATTR_ROWS),rendKeyWordsAttrs.getAttrRows());
        keyWords_.addEntry(_mapping.getVal(ATTR_ID),rendKeyWordsAttrs.getAttrId());
        keyWords_.addEntry(_mapping.getVal(ATTR_COLS),rendKeyWordsAttrs.getAttrCols());
        keyWords_.addEntry(_mapping.getVal(ATTR_DEFAULT),rendKeyWordsAttrs.getAttrDefault());
        keyWords_.addEntry(_mapping.getVal(ATTR_CLASS),rendKeyWordsAttrs.getAttrClass());
        keyWords_.addEntry(_mapping.getVal(ATTR_REL),rendKeyWordsAttrs.getAttrRel());
        return keyWords_;
    }
    public static StringMap<String> mappingAttrs() {
        StringMap<String> mappingAttrs_ = new StringMap<String>();
        mappingAttrs_.addEntry(ATTR_TYPE,"AttrType");
        mappingAttrs_.addEntry(ATTR_MULTIPLE,"AttrMultiple");
        mappingAttrs_.addEntry(ATTR_CLASSNAME,"AttrClassName");
        mappingAttrs_.addEntry(ATTR_CONVERTFIELD,"AttrConvertField");
        mappingAttrs_.addEntry(ATTR_VALUEMESSAGE,"AttrValueMessage");
        mappingAttrs_.addEntry(ATTR_ESCAPEDAMP,"AttrEscapedAmp");
        mappingAttrs_.addEntry(ATTR_CONVERTVALUE,"AttrConvertValue");
        mappingAttrs_.addEntry(ATTR_CONVERTFIELDVALUE,"AttrConvertFieldValue");
        mappingAttrs_.addEntry(ATTR_VARCLASSNAME,"AttrVarClassName");
        mappingAttrs_.addEntry(ATTR_KEEPFIELDS,"AttrKeepFields");
        mappingAttrs_.addEntry(ATTR_KEYCLASSNAME,"AttrKeyClassName");
        mappingAttrs_.addEntry(ATTR_INDEXCLASSNAME,"AttrIndexClassName");
        mappingAttrs_.addEntry(ATTR_KEY,"AttrKey");
        mappingAttrs_.addEntry(ATTR_VALUE,"AttrValue");
        mappingAttrs_.addEntry(ATTR_EQ,"AttrEq");
        mappingAttrs_.addEntry(ATTR_INIT,"AttrInit");
        mappingAttrs_.addEntry(ATTR_LIST,"AttrList");
        mappingAttrs_.addEntry(ATTR_CONDITION,"AttrCondition");
        mappingAttrs_.addEntry(ATTR_TO,"AttrTo");
        mappingAttrs_.addEntry(ATTR_MAP,"AttrMap");
        mappingAttrs_.addEntry(ATTR_BEAN,"AttrBean");
        mappingAttrs_.addEntry(ATTR_FROM,"AttrFrom");
        mappingAttrs_.addEntry(ATTR_CHECKED,"AttrChecked");
        mappingAttrs_.addEntry(ATTR_SELECTED,"AttrSelected");
        mappingAttrs_.addEntry(ATTR_VAR,"AttrVar");
        mappingAttrs_.addEntry(ATTR_LABEL,"AttrLabel");
        mappingAttrs_.addEntry(ATTR_N_F,"AttrNf");
        mappingAttrs_.addEntry(ATTR_N_A,"AttrNa");
        mappingAttrs_.addEntry(ATTR_ALIAS,"AttrAlias");
        mappingAttrs_.addEntry(ATTR_N_I,"AttrNi");
        mappingAttrs_.addEntry(ATTR_N_R,"AttrNr");
        mappingAttrs_.addEntry(ATTR_STEP,"AttrStep");
        mappingAttrs_.addEntry(ATTR_HREF,"AttrHref");
        mappingAttrs_.addEntry(ATTR_PREPARE,"AttrPrepare");
        mappingAttrs_.addEntry(ATTR_FOR,"AttrFor");
        mappingAttrs_.addEntry(ATTR_QUOTED,"AttrQuoted");
        mappingAttrs_.addEntry(ATTR_ID,"AttrId");
        mappingAttrs_.addEntry(ATTR_CLASS,"AttrClass");
        mappingAttrs_.addEntry(ATTR_ACTION,"AttrAction");
        mappingAttrs_.addEntry(ATTR_PARAM,"AttrParam");
        mappingAttrs_.addEntry(ATTR_MESSAGE,"AttrMessage");
        mappingAttrs_.addEntry(ATTR_COLS,"AttrCols");
        mappingAttrs_.addEntry(ATTR_FORM,"AttrForm");
        mappingAttrs_.addEntry(ATTR_VARVALUE,"AttrVarValue");
        mappingAttrs_.addEntry(ATTR_ROWS,"AttrRows");
        mappingAttrs_.addEntry(ATTR_COMMAND,"AttrCommand");
        mappingAttrs_.addEntry(ATTR_SGN,"AttrSgn");
        mappingAttrs_.addEntry(ATTR_DEFAULT,"AttrDefault");
        mappingAttrs_.addEntry(ATTR_SRC,"AttrSrc");
        mappingAttrs_.addEntry(ATTR_ESCAPED,"AttrEscaped");
        mappingAttrs_.addEntry(ATTR_GROUPID,"AttrGroupId");
        mappingAttrs_.addEntry(ATTR_TITLE,"AttrTitle");
        mappingAttrs_.addEntry(ATTR_VALIDATOR,"AttrValidator");
        mappingAttrs_.addEntry(ATTR_PAGE,"AttrPage");
        mappingAttrs_.addEntry(ATTR_WIDTH,"AttrWidth");
        mappingAttrs_.addEntry(ATTR_DELAY,"AttrDelay");
        mappingAttrs_.addEntry(ATTR_REL,"AttrRel");
        mappingAttrs_.addEntry(ATTR_NAME,"AttrName");
        mappingAttrs_.addEntry(ATTR_CONVERT,"AttrConvert");
        return mappingAttrs_;
    }
    public StringMap<String> allValues(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(VALUE_RANGE),rendKeyWordsValues.getValueRange().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_TEXT),rendKeyWordsValues.getValueText().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_CHECKBOX),rendKeyWordsValues.getValueCheckbox().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_NUMBER),rendKeyWordsValues.getValueNumber().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_RADIO),rendKeyWordsValues.getValueRadio().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_SUBMIT),rendKeyWordsValues.getValueSubmit().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_STYLE),rendKeyWordsValues.getValueStyle().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LIDISK),rendKeyWordsValues.getValueLiDisk().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LIMINLET),rendKeyWordsValues.getValueLiMinLet().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LISQUARE),rendKeyWordsValues.getValueLiSquare().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LINB),rendKeyWordsValues.getValueLiNb().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LIMAJLET),rendKeyWordsValues.getValueLiMajLet().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LIMINLAT),rendKeyWordsValues.getValueLiMinLat().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LIMAJLAT),rendKeyWordsValues.getValueLiMajLat().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LICIRCLE),rendKeyWordsValues.getValueLiCircle().trim());
        keyWords_.addEntry(_mapping.getVal(VALUE_LIRECT),rendKeyWordsValues.getValueLiRect().trim());
        return keyWords_;
    }
    public static StringMap<String> mappingValues() {
        StringMap<String> mappingValues_ = new StringMap<String>();
        mappingValues_.addEntry(VALUE_RANGE,"ValueRange");
        mappingValues_.addEntry(VALUE_TEXT,"ValueText");
        mappingValues_.addEntry(VALUE_CHECKBOX,"ValueCheckbox");
        mappingValues_.addEntry(VALUE_NUMBER,"ValueNumber");
        mappingValues_.addEntry(VALUE_RADIO,"ValueRadio");
        mappingValues_.addEntry(VALUE_SUBMIT,"ValueSubmit");
        mappingValues_.addEntry(VALUE_LIMAJLAT,"ValueLiMajLat");
        mappingValues_.addEntry(VALUE_LIRECT,"ValueLiRect");
        mappingValues_.addEntry(VALUE_STYLE,"ValueStyle");
        mappingValues_.addEntry(VALUE_LIMINLET,"ValueLiMinLet");
        mappingValues_.addEntry(VALUE_LINB,"ValueLiNb");
        mappingValues_.addEntry(VALUE_LIMAJLET,"ValueLiMajLet");
        mappingValues_.addEntry(VALUE_LIDISK,"ValueLiDisk");
        mappingValues_.addEntry(VALUE_LIMINLAT,"ValueLiMinLat");
        mappingValues_.addEntry(VALUE_LICIRCLE,"ValueLiCircle");
        mappingValues_.addEntry(VALUE_LISQUARE,"ValueLiSquare");
        return mappingValues_;
    }
    public StringMap<String> allStyleAttrs(StringMap<String> _mapping){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(STYLE_ATTR_BORDER),rendKeyWordsStyles.getStyleAttrBorder());
        keyWords_.addEntry(_mapping.getVal(STYLE_ATTR_FONTFAM),rendKeyWordsStyles.getStyleAttrFontFam());
        keyWords_.addEntry(_mapping.getVal(STYLE_ATTR_FONTSIZE),rendKeyWordsStyles.getStyleAttrFontSize());
        keyWords_.addEntry(_mapping.getVal(STYLE_ATTR_COLOR),rendKeyWordsStyles.getStyleAttrColor());
        keyWords_.addEntry(_mapping.getVal(STYLE_ATTR_BACKGROUND),rendKeyWordsStyles.getStyleAttrBackground());
        return keyWords_;
    }
    public static StringMap<String> mappingStyleAttrs() {
        StringMap<String> mappingStyleAttrs_ = new StringMap<String>();
        mappingStyleAttrs_.addEntry(STYLE_ATTR_BACKGROUND,"StyleAttrBackground");
        mappingStyleAttrs_.addEntry(STYLE_ATTR_COLOR,"StyleAttrColor");
        mappingStyleAttrs_.addEntry(STYLE_ATTR_BORDER,"StyleAttrBorder");
        mappingStyleAttrs_.addEntry(STYLE_ATTR_FONTFAM,"StyleAttrFontFam");
        mappingStyleAttrs_.addEntry(STYLE_ATTR_FONTSIZE,"StyleAttrFontSize");
        return mappingStyleAttrs_;
    }
    public StringMap<String> allStyleValues(StringMap<String> _mapping){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_GREY),rendKeyWordsStyles.getStyleValueGrey().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_MAGENTA),rendKeyWordsStyles.getStyleValueMagenta().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_GREEN),rendKeyWordsStyles.getStyleValueGreen().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_CYAN),rendKeyWordsStyles.getStyleValueCyan().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_YELLOW),rendKeyWordsStyles.getStyleValueYellow().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_WHITE),rendKeyWordsStyles.getStyleValueWhite().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_BLUE),rendKeyWordsStyles.getStyleValueBlue().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_BLACK),rendKeyWordsStyles.getStyleValueBlack().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_RGB),rendKeyWordsStyles.getStyleValueRgb().trim());
        keyWords_.addEntry(_mapping.getVal(STYLE_VALUE_RED),rendKeyWordsStyles.getStyleValueRed().trim());
        return keyWords_;
    }
    public static StringMap<String> mappingStyleValues() {
        StringMap<String> mappingStyleValues_ = new StringMap<String>();
        mappingStyleValues_.addEntry(STYLE_VALUE_BLUE,"StyleValueBlue");
        mappingStyleValues_.addEntry(STYLE_VALUE_GREY,"StyleValueGrey");
        mappingStyleValues_.addEntry(STYLE_VALUE_GREEN,"StyleValueGreen");
        mappingStyleValues_.addEntry(STYLE_VALUE_BLACK,"StyleValueBlack");
        mappingStyleValues_.addEntry(STYLE_VALUE_MAGENTA,"StyleValueMagenta");
        mappingStyleValues_.addEntry(STYLE_VALUE_YELLOW,"StyleValueYellow");
        mappingStyleValues_.addEntry(STYLE_VALUE_WHITE,"StyleValueWhite");
        mappingStyleValues_.addEntry(STYLE_VALUE_CYAN,"StyleValueCyan");
        mappingStyleValues_.addEntry(STYLE_VALUE_RED,"StyleValueRed");
        mappingStyleValues_.addEntry(STYLE_VALUE_RGB,"StyleValueRgb");
        return mappingStyleValues_;
    }
    public StringMap<String> allStyleUnits(StringMap<String> _mapping){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(STYLE_UNIT_SOLID),rendKeyWordsStyles.getStyleUnitSolid());
        keyWords_.addEntry(_mapping.getVal(STYLE_UNIT_PX),rendKeyWordsStyles.getStyleUnitPx());
        keyWords_.addEntry(_mapping.getVal(STYLE_UNIT_EM),rendKeyWordsStyles.getStyleUnitEm());
        return keyWords_;
    }
    public static StringMap<String> mappingStyleUnits() {
        StringMap<String> mappingStyleUnits_ = new StringMap<String>();
        mappingStyleUnits_.addEntry(STYLE_UNIT_SOLID,"StyleUnitSolid");
        mappingStyleUnits_.addEntry(STYLE_UNIT_PX,"StyleUnitEm");
        mappingStyleUnits_.addEntry(STYLE_UNIT_EM,"StyleUnitPx");
        return mappingStyleUnits_;
    }
    public RendKeyWordsGroup group() {
        return new RendKeyWordsGroup(rendKeyWordsTags,rendKeyWordsAttrs,rendKeyWordsValues,rendKeyWordsStyles,rendKeyWordsDefs);
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

    public void setDefMinLetter(String _styleUnitSolid) {
        this.rendKeyWordsDefs.setDefMinLetter(_styleUnitSolid);
    }

    public void setDefMajLetter(String _styleUnitSolid) {
        this.rendKeyWordsDefs.setDefMajLetter(_styleUnitSolid);
    }

    public void setDefMinLatin(String _styleUnitSolid) {
        this.rendKeyWordsDefs.setDefMinLatin(_styleUnitSolid);
    }

    public void setDefMajLatin(String _styleUnitSolid) {
        this.rendKeyWordsDefs.setDefMajLatin(_styleUnitSolid);
    }

    public void setDefBaseSixtyFour(String _styleUnitSolid) {
        this.rendKeyWordsDefs.setDefBaseSixtyFour(_styleUnitSolid);
    }

}
