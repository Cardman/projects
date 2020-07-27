package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

public final class ThisOperation extends LeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int nbAncestors;
    private int off;

    public ThisOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
    }

    @Override
    public void analyze(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        if (isIntermediateDottedOperation()) {
            MethodOperation m_ = getParent();
            OperationNode o_ = m_.getFirstChild();
            if (!(o_ instanceof StaticAccessOperation)) {
                String arg_ = _conf.getAnalyzing().getGlobalClass();
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_conf.getAnalysisMessages().getStaticAccessPrev(),
                        _conf.getKeyWords().getKeyWordThis());
                _conf.getAnalyzing().getLocalizer().addError(static_);
                getErrs().add(static_.getBuiltError());
                setResultClass(new ClassArgumentMatching(arg_));
                return;
            }
            String access_ = previousResultClass.getName();
            String id_ = StringExpUtil.getIdFromAllTypes(access_);
            String gl_ = _conf.getAnalyzing().getGlobalClass();
            gl_ = StringExpUtil.getIdFromAllTypes(gl_);
            RootBlock g_ = _conf.getAnalyzing().getAnaClassBody(gl_);
            for (RootBlock r: g_.getSelfAndParentTypes().getReverse()) {
                if (StringList.quickEq(r.getFullName(), id_)) {
                    if (_conf.getAnalyzing().getStaticContext() != MethodAccessKind.INSTANCE) {
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
                            static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            //key word len
                            static_.buildError(_conf.getAnalysisMessages().getStaticAccess(),
                                    _conf.getKeyWords().getKeyWordThis());
                            _conf.getAnalyzing().getLocalizer().addError(static_);
                            getErrs().add(static_.getBuiltError());
                        } else if (nbAncestors == 0){
                            FoundErrorInterpret static_ = new FoundErrorInterpret();
                            static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            //key word len
                            static_.buildError(_conf.getAnalysisMessages().getStaticAccess(),
                                    _conf.getKeyWords().getKeyWordThis());
                            _conf.getAnalyzing().getLocalizer().addError(static_);
                            getErrs().add(static_.getBuiltError());
                        }
                    }
                    String className_ = r.getGenericString();
                    setResultClass(new ClassArgumentMatching(className_));
                    return;
                }
                nbAncestors++;
            }
            String arg_ = stds_.getAliasObject();
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_conf.getAnalysisMessages().getStaticAccessPrev(),
                    _conf.getKeyWords().getKeyWordThis());
            _conf.getAnalyzing().getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
            int off_ = StringList.getFirstPrintableCharIndex(access_);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            setResultClass(new ClassArgumentMatching(arg_));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off, _conf);
        String arg_ = _conf.getAnalyzing().getGlobalClass();
        if (_conf.getAnalyzing().getStaticContext() != MethodAccessKind.INSTANCE) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            static_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_conf.getAnalysisMessages().getStaticAccess(),
                    _conf.getKeyWords().getKeyWordThis());
            _conf.getAnalyzing().getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
        }
        setResultClass(new ClassArgumentMatching(arg_));
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
    public void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
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
