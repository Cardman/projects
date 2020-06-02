package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.DimensionArrayInstancing;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ExecDimensionArrayInstancing extends
        ExecAbstractArrayInstancingOperation {
    private int countArrayDims;

    protected ExecDimensionArrayInstancing(DimensionArrayInstancing _d) {
        super(_d);
        countArrayDims = _d.getCountArrayDims();
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments,
                         ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String size_;
        size_ = stds_.getAliasBadSize();
        CustList<ExecOperationNode> filter_ = getChildrenNodes();
        String m_= getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = getClassName();
        PageEl page_ = _conf.getLastPage();
        className_ = page_.formatVarType(className_, _conf);
        className_ = PrimitiveTypeUtil.getPrettyArrayType(className_, countArrayDims);

        int[] args_;

        args_ = new int[filter_.size()];
        int i_ = CustList.FIRST_INDEX;
        for (ExecOperationNode o: filter_) {
            NumberStruct n_ = ClassArgumentMatching.convertToNumber(_arguments.get(i_).getStruct());
            setRelativeOffsetPossibleLastPage(o.getIndexInEl()+off_, _conf);
            int dim_ = n_.intStruct();
            if (dim_ < 0) {
                _conf.setException(new ErrorStruct(_conf,StringList.concat(String.valueOf(dim_),RETURN_LINE,String.valueOf(i_),RETURN_LINE),size_));
                return Argument.createVoid();
            }
            args_[i_] = dim_;
            i_++;
        }
        Argument a_ = new Argument();
        Ints dims_;
        dims_ = new Ints();
        for (int d: args_) {
            dims_.add(d);
        }
        a_.setStruct(PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf));
        return a_;
    }

}
