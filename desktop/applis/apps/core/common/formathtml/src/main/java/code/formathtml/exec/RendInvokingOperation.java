package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public abstract class RendInvokingOperation extends RendMethodOperation implements RendPossibleIntermediateDotted {
    private boolean intermediate;

    private Argument previousArgument;

    public RendInvokingOperation(
            InvokingOperation _inter) {
        super(_inter);
        intermediate = _inter.isIntermediateDottedOperation();
        previousArgument = _inter.getPreviousArgument();
    }

    public RendInvokingOperation(int _indexChild, ClassArgumentMatching _res, int _order,
                                 boolean _intermediate, Argument _previousArgument) {
        super(_indexChild,_res,_order);
        intermediate = _intermediate;
        previousArgument = _previousArgument;
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
            Argument argRem_ = new Argument();
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            ExecTemplates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
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

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

}
