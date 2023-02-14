package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.Options;
import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ExecutingOptions {

    private String baseFiles="";
    private String srcFolder = "src";
    private String logFolder = "logs";
    private String mainThread = "main_thread.txt";
    private String files = "files";
    private String resources = "res";
    private String outputZip = "out/results.zip";
    private String outputFolder = "";
    private String output = "";
    private final AbstractAtomicBoolean interrupt;
    private boolean covering;
    private String coverFolder = "coverage";
    private String errorsFolder = "errors";
    private boolean invokeDirect;
    private boolean hasArg;
    private StringList args = new StringList();
    private StringList warns = new StringList();
    private String lg = "";
    private final StringList lgs = new StringList();
    private StringMap<String> messages = new StringMap<String>();
    private StringMap<String> keyWords = new StringMap<String>();
    private StringMap<String> aliases = new StringMap<String>();
    private AbstractLightProgramInfos lightProgramInfos;
    private CdmFactory listGenerator;
    private FileSystemParameterizing fileSystemParameterizing;

    public ExecutingOptions(AbstractAtomicBoolean _inter) {
        interrupt = _inter;
        setFileSystemParameterizing(new FileSystemParameterizing("d",new StringBuilder(),new Ints(), "f",new StringBuilder(),new Ints()));
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
        StringBuilder argParts_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder messagesPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
        StringBuilder classesPart_ = new StringBuilder();
        StringBuilder warnsPart_ = new StringBuilder();
        for (String l: _lines.mid(_from)) {
            extractLog(_exec, l);
            extractLgs(_exec, l);
            extractCover(_options, _exec, l);
            extractErr(_options, _exec, l);
            extractSrc(_options, _exec, l);
            extractSeed(_options, l);
            extractImpl(_options, l);
            extractParts(warnsPart_, l, "warn=");
            extractResources(_exec, l);
            extractFiles(_exec, l);
            extractOut(_exec, l);
            extractTab(_options, l);
            extractInvoke(_exec, l);
            extractArgs(_exec, argParts_, l);
            extractParts(classesPart_, l, "classes=");
            extractParts(aliasesPart_, l, "aliases=");
            extractParts(messagesPart_, l, "messages=");
            extractParts(keyWordsPart_, l, "keyWords=");
            extractComments(_options, l);
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

    private static void extractComments(Options _options, String _l) {
        if (_l.startsWith("comments=")) {
            CustList<CommentDelimiters> comments_ = ParseLinesArgUtil.buildComments(_l.substring("comments=".length()));
            _options.getComments().clear();
            _options.getComments().addAllElts(comments_);
        }
    }

    private static void extractArgs(ExecutingOptions _exec, StringBuilder _argParts, String _l) {
        if (_l.startsWith("args=")) {
            _exec.setHasArg(true);
            _argParts.append(_l.substring("args=".length()));
        }
    }

    private static void extractInvoke(ExecutingOptions _exec, String _l) {
        if (_l.startsWith("invokeDirect=")) {
            _exec.setInvokeDirect(true);
        }
    }

    private static void extractTab(Options _options, String _l) {
        if (_l.startsWith("tabWidth=")) {
            String output_ = _l.substring("tabWidth=".length());
            int t_ = NumberUtil.parseInt(output_);
            if (t_ > 0) {
                _options.setTabWidth(t_);
            }
        }
    }

    private static void extractOut(ExecutingOptions _exec, String _l) {
        if (_l.startsWith("out=")) {
            String output_ = _l.substring("out=".length());
            if (!output_.isEmpty()) {
                output_ = adjustPath(output_);
                _exec.setOutputZip(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractFiles(ExecutingOptions _exec, String _l) {
        if (_l.startsWith("files=")) {
            String output_ = _l.substring("files=".length());
            if (!output_.isEmpty()) {
                output_ = adjustPath(output_);
                _exec.setFiles(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractResources(ExecutingOptions _exec, String _l) {
        if (_l.startsWith("res=")) {
            String output_ = _l.substring("res=".length());
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

    private static void extractImpl(Options _options, String _l) {
        if (_l.startsWith("impl=")) {
            _options.setDisplayImplicit(true);
        }
    }

    private static void extractSeed(Options _options, String _l) {
        if (_l.startsWith("seed=")) {
            _options.setSeedElts(_l.substring("seed=".length()));
        }
    }

    private static void extractSrc(Options _options, ExecutingOptions _exec, String _l) {
        if (_l.startsWith("src=")) {
            _options.setCovering(true);
            _options.setGettingErrors(true);
            String output_ = _l.substring("src=".length());
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
    private static void extractErr(Options _options, ExecutingOptions _exec, String _l) {
        if (_l.startsWith("err=")) {
            _options.setGettingErrors(true);
            String output_ = _l.substring("err=".length());
            if (!output_.isEmpty()) {
                _exec.setErrorsFolder(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractCover(Options _options, ExecutingOptions _exec, String _l) {
        if (_l.startsWith("cover=")) {
            _options.setCovering(true);
            String output_ = _l.substring("cover=".length());
            if (!output_.isEmpty()) {
                _exec.setCoverFolder(StringUtil.replaceBackSlash(output_));
            }
        }
    }

    private static void extractLgs(ExecutingOptions _exec, String _l) {
        if (_l.startsWith("lgs=")) {
            String output_ = _l.substring("lgs=".length());
            for (String s: StringUtil.splitChars(output_,',')) {
                String tr_ = s.trim();
                if (tr_.isEmpty()) {
                    continue;
                }
                _exec.getLgs().add(tr_);
            }
        }
    }

    private static void extractLog(ExecutingOptions _exec, String _l) {
        if (_l.startsWith("log=")) {
            String output_ = _l.substring("log=".length());
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

    public AbstractAtomicBoolean getInterrupt() {
        return interrupt;
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

    public CdmFactory getListGenerator() {
        return listGenerator;
    }

    public void setListGenerator(CdmFactory _l) {
        this.listGenerator = _l;
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

    public String getLg() {
        return lg;
    }

    public void setLg(String _l) {
        this.lg = _l;
    }
}
