package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.VarargError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class VarargOperation extends LeafOperation {

    private String className;
    private int offset;

    public VarargOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (!(m_ instanceof InvokingOperation)) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        InvokingOperation parent_ = (InvokingOperation) m_;
        if (!parent_.isCallMethodCtor()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (!isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        String str_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
        str_ = StringList.removeAllSpaces(str_);
        if (!checkCorrect(_conf, str_, true, getIndexInEl() + offset+1)) {
            str_ = stds_.getAliasObject();
        }
        setResultClass(new ClassArgumentMatching(str_));
        className = str_;
        setSimpleArgument(new Argument());
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();

        for (EntryCust<ClassField, AssignmentBefore> e: vars_.getFieldsBefore().getVal(this).entryList()) {
            AssignmentBefore b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assignAfter(false));
        }
        for (StringMap<AssignmentBefore> s: vars_.getVariablesBefore().getVal(this)) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignAfter(false));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        return;
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
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        return _nodes.getVal(this).getArgument();
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        return true;
    }

    @Override
    public boolean isCalculated() {
        return true;
    }

}
