package code.expressionlanguage.utilcompo;

import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.StringMap;

public final class MessagesExecutingOptions {
    public static final String EXEC_OPTIONS_PREF="exec_options_0";
    public static final String EXEC_OPTIONS_PREF_DIRECTORY="0";
    public static final String EXEC_OPTIONS_PREF_FILE="1";
    public static final String EXEC_OPTIONS_FOLDER="exec_options_1";
    public static final String EXEC_OPTIONS_FOLDER_SRC="0";
    public static final String EXEC_OPTIONS_FOLDER_LOG="1";
    public static final String EXEC_OPTIONS_FOLDER_MAIN_THREAD="2";
    public static final String EXEC_OPTIONS_FOLDER_FILES="3";
    public static final String EXEC_OPTIONS_FOLDER_RESOURCES="4";
    public static final String EXEC_OPTIONS_FOLDER_OUTPUT="5";
    public static final String EXEC_OPTIONS_FOLDER_COVERAGE="6";
    public static final String EXEC_OPTIONS_FOLDER_ERRORS="7";
    public static final String EXEC_OPTIONS_KEY="exec_options_2";
    public static final String EXEC_OPTIONS_KEY_LOG="0";
    public static final String EXEC_OPTIONS_KEY_LGS="1";
    public static final String EXEC_OPTIONS_KEY_COVER="2";
    public static final String EXEC_OPTIONS_KEY_ERR="3";
    public static final String EXEC_OPTIONS_KEY_SRC="4";
    public static final String EXEC_OPTIONS_KEY_SEED="5";
    public static final String EXEC_OPTIONS_KEY_IMPL="6";
    public static final String EXEC_OPTIONS_KEY_IMPL_LABEL="7";
    public static final String EXEC_OPTIONS_KEY_WARN="8";
    public static final String EXEC_OPTIONS_KEY_RES="9";
    public static final String EXEC_OPTIONS_KEY_FILES="10";
    public static final String EXEC_OPTIONS_KEY_OUT="11";
    public static final String EXEC_OPTIONS_KEY_TABWIDTH="12";
    public static final String EXEC_OPTIONS_KEY_INVOKEDIRECT="13";
    public static final String EXEC_OPTIONS_KEY_ARGS="14";
    public static final String EXEC_OPTIONS_KEY_CLASSES="15";
    public static final String EXEC_OPTIONS_KEY_ALIASES="16";
    public static final String EXEC_OPTIONS_KEY_MESSAGES="17";
    public static final String EXEC_OPTIONS_KEY_KEYWORDS="18";
    public static final String EXEC_OPTIONS_KEY_COMMENTS="19";
    public static final String EXEC_OPTIONS_KEY_MAIN="20";
    public static final String EXEC_OPTIONS_KEY_INIT_DB="21";
    public static final String EXEC_OPTIONS_EXT="exec_options_3";
    public static final String EXEC_OPTIONS_EXT_VAL="0";

    private MessagesExecutingOptions() {
    }

