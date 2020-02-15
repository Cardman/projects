package code.expressionlanguage.utilcompo;

import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class RunningTest implements Runnable {
    private String fileConfOrContent;
    private ProgressingTests progressingTests;
    private boolean file;
    private AbstractResourcesReader reader;

    private RunningTest() {
    }
    public static RunningTest newFromFile(String _fileConf, ProgressingTests _progressingTests, AbstractResourcesReader _reader) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.file = true;
        r_.reader = _reader;
        return r_;
    }

    public static RunningTest newFromContent(String _fileConf, ProgressingTests _progressingTests, AbstractResourcesReader _reader) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.reader = _reader;
        return r_;
    }
    @Override
    public void run() {
        String content_ = "";
        if (file) {
            content_ = StreamTextFile.contentsOfFile(fileConfOrContent);
            if (content_ == null) {
                return;
            }
        } else {
            content_ = fileConfOrContent;
        }
        launchByConfContent(content_,progressingTests,reader);
    }

    public static void launchByConfContent(String _content, ProgressingTests _progressingTests, AbstractResourcesReader _reader) {
        StringList lines_ = StringList.splitStrings(_content, "\n", "\r\n");
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
        StringMap<String> zipFiles_ = getFiles(archive_);
        ExecutingOptions exec_ = new ExecutingOptions();
        if (!StringList.contains(Constants.getAvailableLanguages(),lg_)){
            setupOptionals(1, exec_,linesFiles_);
        } else {
            setupOptionals(2, exec_, linesFiles_);
        }
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setFailIfNotAllInit(true);
        CustContextFactory.executeDefKw(lg_,opt_,exec_,zipFiles_,_progressingTests,_reader);
    }

    public static StringMap<String> getFiles(String _archiveOrFolder) {
        return StreamFolderFile.getFiles(_archiveOrFolder);
    }
    public static void setupOptionals(int _from, ExecutingOptions _exec, StringList _lines) {
        StringBuilder argParts_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
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
                _exec.setCovering(true);
                String output_ = l.substring("cover=".length());
                if (!output_.isEmpty()) {
                    _exec.setCoverFolder(output_);
                }
            }
            if (l.startsWith("src=")) {
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
                int t_ = Numbers.parseInt(output_);
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
            if (l.startsWith("aliases=")) {
                aliasesPart_.append(l.substring("aliases=".length()));
            }
            if (l.startsWith("keyWords=")) {
                keyWordsPart_.append(l.substring("keyWords=".length()));
            }
        }
        if (_exec.isHasArg()) {
            _exec.setArgs(LgNames.parseLineArg(argParts_.toString()));
        }
        if (aliasesPart_.length() > 0) {
            StringList infos_ = StringList.splitChars(aliasesPart_.toString(),',');
            StringMap<String> al_ = new StringMap<String>();
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringList.removeAllSpaces(l.substring(sep_ +1));
                value_ = LgNames.parseValue(value_);
                al_.put(key_,value_);
            }
            _exec.setAliases(al_);
        }
        if (keyWordsPart_.length() > 0) {
            StringList infos_ = StringList.splitChars(keyWordsPart_.toString(),',');
            StringMap<String> kw_ = new StringMap<String>();
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringList.removeAllSpaces(l.substring(sep_ +1));
                value_ = LgNames.parseValue(value_);
                kw_.put(key_,value_);
            }
            _exec.setKeyWords(kw_);
        }
    }
}
