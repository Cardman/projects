package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.analyze.reach.opers.util.AnaArgumentList;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ReachInvokingOperation extends ReachMethodOperation implements ReachCalculable {
    private int naturalVararg;
    private String lastType;
    ReachInvokingOperation(AbstractCallFctOperation _meta, OperationNode _info) {
        super(_info);
        naturalVararg = _meta.getCallFctContent().getNaturalVararg();
        lastType = _meta.getCallFctContent().getLastType();
    }
    ReachInvokingOperation(StandardInstancingOperation _meta) {
        super(_meta);
        naturalVararg = _meta.getNaturalVararg();
        lastType = _meta.getLastType();
    }

    void resultMethod(AnalyzedPageEl _page, Struct _instance, ClassMethodId _classMethodId) {
        CustList<Argument> firstArgs_ = getArguments();
        Struct out_ = AnaApplyCoreMethodUtil.invokeAnalyzisStdMethod(_classMethodId, _instance, _page, Argument.toArgArray(firstArgs_));
        if (out_ == null) {
            return;
        }
        Argument arg_ = new Argument(out_);
        setSimpleArgumentAna(arg_);
    }

    CustList<Argument> getArguments() {
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        AnaArgumentList list_ = listNamedArguments(chidren_);
        CustList<ReachOperationNode> filterCh_ = list_.getFilter();
        return quickListArguments(filterCh_, naturalVararg, lastType, list_.getArguments());
    }

    private static AnaArgumentList listNamedArguments(CustList<ReachOperationNode> _children) {
        AnaArgumentList out_ = new AnaArgumentList();
        CustList<Argument> args_ = out_.getArguments();
        CustList<ReachOperationNode> filter_ = out_.getFilter();
        CustList<ReachNamedArgumentOperation> named_ = new CustList<ReachNamedArgumentOperation>();
        for (ReachOperationNode c: _children) {
            if (c instanceof ReachNamedArgumentOperation) {
                named_.add((ReachNamedArgumentOperation)c);
                filter_.add(c);
            } else {
                args_.add(c.getArgument());
                filter_.add(c);
            }
        }
        while (!named_.isEmpty()) {
            int minIndex_ = named_.first().getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                ReachNamedArgumentOperation elt_ = named_.get(i);
                int index_ = elt_.getIndex();
                if (index_ < minIndex_) {
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            args_.add(named_.get(i_).getArgument());
            named_.remove(i_);
        }
        return out_;
    }
    private static CustList<Argument> quickListArguments(CustList<ReachOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Struct> optArgs_ = new CustList<Struct>();
            int lenCh_ = _children.size();
            int natVarArg_ = _natVararg;
            for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
                if (_children.get(i) instanceof ReachNoopOperation) {
                    natVarArg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVarArg_) {
                    optArgs_.add(a_.getStruct());
                } else {
                    firstArgs_.add(a_);
                }
            }
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = NumParsers.setElements(optArgs_,clArr_);
            Argument argRem_ = new Argument(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof ReachNoopOperation) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
        return firstArgs_;
    }

}