    public static StringMap<String> valExecOptions(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_PREF).getMapping();
    }

    public static StringMap<String> valExecOptionsFolder(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_FOLDER).getMapping();
    }

    public static StringMap<String> valExecOptionsKeys(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_KEY).getMapping();
    }

    public static StringMap<String> valExecOptionsExts(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(EXEC_OPTIONS_EXT).getMapping();
    }

    public static TranslationsAppli updateEn(TranslationsAppli _a){
        appendExecOptions(_a, mesExecOptions());
        appendExecOptionsFolder(_a, enExecOptionsFolder());
        appendExecOptionsKeys(_a, enExecOptionsKeys());
        _a.getMapping().addEntry(EXEC_OPTIONS_EXT,mesExecOptionsExt());
        return _a;
    }

    public static TranslationsAppli updateFr(TranslationsAppli _a){
        appendExecOptions(_a, mesExecOptions());
        appendExecOptionsFolder(_a, frExecOptionsFolder());
        appendExecOptionsKeys(_a, frExecOptionsKeys());
        _a.getMapping().addEntry(EXEC_OPTIONS_EXT,mesExecOptionsExt());
        return _a;
    }

    public static void appendExecOptions(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(EXEC_OPTIONS_PREF, _v);
    }

    public static TranslationsFile mesExecOptions(){
        TranslationsFile t_ = new TranslationsFile(2);
        t_.add(EXEC_OPTIONS_PREF_DIRECTORY,"d");
        t_.add(EXEC_OPTIONS_PREF_FILE,"f");
        return t_;
    }

    public static void appendExecOptionsFolder(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(EXEC_OPTIONS_FOLDER, _v);
    }

    public static TranslationsFile enExecOptionsFolder(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_FOLDER_SRC,"src");
        e_.add(EXEC_OPTIONS_FOLDER_LOG,"logs");
        e_.add(EXEC_OPTIONS_FOLDER_MAIN_THREAD,"main_thread.txt");
        e_.add(EXEC_OPTIONS_FOLDER_FILES,"files");
        e_.add(EXEC_OPTIONS_FOLDER_RESOURCES,"res");
        e_.add(EXEC_OPTIONS_FOLDER_OUTPUT,"out/results.zip");
        e_.add(EXEC_OPTIONS_FOLDER_COVERAGE,"coverage");
        e_.add(EXEC_OPTIONS_FOLDER_ERRORS,"errors");
        return e_;
    }

    public static TranslationsFile frExecOptionsFolder(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_FOLDER_SRC,"src");
        f_.add(EXEC_OPTIONS_FOLDER_LOG,"sortie");
        f_.add(EXEC_OPTIONS_FOLDER_MAIN_THREAD,"pcp_tache.txt");
        f_.add(EXEC_OPTIONS_FOLDER_FILES,"fichiers");
        f_.add(EXEC_OPTIONS_FOLDER_RESOURCES,"res");
        f_.add(EXEC_OPTIONS_FOLDER_OUTPUT,"sortie_archive/valeurs.zip");
        f_.add(EXEC_OPTIONS_FOLDER_COVERAGE,"couverture");
        f_.add(EXEC_OPTIONS_FOLDER_ERRORS,"erreurs");
        return f_;
    }

    public static void appendExecOptionsKeys(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(EXEC_OPTIONS_KEY, _v);
    }

    public static TranslationsFile enExecOptionsKeys(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EXEC_OPTIONS_KEY_LOG,"log");
        e_.add(EXEC_OPTIONS_KEY_LGS,"lgs");
        e_.add(EXEC_OPTIONS_KEY_COVER,"cover");
        e_.add(EXEC_OPTIONS_KEY_ERR,"err");
        e_.add(EXEC_OPTIONS_KEY_SRC,"src");
        e_.add(EXEC_OPTIONS_KEY_SEED,"seed");
        e_.add(EXEC_OPTIONS_KEY_IMPL,"impl");
        e_.add(EXEC_OPTIONS_KEY_IMPL_LABEL,"impl_label");
        e_.add(EXEC_OPTIONS_KEY_WARN,"warn");
        e_.add(EXEC_OPTIONS_KEY_RES,"res");
        e_.add(EXEC_OPTIONS_KEY_FILES,"files");
        e_.add(EXEC_OPTIONS_KEY_OUT,"out");
        e_.add(EXEC_OPTIONS_KEY_TABWIDTH,"tabWidth");
        e_.add(EXEC_OPTIONS_KEY_INVOKEDIRECT,"invokeDirect");
        e_.add(EXEC_OPTIONS_KEY_ARGS,"args");
        e_.add(EXEC_OPTIONS_KEY_CLASSES,"classes");
        e_.add(EXEC_OPTIONS_KEY_ALIASES,"aliases");
        e_.add(EXEC_OPTIONS_KEY_MESSAGES,"messages");
        e_.add(EXEC_OPTIONS_KEY_KEYWORDS,"keyWords");
        e_.add(EXEC_OPTIONS_KEY_COMMENTS,"comments");
        e_.add(EXEC_OPTIONS_KEY_MAIN,"main");
        e_.add(EXEC_OPTIONS_KEY_INIT_DB,"initDb");
        return e_;
    }

    public static TranslationsFile frExecOptionsKeys(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EXEC_OPTIONS_KEY_LOG,"log");
        f_.add(EXEC_OPTIONS_KEY_LGS,"lgs");
        f_.add(EXEC_OPTIONS_KEY_COVER,"couverture");
        f_.add(EXEC_OPTIONS_KEY_ERR,"err");
        f_.add(EXEC_OPTIONS_KEY_SRC,"src");
        f_.add(EXEC_OPTIONS_KEY_SEED,"graine");
        f_.add(EXEC_OPTIONS_KEY_IMPL,"impl");
        f_.add(EXEC_OPTIONS_KEY_IMPL_LABEL,"impl_eq");
        f_.add(EXEC_OPTIONS_KEY_WARN,"avert");
        f_.add(EXEC_OPTIONS_KEY_RES,"res");
        f_.add(EXEC_OPTIONS_KEY_FILES,"fichiers");
        f_.add(EXEC_OPTIONS_KEY_OUT,"sortie");
        f_.add(EXEC_OPTIONS_KEY_TABWIDTH,"tabulationLargeur");
        f_.add(EXEC_OPTIONS_KEY_INVOKEDIRECT,"invoqDirect");
        f_.add(EXEC_OPTIONS_KEY_ARGS,"args");
        f_.add(EXEC_OPTIONS_KEY_CLASSES,"classes");
        f_.add(EXEC_OPTIONS_KEY_ALIASES,"aliases");
        f_.add(EXEC_OPTIONS_KEY_MESSAGES,"messages");
        f_.add(EXEC_OPTIONS_KEY_KEYWORDS,"motsCles");
        f_.add(EXEC_OPTIONS_KEY_COMMENTS,"comments");
        f_.add(EXEC_OPTIONS_KEY_MAIN,"pcp");
        f_.add(EXEC_OPTIONS_KEY_INIT_DB,"initBd");
        return f_;
    }

    public static TranslationsFile mesExecOptionsExt(){
        TranslationsFile t_ = new TranslationsFile(1);
        t_.add(EXEC_OPTIONS_EXT_VAL,".txt");
        return t_;
    }
}
