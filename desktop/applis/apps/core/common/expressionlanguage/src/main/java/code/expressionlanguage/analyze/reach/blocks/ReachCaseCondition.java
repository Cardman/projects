package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.DeclaringOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ReachCaseCondition extends ReachSwitchPartBlock {
    private final OperationNode root;
    private final int valueOffset;
    private final String value;
    private final String importedType;
    private final CaseCondition meta;
    private final boolean instance;

    public ReachCaseCondition(CaseCondition _info) {
        super(_info);
        meta = _info;
        root = _info.getRoot();
        value = _info.getValue();
        instance = _info.isInstance();
        importedType = _info.getImportedType();
        valueOffset = _info.getValueOffset();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(valueOffset);
        _page.zeroOffset();
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)&&!(par_ instanceof ReachSwitchMethodBlock)) {
            return;
        }
        EnumBlock e_ = meta.getEnumBlock();
        if (e_ != null) {
            elementEnum(_page, e_);
            return;
        }
        AnaClassArgumentMatching resSwitch_;
        boolean instance_;
        if (par_ instanceof ReachSwitchBlock) {
            ReachSwitchBlock sw_ = (ReachSwitchBlock) par_;
            resSwitch_ = sw_.getResult();
            instance_ = sw_.isInstance();
        } else {
            ReachSwitchMethodBlock sw_ = (ReachSwitchMethodBlock) par_;
            resSwitch_ = sw_.getResult();
            instance_ = sw_.isInstance();
        }
        if (instance) {
            if (root != null) {
                ReachOperationUtil.tryCalculate(root, _page);
            }
            return;
        }
        ReachOperationUtil.tryCalculate(root, _page);
        processNumValues(instance_,resSwitch_, _page);
    }

    private void elementEnum(AnalyzedPageEl _page, EnumBlock _e) {
        CustList<IndexStrPart> values_ = meta.getOffsetsEnum().getValues();
        for (IndexStrPart v: values_) {
            boolean added_ = false;
            if (StringUtil.quickEq(v.getPart(), _page.getKeyWords().getKeyWordNull())) {
                checkDuplicateListedValue(_page,Argument.createVoid());
                meta.getStdValues().add(Argument.createVoid());
                added_ = true;
            } else {
                for (InnerTypeOrElement f: _e.getEnumBlocks()) {
                    if (StringUtil.contains(f.getElements().getFieldName(), v.getPart())) {
                        ClassField pair_ = new ClassField(f.getImportedClassName(), v.getPart());
                        checkDuplicateListedEnum(_page, pair_, StringUtil.concat(pair_.getClassName(), ".", pair_.getFieldName()));
                        meta.getEnumValues().add(pair_);
                        added_ = true;
                        break;
                    }
                }
            }
            if (!added_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _page.getKeyWords().getKeyWordCase(),
                        value);
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
                break;
            }
        }
    }

    private void processNumValues(boolean _instance, AnaClassArgumentMatching _resSwitch, AnalyzedPageEl _page) {
        if (root instanceof DeclaringOperation) {
            CustList<OperationNode> childrenNodes_ = ((DeclaringOperation) root).getChildrenNodes();
            StrTypes children_ = ((DeclaringOperation) root).getChildren();
            int len_ = childrenNodes_.size();
            for (int i = 0; i < len_; i++) {
                OperationNode ch_ = childrenNodes_.get(i);
                String value_ = StrTypes.value(children_, i);
                checkRetrieve(_instance,_resSwitch,ch_.getResultClass(),_page, ch_, value_);
            }
        } else {
            checkRetrieve(_instance,_resSwitch,root.getResultClass(),_page, root, meta.getValue());
        }
    }

    private void checkRetrieve(boolean _instance,AnaClassArgumentMatching _resSwitch, AnaClassArgumentMatching _resCase, AnalyzedPageEl _page, OperationNode _ch, String _value) {
        ClassField classField_ = ElUtil.tryGetAccess(_ch);
        if (classField_ != null) {
            checkDuplicateListedEnum(_page, classField_, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()));
            checkInh(_instance, _resSwitch, _resCase, _page, StringUtil.concat(classField_.getClassName(), ".", classField_.getFieldName()));
            meta.getEnumValues().add(classField_);
        } else {
            Argument argument_ = _ch.getArgument();
            if (argument_ != null) {
                checkDuplicateListedValue(_page,argument_);
                checkInh(_instance, _resSwitch, _resCase, _page, AnaApplyCoreMethodUtil.getString(argument_, _page));
                meta.getStdValues().add(argument_);
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(valueOffset);
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseVar(),
                        _page.getKeyWords().getKeyWordCase(),
                        _value);
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
            }
        }
    }

    private void checkDuplicateListedEnum(AnalyzedPageEl _page, ClassField _classField, String _display) {
        ReachBracedBlock par_ = getParent();
        ReachBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof ReachCaseCondition) {
                ReachCaseCondition c_ = (ReachCaseCondition) first_;
                for (ClassField p: c_.meta.getEnumValues()) {
                    if (_classField.eq(p)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(getFile());
                        un_.setIndexFile(getValueOffset()+ getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                _display,
                                _page.getKeyWords().getKeyWordSwitch());
                        _page.addLocError(un_);
                        addErrorBlock(un_.getBuiltError());
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (ClassField p: meta.getEnumValues()) {
            if (_classField.eq(p)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(getValueOffset()+ getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        _page.getKeyWords().getKeyWordCase(),
                        _display,
                        _page.getKeyWords().getKeyWordSwitch());
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
                return;
            }
        }
    }
    private void checkDuplicateListedValue(AnalyzedPageEl _page, Argument _value) {
        ReachBracedBlock par_ = getParent();
        ReachBlock first_ = par_.getFirstChild();
        while (first_ != this) {
            if (first_ instanceof ReachCaseCondition) {
                ReachCaseCondition c_ = (ReachCaseCondition) first_;
                for (Argument p: c_.meta.getStdValues()) {
                    if (_value.getStruct().sameReference(p.getStruct())) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(getFile());
                        un_.setIndexFile(getValueOffset()+ getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                                _page.getKeyWords().getKeyWordCase(),
                                AnaApplyCoreMethodUtil.getString(_value, _page),
                                _page.getKeyWords().getKeyWordSwitch());
                        _page.addLocError(un_);
                        addErrorBlock(un_.getBuiltError());
                        return;
                    }
                }
            }
            first_ = first_.getNextSibling();
        }
        for (Argument p: meta.getStdValues()) {
            if (_value.getStruct().sameReference(p.getStruct())) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(getValueOffset()+ getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDup(),
                        _page.getKeyWords().getKeyWordCase(),
                        AnaApplyCoreMethodUtil.getString(_value, _page),
                        _page.getKeyWords().getKeyWordSwitch());
                _page.addLocError(un_);
                addErrorBlock(un_.getBuiltError());
                return;
            }
        }
    }

    private void checkInh(boolean _instance, AnaClassArgumentMatching _resSwitch, AnaClassArgumentMatching _resCase, AnalyzedPageEl _page, String _string) {
        Mapping m_ = new Mapping();
        m_.setArg(_resCase);
        m_.setParam(_resSwitch);
        if (!_instance && !AnaInherits.isCorrectOrNumbers(m_, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(valueOffset);
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseValue(),
                    _page.getKeyWords().getKeyWordCase(),
                    _string,
                    StringUtil.join(_resSwitch.getNames(), ExportCst.JOIN_TYPES));
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        }
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        ReachBracedBlock par_ = getParent();
        if (!(par_ instanceof ReachSwitchBlock)) {
            super.reach(_anEl, _page);
            return;
        }
        ReachSwitchBlock s_ = (ReachSwitchBlock) par_;
        if (!s_.isInstance()) {
            super.reach(_anEl, _page);
            return;
        }
        if (instance) {
            processInstance(_anEl, _page);
            return;
        }
        processValues(_anEl, _page);
    }

    private void processValues(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        CustList<ReachCaseCondition> classes_ = previous();
        boolean reachCatch_ = true;
        CustList<OperationNode> childrenNodes_ = childrenNodes();
        for (OperationNode o: childrenNodes_) {
            if (!reachCatch(classes_,o,_anEl,_page)) {
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
    private static boolean reachCatch(CustList<ReachCaseCondition> _classes, OperationNode _o, AnalyzingEl _anEl, AnalyzedPageEl _page) {
        String argType_ = _o.getResultClass().getSingleNameOrEmpty();
        if (argType_.isEmpty()) {
            return true;
        }
        boolean reachCatch_ = true;
        _anEl.setArgMapping(argType_);
        for (ReachCaseCondition c: _classes) {
            if (c.root == null) {
                _anEl.setParamMapping(c.importedType);
                if (_anEl.isCorrectMapping(_page)) {
                    reachCatch_ = false;
                    break;
                }
            }
        }
        return reachCatch_;
    }

    private CustList<OperationNode> childrenNodes() {
        CustList<OperationNode> childrenNodes_;
        if (root instanceof DeclaringOperation) {
            childrenNodes_ = ((DeclaringOperation) root).getChildrenNodes();
        } else {
            childrenNodes_ = new CustList<OperationNode>(root);
        }
        return childrenNodes_;
    }

    private void processInstance(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        CustList<ReachCaseCondition> reachCaseConditions_ = previous();
        _anEl.setArgMapping(importedType);
        boolean reachCatch_ = true;
        for (ReachCaseCondition c : reachCaseConditions_) {
            if (ko(_anEl, _page, c)) {
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

    private boolean ko(AnalyzingEl _anEl, AnalyzedPageEl _page, ReachCaseCondition _other) {
        if (!StringUtil.quickEq(_other.importedType, importedType)) {
            _anEl.setParamMapping(_other.importedType);
            return _anEl.isCorrectMapping(_page);
        }
        return _other.root == null;
    }

    private CustList<ReachCaseCondition> previous() {
        CustList<ReachCaseCondition> classes_ = new CustList<ReachCaseCondition>();
        ReachBlock b_ = getPreviousSibling();
        while (b_ != null) {
            if (b_ instanceof ReachCaseCondition&&!((ReachCaseCondition)b_).importedType.isEmpty()) {
                classes_.add((ReachCaseCondition) b_);
            }
            b_ = b_.getPreviousSibling();
        }
        return classes_;
    }

    public int getValueOffset() {
        return valueOffset;
    }
}
