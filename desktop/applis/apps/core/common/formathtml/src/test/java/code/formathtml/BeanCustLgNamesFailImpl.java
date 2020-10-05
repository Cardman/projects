package code.formathtml;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.Element;

public final class BeanCustLgNamesFailImpl extends TestedBeanCustLgNames {

    public BeanCustLgNamesFailImpl() {
        super(new DefaultGenerator());
    }

    @Override
    public void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess) {
        _rkw.setValueRadio("");
    }

}
