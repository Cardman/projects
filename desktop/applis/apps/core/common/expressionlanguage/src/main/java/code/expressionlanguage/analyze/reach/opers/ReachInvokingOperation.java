package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.analyze.reach.opers.util.*;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ReachInvokingOperation extends ReachMethodOperation implements ReachCalculable {
    private final int naturalVararg;
    private StandardMethod standardMethod;
    private final String lastType;
    ReachInvokingOperation(StandardMethod _standardMethod, AbstractCallFctOperation _meta, OperationNode _info) {
        super(_info);
        standardMethod = _standardMethod;
        naturalVararg = _meta.getCallFctContent().getNaturalVararg();
        lastType = _meta.getCallFctContent().getLastType();
    }
    ReachInvokingOperation(StandardInstancingOperation _meta) {
        super(_meta);
        naturalVararg = _meta.getNaturalVararg();
        lastType = _meta.getLastType();
    }

    void resultMethod(AnalyzedPageEl _page, Struct _instance) {
        CustList<Argument> firstArgs_ = getArguments();
        Struct out_ = AnaApplyCoreMethodUtil.invokeAnalyzisStdMethod(standardMethod, _instance, _page, Argument.toArgArray(firstArgs_));
        if (out_ == null) {
            return;
        }
        Argument arg_ = new Argument(out_);
        setSimpleArgumentAna(arg_);
    }

    CustList<Argument> getArguments() {
        CustList<ReachOperationNode> chidren_ = getChildrenNodes();
        AnaArgumentList list_ = listNamedArguments(chidren_);
        return quickListArguments(naturalVararg, lastType, list_.getArguments());
    }

    private static AnaArgumentList listNamedArguments(CustList<ReachOperationNode> _children) {
        AnaArgumentList out_ = new AnaArgumentList();
        CustList<Argument> args_ = out_.getArguments();
        CustList<ReachNamedArgumentOperation> named_ = new CustList<ReachNamedArgumentOperation>();
        for (ReachOperationNode c: _children) {
            if (c instanceof ReachNoopOperation) {
                continue;
            }
            if (c instanceof ReachNamedArgumentOperation) {
                named_.add((ReachNamedArgumentOperation)c);
            } else {
                args_.add(c.getArgument());
            }
        }
        while (!named_.isEmpty()) {
            ReachOperationIndexer indexer_ = new ReachOperationIndexer(named_);
            int i_ = indexer_.getIndex();
            args_.add(named_.get(i_).getArgument());
            named_.remove(i_);
        }
        return out_;
    }
    private static CustList<Argument> quickListArguments(int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg <= -1) {
            return _nodes;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        CustList<Struct> optArgs_ = new CustList<Struct>();
        int lenCh_ = _nodes.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
            Argument a_ = _nodes.get(i);
            if (i >= _natVararg) {
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

}
