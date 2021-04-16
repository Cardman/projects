package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.LinkageUtil;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class BadTernaryOperation extends MethodOperation {
    public BadTernaryOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String fct_ = getOperations().getFctName();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+StringUtil.getFirstPrintableCharIndex(fct_), _page);
        FoundErrorInterpret badNb_ = new FoundErrorInterpret();
        badNb_.setFileName(_page.getLocalizer().getCurrentFileName());
        badNb_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //=> move to BadTernaryOperation (underline key word)
        badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                Long.toString(BOOLEAN_ARGS),
                Long.toString(chidren_.size()),
                _page.getKeyWords().getKeyWordBool()
        );
        _page.getLocalizer().addError(badNb_);
        addErr(badNb_.getBuiltError());
        StringList deep_ = getErrs();
        int i_ = _page.getLocalizer().getCurrentLocationIndex();
        getPartOffsetsEnd().add(new PartOffset(ExportCst.anchorErr(StringUtil.join(deep_,"\n\n")),i_));
        getPartOffsetsEnd().add(new PartOffset(ExportCst.END_ANCHOR,i_+ _page.getKeyWords().getKeyWordBool().length()));
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }
}
