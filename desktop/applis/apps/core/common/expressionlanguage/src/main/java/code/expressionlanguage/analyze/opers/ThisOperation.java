package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

public final class ThisOperation extends LeafOperation implements PossibleIntermediateDotted {

    private AnaClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int nbAncestors;
    private int off;

    public ThisOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        LgNames stds_ = _page.getStandards();
        if (isIntermediateDottedOperation()) {
            MethodOperation m_ = getParent();
            OperationNode o_ = m_.getFirstChild();
            if (!(o_ instanceof StaticAccessOperation)) {
                String arg_ = _page.getGlobalClass();
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getStaticAccessPrev(),
                        _page.getKeyWords().getKeyWordThis());
                _page.getLocalizer().addError(static_);
                getErrs().add(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(arg_));
                return;
            }
            String access_ = previousResultClass.getName();
            String id_ = StringExpUtil.getIdFromAllTypes(access_);
            String gl_ = _page.getGlobalClass();
            gl_ = StringExpUtil.getIdFromAllTypes(gl_);
            RootBlock g_ = _page.getAnaClassBody(gl_);
            if (g_ == null) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getStaticAccessPrev(),
                        _page.getKeyWords().getKeyWordThis());
                _page.getLocalizer().addError(static_);
                getErrs().add(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getStandards().getAliasObject()));
                return;
            }
            for (RootBlock r: g_.getSelfAndParentTypes().getReverse()) {
                if (StringList.quickEq(r.getFullName(), id_)) {
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
                            static_.setFileName(_page.getLocalizer().getCurrentFileName());
                            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //key word len
                            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                                    _page.getKeyWords().getKeyWordThis());
                            _page.getLocalizer().addError(static_);
                            getErrs().add(static_.getBuiltError());
                        } else if (nbAncestors == 0){
                            FoundErrorInterpret static_ = new FoundErrorInterpret();
                            static_.setFileName(_page.getLocalizer().getCurrentFileName());
                            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //key word len
                            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                                    _page.getKeyWords().getKeyWordThis());
                            _page.getLocalizer().addError(static_);
                            getErrs().add(static_.getBuiltError());
                        }
                    }
                    String className_ = r.getGenericString();
                    setResultClass(new AnaClassArgumentMatching(className_));
                    return;
                }
                nbAncestors++;
            }
            String arg_ = stds_.getAliasObject();
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_page.getAnalysisMessages().getStaticAccessPrev(),
                    _page.getKeyWords().getKeyWordThis());
            _page.getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
            int off_ = StringList.getFirstPrintableCharIndex(access_);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
            setResultClass(new AnaClassArgumentMatching(arg_));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off, _page);
        String arg_ = _page.getGlobalClass();
        if (_page.getStaticContext() != MethodAccessKind.INSTANCE) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                    _page.getKeyWords().getKeyWordThis());
            _page.getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
        }
        setResultClass(new AnaClassArgumentMatching(arg_));
    }

    @Override
    public void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public int getNbAncestors() {
        return nbAncestors;
    }

    public int getOff() {
        return off;
    }
}
