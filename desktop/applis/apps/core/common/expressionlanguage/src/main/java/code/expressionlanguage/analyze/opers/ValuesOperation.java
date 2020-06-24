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
import code.expressionlanguage.analyze.blocks.Classes;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ValuesOperation extends LeafOperation {

    private String className;
    private int argOffset;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ValuesOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.clear();
    }

    @Override
    public void analyze(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
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
        if (!ContextUtil.canAccessType(curClassBase_, a_, _conf)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //className len
            badAccess_.buildError(_conf.getAnalysisMessages().getInaccessibleType(),
                    clName_,
                    curClassBase_);
            _conf.getAnalyzing().getLocalizer().addError(badAccess_);
        }
        className = r_.getWildCardElement();
        String ret_ = StringExpUtil.getPrettyArrayType(className);
        setResultClass(new ClassArgumentMatching(ret_));
    }

    public String getClassName() {
        return className;
    }
    public int getArgOffset() {
        return argOffset;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
