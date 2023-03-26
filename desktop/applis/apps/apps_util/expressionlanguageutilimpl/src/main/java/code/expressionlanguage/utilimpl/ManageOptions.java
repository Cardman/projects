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
    public ManageOptions(StringList _lgs, StringList _linesFiles, ProgressingTests _progressingTests, FileInfos _infos) {
        this(_lgs, _linesFiles,_progressingTests.getFactory(),_infos.getThreadFactory());
    }
    public ManageOptions(StringList _lgs, StringList _linesFiles, CdmFactory _progressingTests, AbstractThreadFactory _infos) {
        ExecutingOptions exec_ = new ExecutingOptions(_infos.newAtomicBoolean());
        exec_.setListGenerator(_progressingTests);
        Options opt_ = new Options();
        if (!StringUtil.contains(_lgs,_linesFiles.get(1))){
            ExecutingOptions.setupOptionals(1, opt_, exec_,_linesFiles);
            exec_.setLg("");
        } else {
            ExecutingOptions.setupOptionals(2, opt_, exec_, _linesFiles);
            exec_.setLg(_linesFiles.get(1));
        }
        exec_.setAccess(_linesFiles.get(0));
        ex = exec_;
        options = opt_;
        language = exec_.getLg();
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
