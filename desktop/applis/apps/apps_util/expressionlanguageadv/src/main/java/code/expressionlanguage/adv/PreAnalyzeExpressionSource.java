package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.TechInfos;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.core.DefaultUniformingString;

public final class PreAnalyzeExpressionSource implements Runnable {
    private final WindowCdmEditor mainFrame;

    public PreAnalyzeExpressionSource(WindowCdmEditor _e) {
        this.mainFrame = _e;
    }

    @Override
    public void run() {
        mainFrame.getStatusAnalyzeArea().setText("");
        ResultContext res_ = baseValidate(mainFrame);
        if (!res_.getPageEl().notAllEmptyErrors()) {
            mainFrame.getAnalyzeMenu().setEnabled(true);
            mainFrame.setBaseResult(res_);
        }
        mainFrame.getStatusAnalyzeArea().append("-----");
    }

    public static ResultContext baseValidate(WindowCdmEditor _window) {
        return baseValidate(_window.manage(_window.getSoftParams().getLines()), new UnitIssuer(_window.getStatusAnalyzeArea()), _window.getCommonFrame().getFrames(), _window.getFactory());
    }
    public static ResultContext baseValidate(ManageOptions _manage, AbstractIssuer _issuer, AbstractProgramInfos _factories, CdmFactory _progressingTests) {
        AbstractNameValidating validator_ = _factories.getValidator();
        DefaultUniformingString un_ = new DefaultUniformingString();
        FileInfos file_ = new FileInfos(new DefaultLogger(_issuer,_factories.getFileCoreStream(),_factories.getStreams()),
                new DefaultFileSystem(un_,validator_,_factories.getFileCoreStream(),_factories.getStreams()), new DefaultReporter(_progressingTests.getProgramInfos(),validator_, un_, false,new TechInfos(_factories.getThreadFactory(),_factories.getStreams()),_factories.getFileCoreStream()), _factories.getGenerator(), _factories.getStreams().getZipFact(), _factories.getThreadFactory());
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        Options opts_ = _manage.getOptions();
        ExecutingOptions ex_ = _manage.getEx();
        ex_.setLightProgramInfos(_factories);
        ex_.setListGenerator(_progressingTests);
        return CustContextFactory.stds(file_, ex_, opts_, mess_, kwl_);
    }
}
