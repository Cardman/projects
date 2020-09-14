package code.formathtml;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.StringList;

public final class RendClass extends RendParentBlock {
    private String name;
    private String fullName=EMPTY_STRING;
    RendClass(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        if (!(getParent() instanceof RendPackage)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getAnalyzingDoc().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _cont.getRendKeyWords().getKeyWordClass(),
                    _cont.getRendKeyWords().getKeyWordPackage());
            Configuration.addError(un_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
        } else {
            RendPackage par_ = (RendPackage) getParent();
            fullName = StringList.concat(par_.getName(),DOT,name);
            fullName = StringExpUtil.removeDottedSpaces(fullName);
            if (_cont.getContext().getAnalyzing().getAnaGeneType(fullName) == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cont.getAnalyzingDoc().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getUnknownType(),
                        fullName);
                Configuration.addError(un_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
            }
        }
    }

    public String getFullName() {
        return fullName;
    }
}
