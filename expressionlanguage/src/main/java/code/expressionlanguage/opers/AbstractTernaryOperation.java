package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.DeadCodeTernary;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.ResultTernary;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractTernaryOperation extends MethodOperation {

    private int offsetLocal;

    public AbstractTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public final void setOffsetLocal(int _offsetLocal) {
        offsetLocal = _offsetLocal;
    }

    public final int getOffsetLocal() {
        return offsetLocal;
    }
    @Override
    public final void tryCalculateNode(Analyzable _conf) {
        tryGetResult(_conf, this);
    }
    public static void tryGetResult(Analyzable _conf, ParentOperable _to) {
        CustList<Operable> chidren_ = _to.getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (Operable o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argBool_ = arguments_.first();
        if (argBool_ == null) {
            return;
        }
        Struct str_ = argBool_.getStruct();
        if (!(str_ instanceof BooleanStruct)) {
            return;
        }
        Argument arg_;
        if (BooleanStruct.of(true).sameReference(str_)) {
            arg_ = arguments_.get(CustList.SECOND_INDEX);
        } else {
            arg_ = arguments_.last();
        }
        if (arg_ == null) {
            return;
        }
        _to.setSimpleArgumentAna(arg_, _conf);
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
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        OperationNode opOne_ = chidren_.first();
        ClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_conf)) {
            setRelativeOffsetPossibleAnalyzable(opOne_.getIndexInEl()+1, _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            //after first arg separator len
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedType(),
                    StringList.join(clMatch_.getNames(),"&"));
            _conf.addError(un_);
        }
        opOne_.getResultClass().setUnwrapObject(booleanPrimType_);
        opOne_.cancelArgument();
        OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        Argument firstArg_ = opTwo_.getArgument();
        Argument secondArg_ = opThree_.getArgument();
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        StringMap<StringList> vars_ = _conf.getCurrentConstraints();
        String void_ = stds_.getAliasVoid();
        if (StringList.contains(one_, void_)) {
            setRelativeOffsetPossibleAnalyzable(opTwo_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            //after first arg separator len
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getVoidType(),
                    void_);
            _conf.addError(un_);
        }
        if (StringList.contains(two_, void_)) {
            setRelativeOffsetPossibleAnalyzable(opThree_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            //after second arg separator len
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getVoidType(),
                    void_);
            _conf.addError(un_);
        }
        OperationNode current_ = this;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractTernaryOperation)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            if (m_.getFirstChild() == current_) {
                break;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String type_ = EMPTY_STRING;
        Block cur_ = _conf.getAnalyzing().getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            FunctionBlock f_ = _conf.getAnalyzing().getCurrentFct();
            if (f_ instanceof NamedFunctionBlock) {
                NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
                String ret_ = n_.getImportedReturnType();
                if (!StringList.quickEq(ret_, void_)) {
                    type_ = ret_;
                }
            }
        } else if (m_ instanceof CastOperation) {
            CastOperation c_ = (CastOperation) m_;
            type_ = c_.getClassName();
        } else if (m_ instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) m_;
            SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
            if (s_ != null) {
                ClassArgumentMatching c_ = s_.getResultClass();
                type_ = c_.getSingleNameOrEmpty();
            }
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, firstArg_, two_, secondArg_, vars_, _conf);
        if (!type_.isEmpty() && !StringList.quickEq(type_, keyWordVar_)) {
            if (PrimitiveTypeUtil.isPrimitive(type_, _conf)) {
                opTwo_.getResultClass().setUnwrapObject(type_);
                opThree_.getResultClass().setUnwrapObject(type_);
                opTwo_.cancelArgument();
                opThree_.cancelArgument();
            }
            setResultClass(new ClassArgumentMatching(type_));
            checkDeadCode(_conf, opOne_);
            return;
        }
        if (res_.isUnwrapFirst()) {
            opTwo_.getResultClass().setUnwrapObject(res_.getTypes().first());
            opTwo_.cancelArgument();
        }
        if (res_.isUnwrapSecond()) {
            opThree_.getResultClass().setUnwrapObject(res_.getTypes().first());
            opThree_.cancelArgument();
        }
        setResultClass(new ClassArgumentMatching(res_.getTypes()));
        checkDeadCode(_conf, opOne_);
    }

    private void checkDeadCode(Analyzable _conf, OperationNode _opOne) {
        if (_opOne.getArgument() != null) {
            DeadCodeTernary d_ = new DeadCodeTernary();
            d_.setIndexFile(_conf.getCurrentLocationIndex());
            d_.setFileName(_conf.getCurrentFileName());
            _conf.addWarning(d_);
        }
    }

    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getContextEl().getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
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
        boolean toBoolean_ = getResultClass().isBoolType(_conf);
        for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            Assignment p_ = fieldsAfterBefLast_.getVal(e.getKey());
            Assignment r_ = Assignment.ternary(p_, b_, toBoolean_);
            fieldsAfter_.put(e.getKey(), r_);
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            int index_ = variablesAfter_.size();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                Assignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey());
                Assignment r_ = Assignment.ternary(p_, b_, toBoolean_);
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
                Assignment r_ = Assignment.ternary(p_, b_, toBoolean_);
                sm_.put(e.getKey(), r_);
            }
            mutableAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
        vars_.getMutableLoop().put(this, mutableAfter_);
    }

}
