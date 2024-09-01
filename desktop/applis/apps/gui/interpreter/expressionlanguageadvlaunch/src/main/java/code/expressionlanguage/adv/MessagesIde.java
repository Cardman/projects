package code.expressionlanguage.adv;

import code.expressionlanguage.common.MessagesCdmBase;
import code.expressionlanguage.utilcompo.*;
import code.sml.util.*;
import code.util.*;

public final class MessagesIde {
    public static final String IDE_INIT_CHOOSE_PRO = "0";
    public static final String IDE_INIT_PRO = "0";
    public static final String IDE_CHOOSE_PRO = "1";
    public static final String IDE_FIND_TEXT = "1";
    public static final String IDE_FIND_TEXT_SENSITIVE_CASE = "0";
    public static final String IDE_FIND_TEXT_WHOLE_WORD = "1";
    public static final String IDE_FIND_TEXT_CLOSE_FINDER = "2";
    public static final String IDE_FIND_TEXT_GO = "3";
    public static final String IDE_FIND_TEXT_REFRESH = "4";
    public static final String IDE_FIND_TEXT_RESET = "5";
    public static final String IDE_FIND_TEXT_FIND = "6";
    public static final String IDE_FIND_TEXT_CANCEL = "7";
    public static final String IDE_FIND_TEXT_APPLY = "8";
    public static final String IDE_FIND_TEXT_CLOSE_EXPRESSION = "9";
    public static final String IDE_FILES = "2";
    public static final String IDE_FILES_CREATE_VALIDATE = "0";
    public static final String IDE_FILES_CREATE_CANCEL = "1";
    public static final String IDE_FILES_RENAMING_VALIDATE = "2";
    public static final String IDE_FILES_RENAMING_CANCEL = "3";
    public static final String IDE_FILES_REMOVING_VALIDATE = "4";
    public static final String IDE_FILES_REMOVING_CANCEL = "5";
    public static final String IDE_ALIASES = "3";
    public static final String IDE_ALIASES_MESSAGES = "0";
    public static final String IDE_ALIASES_KEY_WORD = "1";
    public static final String IDE_ALIASES_NAMES = "2";
    public static final String IDE_ALIASES_VALIDATE = "3";
    public static final String IDE_ALIASES_CANCEL = "4";
    public static final String IDE_COMMENTS = "4";
    public static final String IDE_COMMENTS_VALIDATE = "0";
    public static final String IDE_LANGUAGE = "5";
    public static final String IDE_LANGUAGE_VALIDATE = "0";
    public static final String IDE_MESSAGES = "6";
    public static final String IDE_MESSAGES_VALIDATE = "0";
    public static final String IDE_SRC = "7";
    public static final String IDE_SRC_VALIDATE = "0";
    public static final String IDE_TAB = "8";
    public static final String IDE_TAB_VALIDATE = "0";
    public static final String IDE_MANAGE_STRING = "9";
    public static final String IDE_MANAGE_STRING_FOLDER = "0";
    public static final String IDE_MANAGE_STRING_VALIDATE = "1";
    public static final String IDE_GLOBAL_PARAMETERS = "10";
    public static final String IDE_GLOBAL_PARAMETERS_DIRECT_MATCH = "0";
    public static final String IDE_GLOBAL_PARAMETERS_VALIDATE = "1";
    public static final String IDE_FIND_REF = "11";
    public static final String IDE_FIND_REF_CALLERS = "0";
    public static final String IDE_FIND_REF_USAGE_ONLY = "1";
    public static final String IDE_FIND_REF_USAGE_AND_DEF = "2";
    public static final String IDE_POINTS_KIND = "12";
    public static final String IDE_POINTS_KIND_STD = "0";
    public static final String IDE_POINTS_KIND_STATIC = "1";
    public static final String IDE_POINTS_KIND_INSTANCE = "2";
    public static final String IDE_POINTS_KIND_LENGTH = "3";
    public static final String IDE_POINTS_KIND_INT_GET = "4";
    public static final String IDE_POINTS_KIND_INT_SET = "5";
    public static final String IDE_POINTS_KIND_INT_COMPOUND_GET = "6";
    public static final String IDE_POINTS_KIND_INT_COMPOUND_SET = "7";
    public static final String IDE_POINTS_KIND_INT_COMPOUND_SET_ERR = "8";
    public static final String IDE_POINTS_KIND_RANGE_GET = "9";
    public static final String IDE_POINTS_KIND_RANGE_SET = "10";
    public static final String IDE_POINTS_KIND_RANGE_COMPOUND_GET = "11";
    public static final String IDE_POINTS_KIND_RANGE_COMPOUND_SET = "12";
    public static final String IDE_POINTS_KIND_INT_GET_SET = "13";
    public static final String IDE_POINTS_KIND_INIT_ARRAY = "14";
    public static final String IDE_POINTS_KIND_CLONE = "15";
    public static final String IDE_POINTS_KIND_THROWN = "16";
    public static final String IDE_POINTS_KIND_CAUGHT = "17";
    public static final String IDE_POINTS_KIND_PROPAGATED = "18";
    public static final String IDE_POINTS_KIND_SIMPLE = "19";
    public static final String IDE_POINTS_KIND_COMPOUND = "20";
    public static final String IDE_POINTS_KIND_GET = "21";
    public static final String IDE_POINTS_KIND_STD_CONSTRUCTOR = "22";
    public static final String IDE_POINTS_KIND_STD_METHOD = "23";
    public static final String IDE_POINTS_KIND_WP_GET = "24";
    public static final String IDE_POINTS_KIND_WP_SET = "25";
    public static final String IDE_POINTS_KIND_WP_COMPOUND_GET = "26";
    public static final String IDE_POINTS_KIND_WP_COMPOUND_SET = "27";
    public static final String IDE_POINTS_KIND_WP_COMPOUND_SET_ERR = "28";
    public static final String IDE_POINTS_KIND_WP_TRUE_FIELD = "29";
    public static final String IDE_POINTS_KIND_ENTRY = "30";
    public static final String IDE_POINTS_KIND_EXIT = "31";
    public static final String IDE_POINTS_KIND_ENABLED = "32";
    public static final String IDE_POINTS_KIND_REMOVE = "33";
    public static final String IDE_POINTS_KIND_VALIDATE = "34";
    public static final String IDE_POINTS_GROUP = "13";
    public static final String IDE_POINTS_GROUP_ALL = "0";
    public static final String IDE_POINTS_GROUP_INS = "1";
    public static final String IDE_POINTS_GROUP_WP = "2";
    public static final String IDE_POINTS_GROUP_WAM = "3";
    public static final String IDE_POINTS_GROUP_EXC = "4";
    public static final String IDE_POINTS_GROUP_CM = "5";
    public static final String IDE_POINTS_GROUP_SM = "6";
    public static final String IDE_POINTS_GROUP_ARR = "7";
    public static final String IDE_POINTS_GROUP_PP = "8";
    public static final String IDE_POINTS_GROUP_NAT = "9";
    public static final String IDE_POINTS_GROUP_NAT_COMP = "10";
    public static final String IDE_POINTS_GROUP_TYPE = "11";
    public static final String IDE_POINTS_INH_FROM_PARAM = "12";
    public static final String IDE_POINTS_FAMILY_PARAM = "13";
    public static final String IDE_POINTS_EXACT_PARAM = "14";
    public static final String IDE_POINTS_INH_FROM = "15";
    public static final String IDE_POINTS_FAMILY = "16";
    public static final String IDE_POINTS_EXACT = "17";
    public static final String IDE_POINTS_POINT_FORM = "14";
    public static final String IDE_POINTS_POINT_FORM_SPEC = "0";
    public static final String IDE_POINTS_POINT_FORM_HIT = "1";
    public static final String IDE_POINTS_POINT_FORM_DIS_HIT = "2";
    public static final String IDE_POINTS_POINT_FORM_DIS_AGAIN = "3";
    public static final String IDE_POINTS_POINT_FORM_DIS_SUSPEND = "4";
    public static final String IDE_POINTS_POINT_FORM_LOG_ST = "5";
    public static final String IDE_POINTS_POINT_FORM_LOG_ST_ERR = "6";
    public static final String IDE_POINTS_POINT_FORM_LOG_ST_RES_ERR = "7";
    public static final String IDE_POINTS_POINT_FORM_MAIN = "8";
    public static final String IDE_POINTS_POINT_FORM_COND = "9";
    public static final String IDE_POINTS_POINT_FORM_LOGS = "10";
    public static final String IDE_POINTS_POINT_FORM_WATCHES = "11";
    public static final String IDE_POINTS_POINT_FORM_CONST = "12";
    public static final String IDE_POINTS_POINT_FORM_DEPS = "13";
    public static final String IDE_POINTS_POINT_FORM_PREF = "14";
    public static final String IDE_POINTS_POINT_FORM_SINGLE = "15";
    public static final String IDE_POINTS_POINT_FORM_INC = "16";
    public static final String IDE_POINTS_POINT_FORM_EXC = "17";
    public static final String IDE_POINTS_REND_FORM = "15";
    public static final String IDE_POINTS_REND_FORM_EN_REND = "0";
    public static final String IDE_POINTS_REND_FORM_EN_EXP = "1";
    public static final String IDE_POINTS_REND_FORM_EN_BOTH_EXP = "2";
    public static final String IDE_POINTS_REND_FORM_EN_BOTH_REND = "3";
    public static final String IDE_POINTS_REND_FORM_EXP = "4";
    public static final String IDE_POINTS_REND_FORM_EXP_REND = "5";
    public static final String IDE_POINTS_REND_FORM_EN_GL = "6";
    public static final String IDE_POINTS_REND_FORM_VALIDATE = "7";
    public static final String IDE_POINTS_REND_FORM_REMOVE = "8";
    public static final String IDE_POINTS_REND_FORM_PREF = "9";
    public static final String IDE_POINTS_GL_FORM = "16";
    public static final String IDE_POINTS_GL_FORM_STACK = "0";
    public static final String IDE_POINTS_GL_FORM_REND = "1";
    public static final String IDE_POINTS_GL_FORM_POINTS = "2";
    public static final String IDE_POINTS_GL_FORM_CONST = "3";
    public static final String IDE_POINTS_GL_FORM_EXACT = "4";
    public static final String IDE_POINTS_GL_FORM_ANNOT = "5";
    public static final String IDE_POINTS_SESSION_FORM = "17";
    public static final String IDE_POINTS_SESSION_FORM_SHOW = "0";
    public static final String IDE_POINTS_SESSION_FORM_VARS = "1";
    public static final String IDE_POINTS_SESSION_FORM_CANCEL_EVAL = "2";
    public static final String IDE_POINTS_SESSION_FORM_REFRESH_RENDER = "3";
    public static final String IDE_POINTS_SESSION_FORM_CANCEL_RENDER = "4";
    public static final String IDE_POINTS_SESSION_FORM_PREPARE = "5";
    public static final String IDE_POINTS_SESSION_FORM_MUTE = "6";
    public static final String IDE_POINTS_SESSION_FORM_EVAL_PAGE = "7";
    public static final String IDE_POINTS_SESSION_FORM_EVAL_NO_PAGE = "8";
    public static final String IDE_POINTS_SESSION_FORM_CALLS = "9";
    public static final String IDE_POINTS_SESSION_FORM_RENDER_CALCULATION = "10";
    public static final String IDE_POINTS_SESSION_FORM_DYNAMIC = "11";
    public static final String IDE_POINTS_SESSION_FORM_STATUS = "12";
    public static final String IDE_POINTS_SESSION_FORM_LOGS = "13";
    public static final String IDE_POINTS_SESSION_FORM_RENDER = "14";
    public static final String IDE_POINTS_SESSION_FORM_MENU = "15";
    public static final String IDE_POINTS_SESSION_FORM_ANALYZE = "16";
    public static final String IDE_POINTS_SESSION_FORM_OPEN_POINTS = "17";
    public static final String IDE_POINTS_FORMS = "18";
    public static final String IDE_POINTS_FORMS_CLASS = "0";
    public static final String IDE_POINTS_FORMS_VARARG = "1";
    public static final String IDE_POINTS_FORMS_RETURN = "2";
    public static final String IDE_POINTS_FORMS_PARAM = "3";
    public static final String IDE_POINTS_FRAMES = "19";
    public static final String IDE_POINTS_FRAMES_FILE = "0";
    public static final String IDE_POINTS_FRAMES_SRC = "1";
    public static final String IDE_POINTS_FRAMES_CREATE = "2";
    public static final String IDE_POINTS_FRAMES_DELETE = "3";
    public static final String IDE_POINTS_FRAMES_PARAMETERS = "4";
    public static final String IDE_POINTS_FRAMES_LANGUAGE = "5";
    public static final String IDE_POINTS_FRAMES_TABULATIONS = "6";
    public static final String IDE_POINTS_FRAMES_COMMENTS = "7";
    public static final String IDE_POINTS_FRAMES_ALIASES = "8";
    public static final String IDE_POINTS_FRAMES_FIND = "9";
    public static final String IDE_POINTS_FRAMES_EVENT = "10";
    public static final String IDE_POINTS_FRAMES_OPEN = "11";
    public static final String IDE_POINTS_FRAMES_FOLDER_EXP = "12";
    public static final String IDE_POINTS_FRAMES_CONF = "13";
    public static final String IDE_POINTS_FRAMES_RUN = "14";
    public static final String IDE_POINTS_FRAMES_ANALYZE = "15";
    public static final String IDE_POINTS_FRAMES_STATUS = "16";
    public static final String IDE_POINTS_FRAMES_CHOOSE_FOLDER = "17";
    public static final String IDE_POINTS_FRAMES_CREATE_FILE = "18";
    public static final String IDE_POINTS_FRAMES_SESSION_EXP = "19";
    public static final String IDE_POINTS_FRAMES_SESSION_SINGLE_MAIN = "20";
    public static final String CDM_EDITOR = "cdm_editor";
    public static final String BEGIN_ENCODE = "&#";
    public static final String END_ENCODE = ";";
    public static final String SPAN = "span";
    public static final String HTML = "html";
    public static final String STYLE = "style";
    public static final String COLOR = MessagesCdmBase.COLOR;
    public static final String BACKGROUND_COLOR = MessagesCdmBase.BACKGROUND_COLOR;
    public static final String BLACK = "000000";
    public static final String WHITE = "ffffff";
    public static final String RED = "ff0000";
    public static final String CYAN = "00ffff";
    public static final String EXT_SPACE = "&#160;";

