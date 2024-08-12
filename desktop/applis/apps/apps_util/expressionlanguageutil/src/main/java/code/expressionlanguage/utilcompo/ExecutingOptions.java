package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.Options;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.sml.util.*;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ExecutingOptions {

    public static final String EXEC_OPTIONS_SEP="=";
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
    private String baseFiles="";
    private String srcFolder;
    private String logFolder;
    private String mainThread;
    private String files;
    private String resources;
    private String outputZip;
    private String outputFolder = "";
    private String output = "";
    private boolean covering;
    private String coverFolder;
    private String errorsFolder;
    private boolean invokeDirect;
    private boolean hasArg;
    private StringList args = new StringList();
    private StringList warns = new StringList();
    private String access = "";
    private String lg = "";
    private final StringList lgs = new StringList();
    private StringMap<String> messages = new StringMap<String>();
    private StringMap<String> keyWords = new StringMap<String>();
    private StringMap<String> aliases = new StringMap<String>();
    private AbstractLightProgramInfos lightProgramInfos;
    private AbstractInterceptor interceptor;
    private FileSystemParameterizing fileSystemParameterizing;

    public ExecutingOptions(AbstractLightProgramInfos _api) {
        this(_api.currentLg());
        setLightProgramInfos(_api);
    }

    public ExecutingOptions(TranslationsLg _lg) {
        this(valExecOptions(_lg),valExecOptionsFolder(_lg));
    }
    public ExecutingOptions(StringMap<String> _prefs, StringMap<String> _folders) {
        setFileSystemParameterizing(new FileSystemParameterizing(_prefs.getVal(EXEC_OPTIONS_PREF_DIRECTORY),new StringBuilder(),new Ints(), _prefs.getVal(EXEC_OPTIONS_PREF_FILE),new StringBuilder(),new Ints()));
        srcFolder = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_SRC));
        logFolder = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_LOG));
        mainThread = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_MAIN_THREAD));
        files = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_FILES));
        resources = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_RESOURCES));
        outputZip = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_OUTPUT));
        coverFolder = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_COVERAGE));
        errorsFolder = StringUtil.nullToEmpty(_folders.getVal(EXEC_OPTIONS_FOLDER_ERRORS));
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
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        appendExecOptions(_a, mesExecOptions());
        appendExecOptionsFolder(_a, enExecOptionsFolder());
        appendExecOptionsKeys(_a, enExecOptionsKeys());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        appendExecOptions(_a, mesExecOptions());
        appendExecOptionsFolder(_a, frExecOptionsFolder());
        appendExecOptionsKeys(_a, frExecOptionsKeys());
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
        return f_;
    }
    public static StringList lines(String _content) {
        StringList lines_ = StringUtil.splitStrings(_content, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
        return linesFiles_;
    }
    public static void setupOptionals(int _from, Options _options, ExecutingOptions _exec, StringList _lines) {
        StringMap<String> mesKeys_ = valExecOptionsKeys(_exec.lightProgramInfos.currentLg());
        String log_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_LOG);
        String lgs_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_LGS);
        String cov_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_COVER);
        String err_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_ERR);
        String src_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_SRC);
        String seed_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_SEED);
        String imp_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_IMPL);
        String impLab_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_IMPL_LABEL);
        String w_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_WARN);
        String res_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_RES);
        String files_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_FILES);
        String out_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_OUT);
        String tabWidth_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_TABWIDTH);
        String invDir_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_INVOKEDIRECT);
        String args_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_ARGS);
        String cls_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_CLASSES);
        String als_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_ALIASES);
        String mess_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_MESSAGES);
        String kw_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_KEYWORDS);
        String com_ = mesKeys_.getVal(EXEC_OPTIONS_KEY_COMMENTS);
        StringBuilder argParts_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder messagesPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
        StringBuilder classesPart_ = new StringBuilder();
        StringBuilder warnsPart_ = new StringBuilder();
        for (String l: _lines.mid(_from)) {
            extractLog(_exec, l, log_);
            extractLgs(_exec, l, lgs_);
            extractCover(_options, _exec, l, cov_);
            extractErr(_options, _exec, l, err_);
            extractSrc(_options, _exec, l, src_);
            extractSeed(_options, l, seed_);
            extractImpl(_options, l, imp_);
            extractImplLabel(_options, l, impLab_);
            extractParts(warnsPart_, l, w_+EXEC_OPTIONS_SEP);
            extractResources(_exec, l, res_);
            extractFiles(_exec, l, files_);
            extractOut(_exec, l, out_);
            extractTab(_options, l, tabWidth_);
            extractInvoke(_exec, l, invDir_);
            extractArgs(_exec, argParts_, l, args_);
            extractParts(classesPart_, l, cls_+EXEC_OPTIONS_SEP);
            extractParts(aliasesPart_, l, als_+EXEC_OPTIONS_SEP);
            extractParts(messagesPart_, l, mess_+EXEC_OPTIONS_SEP);
            extractParts(keyWordsPart_, l, kw_+EXEC_OPTIONS_SEP);
            extractComments(_options, l, com_);
        }
        finishArgs(_exec, argParts_);
        finishInit(_options, classesPart_);
        finishWarns(_exec, warnsPart_);
        finishAlias(_exec, aliasesPart_);
        finishMessages(_exec, messagesPart_);
        finishKeywords(_exec, keyWordsPart_);
    }

    private static void finishKeywords(ExecutingOptions _exec, StringBuilder _keyWordsPart) {
        if (_keyWordsPart.length() > 0) {
            StringMap<String> kw_ = new StringMap<String>();
            ParseLinesArgUtil.buildMap(_keyWordsPart,kw_);
            _exec.setKeyWords(kw_);
        }
    }

    private static void finishMessages(ExecutingOptions _exec, StringBuilder _messagesPart) {
        if (_messagesPart.length() > 0) {
            StringMap<String> kw_ = new StringMap<String>();
            ParseLinesArgUtil.buildMap(_messagesPart,kw_);
            _exec.setMessages(kw_);
        }
    }

    private static void finishAlias(ExecutingOptions _exec, StringBuilder _aliasesPart) {
        if (_aliasesPart.length() > 0) {
            StringMap<String> al_ = new StringMap<String>();
            ParseLinesArgUtil.buildMap(_aliasesPart,al_);
            _exec.setAliases(al_);
        }
    }

    private static void finishWarns(ExecutingOptions _exec, StringBuilder _warnsPart) {
        if (_warnsPart.length() > 0) {
            StringList ws_ = new StringList();
            ParseLinesArgUtil.buildList(_warnsPart,ws_);
            _exec.setWarns(ws_);
        }
    }

    private static void finishInit(Options _options, StringBuilder _classesPart) {
        if (_classesPart.length() > 0) {
            _options.getTypesInit().addAllElts(ParseLinesArgUtil.parseLineArg(_classesPart.toString()));
        }
    }

    private static void finishArgs(ExecutingOptions _exec, StringBuilder _argParts) {
        if (_exec.isHasArg()) {
            _exec.setArgs(ParseLinesArgUtil.parseLineArg(_argParts.toString()));
        }
    }

    private static void extractComments(Options _options, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            CustList<CommentDelimiters> comments_ = ParseLinesArgUtil.buildComments(_l.substring((_pref +EXEC_OPTIONS_SEP).length()));
            _options.getComments().clear();
            _options.getComments().addAllElts(comments_);
        }
    }

    private static void extractArgs(ExecutingOptions _exec, StringBuilder _argParts, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _exec.setHasArg(true);
            _argParts.append(_l.substring((_pref +EXEC_OPTIONS_SEP).length()));
        }
    }

    private static void extractInvoke(ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _exec.setInvokeDirect(true);
        }
    }

    private static void extractTab(Options _options, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            int t_ = NumberUtil.parseInt(output_);
            if (t_ > 0) {
                _options.setTabWidth(t_);
            }
        }
    }

    private static void extractOut(ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            if (!output_.isEmpty()) {
                output_ = adjustPath(output_);
                _exec.setOutputZip(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractFiles(ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            if (!output_.isEmpty()) {
                output_ = adjustPath(output_);
                _exec.setFiles(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractResources(ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            if (!output_.isEmpty()) {
                output_ = adjustPath(output_);
                _exec.setResources(StringUtil.replaceBackSlash(output_));
            }
        }
    }


    private static void extractParts(StringBuilder _warnsPart, String _l, String _pref) {
        if (_l.startsWith(_pref)) {
            _warnsPart.append(_l.substring(_pref.length()));
        }
    }

    private static void extractImpl(Options _options, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _options.getOptionsReport().setDisplayImplicit(true);
        }
    }

    private static void extractImplLabel(Options _options, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _options.getOptionsReport().setDisplayImplicitLabel(true);
        }
    }

    private static void extractSeed(Options _options, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _options.setSeedElts(_l.substring((_pref +EXEC_OPTIONS_SEP).length()));
        }
    }

    private static void extractSrc(Options _options, ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _options.setCovering(true);
            _exec.setCovering(true);
            _options.setGettingErrors(true);
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            if (!output_.isEmpty()) {
                output_ = adjustPath(output_);
                _exec.setSrcFolder(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    public static String adjustPath(String _o) {
        if (MemoryFileSystem.endsSep(_o)) {
            return _o.substring(0, _o.length() - 1);
        }
        return _o;
    }
    private static void extractErr(Options _options, ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _options.setGettingErrors(true);
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            if (!output_.isEmpty()) {
                _exec.setErrorsFolder(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractCover(Options _options, ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            _options.setCovering(true);
            _exec.setCovering(true);
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            if (!output_.isEmpty()) {
                _exec.setCoverFolder(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractLgs(ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            for (String s: StringUtil.splitChars(output_,',')) {
                String tr_ = s.trim();
                if (tr_.isEmpty()) {
                    continue;
                }
                _exec.getLgs().add(tr_);
            }
        }
    }

    private static void extractLog(ExecutingOptions _exec, String _l, String _pref) {
        if (_l.startsWith(_pref +EXEC_OPTIONS_SEP)) {
            String output_ = _l.substring((_pref +EXEC_OPTIONS_SEP).length());
            int lastSep_ = output_.lastIndexOf('>');
            if (lastSep_ > -1) {
                _exec.setLogFolder(StringUtil.replaceBackSlash(output_.substring(0,lastSep_)));
                _exec.setMainThread(StringUtil.replaceBackSlash(output_.substring(lastSep_+1)));
            }
        }
    }

    public String getBaseFiles() {
        return baseFiles;
    }

    public void setBaseFiles(String _baseFiles) {
        baseFiles = _baseFiles;
    }

    public String getSrcFolder() {
        return srcFolder;
    }

    public void setSrcFolder(String _srcFolder) {
        srcFolder = _srcFolder;
    }

    public String getLogFolder() {
        return logFolder;
    }

    public void setLogFolder(String _logFolder) {
        logFolder = _logFolder;
    }

    public String getMainThread() {
        return mainThread;
    }

    public void setMainThread(String _mainThread) {
        mainThread = _mainThread;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String _files) {
        this.files = _files;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String _resources) {
        this.resources = _resources;
    }

    public String getOutputZip() {
        return outputZip;
    }

    public void setOutputZip(String _outputZip) {
        this.outputZip = _outputZip;
    }

    public boolean isCovering() {
        return covering;
    }

    public void setCovering(boolean _covering) {
        covering = _covering;
    }

    public String getCoverFolder() {
        return coverFolder;
    }

    public void setCoverFolder(String _coverFolder) {
        coverFolder = _coverFolder;
    }

    public String getErrorsFolder() {
        return errorsFolder;
    }

    public void setErrorsFolder(String _errorsFolder) {
        errorsFolder = _errorsFolder;
    }

    public boolean isInvokeDirect() {
        return invokeDirect;
    }

    public void setInvokeDirect(boolean _invokeDirect) {
        invokeDirect = _invokeDirect;
    }

    public boolean isHasArg() {
        return hasArg;
    }

    public void setHasArg(boolean _hasArg) {
        hasArg = _hasArg;
    }

    public StringList getArgs() {
        return args;
    }

    public void setArgs(StringList _args) {
        args = _args;
    }

    public StringList getWarns() {
        return warns;
    }

    public void setWarns(StringList _warns) {
        this.warns = _warns;
    }

    public StringMap<String> getAliases() {
        return aliases;
    }

    public void setAliases(StringMap<String> _aliases) {
        aliases = _aliases;
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _messages) {
        this.messages = _messages;
    }

    public StringMap<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(StringMap<String> _keyWords) {
        keyWords = _keyWords;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String _output) {
        output = _output;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String _outputFolder) {
        outputFolder = _outputFolder;
    }

    public AbstractLightProgramInfos getLightProgramInfos() {
        return lightProgramInfos;
    }

    public void setLightProgramInfos(AbstractLightProgramInfos _l) {
        this.lightProgramInfos = _l;
    }

    public void setListGenerator(CdmFactory _l) {
        setInterceptor(_l.getInterceptor());
    }

    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(AbstractInterceptor _i) {
        this.interceptor = _i;
    }

    public FileSystemParameterizing getFileSystemParameterizing() {
        return fileSystemParameterizing;
    }

    public void setFileSystemParameterizing(FileSystemParameterizing _f) {
        this.fileSystemParameterizing = _f;
    }

    public StringList getLgs() {
        return lgs;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String _a) {
        this.access = _a;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String _l) {
        this.lg = _l;
    }
}
