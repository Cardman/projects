package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaExplicitContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ImplicitOperation extends AbstractUnaryOperation {
    private final AnaExplicitContent explicitContent;
    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();

    private AnaTypeFct function;
    public ImplicitOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        explicitContent = new AnaExplicitContent();
        explicitContent.setOffset(getOperations().getOperators().firstKey());
        explicitContent.setClassName(getOperations().getOperators().firstValue());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ explicitContent.getOffset(), _page);
        String extract_ = explicitContent.getClassName().substring(explicitContent.getClassName().indexOf(PAR_LEFT)+1, explicitContent.getClassName().lastIndexOf(PAR_RIGHT));
        StringList types_ = StringExpUtil.getAllSepCommaTypes(extract_);
        if (types_.size() > 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page);
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Long.toString(3),
                    Long.toString(types_.size())
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String res_;
        int leftPar_ = explicitContent.getClassName().indexOf(PAR_LEFT);
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(leftPar_ + 1 + StringExpUtil.getOffset(types_.first()), types_.first(), _page);
        res_ = result_.getResult(_page);
        explicitContent.setClassName(res_);
        partOffsets.add(result_);
        setResultClass(new AnaClassArgumentMatching(explicitContent.getClassName(), _page.getPrimitiveTypes()));
        if (!StringExpUtil.customCast(res_)) {
            return;
        }
        if (AnaTypeUtil.isPrimitive(explicitContent.getClassName(), _page)) {
            getFirstChild().getResultClass().setUnwrapObject(explicitContent.getClassName(), _page.getPrimitiveTypes());
        }
        if (types_.size() == 2 && StringUtil.quickEq(types_.last(), _page.getKeyWords().getKeyWordId())) {
            return;
        }
        ClassMethodId uniq_;
        String exp_ = _page.getKeyWords().getKeyWordCast();
        if (types_.size() == 2){
            //add a type for full id
            String arg_ = types_.last();
            AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectAccessibleType(leftPar_ + types_.first().length() + 2 + StringExpUtil.getOffset(arg_), arg_.trim(), explicitContent.getClassName(), _page);
            partOffsets.add(resolved_);
            String lastType_ = resolved_.getResult(_page);
            AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(explicitContent.getClassName()));
            if (geneType_ == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page, leftPar_ +1);
                //_in len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        explicitContent.getClassName());
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                return;
            }
            String gene_ = geneType_.getGenericString();
            uniq_ = new ClassMethodId(explicitContent.getClassName(),new MethodId(MethodAccessKind.STATIC,exp_,new StringList(gene_,lastType_)));
            AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(new AnaClassArgumentMatching(explicitContent.getClassName(), _page.getPrimitiveTypes()));
            args_.add(resultClass_);
            AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetCast(explicitContent.getClassName(), uniq_, argsClass_, _page, _page.getImplicitCastMethods(), _page.getImplicitIdCastMethods(), _page.getImplicitFromCastMethods());
            if (resMethod_ != null) {
                explicitContent.setFormattedTypeOwner(resMethod_.getFormattedType());
                explicitContent.setMemberId(resMethod_.getMemberId());
                function = resMethod_.getPair();
            }
            return;
        }
        AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(explicitContent.getClassName()));
        if (geneType_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page, leftPar_ +1);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    explicitContent.getClassName());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            return;
        }
        String arg_ = types_.get(1);
        int lc_ = leftPar_ + types_.first().length() + 2;
        AnaResultPartType resolvedMid_ = ResolvingTypes.resolveCorrectAccessibleType(lc_ + StringExpUtil.getOffset(arg_),arg_.trim(), explicitContent.getClassName(), _page);
        partOffsets.add(resolvedMid_);
        String midType_ = resolvedMid_.getResult(_page);
        arg_ = types_.last();
        AnaResultPartType resolvedLast_ = ResolvingTypes.resolveCorrectAccessibleType(lc_ +types_.get(1).length()+1 + StringExpUtil.getOffset(arg_),arg_.trim(), explicitContent.getClassName(), _page);
        partOffsets.add(resolvedLast_);
        String lastType_ = resolvedLast_.getResult(_page);
        uniq_ = new ClassMethodId(explicitContent.getClassName(),new MethodId(MethodAccessKind.STATIC,exp_,new StringList(midType_,lastType_)));
        AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        AnaClassArgumentMatching virtual_ = new AnaClassArgumentMatching(AnaInherits.quickFormat(geneType_, explicitContent.getClassName(), midType_), _page.getPrimitiveTypes());
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(virtual_);
        args_.add(resultClass_);
        AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetCast(explicitContent.getClassName(), uniq_, argsClass_, _page, _page.getImplicitCastMethods(), _page.getImplicitIdCastMethods(), _page.getImplicitFromCastMethods());
        if (resMethod_ != null) {
            explicitContent.setFormattedTypeOwner(resMethod_.getFormattedType());
            explicitContent.setMemberId(resMethod_.getMemberId());
            function = resMethod_.getPair();
            setResultClass(virtual_);
            return;
        }
        buildError(argsClass_, _page);
    }
    private void buildError(AnaClassArgumentMatching[] _argsClass, AnalyzedPageEl _page) {
        StringList classesNames_ = new StringList();
        for (AnaClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringUtil.join(c.getNames(), ExportCst.JOIN_TYPES));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFile(_page.getCurrentFile());
        undefined_.setIndexFile(_page);
        //_name len
        String exp_ = _page.getKeyWords().getKeyWordCast();
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(MethodAccessKind.STATIC, exp_, classesNames_).getSignature(_page.getDisplayedStrings()));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());

    }

    public String getClassName() {
        return explicitContent.getClassName();
    }

    public AnaExplicitContent getExplicitContent() {
        return explicitContent;
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }

    public AnaTypeFct getFunction() {
        return function;
    }

}
