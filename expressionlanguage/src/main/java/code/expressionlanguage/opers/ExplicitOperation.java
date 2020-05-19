package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
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
        if (types_.size() > 2) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_conf.getAnalysisMessages().getSplitComaLow(),
                    Integer.toString(2),
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
        ClassMethodId uniq_ = null;
        String exp_ = _conf.getKeyWords().getKeyWordExplicit();
        if (types_.size() == 2){
            String lastType_ = "";
            String arg_ = types_.last();
            lastType_ = ResolvingImportTypes.resolveCorrectAccessibleType(_conf,leftPar_ +types_.first().length()+2,arg_, className);
            partOffsets.addAllElts(_conf.getCoverage().getCurrentParts());
            uniq_ = new ClassMethodId(className,new MethodId(MethodAccessKind.STATIC,exp_,new StringList(lastType_)));
        }
        ClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        ClassArgumentMatching[] argsClass_ = ClassArgumentMatching.toArgArray(new CustList<ClassArgumentMatching>(resultClass_));
        ClassMethodIdReturn resMethod_ = tryGetDeclaredCast(_conf,  className, exp_,  uniq_, argsClass_);
        if (!resMethod_.isFoundMethod()) {
            return;
        }
        castOpId = resMethod_.getRealId();
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
