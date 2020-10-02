package code.formathtml.errors;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.BeanCustLgNamesImpl;
import code.formathtml.InitializationLgNames;
import code.formathtml.util.BeanLgNames;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class RendAnalysisMessagesTest {
    @Test
    public void fail() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        def_.setBadInputName("");
        def_.setStaticInputName("");
        def_.setUnexpectedChildTag("");
        def_.setEmptyAttr("");
        def_.setUnexpectedExp("");
        def_.setInexistantFile("");
        def_.setInexistantKey("");
        def_.setBadDocument("");
        DefaultInitializer di_ = new DefaultInitializer();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        getCtx(di_, lgName_, opts_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(new AnalysisMessages());
        AnalysisMessages.validateMessageContents(def_.allMessages(), page_);
        assertTrue(!page_.isEmptyMessageError());
    }

    private static ContextEl getCtx(DefaultInitializer di_, LgNames lgName_, Options opts_) {
        return lgName_.newContext(4, -1, new Coverage(opts_.isCovering()),di_);
    }
}
