package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.util.core.StringUtil;

public final class NatAnaRendClass extends AnaRendParentBlock implements NatRendBuildEl {
    private final String name;
    private String fullName= AnaRendBlockHelp.EMPTY_STRING;
    NatAnaRendClass(OffsetStringInfo _name, int _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatAnaRendPackage par_ = (NatAnaRendPackage) getParent();
        fullName = StringUtil.concat(par_.getName(), ".",name);
        fullName = StringExpUtil.removeDottedSpaces(fullName);
    }

    public String getFullName() {
        return fullName;
    }
}