    private MessagesIde(){
    }
    public static StringMap<String> valInitChoose(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_INIT_CHOOSE_PRO).getMapping();
    }
    public static StringMap<String> valFindText(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_FIND_TEXT).getMapping();
    }
    public static StringMap<String> valFiles(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_FILES).getMapping();
    }
    public static StringMap<String> valAliases(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_ALIASES).getMapping();
    }
    public static StringMap<String> valComments(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_COMMENTS).getMapping();
    }
    public static StringMap<String> valLanguages(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_LANGUAGE).getMapping();
    }
    public static StringMap<String> valMessages(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_MESSAGES).getMapping();
    }
    public static StringMap<String> valSrc(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_SRC).getMapping();
    }
    public static StringMap<String> valTab(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_TAB).getMapping();
    }
    public static StringMap<String> valManageString(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_MANAGE_STRING).getMapping();
    }
    public static StringMap<String> valGlobalParameters(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_GLOBAL_PARAMETERS).getMapping();
    }
    public static StringMap<String> valFindRef(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_FIND_REF).getMapping();
    }
    public static StringMap<String> valPointsKind(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_KIND).getMapping();
    }
    public static StringMap<String> valGroup(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_GROUP).getMapping();
    }
    public static StringMap<String> valPointForm(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_POINT_FORM).getMapping();
    }
    public static StringMap<String> valRendForm(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_REND_FORM).getMapping();
    }
    public static StringMap<String> valGlForm(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_GL_FORM).getMapping();
    }
    public static StringMap<String> valSessionForm(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_SESSION_FORM).getMapping();
    }
    public static StringMap<String> valForms(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_FORMS).getMapping();
    }
    public static StringMap<String> valFrames(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(IDE_POINTS_FRAMES).getMapping();
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        appendInitChoose(_a, enInitChoose());
        appendFindText(_a, enFindText());
        appendFiles(_a, enFiles());
        appendAliases(_a, enAliases());
        appendComments(_a, enComments());
        appendLanguage(_a, enLanguage());
        appendMessages(_a, enMessages());
        appendSrc(_a, enSrc());
        appendTab(_a, enTab());
        appendFolderExp(_a, enFolderExp());
        appendGlobalParameters(_a, enGlobalParameters());
        appendFindRef(_a, enFindRef());
        appendPointsKind(_a, enPointsKind());
        appendGroup(_a, enGroup());
        appendPointForm(_a, enPointForm());
        appendRendForm(_a, enRendForm());
        appendGlForm(_a, enGlForm());
        appendSessionForm(_a, enSessionForm());
        appendPointsForms(_a, enPointsForms());
        appendFrames(_a, enFrames());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        appendInitChoose(_a, frInitChoose());
        appendFindText(_a, frFindText());
        appendFiles(_a, frFiles());
        appendAliases(_a, frAliases());
        appendComments(_a, frComments());
        appendLanguage(_a, frLanguage());
        appendMessages(_a, frMessages());
        appendSrc(_a, frSrc());
        appendTab(_a, frTab());
        appendFolderExp(_a, frFolderExp());
        appendGlobalParameters(_a, frGlobalParameters());
        appendFindRef(_a, frFindRef());
        appendPointsKind(_a, frPointsKind());
        appendGroup(_a, frGroup());
        appendPointForm(_a, frPointForm());
        appendRendForm(_a, frRendForm());
        appendGlForm(_a, frGlForm());
        appendSessionForm(_a, frSessionForm());
        appendPointsForms(_a, frPointsForms());
        appendFrames(_a, frFrames());
        return _a;
    }
    public static void appendInitChoose(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_INIT_CHOOSE_PRO, _v);
    }
    public static TranslationsFile enInitChoose(){
        TranslationsFile e_ = new TranslationsFile(2);
        e_.add(IDE_INIT_PRO,"Finished initializing project");
        e_.add(IDE_CHOOSE_PRO,"Finished loading project");
        return e_;
    }
    public static TranslationsFile frInitChoose(){
        TranslationsFile f_ = new TranslationsFile(2);
        f_.add(IDE_INIT_PRO,"Initialisation finie du projet");
        f_.add(IDE_CHOOSE_PRO,"Chargement fini du projet");
        return f_;
    }
    public static void appendFindText(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_FIND_TEXT, _v);
    }
    public static TranslationsFile enFindText(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_FIND_TEXT_SENSITIVE_CASE,"Case sensitive (Aa)");
        e_.add(IDE_FIND_TEXT_WHOLE_WORD,"Whole word");
        e_.add(IDE_FIND_TEXT_CLOSE_FINDER,"Close text finder/replacer");
        e_.add(IDE_FIND_TEXT_GO,"Go to line");
        e_.add(IDE_FIND_TEXT_REFRESH,"Refresh");
        e_.add(IDE_FIND_TEXT_RESET,"Reset");
        e_.add(IDE_FIND_TEXT_FIND,"Find by coded macros");
        e_.add(IDE_FIND_TEXT_CANCEL,"Cancel finding expression");
        e_.add(IDE_FIND_TEXT_APPLY,"Apply replacement by coded macros");
        e_.add(IDE_FIND_TEXT_CLOSE_EXPRESSION,"Close expression finder/replacer");
        return e_;
    }
    public static TranslationsFile frFindText(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_FIND_TEXT_SENSITIVE_CASE,"Sensible à la casse (Aa)");
        f_.add(IDE_FIND_TEXT_WHOLE_WORD,"Mot complet");
        f_.add(IDE_FIND_TEXT_CLOSE_FINDER,"Fermer la recherche/remplacement de texte");
        f_.add(IDE_FIND_TEXT_GO,"Aller à la ligne");
        f_.add(IDE_FIND_TEXT_REFRESH,"Rafraîchir");
        f_.add(IDE_FIND_TEXT_RESET,"Remettre à zéro");
        f_.add(IDE_FIND_TEXT_FIND,"Rechercher par macros codées");
        f_.add(IDE_FIND_TEXT_CANCEL,"Annuler la recherche d'expressions");
        f_.add(IDE_FIND_TEXT_APPLY,"Appliquer le remplacement par macros codées");
        f_.add(IDE_FIND_TEXT_CLOSE_EXPRESSION,"Fermer la recherche/remplacement d'expression");
        return f_;
    }
    public static void appendFiles(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_FILES, _v);
    }
    public static TranslationsFile enFiles(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_FILES_CREATE_VALIDATE,"Create");
        e_.add(IDE_FILES_CREATE_CANCEL,"Cancel creating");
        e_.add(IDE_FILES_RENAMING_VALIDATE,"Rename");
        e_.add(IDE_FILES_RENAMING_CANCEL,"Cancel renaming");
        e_.add(IDE_FILES_REMOVING_VALIDATE,"Remove");
        e_.add(IDE_FILES_REMOVING_CANCEL,"Cancel removing");
        return e_;
    }
    public static TranslationsFile frFiles(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_FILES_CREATE_VALIDATE,"Créer");
        f_.add(IDE_FILES_CREATE_CANCEL,"Annuler la création");
        f_.add(IDE_FILES_RENAMING_VALIDATE,"Renommer");
        f_.add(IDE_FILES_RENAMING_CANCEL,"Annuler le renommage");
        f_.add(IDE_FILES_REMOVING_VALIDATE,"Supprimer");
        f_.add(IDE_FILES_REMOVING_CANCEL,"Annuler la suppression");
        return f_;
    }
    public static void appendAliases(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_ALIASES, _v);
    }
    public static TranslationsFile enAliases(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_ALIASES_MESSAGES,"messages");
        e_.add(IDE_ALIASES_KEY_WORD,"key words");
        e_.add(IDE_ALIASES_NAMES,"aliases");
        e_.add(IDE_ALIASES_VALIDATE,"Validate words");
        e_.add(IDE_ALIASES_CANCEL,"Cancel editing words");
        return e_;
    }
    public static TranslationsFile frAliases(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_ALIASES_MESSAGES,"messages");
        f_.add(IDE_ALIASES_KEY_WORD,"mots clés");
        f_.add(IDE_ALIASES_NAMES,"alias");
        f_.add(IDE_ALIASES_VALIDATE,"Valider les mots");
        f_.add(IDE_ALIASES_CANCEL,"Annuler l'édition des mots");
        return f_;
    }
    public static void appendComments(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_COMMENTS, _v);
    }
    public static TranslationsFile enComments(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_COMMENTS_VALIDATE,"validate comments");
        return e_;
    }
    public static TranslationsFile frComments(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_COMMENTS_VALIDATE,"valider les commentaires");
        return f_;
    }
    public static void appendLanguage(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_LANGUAGE, _v);
    }
    public static TranslationsFile enLanguage(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_LANGUAGE_VALIDATE,"validate language");
        return e_;
    }
    public static TranslationsFile frLanguage(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_LANGUAGE_VALIDATE,"valider la langue");
        return f_;
    }
    public static void appendMessages(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_MESSAGES, _v);
    }
    public static TranslationsFile enMessages(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_MESSAGES_VALIDATE,"validate messages match");
        return e_;
    }
    public static TranslationsFile frMessages(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_MESSAGES_VALIDATE,"valider la correspondance de messages");
        return f_;
    }
    public static void appendSrc(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_SRC, _v);
    }
    public static TranslationsFile enSrc(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_SRC_VALIDATE,"validate source folder");
        return e_;
    }
    public static TranslationsFile frSrc(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_SRC_VALIDATE,"valider le dossier source");
        return f_;
    }
    public static void appendTab(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_TAB, _v);
    }
    public static TranslationsFile enTab(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_TAB_VALIDATE,"validate spaces count by tabulation");
        return e_;
    }
    public static TranslationsFile frTab(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_TAB_VALIDATE,"valider le nombre d'espaces pour les tabulations");
        return f_;
    }
    public static void appendFolderExp(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_MANAGE_STRING, _v);
    }
    public static TranslationsFile enFolderExp(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_MANAGE_STRING_FOLDER,"Choose a folder");
        e_.add(IDE_MANAGE_STRING_VALIDATE,"Validate string management space creation");
        return e_;
    }
    public static TranslationsFile frFolderExp(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_MANAGE_STRING_FOLDER,"Choisir un dossier");
        f_.add(IDE_MANAGE_STRING_VALIDATE,"valider la création de l'espace de gestion de chaînes");
        return f_;
    }
    public static void appendGlobalParameters(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_GLOBAL_PARAMETERS, _v);
    }
    public static TranslationsFile enGlobalParameters(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_GLOBAL_PARAMETERS_DIRECT_MATCH,"Directly do matching between key and value");
        e_.add(IDE_GLOBAL_PARAMETERS_VALIDATE,"Validate options");
        return e_;
    }
    public static TranslationsFile frGlobalParameters(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_GLOBAL_PARAMETERS_DIRECT_MATCH,"Faire directement la correspondance entre la clé et la valeur");
        f_.add(IDE_GLOBAL_PARAMETERS_VALIDATE,"valider les options");
        return f_;
    }
    public static void appendFindRef(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_FIND_REF, _v);
    }
    public static TranslationsFile enFindRef(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_FIND_REF_CALLERS,"callers");
        e_.add(IDE_FIND_REF_USAGE_ONLY,"usages only");
        e_.add(IDE_FIND_REF_USAGE_AND_DEF,"usages and definition");
        return e_;
    }
    public static TranslationsFile frFindRef(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_FIND_REF_CALLERS,"appelants");
        f_.add(IDE_FIND_REF_USAGE_ONLY,"utilisations uniquement");
        f_.add(IDE_FIND_REF_USAGE_AND_DEF,"utilisations et définition");
        return f_;
    }
    public static void appendPointsKind(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_KIND, _v);
    }
    public static TranslationsFile enPointsKind(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_KIND_STD,"standard");
        e_.add(IDE_POINTS_KIND_STATIC,"static");
        e_.add(IDE_POINTS_KIND_INSTANCE,"instance");
        e_.add(IDE_POINTS_KIND_LENGTH,"array length");
        e_.add(IDE_POINTS_KIND_INT_GET,"array get phase by index");
        e_.add(IDE_POINTS_KIND_INT_SET,"array set phase by index");
        e_.add(IDE_POINTS_KIND_INT_COMPOUND_GET,"array compound get phase by index");
        e_.add(IDE_POINTS_KIND_INT_COMPOUND_SET,"array compound set phase by index");
        e_.add(IDE_POINTS_KIND_INT_COMPOUND_SET_ERR,"array compound set phase by index when error");
        e_.add(IDE_POINTS_KIND_RANGE_GET,"array get by range");
        e_.add(IDE_POINTS_KIND_RANGE_SET,"array set by range");
        e_.add(IDE_POINTS_KIND_RANGE_COMPOUND_GET,"array compound get phase by range");
        e_.add(IDE_POINTS_KIND_RANGE_COMPOUND_SET,"array compound set phase by range");
        e_.add(IDE_POINTS_KIND_INT_GET_SET,"array get and set phase");
        e_.add(IDE_POINTS_KIND_INIT_ARRAY,"array initialisation phase");
        e_.add(IDE_POINTS_KIND_CLONE,"array clone phase");
        e_.add(IDE_POINTS_KIND_THROWN,"thrown exception");
        e_.add(IDE_POINTS_KIND_CAUGHT,"caught exception");
        e_.add(IDE_POINTS_KIND_PROPAGATED,"propagated exception");
        e_.add(IDE_POINTS_KIND_SIMPLE,"single native operation");
        e_.add(IDE_POINTS_KIND_COMPOUND,"compound native operation");
        e_.add(IDE_POINTS_KIND_GET,"get parent");
        e_.add(IDE_POINTS_KIND_STD_CONSTRUCTOR,"native constructor");
        e_.add(IDE_POINTS_KIND_STD_METHOD,"native method");
        e_.add(IDE_POINTS_KIND_WP_GET,"field get phase");
        e_.add(IDE_POINTS_KIND_WP_SET,"field set phase");
        e_.add(IDE_POINTS_KIND_WP_COMPOUND_GET,"field compound get phase");
        e_.add(IDE_POINTS_KIND_WP_COMPOUND_SET,"field compound set phase");
        e_.add(IDE_POINTS_KIND_WP_COMPOUND_SET_ERR,"field compound set phase when error");
        e_.add(IDE_POINTS_KIND_WP_TRUE_FIELD,"true field");
        e_.add(IDE_POINTS_KIND_ENTRY,"method entry");
        e_.add(IDE_POINTS_KIND_EXIT,"method exit");
        e_.add(IDE_POINTS_KIND_ENABLED,"enabled point");
        e_.add(IDE_POINTS_KIND_REMOVE,"remove point");
        e_.add(IDE_POINTS_KIND_VALIDATE,"validate point");
        return e_;
    }
    public static TranslationsFile frPointsKind(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_KIND_STD,"standard");
        f_.add(IDE_POINTS_KIND_STATIC,"static");
        f_.add(IDE_POINTS_KIND_INSTANCE,"instance");
        f_.add(IDE_POINTS_KIND_LENGTH,"longueur de tableau");
        f_.add(IDE_POINTS_KIND_INT_GET,"phase de récupération d'élément de tableau par indice");
        f_.add(IDE_POINTS_KIND_INT_SET,"phase de mise à jour d'élément de tableau par indice");
        f_.add(IDE_POINTS_KIND_INT_COMPOUND_GET,"phase de récupération composée d'élément de tableau par indice");
        f_.add(IDE_POINTS_KIND_INT_COMPOUND_SET,"phase de mise à jour composée d'élément de tableau par indice");
        f_.add(IDE_POINTS_KIND_INT_COMPOUND_SET_ERR,"phase de mise à jour composée d'élément de tableau par indice lors d'une erreur");
        f_.add(IDE_POINTS_KIND_RANGE_GET,"phase de récupération d'élément de tableau par intervalle");
        f_.add(IDE_POINTS_KIND_RANGE_SET,"phase de mise à jour d'élément de tableau par intervalle");
        f_.add(IDE_POINTS_KIND_RANGE_COMPOUND_GET,"phase de récupération composée d'élément de tableau par intervalle");
        f_.add(IDE_POINTS_KIND_RANGE_COMPOUND_SET,"phase de mise à jour composée d'élément de tableau par intervalle");
        f_.add(IDE_POINTS_KIND_INT_GET_SET,"phase de récupération et de mise à jour d'élément de tableau");
        f_.add(IDE_POINTS_KIND_INIT_ARRAY,"phase d'initialisation de tableau");
        f_.add(IDE_POINTS_KIND_CLONE,"phase de clonage de tableau");
        f_.add(IDE_POINTS_KIND_THROWN,"exception levée");
        f_.add(IDE_POINTS_KIND_CAUGHT,"exception capturée");
        f_.add(IDE_POINTS_KIND_PROPAGATED,"exception propagée");
        f_.add(IDE_POINTS_KIND_SIMPLE,"opération native simple");
        f_.add(IDE_POINTS_KIND_COMPOUND,"opération native composée");
        f_.add(IDE_POINTS_KIND_GET,"récupération du parent");
        f_.add(IDE_POINTS_KIND_STD_CONSTRUCTOR,"constructeur natif");
        f_.add(IDE_POINTS_KIND_STD_METHOD,"méthode native");
        f_.add(IDE_POINTS_KIND_WP_GET,"phase de récupération de champ");
        f_.add(IDE_POINTS_KIND_WP_SET,"phase de mise à jour de champ");
        f_.add(IDE_POINTS_KIND_WP_COMPOUND_GET,"phase de récupération composée de champ");
        f_.add(IDE_POINTS_KIND_WP_COMPOUND_SET,"phase de mise à jour composée de champ");
        f_.add(IDE_POINTS_KIND_WP_COMPOUND_SET_ERR,"phase de mise à jour composée de champ lors d'une erreur");
        f_.add(IDE_POINTS_KIND_WP_TRUE_FIELD,"champ réel");
        f_.add(IDE_POINTS_KIND_ENTRY,"entrée de méthode");
        f_.add(IDE_POINTS_KIND_EXIT,"sortie de méthode");
        f_.add(IDE_POINTS_KIND_ENABLED,"point actif");
        f_.add(IDE_POINTS_KIND_REMOVE,"supprimer point");
        f_.add(IDE_POINTS_KIND_VALIDATE,"valider point");
        return f_;
    }
    public static void appendGroup(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_GROUP, _v);
    }
    public static TranslationsFile enGroup(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_GROUP_ALL,"break points");
        e_.add(IDE_POINTS_GROUP_INS,"instruction");
        e_.add(IDE_POINTS_GROUP_WP,"watch field");
        e_.add(IDE_POINTS_GROUP_WAM,"watch annotation method");
        e_.add(IDE_POINTS_GROUP_EXC,"exception");
        e_.add(IDE_POINTS_GROUP_CM,"custom method");
        e_.add(IDE_POINTS_GROUP_SM,"standard method");
        e_.add(IDE_POINTS_GROUP_ARR,"array point");
        e_.add(IDE_POINTS_GROUP_PP,"parent point");
        e_.add(IDE_POINTS_GROUP_NAT,"native operator point");
        e_.add(IDE_POINTS_GROUP_NAT_COMP,"native compound operator point");
        e_.add(IDE_POINTS_GROUP_TYPE,"type points");
        e_.add(IDE_POINTS_INH_FROM_PARAM,"inherit from {0}");
        e_.add(IDE_POINTS_FAMILY_PARAM,"all types family in {0}");
        e_.add(IDE_POINTS_EXACT_PARAM,"exact type as {0}");
        e_.add(IDE_POINTS_INH_FROM,"inherit");
        e_.add(IDE_POINTS_FAMILY,"same family");
        e_.add(IDE_POINTS_EXACT,"same type");
        return e_;
    }
    public static TranslationsFile frGroup(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_GROUP_ALL,"points d'arrêt");
        f_.add(IDE_POINTS_GROUP_INS,"instruction");
        f_.add(IDE_POINTS_GROUP_WP,"regard du champ");
        f_.add(IDE_POINTS_GROUP_WAM,"regard de méthode d'annotation");
        f_.add(IDE_POINTS_GROUP_EXC,"exception");
        f_.add(IDE_POINTS_GROUP_CM,"méthode personnalisée");
        f_.add(IDE_POINTS_GROUP_SM,"méthode standard");
        f_.add(IDE_POINTS_GROUP_ARR,"point de tableau");
        f_.add(IDE_POINTS_GROUP_PP,"point de parent");
        f_.add(IDE_POINTS_GROUP_NAT,"point d'opérateur natif");
        f_.add(IDE_POINTS_GROUP_NAT_COMP,"point d'opérateur composé natif");
        f_.add(IDE_POINTS_GROUP_TYPE,"points de type");
        f_.add(IDE_POINTS_INH_FROM_PARAM,"hérite de {0}");
        f_.add(IDE_POINTS_FAMILY_PARAM,"tous la famille de types que {0}");
        f_.add(IDE_POINTS_EXACT_PARAM,"le même type que {0}");
        f_.add(IDE_POINTS_INH_FROM,"hérite");
        f_.add(IDE_POINTS_FAMILY,"même famille");
        f_.add(IDE_POINTS_EXACT,"même type");
        return f_;
    }
    public static void appendPointForm(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_POINT_FORM, _v);
    }
    public static TranslationsFile enPointForm(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_POINT_FORM_SPEC,"specific enabled");
        e_.add(IDE_POINTS_POINT_FORM_HIT,"hit");
        e_.add(IDE_POINTS_POINT_FORM_DIS_HIT,"disabled when hit");
        e_.add(IDE_POINTS_POINT_FORM_DIS_AGAIN,"disable again");
        e_.add(IDE_POINTS_POINT_FORM_DIS_SUSPEND,"suspend");
        e_.add(IDE_POINTS_POINT_FORM_LOG_ST,"log stack trace");
        e_.add(IDE_POINTS_POINT_FORM_LOG_ST_ERR,"log stack trace if conditional err");
        e_.add(IDE_POINTS_POINT_FORM_LOG_ST_RES_ERR,"log stack trace result if conditional err");
        e_.add(IDE_POINTS_POINT_FORM_MAIN,"main elements");
        e_.add(IDE_POINTS_POINT_FORM_COND,"conditional");
        e_.add(IDE_POINTS_POINT_FORM_LOGS,"logs");
        e_.add(IDE_POINTS_POINT_FORM_WATCHES,"watches");
        e_.add(IDE_POINTS_POINT_FORM_CONST,"contraints pass");
        e_.add(IDE_POINTS_POINT_FORM_DEPS,"dependencies");
        e_.add(IDE_POINTS_POINT_FORM_PREF,"preference about stack");
        e_.add(IDE_POINTS_POINT_FORM_SINGLE,"single");
        e_.add(IDE_POINTS_POINT_FORM_INC,"add include");
        e_.add(IDE_POINTS_POINT_FORM_EXC,"add exclude");
        return e_;
    }
    public static TranslationsFile frPointForm(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_POINT_FORM_SPEC,"spécifique actif");
        f_.add(IDE_POINTS_POINT_FORM_HIT,"atteint");
        f_.add(IDE_POINTS_POINT_FORM_DIS_HIT,"désactivé lorsqu'atteint");
        f_.add(IDE_POINTS_POINT_FORM_DIS_AGAIN,"désactivé à nouveau");
        f_.add(IDE_POINTS_POINT_FORM_DIS_SUSPEND,"suspendre");
        f_.add(IDE_POINTS_POINT_FORM_LOG_ST,"afficher la pile d'appel");
        f_.add(IDE_POINTS_POINT_FORM_LOG_ST_ERR,"afficher la pile d'appel lors d'une erreur conditionnelle");
        f_.add(IDE_POINTS_POINT_FORM_LOG_ST_RES_ERR,"afficher le résultat de la pile d'appel lors d'une erreur conditionnelle");
        f_.add(IDE_POINTS_POINT_FORM_MAIN,"éléments principaux");
        f_.add(IDE_POINTS_POINT_FORM_COND,"conditionnel");
        f_.add(IDE_POINTS_POINT_FORM_LOGS,"afficheurs");
        f_.add(IDE_POINTS_POINT_FORM_WATCHES,"regards");
        f_.add(IDE_POINTS_POINT_FORM_CONST,"contrainte de passage");
        f_.add(IDE_POINTS_POINT_FORM_DEPS,"dépendances");
        f_.add(IDE_POINTS_POINT_FORM_PREF,"préférence à propos de la pile");
        f_.add(IDE_POINTS_POINT_FORM_SINGLE,"simple");
        f_.add(IDE_POINTS_POINT_FORM_INC,"ajout d'inclusion");
        f_.add(IDE_POINTS_POINT_FORM_EXC,"ajout d'exclusion");
        return f_;
    }
    public static void appendRendForm(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_REND_FORM, _v);
    }
    public static TranslationsFile enRendForm(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_REND_FORM_EN_REND,"enabled custom render");
        e_.add(IDE_POINTS_REND_FORM_EN_EXP,"enabled expand");
        e_.add(IDE_POINTS_REND_FORM_EN_BOTH_EXP,"enabled both (expand first)");
        e_.add(IDE_POINTS_REND_FORM_EN_BOTH_REND,"enabled both (render first)");
        e_.add(IDE_POINTS_REND_FORM_EXP,"expand first");
        e_.add(IDE_POINTS_REND_FORM_EXP_REND,"expand render order");
        e_.add(IDE_POINTS_REND_FORM_EN_GL,"enabled global");
        e_.add(IDE_POINTS_REND_FORM_VALIDATE,"ok");
        e_.add(IDE_POINTS_REND_FORM_REMOVE,"remove render point");
        e_.add(IDE_POINTS_REND_FORM_PREF,"preference about render");
        return e_;
    }
    public static TranslationsFile frRendForm(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_REND_FORM_EN_REND,"rendu personnalisé actif");
        f_.add(IDE_POINTS_REND_FORM_EN_EXP,"expansion active");
        f_.add(IDE_POINTS_REND_FORM_EN_BOTH_EXP,"les deux actifs (expansion en premier)");
        f_.add(IDE_POINTS_REND_FORM_EN_BOTH_REND,"les deux actifs (rendu en premier)");
        f_.add(IDE_POINTS_REND_FORM_EXP,"étendre en premier");
        f_.add(IDE_POINTS_REND_FORM_EXP_REND,"ordre d'expansion de rendu");
        f_.add(IDE_POINTS_REND_FORM_EN_GL,"globalement actif");
        f_.add(IDE_POINTS_REND_FORM_VALIDATE,"ok");
        f_.add(IDE_POINTS_REND_FORM_REMOVE,"supprimer le point de rendu");
        f_.add(IDE_POINTS_REND_FORM_PREF,"préférence à propos du rendu");
        return f_;
    }
    public static void appendGlForm(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_GL_FORM, _v);
    }
    public static TranslationsFile enGlForm(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_GL_FORM_STACK,"validate constraints stack for stepping into");
        e_.add(IDE_POINTS_GL_FORM_REND,"render points");
        e_.add(IDE_POINTS_GL_FORM_POINTS,"custom points");
        e_.add(IDE_POINTS_GL_FORM_CONST,"constraints");
        e_.add(IDE_POINTS_GL_FORM_EXACT,"real field");
        e_.add(IDE_POINTS_GL_FORM_ANNOT,"annotation method");
        return e_;
    }
    public static TranslationsFile frGlForm(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_GL_FORM_STACK,"valider les contraintes de pile pour entrer dans le corps de fonction");
        f_.add(IDE_POINTS_GL_FORM_REND,"points de rendu");
        f_.add(IDE_POINTS_GL_FORM_POINTS,"points personnalisés");
        f_.add(IDE_POINTS_GL_FORM_CONST,"contraintes");
        f_.add(IDE_POINTS_GL_FORM_EXACT,"champ réel");
        f_.add(IDE_POINTS_GL_FORM_ANNOT,"méthode d'annotation");
        return f_;
    }
    public static void appendSessionForm(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_SESSION_FORM, _v);
    }
    public static TranslationsFile enSessionForm(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_SESSION_FORM_SHOW,"show render");
        e_.add(IDE_POINTS_SESSION_FORM_VARS,"variables");
        e_.add(IDE_POINTS_SESSION_FORM_CANCEL_EVAL,"stop evaluating");
        e_.add(IDE_POINTS_SESSION_FORM_REFRESH_RENDER,"refresh render");
        e_.add(IDE_POINTS_SESSION_FORM_CANCEL_RENDER,"stop rendering");
        e_.add(IDE_POINTS_SESSION_FORM_PREPARE,"prepare session");
        e_.add(IDE_POINTS_SESSION_FORM_MUTE,"mute");
        e_.add(IDE_POINTS_SESSION_FORM_EVAL_PAGE,"eval page");
        e_.add(IDE_POINTS_SESSION_FORM_EVAL_NO_PAGE,"eval no page");
        e_.add(IDE_POINTS_SESSION_FORM_CALLS,"calls");
        e_.add(IDE_POINTS_SESSION_FORM_RENDER_CALCULATION,"render calculation");
        e_.add(IDE_POINTS_SESSION_FORM_DYNAMIC,"dynamic");
        e_.add(IDE_POINTS_SESSION_FORM_STATUS,"status");
        e_.add(IDE_POINTS_SESSION_FORM_LOGS,"logs");
        e_.add(IDE_POINTS_SESSION_FORM_RENDER,"render");
        e_.add(IDE_POINTS_SESSION_FORM_MENU,"session");
        e_.add(IDE_POINTS_SESSION_FORM_ANALYZE,"analyze");
        e_.add(IDE_POINTS_SESSION_FORM_OPEN_POINTS,"open points");
        return e_;
    }
    public static TranslationsFile frSessionForm(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_SESSION_FORM_SHOW,"afficher le rendu");
        f_.add(IDE_POINTS_SESSION_FORM_VARS,"variables");
        f_.add(IDE_POINTS_SESSION_FORM_CANCEL_EVAL,"arrêter l'évaluation");
        f_.add(IDE_POINTS_SESSION_FORM_REFRESH_RENDER,"rafraîchir le rendu");
        f_.add(IDE_POINTS_SESSION_FORM_CANCEL_RENDER,"arrêter le rendu");
        f_.add(IDE_POINTS_SESSION_FORM_PREPARE,"préparer la session");
        f_.add(IDE_POINTS_SESSION_FORM_MUTE,"ignorer");
        f_.add(IDE_POINTS_SESSION_FORM_EVAL_PAGE,"évaluer avec l'élément de pile");
        f_.add(IDE_POINTS_SESSION_FORM_EVAL_NO_PAGE,"évaluer sans l'élément de pile");
        f_.add(IDE_POINTS_SESSION_FORM_CALLS,"appels");
        f_.add(IDE_POINTS_SESSION_FORM_RENDER_CALCULATION,"calcul de rendu");
        f_.add(IDE_POINTS_SESSION_FORM_DYNAMIC,"dynamique");
        f_.add(IDE_POINTS_SESSION_FORM_STATUS,"statut");
        f_.add(IDE_POINTS_SESSION_FORM_LOGS,"affichage");
        f_.add(IDE_POINTS_SESSION_FORM_RENDER,"rendu");
        f_.add(IDE_POINTS_SESSION_FORM_MENU,"session");
        f_.add(IDE_POINTS_SESSION_FORM_ANALYZE,"analyse");
        f_.add(IDE_POINTS_SESSION_FORM_OPEN_POINTS,"ouvrir les points");
        return f_;
    }
    public static void appendPointsForms(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_FORMS, _v);
    }
    public static TranslationsFile enPointsForms(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_FORMS_CLASS,"select a class");
        e_.add(IDE_POINTS_FORMS_VARARG,"vararg");
        e_.add(IDE_POINTS_FORMS_RETURN,"return");
        e_.add(IDE_POINTS_FORMS_PARAM,"params");
        return e_;
    }
    public static TranslationsFile frPointsForms(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_FORMS_CLASS,"sélectionner une classe");
        f_.add(IDE_POINTS_FORMS_VARARG,"variadique");
        f_.add(IDE_POINTS_FORMS_RETURN,"retourner une référence");
        f_.add(IDE_POINTS_FORMS_PARAM,"paramétrer une référence");
        return f_;
    }
    public static void appendFrames(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_POINTS_FRAMES, _v);
    }
    public static TranslationsFile enFrames(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(IDE_POINTS_FRAMES_FILE,"file");
        e_.add(IDE_POINTS_FRAMES_SRC,"inputs output conf");
        e_.add(IDE_POINTS_FRAMES_CREATE,"new");
        e_.add(IDE_POINTS_FRAMES_DELETE,"delete");
        e_.add(IDE_POINTS_FRAMES_PARAMETERS,"parameters");
        e_.add(IDE_POINTS_FRAMES_LANGUAGE,"language");
        e_.add(IDE_POINTS_FRAMES_TABULATIONS,"tabulations");
        e_.add(IDE_POINTS_FRAMES_COMMENTS,"comments");
        e_.add(IDE_POINTS_FRAMES_ALIASES,"aliases");
        e_.add(IDE_POINTS_FRAMES_FIND,"find");
        e_.add(IDE_POINTS_FRAMES_EVENT,"event");
        e_.add(IDE_POINTS_FRAMES_OPEN,"open");
        e_.add(IDE_POINTS_FRAMES_FOLDER_EXP,"folder exp");
        e_.add(IDE_POINTS_FRAMES_CONF,"soft conf");
        e_.add(IDE_POINTS_FRAMES_RUN,"run");
        e_.add(IDE_POINTS_FRAMES_ANALYZE,"analyze");
        e_.add(IDE_POINTS_FRAMES_STATUS,"status");
        e_.add(IDE_POINTS_FRAMES_CHOOSE_FOLDER,"choose a folder");
        e_.add(IDE_POINTS_FRAMES_CREATE_FILE,"create a file");
        e_.add(IDE_POINTS_FRAMES_SESSION_EXP,"session exp");
        e_.add(IDE_POINTS_FRAMES_SESSION_SINGLE_MAIN,"session single main");
        return e_;
    }
    public static TranslationsFile frFrames(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(IDE_POINTS_FRAMES_FILE,"fichier");
        f_.add(IDE_POINTS_FRAMES_SRC,"entrées sorties conf");
        f_.add(IDE_POINTS_FRAMES_CREATE,"nouveau");
        f_.add(IDE_POINTS_FRAMES_DELETE,"supprimer");
        f_.add(IDE_POINTS_FRAMES_PARAMETERS,"paramètres");
        f_.add(IDE_POINTS_FRAMES_LANGUAGE,"langage");
        f_.add(IDE_POINTS_FRAMES_TABULATIONS,"tabulations");
        f_.add(IDE_POINTS_FRAMES_COMMENTS,"commentaires");
        f_.add(IDE_POINTS_FRAMES_ALIASES,"alias");
        f_.add(IDE_POINTS_FRAMES_FIND,"rechercher");
        f_.add(IDE_POINTS_FRAMES_EVENT,"événement");
        f_.add(IDE_POINTS_FRAMES_OPEN,"ouvrir");
        f_.add(IDE_POINTS_FRAMES_FOLDER_EXP,"dossier pour gestion d'expressions");
        f_.add(IDE_POINTS_FRAMES_CONF,"configuration du logiciel");
        f_.add(IDE_POINTS_FRAMES_RUN,"exécuter");
        f_.add(IDE_POINTS_FRAMES_ANALYZE,"analyser");
        f_.add(IDE_POINTS_FRAMES_STATUS,"statut");
        f_.add(IDE_POINTS_FRAMES_CHOOSE_FOLDER,"choisir un dossier");
        f_.add(IDE_POINTS_FRAMES_CREATE_FILE,"créer un fichier");
        f_.add(IDE_POINTS_FRAMES_SESSION_EXP,"session sur un gestion d'expression");
        f_.add(IDE_POINTS_FRAMES_SESSION_SINGLE_MAIN,"session sur une simple méthode principale");
        return f_;
    }
}
