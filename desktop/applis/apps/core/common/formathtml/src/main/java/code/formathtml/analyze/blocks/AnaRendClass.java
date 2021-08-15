package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.core.StringUtil;

public final class AnaRendClass  extends AnaRendParentBlock implements AnaRendBuildEl {
    private final String name;
    private String fullName=EMPTY_STRING;
    AnaRendClass(OffsetStringInfo _name, int _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaRendPackage)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _anaDoc.getRendKeyWords().getKeyWordClass(),
                    _anaDoc.getRendKeyWords().getKeyWordPackage());
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        } else {
            AnaRendPackage par_ = (AnaRendPackage) getParent();
            fullName = StringUtil.concat(par_.getName(),DOT,name);
            fullName = StringExpUtil.removeDottedSpaces(fullName);
            if (_page.getAnaGeneType(fullName) == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(getOffset());
                un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        fullName);
                AnalyzingDoc.addError(un_, _anaDoc, _page);
            }
        }
    }

    public String getFullName() {
        return fullName;
    }
}
