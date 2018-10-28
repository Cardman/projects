package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ResultTernary;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.DeadCodeTernary;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractTernaryOperation extends MethodOperation {

    private static final int BOOLEAN_ARGS = 3;

    private int offsetLocal;

    public AbstractTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public final void setOffsetLocal(int _offsetLocal) {
        offsetLocal = _offsetLocal;
    }
    @Override
    public final void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
        if (getFirstChild().getArgument() == null) {
            return;
        }
        quickCalculate(_conf);
    }
    @Override
    public final void tryCalculateNode(Analyzable _conf) {
        if (getFirstChild().getArgument() == null) {
            return;
        }
        quickCalculate(_conf);
    }
    @Override
    public final void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (arguments_.first().isNull()) {
            return;
        }
        Boolean obj_ = (Boolean) arguments_.first().getObject();
        Argument arg_;
        if (obj_) {
            arg_ = arguments_.get(CustList.SECOND_INDEX);
        } else {
            arg_ = arguments_.last();
        }
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public final void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        OperationNode firstChild_ = getFirstChild();
        if (firstChild_ == _previous) {
            analyzeTrueAssignmentBeforeNextSibling(_conf, _nextSibling, firstChild_);
        } else {
            analyzeFalseAssignmentBeforeNextSibling(_conf, _nextSibling, firstChild_);
        }
    }

    @Override
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offsetLocal, _conf);
        LgNames stds_ = _conf.getStandards();
        String booleanType_ = stds_.getAliasBoolean();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        if (chidren_.size() != BOOLEAN_ARGS) {
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode opOne_ = chidren_.first();
        ClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_conf)) {
            setRelativeOffsetPossibleAnalyzable(opOne_.getIndexInEl()+1, _conf);
            ClassArgumentMatching cl_ = chidren_.first().getResultClass();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(booleanType_);
            un_.setOperands(cl_);
            _conf.getClasses().addError(un_);
        }
        opOne_.getResultClass().setUnwrapObject(booleanPrimType_);
        OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
        OperationNode opThree_ = chidren_.get(CustList.SECOND_INDEX);
        ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        Argument firstArg_ = opTwo_.getArgument();
        Argument secondArg_ = opThree_.getArgument();
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        boolean buildMap_ = true;
        if (_conf.isStaticContext()) {
            buildMap_ = false;
        } else if (_conf.getGlobalClass() == null) {
            buildMap_ = false;
        }
        if (buildMap_) {
            for (TypeVar t: Templates.getConstraints(_conf.getGlobalClass(), _conf)) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        String void_ = stds_.getAliasVoid();
        if (one_.containsStr(void_)) {
            setRelativeOffsetPossibleAnalyzable(opTwo_.getIndexInEl(), _conf);
            ClassArgumentMatching cl_ = opTwo_.getResultClass();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(booleanType_);
            un_.setOperands(cl_);
            _conf.getClasses().addError(un_);
        }
        if (two_.containsStr(void_)) {
            setRelativeOffsetPossibleAnalyzable(opThree_.getIndexInEl(), _conf);
            ClassArgumentMatching cl_ = opThree_.getResultClass();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(booleanType_);
            un_.setOperands(cl_);
            _conf.getClasses().addError(un_);
        }
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, firstArg_, two_, secondArg_, vars_, _conf);
        if (res_.isUnwrapFirst()) {
            opTwo_.getResultClass().setUnwrapObject(res_.getTypes().first());
        }
        if (res_.isUnwrapSecond()) {
            opThree_.getResultClass().setUnwrapObject(res_.getTypes().first());
        }
        setResultClass(new ClassArgumentMatching(res_.getTypes()));
        if (opOne_.getArgument() != null) {
            DeadCodeTernary d_ = new DeadCodeTernary();
            d_.setRc(_conf.getCurrentLocation());
            d_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addWarning(d_);
        }
    }

    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.size() != BOOLEAN_ARGS) {
            analyzeStdAssignmentAfter(_conf);
            return;
        }
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        CustList<StringMap<Assignment>> mutableAfterLast_ = vars_.getMutableLoop().getVal(last_);

        OperationNode befLast_ = children_.get(children_.size() - 2);
        StringMap<Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(befLast_);
        CustList<StringMap<Assignment>> variablesAfterBefLast_ = vars_.getVariables().getVal(befLast_);
        CustList<StringMap<Assignment>> mutableAfterBefLast_ = vars_.getMutableLoop().getVal(befLast_);
        if (getResultClass().isBoolType(_conf)) {
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                BooleanAssignment p_ = fieldsAfterBefLast_.getVal(e.getKey()).toBoolAssign();
                BooleanAssignment r_ = b_.ternary(p_);
                fieldsAfter_.put(e.getKey(), r_);
            }
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = e.getValue().toBoolAssign();
                    BooleanAssignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey()).toBoolAssign();
                    BooleanAssignment r_ = b_.ternary(p_);
                    sm_.put(e.getKey(), r_);
                }
                variablesAfter_.add(sm_);
            }
            for (StringMap<Assignment> s: mutableAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = e.getValue().toBoolAssign();
                    BooleanAssignment p_ = mutableAfterBefLast_.get(index_).getVal(e.getKey()).toBoolAssign();
                    BooleanAssignment r_ = b_.ternary(p_);
                    sm_.put(e.getKey(), r_);
                }
                mutableAfter_.add(sm_);
            }
        } else {
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                Assignment b_ = e.getValue();
                Assignment p_ = fieldsAfterBefLast_.getVal(e.getKey());
                SimpleAssignment r_ = b_.ternarySimple(p_);
                fieldsAfter_.put(e.getKey(), r_);
            }
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    Assignment b_ = e.getValue();
                    Assignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey());
                    SimpleAssignment r_ = b_.ternarySimple(p_);
                    sm_.put(e.getKey(), r_);
                }
                variablesAfter_.add(sm_);
            }
            for (StringMap<Assignment> s: mutableAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    Assignment b_ = e.getValue();
                    Assignment p_ = mutableAfterBefLast_.get(index_).getVal(e.getKey());
                    SimpleAssignment r_ = b_.ternarySimple(p_);
                    sm_.put(e.getKey(), r_);
                }
                mutableAfter_.add(sm_);
            }
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }

    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf);
    }

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    final Argument  getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _conf);
        Boolean obj_ = (Boolean) _arguments.first().getObject();
        Argument arg_;
        if (obj_) {
            arg_ = _arguments.get(CustList.SECOND_INDEX);
        } else {
            arg_ = _arguments.last();
        }
        return arg_;
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }

}
