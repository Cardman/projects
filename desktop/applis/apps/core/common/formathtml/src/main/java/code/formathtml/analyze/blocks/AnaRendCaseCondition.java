package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;

public final class AnaRendCaseCondition extends AnaRendParentBlock implements AnaRendBuildEl,WithRendFilterContent {

    private final RendFilterContent filterContent;


    AnaRendCaseCondition(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _value, int _offset, OffsetStringInfo _condition) {
        super(_offset);
        filterContent = new RendFilterContent(_className, _variable, _value,_condition);
    }

    @Override
    public RendFilterContent getFilterContent() {
        return filterContent;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.zeroOffset();
        AnaRendParentBlock par_ = getParent();
        if (!(par_ instanceof AnaRendSwitchBlock)) {
            _page.setSumOffset(filterContent.getResValue().getSumOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    filterContent.getValue(),
                    _page.getKeyWords().getKeyWordSwitch());
            AnalyzingDoc.addError(un_, _page);
            filterContent.getResValue().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page, filterContent.getResValue()));
            return;
        }
        AnaRendSwitchBlock sw_ = (AnaRendSwitchBlock) par_;
        AnaClassArgumentMatching resSwitch_ = sw_.getResult();
        boolean instance_ = sw_.isInstance();
        filterContent.setKeyWord(_page.getKeyWords().getKeyWordCase());
        filterContent.setKeyWordContainer(_page.getKeyWords().getKeyWordSwitch());
        filterContent.buildExpressionLanguage(this,_anaDoc, _page, resSwitch_, instance_);
//        check(_anaDoc, _page, resSwitch_, instance_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        filterContent.removeAllVars(_ip);
    }

    public String getVariableName() {
        return filterContent.getVariableName();
    }
}
