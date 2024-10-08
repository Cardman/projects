package code.expressionlanguage.gui.unit;

import code.expressionlanguage.utilcompo.*;
import code.sml.util.*;
import code.util.*;

public final class MessagesCdmFullGui {
    public static final String KEY_FILE = "full_gui";
    public static final String TITLE = "0";
    public static final String FILE = "1";
    public static final String OPEN = "2";
    public static final String CONFIGURATION = "3";
    public static final String LAUNCH = "4";
    public static final String COVERAGE = "5";
    public static final String STOP = "6";
    public static final String EXEC_OPTIONS_TABLE="exec_options_4";
    public static final String EXEC_OPTIONS_TABLE_SUCCESS="0";
    //    public static final String EXEC_OPTIONS_TABLE_FAIL="1";
//    public static final String EXEC_OPTIONS_TABLE_MS="2";
    public static final String EXEC_OPTIONS_TABLE_NUMBER="3";
    public static final String EXEC_OPTIONS_TABLE_METHOD="4";
    public static final String EXEC_OPTIONS_TABLE_PARAMS="5";
    public static final String EXEC_OPTIONS_TABLE_STATUS="6";
    public static final String EXEC_OPTIONS_TABLE_CONFIGURATION="7";
    public static final String EXEC_OPTIONS_TABLE_FILE="8";
    public static final String EXEC_OPTIONS_TABLE_OPEN="9";
    public static final String EXEC_OPTIONS_TABLE_LAUNCH="10";
    public static final String EXEC_OPTIONS_TABLE_STOP="11";
    public static final String EXEC_OPTIONS_TABLE_TESTS="12";
    public static final String EXEC_OPTIONS_TABLE_TITLE="13";
    public static final String EXEC_OPTIONS_SIMPLE="exec_options_5";
    public static final String EXEC_OPTIONS_SIMPLE_LAUNCH="0";
    public static final String EXEC_OPTIONS_SIMPLE_FOLDER="1";
    public static final String EXEC_OPTIONS_SIMPLE_SRC="2";
    public static final String EXEC_OPTIONS_SIMPLE_FILES="3";
    public static final String EXEC_OPTIONS_SIMPLE_MES="exec_options_6";
    public static final String EXEC_OPTIONS_SIMPLE_MES_FAILED_PATH="0";
    public static final String EXEC_OPTIONS_SIMPLE_MES_FAILED_CONTENT="1";
    public static final String EXEC_OPTIONS_SIMPLE_MES_SUCCESS="2";
    public static final String EXEC_OPTIONS_MAIN="exec_options_7";
    public static final String EXEC_OPTIONS_MAIN_ARCHIVE="0";
    public static final String EXEC_OPTIONS_MAIN_MEMORY="1";
    public static final String CONF_VAL = "Configuration";
    public static final String APPS_UNIT = "ug";
    public static final String APPS_LAUNCHER = "launcher";

    private MessagesCdmFullGui() {
    }
    public static StringMap<String> valMessages(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(KEY_FILE).getMapping();
    }
    public static TranslationsAppli updateEnGui(TranslationsAppli _a){
        _a.getMapping().addEntry(KEY_FILE,en());
        return _a;
    }
    public static TranslationsAppli updateFrGui(TranslationsAppli _a){
        _a.getMapping().addEntry(KEY_FILE,fr());
        return _a;
    }
    public static TranslationsFile en(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TITLE,"Launcher");
        en_.add(FILE,"File");
        en_.add(OPEN,"Open");
        en_.add(CONFIGURATION, CONF_VAL);
        en_.add(LAUNCH,"Launch");
        en_.add(COVERAGE,"Coverage");
        en_.add(STOP,"Stop");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TITLE,"Lanceur");
        en_.add(FILE,"Fichier");
        en_.add(OPEN,"Ouvrir");
        en_.add(CONFIGURATION, CONF_VAL);
        en_.add(LAUNCH,"Lancer");
        en_.add(COVERAGE,"Couverture");
        en_.add(STOP,"Arrêter");
        return en_;
    }

    public static StringMap<String> valExecOptionsTable(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_TABLE).getMapping();
    }

    public static StringMap<String> valExecOptionsSimple(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_TABLE).getMapping();
    }

    public static StringMap<String> valExecOptionsSimpleMes(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_SIMPLE_MES).getMapping();
    }

    public static StringMap<String> valExecOptionsMain(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_MAIN).getMapping();
    }

//    public StringMap<String> getMessages() {
//        return valExecOptionsTable(messages.currentLg());
//    }
    public static TranslationsFile enExecOptionsTable(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_TABLE_SUCCESS,"Success");
