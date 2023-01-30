package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class CaseCondition extends SwitchPartBlock implements WithFilterContent{

    private final FilterContent filterContent;

    public CaseCondition(OffsetStringInfo _value, int _offset, String _declaringType, OffsetStringInfo _variable, OffsetStringInfo _condition) {
        super(_offset);
        filterContent = new FilterContent(_value, _declaringType, _variable, _condition, true);
    }

    public int getValueOffset() {
        return filterContent.getValueOffset();
    }

    public String getValue() {
        return filterContent.getValue();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.zeroOffset();
        BracedBlock par_ = getParent();
        MethodAccessKind stCtx_ = f_.getStaticContext();
        if (!(par_ instanceof SwitchBlock)&&!(par_ instanceof SwitchMethodBlock)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordCase(),
                    filterContent.getValue(),
                    _page.getKeyWords().getKeyWordSwitch());
            //key word len
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
            _page.setSumOffset(filterContent.getResValue().getSumOffset());
            filterContent.getResValue().setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(filterContent.getResValue(), Calculation.staticCalculation(stCtx_), _page));
            return;
        }
        String type_;
        boolean instance_;
        if (par_ instanceof SwitchBlock) {
            SwitchBlock sw_ = (SwitchBlock) par_;
            setSwitchParent(sw_);
            AnaClassArgumentMatching resSwitch_ = sw_.getResult();
            type_ = resSwitch_.getSingleNameOrEmpty();
            instance_ = sw_.isInstance();
        } else {
            SwitchMethodBlock sw_ = (SwitchMethodBlock) par_;
            setSwitchMethod(sw_);
            AnaClassArgumentMatching resSwitch_ = sw_.getResult();
            type_ = resSwitch_.getSingleNameOrEmpty();
            instance_ = sw_.isInstance();
        }
        filterContent.setKeyWord(_page.getKeyWords().getKeyWordCase());
        filterContent.setKeyWordContainer(_page.getKeyWords().getKeyWordSwitch());
        filterContent.buildExpressionLanguageReadOnly(this,_page,instance_,type_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        filterContent.removeAllVars(_ip);
    }

    public FilterContent getFilterContent() {
        return filterContent;
    }

}
