package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.DimensionArrayInstancing;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public final class ExecDimensionArrayInstancing extends
        ExecAbstractArrayInstancingOperation {
    private int countArrayDims;

    public ExecDimensionArrayInstancing(DimensionArrayInstancing _d) {
        super(_d);
        countArrayDims = _d.getCountArrayDims();
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        if (!_conf.isGearConst()) {
            return;
        }

        for (ExecOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        int nbCh_ = chidren_.size();
        int[] args_;

        args_ = new int[chidren_.size()];
        for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
            Struct n_ = arguments_.get(i).getStruct();
            if (!(n_ instanceof NumberStruct)) {
                return;
            }
            int dim_ = ((NumberStruct)n_).getInstance().intValue();
            if (dim_ < 0) {
                return;
            }
            args_[i] = dim_;
        }
        Argument a_ = new Argument();
        Numbers<Integer> dims_;
        dims_ = new Numbers<Integer>();
        for (int d: args_) {
            dims_.add(d);
        }
        String className_ = getClassName();
        className_ = PrimitiveTypeUtil.getPrettyArrayType(className_, countArrayDims);
        a_.setStruct(PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf));
        setSimpleArgumentAna(a_, _conf);
    }
    @Override
    Argument getArgument(CustList<Argument> _arguments,
            ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String size_;
        size_ = stds_.getAliasBadSize();
        CustList<ExecOperationNode> filter_ = getChildrenNodes();
        String m_= getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = getClassName();
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(className_, _conf);
        className_ = PrimitiveTypeUtil.getPrettyArrayType(className_, countArrayDims);

        int[] args_;

        args_ = new int[filter_.size()];
        int i_ = CustList.FIRST_INDEX;
        for (ExecOperationNode o: filter_) {
            NumberStruct n_ = (NumberStruct)_arguments.get(i_).getStruct();
            setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
            int dim_ = n_.getInstance().intValue();
            if (dim_ < 0) {
                _conf.setException(new ErrorStruct(_conf,StringList.concat(String.valueOf(dim_),RETURN_LINE,String.valueOf(i_),RETURN_LINE),size_));
                Argument a_ = new Argument();
                return a_;
            }
            args_[i_] = dim_;
            i_++;
        }
        Argument a_ = new Argument();
        Numbers<Integer> dims_;
        dims_ = new Numbers<Integer>();
        for (int d: args_) {
            dims_.add(d);
        }
        a_.setStruct(PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf));
        return a_;
    }

}
