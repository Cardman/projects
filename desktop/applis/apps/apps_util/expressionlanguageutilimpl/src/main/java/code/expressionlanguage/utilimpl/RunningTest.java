package code.expressionlanguage.utilimpl;

import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RunningTest implements Runnable {
    private String fileConfOrContent;
    private ProgressingTests progressingTests;
    private boolean file;
    private FileInfos infos;
    private StringList languages;

    private RunningTest() {
    }
    public static RunningTest newFromFile(StringList _lgs,String _fileConf, ProgressingTests _progressingTests, FileInfos _infos) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.file = true;
        r_.infos = _infos;
        r_.languages = _lgs;
        return r_;
    }

    public static RunningTest newFromContent(StringList _lgs,String _fileConf, ProgressingTests _progressingTests, FileInfos _infos) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.infos = _infos;
        r_.languages = _lgs;
        return r_;
    }
    @Override
    public void run() {
        String content_ = retrieve();
        launchByConfContent(languages,content_,progressingTests,infos);
    }

    public String retrieve() {
        String content_;
        if (file) {
            content_ = infos.getReporter().conf(fileConfOrContent);
        } else {
            content_ = infos.getReporter().confTxt(fileConfOrContent);
        }
        return content_;
    }

    public ProgressingTests getProgressingTests() {
        return progressingTests;
    }

    public static boolean launchByConfContent(StringList _lgs,String _content, ProgressingTests _progressingTests, FileInfos _infos) {
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(_content));
        if (linesFiles_.size() < 2) {
            return false;
        }
        String archive_ = linesFiles_.first();
        ReadFiles result_ = _infos.getReporter().getFiles(archive_);
        if (result_.getType() == OutputType.NOTHING) {
            return false;
        }
        ManageOptions manage_ = new ManageOptions(_lgs, linesFiles_,_progressingTests,_infos);
        ExecutingOptions exec_ = manage_.getEx();
        Options opt_ = manage_.getOptions();
        String lg_ = manage_.getLanguage();
        StringMap<String> list_ = tryGetSrc(archive_, exec_, _infos, result_);
        if (list_ == null) {
            return false;
        }
        opt_.setReadOnly(true);
        CustContextFactory.executeDefKw(lg_,opt_,exec_,list_,_progressingTests, new LgNamesGui(_infos,_progressingTests.getFactory().getInterceptor()));
        return true;
    }

    public static StringMap<String> tryGetSrc(String _archive, ExecutingOptions _exec, FileInfos _infos,ReadFiles _results) {
        AbstractReporter reporter_ = _infos.getReporter();
        String folderPath_ = reporter_.getFolderPath(_archive,_exec,_results);
        if (reporter_.koPaths(folderPath_, _exec)) {
            return null;
        }
        StringMap<String> readZip_ = reporter_.getSrc(_archive, _exec, _results);
        ReadBinFiles resultOs_ = reporter_.getBinFiles(folderPath_+_exec.getFiles());

        _infos.getFileSystem().build(_exec, resultOs_);
        return readZip_;
    }

}
