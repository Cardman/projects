package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.WarningShow;
import code.expressionlanguage.utilcompo.AbstractReporter;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.ProgressingTests;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class RunningTest implements Runnable {
    private String fileConfOrContent;
    private ProgressingTests progressingTests;
    private boolean file;
    private FileInfos infos;

    private RunningTest() {
    }
    public static RunningTest newFromFile(String _fileConf, ProgressingTests _progressingTests, FileInfos _infos) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.file = true;
        r_.infos = _infos;
        return r_;
    }

    public static RunningTest newFromContent(String _fileConf, ProgressingTests _progressingTests, FileInfos _infos) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.infos = _infos;
        return r_;
    }
    @Override
    public void run() {
        String content_;
        if (file) {
            content_ = infos.getReporter().conf(fileConfOrContent);
        } else {
            content_ = infos.getReporter().confTxt(fileConfOrContent);
        }
        if (content_ == null) {
            return;
        }
        launchByConfContent(content_,progressingTests,infos);
    }

    public ProgressingTests getProgressingTests() {
        return progressingTests;
    }

    public static void launchByConfContent(String _content, ProgressingTests _progressingTests, FileInfos _infos) {
        StringList lines_ = StringUtil.splitStrings(_content, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
        if (linesFiles_.size() < 2) {
            return;
        }
        String archive_ = linesFiles_.first();
        String lg_ = linesFiles_.get(1);
        ReadFiles result_ = _infos.getReporter().getFiles(archive_);
        if (result_.getType() == OutputType.NOTHING) {
            return;
        }
        ExecutingOptions exec_ = new ExecutingOptions(_infos.getThreadFactory().newAtomicBoolean());
        Options opt_ = new Options();
        opt_.setDelimiterCase(';');
        if (!StringUtil.contains(Constants.getAvailableLanguages(),lg_)){
            lg_ = "";
            setupOptionals(1, opt_, exec_,linesFiles_);
        } else {
            setupOptionals(2, opt_, exec_, linesFiles_);
        }
        StringMap<String> list_ = tryGetSrc(archive_, exec_, _infos, result_);
        if (list_ == null) {
            return;
        }
        opt_.setReadOnly(true);
        CustContextFactory.executeDefKw(lg_,opt_,exec_,list_,_progressingTests, new LgNamesUtils(_infos,_progressingTests.getInterceptor()));
    }

    public static StringMap<String> tryGetSrc(String _archive, ExecutingOptions _exec, FileInfos _infos,ReadFiles _results) {
        AbstractReporter reporter_ = _infos.getReporter();
        String folderPath_ = reporter_.getFolderPath(_archive,_exec,_results);
        if (reporter_.koPaths(folderPath_, _exec)) {
            return null;
        }
        StringMap<String> readZip_ = reporter_.getSrc(_archive, _exec, _results);
        ReadBinFiles resultOs_ = reporter_.getBinFiles(folderPath_+_exec.getFiles());

        _infos.getFileSystem().build(_exec.getBaseFiles(),resultOs_);
        return readZip_;
    }

    public static void setupOptionals(int _from, Options _options, ExecutingOptions _exec, StringList _lines) {
        StringBuilder argParts_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder messagesPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
        StringBuilder classesPart_ = new StringBuilder();
        StringBuilder warnsPart_ = new StringBuilder();
        for (String l: _lines.mid(_from)) {
            if (l.startsWith("log=")) {
                String output_ = l.substring("log=".length());
                int lastSep_ = output_.lastIndexOf('>');
                if (lastSep_ > -1) {
                    _exec.setLogFolder(StringUtil.replaceBackSlash(output_.substring(0,lastSep_)));
                    _exec.setMainThread(StringUtil.replaceBackSlash(output_.substring(lastSep_+1)));
                }
            }
            if (l.startsWith("cover=")) {
                _options.setCovering(true);
                _exec.setCovering(true);
                String output_ = l.substring("cover=".length());
                if (!output_.isEmpty()) {
                    _exec.setCoverFolder(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("err=")) {
                _options.setGettingErrors(true);
                String output_ = l.substring("err=".length());
                if (!output_.isEmpty()) {
                    _exec.setErrorsFolder(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("src=")) {
                _options.setCovering(true);
                _options.setGettingErrors(true);
                _exec.setCovering(true);
                String output_ = l.substring("src=".length());
                if (!output_.isEmpty()) {
                    if (endsWithSep(output_)) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setSrcFolder(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("impl=")) {
                _options.setDisplayImplicit(true);
            }
            if (l.startsWith("warn=")) {
                warnsPart_.append(l.substring("warn=".length()));
            }
            if (l.startsWith("res=")) {
                String output_ = l.substring("res=".length());
                if (!output_.isEmpty()) {
                    if (endsWithSep(output_)) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setResources(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("files=")) {
                String output_ = l.substring("files=".length());
                if (!output_.isEmpty()) {
                    if (endsWithSep(output_)) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setFiles(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("out=")) {
                String output_ = l.substring("out=".length());
                if (!output_.isEmpty()) {
                    if (endsWithSep(output_)) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setOutputZip(StringUtil.replaceBackSlash(output_));
                }
            }
            if (l.startsWith("tabWidth=")) {
                String output_ = l.substring("tabWidth=".length());
                int t_ = NumberUtil.parseInt(output_);
                if (t_ > 0) {
                    _exec.setTabWidth(t_);
                }
            }
            if (l.startsWith("invokeDirect=")) {
                _exec.setInvokeDirect(true);
            }
            if (l.startsWith("args=")) {
                _exec.setHasArg(true);
                argParts_.append(l.substring("args=".length()));
            }
            if (l.startsWith("classes=")) {
                classesPart_.append(l.substring("classes=".length()));
            }
            if (l.startsWith("aliases=")) {
                aliasesPart_.append(l.substring("aliases=".length()));
            }
            if (l.startsWith("messages=")) {
                messagesPart_.append(l.substring("messages=".length()));
            }
            if (l.startsWith("keyWords=")) {
                keyWordsPart_.append(l.substring("keyWords=".length()));
            }
            if (l.startsWith("comments=")) {
                CustList<CommentDelimiters> comments_ = ParseLinesArgUtil.buildComments(l.substring("comments=".length()));
                _options.getComments().clear();
                _options.getComments().addAllElts(comments_);
            }
        }
        if (_exec.isHasArg()) {
            _exec.setArgs(ParseLinesArgUtil.parseLineArg(argParts_.toString()));
        }
        if (classesPart_.length() > 0) {
            _options.getTypesInit().addAllElts(ParseLinesArgUtil.parseLineArg(classesPart_.toString()));
        }
        if (warnsPart_.length() > 0) {
            StringList ws_ = new StringList();
            ParseLinesArgUtil.buildList(warnsPart_,ws_);
            _exec.setWarns(ws_);
        }
        if (aliasesPart_.length() > 0) {
            StringMap<String> al_ = new StringMap<String>();
            ParseLinesArgUtil.buildMap(aliasesPart_,al_);
            _exec.setAliases(al_);
        }
        if (messagesPart_.length() > 0) {
            StringMap<String> kw_ = new StringMap<String>();
            ParseLinesArgUtil.buildMap(messagesPart_,kw_);
            _exec.setMessages(kw_);
        }
        if (keyWordsPart_.length() > 0) {
            StringMap<String> kw_ = new StringMap<String>();
            ParseLinesArgUtil.buildMap(keyWordsPart_,kw_);
            _exec.setKeyWords(kw_);
        }
    }

    private static boolean endsWithSep(String _output) {
        return _output.endsWith("/") || _output.endsWith("\\");
    }
}
