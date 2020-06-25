package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.*;

public final class EnumValueOfOperation extends AbstractUnaryOperation {

    private String className;
    private int argOffset;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public EnumValueOfOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        String glClass_ = _conf.getAnalyzing().getGlobalClass();
        Classes classes_ = _conf.getClasses();
        String clName_;
        clName_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,0,className);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        ExecRootBlock r_ = classes_.getClassBody(clName_);
        if (!(r_ instanceof ExecEnumBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //className len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    clName_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
        Accessed a_ = new Accessed(r_.getAccess(), r_.getPackageName(), r_.getParentFullName(), clName_, r_.getOuterFullName());
        if (!ContextUtil.canAccessType(curClassBase_,a_, _conf)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //className len
            badAccess_.buildError(_conf.getAnalysisMessages().getInaccessibleType(),
                    clName_,
                    curClassBase_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
        }
        ClassArgumentMatching argCl_ = firstArgs_.first();
        String stringType_ = _conf.getStandards().getAliasString();
        if (!argCl_.matchClass(stringType_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //separator after className
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(argCl_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(un_);
        }
        className = r_.getWildCardElement();
        setResultClass(new ClassArgumentMatching(className));
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public String getClassName() {
        return className;
    }

    public int getArgOffset() {
        return argOffset;
    }
}
