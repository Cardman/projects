package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AbstractRefTernaryOperation extends AbstractComTernaryOperation implements SettableElResult {

    private final StringList childrenErrors = new StringList();
    private final AnaArrContent arrContent;

    protected AbstractRefTernaryOperation(int _index, int _indexChild, MethodOperation _m,
                                       OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrContent = new AnaArrContent();
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        analyzeTernary(_page);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opTwo_ = chidren_.get(IndexConstants.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        if (!(opTwo_ instanceof WrappOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            childrenErrors.add(varg_.getBuiltError());
        }
        if (!(opThree_ instanceof WrappOperation)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getUnexpectedLeaf(),
                    _page.getKeyWords().getKeyWordThat());
            _page.getLocalizer().addError(varg_);
            childrenErrors.add(varg_.getBuiltError());
        }
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        if (!clMatchTwo_.matchClass(clMatchThree_)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //character before
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchTwo_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchThree_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            childrenErrors.add(cast_.getBuiltError());
        }
        setResultClass(AnaClassArgumentMatching.copy(clMatchTwo_,_page.getPrimitiveTypes()));
    }

    public StringList getChildrenErrors() {
        return childrenErrors;
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }
}
