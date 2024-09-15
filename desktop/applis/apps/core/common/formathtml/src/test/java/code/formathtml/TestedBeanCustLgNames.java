package code.formathtml;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanFileBuilder;
import code.formathtml.util.DefaultBeanAliases;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
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
    public CustList<AbsAliasFileBuilder> newFileBuilders() {
        CustList<AbsAliasFileBuilder> bs_ = new CustList<AbsAliasFileBuilder>();
        bs_.add(new DefAliasFileBuilder());
        bs_.add(getBeanAliases());
        return bs_;
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
    public StringMap<String> mappingRendKeywords() {
        return RendKeyWords.mappingTags();
    }

    @Override
    public StringMap<String> mappingAttrs() {
        return RendKeyWords.mappingAttrs();
    }

    @Override
    public StringMap<String> mappingValues() {
        return RendKeyWords.mappingValues();
    }

    @Override
    public StringMap<String> mappingRendMessages() {
        return RendAnalysisMessages.mapping();
    }

    @Override
    public StringMap<String> mappingStyleValues() {
        return RendKeyWords.mappingStyleValues();
    }

    @Override
    public StringMap<String> mappingStyleUnits() {
        return RendKeyWords.mappingStyleUnits();
    }

    @Override
    public StringMap<String> mappingStyleAttrs() {
        return RendKeyWords.mappingStyleAttrs();
    }

    @Override
    public StringMap<String> mappingStyleDefs() {
        return RendKeyWords.mappingDefs();
    }

    @Override
    public StringMap<String> mappingAliases() {
        StringMap<String> all_ = LgNamesContent.mapping();
        all_.addAllEntries(DefaultBeanAliases.mapping());
        return all_;
    }

    @Override
    public AbstractInterceptorStdCaller interceptor() {
        return new TestedRenderInterceptorStdCaller();
    }
}
