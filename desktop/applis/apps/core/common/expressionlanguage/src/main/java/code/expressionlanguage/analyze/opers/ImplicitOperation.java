package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaExplicitContent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ImplicitOperation extends AbstractUnaryOperation {
    private final AnaExplicitContent explicitContent;
    private CustList<PartOffset> partOffsets;

    private MemberId memberId = new MemberId();
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
            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        res_ = ResolvingTypes.resolveCorrectType(leftPar_ +1,types_.first(), _page);
        explicitContent.setClassName(res_);
        explicitContent.setClassNameOwner(res_);
        partOffsets = new CustList<PartOffset>(_page.getCurrentParts());
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
            String lastType_ = ResolvingTypes.resolveCorrectAccessibleType(leftPar_ + types_.first().length() + 2, arg_, explicitContent.getClassName(), _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(explicitContent.getClassName()));
            if (geneType_ == null) {
                int rc_ = _page.getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
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
            ClassMethodIdReturn resMethod_ = tryGetDeclaredImplicitCast(explicitContent.getClassName(), uniq_, argsClass_, _page);
            if (resMethod_.isFoundMethod()) {
                explicitContent.setClassNameOwner(resMethod_.getRealClass());
                memberId = resMethod_.getMemberId();
                function = resMethod_.getPair();
            }
            return;
        }
        AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(explicitContent.getClassName()));
        if (geneType_ == null) {
            int rc_ = _page.getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    explicitContent.getClassName());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            return;
        }
        String arg_ = types_.get(1);
        int lc_ = leftPar_ + types_.first().length() + 2;
        String midType_ = ResolvingTypes.resolveCorrectAccessibleType(lc_,arg_, explicitContent.getClassName(), _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        arg_ = types_.last();
        String lastType_ = ResolvingTypes.resolveCorrectAccessibleType(lc_ +types_.get(1).length()+1,arg_, explicitContent.getClassName(), _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        uniq_ = new ClassMethodId(explicitContent.getClassName(),new MethodId(MethodAccessKind.STATIC,exp_,new StringList(midType_,lastType_)));
        AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        AnaClassArgumentMatching virtual_ = new AnaClassArgumentMatching(AnaInherits.quickFormat(geneType_, explicitContent.getClassName(), midType_), _page.getPrimitiveTypes());
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(virtual_);
        args_.add(resultClass_);
        AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetDeclaredImplicitCast(explicitContent.getClassName(), uniq_, argsClass_, _page);
        if (resMethod_.isFoundMethod()) {
            explicitContent.setClassNameOwner(resMethod_.getRealClass());
            memberId = resMethod_.getMemberId();
            function = resMethod_.getPair();
            setResultClass(virtual_);
            return;
        }
        buildError(argsClass_, _page);
    }
    private void buildError(AnaClassArgumentMatching[] _argsClass, AnalyzedPageEl _page) {
        StringList classesNames_ = new StringList();
        for (AnaClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringUtil.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //_name len
        String exp_ = _page.getKeyWords().getKeyWordCast();
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(MethodAccessKind.STATIC, exp_, classesNames_).getSignature(_page));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());

    }

    public String getClassName() {
        return explicitContent.getClassName();
    }

    public AnaExplicitContent getExplicitContent() {
        return explicitContent;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    public MemberId getMemberId() {
        return memberId;
    }

}
