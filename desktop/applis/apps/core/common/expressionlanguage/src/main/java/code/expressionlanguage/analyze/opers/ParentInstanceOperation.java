package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.AnaParentInstanceContent;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ParentInstanceOperation extends LeafOperation implements PossibleIntermediateDotted {
    private AnaClassArgumentMatching previousResultClass;
    private AnaParentInstanceContent parentInstanceContent;
    protected ParentInstanceOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        parentInstanceContent = new AnaParentInstanceContent(_op.getOffset());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        StringList converted_ = new StringList();
        if (isIntermediateDottedOperation()) {
            for (String p:previousResultClass.getNames()) {
                if (p.startsWith(AnaTemplates.ARR_BEG_STRING)) {
                    continue;
                }
                converted_.addAllElts(InvokingOperation.getBounds(p, _page));
            }
        } else {
            converted_.add(_page.getGlobalClass());
        }
        StringList rs_ = getParentTypeList(converted_, _page);
        setResultClass(new AnaClassArgumentMatching(rs_));
    }

    static StringList getParentTypeList(StringList _converted, AnalyzedPageEl _page) {
        StringList rs_ = getParentType(_converted);
        if (rs_.isEmpty()) {
            return new StringList(_page.getAliasObject());
        }
        return rs_;
    }

    private static StringList getParentType(StringList _converted) {
        StringList pars_ = new StringList();
        for (String p: _converted) {
            StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(p);
            String outer_ = StringUtil.join(innerParts_.left(innerParts_.size() - 2), "");
            if (StringUtil.contains(pars_,outer_)) {
                continue;
            }
            pars_.add(outer_);
        }
        StringList rs_ = new StringList();
        for (String p:pars_) {
            if (p.trim().isEmpty()) {
                continue;
            }
            rs_.add(p);
        }
        return rs_;
    }


    @Override
    public void setIntermediateDotted() {
        parentInstanceContent.setIntermediate(true);
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return parentInstanceContent.isIntermediate();
    }

    @Override
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    public AnaParentInstanceContent getParentInstanceContent() {
        return parentInstanceContent;
    }
}
