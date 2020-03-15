package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.StaticAccessThisError;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class ThisOperation extends LeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int nbAncestors;
    private int off;

    public ThisOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        if (isIntermediateDottedOperation()) {
            MethodOperation m_ = getParent();
            OperationNode o_ = m_.getFirstChild();
            if (!(o_ instanceof StaticAccessOperation)) {
                String arg_ = _conf.getGlobalClass();
                StaticAccessThisError static_ = new StaticAccessThisError();
                static_.setClassName(arg_);
                static_.setFileName(_conf.getCurrentFileName());
                static_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.addError(static_);
                setResultClass(new ClassArgumentMatching(arg_));
                return;
            }
            String access_ = previousResultClass.getName();
            String id_ = Templates.getIdFromAllTypes(access_);
            String gl_ = _conf.getGlobalClass();
            gl_ = Templates.getIdFromAllTypes(gl_);
            GeneType g_ = _conf.getClassBody(gl_);
            RootBlock r_ = (RootBlock) g_;
            for (RootBlock r: r_.getSelfAndParentTypes().getReverse()) {
                if (StringList.quickEq(r.getFullName(), id_)) {
                    if (_conf.getStaticContext() != MethodAccessKind.INSTANCE) {
                        MethodOperation root_ = getParent();
                        while (true) {
                            MethodOperation par_ = root_.getParent();
                            if (par_ == null) {
                                break;
                            }
                            root_ = par_;
                        }
                        if (!(root_ instanceof AbstractInvokingConstructor)) {
                            StaticAccessThisError static_ = new StaticAccessThisError();
                            static_.setClassName(access_);
                            static_.setFileName(_conf.getCurrentFileName());
                            static_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.addError(static_);
                        } else if (nbAncestors == 0){
                            StaticAccessThisError static_ = new StaticAccessThisError();
                            static_.setClassName(access_);
                            static_.setFileName(_conf.getCurrentFileName());
                            static_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.addError(static_);
                        }
                    }
                    String className_ = r.getGenericString();
                    setResultClass(new ClassArgumentMatching(className_));
                    return;
                }
                nbAncestors++;
            }
            String arg_ = stds_.getAliasObject();
            StaticAccessThisError static_ = new StaticAccessThisError();
            static_.setClassName(access_);
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.addError(static_);
            int off_ = StringList.getFirstPrintableCharIndex(access_);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            setResultClass(new ClassArgumentMatching(arg_));
            return;
        }
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String arg_ = _conf.getGlobalClass();
        if (_conf.getStaticContext() != MethodAccessKind.INSTANCE) {
            StaticAccessThisError static_ = new StaticAccessThisError();
            static_.setClassName(arg_);
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.addError(static_);
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
