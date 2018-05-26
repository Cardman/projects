package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ResultOperand;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class NumericOperation extends MethodOperation {

    public NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument calculateAffect(Argument _left,ExecutableCode _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        Argument o_;
        boolean convert_ = true;
        if (StringList.quickEq(_op, Block.PLUS_EQ)) {
            o_ = NumericOperation.calculateSum(_left, _right, false, _catString, _conf, _arg);
            convert_ = !_catString;
        } else if (StringList.quickEq(_op, Block.EQ_PLUS)) {
            o_ = NumericOperation.calculateSum(_right, _left, false, _catString, _conf, _arg);
            convert_ = !_catString;
        } else if (StringList.quickEq(_op, Block.MINUS_EQ)) {
            o_ = NumericOperation.calculateDiff(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.MULT_EQ)) {
            o_ = NumericOperation.calculateMult(_left, _right, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.DIV_EQ)) {
            o_ = NumericOperation.calculateDivEx(_left, _conf, _right, _arg);
        } else if (StringList.quickEq(_op, Block.MOD_EQ)) {
            o_ = NumericOperation.calculateModEx(_left, _conf, _right, _arg);
        } else if (StringList.quickEq(_op, Block.INCR)) {
            o_ = AddOperation.addOne(_left, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.DECR)) {
            o_ = AddOperation.removeOne(_left, _conf, _arg);
        } else if (StringList.quickEq(_op, Block.AND_EQ)) {
            o_ = new Argument();
            Boolean left_ = (Boolean) _left.getObject();
            Boolean right_ = (Boolean) _right.getObject();
            o_.setStruct(new BooleanStruct(left_ && right_));
            convert_ = false;
        } else if (StringList.quickEq(_op, Block.OR_EQ)) {
            o_ = new Argument();
            Boolean left_ = (Boolean) _left.getObject();
            Boolean right_ = (Boolean) _right.getObject();
            o_.setStruct(new BooleanStruct(left_ || right_));
            convert_ = false;
        } else {
            o_ = _right;
            convert_ = false;
        }
        if (convert_) {
            if (_conf.getException() != null) {
                return o_;
            }
            ClassArgumentMatching cl_ = new ClassArgumentMatching(_left.getObjectClassName(_conf.getContextEl()));
            Argument converted_ = new Argument();
            converted_.setStruct(PrimitiveTypeUtil.convertObject(cl_, o_.getStruct(), _conf));
            o_ = converted_;
        }
        return o_;
    }
    static boolean convert(String _op) {
        if (StringList.quickEq(_op, Block.PLUS_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.EQ_PLUS)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.MINUS_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.MULT_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.DIV_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.MOD_EQ)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.INCR)) {
            return true;
        }
        if (StringList.quickEq(_op, Block.DECR)) {
            return true;
        }
        return false;
    }
    static Argument calculateSum(Argument _a, Argument _b, boolean _catChars, boolean _catString, Analyzable _an,ClassArgumentMatching _order) {
        if (_catString) {
            StringBuilder str_ = new StringBuilder();
            str_.append(_a.getObject());
            str_.append(_b.getObject());
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        if (_catChars) {
            StringBuilder str_ = new StringBuilder();
            str_.append(_a.getObject());
            str_.append(_b.getObject());
            Argument a_ = new Argument();
            a_.setObject(str_.toString());
            return a_;
        }
        Object o_ = _a.getObject();
        Object p_ = _b.getObject();
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).longValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            long right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).longValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ + right_;
        } else {
            double left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).doubleValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            double right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).doubleValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ + right_;
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }

    static Argument calculateDiff(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        Object o_ = _a.getObject();
        Object p_ = _b.getObject();
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).longValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            long right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).longValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ - right_;
        } else {
            double left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).doubleValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            double right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).doubleValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ - right_;
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    static Argument calculateMult(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        Object o_ = _a.getObject();
        Object p_ = _b.getObject();
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).longValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            long right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).longValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ * right_;
        } else {
            double left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).doubleValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            double right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).doubleValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ * right_;
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    static Argument calculateDivEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Argument res_ = calculateDiv(_a, _b, _cont, _order);
        if (res_.isNull()) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),div_));
        }
        return res_;
    }
    static Argument calculateDiv(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        Object o_ = _a.getObject();
        Object p_ = _b.getObject();
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).longValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            long right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).longValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            if (right_ == 0) {
                return Argument.createVoid();
            }
            nb_ = left_ / right_;
        } else {
            double left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).doubleValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            double right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).doubleValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ / right_;
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }
    static Argument calculateModEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Argument res_ = calculateMod(_a, _b, _cont, _order);
        if (res_.isNull()) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),div_));
        }
        return res_;
    }
    static Argument calculateMod(Argument _a, Argument _b, Analyzable _an,ClassArgumentMatching _order) {
        Object o_ = _a.getObject();
        Object p_ = _b.getObject();
        int order_ = PrimitiveTypeUtil.getOrderClass(_order, _an);
        Number nb_;
        String longPrim_ = _an.getStandards().getAliasPrimLong();
        if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _an)) {
            long left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).longValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            long right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).longValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            if (right_ == 0) {
                return Argument.createVoid();
            }
            nb_ = left_ % right_;
        } else {
            double left_;
            if (o_ instanceof Number) {
                left_ = ((Number)o_).doubleValue();
            } else {
                left_ = ((Character)o_).charValue();
            }
            double right_;
            if (p_ instanceof Number) {
                right_ = ((Number)p_).doubleValue();
            } else {
                right_ = ((Character)p_).charValue();
            }
            nb_ = left_ % right_;
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }

    static ClassArgumentMatching getResultClass(ClassArgumentMatching _a, Analyzable _cont, ClassArgumentMatching _b) {
        int oa_ = PrimitiveTypeUtil.getOrderClass(_a, _cont);
        String exp_ = _cont.getStandards().getAliasNumber();
        boolean ok_ = true;
        if (oa_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_cont.getCurrentLocation());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(new StringList(_a.getName()));
            _cont.getClasses().getErrorsDet().add(un_);
            ok_ = false;
        }
        int ob_ = PrimitiveTypeUtil.getOrderClass(_b, _cont);
        if (ob_ == 0) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_cont.getCurrentLocation());
            un_.setFileName(_cont.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(new StringList(_b.getName()));
            _cont.getClasses().getErrorsDet().add(un_);
            ok_ = false;
        }
        if (!ok_) {
            return new ClassArgumentMatching(exp_);
        }
        return getQuickResultClass(_a, oa_, _cont, _b, ob_);
    }

    static ClassArgumentMatching getQuickResultClass(ClassArgumentMatching _a, int _oa, Analyzable _cont, ClassArgumentMatching _b, int _ob) {
        ClassArgumentMatching arg_;
        int max_ = Math.max(_oa, _ob);
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        LgNames stds_ = _cont.getStandards();
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _cont);
        if (max_ < intOrder_) {
            arg_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        return PrimitiveTypeUtil.toPrimitive(arg_, true, _cont);
    }

    @Override
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassArgumentMatching a_ = chidren_.first().getResultClass();
        ResultOperand r_;
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        ClassArgumentMatching c_ = chidren_.last().getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
        r_ = analyzeOper(a_, ops_.firstValue(), c_, _conf);
        setCatenize(r_);
        a_ = r_.getResult();
        setResultClass(a_);
        String res_ = a_.getName();
        if (PrimitiveTypeUtil.isPrimitive(res_, _conf)) {
            a_.setUnwrapObject(res_);
        }
    }
    abstract ResultOperand analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, Analyzable _cont);
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
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
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
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(false));
        }
        vars_.getFields().put(this, fieldsAfter_);
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(false));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getVariables().put(this, variablesAfter_);
    }
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont);
    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, Analyzable _an);

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode o_ = chidren_.first();
        Argument a_ = _nodes.getVal(o_).getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        o_ = chidren_.last();
        Argument c_ = _nodes.getVal(o_).getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        Argument r_;
        r_ = calculateOper(a_, ops_.firstValue(), c_, _conf);
        if (_conf.getException() != null) {
            return r_;
        }
        a_ = r_;
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        Argument r_;
        r_ = calculateOperAna(a_, ops_.firstValue(), c_, _conf);
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _conf);
    }
    @Override
    public final void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        Argument c_ = chidren_.last().getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ops_.firstKey(), _conf);
        Argument r_;
        r_ = calculateOper(a_, ops_.firstValue(), c_, _conf);
        if (_conf.getException() != null) {
            return;
        }
        a_ = r_;
        setSimpleArgument(a_, _conf);
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }

    @Override
    final void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    abstract void setCatenize(ResultOperand _res);
}
