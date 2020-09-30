package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
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
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class ReturnMethod extends AbruptBlock {

    private final String expression;

    private OperationNode root;
    private int expressionOffset;
    private boolean implicit;
    private String returnType = "";

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
            return;
        }
        MethodAccessKind stCtx_ = f_.getStaticContext();
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        root = ElUtil.getRootAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(stCtx_), _page);
        if (!_page.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(_page.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        returnType = retType_;
        checkTypes(retType_, root, _page);
    }

    private String processReturnValue(AnalyzedPageEl _page) {
        String retType_ = _page.getAliasVoid();
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
        if (StringList.quickEq(retType_, _page.getAliasVoid())) {
            if (isEmpty()) {
                return EMPTY_STRING;
            }
        }
        return retType_;
    }
    private void checkTypes(String _retType, OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching ret_ = _root.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(ret_);
        mapping_.setParam(_retType);
        if (StringList.quickEq(_retType, _page.getAliasVoid())) {
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
            ret_.setUnwrapObject(_retType, _page.getPrimitiveTypes());
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

    public String getReturnType() {
        return returnType;
    }
}
