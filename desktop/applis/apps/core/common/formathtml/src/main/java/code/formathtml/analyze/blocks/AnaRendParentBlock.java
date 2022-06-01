package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AnaRendParentBlock extends AnaRendBlock {

    private AnaRendBlock firstChild;
    protected AnaRendParentBlock(int _offset) {
        super(_offset);
    }

    public final void appendChild(AnaRendBlock _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        AnaRendBlock child_ = firstChild;
        while (true) {
            AnaRendBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    protected void checkTreeTry(AnalyzedPageEl _page, String _keyWord) {
        AnaRendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AnaRendAbstractCatchEval) && !(pBlock_ instanceof AnaRendTryEval) && (!isPossibleEmpty(pBlock_) || !(pBlock_.getPreviousSibling() instanceof AnaRendAbstractCatchEval) && !(pBlock_.getPreviousSibling() instanceof AnaRendTryEval))) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                    _keyWord,
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordCatch(),
                                    _page.getKeyWords().getKeyWordTry()
                            ),
                            OR_ERR));
            AnalyzingDoc.addError(un_, _page);
        }
    }


    protected void checkRead(AnalyzedPageEl _page, int _attr, ClassMethodIdReturn _d, ResultInput _res) {
        Mapping m_ = new Mapping();
        String check_ = ResultText.check(_page, _attr, _d);
        m_.setArg(check_);
        m_.setParam(_res.getOpsReadRoot().getResultClass());
        if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_attr);
            badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    check_,
                    StringUtil.join(_res.getOpsReadRoot().getResultClass().getNames(),AND_ERR));
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    protected void checkCharSeq(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, int _attr, ClassMethodIdReturn _id) {
        String check_ = ResultText.check(_page, _attr, _id);
        Mapping m_ = new Mapping();
        m_.setArg(check_);
        m_.setParam(_anaDoc.getAliasCharSequence());
        if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_attr);
            badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    check_,
                    _anaDoc.getAliasCharSequence());
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    public void removeAllVars(AnalyzedPageEl _ip) {
        removeLocalVars(_ip);
    }

    public final void removeLocalVars(AnalyzedPageEl _ip) {
        for (AnaRendBlock s: getDirectChildren(this)) {
            if (s instanceof AnaRendDeclareVariable) {
                for (String v: ((AnaRendDeclareVariable)s).getVariableNames()) {
                    _ip.getInfosVars().removeKey(v);
                }
            }
        }
    }
    @Override
    public final AnaRendBlock getFirstChild() {
        return firstChild;
    }
}
