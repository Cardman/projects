package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public abstract class AbstractArrayElementOperation extends
        AbstractArrayInstancingOperation {

    public AbstractArrayElementOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public final void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (!_conf.isGearConst()) {
            return;
        }

        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        int nbCh_ = chidren_.size();
        int[] args_;
        args_ = new int[CustList.ONE_ELEMENT];
        args_[CustList.FIRST_INDEX] = chidren_.size();
        Argument a_ = new Argument();

        Numbers<Integer> dims_;
        dims_ = new Numbers<Integer>();
        dims_.add(nbCh_);
        String cl_ = getClassName();
        ArrayStruct str_ = PrimitiveTypeUtil.newCustomArray(cl_, dims_, _conf);
        for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
            Argument chArg_ = arguments_.get(i);
            if (!setCheckedElement(str_, i, chArg_, _conf)) {
                return;
            }
        }
        a_.setStruct(str_);
        setSimpleArgumentAna(a_, _conf);
    }
    @Override
    final Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: chidren_) {
            filter_.add(o);
        }
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String cl_ = getClassName();
        String className_;
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(cl_, _conf);

        int nbCh_ = chidren_.size();
        int[] args_;

        args_ = new int[CustList.ONE_ELEMENT];
        args_[CustList.FIRST_INDEX] = chidren_.size();
        Argument a_ = new Argument();

        Numbers<Integer> dims_;
        dims_ = new Numbers<Integer>();
        dims_.add(nbCh_);
        Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
        for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
            Argument chArg_ = _arguments.get(i);
            ArrOperation.setCheckedElement(str_, i, chArg_, _conf);
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return a_;
            }
        }
        a_.setStruct(str_);
        return a_;
    }
}
