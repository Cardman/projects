package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class RendClass extends RendParentBlock {
    private String name;
    private String fullName=EMPTY_STRING;
    RendClass(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof RendPackage)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _cont.getRendKeyWords().getKeyWordClass(),
                    _cont.getRendKeyWords().getKeyWordPackage());
            Configuration.addError(un_, _anaDoc, _page);
        } else {
            RendPackage par_ = (RendPackage) getParent();
            fullName = StringList.concat(par_.getName(),DOT,name);
            fullName = StringExpUtil.removeDottedSpaces(fullName);
            if (_page.getAnaGeneType(fullName) == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        fullName);
                Configuration.addError(un_, _anaDoc, _page);
            }
        }
    }

    public String getFullName() {
        return fullName;
    }
}
