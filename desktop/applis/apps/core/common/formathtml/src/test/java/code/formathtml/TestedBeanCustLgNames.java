package code.formathtml;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanFileBuilder;
import code.maths.montecarlo.AbstractGenerator;
import code.util.StringMap;

public abstract class TestedBeanCustLgNames extends BeanCustLgNames {
    protected TestedBeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }
    @Override
    public void build() {
        buildBase();
        buildOtherBean();
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return commonExecutionInfos(new TestedRenderInterceptorStdCaller(),_opt,_options, new DefaultInitializer());
    }
    @Override
    public AbstractFileBuilder newFileBuilder() {
        return BeanFileBuilder.newInstance(getContent(),getBeanAliases());
    }

    @Override
    public StringMap<String> mappingMessages() {
        return AnalysisMessages.mapping();
    }

    @Override
    public StringMap<String> mappingKeywords() {
        return KeyWords.mapping();
    }

    @Override
    public StringMap<String> mappingAliases() {
        return LgNamesContent.mapping();
    }
}
