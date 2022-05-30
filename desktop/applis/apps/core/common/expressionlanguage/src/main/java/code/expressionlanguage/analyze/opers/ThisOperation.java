package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.AnaThisContent;
import code.util.core.StringUtil;

public final class ThisOperation extends LeafOperation implements PossibleIntermediateDotted {

    private AnaClassArgumentMatching previousResultClass;
    private final AnaThisContent thisContent;

    public ThisOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        thisContent = new AnaThisContent(_op.getOffset());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ thisContent.getOff(), _page);
        if (!isIntermediateDottedOperation()) {
            String arg_ = _page.getGlobalClass();
            checkNotDotted(_page);
            setResultClass(new AnaClassArgumentMatching(arg_));
            return;
        }
        MethodOperation m_ = getParent();
        OperationNode o_ = m_.getFirstChild();
        if (!(o_ instanceof StaticAccessOperation)) {
            String arg_ = _page.getGlobalClass();
            staticAccess(_page, _page.getAnalysisMessages().getStaticAccessPrev());
            setResultClass(new AnaClassArgumentMatching(arg_));
            return;
        }
        String access_ = previousResultClass.getName();
        String id_ = StringExpUtil.getIdFromAllTypes(access_);
        RootBlock g_ = _page.getGlobalType().getRootBlock();
        if (g_ == null) {
            staticAccess(_page, _page.getAnalysisMessages().getStaticAccessPrev());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        for (RootBlock r : g_.getSelfAndParentTypes().getReverse()) {
            if (StringUtil.quickEq(r.getFullName(), id_)) {
                checkDotted(_page);
                String className_ = r.getGenericString();
                setResultClass(new AnaClassArgumentMatching(className_));
                return;
            }
            thisContent.setNbAncestors(thisContent.getNbAncestors() + 1);
        }
        String arg_ = _page.getAliasObject();
        staticAccess(_page, _page.getAnalysisMessages().getStaticAccessPrev());
        setResultClass(new AnaClassArgumentMatching(arg_));
    }

    private void checkDotted(AnalyzedPageEl _page) {
        if (_page.getStaticContext() != MethodAccessKind.INSTANCE) {
            MethodOperation root_ = ancestor();
            if (!(root_ instanceof AbstractInvokingConstructor) || thisContent.getNbAncestors() == 0) {
                staticAccess(_page, _page.getAnalysisMessages().getStaticAccess());
            }
        }
    }

    private void checkNotDotted(AnalyzedPageEl _page) {
        if (_page.getStaticContext() != MethodAccessKind.INSTANCE) {
            staticAccess(_page, _page.getAnalysisMessages().getStaticAccess());
        }
    }

    private MethodOperation ancestor() {
        MethodOperation root_ = getParent();
        while (true) {
            MethodOperation par_ = root_.getParent();
            if (par_ == null) {
                break;
            }
            root_ = par_;
        }
        return root_;
    }

    private void staticAccess(AnalyzedPageEl _page, String _message) {
        FoundErrorInterpret static_ = new FoundErrorInterpret();
        static_.setFile(_page.getCurrentFile());
        static_.setIndexFile(_page);
        //key word len
        static_.buildError(_message,
                _page.getKeyWords().getKeyWordThis());
        _page.getLocalizer().addError(static_);
        addErr(static_.getBuiltError());
    }

    @Override
    public void setIntermediateDotted() {
        thisContent.setIntermediate(true);
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return thisContent.isIntermediate();
    }

    @Override
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    public AnaThisContent getThisContent() {
        return thisContent;
    }
}
