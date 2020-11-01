package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecDimensionArrayInstancing extends
        ExecAbstractArrayInstancingOperation {
    private int countArrayDims;

    public ExecDimensionArrayInstancing(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent, int _countArrayDims) {
        super(_opCont, _intermediateDottedOperation, _arrayInstancingContent);
        countArrayDims = _countArrayDims;
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments,
                         ContextEl _conf) {
        CustList<ExecOperationNode> filter_ = getChildrenNodes();
        String m_= getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(m_);
        setRelOffsetPossibleLastPage(off_, _conf);
        String className_ = getClassName();
        className_ = _conf.formatVarType(className_);
        className_ = StringExpUtil.getPrettyArrayType(className_, countArrayDims);

        int[] args_ = new int[filter_.size()];

        int i_ = IndexConstants.FIRST_INDEX;
        Ints offs_ = new Ints();
        for (ExecOperationNode o: filter_) {
            NumberStruct n_ = NumParsers.convertToNumber(_arguments.get(i_).getStruct());
            int offset_ = getIndexBegin()+o.getIndexInEl() + off_;
            offs_.add(offset_);
            _conf.setOffset(offset_);
            int dim_ = n_.intStruct();
            args_[i_] = dim_;
            i_++;
        }
        Ints dims_;
        dims_ = new Ints();
        for (int d: args_) {
            dims_.add(d);
        }
        Struct res_ = ExecTemplates.newCustomArrayOrExc(offs_,className_, dims_, _conf);
        if (res_ instanceof ErrorStruct) {
            _conf.setCallingState(new CustomFoundExc(res_));
            return new Argument();
        }
        return new Argument(res_);
    }

}
