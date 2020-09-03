package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.exec.blocks.ExecReturnMethod;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class ReturnMethod extends AbruptBlock {

    private final String expression;

    private OperationNode root;
    private int expressionOffset;
    private boolean implicit;

    public ReturnMethod(OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public boolean isEmpty() {
        return expression.trim().isEmpty();
    }

    public String getExpression() {
        return expression;
    }


    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        String retType_ = processReturnValue(_cont);
        if (retType_.isEmpty()) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), true,expressionOffset,null, retType_);
            exec_.setFile(page_.getBlockToWrite().getFile());
            page_.getBlockToWrite().appendChild(exec_);
            _cont.getCoverage().putBlockOperations(_cont, exec_,this);
            return;
        }
        MethodAccessKind stCtx_ = f_.getStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(stCtx_));
        if (!page_.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(page_.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        checkTypes(_cont, retType_, op_.last());
        ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), false,expressionOffset,op_, retType_);
        root = page_.getCurrentRoot();
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private String processReturnValue(ContextEl _cont) {
        LgNames stds_ = _cont.getStandards();
        String retType_ = stds_.getAliasVoid();
        BracedBlock par_ = getParent();
        while (par_ != null) {
            if (par_ instanceof NamedFunctionBlock) {
                NamedFunctionBlock meth_;
                meth_ = (NamedFunctionBlock) par_;
                retType_ = meth_.getImportedReturnType();
                break;
            }
            par_ = par_.getParent();
        }
        if (StringList.quickEq(retType_, stds_.getAliasVoid())) {
            if (isEmpty()) {
                return EMPTY_STRING;
            }
        }
        return retType_;
    }
    private void checkTypes(ContextEl _cont, String _retType, ExecOperationNode _ret) {
        ClassArgumentMatching ret_ = _ret.getResultClass();
        LgNames stds_ = _cont.getStandards();
        StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(ret_);
        mapping_.setParam(_retType);
        if (StringList.quickEq(_retType, stds_.getAliasVoid())) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //original type
            cast_.buildError(_cont.getAnalysisMessages().getVoidType(),
                    _retType);
            _cont.addError(cast_);
            getErrorsBlock().add(cast_.getBuiltError());
            setReachableError(true);
            return;
        }
        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont)) {
            //look for implicit casts
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_cont, _retType, ret_);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                ret_.getImplicits().add(cl_);
                ret_.setRootNumber(res_.getRootNumber());
                ret_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(expressionOffset);
                //original type
                cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(ret_.getNames(), "&"),
                        _retType);
                _cont.addError(cast_);
                setReachableError(true);
                getErrorsBlock().add(cast_.getBuiltError());
            }

        }
        if (PrimitiveTypeUtil.isPrimitive(_retType, _cont)) {
            ret_.setUnwrapObject(_retType);
        }
        ElUtil.setImplicits(_ret,_cont);
    }


    @Override
    public void abrupt(ContextEl _an, AnalyzingEl _anEl) {
        super.abrupt(_an, _anEl);
        BracedBlock par_ = getParent();
        IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
        BracedBlock a_;
        if (_anEl.getParentsReturnables().isEmpty()) {
            a_ = _anEl.getRoot();
        } else {
            a_ = (BracedBlock) _anEl.getParentsReturnables().last();
        }
        while (par_ != a_) {
            pars_.add(par_);
            par_ = par_.getParent();
        }
        if (a_ instanceof Eval) {
            IdMap<ReturnMethod, Eval> breakables_ = _anEl.getReturnables();
            IdMap<ReturnMethod, IdMap<Eval, IdList<BracedBlock>>> breakablesAncestors_ = _anEl.getReturnablesAncestors();
            IdMap<Eval, IdList<BracedBlock>> id_;
            id_ = new IdMap<Eval, IdList<BracedBlock>>();
            id_.put((Eval) a_, pars_);
            breakablesAncestors_.put(this, id_);
            breakables_.put(this, (Eval) a_);
        } else {
            IdMap<ReturnMethod, MemberCallingsBlock> breakables_ = _anEl.getReturnablesCallings();
            IdMap<ReturnMethod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>> breakablesAncestors_ = _anEl.getReturnablesAncestorsCallings();
            IdMap<MemberCallingsBlock, IdList<BracedBlock>> id_;
            id_ = new IdMap<MemberCallingsBlock, IdList<BracedBlock>>();
            id_.put((MemberCallingsBlock) a_, pars_);
            breakablesAncestors_.put(this, id_);
            breakables_.put(this, (MemberCallingsBlock) a_);
        }
    }

    public OperationNode getRoot() {
        return root;
    }

    public boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(boolean implicit) {
        this.implicit = implicit;
    }
}
