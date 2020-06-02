package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class ExplicitOperation extends AbstractUnaryOperation {
    private String className;
    private String classNameOwner = EMPTY_STRING;
    private int offset;
    private CustList<PartOffset> partOffsets;

    private MethodId castOpId;
    public ExplicitOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        String extract_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
        StringList types_ = Templates.getAllSepCommaTypes(extract_);
        if (types_.size() > 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_conf.getAnalysisMessages().getSplitComaLow(),
                    Integer.toString(3),
                    Integer.toString(types_.size())
            );
            _conf.getAnalyzing().getLocalizer().addError(badCall_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        String res_;
        int leftPar_ = className.indexOf(PAR_LEFT);
        res_ = ResolvingImportTypes.resolveCorrectType(_conf,leftPar_ +1,types_.first());
        className = res_;
        classNameOwner = res_;
        partOffsets = new CustList<PartOffset>(_conf.getCoverage().getCurrentParts());
        setResultClass(new ClassArgumentMatching(className));
        if (!customCast(res_)) {
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            getFirstChild().getResultClass().setUnwrapObject(className);
            Argument arg_ = getFirstChild().getArgument();
            checkNull(arg_,_conf);
        }
        if (types_.size() == 2 && StringList.quickEq(types_.last(),_conf.getKeyWords().getKeyWordId())) {
            return;
        }
        ClassMethodId uniq_;
        String exp_ = _conf.getKeyWords().getKeyWordExplicit();
        if (types_.size() == 2){
            //add a type for full id
            String lastType_ = "";
            String arg_ = types_.last();
            lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf,leftPar_ +types_.first().length()+2,arg_, className);
            partOffsets.addAllElts(_conf.getCoverage().getCurrentParts());
            GeneType geneType_ = _conf.getClassBody(Templates.getIdFromAllTypes(className));
            if (geneType_ == null) {
                int rc_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                        className);
                _conf.getAnalyzing().getLocalizer().addError(un_);
                return;
            }
            String gene_ = geneType_.getGenericString();
            uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(gene_,lastType_)));
            ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            CustList<ClassArgumentMatching> args_ = new CustList<ClassArgumentMatching>(new ClassArgumentMatching(className));
            args_.add(resultClass_);
            ClassArgumentMatching[] argsClass_ = ClassArgumentMatching.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, exp_,  uniq_, argsClass_);
            if (resMethod_.isFoundMethod()) {
                classNameOwner = resMethod_.getRealClass();
                castOpId = resMethod_.getRealId();
            }
            return;
        }
        if (types_.size() == 3){
            GeneType geneType_ = _conf.getClassBody(Templates.getIdFromAllTypes(className));
            if (geneType_ == null) {
                int rc_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex() + leftPar_ +1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                        className);
                _conf.getAnalyzing().getLocalizer().addError(un_);
                return;
            }
            String arg_ = types_.get(1);
            int lc_ = leftPar_ + types_.first().length() + 2;
            String midType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf, lc_,arg_, className);
            partOffsets.addAllElts(_conf.getCoverage().getCurrentParts());
            arg_ = types_.last();
            String lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf,lc_ +types_.get(1).length()+1,arg_, className);
            partOffsets.addAllElts(_conf.getCoverage().getCurrentParts());
            uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(midType_,lastType_)));
            ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            ClassArgumentMatching virtual_ = new ClassArgumentMatching(Templates.quickFormat(className, midType_, _conf));
            CustList<ClassArgumentMatching> args_ = new CustList<ClassArgumentMatching>(virtual_);
            args_.add(resultClass_);
            ClassArgumentMatching[] argsClass_ = ClassArgumentMatching.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, exp_,  uniq_, argsClass_);
            if (resMethod_.isFoundMethod()) {
                classNameOwner = resMethod_.getRealClass();
                castOpId = resMethod_.getRealId();
                setResultClass(virtual_);
                return;
            }
            buildError(_conf,argsClass_);
            return;
        }
        ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        CustList<ClassArgumentMatching> args_ = new CustList<ClassArgumentMatching>(new ClassArgumentMatching(className));
        args_.add(resultClass_);
        ClassArgumentMatching[] argsClass_ = ClassArgumentMatching.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, exp_,  null, argsClass_);
        if (!resMethod_.isFoundMethod()) {
            return;
        }
        classNameOwner = resMethod_.getRealClass();
        castOpId = resMethod_.getRealId();
    }
    private void buildError(ContextEl _conf,ClassArgumentMatching[] _argsClass) {
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringList.join(c.getNames(), "&"));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //_name len
        String exp_ = _conf.getKeyWords().getKeyWordExplicit();
        undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(MethodAccessKind.STATIC, exp_, classesNames_).getSignature(_conf));
        _conf.getAnalyzing().getLocalizer().addError(undefined_);

    }

    public static boolean customCast(String _type) {
        boolean direct_ = false;
        for (String p: Templates.getAllTypes(_type).mid(1)) {
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
        if (new ClassArgumentMatching(_type).isArray()) {
            return false;
        }
        return true;
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

}
