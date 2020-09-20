package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class ImplicitOperation extends AbstractUnaryOperation {
    private String className;
    private String classNameOwner = EMPTY_STRING;
    private int offset;
    private CustList<PartOffset> partOffsets;

    private MethodId castOpId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    public ImplicitOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        String extract_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
        StringList types_ = StringExpUtil.getAllSepCommaTypes(extract_);
        if (types_.size() > 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Integer.toString(3),
                    Integer.toString(types_.size())
            );
            _page.getLocalizer().addError(badCall_);
            getErrs().add(badCall_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getStandards().getAliasObject()));
            return;
        }
        String res_;
        int leftPar_ = className.indexOf(PAR_LEFT);
        res_ = ResolvingImportTypes.resolveCorrectType(leftPar_ +1,types_.first(), _page);
        className = res_;
        classNameOwner = res_;
        partOffsets = new CustList<PartOffset>(_page.getCurrentParts());
        setResultClass(new AnaClassArgumentMatching(className, _page.getStandards()));
        if (!ExplicitOperation.customCast(res_)) {
            return;
        }
        if (AnaTypeUtil.isPrimitive(className, _page)) {
            getFirstChild().getResultClass().setUnwrapObject(className, _page.getStandards());
            Argument arg_ = getFirstChild().getArgument();
            checkNull(arg_, _page);
        }
        if (types_.size() == 2 && StringList.quickEq(types_.last(), _page.getKeyWords().getKeyWordId())) {
            return;
        }
        ClassMethodId uniq_;
        String exp_ = _page.getKeyWords().getKeyWordCast();
        if (types_.size() == 2){
            //add a type for full id
            String arg_ = types_.last();
            String lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(leftPar_ + types_.first().length() + 2, arg_, className, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(className));
            if (geneType_ == null) {
                int rc_ = _page.getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        className);
                _page.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
                return;
            }
            String gene_ = geneType_.getGenericString();
            uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(gene_,lastType_)));
            AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(new AnaClassArgumentMatching(className, _page.getStandards()));
            args_.add(resultClass_);
            AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetDeclaredImplicitCast(className, uniq_, argsClass_, _page);
            if (resMethod_.isFoundMethod()) {
                classNameOwner = resMethod_.getRealClass();
                castOpId = resMethod_.getRealId();
                rootNumber = resMethod_.getRootNumber();
                memberNumber = resMethod_.getMemberNumber();
            }
            return;
        }
        AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(className));
        if (geneType_ == null) {
            int rc_ = _page.getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    className);
            _page.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            return;
        }
        String arg_ = types_.get(1);
        int lc_ = leftPar_ + types_.first().length() + 2;
        String midType_ = ResolvingImportTypes.resolveCorrectAccessibleType(lc_,arg_, className, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        arg_ = types_.last();
        String lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(lc_ +types_.get(1).length()+1,arg_, className, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(midType_,lastType_)));
        AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        AnaClassArgumentMatching virtual_ = new AnaClassArgumentMatching(AnaTemplates.quickFormat(geneType_,className, midType_), _page.getStandards());
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(virtual_);
        args_.add(resultClass_);
        AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetDeclaredImplicitCast(className, uniq_, argsClass_, _page);
        if (resMethod_.isFoundMethod()) {
            classNameOwner = resMethod_.getRealClass();
            castOpId = resMethod_.getRealId();
            rootNumber = resMethod_.getRootNumber();
            memberNumber = resMethod_.getMemberNumber();
            setResultClass(virtual_);
            return;
        }
        buildError(argsClass_, _page);
    }
    private void buildError(AnaClassArgumentMatching[] _argsClass, AnalyzedPageEl _page) {
        StringList classesNames_ = new StringList();
        for (AnaClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //_name len
        String exp_ = _page.getKeyWords().getKeyWordCast();
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(MethodAccessKind.STATIC, exp_, classesNames_).getSignature(_page));
        _page.getLocalizer().addError(undefined_);
        getErrs().add(undefined_.getBuiltError());

    }

    public String getClassName() {
        return className;
    }

    public String getClassNameOwner() {
        return classNameOwner;
    }

    public int getOffset() {
        return offset;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public MethodId getCastOpId() {
        return castOpId;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }
}
