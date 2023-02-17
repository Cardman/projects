package code.expressionlanguage.utilimpl;

import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.ProgressingTests;
import code.gui.CdmFactory;
import code.threads.AbstractThreadFactory;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ManageOptions {
    private final ExecutingOptions ex;
    private final Options options;
    private final String language;
    public ManageOptions(StringList _lgs, String _lg, StringList _linesFiles, ProgressingTests _progressingTests, FileInfos _infos) {
        this(_lgs,_lg,_linesFiles,_progressingTests.getFactory(),_infos.getThreadFactory());
    }
    public ManageOptions(StringList _lgs, String _lg, StringList _linesFiles, CdmFactory _progressingTests, AbstractThreadFactory _infos) {
        ExecutingOptions exec_ = new ExecutingOptions(_infos.newAtomicBoolean());
        exec_.setListGenerator(_progressingTests);
        Options opt_ = new Options();
        String lg_ = _lg;
        if (!StringUtil.contains(_lgs,_lg)){
            lg_ = "";
            ExecutingOptions.setupOptionals(1, opt_, exec_,_linesFiles);
        } else {
            ExecutingOptions.setupOptionals(2, opt_, exec_, _linesFiles);
        }
        exec_.setLg(lg_);
        exec_.setCovering(opt_.isCovering());
        ex = exec_;
        options = opt_;
        language = lg_;
    }

    public String getLanguage() {
        return language;
    }

    public Options getOptions() {
        return options;
    }

    public ExecutingOptions getEx() {
        return ex;
    }
}
