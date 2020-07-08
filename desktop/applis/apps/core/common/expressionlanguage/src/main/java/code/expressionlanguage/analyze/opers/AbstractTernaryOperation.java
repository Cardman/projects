package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.errors.custom.DeadCodeTernary;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.analyze.inherits.ResultTernary;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractTernaryOperation extends MethodOperation {

    private int offsetLocal;

    public AbstractTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public final void setOffsetLocal(int _offsetLocal) {
        offsetLocal = _offsetLocal;
    }

    public final int getOffsetLocal() {
        return offsetLocal;
    }
    @Override
    public final void tryCalculateNode(ContextEl _conf) {
        tryGetResult(_conf, this);
    }
    private static void tryGetResult(ContextEl _conf, MethodOperation _to) {
        CustList<OperationNode> chidren_ = _to.getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argBool_ = arguments_.first();
        if (argBool_ == null) {
            return;
        }
        Struct str_ = argBool_.getStruct();
        if (!(str_ instanceof BooleanStruct)) {
            return;
        }
        Argument arg_;
        if (BooleanStruct.isTrue(str_)) {
            arg_ = arguments_.get(CustList.SECOND_INDEX);
        } else {
            arg_ = arguments_.last();
        }
        if (arg_ == null) {
            return;
        }
        _to.setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public final void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offsetLocal, _conf);
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        OperationNode opOne_ = chidren_.first();
        ClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_conf)) {
            setRelativeOffsetPossibleAnalyzable(opOne_.getIndexInEl()+1, _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //after first arg separator len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(clMatch_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            list_.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
            list_.add(new PartOffset("</a>",i_+1));
            getPartOffsetsChildren().add(list_);
        }
        opOne_.getResultClass().setUnwrapObject(booleanPrimType_);
        opOne_.cancelArgument();
        OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        Argument firstArg_ = opTwo_.getArgument();
        Argument secondArg_ = opThree_.getArgument();
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        StringMap<StringList> vars_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        OperationNode current_ = this;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractTernaryOperation)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            if (m_.getFirstChild() == current_) {
                break;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String type_ = EMPTY_STRING;
        if (m_ instanceof CastOperation) {
            CastOperation c_ = (CastOperation) m_;
            type_ = c_.getClassName();
        }
        if (!type_.isEmpty()) {
            if (PrimitiveTypeUtil.isPrimitive(type_, _conf)) {
                opTwo_.getResultClass().setUnwrapObject(type_);
                opThree_.getResultClass().setUnwrapObject(type_);
                opTwo_.cancelArgument();
                opThree_.cancelArgument();
            }
            setResultClass(new ClassArgumentMatching(type_));
            checkDeadCode(_conf, opOne_);
            return;
        }
        ResultTernary res_ = AnaTemplates.getResultTernary(one_, firstArg_, two_, secondArg_, vars_, _conf);
        if (res_.isUnwrapFirst()) {
            opTwo_.getResultClass().setUnwrapObject(res_.getTypes().first());
            opTwo_.cancelArgument();
        }
        if (res_.isUnwrapSecond()) {
            opThree_.getResultClass().setUnwrapObject(res_.getTypes().first());
            opThree_.cancelArgument();
        }
        setResultClass(new ClassArgumentMatching(res_.getTypes()));
        checkDeadCode(_conf, opOne_);
    }

    private void checkDeadCode(ContextEl _conf, OperationNode _opOne) {
        if (_opOne.getArgument() != null) {
            DeadCodeTernary d_ = new DeadCodeTernary();
            d_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            d_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            _conf.getAnalyzing().getLocalizer().addWarning(d_);
        }
    }


}
