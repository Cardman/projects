package code.expressionlanguage.utilimpl;

import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
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
        exec_.setListGenerator(_progressingTests.getFactory());
        Options opt_ = new Options();
        if (!StringUtil.contains(Constants.getAvailableLanguages(),lg_)){
            lg_ = "";
            ExecutingOptions.setupOptionals(1, opt_, exec_,linesFiles_);
        } else {
            ExecutingOptions.setupOptionals(2, opt_, exec_, linesFiles_);
        }
        exec_.setCovering(opt_.isCovering());
        StringMap<String> list_ = tryGetSrc(archive_, exec_, _infos, result_);
        if (list_ == null) {
            return;
        }
        opt_.setReadOnly(true);
        CustContextFactory.executeDefKw(lg_,opt_,exec_,list_,_progressingTests, new LgNamesGui(_infos,_progressingTests.getFactory().getInterceptor()));
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
