package code.formathtml.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
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
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        getCtx(lk_, di_, lgName_, opts_, new ClassesCommon());
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(new AnalysisMessages());
        AnalysisMessages.validateMessageContents(def_.allMessages(), page_);
        assertTrue(!page_.isEmptyMessageError());
    }

    private static SingleContextEl getCtx(DefaultLockingClass lk_, DefaultInitializer di_, LgNames lgName_, Options opts_, ClassesCommon _com) {
        return new SingleContextEl(-1,lk_,di_,opts_, lgName_,4, _com);
    }
}
