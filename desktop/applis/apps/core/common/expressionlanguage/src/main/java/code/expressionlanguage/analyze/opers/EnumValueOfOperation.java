package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvedIdType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.fwd.opers.AnaValuesContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.core.StringUtil;

public final class EnumValueOfOperation extends AbstractUnaryOperation {

    private String className;
    private final int argOffset;
    private final AnaValuesContent valuesContent;

    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();

    public EnumValueOfOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op, String _str, int _argOfff) {
        super(_index, _indexChild, _m, _op);
        valuesContent = new AnaValuesContent();
        className = _str;
        argOffset = _argOfff;
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        valuesContent.setArgOffset(argOffset);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ valuesContent.getArgOffset(), _page);
        CustList<AnaClassArgumentMatching> firstArgs_ = new CustList<AnaClassArgumentMatching>();
        firstArgs_.add(getFirstChild().getResultClass());
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(0, className, _page);
        RootBlock r_ = ValuesOperation.checkType(_page,this,resolvedIdType_,partOffsets,valuesContent);
        if (r_ == null) {
            return;
        }
        AnaClassArgumentMatching argCl_ = firstArgs_.first();
        String stringType_ = _page.getAliasString();
        if (!argCl_.matchClass(stringType_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
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
