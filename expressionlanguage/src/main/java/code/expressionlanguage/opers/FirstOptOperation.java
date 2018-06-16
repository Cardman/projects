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
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringMap;

public final class FirstOptOperation extends AbstractUnaryOperation {

    private int offset;
    public FirstOptOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (!(m_ instanceof InvokingOperation)) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(FIRST_OPT);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        InvokingOperation parent_ = (InvokingOperation)m_;
        if (!parent_.isCallMethodCtor()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(FIRST_OPT);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(FIRST_OPT);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (chidren_.size() != CustList.ONE_ELEMENT) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setRc(_conf.getCurrentLocation());
            varg_.setMethodName(FIRST_OPT);
            _conf.getClasses().getErrorsDet().add(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        setResultClass(chidren_.first().getResultClass());
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        setSimpleArgumentAna(arguments_.first(), _conf);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        return _arguments.first();
    }
}
