package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.StaticAccessThisError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;

public final class InternGlobalOperation extends LeafOperation {

    public InternGlobalOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String arg_ = _conf.getInternGlobalClass();
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
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getException() != null) {
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
        Struct struct_ = _conf.getInternGlobal();
        a_ = new Argument();
        a_.setStruct(struct_);
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

}
