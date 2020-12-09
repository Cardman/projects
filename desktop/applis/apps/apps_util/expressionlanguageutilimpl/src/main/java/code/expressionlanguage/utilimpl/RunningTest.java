package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.ProgressingTests;
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
        String content_ = "";
        if (file) {
            content_ = infos.getReporter().conf(fileConfOrContent);
            if (content_ == null) {
                return;
            }
        } else {
            content_ = fileConfOrContent;
        }
        launchByConfContent(content_,progressingTests,infos);
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
        StringMap<String> zipFiles_ = _infos.getReporter().getFiles(archive_).getZipFiles();
        ExecutingOptions exec_ = new ExecutingOptions();
        Options opt_ = new Options();
        if (!StringUtil.contains(Constants.getAvailableLanguages(),lg_)){
            setupOptionals(1, opt_, exec_,linesFiles_);
        } else {
            setupOptionals(2, opt_, exec_, linesFiles_);
        }
        opt_.setReadOnly(true);
        CustContextFactory.executeDefKw(lg_,opt_,exec_,zipFiles_,_progressingTests, new LgNamesUtils(_infos));
    }

    public static void setupOptionals(int _from, Options _options, ExecutingOptions _exec, StringList _lines) {
        StringBuilder argParts_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder messagesPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
        StringBuilder classesPart_ = new StringBuilder();
        for (String l: _lines.mid(_from)) {
            if (l.startsWith("log=")) {
                String output_ = l.substring("log=".length());
                int lastSep_ = output_.lastIndexOf('>');
                if (lastSep_ > -1) {
                    _exec.setLogFolder(output_.substring(0,lastSep_));
                    _exec.setMainThread(output_.substring(lastSep_+1));
                }
            }
            if (l.startsWith("cover=")) {
                _options.setCovering(true);
                _exec.setCovering(true);
                String output_ = l.substring("cover=".length());
                if (!output_.isEmpty()) {
                    _exec.setCoverFolder(output_);
                }
            }
            if (l.startsWith("err=")) {
                _options.setGettingErrors(true);
                String output_ = l.substring("err=".length());
                if (!output_.isEmpty()) {
                    _exec.setErrorsFolder(output_);
                }
            }
            if (l.startsWith("src=")) {
                _options.setCovering(true);
                _options.setGettingErrors(true);
                _exec.setCovering(true);
                String output_ = l.substring("src=".length());
                if (!output_.isEmpty()) {
                    if (output_.endsWith("/")) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setSrcFolder(output_);
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
                CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
                for (String c: StringUtil.splitChar(
                        l.substring("comments=".length()).trim(),
                        ';')) {
                    StringList parts_ = StringUtil.splitChar(
                            c.trim(),
                            ',');
                    if (parts_.size() <= 2) {
                        parts_.clear();
                        parts_.add(" ");
                        parts_.add(" ");
                    }
                    String begin_ = ParseLinesArgUtil.parseValue(parts_.first());
                    String end_ = ParseLinesArgUtil.parseValue(parts_.last());
                    comments_.add(new CommentDelimiters(begin_,new StringList(end_)));
                }
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
        if (aliasesPart_.length() > 0) {
            StringList infos_ = StringUtil.splitChars(aliasesPart_.toString(),',');
            StringMap<String> al_ = new StringMap<String>();
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringUtil.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(value_);
                al_.put(key_,value_);
            }
            _exec.setAliases(al_);
        }
        if (messagesPart_.length() > 0) {
            StringList infos_ = StringUtil.splitChars(messagesPart_.toString(),',');
            StringMap<String> kw_ = new StringMap<String>();
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringUtil.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(value_);
                kw_.put(key_,value_);
            }
            _exec.setMessages(kw_);
        }
        if (keyWordsPart_.length() > 0) {
            StringList infos_ = StringUtil.splitChars(keyWordsPart_.toString(),',');
            StringMap<String> kw_ = new StringMap<String>();
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringUtil.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(value_);
                kw_.put(key_,value_);
            }
            _exec.setKeyWords(kw_);
        }
    }
}
