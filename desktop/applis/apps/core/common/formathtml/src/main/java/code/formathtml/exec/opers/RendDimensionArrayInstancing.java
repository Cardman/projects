package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.Ints;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendDimensionArrayInstancing extends
        RendAbstractArrayInstancingOperation {
    private int countArrayDims;

    public RendDimensionArrayInstancing(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent, int _countArrayDims) {
        super(_content, _intermediateDottedOperation, _arrayInstancingContent);
        countArrayDims = _countArrayDims;
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments,
                         Configuration _conf, ContextEl _ctx) {
        CustList<RendDynOperationNode> filter_ = getChildrenNodes();
        String m_= getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = getClassName();
        className_ = StringExpUtil.getPrettyArrayType(className_, countArrayDims);

        int[] args_ = new int[filter_.size()];

        int i_ = IndexConstants.FIRST_INDEX;
        Ints offs_ = new Ints();
        for (RendDynOperationNode o: filter_) {
            NumberStruct n_ = NumParsers.convertToNumber(_arguments.get(i_).getStruct());
            int offset_ = getIndexBegin()+ o.getIndexInEl() + off_;
            offs_.add(offset_);
            _conf.setOpOffset(offset_);
            int dim_ = n_.intStruct();
            args_[i_] = dim_;
            i_++;
        }
        Ints dims_;
        dims_ = new Ints();
        for (int d: args_) {
            dims_.add(d);
        }
        Struct res_ = newCustomArrayOrExc(offs_,className_, dims_, _conf, _ctx);
        if (res_ instanceof ErrorStruct) {
            _ctx.setCallingState(res_);
            return new Argument();
        }
        return new Argument(res_);
    }
    public static Struct newCustomArrayOrExc(Ints _filter, String _className, Ints _dims, Configuration _cont, ContextEl _context) {
        Ints dims_ = new Ints();
        String size_;
        LgNames lgNames_ = _context.getStandards();
        size_ = lgNames_.getContent().getCoreNames().getAliasBadSize();
        if (_dims.isEmpty()) {
            return new ErrorStruct(_context,size_);
        }
        int j_ = 0;
        for (int s: _dims) {
            if (s < 0) {
                if (_filter.isValidIndex(j_)) {
                    int off_ = _filter.get(j_);
                    _cont.setOpOffset(off_);
                }
                return new ErrorStruct(_context, StringUtil.concat(Long.toString(s),"<0"),size_);
            }
            dims_.add(s);
            j_++;
        }
        return ExecTemplates.newCustomArray(_className, dims_, _context);
    }
}
