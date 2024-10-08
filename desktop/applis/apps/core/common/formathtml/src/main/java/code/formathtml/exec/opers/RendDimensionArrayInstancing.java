package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendDimensionArrayInstancing extends
        RendAbstractArrayInstancingOperation {
    private final int countArrayDims;

    public RendDimensionArrayInstancing(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent, int _countArrayDims) {
        super(_content, _intermediateDottedOperation, _arrayInstancingContent);
        countArrayDims = _countArrayDims;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<Struct> arguments_ = getArguments(_nodes,this);
        CustList<RendDynOperationNode> filter_ = getChildrenNodes();
        int off_ = getMethodName();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        String className_ = _rendStack.formatVarType(getClassName());
        className_ = StringExpUtil.getPrettyArrayType(className_, countArrayDims);

        int[] args_ = new int[filter_.size()];

        int i_ = IndexConstants.FIRST_INDEX;
        Ints offs_ = new Ints();
        for (RendDynOperationNode o: filter_) {
            NumberStruct n_ = NumParsers.convertToNumber(arguments_.get(i_));
            int offset_ = o.getIndexInEl() + off_;
            offs_.add(offset_);
            int dim_ = n_.intStruct();
            args_[i_] = dim_;
            i_++;
        }
        Ints dims_;
        dims_ = new Ints();
        for (int d: args_) {
            dims_.add(d);
        }
        Struct newArr_ = newCustomArrayOrExc(offs_,className_, dims_, _context, _rendStack);
        Struct res_;
        if (newArr_ instanceof ErrorStruct) {
            _rendStack.getStackCall().setCallingState(new CustomFoundExc(newArr_));
            res_ = NullStruct.NULL_VALUE;
        } else {
            res_ = newArr_;
        }
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }

    public static Struct newCustomArrayOrExc(Ints _filter, String _className, Ints _dims, ContextEl _context, RendStackCall _rendStackCall) {
        Ints dims_ = new Ints();
        String size_;
        LgNames lgNames_ = _context.getStandards();
        size_ = lgNames_.getContent().getCoreNames().getAliasBadSize();
        if (_dims.isEmpty()) {
            return new ErrorStruct(_context,size_, _rendStackCall.getStackCall());
        }
        int j_ = 0;
        for (int s: _dims) {
            if (s < 0) {
                if (_filter.isValidIndex(j_)) {
                    int off_ = _filter.get(j_);
                    _rendStackCall.setOpOffset(off_);
                }
                return new ErrorStruct(_context, StringUtil.concat(Long.toString(s),"<0"),size_, _rendStackCall.getStackCall());
            }
            dims_.add(s);
            j_++;
        }
        return ExecArrayTemplates.newCustomArray(_className, dims_, _context);
    }
}
