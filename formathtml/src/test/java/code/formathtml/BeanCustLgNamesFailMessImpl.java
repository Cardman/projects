package code.formathtml;

import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.sml.Element;

public final class BeanCustLgNamesFailMessImpl extends BeanCustLgNames {

    @Override
    public void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess) {
        _rMess.setEmptyAttr("");
    }
}
