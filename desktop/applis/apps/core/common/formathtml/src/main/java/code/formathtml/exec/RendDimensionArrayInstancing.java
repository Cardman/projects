package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.DimensionArrayInstancing;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class RendDimensionArrayInstancing extends
        RendAbstractArrayInstancingOperation {
    private int countArrayDims;

    public RendDimensionArrayInstancing(DimensionArrayInstancing _d) {
        super(_d);
        countArrayDims = _d.getCountArrayDims();
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments,
                         Configuration _conf) {
        CustList<RendDynOperationNode> filter_ = getChildrenNodes();
        String m_= getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = getClassName();
        PageEl page_ = _conf.getPageEl();
        className_ = page_.formatVarType(className_, _conf.getContext());
        className_ = StringExpUtil.getPrettyArrayType(className_, countArrayDims);

        int[] args_;

        args_ = new int[filter_.size()];
        int i_ = CustList.FIRST_INDEX;
        Ints offs_ = new Ints();
        for (RendDynOperationNode o: filter_) {
            NumberStruct n_ = ClassArgumentMatching.convertToNumber(_arguments.get(i_).getStruct());
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
        Struct res_ = newCustomArrayOrExc(offs_,className_, dims_, _conf);
        if (res_ instanceof ErrorStruct) {
            _conf.setException(res_);
            return new Argument();
        }
        return new Argument(res_);
    }
    public static Struct newCustomArrayOrExc(Ints _filter, String _className, Ints _dims, Configuration _cont) {
        Ints dims_ = new Ints();
        String size_;
        ContextEl ctx_ = _cont.getContext();
        LgNames lgNames_ = ctx_.getStandards();
        size_ = lgNames_.getAliasBadSize();
        if (_dims.isEmpty()) {
            return new ErrorStruct(ctx_,size_);
        }
        int j_ = 0;
        for (int s: _dims) {
            if (s < 0) {
                if (_filter.isValidIndex(j_)) {
                    int off_ = _filter.get(j_);
                    _cont.setOpOffset(off_);
                }
                return new ErrorStruct(ctx_,StringList.concat(Integer.toString(s),"<0"),size_);
            }
            dims_.add(s);
            j_++;
        }
        return ExecTemplates.newCustomArray(_className, dims_, ctx_);
    }
}
