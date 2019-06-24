package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecArrOperation extends ExecInvokingOperation implements DirectExecCalculableOperation,ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    public ExecArrOperation(ArrOperation _arr) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        if (!_conf.isGearConst()) {
            return;
        }
        if (getPreviousArgument() == null) {
            return;
        }
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        if (!(array_ instanceof ArrayStruct)) {
            return;
        }
        Struct o_ = chidren_.last().getArgument().getStruct();
        if (!(o_ instanceof NumberStruct)) {
            return;
        }
        int index_ = ((NumberStruct)o_).intStruct();
        if (index_ < 0) {
            return;
        }
        Struct[] str_ = ((ArrayStruct)array_).getInstance();
        if (index_ >= str_.length) {
            return;
        }
        Struct res_ = str_[index_];
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_);
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(max_, _conf);
        if (resultCanBeSet()) {
            setQuickSimpleArgument(a_, _conf);
        } else {
            setSimpleArgument(a_, _conf);
        }
    }

    Argument getArgument(int _maxIndexChildren, ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.FIRST_INDEX; i < _maxIndexChildren; i++) {
            NumberStruct o_ = (NumberStruct)chidren_.get(i).getArgument().getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = ExecInvokingOperation.getElement(array_, o_, _conf);
            if (_conf.getContextEl().hasException()) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }
    static void setCheckedElement(Struct _array,NumberStruct _index, Argument _element, ExecutableCode _conf) {
        ExecInvokingOperation.setElement(_array, _index, _element.getStruct(), _conf);
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        ExecDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        a_.setStruct(affectArray(array_, last_, lastElement_.getIndexInEl(), _right, _conf));
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        ExecDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        a_.setStruct(compoundAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _right, _conf));
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        ExecDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        a_.setStruct(semiAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _post, _conf));
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Struct affectArray(Struct _array,Argument _index, int _indexEl, Argument _right, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        NumberStruct o_ = (NumberStruct)_index.getStruct();
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return _right.getStruct();
        }
        ExecInvokingOperation.setElement(_array, o_, _right.getStruct(), _conf);
        return _right.getStruct();
    }

    Struct compoundAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, Argument _right, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        NumberStruct o_ = (NumberStruct)_index.getStruct();
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return _stored;
        }
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return _stored;
        }
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf);
        return res_.getStruct();
    }
    Struct semiAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, boolean _post, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        NumberStruct o_ = (NumberStruct)_index.getStruct();
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return _stored;
        }
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, clArg_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return _stored;
        }
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf);
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
        return out_.getStruct();
    }

    @Override
    public Argument endCalculate(ExecutableCode _conf, Argument _right) {
        return endCalculate(_conf, false, null, _right);
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, boolean _post,
            Argument _stored, Argument _right) {
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        ExecDynOperationNode lastElement_ = chidren_.last();
        Argument index_ = lastElement_.getArgument();
        ExecInvokingOperation.setElement(array_, (NumberStruct)index_.getStruct(), _right.getStruct(), _conf);
        if (_conf.getContextEl().hasException()) {
            return a_;
        }
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(out_, _conf);
        return out_;
    }
}
