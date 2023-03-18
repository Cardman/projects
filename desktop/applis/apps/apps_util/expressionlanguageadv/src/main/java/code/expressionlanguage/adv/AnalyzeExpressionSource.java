package code.expressionlanguage.adv;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.TechInfos;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.core.ReadFiles;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;

public final class AnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;

    public AnalyzeExpressionSource(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void run() {
        mainFrame.getStatusAnalyzeArea().setText("");
        ManageOptions manage_ = mainFrame.manage(mainFrame.getSoftParams().getLines());
        AbstractProgramInfos frames_ = mainFrame.getCommonFrame().getFrames();
        ExecutingOptions exec_ = manage_.getEx();
        Options opt_ = manage_.getOptions();
        String archive_ = exec_.getAccess();
        AbstractNameValidating validator_ = frames_.getValidator();
        DefaultUniformingString un_ = new DefaultUniformingString();
        FileInfos file_ = new FileInfos(new DefaultLogger(new UnitIssuer(mainFrame.getStatusAnalyzeArea()),frames_.getFileCoreStream(),frames_.getStreams()),
                new DefaultFileSystem(un_,validator_,frames_.getFileCoreStream(),frames_.getStreams()), new DefaultReporter(mainFrame.getFactory().getProgramInfos(),validator_, un_, false,new TechInfos(frames_.getThreadFactory(),frames_.getStreams()),frames_.getFileCoreStream()), frames_.getGenerator(), frames_.getStreams().getZipFact(), frames_.getThreadFactory());
        ReadFiles result_ = file_.getReporter().getFiles(archive_);
        StringMap<String> list_ = RunningTest.tryGetSrc(archive_, exec_, file_, result_);
        if (list_ == null) {
            mainFrame.getStatusAnalyzeArea().append("KO");
            return;
        }
        opt_.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui(file_, mainFrame.getFactory().getInterceptor());
        ResultContext r_ = CustContextFactory.buildDefKw(opt_, exec_, stds_, list_);
        CustContextFactory.reportErrors(opt_, exec_, r_.getReportedMessages(), file_);
        if (r_.getContext() != null) {
            mainFrame.getResultContext().update(stds_.getExecContent().getCustAliases(), stds_.getContent(),r_,frames_);
        }
        mainFrame.getStatusAnalyzeArea().append("-----");
    }

}
