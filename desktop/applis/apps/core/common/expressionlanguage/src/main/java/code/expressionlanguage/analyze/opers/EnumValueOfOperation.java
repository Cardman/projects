package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvedIdType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaValuesContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class EnumValueOfOperation extends AbstractUnaryOperation {

    private String className;
    private int argOffset;
    private final AnaValuesContent valuesContent;

    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();

    public EnumValueOfOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        valuesContent = new AnaValuesContent();
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        if (vs_.isEmpty()) {
            className = "";
            return;
        }
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        valuesContent.setArgOffset(argOffset);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ valuesContent.getArgOffset(), _page);
        CustList<AnaClassArgumentMatching> firstArgs_ = new CustList<AnaClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        String glClass_ = _page.getGlobalClass();
        String clName_;
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(0, className, _page);
        clName_ = resolvedIdType_.getFullName();
        partOffsets.add(resolvedIdType_.getDels());
        RootBlock r_ = _page.getAnaClassBody(clName_);
        if (!(r_ instanceof EnumBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //className len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    clName_);
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            String argClName_ = _page.getAliasObject();
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        valuesContent.setNumberEnum(r_.getNumberAll());
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
        Accessed a_ = new Accessed(r_.getAccess(), r_.getPackageName(), r_.getParentType(), r_);
        if (!ContextUtil.canAccessType(curClassBase_,a_, _page)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            badAccess_.setFile(_page.getCurrentFile());
            //className len
            badAccess_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    clName_,
                    curClassBase_);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
        AnaClassArgumentMatching argCl_ = firstArgs_.first();
        String stringType_ = _page.getAliasString();
        if (!argCl_.matchClass(stringType_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //separator after className
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(argCl_.getNames(), ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
        }
        className = r_.getWildCardElement();
        setResultClass(new AnaClassArgumentMatching(className));
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }

    public AnaValuesContent getValuesContent() {
        return valuesContent;
    }
}
