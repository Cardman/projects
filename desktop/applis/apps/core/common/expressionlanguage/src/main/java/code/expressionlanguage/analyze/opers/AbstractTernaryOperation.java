package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.DeadCodeTernary;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.inherits.ResultTernary;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractTernaryOperation extends MethodOperation {

    private int offsetLocal;
    private ClassMethodId test;

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
    public final void tryCalculateNode(AnalyzedPageEl _page) {
        tryGetResult(this, _page);
    }
    private static void tryGetResult(MethodOperation _to, AnalyzedPageEl _page) {
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
        _to.setSimpleArgumentAna(arg_, _page);
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offsetLocal, _page);
        OperationNode opOne_ = chidren_.first();
        AnaClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getStandards().getAliasPrimBoolean(), clMatch_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                clMatch_.getImplicits().add(cl_);
                clMatch_.setRootNumber(res_.getRootNumber());
                clMatch_.setMemberNumber(res_.getMemberNumber());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(clMatch_, _page);
                if (trueOp_.isFoundMethod()) {
                    ClassMethodId test_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                    clMatch_.getImplicitsTest().add(test_);
                    clMatch_.setRootNumberTest(trueOp_.getRootNumber());
                    clMatch_.setMemberNumberTest(trueOp_.getMemberNumber());
                    test = test_;
                } else {
                    setRelativeOffsetPossibleAnalyzable(opOne_.getIndexInEl()+1, _page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
                    //after first arg separator len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(clMatch_.getNames(),"&"));
                    _page.getLocalizer().addError(un_);
                    getErrs().add(un_.getBuiltError());
                }
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            list_.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
            list_.add(new PartOffset("</a>",i_+1));
            getPartOffsetsChildren().add(list_);
        }
        opOne_.getResultClass().setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        opOne_.getResultClass().setCheckOnlyNullPe(true);
        opOne_.quickCancel();
        OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
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
            if (AnaTypeUtil.isPrimitive(type_, _page)) {
                opTwo_.getResultClass().setUnwrapObject(type_, _page.getStandards());
                opThree_.getResultClass().setUnwrapObject(type_, _page.getStandards());
                opTwo_.quickCancel();
                opThree_.quickCancel();
            }
            setResultClass(new AnaClassArgumentMatching(type_));
            checkDeadCode(opOne_, _page);
            return;
        }
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        ResultTernary res_ = AnaTemplates.getResultTernary(one_, null, two_, null, vars_, _page);
        if (res_.isUnwrapFirst()) {
            opTwo_.getResultClass().setUnwrapObjectNb(res_.getCastPrim());
            opTwo_.quickCancel();
        }
        if (res_.isUnwrapSecond()) {
            opThree_.getResultClass().setUnwrapObjectNb(res_.getCastPrim());
            opThree_.quickCancel();
        }
        setResultClass(new AnaClassArgumentMatching(res_.getTypes()));
        checkDeadCode(opOne_, _page);
    }

    private void checkDeadCode(OperationNode _opOne, AnalyzedPageEl _page) {
        if (_opOne.getArgument() != null) {
            DeadCodeTernary d_ = new DeadCodeTernary();
            d_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            d_.setFileName(_page.getLocalizer().getCurrentFileName());
            _page.getLocalizer().addWarning(d_);
        }
    }

    public ClassMethodId getTest() {
        return test;
    }
}