//        e_.add(EXEC_OPTIONS_TABLE_FAIL,"Fail");
//        e_.add(EXEC_OPTIONS_TABLE_MS,"ms");
        e_.add(EXEC_OPTIONS_TABLE_NUMBER,"Number");
        e_.add(EXEC_OPTIONS_TABLE_METHOD,"Method");
        e_.add(EXEC_OPTIONS_TABLE_PARAMS,"Parameters");
        e_.add(EXEC_OPTIONS_TABLE_STATUS,"Status");
        e_.add(EXEC_OPTIONS_TABLE_CONFIGURATION, CONF_VAL);
        e_.add(EXEC_OPTIONS_TABLE_FILE,"File");
        e_.add(EXEC_OPTIONS_TABLE_OPEN,"Open");
        e_.add(EXEC_OPTIONS_TABLE_LAUNCH,"Launch");
        e_.add(EXEC_OPTIONS_TABLE_STOP,"Stop");
        e_.add(EXEC_OPTIONS_TABLE_TESTS,"Tests");
        e_.add(EXEC_OPTIONS_TABLE_TITLE,"Unit tests");
        return e_;
    }

    public static TranslationsFile frExecOptionsTable(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_TABLE_SUCCESS,"Succès");
//        f_.add(EXEC_OPTIONS_TABLE_FAIL,"Échec");
//        f_.add(EXEC_OPTIONS_TABLE_MS,"ms");
        f_.add(EXEC_OPTIONS_TABLE_NUMBER,"Numéro");
        f_.add(EXEC_OPTIONS_TABLE_METHOD,"Méthode");
        f_.add(EXEC_OPTIONS_TABLE_PARAMS,"Paramètres");
        f_.add(EXEC_OPTIONS_TABLE_STATUS,"Statut");
        f_.add(EXEC_OPTIONS_TABLE_CONFIGURATION, CONF_VAL);
        f_.add(EXEC_OPTIONS_TABLE_FILE,"Fichier");
        f_.add(EXEC_OPTIONS_TABLE_OPEN,"Ouvrir");
        f_.add(EXEC_OPTIONS_TABLE_LAUNCH,"Lancer");
        f_.add(EXEC_OPTIONS_TABLE_STOP,"Stop");
        f_.add(EXEC_OPTIONS_TABLE_TESTS,"Tests");
        f_.add(EXEC_OPTIONS_TABLE_TITLE,"Tests unitaires");
        return f_;
    }

    public static TranslationsFile enExecOptionsSimple(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_SIMPLE_LAUNCH,"Launch by file");
        e_.add(EXEC_OPTIONS_SIMPLE_FOLDER,"Folder");
        e_.add(EXEC_OPTIONS_SIMPLE_SRC,"Sources");
        e_.add(EXEC_OPTIONS_SIMPLE_FILES,"Data");
        return e_;
    }

    public static TranslationsFile frExecOptionsSimple(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_SIMPLE_LAUNCH,"Lancer par fichier");
        f_.add(EXEC_OPTIONS_SIMPLE_FOLDER,"Dossier");
        f_.add(EXEC_OPTIONS_SIMPLE_SRC,"Sources");
        f_.add(EXEC_OPTIONS_SIMPLE_FILES,"Données");
        return f_;
    }

    public static TranslationsFile enExecOptionsSimpleMes(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_PATH,"Fail loading {0} because {1} is not absolute");
        e_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_CONTENT,"Fail loading {0} because of error of reading");
        e_.add(EXEC_OPTIONS_SIMPLE_MES_SUCCESS,"Successful loading {0}");
        return e_;
    }

    public static TranslationsFile frExecOptionsSimpleMes(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_PATH,"Chargement échoué {0} à cause que {1} n''est pas absolu");
        f_.add(EXEC_OPTIONS_SIMPLE_MES_FAILED_CONTENT,"Chargement échoué {0} à cause d''erreur de lecture");
        f_.add(EXEC_OPTIONS_SIMPLE_MES_SUCCESS,"Chargement réussi {0}");
        return f_;
    }

    public static TranslationsFile enExecOptionsMain(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_MAIN_ARCHIVE,"Archive");
        e_.add(EXEC_OPTIONS_MAIN_MEMORY,"Memory");
        return e_;
    }

    public static TranslationsFile frExecOptionsMain(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_MAIN_ARCHIVE,"Archive");
        f_.add(EXEC_OPTIONS_MAIN_MEMORY,"Mémoire");
        return f_;
    }

    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(EXEC_OPTIONS_TABLE, enExecOptionsTable());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE, enExecOptionsSimple());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE_MES, enExecOptionsSimpleMes());
        _a.getMapping().addEntry(EXEC_OPTIONS_MAIN, enExecOptionsMain());
        return updateEnGui(_a);
    }

    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(EXEC_OPTIONS_TABLE, frExecOptionsTable());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE, frExecOptionsSimple());
        _a.getMapping().addEntry(EXEC_OPTIONS_SIMPLE_MES, frExecOptionsSimpleMes());
        _a.getMapping().addEntry(EXEC_OPTIONS_MAIN, frExecOptionsMain());
        return updateFrGui(_a);
    }

    public static TranslationsAppli sys(TranslationsAppli _lgs) {
        _lgs.sys(mes());
        return _lgs;
    }
    public static TranslationsFile mes(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(Translations.TEMP_FOLDER,"ug");
        return t_;
    }
}
