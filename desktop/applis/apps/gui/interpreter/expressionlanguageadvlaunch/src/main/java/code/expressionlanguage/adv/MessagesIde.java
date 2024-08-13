package code.expressionlanguage.adv;

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
        return _a;
    }
    public static void appendInitChoose(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_INIT_CHOOSE_PRO, _v);
    }
    public static TranslationsFile enInitChoose(){
        TranslationsFile t_ = new TranslationsFile(2);
        t_.add(IDE_INIT_PRO,"Finished initializing project");
        t_.add(IDE_CHOOSE_PRO,"Finished loading project");
        return t_;
    }
    public static TranslationsFile frInitChoose(){
        TranslationsFile t_ = new TranslationsFile(2);
        t_.add(IDE_INIT_PRO,"Initialisation finie du projet");
        t_.add(IDE_CHOOSE_PRO,"Chargement fini du projet");
        return t_;
    }
    public static void appendFindText(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_FIND_TEXT, _v);
    }
    public static TranslationsFile enFindText(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_FIND_TEXT_SENSITIVE_CASE,"Case sensitive (Aa)");
        t_.add(IDE_FIND_TEXT_WHOLE_WORD,"Whole word");
        t_.add(IDE_FIND_TEXT_CLOSE_FINDER,"Close text finder/replacer");
        t_.add(IDE_FIND_TEXT_GO,"Go to line");
        t_.add(IDE_FIND_TEXT_REFRESH,"Refresh");
        t_.add(IDE_FIND_TEXT_RESET,"Reset");
        t_.add(IDE_FIND_TEXT_FIND,"Find by coded macros");
        t_.add(IDE_FIND_TEXT_CANCEL,"Cancel finding expression");
        t_.add(IDE_FIND_TEXT_APPLY,"Apply replacement by coded macros");
        t_.add(IDE_FIND_TEXT_CLOSE_EXPRESSION,"Close expression finder/replacer");
        return t_;
    }
    public static TranslationsFile frFindText(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_FIND_TEXT_SENSITIVE_CASE,"Sensible à la casse (Aa)");
        t_.add(IDE_FIND_TEXT_WHOLE_WORD,"Mot complet");
        t_.add(IDE_FIND_TEXT_CLOSE_FINDER,"Fermer la recherche/remplacement de texte");
        t_.add(IDE_FIND_TEXT_GO,"Aller à la ligne");
        t_.add(IDE_FIND_TEXT_REFRESH,"Rafraîchir");
        t_.add(IDE_FIND_TEXT_RESET,"Remettre à zéro");
        t_.add(IDE_FIND_TEXT_FIND,"Rechercher par macros codées");
        t_.add(IDE_FIND_TEXT_CANCEL,"Annuler la recherche d'expressions");
        t_.add(IDE_FIND_TEXT_APPLY,"Appliquer le remplacement par macros codées");
        t_.add(IDE_FIND_TEXT_CLOSE_EXPRESSION,"Fermer la recherche/remplacement d'expression");
        return t_;
    }
    public static void appendFiles(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_FILES, _v);
    }
    public static TranslationsFile enFiles(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_FILES_CREATE_VALIDATE,"Create");
        t_.add(IDE_FILES_CREATE_CANCEL,"Cancel creating");
        t_.add(IDE_FILES_RENAMING_VALIDATE,"Rename");
        t_.add(IDE_FILES_RENAMING_CANCEL,"Cancel renaming");
        t_.add(IDE_FILES_REMOVING_VALIDATE,"Remove");
        t_.add(IDE_FILES_REMOVING_CANCEL,"Cancel removing");
        return t_;
    }
    public static TranslationsFile frFiles(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_FILES_CREATE_VALIDATE,"Créer");
        t_.add(IDE_FILES_CREATE_CANCEL,"Annuler la création");
        t_.add(IDE_FILES_RENAMING_VALIDATE,"Renommer");
        t_.add(IDE_FILES_RENAMING_CANCEL,"Annuler le renommage");
        t_.add(IDE_FILES_REMOVING_VALIDATE,"Supprimer");
        t_.add(IDE_FILES_REMOVING_CANCEL,"Annuler la suppression");
        return t_;
    }
    public static void appendAliases(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_ALIASES, _v);
    }
    public static TranslationsFile enAliases(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_ALIASES_MESSAGES,"messages");
        t_.add(IDE_ALIASES_KEY_WORD,"key words");
        t_.add(IDE_ALIASES_NAMES,"aliases");
        t_.add(IDE_ALIASES_VALIDATE,"Validate words");
        t_.add(IDE_ALIASES_CANCEL,"Cancel editing words");
        return t_;
    }
    public static TranslationsFile frAliases(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_ALIASES_MESSAGES,"messages");
        t_.add(IDE_ALIASES_KEY_WORD,"mots clés");
        t_.add(IDE_ALIASES_NAMES,"alias");
        t_.add(IDE_ALIASES_VALIDATE,"Valider les mots");
        t_.add(IDE_ALIASES_CANCEL,"Annuler l'édition des mots");
        return t_;
    }
    public static void appendComments(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_COMMENTS, _v);
    }
    public static TranslationsFile enComments(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_COMMENTS_VALIDATE,"validate comments");
        return t_;
    }
    public static TranslationsFile frComments(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_COMMENTS_VALIDATE,"valider les commentaires");
        return t_;
    }
    public static void appendLanguage(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_LANGUAGE, _v);
    }
    public static TranslationsFile enLanguage(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_LANGUAGE_VALIDATE,"validate language");
        return t_;
    }
    public static TranslationsFile frLanguage(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_LANGUAGE_VALIDATE,"valider la langue");
        return t_;
    }
    public static void appendMessages(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_MESSAGES, _v);
    }
    public static TranslationsFile enMessages(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_MESSAGES_VALIDATE,"validate messages match");
        return t_;
    }
    public static TranslationsFile frMessages(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_MESSAGES_VALIDATE,"valider la correspondance de messages");
        return t_;
    }
    public static void appendSrc(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_SRC, _v);
    }
    public static TranslationsFile enSrc(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_SRC_VALIDATE,"validate source folder");
        return t_;
    }
    public static TranslationsFile frSrc(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_SRC_VALIDATE,"valider le dossier source");
        return t_;
    }
    public static void appendTab(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_TAB, _v);
    }
    public static TranslationsFile enTab(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_TAB_VALIDATE,"validate spaces count by tabulation");
        return t_;
    }
    public static TranslationsFile frTab(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_TAB_VALIDATE,"valider le nombre d'espaces pour les tabulations");
        return t_;
    }
    public static void appendFolderExp(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_MANAGE_STRING, _v);
    }
    public static TranslationsFile enFolderExp(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_MANAGE_STRING_FOLDER,"Choose a folder");
        t_.add(IDE_MANAGE_STRING_VALIDATE,"Validate string management space creation");
        return t_;
    }
    public static TranslationsFile frFolderExp(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_MANAGE_STRING_FOLDER,"Choisir un dossier");
        t_.add(IDE_MANAGE_STRING_VALIDATE,"valider la création de l'espace de gestion de chaînes");
        return t_;
    }
    public static void appendGlobalParameters(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_GLOBAL_PARAMETERS, _v);
    }
    public static TranslationsFile enGlobalParameters(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_GLOBAL_PARAMETERS_DIRECT_MATCH,"Directly do matching between key and value");
        t_.add(IDE_GLOBAL_PARAMETERS_VALIDATE,"Validate options");
        return t_;
    }
    public static TranslationsFile frGlobalParameters(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_GLOBAL_PARAMETERS_DIRECT_MATCH,"Faire directement la correspondance entre la clé et la valeur");
        t_.add(IDE_GLOBAL_PARAMETERS_VALIDATE,"valider les options");
        return t_;
    }
    public static void appendFindRef(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_FIND_REF, _v);
    }
    public static TranslationsFile enFindRef(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_FIND_REF_CALLERS,"callers");
        t_.add(IDE_FIND_REF_USAGE_ONLY,"usages only");
        t_.add(IDE_FIND_REF_USAGE_AND_DEF,"usages and definition");
        return t_;
    }
    public static TranslationsFile frFindRef(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(IDE_FIND_REF_CALLERS,"appelants");
        t_.add(IDE_FIND_REF_USAGE_ONLY,"utilisations uniquement");
        t_.add(IDE_FIND_REF_USAGE_AND_DEF,"utilisations et définition");
        return t_;
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
}
