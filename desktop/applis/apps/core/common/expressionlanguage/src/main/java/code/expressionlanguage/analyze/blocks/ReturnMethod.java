package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.exec.blocks.ExecReturnMethod;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        String retType_ = processReturnValue(_page);
        if (retType_.isEmpty()) {
            ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), true,expressionOffset,null, retType_);
            exec_.setFile(_page.getBlockToWrite().getFile());
            _page.getBlockToWrite().appendChild(exec_);
            _page.getCoverage().putBlockOperations(exec_,this);
            return;
        }
        MethodAccessKind stCtx_ = f_.getStaticContext();
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(stCtx_), _page);
        if (!_page.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(_page.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        checkTypes(retType_, op_.last(), _page.getCurrentRoot(), _page);
        ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), false,expressionOffset,op_, retType_);
        root = _page.getCurrentRoot();
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private String processReturnValue(AnalyzedPageEl _page) {
        LgNames stds_ = _page.getStandards();
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
    private void checkTypes(String _retType, ExecOperationNode _ret, OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching ret_ = _root.getResultClass();
        LgNames stds_ = _page.getStandards();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(ret_);
        mapping_.setParam(_retType);
        if (StringList.quickEq(_retType, stds_.getAliasVoid())) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //original type
            cast_.buildError(_page.getAnalysisMessages().getVoidType(),
                    _retType);
            _page.addLocError(cast_);
            getErrorsBlock().add(cast_.getBuiltError());
            setReachableError(true);
            return;
        }
        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
            //look for implicit casts
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_retType, ret_, _page);
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
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(ret_.getNames(), "&"),
                        _retType);
                _page.addLocError(cast_);
                setReachableError(true);
                getErrorsBlock().add(cast_.getBuiltError());
            }

        }
        if (AnaTypeUtil.isPrimitive(_retType, _page)) {
            ret_.setUnwrapObject(_retType, _page.getStandards());
        }
        ElUtil.setImplicits(_ret, _page, _root);
    }


    @Override
    public void abrupt(AnalyzingEl _anEl) {
        super.abrupt(_anEl);
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
