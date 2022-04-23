package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvedIdType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;

public final class StaticCallAccessOperation extends LeafOperation {
    private ResolvedInstance partOffsets;
    private AnaGeneType extractStaticType;
    private String stCall = "";
    private boolean implicit;
    private int lt;
    private int gt;

    public StaticCallAccessOperation(int _indexInEl, int _indexChild,
                                 MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        String originalStr_ = getValue();
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _page.getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            String form_ = AnaTemplates.getInferForm(realCl_);
            if (form_ != null) {
                int trace_ = _page.getIndex();
                int rel_ = trace_ + str_.indexOf(PAR_LEFT) + 1;
                lt = rel_ + realCl_.indexOf('<');
                gt = rel_ + realCl_.indexOf('>') + 1;
                if (!form_.trim().isEmpty()) {
                    ResolvedIdType resolved_ = ResolvingTypes.resolveAccessibleIdTypeBlock(str_.indexOf(PAR_LEFT) + 1, form_, _page);
                    String solved_ = resolved_.getFullName();
                    extractStaticType = resolved_.getGeneType();
//                    String solved_ = ResolvingTypes.resolveAccessibleIdType(str_.indexOf(PAR_LEFT) + 1, form_, _page);
                    partOffsets = new ResolvedInstance(resolved_.getDels());
//                    partOffsets = new CustList<PartOffset>(_page.getCurrentParts());
                    stCall =solved_;
                    if (extractStaticType instanceof RootBlock) {
                        solved_ = ((RootBlock)extractStaticType).getWildCardString();
                    }
                    classStr_ = emptyToObject(solved_,_page);
                } else {
                    stCall = "<>";
                    classStr_ = emptyToObject("",_page);
                    partOffsets = new ResolvedInstance();
                }
            } else {
                AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(str_.indexOf(PAR_LEFT) + 1 + StringExpUtil.getOffset(realCl_), realCl_, _page);
                classStr_ = result_.getResult(_page);
                extractStaticType = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(classStr_));
                partOffsets = new ResolvedInstance(result_);
            }
        } else {
            implicit = true;
            classStr_ = glClass_;
            extractStaticType = _page.getGlobalType().getRootBlock();
            partOffsets = new ResolvedInstance();
        }
        checkClassAccess(glClass_, classStr_, _page);
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setResultClass(new AnaClassArgumentMatching(classStr_));
    }

    public AnaGeneType getExtractStaticType() {
        return extractStaticType;
    }

    public void check(AnalyzedPageEl _page) {
        String type_ = getResultClass().getSingleNameOrEmpty();
        if (type_.startsWith(AnaTemplates.ARR_BEG_STRING)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page);
            badAccess_.setFile(_page.getCurrentFile());
            //type len
            badAccess_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    type_);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
        if (type_.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page);
            badAccess_.setFile(_page.getCurrentFile());
            //type len
            badAccess_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    type_);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
        if (stCall.isEmpty()&&StringExpUtil.isWildCard(type_)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page);
            badAccess_.setFile(_page.getCurrentFile());
            //type len
            badAccess_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    type_);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
    }
    public boolean isImplicit() {
        return implicit;
    }

    public ResolvedInstance getPartOffsets() {
        return partOffsets;
    }

    public void setPartOffsets(ResolvedInstance _partOffsets) {
        partOffsets = _partOffsets;
    }

    public String getStCall() {
        return stCall;
    }

    public int getGt() {
        return gt;
    }

    public int getLt() {
        return lt;
    }

}
