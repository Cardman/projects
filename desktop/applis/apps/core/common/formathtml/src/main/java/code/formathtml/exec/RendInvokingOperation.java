package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.*;
import code.formathtml.util.RendArgumentList;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendInvokingOperation extends RendMethodOperation implements RendPossibleIntermediateDotted {
    private boolean intermediate;

    public RendInvokingOperation(
            InvokingOperation _inter) {
        super(_inter);
        intermediate = _inter.isIntermediateDottedOperation();
    }

    public RendInvokingOperation(int _indexChild, ClassArgumentMatching _res, int _order,
                                 boolean _intermediate) {
        super(_indexChild,_res,_order);
        intermediate = _intermediate;
    }
    public static RendArgumentList listNamedArguments(IdMap<RendDynOperationNode, ArgumentsPair> _all, CustList<RendDynOperationNode> _children) {
        RendArgumentList out_ = new RendArgumentList();
        CustList<Argument> args_ = out_.getArguments();
        CustList<RendDynOperationNode> filter_ = out_.getFilter();
        CustList<RendNamedArgumentOperation> named_ = new CustList<RendNamedArgumentOperation>();
        for (RendDynOperationNode c: _children) {
            if (c instanceof RendNamedArgumentOperation) {
                named_.add((RendNamedArgumentOperation)c);
                filter_.add(c);
            } else if (!(c instanceof RendStaticInitOperation)){
                args_.add(getArgument(_all,c));
                filter_.add(c);
            }
        }
        while (!named_.isEmpty()) {
            RendNamedArgumentOperation min_ = named_.first();
            int minIndex_ = min_.getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                RendNamedArgumentOperation elt_ = named_.get(i);
                int index_ = elt_.getIndex();
                if (index_ < minIndex_) {
                    min_ = elt_;
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            args_.add(getArgument(_all,min_));
            named_.remove(i_);
        }
        return out_;
    }
    public static CustList<Argument> listArguments(CustList<RendDynOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            int natVararg_ = _natVararg;
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                if (_children.get(i) instanceof RendIdFctOperation
                        || _children.get(i) instanceof RendVarargOperation) {
                    natVararg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVararg_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            ExecTemplates.setElements(optArgs_,str_);
            Argument argRem_ = new Argument(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof RendIdFctOperation
                    || _children.get(i) instanceof RendVarargOperation) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
        return firstArgs_;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
