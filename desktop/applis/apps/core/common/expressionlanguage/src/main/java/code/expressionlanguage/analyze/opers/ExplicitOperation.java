package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class ExplicitOperation extends AbstractUnaryOperation {
    private String className;
    private String classNameOwner = EMPTY_STRING;
    private int offset;
    private CustList<PartOffset> partOffsets;

    private MethodId castOpId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    public ExplicitOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        String extract_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
        StringList types_ = StringExpUtil.getAllSepCommaTypes(extract_);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (types_.size() > 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(page_.getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_conf.getAnalyzing().getAnalysisMessages().getSplitComaLow(),
                    Integer.toString(3),
                    Integer.toString(types_.size())
            );
            page_.getLocalizer().addError(badCall_);
            getErrs().add(badCall_.getBuiltError());
            setResultClass(new ClassArgumentMatching(page_.getStandards().getAliasObject()));
            return;
        }
        String res_;
        int leftPar_ = className.indexOf(PAR_LEFT);
        res_ = ResolvingImportTypes.resolveCorrectType(_conf,leftPar_ +1,types_.first());
        className = res_;
        classNameOwner = res_;
        partOffsets = new CustList<PartOffset>(page_.getCurrentParts());
        setResultClass(new ClassArgumentMatching(className));
        if (!customCast(res_)) {
            return;
        }
        if (AnaTypeUtil.isPrimitive(className, page_)) {
            getFirstChild().getResultClass().setUnwrapObject(className);
            Argument arg_ = getFirstChild().getArgument();
            checkNull(arg_,_conf);
        }
        if (types_.size() == 2 && StringList.quickEq(types_.last(), _conf.getAnalyzing().getKeyWords().getKeyWordId())) {
            return;
        }
        ClassMethodId uniq_;
        String exp_ = _conf.getAnalyzing().getKeyWords().getKeyWordExplicit();
        if (types_.size() == 2){
            //add a type for full id
            String arg_ = types_.last();
            String lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf, leftPar_ + types_.first().length() + 2, arg_, className);
            partOffsets.addAllElts(page_.getCurrentParts());
            AnaGeneType geneType_ = page_.getAnaGeneType(StringExpUtil.getIdFromAllTypes(className));
            if (geneType_ == null) {
                int rc_ = page_.getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(page_.getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                        className);
                page_.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
                return;
            }
            String gene_ = geneType_.getGenericString();
            uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(gene_,lastType_)));
            ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            CustList<ClassArgumentMatching> args_ = new CustList<ClassArgumentMatching>(new ClassArgumentMatching(className));
            args_.add(resultClass_);
            ClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, uniq_, argsClass_);
            if (resMethod_.isFoundMethod()) {
                classNameOwner = resMethod_.getRealClass();
                castOpId = resMethod_.getRealId();
                rootNumber = resMethod_.getRootNumber();
                memberNumber = resMethod_.getMemberNumber();
            }
            return;
        }
        if (types_.size() == 3){
            AnaGeneType geneType_ = page_.getAnaGeneType(StringExpUtil.getIdFromAllTypes(className));
            if (geneType_ == null) {
                int rc_ = page_.getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(page_.getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                        className);
                page_.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
                return;
            }
            String arg_ = types_.get(1);
            int lc_ = leftPar_ + types_.first().length() + 2;
            String midType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf, lc_,arg_, className);
            partOffsets.addAllElts(page_.getCurrentParts());
            arg_ = types_.last();
            String lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf,lc_ +types_.get(1).length()+1,arg_, className);
            partOffsets.addAllElts(page_.getCurrentParts());
            uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(midType_,lastType_)));
            ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            ClassArgumentMatching virtual_ = new ClassArgumentMatching(AnaTemplates.quickFormat(geneType_,className, midType_, _conf));
            CustList<ClassArgumentMatching> args_ = new CustList<ClassArgumentMatching>(virtual_);
            args_.add(resultClass_);
            ClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, uniq_, argsClass_);
            if (resMethod_.isFoundMethod()) {
                classNameOwner = resMethod_.getRealClass();
                castOpId = resMethod_.getRealId();
                rootNumber = resMethod_.getRootNumber();
                memberNumber = resMethod_.getMemberNumber();
                setResultClass(virtual_);
                return;
            }
            buildError(_conf,argsClass_);
            return;
        }
        ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        CustList<ClassArgumentMatching> args_ = new CustList<ClassArgumentMatching>(new ClassArgumentMatching(className));
        args_.add(resultClass_);
        ClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, null, argsClass_);
        if (!resMethod_.isFoundMethod()) {
            return;
        }
        rootNumber = resMethod_.getRootNumber();
        memberNumber = resMethod_.getMemberNumber();
        classNameOwner = resMethod_.getRealClass();
        castOpId = resMethod_.getRealId();
    }
    private void buildError(ContextEl _conf,ClassArgumentMatching[] _argsClass) {
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        undefined_.setFileName(page_.getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
        //_name len
        String exp_ = _conf.getAnalyzing().getKeyWords().getKeyWordExplicit();
        undefined_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUndefinedMethod(),
                new MethodId(MethodAccessKind.STATIC, exp_, classesNames_).getSignature(page_));
        page_.getLocalizer().addError(undefined_);
        getErrs().add(undefined_.getBuiltError());

    }

    public static boolean customCast(String _type) {
        boolean direct_ = false;
        for (String p: StringExpUtil.getAllTypes(_type).mid(1)) {
            if (p.startsWith(Templates.SUB_TYPE)) {
                direct_ = true;
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                direct_ = true;
            }
        }
        if (direct_) {
            return false;
        }
        if (_type.startsWith("#")) {
            return false;
        }
        return !new ClassArgumentMatching(_type).isArray();
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
