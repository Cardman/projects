package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.*;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.InvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

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
    public static CustList<Argument> listArguments(CustList<RendDynOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes, ExecutableCode _context) {
        if (!_children.isEmpty() && _children.first() instanceof RendVarargOperation) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            int i_ = CustList.FIRST_INDEX;
            for (RendDynOperationNode o: _children) {
                if (o instanceof RendVarargOperation) {
                    i_++;
                    continue;
                }
                if (o instanceof RendFirstOptOperation) {
                    opt_ = true;
                }
                Argument a_ = _nodes.get(i_);
                if (opt_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
                i_++;
            }
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getName();
            g_ = _context.getOperationPageEl().formatVarType(g_, _context);
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            Templates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            int natVararg_ = _natVararg;
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                if (_children.get(i) instanceof RendIdFctOperation) {
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
            String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            Templates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof RendIdFctOperation) {
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
