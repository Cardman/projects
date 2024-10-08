package code.formathtml.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.formathtml.*;
import code.formathtml.util.DefaultBeanAliases;
import code.util.StringMap;
import org.junit.Test;

public final class RendAnalysisMessagesTest extends EquallableRenderUtil {
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
        TestedBeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setLogErr(new ListLoggableLgNames());
        page_.setAnalysisMessages(new AnalysisMessages());
        AnalysisMessages.validateMessageContents(def_.allMessages(RendAnalysisMessages.mapping()), page_);
        assertTrue(!page_.isEmptyMessageError());
    }

    @Test
    public void getAlias6() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        RendAnalysisMessages lgNamesContent_ = new RendAnalysisMessages();
        lgNamesContent_.rendMessages(def_, cust_, RendAnalysisMessages.mapping());
        assertEq("",lgNamesContent_.getEmptyAttr());
        DefaultBeanAliases.getMessageStruct(null,"");
    }
}
