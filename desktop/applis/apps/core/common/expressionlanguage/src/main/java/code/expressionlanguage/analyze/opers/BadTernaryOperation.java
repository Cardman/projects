package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class BadTernaryOperation extends MethodOperation {

    private final String fctName;

    public BadTernaryOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        fctName = _op.getFctName();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+StringUtil.getFirstPrintableCharIndex(fctName), _page);
        FoundErrorInterpret badNb_ = new FoundErrorInterpret();
        badNb_.setFile(_page.getCurrentFile());
        badNb_.setIndexFile(_page);
        //=> move to BadTernaryOperation (underline key word)
        badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                Long.toString(BOOLEAN_ARGS),
                Long.toString(chidren_.size()),
                _page.getKeyWords().getKeyWordBool()
        );
        _page.getLocalizer().addError(badNb_);
        addErr(badNb_.getBuiltError());
        StringList deep_ = getErrs();
        setPartOffsetsEnd(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),_page,_page.getKeyWords().getKeyWordBool().length()));
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

}
