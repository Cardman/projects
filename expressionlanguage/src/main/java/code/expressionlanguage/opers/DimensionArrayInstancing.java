package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.errors.custom.BadOperandsNumber;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public final class DimensionArrayInstancing extends
        AbstractArrayInstancingOperation {
    private int countArrayDims;

    public DimensionArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        countArrayDims = getOperations().getCountArrays();
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        className_ = className_.trim();
        className_ = _conf.resolveCorrectType(className_);
        if (chidren_.isEmpty()) {
            BadOperandsNumber badCall_ = new BadOperandsNumber();
            badCall_.setOperandsNumber(0);
            badCall_.setFileName(_conf.getCurrentFileName());
            badCall_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badCall_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        for (OperationNode o: chidren_) {
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
            if (!o.getResultClass().isNumericInt(_conf)) {
                ClassArgumentMatching cl_ = o.getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                un_.setOperands(cl_);
                _conf.getClasses().addError(un_);
            }
            o.getResultClass().setUnwrapObject(_conf.getStandards().getAliasPrimInteger());
        }
        setClassName(className_);
        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(className_, chidren_.size()+countArrayDims)));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (!_conf.isGearConst()) {
            return;
        }

        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        int nbCh_ = chidren_.size();
        int[] args_;

        args_ = new int[chidren_.size()];
        for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
            Struct n_ = arguments_.get(i).getStruct();
            if (!(n_ instanceof NumberStruct)) {
                return;
            }
            int dim_ = ((NumberStruct)n_).getInstance().intValue();
            if (dim_ < 0) {
                return;
            }
            args_[i] = dim_;
        }
        Argument a_ = new Argument();
        Numbers<Integer> dims_;
        dims_ = new Numbers<Integer>();
        for (int d: args_) {
            dims_.add(d);
        }
        String className_ = getClassName();
        className_ = PrimitiveTypeUtil.getPrettyArrayType(className_, countArrayDims);
        a_.setStruct(PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf));
        setSimpleArgumentAna(a_, _conf);
    }
    public int getCountArrayDims() {
        return countArrayDims;
    }

}
