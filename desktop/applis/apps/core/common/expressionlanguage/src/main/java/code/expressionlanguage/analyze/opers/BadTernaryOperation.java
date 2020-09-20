package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

public final class BadTernaryOperation extends MethodOperation {
    public BadTernaryOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String fct_ = getOperations().getFctName();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+StringList.getFirstPrintableCharIndex(fct_), _page);
        LgNames stds_ = _page.getStandards();
        FoundErrorInterpret badNb_ = new FoundErrorInterpret();
        badNb_.setFileName(_page.getLocalizer().getCurrentFileName());
        badNb_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //=> move to BadTernaryOperation (underline key word)
        badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                Integer.toString(BOOLEAN_ARGS),
                Integer.toString(chidren_.size()),
                _page.getKeyWords().getKeyWordBool()
        );
        _page.getLocalizer().addError(badNb_);
        getErrs().add(badNb_.getBuiltError());
        StringList deep_ = getErrs();
        int i_ = _page.getLocalizer().getCurrentLocationIndex();
        getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
        getPartOffsetsEnd().add(new PartOffset("</a>",i_+ _page.getKeyWords().getKeyWordBool().length()));
        setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }
}
