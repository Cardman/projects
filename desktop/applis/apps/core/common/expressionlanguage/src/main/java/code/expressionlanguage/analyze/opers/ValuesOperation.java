package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ValuesOperation extends LeafOperation {

    private String className;
    private int argOffset;
    private int numberEnum=-1;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ValuesOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        IntTreeMap< String> vs_ = getOperations().getValues();
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
    }

    @Override
    public void analyze(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        String glClass_ = page_.getGlobalClass();
        int leftPar_ = className.indexOf('(')+1;
        String sub_ = className.substring(leftPar_,className.lastIndexOf(')'));
        leftPar_ += StringList.getFirstPrintableCharIndex(sub_);
        String clName_;
        clName_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,leftPar_,sub_);
        partOffsets.addAllElts(page_.getCurrentParts());
        RootBlock r_ = page_.getAnaClassBody(clName_);
        if (!(r_ instanceof EnumBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //className len
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    clName_);
            page_.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            String argClName_ = page_.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        numberEnum = r_.getNumberAll();
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
        Accessed a_ = new Accessed(r_.getAccess(), r_.getPackageName(), r_.getParentFullName(), clName_, r_.getOuterFullName());
        if (!ContextUtil.canAccessType(curClassBase_, a_, page_)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(page_.getLocalizer().getCurrentFileName());
            //className len
            badAccess_.buildError(_conf.getAnalyzing().getAnalysisMessages().getInaccessibleType(),
                    clName_,
                    curClassBase_);
            page_.getLocalizer().addError(badAccess_);
            getErrs().add(badAccess_.getBuiltError());
        }
        className = r_.getWildCardElement();
        String ret_ = StringExpUtil.getPrettyArrayType(className);
        setResultClass(new ClassArgumentMatching(ret_));
    }

    public int getArgOffset() {
        return argOffset;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public int getNumberEnum() {
        return numberEnum;
    }
}
