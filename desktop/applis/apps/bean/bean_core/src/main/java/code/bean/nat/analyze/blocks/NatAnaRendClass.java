package code.bean.nat.analyze.blocks;

import code.expressionlanguage.common.StringExpUtil;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.core.StringUtil;

public final class NatAnaRendClass extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final String name;
    private String fullName= AnaRendBlockHelp.EMPTY_STRING;
    NatAnaRendClass(String _name) {
        super();
        name = _name;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatAnaRendPackage par_ = (NatAnaRendPackage) getParent();
        fullName = StringUtil.concat(par_.getName(), ".",name);
        fullName = StringExpUtil.removeDottedSpaces(fullName);
    }

    public String getFullName() {
        return fullName;
    }
}
