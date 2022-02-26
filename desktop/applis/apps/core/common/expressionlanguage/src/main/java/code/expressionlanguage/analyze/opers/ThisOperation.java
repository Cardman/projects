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
        if (isIntermediateDottedOperation()) {
            MethodOperation m_ = getParent();
            OperationNode o_ = m_.getFirstChild();
            if (!(o_ instanceof StaticAccessOperation)) {
                String arg_ = _page.getGlobalClass();
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getStaticAccessPrev(),
                        _page.getKeyWords().getKeyWordThis());
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(arg_));
                return;
            }
            String access_ = previousResultClass.getName();
            String id_ = StringExpUtil.getIdFromAllTypes(access_);
            RootBlock g_ = _page.getGlobalType().getRootBlock();
            if (g_ == null) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getStaticAccessPrev(),
                        _page.getKeyWords().getKeyWordThis());
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            for (RootBlock r: g_.getSelfAndParentTypes().getReverse()) {
                if (StringUtil.quickEq(r.getFullName(), id_)) {
                    if (_page.getStaticContext() != MethodAccessKind.INSTANCE) {
                        MethodOperation root_ = getParent();
                        while (true) {
                            MethodOperation par_ = root_.getParent();
                            if (par_ == null) {
                                break;
                            }
                            root_ = par_;
                        }
                        if (!(root_ instanceof AbstractInvokingConstructor)) {
                            FoundErrorInterpret static_ = new FoundErrorInterpret();
                            static_.setFile(_page.getCurrentFile());
                            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //key word len
                            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                                    _page.getKeyWords().getKeyWordThis());
                            _page.getLocalizer().addError(static_);
                            addErr(static_.getBuiltError());
                        } else if (thisContent.getNbAncestors() == 0){
                            FoundErrorInterpret static_ = new FoundErrorInterpret();
                            static_.setFile(_page.getCurrentFile());
                            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //key word len
                            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                                    _page.getKeyWords().getKeyWordThis());
                            _page.getLocalizer().addError(static_);
                            addErr(static_.getBuiltError());
                        }
                    }
                    String className_ = r.getGenericString();
                    setResultClass(new AnaClassArgumentMatching(className_));
                    return;
                }
                thisContent.setNbAncestors(thisContent.getNbAncestors()+1);
            }
            String arg_ = _page.getAliasObject();
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_page.getAnalysisMessages().getStaticAccessPrev(),
                    _page.getKeyWords().getKeyWordThis());
            _page.getLocalizer().addError(static_);
            addErr(static_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(arg_));
            return;
        }
        String arg_ = _page.getGlobalClass();
        if (_page.getStaticContext() != MethodAccessKind.INSTANCE) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                    _page.getKeyWords().getKeyWordThis());
            _page.getLocalizer().addError(static_);
            addErr(static_.getBuiltError());
        }
        setResultClass(new AnaClassArgumentMatching(arg_));
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
