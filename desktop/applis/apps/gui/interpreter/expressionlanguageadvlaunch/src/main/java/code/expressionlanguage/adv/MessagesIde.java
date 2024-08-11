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
    private MessagesIde(){
    }
    public static StringMap<String> valInitChoose(TranslationsLg _lg) {
        return appli(_lg).getMapping().getVal(IDE_INIT_CHOOSE_PRO).getMapping();
    }
    public static StringMap<String> valFindText(TranslationsLg _lg) {
        return appli(_lg).getMapping().getVal(IDE_FIND_TEXT).getMapping();
    }
    public static StringMap<String> valFiles(TranslationsLg _lg) {
        return appli(_lg).getMapping().getVal(IDE_FILES).getMapping();
    }
    public static StringMap<String> valAliases(TranslationsLg _lg) {
        return appli(_lg).getMapping().getVal(IDE_ALIASES).getMapping();
    }
    public static TranslationsAppli appli(TranslationsLg _lg) {
        return _lg.getMapping().getVal(FileInfos.CDM);
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        appendInitChoose(_a, enInitChoose());
        appendFindText(_a, enFindText());
        appendFiles(_a, enFiles());
        appendAliases(_a, enAliases());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        appendInitChoose(_a, frInitChoose());
        appendFindText(_a, frFindText());
        appendFiles(_a, frFiles());
        appendAliases(_a, frAliases());
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
}
