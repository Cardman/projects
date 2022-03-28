package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import code.util.core.IndexConstants;

public final class ExecDimensionArrayInstancing extends
        ExecAbstractArrayInstancingOperation {
    private final int countArrayDims;

    public ExecDimensionArrayInstancing(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent, int _countArrayDims) {
        super(_opCont, _intermediateDottedOperation, _arrayInstancingContent);
        countArrayDims = _countArrayDims;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<ExecOperationNode> filter_ = getChildrenNodes();
        int off_ = getMethodName();
        setRelOffsetPossibleLastPage(off_, _stack);
        String className_ = StringExpUtil.getPrettyArrayType(_stack.formatVarType(getClassName()), countArrayDims);

        int[] args_ = new int[filter_.size()];

        int i_ = IndexConstants.FIRST_INDEX;
        Ints offs_ = new Ints();
        for (ExecOperationNode o: filter_) {
            Argument arg_ = getArgument(_nodes, o);
            NumberStruct n_ = NumParsers.convertToNumber(arg_.getStruct());
            int offset_ = o.getIndexInEl() + off_;
            offs_.add(offset_);
            int dim_ = n_.intStruct();
            args_[i_] = dim_;
            i_++;
        }
        Ints dims_ = new Ints();
        for (int d: args_) {
            dims_.add(d);
        }
        Struct newArr_ = ExecArrayTemplates.newCustomArrayOrExc(offs_,className_, dims_, _conf, _stack);
        Argument res_;
        if (newArr_ instanceof ErrorStruct) {
            _stack.setCallingState(new CustomFoundExc(newArr_));
            res_ = new Argument();
        } else {
            res_ = new Argument(newArr_);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
