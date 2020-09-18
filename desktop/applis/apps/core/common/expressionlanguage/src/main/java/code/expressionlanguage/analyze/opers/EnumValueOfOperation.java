package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.*;

public final class EnumValueOfOperation extends AbstractUnaryOperation {

    private String className;
    private int argOffset;
    private int numberEnum=-1;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public EnumValueOfOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        if (vs_.isEmpty()) {
            className = "";
            return;
        }
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        CustList<AnaClassArgumentMatching> firstArgs_ = new CustList<AnaClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        String glClass_ = page_.getGlobalClass();
        String clName_;
        clName_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,0,className);
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
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        numberEnum = r_.getNumberAll();
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
        Accessed a_ = new Accessed(r_.getAccess(), r_.getPackageName(), r_.getParentFullName(), clName_, r_.getOuterFullName());
        if (!ContextUtil.canAccessType(curClassBase_,a_, page_)) {
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
        AnaClassArgumentMatching argCl_ = firstArgs_.first();
        String stringType_ = page_.getStandards().getAliasString();
        if (!argCl_.matchClass(stringType_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //separator after className
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    StringList.join(argCl_.getNames(),"&"));
            page_.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
        }
        className = r_.getWildCardElement();
        setResultClass(new AnaClassArgumentMatching(className));
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

    public int getNumberEnum() {
        return numberEnum;
    }
}
