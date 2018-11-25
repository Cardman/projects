package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.StaticAccessThisError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;

public final class ThisOperation extends LeafOperation implements PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;
    private int nbAncestors;

    public ThisOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        if (isIntermediateDottedOperation()) {
            MethodOperation m_ = getParent();
            OperationNode o_ = m_.getFirstChild();
            while (o_.getNextSibling() != this) {
                o_ = o_.getNextSibling();
            }
            if (!(o_ instanceof StaticAccessOperation)) {
                String arg_ = _conf.getGlobalClass();
                if (arg_ == null) {
                    arg_ = stds_.getAliasObject();
                }
                StaticAccessThisError static_ = new StaticAccessThisError();
                static_.setClassName(arg_);
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
                setResultClass(new ClassArgumentMatching(arg_));
                return;
            }
            String access_ = previousResultClass.getNames().first();
            String id_ = Templates.getIdFromAllTypes(access_);
            String gl_ = _conf.getGlobalClass();
            gl_ = Templates.getIdFromAllTypes(gl_);
            GeneType g_ = _conf.getClassBody(gl_);
            RootBlock r_ = (RootBlock) g_;
            for (RootBlock r: r_.getSelfAndParentTypes().getReverse()) {
                if (StringList.quickEq(r.getFullName(), id_)) {
                    if (_conf.isStaticContext()) {
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
                            static_.setRc(_conf.getCurrentLocation());
                            _conf.getClasses().addError(static_);
                        } else if (nbAncestors == 0){
                            StaticAccessThisError static_ = new StaticAccessThisError();
                            static_.setClassName(access_);
                            static_.setFileName(_conf.getCurrentFileName());
                            static_.setRc(_conf.getCurrentLocation());
                            _conf.getClasses().addError(static_);
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
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
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
        if (arg_ == null) {
            arg_ = stds_.getAliasObject();
        }
        if (_conf.isStaticContext()) {
            StaticAccessThisError static_ = new StaticAccessThisError();
            static_.setClassName(arg_);
            static_.setFileName(_conf.getCurrentFileName());
            static_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(static_);
        }
        setResultClass(new ClassArgumentMatching(arg_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.hasExceptionOrFailInit()) {
            return arg_;
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    Argument getCommonArgument(ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        Struct struct_ = ip_.getGlobalArgument().getStruct();
        a_ = new Argument();
        a_.setStruct(struct_);
        if (isIntermediateDottedOperation()) {
            String c_ = getResultClass().getNames().first();
            a_.setStruct(PrimitiveTypeUtil.getParent(nbAncestors, c_, a_.getStruct(), _conf));
        }
        return a_;
    }
    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public Argument getPreviousArgument() {
        return null;
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
    }

}
