package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringMap;

public final class IdOperation extends MethodOperation {

    public IdOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf);
    }

    void analyzeCommon(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 1) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        if (getParent() instanceof DotOperation && !isFirstChild()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        setResultClass(chidren_.first().getResultClass());
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        return calculateCommon(_nodes, _conf);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode o_ = chidren_.first();
        Argument a_ = _nodes.getVal(o_).getArgument();
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument(), _conf);
    }
    @Override
    public void calculate(ContextEl _conf) {
        calculateCommon(_conf);
    }

    void calculateCommon(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setSimpleArgument(chidren_.first().getArgument(), _conf);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        vars_.getFields().put(this, fieldsAfter_);
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        
    }
}
