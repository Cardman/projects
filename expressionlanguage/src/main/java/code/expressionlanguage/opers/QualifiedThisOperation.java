package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
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

public final class QualifiedThisOperation extends LeafOperation {

    private int nbAncestors;

    public QualifiedThisOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.SECOND_INDEX);
        String className_ = ContextEl.removeDottedSpaces(originalStr_);
        if (className_.isEmpty()) {
            className_ = _conf.getGlobalClass();
        } else {
            className_ = _conf.resolveIdType(className_);
        }
        String id_ = Templates.getIdFromAllTypes(className_);
        GeneType g_ = _conf.getClassBody(id_);
        LgNames stds_ = _conf.getStandards();
        if (!(g_ instanceof RootBlock)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        RootBlock r_ = (RootBlock) g_;
        for (RootBlock r: r_.getSelfAndParentTypes()) {
            if (PrimitiveTypeUtil.canBeUseAsArgument(false, id_, r.getFullName(),_conf)) {
                className_ = Templates.getFullTypeByBases(r.getGenericString(), id_, _conf);
                setResultClass(new ClassArgumentMatching(className_));
                return;
            }
            nbAncestors++;
        }
        String arg_ = stds_.getAliasObject();
        StaticAccessThisError static_ = new StaticAccessThisError();
        static_.setClassName(originalStr_);
        static_.setFileName(_conf.getCurrentFileName());
        static_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().addError(static_);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
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
        for (int i = 0; i < nbAncestors; i++) {
            struct_ = struct_.getParent();
        }
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
