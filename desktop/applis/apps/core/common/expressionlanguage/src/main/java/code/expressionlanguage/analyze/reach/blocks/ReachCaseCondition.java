package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ReachCaseCondition extends ReachSwitchPartBlock {
    private final OperationNode root;
    private final int valueOffset;
    private final String value;
    private final String importedType;
    private final CaseCondition meta;

    protected ReachCaseCondition(CaseCondition _info) {
        super(_info);
        meta = _info;
        root = _info.getRoot();
        value = _info.getValue();
        importedType = _info.getImportedType();
        valueOffset = _info.getValueOffset();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.zeroOffset();
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)&&!(par_ instanceof ReachSwitchMethodBlock)) {
            return;
        }
        AnaClassArgumentMatching resSwitch_;
        String type_;
        String instanceTest_;
        if (par_ instanceof ReachSwitchBlock) {
            ReachSwitchBlock sw_ = (ReachSwitchBlock) par_;
            resSwitch_ = sw_.getResult();
            instanceTest_ = sw_.getInstanceTest();
        } else {
            ReachSwitchMethodBlock sw_ = (ReachSwitchMethodBlock) par_;
            resSwitch_ = sw_.getResult();
            instanceTest_ = sw_.getInstanceTest();
        }
        type_ = resSwitch_.getSingleNameOrEmpty();
        if (!instanceTest_.isEmpty()) {
            return;
        }
        EnumBlock e_ = getEnumType(type_, _page);
        if (e_ != null) {
            for (InnerTypeOrElement f: e_.getEnumBlocks()) {
                if (!StringUtil.contains(f.getFieldName(), value.trim())) {
                    continue;
                }
                meta.setBuiltEnum(true);
                checkDuplicateEnumCase(_page);
                return;
            }
            meta.setArgument(ReachOperationUtil.tryCalculate(root, _page));
            if (Argument.isNullValue(meta.getArgument())) {
                meta.setBuiltEnum(true);
                meta.setNullCaseEnum(true);
            }
            processNullValue(_page);
            return;
        }
        meta.setArgument(ReachOperationUtil.tryCalculate(root, _page));
        processNumValues(resSwitch_, root.getResultClass(), _page);
    }
    private void processNullValue(AnalyzedPageEl _page) {
        if (Argument.isNullValue(meta.getArgument())) {
            checkDuplicateCase(meta.getArgument(), _page);
            return;
        }
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(getFile().getFileName());
        un_.setIndexFile(valueOffset);
        //key word len
        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                _page.getKeyWords().getKeyWordCase(),
                value);
        _page.addLocError(un_);
        addErrorBlock(un_.getBuiltError());
    }

    private void processNumValues(AnaClassArgumentMatching _resSwitch, AnaClassArgumentMatching _resCase, AnalyzedPageEl _page) {
        if (meta.getArgument() == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                    _page.getKeyWords().getKeyWordCase(),
                    value);
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        } else {
            checkDuplicateCase(meta.getArgument(), _page);
            Mapping m_ = new Mapping();
            m_.setArg(_resCase);
            m_.setParam(_resSwitch);
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                        _page.getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(meta.getArgument(), _page),
                        StringUtil.join(_resSwitch.getNames(), ExportCst.JOIN_TYPES));
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
            }
        }
    }

    private void checkDuplicateCase(Argument _arg, AnalyzedPageEl _page) {
        ReachBracedBlock par_ = getParent();
        ReachBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof ReachCaseCondition) {
                ReachCaseCondition c_ = (ReachCaseCondition) first_;
                Argument a_ = c_.getArgument();
                if (a_ != null) {
                    if (_arg.getStruct().sameReference(a_.getStruct())) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(getValueOffset()+ getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_arg, _page),
                                _page.getKeyWords().getKeyWordSwitch());
                        _page.addLocError(un_);
                        addErrorBlock(un_.getBuiltError());
                        break;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
    }
    private void checkDuplicateEnumCase(AnalyzedPageEl _page) {
        ReachBracedBlock par_ = getParent();
        ReachBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof ReachCaseCondition) {
                ReachCaseCondition c_ = (ReachCaseCondition) first_;
                String v_ = c_.value.trim();
                if (StringUtil.quickEq(v_, value.trim())) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getValueOffset()+ getOffset());
                    //key word len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                            _page.getKeyWords().getKeyWordCase(),
                            value.trim(),
                            _page.getKeyWords().getKeyWordSwitch());
                    _page.addLocError(un_);
                    addErrorBlock(un_.getBuiltError());
                    break;
                }

            }
            first_ = first_.getNextSibling();
        }
    }
    private static EnumBlock getEnumType(String _type, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
        if (g_ instanceof EnumBlock) {
            return (EnumBlock) g_;
        }
        return null;

    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)) {
            super.reach(_anEl, _page);
            return;
        }
        ReachSwitchBlock s_ = (ReachSwitchBlock) par_;
        if (s_.getInstanceTest().isEmpty()) {
            super.reach(_anEl, _page);
            return;
        }
        if (meta.isNullCase()) {
            StringList classes_ = new StringList();
            ReachBlock b_ = getPreviousSibling();
            while (b_ != null) {
                if (b_ instanceof ReachCaseCondition) {
                    classes_.add(((ReachCaseCondition)b_).importedType);
                }
                b_ = b_.getPreviousSibling();
            }
            if (!StringUtil.contains(classes_,"")) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
            return;
        }
        StringList classes_ = new StringList();
        ReachBlock b_ = getPreviousSibling();
        while (b_ != null) {
            if (b_ instanceof ReachCaseCondition) {
                classes_.add(((ReachCaseCondition)b_).importedType);
            }
            b_ = b_.getPreviousSibling();
        }
        _anEl.setArgMapping(importedType);
        boolean reachCatch_ = true;
        for (String c: classes_) {
            _anEl.setParamMapping(c);
            if (_anEl.isCorrectMapping(_page)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    public Argument getArgument() {
        return meta.getArgument();
    }

    public int getValueOffset() {
        return valueOffset;
    }
}
